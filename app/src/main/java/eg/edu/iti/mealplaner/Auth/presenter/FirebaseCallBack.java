package eg.edu.iti.mealplaner.Auth.presenter;

import com.google.firebase.auth.FirebaseUser;

public interface FirebaseCallBack {

    void onSuccess(FirebaseUser user);
    void onFailure();

    void onSuccessGoogle(FirebaseUser user);
}
