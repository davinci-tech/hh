package android.emcom;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface IHandoffSdkCallback extends IInterface {

    /* loaded from: classes8.dex */
    public static class Default implements IHandoffSdkCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.emcom.IHandoffSdkCallback
        public void handoffDataEvent(String str) throws RemoteException {
        }

        @Override // android.emcom.IHandoffSdkCallback
        public void handoffStateChg(int i) throws RemoteException {
        }
    }

    void handoffDataEvent(String str) throws RemoteException;

    void handoffStateChg(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IHandoffSdkCallback {
        private static final String DESCRIPTOR = "android.emcom.IHandoffSdkCallback";
        static final int TRANSACTION_handoffDataEvent = 2;
        static final int TRANSACTION_handoffStateChg = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IHandoffSdkCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IHandoffSdkCallback)) {
                return (IHandoffSdkCallback) queryLocalInterface;
            }
            return new c(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                handoffStateChg(parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                handoffDataEvent(parcel.readString());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        /* loaded from: classes8.dex */
        static class c implements IHandoffSdkCallback {
            public static IHandoffSdkCallback e;
            private IBinder c;

            c(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // android.emcom.IHandoffSdkCallback
            public void handoffStateChg(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.c.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().handoffStateChg(i);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // android.emcom.IHandoffSdkCallback
            public void handoffDataEvent(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.c.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().handoffDataEvent(str);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IHandoffSdkCallback iHandoffSdkCallback) {
            if (c.e != null || iHandoffSdkCallback == null) {
                return false;
            }
            c.e = iHandoffSdkCallback;
            return true;
        }

        public static IHandoffSdkCallback getDefaultImpl() {
            return c.e;
        }
    }
}
