package nobelprizeviewer.Views;

import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nobelprizeviewer.Models.Laureate;

public class UIBiographyPage extends SplitPane {
    protected final AnchorPane imagePane;
    protected final Hyperlink backLink;
    protected final ImageView imageView;
    protected final Label resultsLabel;
    protected final AnchorPane biographyPane;
    protected final Label biographyLabel;
    protected final Label birthTitleLabel;
    protected final Label birthLabel;
    protected final Label deathTitleLabel;
    protected final Label deathLabel;
    protected final Label idTitleLabel;
    protected final Label idLabel;
    protected final Label genderTitleLabel;
    protected final Label genderLabel;
    protected final Label firstNameTitleLabel;
    protected final Label firstNameLabel;
    protected final Label lastNameTitleLabel;
    protected final Label lastNameLabel;
    protected final Label prizesTitleLabel;
    protected final FlowPane prizePane;
    protected final Label prizeYearLabel;
    protected final Label prizeCategoryLabel;
    protected final Label prizeShareLabel;
    protected final Label prizeMotivationLabel;
    protected final Label prizeAffiliationsLabel;
    
    protected final Stage primaryStage;
    protected final Scene overviewScene;
    
    public static final Font FONT_HELVETICA13 = new Font("Helvetica", 13.0);
    public static final Font FONT_HELVETICA16 = new Font("Helvetica", 16.0);
    public static final Font FONT_HELVETICA20 = new Font("Helvetica", 20.0);
    public static final Font FONT_HELVETICA28 = new Font("Helvetica", 28.0);

    public UIBiographyPage(Stage pPrimaryStage, Scene pOverviewScene) {
        primaryStage = pPrimaryStage;
        overviewScene = pOverviewScene;
        
        imagePane = new AnchorPane();
        backLink = new Hyperlink();
        imageView = new ImageView();
        resultsLabel = new Label();
        biographyPane = new AnchorPane();
        biographyLabel = new Label();
        birthTitleLabel = new Label();
        birthLabel = new Label();
        deathTitleLabel = new Label();
        deathLabel = new Label();
        idTitleLabel = new Label();
        idLabel = new Label();
        genderTitleLabel = new Label();
        genderLabel = new Label();
        firstNameTitleLabel = new Label();
        firstNameLabel = new Label();
        lastNameTitleLabel = new Label();
        lastNameLabel = new Label();
        prizesTitleLabel = new Label();
        prizePane = new FlowPane();
        prizeYearLabel = new Label();
        prizeCategoryLabel = new Label();
        prizeShareLabel = new Label();
        prizeMotivationLabel = new Label();
        prizeAffiliationsLabel = new Label();
    }
    
