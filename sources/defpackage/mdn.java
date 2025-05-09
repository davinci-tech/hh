package defpackage;

import android.text.TextUtils;
import com.huawei.hms.network.embedded.y5;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.EventRecord;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.ui.main.stories.nps.interactors.mode.TypeParams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mdn {
    private static final int[] e = {2, 4, 6, 8, 10, 15, 20, 25, 30, 35, 40, 60, 80, 100};
    private static final int[] c = {2000, 4000, y5.h, 8000, 10000, 12000, 14000, 16000, 18000, 20000};

    /* renamed from: a, reason: collision with root package name */
    private static final double[] f14897a = {6.16d, 10.55d, 16.96d, 19.51d, 16.89d, 12.12d, 7.61d, 4.44d, 2.51d, 1.4d, 1.84d};
    private static final double[] b = {28.67d, 16.7d, 12.92d, 7.95d, 5.4d, 9.98d, 5.59d, 3.95d, 2.42d, 1.77d, 1.2d, 2.32d, 0.75d, 0.3d, 0.08d};
    private static final double[] d = {28.32d, 15.48d, 10.46d, 6.68d, 4.83d, 8.38d, 5.3d, 3.75d, 2.79d, 2.19d, 1.74d, 4.54d, 2.47d, 1.51d, 1.56d};

    private static boolean a(int i, int i2) {
        return i == i2 + 1;
    }

    private static boolean c(int i) {
        return i == 6;
    }

    public static boolean e(String str) throws JSONException {
        return "0".equals(str);
    }

    public static String d(JSONArray jSONArray) throws JSONException {
        StringBuilder sb = new StringBuilder(16);
        if (jSONArray == null) {
            return sb.toString();
        }
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            Object opt = jSONArray.opt(i);
            if (opt instanceof JSONObject) {
                JSONObject jSONObject = (JSONObject) opt;
                String optString = jSONObject.optString(ParsedFieldTag.MEDAL_TYPE);
                sb.append(optString);
                if (d(optString)) {
                    sb.append("_");
                }
                sb.append(jSONObject.optInt(ParsedFieldTag.MEDAL_LEVEL));
            }
            if (!a(length, i)) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public static String b(JSONArray jSONArray) {
        StringBuilder sb = new StringBuilder(16);
        ArrayList arrayList = new ArrayList(16);
        if (jSONArray == null) {
            return sb.toString();
        }
        int length = jSONArray.length();
        boolean z = true;
        for (int i = 0; i < length; i++) {
            Object opt = jSONArray.opt(i);
            if (opt instanceof JSONObject) {
                String b2 = b(ParsedFieldTag.MEDAL_ID, (JSONObject) opt);
                if (arrayList.contains(b2)) {
                    z = false;
                } else {
                    arrayList.add(b2);
                    sb.append(b2);
                    z = true;
                }
            }
            if (z && !a(length, i)) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    private static boolean d(String str) {
        return (str == null || "A".equals(str) || "B".equals(str) || TypeParams.SEARCH_CODE.equals(str)) ? false : true;
    }

    public static String d(String str, String str2) {
        StringBuilder sb = new StringBuilder(16);
        sb.append(str);
        sb.append("_");
        sb.append(str2);
        return sb.toString();
    }

    private static boolean c(String str) {
        long j;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            j = Long.parseLong(str);
        } catch (NumberFormatException unused) {
            LogUtil.b("PLGACHIEVE_AchieveParserUtil", "judgeValidEvent Exception");
            j = 0;
        }
        return j != 0 && j <= System.currentTimeMillis();
    }

    private static String e() {
        HashMap hashMap = new HashMap(16);
        hashMap.put(MedalConstants.EVENT_OCCUR, "false");
        hashMap.put(MedalConstants.EVENT_CONTENT, "-1");
        hashMap.put("duration", "0");
        hashMap.put(MedalConstants.EVENT_STEPS, "0");
        hashMap.put("distance", "0");
        hashMap.put("calorie", "0");
        return new JSONObject(hashMap).toString();
    }

    public static EventRecord c(JSONObject jSONObject) {
        int e2 = e(ParsedFieldTag.ACTION_TYPE, jSONObject);
        String b2 = b("endTime", jSONObject);
        if (!c(e2) || !c(b2)) {
            return null;
        }
        EventRecord eventRecord = new EventRecord();
        eventRecord.saveEventType(1);
        eventRecord.saveEventStatus(MedalConstants.EVENT_NOT_UPLOAD);
        eventRecord.saveKey(MedalConstants.APP_OPENED);
        eventRecord.saveKeyType(MedalConstants.COMPLEX);
        eventRecord.saveValue(e());
        eventRecord.saveStartTime(b("startTime", jSONObject));
        eventRecord.saveEndTime(b2);
        return eventRecord;
    }

    public static String a(String str, String str2) {
        JSONArray jSONArray;
        int i = 0;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return String.valueOf(0);
        }
        try {
            jSONArray = new JSONArray(str);
        } catch (JSONException e2) {
            LogUtil.b("PLGACHIEVE_AchieveParserUtil", "getMaxValue JSONException:", e2.getMessage());
        }
        if (jSONArray.length() == 0) {
            return String.valueOf(0);
        }
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            int e3 = e(str2, jSONArray.getJSONObject(i2));
            if (e3 > i) {
                i = e3;
            }
        }
        return String.valueOf(i);
    }

    public static String b(String str, JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.has(str)) {
            try {
                return jSONObject.getString(str);
            } catch (JSONException unused) {
                LogUtil.b("PLGACHIEVE_AchieveParserUtil", "judgeValidKey Exception");
            }
        }
        return "";
    }

    public static long d(String str, JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.has(str)) {
            try {
                return jSONObject.getLong(str);
            } catch (JSONException unused) {
                LogUtil.b("PLGACHIEVE_AchieveParserUtil", "judgeValidKeyLong Exception");
            }
        }
        return 0L;
    }

    public static int e(String str, JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.has(str)) {
            try {
                return jSONObject.getInt(str);
            } catch (JSONException unused) {
                LogUtil.b("PLGACHIEVE_AchieveParserUtil", "judgeValidKeyInt Exception");
            }
        }
        return 0;
    }

    public static double a(String str, JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.has(str)) {
            try {
                return jSONObject.getDouble(str);
            } catch (JSONException unused) {
                LogUtil.b("PLGACHIEVE_AchieveParserUtil", "judgeValidKeyDouble Exception");
            }
        }
        return 0.0d;
    }

    public static Map<String, Object> e(String str, Map<String, Object> map) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (next instanceof String) {
                    String str2 = next;
                    Object obj = jSONObject.get(str2);
                    if (obj instanceof JSONObject) {
                        e(String.valueOf(new JSONObject(String.valueOf(obj))), map);
                    } else if (obj instanceof List) {
                        List list = (List) obj;
                        if (!koq.b(list)) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                e(String.valueOf(it.next()), map);
                            }
                        }
                    } else {
                        map.put(str2, obj);
                    }
                }
            }
        } catch (JSONException e2) {
            LogUtil.b("PLGACHIEVE_AchieveParserUtil", "parseJsonToMap", e2.getMessage());
        }
        return map;
    }

    public static HashMap<String, String> c(HashMap<String, String> hashMap, String str, String str2) {
        if (hashMap == null || str == null || str2 == null) {
            return new HashMap<>();
        }
        if (!hashMap.containsKey(str)) {
            hashMap.put(str, str2);
        } else {
            String str3 = hashMap.get(str);
            StringBuilder sb = new StringBuilder(16);
            sb.append(str3);
            sb.append(",");
            sb.append(str2);
            hashMap.put(str, sb.toString());
        }
        return hashMap;
    }

    private static int c(double d2) {
        for (int i = 0; i < 14; i++) {
            if (d2 < e[i]) {
                return i;
            }
        }
        return 14;
    }

    public static double d(String str, JSONObject jSONObject, double d2, int i) {
        if (jSONObject != null && jSONObject.has(str)) {
            try {
                return jSONObject.getDouble(str);
            } catch (JSONException unused) {
                LogUtil.b("PLGACHIEVE_AchieveParserUtil", "judgeValidKeyDouble Exception");
                return 0.0d;
            }
        }
        return e(d2, i);
    }

    public static double e(int i, int i2) {
        return d(d(i, i2), i, i2);
    }

    private static int d(int i, int i2) {
        for (int i3 = 0; i3 < 10; i3++) {
            if (i < c[i3] * i2) {
                return i3;
            }
        }
        return 10;
    }

    public static double e(double d2, int i) {
        int c2 = c(d2);
        if (i == 0) {
            return b(c2, d2, 0);
        }
        return b(c2, d2, 1);
    }

    private static double d(int i, int i2, int i3) {
        String b2;
        if (i > 9) {
            return 99.9d;
        }
        double d2 = 0.0d;
        for (int i4 = 0; i4 < i; i4++) {
            d2 += f14897a[i4];
        }
        double d3 = f14897a[i];
        if (i < 1) {
            b2 = jed.b((i2 / ((c[i] * i3) * 1.0f)) * d3, 1, 2);
        } else {
            int i5 = c[i - 1];
            b2 = jed.b(((i2 - (i5 * i3)) / (((r7[i] - i5) * i3) * 1.0f)) * d3, 1, 2);
        }
        try {
            return d2 + Double.parseDouble(b2);
        } catch (NumberFormatException unused) {
            LogUtil.b("PLGACHIEVE_AchieveParserUtil", "getPercentBySection Exception");
            return d2;
        }
    }

    private static double b(int i, double d2, int i2) {
        double d3;
        String b2;
        double d4;
        if (i > 13) {
            return 99.9d;
        }
        double d5 = 0.0d;
        for (int i3 = 0; i3 < i; i3++) {
            if (i2 == 0) {
                d4 = b[i3];
            } else {
                d4 = d[i3];
            }
            d5 += d4;
        }
        if (i2 == 0) {
            d3 = b[i];
        } else {
            d3 = d[i];
        }
        if (i < 1) {
            b2 = jed.b((d2 / e[i]) * d3, 1, 2);
        } else {
            b2 = jed.b(((d2 - e[i - 1]) / (r5[i] - r6)) * d3, 1, 2);
        }
        try {
            return d5 + Double.parseDouble(b2);
        } catch (NumberFormatException unused) {
            LogUtil.b("PLGACHIEVE_AchieveParserUtil", "getPercentBySection Exception");
            return d5;
        }
    }
}
