package com.google.android.clockwork.companion.partnerapi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* loaded from: classes8.dex */
public interface PartnerHostedApi extends IInterface {
    public static final String DESCRIPTOR = "com.google.android.clockwork.companion.partnerapi.PartnerHostedApi";

    SmartWatchInfo getPendingPairingSmartWatchInfo() throws RemoteException;

    public static abstract class Stub extends Binder implements PartnerHostedApi {
        static final int TRANSACTION_getPendingPairingSmartWatchInfo = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, PartnerHostedApi.DESCRIPTOR);
        }

        public static PartnerHostedApi asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(PartnerHostedApi.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof PartnerHostedApi)) {
                return (PartnerHostedApi) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(PartnerHostedApi.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(PartnerHostedApi.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                SmartWatchInfo pendingPairingSmartWatchInfo = getPendingPairingSmartWatchInfo();
                parcel2.writeNoException();
                b.bQ_(parcel2, pendingPairingSmartWatchInfo, 1);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class a implements PartnerHostedApi {
            private IBinder b;

            a(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.google.android.clockwork.companion.partnerapi.PartnerHostedApi
            public SmartWatchInfo getPendingPairingSmartWatchInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(PartnerHostedApi.DESCRIPTOR);
                    this.b.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return (SmartWatchInfo) b.bP_(obtain2, SmartWatchInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class b {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T bP_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void bQ_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
