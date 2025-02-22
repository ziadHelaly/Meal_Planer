package eg.edu.iti.mealplaner.Auth.presenter.interfaces;

public interface AuthPresenter {
    void signIn(String email,String password);
    void signUp(String email,String password);
    interface view{
        void onSuccess();
        void onFailure();
        void showProgressBar();
        void hideProgressBar();
    }
}
