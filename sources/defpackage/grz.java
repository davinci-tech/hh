package defpackage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.weight.WeightApi;
import com.huawei.health.weight.bean.WeightTargetDifferences;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import com.huawei.ui.main.stories.health.weight.callback.WeightCallback;
import com.huawei.up.model.UserInfomation;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class grz {
    private static WeightApi f() {
        return (WeightApi) Services.a("Main", WeightApi.class);
    }

    public static HiAggregateOption a(String str) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setType(new int[]{10006});
        hiAggregateOption.setFilter(d(str));
        hiAggregateOption.setConstantsKey(new String[]{BleConstants.WEIGHT_KEY});
        return hiAggregateOption;
    }

    public static String d(String str) {
        return (TextUtils.isEmpty(str) || TextUtils.equals(str, "NULL") || TextUtils.equals(str, LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1011))) ? "NULL" : str;
    }

    public static void aSU_(String str, Bundle bundle) {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("WeightUtils", "goToWeight weightApi is null from ", str, " bundle ", bundle);
        } else {
            f.gotoWeight(str, bundle);
        }
    }

    public static void aST_(String str, Intent intent) {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("WeightUtils", "goToWeight weightApi is null from ", str, " intent ", intent);
        } else {
            f.gotoWeight(str, intent);
        }
    }

    public static void a(Map<String, Object> map) {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("WeightUtils", "goToWeightDataPage weightApi is null timeMillis, params:", String.valueOf(map));
        } else {
            f.goToWeightDataPage(map);
        }
    }

    public static void c(IBaseResponseCallback iBaseResponseCallback) {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("WeightUtils", "initUser weightApi is null callback ", iBaseResponseCallback);
        } else {
            f.initUser(iBaseResponseCallback);
        }
    }

    public static void h(String str) {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("WeightUtils", "setCurrentUser weightApi is null userId ", str);
        } else {
            f.setCurrentUser(str);
        }
    }

    public static void a(ResponseCallback<Double> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("WeightUtils", "getTargetWeight callback is null");
            return;
        }
        WeightApi f = f();
        if (f == null) {
            LogUtil.h("WeightUtils", "getTargetWeight weightApi is null");
            responseCallback.onResponse(-1, Double.valueOf(0.0d));
        } else {
            f.getTargetWeight(responseCallback);
        }
    }

    public static void d(ResponseCallback<HashMap<String, Double>> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("WeightUtils", "getStartWeightAndTargetWeight callback is null");
            return;
        }
        WeightApi f = f();
        if (f == null) {
            LogUtil.h("WeightUtils", "getStartWeightAndTargetWeight weightApi is null");
            responseCallback.onResponse(-1, new HashMap<>());
        } else {
            f.getStartWeightAndTargetWeight(responseCallback);
        }
    }

    public static void b(ResponseCallback<WeightTargetDifferences> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("WeightUtils", "getWeightTargetDifferences callback is null");
            return;
        }
        WeightApi f = f();
        if (f == null) {
            LogUtil.h("WeightUtils", "getWeightTargetDifferences weightApi is null");
            responseCallback.onResponse(-1, null);
        } else {
            f.getWeightTargetDifferences(responseCallback);
        }
    }

    public static float c(long j, long j2) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeRange(j, j2);
        hiAggregateOption.setAggregateType(0);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setCount(1);
        hiAggregateOption.setSortOrder(1);
        WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
        final float[] fArr = {0.0f};
        if (weightApi != null) {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            weightApi.getWeightData(hiAggregateOption, new WeightCallback() { // from class: gsa
                @Override // com.huawei.ui.main.stories.health.weight.callback.WeightCallback
                public final void getWeight(ArrayList arrayList) {
                    grz.d(fArr, countDownLatch, arrayList);
                }
            });
            try {
                if (!countDownLatch.await(5000L, TimeUnit.MILLISECONDS)) {
                    LogUtil.h("WeightUtils", "readWeight wait timeout");
                }
            } catch (InterruptedException unused) {
                LogUtil.b("WeightUtils", "interrupted while waiting for readWeight");
            }
        } else {
            LogUtil.h("WeightUtils", "weightApi is null.");
        }
        return fArr[0];
    }

    static /* synthetic */ void d(float[] fArr, CountDownLatch countDownLatch, ArrayList arrayList) {
        if (koq.c(arrayList)) {
            float e = (float) ((qtm) arrayList.get(0)).e();
            fArr[0] = e;
            LogUtil.h("WeightUtils", "getWeightData is ", Float.valueOf(e));
        } else {
            LogUtil.h("WeightUtils", "getWeightData is null");
        }
        countDownLatch.countDown();
    }

    public static float a() {
        UserInfomation userInfo;
        float c = c(0L, System.currentTimeMillis());
        if (c > 0.0f) {
            return c;
        }
        LogUtil.a("WeightUtils", "get weight from userinfomation.");
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
        return (userProfileMgrApi == null || (userInfo = userProfileMgrApi.getUserInfo()) == null) ? c : userInfo.getWeight();
    }

    public static void c(UiCallback<Float> uiCallback) {
        if (uiCallback == null) {
            LogUtil.h("WeightUtils", "getCurrentWeight uiCallback == null");
            return;
        }
        float c = c(0L, System.currentTimeMillis());
        if (c <= 0.0f) {
            LogUtil.a("WeightUtils", "get weight from userinfomation.");
            UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
            if (userProfileMgrApi == null) {
                uiCallback.onFailure(-1, "userProfileMgrApi == null");
                return;
            }
            UserInfomation userInfo = userProfileMgrApi.getUserInfo();
            if (userInfo == null) {
                uiCallback.onFailure(-1, "userInfomation == null");
                return;
            }
            c = userInfo.getWeight();
        }
        uiCallback.onSuccess(Float.valueOf(c));
    }

    public static boolean e() {
        WeightApi f = f();
        if (f == null) {
            LogUtil.h("WeightUtils", "isSupportAiBodyShape weightApi is null");
            return false;
        }
        return f.isSupportAiBodyShape();
    }

    public static String e(String str) {
        WeightApi f = f();
        if (f == null) {
            LogUtil.h("WeightUtils", "getPeerAnalysis weightApi is null");
            return null;
        }
        return f.getPeerAnalysis(str);
    }

    public static String b(String str) {
        WeightApi f = f();
        if (f == null) {
            LogUtil.h("WeightUtils", "getTrunkFatRateGrades weightApi is null");
            return "";
        }
        return f.getTrunkFatRateGrades(str);
    }

    public static String d() {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("WeightUtils", "getChildHeightUpdateTime weightApi is null");
            return "";
        }
        return f.getChildHeightUpdateTime();
    }

    public static void b() {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("WeightUtils", "saveChildHeightUpdateTime weightApi is null");
        } else {
            f.saveChildHeightUpdateTime();
        }
    }

    public static void d(Context context, ResponseCallback<Integer> responseCallback) {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("WeightUtils", "showUserInfoDialog weightApi is null activity ", context, " callback ", responseCallback);
        } else {
            f.showUserInfoDialog(context, responseCallback);
        }
    }

    public static void a(Context context, ResponseCallback<Integer> responseCallback) {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("WeightUtils", "showInputDialog weightApi is null activity ", context, " callback ", responseCallback);
        } else {
            f.showInputDialog(context, responseCallback);
        }
    }

    public static void c(Context context, ResponseCallback<Integer> responseCallback) {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("WeightUtils", "showAutoUpdateWeightDialog weightApi is null activity ", context, " callback ", responseCallback);
        } else {
            f.showAutoUpdateWeightDialog(context, responseCallback);
        }
    }

    public static void e(Context context, ResponseCallback<Integer> responseCallback) {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("WeightUtils", "showSetGoalDialog weightApi is null activity ", context, " callback ", responseCallback);
        } else {
            f.showSetGoalDialog(context, responseCallback);
        }
    }

    public static void b(Context context, ResponseCallback<Integer> responseCallback) {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("WeightUtils", "showBindDeviceDialog weightApi is null activity ", context, " callback ", responseCallback);
        } else {
            f.showBindDeviceDialog(context, responseCallback);
        }
    }

    public static void g(Context context, ResponseCallback<Integer> responseCallback) {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("WeightUtils", "showUserInfoDialogForMeasureStart weightApi is null activity ", context, " callback ", responseCallback);
        } else {
            f.showUserInfoDialogForMeasureStart(context, responseCallback);
        }
    }

    public static void f(Context context, ResponseCallback<Integer> responseCallback) {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("WeightUtils", "showUserInfoDialogForMeasureStart weightApi is null activity ", context, " callback ", responseCallback);
        } else {
            f.showUserInfoDialogForMeasureFinish(context, responseCallback);
        }
    }

    public static void a(Context context, ResponseCallback<Integer> responseCallback, float f, int i, String str) {
        WeightApi f2 = f();
        if (f2 == null) {
            ReleaseLogUtil.a("WeightUtils", "showGoalMotivationDialog weightApi is null activity ", context, " callback ", responseCallback, " bodyWeightDiff ", Float.valueOf(f), " progress ", Integer.valueOf(i), " json ", str);
        } else {
            f2.showGoalMotivationDialog(context, responseCallback, f, i, str);
        }
    }

    public static void c(ResponseCallback<WeightTargetDifferences> responseCallback) {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("WeightUtils", "initGoalMotivationCache weightApi is null callback ", responseCallback);
        } else {
            f.initGoalMotivationCache(responseCallback);
        }
    }

    public static void e(float f, long j, ResponseCallback<Object> responseCallback) {
        WeightApi f2 = f();
        if (f2 == null) {
            ReleaseLogUtil.a("WeightUtils", "autoUpdateUserWeight weightApi is null weight ", Float.valueOf(f), " measureTime ", Long.valueOf(j), " callback ", responseCallback);
        } else {
            f2.autoUpdateUserWeight(f, j, responseCallback);
        }
    }

    public static void i() {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("WeightUtils", "showBluetoothClaimDataDialog weightApi is null");
        } else {
            f.showBluetoothClaimDataDialog();
        }
    }

    public static void c() {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("WeightUtils", "initCardRedDot weightApi is null");
        } else {
            f.initCardRedDot();
        }
    }

    public static void e(ResponseCallback<Object> responseCallback, String str) {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("R_Weight_WeightUtils", "saveWeightManager weightApi is null");
        } else {
            f.saveWeightManagerAndGoal(responseCallback, str);
        }
    }

    public static void e(ResponseCallback<gse> responseCallback) {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("R_Weight_WeightUtils", "getGoalDetail weightApi is null");
        } else {
            f.getGoalDetail(responseCallback);
        }
    }

    public static void aSS_(Bundle bundle, ResponseCallback<gsi> responseCallback) {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("R_Weight_WeightUtils", "calculateGoal weightApi is null");
        } else {
            f.calculateGoal(bundle, responseCallback);
        }
    }

    public static void a(gsi gsiVar, long j) {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("R_Weight_WeightUtils", "saveWeightTargetDifferences weightApi is null");
        } else {
            f.saveWeightTargetDifferences(gsiVar, j);
        }
    }

    public static void a(qul qulVar, ResponseCallback<quh> responseCallback) {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("R_Weight_WeightUtils", "addMeal weightApi is null meal ", qulVar, " callback ", responseCallback);
        } else {
            f.addMeal(qulVar, responseCallback);
        }
    }

    public static void b(qul qulVar, ResponseCallback<quh> responseCallback) {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("R_Weight_WeightUtils", "updateMeal weightApi is null meal ", qulVar, " callback ", responseCallback);
        } else {
            f.updateMeal(qulVar, responseCallback);
        }
    }

    public static void d(List<qul> list, ResponseCallback<List<quh>> responseCallback) {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("R_Weight_WeightUtils", "updateMeal weightApi is null", " callback ", responseCallback);
        } else {
            f.recordMeals(list, responseCallback);
        }
    }

    public static void h() {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("R_Weight_WeightUtils", "updateOnePointFullAndSaveWeightManager weightApi is null");
        } else {
            f.updateOnePointFull();
        }
    }

    public static void b(int i, int i2, ResponseCallback<List<quh>> responseCallback) {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("R_Weight_WeightUtils", "getDietRecord weightApi is null startDate ", Integer.valueOf(i), " endDate ", Integer.valueOf(i2), " callback ", responseCallback);
        } else {
            f.getDietRecord(i, i2, responseCallback);
        }
    }

    public static void c(int i, int i2, long j, ResponseCallback<List<quh>> responseCallback) {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("R_Weight_WeightUtils", "getDietRecord weightApi is null startDate ", Integer.valueOf(i), " endDate ", Integer.valueOf(i2), " timeout ", Long.valueOf(j), " callback ", responseCallback);
        } else {
            f.getDietRecord(i, i2, j, responseCallback);
        }
    }

    public static void e(int i, int i2, ResponseCallback<List<quh>> responseCallback) {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("R_Weight_WeightUtils", "getBasicDietRecord weightApi is null startDate ", Integer.valueOf(i), " endDate ", Integer.valueOf(i2), " callback ", responseCallback);
        } else {
            f.getBasicDietRecord(i, i2, responseCallback);
        }
    }

    public static void d(int i, int i2, long j, ResponseCallback<List<quh>> responseCallback) {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("R_Weight_WeightUtils", "getBasicDietRecord weightApi is null startDate ", Integer.valueOf(i), " endDate ", Integer.valueOf(i2), " timeout ", Long.valueOf(j), " callback ", responseCallback);
        } else {
            f.getBasicDietRecord(i, i2, j, responseCallback);
        }
    }

    public static void b(boolean z, ResponseCallback<Boolean> responseCallback) {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("R_Weight_WeightUtils", "isSupportDiet weightApi is null isFullDiet ", Boolean.valueOf(z), " callback ", responseCallback);
        } else {
            f.isSupportDiet(z, responseCallback);
        }
    }

    public static void b(quh quhVar, ResponseCallback<cwj> responseCallback) {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("R_Weight_WeightUtils", "getDeviceSyncDietAnalysis weightApi is null dietRecord ", quhVar, " callback ", responseCallback);
        } else {
            f.getDeviceSyncDietAnalysis(quhVar, responseCallback);
        }
    }

    public static void f(String str) {
        String str2;
        ReleaseLogUtil.b("WeightUtils", "goToDietDiaryFastRecord pull from is ：", str);
        if (!Utils.o() || gsd.a("THIRD_DIET_TIME_MILLIS")) {
            LogUtil.a("WeightUtils", "goToDietDiaryFastRecord isSupport full Diet");
            str2 = "huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.diet-diary?h5pro=true&urlType=4&pName=com.huawei.health&path=record_breakfast&statusBarTextBlack&isImmerse&whichMeal=" + g();
        } else {
            LogUtil.a("WeightUtils", "goToDietDiaryFastRecord isSupport not full Diet");
            str2 = "huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.diet-diary?h5pro=true&isImmerse";
        }
        AppRouter.zi_(Uri.parse(str2)).b(268435456).c(com.huawei.hwcommonmodel.application.BaseApplication.getContext());
    }

    private static String g() {
        int i = Calendar.getInstance().get(11);
        return i <= 9 ? "10" : i <= 13 ? HealthZonePushReceiver.DEAUTH_EVENT_NOTIFY : HealthZonePushReceiver.SLEEP_SCORE_NOTIFY;
    }

    public static void c(String str) {
        WeightApi f = f();
        if (f == null) {
            ReleaseLogUtil.a("R_Weight_WeightUtils", "checkFatReductionShapingState weightApi is null");
        } else {
            ReleaseLogUtil.b("WeightUtils", "checkFatReductionShapingState ：", str);
            f.checkFatReductionShapingState();
        }
    }
}
