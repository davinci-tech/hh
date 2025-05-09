package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.gson.Gson;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.h5pro.service.anotation.H5ProMethod;
import com.huawei.health.h5pro.service.anotation.H5ProService;
import com.huawei.health.sleep.SleepApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.SleepMonthlyProcessResultCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.jsoperation.JsUtil;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.utils.versioncontrol.VersionControlUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementCallback;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementJsUtil;
import com.huawei.ui.main.stories.fitness.views.coresleep.RegularWorkAndRestTimeView;
import com.huawei.ui.main.stories.fitness.views.coresleep.RestrictNapsProgressBar;
import com.huawei.ui.main.stories.fitness.views.coresleep.SleepTaskDialog;
import defpackage.dsl;
import defpackage.fca;
import defpackage.fce;
import defpackage.fck;
import defpackage.fcw;
import defpackage.fcy;
import defpackage.fcz;
import defpackage.fdc;
import defpackage.fdh;
import defpackage.gmz;
import defpackage.koq;
import defpackage.pmt;
import defpackage.pob;
import defpackage.pqg;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@H5ProService(name = SleepManagementJsUtil.TAG, users = {"9ea3f06fe2dea3e2c7c21ea1ba1e07c370a786c68417a4a96cb986576883e10f"})
/* loaded from: classes.dex */
public class SleepManagementJsUtil {
    private static final String BROADCASTTODISMISSBUBBLE = "broadcastToDismissBubble";
    private static final String BUBBLE_DISMISS = "BUBBLE_DISMISS";
    private static final String BUBBLE_ON_WEBVIEW_FOR_ENTRY = "BUBBLE_ON_WEBVIEW_FOR_ENTRY";
    private static final String BUBBLE_ON_WEBVIEW_FOR_MONTH_DATA = "BUBBLE_ON_WEBVIEW_FOR_MONTH_DATA";
    private static final int GENERATE_TYPE = 1;
    private static final String SP_KEY_ALARM_INFO = "SLEEP_RECORD_ALARM_INFO";
    private static final String TAG = "SleepManagementJsUtil";
    private static final String TAG_RELEASE = "R_SleepManagementJsUtil";
    private static final int UNIQUE_ALARM_ID = 1000;

    private SleepManagementJsUtil() {
    }

