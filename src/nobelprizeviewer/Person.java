package nobelprizeviewer;

import java.util.ArrayList;

/**
 * A Person structured like the Nobel Prize JSON database's laureate.
 * @author
 */
public class Person {
    public final int m_DatabaseID; // The ID of the laureate in the nobel prize database.
    public final String m_FirstName;
    public final String m_LastName;
    public final String m_BornDate;
    public final String m_BornCity;
    public final Country m_BornCountry;
    public final int m_BornCountryNameID;
    public final String m_DeathDate;
    public final String m_DeathCity;
    public final Country m_DeathCountry;
    public final int m_DeathCountryNameID;
    public final Gender m_Gender;
    public ArrayList<Prize> m_Prizes; // NOTE: This field is NOT final because 1 person can win multiple prizes.
    // NOTE: Shares per-award and motivation for giving each person an award
    //      is done in the Prize class.
    
    /**
     * Constructs a new instance of a "Person".
     * @param pID - The database ID of the laureate in the Nobel prize database.
     * @param pFirstName - The first name of the laureate.
     * @param pLastName  - The last name of the laureate.
     * @param pBornDate
     * @param pBornCity
     * @param pBornCountry
     * @param pBornCountryNameID - The position of the country's name in pBornCounrty.m_Names[].
     * @param pDeathDate
     * @param pDeathCity
     * @param pDeathCountry
     * @param pDeathCountryNameID - The position of the country's name in pDeathCountry.m_Names[].
     * @param pGender 
     */
    public Person(int pID, String pFirstName, String pLastName, String pBornDate, String pBornCity, Country pBornCountry, int pBornCountryNameID, String pDeathDate, String pDeathCity, Country pDeathCountry, int pDeathCountryNameID, Gender pGender){
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
     * Given the string "male" or "female" returns the appropriate Gender enumerate value.
     * @param pGender - The String name of the gender, "male" or "female".
     * @return pGender.compareTo("female") == 0 ? Gender.Female : Gender.Male;
     */
    public static Gender GetGenderFromString(String pGender) {
        return pGender.compareTo("female") == 0 ? Gender.Female : Gender.Male;
    }

    @Override
    public String toString() {
        return m_FirstName + ' ' + m_LastName;
    }
}
