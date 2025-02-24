package eg.edu.iti.mealplaner.model.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import eg.edu.iti.mealplaner.model.models.Meal;

@Database(entities = {Meal.class}, version = 1)
public abstract class FavouritesDataBase extends RoomDatabase {
    private static FavouritesDataBase instance=null;
    public abstract MealDao getMealDao();
    public static synchronized FavouritesDataBase getInstance(Context context){
        if (instance==null){
            instance= Room.databaseBuilder(context, FavouritesDataBase.class,"my_Favourites")
                    .build();
        }
        return instance;
    }
}
