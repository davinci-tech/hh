package defpackage;

import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.healthcloud.plugintrack.ui.viewholder.TrackMainFragmentShowType;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import health.compact.a.LogAnonymous;
import health.compact.a.UnitUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class hnf {
    public static Map<TrackMainFragmentShowType, hoj> blN_(Bundle bundle) {
        if (bundle == null) {
            LogUtil.b("TrackDataConstructUtils", "getShowItemValueMap(), sportData == null");
            return null;
        }
        HashMap hashMap = new HashMap(10);
        blK_(bundle, hashMap);
        hashMap.put(TrackMainFragmentShowType.HEART_RATE, g(p(a(bundle.getString(IndoorEquipManagerApi.KEY_HEART_RATE)))));
        hashMap.put(TrackMainFragmentShowType.CONTACT_TIME, k(p(blR_(bundle, "groundContactTime", 0))));
        hashMap.put(TrackMainFragmentShowType.GROUND_IMPACT, t(p(blR_(bundle, "mGroundImpactAcceleration", 0))));
        hashMap.put(TrackMainFragmentShowType.EVERSION_EXCURSION, s(p(blR_(bundle, "eversion", 0))));
        hashMap.put(TrackMainFragmentShowType.SWING_ANGLE, z(p(blR_(bundle, "swingAngle", 0))));
        hashMap.put(TrackMainFragmentShowType.JUMP_DURATION, v(p(blR_(bundle, "hangTime", 0))));
        hashMap.put(TrackMainFragmentShowType.GROUND_TO_AIR_RATIO, u(p(blR_(bundle, "flightRatio", 1))));
        hashMap.put(TrackMainFragmentShowType.GC_TIME_BALANCE, r(p(blR_(bundle, "GCTimeBalance", 1))));
        hashMap.put(TrackMainFragmentShowType.VERTICAL_OSCILLATION, ai(p(blR_(bundle, "verticalOscillation", 1))));
        hashMap.put(TrackMainFragmentShowType.VERTICAL_RATIO, ag(p(blS_(bundle, "mVerticalRatio", 1))));
        hashMap.put(TrackMainFragmentShowType.IMPACT_PEAK, x(p(blR_(bundle, "activePeak", 1))));
        hashMap.put(TrackMainFragmentShowType.AVERAGE_VERTICAL_IMPACT_RATE, n(p(blR_(bundle, "verticalLoadingRate", 0))));
        hashMap.put(TrackMainFragmentShowType.CADENCE, o(p(blR_(bundle, "cadenceData", 0))));
        hashMap.put(TrackMainFragmentShowType.MAX_SPEED, y(p(blR_(bundle, "maxSpeed", 2))));
        hashMap.put(TrackMainFragmentShowType.AVG_SPEED, l(p(blR_(bundle, "avgSpeed", 2))));
        hashMap.put(TrackMainFragmentShowType.POWER, ad(p(blR_(bundle, "power", 0))));
        return hashMap;
    }

    private static void blK_(Bundle bundle, Map<TrackMainFragmentShowType, hoj> map) {
        map.put(TrackMainFragmentShowType.TOTAL_TIME, ae(p(bundle.getString("duration"))));
        map.put(TrackMainFragmentShowType.TOTAL_DISTANCES, f(p(blO_(bundle))));
        map.put(TrackMainFragmentShowType.PACE, w(p(gvu.e(bundle.getString("pace")))));
        if (bundle.containsKey("dataFromWear")) {
            map.put(TrackMainFragmentShowType.SPEED, i(p(blR_(bundle, "speed", 2))));
        } else {
            map.put(TrackMainFragmentShowType.SPEED, i(p(blR_(bundle, "speed", 1))));
        }
        map.put(TrackMainFragmentShowType.CALORIE, ac(p(blL_(bundle, "calorie"))));
        map.put(TrackMainFragmentShowType.ACTIVE_CALORIE, m(p(blL_(bundle, HwExerciseConstants.JSON_NAME_ACTIVECALORIE))));
        map.put(TrackMainFragmentShowType.STEP_RATE, aa(p(blR_(bundle, "stepRate", 0))));
        map.put(TrackMainFragmentShowType.STEPS, ab(p(blR_(bundle, MedalConstants.EVENT_STEPS, 0))));
        map.put(TrackMainFragmentShowType.ALTITUDE, h(p(d(bundle.getString("altitude")))));
        map.put(TrackMainFragmentShowType.TOTAL_CLIMB, ah(p(b(bundle.getString("totalCreep")))));
        map.put(TrackMainFragmentShowType.TOTAL_DECLINE, af(p(c(bundle.getString(BleConstants.TOTAL_DESCENT)))));
    }

    public static hoj blM_(TrackMainFragmentShowType trackMainFragmentShowType, Bundle bundle) {
        hoj j;
        if (trackMainFragmentShowType == null || bundle == null) {
            return null;
        }
        switch (AnonymousClass5.f13266a[trackMainFragmentShowType.ordinal()]) {
            case 1:
                j = j(p(bundle.getString("countdownValue")));
                break;
            case 2:
                j = q(p(bundle.getString("countdownValue")));
                break;
            case 3:
                j = e(p(bundle.getString("countdownValue")));
                break;
            case 4:
                j = ah(p(b(bundle.getString("totalCreep"))));
                break;
            case 5:
                j = af(p(c(bundle.getString(BleConstants.TOTAL_DESCENT))));
                break;
            case 6:
                j = k(p(blR_(bundle, "groundContactTime", 0)));
                break;
            default:
                j = blQ_(bundle, trackMainFragmentShowType);
                break;
        }
        return j == null ? blP_(bundle, trackMainFragmentShowType) : j;
    }

    private static hoj blQ_(Bundle bundle, TrackMainFragmentShowType trackMainFragmentShowType) {
        switch (trackMainFragmentShowType) {
            case GROUND_IMPACT:
                return t(p(blR_(bundle, "mGroundImpactAcceleration", 0)));
            case EVERSION_EXCURSION:
                return s(p(blR_(bundle, "eversion", 0)));
            case SWING_ANGLE:
                return z(p(blR_(bundle, "swingAngle", 0)));
            case JUMP_DURATION:
                return v(p(blR_(bundle, "hangTime", 0)));
            case GROUND_TO_AIR_RATIO:
                return u(p(blR_(bundle, "flightRatio", 1)));
            case IMPACT_PEAK:
                return x(p(blR_(bundle, "activePeak", 1)));
            case AVERAGE_VERTICAL_IMPACT_RATE:
                return n(p(blR_(bundle, "verticalLoadingRate", 0)));
            case VERTICAL_RATIO:
                return ag(p(blS_(bundle, "mVerticalRatio", 1)));
            case VERTICAL_OSCILLATION:
                return ai(p(blR_(bundle, "verticalOscillation", 1)));
            case GC_TIME_BALANCE:
                return r(p(blR_(bundle, "GCTimeBalance", 1)));
            default:
                return null;
        }
    }

    private static hoj blP_(Bundle bundle, TrackMainFragmentShowType trackMainFragmentShowType) {
        switch (trackMainFragmentShowType) {
            case STEPS:
                return ab(p(blR_(bundle, MedalConstants.EVENT_STEPS, 0)));
            case ALTITUDE:
                return h(p(d(bundle.getString("altitude"))));
            case TOTAL_DISTANCES:
                return f(p(blO_(bundle)));
            case TOTAL_TIME:
                return ae(p(bundle.getString("duration")));
            case SPEED:
                if (bundle.containsKey("dataFromWear")) {
                    return i(p(blR_(bundle, "speed", 2)));
                }
                return i(p(blR_(bundle, "speed", 1)));
            case AVG_SPEED:
                return l(p(blR_(bundle, "avgSpeed", 2)));
            case MAX_SPEED:
                return y(p(blR_(bundle, "maxSpeed", 2)));
            case POWER:
                return ad(p(blR_(bundle, "power", 1)));
            case CALORIE:
                return ac(p(blL_(bundle, "calorie")));
            case ACTIVE_CALORIE:
                return m(p(blL_(bundle, HwExerciseConstants.JSON_NAME_ACTIVECALORIE)));
            case HEART_RATE:
                return g(p(a(bundle.getString(IndoorEquipManagerApi.KEY_HEART_RATE))));
            case STEP_RATE:
                return aa(p(blR_(bundle, "stepRate", 0)));
            case PACE:
                return w(p(gvu.e(bundle.getString("pace"))));
            case CADENCE:
                return o(p(blR_(bundle, "cadenceData", 0)));
            default:
                LogUtil.b("TrackDataConstructUtils", "not a default type");
                return null;
        }
    }

    private static String d(String str) {
        String e;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            double parseInt = Integer.parseInt(str) / 10.0d;
            if (UnitUtil.h()) {
                e = UnitUtil.e(UnitUtil.e(parseInt, 1), 1, 0);
            } else {
                e = UnitUtil.e(Double.parseDouble(String.valueOf(parseInt)), 1, 0);
            }
            return e;
        } catch (NumberFormatException e2) {
            LogUtil.b("TrackDataConstructUtils", LogAnonymous.b((Throwable) e2));
            return null;
        }
    }

    private static String b(String str) {
        String e;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            double parseInt = Integer.parseInt(str) / 10.0d;
            if (UnitUtil.h()) {
                e = UnitUtil.e(UnitUtil.e(parseInt, 1), 1, 0);
            } else {
                e = UnitUtil.e(Double.parseDouble(String.valueOf(parseInt)), 1, 0);
            }
            return e;
        } catch (NumberFormatException e2) {
            LogUtil.b("TrackDataConstructUtils", LogAnonymous.b((Throwable) e2));
            return null;
        }
    }

    private static String c(String str) {
        String e;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            double parseInt = Integer.parseInt(str) / 10.0d;
            if (UnitUtil.h()) {
                e = UnitUtil.e(UnitUtil.e(parseInt, 1), 1, 0);
            } else {
                e = UnitUtil.e(Double.parseDouble(String.valueOf(parseInt)), 1, 0);
            }
            return e;
        } catch (NumberFormatException e2) {
            LogUtil.b("TrackDataConstructUtils", LogAnonymous.b((Throwable) e2));
            return null;
        }
    }

    private static String a(String str) {
        if (!TextUtils.isEmpty(str)) {
            if ("0".equals(str) || str.equals(BaseApplication.getContext().getResources().getString(R.string._2130850262_res_0x7f0231d6))) {
                str = BaseApplication.getContext().getResources().getString(R.string._2130850262_res_0x7f0231d6);
            } else {
                try {
                    str = UnitUtil.e(Double.parseDouble(str), 1, 0);
                } catch (NumberFormatException e) {
                    LogUtil.b("TrackDataConstructUtils", "constructHeartRate NumberFormatException", e.getMessage());
                }
            }
        }
        return TextUtils.isEmpty(str) ? BaseApplication.getContext().getResources().getString(R.string._2130850262_res_0x7f0231d6) : str;
    }

    private static hoj v(String str) {
        return new hoj(BaseApplication.getContext().getResources().getString(R.string._2130843149_res_0x7f02160d), str, "");
    }

    private static hoj u(String str) {
        return new hoj(BaseApplication.getContext().getResources().getString(R.string._2130843723_res_0x7f02184b), str, "");
    }

    private static hoj o(String str) {
        return new hoj(BaseApplication.getContext().getResources().getString(R.string._2130843688_res_0x7f021828), str, "");
    }

    private static hoj ad(String str) {
        return new hoj(BaseApplication.getContext().getResources().getString(R.string._2130843491_res_0x7f021763), str, "");
    }

    private static hoj q(String str) {
        return new hoj(BaseApplication.getContext().getResources().getString(R.string._2130850265_res_0x7f0231d9), str, "");
    }

    private static hoj z(String str) {
        return new hoj(BaseApplication.getContext().getResources().getString(R.string._2130842758_res_0x7f021486), str, "");
    }

    private static hoj t(String str) {
        return new hoj(BaseApplication.getContext().getResources().getString(R.string._2130842715_res_0x7f02145b), str, "");
    }

    private static hoj k(String str) {
        return new hoj(BaseApplication.getContext().getResources().getString(R.string._2130842714_res_0x7f02145a), str, "");
    }

    private static hoj s(String str) {
        return new hoj(BaseApplication.getContext().getResources().getString(R.string._2130842760_res_0x7f021488), str, "");
    }

    private static hoj af(String str) {
        return new hoj(BaseApplication.getContext().getResources().getString(UnitUtil.h() ? R.string._2130843696_res_0x7f021830 : R.string._2130843695_res_0x7f02182f), str, "");
    }

    private static hoj ah(String str) {
        return new hoj(BaseApplication.getContext().getResources().getString(UnitUtil.h() ? R.string._2130843694_res_0x7f02182e : R.string._2130843693_res_0x7f02182d), str, "");
    }

    private static hoj h(String str) {
        return new hoj(BaseApplication.getContext().getResources().getString(UnitUtil.h() ? R.string._2130842550_res_0x7f0213b6 : R.string._2130842549_res_0x7f0213b5), str, "");
    }

    private static hoj ab(String str) {
        return new hoj(BaseApplication.getContext().getResources().getString(R.string.IDS_settings_steps), str, "");
    }

    private static hoj w(String str) {
        return new hoj(BaseApplication.getContext().getResources().getString(R.string._2130844083_res_0x7f0219b3), str, "");
    }

    private static hoj ae(String str) {
        return new hoj(BaseApplication.getContext().getResources().getString(R.string._2130843686_res_0x7f021826), str, "");
    }

    private static hoj m(String str) {
        return new hoj(BaseApplication.getContext().getResources().getString(R.string._2130847546_res_0x7f02273a), str, "");
    }

    private static hoj ac(String str) {
        return new hoj(nsf.h(R.string._2130847442_res_0x7f0226d2), str, "");
    }

    private static hoj x(String str) {
        return new hoj(BaseApplication.getContext().getResources().getString(R.string._2130845176_res_0x7f021df8), str, "");
    }

    private static hoj n(String str) {
        return new hoj(BaseApplication.getContext().getResources().getString(R.string._2130845219_res_0x7f021e23), str, "");
    }

    private static hoj ag(String str) {
        return new hoj(BaseApplication.getContext().getResources().getString(R.string._2130845218_res_0x7f021e22), str, "");
    }

    private static hoj ai(String str) {
        if (!BaseApplication.getContext().getResources().getString(R.string._2130850262_res_0x7f0231d6).equals(str) && UnitUtil.h()) {
            str = UnitUtil.e((float) UnitUtil.e(nsn.j(str), 0), 1, 1);
        }
        return new hoj(BaseApplication.getContext().getResources().getString(R.string._2130845168_res_0x7f021df0), str, "");
    }

    private static hoj r(String str) {
        return new hoj(BaseApplication.getContext().getResources().getString(R.string._2130845165_res_0x7f021ded), str, "");
    }

    private static String p(String str) {
        return TextUtils.isEmpty(str) ? BaseApplication.getContext().getResources().getString(R.string._2130850262_res_0x7f0231d6) : str;
    }

    private static String blO_(Bundle bundle) {
        if (bundle == null || bundle.getString("distance") == null) {
            return UnitUtil.e(0.0d, 1, 0);
        }
        try {
            return UnitUtil.e(Double.parseDouble(bundle.getString("distance")), 1, 2);
        } catch (NumberFormatException e) {
            LogUtil.b("TrackDataConstructUtils", "distance", e.getMessage());
            return "";
        }
    }

    private static String blR_(Bundle bundle, String str, int i) {
        String string = BaseApplication.getContext().getResources().getString(R.string._2130850262_res_0x7f0231d6);
        if (bundle == null || TextUtils.isEmpty(bundle.getString(str))) {
            return string;
        }
        String string2 = bundle.getString(str);
        try {
            return UnitUtil.e(Double.parseDouble(string2), 1, i);
        } catch (NumberFormatException e) {
            LogUtil.b("TrackDataConstructUtils", str, e.getMessage());
            return string2;
        }
    }

    private static String blS_(Bundle bundle, String str, int i) {
        String string = BaseApplication.getContext().getResources().getString(R.string._2130850262_res_0x7f0231d6);
        if (bundle == null || TextUtils.isEmpty(bundle.getString(str)) || bundle.getInt(str) == -101) {
            return string;
        }
        String string2 = bundle.getString(str);
        try {
            return UnitUtil.e(Double.parseDouble(string2), 2, i);
        } catch (NumberFormatException e) {
            LogUtil.b("TrackDataConstructUtils", str, e.getMessage());
            return string2;
        }
    }

    private static String blL_(Bundle bundle, String str) {
        if (bundle == null || bundle.getString(str) == null) {
            return nsf.h(R.string._2130850262_res_0x7f0231d6);
        }
        String e = UnitUtil.e(0.0d, 1, 0);
        try {
            return UnitUtil.e(Double.parseDouble(bundle.getString(str)), 1, 0);
        } catch (NumberFormatException e2) {
            LogUtil.b("TrackDataConstructUtils", str, e2.getMessage());
            return e;
        }
    }

    private static hoj g(String str) {
        return new hoj(BaseApplication.getContext().getResources().getString(R.string._2130841430_res_0x7f020f56), str, BaseApplication.getContext().getResources().getString(R.string.IDS_main_watch_heart_rate_unit_string));
    }

    private static hoj e(String str) {
        return new hoj(BaseApplication.getContext().getResources().getString(R.string._2130850263_res_0x7f0231d7), str, BaseApplication.getContext().getResources().getString(R.string._2130839711_res_0x7f02089f));
    }

    private static hoj j(String str) {
        if (UnitUtil.h()) {
            return new hoj(BaseApplication.getContext().getResources().getString(R.string._2130850264_res_0x7f0231d8), str, BaseApplication.getContext().getResources().getString(R.string._2130844081_res_0x7f0219b1));
        }
        return new hoj(BaseApplication.getContext().getResources().getString(R.string._2130850264_res_0x7f0231d8), str, BaseApplication.getContext().getResources().getString(R.string._2130844082_res_0x7f0219b2));
    }

    private static hoj aa(String str) {
        return new hoj(BaseApplication.getContext().getResources().getString(R.string._2130839754_res_0x7f0208ca), str, "");
    }

    private static hoj i(String str) {
        return new hoj(BaseApplication.getContext().getResources().getString(UnitUtil.h() ? R.string._2130839825_res_0x7f020911 : R.string._2130839826_res_0x7f020912), str, "");
    }

    private static hoj l(String str) {
        return new hoj(BaseApplication.getContext().getResources().getString(UnitUtil.h() ? R.string._2130840125_res_0x7f020a3d : R.string._2130840124_res_0x7f020a3c), str, "");
    }

    private static hoj y(String str) {
        return new hoj(BaseApplication.getContext().getResources().getString(UnitUtil.h() ? R.string._2130840128_res_0x7f020a40 : R.string._2130840127_res_0x7f020a3f), str, "");
    }

    private static hoj f(String str) {
        if (UnitUtil.h()) {
            return new hoj(BaseApplication.getContext().getResources().getString(R.string._2130844081_res_0x7f0219b1), str, "");
        }
        return new hoj(BaseApplication.getContext().getResources().getString(R.string._2130844082_res_0x7f0219b2), str, "");
    }
}
