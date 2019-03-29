package nobelprizeviewer.Views;

import java.util.HashMap;
import java.util.ArrayList;

import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.collections.*;
import javafx.beans.value.ObservableValue;

import nobelprizeviewer.Models.*;

public class UIOverviewPage extends SplitPane {
    protected final AnchorPane filterPane;
    protected final Text genderText;
    protected final CheckBox genderCheckbox_Male;
    protected final CheckBox genderCheckbox_Female;
    protected final Text yearMinText;
    protected final Text yearMaxText;
    protected final Text yearMinSliderText;
    protected final Text yearMaxSliderText;
    protected final Slider yearMinSlider;
    protected final Slider yearMaxSlider;
    protected final ListView<String> countryCodeListView;
    protected final Hyperlink countryCodeListSelectAll;
    protected final Hyperlink countryCodeListDeselectAll;
    protected final Text countryCodeText;
    protected final TextField affiliationTextField;
    protected final Text affiliationText;
    protected final Text prizeCategoryText;
    protected final ListView<String> prizeCategoryListView;
    protected final Hyperlink prizeCategoryListSelectAll;
    protected final Hyperlink prizeCategoryListDeselectAll;
    protected final Button searchButton;
    protected final AnchorPane displayPane;
    protected final Pagination displayPage;
    
    protected ArrayList<Prize> prizeList;

    public UIOverviewPage() {
        filterPane = new AnchorPane();
        genderText = new Text();
        genderCheckbox_Male = new CheckBox();
        genderCheckbox_Female = new CheckBox();
        yearMinText = new Text();
        yearMaxText = new Text();
        yearMinSliderText = new Text();
        yearMaxSliderText = new Text();
        
        yearMinSlider = new Slider();
        yearMaxSlider = new Slider();
        countryCodeListView = new ListView<>();
        countryCodeListSelectAll = new Hyperlink();
        countryCodeListDeselectAll = new Hyperlink();
        countryCodeText = new Text();
        affiliationTextField = new TextField();
        affiliationText = new Text();
        prizeCategoryText = new Text();
        prizeCategoryListView = new ListView<>();
        prizeCategoryListSelectAll = new Hyperlink();
        prizeCategoryListDeselectAll = new Hyperlink();
        searchButton = new Button();
        displayPane = new AnchorPane();
        displayPage = new Pagination();
    }
    
