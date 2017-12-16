package App.models;

import javafx.beans.property.*;

public class Patient {

    public SimpleIntegerProperty id = new SimpleIntegerProperty();
    public SimpleStringProperty firstname = new SimpleStringProperty();
    public SimpleStringProperty lastname = new SimpleStringProperty();
    public SimpleIntegerProperty age = new SimpleIntegerProperty();
    public SimpleStringProperty disease = new SimpleStringProperty();
    public SimpleStringProperty room = new SimpleStringProperty();
    public SimpleStringProperty doctor = new SimpleStringProperty();

    public int getId(){
        return id.get();
    }

    public int getAge(){
        return age.get();
    }

    public String getFirstname(){
        return firstname.get();
    }

    public String getLastname(){
        return lastname.get();
    }

    public String getDisease(){
        return disease.get();
    }

    public String getRoom(){
        return room.get();
    }

    public String getDoctor(){
        return doctor.get();
    }

}

