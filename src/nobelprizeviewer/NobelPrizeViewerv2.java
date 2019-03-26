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
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
/**
 *
 * @author Mathew Aloisio, Tam Le, Dylan, Femi, Alyssa.
 */
public class NobelPrizeViewerv2 extends Application {
    public static HashMap<String, Country> COUNTRY_MAP;
    public static ArrayList<Laureate> LAUREATES;
    public static ArrayList<Prize> PRIZES;

    @Override
    public void start(Stage pPrimaryStage) {
        // Initialize JSON data
        StackPane allGend = new StackPane();
        StackPane allCountry = new StackPane();
        StackPane allPrize = new StackPane();
        Scene genders = new Scene(allGend, 200, 200, Color.BLACK);
        Scene country = new Scene(allCountry, 200, 200, Color.BLACK);
        Scene prizes = new Scene(allPrize, 200, 200, Color.BEIGE);
        InitializeData(allGend, allCountry, allPrize);

        // Build UI.
        //StackPane root = new StackPane();


        StackPane allYear = new StackPane();
        StackPane allGenre = new StackPane();

        StackPane allLaureate = new StackPane();

        Scene years = new Scene(allYear, 300, 300, Color.CHOCOLATE);


        Scene genre = new Scene(allGenre, 300, 300, Color.CHOCOLATE);

        Scene laureate = new Scene(allLaureate, 200, 200, Color.BEIGE);


        Button gend = new Button("Gender");
        gend.setOnAction(e -> pPrimaryStage.setScene(genders));
        Button prize = new Button("Prize");
        prize.setOnAction(e -> pPrimaryStage.setScene(prizes));
        Button year = new Button("Years");
        year.setOnAction(e -> pPrimaryStage.setScene(years));
        Button countr = new Button("Country");
        countr.setOnAction(e -> pPrimaryStage.setScene(country));
        Button person = new Button("Laureate");
        person.setOnAction(e -> pPrimaryStage.setScene(laureate));
        Button genres = new Button("Genre");
        genres.setOnAction(e -> pPrimaryStage.setScene(genre));


        Text text1 = new Text(0, 0, "Nobel Prize Database");
        text1.setFill(Color.BEIGE);
        text1.setFont(Font.font(java.awt.Font.SERIF, 15));

        Rectangle categoryPane = new Rectangle(0,0,25,200);
        categoryPane.setFill(Color.WHITE);

        VBox main = new VBox();
        main.getChildren().addAll(gend, prize, year, countr, person, genres);

        Scene scene = new Scene(main, 300, 250, Color.BLACK);

        Button mainScreen1 = new Button("Back to Main Screen");
        mainScreen1.setOnAction(e -> pPrimaryStage.setScene(scene));
        Button mainScreen2 = new Button("Back to Main Screen");
        mainScreen2.setOnAction(e -> pPrimaryStage.setScene(scene));
        Button mainScreen3 = new Button("Back to Main Screen");
        mainScreen3.setOnAction(e -> pPrimaryStage.setScene(scene));
        Button mainScreen4 = new Button("Back to Main Screen");
        mainScreen4.setOnAction(e -> pPrimaryStage.setScene(scene));
        Button mainScreen5 = new Button("Back to Main Screen");
        mainScreen5.setOnAction(e -> pPrimaryStage.setScene(scene));
        Button mainScreen6 = new Button("Back to Main Screen");
        mainScreen6.setOnAction(e -> pPrimaryStage.setScene(scene));

        //sc.getChildren().addAll(categoryPane, title, text1, gend);
        allGend.getChildren().add(mainScreen1);
        allPrize.getChildren().add(mainScreen2);
        allYear.getChildren().add(mainScreen3);
        allCountry.getChildren().add(mainScreen4);
        allLaureate.getChildren().add(mainScreen5);
        allGenre.getChildren().add(mainScreen6);


        pPrimaryStage.setTitle("Nobel prize viewer");
        pPrimaryStage.setScene(scene);
        pPrimaryStage.show();

    }

    // Section: JSON data parsing.
    private static void InitializeData(StackPane allGend, StackPane allCountry, StackPane allPrize) {
        //TODO: Fetching JSON data loading dialog...

        // Fetch JSON data.
        System.out.print("Fetching JSON data...");
        JsonObject countryData = JSONParser.JsonObjectFromURL("http://api.nobelprize.org/v1/country.json");
        JsonObject laureateData = JSONParser.JsonObjectFromURL("http://api.nobelprize.org/v1/laureate.json");
        JsonObject prizeData = JSONParser.JsonObjectFromURL("http://api.nobelprize.org/v1/prize.json");
        System.out.println(" DONE!");

        //TODO: Change dialogtext to parsiing JSON data...

        // Parse JSON data into countries, then laureates, then prizes.
        System.out.print("Parsing JSON data...");
        COUNTRY_MAP = ParseCountries(countryData, allCountry);
        LAUREATES = ParseLaureates(laureateData, COUNTRY_MAP);
        PRIZES = ParsePrizes(prizeData, LAUREATES, allGend, allPrize);
        System.out.println(" DONE!");


    }

