package com.huawei.hms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import com.huawei.hms.maps.model.CoordinateLatLng;

/* loaded from: classes4.dex */
public interface ICoordinateConverterDelegate extends IInterface {
    CoordinateLatLng[] rectifyCoordinate(CoordinateLatLng[] coordinateLatLngArr);

    public static abstract class Stub extends Binder implements ICoordinateConverterDelegate {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        static class Proxy implements ICoordinateConverterDelegate {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f4952a;

            @Override // com.huawei.hms.maps.internal.ICoordinateConverterDelegate
            public CoordinateLatLng[] rectifyCoordinate(CoordinateLatLng[] coordinateLatLngArr) {
                return com.huawei.hms.maps.model.internal.mab.a(coordinateLatLngArr, this.f4952a, "com.huawei.hms.maps.internal.ICoordinateConverterDelegate", 1);
            }

            public String getInterfaceDescriptor() {
                return "com.huawei.hms.maps.internal.ICoordinateConverterDelegate";
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4952a;
            }

            Proxy(IBinder iBinder) {
                this.f4952a = iBinder;
            }
        }

        public static ICoordinateConverterDelegate asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.maps.internal.ICoordinateConverterDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ICoordinateConverterDelegate)) ? new Proxy(iBinder) : (ICoordinateConverterDelegate) queryLocalInterface;
        }

        public Stub() {
            attachInterface(this, "com.huawei.hms.maps.internal.ICoordinateConverterDelegate");
        }
    }
}
