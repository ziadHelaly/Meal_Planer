package eg.edu.iti.mealplaner.Details.view;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.app.DatePickerDialog;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.snackbar.Snackbar;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.iti.mealplaner.Details.view.DeatilsFragmentArgs;
import eg.edu.iti.mealplaner.R;
import eg.edu.iti.mealplaner.model.models.Meal;
import eg.edu.iti.mealplaner.model.repository.RepositoryImpl;
import eg.edu.iti.mealplaner.Details.presenter.DetailsPresenter;
import eg.edu.iti.mealplaner.Details.presenter.DetailsPresenterImpl;
import eg.edu.iti.mealplaner.databinding.FragmentDeatilsBinding;
import eg.edu.iti.mealplaner.utilies.CalenderUtil;
import eg.edu.iti.mealplaner.utilies.Const;


public class DeatilsFragment extends Fragment implements DetailsPresenter.View {

    FragmentDeatilsBinding binding;
    String id;
    DetailsPresenter presenter;
    boolean isFav = false;

    public DeatilsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDeatilsBinding.inflate(inflater, container, false);
        id = DeatilsFragmentArgs.fromBundle(getArguments()).getIdMeal();
        Log.d("```TAG```", "onCreateView: " + id);
        presenter = new DetailsPresenterImpl(RepositoryImpl.getRepository(getContext()), this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.isFavourite(id);
        binding.back.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack();
        });
        getLifecycle().addObserver(binding.youtubePlayer);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void showData(Meal meal) {
        binding.ivFav.setOnClickListener(v -> {
            if (!isFav) {
                presenter.addMealToFav(meal);
            } else {
                presenter.removeFromFav(meal);
            }
        });
        binding.tvMealName.setText(meal.getStrMeal());
        binding.tvCategoryDetails.setText(meal.getStrCategory());
        binding.tvInstractions.setText(meal.getStrInstructions());
        binding.tvCountryDetailes.setText(meal.getStrArea());
        Glide.with(getContext())
                .load(meal.getStrMealThumb())
                .apply(RequestOptions.bitmapTransform(
                        new RoundedCorners(16)
                ))
                .into(binding.ivMeal);
        binding.rvIngradaients.setAdapter(new IngrediantsAdapter(meal.getIngredient_Measure(), getContext()));
        String videoId = presenter.extractYouTubeVideoId(meal.getStrYoutube());
        binding.youtubePlayer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                if (videoId != null) {
                    youTubePlayer.cueVideo(videoId, 0f);
                } else {
                    youTubePlayer.cueVideo("9IVw7pc8a8I", 0f);
                }
            }

            @Override
            public void onError(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlayerError error) {
                super.onError(youTubePlayer, error);
                Toast.makeText(getContext(), "youtube error " + error.name(), Toast.LENGTH_SHORT).show();
            }
        });
        binding.ivPlan.setOnClickListener(v -> {
            if (Const.isLogged){
                setupDatePicker(meal);
            }else {
                showSnackBar("SignIn to use this Feature");
            }
        });

    }

    private void setupDatePicker(Meal meal) {
        // Get current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(), R.style.CustomDatePickerDialogTheme,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String formattedDate = CalenderUtil.getFormattedDate(dayOfMonth,month,year);
                        presenter.addToPlans(meal, formattedDate);
                    }
                },
                year, month, day
        );

        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        calendar.add(Calendar.DAY_OF_YEAR , 6);
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }

    @Override
    public void showSnackBar(String msg) {
        Snackbar.make(binding.getRoot(), msg, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onAddToFav() {
        binding.ivFav.setImageResource(R.drawable.ic_filled_fav);
        isFav = true;
    }

    @Override
    public void onRemoveFromFav() {
        binding.ivFav.setImageResource(R.drawable.ic_fav);
        isFav = false;
    }


    @Override
    public void showLoading() {
        binding.loadingScreen.setVisibility(VISIBLE);
        binding.cardView2.setVisibility(INVISIBLE);
        binding.cvMeal.setVisibility(INVISIBLE);
    }

    @Override
    public void hideLoading() {
        binding.loadingScreen.setVisibility(GONE);
        binding.cardView2.setVisibility(VISIBLE);
        binding.cvMeal.setVisibility(VISIBLE);
    }


}