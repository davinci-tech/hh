package defpackage;

import android.webkit.JavascriptInterface;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.ui.commonui.utils.versioncontrol.VersionControlUtil;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementCallback;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes6.dex */
public class pmr extends JsBaseModule {
    @JavascriptInterface
    public void registerSleepSyncStatus(final long j) {
        ReleaseLogUtil.b("SleepManageH5Bridge", "registerSyncStatus callbackId:", Long.valueOf(j));
        if (VersionControlUtil.isSupportSleepManagement()) {
            ObserverManagerUtil.c("registerSyncStatus", new SleepManagementCallback<Integer>() { // from class: pmr.2
                @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Integer num) {
                    ReleaseLogUtil.b("SleepManageH5Bridge", "SyncStatus success, result: ", num, " callbackId: ", Long.valueOf(j));
                    pmr.this.onSuccessCallback(j, num);
                }

                @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.a("SleepManageH5Bridge", "SyncStatus failed errCode: ", Integer.valueOf(i), " errMsg: ", str, " callbackId: ", Long.valueOf(j));
                    pmr.this.onFailureCallback(j, str, i);
                }
            });
        } else {
            ReleaseLogUtil.b("SleepManageH5Bridge", "SyncStatus failed with not supportSleepManagement, result: ", -1, " callbackId: ", Long.valueOf(j));
            onFailureCallback(j, "not SupportSleepManagement", -1);
        }
    }

    @JavascriptInterface
    public void registerWebViewDataListener(final long j) {
        ReleaseLogUtil.b("SleepManageH5Bridge", "registerSyncStatus callbackId:", Long.valueOf(j));
        if (VersionControlUtil.isSupportSleepManagement()) {
            ObserverManagerUtil.c("SLEEP_MANAGEMENT_TIME_TAG", new SleepManagementCallback<Long>() { // from class: pmr.5
                @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Long l) {
                    ReleaseLogUtil.b("SleepManageH5Bridge", "notify time changed success, result: ", l, " callbackId: ", Long.valueOf(j));
                    pmr.this.onSuccessCallback(j, l);
                }

                @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.a("SleepManageH5Bridge", "notify time changed errCode: ", Integer.valueOf(i), " errMsg: ", str, " callbackId: ", Long.valueOf(j));
                    pmr.this.onFailureCallback(j, str, i);
                }
            });
        } else {
            ReleaseLogUtil.b("SleepManageH5Bridge", "registerWebViewDataListener failed, callbackId: ", Long.valueOf(j));
            onFailureCallback(j, "not SupportSleepManagement", -1);
        }
    }

    @JavascriptInterface
    public void registerDailyResultChanged(final long j) {
        ReleaseLogUtil.b("SleepManageH5Bridge", "registerDailyResultChanged callbackId:", Long.valueOf(j));
        if (VersionControlUtil.isSupportSleepManagement()) {
            ObserverManagerUtil.c("SLEEP_MANAGEMENT_DAILY_RESULT_TAG", new SleepManagementCallback<fck>() { // from class: pmr.3
                @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onSuccess(fck fckVar) {
                    ReleaseLogUtil.b("SleepManageH5Bridge", "notify daily result changed success, result: ", fckVar, " callbackId: ", Long.valueOf(j));
                    pmr.this.onSuccessCallback(j, fckVar);
                }

                @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.a("SleepManageH5Bridge", "notify daily result changed errCode: ", Integer.valueOf(i), " errMsg: ", str, " callbackId: ", Long.valueOf(j));
                    pmr.this.onFailureCallback(j, str, i);
                }
            });
        } else {
            ReleaseLogUtil.b("SleepManageH5Bridge", "registerDailyResultChanged failed, callbackId: ", Long.valueOf(j));
            onFailureCallback(j, "not SupportSleepManagement", -1);
        }
    }

    @JavascriptInterface
    public void registerDataValid(final long j) {
        ReleaseLogUtil.b("SleepManageH5Bridge", "registerDataValidChanged callbackId:", Long.valueOf(j));
        if (VersionControlUtil.isSupportSleepManagement()) {
            ObserverManagerUtil.c("SLEEP_MANAGEMENT_DATA_VALID_TAG", new SleepManagementCallback<Boolean>() { // from class: pmr.1
                @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Boolean bool) {
                    ReleaseLogUtil.b("SleepManageH5Bridge", "notify registerDataValid success, result: ", bool, " callbackId: ", Long.valueOf(j));
                    pmr.this.onSuccessCallback(j, bool);
                }

                @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.a("SleepManageH5Bridge", "notify registerDataValid errCode: ", Integer.valueOf(i), " errMsg: ", str, " callbackId: ", Long.valueOf(j));
                    pmr.this.onFailureCallback(j, str, i);
                }
            });
        } else {
            ReleaseLogUtil.b("SleepManageH5Bridge", "registerDataValidChanged failed, callbackId: ", Long.valueOf(j));
            onFailureCallback(j, "not SupportSleepManagement", -1);
        }
    }
}
