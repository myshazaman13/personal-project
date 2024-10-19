import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.temporal.ChronoUnit;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *  Represents a point in space and time, recorded by a GPS sensor.
 * 
 * @author Mysha Zaman
 */


public class Track {
    private final List<Point> points;

    public Track() {
        this.points = new ArrayList<>();
    }

    public Track(String filename) throws IOException {
        this();
        readFile(filename);
    }

        public void readFile(String filename) throws IOException {
        Path path = Paths.get(filename);

        points.clear();

        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                if (values.length >= 4) {
                    // Parse values and create a new Point
                    ZonedDateTime time = ZonedDateTime.parse(values[0]);
                    double longitude = Double.parseDouble(values[1]);
                    double latitude = Double.parseDouble(values[2]);
                    double elevation = Double.parseDouble(values[3]);
                    Point point = new Point(time, longitude, latitude, elevation);
                    points.add(point);
                } else {
                    // Handle invalid data by throwing GPSException
                    throw new GPSException("Invalid data in the file");
                }
            }
        }
    }

    public int size() {
        return points.size();
    }

    public Point get(int index) {
        if (index < 0 || index >= size()) {
            throw new GPSException("Index out of bounds");
        }
        return points.get(index);
    }

    public void add(Point point) {
        points.add(point);
    }

    public Point lowestPoint() throws GPSException {
        validateNotEmpty();

        Point lowest = points.get(0);

        for (Point point : points) {
            if (point.getElevation() < lowest.getElevation()) {
                lowest = point;
            }
        }

        return lowest;
    }

    public Point highestPoint() throws GPSException {
        validateNotEmpty();

        Point highest = points.get(0);

        for (Point point : points) {
            if (point.getElevation() > highest.getElevation()) {
                highest = point;
            }
        }

        return highest;
    }

    private void validateNotEmpty() throws GPSException {
    if (points == null || points.isEmpty()) {
        throw new GPSException("Track is empty");
    }
    }


    // Calculate the total distance traveled
    public double totalDistance() throws GPSException {
        if (points.size() < 2) {
            throw new GPSException("Not enough points to calculate total distance");
        }

        double totalDistance = 0.0;

        for (int i = 0; i < points.size() - 1; i++) {
            totalDistance += Point.greatCircleDistance(points.get(i), points.get(i + 1));
        }

        return totalDistance;
    }

    // Calculate the average speed
    public double averageSpeed() throws GPSException {
        if (points.size() < 2) {
            throw new GPSException("Not enough points to calculate average speed");
        }

        ZonedDateTime startTime = points.get(0).getTime();
        ZonedDateTime endTime = points.get(points.size() - 1).getTime();

        double totalTime = ChronoUnit.SECONDS.between(startTime, endTime);

        if (totalTime <= 0) {
        throw new GPSException("Total time is not positive, cannot calculate average speed");
        }

        return totalDistance() / totalTime;
    }



     public void writeKML(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Write KML header
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n");
            writer.write("<Document>\n");

            // Write KML placemarks for each point
            for (Point point : points) {
                writer.write("<Placemark>\n");
                writer.write("<Point>\n");
                writer.write("<coordinates>" +
                point.getLongitude() + "," +
                point.getLatitude() + "," +
                point.getElevation() +
                "</coordinates>\n");
                writer.write("</Point>\n");
                writer.write("</Placemark>\n");
            }

            // Write KML footer
            writer.write("</Document>\n");
            writer.write("</kml>\n");
        }
    }
}

