package nobelprizeviewer.Views;

import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import nobelprizeviewer.Models.Laureate;
import nobelprizeviewer.NobelPrizeViewer;

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
    protected final ScrollPane prizeScrollPane;
    
    protected final Stage primaryStage;
    protected final Scene overviewScene;
    
    public static final Font FONT_HELVETICA13 = new Font("Helvetica", 13.0);
    public static final Font FONT_HELVETICA20 = new Font("Helvetica", 20.0);
    public static final Font FONT_HELVETICA28 = new Font("Helvetica", 28.0);
    
    /**
     * Constructs a new instance of UIBiographyPage.
     * @param pPrimaryStage - The primary stage that controls the scenes.
     * @param pOverviewScene - The scene the "back" button returns to.
     */
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
        prizeScrollPane = new ScrollPane();
    }
    
    public void Initialize() {
        setDividerPositions(0.26);
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(661.0);
        setPrefWidth(1024.0);

        imagePane.setMinHeight(0.0);
        imagePane.setMinWidth(0.0);
        imagePane.setPrefHeight(160.0);
        imagePane.setPrefWidth(100.0);

        backLink.setLayoutX(4.0);
        backLink.setLayoutY(5.0);
        backLink.setText("Back to Results?");
        backLink.setFont(FONT_HELVETICA13);
        backLink.setOnMouseClicked((MouseEvent pEvent) -> {
            BackToOverview();
        });

        imageView.setFitHeight(323.0);
        imageView.setFitWidth(263.0);
        imageView.setLayoutX(18.0);
        imageView.setLayoutY(77.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(NobelPrizeViewer.LOADING_IMAGE);

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
        birthLabel.setFont(FONT_HELVETICA13);

        deathTitleLabel.setLayoutX(335.0);
        deathTitleLabel.setLayoutY(175.0);
        deathTitleLabel.setText("Death");
        deathTitleLabel.setFont(FONT_HELVETICA20);

        deathLabel.setLayoutX(335.0);
        deathLabel.setLayoutY(201.0);
        deathLabel.setFont(FONT_HELVETICA13);

        idTitleLabel.setLayoutX(23.0);
        idTitleLabel.setLayoutY(125.0);
        idTitleLabel.setText("ID");
        idTitleLabel.setFont(FONT_HELVETICA20);

        idLabel.setLayoutX(24.0);
        idLabel.setLayoutY(151.0);
        idLabel.setFont(FONT_HELVETICA13);

        genderTitleLabel.setLayoutX(195.0);
        genderTitleLabel.setLayoutY(125.0);
        genderTitleLabel.setText("Gender");
        genderTitleLabel.setFont(FONT_HELVETICA20);

        genderLabel.setLayoutX(195.0);
        genderLabel.setLayoutY(151.0);
        genderLabel.setFont(FONT_HELVETICA13);

        firstNameTitleLabel.setLayoutX(24.0);
        firstNameTitleLabel.setLayoutY(65.0);
        firstNameTitleLabel.setText("First Name");
        firstNameTitleLabel.setFont(FONT_HELVETICA20);

        firstNameLabel.setLayoutX(25.0);
        firstNameLabel.setLayoutY(91.0);
        firstNameLabel.setFont(FONT_HELVETICA13);

        lastNameTitleLabel.setLayoutX(195.0);
        lastNameTitleLabel.setLayoutY(65.0);
        lastNameTitleLabel.setText("Last Name");
        lastNameTitleLabel.setFont(FONT_HELVETICA20);

        lastNameLabel.setLayoutX(195.0);
        lastNameLabel.setLayoutY(91.0);
        lastNameLabel.setFont(FONT_HELVETICA13);

        prizesTitleLabel.setLayoutX(23.0);
        prizesTitleLabel.setLayoutY(257.0);
        prizesTitleLabel.setText("Prizes");
        prizesTitleLabel.setFont(FONT_HELVETICA28);

        prizeScrollPane.setLayoutX(26.0);
        prizeScrollPane.setLayoutY(302.0);
        prizeScrollPane.setPrefHeight(UIPrizeEntry.PANE_HEIGHT * 2);
        prizeScrollPane.setPrefWidth(648.0);
        prizeScrollPane.setFitToHeight(true);
        prizeScrollPane.setFitToWidth(true);
        prizeScrollPane.setStyle("-fx-border-color: black;");

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
        biographyPane.getChildren().add(prizeScrollPane);
        getItems().add(biographyPane);
    }
    
    /**
     * Updates the biography page to reflect the given laureate with the given image.
     * @param pImage
     * @param pLaureate 
     */
    public void SetLaureate(Image pImage, Laureate pLaureate) {
        // Update results and Prize labels.
        resultsLabel.setText("Result of " + pLaureate.toString());
        prizesTitleLabel.setText("Prizes (" + pLaureate.m_Prizes.size() + ")");
        
        // Set name and gender labels.
        firstNameLabel.setText(pLaureate.m_FirstName);
        lastNameLabel.setText(pLaureate.m_LastName);
        genderLabel.setText(pLaureate.m_Gender.toString());
        
        // Convert int id -> String
        String id = String.valueOf(pLaureate.m_DatabaseID);
        idLabel.setText(id);
        
        // Set Birth and Death labels        
        String birthDate = pLaureate.m_BornDate.toString();
        String birthYear = birthDate.substring(birthDate.length() - 4);
        birthLabel.setText("Born in " + pLaureate.GetBornCountryName() + " in the year " + birthYear);
        
        if (pLaureate.m_DeathCountry != null) {
            String deathDate = pLaureate.m_DeathDate.toString();
            String deathYear = deathDate.substring(birthDate.length() - 4);
            deathLabel.setText("Died in " + pLaureate.GetDeathCountryName() + " in the year " + deathYear);
        }
        else { deathLabel.setText("N/A"); }
        
        // Set laureate imageView.
        if (pImage != null && pImage != NobelPrizeViewer.LOADING_IMAGE) {
            imageView.setImage(pImage);
        }
        else {
            // Load the laureates image on a thread.
            Runnable runnable = () -> {
                Image image = pLaureate.GetImage();
                if (image != null) imageView.setImage(image); // Check if the image was valid incase none was found.
            };
            new Thread(runnable).start();
        }
        
        // Clear & fill prizeScrollPane.
        UIPrizePane prizePane = new UIPrizePane();
        prizePane.Initialize(pLaureate);
        prizeScrollPane.setContent(prizePane);
    }
    
    /**
     * Sets the current scene of 'primaryStage' to the overview scene that this biography page was constructed with.
     */
    public void BackToOverview() {
        imageView.setImage(NobelPrizeViewer.LOADING_IMAGE);
        primaryStage.setScene(overviewScene);
    }
}
