package com.generateinvoice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity
{
    AppCompatButton btnContinue;
    CartAdapter adapter;
    RecyclerView recyclerView;
    private ArrayList<ItemModel> dataSet;
    SharedPreferences sp ;
    ItemModel itemModel;
    String data;
    String radio;
//    CartModel cartModel;
//    ItemListAdapter adapter;

    AlertDialog dialog;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

//        cartModel = new CartModel();
//        dataSet = new ArrayList<>();
//        dataSet.add(cartModel);
//        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
//        adapter = new CartAdapter(dataSet);
//        recyclerView.setAdapter(adapter);



        sp = getSharedPreferences("itemData", 0);
        data= sp.getString("data","");
        itemModel = new Gson().fromJson(data,ItemModel.class);
        dataSet = new ArrayList<>();
//        if(data == null)
//        {
//            Toast.makeText(getApplicationContext(),"No item added in Cart",Toast.LENGTH_LONG).show();
//        }
//        else

            dataSet.add(itemModel);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new CartAdapter(dataSet,getApplicationContext());
        recyclerView.setAdapter(adapter);

        btnContinue = findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    showDialog(CartActivity.this);
            }
        });

    }

    public void showDialog(Activity activity)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
        ViewGroup viewGroup = findViewById(androidx.appcompat.R.id.content);
        View view = getLayoutInflater().inflate(R.layout.alertdialog, viewGroup, false);
        builder.setView(view);

        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        AppCompatRadioButton rbFlat = view.findViewById(R.id.rbFlat);
        AppCompatRadioButton rbPercent = view.findViewById(R.id.rbPercent);
        AppCompatEditText edDis = view.findViewById(R.id.edDiscount);
        AppCompatEditText edRec = view.findViewById(R.id.edRec);
        AppCompatButton btnNext = view.findViewById(R.id.btnNext);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                AppCompatRadioButton radioButton = (AppCompatRadioButton)radioGroup.findViewById(i);
            }
        });

        if(rbFlat.isChecked())
        {
           radio = "Flat";
        }
        else if(rbPercent.isChecked())
        {
            radio = "Percent";
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId == -1)
                {
                    Toast.makeText(CartActivity.this, "Nothing selected", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Log.e("","");

                }

                Intent intent = new Intent(getApplicationContext(),InvoiceActivity.class);
                intent.putExtra("radio",radio);
//                intent.putExtra("qty",);
                intent.putExtra("discount",edDis.getText().toString());
                intent.putExtra("received",edRec.getText().toString());
                startActivity(intent);
            }
        });

        dialog = builder.create();
        dialog.show();
    }

}