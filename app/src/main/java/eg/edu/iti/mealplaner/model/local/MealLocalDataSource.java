package eg.edu.iti.mealplaner.model.local;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.edu.iti.mealplaner.model.models.Meal;

public class MealLocalDataSource {
    Context context;
    MealDao MealDao;
    LiveData<List<Meal>> Meals;
    public MealLocalDataSource(Context _context) {
        context = _context;
        MealDao = FavouritesDataBase.getInstance(context.getApplicationContext()).getMealDao();
    }


    public LiveData<List<Meal>> getMeals(String id) {
        Meals=MealDao.getAllFavourites(id);
        return Meals;
    }

    public void insert(Meal Meal) {
        new Thread(() -> {
            MealDao.insertItem(Meal);
        }).start();
    }

    public void delete(Meal Meal) {
        new Thread(() -> {
            MealDao.deleteItem(Meal);
        }).start();
    }
}
