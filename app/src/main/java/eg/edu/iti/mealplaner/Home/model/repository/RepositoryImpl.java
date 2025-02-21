package eg.edu.iti.mealplaner.Home.model.repository;

import eg.edu.iti.mealplaner.Home.model.network.MealsRemoteDataSource;
import eg.edu.iti.mealplaner.Home.model.network.NetworkCallBack;

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
    public void getRandomMeal(NetworkCallBack networkCallBack){
        remote.getRandomMeal(networkCallBack);
    }

}
