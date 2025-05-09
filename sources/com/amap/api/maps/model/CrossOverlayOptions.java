package com.amap.api.maps.model;

import android.graphics.Bitmap;
import com.autonavi.ae.gmap.gloverlay.AVectorCrossAttr;

/* loaded from: classes8.dex */
public class CrossOverlayOptions {

    /* renamed from: a, reason: collision with root package name */
    AVectorCrossAttr f1426a = null;
    private Bitmap bitmapDescriptor = null;

    public AVectorCrossAttr getAttribute() {
        return this.f1426a;
    }

    public CrossOverlayOptions setAttribute(AVectorCrossAttr aVectorCrossAttr) {
        this.f1426a = aVectorCrossAttr;
        return this;
    }

    public CrossOverlayOptions setRes(Bitmap bitmap) {
        this.bitmapDescriptor = bitmap;
        return this;
    }

    public Bitmap getRes() {
        return this.bitmapDescriptor;
    }
}
