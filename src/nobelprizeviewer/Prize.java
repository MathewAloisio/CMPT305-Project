package nobelprizeviewer;

import java.util.ArrayList;

/**
 * A Nobel Prize structured after the "prize" category in the JSON database.
 * @author Mathew Aloisio
 */
public class Prize {
    // Inner-class: LaureateEntry.
    public class LaureateEntry {
        public final Laureate m_Laureate; // The laureate associated with a prize.
        public final int m_Share;         // The laureate's share in their associated prize.
        public final String m_Motivation; // The motiviation for giving this laureate a share in the prize.
        
        /**
         * 
         * @param pLaureate - The laureate associated with this entry.
         * @param pShare - The # of shares out of 10 this laureate was awarded of this prize.
         * @param pMotivation - The motivation for giving this laureate a share in the prize.
         */
        public LaureateEntry(Laureate pLaureate, int pShare, String pMotivation) {
            m_Laureate = pLaureate;
            m_Share = pShare;
            m_Motivation = pMotivation;
        }
    }
    
    // Prize class.
    public final int m_Year;
    public final PrizeCategory m_Category;
    public final String m_Motivation;
    public final ArrayList<LaureateEntry> m_Laureates; // aka laureates.
    
    /**
     * Constructs a new instance of Prize.
     * @param pYear - The year the prize was awarded.
     * @param pCategory - The PrizeCategory the award was in.
     * @param pMotivation - The String "motivation" behind the award.
     * @param pLaureates - The ArrayList of LaureateEntrys for the prize.
     */
    public Prize(int pYear, PrizeCategory pCategory, String pMotivation, ArrayList<LaureateEntry> pLaureates) {
        m_Year = pYear;
        m_Category = pCategory;
        m_Motivation = pMotivation;
        m_Laureates = pLaureates;
    }
    
    /**
     * Given the name of a category, returns the corresponding enumerate value.
     * @param pCategory - the String name of the category as found in the JSON data.
     * @return the PrizeCategory corresponding to pCategory or null. 
     */
    public static PrizeCategory GetCategoryFromString(String pCategory) {
        switch (pCategory) {
            case "physics":
                return PrizeCategory.PHYSICS;
            case "chemisty":
                return PrizeCategory.CHEMISTRY;
            case "medicine": // also contains physiology awards.
                return PrizeCategory.MEDICINE;
            case "peace":
                return PrizeCategory.PEACE;
            case "economics":
                return PrizeCategory.ECONOMICS;
            case "literature":
                return PrizeCategory.LITERATURE;
        }
        
        return null;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('{');
        builder.append("\"year\":\"");
        builder.append(m_Year);
        builder.append("\", \"category\":\"");
        builder.append(m_Category);
        builder.append("\", \"motivation\":\"");
        builder.append(m_Motivation);
        builder.append("\", \"laureates\":\"[");
        for (LaureateEntry entry : m_Laureates) {
            builder.append('\"');
            builder.append(entry.m_Laureate.toString());
            builder.append("\", ");
        }
        builder.setLength(builder.length() - 2); // remove the last ", " from the builder.
        builder.append("]}");
        return builder.toString();
    }
}
