package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import com.alipay.sdk.m.p.e;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginachievement.manager.model.SingleDayRecord;
import com.huawei.pluginachievement.ui.model.BestAchievementBasic;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mlg {
    public static boolean a(int i) {
        return i == 3 || i == 4 || i == 2;
    }

    public static int d(double d, double d2) {
        double d3 = d - d2;
        if (d3 <= -1.0d) {
            return -1;
        }
        return d3 >= 1.0d ? 1 : 0;
    }

    public static int e(double d, double d2) {
        double d3 = d - d2;
        if (d3 < -0.001d) {
            return -1;
        }
        return d3 > 0.001d ? 1 : 0;
    }

    public static boolean e(int i) {
        return i == 11;
    }

    public static boolean g(int i) {
        return i == 1 || i == 3 || i == 10;
    }

    public static boolean h(int i) {
        return i == 12 || i == 13 || i == 14 || i == 15;
    }

    public static boolean i(int i) {
        return i == 2;
    }

    public static boolean j(int i) {
        return i == 16;
    }

    public static boolean m(int i) {
        return i < 4 || i > 9;
    }

    public static boolean b(String[] strArr, String str) {
        return (strArr == null || str == null || !Arrays.asList(strArr).contains(str)) ? false : true;
    }

    public static void e(Context context) {
        LogUtil.a("PLGACHIEVE_ReportUtil", "showNetworkErrorDialog()");
        if (context == null) {
            LogUtil.a("PLGACHIEVE_ReportUtil", "showNetworkErrorDialog() context is null");
            return;
        }
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(context);
        builder.b(R.string._2130840731_res_0x7f020c9b).d(R.string._2130840733_res_0x7f020c9d).cyU_(R.string._2130840732_res_0x7f020c9c, new View.OnClickListener() { // from class: mlg.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(true);
        a2.show();
    }

    public static boolean d() {
        return MLAsrConstants.LAN_ZH.equalsIgnoreCase(BaseApplication.getContext().getResources().getConfiguration().locale.getLanguage()) && "CN".equalsIgnoreCase(BaseApplication.getContext().getResources().getConfiguration().locale.getCountry());
    }

    public static mka c(String str) {
        JSONException e;
        LogUtil.a("PLGACHIEVE_ReportUtil", "Enter parseBestMotion");
        mka mkaVar = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() == 0) {
                return null;
            }
            mka mkaVar2 = new mka();
            long j = 0;
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    long d = mdn.d("value", jSONObject);
                    long d2 = mdn.d("startTime", jSONObject);
                    int e2 = mdn.e("source", jSONObject);
                    if (d > j) {
                        mkaVar2.b(d);
                        mkaVar2.d(d2);
                        mkaVar2.d(e2);
                        j = d;
                    }
                } catch (JSONException e3) {
                    e = e3;
                    mkaVar = mkaVar2;
                    LogUtil.b("PLGACHIEVE_ReportUtil", "getStartTimeAndEndTime Exception:", e.getMessage());
                    return mkaVar;
                }
            }
            return mkaVar2;
        } catch (JSONException e4) {
            e = e4;
        }
    }

    public static mkc d(String str) {
        JSONException e;
        LogUtil.a("PLGACHIEVE_ReportUtil", "parseBestMotionPace bestMotionPace.");
        mkc mkcVar = null;
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("PLGACHIEVE_ReportUtil", "parseBestMotionPace reportData is null");
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() == 0) {
                return null;
            }
            mkc mkcVar2 = new mkc();
            double d = 0.0d;
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    double a2 = mdn.a("value", jSONObject);
                    long d2 = mdn.d("startTime", jSONObject);
                    if (d == 0.0d) {
                        mkcVar2.a(a2);
                        mkcVar2.a(d2);
                        d = a2;
                    }
                    if (e(a2, d) < 0) {
                        int e2 = mdn.e("source", jSONObject);
                        mkcVar2.a(a2);
                        mkcVar2.a(d2);
                        mkcVar2.a(e2);
                        d = a2;
                    }
                } catch (JSONException e3) {
                    e = e3;
                    mkcVar = mkcVar2;
                    LogUtil.b("PLGACHIEVE_ReportUtil", "getStartTimeAndEndTime Exception:", e.getMessage());
                    return mkcVar;
                }
            }
            return mkcVar2;
        } catch (JSONException e4) {
            e = e4;
        }
    }

    public static ArrayList<mkc> e(String str, int i) {
        ArrayList<mkc> arrayList = new ArrayList<>(5);
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("PLGACHIEVE_ReportUtil", "parseBestMotionPace reportData is null");
            return arrayList;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() != 0) {
                int length = jSONArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    mkc mkcVar = new mkc();
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    double a2 = mdn.a("value", jSONObject);
                    long d = mdn.d("startTime", jSONObject);
                    long d2 = mdn.d("endTime", jSONObject);
                    int e = mdn.e("source", jSONObject);
                    mkcVar.a(a2);
                    mkcVar.a(d);
                    mkcVar.b(d2);
                    mkcVar.a(e);
                    arrayList.add(mkcVar);
                }
                c(arrayList);
                return arrayList.size() > i ? e(arrayList, i) : arrayList;
            }
        } catch (JSONException e2) {
            LogUtil.b("PLGACHIEVE_ReportUtil", "getStartTimeAndEndTime Exception ", e2.getMessage());
        }
        return arrayList;
    }

    public static ArrayList<mka> d(String str, int i) {
        ArrayList<mka> arrayList = new ArrayList<>(5);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("PLGACHIEVE_ReportUtil", "parseMaxNMotion reportData is null");
            return arrayList;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() != 0) {
                int length = jSONArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    mka mkaVar = new mka();
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    long d = mdn.d("value", jSONObject);
                    long d2 = mdn.d("startTime", jSONObject);
                    long d3 = mdn.d("endTime", jSONObject);
                    int e = mdn.e("source", jSONObject);
                    mkaVar.b(d);
                    mkaVar.d(d2);
                    mkaVar.a(d3);
                    mkaVar.d(e);
                    arrayList.add(mkaVar);
                }
                a(arrayList);
                return arrayList.size() > i ? e(arrayList, i) : arrayList;
            }
        } catch (JSONException e2) {
            LogUtil.b("PLGACHIEVE_ReportUtil", "getStartTimeAndEndTime Exception:", e2.getMessage());
        }
        return arrayList;
    }

    private static ArrayList e(ArrayList arrayList, int i) {
        LogUtil.a("PLGACHIEVE_ReportUtil", "getMaxList N:", Integer.valueOf(i));
        ArrayList arrayList2 = new ArrayList(5);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList2.add(arrayList.get(i2));
        }
        return arrayList2;
    }

    public static void a(ArrayList arrayList) {
        if (arrayList == null) {
            return;
        }
        Collections.sort(arrayList, new Comparator<mka>() { // from class: mlg.4
            @Override // java.util.Comparator
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public int compare(mka mkaVar, mka mkaVar2) {
                if (mkaVar.b() - mkaVar2.b() < 0) {
                    return 1;
                }
                return mkaVar.b() - mkaVar2.b() == 0 ? 0 : -1;
            }
        });
    }

    public static void c(ArrayList arrayList) {
        if (arrayList == null) {
            return;
        }
        Collections.sort(arrayList, new Comparator<mkc>() { // from class: mlg.3
            @Override // java.util.Comparator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public int compare(mkc mkcVar, mkc mkcVar2) {
                return mlg.e(mkcVar.b(), mkcVar2.b());
            }
        });
    }

    public static mke h(String str) {
        JSONException e;
        mke mkeVar = null;
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("PLGACHIEVE_ReportUtil", "parseBestStep reportData is null");
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() == 0) {
                return null;
            }
            mke mkeVar2 = new mke();
            int i = 0;
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    int e2 = mdn.e("value", jSONObject);
                    int e3 = mdn.e(ParsedFieldTag.RECORD_DAY, jSONObject);
                    if (e2 > i) {
                        mkeVar2.e(e2);
                        mkeVar2.d(e3);
                        i = e2;
                    }
                } catch (JSONException e4) {
                    e = e4;
                    mkeVar = mkeVar2;
                    LogUtil.b("PLGACHIEVE_ReportUtil", "getStartTimeAndEndTime Exception:", e.getMessage());
                    return mkeVar;
                }
            }
            return mkeVar2;
        } catch (JSONException e5) {
            e = e5;
        }
    }

    public static String d(String str, BestAchievementBasic bestAchievementBasic) {
        if (TextUtils.isEmpty(str) || bestAchievementBasic == null) {
            LogUtil.a("PLGACHIEVE_ReportUtil", "getRefreshJsonStr reportData/bestAchievementBasic is null");
            return "";
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            int length = jSONArray.length();
            if (bestAchievementBasic instanceof mke) {
                jSONArray.put(length, a((mke) bestAchievementBasic));
            } else if (bestAchievementBasic instanceof mka) {
                jSONArray.put(length, a((mka) bestAchievementBasic));
            } else if (bestAchievementBasic instanceof mkc) {
                jSONArray.put(length, a((mkc) bestAchievementBasic));
            } else {
                LogUtil.h("PLGACHIEVE_ReportUtil", "bestAchievementBasic instance type error");
            }
            return jSONArray.toString();
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_ReportUtil", "getRefreshJsonStr Exception:", e.getMessage());
            return "";
        }
    }

    public static String d(String str, String str2, int i) {
        if (TextUtils.isEmpty(str2) || "[]".equals(str2)) {
            LogUtil.h("PLGACHIEVE_ReportUtil", "getMergedJsonStr: newData empty");
            return "";
        }
        LogUtil.a("PLGACHIEVE_ReportUtil", "getMergedJsonStr oldData ", str, " newData ", str2);
        try {
            JSONArray jSONArray = new JSONArray(str2);
            int length = jSONArray.length();
            if (f(i)) {
                mkc d = d(str);
                mkc d2 = d(str2);
                if (d != null && d2 != null && e(d.b(), 0.0d) != 0) {
                    if (d.e() != d2.e() && d.c() == 0 && e(d.b(), d2.b()) < 0) {
                        LogUtil.a("PLGACHIEVE_ReportUtil", "old pace");
                        jSONArray.put(length, d(d, d2));
                    }
                }
                return jSONArray.toString();
            }
            if (!g(i) && !h(i)) {
                LogUtil.c("PLGACHIEVE_ReportUtil", "getMergedJsonStr type error", Integer.valueOf(i));
            }
            mka c = c(str);
            mka c2 = c(str2);
            if (c != null && c2 != null) {
                if (c.e() != c2.e() && c.d() == 0 && e(c.b(), c2.b()) > 0) {
                    LogUtil.c("PLGACHIEVE_ReportUtil", "old value");
                    jSONArray.put(length, b(c, c2));
                }
            }
            return jSONArray.toString();
            return jSONArray.toString();
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_ReportUtil", "getRefreshJsonStr Exception:", e.getMessage());
            return str2;
        }
    }

    private static JSONObject d(mkc mkcVar, mkc mkcVar2) {
        long e = mkcVar.e();
        long currentTimeMillis = System.currentTimeMillis() - e;
        LogUtil.a("PLGACHIEVE_ReportUtil", "getMergedJsonStr checkBestMotionPace startTime ", Long.valueOf(e), " interval ", Long.valueOf(currentTimeMillis));
        if (currentTimeMillis > 2592000000L) {
            LogUtil.a("PLGACHIEVE_ReportUtil", "getMergedJsonStr new pace");
            return a(mkcVar2);
        }
        return a(mkcVar);
    }

    private static JSONObject b(mka mkaVar, mka mkaVar2) {
        long e = mkaVar.e();
        long currentTimeMillis = System.currentTimeMillis() - e;
        LogUtil.a("PLGACHIEVE_ReportUtil", "getMergedJsonStr checkBestMotion startTime ", Long.valueOf(e), " interval ", Long.valueOf(currentTimeMillis));
        if (currentTimeMillis > 2592000000L) {
            LogUtil.a("PLGACHIEVE_ReportUtil", "getMergedJsonStr new value");
            return a(mkaVar2);
        }
        return a(mkaVar);
    }

    private static JSONObject a(mke mkeVar) {
        if (mkeVar == null) {
            LogUtil.h("PLGACHIEVE_ReportUtil", "getBestSteps: bestStep is null");
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("value", mkeVar.a());
            jSONObject.put(ParsedFieldTag.RECORD_DAY, mkeVar.d());
            return jSONObject;
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_ReportUtil", "JSONException ", e.getMessage());
            return null;
        }
    }

    public static JSONObject a(mka mkaVar) {
        if (mkaVar == null) {
            LogUtil.h("PLGACHIEVE_ReportUtil", "getBestMotion: bestMotion is null");
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("value", mkaVar.b());
            jSONObject.put("source", mkaVar.d());
            jSONObject.put("deviceCode", mkaVar.c());
            jSONObject.put("startTime", mkaVar.e());
            jSONObject.put("endTime", mkaVar.a());
            return jSONObject;
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_ReportUtil", "JSONException ", e.getMessage());
            return null;
        }
    }

    public static JSONObject a(mkc mkcVar) {
        if (mkcVar == null) {
            LogUtil.h("PLGACHIEVE_ReportUtil", "getBestMotonPace: bestMotionPace is null");
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("value", mkcVar.b());
            jSONObject.put("source", mkcVar.c());
            jSONObject.put("deviceCode", mkcVar.a());
            jSONObject.put("startTime", mkcVar.e());
            jSONObject.put("endTime", mkcVar.d());
            return jSONObject;
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_ReportUtil", "JSONException ", e.getMessage());
            return null;
        }
    }

    public static String c(JSONObject jSONObject) {
        JSONArray jSONArray = new JSONArray();
        try {
            jSONArray.put(0, jSONObject);
            return jSONArray.toString();
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_ReportUtil", "JSONException ", e.getMessage());
            return "";
        }
    }

    public static double d(float f) {
        try {
            return Double.parseDouble(String.valueOf(f));
        } catch (NumberFormatException e) {
            LogUtil.b("PLGACHIEVE_ReportUtil", "NumberFormatException ", e.getMessage());
            return 0.0d;
        }
    }

    public static String e(long j, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("PLGACHIEVE_ReportUtil", "deleteTrackData reportData is null");
            return "";
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            boolean z = false;
            for (int i = 0; i < jSONArray.length(); i++) {
                if (j == mdn.d("startTime", jSONArray.getJSONObject(i))) {
                    jSONArray.remove(i);
                    z = true;
                }
            }
            return (!z || jSONArray.length() == 0) ? "" : jSONArray.toString();
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_ReportUtil", "deleteTrackData Exception:", e.getMessage());
        }
        return "";
    }

    public static Pair<Double, Long> cku_(String str, long j, long j2, int i) {
        Pair<Double, Long> ckv_;
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("PLGACHIEVE_ReportUtil", "parseBestStep reportData is null");
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (f(i)) {
                ckv_ = ckw_(jSONArray, j, j2);
            } else if (i == 2) {
                ckv_ = ckx_(jSONArray, j, j2);
            } else {
                if (!g(i)) {
                    return null;
                }
                ckv_ = ckv_(jSONArray, j, j2);
            }
            return ckv_;
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_ReportUtil", "getStartTimeAndEndTime Exception:", e.getMessage());
            return null;
        }
    }

    private static Pair<Double, Long> ckw_(JSONArray jSONArray, long j, long j2) throws JSONException {
        if (jSONArray == null) {
            LogUtil.h("PLGACHIEVE_ReportUtil", "getPacePBRecord: jsonArray is null");
            return null;
        }
        long j3 = 0;
        double d = 0.0d;
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            double a2 = mdn.a("value", jSONObject);
            long d2 = mdn.d("startTime", jSONObject);
            if (d2 >= j && d2 <= j2) {
                if (d == 0.0d) {
                    d = a2;
                    j3 = d2;
                }
                if (e(a2, d) < 0) {
                    d = a2;
                    j3 = d2;
                }
            }
        }
        if (d == 0.0d || j3 == 0) {
            return null;
        }
        LogUtil.c("PLGACHIEVE_ReportUtil", "showTypeIsPace");
        return new Pair<>(Double.valueOf(d), Long.valueOf(j3));
    }

    private static Pair<Double, Long> ckx_(JSONArray jSONArray, long j, long j2) throws JSONException {
        if (jSONArray == null) {
            LogUtil.h("PLGACHIEVE_ReportUtil", "getWalkMostPBRecord: jsonArray is null");
            return null;
        }
        long j3 = 0;
        int i = 0;
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i2);
            int e = mdn.e("value", jSONObject);
            long e2 = e(String.valueOf(mdn.e(ParsedFieldTag.RECORD_DAY, jSONObject)));
            if (e2 >= j && e2 <= j2 && e > i) {
                i = e;
                j3 = e2;
            }
        }
        if (i == 0 || j3 == 0) {
            return null;
        }
        LogUtil.c("PLGACHIEVE_ReportUtil", "WALK_MOST_KEY");
        return new Pair<>(Double.valueOf(i * 1.0d), Long.valueOf(j3));
    }

    private static Pair<Double, Long> ckv_(JSONArray jSONArray, long j, long j2) throws JSONException {
        if (jSONArray == null) {
            LogUtil.h("PLGACHIEVE_ReportUtil", "getDistancePBRecord: jsonArray is null");
            return null;
        }
        long j3 = 0;
        long j4 = 0;
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            long d = mdn.d("value", jSONObject);
            long d2 = mdn.d("startTime", jSONObject);
            if (d2 >= j && d2 <= j2 && d > j3) {
                j3 = d;
                j4 = d2;
            }
        }
        if (j3 == 0 || j4 == 0) {
            return null;
        }
        LogUtil.c("PLGACHIEVE_ReportUtil", "RUN_DISTANCE_KEY");
        return new Pair<>(Double.valueOf(j3 * 1.0d), Long.valueOf(j4));
    }

    public static String d(int i, Context context) {
        if (context == null) {
            LogUtil.h("PLGACHIEVE_ReportUtil", "getBreakDescByType: context is null");
            return "";
        }
        switch (i) {
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
        }
        return "";
    }

    public static SingleDayRecord e(int i, SingleDayRecord singleDayRecord, String str) {
        if (singleDayRecord == null) {
            LogUtil.a("PLGACHIEVE_ReportUtil", "sRecord null");
            return singleDayRecord;
        }
        c(i, singleDayRecord, str);
        return singleDayRecord;
    }

    private static void c(int i, SingleDayRecord singleDayRecord, String str) {
        switch (i) {
            case 1:
                singleDayRecord.saveBestWalkDistance(str);
                break;
            case 2:
                singleDayRecord.saveBestStepDay(str);
                break;
            case 3:
                singleDayRecord.saveBestRunDistance(str);
                break;
            case 4:
                singleDayRecord.saveBestRunPace(str);
                break;
            case 5:
                singleDayRecord.saveBestRun3KMPace(str);
                break;
            case 6:
                singleDayRecord.saveBestRun5KMPace(str);
                break;
            case 7:
                singleDayRecord.saveBestRun10KMPace(str);
                break;
            case 8:
                singleDayRecord.saveBestRunHMPace(str);
                break;
            case 9:
                singleDayRecord.saveBestRunFMPace(str);
                break;
            case 10:
                singleDayRecord.saveBestCycleDistance(str);
                break;
            case 11:
                singleDayRecord.saveBestCycleSpeed(str);
                break;
            case 12:
                singleDayRecord.saveBestRopeSingCount(str);
                break;
            case 13:
                singleDayRecord.saveBestRopeContinuousCount(str);
                break;
            case 14:
                singleDayRecord.saveBestRopeSpeed(str);
                break;
            case 15:
                singleDayRecord.saveBestRopeDuration(str);
                break;
        }
    }

    public static String b(int i, SingleDayRecord singleDayRecord) {
        if (singleDayRecord == null) {
            LogUtil.a("PLGACHIEVE_ReportUtil", "sRecord null");
            return "";
        }
        String c = c(i, singleDayRecord);
        LogUtil.c("PLGACHIEVE_ReportUtil", "key=", Integer.valueOf(i), " result=", c);
        return c;
    }

    private static String c(int i, SingleDayRecord singleDayRecord) {
        switch (i) {
            case 1:
                return singleDayRecord.acquireBestWalkDistance();
            case 2:
                return singleDayRecord.acquireBestStepDay();
            case 3:
                return singleDayRecord.acquireBestRunDistance();
            case 4:
                return singleDayRecord.acquireBestRunPace();
            case 5:
                return singleDayRecord.acquireBestRun3KMPace();
            case 6:
                return singleDayRecord.acquireBestRun5KMPace();
            case 7:
                return singleDayRecord.acquireBestRun10KMPace();
            case 8:
                return singleDayRecord.acquireBestRunHMPace();
            case 9:
                return singleDayRecord.acquireBestRunFMPace();
            case 10:
                return singleDayRecord.acquireBestCycleDistance();
            case 11:
                return singleDayRecord.acquireBestCycleSpeed();
            case 12:
                return singleDayRecord.acquireBestRopeSingCount();
            case 13:
                return singleDayRecord.acquireBestRopeContinuousCount();
            case 14:
                return singleDayRecord.acquireBestRopeSpeed();
            case 15:
                return singleDayRecord.acquireBestRopeDuration();
            case 16:
                return singleDayRecord.getNpeBestResult();
            default:
                return "";
        }
    }

    public static String c(int i, Context context) {
        if (context == null) {
            LogUtil.h("PLGACHIEVE_ReportUtil", "getUnitByKey: context is null");
            return "";
        }
        if (i != 1) {
            if (i == 2) {
                return context.getString(R.string._2130840704_res_0x7f020c80);
            }
            if (i != 3 && i != 10) {
                return i != 11 ? "" : context.getString(R.string._2130844078_res_0x7f0219ae);
            }
        }
        return context.getString(R.string._2130840703_res_0x7f020c7f);
    }

    public static String a(int i, Context context) {
        if (context == null) {
            LogUtil.h("PLGACHIEVE_ReportUtil", "getInchUnitByKey: context is null");
            return "";
        }
        if (i != 1) {
            if (i == 2) {
                return context.getString(R.string._2130840704_res_0x7f020c80);
            }
            if (i != 3 && i != 10) {
                return i != 11 ? "" : context.getString(R.string._2130844079_res_0x7f0219af);
            }
        }
        return context.getString(R.string._2130839828_res_0x7f020914);
    }

    public static boolean f(int i) {
        return !m(i) || e(i);
    }

    public static String d(int i) {
        return UnitUtil.d(i);
    }

    public static String a(double d) {
        if (d != 0.0d) {
            return jed.b(c(3600.0d / d), 1, 2);
        }
        LogUtil.a("PLGACHIEVE_ReportUtil", "getCycleDistanceStr value Invalid");
        return "";
    }

    public static float e(double d) {
        return (float) c(3600.0d / d);
    }

    public static double d(long j) {
        return e(j, true);
    }

    public static double e(long j, boolean z) {
        double d = (j * 1.0d) / 1000.0d;
        return z ? c(d) : d;
    }

    public static double e(long j) {
        return c(UnitUtil.e(j * 1.0d, 3) / 1000.0d);
    }

    public static double c(double d) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setRoundingMode(RoundingMode.HALF_UP);
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        try {
            return numberFormat.parse(numberFormat.format(d)).doubleValue();
        } catch (ParseException e) {
            LogUtil.b("PLGACHIEVE_ReportUtil", e.getMessage());
            return 0.0d;
        }
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            return UnitUtil.a(new Date(Long.parseLong(str)), 20);
        } catch (NumberFormatException unused) {
            LogUtil.b("PLGACHIEVE_ReportUtil", "setGainTime NumberFormatException");
            return str;
        }
    }

    public static String a(long j, int i) {
        return UnitUtil.a(new Date(j), i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 5 ? 20 : 36 : 131080 : 131076 : 24 : 52);
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("PLGACHIEVE_ReportUtil", "getStepString: value empty");
            return "";
        }
        try {
            return UnitUtil.a(new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(str), 20);
        } catch (ParseException unused) {
            LogUtil.b("PLGACHIEVE_ReportUtil", "getStepString ParseException");
            return "";
        }
    }

    public static String a(long j) {
        return UnitUtil.a(new Date(j), 20);
    }

    public static long e(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        try {
            return new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(str).getTime();
        } catch (ParseException unused) {
            LogUtil.b("PLGACHIEVE_ReportUtil", "getStepString ParseException");
            return 0L;
        }
    }

    public static int b(int i) {
        return (!m(i) || i == 3) ? R.mipmap._2131821046_res_0x7f1101f6 : (e(i) || i == 10) ? R.mipmap._2131821045_res_0x7f1101f5 : h(i) ? R.drawable.pic_pb_robe : R.mipmap._2131821047_res_0x7f1101f7;
    }

    public static List<Integer> c() {
        ArrayList arrayList = new ArrayList(10);
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), "30004", "ai-report-annual-001");
        LogUtil.h("PLGACHIEVE_ReportUtil", "fetchConfigAnnualShowList config ", b);
        if (TextUtils.isEmpty(b)) {
            return arrayList;
        }
        try {
            JSONObject jSONObject = new JSONObject(b).getJSONObject(e.n);
            for (int b2 = mht.b(mht.d(System.currentTimeMillis())); b2 >= 2017; b2--) {
                if (jSONObject.has(String.valueOf(b2))) {
                    boolean i = i(jSONObject.getString(String.valueOf(b2)));
                    LogUtil.a("PLGACHIEVE_ReportUtil", "fetchConfigAnnualShowList year ", Integer.valueOf(b2), " isShow ", Boolean.valueOf(i));
                    if (i) {
                        arrayList.add(Integer.valueOf(b2));
                    }
                }
            }
        } catch (JSONException unused) {
            LogUtil.b("PLGACHIEVE_ReportUtil", "fetchConfigAnnualShowList JSONException");
        }
        return arrayList;
    }

    private static boolean i(String str) {
        str.hashCode();
        return !str.equals("1") ? !str.equals("2") : CommonUtil.as() || CommonUtil.aq();
    }

    public static String c(int i) {
        switch (i) {
            case 5:
                return "3km";
            case 6:
                return "5km";
            case 7:
                return "10km";
            case 8:
                return "21.0975km";
            case 9:
                return "42.195km";
            default:
                return "";
        }
    }
}
