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
 *
 * @author Mathew Aloisio
 */
public class JSONParser {
    public static String URLToDataString(String pURL) {
        StringBuilder builder = new StringBuilder();
         try {
            // Open URL, create input stream reader and scanner to read from page.
            URL url = new URL(pURL);
            Scanner scanner = new Scanner(new InputStreamReader(url.openStream()));

            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
                if (scanner.hasNextLine())
                    builder.append('\n');
            }
            
            // Close scanner.
            try {
                scanner.close();
            }
            catch(IllegalStateException pException) { System.out.println(pException.toString()); }
        }
        catch (MalformedURLException pException) {
            System.out.println("Unable to connect via URL...\n" + pException.toString());
        }
        catch (IOException pException) {
            System.out.println("IOException!\n" + pException.toString());
        }
         
         return builder.toString();
    }
    
    public static JsonObject JsonObjectFromURL(String pURL) {
        String jsonData = URLToDataString(pURL);
        JsonParser parser = new JsonParser();
        
        return parser.parse(jsonData).getAsJsonObject();
    }
}
