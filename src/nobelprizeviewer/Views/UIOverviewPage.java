package nobelprizeviewer.Views;

import PageCache.PageCache;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;

import nobelprizeviewer.Models.*;

public class UIOverviewPage extends SplitPane {
    protected final AnchorPane filterPane;
    protected final Text noResultsText;
    protected final Text genderText;
    protected final CheckBox genderCheckbox_Male;
    protected final CheckBox genderCheckbox_Female;
    protected final Text sortByText;
    protected final CheckBox sortByCheckbox_Name;
    protected final CheckBox sortByCheckbox_Gender;
    protected final CheckBox sortByCheckbox_PrizeCount;
    protected final CheckBox sortByCheckbox_CountryCode;
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
    protected final TextField nameTextField;
    protected final Text nameText;
    protected final Text prizeCategoryText;
    protected final ListView<String> prizeCategoryListView;
    protected final Hyperlink prizeCategoryListSelectAll;
    protected final Hyperlink prizeCategoryListDeselectAll;
    protected final Button searchButton;
    protected final AnchorPane displayPane;
    protected final Pagination displayPage;
    protected final Stage primaryStage;
    
    protected ArrayList<Prize> prizeList;
    
    public static final int LAUREATES_PER_ROW = 3;
    public static final int LAUREATES_PER_COLUMN = 3;
    public static final int LAUREATES_PER_PAGE = LAUREATES_PER_ROW * LAUREATES_PER_COLUMN;

