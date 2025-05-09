package com.huawei.ui.main.stories.health.weight.callback;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.fatscale.multiusers.WeightDataManager;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.service.anotation.H5ProMethod;
import com.huawei.health.h5pro.service.anotation.H5ProService;
import com.huawei.health.weight.bean.WeightTargetDifferences;
import com.huawei.hihealth.HiGoalInfo;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.health.model.weight.card.CardConstants;
import com.huawei.ui.main.stories.health.weight.callback.DietDiaryCbk;
import com.huawei.ui.main.stories.health.weight.callback.DietDiaryRepository;
import defpackage.bzs;
import defpackage.cfi;
import defpackage.gsi;
import defpackage.jdl;
import defpackage.koq;
import defpackage.kot;
import defpackage.kpu;
import defpackage.kvs;
import defpackage.kvt;
import defpackage.mcv;
import defpackage.qsj;
import defpackage.quh;
import defpackage.quj;
import defpackage.qul;
import defpackage.qvz;
import defpackage.rag;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LogAnonymous;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@H5ProService(name = "DietDiary", users = {"9ea3f06fe2dea3e2c7c21ea1ba1e07c370a786c68417a4a96cb986576883e10f"})
/* loaded from: classes.dex */
public class DietDiaryRepository {
    private static final String TAG = "DietDiaryRepository";
    private static final String TAG_RELEASE = "R_DietDiaryRepository";
    public static final int WEIGHT_DIFF_TO_CALORIE = 7700;
    public static final double WEIGHT_DOUBLE_DIFF_TO_CALORIE = 7.7d;

    private DietDiaryRepository() {
    }

    @H5ProMethod(name = "joinDietDiary")
    public static void joinDietDiary() {
        LogUtil.a(TAG, "joinDietDiary from H5");
        ThreadPoolManager.d().execute(new Runnable() { // from class: qtv
            @Override // java.lang.Runnable
            public final void run() {
                DietDiaryRepository.lambda$joinDietDiary$0();
            }
        });
    }

    public static /* synthetic */ void lambda$joinDietDiary$0() {
        if (CardConstants.b()) {
            return;
        }
        CardConstants.a();
    }

