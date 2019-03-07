package projectcmpt305;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;

/**
 *
 * @author dylanollikka
 */
public class MRN
    {
        private final SimpleStringProperty year;
        private final SimpleStringProperty prize;
        private final SimpleStringProperty name;
        private final SimpleStringProperty longname;
        private final SimpleStringProperty gender;
        private final SimpleStringProperty photo;
        private final SimpleStringProperty country;
        private final SimpleStringProperty affiliation;
        private final SimpleStringProperty birthyear;
        private final SimpleStringProperty deathyear;
        private final SimpleStringProperty biography;
        private final SimpleStringProperty lecture;

        ObservableList<MRN> list = FXCollections.observableArrayList();

/*constructor to load up parsed JSON data in the form of objects, before being
loaded into the columns of the JavaFX tableview*/
        MRN(String y,String p,String n,String ln,String g,String ph,String c,String a, String by, String dy, String b, String l)
        {
            this.year = new SimpleStringProperty(y);
            this.prize = new SimpleStringProperty(p);
            this.name = new SimpleStringProperty(n);
            this.longname = new SimpleStringProperty(ln);
            this.gender = new SimpleStringProperty(g);
            this.photo = new SimpleStringProperty(ph);
            this.country = new SimpleStringProperty(c);
            this.affiliation = new SimpleStringProperty(a);
            this.birthyear = new SimpleStringProperty(by);
            this.deathyear = new SimpleStringProperty(dy);
            this.biography = new SimpleStringProperty(b);
            this.lecture = new SimpleStringProperty(l);
        }

//getter methods defined so that the tableview could extract each property value into the respective columns.
        public String getYear() {
            return year.get();
        }

        public String getPrize() {
            return prize.get();
        }

        public String getName() {
            return name.get();
        }

        public String getLongname() {
            return longname.get();
        }

        public String getGender() {
            return gender.get();
        }

        public String getPhoto() {
            return photo.get();
        }

        public String getCountry() {
            return country.get();
        }

        public String getAffiliation() {
            return affiliation.get();
        }
        public String getBirthyear() {
            return birthyear.get();
        }
        public String getDeathyear() {
            return deathyear.get();
        }
        public String getBiography() {
            return biography.get();
        }
        public String getLecture() {
            return lecture.get();
        }
}
