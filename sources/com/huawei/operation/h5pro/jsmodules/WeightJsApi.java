package com.huawei.operation.h5pro.jsmodules;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.facardagds.FaCardAgdsApi;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.weight.bean.WeightTargetDifferences;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.dlf;
import defpackage.grz;
import defpackage.gsd;
import defpackage.gsi;
import defpackage.nrh;
import defpackage.sqp;
import health.compact.a.CommonUtils;
import health.compact.a.LocalBroadcast;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class WeightJsApi extends JsBaseModule {
    private static final int AGDS_REQUESET_CODE = 7667714;
    private int mAgdsBiFrom;
    private long mAgdsCallbackId;
    private String mFaCardModuleName = FaCardAgdsApi.DIET_FA_CARD;

    @JavascriptInterface
    public void initUser(final long j) {
        ReleaseLogUtil.b(this.TAG, "initUser callbackId ", Long.valueOf(j));
        grz.c(new IBaseResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.WeightJsApi$$ExternalSyntheticLambda0
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                WeightJsApi.this.m735x3c602bd(j, i, obj);
            }
        });
    }

    /* renamed from: lambda$initUser$0$com-huawei-operation-h5pro-jsmodules-WeightJsApi, reason: not valid java name */
    /* synthetic */ void m735x3c602bd(long j, int i, Object obj) {
        onSuccessCallback(j);
    }

    @JavascriptInterface
    public void setCurrentUser(String str) {
        ReleaseLogUtil.b(this.TAG, "setCurrentUser userId ", str);
        grz.h(str);
    }

    @JavascriptInterface
    public void getChildHeightUpdateTime(long j) {
        String d = grz.d();
        ReleaseLogUtil.b(this.TAG, "getChildHeightUpdateTime timeMillis ", d, " callbackId ", Long.valueOf(j));
        onSuccessCallback(j, d);
    }

    @JavascriptInterface
    public void saveChildHeightUpdateTime() {
        ReleaseLogUtil.b(this.TAG, "saveChildHeightUpdateTime");
        grz.b();
    }

    @JavascriptInterface
    public void isSupportAiBodyShape(long j) {
        boolean e = grz.e();
        ReleaseLogUtil.b(this.TAG, "isSupportAiBodyShape isSupport ", Boolean.valueOf(e), " callbackId ", Long.valueOf(j));
        onSuccessCallback(j, Boolean.valueOf(e));
    }

    @JavascriptInterface
    public void getPeerAnalysis(long j, String str) {
        LogUtil.a(this.TAG, "getPeerAnalysis param is ", str);
        if (str == null) {
            return;
        }
        onSuccessCallback(j, grz.e(str));
    }

    @JavascriptInterface
    public void getWeightUnit(long j) {
        LogUtil.a(this.TAG, "getWeightUnit start");
        onSuccessCallback(j, Integer.valueOf(UnitUtil.a()));
    }

    @JavascriptInterface
    public void getWeightPageTimeRange(long j, String str) {
        ReleaseLogUtil.b(this.TAG, "getCurrentTimeRange ,callbackId ", Long.valueOf(j), " param ", str);
        if (str == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(TypedValues.Custom.S_DIMENSION)) {
                ObserverManagerUtil.c("WEIGHT_DATA_CHANGE_TIME_TO_H5", Integer.valueOf(jSONObject.getInt(TypedValues.Custom.S_DIMENSION)));
            }
        } catch (JSONException unused) {
            ReleaseLogUtil.c(this.TAG, "getWeightPageTimeRange JSONException");
        }
    }

    @JavascriptInterface
    public void getTrunkFatRateGrades(long j, String str) {
        LogUtil.a(this.TAG, "getTrunkFatRateGrades callbackId is ", Long.valueOf(j), " param ", str);
        if (str == null) {
            return;
        }
        String b = grz.b(str);
        LogUtil.a(this.TAG, "getTrunkFatRateGrades grades is ", b);
        onSuccessCallback(j, b);
    }

    private void dialogCallback(long j, int i, int i2) {
        HashMap hashMap = new HashMap(1);
        hashMap.put("state", Integer.valueOf(i2));
        if (i != 0) {
            onFailureCallback(j, HiJsonUtil.e(hashMap), i);
        } else {
            if (i2 == 0) {
                return;
            }
            onSuccessCallback(j, hashMap);
        }
    }

    @JavascriptInterface
    public void showUserInfoDialog(final long j) {
        boolean m = gsd.m();
        ReleaseLogUtil.b(this.TAG, "showUserInfoDialog callbackId ", Long.valueOf(j), " mContext ", this.mContext, " isSupportShowUserInfoDialog ", Boolean.valueOf(m));
        if (!m) {
            dialogCallback(j, -1, -1);
        } else {
            grz.d(this.mContext, (ResponseCallback<Integer>) new ResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.WeightJsApi$$ExternalSyntheticLambda2
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    WeightJsApi.this.m743xd421af19(j, i, (Integer) obj);
                }
            });
        }
    }

    /* renamed from: lambda$showUserInfoDialog$1$com-huawei-operation-h5pro-jsmodules-WeightJsApi, reason: not valid java name */
    /* synthetic */ void m743xd421af19(long j, int i, Integer num) {
        ReleaseLogUtil.b(this.TAG, "showUserInfoDialog resultCode ", Integer.valueOf(i), " state ", num);
        dialogCallback(j, i, num.intValue());
    }

    @JavascriptInterface
    public void showInputDialog(final long j) {
        ReleaseLogUtil.b(this.TAG, "showInputDialog callbackId ", Long.valueOf(j), " mContext ", this.mContext);
        grz.a(this.mContext, (ResponseCallback<Integer>) new ResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.WeightJsApi$$ExternalSyntheticLambda9
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                WeightJsApi.this.m741x2bc9709b(j, i, (Integer) obj);
            }
        });
    }

    /* renamed from: lambda$showInputDialog$2$com-huawei-operation-h5pro-jsmodules-WeightJsApi, reason: not valid java name */
    /* synthetic */ void m741x2bc9709b(long j, int i, Integer num) {
        ReleaseLogUtil.b(this.TAG, "showInputDialog resultCode ", Integer.valueOf(i), " state ", num);
        dialogCallback(j, i, num.intValue());
    }

    @JavascriptInterface
    public void showAutoUpdateWeightDialog(final long j) {
        ReleaseLogUtil.b(this.TAG, "showAutoUpdateWeightDialog callbackId ", Long.valueOf(j), " mContext ", this.mContext);
        grz.c(this.mContext, (ResponseCallback<Integer>) new ResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.WeightJsApi$$ExternalSyntheticLambda6
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                WeightJsApi.this.m738xb8987860(j, i, (Integer) obj);
            }
        });
    }

    /* renamed from: lambda$showAutoUpdateWeightDialog$3$com-huawei-operation-h5pro-jsmodules-WeightJsApi, reason: not valid java name */
    /* synthetic */ void m738xb8987860(long j, int i, Integer num) {
        ReleaseLogUtil.b(this.TAG, "showAutoUpdateWeightDialog resultCode ", Integer.valueOf(i), " state ", num);
        dialogCallback(j, i, num.intValue());
    }

    @JavascriptInterface
    public void showSetGoalDialog(final long j) {
        ReleaseLogUtil.b(this.TAG, "showSetGoalDialog callbackId ", Long.valueOf(j), " mContext ", this.mContext);
        grz.e(this.mContext, (ResponseCallback<Integer>) new ResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.WeightJsApi$$ExternalSyntheticLambda4
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                WeightJsApi.this.m742xb5c5cdee(j, i, (Integer) obj);
            }
        });
    }

    /* renamed from: lambda$showSetGoalDialog$4$com-huawei-operation-h5pro-jsmodules-WeightJsApi, reason: not valid java name */
    /* synthetic */ void m742xb5c5cdee(long j, int i, Integer num) {
        ReleaseLogUtil.b(this.TAG, "showSetGoalDialog resultCode ", Integer.valueOf(i), " state ", num);
        dialogCallback(j, i, num.intValue());
    }

    @JavascriptInterface
    public void showBindDeviceDialog(final long j) {
        ReleaseLogUtil.b(this.TAG, "showBindDeviceDialog callbackId ", Long.valueOf(j), " mContext ", this.mContext);
        grz.b(this.mContext, (ResponseCallback<Integer>) new ResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.WeightJsApi$$ExternalSyntheticLambda10
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                WeightJsApi.this.m739xe671717b(j, i, (Integer) obj);
            }
        });
    }

    /* renamed from: lambda$showBindDeviceDialog$5$com-huawei-operation-h5pro-jsmodules-WeightJsApi, reason: not valid java name */
    /* synthetic */ void m739xe671717b(long j, int i, Integer num) {
        ReleaseLogUtil.b(this.TAG, "showBindDeviceDialog resultCode ", Integer.valueOf(i), " state ", num);
        dialogCallback(j, i, num.intValue());
    }

    @JavascriptInterface
    public void showUserInfoDialogForMeasureStart(final long j) {
        ReleaseLogUtil.b(this.TAG, "showUserInfoDialogForMeasureStart callbackId ", Long.valueOf(j), " mContext ", this.mContext);
        grz.g(this.mContext, new ResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.WeightJsApi$$ExternalSyntheticLambda12
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                WeightJsApi.this.m745x836cb4fd(j, i, (Integer) obj);
            }
        });
    }

    /* renamed from: lambda$showUserInfoDialogForMeasureStart$6$com-huawei-operation-h5pro-jsmodules-WeightJsApi, reason: not valid java name */
    /* synthetic */ void m745x836cb4fd(long j, int i, Integer num) {
        ReleaseLogUtil.b(this.TAG, "showUserInfoDialogForMeasureStart resultCode ", Integer.valueOf(i), " state ", num);
        dialogCallback(j, i, num.intValue());
    }

    @JavascriptInterface
    public void showUserInfoDialogForMeasureFinish(final long j) {
        ReleaseLogUtil.b(this.TAG, "showUserInfoDialogForMeasureFinish callbackId ", Long.valueOf(j), " mContext ", this.mContext);
        grz.f(this.mContext, new ResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.WeightJsApi$$ExternalSyntheticLambda7
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                WeightJsApi.this.m744xb8f2ab6b(j, i, (Integer) obj);
            }
        });
    }

    /* renamed from: lambda$showUserInfoDialogForMeasureFinish$7$com-huawei-operation-h5pro-jsmodules-WeightJsApi, reason: not valid java name */
    /* synthetic */ void m744xb8f2ab6b(long j, int i, Integer num) {
        ReleaseLogUtil.b(this.TAG, "showUserInfoDialogForMeasureFinish resultCode ", Integer.valueOf(i), " state ", num);
        dialogCallback(j, i, num.intValue());
    }

    @JavascriptInterface
    public void showGoalMotivationDialog(final long j, String str) {
        JSONObject jSONObject;
        LogUtil.a(this.TAG, "showGoalMotivationDialog callbackId ", Long.valueOf(j), " param ", str, " mContext ", this.mContext);
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            ReleaseLogUtil.c(this.TAG, "showGoalMotivationDialog exception ", ExceptionUtils.d(e));
            jSONObject = null;
        }
        if (jSONObject == null) {
            ReleaseLogUtil.a(this.TAG, "showGoalMotivationDialog jsonObject is null");
            return;
        }
        grz.a(this.mContext, new ResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.WeightJsApi$$ExternalSyntheticLambda5
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                WeightJsApi.this.m740x6c550882(j, i, (Integer) obj);
            }
        }, (float) jSONObject.optDouble("bodyWeightDiff"), jSONObject.optInt("progress"), jSONObject.optString("weightTargetDifferences"));
    }

    /* renamed from: lambda$showGoalMotivationDialog$8$com-huawei-operation-h5pro-jsmodules-WeightJsApi, reason: not valid java name */
    /* synthetic */ void m740x6c550882(long j, int i, Integer num) {
        ReleaseLogUtil.b(this.TAG, "showGoalMotivationDialog resultCode ", Integer.valueOf(i), " state ", num);
        dialogCallback(j, i, num.intValue());
    }

    @JavascriptInterface
    public void initGoalMotivationCache(final long j) {
        ReleaseLogUtil.b(this.TAG, "initGoalMotivationCache callbackId ", Long.valueOf(j), " mContext ", this.mContext);
        grz.c((ResponseCallback<WeightTargetDifferences>) new ResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.WeightJsApi$$ExternalSyntheticLambda1
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                WeightJsApi.this.m734x30ff90a0(j, i, (WeightTargetDifferences) obj);
            }
        });
    }

    /* renamed from: lambda$initGoalMotivationCache$9$com-huawei-operation-h5pro-jsmodules-WeightJsApi, reason: not valid java name */
    /* synthetic */ void m734x30ff90a0(long j, int i, WeightTargetDifferences weightTargetDifferences) {
        LogUtil.a(this.TAG, "initGoalMotivationCache onResponse differences ", weightTargetDifferences);
        onSuccessCallback(j, weightTargetDifferences);
    }

    @JavascriptInterface
    public void autoUpdateUserWeight(final long j, String str) {
        JSONObject jSONObject;
        LogUtil.a(this.TAG, "autoUpdateUserWeight callbackId ", Long.valueOf(j), " param ", str, " mContext ", this.mContext);
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            ReleaseLogUtil.c(this.TAG, "autoUpdateUserWeight exception ", ExceptionUtils.d(e));
            jSONObject = null;
        }
        if (jSONObject == null) {
            ReleaseLogUtil.a(this.TAG, "autoUpdateUserWeight jsonObject is null");
        } else {
            grz.e((float) jSONObject.optDouble("weight"), jSONObject.optLong("measureTime"), (ResponseCallback<Object>) new ResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.WeightJsApi$$ExternalSyntheticLambda8
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    WeightJsApi.this.m732xc86f3b76(j, i, obj);
                }
            });
        }
    }

    /* renamed from: lambda$autoUpdateUserWeight$10$com-huawei-operation-h5pro-jsmodules-WeightJsApi, reason: not valid java name */
    /* synthetic */ void m732xc86f3b76(long j, int i, Object obj) {
        LogUtil.a(this.TAG, "autoUpdateUserWeight status ", Integer.valueOf(i), " object ", obj);
        onSuccessCallback(j, obj);
    }

    @JavascriptInterface
    public void showBluetoothClaimDataDialog(long j) {
        ReleaseLogUtil.b(this.TAG, "showBluetoothClaimDataDialog callbackId ", Long.valueOf(j), " mContext ", this.mContext);
        grz.i();
        onSuccessCallback(j, "success");
    }

    @JavascriptInterface
    public void initCardRedDot() {
        ReleaseLogUtil.b(this.TAG, "initCardRedDot mContext ", this.mContext);
        grz.c();
    }

    @JavascriptInterface
    public void calculateGoal(final long j, String str) {
        LogUtil.a(this.TAG, "calculateGoal param ", str, " callbackId ", Long.valueOf(j));
        try {
            JSONObject jSONObject = new JSONObject(str);
            Bundle bundle = new Bundle();
            bundle.putInt("age", CommonUtils.h(jSONObject.optString("age")));
            bundle.putInt(CommonConstant.KEY_GENDER, CommonUtils.h(jSONObject.optString(CommonConstant.KEY_GENDER)));
            bundle.putInt("height", CommonUtils.h(jSONObject.optString("height")));
            bundle.putDouble("weight", CommonUtils.a(jSONObject.optString("weight")));
            bundle.putDouble("targetWeight", CommonUtils.a(jSONObject.optString("targetWeight")));
            bundle.putInt("fatBurnChoice", CommonUtils.h(jSONObject.optString("fatBurnChoice")));
            bundle.putInt("weightManagerType", CommonUtils.h(jSONObject.optString("weightManagerType")));
            bundle.putBoolean("targetSettingChanged", jSONObject.optBoolean("targetSettingChanged"));
            bundle.putLong("modifiedTime", System.currentTimeMillis());
            bundle.putBoolean("isRefreshInitWeight", true);
            grz.aSS_(bundle, new ResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.WeightJsApi$$ExternalSyntheticLambda14
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    WeightJsApi.this.m733x97b98ad8(j, i, (gsi) obj);
                }
            });
        } catch (JSONException e) {
            String d = ExceptionUtils.d(e);
            ReleaseLogUtil.c(this.TAG, "calculateGoal exception ", d);
            onFailureCallback(j, d);
        }
    }

    /* renamed from: lambda$calculateGoal$12$com-huawei-operation-h5pro-jsmodules-WeightJsApi, reason: not valid java name */
    /* synthetic */ void m733x97b98ad8(long j, int i, gsi gsiVar) {
        LogUtil.a(this.TAG, "calculateGoal onResponse errorCode ", Integer.valueOf(i), " weightManager ", gsiVar);
        if (i == 0) {
            onSuccessCallback(j, gsiVar);
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.operation.h5pro.jsmodules.WeightJsApi$$ExternalSyntheticLambda13
                @Override // java.lang.Runnable
                public final void run() {
                    sqp.d(System.currentTimeMillis());
                }
            });
        } else {
            onFailureCallback(j, HiJsonUtil.e(gsiVar));
        }
    }

    @JavascriptInterface
    public void openFaCardAgds(final long j, String str) {
        ReleaseLogUtil.b(this.TAG, "openFaCardAgds callbackId ", Long.valueOf(j), " param ", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String str2 = (String) jSONObject.get("faCardModuleName");
            this.mFaCardModuleName = str2;
            this.mAgdsBiFrom = ((Integer) jSONObject.get("agdsBiFrom")).intValue();
            ReleaseLogUtil.b(this.TAG, "openFaCardAgds faCardModuleName: ", str2, ", mAgdsBiFrom: ", Integer.valueOf(this.mAgdsBiFrom));
            this.mAgdsCallbackId = j;
            FaCardAgdsApi c = dlf.c();
            if (c == null) {
                ReleaseLogUtil.b(this.TAG, "openFaCardAgds faCardAgdsApi null:");
                onFailureCallback(j, "open faild");
            } else {
                c.open(AGDS_REQUESET_CODE, str2, new FaCardAgdsApi.OpenAgdsResultCallback() { // from class: com.huawei.operation.h5pro.jsmodules.WeightJsApi$$ExternalSyntheticLambda3
                    @Override // com.huawei.health.facardagds.FaCardAgdsApi.OpenAgdsResultCallback
                    public final void onResponse(int i) {
                        WeightJsApi.this.m737x3daa0a2(j, i);
                    }
                });
            }
        } catch (JSONException e) {
            ReleaseLogUtil.c(this.TAG, "openFaCardAgds exception ", ExceptionUtils.d(e));
        }
    }

    /* renamed from: lambda$openFaCardAgds$13$com-huawei-operation-h5pro-jsmodules-WeightJsApi, reason: not valid java name */
    /* synthetic */ void m737x3daa0a2(long j, int i) {
        ReleaseLogUtil.b(this.TAG, "openFaCardAgds errorCode:", Integer.valueOf(i));
        if (i != 0) {
            onFailureCallback(j, "open faild");
        }
    }

    @JavascriptInterface
    public void notifyCheckUpdate(long j, String str) {
        JSONObject jSONObject;
        LogUtil.a(this.TAG, "notifyCheckUpdate callbackId ", Long.valueOf(j), " param ", str, " mContext ", this.mContext);
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            ReleaseLogUtil.c(this.TAG, "notifyCheckUpdate exception ", ExceptionUtils.d(e));
            jSONObject = null;
        }
        if (jSONObject == null) {
            ReleaseLogUtil.a(this.TAG, "notifyCheckUpdate jsonObject is null");
            return;
        }
        String optString = jSONObject.optString("uniqueId");
        String optString2 = jSONObject.optString("productId");
        if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
            ReleaseLogUtil.a(this.TAG, "notifyCheckUpdate uniqueId or productId is null");
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", optString);
        contentValues.put("productId", optString2);
        Intent intent = new Intent("action_start_check_scale");
        intent.putExtra("commonDeviceInfo", contentValues);
        BaseApplication.e().sendBroadcast(intent, LocalBroadcast.c);
        onSuccessCallback(j, "success");
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        ReleaseLogUtil.b(this.TAG, "openFaCardAgds requestCode: ", Integer.valueOf(i), "resultCode: ", Integer.valueOf(i2));
        FaCardAgdsApi c = dlf.c();
        if (c == null) {
            ReleaseLogUtil.b(this.TAG, "openFaCardAgds faCardAgdsApi null:");
        }
        if (c != null && i == AGDS_REQUESET_CODE) {
            int loadResultCode = c.getLoadResultCode(intent);
            ReleaseLogUtil.b(this.TAG, "onActivityResult loadResultcode:", Integer.valueOf(loadResultCode));
            c.addToDeskTopBi(intent, this.mAgdsBiFrom);
            if (loadResultCode == 3) {
                onFailureCallback(this.mAgdsCallbackId, "open faild", loadResultCode);
                nrh.b(BaseApplication.e(), R.string._2130844514_res_0x7f021b62);
            } else {
                onSuccessCallback(this.mAgdsCallbackId, Integer.valueOf(loadResultCode));
            }
            c.close();
        }
        if (c == null || i != 100) {
            return;
        }
        if (i2 == c.getResultcodeAgreeProtocol()) {
            c.open(AGDS_REQUESET_CODE, this.mFaCardModuleName, new FaCardAgdsApi.OpenAgdsResultCallback() { // from class: com.huawei.operation.h5pro.jsmodules.WeightJsApi$$ExternalSyntheticLambda11
                @Override // com.huawei.health.facardagds.FaCardAgdsApi.OpenAgdsResultCallback
                public final void onResponse(int i3) {
                    WeightJsApi.this.m736xf64d22a2(i3);
                }
            });
        } else if (i2 == c.getResultcodeNotAgreeProtocol()) {
            ReleaseLogUtil.b(this.TAG, "openFaCardAgds NotAgreeProtocol");
        }
    }

    /* renamed from: lambda$onActivityResult$14$com-huawei-operation-h5pro-jsmodules-WeightJsApi, reason: not valid java name */
    /* synthetic */ void m736xf64d22a2(int i) {
        ReleaseLogUtil.b(this.TAG, "openFaCardAgds errorCode:", Integer.valueOf(i));
    }

    @JavascriptInterface
    public void closeFaCardAgds() {
        ReleaseLogUtil.b(this.TAG, "closeFaCardAgds");
        dlf.c().close();
    }
}
