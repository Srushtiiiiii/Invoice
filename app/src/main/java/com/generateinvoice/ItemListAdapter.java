package com.generateinvoice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder>
{
    private ArrayList<ItemModel> data;
    private static ItemListAdapter.OnItemListener clickListener;

    public ItemListAdapter(ArrayList<ItemModel> itemModel)
    {
        this.data=itemModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ItemModel model = data.get(position);
        holder.tvName.setText(model.getItemName());
        holder.tvDescription.setText(model.getItemDescription());
        holder.tvPrice.setText( model.getItemPrice());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public AppCompatTextView tvName, tvDescription, tvPrice;
        public RelativeLayout relativeLayout;
        public AppCompatButton ivAdd;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tvName = (AppCompatTextView) itemView.findViewById(R.id.tv_name);
            tvDescription = (AppCompatTextView) itemView.findViewById(R.id.tv_desc);
            tvPrice = (AppCompatTextView) itemView.findViewById(R.id.tv_price);
            ivAdd = (AppCompatButton) itemView.findViewById(R.id.btnAdd);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relative);

            ivAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(clickListener!=null)
                    {
                        clickListener.onClick(view,getAdapterPosition());
                    }
                }
            });
        }
    }

    public interface OnItemListener
    {
//        void onClick(ItemModel model);
        void onClick(View view, int position);
    }

    public void setOnItemListenerListener(ItemListAdapter.OnItemListener listener){
        this.clickListener = listener;
    }
}
