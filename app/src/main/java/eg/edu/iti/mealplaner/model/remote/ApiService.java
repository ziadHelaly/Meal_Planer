package eg.edu.iti.mealplaner.model.remote;

import eg.edu.iti.mealplaner.model.models.Category;
import eg.edu.iti.mealplaner.model.models.CategoryResponse;
import eg.edu.iti.mealplaner.model.models.Country;
import eg.edu.iti.mealplaner.model.models.FilterList;
import eg.edu.iti.mealplaner.model.models.Ingredient;
import eg.edu.iti.mealplaner.model.models.MealsResponse;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("random.php")
    Single<MealsResponse> getRandomMeal();

    @GET("categories.php")
    Single<CategoryResponse> getCategories();

    @GET("filter.php")
    Single<MealsResponse> getFilteredListByArea(@Query("a") String a);

    @GET("filter.php")
    Single<MealsResponse> getFilteredListByCategory(@Query("c") String c);

    @GET("filter.php")
    Single<MealsResponse> getFilteredListByIngredient(@Query("i") String i);

    @GET("lookup.php")
    Single<MealsResponse> getMealById(@Query("i") String id);


    @GET("list.php")
    Single<FilterList<Country>> getCountryList(@Query("a") String a);
    @GET("list.php")
    Single<FilterList<Ingredient>> getIngredientsList(@Query("i") String i);
}
