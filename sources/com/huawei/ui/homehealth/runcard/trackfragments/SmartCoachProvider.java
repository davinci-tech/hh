package com.huawei.ui.homehealth.runcard.trackfragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.health.R;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.gws;
import defpackage.hab;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class SmartCoachProvider extends BaseKnitDataProvider<Object> {

    /* renamed from: a, reason: collision with root package name */
    private SectionBean f9559a;
    private DownloadCallback b = new DownloadCallback() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SmartCoachProvider.3
        @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
        public void onProgress(long j, long j2, boolean z, String str) {
        }

        @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
        public void onFinish(Object obj) {
            LogUtil.a("Track_Provider_SmartCoachProvider", "mDownloadIndexFileListener onFinish, data = ", obj.toString());
            if (SmartCoachProvider.this.e != null) {
                if (SmartCoachProvider.this.e.i()) {
                    if (SmartCoachProvider.this.e.h()) {
                        LogUtil.a("Track_Provider_SmartCoachProvider", "showUpdateDialog");
                        SmartCoachProvider.this.d();
                        SmartCoachProvider.this.c(true);
                        return;
                    }
                    return;
                }
                LogUtil.h("Track_Provider_SmartCoachProvider", "mDownloadIndexFileListener moveIndexFile failed.");
                return;
            }
            LogUtil.h("Track_Provider_SmartCoachProvider", "mDownloadIndexFileListener mSmartCoachDownloadUtils is null.");
        }

        @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
        public void onFail(int i, Throwable th) {
            LogUtil.h("Track_Provider_SmartCoachProvider", "mDownloadIndexFileListener downloadIndexFile failed.");
        }
    };
    private final BroadcastReceiver c = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SmartCoachProvider.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("Track_Provider_SmartCoachProvider", "mGetFragmentDataNotify enter");
            if (context == null || intent == null) {
                LogUtil.h("Track_Provider_SmartCoachProvider", "context or intent is null.");
                return;
            }
            if (intent.getAction() != null && "com.huawei.health.action_receive_data_notify".equals(intent.getAction())) {
                LogUtil.a("Track_Provider_SmartCoachProvider", "mGetFragmentDataNotify ACTION_RECEIVE_FRAGMENT_DATA_NOTIFY");
                SmartCoachProvider.this.c(false);
            }
            SmartCoachProvider.this.c();
        }
    };
    private Context d;
    private gws e;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return true;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        this.f9559a = sectionBean;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        LogUtil.b("Track_Provider_SmartCoachProvider", "loadData");
        this.d = context;
        e();
    }

    private void e() {
        if (CommonUtil.h(SharedPreferenceManager.b(this.d, Integer.toString(20002), "smart_coach_update_layout_count")) >= 3) {
            this.f9559a.e((Object) null);
            return;
        }
        LogUtil.c("Track_Provider_SmartCoachProvider", "enter start check");
        boolean o = Utils.o();
        boolean m = LanguageUtil.m(this.d);
        if (!o && m && hab.j()) {
            if (this.e == null) {
                this.e = new gws();
            }
            this.e.aVg_(BaseApplication.getActivity(), this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.a("Track_Provider_SmartCoachProvider", "Enter registerFragmentDataNotify()");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.health.action_receive_data_notify");
        LocalBroadcastManager.getInstance(BaseApplication.getContext()).registerReceiver(this.c, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("Track_Provider_SmartCoachProvider", "Enter unregisterPushTokenChange");
        try {
            LocalBroadcastManager.getInstance(BaseApplication.getContext()).unregisterReceiver(this.c);
        } catch (IllegalArgumentException e) {
            LogUtil.b("Track_Provider_SmartCoachProvider", "unregisterFragmentDataNotify illegalArgumentException ", LogAnonymous.b((Throwable) e));
        } catch (RuntimeException e2) {
            LogUtil.b("Track_Provider_SmartCoachProvider", "unregisterFragmentDataNotify runtimeException", LogAnonymous.b((Throwable) e2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        if (z) {
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(20002), "smart_coach_update_layout_count", String.valueOf(CommonUtil.h(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(20002), "smart_coach_update_layout_count")) + 1), (StorageParams) null);
            this.f9559a.e(new Object());
            return;
        }
        this.f9559a.e((Object) null);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap<String, Object> hashMap, Object obj) {
        hashMap.put("BANNER_CARD_CONTENT", BaseApplication.getContext().getString(R.string._2130843719_res_0x7f021847));
        hashMap.put("BANNER_CARD_CANCEL", BaseApplication.getContext().getString(R.string._2130841130_res_0x7f020e2a));
        hashMap.put("BANNER_CARD_SUBMIT", BaseApplication.getContext().getString(R.string.IDS_device_manager_update_health));
        b(hashMap);
    }

    private void b(Map<String, Object> map) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SmartCoachProvider.1
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                if ("BANNER_CARD_CANCEL_CLICK_EVENT".equals(str)) {
                    Intent intent = new Intent();
                    intent.setPackage(BaseApplication.getContext().getPackageName());
                    intent.setAction("com.huawei.health.action_receive_data_notify");
                    LocalBroadcastManager.getInstance(BaseApplication.getContext()).sendBroadcast(intent);
                    if (SmartCoachProvider.this.e != null) {
                        SmartCoachProvider.this.e.g();
                        return;
                    }
                    return;
                }
                if ("BANNER_CARD_SUBMIT_CLICK_EVENT".equals(str)) {
                    Intent intent2 = new Intent();
                    intent2.setPackage(BaseApplication.getContext().getPackageName());
                    intent2.setAction("com.huawei.health.action_receive_data_notify");
                    LocalBroadcastManager.getInstance(BaseApplication.getContext()).sendBroadcast(intent2);
                    if (SmartCoachProvider.this.e != null) {
                        SmartCoachProvider.this.e.d((IBaseResponseCallback) null);
                    }
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }
}
