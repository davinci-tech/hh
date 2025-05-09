package com.huawei.health.weight;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.huawei.health.weight.bean.WeightTargetDifferences;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.ui.main.stories.health.weight.callback.DietDiaryCbk;
import com.huawei.ui.main.stories.health.weight.callback.WeightCallback;
import defpackage.cwj;
import defpackage.gse;
import defpackage.gsh;
import defpackage.gsi;
import defpackage.quh;
import defpackage.quj;
import defpackage.qul;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public interface WeightApi {
    void addMeal(qul qulVar, ResponseCallback<quh> responseCallback);

    void autoUpdateUserWeight(float f, long j, ResponseCallback<Object> responseCallback);

    void calculateGoal(Bundle bundle, ResponseCallback<gsi> responseCallback);

    void checkFatReductionShapingState();

    void getBasicDietRecord(int i, int i2, long j, ResponseCallback<List<quh>> responseCallback);

    void getBasicDietRecord(int i, int i2, ResponseCallback<List<quh>> responseCallback);

    double getBmiByHiHealthData(HiHealthData hiHealthData);

    String getChildHeightUpdateTime();

    Class<?> getCustomRecipe();

    void getDeviceSyncDietAnalysis(quh quhVar, ResponseCallback<cwj> responseCallback);

    Class<?> getDietDiaryRepositoryApi();

    void getDietRecord(int i, int i2, long j, ResponseCallback<List<quh>> responseCallback);

    void getDietRecord(int i, int i2, ResponseCallback<List<quh>> responseCallback);

    Class<?> getFastingLiteRepositoryApi();

    String getFastingLiteSetting();

    String getFastingLiteStatus();

    String getFastingLiteViewData();

    void getGoalDetail(ResponseCallback<gse> responseCallback);

    gsh getOnePointFull(int i);

    String getPeerAnalysis(String str);

    void getStartWeight(ResponseCallback<Double> responseCallback);

    void getStartWeightAndTargetWeight(ResponseCallback<HashMap<String, Double>> responseCallback);

    void getTargetWeight(ResponseCallback<Double> responseCallback);

    String getTrunkFatRateGrades(String str);

    int getWeightBondedProducts();

    void getWeightControlSetting(DietDiaryCbk<quj> dietDiaryCbk);

    void getWeightData(HiAggregateOption hiAggregateOption, WeightCallback weightCallback);

    void getWeightTargetDifferences(ResponseCallback<WeightTargetDifferences> responseCallback);

    void goToWeightDataPage(Map<String, Object> map);

    void gotoWeight(Bundle bundle);

    void gotoWeight(String str, Intent intent);

    void gotoWeight(String str, Bundle bundle);

    void initCardRedDot();

    void initGoalMotivationCache(ResponseCallback<WeightTargetDifferences> responseCallback);

    void initUser(IBaseResponseCallback iBaseResponseCallback);

    boolean isHuaweiWspDataTypeApi(int i);

    boolean isJoinDietDiary();

    boolean isSupportAiBodyShape();

    void isSupportDiet(boolean z, ResponseCallback<Boolean> responseCallback);

    void joinDietDiary();

    void recordMeals(List<qul> list, ResponseCallback<List<quh>> responseCallback);

    void refreshDietOverview(int i);

    void registerFastingLite();

    void saveChildHeightUpdateTime();

    void saveWeightManagerAndGoal(ResponseCallback<Object> responseCallback, String str);

    void saveWeightTargetDifferences(gsi gsiVar, long j);

    void setCurrentUser(String str);

    void showAutoUpdateWeightDialog(Context context, ResponseCallback<Integer> responseCallback);

    void showBindDeviceDialog(Context context, ResponseCallback<Integer> responseCallback);

    void showBluetoothClaimDataDialog();

    void showGoalMotivationDialog(Context context, ResponseCallback<Integer> responseCallback, float f, int i, String str);

    void showInputDialog(Context context, ResponseCallback<Integer> responseCallback);

    void showSetGoalDialog(Context context, ResponseCallback<Integer> responseCallback);

    void showUserInfoDialog(Context context, ResponseCallback<Integer> responseCallback);

    void showUserInfoDialogForMeasureFinish(Context context, ResponseCallback<Integer> responseCallback);

    void showUserInfoDialogForMeasureStart(Context context, ResponseCallback<Integer> responseCallback);

    void syncDietData();

    void updateMeal(qul qulVar, ResponseCallback<quh> responseCallback);

    void updateOnePointFull();
}
