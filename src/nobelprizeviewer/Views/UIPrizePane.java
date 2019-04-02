package nobelprizeviewer.Views;

import javafx.scene.layout.FlowPane;

import nobelprizeviewer.Models.Laureate;

/**
 *
 * @author Mathew Aloisio
 */
public class UIPrizePane extends FlowPane {
    public UIPrizePane() {
        // Setup size and style.
        setPrefHeight(106.0);
        setPrefWidth(598.0);
        setStyle("-fx-border-color: black;");
        setMinWidth(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMaxHeight(USE_PREF_SIZE);
        setOrientation(javafx.geometry.Orientation.VERTICAL);
    }
    
    public void Initialize(Laureate pLaureate) {
        // Set prefered height based on the laureate this was initialized with.
        if (!pLaureate.m_Prizes.isEmpty()) {
            setPrefHeight(106.0 * pLaureate.m_Prizes.size());
        }
        else { setPrefHeight(106.0); }
        
        // Create a UIPrizeEntry for every prize this laureate has been awarded.
        for (int i = 0 ; i < pLaureate.m_Prizes.size(); ++i) {
            UIPrizeEntry entry = new UIPrizeEntry(pLaureate, pLaureate.m_Prizes.get(i));
            entry.Initialize();
            
            getChildren().add(entry);
        }
    }
}
