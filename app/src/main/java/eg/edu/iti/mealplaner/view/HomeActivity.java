package eg.edu.iti.mealplaner.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import java.util.HashSet;
import java.util.Set;

import eg.edu.iti.mealplaner.R;
import eg.edu.iti.mealplaner.databinding.ActivityHomeBinding;
import eg.edu.iti.mealplaner.utilies.NetworkStatusManager;
import eg.edu.iti.mealplaner.utilies.NetworkUtil;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private NetworkStatusManager networkStatusManager;
    private BroadcastReceiver networkChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkNetworkStatus();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHomeBinding.inflate(getLayoutInflater());
        View view= binding.getRoot();
        setContentView(view);
        //network
        networkStatusManager = NetworkStatusManager.getInstance();
        checkNetworkStatus();
        registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(binding.navBottom,navHostFragment.getNavController());

        Set<Integer> hiddenFragments = new HashSet<>();
        hiddenFragments.add(R.id.deatilsFragment);
        hiddenFragments.add(R.id.filteredFragment);

        navHostFragment.getNavController().addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (hiddenFragments.contains(destination.getId())) {
                binding.navBottom.setVisibility(View.GONE);
            } else {
                binding.navBottom.setVisibility(View.VISIBLE);
            }
        });

    }
    private void checkNetworkStatus() {
        boolean isConnected = NetworkUtil.isNetworkAvailable(this);
        networkStatusManager.updateNetworkStatus(isConnected);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister BroadcastReceiver
        unregisterReceiver(networkChangeReceiver);
    }
}