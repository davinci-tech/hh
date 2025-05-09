package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.type.HiConfigDataType;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HiHealthDictField;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

/* loaded from: classes7.dex */
public class iwg {
    private static List<String> e = new ArrayList(10);

    public static List<HiHealthData> d(Context context, List<HiHealthData> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        e.clear();
        c(context, arrayList, list);
        return arrayList;
    }

    private static void c(Context context, List<HiHealthData> list, List<HiHealthData> list2) {
        for (HiHealthData hiHealthData : list2) {
            int type = hiHealthData.getType();
            int userId = hiHealthData.getUserId();
            switch (AnonymousClass1.e[HiHealthDataType.e(type).ordinal()]) {
                case 1:
                    a(list, hiHealthData, HiHealthDataType.a(type), a(hiHealthData), userId);
                    break;
                case 2:
                    if (type == DicDataTypeUtil.DataType.SLEEP_DETAILS.value()) {
                        long r = HiDateUtil.r(hiHealthData.getEndTime());
                        hiHealthData.setStartTime(hiHealthData.getEndTime());
                        a(list, hiHealthData, 22100, r, userId);
                        break;
                    } else if (HiHealthDictManager.d(context).d(type) == null || !HiHealthDictManager.d(context).o(type)) {
                        if (type <= 30999) {
                            a(list, hiHealthData, 30001, a(hiHealthData), userId);
                            break;
                        } else {
                            LogUtil.a("HiH_HiStatUtil", "SEQUENCE is not supported, type is ", Integer.valueOf(hiHealthData.getType()));
                            break;
                        }
                    } else {
                        a(list, hiHealthData, type, a(hiHealthData), userId);
                        break;
                    }
                case 3:
                    if (HiHealthDictManager.d(context).d(type) != null) {
                        a(context, list, hiHealthData, a(hiHealthData));
                        break;
                    } else {
                        break;
                    }
                case 4:
                    a(list, type, userId, hiHealthData);
                    break;
                case 5:
                    if (40054 == type) {
                        a(list, hiHealthData, type, a(hiHealthData), userId);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    int[] b = HiConfigDataType.b(type);
                    if (b != null) {
                        for (int i : b) {
                            a(list, hiHealthData, i, a(hiHealthData), userId);
                        }
                        break;
                    } else {
                        break;
                    }
            }
        }
    }

    /* renamed from: iwg$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[HiHealthDataType.Category.values().length];
            e = iArr;
            try {
                iArr[HiHealthDataType.Category.POINT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[HiHealthDataType.Category.SEQUENCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                e[HiHealthDataType.Category.SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                e[HiHealthDataType.Category.SESSION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                e[HiHealthDataType.Category.STAT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                e[HiHealthDataType.Category.CONFIG.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private static long a(HiHealthData hiHealthData) {
        return HiDateUtil.e(hiHealthData.getStartTime(), TimeZone.getDefault());
    }

    private static void a(List<HiHealthData> list, int i, int i2, HiHealthData hiHealthData) {
        if (i <= 21000) {
            a(list, hiHealthData, 20001, a(hiHealthData), i2);
            return;
        }
        if (i <= 22099) {
            a(list, hiHealthData, 22000, HiDateUtil.r(hiHealthData.getStartTime()), i2);
        } else if (i <= 22199) {
            a(list, hiHealthData, 22100, HiDateUtil.r(hiHealthData.getStartTime()), i2);
        } else {
            LogUtil.a("HiH_HiStatUtil", "getStatList in else branch");
        }
    }

    private static void a(List<HiHealthData> list, HiHealthData hiHealthData, int i, long j, int i2) {
        if (i <= 0 || i2 <= 0) {
            return;
        }
        String str = Integer.toString(i) + "_" + j + "_" + i2;
        if (e.contains(str)) {
            return;
        }
        hiHealthData.setType(i);
        hiHealthData.setSequenceData(null);
        hiHealthData.setMetaData(null);
        list.add(hiHealthData);
        e.add(str);
    }

    private static void a(Context context, List<HiHealthData> list, HiHealthData hiHealthData, long j) {
        try {
            HashMap hashMap = (HashMap) new Gson().fromJson(hiHealthData.getFieldsValue(), new TypeToken<HashMap<Integer, Double>>() { // from class: iwg.5
            }.getType());
            if (hashMap == null) {
                LogUtil.a("HiH_HiStatUtil", "FieldsValue is null, do not need stat");
            } else {
                a(context, list, hiHealthData, j, new ArrayList(hashMap.keySet()));
            }
        } catch (JsonSyntaxException e2) {
            ReleaseLogUtil.d("HiH_HiStatUtil", "FromJson JsonSyntaxException ", e2.getMessage());
        }
    }

    private static void a(Context context, List<HiHealthData> list, HiHealthData hiHealthData, long j, List<Integer> list2) {
        Iterator<Integer> it = list2.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            HiHealthDictField b = HiHealthDictManager.d(context).b(intValue);
            if (b != null && b.d() != null) {
                HiHealthData hiHealthData2 = new HiHealthData();
                hiHealthData2.setStartTime(hiHealthData.getStartTime());
                hiHealthData2.setEndTime(hiHealthData.getEndTime());
                hiHealthData2.setSyncStatus(hiHealthData.getSyncStatus());
                hiHealthData2.setUserId(hiHealthData.getUserId());
                a(list, hiHealthData2, intValue, j, hiHealthData.getUserId());
            }
        }
    }

    public static void a(Context context, int i, int i2, boolean z) {
        if (context == null) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("sync_record", 0);
        if (i2 == 30023) {
            sharedPreferences.edit().putBoolean("statBasketballDataCompleteFlag" + i, z).apply();
            return;
        }
        sharedPreferences.edit().putBoolean("statCommonDataCompleteFlag" + i, z).apply();
    }

    public static boolean b(Context context, int i, int i2) {
        if (context == null) {
            return true;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("sync_record", 0);
        if (i2 == 30023) {
            return sharedPreferences.getBoolean("statBasketballDataCompleteFlag" + i, false);
        }
        return sharedPreferences.getBoolean("statCommonDataCompleteFlag" + i, false);
    }

    public static boolean d(int i, int i2, int i3, int[] iArr) {
        if (HiCommonUtil.e(iArr)) {
            ReleaseLogUtil.d("HiH_HiStatUtil", "statTypes is null empty!");
            return false;
        }
        ijd a2 = ivu.a(BaseApplication.getContext(), iArr[0]);
        List<igo> b = a2.b(i, i2, i3, iArr);
        if (HiCommonUtil.d(b)) {
            ReleaseLogUtil.d("HiH_HiStatUtil", "cleanAllStatDatas statLastDatas are null and oldStatTables are null");
            return false;
        }
        Iterator<igo> it = b.iterator();
        while (it.hasNext()) {
            a2.e(it.next().a(), 2);
        }
        ReleaseLogUtil.e("HiH_HiStatUtil", "Clean all stat data, one statType is ", Integer.valueOf(iArr[0]));
        return true;
    }

    public static boolean a(igo igoVar, int[] iArr) {
        if (igoVar == null) {
            ReleaseLogUtil.d("HiH_HiStatUtil", "statTable is null!");
            return false;
        }
        if (iArr.length == 0) {
            ReleaseLogUtil.d("HiH_HiStatUtil", "statTypes is empty!");
            return false;
        }
        List<igo> d = ijd.c(BaseApplication.getContext()).d(igoVar.e(), igoVar.d(), iArr);
        if (HiCommonUtil.d(d)) {
            ReleaseLogUtil.d("HiH_HiStatUtil", "cleanAllStatDatas statLastDatas are null and oldStatTables are null");
            return false;
        }
        Iterator<igo> it = d.iterator();
        while (it.hasNext()) {
            ijd.c(BaseApplication.getContext()).e(it.next().a(), 2);
        }
        ReleaseLogUtil.e("HiH_HiStatUtil", "Clean all stat data,  one statType is ", Integer.valueOf(iArr[0]), " day =", Integer.valueOf(igoVar.e()));
        return true;
    }
}