    @H5ProMethod(name = "getWeightControlSetting")
    public static void getWeightControlSetting(DietDiaryCbk<quj> dietDiaryCbk) {
        if (dietDiaryCbk == null) {
            ReleaseLogUtil.a(TAG_RELEASE, "getWeightControlSetting callback is null");
            return;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        final quj qujVar = new quj();
        kot.a().c(new ResponseCallback() { // from class: qub
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                DietDiaryRepository.lambda$getWeightControlSetting$1(quj.this, countDownLatch, i, (Float) obj);
            }
        });
        kot.a().b(new ResponseCallback() { // from class: qua
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                DietDiaryRepository.lambda$getWeightControlSetting$3(quj.this, countDownLatch, i, (gsi) obj);
            }
        });
        try {
            ReleaseLogUtil.b(TAG_RELEASE, "getWeightControlSetting isAwait ", Boolean.valueOf(countDownLatch.await(5L, TimeUnit.SECONDS)));
        } catch (InterruptedException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "getWeightControlSetting exception ", ExceptionUtils.d(e));
        }
        LogUtil.a(TAG, "getWeightControlSetting ", qujVar);
        dietDiaryCbk.onSuccess(qujVar);
    }

    public static /* synthetic */ void lambda$getWeightControlSetting$1(quj qujVar, CountDownLatch countDownLatch, int i, Float f) {
        LogUtil.a(TAG, "getWeightControlSetting onResponse floatData ", f);
        if (f != null) {
            qujVar.a(f.floatValue());
        }
        countDownLatch.countDown();
    }

    public static /* synthetic */ void lambda$getWeightControlSetting$3(final quj qujVar, final CountDownLatch countDownLatch, int i, gsi gsiVar) {
        if (gsiVar != null && gsiVar.d() > 0.0f) {
            qujVar.d(gsiVar.d());
            countDownLatch.countDown();
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qtw
                @Override // java.lang.Runnable
                public final void run() {
                    DietDiaryRepository.lambda$getWeightControlSetting$2(quj.this, countDownLatch);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$getWeightControlSetting$2(quj qujVar, CountDownLatch countDownLatch) {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.e()).getUserPreference("custom.start_weight_base");
        if (userPreference == null) {
            ReleaseLogUtil.a(TAG_RELEASE, "getWeightControlSetting preference is null");
        } else {
            String value = userPreference.getValue();
            LogUtil.a(TAG, "getWeightControlSetting value ", value);
            qujVar.d(CommonUtil.j(value));
        }
        countDownLatch.countDown();
    }

    public static void getDietRecordLocal(final long j, final long j2, final DietDiaryCbk<quh[]> dietDiaryCbk) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: quc
                @Override // java.lang.Runnable
                public final void run() {
                    DietDiaryRepository.getDietRecord(j / 1000, j2 / 1000, dietDiaryCbk);
                }
            });
        } else {
            getDietRecord(j / 1000, j2 / 1000, dietDiaryCbk);
        }
    }

    @H5ProMethod(name = "getDietRecord")
    public static void getDietRecord(long j, long j2, final DietDiaryCbk<quh[]> dietDiaryCbk) {
        if (dietDiaryCbk == null) {
            ReleaseLogUtil.a(TAG_RELEASE, "callback is null startTime ", Long.valueOf(j), " endTime ", Long.valueOf(j2));
            return;
        }
        int b = DateFormatUtil.b(j * 1000);
        int b2 = DateFormatUtil.b(1000 * j2);
        ReleaseLogUtil.b(TAG_RELEASE, "getDietRecord startTime ", Long.valueOf(j), " endTime ", Long.valueOf(j2), " startDate ", Integer.valueOf(b), " endDate ", Integer.valueOf(b2));
        qvz.d(b, b2, (ResponseCallback<List<quh>>) new ResponseCallback() { // from class: qtu
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                DietDiaryRepository.lambda$getDietRecord$5(DietDiaryCbk.this, i, (List) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$getDietRecord$5(DietDiaryCbk dietDiaryCbk, int i, List list) {
        quh[] quhVarArr = new quh[0];
        new ArrayList();
        try {
            String e = HiJsonUtil.e(list);
            List<quh> list2 = (List) HiJsonUtil.b(e, new TypeToken<List<quh>>() { // from class: com.huawei.ui.main.stories.health.weight.callback.DietDiaryRepository.2
            }.getType());
            if (koq.b(list2)) {
                ReleaseLogUtil.a(TAG_RELEASE, "getDietRecord errorCode ", Integer.valueOf(i), " list ", list, " json ", e, " resultList ", list2);
                dietDiaryCbk.onSuccess(quhVarArr);
                return;
            }
            for (quh quhVar : list2) {
                if (quhVar != null) {
                    quhVar.e(jdl.b(DateFormatUtil.c(quhVar.c()), TimeZone.getDefault()));
                    quhVar.h();
                }
            }
            LogUtil.a(TAG, "getDietRecord errorCode ", Integer.valueOf(i), " list ", list, " json ", e, " resultList ", list2);
            dietDiaryCbk.onSuccess((quh[]) list2.toArray(quhVarArr));
        } catch (NullPointerException e2) {
            LogUtil.b(TAG, "getDietRecord exception ", LogAnonymous.b((Throwable) e2));
            dietDiaryCbk.onSuccess(quhVarArr);
        }
    }

    @H5ProMethod(name = "addMeal")
    public static void addMeal(qul qulVar, final DietDiaryCbk<Object> dietDiaryCbk) {
        if (qulVar == null) {
            if (dietDiaryCbk != null) {
                dietDiaryCbk.onFailure(-1, "addMeal meal is null");
            }
        } else {
            qulVar.m();
            LogUtil.a(TAG, "addMeal meal ", qulVar);
            qvz.a(qulVar, (ResponseCallback<quh>) new ResponseCallback() { // from class: qud
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    DietDiaryRepository.lambda$addMeal$6(DietDiaryCbk.this, i, (quh) obj);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$addMeal$6(DietDiaryCbk dietDiaryCbk, int i, quh quhVar) {
        if (dietDiaryCbk == null) {
            return;
        }
        if (i != 0) {
            dietDiaryCbk.onFailure(i, HiJsonUtil.e(quhVar));
        } else {
            dietDiaryCbk.onSuccess(quhVar);
        }
    }

    @H5ProMethod(name = "updateMeal")
    public static void updateMeal(qul qulVar, final DietDiaryCbk<Object> dietDiaryCbk) {
        if (qulVar == null) {
            if (dietDiaryCbk != null) {
                dietDiaryCbk.onFailure(-1, "updateMeal meal is null");
            }
        } else {
            qulVar.m();
            LogUtil.a(TAG, "updateMeal meal ", qulVar);
            qvz.c(qulVar, (ResponseCallback<quh>) new ResponseCallback() { // from class: qtz
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    DietDiaryRepository.lambda$updateMeal$7(DietDiaryCbk.this, i, (quh) obj);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$updateMeal$7(DietDiaryCbk dietDiaryCbk, int i, quh quhVar) {
        if (dietDiaryCbk == null) {
            return;
        }
        if (i != 0) {
            dietDiaryCbk.onFailure(i, HiJsonUtil.e(quhVar));
        } else {
            dietDiaryCbk.onSuccess(quhVar);
        }
    }

    @H5ProMethod(name = "updateOnePointFull")
    public static void updateOnePointFull() {
        ReleaseLogUtil.b(TAG_RELEASE, "updateOnePointFull");
        qvz.j();
    }

    @H5ProMethod(name = "syncDietData")
    public static void syncDietData() {
        ReleaseLogUtil.b(TAG_RELEASE, "syncDietData");
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.DIET_RECORD_SET.value()));
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.RESTING_METABOLISM_SET.value()));
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.CURRENT_BASAL_METABOLISM_SET.value()));
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.BASAL_METABOLISM_AFTER_EXERCISE_SET.value()));
        qvz.c(arrayList);
    }

    @H5ProMethod(name = "getBaseUrl")
    public static void getBaseUrl(DietDiaryCbk<String> dietDiaryCbk) {
        if (dietDiaryCbk == null) {
            LogUtil.b(TAG, "callback is null");
            return;
        }
        String url = GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl");
        if (TextUtils.isEmpty(url)) {
            dietDiaryCbk.onFailure(-1, "url is null");
        } else {
            LogUtil.a(TAG, "url is:", url);
            dietDiaryCbk.onSuccess(url);
        }
    }

    public static void jumpDietDetails(Context context, String str) {
        String sb;
        if (context == null) {
            ReleaseLogUtil.a(TAG_RELEASE, "jumpDietDetails context is null");
            return;
        }
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        if (TextUtils.isEmpty(str)) {
            sb = "#/diet_recording_tool?from=2";
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(str.contains("&") ? "&" : "?");
            sb2.append("from=2");
            sb = sb2.toString();
        }
        LogUtil.a(TAG, "jumpDietDetails newUri ", sb, " uri ", str);
        builder.addPath(sb);
        bzs.e().loadH5ProApp(context, "com.huawei.health.h5.diet-diary", builder);
        joinDietDiary();
    }

    public static void saveWeightGoalAsync(final WeightTargetDifferences weightTargetDifferences, final boolean z, final float f, final HiGoalInfo hiGoalInfo, final float f2, final IBaseResponseCallback iBaseResponseCallback) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qty
                @Override // java.lang.Runnable
                public final void run() {
                    DietDiaryRepository.saveWeightGoalAsync(WeightTargetDifferences.this, z, f, hiGoalInfo, f2, iBaseResponseCallback);
                }
            });
            return;
        }
        if (!rag.b(weightTargetDifferences)) {
            LogUtil.h(TAG, "saveWeightGoal setUserPreference dailyWeightDiff is fail");
            return;
        }
        ArrayList arrayList = new ArrayList(31);
        arrayList.add(hiGoalInfo);
        HiHealthManager.d(BaseApplication.e()).setGoalInfo(0, arrayList, new HiCommonListener() { // from class: com.huawei.ui.main.stories.health.weight.callback.DietDiaryRepository.1
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                DietDiaryRepository.successRefreshData(WeightTargetDifferences.this.a() / 1000.0d, f, hiGoalInfo, obj, f2, iBaseResponseCallback);
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.h(DietDiaryRepository.TAG, "saveGoals onFailure");
                iBaseResponseCallback.d(100001, obj);
            }
        });
        if (z) {
            long currentTimeMillis = System.currentTimeMillis();
            float goalValue = (float) hiGoalInfo.getGoalValue();
            kpu.e(goalValue, currentTimeMillis, null);
            qsj.d(currentTimeMillis, goalValue, f2, weightTargetDifferences, "", new ResponseCallback() { // from class: qtx
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    LogUtil.a(DietDiaryRepository.TAG, "saveWeightGoalAsync weightManager status ", Integer.valueOf(i), " data ", obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void successRefreshData(double d, float f, HiGoalInfo hiGoalInfo, Object obj, float f2, IBaseResponseCallback iBaseResponseCallback) {
        mcv.d(BaseApplication.e()).c(BaseApplication.e(), String.valueOf(1200), new HashMap());
        boolean z = Math.abs(((double) f) - hiGoalInfo.getGoalValue()) < 0.5d;
        LogUtil.a(TAG, "isGoalChanged=", Boolean.valueOf(z), ",daily gaol=", Double.valueOf(d));
        if (!z) {
            kvs.b(BaseApplication.e()).d(20000);
            SharedPreferenceManager.e(BaseApplication.e(), Integer.toString(10006), "health_weight_goal_change", String.valueOf(true), (StorageParams) null);
        }
        cfi mainUser = MultiUsersManager.INSTANCE.getMainUser();
        if (f2 > 0.0f) {
            kvt.e(BaseApplication.e()).c(BaseApplication.e(), hiGoalInfo.getGoalValue());
            HiUserPreference hiUserPreference = new HiUserPreference();
            hiUserPreference.setKey("custom.start_weight_base");
            hiUserPreference.setValue(String.valueOf(f2));
            HiHealthManager.d(BaseApplication.e()).setUserPreference(hiUserPreference);
            mainUser.c(f2);
        }
        mainUser.d((float) hiGoalInfo.getGoalValue());
        WeightDataManager.INSTANCE.setInitFlag(true);
        CommonUtil.am(BaseApplication.e());
        LogUtil.a(TAG, "data =", obj);
        iBaseResponseCallback.d(0, obj);
    }

    @H5ProMethod(name = "gotoUserInfo")
    public static void gotoUserInfo() {
        AppRouter.b("/HWUserProfileMgr/UserInfoActivity").a(268435456).c(BaseApplication.e());
    }

    @H5ProMethod(name = "getCommonApi")
    public static void getCommonApi(DietDiaryCbk<Object> dietDiaryCbk) {
        if (dietDiaryCbk == null) {
            LogUtil.h(TAG, "getCommonApi callback is null");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("isSupportTimeZone", true);
        hashMap.put("isSupportScans", true);
        hashMap.put("isNewTargetUI", true);
        hashMap.put("isSupportQuickRecord", true);
        hashMap.put("isNewKaka", true);
        hashMap.put("isSupportActivityResult", true);
        hashMap.put("isSupportShowTradeView", true);
        String e = HiJsonUtil.e(hashMap);
        LogUtil.a(TAG, "getCommonApi json", e);
        dietDiaryCbk.onSuccess(e);
    }
}
