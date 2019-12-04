package com.example.myfinalprojectcs3270.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfinalprojectcs3270.Object.MyCartItem;
import com.example.myfinalprojectcs3270.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.myfinalprojectcs3270.Fragments.MyCart.grandTotal;
import static com.example.myfinalprojectcs3270.Fragments.MyCart.grandTotalplus;
import static com.example.myfinalprojectcs3270.Fragments.MyCart.temparraylist;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    ArrayList<MyCartItem> cartModelArrayList;
    Context context;

    public CartAdapter(ArrayList<MyCartItem> cartModelArrayList, Context context) {
        this.context = context;
        this.cartModelArrayList = cartModelArrayList;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
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
    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_row, null);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartAdapter.CartViewHolder holder, final int position) {
        //load all movie order details
        holder.movieTitle.setText(cartModelArrayList.get(position).getTitle());
        holder.moviePrice.setText(String.valueOf(cartModelArrayList.get(position).getPrice()));
        holder.movieQuantity.setText(String.valueOf(cartModelArrayList.get(position).getQuantity()));
        //load movie poster
        Picasso.get()
                .load(cartModelArrayList.get(position).getPoster_path())
                .into(holder.moviePoster);


        //delete item from cart
        holder.cartDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cartModelArrayList.size() == 1) {
                    cartModelArrayList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, cartModelArrayList.size());
                    grandTotalplus = 0;
                    grandTotal.setText(String.valueOf(grandTotalplus));
                }

                if (cartModelArrayList.size() > 0) {
                    cartModelArrayList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, cartModelArrayList.size());
                    grandTotalplus = 0.00;
                    for (int i = 0; i < temparraylist.size(); i++) {
                        grandTotalplus = grandTotalplus + temparraylist.get(i).getTotal();
                    }

                    grandTotal.setText("$"+ String.valueOf(grandTotalplus) + "0");

                } else {
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // increment quantity and update quantity and total cash
        holder.cartIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grandTotalplus = 0;
                holder.cartDecrement.setEnabled(true);

                int cartUpdateCounter = (cartModelArrayList.get(position).getQuantity());

                holder.cartIncrement.setEnabled(true);
                cartUpdateCounter += 1;

                cartModelArrayList.get(position).setQuantity((cartUpdateCounter));
                double cash = ((cartModelArrayList.get(position).getPrice()) * (cartModelArrayList.get(position).getQuantity()));

                holder.movieQuantity.setText(String.valueOf(cartModelArrayList.get(position).getQuantity()));

                cartModelArrayList.get(position).setTotal(cash);
                holder.moviePrice.setText(String.valueOf(cash));


                for (int i = 0; i < temparraylist.size(); i++) {
                    grandTotalplus = grandTotalplus + temparraylist.get(i).getTotal();
                }
                grandTotal.setText(String.valueOf(grandTotalplus));

            }

        });

        // decrement quantity and update quantity and total cash
        holder.cartDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //total_cash=0;
                grandTotalplus = 0;
                holder.cartIncrement.setEnabled(true);

                int cartUpdateCounter = (cartModelArrayList.get(position).getQuantity());

                if (cartUpdateCounter == 1) {
                    holder.cartDecrement.setEnabled(false);
                    Toast.makeText(context, "quantity can't be zero", Toast.LENGTH_SHORT).show();
                } else {
                    holder.cartDecrement.setEnabled(true);
                    cartUpdateCounter -= 1;
                    cartModelArrayList.get(position).setQuantity((cartUpdateCounter));
                    holder.movieQuantity.setText(String.valueOf(cartModelArrayList.get(position).getQuantity()));
                    double cash = ((cartModelArrayList.get(position).getPrice()) * (cartModelArrayList.get(position).getQuantity()));

                    cartModelArrayList.get(position).setTotal(cash);
                    holder.moviePrice.setText(String.valueOf(cash));
                    for (int i = 0; i < temparraylist.size(); i++) {
                        grandTotalplus = grandTotalplus + temparraylist.get(i).getTotal();
                    }
                    grandTotal.setText(String.valueOf(grandTotalplus));

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return cartModelArrayList.size();
    }

    public interface RecyclerClickListener {
        void onItemClicked(int clickedItemIndex);
    }
}
