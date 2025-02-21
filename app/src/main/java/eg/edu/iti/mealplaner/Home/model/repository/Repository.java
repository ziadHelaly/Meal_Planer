package eg.edu.iti.mealplaner.Home.model.repository;

import eg.edu.iti.mealplaner.Home.model.network.NetworkCallBack;

public interface Repository {
    void getRandomMeal(NetworkCallBack networkCallBack);
}
