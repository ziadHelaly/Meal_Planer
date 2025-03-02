package eg.edu.iti.mealplaner.Splash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import eg.edu.iti.mealplaner.Auth.view.AuthActivity;
import eg.edu.iti.mealplaner.R;
import eg.edu.iti.mealplaner.model.local.SharedPreference;
import eg.edu.iti.mealplaner.utilies.Const;
import eg.edu.iti.mealplaner.view.HomeActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    Handler handler;
    SharedPreference pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        pref=SharedPreference.getInstance(this);
        boolean isLogged = pref.getBoolean(Const.isLogged_TAG);
        Const.isLogged = isLogged;
        handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            Intent intent;
            if (isLogged) {
                Const.USER_ID =pref.getString(Const.USER_ID_TAG);
                Const.USER_NAME=pref.getString(Const.USER_NAME_TAG);
                intent = new Intent(SplashActivity.this, HomeActivity.class);
            } else {
                intent = new Intent(SplashActivity.this, AuthActivity.class);

            }
            startActivity(intent);
            finish();
        }, 3000);
    }
}