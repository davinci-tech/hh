package com.huawei.hms.core.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* loaded from: classes4.dex */
public interface IAIDLCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hms.core.aidl.IAIDLCallback";

    public static class a {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T b(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void b(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }

    void call(DataBuffer dataBuffer) throws RemoteException;

    public static abstract class Stub extends Binder implements IAIDLCallback {
        static final int TRANSACTION_call = 1;

        static class a implements IAIDLCallback {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f4481a;

            a(IBinder iBinder) {
                this.f4481a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4481a;
            }

            @Override // com.huawei.hms.core.aidl.IAIDLCallback
            public void call(DataBuffer dataBuffer) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAIDLCallback.DESCRIPTOR);
                    a.b(obtain, dataBuffer, 0);
                    this.f4481a.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IAIDLCallback.DESCRIPTOR);
        }

        public static IAIDLCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IAIDLCallback.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IAIDLCallback)) ? new a(iBinder) : (IAIDLCallback) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IAIDLCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IAIDLCallback.DESCRIPTOR);
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            call((DataBuffer) a.b(parcel, DataBuffer.CREATOR));
            return true;
        }
    }
}
