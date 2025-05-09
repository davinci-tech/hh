package defpackage;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.ImageView;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.model.SportDetailChartDataType;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.UnitUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public class hji {
    private static final String d = gvv.e(BaseApplication.getContext());
    private static final int[] e = {R.string._2130842967_res_0x7f021557, R.string._2130842968_res_0x7f021558, R.string._2130842969_res_0x7f021559, R.string._2130842970_res_0x7f02155a, R.string._2130842966_res_0x7f021556};
    private static final int[] b = {R.string._2130839932_res_0x7f02097c, R.string._2130839933_res_0x7f02097d, R.string._2130839934_res_0x7f02097e, R.string._2130839935_res_0x7f02097f, R.string._2130839936_res_0x7f020980};

    public static boolean a(int i) {
        return i == 5;
    }

    public static double b(float f) {
        if (f != 0.0f) {
            return 3600.0f / f;
        }
        return 0.0d;
    }

    private static boolean b(int i, int i2, int i3, int i4) {
        return i > 80 && i > i2 && i > i4 && i > i3;
    }

    public static boolean c(float f, float f2) {
        return f < Float.MAX_VALUE && f2 > Float.MIN_VALUE;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0021 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0015 A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int d(int r2, int r3) {
        /*
            r0 = 280(0x118, float:3.92E-43)
            r1 = 10
            if (r2 == r0) goto L11
            r0 = 282(0x11a, float:3.95E-43)
            if (r2 == r0) goto L11
            switch(r2) {
                case 257: goto L11;
                case 258: goto L11;
                case 259: goto Le;
                case 260: goto L11;
                default: goto Ld;
            }
        Ld:
            goto L15
        Le:
            if (r3 < r1) goto L15
            goto L21
        L11:
            r2 = 20
            if (r3 > r2) goto L17
        L15:
            r1 = 1
            goto L22
        L17:
            r2 = 60
            if (r3 > r2) goto L1d
            r1 = 3
            goto L22
        L1d:
            r2 = 100
            if (r3 > r2) goto L22
        L21:
            r1 = 5
        L22:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.hji.d(int, int):int");
    }

    public static int e(int i) {
        return i <= 10 ? 1 : 5;
    }

    public static boolean f(int i) {
        return i == 262 || i == 266;
    }

    public static float h(float f) {
        if (f <= 1.0f) {
            return 1.05f;
        }
        if (f >= 5.0f) {
            return 5.5f;
        }
        return f;
    }

    public static boolean h(int i) {
        return i == 194 || i == 197 || i == 201 || i == 208 || i == 209 || i == 172 || i == 178;
    }

    public static boolean j(int i) {
        return i == 198 || i == 199 || i == 200;
    }

    private static boolean k(int i) {
        return i == 257 || i == 258 || i == 260 || i == 282;
    }

    private static boolean m(int i) {
        return i == 261 || i == 264 || i == 280 || i == 281 || i == 271 || i == 273 || i == 222 || i == 270 || i == 169 || i == 214;
    }

    public static String a(Context context, float f, int i) {
        int floor;
        if (context != null && (floor = (int) Math.floor(f - 1.0f)) >= 0) {
            int[] iArr = e;
            if (floor < iArr.length) {
                return context.getString(i == 0 ? iArr[floor] : b[floor]);
            }
        }
        return "";
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x03cc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Integer[] c(com.huawei.up.model.UserInfomation r48) {
        /*
            Method dump skipped, instructions count: 1025
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.hji.c(com.huawei.up.model.UserInfomation):java.lang.Integer[]");
    }

    public static String a(Context context, Integer[] numArr, int i, boolean z) {
        String[] stringArray;
        if (context == null || numArr == null) {
            return "";
        }
        TreeSet treeSet = new TreeSet(Arrays.asList(numArr));
        int binarySearch = treeSet.floor(Integer.valueOf(i)) != null ? Arrays.binarySearch(numArr, treeSet.floor(Integer.valueOf(i))) : 0;
        if (z) {
            stringArray = context.getResources().getStringArray(R.array._2130968627_res_0x7f040033);
        } else {
            stringArray = context.getResources().getStringArray(R.array._2130968626_res_0x7f040032);
        }
        if (binarySearch >= stringArray.length) {
            binarySearch = stringArray.length - 1;
        }
        return stringArray[binarySearch];
    }

    public static float a(Integer[] numArr) {
        if (numArr == null || numArr.length < 5) {
            return 0.0f;
        }
        return (numArr[3].intValue() + numArr[4].intValue()) / 2.0f;
    }

    public static SpannableString bgN_(String str, String str2, int i, int i2, Context context) {
        if (context == null || str == null || str2 == null) {
            return new SpannableString("");
        }
        SpannableString spannableString = new SpannableString(str2);
        spannableString.setSpan(new TextAppearanceSpan(context, i2), 0, spannableString.toString().length(), 33);
        Matcher matcher = Pattern.compile(str, 2).matcher(spannableString);
        while (matcher.find()) {
            spannableString.setSpan(new TextAppearanceSpan(context, i), matcher.start(), matcher.end(), 33);
        }
        return spannableString;
    }

    public static String o(float f) {
        if (f == 0.0f) {
            return d;
        }
        return UnitUtil.e(3600.0f / f, 1, 2);
    }

    public static String g(float f) {
        if (UnitUtil.h()) {
            return o(f * 1.609344f);
        }
        return o(f);
    }

    public static String a(float f) {
        return gvv.a(f);
    }

    public static List<Map.Entry<Integer, Float>> d(Map<Integer, Float> map, float f) {
        if (map == null) {
            return new ArrayList(16);
        }
        ArrayList arrayList = new ArrayList(map.entrySet());
        Collections.sort(arrayList, new Comparator<Map.Entry<Integer, Float>>() { // from class: hji.1
            @Override // java.util.Comparator
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public int compare(Map.Entry<Integer, Float> entry, Map.Entry<Integer, Float> entry2) {
                return Integer.compare(hji.b(entry), hji.b(entry2));
            }
        });
        Iterator it = arrayList.iterator();
        while (it.hasNext() && ((int) ((Float) ((Map.Entry) it.next()).getValue()).floatValue()) != ((int) f)) {
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int b(Map.Entry<Integer, Float> entry) {
        if (entry == null) {
            return 0;
        }
        return entry.getKey().intValue();
    }

    public static String e(double d2) {
        double d3 = d2 / 1000.0d;
        return UnitUtil.h() ? String.valueOf(UnitUtil.e(UnitUtil.e(d3, 3), 1, 2)) : String.valueOf(UnitUtil.e(d3, 1, 2));
    }

    public static String c(long j) {
        return UnitUtil.d((int) TimeUnit.MILLISECONDS.toSeconds(j));
    }

    public static String b(long j) {
        return UnitUtil.a((int) TimeUnit.MILLISECONDS.toSeconds(j));
    }

    public static String c(float f) {
        if (UnitUtil.h()) {
            f = (float) UnitUtil.d(f, 3);
        }
        double d2 = f;
        if (d2 > 360000.0d || d2 <= 3.6d) {
            return d;
        }
        return gvv.a(f);
    }

    public static String d(float f) {
        if (UnitUtil.h()) {
            return UnitUtil.e(UnitUtil.e(f, 1), 1, 2);
        }
        return UnitUtil.e(f, 1, 1);
    }

    public static String c(int i) {
        return UnitUtil.e((i * 1.0d) / 1000.0d, 1, 0);
    }

    public static String b(long j, int i) {
        if (j == 0) {
            LogUtil.b("Track_FragmentUtils", "sport time is zero");
            return d;
        }
        double d2 = (i / ((j * 1.0d) / 1000.0d)) * 100.0d;
        return UnitUtil.e(d2 <= 100.0d ? d2 : 100.0d, 2, 2);
    }

    public static String b(int i) {
        return String.valueOf(UnitUtil.e(i, 1, 0));
    }

    public static String j(float f) {
        if (UnitUtil.h()) {
            f = (float) UnitUtil.e(f, 0);
        }
        return UnitUtil.e(f, 1, 0);
    }

    public static double c(double d2) {
        return UnitUtil.h() ? UnitUtil.e(d2 / 100.0d, 2) : d2 / 100.0d;
    }

    public static String f(float f) {
        float f2 = f / 10.0f;
        double d2 = f2;
        if (d2 > 360000.0d || d2 <= 3.6d) {
            return d;
        }
        if (UnitUtil.h()) {
            f2 = (float) UnitUtil.d(d2, 2);
        }
        return gvv.a(f2);
    }

    public static String i(float f) {
        return UnitUtil.e(UnitUtil.h() ? UnitUtil.e(f, 2) : f, 1, 0);
    }

    public static String b(int i, long j) {
        if (j == 0) {
            LogUtil.b("Track_FragmentUtils", "wrong time");
            return d;
        }
        double d2 = (i * 3.6f) / j;
        if (UnitUtil.h()) {
            d2 = UnitUtil.e(d2, 3);
        }
        if (d2 >= 1000.0d || d2 < 0.01d) {
            return d;
        }
        return UnitUtil.e(d2, 1, 2);
    }

    public static float d(Map<Integer, Float> map, hjw hjwVar) {
        if (map == null || map.isEmpty() || hjwVar == null) {
            LogUtil.a("Track_FragmentUtils", "getLastUseTime paceMap or manager is invalid");
            return 0.0f;
        }
        if (gvv.a(hjwVar)) {
            return e(map, hjwVar);
        }
        String e2 = UnitUtil.e(0.0d, 1, 0);
        if (e(hjwVar.e().requestTotalDistance()).endsWith(e2 + e2)) {
            LogUtil.a("Track_FragmentUtils", "getLastUseTime distance is integer");
            return 0.0f;
        }
        long seconds = TimeUnit.MILLISECONDS.toSeconds(hjwVar.e().requestTotalTime());
        while (map.entrySet().iterator().hasNext()) {
            seconds -= Math.round(r6.next().getValue().floatValue());
        }
        LogUtil.a("Track_FragmentUtils", "getLastUseTime ", LogAnonymous.d(hjwVar.e().requestTotalTime()));
        if (seconds <= 0) {
            return 0.0f;
        }
        return seconds;
    }

    private static float e(Map<Integer, Float> map, hjw hjwVar) {
        if (map == null || map.isEmpty() || hjwVar == null) {
            LogUtil.h("Track_FragmentUtils", "requestBikeTrackLastUseTime validPaceMap or manager is invalid");
            return 0.0f;
        }
        if (Double.compare(a(hjwVar), 0.0d) == 0) {
            return 0.0f;
        }
        long seconds = TimeUnit.MILLISECONDS.toSeconds(hjwVar.e().requestTotalTime());
        Iterator<Map.Entry<Integer, Float>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            seconds -= (long) (it.next().getValue().floatValue() * 5.0f);
        }
        if (seconds <= 0) {
            return 0.0f;
        }
        return seconds;
    }

    public static double a(hjw hjwVar) {
        double requestTotalDistance = (hjwVar.e().requestTotalDistance() / 1000.0d) - (gvv.a(hjwVar) ? (((int) r0) / 5) * 5 : (int) r0);
        return UnitUtil.h() ? UnitUtil.e(requestTotalDistance, 3) : requestTotalDistance;
    }

    public static void d(int i, Context context) {
        d(i, context, 0);
    }

    public static void d(int i, Context context, int i2) {
        if (context == null) {
            return;
        }
        ComponentName componentName = new ComponentName(context.getPackageName(), "com.huawei.ui.homehealth.runcard.SportNounExplainActivity");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra(BleConstants.SPORT_TYPE, i);
        intent.putExtra("eteAlgoKey", i2);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("Track_FragmentUtils", " ActivityNotFoundException.");
        }
    }

    public static boolean g(int i) {
        return i == 260 || i == 259 || i == 273 || i == 265 || kxb.a(i) || i == 163 || h(i);
    }

    public static String a(hjw hjwVar, boolean z) {
        return hjwVar == null ? "" : b((float) ffw.c(hjwVar.d().requestRealTimeSpeedList()), z);
    }

    public static String c(hjw hjwVar, boolean z) {
        if (hjwVar == null) {
            return "";
        }
        float l = (float) ffw.l(hjwVar.d().requestRealTimeSpeedList());
        if (l < 0.0f) {
            l = 0.0f;
        }
        return b(l, z);
    }

    public static int[] c(Map<String, Integer> map) {
        if (map == null) {
            LogUtil.a("Track_FragmentUtils", "score is null");
            return null;
        }
        int i = 25;
        int intValue = (!map.containsKey("jump_score") || map.get("jump_score") == null) ? 25 : map.get("jump_score").intValue();
        int intValue2 = (!map.containsKey("breakthrough_score") || map.get("breakthrough_score") == null) ? 25 : map.get("breakthrough_score").intValue();
        int intValue3 = (!map.containsKey("sport_intensity_score") || map.get("sport_intensity_score") == null) ? 25 : map.get("sport_intensity_score").intValue();
        int intValue4 = (!map.containsKey("run_score") || map.get("run_score") == null) ? 25 : map.get("run_score").intValue();
        if (map.containsKey("burst_score") && map.get("burst_score") != null) {
            i = map.get("burst_score").intValue();
        }
        return new int[]{intValue, intValue3, intValue2, intValue4, i};
    }

    private static String b(float f, boolean z) {
        String e2;
        if (UnitUtil.h()) {
            e2 = UnitUtil.e(UnitUtil.e(f, 3), 1, 2);
        } else {
            e2 = UnitUtil.e(f, 1, 2);
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(e2);
        if (z) {
            stringBuffer.append(" ");
            if (UnitUtil.h()) {
                stringBuffer.append(BaseApplication.getContext().getResources().getString(R.string._2130844079_res_0x7f0219af));
            } else {
                stringBuffer.append(BaseApplication.getContext().getResources().getString(R.string._2130844078_res_0x7f0219ae));
            }
        }
        return stringBuffer.toString();
    }

    public static boolean c(hjw hjwVar) {
        return (hjwVar == null || hjwVar.d() == null || !hjwVar.d().isValidSpeedList()) ? false : true;
    }

    public static boolean d(hjw hjwVar) {
        return hjwVar != null && ((float) b((float) ffw.a(hjwVar.d().requestRealTimePaceList()))) <= 120.0f;
    }

    public static boolean i(int i) {
        return k(i) || m(i);
    }

    public static boolean a(int i, int i2) {
        if (i == 274 || i == 273 || i == 265) {
            return a(i2);
        }
        return false;
    }

    public static boolean d(int i, MotionPath motionPath) {
        return motionPath != null && i == 273 && motionPath.isValidCadenceData();
    }

    public static Float[] d(ArrayList<knz> arrayList) {
        Float[] fArr = new Float[2];
        float f = 0.0f;
        Float valueOf = Float.valueOf(0.0f);
        if (arrayList == null || arrayList.size() == 0) {
            fArr[0] = valueOf;
            fArr[1] = valueOf;
            return fArr;
        }
        Iterator<knz> it = arrayList.iterator();
        float f2 = 0.0f;
        boolean z = false;
        while (it.hasNext()) {
            float c = (float) it.next().c();
            if (!z) {
                z = true;
                f = c;
                f2 = f;
            }
            if (c > f) {
                f = c;
            }
            if (c < f2) {
                f2 = c;
            }
        }
        fArr[0] = Float.valueOf(f);
        fArr[1] = Float.valueOf(f2);
        return fArr;
    }

    public static int a(List<ixt> list) {
        if (koq.b(list)) {
            return 0;
        }
        return ((ixt) Collections.max(list, new Comparator<ixt>() { // from class: hji.3
            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(ixt ixtVar, ixt ixtVar2) {
                return Double.compare(hji.b(ixtVar), hji.b(ixtVar2));
            }
        })).e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double b(ixt ixtVar) {
        if (ixtVar != null && ixtVar.e() > 0) {
            return ixtVar.e();
        }
        return 0.0d;
    }

    public static int c(List<ixt> list) {
        if (koq.b(list)) {
            return 0;
        }
        return ((ixt) Collections.max(list, new Comparator<ixt>() { // from class: hji.5
            @Override // java.util.Comparator
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public int compare(ixt ixtVar, ixt ixtVar2) {
                return Double.compare(hji.c(ixtVar), hji.c(ixtVar2));
            }
        })).c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double c(ixt ixtVar) {
        if (ixtVar != null && ixtVar.c() > 0) {
            return ixtVar.c();
        }
        return 0.0d;
    }

    public static int d(List<ixt> list) {
        if (koq.c(list)) {
            int i = 0;
            int i2 = 0;
            for (ixt ixtVar : list) {
                if (ixtVar != null) {
                    i2 += ixtVar.c();
                    i++;
                }
            }
            if (i != 0) {
                return Math.round(i2 / i);
            }
        }
        return 0;
    }

    public static int e(List<koc> list) {
        if (koq.c(list)) {
            int i = 0;
            int i2 = 0;
            for (koc kocVar : list) {
                if (kocVar != null) {
                    i2 += kocVar.b();
                    i++;
                }
            }
            if (i != 0) {
                return Math.round(i2 / i);
            }
        }
        return 0;
    }

    public static int c(MotionPathSimplify motionPathSimplify, MotionPath motionPath) {
        return d(motionPathSimplify, motionPath);
    }

    public static int c(MotionPathSimplify motionPathSimplify) {
        return b(motionPathSimplify);
    }

    private static int b(MotionPathSimplify motionPathSimplify) {
        if (motionPathSimplify == null) {
            LogUtil.b("Track_FragmentUtils", "motionPathSimplify is null");
            return 0;
        }
        float requestTotalSteps = motionPathSimplify.requestTotalSteps();
        if (((int) TimeUnit.MILLISECONDS.toSeconds(motionPathSimplify.requestTotalTime())) > 0) {
            return (int) Math.floor((requestTotalSteps / r2) * 60.0f);
        }
        return 0;
    }

    private static int d(MotionPathSimplify motionPathSimplify, MotionPath motionPath) {
        if (motionPathSimplify == null) {
            LogUtil.b("Track_FragmentUtils", "motionPathSimplify is null");
            return 0;
        }
        float requestTotalSteps = motionPathSimplify.requestTotalSteps();
        if (requestTotalSteps > 0.0f) {
            long seconds = TimeUnit.MILLISECONDS.toSeconds(motionPathSimplify.requestTotalTime());
            LogUtil.a("Track_FragmentUtils", "avg during =", Long.valueOf(seconds));
            if (seconds > 0) {
                return e(requestTotalSteps, seconds, motionPathSimplify);
            }
            return 0;
        }
        return e(motionPath);
    }

    private static int e(float f, long j, MotionPathSimplify motionPathSimplify) {
        double floor;
        if (motionPathSimplify.requestSportType() == 265 && motionPathSimplify.requestSportDataSource() == 5) {
            floor = Math.floor((f / j) * 60.0f);
        } else {
            floor = Math.floor(((f / 2.0f) / j) * 60.0f);
        }
        return (int) floor;
    }

    private static int e(MotionPath motionPath) {
        if (motionPath == null) {
            LogUtil.b("Track_FragmentUtils", "motionPath is null");
            return 0;
        }
        if (koq.b(motionPath.requestRidePostureDataList())) {
            LogUtil.h("Track_FragmentUtils", "getAverageCadence cadenceDataList is empty");
            return 0;
        }
        List<ffn> requestRidePostureDataList = motionPath.requestRidePostureDataList();
        int size = requestRidePostureDataList.size();
        int i = 0;
        for (ffn ffnVar : requestRidePostureDataList) {
            if (ffnVar != null) {
                if (ffnVar.e() > 0) {
                    i += ffnVar.e();
                } else {
                    size--;
                }
            }
        }
        if (size <= 0) {
            return 0;
        }
        return (int) Math.round(i / size);
    }

    public static int g(List<kob> list) {
        if (koq.b(list)) {
            return 0;
        }
        return ((kob) Collections.max(list, new Comparator() { // from class: hjk
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Integer.compare(hji.a((kob) obj), hji.a((kob) obj2));
                return compare;
            }
        })).c();
    }

    private static int a(kob kobVar) {
        if (kobVar == null) {
            return 0;
        }
        return Math.max(kobVar.c(), 0);
    }

    public static int b(List<ixt> list) {
        if (koq.c(list)) {
            int i = 0;
            int i2 = 0;
            for (ixt ixtVar : list) {
                if (ixtVar != null) {
                    i2 += ixtVar.e();
                    i++;
                }
            }
            if (i != 0) {
                return Math.round(i2 / i);
            }
        }
        return 0;
    }

    public static String d(Context context) {
        if (context == null) {
            LogUtil.h("Track_FragmentUtils", "aquireSwimUnit  and context is null");
            return "";
        }
        if (UnitUtil.h()) {
            return context.getResources().getQuantityString(R.plurals._2130903226_res_0x7f0300ba, 100, 100);
        }
        return context.getResources().getQuantityString(R.plurals._2130903225_res_0x7f0300b9, 100, 100);
    }

    public static ArrayList<Integer> e(hjw hjwVar) {
        List<koe> requestSpeedList = hjwVar.d().requestSpeedList();
        if (koq.b(requestSpeedList)) {
            LogUtil.b("Track_FragmentUtils", "speed list is empty");
            return null;
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (koe koeVar : requestSpeedList) {
            if (koeVar == null) {
                LogUtil.b("Track_FragmentUtils", "speed data is null");
            } else {
                float a2 = koeVar.a();
                if (a2 > 19.444445f) {
                    i2++;
                } else if (a2 > 13.888889f) {
                    i3++;
                } else if (a2 > 8.333334f) {
                    i4++;
                } else if (a2 > 2.777778f) {
                    i5++;
                }
                i++;
            }
        }
        if (i == 0) {
            LogUtil.b("Track_FragmentUtils", "speed list is empty");
            return null;
        }
        ArrayList<Integer> arrayList = new ArrayList<>(5);
        double d2 = i;
        int floor = (int) Math.floor((i2 / d2) * 100.0d);
        int floor2 = (int) Math.floor((i3 / d2) * 100.0d);
        int floor3 = (int) Math.floor((i4 / d2) * 100.0d);
        int floor4 = (int) Math.floor((i5 / d2) * 100.0d);
        arrayList.add(Integer.valueOf(floor));
        arrayList.add(Integer.valueOf(floor2));
        arrayList.add(Integer.valueOf(floor3));
        arrayList.add(Integer.valueOf(floor4));
        int i6 = (((100 - floor) - floor2) - floor3) - floor4;
        arrayList.add(Integer.valueOf(i6));
        LogUtil.a("Track_FragmentUtils", "percentage", Integer.valueOf(i6));
        return arrayList;
    }

    public static ArrayList<Integer> d(List<Double> list, List<Integer> list2, boolean z) {
        if (koq.b(list2) || koq.b(list) || list.size() != 5) {
            LogUtil.b("Track_FragmentUtils", "rankList is empty");
            return null;
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (Integer num : list2) {
            if (num.intValue() >= list.get(4).doubleValue()) {
                i++;
            } else if (num.intValue() >= list.get(3).doubleValue()) {
                i5++;
            } else if (num.intValue() >= list.get(2).doubleValue()) {
                i4++;
            } else if (num.intValue() >= list.get(1).doubleValue()) {
                i3++;
            }
            i2++;
        }
        if (i2 == 0) {
            LogUtil.b("Track_FragmentUtils", "list is empty");
            return null;
        }
        if (z) {
            return e(i2, i, i5, i4, i3);
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(Integer.valueOf(i));
        arrayList.add(Integer.valueOf(i5));
        arrayList.add(Integer.valueOf(i4));
        arrayList.add(Integer.valueOf(i3));
        arrayList.add(Integer.valueOf((((i2 - i) - i5) - i4) - i3));
        return arrayList;
    }

    private static ArrayList<Integer> e(int i, double d2, double d3, double d4, double d5) {
        ArrayList<Integer> arrayList = new ArrayList<>(5);
        double d6 = i;
        int floor = (int) Math.floor((d2 / d6) * 100.0d);
        int floor2 = (int) Math.floor((d3 / d6) * 100.0d);
        int floor3 = (int) Math.floor((d4 / d6) * 100.0d);
        int floor4 = (int) Math.floor((d5 / d6) * 100.0d);
        arrayList.add(Integer.valueOf(floor));
        arrayList.add(Integer.valueOf(floor2));
        arrayList.add(Integer.valueOf(floor3));
        arrayList.add(Integer.valueOf(floor4));
        int i2 = (((100 - floor) - floor2) - floor3) - floor4;
        arrayList.add(Integer.valueOf(i2));
        LogUtil.a("Track_FragmentUtils", "percentage", Integer.valueOf(i2));
        return arrayList;
    }

    public static int a(int i, int[] iArr) {
        if (i < 1800 || iArr == null) {
            return 0;
        }
        int i2 = iArr[0];
        int i3 = iArr[2];
        int i4 = iArr[4];
        int i5 = iArr[3];
        int i6 = iArr[1];
        if (i2 > 80 && i3 > 80 && i4 > 80 && i5 > 80 && i6 > 80) {
            return R.drawable._2131430693_res_0x7f0b0d25;
        }
        if (b(i3, i2, i5, i6) && b(i4, i2, i5, i6)) {
            return R.drawable._2131430695_res_0x7f0b0d27;
        }
        if (d(iArr) == 0) {
            return R.drawable._2131430692_res_0x7f0b0d24;
        }
        if (i > 3600 && d(iArr) == 3) {
            return R.drawable._2131430694_res_0x7f0b0d26;
        }
        if (d(iArr) == 1) {
            return R.drawable._2131430691_res_0x7f0b0d23;
        }
        LogUtil.a("Track_FragmentUtils", "Normal people");
        return 0;
    }

    private static int d(int[] iArr) {
        int[] iArr2 = new int[5];
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        Arrays.sort(iArr2);
        for (int i = 0; i < iArr.length; i++) {
            int i2 = iArr[i];
            if (i2 == iArr2[4] && i2 > 80) {
                return i;
            }
        }
        return -1;
    }

    public static boolean a(MotionPathSimplify motionPathSimplify) {
        return motionPathSimplify.requestSportType() == 274 && motionPathSimplify.requestSportDataSource() == 5;
    }

    public static String e(float f) {
        double d2 = f;
        if (d2 > 360000.0d || d2 <= 3.6d) {
            return d;
        }
        return gvv.a(f);
    }

    public static String a(int i, Context context) {
        if (context == null) {
            return "/";
        }
        if (i == 274) {
            if (UnitUtil.h()) {
                return context.getResources().getQuantityString(R.plurals._2130903226_res_0x7f0300ba, 100, 100);
            }
            return context.getResources().getQuantityString(R.plurals._2130903225_res_0x7f0300b9, 500, 500);
        }
        if (UnitUtil.h()) {
            return "/" + context.getString(R.string._2130844081_res_0x7f0219b1);
        }
        return "/" + context.getString(R.string._2130844082_res_0x7f0219b2);
    }

    public static String a(Context context, int i, String str) {
        if (context == null) {
            return "";
        }
        String string = context.getResources().getString(R.string._2130839835_res_0x7f02091b);
        if (kxb.c(i)) {
            string = context.getResources().getString(R.string._2130839894_res_0x7f020956);
        }
        if (i == 220) {
            string = context.getResources().getString(R.string._2130839903_res_0x7f02095f);
        }
        return ((i == 274 && str.equals("291")) || i == 283) ? context.getResources().getString(R.string._2130845946_res_0x7f0220fa) : string;
    }

    public static boolean a(int i, MotionPathSimplify motionPathSimplify) {
        if (motionPathSimplify != null) {
            return i == 273 && motionPathSimplify.getExtendDataInt("crossTrainerCadence") <= 0;
        }
        LogUtil.h("Track_FragmentUtils", "isNewCrossTrainerRecode motionPathSimplify is null");
        return false;
    }

    public static String c(int i, long j, Context context, int i2) {
        if (context == null) {
            LogUtil.h("Track_FragmentUtils", "getRecoveryTimeMessage context == null");
            return "";
        }
        LogUtil.a("Track_FragmentUtils", "getRecoveryTimeMessage etealgo ", Integer.valueOf(i2));
        if (i2 == 1) {
            return "";
        }
        if (i <= 35) {
            return context.getString(R.string._2130839859_res_0x7f020933);
        }
        if (i <= 53) {
            Calendar a2 = a(i, j);
            a2.add(10, -35);
            return String.format(context.getString(R.string._2130839860_res_0x7f020934), b(a2.getTimeInMillis(), context));
        }
        if (i <= 96) {
            Calendar a3 = a(i, j);
            a3.add(10, -35);
            return String.format(context.getString(R.string._2130839861_res_0x7f020935), b(a3.getTimeInMillis(), context));
        }
        LogUtil.c("Track_FragmentUtils", "getAfterSportSummaryFourTrainEffect unused branch.");
        return "";
    }

    private static Calendar a(int i, long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.add(10, i);
        return calendar;
    }

    private static String b(long j, Context context) {
        return nsj.c(context, j, 131091);
    }

    public static Boolean b(hjw hjwVar) {
        int requestSportType = hjwVar.e().requestSportType();
        return Boolean.valueOf(!(requestSportType == 283 || requestSportType == 264) || hjwVar.e().requestSportDataSource() == 5);
    }

    public static void e(SportDetailChartDataType sportDetailChartDataType, hix hixVar, final Context context, final hjw hjwVar) {
        if (hixVar == null || context == null || hjwVar == null) {
            LogUtil.h("Track_FragmentUtils", "in setRecoverHeartRateChartModel is null");
            return;
        }
        if (sportDetailChartDataType == SportDetailChartDataType.HEART_RATE_RECOVERY) {
            HwHealthBaseCombinedChart e2 = hixVar.e();
            final Resources resources = BaseApplication.getContext().getResources();
            XAxis xAxis = e2.getXAxis();
            xAxis.setAxisMinimum(0);
            xAxis.setAxisMaximum(120);
            xAxis.setValueFormatter(new IAxisValueFormatter() { // from class: hji.2
                @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
                public String getFormattedValue(float f, AxisBase axisBase) {
                    DecimalFormat decimalFormat = new DecimalFormat("#");
                    int round = Math.round(f / 60.0f);
                    if ("0".equals(decimalFormat.format(round))) {
                        return nsj.c(context, hjwVar.e().requestEndTime(), 1);
                    }
                    return resources.getQuantityString(R.plurals._2130903305_res_0x7f030109, round, Integer.valueOf(round));
                }
            });
            e2.getDescription().setText("");
            e2.refresh();
            hixVar.d(e2);
        }
    }

    public static void bgM_(final int i, ImageView imageView, final String str) {
        if (imageView == null) {
            LogUtil.b("Track_FragmentUtils", "explainIcon == null");
        } else {
            imageView.setVisibility(0);
            imageView.setOnClickListener(new View.OnClickListener() { // from class: hji.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (nsn.o()) {
                        LogUtil.h("Track_FragmentUtils", "downRemindClick is fast click");
                        ViewClickInstrumentation.clickOnView(view);
                    } else {
                        caj.a().a(str);
                        hji.o(i);
                        ViewClickInstrumentation.clickOnView(view);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void o(int i) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put("chartType", Integer.valueOf(i));
        hashMap.put("dataPage", "trackDetails");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_CIRCLE_RING_GUIDE_1040121.value(), hashMap, 0);
    }

    public static String d(Context context, int i) {
        if (context == null) {
            LogUtil.h("Track_FragmentUtils", "getDistanceAndUnit context == null");
            return "";
        }
        if (UnitUtil.h()) {
            double e2 = UnitUtil.e(i, 1);
            return context.getResources().getQuantityString(R.plurals._2130903306_res_0x7f03010a, (int) e2, UnitUtil.e(e2, 1, 0));
        }
        return context.getResources().getQuantityString(R.plurals._2130903307_res_0x7f03010b, i, UnitUtil.e(i, 1, 0));
    }

    public static String b(double d2) {
        Context context = BaseApplication.getContext();
        if (Double.compare(d2, 100000.0d) >= 0 && LanguageUtil.m(context)) {
            return context.getResources().getString(R.string._2130850469_res_0x7f0232a5, UnitUtil.e(d2 / 10000.0d, 1, 2));
        }
        return context.getResources().getString(R.string.IDS_plugindameon_hw_show_sport_kcal_string, UnitUtil.e(d2, 1, 0));
    }
}
