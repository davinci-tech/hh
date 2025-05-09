package com.huawei.operation.h5pro.jsmodules;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import androidx.core.app.NotificationCompat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.h5pro.core.H5ProBundle;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.beans.SwitchTimingInfo;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginhealthalgorithm.MenstrualPredictApi;
import defpackage.cun;
import defpackage.cvo;
import defpackage.cvv;
import defpackage.cvx;
import defpackage.cwi;
import defpackage.koq;
import defpackage.mpf;
import defpackage.nhu;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class MenstrualModule extends JsBaseModule {
    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule
    public void onCreate(H5ProBundle h5ProBundle) {
        super.onCreate(h5ProBundle);
        if (!mpf.e().isPluginAvaiable()) {
            mpf.e().loadPlugin(null);
        }
        LogUtil.a(this.TAG, "onCreate");
    }

    @JavascriptInterface
    public void predict(long j, String str) {
        LogUtil.a(this.TAG, "predict json:", str);
        String predict = mpf.e().predict(str);
        if (TextUtils.isEmpty(predict)) {
            onFailureCallback(j, "predict result==null");
        } else {
            onSuccessCallback(j, predict);
        }
    }

    @JavascriptInterface
    public void editPredict(long j, String str) {
        LogUtil.a(this.TAG, "editPredict json:", str);
        String editPredict = mpf.e().editPredict(str);
        if (TextUtils.isEmpty(editPredict)) {
            onFailureCallback(j, "editPredict result==null");
        } else {
            onSuccessCallback(j, editPredict);
        }
    }

    @JavascriptInterface
    public void setReminder(long j, String str) {
        LogUtil.a(this.TAG, "setReminder, json = ", str);
        if (TextUtils.isEmpty(str)) {
            onFailureCallback(j, "setReminder, json is empty.");
        } else {
            handleAlarmManager(j, (List) new Gson().fromJson(str, new TypeToken<List<SwitchTimingInfo>>() { // from class: com.huawei.operation.h5pro.jsmodules.MenstrualModule.1
            }.getType()));
        }
    }

    private void handleAlarmManager(long j, List<SwitchTimingInfo> list) {
        if (koq.b(list)) {
            LogUtil.h(this.TAG, "switchTimingInfos is empty.");
            onFailureCallback(j, "switchTimingInfos is empty.");
            return;
        }
        boolean z = true;
        for (SwitchTimingInfo switchTimingInfo : list) {
            if (switchTimingInfo == null) {
                LogUtil.h(this.TAG, "switchTiming is null.");
            } else {
                List<SwitchTimingInfo.TimingInfo> timingInfo = switchTimingInfo.getTimingInfo();
                if (koq.b(timingInfo)) {
                    LogUtil.h(this.TAG, "timingInfoList is empty.");
                } else {
                    for (SwitchTimingInfo.TimingInfo timingInfo2 : timingInfo) {
                        if (timingInfo2 == null || TextUtils.isEmpty(timingInfo2.getDate())) {
                            LogUtil.h(this.TAG, "switchName = ", switchTimingInfo.getName(), ", timingInfo is null or getDate() is empty.");
                        } else if (!setAlarmManager(this.mContext, switchTimingInfo.getName(), timingInfo2.getCode(), timingInfo2.getDate())) {
                            z = false;
                        }
                    }
                }
            }
        }
        if (z) {
            onSuccessCallback(j, "setAlarmManager success");
        } else {
            onFailureCallback(j, "setAlarmManager failed");
        }
    }

    @JavascriptInterface
    public void getSwitchState(final long j, String str) {
        LogUtil.a(this.TAG, "getSwitchState switchKey: ", str);
        ((MenstrualPredictApi) Services.c("MenstrualPredict", MenstrualPredictApi.class)).getSwitchState(str, new IBaseResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.MenstrualModule$$ExternalSyntheticLambda1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                MenstrualModule.this.m725x545474a9(j, i, obj);
            }
        });
    }

    /* renamed from: lambda$getSwitchState$0$com-huawei-operation-h5pro-jsmodules-MenstrualModule, reason: not valid java name */
    /* synthetic */ void m725x545474a9(long j, int i, Object obj) {
        if (i == 0 && (obj instanceof Boolean)) {
            LogUtil.a(this.TAG, "getSwitchState success: ", obj);
            onSuccessCallback(j, obj);
        } else {
            onFailureCallback(j, "getSwitchState fail");
        }
    }

    @JavascriptInterface
    public void updateSwitchState(final long j, String str) {
        LogUtil.a(this.TAG, "updateSwitchState params: ", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("switchKey");
            String optString2 = jSONObject.optString("isChecked");
            if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                onFailureCallback(j, "updateSwitchState switchKey or isChecked is null");
            } else {
                ((MenstrualPredictApi) Services.c("MenstrualPredict", MenstrualPredictApi.class)).updateSwitchState(optString, optString2, new IBaseResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.MenstrualModule$$ExternalSyntheticLambda0
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        MenstrualModule.this.m726xa9e02355(j, i, obj);
                    }
                });
            }
        } catch (JSONException unused) {
            onFailureCallback(j, "updateSwitchState parse params exception");
        }
    }

    /* renamed from: lambda$updateSwitchState$1$com-huawei-operation-h5pro-jsmodules-MenstrualModule, reason: not valid java name */
    /* synthetic */ void m726xa9e02355(long j, int i, Object obj) {
        if (i == 0) {
            LogUtil.a(this.TAG, "updateSwitchState success");
            onSuccessCallback(j, "updateSwitchState success");
        } else {
            onFailureCallback(j, "updateSwitchState fail");
        }
    }

    private boolean setAlarmManager(Context context, String str, int i, String str2) {
        Intent intent = new Intent();
        intent.setClassName(context, "com.huawei.health.receiver.PhysiologicalCycleReceiver");
        intent.putExtra("switchType", str);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, i, intent, 201326592);
        Object systemService = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (!(systemService instanceof AlarmManager)) {
            return false;
        }
        ((AlarmManager) systemService).set(0, CommonUtil.g(str2), broadcast);
        return true;
    }

    @JavascriptInterface
    public void cancelSwitchReminder(long j, String str) {
        LogUtil.a(this.TAG, "cancelSwitchReminder, json = ", str);
        if (TextUtils.isEmpty(str)) {
            onFailureCallback(j, "cancelSwitchReminder, json is empty.");
            return;
        }
        List list = (List) new Gson().fromJson(str, new TypeToken<List<Integer>>() { // from class: com.huawei.operation.h5pro.jsmodules.MenstrualModule.2
        }.getType());
        if (koq.b(list)) {
            onFailureCallback(j, "cancelSwitchReminder, getCodeList is empty.");
            return;
        }
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += cancelAlarmManager(this.mContext, ((Integer) it.next()).intValue());
        }
        if (i == list.size()) {
            onSuccessCallback(j, "cancelAlarmManager success.");
            return;
        }
        onFailureCallback(j, "cancelSwitchReminder, totalCount = " + list.size() + ", cancelCount = " + i);
    }

    private int cancelAlarmManager(Context context, int i) {
        Intent intent = new Intent();
        intent.setClassName(context, "com.huawei.health.receiver.PhysiologicalCycleReceiver");
        PendingIntent broadcast = PendingIntent.getBroadcast(this.mContext, i, intent, 201326592);
        Object systemService = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (!(systemService instanceof AlarmManager)) {
            return 0;
        }
        AlarmManager alarmManager = (AlarmManager) systemService;
        if (broadcast == null) {
            return 0;
        }
        broadcast.cancel();
        alarmManager.cancel(broadcast);
        return 1;
    }

    @JavascriptInterface
    public void syncMenstrualDataToDevice(long j, String str) {
        LogUtil.a(this.TAG, "enter syncMenstrualDataToDevice");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(this.TAG, "json isEmpty");
            onFailureCallback(j, "json isEmpty");
            return;
        }
        if (!cwi.c(cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, this.TAG), 174)) {
            LogUtil.h(this.TAG, "isSupportMenstrualSymptomMarker == false");
            onFailureCallback(j, "device not support");
            return;
        }
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONArray("pointInfos");
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                int i2 = jSONObject.getInt("type");
                int i3 = jSONObject.getInt("value");
                cvv cvvVar = new cvv();
                cvvVar.d(i2);
                cvvVar.b(cvx.g(i3));
                arrayList.add(cvvVar);
                long optLong = jSONObject.optLong("startTime");
                long optLong2 = jSONObject.optLong("endTime");
                long optLong3 = jSONObject.optLong(ParsedFieldTag.TASK_MODIFY_TIME);
                cvo cvoVar = new cvo();
                cvoVar.a(optLong);
                cvoVar.c(optLong2);
                cvoVar.b(optLong3);
                cvoVar.a(arrayList);
                arrayList2.add(cvoVar);
            }
            onSuccessCallback(j, Boolean.valueOf(nhu.e().syncMenstrualToDevice(arrayList2)));
        } catch (JSONException unused) {
            LogUtil.b(this.TAG, "syncMenstrualDataToDevice jsonException");
            onFailureCallback(j, "jsonException");
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a(this.TAG, "onDestroy");
    }
}
