package com.mycompany.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mycompany.myapplication.R;
import com.mycompany.myapplication.content.FoodList;
import com.mycompany.myapplication.dto.Food;
import com.mycompany.myapplication.dto.NYCFood;

import java.util.ArrayList;
import java.util.List;

public class NYCFoodListFragment extends Fragment {
    private static final String TAG = NYCFoodListFragment.class.getSimpleName();
    private ListView listView;
    private List<NYCFood> list = new ArrayList<>();
    private ItemAdapter itemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        listView = (ListView) inflater.inflate(R.layout.fragment_nycfood_list, container, false);
        itemAdapter = new ItemAdapter();
        listView.setAdapter(itemAdapter);
        listView.setOnItemClickListener(itemClickListener);
        return listView;
    }

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            NYCFood nycFood = (NYCFood) itemAdapter.getItem(position);
            Log.i(TAG, nycFood.getFname());
            Log.i(TAG, nycFood.getFdesc());
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        for (int i = 1; i <= 6; i++) {
            NYCFood nycFood = new NYCFood();
            nycFood.setFno(i);
            nycFood.setFname("음식" + i);
            nycFood.setFphoto(getResources().getIdentifier("food" + i, "drawable", getContext().getPackageName()));
            nycFood.setFstar(getResources().getIdentifier("star" + i, "drawable", getContext().getPackageName()));
            nycFood.setFdesc("뉴욕가서 먹은 " + i + "번째 음식입니다. 진짜 맛있었는데.. 이번 여름은 뉴욕이고 나발이고 프로젝트 여행이나 떠나야겠어용 :)");
            addItem(nycFood);
        }
    }

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
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.food_item, null);
            }

            ImageView fphoto = (ImageView) convertView.findViewById(R.id.fphoto);
            TextView fname = (TextView) convertView.findViewById(R.id.fname);
            ImageView fstar = (ImageView) convertView.findViewById(R.id.fstar);
            TextView fdesc = (TextView) convertView.findViewById(R.id.fdesc);

            NYCFood nycFood = list.get(position);
            fphoto.setImageResource(nycFood.getFphoto());
            fname.setText(nycFood.getFname());
            fstar.setImageResource(nycFood.getFstar());
            fdesc.setText(nycFood.getFdesc());
            Log.i(TAG,"실행");

            return convertView;
        }
    }

    public void addItem(NYCFood item) {
        list.add(item);
        itemAdapter.notifyDataSetChanged();
    }

    public void removeItem(NYCFood item) {
        list.remove(item);
        itemAdapter.notifyDataSetChanged();
    }
}
