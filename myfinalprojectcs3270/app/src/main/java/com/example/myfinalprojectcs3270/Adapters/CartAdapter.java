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

import java.util.List;

import static com.example.myfinalprojectcs3270.Fragments.MyCart.grandTotal;
import static com.example.myfinalprojectcs3270.Fragments.MyCart.grandTotalplus;
import static com.example.myfinalprojectcs3270.Fragments.MyCart.temparraylist;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<MyCartItem> cartItemList;

    private RecyclerClickListener recyclerClickListener;

    public CartAdapter(Context context, List<MyCartItem> cartItemList, CartAdapter.RecyclerClickListener recyclerClickListener) {
        this.context = context;
        this.cartItemList = cartItemList;
        this.recyclerClickListener = recyclerClickListener;
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
        holder.movieTitle.setText(cartItemList.get(position).getTitle());
        holder.moviePrice.setText(String.valueOf(cartItemList.get(position).getPrice()));
        holder.movieQuantity.setText(String.valueOf(cartItemList.get(position).getQuantity()));
        //load movie poster
        Picasso.get()
                .load(cartItemList.get(position).getPoster_path())
                .into(holder.moviePoster);


        //delete item from cart
        holder.cartDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cartItemList.size() == 1) {
                    cartItemList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, cartItemList.size());
                    grandTotalplus = 0;
                    grandTotal.setText(String.valueOf(grandTotalplus));
                }

                if (cartItemList.size() > 0) {
                    cartItemList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, cartItemList.size());
                    grandTotalplus = 0;
                    for (int i = 0; i < temparraylist.size(); i++) {
                        grandTotalplus = grandTotalplus + temparraylist.get(i).getTotal();
                    }

                    grandTotal.setText(String.valueOf(grandTotalplus));

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

                int cartUpdateCounter = (cartItemList.get(position).getQuantity());
                Log.d("counterthegun", String.valueOf(cartItemList.get(position).getQuantity()));

                holder.cartIncrement.setEnabled(true);
                cartUpdateCounter += 1;

                cartItemList.get(position).setQuantity((cartUpdateCounter));
                double cash = ((cartItemList.get(position).getPrice()) * (cartItemList.get(position).getQuantity()));

                holder.movieQuantity.setText(String.valueOf(cartItemList.get(position).getQuantity()));

                cartItemList.get(position).setTotal(cash);
                holder.moviePrice.setText(String.valueOf(cash));


                for (int i = 0; i < temparraylist.size(); i++) {
                    grandTotalplus = grandTotalplus + temparraylist.get(i).getTotal();
                }
                Log.d("totalcashthegun", String.valueOf(grandTotalplus));
                grandTotal.setText(String.valueOf(grandTotalplus));

            }

        });

        // decrement quantity and update quamtity and total cash
        holder.cartDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //total_cash=0;
                grandTotalplus = 0;
                holder.cartIncrement.setEnabled(true);

                int cartUpdateCounter = (cartItemList.get(position).getQuantity());
                Log.d("counterthegun", String.valueOf(cartItemList.get(position).getQuantity()));


                if (cartUpdateCounter == 1) {
                    holder.cartDecrement.setEnabled(false);
                    Toast.makeText(context, "quantity can't be zero", Toast.LENGTH_SHORT).show();
                } else {
                    holder.cartDecrement.setEnabled(true);
                    cartUpdateCounter -= 1;
                    cartItemList.get(position).setQuantity((cartUpdateCounter));
                    holder.movieQuantity.setText(String.valueOf(cartItemList.get(position).getQuantity()));
                    double cash = ((cartItemList.get(position).getPrice()) * (cartItemList.get(position).getQuantity()));

                    cartItemList.get(position).setTotal(cash);
                    holder.movieQuantity.setText(String.valueOf(cash));
                    for (int i = 0; i < temparraylist.size(); i++) {
                        grandTotalplus = grandTotalplus + temparraylist.get(i).getTotal();
                    }

                    Log.d("totalcashthegun", String.valueOf(grandTotalplus));
                    grandTotal.setText(String.valueOf(grandTotalplus));

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public interface RecyclerClickListener {
        void onItemClicked(int clickedItemIndex);
    }
}