    /**
     * Initializes the UIOverviewPage menu by building the UI elements based on the provided laureate and prize data.
     * @param pLaureates
     * @param pPrizes 
     * @param pCountries
     */
    public void Initialize(ArrayList<Laureate> pLaureates, ArrayList<Prize> pPrizes, HashMap<String, Country> pCountries) {
        prizeList = pPrizes;
        
        // Calculate min year, max year.
        int minYear = 0;
        int maxYear = 0;
        for (Prize prize : pPrizes) {
            if (prize.m_Year < minYear)
                minYear = prize.m_Year;
            if (prize.m_Year > maxYear)
                maxYear = prize.m_Year;
        }
        
        ObservableList<String> countryCodes = FXCollections.observableArrayList();
        for (HashMap.Entry<String, Country> entry : pCountries.entrySet()) {
            String key = entry.getKey();
            if (!key.isEmpty())
                key = key + " (" + entry.getValue().m_Names.get(0) + ")";
            countryCodes.add(key);
        }
        
        ObservableList<String> prizeCategories = FXCollections.observableArrayList();
        for (PrizeCategory category : PrizeCategory.values()) {
            prizeCategories.add(category.toString());
        }
        
        // Build filterPane UI elements.
        setDividerPositions(0.3);
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefWidth(1280.0);
        setPrefHeight(800.0);

        filterPane.setMinHeight(0.0);
        filterPane.setMinWidth(0.0);
        filterPane.setPrefHeight(160.0);
        filterPane.setPrefWidth(100.0);

        Font fontBold18 = new Font("System Bold", 18.0);
        genderText.setLayoutX(120.0);
        genderText.setLayoutY(40.0);
        genderText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        genderText.setStrokeWidth(0.0);
        genderText.setText("Gender:");
        genderText.setFont(fontBold18);

        Font fontDefault18 = new Font(18.0);
        genderCheckbox_Male.setLayoutX(115.0);
        genderCheckbox_Male.setLayoutY(48.0);
        genderCheckbox_Male.setMnemonicParsing(false);
        genderCheckbox_Male.setText("Male");
        genderCheckbox_Male.setFont(fontDefault18);
        genderCheckbox_Male.setSelected(true);

        genderCheckbox_Female.setLayoutX(114.0);
        genderCheckbox_Female.setLayoutY(76.0);
        genderCheckbox_Female.setMnemonicParsing(false);
        genderCheckbox_Female.setText("Female");
        genderCheckbox_Female.setFont(fontDefault18);
        genderCheckbox_Female.setSelected(true);

        Font fontBold14 = new Font("System Bold", 14.0);
        yearMinText.setLayoutX(65.0);
        yearMinText.setLayoutY(125.0);
        yearMinText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        yearMinText.setStrokeWidth(0.0);
        yearMinText.setText("Year Min.");
        yearMinText.setFont(fontBold14);

        yearMaxText.setLayoutX(190.0);
        yearMaxText.setLayoutY(125.0);
        yearMaxText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        yearMaxText.setStrokeWidth(0.0);
        yearMaxText.setText("Year Max.");
        yearMaxText.setFont(fontBold14);
        
        Font fontDefault12 = new Font(12.0);
        yearMinSliderText.setLayoutX(80.0);
        yearMinSliderText.setLayoutY(140.0);
        yearMinSliderText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        yearMinSliderText.setStrokeWidth(0.0);
        yearMinSliderText.setText("(" + Integer.toString(minYear) + ")");
        yearMinSliderText.setFont(fontDefault12);

        yearMaxSliderText.setLayoutX(205.0);
        yearMaxSliderText.setLayoutY(140.0);
        yearMaxSliderText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        yearMaxSliderText.setStrokeWidth(0.0);
        yearMaxSliderText.setText("(" + Integer.toString(maxYear) + ")");
        yearMaxSliderText.setFont(fontDefault12);

        yearMinSlider.setLayoutX(20.0);
        yearMinSlider.setLayoutY(148.0);
        yearMinSlider.setMax(maxYear);
        yearMinSlider.setMin(minYear);
        yearMinSlider.setValue(minYear);
        yearMinSlider.setMajorTickUnit(1);
        yearMinSlider.setMajorTickUnit(1);
        yearMinSlider.setBlockIncrement(1);
        yearMinSlider.valueProperty().addListener((ObservableValue<? extends Number> pObservable, Number pOldValue, Number pNewValue) -> {
            yearMinSliderText.setText("(" + Integer.toString(pNewValue.intValue()) + ")");
        });

        yearMaxSlider.setLayoutX(160.0);
        yearMaxSlider.setLayoutY(148.0);
        yearMaxSlider.setMax(maxYear);
        yearMaxSlider.setMin(minYear);
        yearMaxSlider.setValue(maxYear);
        yearMaxSlider.setMajorTickUnit(1);
        yearMaxSlider.setMajorTickUnit(1);
        yearMaxSlider.setBlockIncrement(1);
        yearMaxSlider.valueProperty().addListener((ObservableValue<? extends Number> pObservable, Number pOldValue, Number pNewValue) -> {
            yearMaxSliderText.setText("(" + Integer.toString(pNewValue.intValue()) + ")");
        });

        Font fontDefault10 = new Font(10);
        countryCodeListSelectAll.setLayoutX(50.0);
        countryCodeListSelectAll.setLayoutY(190.0);
        countryCodeListSelectAll.setText("select all");
        countryCodeListSelectAll.setFont(fontDefault10);
        countryCodeListSelectAll.setOnMouseClicked((MouseEvent pEvent) -> {
            if (pEvent.getButton() == MouseButton.PRIMARY) {
                // Select all country codes.
                countryCodeListView.getSelectionModel().selectAll();
            }
        });
        
        countryCodeListDeselectAll.setLayoutX(195.0);
        countryCodeListDeselectAll.setLayoutY(190.0);
        countryCodeListDeselectAll.setText("deselect all");
        countryCodeListDeselectAll.setFont(fontDefault10);
        countryCodeListDeselectAll.setOnMouseClicked((MouseEvent pEvent) -> {
            if (pEvent.getButton() == MouseButton.PRIMARY) {
                // Deselect all country codes.
                countryCodeListView.getSelectionModel().clearSelection();
            }
        });
        
        countryCodeListView.setLayoutX(50.0);
        countryCodeListView.setLayoutY(210.0);
        countryCodeListView.setPrefHeight(160.0);
        countryCodeListView.setPrefWidth(200.0);
        countryCodeListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        countryCodeListView.setItems(countryCodes);

        countryCodeText.setLayoutX(80.0);
        countryCodeText.setLayoutY(190.0);
        countryCodeText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        countryCodeText.setStrokeWidth(0.0);
        countryCodeText.setText("Country Codes:");
        countryCodeText.setFont(fontBold18);

        prizeCategoryText.setLayoutX(75.0);
        prizeCategoryText.setLayoutY(400.0);
        prizeCategoryText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        prizeCategoryText.setStrokeWidth(0.0);
        prizeCategoryText.setText("Prize Category(s):");
        prizeCategoryText.setFont(fontBold18);
        
        prizeCategoryListSelectAll.setLayoutX(50.0);
        prizeCategoryListSelectAll.setLayoutY(400.0);
        prizeCategoryListSelectAll.setText("select all");
        prizeCategoryListSelectAll.setFont(fontDefault10);
        prizeCategoryListSelectAll.setOnMouseClicked((MouseEvent pEvent) -> {
            if (pEvent.getButton() == MouseButton.PRIMARY) {
                // Select all prize categories.
                prizeCategoryListView.getSelectionModel().selectAll();
            }
        });
        
        prizeCategoryListDeselectAll.setLayoutX(195.0);
        prizeCategoryListDeselectAll.setLayoutY(400.0);
        prizeCategoryListDeselectAll.setText("deselect all");
        prizeCategoryListDeselectAll.setFont(fontDefault10);
        prizeCategoryListDeselectAll.setOnMouseClicked((MouseEvent pEvent) -> {
            if (pEvent.getButton() == MouseButton.PRIMARY) {
                // Deselect all prize categories.
                prizeCategoryListView.getSelectionModel().clearSelection();
            }
        });

        prizeCategoryListView.setLayoutX(50.0);
        prizeCategoryListView.setLayoutY(420.0);
        prizeCategoryListView.setPrefHeight(120.0);
        prizeCategoryListView.setPrefWidth(200.0);
        prizeCategoryListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        prizeCategoryListView.setItems(prizeCategories);

        affiliationTextField.setLayoutX(70.0);
        affiliationTextField.setLayoutY(570.0);

        affiliationText.setLayoutX(100.0);
        affiliationText.setLayoutY(560.0);
        affiliationText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        affiliationText.setStrokeWidth(0.0);
        affiliationText.setText("Affiliation");
        affiliationText.setFont(fontBold18);
        
        searchButton.setLayoutX(100.0);
        searchButton.setLayoutY(610.0);
        searchButton.setMnemonicParsing(false);
        searchButton.setText("Search");
        searchButton.setFont(fontDefault18);
        searchButton.setOnMouseClicked((MouseEvent pEvent) -> {
            if (pEvent.getButton() == MouseButton.PRIMARY) {
                // TODO: Update displayPane, populate with laureates.
                ArrayList<Laureate> laureates = GetLaureates();
                for (Laureate laureate : laureates) //test code.
                    System.out.println(laureate.toString());
            }
        });

        // Build displayPane UI elements.
        displayPane.setMinHeight(0.0);
        displayPane.setMinWidth(0.0);
        displayPane.setPrefHeight(160.0);
        displayPane.setPrefWidth(100.0);

        AnchorPane.setBottomAnchor(displayPage, 0.0);
        AnchorPane.setLeftAnchor(displayPage, 0.0);
        AnchorPane.setRightAnchor(displayPage, 0.0);
        AnchorPane.setTopAnchor(displayPage, 0.0);
        displayPage.setLayoutX(76.0);
        displayPage.setLayoutY(62.0);
        displayPage.setPageCount(1);
        displayPage.setPrefHeight(738.0);
        displayPage.setPrefWidth(810.0);

        // Populate filterPane.
        filterPane.getChildren().add(genderText);
        filterPane.getChildren().add(genderCheckbox_Male);
        filterPane.getChildren().add(genderCheckbox_Female);
        filterPane.getChildren().add(yearMinText);
        filterPane.getChildren().add(yearMaxText);
        filterPane.getChildren().add(yearMinSliderText);
        filterPane.getChildren().add(yearMaxSliderText);
        filterPane.getChildren().add(yearMinSlider);
        filterPane.getChildren().add(yearMaxSlider);
        filterPane.getChildren().add(countryCodeListView);
        filterPane.getChildren().add(countryCodeListSelectAll);
        filterPane.getChildren().add(countryCodeListDeselectAll);
        filterPane.getChildren().add(countryCodeText);
        filterPane.getChildren().add(affiliationTextField);
        filterPane.getChildren().add(affiliationText);
        filterPane.getChildren().add(prizeCategoryText);
        filterPane.getChildren().add(prizeCategoryListView);
        filterPane.getChildren().add(prizeCategoryListSelectAll);
        filterPane.getChildren().add(prizeCategoryListDeselectAll);
        filterPane.getChildren().add(searchButton);
        getItems().add(filterPane);
        
        // Populate displayPane.
        displayPane.getChildren().add(displayPage);
        getItems().add(displayPane);
    }
    