    public void Initialize() {
        setDividerPositions(0.3);
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(661.0);
        setPrefWidth(992.0);

        imagePane.setMinHeight(0.0);
        imagePane.setMinWidth(0.0);
        imagePane.setPrefHeight(160.0);
        imagePane.setPrefWidth(100.0);

        backLink.setLayoutX(4.0);
        backLink.setLayoutY(5.0);
        backLink.setText("Back to Results?");
        backLink.setFont(FONT_HELVETICA13);

        imageView.setFitHeight(323.0);
        imageView.setFitWidth(263.0);
        imageView.setLayoutX(18.0);
        imageView.setLayoutY(77.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        resultsLabel.setLayoutX(9.0);
        resultsLabel.setLayoutY(30.0);
        resultsLabel.setFont(FONT_HELVETICA13);

        biographyPane.setPrefHeight(709.0);
        biographyPane.setPrefWidth(265.0);

        biographyLabel.setLayoutX(24.0);
        biographyLabel.setLayoutY(14.0);
        biographyLabel.setText("Biography");
        biographyLabel.setFont(FONT_HELVETICA28);

        birthTitleLabel.setLayoutX(25.0);
        birthTitleLabel.setLayoutY(175.0);
        birthTitleLabel.setText("Birth");
        birthTitleLabel.setFont(FONT_HELVETICA20);

        birthLabel.setLayoutX(25.0);
        birthLabel.setLayoutY(201.0);
        birthLabel.setText("Born in  <birth place> in the year <birth year>");
        birthLabel.setFont(FONT_HELVETICA13);

        deathTitleLabel.setLayoutX(335.0);
        deathTitleLabel.setLayoutY(175.0);
        deathTitleLabel.setText("Death");
        deathTitleLabel.setFont(FONT_HELVETICA20);

        deathLabel.setLayoutX(335.0);
        deathLabel.setLayoutY(201.0);
        deathLabel.setText("Died in  <death place> in the year <death year>");
        deathLabel.setFont(FONT_HELVETICA13);

        idTitleLabel.setLayoutX(23.0);
        idTitleLabel.setLayoutY(125.0);
        idTitleLabel.setText("ID");
        idTitleLabel.setFont(FONT_HELVETICA20);

        idLabel.setLayoutX(24.0);
        idLabel.setLayoutY(151.0);
        idLabel.setText("id");
        idLabel.setFont(FONT_HELVETICA13);

        genderTitleLabel.setLayoutX(164.0);
        genderTitleLabel.setLayoutY(125.0);
        genderTitleLabel.setText("Gender");
        genderTitleLabel.setFont(FONT_HELVETICA20);

        genderLabel.setLayoutX(165.0);
        genderLabel.setLayoutY(151.0);
        genderLabel.setText("Gender");
        genderLabel.setFont(FONT_HELVETICA13);

        firstNameTitleLabel.setLayoutX(24.0);
        firstNameTitleLabel.setLayoutY(65.0);
        firstNameTitleLabel.setText("First Name");
        firstNameTitleLabel.setFont(FONT_HELVETICA20);

        firstNameLabel.setLayoutX(25.0);
        firstNameLabel.setLayoutY(91.0);
        firstNameLabel.setText("first");
        firstNameLabel.setFont(FONT_HELVETICA13);

        lastNameTitleLabel.setLayoutX(164.0);
        lastNameTitleLabel.setLayoutY(65.0);
        lastNameTitleLabel.setText("Last Name");
        lastNameTitleLabel.setFont(FONT_HELVETICA20);

        lastNameLabel.setLayoutX(165.0);
        lastNameLabel.setLayoutY(91.0);
        lastNameLabel.setText("last");
        lastNameLabel.setFont(FONT_HELVETICA13);

        prizesTitleLabel.setLayoutX(23.0);
        prizesTitleLabel.setLayoutY(257.0);
        prizesTitleLabel.setText("Prizes");
        prizesTitleLabel.setFont(FONT_HELVETICA28);

        prizePane.setLayoutX(26.0);
        prizePane.setLayoutY(302.0);
        prizePane.setMaxWidth(USE_PREF_SIZE);
        prizePane.setOrientation(javafx.geometry.Orientation.VERTICAL);
        prizePane.setPrefHeight(111.0);
        prizePane.setPrefWidth(317.0);
        prizePane.setStyle("-fx-border-color: black;");

        prizeYearLabel.setText("Year: year");
        prizeYearLabel.setFont(FONT_HELVETICA16);

        prizeCategoryLabel.setText("Category: category");
        prizeCategoryLabel.setFont(FONT_HELVETICA16);

        prizeShareLabel.setText("Share: share");
        prizeShareLabel.setFont(FONT_HELVETICA16);

        prizeMotivationLabel.setText("Motivation: motivation");
        prizeMotivationLabel.setFont(FONT_HELVETICA16);

        prizeAffiliationsLabel.setText("Affiliations: affiliations separated by commas");
        prizeAffiliationsLabel.setFont(FONT_HELVETICA16);

        imagePane.getChildren().add(backLink);
        imagePane.getChildren().add(imageView);
        imagePane.getChildren().add(resultsLabel);
        getItems().add(imagePane);
        biographyPane.getChildren().add(biographyLabel);
        biographyPane.getChildren().add(birthTitleLabel);
        biographyPane.getChildren().add(birthLabel);
        biographyPane.getChildren().add(deathTitleLabel);
        biographyPane.getChildren().add(deathLabel);
        biographyPane.getChildren().add(idTitleLabel);
        biographyPane.getChildren().add(idLabel);
        biographyPane.getChildren().add(genderTitleLabel);
        biographyPane.getChildren().add(genderLabel);
        biographyPane.getChildren().add(firstNameTitleLabel);
        biographyPane.getChildren().add(firstNameLabel);
        biographyPane.getChildren().add(lastNameTitleLabel);
        biographyPane.getChildren().add(lastNameLabel);
        biographyPane.getChildren().add(prizesTitleLabel);
        prizePane.getChildren().add(prizeYearLabel);
        prizePane.getChildren().add(prizeCategoryLabel);
        prizePane.getChildren().add(prizeShareLabel);
        prizePane.getChildren().add(prizeMotivationLabel);
        prizePane.getChildren().add(prizeAffiliationsLabel);
        biographyPane.getChildren().add(prizePane);
        getItems().add(biographyPane);
    }
    
    public void SetLaureate(Laureate pLaureate) {
        // Update results label.
        resultsLabel.setText("Result of " + pLaureate.toString());
        
        // Set name and gender labels.
        firstNameLabel.setText(pLaureate.m_FirstName);
        lastNameLabel.setText(pLaureate.m_LastName);
        genderLabel.setText(pLaureate.m_Gender.toString());
        
        // Convert int id -> String
        String id = String.valueOf(pLaureate.m_DatabaseID);
        idLabel.setText(id);
        
        // Set Birth and Death labels        
        String birthCountry = pLaureate.m_BornCountry.m_Names.get(0);
        String birthDate = pLaureate.m_BornDate.toString();
        String birthYear = birthDate.substring(birthDate.length() - 4);
        birthLabel.setText("Born in " + birthCountry + " in the year " + birthYear);
        
        String deathCountry = pLaureate.m_DeathCountry.m_Names.get(0);
        String deathDate = pLaureate.m_DeathDate.toString();
        String deathYear = deathDate.substring(birthDate.length() - 4);
        deathLabel.setText("Died in " + deathCountry + " in the year " + deathYear);
        
        // Set laureate imageView.
        imageView.setImage(pLaureate.GetImage());
    }
    
    public void BackToOverview() {
        primaryStage.setScene(overviewScene);
    }
}
