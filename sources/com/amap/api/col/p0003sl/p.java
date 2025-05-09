package com.amap.api.col.p0003sl;

import android.location.Location;
import com.amap.api.maps.LocationSource;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;

/* loaded from: classes2.dex */
final class p implements LocationSource.OnLocationChangedListener {

    /* renamed from: a, reason: collision with root package name */
    Location f1352a;
    private IAMapDelegate b;

    p(IAMapDelegate iAMapDelegate) {
        this.b = iAMapDelegate;
    }

    @Override // com.amap.api.maps.LocationSource.OnLocationChangedListener
    public final void onLocationChanged(Location location) {
        this.f1352a = location;
        try {
            if (this.b.isMyLocationEnabled()) {
                this.b.showMyLocationOverlay(location);
            }
        } catch (Throwable th) {
            iv.c(th, "AMapOnLocationChangedListener", "onLocationChanged");
            th.printStackTrace();
        }
    }
}
