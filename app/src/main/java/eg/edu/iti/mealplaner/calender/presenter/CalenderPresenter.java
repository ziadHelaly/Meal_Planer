package eg.edu.iti.mealplaner.calender.presenter;

import java.util.List;

import eg.edu.iti.mealplaner.model.models.Plan;

public interface CalenderPresenter {
    void getPlansByDay(String date);

    void removePlan(Plan plan);

    void redoPlan();

    interface View{
        void showPlansByDay(List<Plan> plans);

        void showMsg(String error);
        void showRemovedMessage(String msg);
        void showOnGuestMode();
    }
}
