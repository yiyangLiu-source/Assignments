package com.assignment.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignment.order.entity.Food;

public class FoodDetailActivity extends AppCompatActivity {

    public static final int RESULT_CODE = 1001;

    private ImageView ivPicture;
    private TextView tvName;
    private TextView tvDescription;
    private TextView tvCost;
    private TextView tvCount;
    private ImageButton ibAdd;
    private ImageButton ibDec;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        initView();
        initData();
    }
    
    private void initView() {
        ivPicture = findViewById(R.id.iv_picture);
        ivPicture = findViewById(R.id.iv_picture);
        tvName = findViewById(R.id.tv_name);
        tvDescription = findViewById(R.id.tv_description);
        tvCost = findViewById(R.id.tv_cost);
        tvCount = findViewById(R.id.food0list_tv_count);
        ibAdd = findViewById(R.id.food0list_ib_add);
        ibDec = findViewById(R.id.food0list_ib_dec);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Food Detail");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        Food food = (Food) getIntent().getSerializableExtra("food");
        ivPicture.setImageResource(food.getPicture());
        tvName.setText(food.getName());
        tvDescription.setText(food.getDescription());
        tvCost.setText(food.getCost() + "");
        tvCount.setText(food.getCount() + "");
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("food.count", Integer.parseInt(tvCount.getText().toString()));
        intent.putExtra("position", getIntent().getIntExtra("position", 0));
        setResult(RESULT_CODE, intent);
        super.onBackPressed();
    }

    public void clickIbAdd(View view) {
        int count = Integer.parseInt(tvCount.getText().toString());
        if (count == 99) {
            return;
        }
        tvCount.setText((count + 1) + "");
    }

    public void clickIbDec(View view) {
        int count = Integer.parseInt(tvCount.getText().toString());
        if (count == 0) {
            return;
        }
        tvCount.setText((count - 1) + "");
    }
}
