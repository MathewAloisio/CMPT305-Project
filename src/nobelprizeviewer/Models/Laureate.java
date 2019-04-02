package nobelprizeviewer.Models;

import GoogleImageSearch.GoogleImageSearch;
import PageCache.PageCache;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.util.Date;
import java.util.ArrayList;
import javafx.embed.swing.SwingFXUtils;

import javafx.scene.image.Image;

/**
 * A Laureate structured like the Nobel Prize JSON database's laureate.
 * @author Mathew Aloisio
 */
public class Laureate {
    public final int m_DatabaseID; // The ID of the laureate in the nobel prize database.
    public final String m_FirstName;
    public final String m_LastName;
    public final Date m_BornDate;
    public final String m_BornCity;
    public final Country m_BornCountry;
    public final int m_BornCountryNameID;
    public final Date m_DeathDate;
    public final String m_DeathCity;
    public final Country m_DeathCountry;
    public final int m_DeathCountryNameID;
    public final Gender m_Gender;
    public ArrayList<Prize> m_Prizes; // NOTE: This field is NOT final because 1 laureate can win multiple prizes.
    // NOTE: Shares per-award and motivation for giving each laureate an award
    //      is done in the Prize class.

    /**
     * Constructs a new instance of a Laureate.
     * @param pID - The database ID of the laureate in the Nobel prize database.
     * @param pFirstName - The first name of the laureate.
     * @param pLastName  - The last name of the laureate.
     * @param pBornDate - The birth date of the laureate.
     * @param pBornCity - The birth city of the laureate.
     * @param pBornCountry - The birth country of the laureate.
     * @param pBornCountryNameID - The position of the country's name in pBornCounrty.m_Names[].
     * @param pDeathDate - The date the laureate died in.
     * @param pDeathCity - The city the laureate died in.
     * @param pDeathCountry - The country the laureate died in.
     * @param pDeathCountryNameID - The position of the country's name in pDeathCountry.m_Names[].
     * @param pGender - The gender of the laureate
     */
    public Laureate(int pID, String pFirstName, String pLastName, Date pBornDate, String pBornCity, Country pBornCountry, int pBornCountryNameID, Date pDeathDate, String pDeathCity, Country pDeathCountry, int pDeathCountryNameID, Gender pGender){
        m_DatabaseID = pID;
        m_FirstName = pFirstName;
        m_LastName = pLastName;
        m_BornDate = pBornDate;
        m_BornCity = pBornCity;
        m_BornCountry = pBornCountry;
        m_BornCountryNameID = pBornCountryNameID;
        m_DeathDate = pDeathDate;
        m_DeathCity = pDeathCity;
        m_DeathCountry = pDeathCountry;
        m_DeathCountryNameID = pDeathCountryNameID;
        m_Gender = pGender;

        m_Prizes = new ArrayList<>();
    }
    
    /**
     * Checks if this laureate is a person.
     * @return true if the Laureate is a person, otherwise false.
     */
    public boolean IsPerson() {
        return m_FirstName.length() + m_LastName.length() > 0;
    }
    
    /**
     * Uses GoogleImageSearch to get a BufferedImage of this laureate from google.
     * Use the ImageView type to display and resize images.
     * @return Image the image, or null.
     */
    public Image GetImage() {
        // Load buffered image.
        BufferedImage bufferedImage = null;   
        try {
            bufferedImage = GoogleImageSearch.FindImage(toString());
        }
        catch (IOException pException) {
            System.out.println("IOException! Failed to find image for \"" + toString() + "\".\n" +  pException.toString());
        }
        
        return bufferedImage != null ? SwingFXUtils.toFXImage(bufferedImage, null) : null;
    }
    
    /**
     * Returns the appropriate name of their birth country for this person's time.
     * @return m_BornCountry.m_Names.get(m_BornCountryNameID);
     */
    public String GetBornCountryName() {
        return m_BornCountry != null ? m_BornCountry.m_Names.get(m_BornCountryNameID) : "";
    }
    
    /**
     * Returns the appropriate name of their death country for this person's time.
     * @return m_DeathCountry.m_Names.get(m_DeathCountryNameID);
     */
    public String GetDeathCountryName() {
        return m_DeathCountry != null ? m_DeathCountry.m_Names.get(m_DeathCountryNameID) : "";
    }
    
    /**
     * Given the string "male" or "female" returns the appropriate Gender enumerate value.
     * @param pGender - The String name of the gender, "male" or "female".
     * @return pGender.compareTo("female") == 0 ? Gender.Female : Gender.Male;
     */
    public static Gender GetGenderFromString(String pGender) {
        if (pGender.compareTo("female") == 0)
            return Gender.FEMALE;
        if (pGender.compareTo("male") == 0)
            return Gender.MALE;
        if (pGender.compareTo("org") == 0)
            return Gender.ORGANIZATION;
        
        return Gender.UNKNOWN;
    }
    
    /**
     * Given a list of laureates and an ID, returns the Laureate with the given database ID.
     * @param pList
     * @param pID
     * @return Laureate where m_DatabaseID == pID, otherwise null.
     */
    public static Laureate GetLaureateByID(ArrayList<Laureate> pList, int pID) {
        for (Laureate laureate : pList) {
            if (laureate.m_DatabaseID == pID)
                return laureate;
        }
        
        return null;
    }
    
    /**
     * String representation of a laureate
     * @return First and last name of the laureate
     */
    @Override
    public String toString() {
        return m_FirstName + ' ' + m_LastName;
    }
}
