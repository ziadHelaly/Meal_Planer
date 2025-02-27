package eg.edu.iti.mealplaner.FilteredScreen.view;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import eg.edu.iti.mealplaner.FilteredScreen.presenter.FilteredPresenter;
import eg.edu.iti.mealplaner.FilteredScreen.presenter.FilteredPresenterImpl;
import eg.edu.iti.mealplaner.databinding.FragmentFilteredBinding;
import eg.edu.iti.mealplaner.model.models.Meal;
import eg.edu.iti.mealplaner.model.repository.RepositoryImpl;
import eg.edu.iti.mealplaner.utilies.FilterType;
import eg.edu.iti.mealplaner.view.ItemsAdapter;
import eg.edu.iti.mealplaner.view.OnItemClicked;


public class FilteredFragment extends Fragment implements FilteredPresenter.View , OnItemClicked {

    FragmentFilteredBinding binding;
    FilteredPresenter presenter;
    String name;
    FilterType type;

    public FilteredFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentFilteredBinding.inflate(inflater,container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        type=FilteredFragmentArgs.fromBundle(getArguments()).getFilterType();
        name=FilteredFragmentArgs.fromBundle(getArguments()).getCategoryName();
        presenter=new FilteredPresenterImpl(this, RepositoryImpl.getRepository(getContext()));
        presenter.getData(type,name);
        binding.tvCategoryName.setText(name+" Category");
        binding.backBtn.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack();
        });
    }

    @Override
    public void showData(List<Meal> meals) {
        binding.rvItems.setAdapter(new ItemsAdapter(meals,getContext(),this));
    }

    @Override
    public void showErrorMsg(String msg) {
        Snackbar.make(getView(),msg,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingScreen() {
        binding.loadingScreen.setVisibility(VISIBLE);
    }

    @Override
    public void hideLoadingScreen() {
        binding.loadingScreen.setVisibility(GONE);
    }

    @Override
    public void navToDetailsScreen(String id) {
        FilteredFragmentDirections.ActionFilteredFragmentToDeatilsFragment action;
        action=FilteredFragmentDirections.actionFilteredFragmentToDeatilsFragment(id);
        Navigation.findNavController(getView()).navigate(action);
    }


}