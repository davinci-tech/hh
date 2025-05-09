package defpackage;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.common.security.SecurityConstant;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.HwGetSetingItemConstants;
import com.huawei.hihealth.HiSampleConfigKey;
import com.huawei.hihealth.HiSampleConfigProcessOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwfoundationmodel.trackmodel.StepRateData;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes4.dex */
public class gvv {
    private static final String d = SecurityConstant.d;
    private static final String c = File.separator + "odm" + File.separator + "etc" + File.separator + "higeo.conf";

    public static boolean a(int i) {
        return i == 258 || i == 264 || i == 257;
    }

    public static boolean a(int i, boolean z) {
        return (i == 258 || i == 264) && !z;
    }

    public static boolean b(int i) {
        return i > 0 && i < 220;
    }

    public static int d(int i) {
        if (i == -1) {
            return R.color._2131299278_res_0x7f090bce;
        }
        if (i == 0) {
            return R.color._2131299279_res_0x7f090bcf;
        }
        if (i != 1) {
            if (i == 2) {
                return R.color._2131299273_res_0x7f090bc9;
            }
            if (i == 3) {
                return R.color._2131299272_res_0x7f090bc8;
            }
        }
        return R.color._2131299276_res_0x7f090bcc;
    }

    public static int e(int i) {
        if (i == 264) {
            return 3;
        }
        switch (i) {
            case 257:
                return 6;
            case 258:
                return 1;
            case 259:
                return 2;
            default:
                return 0;
        }
    }

    public static int e(int i, int i2) {
        if (i2 != 46) {
            if (i2 == 41) {
                return R.drawable._2131431925_res_0x7f0b11f5;
            }
            if (i == 3 || i == 4) {
                return R.drawable._2131431926_res_0x7f0b11f6;
            }
            if (i != 5) {
                if (i != 6) {
                    return 0;
                }
                return R.drawable._2131431924_res_0x7f0b11f4;
            }
        }
        return R.drawable._2131431927_res_0x7f0b11f7;
    }

    public static final String a(float f) {
        return fhq.a(BaseApplication.getContext(), f);
    }

    public static Map<Integer, Float> a(Map<Integer, Float> map) {
        ArrayList arrayList = new ArrayList(16);
        ArrayList<Integer> arrayList2 = new ArrayList(16);
        TreeMap treeMap = new TreeMap();
        if (map == null) {
            LogUtil.c("Track_CommonUtil", "paceMap is null");
            return null;
        }
        for (Map.Entry<Integer, Float> entry : map.entrySet()) {
            treeMap.put(entry.getKey(), entry.getValue());
            int intValue = entry.getKey().intValue();
            if (intValue < 100000) {
                if (!arrayList2.contains(Integer.valueOf(intValue))) {
                    arrayList2.add(Integer.valueOf(intValue));
                }
            } else {
                int i = intValue / 100000;
                if (i % 100 != 0) {
                    if (!arrayList2.contains(Integer.valueOf(intValue))) {
                        arrayList2.add(Integer.valueOf(intValue));
                    }
                } else {
                    int i2 = i / 100;
                    if (arrayList.contains(Integer.valueOf(i2))) {
                        arrayList2.add(Integer.valueOf(intValue));
                    } else {
                        arrayList.add(Integer.valueOf(i2));
                    }
                }
            }
        }
        for (Integer num : arrayList2) {
            if (treeMap.containsKey(num)) {
                treeMap.remove(num);
            }
        }
        if (treeMap.size() != 0) {
            LogUtil.c("Track_CommonUtil", "The validate paceMap", treeMap.toString());
        }
        return treeMap;
    }

    private static float c(int i, Map<Integer, Float> map, List<Integer> list) {
        int i2;
        float f = 0.0f;
        for (int i3 = 0; i3 < 5 && (i2 = i + i3) < list.size() && map.get(list.get(i2)) != null; i3++) {
            f += map.get(list.get(i2)).floatValue();
        }
        return f;
    }

