package com.huawei.contentsensor.sdk;

import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes3.dex */
public interface ICaptureDataService extends IInterface {
    int subscribeFeatureData(long j, ComponentName componentName, String str) throws RemoteException;

    int unSubscribeFeatureData(long j, ComponentName componentName) throws RemoteException;

    public static abstract class Stub extends Binder implements ICaptureDataService {
        private static final String DESCRIPTOR = "com.huawei.contentsensor.sdk.ICaptureDataService";
        static final int TRANSACTION_subscribeFeatureData = 1;
        static final int TRANSACTION_unSubscribeFeatureData = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICaptureDataService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ICaptureDataService)) {
                return (ICaptureDataService) queryLocalInterface;
            }
            return new d(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                int subscribeFeatureData = subscribeFeatureData(parcel.readLong(), parcel.readInt() != 0 ? (ComponentName) ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(subscribeFeatureData);
                return true;
            }
            if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            int unSubscribeFeatureData = unSubscribeFeatureData(parcel.readLong(), parcel.readInt() != 0 ? (ComponentName) ComponentName.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            parcel2.writeInt(unSubscribeFeatureData);
            return true;
        }

        static class d implements ICaptureDataService {
            private IBinder d;

            d(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.contentsensor.sdk.ICaptureDataService
            public int subscribeFeatureData(long j, ComponentName componentName, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    if (componentName != null) {
                        obtain.writeInt(1);
                        componentName.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    this.d.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.contentsensor.sdk.ICaptureDataService
            public int unSubscribeFeatureData(long j, ComponentName componentName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    if (componentName != null) {
                        obtain.writeInt(1);
                        componentName.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.d.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
