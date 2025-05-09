package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.utils.FoundationCommonUtil;
import health.compact.a.HiDateUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class nix {
    private static final Object b = new Object();
    private static volatile nix e;
    private final int[] d = {DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_GOAL_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_USER_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_GOAL_STATE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_IS_RING.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_USER_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_STATE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_IS_RING.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_USER_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_STATE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_IS_RING.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_USER_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_STATE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_IS_RING_NEW.value()};

    private nix() {
    }

    public static nix c() {
        if (e == null) {
            synchronized (b) {
                if (e == null) {
                    e = new nix();
                }
            }
        }
        return e;
    }

    public void a(Context context, HashMap<Integer, Integer> hashMap, long j, final IBaseResponseCallback iBaseResponseCallback) {
        ArrayList arrayList = new ArrayList(10);
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (entry != null) {
                HiHealthData hiHealthData = new HiHealthData(entry.getKey().intValue());
                hiHealthData.setStartTime(HiDateUtil.t(j));
                hiHealthData.setEndTime(HiDateUtil.f(j));
                hiHealthData.setValue(entry.getValue().intValue());
                hiHealthData.setDeviceUuid(FoundationCommonUtil.getAndroidId(context));
                arrayList.add(hiHealthData);
            }
        }
        LogUtil.a("SCUI_SportGoalAchievementDataManager", "insertSingleAchievementData ", Integer.valueOf(arrayList.size()));
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(arrayList);
        HiHealthManager.d(context).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: nix.3
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                LogUtil.a("SCUI_SportGoalAchievementDataManager", "save dataResult errorCode:", Integer.valueOf(i), "message：", obj);
                if (i == 0) {
                    iBaseResponseCallback.d(i, obj);
                } else {
                    iBaseResponseCallback.d(i, null);
                }
            }
        });
    }

    public void d(final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("SCUI_SportGoalAchievementDataManager", "readThreeCircleData enter ");
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(0L);
        hiAggregateOption.setEndTime(System.currentTimeMillis());
        hiAggregateOption.setType(new int[]{DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_IS_RING_NEW.value()});
        hiAggregateOption.setConstantsKey(new String[]{"calorieIsRingNew"});
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setAggregateType(4);
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setSortOrder(0);
        hiAggregateOption.setCount(1);
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: nix.5
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                LogUtil.a("SCUI_SportGoalAchievementDataManager", "readThreeCircleData callback ", Integer.valueOf(i));
                if (koq.b(list) || list.get(0) == null) {
                    LogUtil.h("SCUI_SportGoalAchievementDataManager", "readThreeCircleData no data");
                    iBaseResponseCallback.d(i, Long.valueOf(HiDateUtil.t(System.currentTimeMillis())));
                } else {
                    iBaseResponseCallback.d(i, Long.valueOf(HiDateUtil.t(list.get(0).getStartTime())));
                }
            }
        });
    }

    public void c(long j, long j2, final ResponseCallback<List<HiHealthData>> responseCallback) {
        LogUtil.a("SCUI_SportGoalAchievementDataManager", "readThreeCircleData enter ");
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(j);
        hiAggregateOption.setEndTime(j2);
        hiAggregateOption.setType(this.d);
        hiAggregateOption.setConstantsKey(new String[]{"stepGoalValue", "stepUserValue", "stepGoalState", "stepIsRing", "durationGoalValue", "durationUserValue", "durationGoalState", "durationIsRing", "activeGoalValue", "activeUserValue", "activeGoalState", "activeIsRing", "calorieGoalValue", "calorieUserValue", "calorieGoalState", "calorieIsRingNew"});
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setAggregateType(4);
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setSortOrder(1);
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: nix.1
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                LogUtil.a("SCUI_SportGoalAchievementDataManager", "readThreeCircleData callback ", Integer.valueOf(i));
                if (koq.b(list)) {
                    LogUtil.h("SCUI_SportGoalAchievementDataManager", "readThreeCircleData no data");
                    responseCallback.onResponse(i, null);
                } else {
                    responseCallback.onResponse(i, list);
                }
            }
        });
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void b(long j, String str, final ResponseCallback<List<HiHealthData>> responseCallback) {
        char c;
        int value;
        String str2;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("SCUI_SportGoalAchievementDataManager", "readSportGoalAchievementData goalKey ", str, " timeMillis ", Long.valueOf(j));
            return;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -1062371202:
                if (str.equals("900200007")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1062371201:
                if (str.equals("900200008")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1062371200:
                if (str.equals("900200009")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            value = DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_VALUE.value();
            str2 = "calorieGoalValue";
        } else if (c == 1) {
            value = DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_VALUE.value();
            str2 = "durationGoalValue";
        } else if (c == 2) {
            value = DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_VALUE.value();
            str2 = "activeGoalValue";
        } else {
            LogUtil.h("SCUI_SportGoalAchievementDataManager", "readSportGoalAchievementData goalKey ", str, " timeMillis ", Long.valueOf(j));
            return;
        }
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(HiDateUtil.t(j));
        hiAggregateOption.setEndTime(HiDateUtil.f(j));
        hiAggregateOption.setType(new int[]{value});
        hiAggregateOption.setConstantsKey(new String[]{str2});
        hiAggregateOption.setAggregateType(4);
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setReadType(0);
        HiHealthManager.d(com.huawei.haf.application.BaseApplication.e()).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: nix.2
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                ResponseCallback responseCallback2 = ResponseCallback.this;
                if (responseCallback2 == null) {
                    LogUtil.h("SCUI_SportGoalAchievementDataManager", "readSportGoalAchievementData onResult callback is null");
                } else {
                    responseCallback2.onResponse(i, list);
                }
            }
        });
    }
}
