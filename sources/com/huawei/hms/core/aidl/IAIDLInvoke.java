package com.huawei.hms.core.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.hms.core.aidl.IAIDLCallback;

/* loaded from: classes4.dex */
public interface IAIDLInvoke extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hms.core.aidl.IAIDLInvoke";

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

    void asyncCall(DataBuffer dataBuffer, IAIDLCallback iAIDLCallback) throws RemoteException;

    void syncCall(DataBuffer dataBuffer) throws RemoteException;

    public static abstract class Stub extends Binder implements IAIDLInvoke {

        static class a implements IAIDLInvoke {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f4482a;

            a(IBinder iBinder) {
                this.f4482a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4482a;
            }

            @Override // com.huawei.hms.core.aidl.IAIDLInvoke
            public void asyncCall(DataBuffer dataBuffer, IAIDLCallback iAIDLCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAIDLInvoke.DESCRIPTOR);
                    a.b(obtain, dataBuffer, 0);
                    obtain.writeStrongInterface(iAIDLCallback);
                    this.f4482a.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.core.aidl.IAIDLInvoke
            public void syncCall(DataBuffer dataBuffer) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAIDLInvoke.DESCRIPTOR);
                    a.b(obtain, dataBuffer, 0);
                    this.f4482a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IAIDLInvoke.DESCRIPTOR);
        }

        public static IAIDLInvoke asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IAIDLInvoke.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IAIDLInvoke)) ? new a(iBinder) : (IAIDLInvoke) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IAIDLInvoke.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IAIDLInvoke.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                syncCall((DataBuffer) a.b(parcel, DataBuffer.CREATOR));
                parcel2.writeNoException();
            } else {
                if (i != 2) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                asyncCall((DataBuffer) a.b(parcel, DataBuffer.CREATOR), IAIDLCallback.Stub.asInterface(parcel.readStrongBinder()));
            }
            return true;
        }
    }
}
