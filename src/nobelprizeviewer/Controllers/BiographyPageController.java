/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nobelprizeviewer.Controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import nobelprizeviewer.Models.Laureate;

/**
 * FXML Controller class
 *
 * @author Tam Le
 */
public class BiographyPageController implements Initializable {
    private Laureate laureate;
    
    public BiographyPageController(Laureate l) {
        laureate = l;
    }
    
    // JavaFX Stuff
    @FXML
    Label firstNameLabel;
    @FXML
    Label lastNameLabel;
    @FXML
    Label idLabel;
    @FXML
    Label genderLabel;
    @FXML
    Label birthLabel;
    @FXML
    Label deathLabel;
    @FXML
    ImageView imageView;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setLabels();
        setImage();
    }
    
    private void setLabels() {
        firstNameLabel.setText(laureate.m_FirstName);
        lastNameLabel.setText(laureate.m_LastName);
        genderLabel.setText(laureate.m_Gender.toString());
        
        // Convert int id -> String
        String id = String.valueOf(laureate.m_DatabaseID);
        idLabel.setText(id);
        
        // Set Birth and Death labels        
        String birthCountry = laureate.m_BornCountry.m_Names.get(0);
        String birthDate = laureate.m_BornDate.toString();
        String birthYear = birthDate.substring(birthDate.length() - 4);
        birthLabel.setText("Born in " + birthCountry + " in the year " + birthYear);
        
        String deathCountry = laureate.m_DeathCountry.m_Names.get(0);
        String deathDate = laureate.m_DeathDate.toString();
        String deathYear = deathDate.substring(birthDate.length() - 4);
        deathLabel.setText("Died in " + deathCountry + " in the year " + deathYear);
    }
    
    private void setImage() {
        imageView.setImage(laureate.GetImage());
    }
}
