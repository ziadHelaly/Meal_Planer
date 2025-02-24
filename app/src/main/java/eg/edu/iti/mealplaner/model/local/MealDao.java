package eg.edu.iti.mealplaner.model.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import eg.edu.iti.mealplaner.model.models.Meal;

@Dao
public interface MealDao {
    @Query("select * from fav_table where userId = :id")
    LiveData<List<Meal>> getAllFavourites(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertItem(Meal meal);

    @Delete
    void deleteItem(Meal meal);
}
