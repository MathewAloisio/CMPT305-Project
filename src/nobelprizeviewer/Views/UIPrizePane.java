package nobelprizeviewer.Views;

import javafx.scene.layout.FlowPane;

import nobelprizeviewer.Models.Laureate;

/**
 * A FlowPane that holds a collection of UIPrizeEntrys vertically separated.
 * @author Mathew Aloisio, Tam Le, Dylan, Femi, Alyssa.
 */
public class UIPrizePane extends FlowPane {
    /**
     * Constructs a new instance of UIPrizePane.
     */
    public UIPrizePane() {
        // Setup size and style.
        setPrefHeight(UIPrizeEntry.PANE_HEIGHT);
        setPrefWidth(UIPrizeEntry.PANE_WIDTH);
        setMinWidth(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setOrientation(javafx.geometry.Orientation.VERTICAL);
    }
    
    /**
     * Initializes a UIPrizePane
     * @param pLaureate - The laureate who "owns" this prize pane.
     */
    public void Initialize(Laureate pLaureate) {
        // Set prefered height based on the laureate this was initialized with.
        if (!pLaureate.m_Prizes.isEmpty()) {
            setPrefHeight((UIPrizeEntry.PANE_HEIGHT + getVgap()) * pLaureate.m_Prizes.size());
        }
        else { setPrefHeight(UIPrizeEntry.PANE_HEIGHT); }
        
        // Create a UIPrizeEntry for every prize this laureate has been awarded.
        for (int i = 0 ; i < pLaureate.m_Prizes.size(); ++i) {
            UIPrizeEntry entry = new UIPrizeEntry(pLaureate, i);
            entry.Initialize();
            
            getChildren().add(entry);
        }
    }
}
