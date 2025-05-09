package com.huawei.hms.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.hms.feature.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public interface IBitmapDescriptorDelegate extends IInterface {
    IObjectWrapper defaultMarker();

    IObjectWrapper defaultMarkerWithHue(float f);

    public static abstract class Stub extends Binder implements IBitmapDescriptorDelegate {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        static class Proxy implements IBitmapDescriptorDelegate {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f5018a;

            public String getInterfaceDescriptor() {
                return "com.huawei.hms.maps.model.internal.IBitmapDescriptorDelegate";
            }

            @Override // com.huawei.hms.maps.model.internal.IBitmapDescriptorDelegate
            public IObjectWrapper defaultMarkerWithHue(float f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.maps.model.internal.IBitmapDescriptorDelegate");
                    obtain.writeFloat(f);
                    this.f5018a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return IObjectWrapper.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.maps.model.internal.IBitmapDescriptorDelegate
            public IObjectWrapper defaultMarker() {
                return IObjectWrapper.Stub.asInterface(mab.j(this.f5018a, "com.huawei.hms.maps.model.internal.IBitmapDescriptorDelegate", 1));
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f5018a;
            }

            Proxy(IBinder iBinder) {
                this.f5018a = iBinder;
            }
        }

        public static IBitmapDescriptorDelegate asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.maps.model.internal.IBitmapDescriptorDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IBitmapDescriptorDelegate)) ? new Proxy(iBinder) : (IBitmapDescriptorDelegate) queryLocalInterface;
        }
    }
}
