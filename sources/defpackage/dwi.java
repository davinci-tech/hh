package defpackage;

import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import health.compact.a.CommonUtil;
import health.compact.a.HEXUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public class dwi {
    public static final Set<Integer> d = Collections.unmodifiableSet(new HashSet<Integer>() { // from class: dwi.5
        private static final long serialVersionUID = 4413638449915745765L;

        {
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_WALK_OR_RUN.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_SPEED.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_STEPS.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_STEP_RATE.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_STRIDE.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_FORE_STEPS.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_WHOLE_STEPS.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_HIND_STEPS.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_ACTIVE_PEAK.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_VERTICAL_LOADING_RATE.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_EVERSION.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_GROUND_CONTACT_TIME.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_HANG_TIME.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_FLIGHT_RATIO.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_SWING_ANGLE.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_VERTICAL_OSCILLATION.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_GC_TIME_BALANCE.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_CADENCE.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_DELTA_CIRCLE.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_SUM_CIRCLE.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_GROUND_IMPACT_ACCELERATION.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_VERTICAL_RATIO.value()));
        }
    });
    public static final Set<String> c = Collections.unmodifiableSet(new HashSet<String>() { // from class: dwi.3
        private static final long serialVersionUID = -2780575908466807560L;

        {
            add("walkOrRun");
            add("speed");
            add(MedalConstants.EVENT_STEPS);
            add("stepRate");
            add("stride");
            add("foreSteps");
            add("wholeSteps");
            add("hindSteps");
            add("activePeak");
            add("verticalLoadingRate");
            add("eversion");
            add("groundContactTime");
            add("hangTime");
            add("flightRatio");
            add("swingAngle");
            add("verticalOscillation");
            add("GCTimeBalance");
            add("cadence");
            add("deltaCircle");
            add("sumCircle");
            add("groundImpactAcceleration");
            add("verticalRatio");
        }
    });
    public static final Set<Integer> b = Collections.unmodifiableSet(new HashSet<Integer>() { // from class: dwi.1
        private static final long serialVersionUID = 303463443691344598L;

        {
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_ACTIVE_PEAK.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_VERTICAL_LOADING_RATE.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_VERTICAL_OSCILLATION.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_GC_TIME_BALANCE.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_VERTICAL_RATIO.value()));
        }
    });

    /* renamed from: a, reason: collision with root package name */
    public static final Set<Integer> f11864a = Collections.unmodifiableSet(new HashSet<Integer>() { // from class: dwi.2
        private static final long serialVersionUID = 4890156620666725262L;

        {
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_STRIDE.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_FORE_STEPS.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_WHOLE_STEPS.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_HIND_STEPS.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_EVERSION.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_GROUND_CONTACT_TIME.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_HANG_TIME.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_SWING_ANGLE.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_GROUND_IMPACT_ACCELERATION.value()));
        }
    });
    public static final Set<Integer> i = Collections.unmodifiableSet(new HashSet<Integer>() { // from class: dwi.4
        private static final long serialVersionUID = 831812500369210778L;

        {
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_WALK_OR_RUN.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_SPEED.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_STEPS.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_STEP_RATE.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_STRIDE.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_FORE_STEPS.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_WHOLE_STEPS.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_HIND_STEPS.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_ACTIVE_PEAK.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_VERTICAL_LOADING_RATE.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_EVERSION.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_GROUND_CONTACT_TIME.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_HANG_TIME.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_FLIGHT_RATIO.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_SWING_ANGLE.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_VERTICAL_OSCILLATION.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_GC_TIME_BALANCE.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_GROUND_IMPACT_ACCELERATION.value()));
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_VERTICAL_RATIO.value()));
        }
    });
    public static final Set<Integer> e = Collections.unmodifiableSet(new HashSet<Integer>() { // from class: dwi.9
        private static final long serialVersionUID = 4244797520969072913L;

        {
            add(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_FLIGHT_RATIO.value()));
        }
    });

    public static boolean a() {
        boolean c2 = cwi.c(cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "Posture_"), 175);
        ReleaseLogUtil.e("HWhealthLinkage_Posture_", "isSupportWatchRunPosture is ", Boolean.valueOf(c2));
        return c2;
    }

    public static Map<Integer, Double> c(List<cvv> list) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(16);
        if (koq.b(list)) {
            ReleaseLogUtil.d("HWhealthLinkage_Posture_", "parseRunPostureSamplePoints failed, SamplePointInfos is null");
            return linkedHashMap;
        }
        Iterator<cvv> it = list.iterator();
        while (it.hasNext()) {
            int a2 = (int) it.next().a();
            if (d.contains(Integer.valueOf(a2))) {
                linkedHashMap.put(Integer.valueOf(a2), Double.valueOf(e(a2, CommonUtil.w(HEXUtils.a(r1.d())))));
            }
        }
        return linkedHashMap;
    }

    public static Map<Integer, Double> e(Object obj) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(16);
        if (obj instanceof ffr) {
            return b((ffr) obj);
        }
        return obj instanceof ffo ? a((ffo) obj) : linkedHashMap;
    }

    private static Map<Integer, Double> a(ffo ffoVar) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(16);
        int b2 = ffoVar.b();
        ffoVar.c();
        if (b2 != -1) {
            linkedHashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_CADENCE.value()), Double.valueOf(e(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_CADENCE.value(), b2)));
        }
        return linkedHashMap;
    }

    private static Map<Integer, Double> b(ffr ffrVar) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(16);
        int c2 = ffrVar.c();
        if (c2 != -1) {
            linkedHashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_STEP_RATE.value()), Double.valueOf(e(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_STEP_RATE.value(), c2)));
        }
        int n = ffrVar.n();
        if (n != -1) {
            linkedHashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_STRIDE.value()), Double.valueOf(e(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_STRIDE.value(), n)));
        }
        int a2 = ffrVar.a();
        if (a2 != -1) {
            linkedHashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_GROUND_CONTACT_TIME.value()), Double.valueOf(e(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_GROUND_CONTACT_TIME.value(), a2)));
        }
        int g = ffrVar.g();
        if (g != -1) {
            linkedHashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_GROUND_IMPACT_ACCELERATION.value()), Double.valueOf(e(DicDataTypeUtil.DataType.REALTIME_GROUND_IMPACT_ACCELERATION.value(), g)));
        }
        int o = ffrVar.o();
        if (o != -1) {
            linkedHashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_SWING_ANGLE.value()), Double.valueOf(e(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_SWING_ANGLE.value(), o)));
        }
        int b2 = ffrVar.b();
        if (b2 != -1) {
            linkedHashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_FORE_STEPS.value()), Double.valueOf(e(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_FORE_STEPS.value(), b2)));
        }
        int m = ffrVar.m();
        if (m != -1) {
            linkedHashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_WHOLE_STEPS.value()), Double.valueOf(e(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_WHOLE_STEPS.value(), m)));
        }
        int i2 = ffrVar.i();
        if (i2 != -1) {
            linkedHashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_HIND_STEPS.value()), Double.valueOf(e(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_HIND_STEPS.value(), i2)));
        }
        int d2 = ffrVar.d();
        if (d2 != -101) {
            linkedHashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_EVERSION.value()), Double.valueOf(e(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_EVERSION.value(), d2)));
        }
        int j = ffrVar.j();
        if (j != -1) {
            linkedHashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_HANG_TIME.value()), Double.valueOf(e(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_HANG_TIME.value(), j)));
        }
        int h = ffrVar.h();
        if (h != -1) {
            linkedHashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_FLIGHT_RATIO.value()), Double.valueOf(e(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_FLIGHT_RATIO.value(), h)));
        }
        return linkedHashMap;
    }

    private static double e(int i2, double d2) {
        if (i2 == DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_ACTIVE_PEAK.value()) {
            LogUtil.c("Posture_", "activePeak: ", Double.valueOf(d2));
            return d2 / 10.0d;
        }
        if (i2 == DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_VERTICAL_LOADING_RATE.value()) {
            double doubleValue = new BigDecimal(d2 / 10.0d).setScale(1, 4).doubleValue();
            LogUtil.c("Posture_", "verticalLoadingRate: ", Double.valueOf(doubleValue));
            return doubleValue;
        }
        if (i2 == DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_VERTICAL_OSCILLATION.value()) {
            double doubleValue2 = new BigDecimal(d2 / 10.0d).setScale(1, 4).doubleValue();
            LogUtil.c("Posture_", "verticalOscillation: ", Double.valueOf(doubleValue2));
            return doubleValue2;
        }
        if (i2 == DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_GC_TIME_BALANCE.value()) {
            double doubleValue3 = new BigDecimal(d2 / 100.0d).setScale(1, 4).doubleValue();
            LogUtil.c("Posture_", "GCTimeBalance: ", Double.valueOf(doubleValue3));
            return doubleValue3;
        }
        if (i2 == DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_DELTA_CIRCLE.value()) {
            double d3 = d2 / 100.0d;
            LogUtil.c("Posture_", "deltaCircle: ", Double.valueOf(d3));
            return d3;
        }
        if (i2 != DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_SUM_CIRCLE.value()) {
            return d2;
        }
        double d4 = d2 / 100.0d;
        LogUtil.c("Posture_", "sumCircle: ", Double.valueOf(d4));
        return d4;
    }

    public static String e(Set<Integer> set) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(16);
        for (Integer num : set) {
            if (i.contains(num)) {
                linkedHashMap.put(num, 5000);
            } else {
                linkedHashMap.put(num, 1000);
            }
        }
        String e2 = HiJsonUtil.e(linkedHashMap);
        LogUtil.a("Posture_", "intervalValueJson: ", e2);
        return e2;
    }
}
