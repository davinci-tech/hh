package com.huawei.hiai.awareness.client;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes4.dex */
public interface OnEnvelopeReceiver extends IInterface {

    /* loaded from: classes8.dex */
    public static class Default implements OnEnvelopeReceiver {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hiai.awareness.client.OnEnvelopeReceiver
        public void onReceive(AwarenessEnvelope awarenessEnvelope) throws RemoteException {
        }
    }

    void onReceive(AwarenessEnvelope awarenessEnvelope) throws RemoteException;

    public static abstract class Stub extends Binder implements OnEnvelopeReceiver {
        private static final String DESCRIPTOR = "com.huawei.hiai.awareness.client.OnEnvelopeReceiver";
        static final int TRANSACTION_onReceive = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static OnEnvelopeReceiver asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof OnEnvelopeReceiver)) {
                return (OnEnvelopeReceiver) queryLocalInterface;
            }
            return new Proxy(iBinder);
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
            onReceive(parcel.readInt() != 0 ? AwarenessEnvelope.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }

        static class Proxy implements OnEnvelopeReceiver {
            public static OnEnvelopeReceiver sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.hiai.awareness.client.OnEnvelopeReceiver
            public void onReceive(AwarenessEnvelope awarenessEnvelope) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (awarenessEnvelope != null) {
                        obtain.writeInt(1);
                        awarenessEnvelope.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onReceive(awarenessEnvelope);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }
        }

        public static boolean setDefaultImpl(OnEnvelopeReceiver onEnvelopeReceiver) {
            if (Proxy.sDefaultImpl != null || onEnvelopeReceiver == null) {
                return false;
            }
            Proxy.sDefaultImpl = onEnvelopeReceiver;
            return true;
        }

        public static OnEnvelopeReceiver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
