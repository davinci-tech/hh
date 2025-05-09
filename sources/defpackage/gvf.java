package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.text.format.DateFormat;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import health.compact.a.LogAnonymous;
import health.compact.a.UnitUtil;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class gvf {

    /* renamed from: a, reason: collision with root package name */
    private feh f12955a;
    private List<hjd> b;
    private MotionPath c;
    private MotionPathSimplify d;
    private Context e;
    private int g;

    public gvf(Context context, MotionPathSimplify motionPathSimplify, MotionPath motionPath, List<hjd> list) {
        this.g = 0;
        this.e = context;
        this.d = motionPathSimplify;
        this.g = motionPathSimplify.requestSportType();
        this.c = motionPath;
        this.b = list;
    }

    public void e() {
        this.f12955a = new feh();
        HashMap hashMap = new HashMap();
        c(hashMap);
        b(hashMap);
        o(hashMap);
        f(hashMap);
        k(hashMap);
        d(hashMap);
        h(hashMap);
        a(hashMap);
        j(hashMap);
        i(hashMap);
        n(hashMap);
        g(hashMap);
        l(hashMap);
        e(hashMap);
        this.f12955a.d(hashMap);
        this.f12955a.a(this.b);
    }

    private void e(Map<String, String> map) {
        String e;
        if (this.g == 222) {
            map.put("sport_type_title", this.e.getString(R.string.IDS_hw_sport_oal));
            map.put("sport_simple_language", gwg.e(this.d));
            map.put("waypoint_mileage_title", this.e.getString(R.string._2130846373_res_0x7f0222a5));
            int extendDataInt = this.d.getExtendDataInt("wayPointDistance");
            if (extendDataInt == 0) {
                e = this.e.getString(R.string._2130850262_res_0x7f0231d6);
            } else {
                e = hji.e(extendDataInt);
            }
            map.put("waypoint_mileage_value", e);
            String string = this.e.getResources().getString(R.string._2130844082_res_0x7f0219b2);
            if (UnitUtil.h()) {
                string = this.e.getResources().getString(R.string._2130841383_res_0x7f020f27);
            }
            map.put("waypoint_mileage_unit", string);
            map.put("step_unit", this.e.getResources().getQuantityString(R.plurals._2130903204_res_0x7f0300a4, this.d.requestTotalSteps()));
            long requestTotalTime = this.d.requestTotalTime();
            int[] c = dpg.c(requestTotalTime);
            int i = c[0];
            int i2 = c[1];
            int i3 = c[2];
            map.put("duration", String.valueOf(requestTotalTime));
            map.put("duration_unit_day", this.e.getResources().getQuantityString(R.plurals._2130903331_res_0x7f030123, i, ""));
            map.put("duration_unit_hour", this.e.getResources().getQuantityString(R.plurals._2130903379_res_0x7f030153, i2, ""));
            String quantityString = this.e.getResources().getQuantityString(R.plurals._2130903368_res_0x7f030148, i3, "");
            if (i == 0 && i2 == 0) {
                quantityString = this.e.getResources().getQuantityString(R.plurals._2130903370_res_0x7f03014a, i3, "");
            }
            map.put("duration_unit_min", quantityString);
            map.put("step_title", this.e.getString(R.string.IDS_settings_steps));
        }
    }

    private void l(Map<String, String> map) {
        int i = this.g;
        if (i == 266 || i == 262) {
            map.put("stroke", gwz.e(this.d.requestSportData() != null ? this.d.requestSportData().get("swim_stroke") : null, this.e));
            map.put("stroke_title", this.e.getResources().getString(R.string._2130839812_res_0x7f020904));
            float requestAvgPace = this.d.requestAvgPace();
            String string = this.e.getResources().getString(R.string._2130850262_res_0x7f0231d6);
            if (requestAvgPace > 0.0f) {
                string = hji.f(requestAvgPace);
            }
            map.put("pace_unit", gwz.b(this.e, this.g));
            map.put("pace", gwz.a(this.e, string));
        }
    }

    private void c(Map<String, String> map) {
        MotionPathSimplify motionPathSimplify;
        String string;
        if (this.e == null || (motionPathSimplify = this.d) == null) {
            return;
        }
        double requestTotalDistance = motionPathSimplify.requestTotalDistance() / 1000.0d;
        String string2 = this.e.getResources().getString(R.string._2130844082_res_0x7f0219b2);
        if (UnitUtil.h()) {
            requestTotalDistance = UnitUtil.e(requestTotalDistance, 3);
            string2 = this.e.getResources().getString(R.string._2130841383_res_0x7f020f27);
        }
        if (requestTotalDistance > 0.0d) {
            string = UnitUtil.e(requestTotalDistance, 1, 2);
        } else {
            string = this.e.getString(R.string._2130850262_res_0x7f0231d6);
        }
        int i = this.g;
        if (i == 262 || i == 266) {
            double requestTotalDistance2 = this.d.requestTotalDistance();
            if (UnitUtil.h()) {
                requestTotalDistance2 = UnitUtil.e(requestTotalDistance2, 2);
                string2 = this.e.getResources().getQuantityString(R.plurals._2130903227_res_0x7f0300bb, (int) Math.round(requestTotalDistance2));
            } else {
                string2 = this.e.getResources().getString(R.string._2130841568_res_0x7f020fe0);
            }
            string = requestTotalDistance2 <= 0.0d ? this.e.getString(R.string._2130850262_res_0x7f0231d6) : UnitUtil.e(requestTotalDistance2, 1, 0);
        }
        if (kxb.a(this.g)) {
            map.put("distance_title", this.e.getString(R.string._2130839891_res_0x7f020953));
        }
        map.put("distance_title_with_unit", gwz.a(this.e, this.g));
        map.put("distance", string);
        map.put("distance_unit", string2);
    }

    private void d(Map<String, String> map) {
        String str;
        if (this.g == 273) {
            String string = this.e.getString(R.string.IDS_settings_steps);
            String string2 = this.e.getString(R.string._2130843486_res_0x7f02175e);
            MotionPath motionPath = this.c;
            if (motionPath != null && motionPath.isValidCadenceData()) {
                int c = hji.c(this.d, this.c);
                String d = gwz.d(this.e, c);
                str = this.e.getResources().getQuantityString(R.plurals._2130903283_res_0x7f0300f3, c, "");
                map.put("cadence", d);
            } else {
                map.put("cadence", this.e.getString(R.string._2130850262_res_0x7f0231d6));
                str = null;
            }
            map.put("cadence_title", string2);
            map.put("cadence_unit", str);
            String d2 = gwz.d(this.e, this.d.requestTotalSteps());
            map.put("step_title", string);
            map.put("step", d2);
        }
    }

    private void h(Map<String, String> map) {
        String str;
        if (this.g == 274) {
            String string = this.e.getString(R.string._2130843495_res_0x7f021767);
            String string2 = this.e.getString(R.string._2130843496_res_0x7f021768);
            MotionPath motionPath = this.c;
            if (motionPath != null && motionPath.isValidPaddleData()) {
                int c = hji.c(this.d);
                String d = gwz.d(this.e, c);
                str = this.e.getResources().getQuantityString(R.plurals._2130903224_res_0x7f0300b8, c);
                map.put("paddle_frequency", d);
            } else {
                map.put("paddle_frequency", this.e.getString(R.string._2130850262_res_0x7f0231d6));
                str = null;
            }
            map.put("paddle_title", string2);
            map.put("paddle_unit", str);
            map.put("paddle", gwz.d(this.e, this.d.requestTotalSteps()));
            map.put("paddle_title", string);
        }
    }

    private void f(Map<String, String> map) {
        String string;
        String str;
        float requestAvgPace = this.d.requestAvgPace();
        if (UnitUtil.h()) {
            requestAvgPace = gwz.b(requestAvgPace, this.g);
        }
        if (requestAvgPace > 0.0f) {
            str = gwz.b(this.e, this.g);
            string = gvv.a(requestAvgPace);
        } else {
            string = this.e.getString(R.string._2130850262_res_0x7f0231d6);
            str = null;
        }
        String c = gwz.c(this.e, this.g);
        map.put("pace", string);
        map.put("pace_unit", str);
        map.put("pace_title_with_unit", c);
    }

    private void i(Map<String, String> map) {
        int i;
        if (this.g == 283) {
            String extendDataString = this.d.getExtendDataString("skipNum");
            try {
                i = Integer.parseInt(extendDataString);
            } catch (NumberFormatException e) {
                LogUtil.b("Track_ShareWatermarkDataController", LogAnonymous.b((Throwable) e));
                i = 0;
            }
            if (i > 0) {
                map.put("rope_skipping_numbers", extendDataString);
            } else {
                map.put("rope_skipping_numbers", this.e.getString(R.string._2130850262_res_0x7f0231d6));
            }
            map.put("rope_skipping_numbers_unit", this.e.getResources().getQuantityString(R.plurals._2130903274_res_0x7f0300ea, i, ""));
            if (this.d.getExtendDataInt("skipSpeed") <= 0) {
                map.put("rope_skipping_frequency", this.e.getString(R.string._2130850262_res_0x7f0231d6));
            } else {
                map.put("rope_skipping_frequency", this.d.getExtendDataString("skipSpeed"));
            }
            map.put("rope_skipping_frequency_unit", this.e.getResources().getString(R.string._2130843710_res_0x7f02183e));
            map.put("rope_skipping_frequency_title", this.e.getResources().getString(R.string._2130843486_res_0x7f02175e));
            map.put("rope_skipping_frequency_title_with_unit", this.e.getResources().getString(R.string._2130843711_res_0x7f02183f));
            map.put("rope_skipping_numbers_title", this.e.getResources().getString(R.string._2130843714_res_0x7f021842));
            if (this.d.requestSportDataSource() == 5) {
                String b = b();
                if (TextUtils.isEmpty(b)) {
                    return;
                }
                map.put("sport_source", b);
            }
        }
    }

    private String b() {
        SharedPreferences sharedPreferences = BaseApplication.getContext().getSharedPreferences(PluginPayAdapter.KEY_DEVICE_INFO_NAME, 0);
        return sharedPreferences != null ? sharedPreferences.getString("device_name_key", "") : "";
    }

    private void j(Map<String, String> map) {
        int i = this.g;
        if (i == 263 || i == 220) {
            map.put("swings", this.d.requestExtendDataMap().get("golfSwingCount"));
            e(map, this.d);
            map.put("swings_title", this.e.getString(R.string._2130843676_res_0x7f02181c));
            d(map, this.d);
            map.put("swing_tempo_title", this.e.getString(R.string._2130843677_res_0x7f02181d));
            b(map, this.d);
            map.put("swing_speed_unit", gwz.d(this.e));
            if (UnitUtil.h()) {
                map.put("swing_speed_title_with_unit", this.e.getString(R.string._2130844122_res_0x7f0219da));
            } else {
                map.put("swing_speed_title_with_unit", this.e.getString(R.string._2130844121_res_0x7f0219d9));
            }
        }
    }

    private void e(Map<String, String> map, MotionPathSimplify motionPathSimplify) {
        double extendDataDouble = motionPathSimplify.getExtendDataDouble("golfSwingTempo");
        if (extendDataDouble != -1.0d) {
            map.put("swing_tempo", gwz.a(this.e, UnitUtil.e((extendDataDouble / 100.0d) * 1.0d, 1, 1)));
        } else {
            map.put("swing_tempo", this.e.getString(R.string._2130850262_res_0x7f0231d6));
        }
    }

    private void b(Map<String, String> map, MotionPathSimplify motionPathSimplify) {
        double extendDataDouble = motionPathSimplify.getExtendDataDouble("golfSwingSpeed");
        if (extendDataDouble != -1.0d) {
            String a2 = gwz.a(this.e, UnitUtil.e((extendDataDouble / 100.0d) * 1.0d, 1, 1));
            map.put("swing_speed_title", this.e.getString(R.string._2130843678_res_0x7f02181e));
            d(map, extendDataDouble, a2);
            return;
        }
        map.put("swing_speed", this.e.getString(R.string._2130850262_res_0x7f0231d6));
    }

    private void d(Map<String, String> map, double d, String str) {
        if (UnitUtil.h()) {
            map.put("swing_speed", gwz.a(this.e, UnitUtil.e(UnitUtil.e((d / 100.0d) * 3.5999999046325684d, 3), 1, 1)));
        } else {
            map.put("swing_speed", str);
        }
    }

    private void d(Map<String, String> map, MotionPathSimplify motionPathSimplify) {
        int extendDataInt = motionPathSimplify.getExtendDataInt("golfSwingCount");
        if (extendDataInt != -1) {
            map.put("swings_unit", this.e.getResources().getQuantityString(R.plurals._2130903269_res_0x7f0300e5, extendDataInt, ""));
        } else {
            map.put("swings_unit", this.e.getString(R.string._2130850262_res_0x7f0231d6));
        }
    }

    private void a(Map<String, String> map) {
        String str;
        String str2;
        String str3;
        String str4;
        String e;
        if (this.g == 271) {
            MotionPathSimplify motionPathSimplify = this.d;
            if (motionPathSimplify == null || motionPathSimplify.requestSportData() == null) {
                str = null;
                str2 = null;
            } else {
                String e2 = this.d.requestSportData().containsKey("overall_score") ? UnitUtil.e(this.d.requestSportData().get("overall_score").intValue(), 1, 0) : null;
                if (this.d.requestSportData().containsKey("jump_times")) {
                    str = e2;
                    str2 = UnitUtil.e(this.d.requestSportData().get("jump_times").intValue(), 1, 0);
                } else {
                    str = e2;
                    str2 = null;
                }
            }
            MotionPath motionPath = this.c;
            if (motionPath == null || !koq.c(motionPath.requestJumpDataList())) {
                str3 = null;
                str4 = null;
            } else {
                if (UnitUtil.h()) {
                    e = UnitUtil.e((int) UnitUtil.e(hji.d(this.c.requestJumpDataList()), 0), 1, 0);
                } else {
                    e = UnitUtil.e(hji.d(this.c.requestJumpDataList()), 1, 0);
                }
                str4 = UnitUtil.e(hji.b(this.c.requestJumpDataList()), 1, 0);
                str3 = e;
            }
            a(map, str, str2, str3, str4);
        }
    }

    private void a(Map<String, String> map, String str, String str2, String str3, String str4) {
        String a2 = gwz.a(this.e, str);
        String a3 = gwz.a(this.e, str2);
        String a4 = gwz.a(this.e, str3);
        String a5 = gwz.a(this.e, str4);
        map.put("show_score", a2);
        map.put("show_score_title", this.e.getResources().getString(R.string._2130843175_res_0x7f021627));
        map.put("show_score_unit", this.e.getResources().getString(R.string._2130849054_res_0x7f022d1e));
        map.put("jumps_times_title", this.e.getResources().getString(R.string._2130843162_res_0x7f02161a));
        map.put("jump_times_unit", this.e.getResources().getString(R.string._2130841409_res_0x7f020f41));
        map.put("jump_times", a3);
        map.put("jump_height", a4);
        map.put("jump_height_title", this.e.getResources().getString(R.string._2130843146_res_0x7f02160a));
        if (UnitUtil.h()) {
            map.put("jump_height_unit", this.e.getResources().getString(R.string._2130841897_res_0x7f021129));
        } else {
            map.put("jump_height_unit", this.e.getResources().getString(R.string._2130841416_res_0x7f020f48));
        }
        map.put("hang_time", a5);
        map.put("hang_time_unit", this.e.getResources().getString(R.string._2130842713_res_0x7f021459));
        map.put("hang_time_title", this.e.getResources().getString(R.string._2130843150_res_0x7f02160e));
    }

    private void k(Map<String, String> map) {
        String string;
        float requestAvgPace = this.d.requestAvgPace();
        String string2 = this.e.getResources().getString(R.string._2130844078_res_0x7f0219ae);
        String string3 = this.e.getResources().getString(R.string._2130839826_res_0x7f020912);
        if (UnitUtil.h()) {
            string2 = this.e.getResources().getString(R.string._2130844079_res_0x7f0219af);
            string3 = this.e.getResources().getString(R.string._2130839825_res_0x7f020911);
        }
        if (hji.g(this.g)) {
            if (requestAvgPace > 0.0f) {
                string = hji.g(requestAvgPace);
            } else {
                string = this.e.getString(R.string._2130850262_res_0x7f0231d6);
            }
            map.put("speed", string);
            map.put("speed_unit", string2);
            map.put("speed_title_with_unit", string3);
        }
    }

    private void o(Map<String, String> map) {
        String b = hpx.b(this.d);
        if (TextUtils.isEmpty(b)) {
            int requestDeviceType = this.d.requestDeviceType();
            Context context = this.e;
            b = cwa.a(requestDeviceType, context, context.getPackageName(), this.d.requestProductId());
        }
        String format = new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "Md")).format(Long.valueOf(this.d.requestStartTime()));
        String e = gwg.e(this.e, this.g);
        if (gwg.a(this.d)) {
            e = this.e.getString(R.string._2130845268_res_0x7f021e54);
        }
        map.put("sport_source", b);
        map.put("sport_date", format);
        map.put("sport_type", e);
    }

    private void b(Map<String, String> map) {
        long requestTotalTime;
        String d;
        if (this.d.requestSportDataSource() != 2 && kxb.c(this.g)) {
            requestTotalTime = this.d.getExtendDataLong("skiTotalTime");
        } else {
            requestTotalTime = this.d.requestTotalTime();
        }
        if (requestTotalTime <= 0) {
            d = this.e.getString(R.string._2130850262_res_0x7f0231d6);
        } else {
            d = UnitUtil.d((int) TimeUnit.MILLISECONDS.toSeconds(requestTotalTime));
        }
        if (TextUtils.isEmpty(d)) {
            d = this.e.getString(R.string._2130850262_res_0x7f0231d6);
        }
        String string = this.e.getString(R.string._2130843876_res_0x7f0218e4);
        map.put("duration", d);
        map.put("duration_title", string);
    }

    private void g(Map<String, String> map) {
        String d;
        String string;
        if (kxb.a(this.g)) {
            String string2 = this.e.getString(R.string._2130839890_res_0x7f020952);
            long requestTotalTime = this.d.requestTotalTime();
            int requestSportDataSource = this.d.requestSportDataSource();
            float c = (float) ffw.c(this.c.requestRealTimeSpeedList());
            if (requestTotalTime <= 0) {
                d = this.e.getString(R.string._2130850262_res_0x7f0231d6);
            } else {
                d = UnitUtil.d((int) TimeUnit.MILLISECONDS.toSeconds(requestTotalTime));
            }
            if (TextUtils.isEmpty(d) || requestSportDataSource == 2) {
                d = this.e.getString(R.string._2130850262_res_0x7f0231d6);
            }
            String string3 = this.e.getString(R.string._2130842157_res_0x7f02122d);
            if (c > 0.0f) {
                if (UnitUtil.h()) {
                    string = UnitUtil.e(UnitUtil.e(c, 3), 1, 2);
                } else {
                    string = UnitUtil.e(c, 1, 2);
                }
            } else {
                string = this.e.getString(R.string._2130850262_res_0x7f0231d6);
            }
            map.put("skiing_time_value", d);
            map.put("skiing_time_title", string2);
            map.put("skiing_max_speed_title", string3);
            map.put("skiing_max_speed", string);
        }
    }

    private void n(Map<String, String> map) {
        String e = gwz.e(this.e, this.d.requestTotalCalories());
        String d = gwz.d(this.e, this.d.requestTotalSteps());
        String string = this.e.getString(R.string._2130839711_res_0x7f02089f);
        String string2 = this.g == 273 ? this.e.getString(R.string.IDS_settings_steps) : null;
        map.put("calorie", e);
        map.put("calorie_unit", string);
        map.put("step", d);
        map.put("step_title", string2);
        map.put("calorie_title", this.e.getString(R.string._2130847440_res_0x7f0226d0));
        map.put("calorie_title_with_unit", this.e.getString(R.string._2130847441_res_0x7f0226d1));
    }

    public feh a() {
        return this.f12955a;
    }

    public void e(String str) {
        Map<String, String> b;
        feh fehVar = this.f12955a;
        if (fehVar == null || (b = fehVar.b()) == null) {
            return;
        }
        b.put("sport_type", str);
    }

    public void b(String str) {
        if (this.f12955a == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Track_ShareWatermarkDataController", "srcPath in null");
            return;
        }
        try {
            FileUtils.d(new File(str), new File(fdv.e));
            this.f12955a.b(fdv.e);
        } catch (IOException unused) {
            LogUtil.b("Track_ShareWatermarkDataController", "copy file is fail in setWatermarkPath");
        }
    }
}