    public ArrayList<Laureate> GetLaureates() {
        // Determine which category to show.
        ArrayList<PrizeCategory> validCategories = new ArrayList<>();
        ObservableList<String> selectedCategories = prizeCategoryListView.getSelectionModel().getSelectedItems();
        selectedCategories.forEach((String pCategoryName) -> {
            validCategories.add(PrizeCategory.valueOf(pCategoryName));
        });
        
        // Determine which country codes to show.
        ArrayList<String> validCountryCodes = new ArrayList<>();
        ObservableList<String> selectedCountryCodes = countryCodeListView.getSelectionModel().getSelectedItems();
        selectedCountryCodes.forEach((String pCountryName) -> {
            pCountryName = (String)pCountryName;
            if (!pCountryName.isEmpty()) {
                validCountryCodes.add(pCountryName.split(" ")[0]);
            }
            else { validCountryCodes.add(pCountryName); }
        });
        
        // Determine which genders to search for.
        boolean searchMen = genderCheckbox_Male.isSelected();
        boolean searchWomen = genderCheckbox_Female.isSelected();
        // boolean searchOrgs = genderCehckbox_Organization.isSelected();
        if (!searchMen && !searchWomen /*&& !searchOrgs*/) { // If neither box is checked, it is assumed the user wants to allow all genders.
            searchMen = true;
            searchWomen = true;
            //searchOrgs = true;
        }
        
        // Determine which people apply to the given filters.
        ArrayList<Laureate> laureates = new ArrayList<>();
        for (Prize prize : prizeList) {
            // Check category.
            if (!selectedCategories.isEmpty()) {
                boolean categoryMatch = false;
                for (PrizeCategory category : validCategories) {
                    if (category == prize.m_Category) {
                        categoryMatch = true;
                        break;
                    }
                }
                if (!categoryMatch) continue; // Skip if no category match.
            }
            
            // Check year range.
            if (prize.m_Year > (int)yearMaxSlider.getValue() || prize.m_Year < (int)yearMinSlider.getValue()) continue;
            
            // Check if the laureate should be added to the arraylist.
            for (LaureateEntry entry : prize.m_Laureates) {
                // Check for country code match.
                boolean countryCodeMatch = false;
                if (!selectedCountryCodes.isEmpty()) {
                    for (String countryCode : validCountryCodes) {
                        if (entry.m_Laureate.m_BornCountry.m_Code.compareTo(countryCode) == 0
                            || entry.m_Laureate.m_DeathCountry.m_Code.compareTo(countryCode) == 0
                        ) {
                            countryCodeMatch = true;
                            break;
                        }
                    }
                }
                else { countryCodeMatch = true; }
                
                // Check for gender match.
                boolean genderMatch = false;
                if (searchMen || searchWomen /*|| searchOrgs*/) {
                    if ((searchMen && entry.m_Laureate.m_Gender == Gender.MALE)
                            || (searchWomen && entry.m_Laureate.m_Gender == Gender.FEMALE)
                            /*|| (searchOrgs && entry.m_Laureate.m_Gender == Gender.ORGANIZATION)*/
                    ) genderMatch = true;
                }
                else { genderMatch = true; } // No genders selected, automatic match.
                
                // Check for affiliation match.
                String affiliationEntry = affiliationTextField.getCharacters().toString();
                boolean affiliationMatch = affiliationEntry.isEmpty();
                //TODO: affiliation match checking.
                
                if (countryCodeMatch && genderMatch && affiliationMatch)
                    laureates.add(entry.m_Laureate);
            }
        }
        
        return laureates;
    }
}
