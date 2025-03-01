package eg.edu.iti.mealplaner.Auth.presenter;

import android.content.Intent;

public interface AuthPresenter {
    void signIn(String email,String password);
    void signUp(String email,String password);

    void signInWithGoogle(Intent data);

    void guestMode();

    interface view{
        void onSuccess();
        void onFailure();
        void showProgressBar();
        void hideProgressBar();
    }
}
