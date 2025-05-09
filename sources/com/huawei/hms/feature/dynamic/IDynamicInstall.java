package com.huawei.hms.feature.dynamic;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.hms.feature.dynamic.IObjectWrapper;

/* loaded from: classes9.dex */
public interface IDynamicInstall extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hms.feature.dynamic.IDynamicInstall";

    public static class Default implements IDynamicInstall {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hms.feature.dynamic.IDynamicInstall
        public Bundle install(IObjectWrapper iObjectWrapper, Bundle bundle) throws RemoteException {
            return null;
        }
    }

    Bundle install(IObjectWrapper iObjectWrapper, Bundle bundle) throws RemoteException;

    public static abstract class Stub extends Binder implements IDynamicInstall {

        /* renamed from: a, reason: collision with root package name */
        public static final int f4500a = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static class Proxy implements IDynamicInstall {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f4501a;

            @Override // com.huawei.hms.feature.dynamic.IDynamicInstall
            public Bundle install(IObjectWrapper iObjectWrapper, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDynamicInstall.DESCRIPTOR);
                    obtain.writeStrongInterface(iObjectWrapper);
                    _Parcel.b(obtain, bundle, 0);
                    this.f4501a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return (Bundle) _Parcel.b(obtain2, Bundle.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return IDynamicInstall.DESCRIPTOR;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4501a;
            }

            public Proxy(IBinder iBinder) {
                this.f4501a = iBinder;
            }
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IDynamicInstall.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IDynamicInstall.DESCRIPTOR);
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            Bundle install = install(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (Bundle) _Parcel.b(parcel, Bundle.CREATOR));
            parcel2.writeNoException();
            _Parcel.b(parcel2, install, 1);
            return true;
        }

        public static IDynamicInstall asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IDynamicInstall.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IDynamicInstall)) ? new Proxy(iBinder) : (IDynamicInstall) queryLocalInterface;
        }

        public Stub() {
            attachInterface(this, IDynamicInstall.DESCRIPTOR);
        }
    }

    public static class _Parcel {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void b(Parcel parcel, T t, int i) {
            if (t == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T b(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }
    }
}
