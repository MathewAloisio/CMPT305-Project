package nobelprizeviewer.Models;

/**
 * A type to store information about an affiliation
 * @author Mathew Aloisio
 */
public class Affiliation {
    public final String m_Name;     // The name of the affiliation
    public final String m_City;     // The city the affiliation is located
    public final String m_Country;  
    
    /**
     * Constructs a new instance of an Affiliation.
     * @param pName - The name of the affiliation.
     * @param pCity - The city affiliated.
     * @param pCountry - The country affiliated.
     */
    public Affiliation(String pName, String pCity, String pCountry) {
        m_Name = pName;
        m_City = pCity;
        m_Country = pCountry;
    }
    
    /**
     * String representation of an affiliation
     * @return String containing the name, city, and country of the affiliation
     */
    @Override
    public String toString() {
        return m_Name + ". " + m_City + ", " + m_Country;
    }
}
