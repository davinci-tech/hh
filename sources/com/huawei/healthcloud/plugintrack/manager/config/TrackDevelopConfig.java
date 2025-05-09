package com.huawei.healthcloud.plugintrack.manager.config;

import android.content.Context;
import android.content.SharedPreferences;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.dwt;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

/* loaded from: classes4.dex */
public class TrackDevelopConfig {
    private Context b;
    private StorageParams g;
    private SharedPreferences i;
    private String j;

    /* renamed from: a, reason: collision with root package name */
    private boolean f3520a = true;
    private boolean e = true;
    private boolean c = true;
    private boolean f = true;
    private boolean d = true;

    public TrackDevelopConfig(Context context) {
        if (context == null) {
            throw new RuntimeException("TrackDevelopConfig invalid params in constructor");
        }
        this.j = Integer.toString(20002);
        this.b = context;
        this.i = context.getSharedPreferences("TrackDevelopConfig", 0);
        StorageParams storageParams = new StorageParams();
        this.g = storageParams;
        storageParams.d(0);
    }

    public void d() {
        SharedPreferences sharedPreferences = this.i;
        if (sharedPreferences != null) {
            this.f3520a = sharedPreferences.getBoolean("StepEstimatedDitance", true);
            this.e = this.i.getBoolean("StaticDropGps", true);
            this.c = this.i.getBoolean("LogConvert", true);
            this.f = this.i.getBoolean("TrackSmooth", true);
            this.d = this.i.getBoolean("NeedStartFinding", true);
        }
    }

    private int b(String str, String str2) {
        if (str == null) {
            return 0;
        }
        try {
            int parseInt = Integer.parseInt(str2);
            String b = SharedPreferenceManager.b(this.b, this.j, str);
            return (b == null || b.isEmpty()) ? parseInt : Integer.parseInt(b);
        } catch (NumberFormatException e) {
            LogUtil.b("Track_TrackDevelopConfig", e.getMessage());
            return 0;
        }
    }

    private float d(String str, String str2) {
        if (str == null) {
            return 0.0f;
        }
        try {
            float parseFloat = Float.parseFloat(str2);
            String b = SharedPreferenceManager.b(this.b, this.j, str);
            return (b == null || b.isEmpty()) ? parseFloat : Float.parseFloat(b);
        } catch (NumberFormatException e) {
            LogUtil.b("Track_TrackDevelopConfig", e.getMessage());
            return 0.0f;
        }
    }

    public int b(String str, int i) {
        return b(str, Integer.toString(i));
    }

    public boolean b() {
        return this.e;
    }

    public boolean e() {
        return this.f3520a;
    }

    public boolean c() {
        return this.c;
    }

    public dwt a() {
        dwt dwtVar = new dwt();
        dwtVar.d(d("filter_distance", "3"));
        dwtVar.e(d("min_satellite_threshold_speed", "0.3"));
        dwtVar.b(d("max_satellite_speed_multiple", "2.8"));
        dwtVar.e(this.i.getBoolean("TrackSmooth", true));
        dwtVar.d(this.i.getBoolean("NeedStartFinding", true));
        dwtVar.d(b("min_dis_acc", "100"));
        dwtVar.c(d("min_time_interval", "1.5"));
        dwtVar.b(new float[]{d("max_speed_dis_bike", "12"), d("max_speed_dis_run", "12"), d("max_speed_dis_bike", "33.3")});
        LogUtil.a("Track_TrackDevelopConfig", "MIN_DISTANCE_REDUNDANCY :", Float.valueOf(dwtVar.a()), " -- MIN_GPS_SPEED :", Float.valueOf(dwtVar.e()), " -- FILTER_TIMES_OF_GPS_SPEED :", Float.valueOf(dwtVar.d()), " -- NEED_SMOOTH :", Boolean.valueOf(dwtVar.h()), " -- NEED_START_FINDING :", Boolean.valueOf(dwtVar.j()), " -- MIN_GPS_ACCURACY :", Integer.valueOf(dwtVar.b()), " -- MIN_TIME_INTERVAL :", Float.valueOf(dwtVar.f()), " -- MAX_SPEED_DISTANCE_WALK :", Float.valueOf(dwtVar.a(0)), " -- DEFAULT_MAX_SPEED_DISTANCE_RUN :", Float.valueOf(dwtVar.a(1)), " -- MAX_SPEED_DISTANCE_BIKE :", Float.valueOf(dwtVar.a(2)));
        return dwtVar;
    }

    public long e(String str, long j) {
        String b;
        if (str == null || (b = SharedPreferenceManager.b(this.b, this.j, str)) == null || b.isEmpty()) {
            return j;
        }
        try {
            return Long.parseLong(b);
        } catch (NumberFormatException e) {
            LogUtil.b("Track_TrackDevelopConfig", e.getMessage());
            return j;
        }
    }

    public boolean c(String str, boolean z) {
        String b;
        return (str == null || (b = SharedPreferenceManager.b(this.b, this.j, str)) == null || b.isEmpty()) ? z : "1".equals(b);
    }
}
