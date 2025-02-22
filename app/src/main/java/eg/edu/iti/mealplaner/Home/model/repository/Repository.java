package eg.edu.iti.mealplaner.Home.model.repository;

import eg.edu.iti.mealplaner.Home.model.network.HomeNetworkCallBack;
import eg.edu.iti.mealplaner.utilies.NetworkCalls;

public interface Repository {
    void getRandomMeal(HomeNetworkCallBack homeNetworkCallBack);
    void getCategories(HomeNetworkCallBack homeNetworkCallBack);
    void getMealById(String id,HomeNetworkCallBack homeNetworkCallBack,NetworkCalls type);
    void getFilteredDataByArea(String a,HomeNetworkCallBack homeNetworkCallBack, NetworkCalls type);
    void getFilteredDataByCategory(String c,HomeNetworkCallBack homeNetworkCallBack, NetworkCalls type);
    void getFilteredDataByIngradiants(String i,HomeNetworkCallBack homeNetworkCallBack, NetworkCalls type);
}
