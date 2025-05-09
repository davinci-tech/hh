package com.huawei.android.powerkit.adapter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface IStateSink extends IInterface {
    void onPowerBaseLineUsing(String str, String str2, long j, int i, String str3) throws RemoteException;

    void onPowerOverUsing(String str, int i, long j, long j2, String str2) throws RemoteException;

    void onStateChanged(int i, int i2, int i3, String str, int i4) throws RemoteException;

    public static abstract class Stub extends Binder implements IStateSink {
        private static final String DESCRIPTOR = "com.huawei.android.powerkit.adapter.IStateSink";
        static final int TRANSACTION_onPowerBaseLineUsing = 3;
        static final int TRANSACTION_onPowerOverUsing = 1;
        static final int TRANSACTION_onStateChanged = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IStateSink asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IStateSink)) {
                return (IStateSink) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onPowerOverUsing(parcel.readString(), parcel.readInt(), parcel.readLong(), parcel.readLong(), parcel.readString());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onStateChanged(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onPowerBaseLineUsing(parcel.readString(), parcel.readString(), parcel.readLong(), parcel.readInt(), parcel.readString());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class a implements IStateSink {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f1848a;

            a(IBinder iBinder) {
                this.f1848a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f1848a;
            }

            @Override // com.huawei.android.powerkit.adapter.IStateSink
            public void onPowerOverUsing(String str, int i, long j, long j2, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    obtain.writeString(str2);
                    this.f1848a.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.powerkit.adapter.IStateSink
            public void onStateChanged(int i, int i2, int i3, String str, int i4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeString(str);
                    obtain.writeInt(i4);
                    this.f1848a.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.powerkit.adapter.IStateSink
            public void onPowerBaseLineUsing(String str, String str2, long j, int i, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeLong(j);
                    obtain.writeInt(i);
                    obtain.writeString(str3);
                    this.f1848a.transact(3, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }
}
