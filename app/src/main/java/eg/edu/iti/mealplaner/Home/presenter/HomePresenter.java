package eg.edu.iti.mealplaner.Home.presenter;

import java.util.List;

import eg.edu.iti.mealplaner.model.models.Category;
import eg.edu.iti.mealplaner.model.models.Meal;

public interface HomePresenter {

    void addMealToFav(Meal meal);

    void getData();


    public interface View{
        void showLoadingScreen();
        void hideLoadingScreen();
        void displayMealOfToday(Meal mealOfToday);
        void displayCategories(List<Category> categories);
        void displayEgyptSection(List<Meal> meals);
        void displayBeefSection(List<Meal> meals);
        void displayVeganSection(List<Meal> meals);

        void showMsg(String msg);

    }
}