    public UIOverviewPage(Stage pPrimaryStage) {
        primaryStage = pPrimaryStage;
        filterPane = new AnchorPane();
        noResultsText = new Text();
        genderText = new Text();
        genderCheckbox_Male = new CheckBox();
        genderCheckbox_Female = new CheckBox();
        sortByText = new Text();
        sortByCheckbox_Name = new CheckBox();
        sortByCheckbox_Gender = new CheckBox();
        sortByCheckbox_PrizeCount = new CheckBox();
        sortByCheckbox_CountryCode = new CheckBox();
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
        nameTextField = new TextField();
        nameText = new Text();
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
        int minYear = 1901;
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
            
            if (!key.isEmpty()) {
                key = key + " (" + entry.getValue().m_Names.get(0) + ")";
                countryCodes.add(key);
            }
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

        Font fontDefault10 = new Font(10);
        Font fontBold14 = new Font("System Bold", 14.0);
        genderText.setLayoutX(70.0);
        genderText.setLayoutY(20.0);
        genderText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        genderText.setStrokeWidth(0.0);
        genderText.setText("Gender:");
        genderText.setFont(fontBold14);
        
        Font fontDefault14 = new Font(14.0);
        genderCheckbox_Male.setLayoutX(65.0);
        genderCheckbox_Male.setLayoutY(28.0);
        genderCheckbox_Male.setMnemonicParsing(false);
        genderCheckbox_Male.setText("Male");
        genderCheckbox_Male.setFont(fontDefault14);
        genderCheckbox_Male.setSelected(true);

        genderCheckbox_Female.setLayoutX(65.0);
        genderCheckbox_Female.setLayoutY(56.0);
        genderCheckbox_Female.setMnemonicParsing(false);
        genderCheckbox_Female.setText("Female");
        genderCheckbox_Female.setFont(fontDefault14);
        genderCheckbox_Female.setSelected(true);
        
        sortByText.setLayoutX(165.0);
        sortByText.setLayoutY(20.0);
        sortByText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        sortByText.setStrokeWidth(0.0);
        sortByText.setText("Sort by:");
        sortByText.setFont(fontBold14);
        
        sortByCheckbox_Name.setLayoutX(200.0);
        sortByCheckbox_Name.setLayoutY(25.0);
        sortByCheckbox_Name.setMnemonicParsing(false);
        sortByCheckbox_Name.setText("Name");
        sortByCheckbox_Name.setFont(fontDefault10);
        sortByCheckbox_Name.setSelected(false);
        sortByCheckbox_Name.selectedProperty().addListener((ObservableValue<? extends Boolean> pObservable, Boolean pOldValue, Boolean pNewValue) -> {
            if (pNewValue == true) {
                sortByCheckbox_Gender.setSelected(false);
                sortByCheckbox_PrizeCount.setSelected(false);
                sortByCheckbox_CountryCode.setSelected(false);
            }
        });
        
        sortByCheckbox_Gender.setLayoutX(200.0);
        sortByCheckbox_Gender.setLayoutY(40.0);
        sortByCheckbox_Gender.setMnemonicParsing(false);
        sortByCheckbox_Gender.setText("Gender");
        sortByCheckbox_Gender.setFont(fontDefault10);
        sortByCheckbox_Gender.setSelected(false);
        sortByCheckbox_Gender.selectedProperty().addListener((ObservableValue<? extends Boolean> pObservable, Boolean pOldValue, Boolean pNewValue) -> {
            if (pNewValue == true) {
                sortByCheckbox_Name.setSelected(false);
                sortByCheckbox_PrizeCount.setSelected(false);
                sortByCheckbox_CountryCode.setSelected(false);
            }
        });
        
        sortByCheckbox_PrizeCount.setLayoutX(200.0);
        sortByCheckbox_PrizeCount.setLayoutY(55.0);
        sortByCheckbox_PrizeCount.setMnemonicParsing(false);
        sortByCheckbox_PrizeCount.setText("Prize Count");
        sortByCheckbox_PrizeCount.setFont(fontDefault10);
        sortByCheckbox_PrizeCount.setSelected(false);
        sortByCheckbox_PrizeCount.selectedProperty().addListener((ObservableValue<? extends Boolean> pObservable, Boolean pOldValue, Boolean pNewValue) -> {
            if (pNewValue == true) {
                sortByCheckbox_Name.setSelected(false);
                sortByCheckbox_Gender.setSelected(false);
                sortByCheckbox_CountryCode.setSelected(false);
            }
        });
        
        sortByCheckbox_CountryCode.setLayoutX(200.0);
        sortByCheckbox_CountryCode.setLayoutY(70.0);
        sortByCheckbox_CountryCode.setMnemonicParsing(false);
        sortByCheckbox_CountryCode.setText("Country Code");
        sortByCheckbox_CountryCode.setFont(fontDefault10);
        sortByCheckbox_CountryCode.setSelected(false);
        sortByCheckbox_CountryCode.selectedProperty().addListener((ObservableValue<? extends Boolean> pObservable, Boolean pOldValue, Boolean pNewValue) -> {
            if (pNewValue == true) {
                sortByCheckbox_Name.setSelected(false);
                sortByCheckbox_Gender.setSelected(false);
                sortByCheckbox_PrizeCount.setSelected(false);
            }
        });

        yearMinText.setLayoutX(65.0);
        yearMinText.setLayoutY(100.0);
        yearMinText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        yearMinText.setStrokeWidth(0.0);
        yearMinText.setText("Year Min.");
        yearMinText.setFont(fontBold14);

        yearMaxText.setLayoutX(190.0);
        yearMaxText.setLayoutY(100.0);
        yearMaxText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        yearMaxText.setStrokeWidth(0.0);
        yearMaxText.setText("Year Max.");
        yearMaxText.setFont(fontBold14);
        
        Font fontDefault12 = new Font(12.0);
        yearMinSliderText.setLayoutX(80.0);
        yearMinSliderText.setLayoutY(115.0);
        yearMinSliderText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        yearMinSliderText.setStrokeWidth(0.0);
        yearMinSliderText.setText("(" + Integer.toString(minYear) + ")");
        yearMinSliderText.setFont(fontDefault12);

        yearMaxSliderText.setLayoutX(205.0);
        yearMaxSliderText.setLayoutY(115.0);
        yearMaxSliderText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        yearMaxSliderText.setStrokeWidth(0.0);
        yearMaxSliderText.setText("(" + Integer.toString(maxYear) + ")");
        yearMaxSliderText.setFont(fontDefault12);

        yearMinSlider.setLayoutX(20.0);
        yearMinSlider.setLayoutY(123.0);
        yearMinSlider.setMax(maxYear);
        yearMinSlider.setMin(minYear);
        yearMinSlider.setValue(minYear);
        yearMinSlider.setMajorTickUnit(1);
        yearMinSlider.setMinorTickCount(1);
        yearMinSlider.setBlockIncrement(1);
        yearMinSlider.setSnapToTicks(true);
        yearMinSlider.valueProperty().addListener((ObservableValue<? extends Number> pObservable, Number pOldValue, Number pNewValue) -> {
            yearMinSliderText.setText("(" + Integer.toString(pNewValue.intValue()) + ")");
        });

        yearMaxSlider.setLayoutX(160.0);
        yearMaxSlider.setLayoutY(123.0);
        yearMaxSlider.setMax(maxYear);
        yearMaxSlider.setMin(minYear);
        yearMaxSlider.setValue(maxYear);
        yearMaxSlider.setMajorTickUnit(1);
        yearMaxSlider.setMinorTickCount(1);
        yearMaxSlider.setBlockIncrement(1);
        yearMaxSlider.setSnapToTicks(true);
        yearMaxSlider.valueProperty().addListener((ObservableValue<? extends Number> pObservable, Number pOldValue, Number pNewValue) -> {
            yearMaxSliderText.setText("(" + Integer.toString(pNewValue.intValue()) + ")");
        });

        countryCodeListSelectAll.setLayoutX(50.0);
        countryCodeListSelectAll.setLayoutY(160.0);
        countryCodeListSelectAll.setText("select all");
        countryCodeListSelectAll.setFont(fontDefault10);
        countryCodeListSelectAll.setOnMouseClicked((MouseEvent pEvent) -> {
            if (pEvent.getButton() == MouseButton.PRIMARY) {
                // Select all country codes.
                countryCodeListView.getSelectionModel().selectAll();             
            }
        });
        
        countryCodeListDeselectAll.setLayoutX(195.0);
        countryCodeListDeselectAll.setLayoutY(160.0);
        countryCodeListDeselectAll.setText("deselect all");
        countryCodeListDeselectAll.setFont(fontDefault10);
        countryCodeListDeselectAll.setOnMouseClicked((MouseEvent pEvent) -> {
            if (pEvent.getButton() == MouseButton.PRIMARY) {
                // Deselect all country codes.
                countryCodeListView.getSelectionModel().clearSelection();
            }
        });
        
        countryCodeListView.setLayoutX(50.0);
        countryCodeListView.setLayoutY(180.0);
        countryCodeListView.setPrefHeight(160.0);
        countryCodeListView.setPrefWidth(200.0);
        countryCodeListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        countryCodeListView.setItems(countryCodes);

        countryCodeText.setLayoutX(95.0);
        countryCodeText.setLayoutY(155.0);
        countryCodeText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        countryCodeText.setStrokeWidth(0.0);
        countryCodeText.setText("Country Codes:");
        countryCodeText.setFont(fontBold14);

        prizeCategoryText.setLayoutX(90.0);
        prizeCategoryText.setLayoutY(365.0);
        prizeCategoryText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        prizeCategoryText.setStrokeWidth(0.0);
        prizeCategoryText.setText("Prize Category(s):");
        prizeCategoryText.setFont(fontBold14);
        
        prizeCategoryListSelectAll.setLayoutX(50.0);
        prizeCategoryListSelectAll.setLayoutY(370.0);
        prizeCategoryListSelectAll.setText("select all");
        prizeCategoryListSelectAll.setFont(fontDefault10);
        prizeCategoryListSelectAll.setOnMouseClicked((MouseEvent pEvent) -> {
            if (pEvent.getButton() == MouseButton.PRIMARY) {
                // Select all prize categories.
                prizeCategoryListView.getSelectionModel().selectAll();
              
            }
        });
        
        prizeCategoryListDeselectAll.setLayoutX(195.0);
        prizeCategoryListDeselectAll.setLayoutY(370.0);
        prizeCategoryListDeselectAll.setText("deselect all");
        prizeCategoryListDeselectAll.setFont(fontDefault10);
        prizeCategoryListDeselectAll.setOnMouseClicked((MouseEvent pEvent) -> {
            if (pEvent.getButton() == MouseButton.PRIMARY) {
                // Deselect all prize categories.
                prizeCategoryListView.getSelectionModel().clearSelection();
                
            }
        });

        prizeCategoryListView.setLayoutX(50.0);
        prizeCategoryListView.setLayoutY(390.0);
        prizeCategoryListView.setPrefHeight(120.0);
        prizeCategoryListView.setPrefWidth(200.0);
        prizeCategoryListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        prizeCategoryListView.setItems(prizeCategories);
        
        nameText.setLayoutX(100.0);
        nameText.setLayoutY(530.0);
        nameText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        nameText.setStrokeWidth(0.0);
        nameText.setText("Laureate Name");
        nameText.setFont(fontBold14);
        
        nameTextField.setLayoutX(67.5);
        nameTextField.setLayoutY(535.0);

        affiliationText.setLayoutX(119.0);
        affiliationText.setLayoutY(575.0);
        affiliationText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        affiliationText.setStrokeWidth(0.0);
        affiliationText.setText("Affiliation");
        affiliationText.setFont(fontBold14);
        
        
        affiliationTextField.setLayoutX(67.5);
        affiliationTextField.setLayoutY(580.0);

        searchButton.setLayoutX(118.0);
        searchButton.setLayoutY(610.0);
        searchButton.setMnemonicParsing(false);
        searchButton.setText("Search");
        searchButton.setFont(fontDefault14);
        searchButton.setOnMouseClicked((MouseEvent pEvent) -> {
            if (pEvent.getButton() == MouseButton.PRIMARY) {
                // Reset the PageCache.
                PageCache.Clear();
                
                // Update displayPane, populate with laureates.
                ArrayList<Laureate> laureates = SortLaureates(GetLaureates());
                displayPage.setPageCount(0);
                if (!laureates.isEmpty()) {
                    displayPage.setStyle("-fx-border-color:gray;");
                    displayPage.setPageFactory((Integer pPageIndex) -> CreatePage(laureates, pPageIndex));
                    displayPage.setPageCount((int)Math.ceil(laureates.size() / LAUREATES_PER_PAGE));
                    
                    noResultsText.setVisible(false);
                }
                else {
                    displayPage.setStyle("-fx-border-color:red; -fx-border-width: 8"); 
                    displayPage.setPageFactory(null);
                    displayPage.setPageCount(1);
                    
                    noResultsText.setVisible(true);
                }
            }
        });
        
        noResultsText.setLayoutX(searchButton.getLayoutX() + 85);
        noResultsText.setLayoutY(searchButton.getLayoutY() + 45);
        noResultsText.setFill(Color.RED);
        noResultsText.setText("No results found...");
        DropShadow ds = new DropShadow();
        noResultsText.setEffect(ds);
        noResultsText.setFont(Font.font(null, FontWeight.BOLD, 20));
        noResultsText.setVisible(false);
        
        // Build displayPane UI elements.
        displayPane.setMinHeight(0.0);
        displayPane.setMinWidth(0.0);
        displayPane.setPrefHeight(160.0);
        displayPane.setPrefWidth(100.0);
        displayPane.setStyle("-fx-background-image: url(https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQXlgN8ZAPJL7Qov-e0e8F56dvx_KKD8erQBc8cCIZlEMiubxg0); -fx-border-color: black; -fx-border-width: 10");

        AnchorPane.setBottomAnchor(displayPage, 0.0);
        AnchorPane.setLeftAnchor(displayPage, 0.0);
        AnchorPane.setRightAnchor(displayPage, 0.0);
        AnchorPane.setTopAnchor(displayPage, 0.0);
        
        displayPage.setLayoutX(76.0);
        displayPage.setLayoutY(62.0);
        displayPage.setPrefHeight(738.0);
        displayPage.setPrefWidth(810.0);
        displayPage.setPageCount(1);
        
        // Populate filterPane.
        filterPane.getChildren().add(genderText);
        filterPane.getChildren().add(genderCheckbox_Male);
        filterPane.getChildren().add(genderCheckbox_Female);
        filterPane.getChildren().add(sortByText);
        filterPane.getChildren().add(sortByCheckbox_Name);
        filterPane.getChildren().add(sortByCheckbox_Gender);
        filterPane.getChildren().add(sortByCheckbox_PrizeCount);
        filterPane.getChildren().add(sortByCheckbox_CountryCode);
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
        filterPane.getChildren().add(nameTextField);
        filterPane.getChildren().add(nameText);
        filterPane.getChildren().add(affiliationTextField);
        filterPane.getChildren().add(affiliationText);
        filterPane.getChildren().add(prizeCategoryText);
        filterPane.getChildren().add(prizeCategoryListView);
        filterPane.getChildren().add(prizeCategoryListSelectAll);
        filterPane.getChildren().add(prizeCategoryListDeselectAll);
        filterPane.getChildren().add(searchButton);
        filterPane.getChildren().add(noResultsText);
        filterPane.setStyle("-fx-background-color: lightsteelblue; -fx-opacity: 0.8");
        getItems().add(filterPane);
        
        // Populate displayPane.
        displayPane.getChildren().addAll(displayPage);
        getItems().add(displayPane);
    }
    
