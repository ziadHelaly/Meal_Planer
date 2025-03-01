package eg.edu.iti.mealplaner.model.firebase;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class FireBaseAuthModel {

    public Task<AuthResult> singIn(String email, String password) {
        return FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password);
    }
    public Task<AuthResult> singUp(String email, String password) {
        return FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password);
    }
    public void signOut() {
         FirebaseAuth.getInstance().signOut();
    }
    public Task<AuthResult> signInWithGoogleCredential(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        return FirebaseAuth.getInstance().signInWithCredential(credential);
    }
}
