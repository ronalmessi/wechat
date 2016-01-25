package com.ronaldong.messi.ui.fragment;


import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.ronaldong.messi.data.entity.remote.SimpleGroupTagResult;
import com.ronaldong.messi.presenter.ContactPresenter;
import com.ronaldong.messi.ui.adapter.ContactAdapter;
import com.ronaldong.messi.ui.view.IContactView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ronaldong on 2015/12/31.
 */
public class ContactFagment extends ListFragment implements IContactView {

    private ContactAdapter adapter;
    private List<SimpleGroupTagResult> simpleGroupTagResultList;
    private ContactPresenter contactPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactPresenter = new ContactPresenter(getActivity(),this);
        simpleGroupTagResultList = new ArrayList<SimpleGroupTagResult>();
        adapter = new ContactAdapter(getActivity(), simpleGroupTagResultList);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

    @Override
    public void onStart() {
        super.onStart();
        setEmptyText("正在加载数据");
    }

    @Override
    public void onResume() {
        super.onResume();
        contactPresenter.loadSimpleGroupTags();
    }


    @Override
    public void showSimpleGroupTags(List<SimpleGroupTagResult> simpleGroupTagResultList) {
        adapter.replaceAll(simpleGroupTagResultList);
    }

}
