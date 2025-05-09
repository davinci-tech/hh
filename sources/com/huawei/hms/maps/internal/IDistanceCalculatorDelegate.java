package com.huawei.hms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import com.huawei.hms.maps.model.LatLng;

/* loaded from: classes4.dex */
public interface IDistanceCalculatorDelegate extends IInterface {
    double computeDistanceBetween(LatLng latLng, LatLng latLng2);

    public static abstract class Stub extends Binder implements IDistanceCalculatorDelegate {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        static class Proxy implements IDistanceCalculatorDelegate {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f4954a;

            public String getInterfaceDescriptor() {
                return "com.huawei.hms.maps.internal.IDistanceCalculatorDelegate";
            }

            @Override // com.huawei.hms.maps.internal.IDistanceCalculatorDelegate
            public double computeDistanceBetween(LatLng latLng, LatLng latLng2) {
                return com.huawei.hms.maps.model.internal.mab.a(this.f4954a, "com.huawei.hms.maps.internal.IDistanceCalculatorDelegate", 1, 2, latLng, latLng2).doubleValue();
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4954a;
            }

            Proxy(IBinder iBinder) {
                this.f4954a = iBinder;
            }
        }

        public static IDistanceCalculatorDelegate asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.maps.internal.IDistanceCalculatorDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IDistanceCalculatorDelegate)) ? new Proxy(iBinder) : (IDistanceCalculatorDelegate) queryLocalInterface;
        }

        public Stub() {
            attachInterface(this, "com.huawei.hms.maps.internal.IDistanceCalculatorDelegate");
        }
    }
}
