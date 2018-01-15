package com.pravesh.myapplication;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.readystatesoftware.systembartint.SystemBarTintManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class ElectronicsFragment extends Fragment {


    ListView lst;
    String []product_name={"Product-1","Product-1","Product-1"};
    String []product_price={"100","200","300"};
    int[]imgid={R.drawable.bazaar_harpers,R.drawable.ic_account_circle_black_48dp,R.drawable.ic_favorite_black_18dp};

    public ElectronicsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_electronics, container, false);


// create our manager instance after the content view is set
        SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
// enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
// enable navigation bar tint
        tintManager.setNavigationBarTintEnabled(true);
// set the transparent color of the status bar, 20% darker
        tintManager.setTintColor(Color.parseColor("#20000000"));

        lst = v.findViewById(R.id.listview2);
        ElectronicsFragment.CustomAdapter customAdapter = new ElectronicsFragment.CustomAdapter();
        lst.setAdapter(customAdapter);
        return v;
    }


    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return imgid.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @SuppressLint("ViewHolder")
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.product,null);
            ImageView imageView = view.findViewById(R.id.imageView);
            TextView textView_name = view.findViewById(R.id.textView_name);
            TextView textView_price = view.findViewById(R.id.textView_price);

            imageView.setImageResource(imgid[i]);
            textView_name.setText(product_name[i]);
            textView_price.setText(product_price[i]);
            return view;
        }
    }
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
