package eg.edu.iti.mealplaner.Auth.model;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FireBaseAuthModel {

    public Task<AuthResult> singIn(String email, String password) {
        return FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password);
    }
    public Task<AuthResult> singUp(String email, String password) {
        return FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password);
    }

}
