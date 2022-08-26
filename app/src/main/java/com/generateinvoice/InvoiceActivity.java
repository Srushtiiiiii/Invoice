package com.generateinvoice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;

public class InvoiceActivity extends AppCompatActivity
{
    String discount, received, data;
    String radio;
    Float total;
    AppCompatTextView tvName, tvDescription, tvDiscount, tvTotal, tvReceived, tvReturned;
    SharedPreferences sp;
    ItemModel itemModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        Intent i = getIntent();
        radio = i.getStringExtra("radio");
        discount = i.getStringExtra("discount");
        received = i.getStringExtra("received");

        sp = getSharedPreferences("itemData", 0);
        data= sp.getString("data","");
        itemModel = new Gson().fromJson(data,ItemModel.class);
        String price = itemModel.getItemPrice();

        tvName = findViewById(R.id.tvInvoiceName);
        tvDescription = findViewById(R.id.tvInvoiceDesc);
        tvDiscount = findViewById(R.id.tvDiscount);
        tvTotal = findViewById(R.id.tvTotal);
        tvReceived = findViewById(R.id.tvReceived);
        tvReturned = findViewById(R.id.tvReturned);

        tvName.setText(itemModel.getItemName());
        tvDescription.setText(itemModel.getItemDescription());


        if(radio.equals("Flat"))
        {
            tvDiscount.setText("₹" + discount);
            total = Float.parseFloat(price) - Float.parseFloat(discount);
            tvTotal.setText(total.toString());
        }
        else if(radio.equals("Percent"))
        {
            tvDiscount.setText(discount + "%");
            total= Float.parseFloat(price ) - (Float.parseFloat(discount) / 100) * Float.parseFloat(price);
            tvTotal.setText(total.toString());
        }

        tvReceived.setText(received);

        if(Float.parseFloat(received) > total)
        {
            float r = Float.parseFloat(received) - total;
            String mytext=Float.toString(r);
            tvReturned.setText(mytext);
        }
        else if(Float.parseFloat(received) < total)
        {
            tvReturned.setText("0");
        }
        else if(Float.parseFloat(received) == total)
        {
            tvReturned.setText("0");
        }
    }
}