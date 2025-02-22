package eg.edu.iti.mealplaner.Home.model.network;

import eg.edu.iti.mealplaner.Home.model.models.CategoryResponse;
import eg.edu.iti.mealplaner.Home.model.models.MealsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("random.php")
    Call<MealsResponse> getRandomMeal();
    @GET("categories.php")
    Call<CategoryResponse> getCategories();
    @GET("filter.php")
    Call<MealsResponse> getFilteredListByArea(@Query("a") String a);
    @GET("filter.php")
    Call<MealsResponse> getFilteredListByCategory(@Query("c") String c);
    @GET("filter.php")
    Call<MealsResponse> getFilteredListByIngredient(@Query("i") String i);
    @GET("lookup.php")
    Call<MealsResponse> getMealById(@Query("i") String id);
}
