package com.huawei.hms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import com.huawei.hms.maps.model.StreetViewPanoramaOrientation;

/* loaded from: classes9.dex */
public interface IOnStreetViewPanoramaLongClickListener extends IInterface {
    void onStreetViewPanoramaLongClick(StreetViewPanoramaOrientation streetViewPanoramaOrientation);

    public static abstract class Stub extends Binder implements IOnStreetViewPanoramaLongClickListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.huawei.hms.maps.internal.IOnStreetViewPanoramaLongClickListener");
        }
    }
}
