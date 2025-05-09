package defpackage;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import com.huawei.hihealth.data.model.TrackSwimSegment;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes2.dex */
public class ur {
    private static final double[] b = {1.0d, 3.0d, 5.0d, 10.0d};

    private static boolean a(int i) {
        return i == 266 || i == 262;
    }

    public static double b(int i) {
        if (i == 1) {
            return 131.96d;
        }
        if (i == 3) {
            return 440.67d;
        }
        if (i != 5) {
            return i != 10 ? 0.0d : 1577.53d;
        }
        return 757.35d;
    }

    private static boolean e(int i) {
        return i == 259 || i == 265;
    }

    private static boolean g(int i) {
        return i == 264 || i == 258;
    }

    private static boolean h(int i) {
        return i == 257 || i == 282;
    }

    public static int a(us usVar) {
        if (usVar == null) {
            LogUtil.h("Track_AbnormalTrackUtil", "abnormalData is null ");
            return 0;
        }
        int g = d(usVar.c()) ? g(usVar) : 0;
        if (a(usVar.c())) {
            g = f(usVar);
        }
        if (e(usVar.c())) {
            g = d(usVar);
        }
        if (c(usVar.c()) && usVar.f() > 1000000) {
            LogUtil.a("Track_AbnormalTrackUtil", "distance, checkAbnormalTrack result is ", 2);
            return 2;
        }
        LogUtil.a("Track_AbnormalTrackUtil", "heckAbnormalTrack result is ", Integer.valueOf(g));
        return g;
    }

    private static float c(us usVar) {
        if (usVar.g() == 0) {
            return 0.0f;
        }
        float f = usVar.f() / usVar.g();
        LogUtil.a("Track_AbnormalTrackUtil", "stride is ", Float.valueOf(f));
        return f;
    }

    private static boolean b(float f, boolean z) {
        LogUtil.a("Track_AbnormalTrackUtil", "checkStepIsAbnormal, stride is ", Float.valueOf(f), " ,supportStep is ", Boolean.valueOf(z));
        return (Float.compare(f, 0.0f) == 0 && z) || f >= 3.0f;
    }

    private static boolean d(int i) {
        return g(i) || h(i) || i == 260;
    }

    private static boolean c(int i) {
        return g(i) || h(i) || i == 259 || i == 260;
    }

    private static int g(us usVar) {
        if (usVar.d() < 131.96d) {
            ReleaseLogUtil.e("Track_AbnormalTrackUtil", "avgPace ,checkAbnormalTrack result is ", 1);
            return 1;
        }
        int i = 0;
        if (usVar.j() != null && usVar.b() != null && !usVar.j().isEmpty() && !usVar.b().isEmpty()) {
            if (b(usVar)) {
                ReleaseLogUtil.e("Track_AbnormalTrackUtil", "AbnormalTrack break world record");
                return 1;
            }
            i = b(usVar, 0);
            if (i != 0) {
                ReleaseLogUtil.e("Track_AbnormalTrackUtil", "AbnormalTrack break world record Continuous");
                return i;
            }
        }
        Map<Integer, Float> b2 = b(usVar.b());
        if (b2.size() > 0) {
            LogUtil.a("Track_AbnormalTrackUtil", "validPaceMap abnormal is ", b2.toString());
            if (c(b2, 131.96f) > 0) {
                ReleaseLogUtil.e("Track_AbnormalTrackUtil", "pace, checkAbnormalTrack result is ", 1);
                return 1;
            }
        }
        return i;
    }

    public static boolean e(us usVar) {
        if (!d(usVar.c())) {
            ReleaseLogUtil.e("Track_AbnormalTrackUtil", "sportType no need to verify step ");
            return false;
        }
        if (!b(c(usVar), usVar.a())) {
            return false;
        }
        ReleaseLogUtil.e("Track_AbnormalTrackUtil", "step, getAbnormalStepTrack is true ");
        return true;
    }

