package eg.edu.iti.mealplaner.model.models;

import java.util.List;

public class FilterList<T> {
    List<T> meals;

    public FilterList(List<T> meals) {
        this.meals = meals;
    }

    public List<T> getMeals() {
        return meals;
    }

    public void setMeals(List<T> meals) {
        this.meals = meals;
    }
}
