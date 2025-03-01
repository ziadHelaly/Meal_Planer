package eg.edu.iti.mealplaner.calender.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import eg.edu.iti.mealplaner.Details.view.IngrediantsAdapter;
import eg.edu.iti.mealplaner.R;
import eg.edu.iti.mealplaner.databinding.FavAdapterBinding;
import eg.edu.iti.mealplaner.model.models.Plan;
import eg.edu.iti.mealplaner.view.OnItemClicked;

public class PlansAdapter extends RecyclerView.Adapter<PlansAdapter.ViewHolder> {
    List<Plan> plans;
    Context context;
    OnPlanRemoved onPlanRemoved;
    OnItemClicked onItemClicked;
    public PlansAdapter(Context context, OnPlanRemoved onPlanRemoved, OnItemClicked onItemClicked) {
        this.context = context;
        this.onPlanRemoved = onPlanRemoved;
        this.onItemClicked=onItemClicked;
        plans= new ArrayList<>();
    }

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
        notifyDataSetChanged();
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
        holder.binding.tvMealName.setText(plans.get(position).getMealName());
        Glide.with(context).load(plans.get(position).getMealImg())
                .into(holder.binding.ivItem);
        holder.binding.removeBg.setOnClickListener(v -> {
            onPlanRemoved.removePlan(plans.get(position));
        });
        holder.binding.cvMeal.setOnClickListener(v -> {
            onItemClicked.navToDetailsScreen(plans.get(position).getMealId());
        });
    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        FavAdapterBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = FavAdapterBinding.bind(itemView);
        }
    }
}
