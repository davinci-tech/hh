package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.health.R;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwSportTypeInfo;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hms.network.embedded.g2;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessSportType;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ErrorCode;
import com.huawei.ui.main.R$string;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes7.dex */
public class rdu {
    private static final Map<Integer, Integer> e;

    private static boolean a(int i) {
        return i == 265 || i == 274 || i == 273;
    }

    public static int d(int i) {
        if (i != 257) {
            if (i != 258) {
                if (i != 262) {
                    if (i != 264) {
                        if (i != 266) {
                            if (i == 291) {
                                return 287;
                            }
                            switch (i) {
                                case 217:
                                case 218:
                                case 219:
                                    return 217;
                                case HeartRateThresholdConfig.HEART_RATE_LIMIT /* 220 */:
                                    return 286;
                                default:
                                    switch (i) {
                                        case OldToNewMotionPath.SPORT_TYPE_CROSS_COUNTRY_RACE /* 280 */:
                                            break;
                                        case 281:
                                        case 282:
                                            break;
                                        default:
                                            switch (i) {
                                                case 286:
                                                    return 286;
                                                case 287:
                                                case 288:
                                                case ComponentInfo.PluginPay_A_N /* 289 */:
                                                    return 287;
                                                default:
                                                    return i;
                                            }
                                    }
                                case FitnessSportType.HW_FITNESS_SPORT_ALL /* 221 */:
                                    return 258;
                            }
                        }
                    }
                }
                return 262;
            }
            return 258;
        }
        return 257;
    }

    static {
        HashMap hashMap = new HashMap();
        e = hashMap;
        hashMap.put(0, 199);
        hashMap.put(1, 199);
        hashMap.put(10004, 199);
        hashMap.put(32, 0);
        hashMap.put(30, 0);
        hashMap.put(23, 4);
        hashMap.put(35, 4);
        hashMap.put(21, 4);
        hashMap.put(22, 4);
        hashMap.put(39, 4);
        hashMap.put(44, 4);
        hashMap.put(45, 4);
        hashMap.put(42, 4);
        hashMap.put(47, 4);
        hashMap.put(81, 4);
        hashMap.put(83, 4);
        hashMap.put(90, 4);
        hashMap.put(91, 4);
        hashMap.put(77, 4);
        hashMap.put(78, 4);
        hashMap.put(36, 5);
        hashMap.put(41, 5);
        hashMap.put(37, 5);
        hashMap.put(46, 5);
        hashMap.put(51, 5);
        hashMap.put(61, 5);
        hashMap.put(62, 5);
        hashMap.put(79, 5);
        hashMap.put(80, 5);
        hashMap.put(88, 5);
        hashMap.put(43, 6);
        hashMap.put(38, 6);
        hashMap.put(436, 7);
        hashMap.put(437, 7);
        hashMap.put(438, 7);
        hashMap.put(439, 7);
        hashMap.put(440, 7);
        hashMap.put(441, 7);
        hashMap.put(Integer.valueOf(ErrorCode.ERROR_CODE_NO_USERINFO), 7);
        hashMap.put(Integer.valueOf(g2.n), 7);
        hashMap.put(Integer.valueOf(WorkoutRecord.COURSE_TYPE_YOGA), 7);
        hashMap.put(445, 7);
        hashMap.put(446, 7);
        hashMap.put(447, 7);
        hashMap.put(448, 7);
        hashMap.put(449, 7);
    }

    static int c(int i, String str) {
        int c;
        LogUtil.a("Track_TrackSportHistoryUtils", "sortDeviceCategory deviceType is ", Integer.valueOf(i));
        Map<Integer, Integer> map = e;
        if (map.containsKey(Integer.valueOf(i))) {
            c = map.get(Integer.valueOf(i)).intValue();
        } else {
            c = c("deviceId", String.valueOf(i));
        }
        return c == 0 ? c("smartProductId", str) : c;
    }

