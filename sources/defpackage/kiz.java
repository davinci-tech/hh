package defpackage;

import android.text.TextUtils;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HiHealthDictField;
import com.huawei.hihealth.dictionary.model.HiHealthDictType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.threecircle.strategy.SyncStrategy;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.operation.utils.Constants;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class kiz {

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f14409a = {40002, 40003, 47101, 40004, SmartMsgConstant.MSG_TYPE_RIDE_USER};
    private static final int[] e = {2, 4};
    private static final int[] c = {DicDataTypeUtil.DataType.DAILY_ACTIVITY_RECORD_ACTIVE_HOUR.value(), DicDataTypeUtil.DataType.DAILY_ACTIVITY_RECORD_CALORIE.value(), DicDataTypeUtil.DataType.DAILY_ACTIVITY_RECORD_DURATION.value(), DicDataTypeUtil.DataType.DAILY_ACTIVITY_RECORD_STEPS.value()};
    private static final int[] i = {DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_GOAL_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_USER_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_GOAL_STATE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_IS_RING.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_USER_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_STATE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_IS_RING_NEW.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_USER_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_STATE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_IS_RING.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_USER_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_STATE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_IS_RING.value()};
    private static final Map<String, Long> b = new ConcurrentHashMap();
    private static final Object d = new Object();

    public static int[] c() {
        return (int[]) f14409a.clone();
    }

    public static int[] e() {
        return (int[]) e.clone();
    }

    public static int[] b() {
        return (int[]) c.clone();
    }

    public static int[] d() {
        return (int[]) i.clone();
    }

    public static Collection<cvu> d(Collection<HiHealthData> collection, String str, String str2) {
        HashMap hashMap = new HashMap(16);
        if (koq.b(collection) || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.e("DEVMGR_WriteBackUtils", "toSamplePoints() input with empty param");
            return hashMap.values();
        }
        LogUtil.d("DEVMGR_WriteBackUtils", "toSamplePoints() input dataSize=", Integer.valueOf(collection.size()));
        HiHealthDictManager d2 = HiHealthDictManager.d(BaseApplication.getContext());
        Comparator<cvv> comparator = new Comparator<cvv>() { // from class: kiz.5
            @Override // java.util.Comparator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public int compare(cvv cvvVar, cvv cvvVar2) {
                return Long.compare(cvvVar.a(), cvvVar2.a());
            }
        };
        for (HiHealthData hiHealthData : collection) {
            if (hiHealthData != null) {
                int b2 = b(hiHealthData.getType(), d2);
                String str3 = b2 + "_" + hiHealthData.getStartTime() + "_" + hiHealthData.getEndTime();
                cvu cvuVar = (cvu) hashMap.get(str3);
                TreeSet treeSet = new TreeSet(comparator);
                if (cvuVar == null) {
                    cvuVar = new cvu();
                    cvuVar.setSrcPkgName(str);
                    cvuVar.setWearPkgName(str2);
                    cvuVar.d(b(hiHealthData.getStartTime()));
                    cvuVar.b(b(hiHealthData.getEndTime()));
                    cvuVar.c(b2);
                } else {
                    treeSet.addAll(cvuVar.a());
                }
                cvv cvvVar = new cvv();
                cvvVar.d(hiHealthData.getType());
                cvvVar.b(e(hiHealthData.getIntValue()));
                treeSet.add(cvvVar);
                cvuVar.e(new ArrayList(treeSet));
                hashMap.put(str3, cvuVar);
            }
        }
        for (cvu cvuVar2 : hashMap.values()) {
            HiHealthDictType d3 = d2.d(Long.valueOf(cvuVar2.e()).intValue());
            if (d3 != null) {
                a(d3, cvuVar2, comparator);
            }
        }
        LogUtil.d("DEVMGR_WriteBackUtils", "toSamplePoints() output resultSize=", Integer.valueOf(hashMap.size()));
        return hashMap.values();
    }

    private static void a(HiHealthDictType hiHealthDictType, cvu cvuVar, Comparator<cvv> comparator) {
        TreeSet treeSet = new TreeSet();
        Iterator<HiHealthDictField> it = hiHealthDictType.b().iterator();
        while (it.hasNext()) {
            treeSet.add(Integer.valueOf(it.next().c()));
        }
        Iterator<cvv> it2 = cvuVar.a().iterator();
        while (it2.hasNext()) {
            treeSet.remove(Integer.valueOf(Long.valueOf(it2.next().a()).intValue()));
        }
        if (treeSet.isEmpty()) {
            return;
        }
        TreeSet treeSet2 = new TreeSet(comparator);
        Iterator it3 = treeSet.iterator();
        while (it3.hasNext()) {
            int intValue = ((Integer) it3.next()).intValue();
            cvv cvvVar = new cvv();
            cvvVar.d(intValue);
            cvvVar.b(e(0));
            treeSet2.add(cvvVar);
        }
        treeSet2.addAll(cvuVar.a());
        cvuVar.e(new ArrayList(treeSet2));
    }

    public static long b(long j) {
        return j / 1000;
    }

    public static long b(long j, long j2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.set(14, 0);
        if (j2 >= 60000) {
            calendar.set(13, 0);
        }
        if (j2 == 1800000) {
            calendar.set(12, calendar.get(12) < 30 ? 0 : 30);
        }
        if (j2 >= 3600000) {
            calendar.set(12, 0);
        }
        if (j2 >= 86400000) {
            calendar.set(11, 0);
        }
        return calendar.getTimeInMillis();
    }

    public static long c(long j, long j2) {
        if (86400000 == j2) {
            return jdl.e(j);
        }
        return b(j, j2) + j2;
    }

    public static String a(List<HiHealthData> list) {
        if (koq.b(list)) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (i2 > 0) {
                sb.append(',');
            }
            sb.append(e(list.get(i2)));
        }
        sb.append(']');
        return sb.toString();
    }

    private static String e(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            return Constants.NULL;
        }
        StringBuilder sb = new StringBuilder("{startTime:");
        sb.append(hiHealthData.getStartTime());
        for (Map.Entry<String, Object> entry : hiHealthData.getValueHolder().valueSet()) {
            String key = entry.getKey();
            if (key.startsWith("WriteBackKey_")) {
                sb.append(',');
                sb.append(key);
                sb.append(':');
                sb.append(entry.getValue());
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public static int d(int i2, long j) {
        if (j != 86400000 && j != 1800000) {
            return -1;
        }
        if (i2 == DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE.value()) {
            if (j == 1800000) {
                return DicDataTypeUtil.DataType.DAILY_ACTIVITY_RECORD_ACTIVE_HOUR.value();
            }
            return DicDataTypeUtil.DataType.TODAY_ACTIVITY_RECORD_ACTIVE_HOUR.value();
        }
        if (i2 != 2) {
            if (i2 != 3) {
                if (i2 != 4) {
                    if (i2 != 5) {
                        if (i2 == 7 || i2 == 47101) {
                            if (j == 1800000) {
                                return DicDataTypeUtil.DataType.DAILY_ACTIVITY_RECORD_DURATION.value();
                            }
                            return DicDataTypeUtil.DataType.TODAY_ACTIVITY_RECORD_DURATION.value();
                        }
                        switch (i2) {
                            case 40002:
                                break;
                            case 40003:
                                break;
                            case 40004:
                                break;
                            case SmartMsgConstant.MSG_TYPE_RIDE_USER /* 40005 */:
                                break;
                            default:
                                if (HiHealthDictManager.d(com.huawei.haf.application.BaseApplication.e()).f(i2) == null) {
                                    return -1;
                                }
                                return i2;
                        }
                    }
                    if (j == 1800000) {
                        return -1;
                    }
                    return DicDataTypeUtil.DataType.TODAY_ACTIVITY_RECORD_CLIMB.value();
                }
                if (j == 1800000) {
                    return DicDataTypeUtil.DataType.DAILY_ACTIVITY_RECORD_CALORIE.value();
                }
                return DicDataTypeUtil.DataType.TODAY_ACTIVITY_RECORD_ACTIVE_CALORIE.value();
            }
            if (j == 1800000) {
                return -1;
            }
            return DicDataTypeUtil.DataType.TODAY_ACTIVITY_RECORD_DISTANCE.value();
        }
        if (j == 1800000) {
            return DicDataTypeUtil.DataType.DAILY_ACTIVITY_RECORD_STEPS.value();
        }
        return DicDataTypeUtil.DataType.TODAY_ACTIVITY_RECORD_STEPS.value();
    }

    private static int b(int i2, HiHealthDictManager hiHealthDictManager) {
        HiHealthDictType f = hiHealthDictManager.f(i2);
        if (f == null) {
            return -1;
        }
        return f.i();
    }

    private static byte[] e(int i2) {
        byte[] bArr = {(byte) ((i2 >> 24) & 255), (byte) ((i2 >> 16) & 255), (byte) ((i2 >> 8) & 255), (byte) (i2 & 255)};
        LogUtil.b("DEVMGR_WriteBackUtils", "toBytes() origin=", Integer.valueOf(i2), ", result=", Arrays.toString(bArr));
        return bArr;
    }

    public static void a(String str) {
        if (SyncStrategy.SP_KEY_RECONNECT_LAST_SYNC_TIME.equals(str)) {
            SharedPreferenceManager.e("threeCircleSp", str, System.currentTimeMillis());
            return;
        }
        synchronized (d) {
            b.put(str, Long.valueOf(System.currentTimeMillis()));
        }
    }

    public static long d(String str) {
        long j = 0;
        if (SyncStrategy.SP_KEY_RECONNECT_LAST_SYNC_TIME.equals(str)) {
            return SharedPreferenceManager.b("threeCircleSp", str, 0L);
        }
        Map<String, Long> map = b;
        synchronized (map) {
            Long l = map.get(str);
            if (l != null) {
                j = l.longValue();
            }
        }
        return j;
    }
}
