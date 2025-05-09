package com.huawei.health.suggestion.ui.fitness.viewmodel;

import android.os.Bundle;
import android.os.Message;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.huawei.health.basesport.viewmodel.BaseSportingViewModel;
import com.huawei.health.device.api.EcologyDeviceApi;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.sportservice.SportBleStatus;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.suggestion.ui.fitness.activity.LongCoachActivity;
import com.huawei.health.suggestion.ui.fitness.viewmodel.CourseEquipmentViewModel;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cei;
import defpackage.koq;
import defpackage.moj;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class CourseEquipmentViewModel extends BaseSportingViewModel implements SportBleStatus, SportDataNotify {
    private MutableLiveData<Bundle> b;
    private int c;
    private SportBleStatus d;
    private Bundle e;

    public static /* synthetic */ void a(int i, Object obj) {
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        return false;
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void initFragment() {
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void initReceivedDataHandlers() {
    }

    public void d(int i, String str, String str2) {
        if (i == 0) {
            LogUtil.h("Suggestion_CourseEquipmentViewModel", "equipmentType is no match", Integer.valueOf(i));
            return;
        }
        Bundle bundle = new Bundle();
        SportLaunchParams sportLaunchParams = new SportLaunchParams();
        sportLaunchParams.setDataSource(5);
        bundle.putInt("sport_data_source_sportting", 5);
        this.c = i;
        if (i == 1) {
            sportLaunchParams.setSportType(264);
            bundle.putInt("map_tracking_sport_type_sportting", 264);
        } else if (i == 2) {
            sportLaunchParams.setSportType(OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE);
            bundle.putInt("map_tracking_sport_type_sportting", OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE);
        } else if (i == 3) {
            sportLaunchParams.setSportType(283);
            bundle.putInt("map_tracking_sport_type_sportting", 283);
            EcologyDeviceApi ecologyDeviceApi = (EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class);
            if (ecologyDeviceApi.isSupportPerformanceByProductId(cei.b().getProductId())) {
                ecologyDeviceApi.requestPerformanceRank(new IBaseResponseCallback() { // from class: fsj
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i2, Object obj) {
                        CourseEquipmentViewModel.a(i2, obj);
                    }
                });
            }
        } else {
            LogUtil.h("Suggestion_CourseEquipmentViewModel", "equipmentType is no match", Integer.valueOf(i));
        }
        sportLaunchParams.setLaunchActivity(LongCoachActivity.class.getName());
        sportLaunchParams.setNeedRestart(true);
        sportLaunchParams.addExtra("workoutid", str);
        sportLaunchParams.addExtra("coach_detail_name", str2);
        sportLaunchParams.addExtra("equipment_course", true);
        bundle.putParcelable("bundle_key_sport_launch_paras", sportLaunchParams);
        super.init(bundle);
        this.mSportDataOutputApi.registerSportBleStatus("Suggestion_CourseEquipmentViewModel", this);
        onPreSport();
        requestData();
        MutableLiveData<Bundle> mutableLiveData = new MutableLiveData<>();
        this.b = mutableLiveData;
        this.e = mutableLiveData.getValue();
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel, com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        super.m134x32b3e3a1();
        ReleaseLogUtil.e("Suggestion_CourseEquipmentViewModel", "stopSport,", Integer.valueOf(this.c));
        IndoorEquipManagerApi indoorEquipManagerApi = (IndoorEquipManagerApi) Services.c("PluginLinkIndoorEquip", IndoorEquipManagerApi.class);
        if (this.c == 2) {
            indoorEquipManagerApi.stopSport();
            indoorEquipManagerApi.setHasExperiencedStateOfStartForFtmp(false);
        }
    }

    public void c(Observer<Bundle> observer) {
        if (observer != null) {
            this.b.observeForever(observer);
        }
    }

    public void a(LifecycleOwner lifecycleOwner) {
        if (lifecycleOwner != null) {
            this.b.removeObservers(lifecycleOwner);
        }
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel, com.huawei.health.sportservice.SportDataNotify
    public void onChange(List<String> list, Map<String, Object> map) {
        if (koq.b(list) || map == null) {
            LogUtil.h("Suggestion_CourseEquipmentViewModel", "onChange receive change error with wrong paras.");
            return;
        }
        if (!list.contains("TIME_ONE_SECOND_DURATION") && !c(list)) {
            LogUtil.h("Suggestion_CourseEquipmentViewModel", "onChange tagList unContains time or no calories or shipNum");
            return;
        }
        if (this.e == null) {
            this.e = new Bundle();
        }
        LogUtil.c("Suggestion_CourseEquipmentViewModel", "sportDataList:", moj.e(map));
        c(map);
        int i = this.c;
        if (i == 1) {
            d(map);
        } else if (i == 2) {
            a(map);
        } else if (i == 3) {
            b(map);
        } else {
            LogUtil.h("Suggestion_CourseEquipmentViewModel", "onChange mEquipmentType", Integer.valueOf(i));
        }
        this.b.postValue(this.e);
    }

    private boolean c(List<String> list) {
        return this.c == 3 && (list.contains("CALORIES_DATA") || list.contains("SKIP_NUM_DATA"));
    }

    private void c(Map<String, Object> map) {
        if (this.e == null) {
            LogUtil.h("Suggestion_CourseEquipmentViewModel", "commonData mBundle is null");
            return;
        }
        Object obj = map.get("CALORIES_DATA");
        if (obj instanceof Integer) {
            this.e.putInt("fitnessCalories", ((Integer) obj).intValue());
        }
        Object obj2 = map.get("HEART_RATE_DATA");
        if (obj2 instanceof Integer) {
            this.e.putInt("fitnessHeart", ((Integer) obj2).intValue());
        }
        Object obj3 = map.get("DISTANCE_DATA");
        if (obj3 instanceof Integer) {
            Integer num = (Integer) obj3;
            if (num.intValue() > 0) {
                this.e.putInt("fitnessDistance", num.intValue());
            }
        }
        this.e.putFloat("fitnessSpeed", CommonUtil.j(String.valueOf(map.get("SPEED_DATA"))) / 100.0f);
        this.e.putInt("fitnessTime", Math.round(((map.get("TIME_ONE_SECOND_DURATION") instanceof Integer ? 0L : ((Long) r4).longValue()) * 1.0f) / 1000.0f));
    }

    private void b(Map<String, Object> map) {
        if (this.e == null) {
            LogUtil.h("Suggestion_CourseEquipmentViewModel", "ropeData mBundle is null");
            return;
        }
        Object obj = map.get("SKIP_SPEED_DATA");
        if (obj instanceof Integer) {
            this.e.putInt("fitnessRopeSpeed", ((Integer) obj).intValue());
        }
        Object obj2 = map.get("STUMBLING_ROPE_DATA");
        if (obj2 instanceof Integer) {
            this.e.putInt("fitnessStumbling", ((Integer) obj2).intValue());
        }
        Object obj3 = map.get("CONTINUOUS_SKIPPING_JUMP_DATA");
        if (obj3 instanceof Integer) {
            this.e.putInt("fitnessContinuousSkippingJump", ((Integer) obj3).intValue());
        }
        Object obj4 = map.get("SKIP_NUM_DATA");
        if (obj4 instanceof Integer) {
            this.e.putInt("fitnessRopeTotalNumber", ((Integer) obj4).intValue());
        }
    }

    private void d(Map<String, Object> map) {
        if (this.e == null) {
            LogUtil.h("Suggestion_CourseEquipmentViewModel", "treadmillData mBundle is null");
            return;
        }
        this.e.putString("fitnessSlope", UnitUtil.a(CommonUtil.a(String.valueOf(map.get("INCLINATION_DATA"))), 1, 1, false));
        double a2 = CommonUtil.a(String.valueOf(map.get("STEP_DATA")));
        if (a2 < 0.0d) {
            a2 = 0.0d;
        }
        this.e.putString("fitnessSteps", UnitUtil.a(a2, 1, 0, false));
        double a3 = CommonUtil.a(String.valueOf(map.get("STEP_RATE_DATA")));
        this.e.putString("fitnessStepFrequency", UnitUtil.a(a3 >= 0.0d ? a3 : 0.0d, 1, 0, false));
    }

    private void a(Map<String, Object> map) {
        if (this.e == null) {
            LogUtil.h("Suggestion_CourseEquipmentViewModel", "bicycleData mBundle is null");
            return;
        }
        this.e.putString("fitnessResistance", UnitUtil.a(CommonUtil.a(String.valueOf(map.get("RESISTANCE_LEVEL_DATA"))), 1, 0, false));
        this.e.putInt("fitnessTreadFrequancy", CommonUtil.h(String.valueOf(map.get("CADENCE_DATA"))));
        double a2 = CommonUtil.a(String.valueOf(map.get("POWER_DATA")));
        this.e.putString("fitnessPower", a2 > 0.0d ? UnitUtil.a(a2, 1, 0, false) : "");
    }

    public void e(SportBleStatus sportBleStatus) {
        if (sportBleStatus != null) {
            this.d = sportBleStatus;
        }
    }

    @Override // com.huawei.health.sportservice.SportBleStatus
    public void onInterrupted() {
        SportBleStatus sportBleStatus = this.d;
        if (sportBleStatus != null) {
            sportBleStatus.onInterrupted();
        } else {
            LogUtil.h("Suggestion_CourseEquipmentViewModel", "onInterrupted mSportBleStatus is null");
        }
    }

    @Override // com.huawei.health.sportservice.SportBleStatus
    public void onConnected() {
        SportBleStatus sportBleStatus = this.d;
        if (sportBleStatus != null) {
            sportBleStatus.onConnected();
        } else {
            LogUtil.h("Suggestion_CourseEquipmentViewModel", "onConnected mSportBleStatus is null");
        }
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public List<String> getSubscribeTagList() {
        ArrayList arrayList = new ArrayList(16);
        arrayList.add("HEART_RATE_DATA");
        arrayList.add("CALORIES_DATA");
        arrayList.add("DISTANCE_DATA");
        arrayList.add("SPEED_DATA");
        arrayList.add("TIME_ONE_SECOND_DURATION");
        int i = this.c;
        if (i == 1) {
            arrayList.add("INCLINATION_DATA");
            arrayList.add("STEP_DATA");
            arrayList.add("STEP_RATE_DATA");
        } else if (i == 2) {
            arrayList.add("CADENCE_DATA");
            arrayList.add("RESISTANCE_LEVEL_DATA");
            arrayList.add("POWER_DATA");
        } else if (i == 3) {
            arrayList.add("SKIP_SPEED_DATA");
            arrayList.add("CONTINUOUS_SKIPPING_JUMP_DATA");
            arrayList.add("STUMBLING_ROPE_DATA");
            arrayList.add("SKIP_NUM_DATA");
        } else {
            LogUtil.a("Suggestion_CourseEquipmentViewModel", "getSubscribeTagList equipmentType", Integer.valueOf(i));
        }
        return arrayList;
    }
}
