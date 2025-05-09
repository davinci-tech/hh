package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.wifi.control.claim.ClaimWeightDataManager;
import com.huawei.health.weight.WeightApi;
import com.huawei.health.weight.bean.WeightTargetDifferences;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.health.model.weight.card.CardConstants;
import com.huawei.ui.main.stories.health.util.TrendFragmentCallback;
import com.huawei.ui.main.stories.health.weight.callback.CustomizationRecipe;
import com.huawei.ui.main.stories.health.weight.callback.DietDiaryCbk;
import com.huawei.ui.main.stories.health.weight.callback.DietDiaryRepository;
import com.huawei.ui.main.stories.health.weight.callback.FastingLiteRepository;
import com.huawei.ui.main.stories.health.weight.callback.WeightCallback;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApiDefine(uri = WeightApi.class)
@Singleton
/* loaded from: classes7.dex */
public class qvq implements WeightApi {
    @Override // com.huawei.health.weight.WeightApi
    public Class<?> getDietDiaryRepositoryApi() {
        return DietDiaryRepository.class;
    }

    @Override // com.huawei.health.weight.WeightApi
    public Class<?> getFastingLiteRepositoryApi() {
        return FastingLiteRepository.class;
    }

    @Override // com.huawei.health.weight.WeightApi
    public Class<?> getCustomRecipe() {
        return CustomizationRecipe.class;
    }

    @Override // com.huawei.health.weight.WeightApi
    public void syncDietData() {
        DietDiaryRepository.syncDietData();
    }

