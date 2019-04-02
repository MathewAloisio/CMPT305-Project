package nobelprizeviewer.Views;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.layout.FlowPane;
import static javafx.scene.layout.Region.USE_PREF_SIZE;

import nobelprizeviewer.Models.Laureate;
import nobelprizeviewer.Models.LaureateEntry;
import nobelprizeviewer.Models.Prize;

/**
 *
 * @author Mathew Aloisio
 */
public class UIPrizeEntry extends FlowPane {
    protected final Label yearLabel;
    protected final Label categoryLabel;
    protected final Label shareLabel;
    protected final Label motivationLabel;
    
    public static final Font FONT_HELVETICA16 = new Font("Helvetica", 16.0); 
    public static final double PANE_HEIGHT = 106.0; // The default height of the UIPrizeEntry.
    
    public UIPrizeEntry(Laureate pLaureate, Prize pPrize) {
        // Create labels.
        yearLabel = new Label();
        categoryLabel = new Label();
        shareLabel = new Label();
        motivationLabel = new Label();

        // Setup ScrollPane size.
        setPrefHeight(PANE_HEIGHT);
        setPrefWidth(598.0);
        setStyle("-fx-border-color: black;");
        setMinWidth(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMaxHeight(USE_PREF_SIZE);
        setOrientation(javafx.geometry.Orientation.VERTICAL);

        // Determine which LaureateEntry belongs to this laureate.
        LaureateEntry laureateEntry = null;
        for (LaureateEntry entry : pPrize.m_Laureates) {
            if (entry.m_Laureate == pLaureate) {
                laureateEntry = entry;
                break;
            }
        }
        if (laureateEntry == null) { // This should never happen or the code is flawed somewhere.
            System.out.println("ERROR : Unable to find LaureateEntry for given laureate when creating UIPrizeEntry.");
            return;
        }

        // Populate labels.
        yearLabel.setText("Year: " + pPrize.m_Year);
        yearLabel.setFont(FONT_HELVETICA16);

        categoryLabel.setText("Category: " + pPrize.m_Category.toString());
        categoryLabel.setFont(FONT_HELVETICA16);

        shareLabel.setText("Share: " + laureateEntry.m_Share);
        shareLabel.setFont(FONT_HELVETICA16);

        motivationLabel.setText("Motivation: " + (laureateEntry.m_Motivation.isEmpty() ? pPrize.m_Motivation : laureateEntry.m_Motivation));
        motivationLabel.setFont(FONT_HELVETICA16);
    }  
    
    public void Initialize() { 
        // Add labels as children to this UIPrizeEntry instance.
        getChildren().add(yearLabel);
        getChildren().add(categoryLabel);
        getChildren().add(shareLabel);
        getChildren().add(motivationLabel);
    }
}