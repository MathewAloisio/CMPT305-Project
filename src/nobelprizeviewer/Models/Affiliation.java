package nobelprizeviewer.Models;

/**
 *
 * @author Mathew Aloisio
 */
public class Affiliation {
    public final String m_Name;
    public final String m_City;
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
    
    @Override
    public String toString() {
        return m_Name + ". " + m_City + ", " + m_Country;
    }
}
