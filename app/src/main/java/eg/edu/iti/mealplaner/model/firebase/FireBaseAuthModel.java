package eg.edu.iti.mealplaner.model.firebase;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import eg.edu.iti.mealplaner.Auth.presenter.FirebaseCallBack;

public class FireBaseAuthModel {
    FirebaseAuth firebaseAuth;
    private static FireBaseAuthModel fireBaseAuthModel;

    public FireBaseAuthModel(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    public FireBaseAuthModel getFireBase(FirebaseAuth firebaseAuth) {
        if (fireBaseAuthModel == null) {
            fireBaseAuthModel = new FireBaseAuthModel(firebaseAuth);
        }
        return fireBaseAuthModel;
    }

    public void singIn(String email, String password, FirebaseCallBack callBack) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                callBack.onSuccess(task.getResult().getUser());
            }else {
                callBack.onFailure();
            }
        });
    }

    public void singUp(String email, String password,FirebaseCallBack callBack) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task->{
            if (task.isSuccessful()){
                callBack.onSuccess(task.getResult().getUser());
            }else {
                callBack.onFailure();
            }
        });
    }

    public void signOut() {
        firebaseAuth.signOut();
    }

    public void signInWithGoogleCredential(String idToken,FirebaseCallBack callBack) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(result -> {
            if (result.isSuccessful()) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                callBack.onSuccessGoogle(user);
            }
        });
    }
}
