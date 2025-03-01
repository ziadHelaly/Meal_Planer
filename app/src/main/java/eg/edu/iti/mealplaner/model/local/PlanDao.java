package eg.edu.iti.mealplaner.model.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import eg.edu.iti.mealplaner.model.models.Plan;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface PlanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addPlan(Plan plan);

    @Delete
    Completable removePlan(Plan plan);
    @Query("select * from plans where userId = :userId and date = :day")
    Flowable<List<Plan>> getPlansByDay(String userId,String day);
}
