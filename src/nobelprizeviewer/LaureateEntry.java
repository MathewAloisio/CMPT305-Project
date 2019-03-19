/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nobelprizeviewer;

/**
 * For Laureate entries in the Prize class.
 * @author Mathew Aloisio
 */
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
