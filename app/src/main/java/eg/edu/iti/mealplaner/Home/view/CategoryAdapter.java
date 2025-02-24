package eg.edu.iti.mealplaner.Home.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import eg.edu.iti.mealplaner.model.models.Category;
import eg.edu.iti.mealplaner.R;
import eg.edu.iti.mealplaner.databinding.AdapterCategoriesBinding;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    List<Category> categories;
    Context _context;
    public void setCategories(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    public CategoryAdapter(List<Category> categories,Context context) {
        this.categories = categories;
        _context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.adapter_categories,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.tvCategoryName.setText(categories.get(position).getStrCategory());
        Glide.with(_context).load(categories.get(position).getStrCategoryThumb())
                .into(holder.binding.imageView2);
        holder.binding.cvItem.setOnClickListener(v->{

        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AdapterCategoriesBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=AdapterCategoriesBinding.bind(itemView);
        }
    }
}
