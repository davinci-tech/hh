package com.huawei.hms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import com.huawei.hms.maps.model.StreetViewPanoramaOrientation;

/* loaded from: classes9.dex */
public interface IOnStreetViewPanoramaClickListener extends IInterface {
    void onStreetViewPanoramaClick(StreetViewPanoramaOrientation streetViewPanoramaOrientation);

    public static abstract class Stub extends Binder implements IOnStreetViewPanoramaClickListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.huawei.hms.maps.internal.IOnStreetViewPanoramaClickListener");
        }
    }
}
