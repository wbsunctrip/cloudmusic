package com.ctrip.basebiz.cloudmusic.contract;

import android.support.v7.util.DiffUtil;

import com.ctrip.basebiz.cloudmusic.viewmodel.ConversationViewModel;

import java.util.List;


/**
 * Created by zhoujian on 2017/5/25.
 */

public interface ConversationContact {

    interface IConversationView extends IView {
        void showConversations(List<ConversationViewModel> conversations);

        void refreshConversations(DiffUtil.DiffResult diffResult, List<ConversationViewModel> conversations, boolean isNeedReplaceData);

        void hideTipsView();

        void showTipsView();

        void updateTitle(String title, boolean isLoading);

        List<ConversationViewModel> getConversationInfos();
    }

    interface IConversationPresenter extends IPresenter {

        /**
         * 页面创建时加载会话
         */
        void loadConversationsOnCreate();

        /**
         * 全量加载会话
         */
        void loadConversations(boolean isNeedClearCache);

        /**
         * 删除会话
         */
        void deleteConversation(ConversationViewModel model);

        /**
         * 置顶操作
         */
        void setConversationTop(ConversationViewModel model);

        /**
         * 跳转聊天页面
         */
        void gotoChatPage(ConversationViewModel model);
    }
}
