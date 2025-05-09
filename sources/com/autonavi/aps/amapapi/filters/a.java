package com.autonavi.aps.amapapi.filters;

import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.autonavi.aps.amapapi.utils.i;
import com.huawei.operation.OpAnalyticsConstants;

/* loaded from: classes2.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    com.autonavi.aps.amapapi.model.a f1621a = null;
    long b = 0;
    long c = 0;
    private boolean h = true;
    int d = 0;
    long e = 0;
    AMapLocation f = null;
    long g = 0;

    public final void a() {
        this.f1621a = null;
        this.b = 0L;
        this.c = 0L;
        this.f = null;
        this.g = 0L;
    }

    public final com.autonavi.aps.amapapi.model.a a(com.autonavi.aps.amapapi.model.a aVar) {
        if (i.b() - this.e > OpAnalyticsConstants.H5_LOADING_DELAY) {
            this.f1621a = aVar;
            this.e = i.b();
            return this.f1621a;
        }
        this.e = i.b();
        if (!i.a(this.f1621a) || !i.a(aVar)) {
            this.b = i.b();
            this.f1621a = aVar;
            return aVar;
        }
        if (aVar.getTime() == this.f1621a.getTime() && aVar.getAccuracy() < 300.0f) {
            return aVar;
        }
        if (GeocodeSearch.GPS.equals(aVar.getProvider())) {
            this.b = i.b();
            this.f1621a = aVar;
            return aVar;
        }
        if (aVar.c() != this.f1621a.c()) {
            this.b = i.b();
            this.f1621a = aVar;
            return aVar;
        }
        if (aVar.getBuildingId() != null && !aVar.getBuildingId().equals(this.f1621a.getBuildingId()) && !TextUtils.isEmpty(aVar.getBuildingId())) {
            this.b = i.b();
            this.f1621a = aVar;
            return aVar;
        }
        this.d = aVar.getLocationType();
        float a2 = i.a(aVar, this.f1621a);
        float accuracy = this.f1621a.getAccuracy();
        float accuracy2 = aVar.getAccuracy();
        float f = accuracy2 - accuracy;
        long b = i.b();
        long j = this.b;
        boolean z = false;
        boolean z2 = accuracy <= 100.0f && accuracy2 > 299.0f;
        if (accuracy > 299.0f && accuracy2 > 299.0f) {
            z = true;
        }
        if (z2 || z) {
            long j2 = this.c;
            if (j2 == 0) {
                this.c = b;
            } else if (b - j2 > OpAnalyticsConstants.H5_LOADING_DELAY) {
                this.b = b;
                this.f1621a = aVar;
                this.c = 0L;
                return aVar;
            }
            com.autonavi.aps.amapapi.model.a b2 = b(this.f1621a);
            this.f1621a = b2;
            return b2;
        }
        if (accuracy2 < 100.0f && accuracy > 299.0f) {
            this.b = b;
            this.f1621a = aVar;
            this.c = 0L;
            return aVar;
        }
        if (accuracy2 <= 299.0f) {
            this.c = 0L;
        }
        if (a2 >= 10.0f || a2 <= 0.1d || accuracy2 <= 5.0f) {
            if (f < 300.0f) {
                this.b = i.b();
                this.f1621a = aVar;
                return aVar;
            }
            if (b - j >= OpAnalyticsConstants.H5_LOADING_DELAY) {
                this.b = i.b();
                this.f1621a = aVar;
                return aVar;
            }
            com.autonavi.aps.amapapi.model.a b3 = b(this.f1621a);
            this.f1621a = b3;
            return b3;
        }
        if (f >= -300.0f) {
            com.autonavi.aps.amapapi.model.a b4 = b(this.f1621a);
            this.f1621a = b4;
            return b4;
        }
        if (accuracy / accuracy2 >= 2.0f) {
            this.b = b;
            this.f1621a = aVar;
            return aVar;
        }
        com.autonavi.aps.amapapi.model.a b5 = b(this.f1621a);
        this.f1621a = b5;
        return b5;
    }

    private com.autonavi.aps.amapapi.model.a b(com.autonavi.aps.amapapi.model.a aVar) {
        if (i.a(aVar)) {
            if (this.h && com.autonavi.aps.amapapi.utils.a.a(aVar.getTime())) {
                if (aVar.getLocationType() == 5 || aVar.getLocationType() == 6) {
                    aVar.setLocationType(4);
                }
            } else {
                aVar.setLocationType(this.d);
            }
        }
        return aVar;
    }

    public final void a(boolean z) {
        this.h = z;
    }

    public final AMapLocation a(AMapLocation aMapLocation) {
        if (!i.a(aMapLocation)) {
            return aMapLocation;
        }
        long b = i.b();
        long j = this.g;
        this.g = i.b();
        if (b - j > 5000) {
            return aMapLocation;
        }
        AMapLocation aMapLocation2 = this.f;
        if (aMapLocation2 == null) {
            this.f = aMapLocation;
            return aMapLocation;
        }
        if (1 != aMapLocation2.getLocationType() && !GeocodeSearch.GPS.equalsIgnoreCase(this.f.getProvider())) {
            this.f = aMapLocation;
            return aMapLocation;
        }
        if (this.f.getAltitude() == aMapLocation.getAltitude() && this.f.getLongitude() == aMapLocation.getLongitude()) {
            this.f = aMapLocation;
            return aMapLocation;
        }
        long abs = Math.abs(aMapLocation.getTime() - this.f.getTime());
        if (OpAnalyticsConstants.H5_LOADING_DELAY < abs) {
            this.f = aMapLocation;
            return aMapLocation;
        }
        if (i.a(aMapLocation, this.f) > (((this.f.getSpeed() + aMapLocation.getSpeed()) * abs) / 2000.0f) + ((this.f.getAccuracy() + aMapLocation.getAccuracy()) * 2.0f) + 3000.0f) {
            return this.f;
        }
        this.f = aMapLocation;
        return aMapLocation;
    }
}
