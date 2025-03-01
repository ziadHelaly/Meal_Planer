package eg.edu.iti.mealplaner.model.local;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import eg.edu.iti.mealplaner.model.models.Meal;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class MealLocalDataSource {
    Context context;
    MealDao MealDao;
    Flowable<List<Meal>> Meals;

    public MealLocalDataSource(Context _context) {
        context = _context;
        MealDao = FavouritesDataBase.getInstance(context.getApplicationContext()).getMealDao();
    }


    public Flowable<List<Meal>> getMeals(String id) {
        Meals = MealDao.getAllFavourites(id);
        return Meals;
    }

    public Completable insert(Meal Meal) {
        return MealDao.insertItem(Meal);
    }

    public Completable delete(Meal Meal) {
        return MealDao.deleteItem(Meal);
    }
    public Single<Meal> getMeal(String id){
        return MealDao.getMeal(id);
    }

    public Single<Integer> isFavourite(String id){
        return MealDao.isFavourite(id);
    }
}
