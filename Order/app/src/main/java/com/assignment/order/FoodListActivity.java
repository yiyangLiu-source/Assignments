package com.assignment.order;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.order.adapter.FoodListAdapter;
import com.assignment.order.entity.Food;
import com.assignment.order.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class FoodListActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1001;

    private RecyclerView recyclerView;
    private FoodListAdapter adapter;
    private TextView tvTotalCost;

    private List<Food> foodList;
    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FoodListAdapter(FoodListAdapter.FoodVHolderType.FOOD_LIST, this);
        recyclerView.setAdapter(adapter);

        tvTotalCost = findViewById(R.id.tv_total_cost);

        setTitle("Food List");
    }

    private void initData() {
        order = new Order();
        foodList = new ArrayList<>();
        foodList.add(new Food(R.mipmap.pic1, "Pineapple Burger Package", "Double-layer Angus thick cattle pineapple fort + one fries", 10.0F));
        foodList.add(new Food(R.mipmap.pic2, "New Angus Black Gold Double Meal", "New Angus Pineapple Burger + Angus Cheeseburger + 5 McDonald's Chicken Pieces + Spicy Chicken Wings + 2 Cups of Coca Cola", 20.0F));
        foodList.add(new Food(R.mipmap.pic3, "Just eat chicken double meal", "2 Spicy Chicken Legs + 5 Meer Chickens + 2 Cokes", 15.0F));
        foodList.add(new Food(R.mipmap.pic4, "Salt can be sweet afternoon tea for two meals", "2 pairs of wheat spicy chicken wings + 10 pieces of wheat chicken + Oreo wheat whirlwind 2 cups", 10.0F));
        foodList.add(new Food(R.mipmap.pic5, "Enjoy three meals", "Plate roast chicken leg fort 1 part + wheat spicy chicken leg burger one + golden crispy potato grid + wheat music chicken 5 pieces + citron pie 1 copy + big cola two cups + big lemon red tea cup", 20.0F));
        foodList.add(new Food(R.mipmap.pic6, "Spicy leg four-piece", "A spicy chicken drumstick + a golden French fries one + wheat spicy chicken wings a pair + a cup of cola", 15.0F));

        foodList.add(new Food(R.mipmap.pic7, "Big Four Potato Chips Set", "Big Mac + 2 spicy chicken wings + 1 medium potato + one cup of Coke", 10.0F));
        foodList.add(new Food(R.mipmap.pic8, "Mai Spicy Chicken Leg Fort Package", "A piece of wheat spicy chicken leg + golden crispy potato + a cup of black tea", 20.0F));
        foodList.add(new Food(R.mipmap.pic9, "Maixiang Fish big set meal", "A piece of fragrant fish + a golden crispy potato + a cup of cola", 15.0F));
        foodList.add(new Food(R.mipmap.pic10, "Double-layer Angus thick cattle Baconburg", "Double-layer Angus thick cattle Baconburg", 10.0F));
        foodList.add(new Food(R.mipmap.pic11, "Double-layer deep sea squid fort", "Double-layer deep sea squid fort", 20.0F));
        foodList.add(new Food(R.mipmap.pic12, "Double cheeseburger", "Double cheeseburger", 15.0F));
        adapter.setFoodList(foodList);
    }

    private void initListener() {
        adapter.setFoodListener(new FoodListAdapter.FoodListener() {

            @Override
            public void clickItem(int position) {
                Intent intent = new Intent(FoodListActivity.this, FoodDetailActivity.class);
                intent.putExtra("food", foodList.get(position));
                intent.putExtra("position", position);
                startActivityForResult(intent, REQUEST_CODE);
            }

            @Override
            public void clickAdd(int position, int count) {
                int idx = order.getFoods().indexOf(foodList.get(position));
                System.out.println("add " + idx + " " + count);
                if (idx != -1) {
                    order.getFoods().get(idx).setCount(count);
                } else {
                    order.getFoods().add(foodList.get(position));
                }
                tvTotalCost.setText(order.cost());
            }

            @Override
            public void clickDec(int position, int count) {
                int idx = order.getFoods().indexOf(foodList.get(position));
                System.out.println("dec " + idx + " " + count);
                if (count > 0) {
                    order.getFoods().get(idx).setCount(count);
                } else {
                    order.getFoods().remove(idx);
                }
                tvTotalCost.setText(order.cost());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == FoodDetailActivity.RESULT_CODE) {
            int count = data.getIntExtra("food.count", 0);
            int position = data.getIntExtra("position", 0);
            foodList.get(position).setCount(count);
            adapter.notifyItemChanged(position);
            int idx = order.getFoods().indexOf(foodList.get(position));
            // if before jump to FoodDetailActivity, the food has been ordered
            if (idx !=-1) {
                if (count > 0) {
                    order.getFoods().get(idx).setCount(count);
                } else {
                    order.getFoods().remove(idx);
                }
            } else {
                // the food is ordered in FoodDetailActivity
                if (count > 0) {
                    order.getFoods().add(foodList.get(position));
                }
            }
            tvTotalCost.setText(order.cost());
        }
    }

    public void clickBtnViewOrder(View view) {
        if (order.getFoods().size() == 0) {
            Toast.makeText(this, "You have ordered nothing!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra("order", order);
        startActivity(intent);
    }
}
