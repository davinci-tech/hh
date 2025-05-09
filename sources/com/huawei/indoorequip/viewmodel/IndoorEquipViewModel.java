package com.huawei.indoorequip.viewmodel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.device.api.EcologyDeviceApi;
import com.huawei.health.device.datatype.IntermitentJumpData;
import com.huawei.health.device.model.DeviceInformation;
import com.huawei.health.sportservice.SportBleStatus;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.indoorequip.datastruct.QrCodeOrNfcInfo;
import com.huawei.indoorequip.datastruct.SupportDataRange;
import com.huawei.indoorequip.viewmodel.IndoorConnectViewModel;
import com.huawei.indoorequip.viewmodel.IndoorEquipViewModel;
import com.huawei.openalliance.ad.constant.ErrorCode;
import defpackage.cei;
import defpackage.ckh;
import defpackage.ffd;
import defpackage.ffs;
import defpackage.gwl;
import defpackage.jpu;
import defpackage.kyv;
import defpackage.kzc;
import defpackage.lau;
import defpackage.lbv;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class IndoorEquipViewModel extends IndoorConnectViewModel implements SportBleStatus, SportDataNotify, Handler.Callback {
    protected boolean l;
    protected boolean o;
    private int v;
    private boolean s = false;
    private boolean w = false;
    private boolean r = false;
    private int m = 0;
    private boolean t = true;
    private boolean p = true;
    private boolean x = false;
    private HashMap<String, Object> q = new HashMap<>();

    public static /* synthetic */ void d(int i, Object obj) {
    }

    @Override // com.huawei.indoorequip.viewmodel.IndoorConnectViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void initFragment() {
    }

    public void bWa_(SportLaunchParams sportLaunchParams, boolean z, Handler handler) {
        Bundle bundle = new Bundle();
        if (sportLaunchParams != null) {
            bundle.putInt("deviceType", sportLaunchParams.getSportType());
            bundle.putInt("sport_target_type_sportting", sportLaunchParams.getSportTarget());
            bundle.putInt("origintarget", sportLaunchParams.getTrackType());
            bundle.putFloat("sport_target_value_sportting", sportLaunchParams.getTargetValue());
            bundle.putInt("sport_data_source_sportting", 5);
            this.e = kzc.n();
            sportLaunchParams.setDataSource(5);
            sportLaunchParams.addExtra("origintarget", Integer.valueOf(sportLaunchParams.getSportTarget()));
            sportLaunchParams.setNeedRestart(true);
            bundle.putParcelable("bundle_key_sport_launch_paras", sportLaunchParams);
        }
        bundle.putBoolean("ExitApp", z);
        super.bVY_(bundle, handler);
        addSportDataMap("showSportTime");
        addSportDataMap("sportTotalDistance");
        this.mSportDataOutputApi.registerSportBleStatus(getTag(), this);
        observeSportLifeCycle(getTag(), this);
        d(sportLaunchParams);
        if (sportLaunchParams != null) {
            this.o = jpu.e("Track_IndoorEquipViewModel") != null;
            this.s = b(sportLaunchParams.getSportType());
            boolean c = gwl.c();
            this.w = c;
            this.l = this.o || this.s || c;
            this.m = e(sportLaunchParams.getSportType());
            this.r = (this.s || this.o || !this.w) ? false : true;
            if (sportLaunchParams.getSportType() != 283) {
                return;
            }
            EcologyDeviceApi ecologyDeviceApi = (EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class);
            if (ecologyDeviceApi.isSupportPerformanceByProductId(cei.b().getProductId())) {
                ecologyDeviceApi.requestPerformanceRank(new IBaseResponseCallback() { // from class: lcq
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        IndoorEquipViewModel.d(i, obj);
                    }
                });
            }
        }
    }

    private void d(SportLaunchParams sportLaunchParams) {
        if (this.k == null || sportLaunchParams == null || this.mSportDataOutputApi == null || sportLaunchParams.isRestart()) {
            return;
        }
        if (((Boolean) sportLaunchParams.getExtra("KEY_IS_SWITCH_TO_HORIZONTAL_SCREEN", Boolean.class, false)).booleanValue()) {
            this.v = ((Integer) sportLaunchParams.getExtra("key_progress", Integer.class, 0)).intValue();
            return;
        }
        if (b()) {
            lbv.b(BaseApplication.e(), "rope_launch", o());
        }
        this.mSportDataOutputApi.addDeviceStatusCallback(getTag(), new kyv(this.k));
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void requestLocalData() {
        super.requestLocalData();
        postValue("sportTotalDistance", 0);
        postValue("showSportTime", UnitUtil.a(0));
    }

    @Override // com.huawei.indoorequip.viewmodel.IndoorConnectViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public List<String> getSubscribeTagList() {
        return u();
    }

    private List<String> u() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("TIME_ONE_SECOND_DURATION");
        arrayList.add("UPDATE_VIEW_SIGNAL");
        arrayList.add("CALORIES_DATA");
        arrayList.add("HEART_RATE_DATA");
        arrayList.add("INDOOR_DEVICE_INFO_DATA");
        if (getTargetValue() != 6.0f) {
            arrayList.add("PROGRESS_DATA");
        }
        int sportType = getSportType();
        if (sportType == 264) {
            a(arrayList);
        } else if (sportType == 265) {
            c(arrayList);
        } else if (sportType == 273) {
            c(arrayList);
            arrayList.add("STEP_DATA");
            arrayList.add("STEP_RATE_DATA");
        } else if (sportType == 274) {
            d(arrayList);
        } else if (sportType == 281) {
            b(arrayList);
        } else if (sportType == 283) {
            e(arrayList);
        }
        return arrayList;
    }

    private void d(List<String> list) {
        if (w()) {
            list.add("WEIGHT_DATA");
            list.add("ACTION_GROUP_DATA");
            list.add("GROUP_COUNT_DATA");
        } else {
            list.add("DISTANCE_DATA");
            list.add("SPEED_DATA");
            list.add("PACE_DATA");
            list.add("RESISTANCE_LEVEL_DATA");
        }
        list.add("TEMPO_DATA");
        list.add("POWER_DATA");
        list.add("PADDLE_DATA");
    }

    private void c(List<String> list) {
        list.add("DISTANCE_DATA");
        list.add("SPEED_DATA");
        list.add("CADENCE_DATA");
        list.add("PACE_DATA");
        list.add("POWER_DATA");
        list.add("RESISTANCE_LEVEL_DATA");
    }

    private void b(List<String> list) {
        list.add("DISTANCE_DATA");
        list.add("SPEED_DATA");
        list.add("STEP_DATA");
        list.add("STEP_RATE_DATA");
        list.add("PACE_DATA");
    }

    private void a(List<String> list) {
        list.add("DISTANCE_DATA");
        list.add("SPEED_DATA");
        list.add("RUNNING_POSTURE_DATA");
        list.add("STEP_DATA");
        list.add("STEP_RATE_DATA");
        list.add("PACE_DATA");
    }

    private void e(List<String> list) {
        list.add("SKIP_NUM_DATA");
        list.add("SKIP_SPEED_DATA");
        list.add("STUMBLING_ROPE_DATA");
        list.add("CONTINUOUS_SKIPPING_JUMP_DATA");
        list.add("SKIPPING_AVG_SPEED_DATA");
        list.add("ROPE_SKIPPING_MODE_DATA");
        list.add("ROPE_INTERMITTENT_GROUP_NO_DATA");
        list.add("ROPE_SKIPPING_TRICK_DATA");
        list.add("ROPE_MACHINE_STATUS_DATA");
        list.add("SKIPPING_INTERMITTENT_SPORT_TIME_DATA");
        list.add("SKIPPING_INTERMITTENT_REST_TIME_DATA");
        list.add("SKIPPING_ACCUMULATED_COUNT_DATA");
        list.add("SKIPPING_ACCUMULATED_DURATION_DATA");
        list.add("ROPE_INTERMITTENT_JUMP_ENERGY_DATA");
        list.add("ROPE_INTERMITTENT_JUMP_SPEED_DATA");
        list.add("ROPE_ACCUMULATED_ENERGY_DATA");
    }

    @Override // com.huawei.indoorequip.viewmodel.IndoorConnectViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void initReceivedDataHandlers() {
        e("UPDATE_VIEW_SIGNAL", "showSportTime", Integer.class, new IndoorConnectViewModel.DataChangeHandle() { // from class: lco
            @Override // com.huawei.indoorequip.viewmodel.IndoorConnectViewModel.DataChangeHandle
            public final void changeHandle(Object obj, Map map) {
                IndoorEquipViewModel.this.a((Integer) obj, map);
            }
        });
        e("TIME_ONE_SECOND_DURATION", "showSportTime", Long.class, new IndoorConnectViewModel.DataChangeHandle() { // from class: lcm
            @Override // com.huawei.indoorequip.viewmodel.IndoorConnectViewModel.DataChangeHandle
            public final void changeHandle(Object obj, Map map) {
                IndoorEquipViewModel.this.e((Long) obj, map);
            }
        });
        e("SKIP_NUM_DATA", "sportSkipNum", Integer.class, new IndoorConnectViewModel.DataChangeHandle() { // from class: lck
            @Override // com.huawei.indoorequip.viewmodel.IndoorConnectViewModel.DataChangeHandle
            public final void changeHandle(Object obj, Map map) {
                IndoorEquipViewModel.this.c((Integer) obj, map);
            }
        });
        d("SPEED_DATA", "sportSpeed", Integer.class);
        d("RESISTANCE_LEVEL_DATA", "sportResistanceLevel", Integer.class);
    }

    public /* synthetic */ void a(Integer num, Map map) {
        LogUtil.a("Track_IndoorEquipViewModel", "addUpdateViewSignalDataHandler ", num);
        a((Map<String, Object>) map, 0L);
        a(num);
    }

    public /* synthetic */ void e(Long l, Map map) {
        int round = Math.round((l.longValue() * 1.0f) / 1000.0f);
        LogUtil.a("Track_IndoorEquipViewModel", "sportTime=", Integer.valueOf(round), " allData.size ", Integer.valueOf(map.size()), map.toString());
        if (this.e != null) {
            int currentTimeMillis = (int) ((System.currentTimeMillis() - this.e.k()) / 1000);
            if (currentTimeMillis <= 1) {
                c(round);
            }
            if (currentTimeMillis <= 1 && d() && getTargetSportStatus() == 2) {
                y();
            }
        }
        Object obj = map.get("SKIP_NUM_DATA");
        if (obj != null) {
            this.j = ((Integer) obj).intValue();
        }
        a((Map<String, Object>) map, 0L);
    }

    public /* synthetic */ void c(Integer num, Map map) {
        Object obj = map.get("SKIP_NUM_DATA");
        if (obj != null) {
            this.j = ((Integer) obj).intValue();
        }
        if ((getSportTarget() == 5 && this.j == getTargetValue()) || x()) {
            a((Map<String, Object>) map, 0L);
        }
    }

    private boolean x() {
        IntermitentJumpData intermitentJumpData = (IntermitentJumpData) getExtraValue("type_intermittent_jump_mode_data", IntermitentJumpData.class);
        return intermitentJumpData != null && getSportTarget() == 8 && intermitentJumpData.getIntermittentJumpMode() == 0 && intermitentJumpData.getIntermittentJumpExerciseNum() == this.j;
    }

    private <T> void d(String str, String str2, Class<T> cls) {
        e(str, str2, cls, new IndoorConnectViewModel.DataChangeHandle() { // from class: lcl
            @Override // com.huawei.indoorequip.viewmodel.IndoorConnectViewModel.DataChangeHandle
            public final void changeHandle(Object obj, Map map) {
                IndoorEquipViewModel.this.b(obj, map);
            }
        });
    }

    public /* synthetic */ void b(Object obj, Map map) {
        if (this.mSportDataOutputApi == null || this.mSportDataOutputApi.getStatus() != 2) {
            return;
        }
        a((Map<String, Object>) map, 0L);
    }

    private void a(Integer num) {
        if (num.intValue() == 27) {
            LogUtil.a("Track_IndoorEquipViewModel", "handleRefreshView");
            if (this.k != null) {
                Message obtainMessage = this.k.obtainMessage();
                obtainMessage.what = 1017;
                this.k.sendMessage(obtainMessage);
            }
        }
    }

    private void a(Map<String, Object> map, long j) {
        HashMap hashMap = new HashMap();
        hashMap.put(2, Integer.valueOf(Math.round(((map.get("TIME_ONE_SECOND_DURATION") instanceof Integer ? 0L : ((Long) map.get("TIME_ONE_SECOND_DURATION")).longValue()) * 1.0f) / 1000.0f)));
        hashMap.put(1, map.get("DISTANCE_DATA"));
        hashMap.put(20004, map.get("HEART_RATE_DATA"));
        hashMap.put(6, map.get("CALORIES_DATA"));
        hashMap.put(8, map.get("STEP_DATA"));
        hashMap.put(4, map.get("STEP_RATE_DATA"));
        hashMap.put(3, map.get("SPEED_DATA"));
        Object obj = map.get("INDOOR_DEVICE_INFO_DATA");
        if (obj instanceof DeviceInformation) {
            this.d = (DeviceInformation) obj;
        }
        Object obj2 = map.get("RUNNING_POSTURE_DATA");
        if (obj2 instanceof ffs) {
            hashMap.put(20002, obj2);
        }
        a(map, hashMap);
        hashMap.put(26, map.get("TEMPO_DATA"));
        hashMap.put(31, map.get("CADENCE_DATA"));
        hashMap.put(7, map.get("POWER_DATA"));
        d(map, hashMap);
        hashMap.put(22, map.get("RESISTANCE_LEVEL_DATA"));
        hashMap.put(10003, Integer.valueOf(this.v));
        c(map, hashMap);
        a(map);
        if (this.k != null) {
            Message obtainMessage = this.k.obtainMessage();
            obtainMessage.obj = hashMap;
            obtainMessage.what = 1014;
            this.k.sendMessageDelayed(obtainMessage, j);
        }
    }

    private void a(Map<String, Object> map, Map<Integer, Object> map2) {
        int i;
        if (getSportType() == 264 || getSportType() == 275) {
            int intValue = ((Integer) map.get("SPEED_DATA")).intValue();
            float f = intValue;
            if (intValue != 0) {
                i = new BigDecimal(3600).divide(new BigDecimal(f).divide(new BigDecimal(100), 2, 4), 2, 4).multiply(new BigDecimal(100)).intValue();
            } else {
                i = 0;
            }
            LogUtil.a("Track_IndoorEquipViewModel", "mpace is ", Integer.valueOf(i), "and speed  :", Integer.valueOf(intValue));
            map2.put(14, Integer.valueOf(i));
            return;
        }
        map2.put(14, map.get("PACE_DATA"));
    }

    private void c(Map<String, Object> map, Map<Integer, Object> map2) {
        map2.put(40001, map.get("SKIP_NUM_DATA"));
        map2.put(40004, map.get("SKIP_SPEED_DATA"));
        map2.put(40002, map.get("STUMBLING_ROPE_DATA"));
        map2.put(40003, map.get("CONTINUOUS_SKIPPING_JUMP_DATA"));
        map2.put(Integer.valueOf(SmartMsgConstant.MSG_TYPE_BLOOD_SUGAR_USER), map.get("SKIPPING_AVG_SPEED_DATA"));
        map2.put(40009, map.get("ROPE_SKIPPING_MODE_DATA"));
        map2.put(40012, map.get("ROPE_SKIPPING_TRICK_DATA"));
        map2.put(10003, Integer.valueOf(this.v));
        b(map, map2);
        e(map, map2);
    }

    private void b(Map<String, Object> map, Map<Integer, Object> map2) {
        Object obj = map.get("ROPE_SKIPPING_TRICK_DATA");
        if (obj instanceof ckh) {
            ckh ckhVar = (ckh) obj;
            map2.put(40063, Integer.valueOf(ckhVar.d()));
            map2.put(40064, Integer.valueOf(ckhVar.b()));
            map2.put(40065, Integer.valueOf(ckhVar.e()));
        }
    }

    private void e(Map<String, Object> map, Map<Integer, Object> map2) {
        if (map == null || map2 == null || getSportType() != 283 || getSportTarget() != 8) {
            return;
        }
        int intValue = ((Integer) map.get("ROPE_MACHINE_STATUS_DATA")).intValue();
        this.t = intValue != 2;
        map2.put(40015, Boolean.valueOf(this.t));
        int intValue2 = ((Integer) map.get("ROPE_INTERMITTENT_GROUP_NO_DATA")).intValue();
        map2.put(40011, Integer.valueOf(intValue2));
        map2.put(40059, Integer.valueOf(((Integer) map.get("ROPE_INTERMITTENT_JUMP_SPEED_DATA")).intValue()));
        map2.put(40057, Integer.valueOf(((Integer) map.get("ROPE_INTERMITTENT_JUMP_ENERGY_DATA")).intValue()));
        int intValue3 = ((Integer) map.get("SKIP_NUM_DATA")).intValue();
        map2.put(40016, Integer.valueOf(intValue3));
        if (intValue == 2) {
            map2.put(40013, Integer.valueOf(((Integer) map.get("SKIPPING_ACCUMULATED_COUNT_DATA")).intValue()));
            map2.put(40014, Integer.valueOf(((Integer) map.get("SKIPPING_ACCUMULATED_DURATION_DATA")).intValue()));
            map2.put(40058, Integer.valueOf(((Integer) map.get("ROPE_ACCUMULATED_ENERGY_DATA")).intValue()));
        }
        int intValue4 = ((Integer) map.get("SKIPPING_INTERMITTENT_SPORT_TIME_DATA")).intValue();
        map2.put(40051, Integer.valueOf(intValue4));
        int intValue5 = ((Integer) map.get("SKIPPING_INTERMITTENT_REST_TIME_DATA")).intValue();
        map2.put(40052, Integer.valueOf(intValue5));
        e(map2, intValue3, intValue4, intValue5);
        LogUtil.a("Track_IndoorEquipViewModel", "putRopeIntermittentData no=", Integer.valueOf(intValue2), " ropeStatus:", Integer.valueOf(intValue), " sportTime:", Integer.valueOf(intValue4), " restTime:", Integer.valueOf(intValue5));
    }

    private void e(Map<Integer, Object> map, int i, int i2, int i3) {
        float f;
        int intermittentJumpBreakTime;
        IntermitentJumpData intermitentJumpData = (IntermitentJumpData) getExtraValue("type_intermittent_jump_mode_data", IntermitentJumpData.class);
        if (this.t) {
            if (intermitentJumpData.getIntermittentJumpMode() == 0) {
                f = i * 1.0f;
                intermittentJumpBreakTime = intermitentJumpData.getIntermittentJumpExerciseNum();
            } else {
                f = i2 * 1.0f;
                intermittentJumpBreakTime = intermitentJumpData.getIntermittentJumpExerciseTime();
            }
        } else {
            f = i3 * 1.0f;
            intermittentJumpBreakTime = intermitentJumpData.getIntermittentJumpBreakTime();
        }
        this.v = (int) ((f / intermittentJumpBreakTime) * 100.0f);
        map.put(10003, Integer.valueOf(this.v));
        LogUtil.a("Track_IndoorEquipViewModel", "updataProgress mProgress = ", Integer.valueOf(this.v), " skippingNum = ", Integer.valueOf(i), " sportTime = ", Integer.valueOf(i2), " restTime = ", Integer.valueOf(i3));
    }

    private void d(Map<String, Object> map, Map<Integer, Object> map2) {
        if (w()) {
            map2.put(38, map.get("PADDLE_DATA"));
            Object obj = map.get("WEIGHT_DATA");
            int intValue = obj instanceof Integer ? ((Integer) obj).intValue() : 0;
            if (UnitUtil.h()) {
                intValue = (int) Math.round(UnitUtil.h(intValue));
            }
            map2.put(37, Integer.valueOf(intValue));
            map2.put(40, map.get("GROUP_COUNT_DATA"));
            map2.put(39, map.get("ACTION_GROUP_DATA"));
            return;
        }
        map2.put(27, map.get("PADDLE_DATA"));
    }

    private void a(Map<String, Object> map) {
        int intValue = ((Integer) map.get("HEART_RATE_DATA")).intValue();
        if (intValue > 0 && !this.x) {
            lbv.a(BaseApplication.e(), o());
            this.x = true;
            LogUtil.a("Track_IndoorEquipViewModel", "tickRopeHearRate heartRate =", Integer.valueOf(intValue));
        }
    }

    public void v() {
        if (this.mSportDataOutputApi == null) {
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : u()) {
            linkedHashMap.put(str, this.mSportDataOutputApi.getData(str));
        }
        a(linkedHashMap, 100L);
        c(this.v, 100);
    }

    private boolean b(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(554);
        return i == 264 && this.mSportDataOutputApi != null && this.mSportDataOutputApi.connectSubDeviceStatus(arrayList).get(0).intValue() > 0;
    }

    private int e(int i) {
        if (i != 264 || this.mSportDataOutputApi == null) {
            return 0;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(554);
        return this.mSportDataOutputApi.connectSubDeviceStatus(arrayList).get(0).intValue();
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel, com.huawei.health.sport.ITargetUpdateListener
    public void onTargetDataUpdate(ffd ffdVar) {
        if (ffdVar != null) {
            this.v = (int) (ffdVar.a() * 100.0f);
        }
        c(this.v, 0);
    }

    private void c(int i, int i2) {
        if (this.k != null) {
            Message obtainMessage = this.k.obtainMessage();
            obtainMessage.obj = Integer.valueOf(i);
            obtainMessage.what = 1016;
            this.k.sendMessageDelayed(obtainMessage, i2);
        }
    }

    public int l() {
        return this.v;
    }

    private void c(int i) {
        if (this.k == null || i < 15 || b()) {
            return;
        }
        LogUtil.h("Track_IndoorEquipViewModel", "not start from zero, will show dialog (if allowed)", Integer.valueOf(i));
        this.k.sendEmptyMessageDelayed(TypedValues.PositionType.TYPE_PERCENT_Y, 2000L);
    }

    private void y() {
        LogUtil.a("Track_IndoorEquipViewModel", "sendStartOverTargetMessage");
        this.k.sendEmptyMessageDelayed(515, 2500L);
    }

    public boolean s() {
        return this.o;
    }

    public boolean p() {
        return this.l;
    }

    public boolean t() {
        return this.s;
    }

    public boolean q() {
        return this.r;
    }

    public int n() {
        return this.m;
    }

    @Override // com.huawei.indoorequip.viewmodel.IndoorConnectViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel, com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        if (b()) {
            cei.b().requestDeviceInfoAndState("SportService_EcologyManager");
        }
        super.onPreSport();
    }

    @Override // com.huawei.health.sportservice.SportBleStatus
    public void onStateChanged(String str) {
        c(str);
    }

    @Override // com.huawei.health.sportservice.SportBleStatus
    public void onConnecting() {
        if (this.k != null) {
            this.k.sendEmptyMessage(301);
        }
    }

    @Override // com.huawei.health.sportservice.SportBleStatus
    public void onConnected() {
        if (this.k != null) {
            this.k.sendEmptyMessage(305);
        }
    }

    @Override // com.huawei.health.sportservice.SportBleStatus
    public void onReconnecting() {
        if (this.k != null) {
            this.k.sendEmptyMessage(307);
        }
    }

    @Override // com.huawei.health.sportservice.SportBleStatus
    public void onRechecking() {
        if (this.k != null) {
            this.k.sendEmptyMessage(309);
        }
    }

    @Override // com.huawei.health.sportservice.SportBleStatus
    public void onInterrupted() {
        if (this.k != null) {
            this.k.sendEmptyMessage(304);
        }
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel, com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        super.m134x32b3e3a1();
        if (!this.b) {
            a(true, true);
        }
        this.e.e(false);
        LogUtil.a("Track_IndoorEquipViewModel", "onStopSport()");
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel, com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        super.onPauseSport();
        if (b()) {
            v();
        }
        LogUtil.a("Track_IndoorEquipViewModel", "onPauseSport()");
    }

    @Override // com.huawei.indoorequip.viewmodel.IndoorConnectViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel, androidx.lifecycle.ViewModel
    public void onCleared() {
        a(this.p);
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel, com.huawei.health.sportservice.IHeartRateControlCallback
    public void onStatusUpdate(int i) {
        if (this.k != null) {
            Message obtainMessage = this.k.obtainMessage();
            obtainMessage.obj = Integer.valueOf(i);
            obtainMessage.what = ErrorCode.ERROR_PLACEMENT_EMPTY_AD_IDS;
            this.k.sendMessage(obtainMessage);
        }
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel, com.huawei.health.sportservice.IHeartRateControlCallback
    public void onDataUpdate(Object obj) {
        if (obj == null) {
            LogUtil.h("Track_IndoorEquipViewModel", "onDataUpdate data is null");
            return;
        }
        if (this.k != null) {
            Message obtainMessage = this.k.obtainMessage();
            obtainMessage.obj = obj;
            obtainMessage.what = 801;
            this.k.sendMessage(obtainMessage);
            HashMap hashMap = (HashMap) obj;
            if (hashMap.containsKey("COURSE_NAME_LIST")) {
                this.q.putAll(hashMap);
            }
            if (hashMap.containsKey("CURRENT_STAGE_NUMBER")) {
                this.q.putAll(hashMap);
            }
        }
    }

    public HashMap<String, Object> k() {
        return this.q;
    }

    public void c(SportParamsType sportParamsType, int i) {
        this.mSportDataOutputApi.setParas(sportParamsType, Integer.valueOf(i));
    }

    public void b(boolean z) {
        this.p = z;
    }

    public void bVZ_(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            boolean z = extras.getBoolean("KEY_TO_GET_HAS_NOT_DISCONNECTED", false);
            boolean z2 = extras.getBoolean("KEY_TO_GET_ALLOW_TO_SHOW_UI", false);
            this.g = extras.getBoolean("KEY_TO_GET_STOP_SIGNAL_FROM_WEARABLE", false);
            LogUtil.a("Track_IndoorEquipViewModel", "is it stop from wearable?", Boolean.valueOf(this.g));
            a(z, z2);
        }
    }

    public void d(String str, String str2) {
        if (this.i == null) {
            this.i = new QrCodeOrNfcInfo();
        }
        this.i.setBtMac(str);
        this.i.setBtName(str2);
    }

    public boolean w() {
        return r().equals("291");
    }

    public String r() {
        SportLaunchParams sportLaunchParams;
        if (this.mSportDataOutputApi == null || (sportLaunchParams = (SportLaunchParams) this.mSportDataOutputApi.getParas(SportParamsType.SPORT_LAUNCH_PARAS)) == null) {
            return "";
        }
        String str = (String) sportLaunchParams.getExtra("sportMode", String.class);
        LogUtil.a("Track_IndoorEquipViewModel", "getSportMode() sportMode = ", str);
        return str == null ? "" : str;
    }

    public void c(SupportDataRange supportDataRange) {
        this.h = supportDataRange;
        LogUtil.a("Track_IndoorEquipViewModel", "mSupportDataRange max ", Integer.valueOf(this.h.getMaxLevel()), Integer.valueOf(this.h.getMinLevel()));
    }

    public String o() {
        if (TextUtils.isEmpty(this.f)) {
            this.f = b() ? cei.b().getCurrentMacAddress() : lau.d().b();
        }
        return this.f;
    }

    @Override // com.huawei.indoorequip.viewmodel.IndoorConnectViewModel, com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public String getTag() {
        return "Track_IndoorEquipViewModel";
    }
}
