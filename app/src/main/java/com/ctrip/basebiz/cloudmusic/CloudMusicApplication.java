package com.ctrip.basebiz.cloudmusic;

import android.common.lib.core.BaseApplication;
import android.common.lib.wrap.logger.L;

/**
 * Created by wbsun on 2018/3/8.
 */

public class CloudMusicApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        // 日志
        L.setTag("CloudMusic");
        L.setEnableDebug(true);

        // httpclient
        setupHttpClient(10000L, 10000L);

        // ImageLoader
        setupImageLoader(false);

        // crashReport 初始化
        //setupCrashReport("2fdbcd814f", false, appVersion, appChannelName, packageName, false);
    }
}
