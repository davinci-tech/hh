package com.huawei.operation.operationactivity;

import android.text.TextUtils;
import com.huawei.health.marketing.request.ActivityInfoListFactory;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;
import defpackage.koq;
import defpackage.kvm;
import defpackage.kvo;
import defpackage.kvp;
import defpackage.lqi;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class OperationActivityPuller {
    private static final int HAS_NO_MORE_DATA = 0;
    private static final int MAX_REQUEST_NUMBER = 20;
    private static final int NEXT_PAGE = 1;
    private static final String TAG = "OperationActivity_OperationActivityPuller";
    private static String sGetActivitiesUrl;

    private OperationActivityPuller() {
    }

    public static void getOperationActivity(final kvo kvoVar, final OperationActivityCallback operationActivityCallback) {
        LogUtil.a(TAG, "getOperationActivity() enter");
        GRSManager.a(BaseApplication.getContext()).e("activityUrl", new GrsQueryCallback() { // from class: com.huawei.operation.operationactivity.OperationActivityPuller.1
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                OperationActivityPuller.onResponseResult(str, kvo.this, operationActivityCallback);
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.h(OperationActivityPuller.TAG, "GRSManager onCallBackFail ACTIVITY_KEY failCode = ", Integer.valueOf(i));
                OperationActivityCallback operationActivityCallback2 = operationActivityCallback;
                if (operationActivityCallback2 != null) {
                    operationActivityCallback2.onResponse(i, null);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void onResponseResult(String str, kvo kvoVar, OperationActivityCallback operationActivityCallback) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "GRSManager onResponseResult ACTIVITY_KEY url is empty.");
            if (operationActivityCallback != null) {
                operationActivityCallback.onResponse(-1, null);
                return;
            }
            return;
        }
        sGetActivitiesUrl = str + "/activity/getActivities";
        ArrayList arrayList = new ArrayList(10);
        int i = 0;
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        do {
            kvp pullOperationActivity = pullOperationActivity(kvoVar, i);
            i2++;
            if (pullOperationActivity != null && String.valueOf(0).equals(pullOperationActivity.c())) {
                i = pullOperationActivity.e() + 1;
                i3 = pullOperationActivity.d();
                LogUtil.a(TAG, "onCallBackSuccess hasMore = ", Integer.valueOf(i3), ", requestNumber = ", Integer.valueOf(i2));
                List<kvm> b = pullOperationActivity.b();
                if (koq.c(b)) {
                    arrayList.addAll(b);
                }
            } else {
                LogUtil.h(TAG, "response is null");
                z = true;
            }
            if (i3 <= 0 || z) {
                break;
            }
        } while (i2 < 20);
        if (z) {
            LogUtil.h(TAG, "isRequestProceed is true");
            if (operationActivityCallback != null) {
                operationActivityCallback.onResponse(1008, null);
                return;
            }
            return;
        }
        if (operationActivityCallback != null) {
            operationActivityCallback.onResponse(0, arrayList);
            LogUtil.a(TAG, "listSize = ", Integer.valueOf(arrayList.size()));
        }
    }

    private static kvp pullOperationActivity(kvo kvoVar, int i) {
        JSONObject operationActivityParams = getOperationActivityParams(kvoVar, i);
        String str = (String) lqi.d().d(sGetActivitiesUrl, new HealthDataCloudFactory(BaseApplication.getContext()).getHeaders(), operationActivityParams.toString(), String.class);
        LogUtil.c(TAG, "jsonResult = ", str);
        return OperationActivityParser.analyzeOperationActivityResponse(str);
    }

    private static JSONObject getOperationActivityParams(kvo kvoVar, int i) {
        JSONObject commonRequestParams = getCommonRequestParams();
        try {
            commonRequestParams.put("pageNo", i);
            if (kvoVar != null) {
                List<Integer> e = kvoVar.e();
                if (koq.c(e)) {
                    JSONArray jSONArray = new JSONArray();
                    Iterator<Integer> it = e.iterator();
                    while (it.hasNext()) {
                        jSONArray.put(it.next().intValue());
                    }
                    commonRequestParams.put("activityTypeList", jSONArray);
                }
                commonRequestParams.put("joinStatus", kvoVar.c());
                int a2 = kvoVar.a();
                if (a2 >= 0 && a2 <= 3) {
                    commonRequestParams.put("finishFlag", a2);
                }
            }
            commonRequestParams.put(IAchieveDBMgr.PARAM_PAGE_SIZE, 20);
        } catch (JSONException e2) {
            LogUtil.b(TAG, "getOperationActivityParams exception = ", e2.getMessage());
        }
        if (!CommonUtil.as() && !CommonUtil.cc()) {
            commonRequestParams.put("isBeta", 0);
            return commonRequestParams;
        }
        commonRequestParams.put("isBeta", 1);
        return commonRequestParams;
    }

    private static JSONObject getCommonRequestParams() {
        return new JSONObject(new ActivityInfoListFactory(BaseApplication.getContext()).getBody(null));
    }
}