    private static int f(us usVar) {
        LogUtil.a("Track_AbnormalTrackUtil", "getSportSwim start");
        int i = 0;
        if (usVar.f() == 0) {
            return 0;
        }
        long j = 0;
        if (usVar.h() == 0) {
            return 6;
        }
        if (usVar.h() < usVar.f() * 368.0d) {
            return 5;
        }
        List<TrackSwimSegment> e = usVar.e();
        if (koq.b(e)) {
            LogUtil.h("Track_AbnormalTrackUtil", "getSportSwim swimSegments is null");
            return 0;
        }
        Iterator<TrackSwimSegment> it = e.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            i += it.next().requestDistance();
            j += r0.requestDuration() * 1000;
            i2 = e(i, j);
            if (i2 > 0) {
                break;
            }
        }
        LogUtil.a("Track_AbnormalTrackUtil", "getSportSwim end result", Integer.valueOf(i2));
        return i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0058 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0056 A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int e(int r2, long r3) {
        /*
            r0 = 50
            if (r2 == r0) goto L4c
            r0 = 100
            if (r2 == r0) goto L41
            r0 = 200(0xc8, float:2.8E-43)
            if (r2 == r0) goto L36
            r0 = 400(0x190, float:5.6E-43)
            if (r2 == r0) goto L2b
            r0 = 800(0x320, float:1.121E-42)
            if (r2 == r0) goto L20
            java.lang.String r2 = " acquireSportSwim is default"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            java.lang.String r3 = "Track_AbnormalTrackUtil"
            com.huawei.hwlogsmodel.LogUtil.h(r3, r2)
            goto L58
        L20:
            double r2 = (double) r3
            r0 = 4689955853162250240(0x4116120000000000, double:361600.0)
            int r2 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r2 >= 0) goto L58
            goto L56
        L2b:
            double r2 = (double) r3
            r0 = 4685287326790713344(0x41057c0000000000, double:176000.0)
            int r2 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r2 >= 0) goto L58
            goto L56
        L36:
            double r2 = (double) r3
            r0 = 4680343922512232448(0x40f3ec0000000000, double:81600.0)
            int r2 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r2 >= 0) goto L58
            goto L56
        L41:
            double r2 = (double) r3
            r0 = 4675300572626786714(0x40e201199999999a, double:36872.8)
            int r2 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r2 >= 0) goto L58
            goto L56
        L4c:
            double r2 = (double) r3
            r0 = 4670061729573922407(0x40cf646666666667, double:16072.800000000001)
            int r2 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r2 >= 0) goto L58
        L56:
            r2 = 5
            goto L59
        L58:
            r2 = 0
        L59:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ur.e(int, long):int");
    }

    private static int d(us usVar) {
        if (usVar.c() == 265 && usVar.f() == 0) {
            ReleaseLogUtil.e("Track_AbnormalTrackUtil", "totalDistance, totalDistance is 0");
            return 0;
        }
        if (usVar.h() == 0) {
            return 6;
        }
        if (usVar.d() < 50.0f) {
            return 5;
        }
        if (usVar.b() != null && !usVar.b().isEmpty()) {
            Map<Integer, Float> b2 = b(usVar.b());
            if (b2.size() != 0 && c(b2, 50.0f) > 0) {
                ReleaseLogUtil.e("Track_AbnormalTrackUtil", "pace, checkAbnormalTrack result is ", 1);
                return 1;
            }
        }
        return 0;
    }

    public static int c(Map<Integer, Float> map, float f) {
        if (map == null) {
            return 1;
        }
        Iterator<Map.Entry<Integer, Float>> it = map.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().getValue().floatValue() < f) {
                i++;
            }
        }
        return i;
    }

    public static Map<Integer, Float> b(Map<Integer, Float> map) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        TreeMap treeMap = new TreeMap();
        if (map == null) {
            LogUtil.c("Track_AbnormalTrackUtil", "paceMap is null");
            return treeMap;
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
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            treeMap.remove((Integer) it.next());
        }
        return treeMap;
    }

    public static boolean d(Context context) {
        SensorManager sensorManager;
        if (context != null && (context.getSystemService("sensor") instanceof SensorManager) && (sensorManager = (SensorManager) context.getSystemService("sensor")) != null && sensorManager.getDefaultSensor(19) != null) {
            boolean b2 = b();
            LogUtil.a("Track_AbnormalTrackUtil", "supportStandStepCounter ", Boolean.valueOf(b2));
            return b2;
        }
        LogUtil.a("supportStandStepCounter", "supportStandStepCounter false");
        return false;
    }

    private static boolean b() {
        return CommonUtil.bd() || Build.VERSION.SDK_INT < 29 || ActivityCompat.checkSelfPermission(BaseApplication.getContext(), "android.permission.ACTIVITY_RECOGNITION") == 0;
    }

    private static boolean b(us usVar) {
        double f = usVar.f();
        if (f >= 42195.0d) {
            return a(usVar, 42.0d) < 7377.0d;
        }
        if (f >= 21097.5d) {
            return a(usVar, 21.0d) < 3480.0d;
        }
        if (f >= 10000.0d) {
            return a(usVar, 10.0d) < 1577.53d;
        }
        if (f >= 5000.0d) {
            return a(usVar, 5.0d) < 757.35d;
        }
        if (f >= 3000.0d) {
            return a(usVar, 3.0d) < 440.67d;
        }
        if (f >= 1000.0d) {
            return a(usVar, 1.0d) < 131.96d;
        }
        LogUtil.h("Track_AbnormalTrackUtil", "abnormalTrack distance less than 1km or it is abnormal");
        return false;
    }

    private static int b(us usVar, int i) {
        int f = usVar.f() / 1000;
        Map<Double, Double> j = usVar.j();
        int i2 = i;
        for (double d : b) {
            if (f < d) {
                return i2;
            }
            i2 = d(i2, f, j, d);
        }
        return i2;
    }

    private static int d(int i, long j, Map<Double, Double> map, double d) {
        double b2 = b((int) d);
        int i2 = 0;
        int i3 = i;
        while (true) {
            double d2 = i2;
            if (d2 > j - d) {
                return i3;
            }
            double d3 = d2 + d;
            if (map.size() >= d3) {
                if (i2 == 0) {
                    if (map.get(Double.valueOf(d)) == null) {
                        LogUtil.h("Track_AbnormalTrackUtil", "partTime.get(partTimeKey) is null", Double.valueOf(d));
                    } else if (map.get(Double.valueOf(d)).doubleValue() < b2) {
                        ReleaseLogUtil.d("Track_AbnormalTrackUtil", "error time1", map.get(Double.valueOf(d)));
                        i3 = 5;
                    }
                } else if (map.get(Double.valueOf(d3)) == null || map.get(Double.valueOf(d2)) == null) {
                    LogUtil.h("Track_AbnormalTrackUtil", "partTime.get(i + partTimeKey) is null");
                } else if (map.get(Double.valueOf(d3)).doubleValue() - map.get(Double.valueOf(d2)).doubleValue() < b2) {
                    ReleaseLogUtil.d("Track_AbnormalTrackUtil", "AbnormalTrackForEachDistance", map.get(Double.valueOf(d2)), "add", map.get(Double.valueOf(d3)));
                    i3 = 5;
                }
            }
            i2++;
        }
    }

    private static double a(us usVar, double d) {
        Map<Double, Double> j = usVar.j();
        if (j == null || d > j.size() || j.get(Double.valueOf(d)) == null) {
            return 0.0d;
        }
        return j.get(Double.valueOf(d)).doubleValue();
    }
}
