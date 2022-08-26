 package com.generateinvoice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

 public class MainActivity extends AppCompatActivity
{
    MaterialToolbar materialToolbar;
    ItemListAdapter adapter;
    RecyclerView recyclerView;
    private ArrayList<ItemModel> dataSet;
    SharedPreferences sharedPreferences;
    ItemModel model;
    CartModel cartModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        materialToolbar = findViewById(R.id.topAppBar);
        cartModel = new CartModel();

        dataSet = getData();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new ItemListAdapter(dataSet);
        recyclerView.setAdapter(adapter);




        adapter.setOnItemListenerListener(new ItemListAdapter.OnItemListener() {
            @Override
            public void onClick(View view, int position) {

                model = dataSet.get(position);
                Toast.makeText(getApplicationContext(),model.getItemName()+ " added to cart", Toast.LENGTH_SHORT).show();

                sharedPreferences = getSharedPreferences("itemData",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("data", new Gson().toJson(model));
//                editor.putString("data", model.getItemName());
                editor.commit();


                Log.e("model", String.valueOf(model));


                List<CartModel> mylist = new ArrayList<>();
                for(int i=0 ; i <=15 ; i++){
                    mylist.add(new CartModel(model.getItemName(), model.getItemPrice(), model.getItemPrice()));
                    Log.e("added","to cartlist");
                }




            }
        });



        materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
//                intent.putExtra("data", new Gson().toJson(model));
                startActivity(intent);
                return true;
            }
        });

    }

    private ArrayList<ItemModel> getData()
    {
        ArrayList<ItemModel> list = new ArrayList<>();

        list.add(new ItemModel("Item1","20","abcdbfbvkjsfbvkjsbkj"));
        list.add(new ItemModel("Item2","30","abcdbfbvkjsfbvkjsbkj"));
        list.add(new ItemModel("Item3","20","abcdbfbvkjsfbvkjsbkj"));
        list.add(new ItemModel("Item4","30","abcdbfbvkjsfbvkjsbkj"));
        list.add(new ItemModel("Item5","20","abcdbfbvkjsfbvkjsbkj"));
        list.add(new ItemModel("Item6","30","abcdbfbvkjsfbvkjsbkj"));


        return list;
    }

    public static List<Integer> Makearray(int arry) {

        List<Integer> mylist = new ArrayList<Integer>();

        mylist.add(arry);

        return mylist;
    }
}