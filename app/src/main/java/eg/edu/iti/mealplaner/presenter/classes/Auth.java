package eg.edu.iti.mealplaner.presenter.classes;

import eg.edu.iti.mealplaner.model.FireBaseAuthModel;
import eg.edu.iti.mealplaner.presenter.interfaces.AuthPresenter;

public class Auth implements AuthPresenter {
    FireBaseAuthModel model;
    AuthPresenter.view view;

    public Auth(AuthPresenter.view view) {
        this.model = new FireBaseAuthModel();
        this.view = view;
    }

    @Override
    public void signIn(String email, String password) {
        view.showProgressBar();
        model.singIn(email, password).addOnCompleteListener(task->{
            if (task.isSuccessful()){
                view.hideProgressBar();
                view.onSuccess();
            }else {
                view.hideProgressBar();
                view.onFailure();
            }
        });

    }
}
