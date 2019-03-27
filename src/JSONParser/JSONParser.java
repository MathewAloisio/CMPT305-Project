/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSONParser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Class that accepts a URL, gets JSON data as a string, then parses the string
 * into a JSON object
 * @author Mathew Aloisio
 */
public class JSONParser {
    /**
     * Get the JSON from the URL as a string
     * @param pURL - URL to get data from
     * @return The JSON data as a string (needs to be parsed)
     */
    public static String URLToDataString(String pURL) {
        StringBuilder builder = new StringBuilder();
        
        try {
            // Open URL
            URL url = new URL(pURL);
            // Create scanner from an input stream reader to read from the URL
            Scanner scanner = new Scanner(new InputStreamReader(url.openStream()));
            
            // Add each line from the URL to a StringBuilder
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
                
                if (scanner.hasNextLine())
                    builder.append('\n');
            }
            
            try {
                scanner.close();
            }
            // Error if the scanner can't be closed
            catch(IllegalStateException pException) { System.out.println(pException.toString()); }
        }
        // More required error handling
        catch (MalformedURLException pException) {
            System.out.println("Unable to connect via URL...\n" + pException.toString());
        }
        catch (IOException pException) {
            System.out.println("IOException!\n" + pException.toString());
        }
         
        return builder.toString();
    }
    
    /**
     * Get data from a URL, convert to a string, then convert to a JSON object
     * @param pURL URL to get data from
     * @return JSON object from the URL
     */
    public static JsonObject JsonObjectFromURL(String pURL) {
        String jsonData = URLToDataString(pURL);
        JsonParser parser = new JsonParser();
        
        return parser.parse(jsonData).getAsJsonObject();
    }
}
