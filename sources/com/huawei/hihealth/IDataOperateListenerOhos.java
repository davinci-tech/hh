package com.huawei.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* loaded from: classes.dex */
public interface IDataOperateListenerOhos extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hihealth.IDataOperateListenerOhos";

    public static class Default implements IDataOperateListenerOhos {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hihealth.IDataOperateListenerOhos
        public void onResult(int i, List<String> list) throws RemoteException {
        }
    }

    void onResult(int i, List<String> list) throws RemoteException;

    public static abstract class Stub extends Binder implements IDataOperateListenerOhos {
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IDataOperateListenerOhos.DESCRIPTOR);
        }

        public static IDataOperateListenerOhos asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IDataOperateListenerOhos.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDataOperateListenerOhos)) {
                return (IDataOperateListenerOhos) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IDataOperateListenerOhos.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IDataOperateListenerOhos.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onResult(parcel.readInt(), parcel.createStringArrayList());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class Proxy implements IDataOperateListenerOhos {
            private IBinder e;

            Proxy(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.huawei.hihealth.IDataOperateListenerOhos
            public void onResult(int i, List<String> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDataOperateListenerOhos.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStringList(list);
                    this.e.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