    @H5ProMethod(name = "getQuestionnaire")
    public static void getQuestionnaire(long j, long j2, final SleepManagementCallback<List<String>> sleepManagementCallback) {
        if (sleepManagementCallback == null) {
            LogUtil.h(TAG, "getQuestionnaire callback is null");
            return;
        }
        if (!VersionControlUtil.isSupportSleepManagement()) {
            LogUtil.h(TAG, "not support sleep management");
            sleepManagementCallback.onFailure(-1, "not support sleep management");
            return;
        }
        LogUtil.h(TAG, "getQuestionnaire callback return");
        SleepApi sleepApi = (SleepApi) Services.a("Main", SleepApi.class);
        if (sleepApi != null) {
            sleepApi.requestQuestionnaireProcessResult(j, j2, new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementJsUtil.1
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (!(obj instanceof fdc)) {
                        LogUtil.b(SleepManagementJsUtil.TAG, "obj is error");
                        SleepManagementCallback.this.onFailure(-1, "obj is error");
                        return;
                    }
                    fdc fdcVar = (fdc) obj;
                    ArrayList arrayList = new ArrayList();
                    if (koq.b(fdcVar.b())) {
                        LogUtil.b(SleepManagementJsUtil.TAG, "QuestionnaireResultBean is null");
                        SleepManagementCallback.this.onFailure(-1, "QuestionnaireResultBean is null");
                    } else {
                        arrayList.addAll(fdcVar.b());
                        LogUtil.a(SleepManagementJsUtil.TAG, "Questionnaire list： ", arrayList);
                        SleepManagementCallback.this.onSuccess(arrayList);
                    }
                }
            });
        } else {
            LogUtil.a(TAG, "sleepServiceApi is null");
            sleepManagementCallback.onFailure(-1, "getQuestionnaire sleepServiceApi is null");
        }
    }

    @H5ProMethod(name = "getQuestionnaireAndResult")
    public static void getQuestionnaireAndResult(long j, long j2, final SleepManagementCallback<fdc> sleepManagementCallback) {
        if (sleepManagementCallback == null) {
            LogUtil.h(TAG, "getQuestionnaire callback is null");
            return;
        }
        if (!VersionControlUtil.isSupportSleepManagement()) {
            LogUtil.h(TAG, "not support sleep management");
            sleepManagementCallback.onFailure(-1, "not support sleep management");
            return;
        }
        LogUtil.h(TAG, "getQuestionnaire callback return");
        SleepApi sleepApi = (SleepApi) Services.a("Main", SleepApi.class);
        if (sleepApi != null) {
            sleepApi.requestQuestionnaireProcessResult(j, j2, new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementJsUtil.7
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (!(obj instanceof fdc)) {
                        LogUtil.b(SleepManagementJsUtil.TAG, "obj is error");
                        SleepManagementCallback.this.onFailure(-1, "obj is error");
                        return;
                    }
                    fdc fdcVar = (fdc) obj;
                    if (koq.b(fdcVar.b())) {
                        LogUtil.b(SleepManagementJsUtil.TAG, "QuestionnaireResultBean is null");
                        SleepManagementCallback.this.onFailure(-1, "QuestionnaireResultBean is null");
                    } else {
                        LogUtil.a(SleepManagementJsUtil.TAG, "QuestionnaireResultBean： ", fdcVar.toString());
                        SleepManagementCallback.this.onSuccess(fdcVar);
                    }
                }
            });
        } else {
            LogUtil.a(TAG, "sleepServiceApi is null");
            sleepManagementCallback.onFailure(-1, "getQuestionnaire sleepServiceApi is null");
        }
    }

    @H5ProMethod(name = "getDailyProcessResult")
    public static void getDailyProcessResult(long j, SleepManagementCallback<fck> sleepManagementCallback) {
        if (sleepManagementCallback == null) {
            LogUtil.h(TAG, "getDailyProcessResult callback is null");
        } else if (!VersionControlUtil.isSupportSleepManagement()) {
            LogUtil.h(TAG, "not support sleep management");
            sleepManagementCallback.onFailure(-1, "not support sleep management");
        } else {
            pob.b(j, sleepManagementCallback);
        }
    }

    @H5ProMethod(name = "startJobService")
    public static void startJobService(final SleepManagementCallback<Boolean> sleepManagementCallback) {
        if (VersionControlUtil.isSupportSleepManagement()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementJsUtil.9
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.a(SleepManagementJsUtil.TAG, "startJobService");
                    pmt.d().a();
                    pob.d(true);
                    SleepManagementCallback.this.onSuccess(true);
                }
            });
        }
    }

    @H5ProMethod(name = "stopJobService")
    public static void stopJobService(final SleepManagementCallback<Boolean> sleepManagementCallback) {
        if (VersionControlUtil.isSupportSleepManagement()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementJsUtil.8
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.a(SleepManagementJsUtil.TAG, "stopJobService");
                    pmt.d().e();
                    pob.d(false);
                    SleepManagementCallback.this.onSuccess(true);
                }
            });
        }
    }

    @H5ProMethod(name = BROADCASTTODISMISSBUBBLE)
    public static void broadcastToDismissBubble(final SleepManagementCallback<Boolean> sleepManagementCallback) {
        if (VersionControlUtil.isSupportSleepManagement()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementJsUtil.12
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.a(SleepManagementJsUtil.TAG, SleepManagementJsUtil.BROADCASTTODISMISSBUBBLE);
                    ObserverManagerUtil.c(SleepManagementJsUtil.BUBBLE_DISMISS, SleepManagementJsUtil.BUBBLE_ON_WEBVIEW_FOR_ENTRY);
                    ObserverManagerUtil.c(SleepManagementJsUtil.BUBBLE_DISMISS, SleepManagementJsUtil.BUBBLE_ON_WEBVIEW_FOR_MONTH_DATA);
                    SleepManagementCallback.this.onSuccess(true);
                }
            });
        }
    }

    @H5ProMethod(name = "checkSleepUserPrivate")
    public static void checkUserPrivateAndIntroduce(SleepManagementCallback<Boolean> sleepManagementCallback) {
        checkAndShownPrivateDialog(sleepManagementCallback);
    }

    @H5ProMethod(name = "startSleepSync")
    public static void startSleepAllDataSync(SleepManagementCallback<Boolean> sleepManagementCallback) {
        ReleaseLogUtil.b(TAG, "startSleepAllDataSync callback:");
        if (VersionControlUtil.isSupportSleepManagement()) {
            ObserverManagerUtil.c("startSleepAllDataSync", sleepManagementCallback);
        } else if (sleepManagementCallback != null) {
            LogUtil.h(TAG, "startSleepAllDataSync callback is not support");
            sleepManagementCallback.onFailure(-1, "not SupportSleepManagement");
        }
    }

    private static void checkAndShownPrivateDialog(SleepManagementCallback<Boolean> sleepManagementCallback) {
        if (!VersionControlUtil.isSupportSleepManagement()) {
            ReleaseLogUtil.a(TAG, "checkAndShownPrivateDialog with not support sleep management.");
            if (sleepManagementCallback != null) {
                sleepManagementCallback.onFailure(-1, "no need check with not support sleepManagement");
                return;
            }
            return;
        }
        List<Integer> needOpenPrivate = getNeedOpenPrivate();
        if (!needOpenPrivate.isEmpty()) {
            showPrivateOpenDialog(needOpenPrivate, sleepManagementCallback);
        } else if (sleepManagementCallback != null) {
            sleepManagementCallback.onSuccess(true);
        }
    }

    public static List<Integer> getNeedOpenPrivate() {
        int[] iArr = {3, 7};
        ArrayList arrayList = new ArrayList(2);
        for (int i = 0; i < 2; i++) {
            int i2 = iArr[i];
            if (!getPersonalPrivacySettingValue(i2)) {
                arrayList.add(Integer.valueOf(i2));
            }
        }
        return arrayList;
    }

    public static void showPrivateOpenDialog(final List<Integer> list, final SleepManagementCallback<Boolean> sleepManagementCallback) {
        Activity wa_ = BaseApplication.wa_();
        if (wa_ == null || koq.b(list)) {
            return;
        }
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(wa_).e(R$string.IDS_sleep_private_introduce_content).czC_(R$string.IDS_settings_about_huawei_cloud_service_action_turn_on, new View.OnClickListener() { // from class: pne
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepManagementJsUtil.lambda$showPrivateOpenDialog$0(list, view);
            }
        }).czz_(R$string.IDS_plugin_fitnessadvice_cancal, new View.OnClickListener() { // from class: png
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        if (sleepManagementCallback != null) {
            e.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: pnj
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    SleepManagementJsUtil.lambda$showPrivateOpenDialog$2(SleepManagementCallback.this, dialogInterface);
                }
            });
        }
        e.show();
    }

    public static /* synthetic */ void lambda$showPrivateOpenDialog$0(List list, View view) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            setPersonalPrivacySettingValue(((Integer) it.next()).intValue(), true);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void lambda$showPrivateOpenDialog$2(SleepManagementCallback sleepManagementCallback, DialogInterface dialogInterface) {
        if (getNeedOpenPrivate().isEmpty()) {
            sleepManagementCallback.onSuccess(true);
        } else {
            sleepManagementCallback.onSuccess(false);
        }
    }

    public static boolean getPersonalPrivacySettingValue(int i) {
        String c = gmz.d().c(i);
        LogUtil.a(TAG, "getPersonalPrivacySettingValue... privacyId: ", Integer.valueOf(i), " value: ", c);
        return Boolean.TRUE.toString().equalsIgnoreCase(c);
    }

    public static void setPersonalPrivacySettingValue(int i, boolean z) {
        ReleaseLogUtil.b(TAG, "setPersonalPrivacySettingValue... privacyId = ", Integer.valueOf(i), ", isOpen = ", Boolean.valueOf(z));
        gmz.d().c(i, z, TAG, new IBaseResponseCallback() { // from class: pnz
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                SleepManagementJsUtil.lambda$setPersonalPrivacySettingValue$3(i2, obj);
            }
        });
    }

    public static /* synthetic */ void lambda$setPersonalPrivacySettingValue$3(int i, Object obj) {
        if (i == 0) {
            ReleaseLogUtil.b(TAG, "onResponse setUserPrivacy success ");
        } else {
            ReleaseLogUtil.c(TAG, "onResponse setUserPrivacy failure");
        }
    }

    @H5ProMethod(name = "requestMonthProcessResult")
    public static void requestMonthProcessResult(final String str, final long j, final long j2, final SleepManagementCallback<Map<String, Object>> sleepManagementCallback) {
        if (sleepManagementCallback == null) {
            LogUtil.h(TAG, "getMonthProcessResult callback is null");
        } else if (!VersionControlUtil.isSupportSleepManagement()) {
            LogUtil.h(TAG, "not support sleep management");
            sleepManagementCallback.onFailure(-1, "not support sleep management");
        } else {
            LogUtil.a(TAG, "requestMonthProcessResult, questionResult: ", str);
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementJsUtil.15
                /* JADX WARN: Removed duplicated region for block: B:13:0x0074  */
                /* JADX WARN: Removed duplicated region for block: B:9:0x0071  */
                @Override // java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void run() {
                    /*
                        r12 = this;
                        java.lang.String r0 = "SleepManagementJsUtil"
                        java.lang.String r1 = r1
                        fcz r1 = com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementJsUtil.access$000(r1)
                        r9 = 1
                        r10 = 0
                        r11 = 2
                        org.json.JSONObject r2 = new org.json.JSONObject     // Catch: org.json.JSONException -> L53
                        java.lang.String r3 = r1     // Catch: org.json.JSONException -> L53
                        r2.<init>(r3)     // Catch: org.json.JSONException -> L53
                        java.lang.String r3 = "questionnaireMonthlyResult"
                        org.json.JSONObject r2 = r2.getJSONObject(r3)     // Catch: org.json.JSONException -> L53
                        fcw r3 = new fcw     // Catch: org.json.JSONException -> L53
                        r3.<init>()     // Catch: org.json.JSONException -> L53
                        java.lang.String r4 = "monthly_level"
                        int r4 = r2.optInt(r4)     // Catch: org.json.JSONException -> L54
                        r3.a(r4)     // Catch: org.json.JSONException -> L54
                        java.lang.String r4 = "monthly_rhythm"
                        int r4 = r2.optInt(r4)     // Catch: org.json.JSONException -> L54
                        r3.d(r4)     // Catch: org.json.JSONException -> L54
                        java.lang.String r4 = "monthly_problem_val"
                        int r4 = r2.optInt(r4)     // Catch: org.json.JSONException -> L54
                        r3.e(r4)     // Catch: org.json.JSONException -> L54
                        java.lang.String r4 = "monthly_problem"
                        int r2 = r2.optInt(r4)     // Catch: org.json.JSONException -> L54
                        r3.b(r2)     // Catch: org.json.JSONException -> L54
                        java.lang.Object[] r2 = new java.lang.Object[r11]     // Catch: org.json.JSONException -> L54
                        java.lang.String r4 = "monthlyResult: "
                        r2[r10] = r4     // Catch: org.json.JSONException -> L54
                        r2[r9] = r3     // Catch: org.json.JSONException -> L54
                        com.huawei.hwlogsmodel.LogUtil.a(r0, r2)     // Catch: org.json.JSONException -> L54
                        goto L5e
                    L53:
                        r3 = 0
                    L54:
                        java.lang.String r2 = "object null"
                        java.lang.Object[] r2 = new java.lang.Object[]{r2}
                        com.huawei.hwlogsmodel.LogUtil.h(r0, r2)
                    L5e:
                        long r4 = r2
                        long r6 = r4
                        com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementCallback r8 = r6
                        r2 = r1
                        com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementJsUtil.access$100(r2, r3, r4, r6, r8)
                        java.lang.Object[] r2 = new java.lang.Object[r11]
                        java.lang.String r3 = "result: "
                        r2[r10] = r3
                        if (r1 != 0) goto L74
                        java.lang.String r1 = ""
                        goto L78
                    L74:
                        java.lang.String r1 = r1.toString()
                    L78:
                        r2[r9] = r1
                        com.huawei.hwlogsmodel.LogUtil.a(r0, r2)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementJsUtil.AnonymousClass15.run():void");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static fcz buildQuestionResult(String str) {
        JSONArray optJSONArray;
        fcz fczVar = new fcz();
        try {
            optJSONArray = new JSONObject(str).optJSONArray("questionResult");
        } catch (JSONException unused) {
            LogUtil.b(TAG, "buildQuestionResult JSONException");
        }
        if (optJSONArray != null && optJSONArray.length() != 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    fcy fcyVar = new fcy();
                    fcyVar.c(optJSONObject.optInt("question"));
                    fcyVar.d(optJSONObject.optInt(JsUtil.SCORE));
                    arrayList.add(fcyVar);
                }
            }
            fczVar.e(arrayList);
            return fczVar;
        }
        return fczVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void requestMonthResult(fcz fczVar, fcw fcwVar, final long j, long j2, final SleepManagementCallback<Map<String, Object>> sleepManagementCallback) {
        LogUtil.a(TAG, "requestMonthResult, questionnaireResult: ", fczVar);
        final long currentTimeMillis = System.currentTimeMillis();
        SleepApi sleepApi = (SleepApi) Services.a("Main", SleepApi.class);
        if (sleepApi != null) {
            sleepApi.requestMonthlyProcessResult(fczVar, fcwVar, j, j2, new SleepMonthlyProcessResultCallback() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementJsUtil.14
                @Override // com.huawei.hwbasemgr.SleepMonthlyProcessResultCallback
                public void onResponse(int i, Object obj) {
                    if (!(obj instanceof fdh)) {
                        LogUtil.b(SleepManagementJsUtil.TAG, "objData error");
                        SleepManagementCallback.this.onFailure(-1, "requestMonthResult objData error");
                        return;
                    }
                    LogUtil.a(SleepManagementJsUtil.TAG, "handle from requestMonthResult");
                    fdh fdhVar = (fdh) obj;
                    try {
                        pob.b(i, obj, j, currentTimeMillis, 1);
                    } catch (NoSuchMethodError e) {
                        ReleaseLogUtil.c(SleepManagementJsUtil.TAG_RELEASE, "requestMonthResult error ", ExceptionUtils.d(e));
                    }
                    SleepManagementCallback.this.onSuccess(pob.c(fdhVar, currentTimeMillis));
                }
            });
        } else {
            LogUtil.a(TAG, "sleepServiceApi is null");
            sleepManagementCallback.onFailure(-1, "requestMonthResult sleepServiceApi is null");
        }
    }

    @H5ProMethod(name = "queryMonthProcessResultWithRecent")
    public static void queryMonthProcessResultWithRecent(long j, SleepManagementCallback<Map<String, Object>> sleepManagementCallback) {
        LogUtil.a(TAG, "queryMonthProcessResultWithRecent time: ", Long.valueOf(j));
        queryMonthLabel(j, sleepManagementCallback, true);
    }

    @H5ProMethod(name = "queryMonthProcessResult")
    public static void queryMonthProcessResult(long j, SleepManagementCallback<Map<String, Object>> sleepManagementCallback) {
        LogUtil.a(TAG, "queryMonthProcessResult time: ", Long.valueOf(j));
        queryMonthLabel(j, sleepManagementCallback, false);
    }

    private static void queryMonthLabel(final long j, final SleepManagementCallback<Map<String, Object>> sleepManagementCallback, final boolean z) {
        if (sleepManagementCallback == null) {
            LogUtil.h(TAG, "queryMonthProcessResult callback is null");
        } else if (!VersionControlUtil.isSupportSleepManagement()) {
            LogUtil.h(TAG, "not support sleep management");
            sleepManagementCallback.onFailure(-1, "not support sleep management");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementJsUtil.13
                @Override // java.lang.Runnable
                public void run() {
                    pob.d(new HiDataReadResultListener() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementJsUtil.13.1
                        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                        public void onResult(Object obj, int i, int i2) {
                            LogUtil.a(SleepManagementJsUtil.TAG, "queryMonthResult onResult, data: ", obj);
                            if (i != 0) {
                                LogUtil.h(SleepManagementJsUtil.TAG, "errorCode: ", Integer.valueOf(i));
                                SleepManagementCallback.this.onFailure(-1, "errorCode: " + i);
                                return;
                            }
                            if (!(obj instanceof SparseArray)) {
                                SleepManagementCallback.this.onFailure(-1, "data parse error");
                                return;
                            }
                            SparseArray sparseArray = (SparseArray) obj;
                            if (sparseArray.size() <= 0) {
                                LogUtil.h(SleepManagementJsUtil.TAG, "map.size() <= 0");
                                SleepManagementCallback.this.onFailure(-1, "map.size() <= 0");
                            } else {
                                LogUtil.a(SleepManagementJsUtil.TAG, "queryMonthProcessResult success");
                                SleepManagementCallback.this.onSuccess(pob.drk_(sparseArray, j, z));
                            }
                        }

                        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                        public void onResultIntent(int i, Object obj, int i2, int i3) {
                            LogUtil.a(SleepManagementJsUtil.TAG, "queryMonthProcessResult intentType ", Integer.valueOf(i), " errorCode ", Integer.valueOf(i2));
                        }
                    }, j, z);
                }
            });
        }
    }

    @H5ProMethod(name = "setSleepTime")
    public static void setSleepTime(String str, SleepManagementCallback<Boolean> sleepManagementCallback) {
        fca fcaVar;
        if (sleepManagementCallback == null || TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "queryMonthProcessResult callback is null");
            return;
        }
        if (!VersionControlUtil.isSupportSleepManagement()) {
            LogUtil.h(TAG, "not support sleep management");
            sleepManagementCallback.onFailure(-1, "not support sleep management");
            return;
        }
        LogUtil.a(TAG, "setSleepTime enter");
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("sleepMinute");
            int optInt2 = jSONObject.optInt("sleepHour");
            int optInt3 = jSONObject.optInt("minute");
            int optInt4 = jSONObject.optInt("hour");
            fce fceVar = new fce();
            Set<fca> alarms = fceVar.getAlarms();
            LogUtil.a(TAG, "alarmInfos: ", Integer.valueOf(alarms.size()));
            Iterator<fca> it = alarms.iterator();
            while (true) {
                if (!it.hasNext()) {
                    fcaVar = null;
                    break;
                } else {
                    fcaVar = it.next();
                    if (fcaVar.b() == 1000) {
                        break;
                    }
                }
            }
            if (fcaVar == null) {
                LogUtil.b(TAG, "setSleepTime curAlarmInfo is null");
                fcaVar = new fca(1000, optInt4, optInt3, null);
                fcaVar.e(optInt);
                fcaVar.d(optInt2);
                alarms.add(fcaVar);
            } else {
                fcaVar.e(optInt);
                fcaVar.d(optInt2);
                fcaVar.c(optInt3);
                fcaVar.a(optInt4);
            }
            boolean save = fceVar.save(alarms);
            LogUtil.a(TAG, "isRet: ", Boolean.valueOf(save));
            if (save) {
                setAlarmInfo(fcaVar);
                sleepManagementCallback.onSuccess(true);
            } else {
                sleepManagementCallback.onFailure(-1, "addRet: false");
            }
        } catch (JSONException e) {
            LogUtil.b(TAG, "parse SleepAlarmInfo error:", e.getMessage());
            sleepManagementCallback.onFailure(-1, "parse SleepAlarmInfo error");
        }
    }

    private static void setAlarmInfo(fca fcaVar) {
        if (fcaVar == null) {
            LogUtil.h(TAG, "setAlarmInfo is null");
            return;
        }
        String json = new Gson().toJson(fcaVar);
        LogUtil.a(TAG, "setAlarmInfo: ", json);
        setSharedPreference(BaseApplication.e(), SP_KEY_ALARM_INFO, json);
    }

    private static void setSharedPreference(Context context, String str, String str2) {
        SharedPreferenceManager.e(context, Integer.toString(10000), str, str2, (StorageParams) null);
    }

    @H5ProMethod(name = "showRegularWakeupInputDialog")
    public static void showRegularWakeupInputDialog(final int i, final SleepManagementCallback<Integer> sleepManagementCallback) {
        final Activity wa_ = BaseApplication.wa_();
        if (wa_ == null) {
            LogUtil.b(TAG, "top activity is null");
            return;
        }
        Context e = BaseApplication.e();
        new SleepTaskDialog.Builder(wa_).a(pqg.e()).b(e.getString(R$string.IDS_health_model_wake_up)).c(e.getString(R$string.IDS_sleep_complete_this_month, e.getResources().getQuantityString(R.plurals.IDS_user_profile_achieve_num_day, i, Integer.valueOf(i)))).a(e.getString(R$string.IDS_getup_regularly_no_data_content)).dxv_(e.getString(R$string.IDS_hw_health_show_healthdata_input), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementJsUtil.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                pqg.b(wa_);
                sleepManagementCallback.onSuccess(0);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).dxu_(e.getString(R$string.IDS_device_know), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementJsUtil.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SleepManagementCallback.this.onSuccess(0);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).dxw_(new View.OnClickListener() { // from class: pnh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepManagementJsUtil.lambda$showRegularWakeupInputDialog$4(i, view);
            }
        }).dxr_(new View.OnClickListener() { // from class: pnn
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepManagementJsUtil.lambda$showRegularWakeupInputDialog$5(wa_, view);
            }
        }).b().show();
    }

    public static /* synthetic */ void lambda$showRegularWakeupInputDialog$4(int i, View view) {
        dsl.ZN_(6, i, pqg.drZ_());
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void lambda$showRegularWakeupInputDialog$5(Activity activity, View view) {
        dsl.a(6, activity);
        ViewClickInstrumentation.clickOnView(view);
    }

    @H5ProMethod(name = "showRegularWorkAndRestInputDialog")
    public static void showRegularWorkAndRestInputDialog(final int i, long j, long j2, boolean z, boolean z2, final SleepManagementCallback<Integer> sleepManagementCallback) {
        final Activity wa_ = BaseApplication.wa_();
        if (wa_ == null) {
            LogUtil.b(TAG, "top activity is null");
            return;
        }
        RegularWorkAndRestTimeView regularWorkAndRestTimeView = new RegularWorkAndRestTimeView(wa_);
        regularWorkAndRestTimeView.setupSleepWakeupTime(j, j2, z, z2);
        Context e = BaseApplication.e();
        new SleepTaskDialog.Builder(wa_).a(pqg.b()).b(e.getString(R$string.IDS_regular_work_and_rest)).c(e.getString(R$string.IDS_sleep_complete_this_month, e.getResources().getQuantityString(R.plurals.IDS_user_profile_achieve_num_day, i, Integer.valueOf(i)))).a(e.getString(R$string.IDS_regular_work_and_rest_no_data)).dxv_(e.getString(R$string.IDS_hw_health_show_healthdata_input), new View.OnClickListener() { // from class: pnv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepManagementJsUtil.lambda$showRegularWorkAndRestInputDialog$6(wa_, sleepManagementCallback, view);
            }
        }).dxu_(e.getString(R$string.IDS_device_know), new View.OnClickListener() { // from class: pny
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepManagementJsUtil.lambda$showRegularWorkAndRestInputDialog$7(SleepManagementCallback.this, view);
            }
        }).dxs_(regularWorkAndRestTimeView).dxw_(new View.OnClickListener() { // from class: pmz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepManagementJsUtil.lambda$showRegularWorkAndRestInputDialog$8(i, view);
            }
        }).dxr_(new View.OnClickListener() { // from class: pmy
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepManagementJsUtil.lambda$showRegularWorkAndRestInputDialog$9(wa_, view);
            }
        }).b().show();
    }

    public static /* synthetic */ void lambda$showRegularWorkAndRestInputDialog$6(Activity activity, SleepManagementCallback sleepManagementCallback, View view) {
        pqg.b(activity);
        sleepManagementCallback.onSuccess(0);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void lambda$showRegularWorkAndRestInputDialog$7(SleepManagementCallback sleepManagementCallback, View view) {
        sleepManagementCallback.onSuccess(0);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void lambda$showRegularWorkAndRestInputDialog$8(int i, View view) {
        dsl.ZN_(101, i, pqg.dsa_());
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void lambda$showRegularWorkAndRestInputDialog$9(Activity activity, View view) {
        dsl.a(101, activity);
        ViewClickInstrumentation.clickOnView(view);
    }

    @H5ProMethod(name = "showRegularWorkAndRestSuccessDialog")
    public static void showRegularWorkAndRestSuccessDialog(final int i, long j, long j2, final SleepManagementCallback<Integer> sleepManagementCallback) {
        Activity wa_ = BaseApplication.wa_();
        if (wa_ == null) {
            LogUtil.b(TAG, "top activity is null");
            return;
        }
        RegularWorkAndRestTimeView regularWorkAndRestTimeView = new RegularWorkAndRestTimeView(wa_);
        regularWorkAndRestTimeView.setupSleepWakeupTime(j, j2, true, true);
        Context e = BaseApplication.e();
        new SleepTaskDialog.Builder(wa_).a(pqg.b()).b(e.getString(R$string.IDS_hw_sport_result_congratulations)).c(e.getString(R$string.IDS_sleep_complete_this_month, e.getResources().getQuantityString(R.plurals.IDS_user_profile_achieve_num_day, i, Integer.valueOf(i)))).dxt_(e.getString(R$string.IDS_device_know), new View.OnClickListener() { // from class: pnm
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepManagementJsUtil.lambda$showRegularWorkAndRestSuccessDialog$10(SleepManagementCallback.this, view);
            }
        }).dxs_(regularWorkAndRestTimeView).dxw_(new View.OnClickListener() { // from class: pno
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepManagementJsUtil.lambda$showRegularWorkAndRestSuccessDialog$11(i, view);
            }
        }).b().show();
    }

    public static /* synthetic */ void lambda$showRegularWorkAndRestSuccessDialog$10(SleepManagementCallback sleepManagementCallback, View view) {
        sleepManagementCallback.onSuccess(0);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void lambda$showRegularWorkAndRestSuccessDialog$11(int i, View view) {
        dsl.ZN_(101, i, pqg.dsa_());
        ViewClickInstrumentation.clickOnView(view);
    }

    @H5ProMethod(name = "showRegularWorkAndRestFailDialog")
    public static void showRegularWorkAndRestFailDialog(final int i, long j, long j2, boolean z, boolean z2, final SleepManagementCallback<Integer> sleepManagementCallback) {
        final Activity wa_ = BaseApplication.wa_();
        if (wa_ == null) {
            LogUtil.b(TAG, "top activity is null");
            return;
        }
        RegularWorkAndRestTimeView regularWorkAndRestTimeView = new RegularWorkAndRestTimeView(wa_);
        regularWorkAndRestTimeView.setupSleepWakeupTime(j, j2, z, z2);
        Context e = BaseApplication.e();
        new SleepTaskDialog.Builder(wa_).a(pqg.b()).b(e.getString(R$string.IDS_fitness_comeon)).c(e.getString(R$string.IDS_regular_work_and_rest_fail_task, e.getResources().getQuantityString(R.plurals.IDS_user_profile_achieve_num_day, i, Integer.valueOf(i)))).dxt_(e.getString(R$string.IDS_device_know), new View.OnClickListener() { // from class: pni
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepManagementJsUtil.lambda$showRegularWorkAndRestFailDialog$12(SleepManagementCallback.this, view);
            }
        }).dxs_(regularWorkAndRestTimeView).dxw_(new View.OnClickListener() { // from class: pnu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepManagementJsUtil.lambda$showRegularWorkAndRestFailDialog$13(i, view);
            }
        }).dxr_(new View.OnClickListener() { // from class: pnq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepManagementJsUtil.lambda$showRegularWorkAndRestFailDialog$14(wa_, view);
            }
        }).b().show();
    }

    public static /* synthetic */ void lambda$showRegularWorkAndRestFailDialog$12(SleepManagementCallback sleepManagementCallback, View view) {
        sleepManagementCallback.onSuccess(0);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void lambda$showRegularWorkAndRestFailDialog$13(int i, View view) {
        dsl.ZN_(101, i, pqg.dsa_());
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void lambda$showRegularWorkAndRestFailDialog$14(Activity activity, View view) {
        dsl.a(101, activity);
        ViewClickInstrumentation.clickOnView(view);
    }

    @H5ProMethod(name = "showRegularWakeupSuccessDialog")
    public static void showRegularWakeupSuccessDialog(final int i, final SleepManagementCallback<Integer> sleepManagementCallback) {
        final Activity wa_ = BaseApplication.wa_();
        if (wa_ == null) {
            LogUtil.b(TAG, "top activity is null");
            return;
        }
        Context e = BaseApplication.e();
        new SleepTaskDialog.Builder(wa_).a(pqg.e()).b(e.getString(R$string.IDS_hw_sport_result_congratulations)).c(e.getString(R$string.IDS_sleep_complete_this_month, e.getResources().getQuantityString(R.plurals.IDS_user_profile_achieve_num_day, i, Integer.valueOf(i)))).dxt_(e.getString(R$string.IDS_device_know), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementJsUtil.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SleepManagementCallback.this.onSuccess(0);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).dxw_(new View.OnClickListener() { // from class: pnr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepManagementJsUtil.lambda$showRegularWakeupSuccessDialog$15(i, view);
            }
        }).dxr_(new View.OnClickListener() { // from class: pms
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepManagementJsUtil.lambda$showRegularWakeupSuccessDialog$16(wa_, view);
            }
        }).b().show();
    }

    public static /* synthetic */ void lambda$showRegularWakeupSuccessDialog$15(int i, View view) {
        dsl.ZN_(6, i, pqg.drZ_());
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void lambda$showRegularWakeupSuccessDialog$16(Activity activity, View view) {
        dsl.a(6, activity);
        ViewClickInstrumentation.clickOnView(view);
    }

    @H5ProMethod(name = "showRegularWakeupFailDialog")
    public static void showRegularWakeupFailDialog(final int i, final SleepManagementCallback<Integer> sleepManagementCallback) {
        final Activity wa_ = BaseApplication.wa_();
        if (wa_ == null) {
            LogUtil.b(TAG, "top activity is null");
        } else {
            Context e = BaseApplication.e();
            new SleepTaskDialog.Builder(wa_).a(pqg.e()).b(e.getString(R$string.IDS_fitness_comeon)).c(e.getString(R$string.IDS_getup_regularly_failed_content)).dxt_(e.getString(R$string.IDS_device_know), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementJsUtil.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SleepManagementCallback.this.onSuccess(0);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).dxw_(new View.OnClickListener() { // from class: pnk
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SleepManagementJsUtil.lambda$showRegularWakeupFailDialog$17(i, view);
                }
            }).dxr_(new View.OnClickListener() { // from class: pnp
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SleepManagementJsUtil.lambda$showRegularWakeupFailDialog$18(wa_, view);
                }
            }).b().show();
        }
    }

    public static /* synthetic */ void lambda$showRegularWakeupFailDialog$17(int i, View view) {
        dsl.ZN_(6, i, pqg.drZ_());
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void lambda$showRegularWakeupFailDialog$18(Activity activity, View view) {
        dsl.a(6, activity);
        ViewClickInstrumentation.clickOnView(view);
    }

    @H5ProMethod(name = "showRestrictNapsSuccessDialog")
    public static void showRestrictNapsSuccessDialog(final int i, final SleepManagementCallback<Integer> sleepManagementCallback) {
        final Activity wa_ = BaseApplication.wa_();
        if (wa_ == null) {
            LogUtil.b(TAG, "top activity is null");
            return;
        }
        Context e = BaseApplication.e();
        new SleepTaskDialog.Builder(wa_).a(pqg.g()).b(e.getString(R$string.IDS_hw_sport_result_congratulations)).c(e.getString(R$string.IDS_sleep_complete_this_month, e.getResources().getQuantityString(R.plurals.IDS_user_profile_achieve_num_day, i, Integer.valueOf(i)))).dxt_(e.getString(R$string.IDS_device_know), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementJsUtil.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SleepManagementCallback.this.onSuccess(0);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).dxw_(new View.OnClickListener() { // from class: pmu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepManagementJsUtil.lambda$showRestrictNapsSuccessDialog$19(i, view);
            }
        }).dxr_(new View.OnClickListener() { // from class: pmw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepManagementJsUtil.lambda$showRestrictNapsSuccessDialog$20(wa_, view);
            }
        }).b().show();
    }

    public static /* synthetic */ void lambda$showRestrictNapsSuccessDialog$19(int i, View view) {
        dsl.ZN_(100, i, pqg.dsb_());
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void lambda$showRestrictNapsSuccessDialog$20(Activity activity, View view) {
        dsl.a(100, activity);
        ViewClickInstrumentation.clickOnView(view);
    }

    @H5ProMethod(name = "showRestrictNapsFailDialog")
    public static void showRestrictNapsFailDialog(final int i, final SleepManagementCallback<Integer> sleepManagementCallback) {
        final Activity wa_ = BaseApplication.wa_();
        if (wa_ == null) {
            LogUtil.b(TAG, "top activity is null");
            return;
        }
        Context e = BaseApplication.e();
        new SleepTaskDialog.Builder(wa_).a(pqg.g()).b(e.getString(R$string.IDS_fitness_comeon)).c(e.getString(R$string.IDS_restrict_naps_fail_task, UnitUtil.e(30.0d, 1, 0))).dxt_(e.getString(R$string.IDS_device_know), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementJsUtil.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SleepManagementCallback.this.onSuccess(0);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).dxw_(new View.OnClickListener() { // from class: pnx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepManagementJsUtil.lambda$showRestrictNapsFailDialog$21(i, view);
            }
        }).dxr_(new View.OnClickListener() { // from class: pnw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepManagementJsUtil.lambda$showRestrictNapsFailDialog$22(wa_, view);
            }
        }).b().show();
    }

    public static /* synthetic */ void lambda$showRestrictNapsFailDialog$21(int i, View view) {
        dsl.ZN_(100, i, pqg.dsb_());
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void lambda$showRestrictNapsFailDialog$22(Activity activity, View view) {
        dsl.a(100, activity);
        ViewClickInstrumentation.clickOnView(view);
    }

    @H5ProMethod(name = "showRestrictNapsHasDataDialog")
    public static void showRestrictNapsHasDataDialog(int i, final int i2, final SleepManagementCallback<Integer> sleepManagementCallback) {
        final Activity wa_ = BaseApplication.wa_();
        if (wa_ == null) {
            LogUtil.b(TAG, "top activity is null");
            return;
        }
        Context e = BaseApplication.e();
        View inflate = LayoutInflater.from(wa_).inflate(R.layout.restrict_naps_custom_view_layout, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.restrict_naps_description_text);
        String e2 = UnitUtil.e(30.0d, 1, 0);
        textView.setText(e.getResources().getString(R$string.IDS_sleep_limit, e2));
        RestrictNapsProgressBar restrictNapsProgressBar = (RestrictNapsProgressBar) inflate.findViewById(R.id.restrict_naps_progress_bar);
        String quantityString = e.getResources().getQuantityString(R.plurals.IDS_user_profile_achieve_num_day, i2, Integer.valueOf(i2));
        restrictNapsProgressBar.setProgress((int) ((i / 30.0d) * 100.0d));
        restrictNapsProgressBar.setTile(e.getResources().getQuantityString(R.plurals._2130903305_res_0x7f030109, i, Integer.valueOf(i)));
        new SleepTaskDialog.Builder(wa_).b(e.getString(R$string.IDS_restrict_naps)).c(e.getString(R$string.IDS_sleep_complete_this_month, quantityString)).a(e.getString(R$string.IDS_restrict_naps_no_data_new, e2, e2)).a(pqg.g()).dxt_(e.getString(R$string.IDS_device_know), new View.OnClickListener() { // from class: pnl
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepManagementJsUtil.lambda$showRestrictNapsHasDataDialog$23(SleepManagementCallback.this, view);
            }
        }).dxw_(new View.OnClickListener() { // from class: pnt
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepManagementJsUtil.lambda$showRestrictNapsHasDataDialog$24(i2, view);
            }
        }).dxs_(inflate).dxr_(new View.OnClickListener() { // from class: pns
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepManagementJsUtil.lambda$showRestrictNapsHasDataDialog$25(wa_, view);
            }
        }).b().show();
    }

    public static /* synthetic */ void lambda$showRestrictNapsHasDataDialog$23(SleepManagementCallback sleepManagementCallback, View view) {
        sleepManagementCallback.onSuccess(0);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void lambda$showRestrictNapsHasDataDialog$24(int i, View view) {
        dsl.ZN_(100, i, pqg.dsb_());
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void lambda$showRestrictNapsHasDataDialog$25(Activity activity, View view) {
        dsl.a(100, activity);
        ViewClickInstrumentation.clickOnView(view);
    }

    @H5ProMethod(name = "showRestrictNapsNoDataDialog")
    public static void showRestrictNapsNoDataDialog(final int i, final SleepManagementCallback<Integer> sleepManagementCallback) {
        final Activity wa_ = BaseApplication.wa_();
        if (wa_ == null) {
            LogUtil.b(TAG, "top activity is null");
            return;
        }
        Context e = BaseApplication.e();
        View inflate = LayoutInflater.from(wa_).inflate(R.layout.restrict_30_min_layout, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.restrict_naps_description_text);
        String e2 = UnitUtil.e(30.0d, 1, 0);
        textView.setText(e.getResources().getString(R$string.IDS_sleep_limit, e2));
        new SleepTaskDialog.Builder(wa_).b(e.getString(R$string.IDS_restrict_naps)).c(e.getString(R$string.IDS_sleep_complete_this_month, e.getResources().getQuantityString(R.plurals.IDS_user_profile_achieve_num_day, i, Integer.valueOf(i)))).a(e.getString(R$string.IDS_restrict_naps_no_data_new, e2, e2)).a(pqg.g()).dxv_(e.getString(R$string.IDS_sleep_within_minutes, e2), new View.OnClickListener() { // from class: pnc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepManagementJsUtil.lambda$showRestrictNapsNoDataDialog$26(SleepManagementCallback.this, view);
            }
        }).dxu_(e.getString(R$string.IDS_sleep_more_than_minutes, e2), new View.OnClickListener() { // from class: pnb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepManagementJsUtil.lambda$showRestrictNapsNoDataDialog$27(SleepManagementCallback.this, view);
            }
        }).dxw_(new View.OnClickListener() { // from class: pnf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepManagementJsUtil.lambda$showRestrictNapsNoDataDialog$28(i, view);
            }
        }).dxs_(inflate).dxr_(new View.OnClickListener() { // from class: pnd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepManagementJsUtil.lambda$showRestrictNapsNoDataDialog$29(wa_, view);
            }
        }).b().show();
    }

    public static /* synthetic */ void lambda$showRestrictNapsNoDataDialog$26(SleepManagementCallback sleepManagementCallback, View view) {
        sleepManagementCallback.onSuccess(0);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void lambda$showRestrictNapsNoDataDialog$27(SleepManagementCallback sleepManagementCallback, View view) {
        sleepManagementCallback.onSuccess(1);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void lambda$showRestrictNapsNoDataDialog$28(int i, View view) {
        dsl.ZN_(100, i, pqg.dsb_());
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void lambda$showRestrictNapsNoDataDialog$29(Activity activity, View view) {
        dsl.a(100, activity);
        ViewClickInstrumentation.clickOnView(view);
    }

    @H5ProMethod(name = "showRestrictNapsExpiredDialog")
    public static void showRestrictNapsExpiredDialog(final int i, final SleepManagementCallback<Integer> sleepManagementCallback) {
        final Activity wa_ = BaseApplication.wa_();
        if (wa_ == null) {
            LogUtil.b(TAG, "top activity is null");
        } else {
            Context e = BaseApplication.e();
            new SleepTaskDialog.Builder(wa_).a(pqg.g()).b(e.getString(R$string.IDS_fitness_comeon)).c(e.getString(R$string.IDS_health_model_wake_up_late)).dxt_(e.getString(R$string.IDS_device_know), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementJsUtil.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SleepManagementCallback.this.onSuccess(0);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).dxr_(new View.OnClickListener() { // from class: pna
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SleepManagementJsUtil.lambda$showRestrictNapsExpiredDialog$30(wa_, view);
                }
            }).dxw_(new View.OnClickListener() { // from class: pmx
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SleepManagementJsUtil.lambda$showRestrictNapsExpiredDialog$31(i, view);
                }
            }).b().show();
        }
    }

    public static /* synthetic */ void lambda$showRestrictNapsExpiredDialog$30(Activity activity, View view) {
        dsl.a(100, activity);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void lambda$showRestrictNapsExpiredDialog$31(int i, View view) {
        dsl.ZN_(100, i, pqg.drZ_());
        ViewClickInstrumentation.clickOnView(view);
    }
}
