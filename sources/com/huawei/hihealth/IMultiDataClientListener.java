package com.huawei.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public interface IMultiDataClientListener extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hihealth.IMultiDataClientListener";

    public static class Default implements IMultiDataClientListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hihealth.IMultiDataClientListener
        public void onMergeTypeResult(List list) throws RemoteException {
        }

        @Override // com.huawei.hihealth.IMultiDataClientListener
        public void onMultiTypeResult(Map map) throws RemoteException {
        }
    }

    void onMergeTypeResult(List list) throws RemoteException;

    void onMultiTypeResult(Map map) throws RemoteException;

    public static abstract class Stub extends Binder implements IMultiDataClientListener {
        static final int TRANSACTION_onMergeTypeResult = 2;
        static final int TRANSACTION_onMultiTypeResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IMultiDataClientListener.DESCRIPTOR);
        }

        public static IMultiDataClientListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IMultiDataClientListener.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IMultiDataClientListener)) {
                return (IMultiDataClientListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IMultiDataClientListener.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IMultiDataClientListener.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onMultiTypeResult(parcel.readHashMap(getClass().getClassLoader()));
                parcel2.writeNoException();
            } else if (i == 2) {
                onMergeTypeResult(parcel.readArrayList(getClass().getClassLoader()));
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class Proxy implements IMultiDataClientListener {
            private IBinder d;

            Proxy(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.hihealth.IMultiDataClientListener
            public void onMultiTypeResult(Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMultiDataClientListener.DESCRIPTOR);
                    obtain.writeMap(map);
                    this.d.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.IMultiDataClientListener
            public void onMergeTypeResult(List list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMultiDataClientListener.DESCRIPTOR);
                    obtain.writeList(list);
                    this.d.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
