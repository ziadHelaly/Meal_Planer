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
import eg.edu.iti.mealplaner.model.models.Country;
import eg.edu.iti.mealplaner.utilies.CountriesCode;
import eg.edu.iti.mealplaner.utilies.FilterType;
import eg.edu.iti.mealplaner.view.OnItemClicked;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder> {
    List<Country> countries;

    public void setCountries(List<Country> countries) {
        this.countries = countries;
        notifyDataSetChanged();
    }

    Context context;
    OnItemClicked itemClicked;

    public CountriesAdapter(List<Country> countries, Context context, OnItemClicked itemClicked) {
        this.countries = countries;
        this.context = context;
        this.itemClicked = itemClicked;
    }
    public CountriesAdapter( Context context, OnItemClicked itemClicked) {
        this.context = context;
        this.itemClicked = itemClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.adapter_filters, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.cvFilter.setOnClickListener(view-> {
            itemClicked.navToFilteredScreen(FilterType.Area, countries.get(position).getStrArea());
        });
        holder.binding.tvTitle.setText(countries.get(position).getStrArea());
        Glide.with(context)
                .load("https://flagsapi.com/"+ CountriesCode.getCountryCode(countries.get(position).getStrArea()) +"/flat/64.png")
                .into(holder.binding.ivItem);
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        AdapterFiltersBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = AdapterFiltersBinding.bind(itemView);
        }
    }
}