    /**
     *  Given a JsonObject of country data, generates a hash map
     * where the country code is the key and the value is an ArrayList
     * of the different names it goes by.
     * @param pData - The JsonObject containing the country data.
     * @return HashMap with all country's keys are the country codes.
     */
    public static HashMap<String, Country> ParseCountries(JsonObject pData, StackPane allCountry) {
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
        /* Adds the countries to the UI
         * Use val instead of val.m_Names for the entire expression and
         * set the ArrayList type to country instead of arraylist for initialization
        */
        ArrayList<String> countries = new ArrayList();
        for(String key: map.keySet()){
            countries.add(key);
        }
           ListView<String> listView2 = new ListView<>(
                FXCollections.observableArrayList(countries)
        );
        allCountry.getChildren().add(listView2);

        return map;
    }

    /**
     *
     * @param pData - The JsonObject containing the laureate data.
     * @param pCountries - A map of valid country-codes and countries.
     * @return
     */
    public static ArrayList<Laureate> ParseLaureates(JsonObject pData, HashMap<String, Country> pCountries) {
        ArrayList<Laureate> list = new ArrayList<>();

         for (JsonElement element : pData.get("laureates").getAsJsonArray()) {
            JsonObject obj = element.getAsJsonObject();
            String birthCountryName = obj.has("bornCountry") ? obj.get("bornCountry").getAsString() : "";
            String deathCountryName = obj.has("diedCountry") ? obj.get("diedCountry").getAsString() : "";

            // Determine born/death country & country name index.
            Country birthCountry = obj.has("bornCountryCode") ? pCountries.get(obj.get("bornCountryCode").getAsString()) : null;
            int birthCountryNameID = 0;
            if (birthCountry != null) {
                for (; birthCountryNameID < birthCountry.m_Names.size(); ++birthCountryNameID) {
                    if (birthCountry.m_Names.get(birthCountryNameID).compareTo(birthCountryName) == 0)
                        break;
                }
            }

            Country deathCountry = obj.has("diedCountryCode") ? pCountries.get(obj.get("diedCountryCode").getAsString()) : null;
            int deathCountryNameID = 0;
            if (deathCountry != null) {
                for (; deathCountryNameID < deathCountry.m_Names.size(); ++deathCountryNameID) {
                    if (deathCountry.m_Names.get(deathCountryNameID).compareTo(deathCountryName) == 0)
                        break;
                }
            }

            // Create a new laureate and add them to the list.
            list.add(new Laureate(
                obj.get("id").getAsInt(),
                obj.has("firstname") ? obj.get("firstname").getAsString() : "",
                obj.has("surname") ? obj.get("surname").getAsString() : "",
                Util.GetDateFromString(obj.has("born") ? obj.get("born").getAsString() : "0000-00-00"),
                obj.has("bornCity") ? obj.get("bornCity").getAsString() : "",
                birthCountry,
                birthCountryNameID,
                Util.GetDateFromString(obj.has("died") ? obj.get("died").getAsString() : "0000-00-00"),
                obj.has("diedCity") ? obj.get("diedCity").getAsString() : "",
                deathCountry,
                deathCountryNameID,
                Laureate.GetGenderFromString(obj.get("gender").getAsString())
            ));
         }

        return list;
    }

    public static ArrayList<Prize> ParsePrizes(JsonObject pData, ArrayList<Laureate> pLaureates, StackPane allGend, StackPane allPrize) {
        ArrayList<Prize> list = new ArrayList<>();

        ListView<Laureate> listView = new ListView<>(
                FXCollections.observableArrayList(pLaureates)
        );
        allGend.getChildren().add(listView);



        for (JsonElement element : pData.get("prizes").getAsJsonArray()) {

            JsonObject obj = element.getAsJsonObject();
            // Generate list of awardees for this prize.
            ArrayList<LaureateEntry> awardees = new ArrayList<>();
            if (obj.has("laureates")) {
                for (JsonElement subElement : obj.get("laureates").getAsJsonArray()) {
                    JsonObject laureateObj = subElement.getAsJsonObject();
                    Laureate laureate = Laureate.GetLaureateByID(pLaureates, laureateObj.get("id").getAsInt());

                    awardees.add(new LaureateEntry(
                        laureate,
                        laureateObj.has("share") ? laureateObj.get("share").getAsInt() : 10,
                        laureateObj.has("motivation") ? laureateObj.get("motivation").getAsString() : ""
                    ));
                }
            }

            // Add new prize to the list.
            Prize prize = new Prize(
                obj.get("year").getAsInt(),                                                     // Year.
                Prize.GetCategoryFromString(obj.get("category").getAsString()),                 // Category.
                obj.has("overallMotivation") ? obj.get("overallMotivation").getAsString() : "", // Motivation.
                awardees                                                                        // Laureates.
            );
            list.add(prize);

            // For each awardee, add the award to their list of prizes.
            awardees.forEach((entry) -> {
                entry.m_Laureate.m_Prizes.add(prize);
            });
        }
        /*
         * Add the prizes to the prize category in the UI
         * Some problems here
        */

         ListView<Prize> listView3 = new ListView<>(
                FXCollections.observableArrayList(list)
        );
        allPrize.getChildren().add(listView3);

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