    private static int c(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return 0;
        }
        ProductMapParseUtil.b(BaseApplication.getContext());
        List<ProductMapInfo> b = ProductMap.b(str, str2);
        if (koq.b(b) || b.get(0) == null || b.get(0).e() == null) {
            LogUtil.h("Track_TrackSportHistoryUtils", "productInInfoList is null");
            return 0;
        }
        String e2 = b.get(0).e();
        LogUtil.a("Track_TrackSportHistoryUtils", "getDeviceProductName value ", str2, "productType ", e2);
        if ("06E".equals(e2)) {
            return 4;
        }
        if ("06D".equals(e2)) {
            return 5;
        }
        return "082".equals(e2) ? 6 : 0;
    }

    public static List<rdr> c(List<rdn> list, List<WorkoutRecord> list2, int i) {
        int size = list != null ? list.size() : 0;
        int size2 = list2 != null ? list2.size() : 0;
        LogUtil.a("Track_TrackSportHistoryUtils", "combineRecordListData trackListLength = ", Integer.valueOf(size), " fitnessList = ", Integer.valueOf(size2), " dataNum = ", Integer.valueOf(i));
        int min = i == 0 ? size + size2 : Math.min(size + size2, i);
        ArrayList arrayList = new ArrayList(min);
        int i2 = 0;
        int i3 = 0;
        while (i2 + i3 < min) {
            if (i2 >= size) {
                arrayList.add(new rdr(1, list2.get(i3)));
            } else {
                if (i3 >= size2) {
                    arrayList.add(new rdr(0, list.get(i2)));
                } else {
                    rdn rdnVar = list.get(i2);
                    WorkoutRecord workoutRecord = list2.get(i3);
                    if (rdnVar.k() > workoutRecord.startTime()) {
                        arrayList.add(new rdr(0, rdnVar));
                    } else {
                        arrayList.add(new rdr(1, workoutRecord));
                    }
                }
                i2++;
            }
            i3++;
        }
        LogUtil.a("Track_TrackSportHistoryUtils", "combineRecordListData result ListLength = ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    public static List<rdj> a(List<rdj> list, List<rdj> list2) {
        int i = 0;
        int d = d(list, 0, true);
        int d2 = d(list2, 0, false);
        ArrayList arrayList = new ArrayList(10);
        int i2 = 0;
        int i3 = 0;
        while (d(list, list2, i, i2)) {
            if (list.get(i).c() < list2.get(i2).c()) {
                arrayList.add(i3, list2.get(i2));
                i3++;
                i2++;
            } else {
                if (list.get(i).c() == list2.get(i2).c()) {
                    arrayList.add(i3, b(list, list2, i, i2));
                    i++;
                    i2++;
                } else {
                    arrayList.add(i3, list.get(i));
                    i++;
                }
                i3++;
            }
        }
        LogUtil.a("Track_TrackSportHistoryUtils", "temp all month data is ", Integer.valueOf(arrayList.size()));
        c(list2, d2, arrayList, c(list, d, arrayList, i3, i), i2);
        return arrayList;
    }

    private static boolean d(List<rdj> list, List<rdj> list2, int i, int i2) {
        if (koq.d(list, i)) {
            return koq.d(list2, i2);
        }
        return false;
    }

    private static int d(List<rdj> list, int i, boolean z) {
        if (list != null) {
            i = list.size();
            if (z) {
                LogUtil.a("Track_TrackSportHistoryUtils", "tempTrackMonthDataLength is ", Integer.valueOf(i));
            } else {
                LogUtil.a("Track_TrackSportHistoryUtils", "tempFitnessMonthDataLength is ", Integer.valueOf(i));
            }
        } else if (z) {
            LogUtil.a("Track_TrackSportHistoryUtils", "tempTrackMonthDataLength is null");
        } else {
            LogUtil.a("Track_TrackSportHistoryUtils", "FitnessTrackMonthDataLength is null");
        }
        return i;
    }

    private static rdj b(List<rdj> list, List<rdj> list2, int i, int i2) {
        if (koq.b(list, i) || koq.b(list2, i2)) {
            return new rdj();
        }
        rdj rdjVar = list.get(i);
        rdj rdjVar2 = list2.get(i2);
        HashMap hashMap = new HashMap();
        if (rdjVar.d() == null || rdjVar2.d() == null) {
            return new rdj();
        }
        hashMap.putAll(rdjVar.d());
        hashMap.putAll(rdjVar2.d());
        return new rdj(hashMap, rdjVar.b() + rdjVar2.b(), rdjVar.c());
    }

    private static int c(List<rdj> list, int i, List<rdj> list2, int i2, int i3) {
        if (list != null) {
            while (i > i3) {
                list2.add(i2, list.get(i3));
                i3++;
                i2++;
            }
        }
        return i2;
    }

    public static String c(int i, Context context) {
        if (context == null) {
            return null;
        }
        if (i == 0) {
            return context.getResources().getString(R$string.IDS_hwh_motiontrack_sport_data_all_exercise);
        }
        if (i != 220) {
            if (i == 262) {
                return context.getResources().getString(R$string.IDS_motiontrack_swim_tip);
            }
            if (i == 10001) {
                return context.getResources().getString(R$string.IDS_hwh_motiontrack_sport_fitness_course);
            }
            if (i != 286) {
                if (i != 287) {
                    switch (i) {
                        case 257:
                            return context.getResources().getString(R$string.IDS_motiontrack_walk_tip);
                        case 258:
                            return context.getResources().getString(R$string.IDS_motiontrack_run_tip);
                        case 259:
                            return context.getResources().getString(R$string.IDS_hwh_start_track_sport_type_cycling);
                        default:
                            return d(i, context);
                    }
                }
                return context.getResources().getString(R$string.IDS_diving_tip);
            }
        }
        return context.getResources().getString(R$string.IDS_motiontrack_golf_tip);
    }

    private static String a(int i, Context context) {
        if (i == 0) {
            return context.getResources().getString(R$string.IDS_FitnessAdvice_all_courses);
        }
        if (i != 137) {
            return i != 283 ? "" : context.getResources().getString(R$string.IDS_indoor_skipper_rope_sport_type);
        }
        return context.getResources().getString(R$string.IDS_start_fitness_type_yoga);
    }

    public static String d(int i, Context context) {
        if (context == null) {
            LogUtil.b("Track_TrackSportHistoryUtils", "getListSportTypeName context is null");
            return null;
        }
        if (hln.c(context).d(i) == null) {
            LogUtil.b("Track_TrackSportHistoryUtils", "getSportTypeInfoById is null :sportType", Integer.valueOf(i));
            return null;
        }
        try {
            return context.getResources().getString(gxz.b(hln.c(context).d(i).getSportTypeRes(), context));
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("Track_TrackSportHistoryUtils", "NotFoundException", Integer.valueOf(i));
            return "";
        }
    }

    public static int c(int i, List<Integer> list) {
        if (list == null || !list.contains(Integer.valueOf(i))) {
            return 0;
        }
        return list.indexOf(Integer.valueOf(i));
    }

    public static int d(int i, List<Integer> list) {
        if (koq.b(list, i)) {
            LogUtil.b("Track_TrackSportHistoryUtils", "sortSportType OutOfBounds");
            return 0;
        }
        return list.get(i).intValue();
    }

    public static String[] e(List<Integer> list, Context context) {
        if (context == null || koq.b(list)) {
            return new String[]{""};
        }
        ArrayList arrayList = new ArrayList(list);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(context.getResources().getString(R$string.IDS_hwh_motiontrack_sport_data_all_exercise));
        for (int i = 1; i < arrayList.size(); i++) {
            String c = c(((Integer) arrayList.get(i)).intValue(), context);
            if (((Integer) arrayList.get(i)).intValue() != 0 && c != null) {
                arrayList2.add(c);
            }
        }
        if (koq.b(arrayList2)) {
            return new String[]{""};
        }
        String[] strArr = new String[arrayList2.size()];
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            strArr[i2] = (String) arrayList2.get(i2);
        }
        return strArr;
    }

    public static Drawable dMy_(Context context, int i) {
        return dMz_(context, i, null);
    }

    public static Drawable dMz_(Context context, int i, String str) {
        hln c = hln.c(context);
        Drawable drawable = context.getDrawable(R.drawable.ic_health_list_outdoor_running);
        if (c.d(i) == null || c.d(i).getHistoryList() == null) {
            LogUtil.h("Track_TrackSportHistoryUtils", "getSportTypeInfoById =null sportType= ", Integer.valueOf(i));
            return drawable;
        }
        return c.d(i).getHistoryList().getItemDrawable(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:9:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int b(android.content.Context r3, int r4) {
        /*
            hln r0 = defpackage.hln.c(r3)
            com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwSportTypeInfo r1 = r0.d(r4)
            java.lang.String r2 = "Track_TrackSportHistoryUtils"
            if (r1 != 0) goto L1a
            java.lang.String r0 = "getSportTypeInfoById =null sportType= "
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            java.lang.Object[] r4 = new java.lang.Object[]{r0, r4}
            com.huawei.hwlogsmodel.LogUtil.h(r2, r4)
            goto L35
        L1a:
            com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwSportTypeInfo r1 = r0.d(r4)
            com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwHistoryListInfo r1 = r1.getHistoryList()
            if (r1 == 0) goto L35
            com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwSportTypeInfo r4 = r0.d(r4)
            com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwHistoryListInfo r4 = r4.getHistoryList()
            int r4 = r4.getItemBgColor()
            int r4 = b(r4, r3)
            goto L36
        L35:
            r4 = 0
        L36:
            if (r4 != 0) goto L48
            java.lang.String r4 = "acquireSportTypeBackgroundColor = 0"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            com.huawei.hwlogsmodel.LogUtil.h(r2, r4)
            r4 = 2131296651(0x7f09018b, float:1.8211225E38)
            int r4 = r3.getColor(r4)
        L48:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.rdu.b(android.content.Context, int):int");
    }

    private static int b(int i, Context context) {
        if (context == null) {
            LogUtil.h("Track_TrackSportHistoryUtils", "acquireBgColor  context is null");
            return 0;
        }
        if (i == 0) {
            return context.getColor(R.color._2131299143_res_0x7f090b47);
        }
        if (i == 1) {
            return context.getColor(R.color._2131296651_res_0x7f09018b);
        }
        if (i == 2) {
            return context.getColor(R.color._2131296795_res_0x7f09021b);
        }
        if (i == 3) {
            return context.getColor(R.color._2131298953_res_0x7f090a89);
        }
        if (i == 4) {
            return context.getColor(R.color._2131299162_res_0x7f090b5a);
        }
        if (i == 5) {
            return context.getColor(R.color._2131299144_res_0x7f090b48);
        }
        return context.getColor(R.color._2131296651_res_0x7f09018b);
    }

    public static String[] d(List<Integer> list, Context context) {
        if (context == null || koq.b(list)) {
            return new String[]{""};
        }
        ArrayList arrayList = new ArrayList(10);
        for (int i = 0; i < list.size(); i++) {
            String a2 = a(list.get(i).intValue(), context);
            if (!TextUtils.isEmpty(a2)) {
                arrayList.add(a2);
            }
        }
        if (koq.b(arrayList)) {
            return new String[]{""};
        }
        String[] strArr = new String[arrayList.size()];
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            strArr[i2] = (String) arrayList.get(i2);
        }
        return strArr;
    }

    public static String c(int i, int i2) {
        return "Track_" + i + i2;
    }

    public static int a(String str) {
        int m = CommonUtil.m(BaseApplication.getContext(), str.substring(6, str.length() - 1));
        if (String.valueOf(m).contains(String.valueOf(287))) {
            return 287;
        }
        if (CommonUtil.m(BaseApplication.getContext(), str.substring(str.length() - 1)) != 1) {
            return m;
        }
        LogUtil.b("Track_TrackSportHistoryUtils", "SportKey   =  1");
        return 0;
    }

    public static HashSet<Integer> c(List<String> list) {
        HashSet<Integer> hashSet = new HashSet<>();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            int d = d(a(it.next()));
            if (d != 0 && !hashSet.contains(Integer.valueOf(d))) {
                hashSet.add(Integer.valueOf(d));
            }
        }
        return hashSet;
    }

    public static void c(rdr rdrVar, Map<String, Double> map, long j, long j2, int i, IBaseResponseCallback iBaseResponseCallback) {
        e(rdrVar, 287, map);
        d(rdrVar, map, j, j2, i, iBaseResponseCallback);
    }

    public static Map<String, Double> b(rdr rdrVar, int i, Map<String, Double> map) {
        if (i == 262 && rdrVar.c() == 2) {
            a(rdrVar, e(rdrVar, i, map), map);
        } else if (rdrVar.b() == 0 && rdrVar.h() == 0) {
            if (i == 287) {
                a("Track_Duration_Sum", rdrVar.j(), map);
                return map;
            }
            List<String> e2 = e(rdrVar, i, map);
            if (i == 262) {
                a(rdrVar, e2, map);
            }
        }
        return map;
    }

    private static void a(String str, double d, Map<String, Double> map) {
        if (map == null || !map.containsKey(str) || map.get(str) == null) {
            return;
        }
        map.put(str, Double.valueOf(map.get(str).doubleValue() - d));
    }

    public static List<String> e(int i) {
        hln c = hln.c(BaseApplication.getContext());
        LogUtil.a("Track_TrackSportHistoryUtils", " mSportType= ", Integer.valueOf(i));
        ArrayList arrayList = new ArrayList();
        HwSportTypeInfo d = c.d(i);
        if (d != null) {
            return d.getHistoryList().getTrackRequestDataBase(i);
        }
        LogUtil.a("Track_TrackSportHistoryUtils", "static is wrong");
        return arrayList;
    }

    private static void d(rdr rdrVar, final Map<String, Double> map, long j, long j2, int i, final IBaseResponseCallback iBaseResponseCallback) {
        float k = rdrVar.k() / 1000.0f;
        if (map.get("Track_28721") != null) {
            map.put("Track_28721", Double.valueOf(map.get("Track_28721").doubleValue() - k));
        }
        final String str = "Track_28722";
        if (map.get("Track_28722") == null) {
            return;
        }
        if (map.get("Track_28722").doubleValue() == rdrVar.d() * 10.0f) {
            kor.a().b(j, j2, i, new IBaseResponseCallback() { // from class: rdv
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                public final void onResponse(int i2, Object obj) {
                    rdu.a(map, str, iBaseResponseCallback, i2, obj);
                }
            });
        } else if (iBaseResponseCallback != null) {
            iBaseResponseCallback.onResponse(0, map);
        }
    }

    static /* synthetic */ void a(Map map, String str, IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
        try {
            if ((obj instanceof Map) && !((Map) obj).isEmpty()) {
                Iterator it = ((Map) obj).entrySet().iterator();
                double d = 0.0d;
                while (it.hasNext()) {
                    d = Math.max(d, ((Double) ((Map.Entry) it.next()).getValue()).doubleValue());
                }
                map.put(str, Double.valueOf(d));
            } else {
                map.put(str, Double.valueOf(0.0d));
            }
        } catch (ClassCastException unused) {
            LogUtil.b("Track_TrackSportHistoryUtils", "deleteDiving classCastException");
            map.put(str, Double.valueOf(0.0d));
        }
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.onResponse(0, map);
        }
    }

    private static void a(rdr rdrVar, List<String> list, Map<String, Double> map) {
        if (koq.d(list, 3)) {
            String str = list.get(3);
            if (!map.containsKey(str) || map.get(str) == null) {
                return;
            }
            map.put(str, Double.valueOf(map.get(str).doubleValue() - rdrVar.k()));
        }
    }

    private static List<String> e(rdr rdrVar, int i, Map<String, Double> map) {
        List<String> e2 = e(i);
        if (rdrVar.h() != 1 && map != null && !map.isEmpty()) {
            if (rdrVar.c() == 0) {
                double d = rdrVar.d();
                a("Track_Distance_Sum", d, map);
                a("Track_" + i + 2, d, map);
            }
            if (b(rdrVar, i)) {
                double d2 = rdrVar.d();
                a("Track_Distance_Sum", d2, map);
                a("Track_" + i + 2, d2, map);
            }
            double k = rdrVar.k();
            a("Track_" + i + 4, k, map);
            a("Track_" + i + 3, rdrVar.a(), map);
            if (i != 10001) {
                a("Track_Duration_Sum", k, map);
                a("Track_Calorie_Sum", rdrVar.a(), map);
            }
        }
        return e2;
    }

    public static boolean b(rdr rdrVar, int i) {
        return (rdrVar.c() != 0 && a(i)) || rdrVar.c() == 4;
    }

    public static CopyOnWriteArrayList<Integer> c(List<Integer> list, Context context) {
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        if (list == null) {
            LogUtil.h("Track_TrackSportHistoryUtils", "filterSportType  sportTypeList is null");
            return copyOnWriteArrayList;
        }
        for (Integer num : list) {
            if (!copyOnWriteArrayList.contains(num) && d(num.intValue(), context) != null) {
                copyOnWriteArrayList.add(num);
            }
        }
        return copyOnWriteArrayList;
    }

    public static int d(CopyOnWriteArrayList<rdo> copyOnWriteArrayList, int i, int i2) {
        rdo rdoVar;
        rdr a2;
        if (koq.b(copyOnWriteArrayList, i) || copyOnWriteArrayList.get(i) == null || (rdoVar = copyOnWriteArrayList.get(i)) == null || rdoVar.a(i2) == null || (a2 = copyOnWriteArrayList.get(i).a(i2)) == null) {
            return 0;
        }
        int y = a2.y();
        if (a2.ae() == 2) {
            y = 6;
        }
        LogUtil.a("Track_TrackSportHistoryUtils", "TrackType :", Integer.valueOf(y));
        return y;
    }
}
