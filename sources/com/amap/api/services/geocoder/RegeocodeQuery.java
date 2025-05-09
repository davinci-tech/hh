package com.amap.api.services.geocoder;

import com.amap.api.services.core.LatLonPoint;

/* loaded from: classes2.dex */
public class RegeocodeQuery {

    /* renamed from: a, reason: collision with root package name */
    private LatLonPoint f1499a;
    private float b;
    private String c = GeocodeSearch.AMAP;
    private String d = "";
    private String e = "distance";
    private String f = "base";

    public RegeocodeQuery(LatLonPoint latLonPoint, float f, String str) {
        this.f1499a = latLonPoint;
        this.b = f;
        setLatLonType(str);
    }

    public LatLonPoint getPoint() {
        return this.f1499a;
    }

    public void setPoint(LatLonPoint latLonPoint) {
        this.f1499a = latLonPoint;
    }

    public float getRadius() {
        return this.b;
    }

    public void setRadius(float f) {
        this.b = f;
    }

    public String getLatLonType() {
        return this.c;
    }

    public void setLatLonType(String str) {
        if (str != null) {
            if (str.equals(GeocodeSearch.AMAP) || str.equals(GeocodeSearch.GPS)) {
                this.c = str;
            }
        }
    }

    public String getPoiType() {
        return this.d;
    }

    public void setPoiType(String str) {
        this.d = str;
    }

    public String getMode() {
        return this.e;
    }

    public void setMode(String str) {
        this.e = str;
    }

    public String getExtensions() {
        return this.f;
    }

    public void setExtensions(String str) {
        this.f = str;
    }

    public int hashCode() {
        String str = this.c;
        int hashCode = str == null ? 0 : str.hashCode();
        LatLonPoint latLonPoint = this.f1499a;
        return ((((hashCode + 31) * 31) + (latLonPoint != null ? latLonPoint.hashCode() : 0)) * 31) + Float.floatToIntBits(this.b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RegeocodeQuery regeocodeQuery = (RegeocodeQuery) obj;
        String str = this.c;
        if (str == null) {
            if (regeocodeQuery.c != null) {
                return false;
            }
        } else if (!str.equals(regeocodeQuery.c)) {
            return false;
        }
        LatLonPoint latLonPoint = this.f1499a;
        if (latLonPoint == null) {
            if (regeocodeQuery.f1499a != null) {
                return false;
            }
        } else if (!latLonPoint.equals(regeocodeQuery.f1499a)) {
            return false;
        }
        if (Float.floatToIntBits(this.b) != Float.floatToIntBits(regeocodeQuery.b) || !this.e.equals(regeocodeQuery.e)) {
            return false;
        }
        String str2 = this.f;
        if (str2 == null) {
            if (regeocodeQuery.f != null) {
                return false;
            }
        } else if (!str2.equals(regeocodeQuery.f)) {
            return false;
        }
        return true;
    }
}
