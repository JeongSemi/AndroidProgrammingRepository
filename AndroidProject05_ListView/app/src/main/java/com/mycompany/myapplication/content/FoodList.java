package com.mycompany.myapplication.content;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mycompany.myapplication.R;
import com.mycompany.myapplication.dto.Food;
import com.mycompany.myapplication.dto.Review;

import java.util.ArrayList;
import java.util.List;

public class FoodList extends LinearLayout {
    private static final String TAG = ReviewList.class.getSimpleName();
    private ListView listView;
    private List<Food> list = new ArrayList<>();
    private ItemAdapter itemAdapter;

    public FoodList(Context context) {
        super(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        listView = (ListView) inflater.inflate(R.layout.food_list, null);
        itemAdapter = new ItemAdapter();
        listView.setAdapter(itemAdapter);
        listView.setOnItemClickListener(itemClickListener);
        addView(listView);
    }

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Food food = (Food) itemAdapter.getItem(position);
            Log.i(TAG, food.getFname());
            Log.i(TAG, food.getFdesc());
        }
    };

    class ItemAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return list.get(position).getFno();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.food_item, null);
            }

            ImageView fphoto = (ImageView) convertView.findViewById(R.id.fphoto);
            TextView fname = (TextView) convertView.findViewById(R.id.fname);
            ImageView fstar = (ImageView) convertView.findViewById(R.id.fstar);
            TextView fdesc = (TextView) convertView.findViewById(R.id.fdesc);

            Food food = list.get(position);
            fphoto.setImageResource(food.getFphoto());
            fname.setText(food.getFname());
            fstar.setImageResource(food.getFstar());
            fdesc.setText(food.getFdesc());

            return convertView;
        }
    }

    public void addItem(Food item) {
        list.add(item);
        itemAdapter.notifyDataSetChanged();
    }

    public void removeItem(Food item) {
        list.remove(item);
        itemAdapter.notifyDataSetChanged();
    }
}
