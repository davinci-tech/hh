package defpackage;

import android.content.Context;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class hcj {
    public static void a(Context context, int i, boolean z, int i2) {
        HashMap hashMap = new HashMap(5);
        hashMap.put("click", 1);
        hashMap.put("sport_type", Integer.valueOf(i));
        hashMap.put("trackType", z ? "3D" : "common");
        hashMap.put("logTime", Integer.valueOf(i2));
        ixx.d().d(context, AnalyticsValue.HEALTH_RETRACK_ENTER_1040057.value(), hashMap, 0);
    }

    public static void d(Context context, int i, boolean z) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put("sport_type", Integer.valueOf(i));
        hashMap.put("trackType", z ? "3D" : "common");
        ixx.d().d(context, AnalyticsValue.HEALTH_RETRACK_START_1040058.value(), hashMap, 0);
    }

    public static void b(Context context, int i, boolean z, boolean z2, int i2) {
        HashMap hashMap = new HashMap(6);
        hashMap.put("click", 1);
        if (z2) {
            hashMap.put("video_type", 1);
        } else {
            hashMap.put("video_type", 0);
        }
        hashMap.put("click", 1);
        hashMap.put("sport_type", Integer.valueOf(i));
        hashMap.put("trackType", z ? "3D" : "common");
        hashMap.put("video_quality", Integer.valueOf(i2));
        ixx.d().d(context, AnalyticsValue.HEALTH_RETRACK_SAVE_1040051.value(), hashMap, 0);
    }

    public static void e(Context context, int i, boolean z, String str) {
        HashMap hashMap = new HashMap(4);
        hashMap.put("click", 1);
        hashMap.put("sport_type", Integer.valueOf(i));
        hashMap.put("trackType", z ? "3D" : "common");
        hashMap.put(CommonUtil.PAGE_TYPE, str);
        ixx.d().d(context, AnalyticsValue.HEALTH_RETRACK_MUSIC_1040053.value(), hashMap, 0);
    }

    public static void d(Context context, hcm hcmVar, String str) {
        HashMap hashMap = new HashMap(8);
        hashMap.put("click", 1);
        hashMap.put("sport_type", Integer.valueOf(hcmVar.i()));
        hashMap.put("trackType", hcmVar.f() ? "3D" : "common");
        hashMap.put("defaultValue", str);
        hashMap.put("map_type", null);
        hashMap.put("mark_type", hcmVar.d());
        hashMap.put("music_type", hcmVar.c());
        hashMap.put("angleType", Integer.valueOf(hcmVar.e()));
        ixx.d().d(context, AnalyticsValue.HEALTH_RETRACK_MAPTYPE_1040052.value(), hashMap, 0);
    }

    public static void e(Context context, int i, boolean z, int i2) {
        HashMap hashMap = new HashMap(4);
        hashMap.put("click", 1);
        hashMap.put("dynamic_track_share_type", Integer.valueOf(i2));
        hashMap.put("sport_type", Integer.valueOf(i));
        hashMap.put("trackType", z ? "3D" : "common");
        ixx.d().d(context, AnalyticsValue.HEALTH_RETRACK_SHARE_TO_PLATFORM_1040054.value(), hashMap, 0);
    }

    public static void a(Context context, int i, boolean z) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put("sport_type", Integer.valueOf(i));
        hashMap.put("trackType", z ? "3D" : "common");
        ixx.d().d(context, AnalyticsValue.HEALTH_RETRACK_PERSONALIZED_1040055.value(), hashMap, 0);
    }

    public static void e(Context context, int i, boolean z, int i2, String str) {
        HashMap hashMap = new HashMap(4);
        hashMap.put("click", 1);
        hashMap.put("sport_type", Integer.valueOf(i));
        hashMap.put("trackType", z ? "3D" : "common");
        hashMap.put("event", Integer.valueOf(i2));
        hashMap.put("signageType", str);
        ixx.d().d(context, AnalyticsValue.HEALTH_RETRACK_MOMENTS_1040067.value(), hashMap, 0);
    }

    public static void e(Context context, hcm hcmVar) {
        HashMap hashMap = new HashMap(10);
        hashMap.put("click", 1);
        hashMap.put("sport_type", Integer.valueOf(hcmVar.i()));
        hashMap.put("trackType", hcmVar.f() ? "3D" : "common");
        hashMap.put("piclNum", Integer.valueOf(hcmVar.j()));
        hashMap.put("videoNum", Integer.valueOf(hcmVar.h()));
        hashMap.put("feelings", Boolean.valueOf(hcmVar.b()));
        hashMap.put("map_type", hcmVar.a());
        hashMap.put("mark_type", hcmVar.d());
        hashMap.put("music_type", hcmVar.c());
        hashMap.put("angleType", Integer.valueOf(hcmVar.e()));
        ixx.d().d(context, AnalyticsValue.HEALTH_RETRACK_PERSONALIZED_1040070.value(), hashMap, 0);
    }
}
