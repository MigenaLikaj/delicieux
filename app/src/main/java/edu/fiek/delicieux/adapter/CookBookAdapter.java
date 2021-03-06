package edu.fiek.delicieux.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import edu.fiek.delicieux.R;
import edu.fiek.delicieux.models.CookBook;
import edu.fiek.delicieux.ui.cookbook.CookBookDetailViewModel;


public class CookBookAdapter extends RecyclerView.Adapter<CookBookAdapter.CookBookViewHolder> {

    Context context;
    List<CookBook> cookBookList;
    CookBookDetailViewModel detailViewModel;

    public CookBookAdapter(Context context, List<CookBook> cookBookList, CookBookDetailViewModel detailViewModel) {
        this.context = context;
        this.cookBookList = cookBookList;
        this.detailViewModel = detailViewModel;
    }

    @NonNull
    @Override
    public CookBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cook_book_row_item, parent, false);
        return new CookBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CookBookViewHolder holder, final int position) {

        Glide.with(holder.foodImage.getContext()).load(cookBookList.get(position).getMedia()).into(holder.foodImage);
        holder.name.setText(cookBookList.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailViewModel.setSelected(cookBookList.get(position));
                Navigation.findNavController(view).navigate(R.id.cookBookDetailFragment);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cookBookList.size();
    }

    public static final class CookBookViewHolder extends RecyclerView.ViewHolder {

        ImageView foodImage;
        TextView name;

        public CookBookViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.food_image);
            name = itemView.findViewById(R.id.name);

        }
    }
}
