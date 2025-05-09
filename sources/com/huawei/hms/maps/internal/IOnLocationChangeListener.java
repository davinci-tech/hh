package com.huawei.hms.maps.internal;

import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes9.dex */
public interface IOnLocationChangeListener extends IInterface {
    void onLocationChange(Location location);

    public static abstract class Stub extends Binder implements IOnLocationChangeListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        static class Proxy implements IOnLocationChangeListener {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f4960a;

            @Override // com.huawei.hms.maps.internal.IOnLocationChangeListener
            public void onLocationChange(Location location) {
                com.huawei.hms.maps.model.internal.mab.a(this.f4960a, "com.huawei.hms.maps.internal.IOnLocationChangeListener", 1, location);
            }

            public String getInterfaceDescriptor() {
                return "com.huawei.hms.maps.internal.IOnLocationChangeListener";
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4960a;
            }

            Proxy(IBinder iBinder) {
                this.f4960a = iBinder;
            }
        }

        public static IOnLocationChangeListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.maps.internal.IOnLocationChangeListener");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IOnLocationChangeListener)) ? new Proxy(iBinder) : (IOnLocationChangeListener) queryLocalInterface;
        }
    }
}
