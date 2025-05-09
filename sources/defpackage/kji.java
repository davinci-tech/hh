package defpackage;

import android.os.Bundle;

/* loaded from: classes5.dex */
public class kji {
    private boolean i = false;
    private boolean m = false;
    private boolean c = false;
    private boolean j = false;

    /* renamed from: a, reason: collision with root package name */
    private boolean f14413a = false;
    private boolean h = false;
    private boolean e = false;
    private boolean b = false;
    private boolean g = false;
    private boolean f = false;
    private boolean d = false;
    private boolean o = false;
    private boolean n = false;

    public boolean o() {
        return this.o;
    }

    public void c(boolean z) {
        this.o = ((Boolean) jdy.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean e() {
        return this.d;
    }

    public void a(boolean z) {
        this.d = ((Boolean) jdy.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean i() {
        return this.f;
    }

    public boolean j() {
        return this.h;
    }

    public boolean b() {
        return this.b;
    }

    public boolean h() {
        return this.g;
    }

    public boolean n() {
        return ((Boolean) jdy.d(Boolean.valueOf(this.i))).booleanValue();
    }

    public boolean k() {
        return ((Boolean) jdy.d(Boolean.valueOf(this.m))).booleanValue();
    }

    public boolean g() {
        return this.c;
    }

    public boolean f() {
        return ((Boolean) jdy.d(Boolean.valueOf(this.j))).booleanValue();
    }

    public boolean d() {
        return ((Boolean) jdy.d(Boolean.valueOf(this.e))).booleanValue();
    }

    public boolean a() {
        return ((Boolean) jdy.d(Boolean.valueOf(this.f14413a))).booleanValue();
    }

    public boolean c() {
        return this.n;
    }

    public void e(boolean z) {
        this.n = z;
    }

    public void bOD_(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.i = ((Boolean) jdy.d(Boolean.valueOf(bundle.getBoolean("weather_support", false)))).booleanValue();
        this.m = ((Boolean) jdy.d(Boolean.valueOf(bundle.getBoolean("wind_support", false)))).booleanValue();
        this.c = ((Boolean) jdy.d(Boolean.valueOf(bundle.getBoolean("pm2_5_support", false)))).booleanValue();
        this.j = ((Boolean) jdy.d((Boolean) jdy.d(Boolean.valueOf(bundle.getBoolean("temperature_support", false))))).booleanValue();
        this.e = ((Boolean) jdy.d(Boolean.valueOf(bundle.getBoolean("location_name_support", false)))).booleanValue();
        this.f14413a = ((Boolean) jdy.d(Boolean.valueOf(bundle.getBoolean("temperature_current_support", false)))).booleanValue();
        this.h = ((Boolean) jdy.d(Boolean.valueOf(bundle.getBoolean("unit_support", false)))).booleanValue();
        this.b = ((Boolean) jdy.d(Boolean.valueOf(bundle.getBoolean("aqi_support", false)))).booleanValue();
        this.g = ((Boolean) jdy.d(Boolean.valueOf(bundle.getBoolean("time_support", false)))).booleanValue();
        this.f = ((Boolean) jdy.d(Boolean.valueOf(bundle.getBoolean("source_support", false)))).booleanValue();
        this.d = ((Boolean) jdy.d(Boolean.valueOf(bundle.getBoolean("cn_weather_icon_support", false)))).booleanValue();
        this.o = ((Boolean) jdy.d(Boolean.valueOf(bundle.getBoolean("weather_icon_expand_support", false)))).booleanValue();
    }
}
