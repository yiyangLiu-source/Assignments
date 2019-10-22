package com.assignment.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.assignment.order.adapter.FoodListAdapter;
import com.assignment.order.entity.Order;

public class OrderActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FoodListAdapter adapter;

    private TextView tvTotalCost;

    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();
        initData();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FoodListAdapter(FoodListAdapter.FoodVHolderType.ORDER, this);
        recyclerView.setAdapter(adapter);

        tvTotalCost = findViewById(R.id.tv_total_cost);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Order");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        order = (Order) getIntent().getSerializableExtra("order");
        adapter.setFoodList(order.getFoods());

        tvTotalCost.setText(order.cost());
    }
}
