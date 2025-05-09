package com.huawei.health;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* loaded from: classes3.dex */
public interface ITrackDataForDeveloper extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.health.ITrackDataForDeveloper";

    void onDataChange(Bundle bundle) throws RemoteException;

    void onStateChanged(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements ITrackDataForDeveloper {
        static final int TRANSACTION_onDataChange = 2;
        static final int TRANSACTION_onStateChanged = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ITrackDataForDeveloper.DESCRIPTOR);
        }

        public static ITrackDataForDeveloper asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ITrackDataForDeveloper.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ITrackDataForDeveloper)) {
                return (ITrackDataForDeveloper) queryLocalInterface;
            }
            return new e(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ITrackDataForDeveloper.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(ITrackDataForDeveloper.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onStateChanged(parcel.readInt());
                parcel2.writeNoException();
            } else {
                if (i != 2) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                Bundle bundle = (Bundle) a.AP_(parcel, Bundle.CREATOR);
                onDataChange(bundle);
                parcel2.writeNoException();
                a.AQ_(parcel2, bundle, 1);
            }
            return true;
        }

        static class e implements ITrackDataForDeveloper {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f2173a;

            e(IBinder iBinder) {
                this.f2173a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f2173a;
            }

            @Override // com.huawei.health.ITrackDataForDeveloper
            public void onStateChanged(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITrackDataForDeveloper.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.f2173a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ITrackDataForDeveloper
            public void onDataChange(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITrackDataForDeveloper.DESCRIPTOR);
                    a.AQ_(obtain, bundle, 0);
                    this.f2173a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        bundle.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class a {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T AP_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void AQ_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
