package nobelprizeviewer.Views;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.layout.FlowPane;
import static javafx.scene.layout.Region.USE_PREF_SIZE;

import nobelprizeviewer.Models.Laureate;
import nobelprizeviewer.Models.LaureateEntry;
import nobelprizeviewer.Models.Prize;
import nobelprizeviewer.Models.Affiliation;

/**
 *
 * @author Mathew Aloisio
 */
public class UIPrizeEntry extends FlowPane {
    protected final Label yearLabel;
    protected final Label categoryLabel;
    protected final Label shareLabel;
    protected final Label affiliationLabel;
    protected final Label motivationLabel;
    protected final Label prizeMotivationLabel;
    
    // Static member(s).
    public static final Font FONT_HELVETICA14 = new Font("Helvetica", 14.0); 
    
    public static final double PANE_HEIGHT = 122.0;
    public static final double PANE_WIDTH = 948.0;
    
    /**
     * Constructs a new instance of UIPrizeEntry.
     * @param pLaureate - The laureate of the prize.
     * @param pPrizeIndex - The index of the prize in pLaureate.m_Prizes[].
     */
    public UIPrizeEntry(Laureate pLaureate, int pPrizeIndex) {
        // Create labels.
        yearLabel = new Label();
        categoryLabel = new Label();
        shareLabel = new Label();
        affiliationLabel = new Label();
        motivationLabel = new Label();
        prizeMotivationLabel = new Label();
        
        // Setup ScrollPane size.
        setPrefHeight(PANE_HEIGHT);
        setPrefWidth(PANE_WIDTH);
        setMinWidth(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMaxHeight(USE_PREF_SIZE);
        setOrientation(javafx.geometry.Orientation.VERTICAL);
        setStyle("-fx-border-color: black;");
        
        // Determine which LaureateEntry belongs to this laureate.
        Prize prize = pLaureate.m_Prizes.get(pPrizeIndex);
        LaureateEntry laureateEntry = null;
        for (LaureateEntry entry : prize.m_Laureates) {
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
        yearLabel.setText("Year: " + prize.m_Year);
        yearLabel.setFont(FONT_HELVETICA14);

        categoryLabel.setText("Category: " + prize.m_Category.toString());
        categoryLabel.setFont(FONT_HELVETICA14);

        shareLabel.setText("Share: " + laureateEntry.m_Share);
        shareLabel.setFont(FONT_HELVETICA14);
        
        if (pLaureate.m_PrizeAffiliations.size() > pPrizeIndex) {
            Affiliation affiliation = pLaureate.m_PrizeAffiliations.get(pPrizeIndex);
            affiliationLabel.setText("Affiliation: " + affiliation.toString());
        }
        else { affiliationLabel.setText("Affiliation: N/A"); }
        affiliationLabel.setFont(FONT_HELVETICA14);

        motivationLabel.setText("Personal motivation: " + (laureateEntry.m_Motivation.isEmpty() ? "N/A" : laureateEntry.m_Motivation));
        motivationLabel.setFont(FONT_HELVETICA14);
        
        prizeMotivationLabel.setText("Motivation: " + (prize.m_Motivation.isEmpty() ? "N/A" : prize.m_Motivation));
        prizeMotivationLabel.setFont(FONT_HELVETICA14);
    }  
    
    /**
     * Initializes the UI element.
     */
    public void Initialize() { 
        // Add labels as children to this UIPrizeEntry instance.
        getChildren().add(yearLabel);
        getChildren().add(categoryLabel);
        getChildren().add(shareLabel);
        getChildren().add(affiliationLabel);
        getChildren().add(motivationLabel);
        getChildren().add(prizeMotivationLabel);
    }
}