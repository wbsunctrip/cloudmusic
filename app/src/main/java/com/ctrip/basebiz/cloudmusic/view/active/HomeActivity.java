package com.ctrip.basebiz.cloudmusic.view.active;

import android.Manifest;
import android.app.Activity;
import android.common.lib.core.BaseActivity;
import android.common.lib.core.BaseApplication;
import android.common.lib.core.BaseFragment;
import android.common.lib.utils.ROMUtils;
import android.common.lib.utils.SharedPreferencesUtils;
import android.common.lib.wrap.logger.L;
import android.common.lib.wrap.permission1.AndPermission;
import android.common.lib.wrap.permission1.Rationale;
import android.common.lib.wrap.permission1.RationaleListener;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ctrip.basebiz.cloudmusic.R;
import com.ctrip.basebiz.cloudmusic.view.fragment.ConversationFragment;

import java.util.List;

/**
 * 主页面
 */

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private static final String ACTION_EXIT_APP_RECEIVER = "action_exit_app_receiver";
    public static final String KEY_HOME_PAGE_INDEX = "home_page_index";
    private final int PERMISSION_REQUEST_CODE = 105;

    private int currentTabIndex = 0;
    private BaseFragment fragments[];
    private ImageView imagebuttons[];
    private TextView textviews[];
    private ConversationFragment conversationFragment;

    public static void start(Context context, int pageIndex) {
        Intent starter = new Intent(context, HomeActivity.class);
        starter.putExtra(KEY_HOME_PAGE_INDEX, pageIndex);
        if (!(context instanceof Activity)) {
            starter.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(starter);
    }

    public static void exitApp(Context context) {
        Intent intent = new Intent();
        intent.setAction(ACTION_EXIT_APP_RECEIVER);
        intent.setPackage(context.getPackageName());
        context.sendBroadcast(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        initTabView();

        checkAndRequestPermission();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int index = intent.getIntExtra(KEY_HOME_PAGE_INDEX, 0);
        if (index < 0 || index > 3) {
            index = 0;
        }
        changeTab(index);
    }


    private void initTabView() {
        conversationFragment = new ConversationFragment();
//        contactsFragment = new ContactsFragment();
//        phoneFragment = new PhoneFragment();
//        meFragment = new MeFragment();
//        fragments = new BaseFragment[]{conversationFragment, phoneFragment,
//                contactsFragment, meFragment};
        fragments = new BaseFragment[]{conversationFragment};
        imagebuttons = new ImageView[4];
        imagebuttons[0] = (ImageView) findViewById(R.id.iv_conversation);
        imagebuttons[1] = (ImageView) findViewById(R.id.iv_phone);
        imagebuttons[2] = (ImageView) findViewById(R.id.iv_contacts);
        imagebuttons[3] = (ImageView) findViewById(R.id.iv_me);
        imagebuttons[0].setSelected(true);
        textviews = new TextView[4];
        textviews[0] = (TextView) findViewById(R.id.tv_conversation);
        textviews[1] = (TextView) findViewById(R.id.tv_phone);
        textviews[2] = (TextView) findViewById(R.id.tv_contacts);
        textviews[3] = (TextView) findViewById(R.id.tv_me);
        textviews[0].setSelected(true);
        // 添加显示第一个fragment
        clearFragments();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, conversationFragment)
//                .add(R.id.fragment_container, phoneFragment)
//                .add(R.id.fragment_container, contactsFragment)
//                .add(R.id.fragment_container, meFragment).hide(phoneFragment)
//                .hide(contactsFragment).hide(meFragment)
                .show(conversationFragment)
                .commit();
        currentTabIndex = 0;
    }

    public void changeTab(int index) {
        if (fragments[index] == null || fragments[currentTabIndex] == null) {
            L.d("enter changeTab method; fragment is null");
            return;
        }
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager()
                    .beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            fragments[currentTabIndex].onHide();
            if (!fragments[index].isAdded()) {
                trx.remove(fragments[index]);
                trx.add(R.id.fragment_container, fragments[index]);
            }
            trx.show(fragments[index]).commit();
            fragments[index].onShow();
        }
        imagebuttons[currentTabIndex].setSelected(false);
        // 把当前tab设为选中状态
        imagebuttons[index].setSelected(true);
        textviews[currentTabIndex].setSelected(false);
        textviews[index].setSelected(true);
        currentTabIndex = index;
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(exitAppReceiver);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.conversation:
                changeTab(0);
                break;

            case R.id.phone:
                changeTab(1);
                break;

            case R.id.contacts:
                changeTab(2);
                break;

            case R.id.me:
                changeTab(3);
                break;
        }
    }

    private void clearFragments() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null && fragments.size() != 0) {
            for (Fragment fragment : fragments) {
                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().remove(fragment).commitAllowingStateLoss();
                }
            }
        }
    }

    private BroadcastReceiver exitAppReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (ACTION_EXIT_APP_RECEIVER.equals(intent.getAction())) {
                finish();
            }
        }
    };

    public int getCurrentTabIndex() {
        return currentTabIndex;
    }

    private void checkAndRequestPermission() {
        String[] permissions;
        if (ROMUtils.isMIUI()) {
            permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        } else {
            permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE};
        }
        if (!AndPermission.hasPermission(BaseApplication.getContext(), permissions)) {
            AndPermission.with(BaseApplication.getContext()).requestCode(PERMISSION_REQUEST_CODE)
                    .permission(permissions)
                    .rationale(new RationaleListener() {
                        @Override
                        public void showRequestPermissionRationale(int i, Rationale rationale) {
                            AndPermission.rationaleDialog(BaseApplication.getContext(), rationale);
                        }
                    }).start();
        }
    }
}
