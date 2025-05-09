package com.huawei.multisimservice.model;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* loaded from: classes5.dex */
public interface IAttachedDeviceMultiSimCallback extends IInterface {
    void deleteESimProfileNotify(int i) throws RemoteException;

    void downloadESimProfile(int i, List<SimInfo> list) throws RemoteException;

    void getAttachedDeviceMultiSimInfo(MultiSimDeviceInfo multiSimDeviceInfo) throws RemoteException;

    public static abstract class Stub extends Binder implements IAttachedDeviceMultiSimCallback {
        private static final String DESCRIPTOR = "com.huawei.multisimservice.model.IAttachedDeviceMultiSimCallback";
        static final int TRANSACTION_deleteESimProfileNotify = 3;
        static final int TRANSACTION_downloadESimProfile = 2;
        static final int TRANSACTION_getAttachedDeviceMultiSimInfo = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAttachedDeviceMultiSimCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAttachedDeviceMultiSimCallback)) {
                return (IAttachedDeviceMultiSimCallback) queryLocalInterface;
            }
            return new d(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                getAttachedDeviceMultiSimInfo(parcel.readInt() != 0 ? MultiSimDeviceInfo.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                downloadESimProfile(parcel.readInt(), parcel.createTypedArrayList(SimInfo.CREATOR));
                parcel2.writeNoException();
                return true;
            }
            if (i != 3) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            deleteESimProfileNotify(parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        static class d implements IAttachedDeviceMultiSimCallback {
            public static IAttachedDeviceMultiSimCallback e;

            /* renamed from: a, reason: collision with root package name */
            private IBinder f6543a;

            d(IBinder iBinder) {
                this.f6543a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f6543a;
            }

            @Override // com.huawei.multisimservice.model.IAttachedDeviceMultiSimCallback
            public void getAttachedDeviceMultiSimInfo(MultiSimDeviceInfo multiSimDeviceInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (multiSimDeviceInfo != null) {
                        obtain.writeInt(1);
                        multiSimDeviceInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.f6543a.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getAttachedDeviceMultiSimInfo(multiSimDeviceInfo);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.multisimservice.model.IAttachedDeviceMultiSimCallback
            public void downloadESimProfile(int i, List<SimInfo> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeTypedList(list);
                    if (!this.f6543a.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().downloadESimProfile(i, list);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.multisimservice.model.IAttachedDeviceMultiSimCallback
            public void deleteESimProfileNotify(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.f6543a.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteESimProfileNotify(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAttachedDeviceMultiSimCallback iAttachedDeviceMultiSimCallback) {
            if (d.e != null || iAttachedDeviceMultiSimCallback == null) {
                return false;
            }
            d.e = iAttachedDeviceMultiSimCallback;
            return true;
        }

        public static IAttachedDeviceMultiSimCallback getDefaultImpl() {
            return d.e;
        }
    }
}
