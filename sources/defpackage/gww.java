package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

/* loaded from: classes4.dex */
public class gww {

    /* renamed from: a, reason: collision with root package name */
    private Context f12984a;
    private String b;
    private StorageParams c;

    public gww(Context context, StorageParams storageParams, String str) {
        this.f12984a = context;
        this.c = storageParams;
        this.b = str;
    }

    public static int e() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(20002), "voice_enable_type");
        if (TextUtils.isEmpty(b)) {
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(20002), "voice_enable_type", "1", new StorageParams());
            return 1;
        }
        try {
            return Integer.parseInt(b);
        } catch (NumberFormatException unused) {
            LogUtil.h("Track_TrackSharedPreferenceUtil", "getVoiceEnable ", 1);
            return 1;
        }
    }

    public static void e(int i) {
        SharedPreferenceManager.e(com.huawei.haf.application.BaseApplication.e(), Integer.toString(20002), "voice_enable_type", Integer.toString(i), new StorageParams());
    }

    public static boolean c() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(20002), "iscrash");
        if (TextUtils.isEmpty(b)) {
            LogUtil.h("Track_TrackSharedPreferenceUtil", "isTrackCrash TRACK_CRASH_KEY is null!");
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(20002), "iscrash", "false", new StorageParams());
            return false;
        }
        return "true".equals(b);
    }

    public static void a(boolean z) {
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(20002), "iscrash", String.valueOf(z), new StorageParams());
    }

    public String w() {
        Context context = this.f12984a;
        if (context == null) {
            LogUtil.a("Track_TrackSharedPreferenceUtil", "getLogOffset ", "mContext is null");
            return null;
        }
        return SharedPreferenceManager.b(context, this.b, "logoffset");
    }

    public void f(String str) {
        Context context = this.f12984a;
        if (context == null) {
            LogUtil.a("Track_TrackSharedPreferenceUtil", "setLogOffset ", "mContext is null");
        } else {
            SharedPreferenceManager.e(context, this.b, "logoffset", str, this.c);
        }
    }

    public void a(long j) {
        if (this.f12984a == null) {
            LogUtil.a("Track_TrackSharedPreferenceUtil", "setLastSportStartElapsedTime mContext is null");
        } else {
            SharedPreferenceManager.e(this.f12984a, this.b, "LastSportStartElapsedTime", Long.toString(j), this.c);
        }
    }

    public int v() {
        Context context = this.f12984a;
        if (context == null) {
            LogUtil.a("Track_TrackSharedPreferenceUtil", "getMapType mContext is null");
            return 0;
        }
        String b = SharedPreferenceManager.b(context, this.b, "map_type_setting_key");
        if (TextUtils.isEmpty(b)) {
            return 0;
        }
        try {
            return Integer.parseInt(b);
        } catch (NumberFormatException e) {
            LogUtil.h("Track_TrackSharedPreferenceUtil", "getMapType ", e.getMessage());
            return 0;
        }
    }

    public int j() {
        Context context = this.f12984a;
        if (context == null) {
            LogUtil.a("Track_TrackSharedPreferenceUtil", "getAutoMapType mContext is null");
            return -1;
        }
        String b = SharedPreferenceManager.b(context, this.b, "auto_map_setting");
        if (TextUtils.isEmpty(b)) {
            return -1;
        }
        try {
            return Integer.parseInt(b);
        } catch (NumberFormatException e) {
            LogUtil.h("Track_TrackSharedPreferenceUtil", "getAutoMapType ", e.getMessage());
            return -1;
        }
    }

    public int k() {
        Context context = this.f12984a;
        if (context == null) {
            LogUtil.a("Track_TrackSharedPreferenceUtil", "getAutoTrackMapType mContext is null");
            return -1;
        }
        String b = SharedPreferenceManager.b(context, this.b, "auto_track_map_setting");
        if (TextUtils.isEmpty(b)) {
            return -1;
        }
        try {
            return Integer.parseInt(b);
        } catch (NumberFormatException e) {
            LogUtil.h("Track_TrackSharedPreferenceUtil", "getAutoTrackMapType ", e.getMessage());
            return -1;
        }
    }

    public long m() {
        Context context = this.f12984a;
        if (context == null) {
            LogUtil.a("Track_TrackSharedPreferenceUtil", "getDiffTimeBetweenGpsAndSystem mContext is null");
            return 0L;
        }
        String b = SharedPreferenceManager.b(context, this.b, "diffTimeBetweenGpsAndSystem");
        if (TextUtils.isEmpty(b)) {
            SharedPreferenceManager.e(this.f12984a, this.b, "diffTimeBetweenGpsAndSystem", "0", this.c);
            return 0L;
        }
        try {
            return Long.parseLong(b);
        } catch (NumberFormatException e) {
            LogUtil.a("Track_TrackSharedPreferenceUtil", "numberFormatException", e.getMessage());
            return 0L;
        }
    }

    public void c(long j) {
        if (this.f12984a == null) {
            LogUtil.a("Track_TrackSharedPreferenceUtil", "setDiffTimeBetweenGpsAndSystem ", "mContext is null");
        } else {
            SharedPreferenceManager.e(this.f12984a, this.b, "diffTimeBetweenGpsAndSystem", Long.toString(j), this.c);
        }
    }

    public int i() {
        Context context = this.f12984a;
        if (context == null) {
            LogUtil.a("Track_TrackSharedPreferenceUtil", "acquireVoiceIntervalSettingType ", "mContext is null");
            return 0;
        }
        String b = SharedPreferenceManager.b(context, this.b, "voice_broadcast_interval_type");
        if (!TextUtils.isEmpty(b)) {
            try {
                return Integer.parseInt(b);
            } catch (NumberFormatException e) {
                LogUtil.h("Track_TrackSharedPreferenceUtil", "acquireVoiceIntervalSettingType ", e.getMessage());
                return 0;
            }
        }
        LogUtil.h("Track_TrackSharedPreferenceUtil", "acquireVoiceIntervalSettingType you should set it before get");
        return 0;
    }

    public int f() {
        Context context = this.f12984a;
        if (context == null) {
            LogUtil.a("Track_TrackSharedPreferenceUtil", "acquireVoiceTimeIntervalValue ", "mContext is null");
            return 0;
        }
        String b = SharedPreferenceManager.b(context, this.b, "voice_broadcast_time_interval_value");
        if (!TextUtils.isEmpty(b)) {
            try {
                return Integer.parseInt(b);
            } catch (NumberFormatException e) {
                LogUtil.h("Track_TrackSharedPreferenceUtil", "acquireVoiceTimeIntervalValue ", e.getMessage());
                return 0;
            }
        }
        LogUtil.h("Track_TrackSharedPreferenceUtil", "acquireVoiceTimeIntervalValue you should set it before get");
        return 0;
    }

    public int g() {
        Context context = this.f12984a;
        if (context == null) {
            LogUtil.a("Track_TrackSharedPreferenceUtil", "acquireVoiceDistanceIntervalValue ", "mContext is null");
            return 0;
        }
        String b = SharedPreferenceManager.b(context, this.b, "voice_broadcast_distance_interval_value");
        if (!TextUtils.isEmpty(b)) {
            try {
                return Integer.parseInt(b);
            } catch (NumberFormatException e) {
                LogUtil.h("Track_TrackSharedPreferenceUtil", "acquireVoiceDistanceIntervalValue ", e.getMessage());
                return 0;
            }
        }
        LogUtil.h("Track_TrackSharedPreferenceUtil", "acquireVoiceDistanceIntervalValue you should set it before get");
        return 0;
    }

    public void b(long j) {
        if (this.f12984a == null) {
            LogUtil.a("Track_TrackSharedPreferenceUtil", "saveOnceDiffTime mContext is null");
        } else {
            SharedPreferenceManager.e(this.f12984a, this.b, "diffTimeOnce", Long.toString(j), this.c);
        }
    }

    public long h() {
        Context context = this.f12984a;
        if (context == null) {
            LogUtil.a("Track_TrackSharedPreferenceUtil", "acquireOnceDiffTime ", "mContext is null");
            return 0L;
        }
        String b = SharedPreferenceManager.b(context, this.b, "diffTimeOnce");
        if (TextUtils.isEmpty(b)) {
            SharedPreferenceManager.e(this.f12984a, this.b, "diffTimeOnce", "0", this.c);
            return 0L;
        }
        try {
            return Long.parseLong(b);
        } catch (NumberFormatException e) {
            LogUtil.a("Track_TrackSharedPreferenceUtil", "acquireOnceDiffTime ", e.getMessage());
            return 0L;
        }
    }

    public String b(String str) {
        Context context = this.f12984a;
        if (context == null) {
            LogUtil.a("Track_TrackSharedPreferenceUtil", "acquireCalibrationData ", "mContext is null");
            return null;
        }
        return SharedPreferenceManager.b(context, this.b, str);
    }

    public void a(String str, String str2) {
        Context context = this.f12984a;
        if (context == null) {
            LogUtil.a("Track_TrackSharedPreferenceUtil", "saveCalibrationData ", "mContext is null");
        } else {
            SharedPreferenceManager.e(context, this.b, str, str2, this.c);
        }
    }

    public boolean b() {
        Context context = this.f12984a;
        if (context == null) {
            LogUtil.a("Track_TrackSharedPreferenceUtil", "acquireAutoPauseEnableStatus ", "mContext is null");
            return false;
        }
        String b = SharedPreferenceManager.b(context, Integer.toString(20002), "auto_pause_enable_status");
        if (!TextUtils.isEmpty(b)) {
            return Boolean.parseBoolean(b);
        }
        LogUtil.h("Track_TrackSharedPreferenceUtil", "acquireAutoPauseEnableStatus you should set it before get");
        return false;
    }

    public static int a(Context context) {
        if (context != null) {
            String b = SharedPreferenceManager.b(context, Integer.toString(20002), "sport_listen_type");
            if (!TextUtils.isEmpty(b)) {
                try {
                    return Integer.parseInt(b);
                } catch (NumberFormatException e) {
                    LogUtil.h("Track_TrackSharedPreferenceUtil", "acquireSportListenerType ", e.getMessage());
                    return 0;
                }
            }
            LogUtil.h("Track_TrackSharedPreferenceUtil", "acquireListenerType you should set it before get");
            return 0;
        }
        LogUtil.h("Track_TrackSharedPreferenceUtil", "acquireListenerType context is null");
        return 0;
    }

    public boolean ab() {
        if (this.f12984a == null) {
            LogUtil.a("Track_TrackSharedPreferenceUtil", "isChartHorizontalHasShown ", "mContext is null");
            return false;
        }
        LogUtil.c("Track_TrackSharedPreferenceUtil", "isChartHorizontalHasShown ", this.b, " ", "track_horizontal_chart_tips_shown");
        String b = SharedPreferenceManager.b(this.f12984a, this.b, "track_horizontal_chart_tips_shown");
        if (!TextUtils.isEmpty(b)) {
            boolean parseBoolean = Boolean.parseBoolean(b);
            LogUtil.a("Track_TrackSharedPreferenceUtil", "isChartHorizontalHasShown bFlag:", Boolean.valueOf(parseBoolean));
            return parseBoolean;
        }
        LogUtil.a("Track_TrackSharedPreferenceUtil", "isChartHorizontalHasShown return default");
        return false;
    }

    public void d(boolean z) {
        if (this.f12984a == null) {
            LogUtil.a("Track_TrackSharedPreferenceUtil", "saveChartHorizontalHasShown ", "mContext is null");
            return;
        }
        LogUtil.a("Track_TrackSharedPreferenceUtil", "saveChartHorizontalHasShown bFlag:", Boolean.valueOf(z));
        LogUtil.c("Track_TrackSharedPreferenceUtil", "saveChartHorizontalHasShown ", this.b, " ", "track_horizontal_chart_tips_shown");
        SharedPreferenceManager.e(this.f12984a, this.b, "track_horizontal_chart_tips_shown", Boolean.toString(z), this.c);
    }

    public boolean aa() {
        Context context = this.f12984a;
        if (context == null) {
            LogUtil.a("Track_TrackSharedPreferenceUtil", "isSecurityNoMoreRemind mContext is null");
            return false;
        }
        String b = SharedPreferenceManager.b(context, this.b, "security_no_more_remind");
        LogUtil.a("Track_TrackSharedPreferenceUtil", "isSecurityNoMoreRemind ", b);
        if (TextUtils.isEmpty(b)) {
            return false;
        }
        return "true".equals(b);
    }

    public void a(int i, int i2) {
        Context context = this.f12984a;
        if (context == null) {
            LogUtil.a("Track_TrackSharedPreferenceUtil", "setMusicOperatorType ", "mContext is null");
            return;
        }
        LogUtil.a("Track_TrackSharedPreferenceUtil", "setMusicOperatorType result ", Integer.valueOf(SharedPreferenceManager.e(context, this.b, "sport_music_operator_type" + i2, Integer.toString(i), this.c)));
    }

    public int f(int i) {
        Context context = this.f12984a;
        if (context != null) {
            String b = SharedPreferenceManager.b(context, this.b, "sport_music_operator_type" + i);
            LogUtil.a("Track_TrackSharedPreferenceUtil", "operatorTypeString ", b);
            if (!TextUtils.isEmpty(b)) {
                try {
                    return Integer.parseInt(b);
                } catch (NumberFormatException e) {
                    LogUtil.h("Track_TrackSharedPreferenceUtil", "getSportMusicOperatorType ", e.getMessage());
                    return 0;
                }
            }
            LogUtil.h("Track_TrackSharedPreferenceUtil", "getSportMusicOperatorType you should set it before get");
            return 0;
        }
        LogUtil.h("Track_TrackSharedPreferenceUtil", "getSportMusicOperatorType context is null");
        return 0;
    }

    public void b(String str, int i) {
        Context context = this.f12984a;
        if (context == null) {
            LogUtil.a("Track_TrackSharedPreferenceUtil", "setSportMusicContentId ", "mContext is null");
            return;
        }
        SharedPreferenceManager.e(context, this.b, "sport_music_content_id" + i, str, this.c);
    }

    public String b(int i) {
        Context context = this.f12984a;
        if (context == null) {
            return null;
        }
        return SharedPreferenceManager.b(context, this.b, "sport_music_content_id" + i);
    }

    public void d(String str, int i) {
        LogUtil.a("Track_TrackSharedPreferenceUtil", "setSportMusicContentType ", "contentType ", str);
        Context context = this.f12984a;
        if (context == null) {
            LogUtil.a("Track_TrackSharedPreferenceUtil", "setSportMusicContentId ", "mContext is null");
            return;
        }
        SharedPreferenceManager.e(context, this.b, "sport_music_content_type" + i, str, this.c);
    }

    public String a(int i) {
        Context context = this.f12984a;
        if (context != null) {
            return SharedPreferenceManager.b(context, this.b, "sport_music_content_type" + i);
        }
        LogUtil.h("Track_TrackSharedPreferenceUtil", "getSportMusicContentType ", "mContext is null");
        return null;
    }

    public void e(String str, int i) {
        Context context = this.f12984a;
        if (context == null) {
            LogUtil.a("Track_TrackSharedPreferenceUtil", "setSportMusicContentId ", "mContext is null");
            return;
        }
        SharedPreferenceManager.e(context, this.b, "sport_music_content_element" + i, str, this.c);
    }

    public String d(int i) {
        Context context = this.f12984a;
        if (context == null) {
            return null;
        }
        return SharedPreferenceManager.b(context, this.b, "sport_music_content_element" + i);
    }

    public void a(String str) {
        SharedPreferenceManager.e(this.f12984a, this.b, "music_information", str, this.c);
    }

    public String l() {
        Context context = this.f12984a;
        if (context != null) {
            return SharedPreferenceManager.b(context, this.b, "music_information");
        }
        return null;
    }

    public void h(String str) {
        SharedPreferenceManager.e(this.f12984a, this.b, "dynamic_resource_version", str, this.c);
    }

    public String r() {
        Context context = this.f12984a;
        if (context != null) {
            return SharedPreferenceManager.b(context, this.b, "dynamic_resource_version");
        }
        return null;
    }

    public void e(String str) {
        SharedPreferenceManager.e(this.f12984a, this.b, "dynamic_music_path", str, this.c);
    }

    public String n() {
        Context context = this.f12984a;
        if (context != null) {
            return SharedPreferenceManager.b(context, this.b, "dynamic_music_path");
        }
        return null;
    }

    public void c(String str, int i) {
        String c = hcr.c(i);
        if (TextUtils.isEmpty(c)) {
            LogUtil.b("Track_TrackSharedPreferenceUtil", "setDynamicCustomMapInformation storageKey is null.");
        } else {
            SharedPreferenceManager.e(this.f12984a, this.b, c, str, this.c);
        }
    }

    public String c(int i) {
        if (this.f12984a == null || TextUtils.isEmpty(hcr.c(i))) {
            return null;
        }
        return SharedPreferenceManager.b(this.f12984a, this.b, hcr.c(i));
    }

    public void d(String str) {
        SharedPreferenceManager.e(this.f12984a, this.b, "new_custom_mark_information", str, this.c);
    }

    public String t() {
        Context context = this.f12984a;
        if (context != null) {
            return SharedPreferenceManager.b(context, this.b, "new_custom_mark_information");
        }
        return null;
    }

    public void i(String str) {
        SharedPreferenceManager.e(this.f12984a, this.b, "dynamic_map_resource_version", str, this.c);
    }

    public String q() {
        Context context = this.f12984a;
        if (context != null) {
            return SharedPreferenceManager.b(context, this.b, "dynamic_map_resource_version");
        }
        return null;
    }

    public String s() {
        Context context = this.f12984a;
        return context != null ? SharedPreferenceManager.b(context, this.b, "dynamic_map_gaode_sdk_style_version") : "";
    }

    public void g(String str) {
        SharedPreferenceManager.e(this.f12984a, this.b, "dynamic_map_gaode_sdk_style_version", str, this.c);
    }

    public void l(String str) {
        SharedPreferenceManager.e(this.f12984a, this.b, "current_connected_foot_bolt", str, this.c);
    }

    public String y() {
        Context context = this.f12984a;
        return context != null ? SharedPreferenceManager.b(context, this.b, "current_connected_foot_bolt") : "";
    }

    public void o(String str) {
        SharedPreferenceManager.e(this.f12984a, this.b, "current_connected_waist_bolt", str, this.c);
    }

    public String ad() {
        Context context = this.f12984a;
        return context != null ? SharedPreferenceManager.b(context, this.b, "current_connected_waist_bolt") : "";
    }

    public void k(String str) {
        SharedPreferenceManager.e(this.f12984a, this.b, "current_connected_bike_bolt", str, this.c);
    }

    public String u() {
        Context context = this.f12984a;
        return context != null ? SharedPreferenceManager.b(context, this.b, "current_connected_bike_bolt") : "";
    }

    public void j(String str) {
        SharedPreferenceManager.e(this.f12984a, this.b, "current_preemption_bolt", str, this.c);
    }

    public void c(String str) {
        Context context = this.f12984a;
        if (context != null) {
            SharedPreferenceManager.e(context, this.b, "run_connect_bolt_tip_show", str, (StorageParams) null);
        }
    }

    public String o() {
        Context context = this.f12984a;
        return context != null ? SharedPreferenceManager.b(context, this.b, "run_connect_bolt_tip_show") : "";
    }

    public String x() {
        Context context = this.f12984a;
        return context != null ? SharedPreferenceManager.b(context, this.b, "current_preemption_bolt") : "";
    }

    public boolean p() {
        return SharedPreferenceManager.a(this.b, "first_skip_tag", true);
    }

    public void e(boolean z) {
        SharedPreferenceManager.e(this.b, "first_skip_tag", z);
    }

    public boolean z() {
        return SharedPreferenceManager.a(this.b, "is_s_r_p_tag", true);
    }

    public void c(boolean z) {
        SharedPreferenceManager.e(this.b, "is_s_r_p_tag", z);
    }

    public static boolean a() {
        if (e() == 0) {
            LogUtil.a("Track_TrackSharedPreferenceUtil", "getVoiceEnable() is false");
            return false;
        }
        LogUtil.a("Track_TrackSharedPreferenceUtil", "getVoiceEnable() is true ");
        return true;
    }

    public static void b(boolean z) {
        LogUtil.a("Track_TrackSharedPreferenceUtil", "setVoiceEnable() ", Boolean.valueOf(z));
        if (z) {
            e(1);
        } else {
            e(0);
        }
    }

    public static boolean d() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(20002), "voice_rope_skpping");
        return TextUtils.isEmpty(b) || CommonUtil.h(b) == 1;
    }
}
