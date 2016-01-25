package com.ronaldong.messi.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ronaldong.messi.Person;
import com.ronaldong.messi.PersonDao;
import com.ronaldong.messi.R;
import com.ronaldong.messi.app.Constants;
import com.ronaldong.messi.data.cache.ACache;
import com.ronaldong.messi.data.db.DBManager;
import com.ronaldong.messi.ui.Base.BaseFragment;
import com.ronaldong.messi.ui.activity.AppsActivity;
import com.ronaldong.messi.ui.activity.SettingActivity;

/**
 * 个人信息页面
 * Created by ronaldong on 2015/12/31.
 */
public class ProfileFragment extends BaseFragment {


    private RelativeLayout rl_profile_myinfo;
    private RelativeLayout rl_profile_netdisk;
    private RelativeLayout rl_profile_app;
    private RelativeLayout rl_profile_setting;


    private TextView tv_profile_name;
    private TextView tv_profile_signature;
    private SimpleDraweeView iv_profile_avatar;
    private ImageView iv_profile_sex;

    private ACache aCache;
    private PersonDao personDao;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    protected void initVariables() {
        aCache = ACache.get(getActivity());
        personDao = DBManager.getInstance().getDaoSession(getActivity()).getPersonDao();
    }

    @Override
    protected void initViews(View v) {
        rl_profile_myinfo = (RelativeLayout) v.findViewById(R.id.re_profile_myinfo);
        rl_profile_netdisk = (RelativeLayout) v.findViewById(R.id.re_profile_netdisk);
        rl_profile_app = (RelativeLayout) v.findViewById(R.id.re_profile_app);
        rl_profile_app.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AppsActivity.class);
                startActivity(intent);
            }
        });
        rl_profile_setting = (RelativeLayout) v.findViewById(R.id.re_profile_setting);
        rl_profile_setting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });

        tv_profile_name = (TextView) v.findViewById(R.id.tv_profile_name);
        tv_profile_signature = (TextView) v.findViewById(R.id.tv_profile_signature);
        iv_profile_avatar = (SimpleDraweeView) v.findViewById(R.id.iv_profile_avatar);
        iv_profile_sex = (ImageView) v.findViewById(R.id.iv_profile_sex);

    }

    @Override
    protected void loadData() {
        String userId = aCache.getAsString(Constants.USER_ID);

        Person person = personDao.load(Long.valueOf(userId));

        //       会话头像
        Uri uri = Uri.parse(person.getAvatar());
        iv_profile_avatar.setImageURI(uri);

        tv_profile_name.setText(person.getName());
        tv_profile_signature.setText(person.getName());

        if (person.getGender().equals("0")) {
            iv_profile_sex.setImageResource(R.mipmap.ic_sex_male);
        } else {
            iv_profile_sex.setImageResource(R.mipmap.ic_sex_female);
        }

    }
}
