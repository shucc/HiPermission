package me.weyye.hipermission;

import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/5/10 0010.
 */
public class PermissionAdapter extends BaseAdapter {

    private List<PermissionItem> mData;

    private int mTextColor;

    private int mFilterColor;

    private ColorMatrixColorFilter filter;

    public PermissionAdapter(List<PermissionItem> data) {
        mData = data;
        changeColorFilter();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PermissionHolder holder;
        if (null == convertView) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.permission_info_item, parent, false);
            holder = new PermissionHolder();
            holder.imgIcon = convertView.findViewById(R.id.img_permission);
            holder.textName = convertView.findViewById(R.id.text_permission_name);
            convertView.setTag(holder);
        } else {
            holder = (PermissionHolder) convertView.getTag();
        }
        holder.imgIcon.setColorFilter(filter);
        if (mTextColor != 0) {
            holder.textName.setTextColor(mTextColor);
        }
        PermissionItem item = mData.get(position);
        holder.imgIcon.setImageResource(item.PermissionIconRes);
        holder.textName.setText(item.PermissionName);
        return convertView;
    }

    public void setTextColor(int itemTextColor) {
        mTextColor = itemTextColor;
        notifyDataSetChanged();
    }

    public void setFilterColor(int filterColor) {
        mFilterColor = filterColor;
        changeColorFilter();
        notifyDataSetChanged();
    }

    private void changeColorFilter() {
        int blue = Color.blue(mFilterColor);
        int green = Color.green(mFilterColor);
        int red = Color.red(mFilterColor);
        float[] cm = new float[]{
                1, 0, 0, 0, red,// 红色值
                0, 1, 0, 0, green,// 绿色值
                0, 0, 1, 0, blue,// 蓝色值
                0, 0, 0, 1, 1 // 透明度
        };
        filter = new ColorMatrixColorFilter(cm);
    }

    private class PermissionHolder {
        ImageView imgIcon;
        TextView textName;
    }
}
