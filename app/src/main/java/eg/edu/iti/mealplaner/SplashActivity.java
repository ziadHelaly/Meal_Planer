package eg.edu.iti.mealplaner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import eg.edu.iti.mealplaner.Auth.view.AuthActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
       handler=new Handler(Looper.getMainLooper());
       handler.postDelayed(()->{
           Intent intent=new Intent(SplashActivity.this, AuthActivity.class);
           startActivity(intent);
           finish();
       },3000);
    }
}