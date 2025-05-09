package defpackage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OperationKey;
import com.huawei.threecircle.ThreeCircleConfigUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public class iwl {

    /* renamed from: a, reason: collision with root package name */
    private static a f13638a;
    private static ExtendHandler b;
    private static int d;
    private int h;
    private static final Map<String, HiHealthData> c = new ConcurrentHashMap();
    private static final Object e = new Object();

    static {
        a aVar = new a();
        f13638a = aVar;
        b = HandlerCenter.yt_(aVar, "HiH_3CircAftProc");
    }

    private iwl() {
        this.h = 62;
    }

    public static iwl c() {
        return c.b;
    }

    static class c {
        private static final iwl b = new iwl();
    }

    public void j(HiHealthData hiHealthData) {
        String str;
        if (iwp.a(hiHealthData) && iuz.i(BaseApplication.e(), hiHealthData.getUserId()) && hiHealthData.getInt("merged") == 0) {
            d = hiHealthData.getUserId();
            ThreadPoolManager.d().execute(new Runnable() { // from class: iwm
                @Override // java.lang.Runnable
                public final void run() {
                    iwl.this.a();
                }
            });
            long t = HiDateUtil.t(hiHealthData.getStartTime());
            int type = hiHealthData.getType();
            switch (type) {
                case 7:
                case 47101:
                    str = "INTENSITY_" + t + "_" + hiHealthData.getUserId();
                    break;
                case 20001:
                case 40002:
                case 40003:
                case 40054:
                    str = "STEP_CALORIE_" + t + "_" + hiHealthData.getUserId();
                    break;
                default:
                    if (iwp.b(type)) {
                        str = "ACTIVE_HOUR_" + t + "_" + hiHealthData.getUserId();
                        break;
                    } else if (iwp.j(type)) {
                        str = "STEP_STATE_CHANGE_" + t + "_" + hiHealthData.getUserId();
                        break;
                    } else if (iwp.d(type)) {
                        str = "CALORIE_STATE_CHANGE_" + t + "_" + hiHealthData.getUserId();
                        break;
                    } else if (iwp.e(type)) {
                        str = "INTENSITY_STATE_CHANGE_" + t + "_" + hiHealthData.getUserId();
                        break;
                    } else if (!iwp.a(type)) {
                        str = null;
                        break;
                    } else {
                        str = "ACTIVE_HOUR_STATE_CHANGE_" + t + "_" + hiHealthData.getUserId();
                        break;
                    }
            }
            if (str == null) {
                LogUtil.h("HiH_3CircAftProc", "triggerAfterProcess() [", str, "]  is empty");
                return;
            }
            Message obtain = Message.obtain();
            obtain.what = 1;
            Bundle bundle = new Bundle();
            bundle.putString("ThreeCircleMapKey", str);
            bundle.putParcelable("ThreeCircleDataKey", hiHealthData);
            obtain.setData(bundle);
            b.sendMessage(obtain);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public void a() {
        synchronized (this) {
            int c2 = HiDateUtil.c(System.currentTimeMillis(), 1);
            int a2 = SharedPreferenceManager.a("HiHealthService", "lastSaveThreeCircleLog", 0);
            if (c2 == a2) {
                return;
            }
            if (a2 == 0) {
                LogUtil.a("HiH_3CircAftProc", "saveThreeCircleLog first");
                for (int i = this.h; i > 0; i--) {
                    int c3 = HiDateUtil.c(System.currentTimeMillis(), i);
                    c(c3);
                    SharedPreferenceManager.b("HiHealthService", "lastSaveThreeCircleLog", c3);
                }
                return;
            }
            try {
                int min = Math.min(HiDateUtil.b(a2, c2, "yyyyMMdd"), this.h);
                LogUtil.a("HiH_3CircAftProc", "saveThreeCircleLog gap=", Integer.valueOf(min));
                while (min > 0) {
                    int c4 = HiDateUtil.c(System.currentTimeMillis(), min);
                    c(c4);
                    SharedPreferenceManager.b("HiHealthService", "lastSaveThreeCircleLog", c4);
                    min--;
                }
            } catch (ParseException unused) {
                LogUtil.a("HiH_3CircAftProc", "saveThreeCircleLog ParseException");
            }
        }
    }

    private void c(int i) {
        List asList = Arrays.asList(",AS=", ",AG=", ",AU=", ",OAU=", ",CS=", ",CG=", ",CU=", ",OCU=", ",DS=", ",DG=", ",DU=", ",ODU=");
        List asList2 = Arrays.asList(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_STATE_STAT.value()), Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_VALUE_STAT.value()), Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_USER_VALUE_STAT.value()), Integer.valueOf(DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE_COUNT.value()), Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_STATE_STAT.value()), Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_VALUE_STAT.value()), Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_USER_VALUE_STAT.value()), 40003, Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_STATE_STAT.value()), Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_VALUE_STAT.value()), Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_USER_VALUE_STAT.value()), 47101);
        HashMap hashMap = new HashMap();
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_STATE_STAT.value()), ",ASS=");
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_STATE_STAT.value()), ",CSS=");
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_STATE_STAT.value()), ",DSS=");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(System.currentTimeMillis()).append(",").append(i);
        int b2 = ikr.b(BaseApplication.e()).a(0, d, 0).b();
        String str = "";
        for (int i2 = 0; i2 < asList2.size(); i2++) {
            String str2 = (String) asList.get(i2);
            int intValue = ((Integer) asList2.get(i2)).intValue();
            igo d2 = ijd.c(BaseApplication.e()).d(i, intValue, b2);
            stringBuffer.append(str2);
            if (d2 != null) {
                stringBuffer.append(d2.l());
                if (hashMap.containsKey(Integer.valueOf(intValue))) {
                    stringBuffer.append((String) hashMap.get(Integer.valueOf(intValue))).append(d2.g());
                }
                str = d2.h();
            }
        }
        stringBuffer.append(",TZ=").append(str);
        iwp.c(stringBuffer.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void i() {
        if (SharedPreferenceManager.a("HiHealthService", "ThreeCircleBi", 0) == HiDateUtil.c(System.currentTimeMillis())) {
            return;
        }
        SharedPreferenceManager.b("HiHealthService", "ThreeCircleBi", HiDateUtil.c(System.currentTimeMillis()));
        List<Integer> g = iis.d().g(d);
        ijt b2 = ijt.b();
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(System.currentTimeMillis() - 604800000);
        hiAggregateOption.setEndTime(System.currentTimeMillis() - 86400000);
        hiAggregateOption.setType(new int[]{DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_USER_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_USER_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_USER_VALUE.value()});
        hiAggregateOption.setConstantsKey(new String[]{"durationGoalValue", "durationUserValue", "activeGoalValue", "activeUserValue", "calorieGoalValue", "calorieUserValue"});
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setAggregateType(4);
        List<HiHealthData> c2 = b2.c(g, hiAggregateOption, (ifl) null);
        if (HiCommonUtil.d(c2)) {
            return;
        }
        hiAggregateOption.setType(new int[]{47101, DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE_COUNT.value(), 40003, DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_STATE_STAT.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_STATE_STAT.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_STATE_STAT.value()});
        hiAggregateOption.setConstantsKey(new String[]{"durationUserValue", "activeUserValue", "calorieUserValue", "calorieGoalState", "durationGoalState", "activeGoalState"});
        List<HiHealthData> a2 = b2.a(iis.d().h(d), hiAggregateOption, (ifl) null);
        if (HiCommonUtil.d(a2)) {
            return;
        }
        for (HiHealthData hiHealthData : c2) {
            Iterator<HiHealthData> it = a2.iterator();
            while (true) {
                if (it.hasNext()) {
                    HiHealthData next = it.next();
                    if (hiHealthData.getDay() == next.getDay()) {
                        if (!c(hiHealthData, next)) {
                            LogUtil.a("HiH_3CircAftProc", "setBiEvent is not SameValue");
                        }
                    }
                }
            }
        }
    }

    private static boolean c(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
        int i = hiHealthData.getInt("durationUserValue");
        int i2 = hiHealthData2.getInt("durationUserValue");
        int i3 = hiHealthData.getInt("activeUserValue");
        int i4 = hiHealthData2.getInt("activeUserValue");
        int i5 = hiHealthData.getInt("calorieUserValue");
        int i6 = hiHealthData2.getInt("calorieUserValue");
        if (i == i2 && i3 == i4 && i5 == i6) {
            return true;
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(17);
        linkedHashMap.put("threeCircleIntensity", String.valueOf(i));
        linkedHashMap.put("sportDataIntensity", String.valueOf(i2));
        linkedHashMap.put("threeCircleActiveHour", String.valueOf(i3));
        linkedHashMap.put("sportDataActiveHour", String.valueOf(i4));
        linkedHashMap.put("threeCircleCalorie", String.valueOf(i5));
        linkedHashMap.put("sportDataCalorie", String.valueOf(i6));
        linkedHashMap.put("durationGoalValue", String.valueOf(hiHealthData.getInt("durationGoalValue")));
        linkedHashMap.put("activeGoalValue", String.valueOf(hiHealthData.getInt("activeGoalValue")));
        linkedHashMap.put("calorieGoalValue", String.valueOf(hiHealthData.getInt("calorieGoalValue")));
        linkedHashMap.put("day", String.valueOf(hiHealthData.getDay()));
        linkedHashMap.put("calorieGoalState", String.valueOf(hiHealthData2.getInt("calorieGoalState")));
        linkedHashMap.put("durationGoalState", String.valueOf(hiHealthData2.getInt("durationGoalState")));
        linkedHashMap.put("activeGoalState", String.valueOf(hiHealthData2.getInt("activeGoalState")));
        ReleaseLogUtil.e("HiH_3CircAftProc", "biMap=" + HiJsonUtil.e(linkedHashMap));
        ivz.d(BaseApplication.e()).e(OperationKey.HEALTH_APP_THREE_CIRCLE_2129007.value(), linkedHashMap, false);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void k(HiHealthData hiHealthData) {
        HashMap hashMap = new HashMap(16);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_USER_VALUE.value()), Double.valueOf(iwp.c(hiHealthData, 40002)));
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_USER_VALUE.value()), Double.valueOf(iwp.c(hiHealthData, 40003)));
        iwp.e(hiHealthData, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void g(HiHealthData hiHealthData) {
        HashMap hashMap = new HashMap(16);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_USER_VALUE.value()), Double.valueOf(iwp.c(hiHealthData, DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE_COUNT.value())));
        iwp.e(hiHealthData, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void h(HiHealthData hiHealthData) {
        HashMap hashMap = new HashMap(16);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_USER_VALUE.value()), Double.valueOf(iwp.c(hiHealthData, 47101)));
        iwp.e(hiHealthData, hashMap);
    }

    private static void b(HiHealthData hiHealthData, ThreeCircleConfigUtil.ThreeCircleConfig threeCircleConfig, int i, int i2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put(Integer.valueOf(i), Double.valueOf(iwp.a(threeCircleConfig)));
        int d2 = iwp.d(hiHealthData);
        if (HiDateUtil.c(hiHealthData.getStartTime()) == HiDateUtil.c(System.currentTimeMillis()) || 1 == d2) {
            hashMap.put(Integer.valueOf(i2), Double.valueOf(d2));
        }
        iwp.e(hiHealthData, hashMap);
    }

    public static void f(HiHealthData hiHealthData) {
        b(hiHealthData, ThreeCircleConfigUtil.ThreeCircleConfig.STEP, DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_IS_RING.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_GOAL_STATE.value());
    }

    public static void c(HiHealthData hiHealthData) {
        b(hiHealthData, ThreeCircleConfigUtil.ThreeCircleConfig.CALORIE, DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_IS_RING_NEW.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_STATE.value());
    }

    public static void i(HiHealthData hiHealthData) {
        b(hiHealthData, ThreeCircleConfigUtil.ThreeCircleConfig.INTENSITY, DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_IS_RING.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_STATE.value());
    }

    public static void b(HiHealthData hiHealthData) {
        b(hiHealthData, ThreeCircleConfigUtil.ThreeCircleConfig.ACTIVE_HOUR, DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_IS_RING.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_STATE.value());
    }

    static class a implements Handler.Callback {
        private a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message == null) {
                return false;
            }
            int i = message.what;
            if (i == 1) {
                HiHealthData hiHealthData = (HiHealthData) message.getData().getParcelable("ThreeCircleDataKey");
                String string = message.getData().getString("ThreeCircleMapKey");
                LogUtil.a("HiH_3CircAftProc", "produce: key:", string, "data:", hiHealthData);
                if (iwl.c.containsKey(string)) {
                    LogUtil.h("HiH_3CircAftProc", "triggerAfterProcess() [", string, "] empty or duplicated, skipped");
                } else {
                    hiHealthData.setTimeZone(hiHealthData.getTimeZone());
                    iwl.c.put(string, hiHealthData);
                    if (iwl.c.size() == 1) {
                        iwl.b.sendEmptyMessage(2, 5000L);
                    }
                    ReleaseLogUtil.e("HiH_3CircAftProc", "trigAfterProc[", string, "] InitMap,cacheSz=", Integer.valueOf(iwl.c.size()));
                }
            } else {
                if (i != 2) {
                    return false;
                }
                e(iwl.c);
                iwl.i();
            }
            return true;
        }

        private void e(Map<String, HiHealthData> map) {
            if (map.isEmpty()) {
                LogUtil.b("HiH_3CircAftProc", "triggerAfterProcess() skipped by empty map");
                return;
            }
            for (HiHealthData hiHealthData : map.values()) {
                int type = hiHealthData.getType();
                switch (type) {
                    case 7:
                    case 47101:
                        iwl.h(hiHealthData);
                        break;
                    case 20001:
                    case 40002:
                    case 40003:
                    case 40054:
                        iwl.k(hiHealthData);
                        break;
                    default:
                        if (iwp.b(type)) {
                            iwl.g(hiHealthData);
                            break;
                        } else if (iwp.j(type)) {
                            iwl.f(hiHealthData);
                            break;
                        } else if (iwp.d(type)) {
                            iwl.c(hiHealthData);
                            break;
                        } else if (iwp.e(type)) {
                            iwl.i(hiHealthData);
                            break;
                        } else if (iwp.a(type)) {
                            iwl.b(hiHealthData);
                            break;
                        } else {
                            break;
                        }
                }
            }
            map.clear();
            ReleaseLogUtil.e("HiH_3CircAftProc", "trigAfterProcOk,Mapclear");
        }
    }
}
