package com.hihonor.systemserver.activityrecognition;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IActivityRecognitionHardwareSink extends IInterface {
    void onActivityChanged(HwActivityChangedEvent hwActivityChangedEvent) throws RemoteException;

    void onActivityExtendChanged(HwActivityChangedExtendEvent hwActivityChangedExtendEvent) throws RemoteException;

    void onEnvironmentChanged(HwEnvironmentChangedEvent hwEnvironmentChangedEvent) throws RemoteException;

    public static abstract class Stub extends Binder implements IActivityRecognitionHardwareSink {
        private static final String DESCRIPTOR = "com.hihonor.systemserver.activityrecognition.IActivityRecognitionHardwareSink";
        static final int TRANSACTION_onActivityChanged = 1;
        static final int TRANSACTION_onActivityExtendChanged = 2;
        static final int TRANSACTION_onEnvironmentChanged = 3;

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
            return new c(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onActivityChanged(parcel.readInt() != 0 ? HwActivityChangedEvent.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onActivityExtendChanged(parcel.readInt() != 0 ? HwActivityChangedExtendEvent.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i != 3) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            onEnvironmentChanged(parcel.readInt() != 0 ? HwEnvironmentChangedEvent.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }

        /* loaded from: classes2.dex */
        static class c implements IActivityRecognitionHardwareSink {
            private IBinder c;

            c(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.hihonor.systemserver.activityrecognition.IActivityRecognitionHardwareSink
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
                    this.c.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.hihonor.systemserver.activityrecognition.IActivityRecognitionHardwareSink
            public void onActivityExtendChanged(HwActivityChangedExtendEvent hwActivityChangedExtendEvent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (hwActivityChangedExtendEvent != null) {
                        obtain.writeInt(1);
                        hwActivityChangedExtendEvent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.c.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.hihonor.systemserver.activityrecognition.IActivityRecognitionHardwareSink
            public void onEnvironmentChanged(HwEnvironmentChangedEvent hwEnvironmentChangedEvent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (hwEnvironmentChangedEvent != null) {
                        obtain.writeInt(1);
                        hwEnvironmentChangedEvent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.c.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
