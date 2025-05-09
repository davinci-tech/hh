package com.huawei.hms.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes9.dex */
public interface ICustomLayerDelegate extends IInterface {

    public static class Default implements ICustomLayerDelegate {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hms.maps.model.internal.ICustomLayerDelegate
        public void remove() {
        }
    }

    void remove();

    public static abstract class Stub extends Binder implements ICustomLayerDelegate {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.huawei.hms.maps.model.internal.ICustomLayerDelegate");
                return true;
            }
            parcel.enforceInterface("com.huawei.hms.maps.model.internal.ICustomLayerDelegate");
            remove();
            parcel2.writeNoException();
            return true;
        }

        static class Proxy implements ICustomLayerDelegate {
            public static ICustomLayerDelegate sDefaultImpl;

            /* renamed from: a, reason: collision with root package name */
            private IBinder f5020a;

            @Override // com.huawei.hms.maps.model.internal.ICustomLayerDelegate
            public void remove() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.maps.model.internal.ICustomLayerDelegate");
                    if (this.f5020a.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().remove();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return "com.huawei.hms.maps.model.internal.ICustomLayerDelegate";
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f5020a;
            }

            Proxy(IBinder iBinder) {
                this.f5020a = iBinder;
            }
        }

        public static boolean setDefaultImpl(ICustomLayerDelegate iCustomLayerDelegate) {
            if (Proxy.sDefaultImpl != null || iCustomLayerDelegate == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCustomLayerDelegate;
            return true;
        }

        public static ICustomLayerDelegate getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static ICustomLayerDelegate asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.maps.model.internal.ICustomLayerDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ICustomLayerDelegate)) ? new Proxy(iBinder) : (ICustomLayerDelegate) queryLocalInterface;
        }

        public Stub() {
            attachInterface(this, "com.huawei.hms.maps.model.internal.ICustomLayerDelegate");
        }
    }
}
