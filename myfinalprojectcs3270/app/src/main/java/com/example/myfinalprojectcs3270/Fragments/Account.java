package com.example.myfinalprojectcs3270.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.myfinalprojectcs3270.HistoryActivity;
import com.example.myfinalprojectcs3270.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Account extends Fragment {

    View root;
    private Button history;
    public Account() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_account, container, false);

        history = root.findViewById(R.id.history);


        history = (Button) root.findViewById(R.id.history);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HistoryActivity.class);
                startActivity(intent);



            }
        });

        return root;
    }


//    @Override
//    public void onClick(View v) {
//        // TODO Auto-generated method stub
//
//        if(v.getId()==R.id.btnBUY)
//        {
//            if(BillingHelper.isBillingSupported()){
//
//                BillingHelper.requestPurchase(mContext, "com.test.buy");
//
//                // android.test.purchased or android.test.canceled or android.test.refunded or com.blundell.item.passport
//            } else {
//                Log.i(TAG,"Can't purchase on this device");
//                Toast.makeText(this, "Can't purchase on this device, Billing not Supprted", Toast.LENGTH_SHORT).show();
//
//                hajj.setEnabled(false); // XXX press button before service started will disable when it shouldnt
//            }
//
//        }
//
//        public Handler mTransactionHandler = new Handler(){
//            public void handleMessage(android.os.Message msg) {
//                Log.i(TAG, "Transaction complete");
//                Log.i(TAG, "Transaction status: "+BillingHelper.latestPurchase.purchaseState);
//                Log.i(TAG, "Item purchased is: "+BillingHelper.latestPurchase.productId);
//
//                if(BillingHelper.latestPurchase.isPurchased()){
//                    showItem();
//                }
//            };


}
