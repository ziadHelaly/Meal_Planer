package eg.edu.iti.mealplaner.model.remote;

import eg.edu.iti.mealplaner.model.models.CategoryResponse;
import eg.edu.iti.mealplaner.model.models.Country;
import eg.edu.iti.mealplaner.model.models.FilterList;
import eg.edu.iti.mealplaner.model.models.Ingredient;
import eg.edu.iti.mealplaner.model.models.MealsResponse;
import io.reactivex.rxjava3.core.Single;

public interface MealsRemoteDataSource {

    Single<MealsResponse> getRandomMeal();

    Single<CategoryResponse> getCategories();

    Single<MealsResponse> getFilteredDataByArea(String a);

    Single<MealsResponse> getFilteredDataByCategory(String c);

    Single<MealsResponse> getFilteredDataByIngredients(String i);

    Single<MealsResponse> getMealById(String id);

    Single<FilterList<Country>> getCountriesList();

    Single<FilterList<Ingredient>> getIngredientsList();
}
