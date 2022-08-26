package com.generateinvoice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    private ArrayList<ItemModel> data;
    private static CartAdapter.OnItemListener clickListener;

//    public CartAdapter(){}
    public CartAdapter(ArrayList<ItemModel> cartModel,Context context) {
        this.data = cartModel;
        this.context=context;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.list_cart, parent, false);
        CartAdapter.ViewHolder viewHolder = new CartAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        final ItemModel model = data.get(position);
        holder.tvName.setText(model.getItemName());
        holder.tvDescription.setText(model.getItemDescription());
        holder.tvPrice.setText(model.getItemPrice());

        List<Integer> list = new ArrayList<>();
        for (int i=0; i<=10; i++){
            list.add(i);
        }
        holder.spinner.setAdapter(new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, list));
//        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String qty = holder.spinner.getSelectedItem().toString();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView tvName, tvDescription, tvPrice;
        public RelativeLayout relativeLayout;
        public AppCompatSpinner spinner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = (AppCompatTextView) itemView.findViewById(R.id.tv_name);
            tvDescription = (AppCompatTextView) itemView.findViewById(R.id.tv_desc);
            tvPrice = (AppCompatTextView) itemView.findViewById(R.id.tv_price);
            spinner = (AppCompatSpinner) itemView.findViewById(R.id.sp);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relative);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String qty = spinner.getSelectedItem().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

    public interface OnItemListener {
        void onClick(View view, int position);
    }

    public void setOnItemListenerListener(CartAdapter.OnItemListener listener) {
        this.clickListener = listener;
    }
}