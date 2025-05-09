package com.huawei.hms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import com.huawei.hms.maps.model.StreetViewPanoramaLocation;

/* loaded from: classes9.dex */
public interface IOnStreetViewPanoramaChangeListener extends IInterface {
    void onStreetViewPanoramaChange(StreetViewPanoramaLocation streetViewPanoramaLocation);

    public static abstract class Stub extends Binder implements IOnStreetViewPanoramaChangeListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.huawei.hms.maps.internal.IOnStreetViewPanoramaChangeListener");
        }
    }
}
