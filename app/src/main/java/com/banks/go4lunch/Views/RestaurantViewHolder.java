package com.banks.go4lunch.Views;

import androidx.recyclerview.widget.RecyclerView;

import com.banks.go4lunch.databinding.FragmentListItemBinding;

public class RestaurantViewHolder extends RecyclerView.ViewHolder {

    FragmentListItemBinding binding;


    public RestaurantViewHolder(FragmentListItemBinding itemView) {
        super(itemView.getRoot());
        this.binding = itemView;
    }
}
