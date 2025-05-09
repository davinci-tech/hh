package android.emcom;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface IOneHopAppCallback extends IInterface {

    /* loaded from: classes8.dex */
    public static class Default implements IOneHopAppCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.emcom.IOneHopAppCallback
        public void onOneHopReceived(String str) throws RemoteException {
        }
    }

    void onOneHopReceived(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IOneHopAppCallback {
        private static final String DESCRIPTOR = "android.emcom.IOneHopAppCallback";
        static final int TRANSACTION_onOneHopReceived = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOneHopAppCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IOneHopAppCallback)) {
                return (IOneHopAppCallback) queryLocalInterface;
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
            onOneHopReceived(parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        /* loaded from: classes8.dex */
        static class d implements IOneHopAppCallback {
            public static IOneHopAppCallback b;

            /* renamed from: a, reason: collision with root package name */
            private IBinder f204a;

            d(IBinder iBinder) {
                this.f204a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f204a;
            }

            @Override // android.emcom.IOneHopAppCallback
            public void onOneHopReceived(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.f204a.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onOneHopReceived(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOneHopAppCallback iOneHopAppCallback) {
            if (d.b != null || iOneHopAppCallback == null) {
                return false;
            }
            d.b = iOneHopAppCallback;
            return true;
        }

        public static IOneHopAppCallback getDefaultImpl() {
            return d.b;
        }
    }
}
