package eg.edu.iti.mealplaner.presenter.interfaces;

public interface AuthPresenter {
    void signIn(String email,String password);
    interface view{
        void onSuccess();
        void onFailure();
        void showProgressBar();
        void hideProgressBar();
    }
}
