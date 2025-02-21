package eg.edu.iti.mealplaner.Home.model.network;

import eg.edu.iti.mealplaner.Home.model.models.MealsResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("random.php")
    Call<MealsResponse> getRandomMeal();
}
