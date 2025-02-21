package eg.edu.iti.mealplaner.Home.model.network;

import static eg.edu.iti.mealplaner.utilies.Const.BASE_URL;

import eg.edu.iti.mealplaner.Home.model.models.MealsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteDataSource {
    ApiService myApi;
    public MealsRemoteDataSource() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(ApiService.class);
    }
    /*public static ProductRemoteDataSource getInstance(){
        if (client ==null){
            client=new ProductRemoteDataSource();
        }
        return client;
    }*/
    public void getRandomMeal(NetworkCallBack networkCallBack){
        myApi.getRandomMeal().enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                if (response.body() != null) {
                    networkCallBack.onSuccessResult(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                networkCallBack.onFailure(t.getMessage());
            }
        });
    }


}
