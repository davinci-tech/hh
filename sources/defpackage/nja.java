package defpackage;

import android.content.Intent;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.api.phoneprocess.SamplePointAfterProcess;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class nja implements SamplePointAfterProcess {
    @Override // com.huawei.health.devicemgr.api.phoneprocess.SamplePointAfterProcess
    public HiHealthData onSamplePointAfterProcess(final HiHealthData hiHealthData, String str) {
        if (hiHealthData == null) {
            LogUtil.h("SCUI_ThreeCirclePointAfterProcess", "hiHealthData == null");
            return null;
        }
        long startTime = hiHealthData.getStartTime();
        long endTime = hiHealthData.getEndTime();
        hiHealthData.setStartTime(jdl.t(startTime));
        hiHealthData.setEndTime(jdl.e(endTime));
        final HashMap<Integer, Integer> hashMap = (HashMap) HiJsonUtil.b(hiHealthData.getFieldsValue(), new TypeToken<HashMap<Integer, Integer>>() { // from class: nja.5
        }.getType());
        e(hashMap, hiHealthData, startTime);
        if (jdl.ac(startTime)) {
            LogUtil.a("SCUI_ThreeCirclePointAfterProcess", "isCurrentDay return startTime is", Long.valueOf(startTime));
            return hiHealthData;
        }
        if (hashMap != null) {
            final int c = c(hashMap, DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_GOAL_VALUE.value());
            final int c2 = c(hashMap, DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_VALUE.value());
            final int c3 = c(hashMap, DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_VALUE.value());
            final int c4 = c(hashMap, DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_VALUE.value());
            LogUtil.a("SCUI_ThreeCirclePointAfterProcess", "onSamplePointAfterProcess startTime ", Long.valueOf(startTime), " syncStepTarget ", Integer.valueOf(c), " syncIntensityTarget ", Integer.valueOf(c2), "  syncCalorieTarget ", Integer.valueOf(c3), " syncActiveHourTarget ", Integer.valueOf(c4));
            d(jdl.t(startTime), jdl.e(endTime), new ResponseCallback<List<HiHealthData>>() { // from class: nja.4
                @Override // com.huawei.hwbasemgr.ResponseCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onResponse(int i, List<HiHealthData> list) {
                    if (koq.b(list)) {
                        LogUtil.h("SCUI_ThreeCirclePointAfterProcess", "onSamplePointAfterProcess data is null ");
                        return;
                    }
                    HiHealthData hiHealthData2 = list.get(0);
                    int i2 = hiHealthData2.getInt("stepGoalValue");
                    int i3 = hiHealthData2.getInt("durationGoalValue");
                    int i4 = hiHealthData2.getInt("activeGoalValue");
                    int i5 = hiHealthData2.getInt("calorieGoalValue");
                    LogUtil.a("SCUI_ThreeCirclePointAfterProcess", "onSamplePointAfterProcess stepTarget ", Integer.valueOf(i2), " intensityTarget ", Integer.valueOf(i3), "  calorieTarget ", Integer.valueOf(i5), " standTarget ", Integer.valueOf(i4));
                    hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_GOAL_VALUE.value()), Integer.valueOf(nja.this.e(i2, c)));
                    hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_VALUE.value()), Integer.valueOf(nja.this.e(i3, c2)));
                    hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_VALUE.value()), Integer.valueOf(nja.this.e(i5, c3)));
                    hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_VALUE.value()), Integer.valueOf(nja.this.e(i4, c4)));
                    hiHealthData.setFieldsValue(HiJsonUtil.e(hashMap));
                }
            });
            try {
                Thread.sleep(50L);
            } catch (InterruptedException unused) {
                LogUtil.b("SCUI_ThreeCirclePointAfterProcess", "InterruptedException");
            }
            return hiHealthData;
        }
        LogUtil.h("SCUI_ThreeCirclePointAfterProcess", "values is null");
        return hiHealthData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int e(int i, int i2) {
        return i != 0 ? Math.min(i, i2) : i2;
    }

    private void d(long j, long j2, ResponseCallback<List<HiHealthData>> responseCallback) {
        nix.c().c(j, j2, responseCallback);
    }

    private void e(HashMap<Integer, Integer> hashMap, final HiHealthData hiHealthData, long j) {
        if (hashMap == null) {
            LogUtil.h("SCUI_ThreeCirclePointAfterProcess", "saveStatisticsData values is null.");
            return;
        }
        int c = c(hashMap, DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_USER_VALUE.value());
        int c2 = c(hashMap, DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_USER_VALUE.value());
        if (jdl.ad(j)) {
            a(j, c, c2);
        }
        int c3 = c(hashMap, DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_USER_VALUE.value());
        int c4 = c(hashMap, DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CLIMB_USER_VALUE.value());
        int c5 = c(hashMap, DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DISTANCE_USER_VALUE.value());
        ReleaseLogUtil.e("SCUI_ThreeCirclePointAfterProcess", "time: ", Long.valueOf(j), "saveStatisticsData syncTotalStep ", LogAnonymous.b(c), " syncTotalCalorie ", LogAnonymous.b(c2), " syncTotalIntensity ", LogAnonymous.b(c3), " syncTotalClimb ", LogAnonymous.b(c4), " syncTotalDistance ", LogAnonymous.b(c5));
        if (c == 0 && c2 == 0 && c3 == 0 && c4 == 0 && c5 == 0) {
            LogUtil.h("SCUI_ThreeCirclePointAfterProcess", "All zero data");
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(e(40002, hiHealthData, c));
        arrayList.add(e(40003, hiHealthData, c2));
        arrayList.add(e(47101, hiHealthData, c3));
        arrayList.add(e(SmartMsgConstant.MSG_TYPE_RIDE_USER, hiHealthData, c4));
        arrayList.add(e(40004, hiHealthData, c5));
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(arrayList);
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: nja.2
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                LogUtil.a("SCUI_ThreeCirclePointAfterProcess", "onSamplePointAfterProcess errorCode:", Integer.valueOf(i));
                if (jec.ab(new Date(hiHealthData.getStartTime()))) {
                    nja.this.a();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        BroadcastManagerUtil.bFG_(BaseApplication.getContext(), new Intent("com.huawei.health.SYNC_DB_FOR_DEVICE_CHANGE"));
    }

    private HiHealthData e(int i, HiHealthData hiHealthData, int i2) {
        HiHealthData hiHealthData2 = new HiHealthData();
        hiHealthData2.setType(i);
        hiHealthData2.setDeviceUuid(hiHealthData.getDeviceUuid());
        hiHealthData2.setTimeInterval(hiHealthData.getStartTime(), hiHealthData.getEndTime());
        hiHealthData2.setValue(i2);
        return hiHealthData2;
    }

    private int c(HashMap<Integer, Integer> hashMap, int i) {
        if (!hashMap.containsKey(Integer.valueOf(i)) || hashMap.get(Integer.valueOf(i)) == null) {
            return 0;
        }
        return hashMap.get(Integer.valueOf(i)).intValue();
    }

    private void a(final long j, final int i, final int i2) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: niy
            @Override // java.lang.Runnable
            public final void run() {
                nja.this.b(j, i, i2);
            }
        });
    }

    /* synthetic */ void b(long j, int i, int i2) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(5);
        linkedHashMap.put(OpAnalyticsConstants.RESULT_DESCRIBE, "saveFutureError" + j);
        DeviceInfo c = c();
        if (c != null) {
            linkedHashMap.put("deviceVersion", c.getSoftVersion());
            linkedHashMap.put("deviceName", c.getDeviceName());
        }
        linkedHashMap.put("syncTotalStep", String.valueOf(i));
        linkedHashMap.put("syncTotalCalorie", String.valueOf(i2));
        OpAnalyticsUtil.getInstance().setKeyProcessEvent(OperationKey.HEALTH_APP_STEP_COUNT_85070014.value(), linkedHashMap);
        ReleaseLogUtil.c("SCUI_ThreeCirclePointAfterProcess", "Future error：", Long.valueOf(j), " cl:' ", Integer.valueOf(i2));
    }

    private DeviceInfo c() {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cuo.d().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "SCUI_ThreeCirclePointAfterProcess");
        if (koq.b(deviceList)) {
            return null;
        }
        Iterator<DeviceInfo> it = deviceList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DeviceInfo next = it.next();
            if (next != null && !cvt.c(next.getProductType())) {
                deviceInfo = next;
                break;
            }
        }
        LogUtil.a("SCUI_ThreeCirclePointAfterProcess", "getConnectDeviceInfo() deviceInfo ", deviceInfo);
        return deviceInfo;
    }
}
