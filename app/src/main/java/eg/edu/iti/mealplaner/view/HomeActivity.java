package eg.edu.iti.mealplaner.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import java.util.HashSet;
import java.util.Set;

import eg.edu.iti.mealplaner.R;
import eg.edu.iti.mealplaner.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHomeBinding.inflate(getLayoutInflater());
        View view= binding.getRoot();
        setContentView(view);
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
}