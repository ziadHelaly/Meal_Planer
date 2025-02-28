package eg.edu.iti.mealplaner.Auth.presenter;

import android.util.Log;

import eg.edu.iti.mealplaner.model.firebase.FireBaseAuthModel;
import eg.edu.iti.mealplaner.model.repository.Repository;

public class AuthPresenterImpl implements AuthPresenter {
    FireBaseAuthModel model;
    AuthPresenter.view view;
    Repository repo;

    public AuthPresenterImpl(AuthPresenter.view view,Repository repo) {
        this.model = new FireBaseAuthModel();
        this.view = view;
        this.repo=repo;
    }

    @Override
    public void signIn(String email, String password) {
        view.showProgressBar();
        model.singIn(email, password).addOnCompleteListener(task->{
            if (task.isSuccessful()){
                repo.saveUserId(task.getResult().getUser().getUid());
                view.hideProgressBar();
                view.onSuccess();
            }else {
                view.hideProgressBar();
                view.onFailure();
            }
        });

    }

    @Override
    public void signUp(String email, String password) {
        view.showProgressBar();
        model.singUp(email, password).addOnCompleteListener(task->{
            if (task.isSuccessful()){
                repo.saveUserId(task.getResult().getUser().getUid());
                view.hideProgressBar();
                view.onSuccess();
            }else {
                view.hideProgressBar();
                view.onFailure();
            }
        });
    }
}
