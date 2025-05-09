package android.emcom;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface IOneHopAuthReqCallback extends IInterface {

    /* loaded from: classes8.dex */
    public static class Default implements IOneHopAuthReqCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.emcom.IOneHopAuthReqCallback
        public void onAuthResult(boolean z) throws RemoteException {
        }
    }

    void onAuthResult(boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements IOneHopAuthReqCallback {
        private static final String DESCRIPTOR = "android.emcom.IOneHopAuthReqCallback";
        static final int TRANSACTION_onAuthResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOneHopAuthReqCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IOneHopAuthReqCallback)) {
                return (IOneHopAuthReqCallback) queryLocalInterface;
            }
            return new a(iBinder);
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
            onAuthResult(parcel.readInt() != 0);
            parcel2.writeNoException();
            return true;
        }

        /* loaded from: classes8.dex */
        static class a implements IOneHopAuthReqCallback {

            /* renamed from: a, reason: collision with root package name */
            public static IOneHopAuthReqCallback f205a;
            private IBinder d;

            a(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // android.emcom.IOneHopAuthReqCallback
            public void onAuthResult(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.d.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAuthResult(z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOneHopAuthReqCallback iOneHopAuthReqCallback) {
            if (a.f205a != null || iOneHopAuthReqCallback == null) {
                return false;
            }
            a.f205a = iOneHopAuthReqCallback;
            return true;
        }

        public static IOneHopAuthReqCallback getDefaultImpl() {
            return a.f205a;
        }
    }
}
