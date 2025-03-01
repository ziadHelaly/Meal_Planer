package eg.edu.iti.mealplaner.calender.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import eg.edu.iti.mealplaner.model.models.Plan;
import eg.edu.iti.mealplaner.model.repository.Repository;
import eg.edu.iti.mealplaner.utilies.Const;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CalenderPresenterImpl implements CalenderPresenter {
    Repository repo;
    CalenderPresenter.View view;

    public CalenderPresenterImpl(Repository repo, View view) {
        this.repo = repo;
        this.view = view;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getPlansByDay(String date) {
        repo.getPlansByDay(Const.USER_ID, date).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> {
                            view.showPlansByDay(item);
                        },error->{
                            view.showMsg("Error");
                        },()->{
                            Log.d("``TAG``", "getPlansByDay: Complete");
                        });
    }
    private Plan holding;
    @SuppressLint("CheckResult")
    @Override
    public void removePlan(Plan plan) {
        holding=plan;
        repo.removePlan(plan)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->{
                    view.showRemovedMessage("Removed Successfully");
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void redoPlan() {
        repo.addPlan(holding)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->{
                    view.showMsg("Plan returned");
                });
    }
}
