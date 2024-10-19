import java.io.IOException;

/**
 * Program to provide information on a GPS track stored in a file.
 * 
 * @author Mysha Zaman
 */

public class TrackInfo {

    public static void main(String[] args) {
        // Check if a filename is provided as a command-line argument
        if (args.length != 1) {
            System.out.println("Please provide a filename as a command-line argument.");
            System.exit(1);
        }

        // Get the filename from the command-line argument
        String filename = args[0];

        try {
            // Create a Track object and read data from the file
            Track track = new Track(filename);
            

            // Display track information
            displayTrackInfo(track);
        } catch (IOException | GPSException e) {
            // Handle exceptions, print error message, and exit with a non-zero status
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    // Display information about the GPS track
    private static void displayTrackInfo(Track track) {
        System.out.println(track.size() + " points in track");

        try {
            // Display additional track information
            System.out.println("Lowest point is " + track.lowestPoint());
            System.out.println("Highest point is " + track.highestPoint());
            System.out.printf("Total distance = %.3f km%n", track.totalDistance() / 1000.0);
            System.out.printf("Average speed = %.3f m/s%n", track.averageSpeed());
        } catch (GPSException e) {
            // Handle GPSException if any of the computation methods fail
            System.err.println("Error: " + e.getMessage());
        }
    }
}
