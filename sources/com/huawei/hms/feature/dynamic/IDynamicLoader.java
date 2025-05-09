package com.huawei.hms.feature.dynamic;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.hms.feature.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public interface IDynamicLoader extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hms.feature.dynamic.IDynamicLoader";

    /* loaded from: classes9.dex */
    public static class Default implements IDynamicLoader {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hms.feature.dynamic.IDynamicLoader
        public IObjectWrapper load(IObjectWrapper iObjectWrapper, String str, int i, IObjectWrapper iObjectWrapper2) throws RemoteException {
            return null;
        }
    }

    IObjectWrapper load(IObjectWrapper iObjectWrapper, String str, int i, IObjectWrapper iObjectWrapper2) throws RemoteException;

    public static abstract class Stub extends Binder implements IDynamicLoader {

        /* renamed from: a, reason: collision with root package name */
        public static final int f4502a = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static class Proxy implements IDynamicLoader {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f4503a;

            @Override // com.huawei.hms.feature.dynamic.IDynamicLoader
            public IObjectWrapper load(IObjectWrapper iObjectWrapper, String str, int i, IObjectWrapper iObjectWrapper2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDynamicLoader.DESCRIPTOR);
                    obtain.writeStrongInterface(iObjectWrapper);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iObjectWrapper2);
                    this.f4503a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return IObjectWrapper.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return IDynamicLoader.DESCRIPTOR;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4503a;
            }

            public Proxy(IBinder iBinder) {
                this.f4503a = iBinder;
            }
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IDynamicLoader.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IDynamicLoader.DESCRIPTOR);
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            IObjectWrapper load = load(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt(), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            parcel2.writeStrongInterface(load);
            return true;
        }

        public static IDynamicLoader asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IDynamicLoader.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IDynamicLoader)) ? new Proxy(iBinder) : (IDynamicLoader) queryLocalInterface;
        }

        public Stub() {
            attachInterface(this, IDynamicLoader.DESCRIPTOR);
        }
    }
}