    /**
     * Create a page based on the filters selected in the overview page
     * @param pLaureates
     * @param pPageIndex
     * @return GridBox - UI element containing the UILaureateImages for a given page.
     */
    public GridPane CreatePage(ArrayList<Laureate> pLaureates, int pPageIndex) { 
        GridPane pane = PageCache.RequestPage(pPageIndex);
        if (pane != null)
            return pane;
        
        pane = new GridPane();
        int minLaureateIndex = pPageIndex * LAUREATES_PER_PAGE;
        for (int i = minLaureateIndex; i < Math.min(minLaureateIndex + LAUREATES_PER_PAGE, pLaureates.size()); ++i) {
            // Create laureate button.
            UILaureateButton laureateButton = new UILaureateButton(pLaureates.get(i), primaryStage, this);
            laureateButton.Initialize();
            
            // Position the laureate button in the GridPane.
            int column = i - minLaureateIndex;
            int row = 0;
            while (column > (LAUREATES_PER_ROW - 1)) {
                column -= LAUREATES_PER_ROW;
                ++row;
            }
            GridPane.setConstraints(laureateButton, column, row);
            pane.getChildren().add(laureateButton);
        }
        
        // Cache the pane.
        PageCache.CachePage(pPageIndex, pane);
        
        return pane;
    }
    
