package nobelprizeviewer.Views;

import javafx.scene.text.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import nobelprizeviewer.Models.*;
import nobelprizeviewer.NobelPrizeViewer;

public class UILaureateButton extends AnchorPane {
    protected final ImageView imageView;
    protected final Text nameLabel;
    protected final Laureate laureate; // Keep track of what laureate this button is for.
    protected final UIOverviewPage overviewPage; // Keep track of what overview page this button belongs to.
    protected final Stage primaryStage;
    
    public static Font FONT_DEFAULT12 = new Font(12.0);
    public static Font FONT_DEFAULT14 = new Font(14.0);

    /**
     * A collection of an ImageView of a laureate's GetImage() and a Text label of their name.
     * Both elements can be clicked and will take you to the biography page for the given laureate.
     * @param pLaureate - The laureate to generate a UI button for.
     * @param pPrimaryStage - The primary Stage object.
     * @param pOverviewPage - The overview page to pass to the biography page so we know what scene to return to.
     */
    public UILaureateButton(Laureate pLaureate, Stage pPrimaryStage, UIOverviewPage pOverviewPage) {
        primaryStage = pPrimaryStage;
        imageView = new ImageView();
        nameLabel = new Text();
        
        overviewPage = pOverviewPage;
        laureate = pLaureate;
    }
    
    /**
     * Initializes the UI elements, separated from the constructor to avoid calling over-rideable methods in a constructor.
     */
    public void Initialize() {
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(210.0);
        setPrefWidth(246.0);
        
        setOnMouseEntered((MouseEvent pEvent) -> {
            setStyle("-fx-border-color: green; -fx-border-width: 1;");
        });
        setOnMouseExited((MouseEvent pEvent) -> {
            setStyle(null);
        });

        imageView.setFitHeight(158.0);
        imageView.setFitWidth(150.0);
        imageView.setLayoutX(60.0);
        imageView.setLayoutY(8.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setOnMouseClicked((MouseEvent pEvent) -> {
            OnMouseClicked(pEvent);
        });
        
        // Set imageView image.
        imageView.setImage(NobelPrizeViewer.LOADING_IMAGE);
        Runnable runnable = () -> {
            Image image = laureate.GetImage();
            if (image != null) imageView.setImage(image); // Check if the image was valid incase none was found.
        };
        new Thread(runnable).start();

        String laureateName = laureate.toString();
        nameLabel.setLayoutY(191.0);
        nameLabel.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        nameLabel.setStrokeWidth(0.0);
        nameLabel.setText(laureateName);
        nameLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        nameLabel.setWrappingWidth(246.0);
        nameLabel.setFont(laureateName.length() < 62 ? FONT_DEFAULT14 : FONT_DEFAULT12);
       
        nameLabel.setOnMouseClicked((MouseEvent pEvent) -> {
            OnMouseClicked(pEvent);
        });

        getChildren().add(imageView);
        getChildren().add(nameLabel);
    }
    
    public void OnMouseClicked(MouseEvent pEvent) {
        if (pEvent.getButton() != MouseButton.PRIMARY)
            return;
        
        // Go to the bigography page for 'laureate'.
        if (primaryStage != null) {
            ((UIBiographyPage)NobelPrizeViewer.BIOGRAPHY_SCENE.getRoot()).SetLaureate(imageView.getImage(), laureate);
            primaryStage.setMaximized(false);
            primaryStage.setScene(NobelPrizeViewer.BIOGRAPHY_SCENE);
            primaryStage.setMaximized(true);
        }
    }
}
