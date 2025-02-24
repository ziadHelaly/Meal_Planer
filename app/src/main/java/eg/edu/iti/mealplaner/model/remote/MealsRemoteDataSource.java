package eg.edu.iti.mealplaner.model.remote;

import static eg.edu.iti.mealplaner.utilies.Const.BASE_URL;

import eg.edu.iti.mealplaner.model.models.CategoryResponse;
import eg.edu.iti.mealplaner.model.models.MealsResponse;
import eg.edu.iti.mealplaner.utilies.NetworkCalls;
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
    public void getRandomMeal(HomeNetworkCallBack networkCallBack) {
        myApi.getRandomMeal().enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                if (response.body() != null) {
                    networkCallBack.onSuccessResultMeals(response.body().getMeals(), NetworkCalls.MealOfToday);
                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                networkCallBack.onFailure(t.getMessage());
            }
        });
    }

    public void getCategories(HomeNetworkCallBack homeNetworkCallBack) {
        myApi.getCategories().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                homeNetworkCallBack.onSuccessResultCategories(response.body().getCategories());
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                homeNetworkCallBack.onFailure(t.getMessage());
            }
        });
    }

    public void getFilteredDataByArea(String a, HomeNetworkCallBack homeNetworkCallBack,NetworkCalls type) {
        myApi.getFilteredListByArea(a).enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                homeNetworkCallBack.onSuccessResultMeals(response.body().getMeals(), type);
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                homeNetworkCallBack.onFailure(t.getMessage());
            }
        });
    }

    public void getFilteredDataByCategory(String c, HomeNetworkCallBack homeNetworkCallBack,NetworkCalls type) {
        myApi.getFilteredListByCategory(c).enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                homeNetworkCallBack.onSuccessResultMeals(response.body().getMeals(), type);
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                homeNetworkCallBack.onFailure(t.getMessage());
            }
        });
    }

    public void getFilteredDataByIngredients(String i, HomeNetworkCallBack homeNetworkCallBack,NetworkCalls type) {
        myApi.getFilteredListByIngredient(i).enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                homeNetworkCallBack.onSuccessResultMeals(response.body().getMeals(), type);
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                homeNetworkCallBack.onFailure(t.getMessage());
            }
        });
    }
    public void getMealById(String id, HomeNetworkCallBack homeNetworkCallBack,NetworkCalls type) {
        myApi.getMealById(id).enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                homeNetworkCallBack.onSuccessResultMeals(response.body().getMeals(), type);
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                homeNetworkCallBack.onFailure(t.getMessage());
            }
        });
    }

}
