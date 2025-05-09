package com.amap.api.trace;

/* loaded from: classes8.dex */
public class TraceLocation {

    /* renamed from: a, reason: collision with root package name */
    private double f1607a;
    private double b;
    private float c;
    private float d;
    private long e;

    public TraceLocation(double d, double d2, float f, float f2, long j) {
        this.f1607a = a(d);
        this.b = a(d2);
        this.c = (int) ((f * 3600.0f) / 1000.0f);
        this.d = (int) f2;
        this.e = j;
    }

    public TraceLocation() {
    }

    public double getLatitude() {
        return this.f1607a;
    }

    public void setLatitude(double d) {
        this.f1607a = a(d);
    }

    public double getLongitude() {
        return this.b;
    }

    public void setLongitude(double d) {
        this.b = a(d);
    }

    public float getSpeed() {
        return this.c;
    }

    public void setSpeed(float f) {
        this.c = (int) ((f * 3600.0f) / 1000.0f);
    }

    public float getBearing() {
        return this.d;
    }

    public void setBearing(float f) {
        this.d = (int) f;
    }

    public long getTime() {
        return this.e;
    }

    public void setTime(long j) {
        this.e = j;
    }

    private static double a(double d) {
        return Math.round(d * 1000000.0d) / 1000000.0d;
    }

    public TraceLocation copy() {
        TraceLocation traceLocation = new TraceLocation();
        traceLocation.d = this.d;
        traceLocation.f1607a = this.f1607a;
        traceLocation.b = this.b;
        traceLocation.c = this.c;
        traceLocation.e = this.e;
        return traceLocation;
    }

    public String toString() {
        return this.f1607a + ",longtitude " + this.b + ",speed " + this.c + ",bearing " + this.d + ",time " + this.e;
    }
}
