package eg.edu.iti.mealplaner.model.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import eg.edu.iti.mealplaner.model.models.Meal;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface MealDao {
    @Query("select * from fav_table where userId = :id")
    Flowable<List<Meal>> getAllFavourites(String id);

    @Query("select * from fav_table where idMeal = :id")
    Single<Meal> getMeal(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertItem(Meal meal);

    @Delete
    Completable deleteItem(Meal meal);
}
