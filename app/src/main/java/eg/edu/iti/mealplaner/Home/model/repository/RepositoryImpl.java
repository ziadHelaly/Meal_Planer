package eg.edu.iti.mealplaner.Home.model.repository;

import eg.edu.iti.mealplaner.Home.model.network.MealsRemoteDataSource;
import eg.edu.iti.mealplaner.Home.model.network.HomeNetworkCallBack;
import eg.edu.iti.mealplaner.utilies.NetworkCalls;

public class RepositoryImpl implements Repository {
    MealsRemoteDataSource remote;
    private static Repository repo = null;
    private RepositoryImpl(){
        remote= new MealsRemoteDataSource();
    }
    public static Repository getRepository() {
        if (repo ==null){
            repo =new RepositoryImpl();
        }
        return repo;
    }

    @Override
    public void getRandomMeal(HomeNetworkCallBack homeNetworkCallBack){
        remote.getRandomMeal(homeNetworkCallBack);
    }
    @Override
    public void getCategories(HomeNetworkCallBack homeNetworkCallBack){
        remote.getCategories(homeNetworkCallBack);
    }

    @Override
    public void getMealById(String id, HomeNetworkCallBack homeNetworkCallBack,NetworkCalls type) {
        remote.getMealById(id,homeNetworkCallBack,type);
    }

    @Override
    public void getFilteredDataByArea(String a, HomeNetworkCallBack homeNetworkCallBack, NetworkCalls type){
        remote.getFilteredDataByArea(a,homeNetworkCallBack,type);
    }

    @Override
    public void getFilteredDataByCategory(String c, HomeNetworkCallBack homeNetworkCallBack, NetworkCalls type) {
        remote.getFilteredDataByCategory(c,homeNetworkCallBack, type);
    }

    @Override
    public void getFilteredDataByIngradiants(String i, HomeNetworkCallBack homeNetworkCallBack, NetworkCalls type) {
        remote.getFilteredDataByIngredients(i,homeNetworkCallBack,type);
    }

}
