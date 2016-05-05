package com.example.zhangli.insanityworkout.util;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zhangli.insanityworkout.R;
import com.example.zhangli.insanityworkout.db.ItemData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhangli on 16/4/9.
 */

public class ListAdapter extends BaseAdapter {

    private LayoutInflater mInflater = null;
    private List<ItemData> listItem;


    class ViewHolder {
        public TextView day;
        public TextView content;
        public int id;
    }

    public ListAdapter(Context context, List<ItemData> listItem0) {
        super();
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listItem = listItem0;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listItem.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder.day = (TextView) convertView.findViewById(R.id.day);
            holder.content = (TextView) convertView.findViewById(R.id.list_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.day.setText(listItem.get(position).getDay());
        holder.content.setText(listItem.get(position).getContent());
        if (listItem.get(position).getIscomplete().equals("1") ) {
            holder.content.setTextColor(Color.argb(127, 255, 0, 255));
        }
        return convertView;
    }

}

