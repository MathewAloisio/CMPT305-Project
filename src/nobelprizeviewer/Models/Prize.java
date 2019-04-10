package nobelprizeviewer.Models;

import java.util.ArrayList;

/**
 * A Nobel Prize structured after the "prize" category in the JSON database.
 * @author Mathew Aloisio
 */
public class Prize {
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
            case "chemistry":
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
    
    /**
     * String representation of the prize
     * @return String containing the year, category, motivation, and laureates of a prize
     */
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