    public static void a(Map<Integer, Float> map, Map<Integer, Float> map2, hjw hjwVar) {
        if (map == null || map.isEmpty()) {
            LogUtil.h("Track_CommonUtil", "dealBikeTrackSpeedList validPaceMap is empty.");
            return;
        }
        if (map2 == null || map2.isEmpty()) {
            LogUtil.h("Track_CommonUtil", "dealBikeTrackSpeedList speedMap is empty.");
            return;
        }
        if (a(hjwVar)) {
            ArrayList arrayList = new ArrayList(16);
            TreeMap treeMap = new TreeMap();
            Iterator<Map.Entry<Integer, Float>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getKey());
            }
            int i = 0;
            int i2 = 0;
            for (Map.Entry<Integer, Float> entry : map2.entrySet()) {
                if (i2 == map.size() - 1 && !map.containsKey(entry.getKey())) {
                    map.put(entry.getKey(), entry.getValue());
                }
                i2++;
            }
            int size = map.size() / 5;
            int i3 = 0;
            while (i < size * 5) {
                treeMap.put((Integer) arrayList.get(i3), Float.valueOf(c(i, map, arrayList) / 5.0f));
                i += 5;
                i3++;
            }
            map.clear();
            map.putAll(treeMap);
        }
    }

    public static boolean a(hjw hjwVar) {
        if (hjwVar != null) {
            return hjwVar.e().requestSportType() == 259 && ((double) hjwVar.e().requestTotalDistance()) / 1000.0d >= 10.0d;
        }
        LogUtil.h("Track_CommonUtil", "isBikeTrackAndMoreThanTen trackDetailDataManager is null");
        return false;
    }

    public static final Float[] e(Map<Integer, Float> map) {
        float f;
        Float[] fArr = new Float[2];
        float f2 = 0.0f;
        if (map != null) {
            Iterator<Map.Entry<Integer, Float>> it = map.entrySet().iterator();
            boolean z = false;
            f = 0.0f;
            while (it.hasNext()) {
                float floatValue = it.next().getValue().floatValue();
                if (!z) {
                    z = true;
                    f2 = floatValue;
                    f = f2;
                }
                if (floatValue > f) {
                    f = floatValue;
                }
                if (floatValue < f2) {
                    f2 = floatValue;
                }
            }
        } else {
            f = 0.0f;
        }
        fArr[0] = Float.valueOf(f2);
        fArr[1] = Float.valueOf(f);
        return fArr;
    }

    public static final int c(ArrayList<HeartRateData> arrayList) {
        int i = 0;
        if (!koq.c(arrayList)) {
            return 0;
        }
        Iterator<HeartRateData> it = arrayList.iterator();
        while (it.hasNext()) {
            i += it.next().acquireHeartRate();
        }
        return i / arrayList.size();
    }

    public static final int d(ArrayList<HeartRateData> arrayList) {
        int i = 0;
        if (arrayList != null) {
            Iterator<HeartRateData> it = arrayList.iterator();
            while (it.hasNext()) {
                HeartRateData next = it.next();
                if (next.acquireHeartRate() > i) {
                    i = next.acquireHeartRate();
                }
            }
        }
        return i;
    }

    public static final int d(Context context, float f) {
        if (context == null) {
            LogUtil.a("Track_CommonUtil", "dip2px ", "context is null");
            return 0;
        }
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static final int b(Context context, float f) {
        if (context == null) {
            LogUtil.a("Track_CommonUtil", "px2dip ", "context is null");
            return 0;
        }
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static final void e(Context context, String str) {
        LogUtil.a("Track_CommonUtil", "sendDebugInfo ", str);
        Intent intent = new Intent("com.huawei.health.autotrack.debug");
        intent.putExtra("debug_info", str);
        if (context != null) {
            intent.setPackage(context.getPackageName());
            context.sendBroadcast(intent, d);
        }
    }

    public static final void c(Context context, String str) {
        if (context == null) {
            LogUtil.b("Track_CommonUtil", "removeTempFile context is null!");
            return;
        }
        File file = new File(context.getFilesDir(), str);
        if (file.exists()) {
            if (!file.delete()) {
                LogUtil.b("Track_CommonUtil", " delete motion path file fail!");
            } else {
                LogUtil.a("Track_CommonUtil", "remove file: ", str);
            }
        }
    }

    public static final int[] d(ArrayList<StepRateData> arrayList, long j) {
        int i;
        int i2;
        int i3;
        int[] iArr = new int[3];
        if (koq.b(arrayList)) {
            i = 0;
            i2 = 0;
            i3 = 0;
        } else {
            Iterator<StepRateData> it = arrayList.iterator();
            i2 = 0;
            i3 = 0;
            while (it.hasNext()) {
                StepRateData next = it.next();
                i2 += next.acquireStepRate();
                if (next.acquireStepRate() > i3) {
                    i3 = next.acquireStepRate();
                }
            }
            if (j > 0) {
                float f = ((i2 * 1000.0f) * 60.0f) / j;
                if (f > i3) {
                    i = i3;
                } else {
                    int i4 = i3;
                    i3 = (int) f;
                    i = i4;
                }
            } else {
                i = i3;
                i3 = 0;
            }
        }
        iArr[0] = i3;
        iArr[1] = i;
        iArr[2] = i2;
        return iArr;
    }

    public static final <T> T a(String str, TypeToken<T> typeToken) {
        Gson gson = new Gson();
        try {
            if (!TextUtils.isEmpty(str) && typeToken != null && typeToken.getType() != null) {
                return (T) gson.fromJson(str, typeToken.getType());
            }
        } catch (JsonSyntaxException unused) {
            LogUtil.a("Track_CommonUtil", "Gson to List error");
        }
        return null;
    }

    public static boolean d(Context context) {
        if (context == null) {
            LogUtil.a("Track_CommonUtil", "isOpenFreeIndoorRunning ", "context is null");
            return false;
        }
        String b = SharedPreferenceManager.b(context, Integer.toString(20002), "ihealthlabs");
        String e = jah.c().e("support_sport_lab");
        LogUtil.a("Track_CommonUtil", "SUPPORT_SPORT_LAB: ", e);
        if (e == null) {
            e = "HMA,LYA,EVR,BSA,VOG,DZH,ELE,NLE,AMZ,LIO,TAS,FRO,NEY,ANA,ELS,KUL,RAI,OCE,NOH,NOP,LAN";
        }
        if (!CommonUtil.c(e.split(",")) || nsn.ae(BaseApplication.getContext())) {
            return false;
        }
        return "true".equals(b);
    }

    public static boolean e() {
        String e;
        if (!d(BaseApplication.getContext()) || (e = jah.c().e("support_sport_lab")) == null) {
            return true;
        }
        String[] split = e.split(",");
        LogUtil.a("Track_CommonUtil", "closeRoad value", e);
        if (split.length == 0) {
            return true;
        }
        return "CLOSEREFRESH".equals(split[0]);
    }

    public static boolean c(Context context) {
        if (context == null) {
            LogUtil.a("Track_CommonUtil", "acquireAutoPauseEnableStatus ", "context is null");
            return false;
        }
        String b = SharedPreferenceManager.b(context, Integer.toString(20002), "auto_pause_enable_status");
        if (!TextUtils.isEmpty(b)) {
            return Boolean.parseBoolean(b);
        }
        LogUtil.h("Track_CommonUtil", "acquireAutoPauseEnableStatus you should set it before get");
        return false;
    }

    public static String e(Context context) {
        return context == null ? "--" : context.getString(R.string._2130849885_res_0x7f02305d);
    }

    public static boolean b(double d2, double d3) {
        return Math.abs(d2 - d3) < 1.0E-6d;
    }

    public static boolean b(double d2) {
        return Math.abs(d2) <= 1.0E-6d;
    }

    public static int b(Context context) {
        if (context == null) {
            return 0;
        }
        String b = SharedPreferenceManager.b(context, Integer.toString(20002), "sport_listen_type");
        if (TextUtils.isEmpty(b)) {
            return 0;
        }
        try {
            return Integer.parseInt(b);
        } catch (NumberFormatException unused) {
            LogUtil.b("Track_CommonUtil", "acquireSportListenerType NumberFormatException");
            return 0;
        }
    }

    public static void c(int i) {
        if (i == 257 || i == 258 || i == 259) {
            c();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0045, code lost:
    
        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("Track_CommonUtil", "printHiGeoCapability HiGeo Capability = ", java.lang.Integer.valueOf(health.compact.a.CommonUtil.h(r4[1])));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void c() {
        /*
            java.io.File r0 = new java.io.File
            java.lang.String r1 = defpackage.gvv.c
            r0.<init>(r1)
            boolean r1 = r0.exists()
            java.lang.String r2 = "Track_CommonUtil"
            if (r1 != 0) goto L19
            java.lang.String r0 = "printHiGeoCapability configFile is null"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.h(r2, r0)
            return
        L19:
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.io.IOException -> L84
            r1.<init>(r0)     // Catch: java.io.IOException -> L84
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L7a
            java.lang.String r3 = "UTF-8"
            r0.<init>(r1, r3)     // Catch: java.lang.Throwable -> L7a
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L70
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L70
        L2a:
            java.lang.String r4 = r3.readLine()     // Catch: java.lang.Throwable -> L66
            if (r4 == 0) goto L5c
            java.lang.String r5 = "higeo_enable_quickttff"
            boolean r5 = r4.contains(r5)     // Catch: java.lang.Throwable -> L66
            if (r5 == 0) goto L2a
            java.lang.String r5 = "="
            java.lang.String[] r4 = r4.split(r5)     // Catch: java.lang.Throwable -> L66
            if (r4 == 0) goto L2a
            int r5 = r4.length     // Catch: java.lang.Throwable -> L66
            r6 = 2
            if (r5 == r6) goto L45
            goto L2a
        L45:
            r5 = 1
            r4 = r4[r5]     // Catch: java.lang.Throwable -> L66
            int r4 = health.compact.a.CommonUtil.h(r4)     // Catch: java.lang.Throwable -> L66
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> L66
            java.lang.String r7 = "printHiGeoCapability HiGeo Capability = "
            r8 = 0
            r6[r8] = r7     // Catch: java.lang.Throwable -> L66
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.Throwable -> L66
            r6[r5] = r4     // Catch: java.lang.Throwable -> L66
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r2, r6)     // Catch: java.lang.Throwable -> L66
        L5c:
            r3.close()     // Catch: java.lang.Throwable -> L70
            r0.close()     // Catch: java.lang.Throwable -> L7a
            r1.close()     // Catch: java.io.IOException -> L84
            goto L90
        L66:
            r4 = move-exception
            r3.close()     // Catch: java.lang.Throwable -> L6b
            goto L6f
        L6b:
            r3 = move-exception
            r4.addSuppressed(r3)     // Catch: java.lang.Throwable -> L70
        L6f:
            throw r4     // Catch: java.lang.Throwable -> L70
        L70:
            r3 = move-exception
            r0.close()     // Catch: java.lang.Throwable -> L75
            goto L79
        L75:
            r0 = move-exception
            r3.addSuppressed(r0)     // Catch: java.lang.Throwable -> L7a
        L79:
            throw r3     // Catch: java.lang.Throwable -> L7a
        L7a:
            r0 = move-exception
            r1.close()     // Catch: java.lang.Throwable -> L7f
            goto L83
        L7f:
            r1 = move-exception
            r0.addSuppressed(r1)     // Catch: java.io.IOException -> L84
        L83:
            throw r0     // Catch: java.io.IOException -> L84
        L84:
            r0 = move-exception
            java.lang.String r0 = health.compact.a.LogAnonymous.b(r0)
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.b(r2, r0)
        L90:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.gvv.c():void");
    }

    public static float a(Context context) {
        return nsn.f(cun.c().getDeviceSettingItemById(HwGetSetingItemConstants.RIM_SIZE, new gww(context, new StorageParams(), Integer.toString(20002)).u(), "Track_CommonUtil")) / 1000.0f;
    }

    public static String c(MotionPathSimplify motionPathSimplify) {
        return motionPathSimplify != null ? motionPathSimplify.getExtendDataString("sportMode", "") : "";
    }

    public static int e(Object obj) {
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public static boolean a() {
        return CommonUtil.bh() || lcu.c();
    }

    public static CharSequence c(long j, int i, int i2) {
        int[] c2 = dpg.c(j);
        int i3 = c2[0];
        int i4 = c2[1];
        int i5 = c2[2];
        Context context = BaseApplication.getContext();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        SpannableString awT_ = ffy.awT_(context, "\\d+.\\d+|\\d+", ffy.b(R.plurals._2130903379_res_0x7f030153, i4, UnitUtil.e(i4, 1, 0)), i, i2);
        if (i3 > 0) {
            spannableStringBuilder.append((CharSequence) ffy.awT_(context, "\\d+.\\d+|\\d+", ffy.b(R.plurals._2130903331_res_0x7f030123, i3, UnitUtil.e(i3, 1, 0)), i, i2));
            spannableStringBuilder.append((CharSequence) awT_);
        } else if (i4 > 0) {
            spannableStringBuilder.append((CharSequence) awT_);
        }
        String e = UnitUtil.e(i5, 1, 0);
        String b = ffy.b(R.plurals._2130903368_res_0x7f030148, i5, e);
        if (i3 == 0 && i4 == 0) {
            b = ffy.b(R.plurals._2130903370_res_0x7f03014a, i5, e);
        }
        spannableStringBuilder.append((CharSequence) ffy.awT_(context, "\\d+.\\d+|\\d+", b, i, i2));
        return spannableStringBuilder;
    }

    public static void b(final String str, final String str2, final HiDataReadResultListener hiDataReadResultListener) {
        if (hiDataReadResultListener == null) {
            LogUtil.h("Track_CommonUtil", "getSampleConfig listener is null");
            return;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            hiDataReadResultListener.onResult("getSampleConfig type " + str + " configId " + str2, -1, -1);
            return;
        }
        if (HandlerExecutor.c()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: gvx
                @Override // java.lang.Runnable
                public final void run() {
                    gvv.b(str, str2, hiDataReadResultListener);
                }
            });
            return;
        }
        HiSampleConfigKey.Builder builder = new HiSampleConfigKey.Builder();
        builder.a(String.valueOf(0));
        builder.e(String.valueOf(0));
        builder.b(str);
        builder.d(str2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new HiSampleConfigKey(builder));
        HiSampleConfigProcessOption hiSampleConfigProcessOption = new HiSampleConfigProcessOption();
        hiSampleConfigProcessOption.setSampleConfigKeyList(arrayList);
        HiHealthManager.d(com.huawei.haf.application.BaseApplication.e()).getSampleConfig(hiSampleConfigProcessOption, hiDataReadResultListener);
    }
}
