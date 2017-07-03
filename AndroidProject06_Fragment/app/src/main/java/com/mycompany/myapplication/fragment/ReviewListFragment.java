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
import com.mycompany.myapplication.dto.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewListFragment extends Fragment {
    private static final String TAG = ReviewListFragment.class.getSimpleName();
    private ListView listView;
    private List<Review> list = new ArrayList<>();
    private ItemAdapter itemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        listView = (ListView) inflater.inflate(R.layout.fragment_review_list, null);
        itemAdapter = new ItemAdapter();
        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(itemClickListener);
        return listView;
    }

    @Override
    public void onStart() {
        super.onStart();
        for (int i = 1; i <= 10; i++) {
            Review review = new Review();
            review.setPhoto(getResources().getIdentifier("member" + i, "drawable", getContext().getPackageName()));
            review.setTitle("ListView와 Adapter");
            review.setStar(getResources().getIdentifier("star" + i, "drawable", getContext().getPackageName()));
            review.setContent("Adapter는 item XML를 Inflation해서 데이터를 세팅한 다음 ListView에 제공하는 역할을 합니다.");
            addItem(review);
        }
    }

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Review review = (Review) itemAdapter.getItem(position);
            Log.i(TAG, review.getTitle());
            Log.i(TAG, review.getContent());
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
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                //Item UI 객체 생성(Inflation)
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.review_item, null);
            }
            //데이터 세팅
            ImageView photo = (ImageView) convertView.findViewById(R.id.photo);
            TextView title = (TextView) convertView.findViewById(R.id.title);
            ImageView star = (ImageView) convertView.findViewById(R.id.star);
            TextView content = (TextView) convertView.findViewById(R.id.content);
            Review item = list.get(position);
            photo.setImageResource(item.getPhoto());
            title.setText(item.getTitle());
            star.setImageResource(item.getStar());
            content.setText(item.getContent());
            return convertView;
        }
    }

    public void addItem(Review item) {
        list.add(item);
        itemAdapter.notifyDataSetChanged();
    }

    public void removeItem(Review item) {
        list.remove(item);
        itemAdapter.notifyDataSetChanged();
    }
}
