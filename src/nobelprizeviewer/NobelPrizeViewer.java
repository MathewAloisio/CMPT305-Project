package nobelprizeviewer;

import nobelprizeviewer.Models.*;
import nobelprizeviewer.Views.*;

import java.io.IOException;
import java.io.File;

import java.util.HashMap;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

import com.google.gson.*;

import JSONParser.*;
import com.google.gson.JsonElement;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

/**
 * Class for fetching JSON data from the API, converting it to Java classes, 
 * and initializing views.
 * @author Mathew Aloisio, Tam Le, Dylan, Femi, Alyssa.
 */
public class NobelPrizeViewer extends Application {
    public static HashMap<String, Country> COUNTRY_MAP;
    public static ArrayList<Laureate> LAUREATES;
    public static ArrayList<Prize> PRIZES;
    public static Scene BIOGRAPHY_SCENE;
    public static Image LOADING_IMAGE = null;
    
    @Override
    public void start(Stage pPrimaryStage) throws Exception {
        // Initialize JSON data.
        InitializeData();

        // Load loading image.
        BufferedImage bufferedLoadingImage = null;
        try {
            bufferedLoadingImage = ImageIO.read(new File("assets/loading.gif"));
        }
        catch (IOException pException) {
            System.out.println("IOException! Failed to find image for \"" + toString() + "\".\n" +  pException.toString());
        }
        if (bufferedLoadingImage != null)
            LOADING_IMAGE = SwingFXUtils.toFXImage(bufferedLoadingImage, null);
        
        // Build UIOverviewPage and scene.
        UIOverviewPage uiOverviewPage = new UIOverviewPage(pPrimaryStage);
        uiOverviewPage.Initialize(LAUREATES, PRIZES, COUNTRY_MAP);
        Scene scene = new Scene(uiOverviewPage, 1024, 800);
        
        // Build UIBiographyPage and scene.
        UIBiographyPage biographyPage = new UIBiographyPage(pPrimaryStage, scene);
        biographyPage.Initialize();
        BIOGRAPHY_SCENE = new Scene(biographyPage, 1024, 800);
        
        pPrimaryStage.setTitle("Nobel Prize Viewer");
        pPrimaryStage.setMaximized(true);
        pPrimaryStage.setScene(scene);
        pPrimaryStage.show();
    }
    
    // Section: JSON data parsing.
    /**
     * Get JSON data from the API and parse it
     */
    private static void InitializeData() {
        // Fetch JSON data.
        System.out.print("Fetching JSON data...");
        JsonObject countryData = JSONParser.JsonObjectFromURL("http://api.nobelprize.org/v1/country.json");
        JsonObject laureateData = JSONParser.JsonObjectFromURL("http://api.nobelprize.org/v1/laureate.json");
        JsonObject prizeData = JSONParser.JsonObjectFromURL("http://api.nobelprize.org/v1/prize.json");
        System.out.println(" DONE!");

        // Parse JSON data into countries, then laureates, then prizes.
        System.out.print("Parsing JSON data...");
        COUNTRY_MAP = ParseCountries(countryData);
        LAUREATES = ParseLaureates(laureateData, COUNTRY_MAP);
        PRIZES = ParsePrizes(prizeData, LAUREATES);
        System.out.println(" DONE!");
    }
    
    /**
     * Given a JsonObject of country data, generates a hash map where
     * the country code is the key and the value is the Country for that key
     * @param pData - The JsonObject containing the country data.
     * @return HashMap with all country's keys as the country codes and country
     * names as values.
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
     * Given a JsonObject of laureate data and a Map of the countries, generates
     * a list containing the laureates
     * @param pData - The JsonObject containing the laureate data.
     * @param pCountries - A map of valid country-codes and countries.
     * @return ArrayList with all the laureates
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
            
            Laureate laureate = new Laureate(
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
            );
            
            // Parse affiliations.
            if (obj.has("prizes")) {
                for (JsonElement prizeElement : obj.get("prizes").getAsJsonArray()) {
                    JsonObject prizeObj = prizeElement.getAsJsonObject();
                    if (!prizeObj.has("affiliations"))
                        continue;
                    
                    for (JsonElement affiliationElement : prizeObj.get("affiliations").getAsJsonArray()) {
                        if (affiliationElement.isJsonObject()) {
                            JsonObject affiliationObj = affiliationElement.getAsJsonObject();
                            laureate.m_PrizeAffiliations.add(new Affiliation(
                                affiliationObj.has("name") ? affiliationObj.get("name").getAsString() : "",
                                affiliationObj.has("city") ? affiliationObj.get("city").getAsString() : "",
                                affiliationObj.has("country") ? affiliationObj.get("country").getAsString() : ""
                            ));
                        }
                    }
                }
            }
                
            // Create a new laureate and add them to the list.
            list.add(laureate);
        }
        
        return list;
    }   
    
    /**
     * Given a JsonObject of prize data and a list of laureates, generates a 
     * list of prizes
     * @param pData - The JsonObject containing the prize data
     * @param pLaureates - List containing the laureates
     * @return ArrayList with all the prizes, where each prize has a year, category,
     * motivation, and list of awardees
     */
    public static ArrayList<Prize> ParsePrizes(JsonObject pData, ArrayList<Laureate> pLaureates) {
        ArrayList<Prize> list = new ArrayList<>();

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
