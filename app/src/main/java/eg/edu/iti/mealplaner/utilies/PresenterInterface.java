package eg.edu.iti.mealplaner.utilies;

import java.util.List;

import eg.edu.iti.mealplaner.model.models.Meal;

public interface PresenterInterface {
    void getData();
    interface View{
        void showData(List<Meal> meals);
        void showErrorMsg(String msg);
    }
}
