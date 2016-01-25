package com.ronaldong.messi.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ronaldong.messi.R;
import com.ronaldong.messi.data.entity.remote.SimpleGroupTagResult;
import com.ronaldong.messi.ui.Base.BaseAdapter;

import java.util.List;

/**
 * Created by ronaldong on 2016/1/22.
 */
public class ContactAdapter extends BaseAdapter<SimpleGroupTagResult> {

    public ContactAdapter(Context context, List<SimpleGroupTagResult> data) {
        super(context, data);
    }

    @Override
    public int getItemResource() {
        return R.layout.item_contacts_group;
    }

    @Override
    public View getItemView(int position, View convertView, ViewHolder holder) {
        SimpleDraweeView avatar = holder.getView(R.id.img_contact_avatar);
        TextView name = holder.getView(R.id.tv_contact_name);

        SimpleGroupTagResult simpleGroupTagResult = (SimpleGroupTagResult) getItem(position);

//      联系人头像
        Uri uri = Uri.parse(simpleGroupTagResult.getIconUrl());
        avatar.setImageURI(uri);
//      联系人名称
        name.setText(simpleGroupTagResult.getName());
        return convertView;
    }
}
