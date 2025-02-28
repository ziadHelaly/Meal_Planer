package eg.edu.iti.mealplaner.Search.presenter;

import java.util.List;

import eg.edu.iti.mealplaner.model.models.Category;
import eg.edu.iti.mealplaner.model.models.Country;
import eg.edu.iti.mealplaner.model.models.Ingredient;
import eg.edu.iti.mealplaner.presenter.PresenterInterface;

public interface SearchPresenter extends PresenterInterface {
    void getData(String search);


    void setType(String type);

    interface View extends PresenterInterface.View {
        void showCategories(List<Category> categories);

        void showCountries(List<Country> countries);

        void showIngredients(List<Ingredient> ingredients);
    }
}
