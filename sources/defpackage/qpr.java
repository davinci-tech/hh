package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Pair;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.temperature.view.levelcard.TemperatureCardView;
import health.compact.a.UnitUtil;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes7.dex */
public class qpr {
    public static final int d = qpk.d().j();
    public static final int b = qpk.d().k();
    public static final int c = qpk.d().s();
    private static String e = "";

    public static float d(float f, float f2, float f3, float f4) {
        if (f == 0.0f && f2 == 0.0f && f3 > 0.0f && f4 > 0.0f) {
            return f3 + 0.8f;
        }
        if (f > 0.0f && f2 > 0.0f && f3 == 0.0f && f4 == 0.0f) {
            return f;
        }
        if (f <= 0.0f || f2 <= 0.0f || f3 <= 0.0f || f4 <= 0.0f) {
            return 0.0f;
        }
        return f3 > f ? f3 + 0.8f : f;
    }

    public static boolean i(float f) {
        return f > 42.0f || f < 34.0f;
    }

    public static long e(long j) {
        return jdl.h(j, 30);
    }

    public static long b(String str) {
        try {
            Date parse = new SimpleDateFormat("HH:mm", Locale.getDefault()).parse(str);
            if (parse == null) {
                LogUtil.h("TemperatureUtils", "getTimestampByString date is null ", str);
                return System.currentTimeMillis();
            }
            return parse.getTime();
        } catch (ParseException unused) {
            LogUtil.h("TemperatureUtils", "getTimestampByString ParseException ", str);
            return System.currentTimeMillis();
        }
    }

