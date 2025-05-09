package defpackage;

import android.util.Log;
import com.huawei.hms.ads.dynamic.a;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class slv {
    public static final String[] j = {HiAnalyticsConstant.KeyAndValue.NUMBER_01, a.t, "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    public static final String[] b = {HiAnalyticsConstant.KeyAndValue.NUMBER_01, a.t, "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", HealthZonePushReceiver.DEAUTH_EVENT_NOTIFY, "21", "22", "23", "24", "25", "26", HealthZonePushReceiver.BLOOD_SUGAR_DAWN_NOTIFY, "28", HealthZonePushReceiver.SLEEP_TIME_NOTIFY, HealthZonePushReceiver.SLEEP_SCORE_NOTIFY, "31"};
    public static final String[] c = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};

    /* renamed from: a, reason: collision with root package name */
    public static final String[] f17114a = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
    public static final String[] h = {"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};
    public static final String[] d = {"正月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "冬月", "腊月"};
    public static final String[] e = {"初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十", "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十", "廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九", "三十"};

    protected slv() {
    }

    public static void a(BufferedReader bufferedReader) {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException unused) {
                Log.e("HwAbsLunarUtils", "br close fail");
            }
        }
    }

    public static String b(String str) {
        if (str == null || str.indexOf("_") == -1) {
            return "";
        }
        String[] split = str.split("_");
        if (split.length < 2) {
            return "";
        }
        return split[0] + split[1];
    }

    public static void c(int i, String[] strArr, String[] strArr2, List<String> list, List<String> list2) {
        if (strArr == null || strArr2 == null) {
            Log.e("HwAbsLunarUtils", "getTwoMaxList: currentStrings or currentNumberStrings is null!");
            return;
        }
        if (strArr.length > strArr2.length) {
            Log.e("HwAbsLunarUtils", "getTwoMaxList: currentStrings's length is greater than to currentNumberStrings's length!");
            return;
        }
        if (list == null || list2 == null) {
            Log.e("HwAbsLunarUtils", "getTwoMaxList: displayList or displayNumberList is null!");
            return;
        }
        while (i < strArr.length) {
            list.add(strArr[i]);
            list2.add(strArr2[i]);
            i++;
        }
        if (list.size() < 3) {
            int i2 = 0;
            for (int size = 3 - list.size(); size > 0 && i2 < strArr.length && i2 < strArr2.length; size--) {
                list.add(strArr[i2]);
                list2.add(strArr2[i2]);
                i2++;
            }
        }
    }

    public static String[] c(List<String> list) {
        if (list == null) {
            Log.e("HwAbsLunarUtils", "listChangeGroup | but list is null !");
            return new String[0];
        }
        int size = list.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = list.get(i);
        }
        return strArr;
    }

    public static int d(String str, String[] strArr) {
        if (strArr == null || str == null) {
            return 0;
        }
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (strArr[i].equals(str)) {
                return i;
            }
        }
        return 0;
    }

    public static void e(int i, String[] strArr, String[] strArr2, List<String> list, List<String> list2) {
        if (strArr == null || strArr2 == null) {
            Log.e("HwAbsLunarUtils", "getTwoMaxList: currentStrings or currentNumberStrings is null!");
            return;
        }
        if (strArr.length > strArr2.length) {
            Log.e("HwAbsLunarUtils", "getTwoMaxList: currentStrings's length is greater than currentNumberStrings's length!");
            return;
        }
        if (list == null || list2 == null) {
            Log.e("HwAbsLunarUtils", "getTwoMaxList: displayList or displayNumberList is null!");
            return;
        }
        int i2 = i + 1;
        if (i2 < 3) {
            int length = strArr.length - 1;
            for (int i3 = 3 - i2; i3 > 0 && length >= 0; i3--) {
                list.add(strArr[length]);
                list2.add(strArr2[length]);
                length--;
            }
        }
        for (int i4 = 0; i4 <= i; i4++) {
            list.add(strArr[i4]);
            list2.add(strArr2[i4]);
        }
    }

    public static void e(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
                Log.e("HwAbsLunarUtils", "io close fail");
            }
        }
    }

    public static String[] a(int i, String[] strArr) {
        if (strArr == null) {
            return new String[0];
        }
        ArrayList arrayList = new ArrayList(10);
        while (i < strArr.length) {
            arrayList.add(strArr[i]);
            i++;
        }
        if (arrayList.size() < 3) {
            int i2 = 0;
            for (int size = 3 - arrayList.size(); size > 0; size--) {
                if (i2 < strArr.length) {
                    arrayList.add(strArr[i2]);
                    i2++;
                }
            }
        }
        String[] strArr2 = new String[arrayList.size()];
        int size2 = arrayList.size();
        for (int i3 = 0; i3 < size2; i3++) {
            strArr2[i3] = (String) arrayList.get(i3);
        }
        return strArr2;
    }

    public static String[] e(int i, String[] strArr) {
        if (strArr == null) {
            return new String[0];
        }
        ArrayList arrayList = new ArrayList(10);
        int i2 = i + 1;
        if (i2 < 3) {
            int length = strArr.length - 1;
            for (int i3 = 3 - i2; i3 > 0 && length >= 0; i3--) {
                arrayList.add(strArr[length]);
                length--;
            }
        }
        for (int i4 = 0; i4 <= i; i4++) {
            arrayList.add(strArr[i4]);
        }
        String[] strArr2 = new String[arrayList.size()];
        int size = arrayList.size();
        for (int i5 = 0; i5 < size; i5++) {
            strArr2[i5] = (String) arrayList.get(i5);
        }
        return strArr2;
    }
}
