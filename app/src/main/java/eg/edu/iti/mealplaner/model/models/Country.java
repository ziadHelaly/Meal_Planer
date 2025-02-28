package eg.edu.iti.mealplaner.model.models;

import com.google.gson.annotations.Expose;

public class Country {
    @Expose
    String strArea;
    String nationality;


    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public Country() {
    }

    public Country(String strArea) {
        this.strArea = strArea;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }
}