    @Override // com.huawei.health.weight.WeightApi
    public void addMeal(qul qulVar, ResponseCallback<quh> responseCallback) {
        qvz.a(qulVar, responseCallback);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void updateMeal(qul qulVar, ResponseCallback<quh> responseCallback) {
        qvz.c(qulVar, responseCallback);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void updateOnePointFull() {
        qvz.j();
    }

    @Override // com.huawei.health.weight.WeightApi
    public void recordMeals(List<qul> list, ResponseCallback<List<quh>> responseCallback) {
        qvz.a(list, responseCallback);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void getDietRecord(int i, int i2, ResponseCallback<List<quh>> responseCallback) {
        qvz.d(i, i2, responseCallback);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void getDietRecord(int i, int i2, long j, ResponseCallback<List<quh>> responseCallback) {
        qvz.d(i, i2, j, responseCallback);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void getBasicDietRecord(int i, int i2, ResponseCallback<List<quh>> responseCallback) {
        qvz.b(i, i2, responseCallback);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void getBasicDietRecord(int i, int i2, long j, ResponseCallback<List<quh>> responseCallback) {
        qvz.a(i, i2, j, responseCallback);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void isSupportDiet(boolean z, ResponseCallback<Boolean> responseCallback) {
        qvz.e(z, responseCallback);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void getDeviceSyncDietAnalysis(quh quhVar, ResponseCallback<cwj> responseCallback) {
        qvz.a(quhVar, responseCallback);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void getWeightData(HiAggregateOption hiAggregateOption, final WeightCallback weightCallback) {
        qsj.c(MultiUsersManager.INSTANCE.getCurrentUser(), hiAggregateOption, new TrendFragmentCallback() { // from class: qvq.4
            @Override // com.huawei.ui.main.stories.health.util.TrendFragmentCallback
            public void getWeight(ArrayList<cfe> arrayList, boolean z) {
                weightCallback.getWeight(qsj.b(arrayList));
            }
        });
    }

    @Override // com.huawei.health.weight.WeightApi
    public boolean isJoinDietDiary() {
        return CardConstants.b();
    }

    @Override // com.huawei.health.weight.WeightApi
    public void joinDietDiary() {
        CardConstants.a();
    }

    @Override // com.huawei.health.weight.WeightApi
    public int getWeightBondedProducts() {
        return qsj.b("HDK_WEIGHT");
    }

    @Override // com.huawei.health.weight.WeightApi
    public double getBmiByHiHealthData(HiHealthData hiHealthData) {
        cfe cfeVar = new cfe();
        rag.d(hiHealthData, cfeVar, Utils.o(), null);
        return cfeVar.j();
    }

    @Override // com.huawei.health.weight.WeightApi
    public void getWeightControlSetting(DietDiaryCbk<quj> dietDiaryCbk) {
        DietDiaryRepository.getWeightControlSetting(dietDiaryCbk);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void checkFatReductionShapingState() {
        qvz.b();
    }

    @Override // com.huawei.health.weight.WeightApi
    public String getFastingLiteSetting() {
        return HiJsonUtil.e(qve.e());
    }

    @Override // com.huawei.health.weight.WeightApi
    public String getFastingLiteStatus() {
        return CardConstants.e();
    }

    @Override // com.huawei.health.weight.WeightApi
    public String getFastingLiteViewData() {
        qlh g = qlc.b().g();
        if (g == null) {
            return "";
        }
        qux quxVar = new qux();
        quxVar.a(g.b());
        quxVar.a(g.e());
        quxVar.d(g.a());
        LogUtil.c("WeightApiImpl", "getFastingLiteViewData faData = ", quxVar.toString());
        return HiJsonUtil.e(quxVar);
    }

    @Override // com.huawei.health.weight.WeightApi
    public boolean isHuaweiWspDataTypeApi(int i) {
        return cpa.e(i);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void registerFastingLite() {
        quq.a().b("pullPkgService", null);
    }

    @Override // com.huawei.health.weight.WeightApi
    public boolean isSupportAiBodyShape() {
        return rag.e();
    }

    @Override // com.huawei.health.weight.WeightApi
    public String getPeerAnalysis(String str) {
        qvn qvnVar = (qvn) HiJsonUtil.e(str, qvn.class);
        sdb c = qrj.c(qvnVar.d(), qvnVar.e());
        ArrayList arrayList = new ArrayList();
        for (Integer num : qvnVar.c()) {
            HashMap hashMap = new HashMap();
            hashMap.put("type", num);
            if (num.intValue() == 0) {
                hashMap.put("data", Float.valueOf(c.s()));
            } else if (num.intValue() == 1) {
                hashMap.put("data", Float.valueOf(c.l()));
            } else if (num.intValue() == 2) {
                hashMap.put("data", Float.valueOf(c.v()));
            } else if (num.intValue() == 3) {
                hashMap.put("data", Float.valueOf(c.q()));
            } else if (num.intValue() == 4) {
                hashMap.put("data", Float.valueOf(c.t()));
            } else {
                LogUtil.h("WeightApiImpl", "the type is illegal");
            }
            arrayList.add(hashMap);
        }
        return HiJsonUtil.e(arrayList);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void getStartWeight(ResponseCallback<Double> responseCallback) {
        rag.b(responseCallback);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void getTargetWeight(ResponseCallback<Double> responseCallback) {
        rag.e(responseCallback);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void getStartWeightAndTargetWeight(ResponseCallback<HashMap<String, Double>> responseCallback) {
        rag.d(responseCallback);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void getWeightTargetDifferences(ResponseCallback<WeightTargetDifferences> responseCallback) {
        rag.a(responseCallback);
    }

    @Override // com.huawei.health.weight.WeightApi
    public String getTrunkFatRateGrades(String str) {
        cps cpsVar;
        qvp qvpVar = (qvp) HiJsonUtil.e(str, qvp.class);
        cfe d = qvpVar.d();
        int a2 = qvpVar.a();
        boolean c = cpa.c(d);
        LogUtil.a("WeightApiImpl", "getTrunkFatRateGrades isSupportMult: ", Boolean.valueOf(c));
        if (c) {
            cpsVar = new cps(d.t(), (float) d.ax(), d.an(), d.e(), a2, 8, d.ag(), 2, d.q());
        } else {
            cpsVar = new cps(d.t(), (float) d.ax(), d.an(), d.e(), a2, d.ag());
        }
        LogUtil.a("WeightApiImpl", "getTrunkFatRateGrades hwWeightAlgorithm is ", cpsVar.toString());
        ArrayList<Integer> d2 = qsj.d(cpsVar, qsj.b(doj.i(d.an(), cpsVar.l(), d.e()), doj.i(d.an(), cpsVar.ad(), d.e()), UnitUtil.c(d.v(), 1), UnitUtil.c(d.am(), 1)), qsj.b(doj.c(d.an(), cpsVar.o(), d.e()), doj.c(d.an(), cpsVar.z(), d.e()), UnitUtil.c(d.u(), 1), UnitUtil.c(d.ai(), 1)), d);
        HashMap hashMap = new HashMap();
        if (koq.d(d2, 4)) {
            hashMap.put("trunk", Integer.valueOf(doj.j(d.an(), cpsVar.am(), d.e())));
            hashMap.put("leftUpperLimb", d2.get(1));
            hashMap.put("rightUpperLimb", d2.get(2));
            hashMap.put("leftLowerLimb", d2.get(3));
            hashMap.put("rightLowerLimb", d2.get(4));
        }
        return HiJsonUtil.e(hashMap);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void saveChildHeightUpdateTime() {
        cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
        if (currentUser == null) {
            LogUtil.h("WeightApiImpl", "saveChildHeightUpdateTime user is null");
            return;
        }
        String valueOf = String.valueOf(System.currentTimeMillis());
        LogUtil.a("WeightApiImpl", "saveChildHeightUpdateTime timeMillis ", valueOf, " result ", Integer.valueOf(SharedPreferenceManager.e(BaseApplication.e(), Integer.toString(10000), "update_child_height_time" + currentUser.i(), valueOf, (StorageParams) null)));
    }

    @Override // com.huawei.health.weight.WeightApi
    public String getChildHeightUpdateTime() {
        cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
        if (currentUser == null) {
            LogUtil.h("WeightApiImpl", "getChildHeightUpdateTime user is null");
            return "";
        }
        String b = SharedPreferenceManager.b(BaseApplication.e(), Integer.toString(10000), "update_child_height_time" + currentUser.i());
        LogUtil.a("WeightApiImpl", "getChildHeightUpdateTime timeMillis ", b);
        return b;
    }

    @Override // com.huawei.health.weight.WeightApi
    public void initUser(final IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.b("WeightApiImpl", "initUser callback ", iBaseResponseCallback);
        MultiUsersManager.INSTANCE.getOtherUserData(new IBaseResponseCallback() { // from class: qvs
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                rag.d((ResponseCallback<HashMap<String, Double>>) new ResponseCallback() { // from class: qwa
                    @Override // com.huawei.hwbasemgr.ResponseCallback
                    public final void onResponse(int i2, Object obj2) {
                        qvq.a(i, r2, obj, i2, (HashMap) obj2);
                    }
                });
            }
        });
    }

    static /* synthetic */ void a(int i, IBaseResponseCallback iBaseResponseCallback, Object obj, int i2, HashMap hashMap) {
        LogUtil.a("WeightApiImpl", "initUser errorCode ", Integer.valueOf(i), " status ", hashMap);
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.a("WeightApiImpl", "initUser callback is null");
        } else {
            iBaseResponseCallback.d(i, obj);
        }
    }

    @Override // com.huawei.health.weight.WeightApi
    public void setCurrentUser(String str) {
        cfi singleUserById = MultiUsersManager.INSTANCE.getSingleUserById(rag.d(str));
        LogUtil.a("WeightApiImpl", "setCurrentUser userId ", str, " user ", singleUserById);
        MultiUsersManager.INSTANCE.setCurrentUser(singleUserById);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void gotoWeight(Bundle bundle) {
        rag.dJz_(bundle);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void gotoWeight(String str, Bundle bundle) {
        rag.dJB_(str, bundle);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void gotoWeight(String str, Intent intent) {
        rag.dJA_(str, intent);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void goToWeightDataPage(Map<String, Object> map) {
        rag.e(map);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void showUserInfoDialog(Context context, ResponseCallback<Integer> responseCallback) {
        qyw.i(context, responseCallback);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void showInputDialog(Context context, ResponseCallback<Integer> responseCallback) {
        qyw.g(context, responseCallback);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void showAutoUpdateWeightDialog(Context context, ResponseCallback<Integer> responseCallback) {
        qyw.d(context, responseCallback);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void showSetGoalDialog(Context context, ResponseCallback<Integer> responseCallback) {
        qyw.h(context, responseCallback);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void showBindDeviceDialog(Context context, ResponseCallback<Integer> responseCallback) {
        qyw.e(context, responseCallback);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void showUserInfoDialogForMeasureStart(Context context, ResponseCallback<Integer> responseCallback) {
        qyw.f(context, responseCallback);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void showUserInfoDialogForMeasureFinish(Context context, ResponseCallback<Integer> responseCallback) {
        qyw.j(context, responseCallback);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void showGoalMotivationDialog(Context context, ResponseCallback<Integer> responseCallback, float f, int i, String str) {
        qyw.c(context, responseCallback, f, i, str);
    }

    @Override // com.huawei.health.weight.WeightApi
    /* renamed from: initGoalMotivationCache, reason: merged with bridge method [inline-methods] */
    public void d(final ResponseCallback<WeightTargetDifferences> responseCallback) {
        if (HandlerExecutor.c()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qvt
                @Override // java.lang.Runnable
                public final void run() {
                    qvq.this.d(responseCallback);
                }
            });
            return;
        }
        WeightTargetDifferences b = rag.b();
        qry.c(b);
        responseCallback.onResponse(0, b);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void autoUpdateUserWeight(float f, long j, ResponseCallback<Object> responseCallback) {
        rag.c(f, j, responseCallback);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void showBluetoothClaimDataDialog() {
        if (HandlerExecutor.b()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qvv
                @Override // java.lang.Runnable
                public final void run() {
                    qvq.this.showBluetoothClaimDataDialog();
                }
            });
            return;
        }
        cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
        if (currentUser != null) {
            LogUtil.a("WeightApiImpl", "showBluetoothClaimDataDialog currentUser ", currentUser.i());
        }
        ClaimWeightDataManager.INSTANCE.queryBluetoothClaimData();
    }

    @Override // com.huawei.health.weight.WeightApi
    public void initCardRedDot() {
        if (HandlerExecutor.b()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qvw
                @Override // java.lang.Runnable
                public final void run() {
                    qvq.this.initCardRedDot();
                }
            });
        } else {
            ClaimWeightDataManager.INSTANCE.initShowRed();
        }
    }

    @Override // com.huawei.health.weight.WeightApi
    public void saveWeightManagerAndGoal(ResponseCallback<Object> responseCallback, String str) {
        qsj.d(responseCallback, str);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void refreshDietOverview(int i) {
        ReleaseLogUtil.b("WeightApiImpl", "refreshDietOverview date ", Integer.valueOf(i));
        qvz.d(i, i, (ResponseCallback<List<quh>>) new ResponseCallback() { // from class: qvu
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i2, Object obj) {
                LogUtil.a("WeightApiImpl", "refreshDietOverview errorCode ", Integer.valueOf(i2), " list ", (List) obj);
            }
        });
    }

    @Override // com.huawei.health.weight.WeightApi
    public void getGoalDetail(ResponseCallback<gse> responseCallback) {
        qzw.e(responseCallback);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void calculateGoal(Bundle bundle, ResponseCallback<gsi> responseCallback) {
        qzw.dJr_(bundle, responseCallback);
    }

    @Override // com.huawei.health.weight.WeightApi
    public void saveWeightTargetDifferences(gsi gsiVar, long j) {
        qzw.b(gsiVar, j);
    }

    @Override // com.huawei.health.weight.WeightApi
    public gsh getOnePointFull(int i) {
        return dpe.c(i);
    }
}
