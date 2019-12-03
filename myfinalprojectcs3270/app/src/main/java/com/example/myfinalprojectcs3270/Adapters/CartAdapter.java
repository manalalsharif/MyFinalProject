package com.example.myfinalprojectcs3270.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfinalprojectcs3270.Object.MyCartItem;
import com.example.myfinalprojectcs3270.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<MyCartItem> cartItemList;

    private RecyclerClickListener recyclerClickListener;

    public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView moviePoster, cartDecrement, cartIncrement, cartDelete;
        TextView movieTitle, moviePrice, movieQuantity;

        public CartViewHolder(View view) {
            super(view);
            moviePoster = view.findViewById(R.id.list_image_cart);
            movieTitle = view.findViewById(R.id.product_cart_name);
            moviePrice = view.findViewById(R.id.product_cart_price);
            cartDecrement = view.findViewById(R.id.cart_decrement);
            movieQuantity = view.findViewById(R.id.cart_product_quantity_tv);
            cartIncrement = view.findViewById(R.id.cart_increment);
            cartDelete = view.findViewById(R.id.delete_item_from_cart);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            recyclerClickListener.onItemClicked(clickedPosition);
        }
    }

    public CartAdapter(Context context, List<MyCartItem> cartItemList, CartAdapter.RecyclerClickListener recyclerClickListener) {
        this.context = context;
        this.cartItemList = cartItemList;
        this.recyclerClickListener = recyclerClickListener;
    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_row, null);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public interface RecyclerClickListener {
        void onItemClicked(int clickedItemIndex);
    }
}