    /**
     * Returns the current LaureateSortMode based on the filters set by the user.
     * @return LaureateSortMode - current sort mode.
     */
    public LaureateSortMode GetSortMode() {
        if (sortByCheckbox_Name.isSelected())
            return LaureateSortMode.NAME;
        if (sortByCheckbox_Gender.isSelected())
            return LaureateSortMode.GENDER;
        if (sortByCheckbox_PrizeCount.isSelected())
            return LaureateSortMode.PRIZECOUNT;
        if (sortByCheckbox_CountryCode.isSelected())
            return LaureateSortMode.COUNTRYCODE;
        
        return LaureateSortMode.NONE;
    }
    
    /**
     * Sorts an ArrayList of laureates based on GetSortMode().
     * @param pLaureates - The list of laureates to sort.
     * @return ArrayList of Laureates sorted based on GetSortMode().
     */
    public ArrayList<Laureate> SortLaureates(ArrayList<Laureate> pLaureates) {
        switch (GetSortMode()) {
            case NAME:
                Collections.sort(pLaureates, (Laureate pLHS, Laureate pRHS) -> {
                    return pLHS.toString().compareTo(pRHS.toString());
                });
                break;
            case GENDER:
                Collections.sort(pLaureates, (Laureate pLHS, Laureate pRHS) -> {
                    return pLHS.m_Gender.compareTo(pRHS.m_Gender);
                });
                break;
            case PRIZECOUNT:
                Collections.sort(pLaureates, (Laureate pLHS, Laureate pRHS) -> {
                    return Integer.compare(pRHS.m_Prizes.size(), pLHS.m_Prizes.size());
                });
                break;
            case COUNTRYCODE:
                Collections.sort(pLaureates, (Laureate pLHS, Laureate pRHS) -> {
                    if (pLHS.m_BornCountry == null)
                        return 1;
                    if (pRHS.m_BornCountry == null)
                        return -1;
                    
                    return pLHS.m_BornCountry.m_Code.compareTo(pRHS.m_BornCountry.m_Code);
                });
                break;
        }
        
        return pLaureates;
    }
    
