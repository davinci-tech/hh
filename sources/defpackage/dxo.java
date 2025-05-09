package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.health.courseplanservice.api.RecordApi;
import com.huawei.health.healthdatamgr.api.HealthDataMgrApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.userlabelmgr.model.UpdateUserLabel;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import defpackage.dxe;
import defpackage.dxo;
import defpackage.kwy;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class dxo {
    private static Context r;
    private UpdateUserLabel q;
    private static final int[] k = {30006, 30005, 30007, 30021, 30023};
    private static final dxv[] f = {new dxv(1.0d, "g_m"), new dxv(0.0d, "g_f"), new dxv(2.0d, "g_x")};
    private static final dyb[] m = {new dyb(0.0d, 35.0d, "weight_0_35"), new dyb(35.0d, 40.0d, "weight_36_40"), new dyb(40.0d, 45.0d, "weight_41_45"), new dyb(45.0d, 50.0d, "weight_46_50"), new dyb(50.0d, 55.0d, "weight_51_55"), new dyb(55.0d, 60.0d, "weight_56_60"), new dyb(60.0d, 65.0d, "weight_61_65"), new dyb(65.0d, 70.0d, "weight_66_70"), new dyb(70.0d, 75.0d, "weight_71_75"), new dyb(75.0d, 80.0d, "weight_76_80"), new dyb(80.0d, 85.0d, "weight_81_85"), new dyb(85.0d, 90.0d, "weight_86_90"), new dyb(90.0d, 2.147483647E9d, "weight_90+")};
    private static final dyb[] h = {new dyb(0.0d, 18.5d, "SportWeight_0"), new dyb(18.5d, 24.0d, "SportWeight_1"), new dyb(24.0d, 28.0d, "SportWeight_2"), new dyb(28.0d, 40.0d, "SportWeight_3"), new dyb(40.0d, 2.147483647E9d, "SportWeight_4")};
    private static final dyb[] e = {new dyb(0.0d, 18.0d, "age_0_18"), new dyb(18.0d, 24.0d, "age_19_24"), new dyb(24.0d, 29.0d, "age_25_29"), new dyb(29.0d, 34.0d, "age_30_34"), new dyb(34.0d, 39.0d, "age_35_39"), new dyb(39.0d, 44.0d, "age_40_44"), new dyb(44.0d, 49.0d, "age_45_49"), new dyb(49.0d, 2.147483647E9d, "age_50+")};
    private static final dyb[] o = {new dyb(0.0d, 5000.0d, "SportStep_1"), new dyb(5000.0d, 10000.0d, "SportStep_2"), new dyb(10000.0d, 13000.0d, "SportStep_3"), new dyb(13000.0d, 2.147483647E9d, "SportStep_4")};
    private static final dyb[] l = {new dyb(0.0d, 2.0d, "SportRun_1"), new dyb(2.0d, 7.0d, "SportRun_2"), new dyb(7.0d, 12.0d, "SportRun_3"), new dyb(12.0d, 2.147483647E9d, "SportRun_4")};
    private static final dyb[] d = {new dyb(0.0d, 2.0d, "SportBike_1"), new dyb(2.0d, 7.0d, "SportBike_2"), new dyb(7.0d, 12.0d, "SportBike_3"), new dyb(12.0d, 2.147483647E9d, "SportBike_4")};
    private static final dyb[] b = {new dyb(0.0d, 2.0d, "BloodSugar_1"), new dyb(2.0d, 8.0d, "BloodSugar_2"), new dyb(8.0d, 2.147483647E9d, "BloodSugar_3")};
    private static final dyb[] c = {new dyb(0.0d, 2.0d, "BloodPressure_1"), new dyb(2.0d, 8.0d, "BloodPressure_2"), new dyb(8.0d, 2.147483647E9d, "BloodPressure_3")};
    private static final dyb[] j = {new dyb(0.0d, 7.0d, "SportLastRide_0"), new dyb(7.0d, 30.0d, "SportLastRide_1"), new dyb(30.0d, 60.0d, "SportLastRide_2"), new dyb(60.0d, 90.0d, "SportLastRide_3"), new dyb(90.0d, 2.147483647E9d, "SportLastRide_4")};
    private static final dyb[] i = {new dyb(0.0d, 7.0d, "SportLastRun_0"), new dyb(7.0d, 30.0d, "SportLastRun_1"), new dyb(30.0d, 60.0d, "SportLastRun_2"), new dyb(60.0d, 90.0d, "SportLastRun_3"), new dyb(90.0d, 2.147483647E9d, "SportLastRun_4")};

    /* renamed from: a, reason: collision with root package name */
    private static final dyb[] f11887a = {new dyb(-1.0E-6d, 0.0d, "SportUserApp_0"), new dyb(0.0d, 1.0d, "SportUserApp_1"), new dyb(1.0d, 2.0d, "SportUserApp_2"), new dyb(2.0d, 3.0d, "SportUserApp_3"), new dyb(3.0d, 8.0d, "SportUserApp_4"), new dyb(8.0d, 2.147483647E9d, "SportUserApp_5")};
    private static final dyb[] g = {new dyb(-1.0E-6d, 0.0d, "LastMaxVo2_none"), new dyb(0.0d, 28.0d, "LastMaxVo2_0_28"), new dyb(28.0d, 32.0d, "LastMaxVo2_28_32"), new dyb(32.0d, 38.0d, "LastMaxVo2_32_38"), new dyb(38.0d, 43.0d, "LastMaxVo2_38_43"), new dyb(43.0d, 48.0d, "LastMaxVo2_43_48"), new dyb(48.0d, 54.0d, "LastMaxVo2_48_54"), new dyb(54.0d, Double.MAX_VALUE, "LastMaxVo2_54+")};
    private static final dyb[] n = {new dyb(-1.0E-6d, 0.0d, "LastStressScore_none"), new dyb(0.0d, 30.0d, "LastStressScore_0_30"), new dyb(30.0d, 60.0d, "LastStressScore_30_60"), new dyb(60.0d, 80.0d, "LastStressScore_60_80"), new dyb(80.0d, 100.0d, "LastStressScore_80_100")};

    static class b {

        /* renamed from: a, reason: collision with root package name */
        public static final dxo f11896a = new dxo();
    }

    private double a(double d2, int i2) {
        double d3;
        if (i2 == 5) {
            d3 = 60000.0d;
        } else {
            if (i2 != 13) {
                return d2;
            }
            d3 = 60.0d;
        }
        return d2 / d3;
    }

    private dxo() {
    }

    public static dxo o() {
        LogUtil.a("BiUserLabelHelper", "BiUserLabelHelper getInstance");
        r = BaseApplication.e();
        return b.f11896a;
    }

    public void l() {
        LogUtil.a("BiUserLabelHelper", "registerCallback");
        this.q = new UpdateUserLabel() { // from class: dxr
            @Override // com.huawei.health.userlabelmgr.model.UpdateUserLabel
            public final void onUpdate() {
                dxo.this.n();
            }
        };
        dxw.a(r).b(this.q);
    }

    /* synthetic */ void n() {
        cpp.e(new Runnable() { // from class: dxo.3
            @Override // java.lang.Runnable
            public void run() {
                dxo.this.w();
            }
        }, 60000L);
    }

    public void m() {
        LogUtil.a("BiUserLabelHelper", "enter unRegisterCallback");
        if (this.q != null) {
            dxw.a(r).e(this.q);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        LogUtil.a("BiUserLabelHelper", "generateLabels");
        aj();
        t();
        af();
        ag();
        ah();
        q();
        x();
        v();
        p();
        k();
        r();
        ac();
        aa();
        ab();
        al();
        u();
        ai();
        am();
        s();
        ad();
        ae();
        y();
        an();
        z();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String ao() {
        return LoginInit.getInstance(r).getAccountInfo(1011);
    }

    private void y() {
        String ao = ao();
        if (CommonUtil.d(CommonUtil.g(HiHealthManager.d(r).getUserPreference("login_timestamp").getValue()))) {
            dxw.a(r).b("health_sport_is_new_user", "HealthNewUser", ao);
        } else {
            dxw.a(r).b("health_sport_is_new_user", "HealthOldUser", ao);
        }
    }

    private void aj() {
        HiHealthManager.d(r).fetchUserData(new HiCommonListener() { // from class: dxo.11
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i2, Object obj) {
                if (!(obj instanceof List) || i2 != 0) {
                    LogUtil.h("BiUserLabelHelper", "generateUserInfoLabel null");
                    return;
                }
                List list = (List) obj;
                if (list.isEmpty()) {
                    LogUtil.h("BiUserLabelHelper", "generateUserInfoLabel userInfos empty");
                    return;
                }
                LogUtil.a("BiUserLabelHelper", "generateUserInfoLabel, userInfos.size() = ", Integer.valueOf(list.size()));
                HiUserInfo hiUserInfo = (HiUserInfo) list.get(0);
                if (hiUserInfo != null) {
                    dxo.this.d(hiUserInfo);
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i2, Object obj) {
                LogUtil.a("BiUserLabelHelper", "enter generateUserInfoLabel onFailure, errCode = ", Integer.valueOf(i2), " errMsg = ", obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(HiUserInfo hiUserInfo) {
        if (hiUserInfo == null) {
            LogUtil.h("BiUserLabelHelper", "generateUserInfoLabel hiUserInfo null");
            return;
        }
        int age = hiUserInfo.getAge();
        int gender = hiUserInfo.getGender();
        float weight = hiUserInfo.getWeight();
        String ao = ao();
        dxw a2 = dxw.a(r);
        dyb[] dybVarArr = e;
        int length = dybVarArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            String matchResult = dybVarArr[i2].getMatchResult(age);
            if (matchResult != null) {
                a2.b("age", matchResult, ao);
                break;
            }
            i2++;
        }
        dxv[] dxvVarArr = f;
        int length2 = dxvVarArr.length;
        int i3 = 0;
        while (true) {
            if (i3 >= length2) {
                break;
            }
            String matchResult2 = dxvVarArr[i3].getMatchResult(gender);
            if (matchResult2 != null) {
                a2.b(CommonConstant.KEY_GENDER, matchResult2, ao);
                break;
            }
            i3++;
        }
        dyb[] dybVarArr2 = m;
        int length3 = dybVarArr2.length;
        int i4 = 0;
        while (true) {
            if (i4 >= length3) {
                break;
            }
            String matchResult3 = dybVarArr2[i4].getMatchResult(weight);
            if (matchResult3 != null) {
                a2.b("weight", matchResult3, ao);
                break;
            }
            i4++;
        }
        int height = hiUserInfo.getHeight();
        if (height != 0) {
            double d2 = height / 100.0d;
            double d3 = weight / (d2 * d2);
            dyg.e("BMI", Double.valueOf(d3).floatValue());
            for (dyb dybVar : h) {
                String matchResult4 = dybVar.getMatchResult(d3);
                if (matchResult4 != null) {
                    a2.b("health_sport_weight_size", matchResult4, ao);
                    return;
                }
            }
        }
    }

    private void t() {
        long currentTimeMillis = System.currentTimeMillis();
        long currentTimeMillis2 = System.currentTimeMillis();
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(currentTimeMillis - 2592000000L);
        hiAggregateOption.setEndTime(currentTimeMillis2);
        hiAggregateOption.setType(new int[]{2006});
        hiAggregateOption.setConstantsKey(new String[]{"BLOOD_PRESSURE_SYSTOLIC"});
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setAggregateType(2);
        hiAggregateOption.setGroupUnitType(7);
        hiAggregateOption.setSortOrder(1);
        HiHealthManager.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: dxo.19
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i2, int i3) {
                if (i2 != 0) {
                    LogUtil.h("BiUserLabelHelper", "generateBloodPressureLabel not success");
                } else if (koq.b(list)) {
                    LogUtil.h("BiUserLabelHelper", "generateBloodPressureLabel null");
                    dxw.a(dxo.r).b("health_blood_pressure_group_up", "BloodPressure_0", dxo.this.ao());
                } else {
                    dxw.a(dxo.r).b("health_blood_pressure_group_up", dxo.this.e("BloodPressure_0", dxo.c, ((Double) list.get(0).get("BLOOD_PRESSURE_SYSTOLIC")).doubleValue()), dxo.this.ao());
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
                LogUtil.c("BiUserLabelHelper", "generateBloodPressureLabel onResultIntent");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String e(String str, dyb[] dybVarArr, double d2) {
        for (dyb dybVar : dybVarArr) {
            str = dybVar.getMatchResult(d2);
            if (str != null) {
                break;
            }
        }
        return str;
    }

    private void af() {
        long currentTimeMillis = System.currentTimeMillis();
        long currentTimeMillis2 = System.currentTimeMillis();
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(currentTimeMillis - 2592000000L);
        hiAggregateOption.setEndTime(currentTimeMillis2);
        hiAggregateOption.setType(new int[]{40002});
        hiAggregateOption.setConstantsKey(new String[]{"sport_walk_step_sum"});
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(7);
        hiAggregateOption.setSortOrder(1);
        HiHealthManager.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: dxo.16
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i2, int i3) {
                if (i2 != 0) {
                    LogUtil.h("BiUserLabelHelper", "generateBloodPressureLabel not success");
                    return;
                }
                if (koq.b(list) || list.get(0) == null) {
                    LogUtil.h("BiUserLabelHelper", "generateBloodPressureLabel null");
                    dxw.a(dxo.r).b("health_sport_grade_up", "SportStep_0", dxo.this.ao());
                } else {
                    dxw.a(dxo.r).b("health_sport_grade_up", dxo.this.e("SportStep_0", dxo.o, list.get(0).getDouble("sport_walk_step_sum") / 30.0d), dxo.this.ao());
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
                LogUtil.c("BiUserLabelHelper", "generateSportStepLabel onResultIntent");
            }
        });
    }

    private void ag() {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        long currentTimeMillis = System.currentTimeMillis();
        long currentTimeMillis2 = System.currentTimeMillis();
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setStartTime(currentTimeMillis - 2592000000L);
        hiAggregateOption.setEndTime(currentTimeMillis2);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(7);
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setConstantsKey(new String[]{"Track_Run_Count_Sum"});
        hiAggregateOption.setType(new int[]{42105});
        HiHealthManager.d(r).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: dxo.18
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i2, int i3) {
                if (i2 != 0) {
                    LogUtil.h("BiUserLabelHelper", "generateSportRunLabel not success");
                    return;
                }
                if (koq.b(list) || list.get(0) == null) {
                    LogUtil.h("BiUserLabelHelper", "generateSportRunLabel datas null");
                    dxw.a(dxo.r).b("health_sport_freq_up", "SportRun_0", dxo.this.ao());
                } else {
                    LogUtil.a("BiUserLabelHelper", "generateSportRunLabel onResult ,data.size = ", Integer.valueOf(list.size()));
                    dxw.a(dxo.r).b("health_sport_freq_up", dxo.this.e("SportRun_0", dxo.l, list.get(0).getDouble("Track_Run_Count_Sum")), dxo.this.ao());
                }
            }
        });
    }

    private void ah() {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        long currentTimeMillis = System.currentTimeMillis();
        long currentTimeMillis2 = System.currentTimeMillis();
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setStartTime(currentTimeMillis - 2592000000L);
        hiAggregateOption.setEndTime(currentTimeMillis2);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(7);
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setConstantsKey(new String[]{"Track_Ride_Count_Sum"});
        hiAggregateOption.setType(new int[]{42155});
        HiHealthManager.d(r).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: dxo.25
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i2, int i3) {
                if (i2 != 0) {
                    LogUtil.h("BiUserLabelHelper", "generateSportBikeLabel not success");
                    return;
                }
                if (koq.b(list) || list.get(0) == null) {
                    LogUtil.h("BiUserLabelHelper", "generateSportBikeLabel null");
                    dxw.a(dxo.r).b("health_sport_bike_up", "SportBike_0", dxo.this.ao());
                } else {
                    LogUtil.h("BiUserLabelHelper", "requestSportBikeData onResult ,data.size = ", Integer.valueOf(list.size()));
                    double d2 = list.get(0).getDouble("Track_Ride_Count_Sum");
                    LogUtil.h("BiUserLabelHelper", "requestSportBikeData onResult, sportbiketimes = ", Double.valueOf(d2));
                    dxw.a(dxo.r).b("health_sport_bike_up", dxo.this.e("SportBike_0", dxo.d, d2), dxo.this.ao());
                }
            }
        });
    }

    private void q() {
        long currentTimeMillis = System.currentTimeMillis();
        long currentTimeMillis2 = System.currentTimeMillis();
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeInterval(currentTimeMillis - 2592000000L, currentTimeMillis2);
        hiAggregateOption.setType(new int[]{2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015});
        hiAggregateOption.setConstantsKey(new String[]{"bloodsugar_bf_before", "bloodsugar_bf_after", "bloodsugar_lc_before", "bloodsugar_lc_after", "bloodsugar_dn_before", "bloodsugar_dn_after", "bloodsugar_sl_before", "bloodsugar_before_dawn"});
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setReadType(0);
        HiHealthManager.d(r).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: dxo.23
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i2, int i3) {
                if (i2 != 0) {
                    LogUtil.h("BiUserLabelHelper", "generateBloodSugarLabel1 not success");
                    return;
                }
                if (koq.b(list)) {
                    LogUtil.h("BiUserLabelHelper", "generateBloodSugarLabel1 null");
                    dxw.a(dxo.r).b("health_blood_sugar_group_up", "BloodSugar_0", dxo.this.ao());
                } else {
                    int size = list.size();
                    LogUtil.h("BiUserLabelHelper", "generateBloodSugarLabel1 counts = ", Integer.valueOf(size));
                    dxw.a(dxo.r).b("health_blood_sugar_group_up", dxo.this.e("BloodSugar_0", dxo.b, size), dxo.this.ao());
                }
            }
        });
    }

    private void x() {
        final long currentTimeMillis = System.currentTimeMillis();
        ((HealthDataMgrApi) Services.c("HWHealthDataMgr", HealthDataMgrApi.class)).requestTrackSimplifyData(0L, currentTimeMillis, 259, new IBaseResponseCallback() { // from class: dxo.24
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (i2 == 0) {
                    dxw a2 = dxw.a(dxo.r);
                    String ao = dxo.this.ao();
                    if (obj == null) {
                        LogUtil.h("BiUserLabelHelper", "generateLastRideLabel objData null");
                        a2.b("health_sport_last_ride", "", ao);
                        return;
                    }
                    List list = (List) obj;
                    if (list.isEmpty() || list.get(0) == null) {
                        LogUtil.h("BiUserLabelHelper", "generateLastRideLabel size 0");
                        a2.b("health_sport_last_ride", "", ao);
                        return;
                    } else {
                        double endTime = (currentTimeMillis - ((HiHealthData) list.get(0)).getEndTime()) / 8.64E7d;
                        LogUtil.c("BiUserLabelHelper", "generateLastRideLabel days = ", Double.valueOf(endTime));
                        a2.b("health_sport_last_ride", dxo.this.e("", dxo.j, endTime), ao);
                        return;
                    }
                }
                LogUtil.h("BiUserLabelHelper", "generateLastRideLabel not success");
            }
        });
    }

    private void v() {
        final long currentTimeMillis = System.currentTimeMillis();
        ((HealthDataMgrApi) Services.c("HWHealthDataMgr", HealthDataMgrApi.class)).requestTrackSimplifyData(0L, currentTimeMillis, 258, new IBaseResponseCallback() { // from class: dxo.22
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (i2 == 0) {
                    dxw a2 = dxw.a(dxo.r);
                    String ao = dxo.this.ao();
                    if (obj == null) {
                        LogUtil.h("BiUserLabelHelper", "generateLastRideLabel objData null");
                        a2.b("health_sport_last_run", "", ao);
                        return;
                    }
                    List list = (List) obj;
                    if (list.isEmpty() || list.get(0) == null) {
                        LogUtil.h("BiUserLabelHelper", "generateLastRideLabel size 0");
                        a2.b("health_sport_last_run", "", ao);
                        return;
                    } else {
                        a2.b("health_sport_last_run", dxo.this.e("", dxo.i, (currentTimeMillis - ((HiHealthData) list.get(0)).getEndTime()) / 8.64E7d), ao);
                        return;
                    }
                }
                LogUtil.h("BiUserLabelHelper", "generateLastRideLabel not success");
            }
        });
    }

    private void p() {
        final long currentTimeMillis = System.currentTimeMillis();
        final long j2 = currentTimeMillis - 7776000000L;
        dxf.d().d(j2, currentTimeMillis, LoginInit.getInstance(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).getAccountInfo(1011), new IBaseResponseCallback() { // from class: dxo.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (i2 == 0 && (obj instanceof List)) {
                    List list = (List) obj;
                    LogUtil.c("BiUserLabelHelper", "generateCareLabel size = ", Integer.valueOf(list.size()));
                    int[] e2 = dxo.this.e((List<dxi>) list);
                    StringBuilder sb = new StringBuilder(16);
                    for (int i3 : e2) {
                        sb.append(i3);
                        sb.append(" ");
                    }
                    LogUtil.c("BiUserLabelHelper", "count = ", sb);
                    dxo.this.d(j2, currentTimeMillis, e2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int[] e(List<dxi> list) {
        int[] iArr = new int[4];
        for (dxi dxiVar : list) {
            if (dxiVar == null) {
                LogUtil.h("BiUserLabelHelper", "generateCareLabel record == null");
            } else {
                LogUtil.c("BiUserLabelHelper", "generateCareLabel getType = ", Integer.valueOf(dxiVar.b()));
                switch (dxiVar.b()) {
                    case 2010011:
                        iArr[1] = iArr[1] + 1;
                        break;
                    case 2010023:
                        iArr[0] = iArr[0] + 1;
                        break;
                    case 2010029:
                        iArr[2] = iArr[2] + 1;
                        break;
                    case 2010099:
                        iArr[3] = iArr[3] + 1;
                        break;
                    default:
                        LogUtil.h("BiUserLabelHelper", "generateCareLabel default");
                        break;
                }
            }
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(long j2, long j3, int[] iArr) {
        if (iArr == null || iArr.length != 4) {
            LogUtil.h("BiUserLabelHelper", "generateCareLabel cardType null or not 4");
            return;
        }
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = iArr[2];
        dxw a2 = dxw.a(r);
        String ao = ao();
        if (i2 > 10) {
            a2.b("health_sport_weight_care", "SportWeight_Care", ao);
        } else {
            b(j2, j3, 2004);
        }
        if (i3 > 10) {
            a2.b("health_sport_sleep_care", "SportSleep_Care", ao);
        } else {
            b(j2, j3, 44004);
        }
        if (i4 > 10) {
            a2.b("health_sport_heart_rate", "SportHeart_Care_0", ao);
        } else {
            b(j2, j3, 46016);
        }
        int i5 = iArr[3];
        if (i5 > 10) {
            a2.b("health_sport_blood_oxygen", "SportBlood_Care_0", ao);
        } else {
            b(j2, j3, 47201);
        }
        dyg.c(i2, i3, i4, i5);
    }

    private HiAggregateOption ak() {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeRange(System.currentTimeMillis() - 7776000000L, System.currentTimeMillis());
        hiAggregateOption.setType(new int[]{44006, 44007, 44004, 44001, 44002, 44003, 44005, 44201, 44202, 44105, 44102, 44103, 44104, 44107});
        hiAggregateOption.setConstantsKey(new String[]{"stat_sleep_start_time", "stat_sleep_end_time", "stat_sleep_duration_sum", "stat_sleep_deep_duration", "stat_sleep_shallow_duration", "stat_sleep_wake_duration", "stat_sleep_wake_count", "stat_out_core_sleep_fall_time", "stat_out_core_sleep_wake_up_time", "stat_core_sleep_duration_sum", "stat_core_sleep_deep_duration", "stat_core_sleep_shallow_duration", "stat_core_sleep_wake_duration", "stat_core_sleep_wake_count"});
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(3);
        return hiAggregateOption;
    }

    private HiAggregateOption a(long j2, long j3, int i2) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeRange(j2, j3);
        LogUtil.c("BiUserLabelHelper", "getAggregateOption query = ", Integer.valueOf(i2));
        if (i2 == 2004) {
            hiAggregateOption.setType(new int[]{2004});
            hiAggregateOption.setConstantsKey(new String[]{"bodyWeight"});
        } else if (i2 == 44004) {
            hiAggregateOption.setType(new int[]{44004, 44105});
            hiAggregateOption.setConstantsKey(new String[]{"stat_sleep_duration_sum", "stat_sleep_shallow_duration"});
        } else if (i2 == 46016) {
            hiAggregateOption.setType(new int[]{46016});
            hiAggregateOption.setConstantsKey(new String[]{"maxHeartRate"});
        } else if (i2 == 47201) {
            hiAggregateOption.setType(new int[]{47201});
            hiAggregateOption.setConstantsKey(new String[]{"maxBloodOxygenSaturation"});
        } else {
            LogUtil.c("BiUserLabelHelper", "getAggregateOption default");
        }
        hiAggregateOption.setAggregateType(2);
        hiAggregateOption.setGroupUnitType(3);
        return hiAggregateOption;
    }

    private void b(long j2, long j3, final int i2) {
        HiHealthNativeApi.a(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).aggregateHiHealthData(a(j2, j3, i2), new HiAggregateListener() { // from class: dxo.1
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i3, int i4) {
                if (i3 != 0 || koq.b(list)) {
                    LogUtil.h("BiUserLabelHelper", "generateHeartRateCareLabelFromHealth errorCode = ", Integer.valueOf(i3));
                    dxo.this.b(i2, false);
                } else {
                    int size = list.size();
                    LogUtil.c("BiUserLabelHelper", "generateHeartRateCareLabelFromHealth count = ", Integer.valueOf(size), "query = ", Integer.valueOf(i2));
                    dxo.this.b(i2, size > 10);
                    dyg.e(i2, size);
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i3, List<HiHealthData> list, int i4, int i5) {
                LogUtil.c("BiUserLabelHelper", "generateHeartRateCareLabelFromHealth onResultIntent");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i2, boolean z) {
        LogUtil.c("BiUserLabelHelper", "generateCareLabelFromHealth query = ", Integer.valueOf(i2), " hasData = ", Boolean.valueOf(z));
        dxw a2 = dxw.a(com.huawei.hwcommonmodel.application.BaseApplication.getContext());
        String ao = ao();
        if (i2 == 2004) {
            if (z) {
                a2.b("health_sport_weight_care", "SportWeight_Care", ao);
                return;
            } else {
                a2.b("health_sport_weight_care", "", ao);
                return;
            }
        }
        if (i2 == 44004) {
            if (z) {
                a2.b("health_sport_sleep_care", "SportSleep_Care", ao);
                return;
            } else {
                a2.b("health_sport_sleep_care", "", ao);
                return;
            }
        }
        if (i2 == 46016) {
            if (z) {
                a2.b("health_sport_heart_rate", "SportHeart_Care_0", ao);
                return;
            } else {
                a2.b("health_sport_heart_rate", "", ao);
                return;
            }
        }
        if (i2 != 47201) {
            LogUtil.c("BiUserLabelHelper", "generateCareLabelFromHealth default");
        } else if (z) {
            a2.b("health_sport_blood_oxygen", "SportBlood_Care_0", ao);
        } else {
            a2.b("health_sport_blood_oxygen", "", ao);
        }
    }

    private void k() {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            dxf.d().c(currentTimeMillis - 2592000000L, currentTimeMillis, ao(), Integer.parseInt(AnalyticsValue.HOME_1010001.value()), new IBaseResponseCallback() { // from class: dxs
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    dxo.this.c(i2, obj);
                }
            });
        } catch (NumberFormatException unused) {
            LogUtil.b("BiUserLabelHelper", "generateAppUseFrequencyLabel, Exception.");
        }
    }

    /* synthetic */ void c(int i2, Object obj) {
        if (i2 != 0 || !(obj instanceof Integer)) {
            LogUtil.c("BiUserLabelHelper", "generateAppUseFrequencyLabel errorCode = ", Integer.valueOf(i2));
            return;
        }
        int intValue = ((Integer) obj).intValue();
        LogUtil.c("BiUserLabelHelper", "generateAppUseFrequencyLabel useApp count = ", Integer.valueOf(intValue));
        String str = null;
        for (dyb dybVar : f11887a) {
            str = dybVar.getMatchResult(intValue);
            if (str != null) {
                break;
            }
        }
        dxw.a(r).b("health_sport_user_app", str, ao());
    }

    private void r() {
        long currentTimeMillis = System.currentTimeMillis();
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(currentTimeMillis - 31104000000L, currentTimeMillis);
        hiDataReadOption.setType(new int[]{30007});
        hiDataReadOption.setSortOrder(1);
        HiHealthManager.d(r).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: dxo.5
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i2, int i3) {
                if (i2 == 0) {
                    dxw a2 = dxw.a(dxo.r);
                    String ao = dxo.this.ao();
                    if (!(obj instanceof SparseArray)) {
                        a2.b("health_sport_ride_up", "SportRide_4", ao);
                        return;
                    }
                    SparseArray sparseArray = (SparseArray) obj;
                    if (sparseArray.size() <= 0) {
                        a2.b("health_sport_ride_up", "SportRide_4", ao);
                        return;
                    }
                    Object obj2 = sparseArray.get(30007);
                    if (!(obj2 instanceof List)) {
                        a2.b("health_sport_ride_up", "SportRide_4", ao);
                        return;
                    }
                    List list = (List) obj2;
                    if (koq.b(list)) {
                        a2.b("health_sport_ride_up", "SportRide_4", ao);
                        return;
                    } else {
                        a2.b("health_sport_ride_up", dxo.this.d((List<HiHealthData>) list), ao);
                        return;
                    }
                }
                LogUtil.h("BiUserLabelHelper", "generateBikeIntensiveLabel error = ", Integer.valueOf(i2));
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i2, Object obj, int i3, int i4) {
                LogUtil.a("BiUserLabelHelper", "generateBikeIntensiveLabel onResultIntent");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String d(List<HiHealthData> list) {
        if (koq.b(list)) {
            LogUtil.h("BiUserLabelHelper", "getBikeIntensiveLabel empty");
            return null;
        }
        int[] iArr = new int[4];
        boolean z = false;
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2) != null) {
                boolean z2 = i2 == 0 ? System.currentTimeMillis() - list.get(i2).getEndTime() > 2592000000L : z;
                d(list, iArr, i2, 20000, SmartMsgConstant.MSG_TYPE_REDUCE_FAT_USER);
                z = z2;
            }
        }
        LogUtil.c("BiUserLabelHelper", "getBikeIntensiveLabel", Boolean.valueOf(z), Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]), Integer.valueOf(iArr[2]), Integer.valueOf(iArr[3]));
        return a(iArr, z);
    }

    private String a(int[] iArr, boolean z) {
        StringBuilder sb = new StringBuilder(16);
        int length = iArr.length;
        boolean z2 = false;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            if (iArr[i2] != 0) {
                z2 = true;
                break;
            }
            i2++;
        }
        c(iArr, sb);
        if (z && z2) {
            sb.append(",");
            sb.append("SportRide_4");
        } else if (z) {
            sb.append("SportRide_4");
        } else {
            LogUtil.c("BiUserLabelHelper", "getBikeIntensiveLabel else branch");
        }
        return sb.toString();
    }

    private StringBuilder c(int[] iArr, StringBuilder sb) {
        int i2;
        if (iArr[3] > 0) {
            sb.append("SportRide_3");
            return sb;
        }
        int i3 = iArr[2];
        if (i3 > 2) {
            sb.append("SportRide_2");
            return sb;
        }
        if (i3 > 0 || (i2 = iArr[1]) > 3) {
            sb.append("SportRide_1");
            return sb;
        }
        if (i2 <= 0 && iArr[0] <= 0) {
            return sb;
        }
        sb.append("SportRide_0");
        return sb;
    }

    private void ac() {
        HiHealthManager.d(r).aggregateHiHealthData(ak(), new HiAggregateListener() { // from class: dxo.2
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i2, int i3) {
                if (i2 != 0 || koq.b(list)) {
                    LogUtil.c("BiUserLabelHelper", "generateSleepLabel errorCode = ", Integer.valueOf(i2));
                    dxw.a(dxo.r).b("health_sport_sleep_quality", "", dxo.this.ao());
                } else {
                    LogUtil.c("BiUserLabelHelper", "generateSleepLabel size = ", Integer.valueOf(list.size()));
                    dxo.this.c(list);
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
                LogUtil.a("BiUserLabelHelper", "generateSleepLabel onResultIntent");
            }
        });
    }

    private void b(int[] iArr) {
        if (iArr == null || iArr.length != 5) {
            return;
        }
        LogUtil.c("BiUserLabelHelper", "generateSleepLabel counter", Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]), Integer.valueOf(iArr[2]), Integer.valueOf(iArr[3]), Integer.valueOf(iArr[4]));
        StringBuilder sb = new StringBuilder(16);
        if (iArr[1] > 20) {
            sb.append("SportSleep_0");
        }
        if (iArr[2] > 10) {
            if (TextUtils.isEmpty(sb)) {
                sb.append("SportSleep_1");
            } else {
                sb.append(",");
                sb.append("SportSleep_1");
            }
        }
        if (iArr[3] > 20) {
            if (TextUtils.isEmpty(sb)) {
                sb.append("SportSleep_2");
            } else {
                sb.append(",");
                sb.append("SportSleep_2");
            }
        }
        if (iArr[4] > 20) {
            if (TextUtils.isEmpty(sb)) {
                sb.append("SportSleep_3");
            } else {
                sb.append(",");
                sb.append("SportSleep_3");
            }
        }
        dxw.a(r).b("health_sport_sleep_quality", sb.toString(), ao());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<HiHealthData> list) {
        int[] iArr = new int[5];
        for (HiHealthData hiHealthData : list) {
            long j2 = hiHealthData.getLong("stat_out_core_sleep_fall_time");
            long j3 = hiHealthData.getLong("stat_out_core_sleep_wake_up_time");
            int i2 = hiHealthData.getInt("stat_core_sleep_duration_sum");
            int i3 = hiHealthData.getInt("stat_core_sleep_deep_duration");
            int i4 = hiHealthData.getInt("stat_core_sleep_wake_count");
            if (i2 <= 0) {
                i2 = hiHealthData.getInt("stat_sleep_duration_sum") / 60;
                i3 = hiHealthData.getInt("stat_sleep_deep_duration") / 60;
                i4 = hiHealthData.getInt("stat_sleep_wake_count");
            }
            long e2 = nsj.e(j2, 0);
            if (j3 - e2 > 32400000) {
                iArr[0] = iArr[0] + 1;
            }
            if (j2 > e2) {
                iArr[1] = iArr[1] + 1;
            }
            if (i4 > 2) {
                iArr[2] = iArr[2] + 1;
            }
            if (i2 <= 300) {
                iArr[3] = iArr[3] + 1;
            }
            if (i3 <= 90) {
                iArr[4] = iArr[4] + 1;
            }
        }
        b(iArr);
    }

    private void aa() {
        long currentTimeMillis = System.currentTimeMillis();
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(currentTimeMillis - 31104000000L, currentTimeMillis);
        hiDataReadOption.setType(new int[]{30006, 30005});
        hiDataReadOption.setSortOrder(1);
        HiHealthManager.d(r).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: dxo.7
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i2, int i3) {
                SparseArray aav_ = dxo.this.aav_(obj, i2);
                dxw a2 = dxw.a(dxo.r);
                String ao = dxo.this.ao();
                if (aav_ == null || aav_.size() <= 0) {
                    a2.b("health_sport_strength_up", "SportStrength_5", ao);
                    return;
                }
                Object obj2 = aav_.get(30006);
                Object obj3 = aav_.get(30005);
                if (!(obj2 instanceof List) && !(obj3 instanceof List)) {
                    a2.b("health_sport_strength_up", "SportStrength_5", ao);
                } else {
                    a2.b("health_sport_strength_up", dxo.this.e((List<HiHealthData>) obj2, (List<HiHealthData>) obj3), ao);
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i2, Object obj, int i3, int i4) {
                LogUtil.a("BiUserLabelHelper", "generateRunIntensiveLabel onResultIntent");
            }
        });
    }

    private void ab() {
        long currentTimeMillis = System.currentTimeMillis();
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(currentTimeMillis - 2592000000L, currentTimeMillis);
        hiDataReadOption.setType(new int[]{42006});
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setCount(1);
        HiHealthManager.d(r).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: dxo.8
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i2, int i3) {
                SparseArray aav_ = dxo.this.aav_(obj, i2);
                dxw a2 = dxw.a(dxo.r);
                String ao = dxo.this.ao();
                if (aav_ == null || aav_.size() == 0) {
                    LogUtil.h("BiUserLabelHelper", "generateMaxVo2Label map is null or map.size() is zero");
                    a2.b("health_sport_max_vo2", "LastMaxVo2_none", ao);
                    return;
                }
                Object obj2 = aav_.get(42006);
                if (!(obj2 instanceof List)) {
                    LogUtil.h("BiUserLabelHelper", "generateMaxVo2Label maxVo2Object is null or not list");
                    a2.b("health_sport_max_vo2", "LastMaxVo2_none", ao);
                    return;
                }
                List list = (List) obj2;
                if (koq.b(list) || list.get(0) == null) {
                    LogUtil.h("BiUserLabelHelper", "generateMaxVo2Label maxVo2List size 0");
                    a2.b("health_sport_max_vo2", "LastMaxVo2_none", ao);
                } else {
                    a2.b("health_sport_max_vo2", dxo.this.e("LastMaxVo2_none", dxo.g, ((HiHealthData) list.get(0)).getValue()), ao);
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i2, Object obj, int i3, int i4) {
                LogUtil.a("BiUserLabelHelper", "generateMaxVo2Label onResultIntent");
            }
        });
    }

    private void al() {
        long currentTimeMillis = System.currentTimeMillis();
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(currentTimeMillis - 2592000000L, currentTimeMillis);
        hiDataReadOption.setType(new int[]{44306});
        HiHealthManager.d(r).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: dxo.9
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i2, int i3) {
                SparseArray aav_ = dxo.this.aav_(obj, i2);
                dxw a2 = dxw.a(dxo.r);
                String ao = dxo.this.ao();
                if (aav_ == null || aav_.size() == 0) {
                    LogUtil.h("BiUserLabelHelper", "generateStressLabel map is null or map.size() is zero");
                    a2.b("health_sport_stress_average", "LastStressScore_none", ao);
                    return;
                }
                Object obj2 = aav_.get(44306);
                if (obj2 instanceof List) {
                    a2.b("health_sport_stress_average", dxo.this.e("LastStressScore_none", dxo.n, dxo.this.a((List) obj2)), ao);
                } else {
                    LogUtil.h("BiUserLabelHelper", "generateStressLabel stressScoreObject is null or not list");
                    a2.b("health_sport_stress_average", "LastStressScore_none", ao);
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i2, Object obj, int i3, int i4) {
                LogUtil.a("BiUserLabelHelper", "generateStressLabel onResultIntent");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public double a(List<HiHealthData> list) {
        if (koq.b(list)) {
            LogUtil.h("BiUserLabelHelper", "getAverageData hiHealthDataList is empty or size is zero");
            return 0.0d;
        }
        int i2 = 0;
        double d2 = 0.0d;
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData.getValue() > 0.0d) {
                d2 += hiHealthData.getValue();
                i2++;
            }
        }
        if (i2 != 0) {
            return d2 / i2;
        }
        LogUtil.h("BiUserLabelHelper", "generateStressLabel count is 0");
        return 0.0d;
    }

    private void u() {
        long currentTimeMillis = System.currentTimeMillis();
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(currentTimeMillis - 2592000000L, currentTimeMillis);
        hiDataReadOption.setType(new int[]{44004, 44105});
        HiHealthManager.d(r).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: dxo.10
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i2, int i3) {
                SparseArray aav_ = dxo.this.aav_(obj, i2);
                dxw a2 = dxw.a(dxo.r);
                String ao = dxo.this.ao();
                if (aav_ == null || aav_.size() <= 0) {
                    a2.b("health_sport_sleep_average", "", ao);
                    return;
                }
                double c2 = dxo.this.c(aav_.get(44004), aav_.get(44105));
                if (c2 == 0.0d) {
                    LogUtil.h("BiUserLabelHelper", "generateLastSleepLabel lastSleepAverage is zero");
                    a2.b("health_sport_sleep_average", "", ao);
                } else {
                    a2.b("health_sport_sleep_average", String.valueOf((int) UnitUtil.a(c2, 0)), ao);
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i2, Object obj, int i3, int i4) {
                LogUtil.a("BiUserLabelHelper", "generateLastSleepLabel onResultIntent");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public double c(Object obj, Object obj2) {
        ArrayList<HiHealthData> arrayList = new ArrayList(60);
        if (obj instanceof List) {
            List list = (List) obj;
            if (koq.c(list)) {
                arrayList.addAll(list);
            }
        }
        if (obj2 instanceof List) {
            List list2 = (List) obj2;
            if (koq.c(list2)) {
                arrayList.addAll(list2);
            }
        }
        if (koq.b(arrayList)) {
            LogUtil.h("BiUserLabelHelper", "getSleepAverageData lastSleepList is empty");
            return 0.0d;
        }
        HashMap hashMap = new HashMap();
        for (HiHealthData hiHealthData : arrayList) {
            double a2 = a(hiHealthData.getValue(), hiHealthData.getPointUnit());
            if (!hashMap.containsKey(Long.valueOf(hiHealthData.getDay()))) {
                hashMap.put(Long.valueOf(hiHealthData.getDay()), Double.valueOf(a2));
            } else if (((Double) hashMap.get(Long.valueOf(hiHealthData.getDay()))).doubleValue() < a2) {
                hashMap.put(Long.valueOf(hiHealthData.getDay()), Double.valueOf(a2));
            }
        }
        int i2 = 0;
        double d2 = 0.0d;
        for (Map.Entry entry : hashMap.entrySet()) {
            if (entry.getValue() != null && ((Double) entry.getValue()).doubleValue() > 0.0d) {
                d2 += ((Double) entry.getValue()).doubleValue();
                i2++;
            }
        }
        if (i2 != 0) {
            return d2 / i2;
        }
        LogUtil.h("BiUserLabelHelper", "getSleepAverageData count is 0");
        return 0.0d;
    }

    private void ai() {
        long currentTimeMillis = System.currentTimeMillis();
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(currentTimeMillis - 2592000000L, currentTimeMillis);
        hiDataReadOption.setType(new int[]{30006, 30005, 30007, 30021});
        HiHealthManager.d(r).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: dxo.6
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i2, int i3) {
                SparseArray aav_ = dxo.this.aav_(obj, i2);
                dxw a2 = dxw.a(dxo.r);
                String ao = dxo.this.ao();
                if (aav_ == null || aav_.size() <= 0) {
                    LogUtil.h("BiUserLabelHelper", "generateSportLabel sportMap is null or empty");
                    a2.b("health_sport_average_pace", "", ao);
                    a2.b("health_sport_walk_weekday", "", ao);
                    a2.b("health_sport_run_weekday", "", ao);
                    a2.b("health_sport_ride_weekday", "", ao);
                    return;
                }
                a2.b("health_sport_average_pace", Arrays.toString(new double[]{UnitUtil.a(dxo.this.e(aav_.get(30006)), 2), UnitUtil.a(dxo.this.e(aav_.get(30005)), 2), UnitUtil.a(dxo.this.e(aav_.get(30007)), 2), UnitUtil.a(dxo.this.e(aav_.get(30021)), 2)}), ao);
                dxo.this.a("health_sport_walk_weekday", aav_.get(30005));
                dxo.this.a("health_sport_run_weekday", aav_.get(30006));
                dxo.this.a("health_sport_ride_weekday", aav_.get(30007));
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i2, Object obj, int i3, int i4) {
                LogUtil.a("BiUserLabelHelper", "generateSportLabel onResultIntent");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public double e(Object obj) {
        if (!(obj instanceof List)) {
            LogUtil.h("BiUserLabelHelper", "parsingObject sportObject is null or not list");
            return 0.0d;
        }
        List list = (List) obj;
        if (koq.b(list)) {
            LogUtil.h("BiUserLabelHelper", "parsingObject sportList is empty");
            return 0.0d;
        }
        Iterator it = list.iterator();
        int i2 = 0;
        double d2 = 0.0d;
        while (it.hasNext()) {
            HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(((HiHealthData) it.next()).getMetaData(), HiTrackMetaData.class);
            if (hiTrackMetaData != null && hiTrackMetaData.getAbnormalTrack() == 0 && hiTrackMetaData.getDuplicated() == 0) {
                double a2 = CommonUtil.a(String.valueOf(hiTrackMetaData.getAvgPace()));
                if (a2 > 0.0d) {
                    d2 += a2;
                    i2++;
                }
            }
        }
        if (i2 != 0) {
            return (d2 / 60.0d) / i2;
        }
        return 0.0d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, Object obj) {
        dxw a2 = dxw.a(r);
        String ao = ao();
        if (!(obj instanceof List)) {
            LogUtil.h("BiUserLabelHelper", "getSportWeekday sportObject is null or not list");
            a2.b(str, "", ao);
            return;
        }
        List<HiHealthData> list = (List) obj;
        if (koq.b(list)) {
            LogUtil.h("BiUserLabelHelper", "getSportWeekday sportList is empty");
            a2.b(str, "", ao);
            return;
        }
        int[] iArr = new int[7];
        for (HiHealthData hiHealthData : list) {
            HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiTrackMetaData.class);
            if (hiTrackMetaData != null && hiTrackMetaData.getAbnormalTrack() == 0 && hiTrackMetaData.getDuplicated() == 0) {
                int b2 = b(hiHealthData.getStartTime());
                int b3 = b(hiHealthData.getEndTime());
                LogUtil.a("BiUserLabelHelper", "getSportWeekday startIndex = ", Integer.valueOf(b2), ", endIndex = ", Integer.valueOf(b3));
                if (b2 == b3) {
                    iArr[b2] = iArr[b2] + 1;
                } else {
                    a(b2, b3, iArr);
                }
            }
        }
        a2.b(str, Arrays.toString(iArr), ao);
    }

    private int b(long j2) {
        Date date = new Date();
        date.setTime(j2);
        Calendar.getInstance().setTime(date);
        return r2.get(7) - 1;
    }

    private void a(int i2, int i3, int[] iArr) {
        if (i2 < i3) {
            while (i2 <= i3) {
                iArr[i2] = iArr[i2] + 1;
                i2++;
            }
        } else {
            while (i2 <= i3 + 7) {
                int i4 = i2 >= 7 ? i2 - 7 : i2;
                iArr[i4] = iArr[i4] + 1;
                i2++;
            }
        }
    }

    private void am() {
        long currentTimeMillis = System.currentTimeMillis();
        long currentTimeMillis2 = System.currentTimeMillis();
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(currentTimeMillis - 1123200000, currentTimeMillis2);
        hiDataReadOption.setType(new int[]{40002});
        HiHealthManager.d(r).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: dxo.12
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i2, int i3) {
                SparseArray aav_ = dxo.this.aav_(obj, i2);
                dxw a2 = dxw.a(dxo.r);
                String ao = dxo.this.ao();
                if (aav_ == null || aav_.size() <= 0) {
                    LogUtil.h("BiUserLabelHelper", "generateStepLabel stepMap is null or empty");
                    a2.b("health_sport_step_average", "", ao);
                    return;
                }
                Object obj2 = aav_.get(40002);
                if (!(obj2 instanceof List)) {
                    LogUtil.h("BiUserLabelHelper", "generateStepLabel stepObject is null or not list");
                    a2.b("health_sport_step_average", "", ao);
                } else {
                    a2.b("health_sport_step_average", String.valueOf((int) UnitUtil.a(dxo.this.a((List) obj2), 0)), ao);
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i2, Object obj, int i3, int i4) {
                LogUtil.a("BiUserLabelHelper", "generateStepLabel onResultIntent");
            }
        });
    }

    private void s() {
        long currentTimeMillis = System.currentTimeMillis();
        final long currentTimeMillis2 = System.currentTimeMillis();
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(currentTimeMillis - Constants.TWO_WEEK, currentTimeMillis2);
        hiDataReadOption.setType(new int[]{47101});
        HiHealthManager.d(r).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: dxo.14
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i2, int i3) {
                SparseArray aav_ = dxo.this.aav_(obj, i2);
                dxw a2 = dxw.a(dxo.r);
                String ao = dxo.this.ao();
                if (aav_ == null || aav_.size() <= 0) {
                    LogUtil.h("BiUserLabelHelper", "generateExerciseIntensityLabel exerciseIntensityMap is null or empty");
                    a2.b("health_sport_exercise_intensity_average", "", ao);
                    return;
                }
                Object obj2 = aav_.get(47101);
                if (!(obj2 instanceof List)) {
                    LogUtil.h("BiUserLabelHelper", "generateExerciseIntensityLabel exerciseIntensityObject is null or not list");
                    a2.b("health_sport_exercise_intensity_average", "", ao);
                } else {
                    a2.b("health_sport_exercise_intensity_average", String.valueOf(dxo.this.c((List<HiHealthData>) obj2, currentTimeMillis2)), ao);
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i2, Object obj, int i3, int i4) {
                LogUtil.a("BiUserLabelHelper", "generateExerciseIntensityLabel onResultIntent");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public double c(List<HiHealthData> list, long j2) {
        int i2;
        double d2;
        if (koq.b(list)) {
            LogUtil.h("BiUserLabelHelper", "getWeekAverageData hiHealthDataList is empty or size is zero");
            return 0.0d;
        }
        boolean[] zArr = new boolean[2];
        long j3 = j2 - 604800000;
        Iterator<HiHealthData> it = list.iterator();
        double d3 = 0.0d;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            HiHealthData next = it.next();
            long startTime = next.getStartTime();
            if (startTime > j2 || startTime < j3) {
                if (startTime >= j3 || startTime < j2 - Constants.TWO_WEEK) {
                    LogUtil.h("BiUserLabelHelper", "getWeekAverageData hiHealthData.getStartTime() is more than two weeks");
                } else if (next.getValue() > 0.0d) {
                    zArr[1] = true;
                    d2 = next.getValue();
                    d3 += d2;
                }
            } else if (next.getValue() > 0.0d) {
                zArr[0] = true;
                d2 = next.getValue();
                d3 += d2;
            }
        }
        int i3 = 0;
        for (i2 = 0; i2 < 2; i2++) {
            if (zArr[i2]) {
                i3++;
            }
        }
        if (i3 != 0) {
            return d3 / i3;
        }
        LogUtil.h("BiUserLabelHelper", "getWeekAverageData two weeks don't have data");
        return 0.0d;
    }

    private void z() {
        boolean j2 = EnvironmentInfo.j();
        LogUtil.a("BiUserLabelHelper", "isHarmony4AndLater:", Boolean.valueOf(j2));
        if (!j2) {
            e(dxy.b().a());
        } else {
            dxy.b().e(new BaseResponseCallback() { // from class: dxt
                @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
                public final void onResponse(int i2, Object obj) {
                    dxo.this.e(i2, (HashMap) obj);
                }
            });
        }
    }

    /* synthetic */ void e(int i2, HashMap hashMap) {
        if (CollectionUtils.e(hashMap)) {
            LogUtil.h("BiUserLabelHelper", "data isEmpty ");
        } else {
            e((HashMap<String, String>) hashMap);
            LogUtil.c("BiUserLabelHelper", "newKeyAndValueHashMap:", hashMap.toString());
        }
    }

    private void e(HashMap<String, String> hashMap) {
        dxw a2 = dxw.a(r);
        String ao = ao();
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            a2.b(entry.getKey(), entry.getValue(), ao);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Object obj, Object obj2, int i2) {
        SparseArray<Object> aav_ = aav_(obj2, i2);
        String[] strArr = new String[6];
        if (aav_ == null || aav_.size() <= 0) {
            LogUtil.h("BiUserLabelHelper", "getAverageSportTimeLabel sportMap is null or empty");
            strArr[0] = "";
            strArr[1] = "";
            strArr[2] = "";
            strArr[4] = "";
            strArr[5] = "";
        } else {
            aaz_(strArr, aav_);
        }
        if (obj == null || !koq.e(obj, WorkoutRecord.class)) {
            strArr[3] = "";
        } else {
            List<WorkoutRecord> list = (List) obj;
            if (koq.b(list)) {
                strArr[3] = "";
            } else {
                c(strArr, list);
            }
        }
        dxw.a(r).b("health_sport_average_time_month", Arrays.toString(strArr), ao());
    }

    private void aaz_(String[] strArr, SparseArray<Object> sparseArray) {
        aax_(strArr, 30006, sparseArray, 0);
        aax_(strArr, 30005, sparseArray, 1);
        aax_(strArr, 30007, sparseArray, 2);
        aax_(strArr, 30021, sparseArray, 4);
        aax_(strArr, 30023, sparseArray, 5);
    }

    private void aax_(String[] strArr, int i2, SparseArray<Object> sparseArray, int i3) {
        Object obj = sparseArray.get(i2);
        if (obj instanceof List) {
            if (koq.b((List) obj)) {
                strArr[i3] = "";
                return;
            }
            strArr[i3] = String.valueOf(UnitUtil.a(j(r4) / r4.size(), 2));
            return;
        }
        strArr[i3] = "";
    }

    private long j(List<HiHealthData> list) {
        Iterator<HiHealthData> it = list.iterator();
        long j2 = 0;
        while (it.hasNext()) {
            try {
                HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(it.next().getMetaData(), HiTrackMetaData.class);
                if (hiTrackMetaData == null) {
                    LogUtil.h("BiUserLabelHelper", "getMetaData is null");
                } else {
                    int abnormalTrack = hiTrackMetaData.getAbnormalTrack();
                    int duplicated = hiTrackMetaData.getDuplicated();
                    if (abnormalTrack == 0 && duplicated == 0) {
                        j2 += hiTrackMetaData.getTotalTime();
                    } else {
                        LogUtil.h("BiUserLabelHelper", "getTrackMetaData is abnormal or repetition");
                    }
                }
            } catch (JsonSyntaxException unused) {
                LogUtil.h("BiUserLabelHelper", "getSportTotalTime trackMetaData is error");
            }
        }
        return j2;
    }

    private void c(String[] strArr, List<WorkoutRecord> list) {
        if (koq.b(list)) {
            strArr[3] = "";
            return;
        }
        long j2 = 0;
        while (list.iterator().hasNext()) {
            j2 += r0.next().getDuration();
        }
        double size = list.size();
        if (size == 0.0d) {
            strArr[3] = "";
        } else {
            strArr[3] = String.valueOf(UnitUtil.a(j2 / size, 2));
        }
    }

    private void ad() {
        long currentTimeMillis = System.currentTimeMillis();
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(currentTimeMillis - 604800000, currentTimeMillis);
        hiDataReadOption.setType(k);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setCount(1);
        HiHealthManager.d(r).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: dxo.13
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i2, int i3) {
                SparseArray aav_ = dxo.this.aav_(obj, i2);
                dxw a2 = dxw.a(dxo.r);
                if (aav_ != null && aav_.size() > 0) {
                    dxo.this.aaw_(a2, aav_);
                } else {
                    LogUtil.h("BiUserLabelHelper", "generateLastSportTimeRecoveryLabel sportMap is null or empty");
                    a2.b("health_seven_day_last_sport_recovery_time", "", dxo.this.ao());
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i2, Object obj, int i3, int i4) {
                LogUtil.a("BiUserLabelHelper", "generateLastSportTimeRecoveryLabel onResultIntent");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aaw_(dxw dxwVar, SparseArray<Object> sparseArray) {
        Object obj;
        int i2 = 0;
        while (true) {
            int[] iArr = k;
            if (i2 >= iArr.length) {
                obj = null;
                break;
            } else {
                if (sparseArray.get(iArr[i2]) != null) {
                    obj = sparseArray.get(iArr[i2]);
                    break;
                }
                i2++;
            }
        }
        if (obj instanceof List) {
            List<HiHealthData> list = (List) obj;
            if (koq.c(list)) {
                int i3 = i(list);
                if (i3 == 0) {
                    dxwVar.b("health_seven_day_last_sport_recovery_time", "", ao());
                    return;
                } else {
                    dxwVar.b("health_seven_day_last_sport_recovery_time", String.valueOf(i3), ao());
                    return;
                }
            }
            dxwVar.b("health_seven_day_last_sport_recovery_time", "", ao());
            return;
        }
        dxwVar.b("health_seven_day_last_sport_recovery_time", "", ao());
    }

    private int i(List<HiHealthData> list) {
        Map<String, Integer> wearSportData;
        if (koq.b(list)) {
            return 0;
        }
        Iterator<HiHealthData> it = list.iterator();
        while (true) {
            int i2 = 0;
            while (it.hasNext()) {
                try {
                    wearSportData = ((HiTrackMetaData) HiJsonUtil.e(it.next().getMetaData(), HiTrackMetaData.class)).getWearSportData();
                } catch (JsonSyntaxException unused) {
                    LogUtil.h("BiUserLabelHelper", "getSportRecoverTime trackMetaData is error");
                }
                if (wearSportData != null && wearSportData.containsKey("recovery_time")) {
                    if (wearSportData.get("recovery_time") != null) {
                        i2 = wearSportData.get("recovery_time").intValue() / 60;
                    }
                }
            }
            return i2;
        }
    }

    private void ae() {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        long currentTimeMillis = System.currentTimeMillis();
        hiDataReadOption.setTimeInterval(currentTimeMillis - 2592000000L, currentTimeMillis);
        hiDataReadOption.setType(k);
        hiDataReadOption.setSortOrder(1);
        HiHealthManager.d(r).readHiHealthData(hiDataReadOption, new AnonymousClass15());
    }

    /* renamed from: dxo$15, reason: invalid class name */
    class AnonymousClass15 implements HiDataReadResultListener {
        AnonymousClass15() {
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(final Object obj, final int i, int i2) {
            dxw a2 = dxw.a(dxo.r);
            SparseArray aav_ = dxo.this.aav_(obj, i);
            if (aav_ == null || aav_.size() <= 0) {
                a2.b("health_sport_run_count", "", dxo.this.ao());
                a2.b("health_sport_walk_count", "", dxo.this.ao());
                a2.b("health_sport_ride_count", "", dxo.this.ao());
                a2.b("health_sport_swim_count", "", dxo.this.ao());
                a2.b("health_sport_basketball_count", "", dxo.this.ao());
            } else {
                dxo.this.aaA_(aav_);
            }
            dxo.this.c(new IBaseResponseCallback() { // from class: dxq
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i3, Object obj2) {
                    dxo.AnonymousClass15.this.b(obj, i, i3, obj2);
                }
            });
        }

        /* synthetic */ void b(Object obj, int i, int i2, Object obj2) {
            dxo.this.d(obj2);
            dxo.this.b(obj2, obj, i);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
            LogUtil.a("BiUserLabelHelper", "generateSportCountAndTimeLabel onResultIntent");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aaA_(SparseArray<Object> sparseArray) {
        aay_(30006, "health_sport_run_count", sparseArray);
        aay_(30005, "health_sport_walk_count", sparseArray);
        aay_(30007, "health_sport_ride_count", sparseArray);
        aay_(30021, "health_sport_swim_count", sparseArray);
        aay_(30023, "health_sport_basketball_count", sparseArray);
    }

    private void aay_(int i2, String str, SparseArray<Object> sparseArray) {
        dxw a2 = dxw.a(r);
        Object obj = sparseArray.get(i2);
        if (obj instanceof List) {
            List<HiHealthData> list = (List) obj;
            if (koq.b(list)) {
                a2.b(str, "", ao());
                return;
            } else {
                a2.b(str, Arrays.toString(f(list)), ao());
                return;
            }
        }
        a2.b(str, "", ao());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Object obj) {
        dxw a2 = dxw.a(r);
        if (obj == null || !koq.e(obj, WorkoutRecord.class)) {
            a2.b("health_sport_fitness_count", "", ao());
            return;
        }
        List<WorkoutRecord> list = (List) obj;
        if (koq.b(list)) {
            a2.b("health_sport_fitness_count", "", ao());
            return;
        }
        int[] iArr = new int[12];
        long j2 = 0;
        for (WorkoutRecord workoutRecord : list) {
            j2 += (workoutRecord.acquireExerciseTime() - workoutRecord.startTime()) / 86400000;
            a(iArr, jec.b(workoutRecord.startTime()), jec.b(workoutRecord.acquireExerciseTime()));
        }
        for (int i2 = 0; i2 < 12; i2++) {
            iArr[i2] = (int) (iArr[i2] + j2);
        }
        a2.b("health_sport_fitness_count", Arrays.toString(iArr), ao());
    }

    private int[] f(List<HiHealthData> list) {
        int[] iArr = new int[12];
        long j2 = 0;
        for (HiHealthData hiHealthData : list) {
            j2 += (hiHealthData.getEndTime() - hiHealthData.getStartTime()) / 86400000;
            a(iArr, jec.b(hiHealthData.getStartTime()), jec.b(hiHealthData.getEndTime()));
        }
        for (int i2 = 0; i2 < 12; i2++) {
            iArr[i2] = (int) (iArr[i2] + j2);
        }
        return iArr;
    }

    private void a(int[] iArr, int i2, int i3) {
        if (i3 >= i2) {
            for (int i4 = i2 / 2; i4 <= i3 / 2; i4++) {
                int i5 = iArr[i4] + 1;
                iArr[i4] = i5;
                iArr[i4] = i5;
            }
            return;
        }
        for (int i6 = 0; i6 <= i3; i6 += 2) {
            int i7 = i6 / 2;
            int i8 = iArr[i7] + 1;
            iArr[i7] = i8;
            iArr[i7] = i8;
        }
        while (i2 < 24) {
            int i9 = i2 / 2;
            int i10 = iArr[i9] + 1;
            iArr[i9] = i10;
            iArr[i9] = i10;
            i2 += 2;
        }
    }

    public void c(final IBaseResponseCallback iBaseResponseCallback) {
        long currentTimeMillis = System.currentTimeMillis();
        RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
        if (recordApi == null) {
            LogUtil.h("BiUserLabelHelper", "getFitnessRecord recordApi is null.");
        } else {
            recordApi.acquireDetailFitnessRecords(new kwy.a().a(currentTimeMillis - 2592000000L).e(currentTimeMillis).d(), new IBaseResponseCallback() { // from class: dxo.17
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    if (obj == null) {
                        LogUtil.a("BiUserLabelHelper", "fitnessRecords is null");
                    }
                    iBaseResponseCallback.d(i2, obj);
                }
            });
        }
    }

    public void e(int i2, long j2, long j3) {
        e(i2);
        c(j2, j3);
    }

    public void c(long j2) {
        long currentTimeMillis = System.currentTimeMillis() - j2;
        String str = currentTimeMillis <= 604800000 ? "SportLastLogin_0" : (currentTimeMillis <= 604800000 || currentTimeMillis >= 2592000000L) ? (currentTimeMillis <= 2592000000L || currentTimeMillis > 5184000000L) ? (currentTimeMillis <= 5184000000L || currentTimeMillis > 7776000000L) ? "SportLastLogin_4" : "SportLastLogin_3" : "SportLastLogin_2" : "SportLastLogin_1";
        dxw a2 = dxw.a(r);
        LogUtil.c("BiUserLabelHelper", "generateLastLoginLabel=", str);
        a2.b("health_sport_last_login", str, ao());
    }

    private void e(int i2) {
        String str = "SportUserActive_0";
        String[] strArr = {"SportUserActive_0", "SportUserActive_1", "SportUserActive_2", "SportUserActive_3"};
        if (i2 > 0 && i2 < 4) {
            str = strArr[i2];
        } else if (i2 >= 4 && i2 <= 8) {
            str = "SportUserActive_4";
        } else if (i2 > 8 && i2 <= 16) {
            str = "SportUserActive_5";
        } else if (i2 > 16) {
            str = "SportUserActive_6";
        }
        dxw a2 = dxw.a(r);
        LogUtil.c("BiUserLabelHelper", "generateActivityFrequencyLabel=", str);
        a2.b("health_sport_user_active", str, ao());
    }

    private void c(long j2, long j3) {
        dxw a2 = dxw.a(r);
        if (j2 == 0) {
            a2.b("health_sport_last_active", "", ao());
            return;
        }
        long j4 = j3 - j2;
        String str = j4 <= 2592000000L ? "SportLastActive_0" : j4 <= 5184000000L ? "SportLastActive_1" : j4 <= 7776000000L ? "SportLastActive_2" : j4 <= ThirdLoginDataStorageUtil.REFRESH_TOKEN_INTERVAL ? "SportLastActive_3" : "SportLastActive_4";
        LogUtil.c("BiUserLabelHelper", "generateActivityLastLabel=", str);
        a2.b("health_sport_last_active", str, ao());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SparseArray<Object> aav_(Object obj, int i2) {
        LogUtil.c("BiUserLabelHelper", "result = ", Integer.valueOf(i2));
        if (i2 == 0 && (obj instanceof SparseArray)) {
            return (SparseArray) obj;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String e(List<HiHealthData> list, List<HiHealthData> list2) {
        boolean z;
        if (koq.b(list)) {
            return b(list2);
        }
        int[] iArr = new int[4];
        boolean z2 = false;
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2) != null) {
                if (i2 == 0) {
                    if (System.currentTimeMillis() - list.get(i2).getEndTime() > 2592000000L) {
                        z2 = true;
                    } else {
                        z = false;
                        d(list, iArr, i2, 10000, 20000);
                        z2 = z;
                    }
                }
                z = z2;
                d(list, iArr, i2, 10000, 20000);
                z2 = z;
            }
        }
        return e(iArr, z2);
    }

    private void d(List<HiHealthData> list, int[] iArr, int i2, int i3, int i4) {
        int totalDistance;
        HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(list.get(i2).getMetaData(), HiTrackMetaData.class);
        if (hiTrackMetaData != null && (totalDistance = hiTrackMetaData.getTotalDistance()) >= 0) {
            if (totalDistance < 5000) {
                iArr[0] = iArr[0] + 1;
                return;
            }
            if (totalDistance < i3) {
                iArr[1] = iArr[1] + 1;
            } else if (totalDistance < i4) {
                iArr[2] = iArr[2] + 1;
            } else {
                iArr[3] = iArr[3] + 1;
            }
        }
    }

    private String e(int[] iArr, boolean z) {
        StringBuilder sb = new StringBuilder();
        boolean z2 = false;
        for (int i2 : iArr) {
            if (i2 != 0) {
                z2 = true;
            }
        }
        a(sb, iArr);
        if (z && z2) {
            sb.append(",");
            sb.append("SportStrength_5");
        } else if (z) {
            sb.append("SportStrength_5");
        } else {
            LogUtil.c("BiUserLabelHelper", "getRunIntensiveLabel else branch");
        }
        return sb.toString();
    }

    private StringBuilder a(StringBuilder sb, int[] iArr) {
        int i2;
        if (iArr[3] > 0) {
            sb.append("SportStrength_4");
            return sb;
        }
        int i3 = iArr[2];
        if (i3 > 2) {
            sb.append("SportStrength_3");
            return sb;
        }
        if (i3 > 0 || (i2 = iArr[1]) > 2) {
            sb.append("SportStrength_2");
            return sb;
        }
        if (i2 <= 0 && iArr[0] <= 0) {
            return sb;
        }
        sb.append("SportStrength_1");
        return sb;
    }

    private String b(List<HiHealthData> list) {
        return (!koq.b(list) && list.size() > 10) ? "SportStrength_0SportStrength_5" : "SportStrength_5";
    }

    private static HealthDataCloudFactory ar() {
        return new HealthDataCloudFactory(BaseApplication.e());
    }

    private void an() {
        LogUtil.a("BiUserLabelHelper", "enter generateUserLabelByCloudRule");
        dxj dxjVar = new dxj();
        dxjVar.e(dyg.c());
        lqi.d().b(dxjVar.getUrl(), ar().getHeaders(), lql.b(ar().getBody(dxjVar)), dxg.class, new ResultCallback<dxg>() { // from class: dxo.20
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(dxg dxgVar) {
                LogUtil.a("BiUserLabelHelper", "generateUserLabelByCloudRule code = ", dxgVar.getResultCode());
                dxo.this.h(dxgVar.b());
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.b("BiUserLabelHelper", "generateUserLabelByCloudRule throwable ", th.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(List<dxc> list) {
        if (koq.b(list)) {
            LogUtil.a("BiUserLabelHelper", "labelRules is empty");
            return;
        }
        for (dxc dxcVar : list) {
            List<dxe.e> b2 = dxcVar.c().b();
            if (koq.b(b2)) {
                LogUtil.a("BiUserLabelHelper", "labelThresholdValue is empty");
                dxw.a(r).b(dxcVar.e(), "", ao());
            } else {
                c(dxcVar.e(), b2);
            }
        }
    }

    private void c(String str, List<dxe.e> list) {
        boolean z = false;
        for (dxe.e eVar : list) {
            String d2 = eVar.d();
            LogUtil.a("BiUserLabelHelper", "parsingLabelRule rule is ", d2);
            if (dyg.d(d2)) {
                dxw.a(r).b(str, eVar.e(), ao());
                LogUtil.a("BiUserLabelHelper", "insert ", str, " is ", eVar.e());
                z = true;
            }
        }
        if (z) {
            return;
        }
        dxw.a(r).b(str, "", ao());
        LogUtil.a("BiUserLabelHelper", "insert ", str, " is empty label value");
    }
}
