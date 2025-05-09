package com.huawei.ui.homehealth.achievementcard;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.device.interactors.NotificationPushInteractor;
import com.huawei.ui.homehealth.refreshcard.CardViewHolder;
import defpackage.ixx;
import defpackage.oxa;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedHashMap;

/* loaded from: classes6.dex */
public class AchievementCardViewHolder extends CardViewHolder {
    private boolean d;
    private Handler e;

    public AchievementCardViewHolder(View view, Context context, boolean z) {
        super(view, context, z);
        this.d = false;
        this.e = new c(this);
        LogUtil.a("AchievementCardViewHolder", "AchievementCardViewHolder");
    }

    static class b implements Runnable {
        private WeakReference<AchievementCardViewHolder> c;

        b(AchievementCardViewHolder achievementCardViewHolder) {
            this.c = new WeakReference<>(achievementCardViewHolder);
        }

        @Override // java.lang.Runnable
        public void run() {
            AchievementCardViewHolder achievementCardViewHolder = this.c.get();
            if (achievementCardViewHolder == null) {
                return;
            }
            LogUtil.a("AchievementCardViewHolder", "check notification is enable");
            if (CommonUtil.ab(BaseApplication.e())) {
                achievementCardViewHolder.d(1);
                return;
            }
            LogUtil.h("AchievementCardViewHolder", "check notification is disabled");
            achievementCardViewHolder.d(0);
            CommonUtil.aj(BaseApplication.e());
            Handler handler = achievementCardViewHolder.e;
            if (handler != null) {
                handler.removeMessages(7);
                handler.sendEmptyMessageDelayed(7, 20000L);
            }
        }
    }

    static class c extends BaseHandler<AchievementCardViewHolder> {
        c(AchievementCardViewHolder achievementCardViewHolder) {
            super(achievementCardViewHolder);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: cWV_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(AchievementCardViewHolder achievementCardViewHolder, Message message) {
            int i = message.what;
            if (i == 5) {
                achievementCardViewHolder.a();
            } else if (i == 7) {
                achievementCardViewHolder.d();
            } else {
                LogUtil.h("AchievementCardViewHolder", "handleMessage else");
            }
        }
    }

    public void e() {
        LogUtil.a("AchievementCardViewHolder", "restartNotifyService");
        Handler handler = this.e;
        if (handler != null) {
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = 5;
            this.e.sendMessageDelayed(obtainMessage, PreConnectManager.CONNECT_INTERNAL);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.a("AchievementCardViewHolder", "enter checkIsNotifyEnable");
        this.d = false;
        if (CommonUtil.ab(getContext())) {
            LogUtil.a("AchievementCardViewHolder", "Notify service is running");
            c(1);
            return;
        }
        LogUtil.h("AchievementCardViewHolder", "Notify service is disabled,send broadcast");
        c(0);
        if (CommonUtil.bh()) {
            LogUtil.h("AchievementCardViewHolder", "checkIsNotifyEnable isHuaweiSystem");
            return;
        }
        if (CommonUtil.o(getContext()) == 30) {
            LogUtil.h("AchievementCardViewHolder", "checkIsNotifyEnable getRunningServicesSize is 30");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.huawei.health.action.NPL_SERVICE_NOT_AVALIABLE");
        intent.setPackage(getContext().getPackageName());
        getContext().sendBroadcast(intent, LocalBroadcast.c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (!this.d) {
            if (oxa.a() == null) {
                LogUtil.h("AchievementCardViewHolder", "checkNotifyService DeviceStateInteractors instance is null");
                return;
            }
            DeviceInfo f = oxa.a().f();
            if (f != null && f.getDeviceConnectState() == 2) {
                DeviceCapability e = DeviceSettingsInteractors.d(getContext()).e();
                NotificationPushInteractor notificationPushInteractor = new NotificationPushInteractor(getContext());
                if (e != null && e.isMessageAlert() && notificationPushInteractor.b()) {
                    ThreadPoolManager.d().c("AchievementCardViewHolder", new b(this));
                    this.d = true;
                    return;
                }
                return;
            }
            LogUtil.h("AchievementCardViewHolder", "onResume is checking notification not connected");
            return;
        }
        LogUtil.h("AchievementCardViewHolder", "onResume is checking notification");
    }

    public void b() {
        Handler handler = this.e;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.e = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        String str = Build.BRAND;
        HashMap hashMap = new HashMap(8);
        hashMap.put(JsbMapKeyNames.H5_BRAND, str);
        hashMap.put("code", Integer.valueOf(i));
        ixx.d().d(getContext(), AnalyticsValue.NOTIFY_SERVICE_ENABLE_1090027.value(), hashMap, 0);
    }

    private void c(int i) {
        String str = Build.BRAND;
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(8);
        linkedHashMap.put(JsbMapKeyNames.H5_BRAND, str);
        linkedHashMap.put("code", String.valueOf(i));
        OpAnalyticsUtil.getInstance().setEvent2nd(AnalyticsValue.NOTIFY_SERVICE_ENABLE_1090028.value(), linkedHashMap);
    }
}
