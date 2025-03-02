package eg.edu.iti.mealplaner.model.local;

import java.util.List;

import eg.edu.iti.mealplaner.model.models.Meal;
import eg.edu.iti.mealplaner.model.models.Plan;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface MealLocalDataSource {
    Flowable<List<Meal>> getMeals(String id);

    Completable insert(Meal Meal);

    Completable delete(Meal Meal);

    Single<Meal> getMeal(String id);

    Single<Integer> isFavourite(String id);

    Flowable<List<Plan>> getPlans(String userId, String day);

    Completable addPlan(Plan plan);

    Completable removePlan(Plan plan);
}
