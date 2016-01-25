package com.ronaldong.messi.presenter;

import android.app.Activity;

import com.ronaldong.messi.R;
import com.ronaldong.messi.app.Constants;
import com.ronaldong.messi.data.api.Api;
import com.ronaldong.messi.data.api.RequestCallBack;
import com.ronaldong.messi.data.cache.ACache;
import com.ronaldong.messi.data.entity.remote.PagingResult;
import com.ronaldong.messi.data.entity.remote.SimpleGroupTagResult;
import com.ronaldong.messi.ui.fragment.ContactFagment;
import com.ronaldong.messi.ui.view.IContactView;

import java.util.List;

/**
 * Created by ronaldong on 2016/1/22.
 */
public class ContactPresenter {

    private IContactView iContactView;
    private ACache cache;

    public ContactPresenter(Activity activity, ContactFagment iContactView) {
        this.iContactView = iContactView;
        this.cache = ACache.get(activity);
    }

    public void loadSimpleGroupTags() {
        String accessToken = cache.getAsString(Constants.ACCESSTOKEN);
        Api.getSimpleGroupTags(accessToken, new RequestCallBack<PagingResult>() {
            @Override
            public void onSuccess(PagingResult pagingResult) {
                List<SimpleGroupTagResult> simpleGroupTagResultList = (List<SimpleGroupTagResult>) pagingResult.array;

                SimpleGroupTagResult mySimpleGroup1 = new SimpleGroupTagResult();
                mySimpleGroup1.setId(-104);
                mySimpleGroup1.setIconUrl(R.mipmap.avatar_default + "");
                mySimpleGroup1.setName("我的好友");
                simpleGroupTagResultList.add(0,mySimpleGroup1);

                SimpleGroupTagResult mySimpleGroup2 = new SimpleGroupTagResult();
                mySimpleGroup2.setId(-103);
                mySimpleGroup2.setIconUrl(R.mipmap.avatar_default + "");
                mySimpleGroup2.setName("教师黄页");
                simpleGroupTagResultList.add(1,mySimpleGroup2);

                SimpleGroupTagResult mySimpleGroup3 = new SimpleGroupTagResult();
                mySimpleGroup3.setId(-102);
                mySimpleGroup3.setIconUrl(R.mipmap.avatar_default + "");
                mySimpleGroup3.setName("学生黄页");

                simpleGroupTagResultList.add(2,mySimpleGroup3);

                SimpleGroupTagResult mySimpleGroup = new SimpleGroupTagResult();
                mySimpleGroup.setId(-101);
                mySimpleGroup.setIconUrl(R.mipmap.avatar_default + "");
                mySimpleGroup.setName("我的群组");

                SimpleGroupTagResult st = new SimpleGroupTagResult();
                st.setId(-100);
                st.setIconUrl(R.mipmap.avatar_default + "");
                st.setName("黑名单");

                simpleGroupTagResultList.add(3, mySimpleGroup);
                simpleGroupTagResultList.add(simpleGroupTagResultList.size(), st);

                iContactView.showSimpleGroupTags(simpleGroupTagResultList);
            }

            @Override
            public void onFailure(String errorMsg) {

            }
        });


    }
}
