package com.huawei.android.airsharing.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface IRemoteCtrlEventProcessor extends IInterface {
    int process(int i, int i2, byte[] bArr) throws RemoteException;

    public static abstract class Stub extends Binder implements IRemoteCtrlEventProcessor {
        private static final String DESCRIPTOR = "com.huawei.android.airsharing.api.IRemoteCtrlEventProcessor";
        static final int TRANSACTION_process = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRemoteCtrlEventProcessor asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRemoteCtrlEventProcessor)) {
                return (IRemoteCtrlEventProcessor) queryLocalInterface;
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
            int process = process(parcel.readInt(), parcel.readInt(), parcel.createByteArray());
            parcel2.writeNoException();
            parcel2.writeInt(process);
            return true;
        }

        static class a implements IRemoteCtrlEventProcessor {

            /* renamed from: a, reason: collision with root package name */
            public static IRemoteCtrlEventProcessor f1821a;
            private IBinder b;

            a(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.android.airsharing.api.IRemoteCtrlEventProcessor
            public int process(int i, int i2, byte[] bArr) throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeByteArray(bArr);
                    if (!this.b.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().process(i, i2, bArr);
                    } else {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRemoteCtrlEventProcessor iRemoteCtrlEventProcessor) {
            if (a.f1821a != null || iRemoteCtrlEventProcessor == null) {
                return false;
            }
            a.f1821a = iRemoteCtrlEventProcessor;
            return true;
        }

        public static IRemoteCtrlEventProcessor getDefaultImpl() {
            return a.f1821a;
        }
    }
}
