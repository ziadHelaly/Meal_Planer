package eg.edu.iti.mealplaner.Home.presenter;

import eg.edu.iti.mealplaner.Home.model.models.Meal;

public interface HomePresenter {
    void getMealOfToday();
    public interface View{
        void showLoadingScreen();
        void hideLoadingScreen();
        void displayMealOfToday(Meal mealOfToday);
    }
}
