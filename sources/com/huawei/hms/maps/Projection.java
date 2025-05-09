package com.huawei.hms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import com.huawei.hms.maps.internal.IProjectionDelegate;
import com.huawei.hms.maps.model.LatLng;
import com.huawei.hms.maps.model.VisibleRegion;
import com.huawei.hms.maps.utils.LogM;

/* loaded from: classes4.dex */
public final class Projection {

    /* renamed from: a, reason: collision with root package name */
    private final IProjectionDelegate f4941a;

    public Point toScreenLocation(LatLng latLng) {
        try {
            return this.f4941a.toScreenLocation(latLng);
        } catch (RemoteException e) {
            LogM.e("Projection", "toScreenLocation: " + e.getMessage());
            return null;
        }
    }

    public VisibleRegion getVisibleRegion() {
        try {
            return this.f4941a.getVisibleRegion();
        } catch (RemoteException e) {
            LogM.e("Projection", "getVisibleRegion: " + e.getMessage());
            return null;
        }
    }

    public LatLng fromScreenLocation(Point point) {
        try {
            return this.f4941a.fromScreenLocation(point);
        } catch (RemoteException e) {
            LogM.e("Projection", "fromScreenLocation: " + e.getMessage());
            return null;
        }
    }

    public Projection(IProjectionDelegate iProjectionDelegate) {
        this.f4941a = iProjectionDelegate;
    }
}
