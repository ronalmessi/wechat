package com.ronaldong.messi.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ronaldong.messi.Conversation;
import com.ronaldong.messi.Message;
import com.ronaldong.messi.MessageDao;
import com.ronaldong.messi.R;
import com.ronaldong.messi.app.Constants;
import com.ronaldong.messi.data.db.DBManager;
import com.ronaldong.messi.ui.Base.BaseAdapter;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by ronaldong on 2016/1/6.
 */
public class ConversationAdapter extends BaseAdapter<Conversation> {

    private Context context;

    public ConversationAdapter(Context context, List<Conversation> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    public int getItemResource() {
        return R.layout.item_conversation;
    }

    @Override
    public View getItemView(int position, View convertView, ViewHolder holder) {

        SimpleDraweeView avatar = holder.getView(R.id.imageView_msgFrag_avatar);
        TextView name = holder.getView(R.id.textView_msgFrag_name);
        TextView latestMsg = holder
                .getView(R.id.textView_msgFrag_latestMsg);
        TextView sendTime = holder.getView(R.id.textView_msgFrag_sendTime);
        TextView newTip = holder.getView(R.id.textView_msgFrag_newTip);
        ImageView silent = holder.getView(R.id.imageView_msgFrag_silent);

        Conversation conversation = (Conversation) getItem(position);

//       会话头像
        Uri uri = Uri.parse(conversation.getAvatar());
        avatar.setImageURI(uri);
//       会话名称
        name.setText(conversation.getTitle());

        MessageDao messageDao = DBManager.getInstance().getDaoSession(context).getMessageDao();
        QueryBuilder qb = messageDao.queryBuilder();
        qb.orderAsc(MessageDao.Properties.SendTime);
        qb.where(MessageDao.Properties.ConversationId.eq(conversation.getId()));

        List<Message> messages=qb.list();
        Message latestMessage =messages.get(messages.size() - 1);

//      会话的最新一条消息内容和时间
        sendTime.setText(DateUtils.isToday(latestMessage.getSendTime().getTime()) ? DateFormat.getTimeFormat(context).format(latestMessage.getSendTime()) :
                DateFormat.getDateFormat(context).format(latestMessage.getSendTime()));
        latestMsg.setText(latestMessage.getContent());


        qb.where(MessageDao.Properties.State.eq(Constants.MSG_STATE_RECEIVED_UNREAD));
        //                qb.where(MessageDao.Properties.ConversationId.eq(conversation.getId()), MessageDao.Properties.State.eq(Constants.MSG_STATE_RECEIVED_UNREAD));
//      会话的未读消息数目
        long unReadMessageCount = qb.count();
        if (unReadMessageCount > 0) {
            newTip.setVisibility(View.VISIBLE);
            newTip.setText(unReadMessageCount + "");
        }else{
            newTip.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }
}
