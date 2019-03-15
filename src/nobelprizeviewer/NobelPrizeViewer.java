/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nobelprizeviewer;

import java.util.HashMap;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import com.google.gson.*;

import JSONParser.*;
import com.google.gson.JsonElement;
import java.util.Map;

/**
 *
 * @author raloi
 */
public class NobelPrizeViewer extends Application {
    @Override
    public void start(Stage pPrimaryStage) {
        // Initialize JSON data.
        InitializeData();
        
        // Build UI.
        StackPane root = new StackPane();

        Scene scene = new Scene(root, 300, 250);
        
        pPrimaryStage.setTitle("Nobel prize viewer");
        pPrimaryStage.setScene(scene);
        pPrimaryStage.show();
    }
    
    // Section: JSON data parsing.
    private static void InitializeData() {
        //TODO: Fetching JSON data loading dialog...
        
        // Fetch JSON data.
        System.out.print("Fetching JSON data...");
        JsonObject countryData = JSONParser.JsonObjectFromURL("http://api.nobelprize.org/v1/country.json");
        JsonObject peopleData = JSONParser.JsonObjectFromURL("http://api.nobelprize.org/v1/laureate.json");
        JsonObject prizeData = JSONParser.JsonObjectFromURL("http://api.nobelprize.org/v1/prize.json");
        System.out.println(" DONE!");
        
        //TODO: Change dialogtext to parsiing JSON data...
        
        // Parse JSON data into countries, then persons, then prizes.
        System.out.print("Parsing JSON data...");
        HashMap<String, Country> countryMap = ParseCountries(countryData);
        ArrayList<Person> people = ParsePeople(peopleData, countryMap);
        ArrayList<Prize> prizes = ParsePrizes(prizeData, people);
        System.out.println(" DONE!");
    }
    
    /**
     *  Given a JsonObject of country data, generates a hash map
     * where the country code is the key and the value is an ArrayList
     * of the different names it goes by.
     * @param pData - The JsonObject containing the country data.
     * @return HashMap with all country's keys are the country codes.
     */
    public static HashMap<String, Country> ParseCountries(JsonObject pData) {
        HashMap<String, Country> map = new HashMap<>();
        for (JsonElement element : pData.get("countries").getAsJsonArray()) {
            JsonObject obj = element.getAsJsonObject();
            String countryCode = obj.get("code").getAsString();
            
            // Add the country to the map if neccesary.
            if (!map.containsKey(countryCode))
                map.put(countryCode, new Country(countryCode));
            Country country = map.get(countryCode);
            
            // Populate the country with possible names.
            String countryName = obj.get("name").getAsString();
            if (!country.m_Names.contains(countryName))
                country.m_Names.add(countryName);
        }
        
        return map;
    }
    
    /**
     * 
     * @param pData - The JsonObject containing the laureate data.
     * @param pCountries - A map of valid country-codes and countries.
     * @return 
     */
    public static ArrayList<Person> ParsePeople(JsonObject pData, HashMap<String, Country> pCountries) {
        ArrayList<Person> list = new ArrayList<>();
        
        //TODO
        
        return list;
    }
    
    public static ArrayList<Prize> ParsePrizes(JsonObject pData, ArrayList<Person> pPeople) {
        ArrayList<Prize> list = new ArrayList<>();
        
        //TODO
        
        return list;
    }
    // End of section: JSON data parsing.

    /**
     * @param pArgs the command line arguments
     */
    public static void main(String[] pArgs) {
        launch(pArgs);
        // WARNNG: Code under launch(pArgs) WILL NOT execute til the program is about to close, see 'start()'.
    }
    
}
