package eg.edu.iti.mealplaner.Favourites.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import eg.edu.iti.mealplaner.Favourites.presenter.FavPresenter;
import eg.edu.iti.mealplaner.R;
import eg.edu.iti.mealplaner.databinding.FavAdapterBinding;
import eg.edu.iti.mealplaner.model.models.Meal;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {
    List<Meal> meals;
    Context context;
    FavPresenter presenter;
    OnFavRemoveItemClick itemClick;

    public FavAdapter(List<Meal> meals, Context context, OnFavRemoveItemClick itemClick) {
        this.meals = meals;
        this.context = context;
        this.itemClick=itemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.fav_adapter, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.tvMealName.setText(meals.get(position).getStrMeal());
        Glide.with(context).load(meals.get(position).getStrMealThumb()).into(holder.binding.ivItem);
        holder.binding.cvMeal.setOnClickListener(view -> {
            FavFragmentDirections.ActionFavFragmentToDeatilsFragment action;
            action = FavFragmentDirections.actionFavFragmentToDeatilsFragment(meals.get(position).getIdMeal());
            Navigation.findNavController(view).navigate(action);
        });
        holder.binding.removeBg.setOnClickListener(view->{

            itemClick.removeItem(meals.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        FavAdapterBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = FavAdapterBinding.bind(itemView);
        }
    }
}