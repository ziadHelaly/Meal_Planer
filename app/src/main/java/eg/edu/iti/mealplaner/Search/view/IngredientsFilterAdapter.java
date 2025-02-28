package eg.edu.iti.mealplaner.Search.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import eg.edu.iti.mealplaner.R;
import eg.edu.iti.mealplaner.databinding.AdapterFiltersBinding;
import eg.edu.iti.mealplaner.model.models.Ingredient;
import eg.edu.iti.mealplaner.utilies.FilterType;
import eg.edu.iti.mealplaner.view.OnItemClicked;

public class IngredientsFilterAdapter extends RecyclerView.Adapter<IngredientsFilterAdapter.ViewHolder> {
    List<Ingredient> ingredients;
    Context context;
    OnItemClicked itemClicked;

    public IngredientsFilterAdapter(Context context, OnItemClicked itemClicked) {
        this.context = context;
        this.itemClicked = itemClicked;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.adapter_filters,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.cvFilter.setOnClickListener(view-> {
            itemClicked.navToFilteredScreen(FilterType.Ingredient, ingredients.get(position).getStrIngredient());
        });
        holder.binding.tvTitle.setText(ingredients.get(position).getStrIngredient());
        Glide.with(context)
                .load("https://www.themealdb.com/images/ingredients/"+ingredients.get(position).getStrIngredient()+"-Small.png")
                .into(holder.binding.ivItem);

    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        AdapterFiltersBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = AdapterFiltersBinding.bind(itemView);
        }
    }
}