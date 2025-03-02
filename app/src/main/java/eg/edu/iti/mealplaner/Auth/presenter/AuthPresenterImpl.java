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

public class AuthPresenterImpl implements AuthPresenter ,FirebaseCallBack{

    AuthPresenter.view view;
    Repository repo;

    public AuthPresenterImpl(AuthPresenter.view view,Repository repo) {
        this.view = view;
        this.repo=repo;
    }

    @Override
    public void signIn(String email, String password) {
        view.showProgressBar();
        repo.singIn(email, password,this);
    }

    @Override
    public void signUp(String email, String password) {
        view.showProgressBar();
        repo.singUp(email, password,this);
    }
    @Override
    public void signInWithGoogle(Intent data) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            if (account != null) {
                repo.signInWithGoogleCredential(account.getIdToken(),this);
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

    @Override
    public void onSuccess(FirebaseUser user) {
        repo.saveUserId(user.getUid());
        repo.saveUserName("user"+user.getUid().subSequence(0,5));
        view.hideProgressBar();
        view.onSuccess();
    }

    @Override
    public void onFailure() {
        view.hideProgressBar();
        view.onFailure();
    }

    @Override
    public void onSuccessGoogle(FirebaseUser user) {
        repo.saveUserId(user.getUid());
        repo.saveUserName(user.getDisplayName());
        view.onSuccess();
    }
}