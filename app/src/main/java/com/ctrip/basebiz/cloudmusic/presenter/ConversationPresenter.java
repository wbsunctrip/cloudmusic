package com.ctrip.basebiz.cloudmusic.presenter;

import android.common.lib.core.BaseApplication;
import android.common.lib.utils.NetworkUtils;
import android.common.lib.utils.StringUtils;
import android.common.lib.utils.ThreadUtils;
import android.common.lib.utils.ToastUtils;
import android.common.lib.wrap.logger.L;
import android.support.v7.util.DiffUtil;
import android.text.TextUtils;
import android.util.Log;

import com.ctrip.basebiz.cloudmusic.contract.ConversationContact;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by zhoujian on 2017/5/27.
 */

public class ConversationPresenter extends BasePresenter<ConversationContact.IConversationView>{

    public ConversationPresenter(ConversationContact.IConversationView view) {
        super(view);
    }
}
