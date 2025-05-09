package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.utils.FoundationCommonUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class piu {
    protected List<Integer> c;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f16149a = {DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_GOAL_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_USER_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_GOAL_STATE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_IS_RING.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_USER_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_STATE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_IS_RING.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_USER_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_STATE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_IS_RING.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_USER_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_STATE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_IS_RING_NEW.value()};
    private static final String[] d = {"stepGoalValue", "stepUserValue", "stepGoalState", "stepIsRing", "durationGoalValue", "durationUserValue", "durationGoalState", "durationIsRing", "activeGoalValue", "activeUserValue", "activeGoalState", "activeIsRing", "calorieGoalValue", "sport_calorie_sum", "calorieGoalState", "calorieIsRingNew"};
    private static final Map<Integer, String> b = new HashMap(16);

    protected HiHealthData a(HiHealthData hiHealthData) {
        return null;
    }

    protected List<Integer> b() {
        return null;
    }

    protected List<Integer> c() {
        return null;
    }

    protected void d() {
    }

    protected List<Integer> e() {
        return null;
    }

    static {
        int i = 0;
        while (true) {
            int[] iArr = f16149a;
            if (i >= iArr.length) {
                return;
            }
            b.put(Integer.valueOf(iArr[i]), d[i]);
            i++;
        }
    }

    public void a(List<Integer> list) {
        this.c = list;
    }

    public HiHealthData d(HiHealthData hiHealthData, HiHealthData hiHealthData2, List<HiHealthData> list, boolean z) {
        int e2;
        synchronized (e) {
            d();
        }
        int i = 0;
        while (true) {
            if (i < c().size()) {
                Map<Integer, String> map = b;
                int i2 = hiHealthData2.getInt(map.get(c().get(i)));
                synchronized (e) {
                    if (koq.d(this.c, i)) {
                        Integer num = this.c.get(i);
                        if (num != null) {
                            e2 = num.intValue();
                        } else {
                            e2 = e(b().get(i).intValue());
                        }
                    } else {
                        e2 = e(b().get(i).intValue());
                    }
                }
                int e3 = e(hiHealthData, b().get(i).intValue(), e2);
                int i3 = e3 > i2 ? 0 : 1;
                hiHealthData.putInt(map.get(c().get(i)), i2);
                hiHealthData.putInt(map.get(b().get(i)), e3);
                hiHealthData.putInt(map.get(e().get(i)), i3);
                if (z) {
                    e(hiHealthData, list, i, i2, e3);
                }
                i++;
            } else {
                list.add(a(hiHealthData));
                hiHealthData.putInt(b.get(Integer.valueOf(a())), 1);
                d(hiHealthData, hiHealthData2, "sport_distance_sum");
                d(hiHealthData, hiHealthData2, "sport_calorie_sum");
                d(hiHealthData, hiHealthData2, "sport_altitude_offset_sum");
                d(hiHealthData, hiHealthData2, "stepUserValue");
                return hiHealthData;
            }
        }
    }

    private void d(HiHealthData hiHealthData, HiHealthData hiHealthData2, String str) {
        hiHealthData.putInt(str, hiHealthData2.getInt(str));
    }

    private void e(HiHealthData hiHealthData, List<HiHealthData> list, int i, int i2, int i3) {
        HiHealthData d2 = d(hiHealthData, c().get(i).intValue(), i2);
        LogUtil.a("Track_BaseThreeCircleSupplementWorker", "fillInsertList value", Integer.valueOf(i2));
        HiHealthData d3 = d(hiHealthData, e().get(i).intValue(), i3 > i2 ? 0 : 1);
        HiHealthData d4 = d(hiHealthData, b().get(i).intValue(), i3);
        list.add(d2);
        list.add(d3);
        LogUtil.a("Track_BaseThreeCircleSupplementWorker", "fillInsertList goal", Integer.valueOf(i3));
        list.add(d4);
    }

    protected int a() {
        return DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_IS_RING_NEW.value();
    }

    private HiHealthData d(HiHealthData hiHealthData, int i, int i2) {
        HiHealthData hiHealthData2 = new HiHealthData(i);
        hiHealthData2.setStartTime(hiHealthData.getStartTime());
        hiHealthData2.setEndTime(hiHealthData.getEndTime());
        hiHealthData2.setValue(i2);
        hiHealthData2.setDeviceUuid(FoundationCommonUtil.getAndroidId(BaseApplication.e()));
        return hiHealthData2;
    }

    private int e(HiHealthData hiHealthData, int i, int i2) {
        int i3 = hiHealthData.getInt(b.get(Integer.valueOf(i)));
        return i3 == 0 ? i2 : i3;
    }

    private int e(int i) {
        if (DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_GOAL_VALUE.value() == i) {
            return 10000;
        }
        if (DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_VALUE.value() == i) {
            return 25;
        }
        if (DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_VALUE.value() == i) {
            return 12;
        }
        return DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_VALUE.value() == i ? 270000 : 0;
    }
}
