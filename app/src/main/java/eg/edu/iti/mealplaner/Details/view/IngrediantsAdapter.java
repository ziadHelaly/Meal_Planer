package eg.edu.iti.mealplaner.Details.view;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import eg.edu.iti.mealplaner.R;
import eg.edu.iti.mealplaner.databinding.IngrediantsAdapterBinding;

public class IngrediantsAdapter extends RecyclerView.Adapter<IngrediantsAdapter.ViewHodler> {
    List<Pair<String,String>> ing_mess;
    Context context;

    public IngrediantsAdapter(List<Pair<String, String>> ing_mess, Context context) {
        this.ing_mess = ing_mess;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.ingrediants_adapter,parent,false);
        return new ViewHodler(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler holder, int position) {
        holder.binding.tvIngred.setText(ing_mess.get(position).first);
        holder.binding.tvMessgure.setText(ing_mess.get(position).second);
        Glide.with(context)
                .load("https://www.themealdb.com/images/ingredients/"+ing_mess.get(position).first+"-Small.png")
                .into(holder.binding.ivIngred);
    }

    @Override
    public int getItemCount() {
        return ing_mess.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder {
        IngrediantsAdapterBinding binding;
        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            binding=IngrediantsAdapterBinding.bind(itemView);
        }
    }
}
