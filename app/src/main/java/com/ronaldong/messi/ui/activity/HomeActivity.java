package com.ronaldong.messi.ui.activity;

import android.app.Fragment;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.igexin.sdk.PushManager;
import com.ronaldong.messi.Message;
import com.ronaldong.messi.R;
import com.ronaldong.messi.broadcast.MessageReceiver;
import com.ronaldong.messi.presenter.HomePresenter;
import com.ronaldong.messi.ui.Base.BaseFragmentActivity;
import com.ronaldong.messi.ui.fragment.ContactFagment;
import com.ronaldong.messi.ui.fragment.ConversationFragment;
import com.ronaldong.messi.ui.fragment.LearnFragment;
import com.ronaldong.messi.ui.fragment.ProfileFragment;
import com.ronaldong.messi.ui.view.IHomeView;


/**
 * Created by ronaldong on 2015/12/23.
 */
public class HomeActivity extends BaseFragmentActivity implements IHomeView,MessageReceiver.OnMessageReceivedListener {


    private String[] tabTitles;
    private int[] imageResId;
    private int[] selectedImageResId;

    private static final int STATE_SELECTED = 0;
    private static final int STATE_UNSELECTED = 1;

    private ConversationFragment conversationFragment;
    private LearnFragment learnFragment;
    private ContactFagment contactFagment;
    private ProfileFragment profileFragment;
    private TabLayout tabLayout;

    private HomePresenter homePresenter;
    private LocalBroadcastManager lbm;
    private MessageReceiver messageReceiver;


    @Override
    protected void initVariables() {

        PushManager.getInstance().initialize(this.getApplicationContext());
        PushManager.getInstance().stopService(this.getApplicationContext());

        lbm = LocalBroadcastManager.getInstance(this);
        messageReceiver=new MessageReceiver();
        messageReceiver.setOnActionResponse(this);
        homePresenter = new HomePresenter(getApplicationContext(), this);
        homePresenter.loginXmppServer();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);

        conversationFragment = new ConversationFragment();
        learnFragment = new LearnFragment();
        contactFagment=new ContactFagment();
        profileFragment = new ProfileFragment();

        tabLayout = (TabLayout) findViewById(R.id.home_tl_tabs);

        initTabView(tabLayout);

    }

    private void initTabView(TabLayout tabLayout) {
        Resources res = this.getResources();
        tabTitles = res.getStringArray(R.array.tabTitle);
        imageResId = new int[tabTitles.length];
        selectedImageResId = new int[tabTitles.length];
        TypedArray tabIcons = res.obtainTypedArray(R.array.tabIcon);
        TypedArray tabIcons_select = res.obtainTypedArray(R.array.tabIcon_select);
        for (int i = 0; i < tabTitles.length; i++) {
            imageResId[i] = tabIcons.getResourceId(i, 0);
            selectedImageResId[i] = tabIcons_select.getResourceId(i, 0);
            if (i == 0) {
                tabLayout.addTab(tabLayout.newTab().setCustomView(getTabView(i)), true);
            } else {
                tabLayout.addTab(tabLayout.newTab().setCustomView(getTabView(i)));
            }
        }
        homePresenter.SetViewByState(tabLayout.getTabAt(0), STATE_SELECTED);
        tabIcons.recycle();
        tabIcons_select.recycle();

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                homePresenter.SetViewByState(tab, STATE_SELECTED);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                homePresenter.SetViewByState(tab, STATE_UNSELECTED);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }


    @Override
    protected void loadData() {
        homePresenter.getUnReadMessageCount();
    }

    @Override
    protected void changeFragment(Fragment targetFragment) {
        changeFragment(R.id.home_fl_content, targetFragment);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter_dynamic = new IntentFilter();
        filter_dynamic.addAction("android.intent.action.MESSAGE_RECEIVED");
        lbm.registerReceiver(messageReceiver, filter_dynamic);
        homePresenter.getUnReadMessageCount();
//        NotificationManager notiManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        notiManager.cancelAll();
//        String accessToken = ACache.get(this).getAsString("accessToken");
//        Call<ResultVO> observable = ApiService.Creator.newApiService().registPushToken(accessToken, "");
//        observable.enqueue(new Callback<ResultVO>() {
//            @Override
//            public void onResponse(Response<ResultVO> response, Retrofit retrofit) {
//                if (response.isSuccess() && response.body() != null) {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//
//            }
//        });
//
//        String clientId = PushManager.getInstance().getClientid(getApplicationContext());
//        if (clientId != null) {
//            ACache.get(this).put(Constants.GETUI_CLIENTID, clientId);
//        }
//        PushManager.getInstance().turnOffPush(getApplicationContext());
    }

    @Override
    public void setTabViewByState(TabLayout.Tab tab, int state) {
        View view = tab.getCustomView();
        TextView tv = (TextView) view.findViewById(R.id.tab_title);
        ImageView img = (ImageView) view.findViewById(R.id.tab_icon);
        if (STATE_SELECTED == state) {
            tv.setTextColor(getResources().getColor(R.color.material_deep_teal_50));
            img.setImageResource(selectedImageResId[tab.getPosition()]);
            switch (tab.getPosition()) {
                case 0:
                    changeFragment(learnFragment);
                    break;
                case 1:
                    changeFragment(conversationFragment);
                    break;
                case 2:
                    changeFragment(contactFagment);
                    break;
                case 3:
                    break;
                case 4:
                    changeFragment(profileFragment);
                    break;
            }
        } else if (STATE_UNSELECTED == state) {
            tv.setTextColor(getResources().getColor(R.color.white));
            img.setImageResource(imageResId[tab.getPosition()]);
        }
        setTitle(tabTitles[tab.getPosition()]);
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_tab, null);
        TextView tv_title = (TextView) view.findViewById(R.id.tab_title);
        tv_title.setText(tabTitles[position]);
        ImageView iv_icon = (ImageView) view.findViewById(R.id.tab_icon);
        iv_icon.setImageResource(imageResId[position]);
        return view;
    }


    @Override
    public void updateUnreadMessageCount(long count) {
        View view = tabLayout.getTabAt(1).getCustomView();
        TextView tv_unReadMsgCount = (TextView) view.findViewById(R.id.tab_unReadmsgCount);
        if (count > 0) {
            tv_unReadMsgCount.setVisibility(View.VISIBLE);
            tv_unReadMsgCount.setText(count + "");
        } else {
            tv_unReadMsgCount.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        lbm.unregisterReceiver(messageReceiver);
    }



    @Override
    public void onReceive(Message message) {
        if (conversationFragment.isVisible()) {
            conversationFragment.getConversationPresenter().loadConversations();
        }
        homePresenter.getUnReadMessageCount();
    }
}
