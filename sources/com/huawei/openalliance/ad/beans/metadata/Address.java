package com.huawei.openalliance.ad.beans.metadata;

/* loaded from: classes5.dex */
public class Address {
    private String adminArea;
    private String country;
    private String fetureName;
    private String locale;
    private String locality;
    private String subAdminArea;
    private String subLocality;
    private String subThoroughfare;
    private String thoroughfare;

    public void i(String str) {
        this.locale = str;
    }

    public void h(String str) {
        this.fetureName = str;
    }

    public void g(String str) {
        this.subThoroughfare = str;
    }

    public void f(String str) {
        this.thoroughfare = str;
    }

    public void e(String str) {
        this.subAdminArea = str;
    }

    public void d(String str) {
        this.subLocality = str;
    }

    public void c(String str) {
        this.locality = str;
    }

    public void b(String str) {
        this.adminArea = str;
    }

    public void a(String str) {
        this.country = str;
    }
}
