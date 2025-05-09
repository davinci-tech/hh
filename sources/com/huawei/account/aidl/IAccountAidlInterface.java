package com.huawei.account.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface IAccountAidlInterface extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.account.aidl.IAccountAidlInterface";

    AccountAidlInfo getRemoteAccountInfo() throws RemoteException;

    void makeStInvalid() throws RemoteException;

    public static abstract class Stub extends Binder implements IAccountAidlInterface {
        static final int TRANSACTION_getRemoteAccountInfo = 1;
        static final int TRANSACTION_makeStInvalid = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IAccountAidlInterface.DESCRIPTOR);
        }

        public static IAccountAidlInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IAccountAidlInterface.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAccountAidlInterface)) {
                return (IAccountAidlInterface) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IAccountAidlInterface.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IAccountAidlInterface.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                AccountAidlInfo remoteAccountInfo = getRemoteAccountInfo();
                parcel2.writeNoException();
                c.dl_(parcel2, remoteAccountInfo, 1);
            } else if (i == 2) {
                makeStInvalid();
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class a implements IAccountAidlInterface {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f1669a;

            a(IBinder iBinder) {
                this.f1669a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f1669a;
            }

            @Override // com.huawei.account.aidl.IAccountAidlInterface
            public AccountAidlInfo getRemoteAccountInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAccountAidlInterface.DESCRIPTOR);
                    this.f1669a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return (AccountAidlInfo) c.dk_(obtain2, AccountAidlInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.account.aidl.IAccountAidlInterface
            public void makeStInvalid() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAccountAidlInterface.DESCRIPTOR);
                    this.f1669a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class c {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T dk_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void dl_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
