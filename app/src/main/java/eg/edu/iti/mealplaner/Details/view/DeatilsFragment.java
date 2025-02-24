package eg.edu.iti.mealplaner.Details.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.iti.mealplaner.Details.view.DeatilsFragmentArgs;
import eg.edu.iti.mealplaner.model.models.Meal;
import eg.edu.iti.mealplaner.model.repository.RepositoryImpl;
import eg.edu.iti.mealplaner.Details.presenter.DetailsPresenter;
import eg.edu.iti.mealplaner.Details.presenter.DetailsPresenterImpl;
import eg.edu.iti.mealplaner.databinding.FragmentDeatilsBinding;


public class DeatilsFragment extends Fragment implements DetailsPresenter.View {

    FragmentDeatilsBinding binding;
    String id;
    DetailsPresenter presenter;

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
        presenter=new DetailsPresenterImpl(RepositoryImpl.getRepository(getContext()),this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getData(id);
        binding.back.setOnClickListener(v->{
            Navigation.findNavController(view).popBackStack();
        });
        getLifecycle().addObserver(binding.youtubePlayer);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showData(Meal meal) {
        binding.ivFav.setOnClickListener(v->{
            presenter.addMealToFav(meal);
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
        binding.rvIngradaients.setAdapter(new IngrediantsAdapter(meal.getIngredient_Measure(),getContext()));
        String videoId=extractYouTubeVideoId(meal.getStrYoutube());
        binding.youtubePlayer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.cueVideo(videoId,0f);
            }

            @Override
            public void onError(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlayerError error) {
                super.onError(youTubePlayer, error);
                Toast.makeText(getContext(), "youtube error "+error.name(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private String extractYouTubeVideoId(String videoUrl) {
        if (videoUrl == null) return null;

        String pattern = "^(?:https?://)?(?:www\\.)?(?:youtube\\.com/(?:[^/]+/.+/|(?:v|e(?:mbed)?)|.*[?&]v=)|youtu\\.be/)([^\"&?/ ]{11})";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(videoUrl);
        return matcher.find() ? matcher.group(1) : null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}