package com.hihonor.android.location.activityrecognition;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IActivityRecognitionHardwareSink extends IInterface {
    void onActivityChanged(HwActivityChangedEvent hwActivityChangedEvent) throws RemoteException;

    public static abstract class Stub extends Binder implements IActivityRecognitionHardwareSink {
        private static final String DESCRIPTOR = "com.hihonor.android.location.activityrecognition.IActivityRecognitionHardwareSink";
        static final int TRANSACTION_onActivityChanged = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IActivityRecognitionHardwareSink asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IActivityRecognitionHardwareSink)) {
                return (IActivityRecognitionHardwareSink) queryLocalInterface;
            }
            return new d(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            onActivityChanged(parcel.readInt() != 0 ? HwActivityChangedEvent.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }

        /* loaded from: classes2.dex */
        static class d implements IActivityRecognitionHardwareSink {
            private IBinder e;

            d(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.hihonor.android.location.activityrecognition.IActivityRecognitionHardwareSink
            public void onActivityChanged(HwActivityChangedEvent hwActivityChangedEvent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (hwActivityChangedEvent != null) {
                        obtain.writeInt(1);
                        hwActivityChangedEvent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
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