    public static String c(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.set(14, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        long timeInMillis = calendar.getTimeInMillis();
        calendar.add(11, 1);
        return DateUtils.formatDateRange(BaseApplication.getContext(), timeInMillis, calendar.getTimeInMillis(), 129);
    }

    public static int e(float f) {
        LogUtil.c("TemperatureUtils", "getProgressOfTemperature, value = ", Float.valueOf(f));
        int i = f < 34.0f ? 0 : f < 37.2f ? (int) (((f - 34.0f) * 68.0f) / 3.2000008f) : f == 37.2f ? 68 : f <= 38.0f ? ((int) (((f - 37.2f) * 34.0f) / 0.79999924f)) + 68 : ((int) (((f - 38.0f) * 34.0f) / 4.0f)) + 102;
        LogUtil.c("TemperatureUtils", "getProgressOfTemperature, progress = ", Integer.valueOf(i));
        return i + 3;
    }

    public static void a(List<HiHealthData> list, TemperatureCardView temperatureCardView) {
        if (temperatureCardView == null) {
            LogUtil.h("TemperatureUtils", "notifyLevelCardView mCardView is null");
            return;
        }
        HashMap hashMap = new HashMap();
        if (koq.b(list)) {
            LogUtil.h("TemperatureUtils", "notifyLevelCardView data list is isEmpty");
            hashMap.put("TEMPERATURE_MIN_MAX", "--");
            hashMap.put("SKIN_TEMPERATURE_MIN_MAX", "--");
            temperatureCardView.d(hashMap);
            return;
        }
        HiHealthData hiHealthData = list.get(0);
        String b2 = b(hiHealthData.getFloat("body_min_key"));
        String b3 = b(hiHealthData.getFloat("body_max_key"));
        String b4 = b(hiHealthData.getFloat("skin_min_key"));
        String b5 = b(hiHealthData.getFloat("skin_max_key"));
        String str = b2 + Constants.LINK + b3;
        if (str.equals("0.0-0.0")) {
            str = "--";
        }
        String str2 = b4 + Constants.LINK + b5;
        String str3 = str2.equals("0.0-0.0") ? "--" : str2;
        LogUtil.c("TemperatureUtils", "bodyMinMax ", str, "skinMinMax ", str3);
        hashMap.put("TEMPERATURE_MIN_MAX", str);
        hashMap.put("SKIN_TEMPERATURE_MIN_MAX", str3);
        temperatureCardView.d(hashMap);
    }

    public static String c() {
        if (UnitUtil.d()) {
            return nsf.b(R$string.IDS_settings_health_temperature_unit, "");
        }
        return nsf.b(R$string.IDS_temp_unit_fahrenheit, "");
    }

    public static String b(float f) {
        return c(f, UnitUtil.d());
    }

    public static String c(float f, boolean z) {
        if (f == 0.0f) {
            return String.valueOf(f);
        }
        if (z) {
            return UnitUtil.e(f, 1, 1);
        }
        return UnitUtil.e((f * 1.8f) + 32.0f, 1, 1);
    }

    public static float d(float f) {
        return UnitUtil.d() ? f : c(f);
    }

    public static float c(float f) {
        return new BigDecimal((f * 1.8f) + 32.0f).setScale(1, 4).floatValue();
    }

    public static float a(float f) {
        return new BigDecimal((f - 32.0f) / 1.8f).setScale(1, 4).floatValue();
    }

    public static float e(float f, float f2, boolean z) {
        float d2 = d(z ? 34.0f : 20.0f);
        float d3 = d(42.0f);
        if (f2 <= f || f > d3) {
            return d2;
        }
        float floor = (int) Math.floor(f);
        if (floor % 2.0f != 0.0f) {
            floor -= 1.0f;
        }
        if (f - floor < 0.5f) {
            floor -= 2.0f;
        }
        float f3 = d2 - 2.0f;
        return floor < f3 ? f3 : floor;
    }

    public static float a(float f, float f2, boolean z) {
        float d2 = d(z ? 34.0f : 20.0f);
        float d3 = d(42.0f);
        if (f2 <= f || f2 < d2) {
            return d3;
        }
        float ceil = (int) Math.ceil(f2);
        if (ceil % 2.0f != 0.0f) {
            ceil += 1.0f;
        }
        return ceil > d3 ? d3 : ceil;
    }

    public static float a(boolean z) {
        return d(42.0f);
    }

    public static float b(boolean z) {
        return d(z ? 34.0f : 20.0f);
    }

    public static List<qon> b(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            return arrayList;
        }
        HashMap hashMap = new HashMap();
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                long f = jec.f(hiHealthData.getStartTime());
                if (!hashMap.containsKey(Long.valueOf(f))) {
                    hashMap.put(Long.valueOf(f), new ArrayList());
                }
                ((List) hashMap.get(Long.valueOf(f))).add(hiHealthData);
            }
        }
        boolean d2 = UnitUtil.d();
        for (Map.Entry entry : hashMap.entrySet()) {
            if (entry != null) {
                qon qonVar = new qon();
                qonVar.a(((Long) entry.getKey()).longValue());
                List<qoi> c2 = c((List<HiHealthData>) entry.getValue(), d2);
                Collections.sort(c2, new Comparator() { // from class: qpp
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        int compare;
                        compare = Long.compare(((qoi) obj2).b(), ((qoi) obj).b());
                        return compare;
                    }
                });
                qonVar.setChildList(c2);
                arrayList.add(qonVar);
            }
        }
        Collections.sort(arrayList, new Comparator() { // from class: qpu
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Long.compare(((qon) obj2).e(), ((qon) obj).e());
                return compare;
            }
        });
        return arrayList;
    }

    public static List<qoi> a(List<HiHealthData> list) {
        return c(list, UnitUtil.d());
    }

    public static List<qoi> c(List<HiHealthData> list, boolean z) {
        String d2;
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            LogUtil.h("TemperatureUtils", "convert list is empty");
            return arrayList;
        }
        Context context = BaseApplication.getContext();
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                qoi qoiVar = new qoi(hiHealthData.getStartTime(), hiHealthData.getEndTime());
                qoiVar.c(hiHealthData.getType());
                long endTime = hiHealthData.getEndTime() - 600000;
                long endTime2 = hiHealthData.getEndTime();
                qoiVar.c(DateUtils.formatDateRange(context, endTime, endTime2, 137));
                qoiVar.d(DateUtils.formatDateRange(context, endTime, endTime2, 131225));
                if (hiHealthData.getType() == b) {
                    d2 = a(z, 37.2f);
                } else {
                    d2 = d(hiHealthData.getMetaData(), z);
                }
                if (!d2.isEmpty()) {
                    qoiVar.a(d2);
                    arrayList.add(qoiVar);
                }
            }
        }
        return arrayList;
    }

    public static String a(boolean z, float f) {
        String string;
        if (z) {
            string = BaseApplication.getContext().getString(R$string.IDS_hw_health_blood_oxygen_measure_interval_greater_than, UnitUtil.e(f, 1, 1));
        } else {
            string = BaseApplication.getContext().getString(R$string.IDS_hw_health_blood_oxygen_measure_interval_greater_than, UnitUtil.e(c(f), 1, 1));
        }
        return BaseApplication.getContext().getString(R$string.IDS_temp_desc_suspect, string);
    }

    private static String d(String str, boolean z) {
        String string;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("TemperatureUtils", "getCelsiusRange metaData is empty");
            return "";
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            float f = 0.0f;
            float f2 = 0.0f;
            for (int i = 0; i < jSONArray.length(); i++) {
                Object obj = jSONArray.get(i);
                if (obj instanceof Integer) {
                    int intValue = ((Integer) obj).intValue();
                    if (i == 0) {
                        f = intValue;
                    }
                    float f3 = intValue;
                    f = Math.min(f, f3);
                    f2 = Math.max(f2, f3);
                } else {
                    LogUtil.h("TemperatureUtils", "convert no Integer");
                }
            }
            if (f == 0.0f || f2 == 0.0f) {
                LogUtil.h("TemperatureUtils", "max or min error");
                return "";
            }
            String c2 = c(f / 10.0f, z);
            String c3 = c(f2 / 10.0f, z);
            if (z) {
                string = BaseApplication.getContext().getString(R$string.IDS_temperature_warning_celsius);
            } else {
                string = BaseApplication.getContext().getString(R$string.IDS_temperature_warning_fahrenheit);
            }
            return String.format(Locale.ENGLISH, string, c2, c3);
        } catch (JSONException unused) {
            LogUtil.b("TemperatureUtils", "convert JSONException");
            return "";
        }
    }

    public static boolean b(float f, String str) {
        return "TEMPERATURE_MIN_MAX".equals(str) ? f > 42.0f || f < 34.0f : f > 42.0f || f < 20.0f;
    }

    public static Pair<String, String> dHg_(float f, float f2, float f3, float f4) {
        if (f == 0.0f && f2 == 0.0f && f3 > 0.0f && f4 > 0.0f) {
            if (f3 != f4) {
                return dHe_(f3, f4);
            }
            return dHf_(f4);
        }
        if (f > 0.0f && f2 > 0.0f && f3 == 0.0f && f4 == 0.0f) {
            return dHe_(f, f2);
        }
        if (f > 0.0f && f2 > 0.0f && f3 > 0.0f && f4 > 0.0f) {
            return dHe_(Math.max(f, f3), Math.min(f2, f4));
        }
        return new Pair<>("--", "");
    }

    private static Pair<String, String> dHe_(float f, float f2) {
        String string;
        String string2 = BaseApplication.getContext().getString(R$string.IDS_hw_health_coresleep_standard_range_1, UnitUtil.e(f2, 1, 1), UnitUtil.e(f, 1, 1));
        if (UnitUtil.d()) {
            string = BaseApplication.getContext().getString(com.huawei.health.servicesui.R$string.IDS_temperature_celsius_unit, string2);
        } else {
            string = BaseApplication.getContext().getString(com.huawei.health.servicesui.R$string.IDS_temperature_fahrenheit_unit, string2);
        }
        return new Pair<>(string, string2);
    }

    private static Pair<String, String> dHf_(float f) {
        String string;
        String e2 = UnitUtil.e(f, 1, 1);
        if (UnitUtil.d()) {
            string = BaseApplication.getContext().getString(com.huawei.health.servicesui.R$string.IDS_temp_over_suspected_celsius_unit, e2);
        } else {
            string = BaseApplication.getContext().getString(com.huawei.health.servicesui.R$string.IDS_temp_over_suspected_fahrenheit_unit, e2);
        }
        return new Pair<>(string, e2);
    }

    public static float c(float f, float f2, float f3, float f4) {
        if (f == 0.0f && f2 == 0.0f && f3 > 0.0f && f4 > 0.0f) {
            return f4;
        }
        if (f > 0.0f && f2 > 0.0f && f3 == 0.0f && f4 == 0.0f) {
            return f2;
        }
        if (f <= 0.0f || f2 <= 0.0f || f3 <= 0.0f || f4 <= 0.0f) {
            return 0.0f;
        }
        return Math.min(f2, f4);
    }

    public static void d(List<HiHealthData> list, List<HiHealthData> list2) {
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        ArrayList arrayList3 = new ArrayList(16);
        if (koq.b(list)) {
            LogUtil.h("TemperatureUtils", "getCardMaxMin list is null");
            return;
        }
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            if (it.next() != null) {
                float floatValue = new BigDecimal(r4.getFloat("body_min_key")).setScale(1, 4).floatValue();
                if (!b(floatValue, "TEMPERATURE_MIN_MAX")) {
                    arrayList.add(Float.valueOf(floatValue));
                }
                float floatValue2 = new BigDecimal(r4.getFloat("body_max_key")).setScale(1, 4).floatValue();
                if (!b(floatValue2, "TEMPERATURE_MIN_MAX")) {
                    arrayList.add(Float.valueOf(floatValue2));
                }
                float floatValue3 = new BigDecimal(r4.getFloat("skin_min_key")).setScale(1, 4).floatValue();
                if (!b(floatValue3, "SKIN_TEMPERATURE_MIN_MAX")) {
                    arrayList2.add(Float.valueOf(floatValue3));
                }
                float floatValue4 = new BigDecimal(r4.getFloat("skin_max_key")).setScale(1, 4).floatValue();
                if (!b(floatValue4, "SKIN_TEMPERATURE_MIN_MAX")) {
                    arrayList2.add(Float.valueOf(floatValue4));
                }
                float floatValue5 = new BigDecimal(r4.getFloat("suspected_temperature_high_min_key")).setScale(1, 4).floatValue();
                if (!i(floatValue5)) {
                    arrayList3.add(Float.valueOf(floatValue5));
                }
                float floatValue6 = new BigDecimal(r4.getFloat("suspected_temperature_high_max_key")).setScale(1, 4).floatValue();
                if (!i(floatValue6)) {
                    arrayList3.add(Float.valueOf(floatValue6));
                }
            }
        }
        HiHealthData hiHealthData = new HiHealthData();
        if (koq.c(arrayList)) {
            hiHealthData.putFloat("body_max_key", ((Float) Collections.max(arrayList)).floatValue());
            hiHealthData.putFloat("body_min_key", ((Float) Collections.min(arrayList)).floatValue());
        }
        if (koq.c(arrayList2)) {
            hiHealthData.putFloat("skin_max_key", ((Float) Collections.max(arrayList2)).floatValue());
            hiHealthData.putFloat("skin_min_key", ((Float) Collections.min(arrayList2)).floatValue());
        }
        if (koq.c(arrayList3)) {
            hiHealthData.putFloat("suspected_temperature_high_max_key", ((Float) Collections.max(arrayList3)).floatValue());
            hiHealthData.putFloat("suspected_temperature_high_min_key", ((Float) Collections.min(arrayList3)).floatValue());
        }
        list2.add(hiHealthData);
    }

    public static String d() {
        return e;
    }

    public static void a(String str) {
        e = str;
    }

    public static void e(qoi qoiVar, RecyclerHolder recyclerHolder) {
        a(qoiVar, recyclerHolder, UnitUtil.d());
    }

    public static void a(qoi qoiVar, RecyclerHolder recyclerHolder, boolean z) {
        String string;
        if (recyclerHolder == null || qoiVar == null) {
            LogUtil.h("TemperatureUtils", "setTextProperties holder or itemData is null");
            return;
        }
        Context context = BaseApplication.getContext();
        if (z) {
            string = context.getResources().getString(R$string.IDS_settings_health_temperature_unit, qoiVar.i());
        } else {
            string = context.getResources().getString(R$string.IDS_temp_unit_fahrenheit, qoiVar.i());
        }
        recyclerHolder.b(R.id.temperature_list_data, string);
        if (qoiVar.d() == d) {
            recyclerHolder.c(R.id.temperature_list_status, ContextCompat.getColor(context, R.color._2131299223_res_0x7f090b97));
            recyclerHolder.b(R.id.temperature_list_status, context.getString(R$string.IDS_temperature_warning_low));
        } else {
            recyclerHolder.c(R.id.temperature_list_status, ContextCompat.getColor(context, R.color._2131299222_res_0x7f090b96));
            recyclerHolder.b(R.id.temperature_list_status, context.getString(R$string.IDS_temperature_warning_high));
        }
    }
}
