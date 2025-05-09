package com.huawei.hwsmartinteractmgr;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.hwsmartinteractmgr.ISmartMsgReadResultListener;

/* loaded from: classes.dex */
public interface ISmartInteract extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hwsmartinteractmgr.ISmartInteract";

    void checkSmartMsg(int i, ISmartMsgReadResultListener iSmartMsgReadResultListener) throws RemoteException;

    public static abstract class Stub extends Binder implements ISmartInteract {
        static final int TRANSACTION_checkSmartMsg = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ISmartInteract.DESCRIPTOR);
        }

        public static ISmartInteract asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ISmartInteract.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISmartInteract)) {
                return (ISmartInteract) queryLocalInterface;
            }
            return new e(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ISmartInteract.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(ISmartInteract.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                checkSmartMsg(parcel.readInt(), ISmartMsgReadResultListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        /* loaded from: classes5.dex */
        static class e implements ISmartInteract {
            private IBinder c;

            e(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.hwsmartinteractmgr.ISmartInteract
            public void checkSmartMsg(int i, ISmartMsgReadResultListener iSmartMsgReadResultListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISmartInteract.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iSmartMsgReadResultListener);
                    this.c.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
