package eg.edu.iti.mealplaner.model.local;

import android.content.Context;

import java.util.List;

import eg.edu.iti.mealplaner.model.models.Meal;
import eg.edu.iti.mealplaner.model.models.Plan;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class MealLocalDataSourceImpl implements MealLocalDataSource {
    Context context;
    MealDao MealDao;
    Flowable<List<Meal>> Meals;
    PlanDao planDao;
    private static MealLocalDataSource mealLocalDataSource =null;
    private MealLocalDataSourceImpl(Context _context) {
        context = _context;
        MealDao = FavouritesDataBase.getInstance(context.getApplicationContext()).getMealDao();
        planDao = FavouritesDataBase.getInstance(context.getApplicationContext()).getPlanDao();
    }

    public static MealLocalDataSource getMealLocalDataSource(Context context) {
        if (mealLocalDataSource==null){
            mealLocalDataSource=new MealLocalDataSourceImpl(context);
        }
        return mealLocalDataSource;
    }

    @Override
    public Flowable<List<Meal>> getMeals(String id) {
        Meals = MealDao.getAllFavourites(id);
        return Meals;
    }

    @Override
    public Completable insert(Meal Meal) {
        return MealDao.insertItem(Meal);
    }

    @Override
    public Completable delete(Meal Meal) {
        return MealDao.deleteItem(Meal);
    }
    @Override
    public Single<Meal> getMeal(String id){
        return MealDao.getMeal(id);
    }

    @Override
    public Single<Integer> isFavourite(String id){
        return MealDao.isFavourite(id);
    }
    @Override
    public Flowable<List<Plan>> getPlans(String userId, String day){
        return planDao.getPlansByDay(userId,day);
    }
    @Override
    public Completable addPlan(Plan plan){
        return planDao.addPlan(plan);
    }
    @Override
    public Completable removePlan(Plan plan){
        return planDao.removePlan(plan);
    }
}
