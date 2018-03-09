package com.ctrip.basebiz.cloudmusic.view.fragment;

import android.app.Activity;
import android.common.lib.core.BaseApplication;
import android.common.lib.utils.NetworkUtils;
import android.common.lib.utils.ThreadUtils;
import android.common.lib.wrap.logger.L;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ctrip.basebiz.cloudmusic.R;
import com.ctrip.basebiz.cloudmusic.contract.ConversationContact;
import com.ctrip.basebiz.cloudmusic.presenter.ConversationPresenter;
import com.ctrip.basebiz.cloudmusic.viewmodel.ConversationViewModel;

import java.util.List;

/**
 * 会话列表页面
 * <p>
 * Created by zhoujian on 2017/5/23.
 */

public class ConversationFragment extends BasePresenterFragment<ConversationContact.IConversationPresenter>
        implements ConversationContact.IConversationView, View.OnClickListener{

    @Override
    protected ConversationContact.IConversationPresenter createPresenter() {
        return (ConversationContact.IConversationPresenter) new ConversationPresenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conversation, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initToolbar();

    }


    /**
     * 初始化标题栏
     */
    private void initToolbar() {

    }

    @Override
    public void onShow() {
        super.onShow();
        mPresenter.loadConversations(true);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void closeView() {

    }

    public void showConversations(List<ConversationViewModel> conversations) {

    }

    public void refreshConversations(DiffUtil.DiffResult diffResult, List<ConversationViewModel> conversations, boolean isNeedReplaceData) {

    }

    @Override
    public void hideTipsView() {

    }

    @Override
    public void showTipsView() {

    }

    @Override
    public void updateTitle(String title, boolean isLoading) {

    }

    @Override
    public List<ConversationViewModel> getConversationInfos() {
        return null;
    }
}
