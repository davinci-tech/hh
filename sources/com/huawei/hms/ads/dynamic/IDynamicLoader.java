package com.huawei.hms.ads.dynamic;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.hms.ads.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public interface IDynamicLoader extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hms.ads.dynamic.IDynamicLoader";

    /* loaded from: classes9.dex */
    public static class Default implements IDynamicLoader {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hms.ads.dynamic.IDynamicLoader
        public IObjectWrapper load(IObjectWrapper iObjectWrapper, String str, int i, IObjectWrapper iObjectWrapper2) throws RemoteException {
            return null;
        }
    }

    IObjectWrapper load(IObjectWrapper iObjectWrapper, String str, int i, IObjectWrapper iObjectWrapper2) throws RemoteException;

    public static abstract class Stub extends Binder implements IDynamicLoader {

        /* renamed from: a, reason: collision with root package name */
        static final int f4298a = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(IDynamicLoader.DESCRIPTOR);
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(IDynamicLoader.DESCRIPTOR);
            IObjectWrapper load = load(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt(), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            parcel2.writeStrongBinder(load != null ? load.asBinder() : null);
            return true;
        }

        static class Proxy implements IDynamicLoader {
            public static IDynamicLoader sDefaultImpl;

            /* renamed from: a, reason: collision with root package name */
            private IBinder f4299a;

            @Override // com.huawei.hms.ads.dynamic.IDynamicLoader
            public IObjectWrapper load(IObjectWrapper iObjectWrapper, String str, int i, IObjectWrapper iObjectWrapper2) throws RemoteException {
                IObjectWrapper asInterface;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDynamicLoader.DESCRIPTOR);
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iObjectWrapper2 != null ? iObjectWrapper2.asBinder() : null);
                    if (this.f4299a.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        asInterface = IObjectWrapper.Stub.asInterface(obtain2.readStrongBinder());
                    } else {
                        asInterface = Stub.getDefaultImpl().load(iObjectWrapper, str, i, iObjectWrapper2);
                    }
                    return asInterface;
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
                return this.f4299a;
            }

            Proxy(IBinder iBinder) {
                this.f4299a = iBinder;
            }
        }

        public static boolean setDefaultImpl(IDynamicLoader iDynamicLoader) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDynamicLoader == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDynamicLoader;
            return true;
        }

        public static IDynamicLoader getDefaultImpl() {
            return Proxy.sDefaultImpl;
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