    /**
     * Returns an array of laureates that match the set filters.
     * @return ArrayList of Laureates that match the set filters.
     */
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
                        if ((entry.m_Laureate.m_BornCountry != null && entry.m_Laureate.m_BornCountry.m_Code.compareTo(countryCode) == 0)
                            || (entry.m_Laureate.m_DeathCountry != null && entry.m_Laureate.m_DeathCountry.m_Code.compareTo(countryCode) == 0)
                        ) {
                            countryCodeMatch = true;
                            break;
                        }
                    }
                }
                else { countryCodeMatch = true; }
                if (!countryCodeMatch) 
                    continue;
                
                // Check for gender match.
                boolean genderMatch = false;
                if (searchMen || searchWomen /*|| searchOrgs*/) {
                    if ((searchMen && entry.m_Laureate.m_Gender == Gender.MALE)
                            || (searchWomen && entry.m_Laureate.m_Gender == Gender.FEMALE)
                            /*|| (searchOrgs && entry.m_Laureate.m_Gender == Gender.ORGANIZATION)*/
                    ) genderMatch = true;
                }
                else { genderMatch = true; } // No genders selected, automatic match.
                if (!genderMatch) 
                    continue;
                
                // Check for affiliation match.
                String affiliationEntry = affiliationTextField.getCharacters().toString();
                boolean affiliationMatch = affiliationEntry.isEmpty();
                if (!affiliationMatch) {
                    // Determine the prize index. (This will never fail since the enclosing loop guarentees the laureate has the award.)
                    int prizeID = 0;
                    for (; prizeID < entry.m_Laureate.m_Prizes.size(); ++prizeID) {
                        if (prize == entry.m_Laureate.m_Prizes.get(prizeID))
                            break;
                    }
                    
                    if (entry.m_Laureate.m_PrizeAffiliations.size() > prizeID) {
                        Affiliation affiliation = entry.m_Laureate.m_PrizeAffiliations.get(prizeID);
                        if (affiliation.toString().toLowerCase().contains(affiliationEntry.toLowerCase()))
                            affiliationMatch = true;
                    }
                }
                if (!affiliationMatch)
                    continue;
                
                // Check for name match.
                String nameEntry = nameTextField.getCharacters().toString();
                boolean nameMatch = nameEntry.isEmpty();
                if (!nameMatch)
                    nameMatch = entry.m_Laureate.toString().toLowerCase().contains(nameEntry.toLowerCase());
                
                if (entry.m_Laureate.m_Gender != Gender.ORGANIZATION && nameMatch && !laureates.contains(entry.m_Laureate))
                    laureates.add(entry.m_Laureate);
            }
        }  
        return laureates;
    }
}
