package eg.edu.iti.mealplaner.model.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "Plans", primaryKeys = {"userId", "mealId","date"})
public class Plan {
    @NonNull
    String userId;
    @NonNull
    String mealId;
    String mealImg;
    String mealName;
    @NonNull
    String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Plan() {
    }

    public Plan(String userId, String mealId, String mealImg, String mealName, String date) {
        this.userId = userId;
        this.mealId = mealId;
        this.mealImg = mealImg;
        this.mealName = mealName;
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getMealImg() {
        return mealImg;
    }

    public void setMealImg(String mealImg) {
        this.mealImg = mealImg;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }
}
