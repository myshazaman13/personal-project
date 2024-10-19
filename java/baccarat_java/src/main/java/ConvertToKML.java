/**
 * Program to general a KML file from GPS track data stored in a file,
 * for the Advanced task of COMP1721 Coursework 1.
 *
 * @author Mysha Zaman
 */

import java.io.IOException;

public class ConvertToKML {
  public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: ConvertToKML <input_csv_file> <output_kml_file>");
            return;
        }

        String inputCsvFile = args[0];
        String outputKmlFile = args[1];

        try {
            Track track = new Track(inputCsvFile);
            track.writeKML(outputKmlFile);
            System.out.println("Conversion completed successfully.");
        } catch (IOException | GPSException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}