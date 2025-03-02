package eg.edu.iti.mealplaner.model.remote;

import static eg.edu.iti.mealplaner.utilies.Const.BASE_URL;

import android.content.Context;

import java.io.File;

import eg.edu.iti.mealplaner.model.models.CategoryResponse;
import eg.edu.iti.mealplaner.model.models.Country;
import eg.edu.iti.mealplaner.model.models.FilterList;
import eg.edu.iti.mealplaner.model.models.Ingredient;
import eg.edu.iti.mealplaner.model.models.MealsResponse;
import eg.edu.iti.mealplaner.utilies.Const;
import io.reactivex.rxjava3.core.Single;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteDataSourceImpl implements MealsRemoteDataSource {
    ApiService myApi;
    private static MealsRemoteDataSource mealsRemoteDataSource=null;

    private MealsRemoteDataSourceImpl(Context context) {
        //TODO edit it in search
        Cache cache = new Cache(new File(context.getCacheDir(), "cache"), Const.CACHE_SIZE);
        Interceptor onlineInterceptor = chain -> {
            okhttp3.Response response = chain.proceed(chain.request());
            return response.newBuilder()
                    .header("Cache-Control", "public, max-age=60")
                    .build();
        };
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .addNetworkInterceptor(onlineInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        myApi = retrofit.create(ApiService.class);
    }

    public static MealsRemoteDataSource getInstance(Context context){
        if (mealsRemoteDataSource ==null){
            mealsRemoteDataSource=new MealsRemoteDataSourceImpl(context);
        }
        return mealsRemoteDataSource;
    }
    @Override
    public Single<MealsResponse> getRandomMeal() {
        return myApi.getRandomMeal();
    }

    @Override
    public Single<CategoryResponse> getCategories() {
        return myApi.getCategories();
    }

    @Override
    public Single<MealsResponse> getFilteredDataByArea(String a) {
        return myApi.getFilteredListByArea(a);
    }

    @Override
    public Single<MealsResponse> getFilteredDataByCategory(String c) {
        return myApi.getFilteredListByCategory(c);
    }

    @Override
    public Single<MealsResponse> getFilteredDataByIngredients(String i) {
        return myApi.getFilteredListByIngredient(i);
    }

    @Override
    public Single<MealsResponse> getMealById(String id) {
        return myApi.getMealById(id);
    }

    @Override
    public Single<FilterList<Country>> getCountriesList() {
        return myApi.getCountryList("list");
    }

    @Override
    public Single<FilterList<Ingredient>> getIngredientsList() {
        return myApi.getIngredientsList("list");
    }
}
