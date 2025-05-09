package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class gzv {
    public static final String e = mrv.d + "smart_coach_res" + File.separator;

    public static JSONObject e() {
        String e2 = mrx.e(new File(CommonUtil.c(e + "1.1.1" + File.separator + "json" + File.separator + "smart_coach_params.json")));
        if ("".equals(e2)) {
            e2 = e("local_smart_coach_params.json");
        }
        if (e2 != null) {
            try {
                return new JSONObject(e2);
            } catch (JSONException e3) {
                LogUtil.h("Track_CloudConfigParam", "getCloudConfigScriptJsonObject jsonException", LogAnonymous.b((Throwable) e3));
            }
        }
        return null;
    }

    public static String b(boolean z, String str, boolean z2) {
        String c;
        JSONObject e2 = e();
        if (e2 == null) {
            LogUtil.h("Track_CloudConfigParam", "getHeartRateRange jsonObject is null.");
            return "";
        }
        try {
            String c2 = c(e2.getString("data"), "heartRateRange");
            if (z) {
                String c3 = c(c2, "hasReserveHeartRate");
                if (z2) {
                    c = c(c(c3, str), "max");
                } else {
                    c = c(c(c3, str), "min");
                }
            } else {
                String c4 = c(c2, "noReserveHeartRate");
                if (z2) {
                    c = c(c(c4, str), "max");
                } else {
                    c = c(c(c4, str), "min");
                }
            }
            return c;
        } catch (JSONException e3) {
            LogUtil.h("Track_CloudConfigParam", "getHeartRateRange jsonException", LogAnonymous.b((Throwable) e3));
            return "";
        }
    }

    public static String c() {
        JSONObject e2 = e();
        if (e2 == null) {
            LogUtil.h("Track_CloudConfigParam", "getHeartRateRange jsonObject is null.");
            return "";
        }
        try {
            return c(c(c(e2.getString("data"), "heartRateRange"), "noReserveHeartRate"), "restHeartRate");
        } catch (JSONException e3) {
            LogUtil.h("Track_CloudConfigParam", "getRestHeartRate jsonException", LogAnonymous.b((Throwable) e3));
            return "";
        }
    }

    public static String e(boolean z, String str, String str2) {
        String c;
        JSONObject e2 = e();
        if (e2 == null) {
            LogUtil.h("Track_CloudConfigParam", "getBeforeSportTime jsonObject is null.");
            return "";
        }
        try {
            String c2 = c(c(e2.getString("data"), "durationAndPace"), "duration");
            if (z) {
                c = c(c(c2, "hasIntervals"), str);
            } else {
                c = c(c(c(c2, "noIntervals"), str2), str);
            }
            return c;
        } catch (JSONException e3) {
            LogUtil.h("Track_CloudConfigParam", "getBeforeSportTime jsonException", LogAnonymous.b((Throwable) e3));
            return "";
        }
    }

    public static String b() {
        JSONObject e2 = e();
        if (e2 == null) {
            LogUtil.h("Track_CloudConfigParam", "getMonthSportTimes jsonObject is null.");
            return "";
        }
        try {
            return c(c(e2.getString("data"), "durationAndPace"), "MonthSportTimes");
        } catch (JSONException e3) {
            LogUtil.h("Track_CloudConfigParam", "getMonthSportTimes jsonException", LogAnonymous.b((Throwable) e3));
            return "";
        }
    }

    public static String d(String str, int i, String str2, boolean z, boolean z2) {
        String c;
        JSONObject e2 = e();
        if (e2 == null) {
            LogUtil.h("Track_CloudConfigParam", "getPaceValue jsonObject is null.");
            return "";
        }
        try {
            String c2 = c(c(c(c(c(e2.getString("data"), "durationAndPace"), "pace"), str), c(i)), str2);
            if ("recovery".equals(str2)) {
                if (z2) {
                    c = c(c2, "max");
                } else {
                    c = c(c2, "min");
                }
            } else if (z) {
                c = z2 ? "-1" : c(c(c2, "hasHistory"), "min");
            } else if (z2) {
                c = c(c(c2, "noHistory"), "max");
            } else {
                c = c(c(c2, "noHistory"), "min");
            }
            return c;
        } catch (JSONException e3) {
            LogUtil.h("Track_CloudConfigParam", "getPaceValue jsonException", LogAnonymous.b((Throwable) e3));
            return "";
        }
    }

    public static String a(String str, int i, String str2, boolean z) {
        String c;
        JSONObject e2 = e();
        if (e2 == null) {
            LogUtil.h("Track_CloudConfigParam", "getSpeedValue jsonObject is null.");
            return "";
        }
        String c2 = c(i);
        try {
            String c3 = c(c(c(e2.getString("data"), "durationAndPace"), "speedForTreadmill"), str);
            if (z) {
                c = c(c(c(c3, c2), str2), "max");
            } else {
                c = c(c(c(c3, c2), str2), "min");
            }
            return c;
        } catch (JSONException e3) {
            LogUtil.h("Track_CloudConfigParam", "getSpeedValue jsonException", LogAnonymous.b((Throwable) e3));
            return "";
        }
    }

    private static String c(int i) {
        if (i < 18 || i > 29) {
            if (i >= 30 && i <= 39) {
                return "age30s";
            }
            if (i >= 40 && i <= 49) {
                return "age40s";
            }
            if (i >= 50 && i <= 59) {
                return "age50s";
            }
            LogUtil.h("Track_CloudConfigParam", "getPaceValue age is over section.");
        }
        return "age20s";
    }

    private static String c(String str, String str2) {
        if (str != null && !str.isEmpty()) {
            if (str2 != null && str.charAt(0) == '[') {
                return b(str, str2);
            }
            try {
                return new JSONObject(str).getString(str2);
            } catch (JSONException e2) {
                LogUtil.h("Track_CloudConfigParam", "getNextNodeString jsonException", LogAnonymous.b((Throwable) e2));
            }
        }
        return "";
    }

    private static String b(String str, String str2) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (!jSONObject.isNull(str2)) {
                    return jSONObject.getString(str2);
                }
            }
            return "";
        } catch (JSONException e2) {
            LogUtil.h("Track_CloudConfigParam", "parseJsonArrayUrl jsonException", LogAnonymous.b((Throwable) e2));
            return null;
        }
    }

    private static String e(String str) {
        if (str != null && !str.isEmpty()) {
            try {
                return a(BaseApplication.getContext().getAssets().open(str));
            } catch (IOException e2) {
                LogUtil.h("Track_CloudConfigParam", "parseAssestsJsonFile ioException", LogAnonymous.b((Throwable) e2));
            }
        }
        return "";
    }

    public static String a(InputStream inputStream) {
        String str;
        str = "";
        if (inputStream == null) {
            return "";
        }
        try {
            try {
                byte[] bArr = new byte[inputStream.available()];
                str = inputStream.read(bArr) > 0 ? new String(bArr, "UTF-8") : "";
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    LogUtil.h("Track_CloudConfigParam", "getString ioException", LogAnonymous.b((Throwable) e2));
                }
            } catch (IOException unused) {
                LogUtil.b("Track_CloudConfigParam", "getString IOException");
            }
            return str;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e3) {
                LogUtil.h("Track_CloudConfigParam", "getString ioException", LogAnonymous.b((Throwable) e3));
            }
        }
    }
}
