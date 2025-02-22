package eg.edu.iti.mealplaner.Home.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import eg.edu.iti.mealplaner.Home.model.models.Meal;
import eg.edu.iti.mealplaner.R;
import eg.edu.iti.mealplaner.databinding.AdabterItemsBinding;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
    List<Meal> meals;
    Context context;

    public ItemsAdapter(List<Meal> meals, Context context) {
        this.meals = meals;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.adabter_items,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.tvMealName.setText(meals.get(position).getStrMeal());
        Glide.with(context).load(meals.get(position).getStrMealThumb()).into(holder.binding.ivItem);
        holder.binding.cvMeal.setOnClickListener(view->{
            HomeFragmentDirections.ActionHomeFragmentToDeatilsFragment action;
            action = HomeFragmentDirections.actionHomeFragmentToDeatilsFragment(meals.get(position).getIdMeal());
            Navigation.findNavController(view).navigate(action);
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        AdabterItemsBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=AdabterItemsBinding.bind(itemView);
        }
    }
}
