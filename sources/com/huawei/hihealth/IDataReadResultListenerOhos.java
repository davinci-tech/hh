package com.huawei.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import java.util.List;

/* loaded from: classes.dex */
public interface IDataReadResultListenerOhos extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hihealth.IDataReadResultListenerOhos";

    public static class Default implements IDataReadResultListenerOhos {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hihealth.IDataReadResultListenerOhos
        public void onResult(List<HiHealthKitDataOhos> list, int i, int i2) throws RemoteException {
        }
    }

    void onResult(List<HiHealthKitDataOhos> list, int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements IDataReadResultListenerOhos {
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IDataReadResultListenerOhos.DESCRIPTOR);
        }

        public static IDataReadResultListenerOhos asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IDataReadResultListenerOhos.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDataReadResultListenerOhos)) {
                return (IDataReadResultListenerOhos) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IDataReadResultListenerOhos.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IDataReadResultListenerOhos.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onResult(parcel.createTypedArrayList(HiHealthKitDataOhos.CREATOR), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class Proxy implements IDataReadResultListenerOhos {
            private IBinder b;

            Proxy(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.hihealth.IDataReadResultListenerOhos
            public void onResult(List<HiHealthKitDataOhos> list, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDataReadResultListenerOhos.DESCRIPTOR);
                    _Parcel.bvd_(obtain, list, 0);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.b.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class _Parcel {
        private static <T extends Parcelable> void bve_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void bvd_(Parcel parcel, List<T> list, int i) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                bve_(parcel, list.get(i2), i);
            }
        }
    }
}
