package com.huawei.health.ui.notification.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.huawei.health.manager.util.Consts;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.StepsRecord;
import health.compact.a.Utils;
import health.compact.a.utils.StringUtils;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public abstract class IReporter {
    private static final int RUNNING = 1001;
    private static final int STOPPED = 1000;
    private static final String TAG = "Step_IReporter";
    private int mStatus = 1000;
    private static BroadcastReceiver sLoginSuccessMgrReceiver = new BroadcastReceiver() { // from class: com.huawei.health.ui.notification.common.IReporter.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h(IReporter.TAG, "mLoginSuccessMgrReceiver intent is null.");
                return;
            }
            String action = intent.getAction();
            if (StringUtils.i(action)) {
                action.hashCode();
                if (action.equals("com.huawei.plugin.account.login")) {
                    LogUtil.a(IReporter.TAG, "ACTION_LOGIN_SUCCESSFUL");
                    boolean unused = IReporter.sIsBrowseMode = false;
                } else if (action.equals("com.huawei.plugin.account.logout")) {
                    LogUtil.a(IReporter.TAG, "ACTION_LOGOUT_SUCCESSFUL");
                    boolean unused2 = IReporter.sIsBrowseMode = true;
                }
            }
        }
    };
    private static boolean sIsBrowseMode = Utils.f();
    private static final AtomicInteger sCount = new AtomicInteger(0);

    protected abstract void languageChanged();

    protected abstract void refresh(StepsRecord stepsRecord);

    public void onStart(Bundle bundle) {
        setStatus(1001);
        if (sCount.getAndIncrement() == 0) {
            registerLoginSuccessBroadcast();
        }
    }

    private static void registerLoginSuccessBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.plugin.account.login");
        intentFilter.addAction("com.huawei.plugin.account.logout");
        BroadcastManagerUtil.bFA_(BaseApplication.getContext(), sLoginSuccessMgrReceiver, intentFilter, Consts.f2803a, null);
    }

    public void onUpdate(StepsRecord stepsRecord) {
        if (stepsRecord == null) {
            LogUtil.a(TAG, "record is null");
        } else if (getStatus() == 1001) {
            if (sIsBrowseMode) {
                stepsRecord.i = 10000;
                LogUtil.a(TAG, "default target in browse Mode");
            }
            refresh(stepsRecord);
        }
    }

    public void onStop() {
        setStatus(1000);
        if (sCount.decrementAndGet() == 0) {
            try {
                if (sLoginSuccessMgrReceiver != null) {
                    BaseApplication.getContext().unregisterReceiver(sLoginSuccessMgrReceiver);
                }
            } catch (IllegalArgumentException unused) {
                LogUtil.b(TAG, "onStop unregisterReceiver mLoginSuccessMgrReceiver is error");
            }
        }
    }

    public boolean isRunning() {
        return getStatus() == 1001;
    }

    private int getStatus() {
        return this.mStatus;
    }

    private void setStatus(int i) {
        this.mStatus = i;
    }

    public void onLanguageChanged() {
        if (getStatus() == 1001) {
            languageChanged();
        }
    }
}
