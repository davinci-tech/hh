package com.huawei.hms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import com.huawei.hms.maps.model.StreetViewPanoramaCamera;

/* loaded from: classes9.dex */
public interface IOnStreetViewPanoramaCameraChangeListener extends IInterface {
    void onStreetViewPanoramaCameraChange(StreetViewPanoramaCamera streetViewPanoramaCamera);

    public static abstract class Stub extends Binder implements IOnStreetViewPanoramaCameraChangeListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.huawei.hms.maps.internal.IOnStreetViewPanoramaCameraChangeListener");
        }
    }
}
