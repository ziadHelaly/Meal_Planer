package eg.edu.iti.mealplaner.Auth.presenter;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import eg.edu.iti.mealplaner.model.firebase.FireBaseAuthModel;
import eg.edu.iti.mealplaner.model.repository.Repository;
import eg.edu.iti.mealplaner.utilies.Const;

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
                Log.d("``TAG``", "signIn: "+task.getResult().getUser().getUid());
                repo.saveUserName("user"+task.getResult().getUser().getUid().subSequence(0,5));
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
                repo.saveUserName("user"+task.getResult().getUser().getUid().subSequence(0,5));
                view.hideProgressBar();
                view.onSuccess();
            }else {
                view.hideProgressBar();
                view.onFailure();
            }
        });
    }
    @Override
    public void signInWithGoogle(Intent data) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            if (account != null) {
                model.signInWithGoogleCredential(account.getIdToken()).addOnCompleteListener(result -> {
                    if (result.isSuccessful()) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        repo.saveUserId(result.getResult().getUser().getUid());
                        repo.saveUserName(user.getDisplayName());
                        view.onSuccess();
                    }
                });
            }
        } catch (ApiException e) {
            Log.d("``TAG``", "signInWithGoogle: "+e.getMessage());
        }
    }
    @Override
    public void guestMode() {
        Const.isLogged=false;
        view.onSuccess();
    }
}
