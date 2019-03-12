/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsontest;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Tam Le
 */
public class GetDataFromURL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Create a URL
            URL url = new URL("http://api.nobelprize.org/v1/prize.json");
            
            // Use an InputStream to read from the URL
            InputStream stream = url.openStream();
            
            // And use a Scanner to read from InputStream
            Scanner scan = new Scanner(stream);
            
            // Scanner's next line will contain data from the API
            while (scan.hasNextLine()) {
                System.out.println(scan.nextLine());
            }
        // Error checking stuff
        } catch (MalformedURLException pException) {
            System.out.println("Unable to connect via URL...\n" + pException.toString());
        } catch (IOException pException) {
            System.out.println("IOException!\n" + pException.toString());
        }
    }
}
