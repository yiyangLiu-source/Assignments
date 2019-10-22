package com.assignment.order.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.order.entity.Food;
import com.assignment.order.R;

import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodVHolder> {

    public enum FoodVHolderType {
        FOOD_LIST, ORDER
    }

    public FoodVHolderType type;

    private Context context;
    private List<Food> foodList;
    private FoodListener foodListener;

    public FoodListAdapter(FoodVHolderType type, Context context) {
        this.type = type;
        this.context = context;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public void setFoodListener(FoodListener foodListener) {
        this.foodListener = foodListener;
    }

    @NonNull
    @Override
    public FoodVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodVHolder(LayoutInflater.from(context).inflate(R.layout.item_food, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final FoodVHolder holder, final int position) {

        // update view by data
        Food food = foodList.get(position);
        holder.ivPicture.setImageResource(food.getPicture());
        holder.tvName.setText(food.getName());
        holder.tvDescription.setText(food.getDescription());
        holder.tvCost.setText(food.getCost() + "");
        holder.tvCount.setText(food.getCount() + "");

        holder.orderTvCount.setText(food.getCount() + "");

        // event listener
        if (foodListener == null) {
            return;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodListener.clickItem(position);
            }
        });
        holder.ibAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(holder.tvCount.getText().toString());
                if (count == 99) {
                    return;
                }
                holder.tvCount.setText((count + 1) + "");
                foodList.get(position).setCount(count + 1);
                foodListener.clickAdd(position, count + 1);
            }
        });
        holder.ibDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(holder.tvCount.getText().toString());
                if (count == 0) {
                    return;
                }
                holder.tvCount.setText((count - 1) + "");
                foodList.get(position).setCount(count - 1);
                foodListener.clickDec(position, count - 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList == null ? 0 : foodList.size();
    }

    class FoodVHolder extends RecyclerView.ViewHolder {

        ImageView ivPicture;
        TextView tvName;
        TextView tvDescription;
        TextView tvCost;
        TextView tvCount;
        ImageButton ibAdd;
        ImageButton ibDec;

        TextView orderTvCount;

        public FoodVHolder(@NonNull View itemView) {
            super(itemView);
            ivPicture = itemView.findViewById(R.id.iv_picture);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvCost = itemView.findViewById(R.id.tv_cost);
            tvCount = itemView.findViewById(R.id.food0list_tv_count);
            ibAdd = itemView.findViewById(R.id.food0list_ib_add);
            ibDec = itemView.findViewById(R.id.food0list_ib_dec);

            orderTvCount = itemView.findViewById(R.id.order_tv_count);

            if (type == FoodVHolderType.FOOD_LIST) {
                orderTvCount.setVisibility(View.GONE);
            } else {
                tvCount.setVisibility(View.GONE);
                ibAdd.setVisibility(View.GONE);
                ibDec.setVisibility(View.GONE);
            }
        }
    }

     public interface FoodListener {
        void clickItem(int position);
        void clickAdd(int position, int count);
        void clickDec(int position, int count);
    }
}
