/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nobelprizeviewer;

import java.util.ArrayList;

/**
 * A country type to store information about
 * @author
 */
public class Country {
    public final String m_Code;         // The country code.
    public ArrayList<String> m_Names;   // All names of this country (previous & current).
    
    /**
     * Constructs a new instance of Country 
     * @param pCode - The country code.
     */
    public Country(String pCode) {
        m_Code = pCode;
        m_Names = new ArrayList<>();
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('{');
        builder.append(m_Code);
        builder.append('[');
        builder.append(m_Names.toString());
        builder.append("]}");
        return builder.toString();
    }
}
