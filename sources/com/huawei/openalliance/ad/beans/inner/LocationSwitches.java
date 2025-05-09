package com.huawei.openalliance.ad.beans.inner;

/* loaded from: classes5.dex */
public class LocationSwitches {
    public static final int SWITCH_OFF = 0;
    public static final int SWITCH_ON = 1;
    private int adsLoc;
    private int gpsOn;
    private boolean locationAccess;
    private boolean locationAvailable;
    private int mediaGpsOn;

    public boolean e() {
        return this.locationAccess;
    }

    public boolean d() {
        return this.locationAvailable;
    }

    public void c(int i) {
        this.mediaGpsOn = i;
    }

    public int c() {
        return this.mediaGpsOn;
    }

    public void b(boolean z) {
        this.locationAccess = z;
    }

    public void b(int i) {
        this.gpsOn = i;
    }

    public int b() {
        return this.gpsOn;
    }

    public void a(boolean z) {
        this.locationAvailable = z;
    }

    public void a(int i) {
        this.adsLoc = i;
    }

    public int a() {
        return this.adsLoc;
    }
}
