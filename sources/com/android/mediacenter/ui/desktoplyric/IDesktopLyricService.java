package com.android.mediacenter.ui.desktoplyric;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes8.dex */
public interface IDesktopLyricService extends IInterface {
    public static final String DESCRIPTOR = "com.android.mediacenter.ui.desktoplyric.IDesktopLyricService";

    public static class Default implements IDesktopLyricService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.mediacenter.ui.desktoplyric.IDesktopLyricService
        public void startDesktopLyric(boolean z) throws RemoteException {
        }

        @Override // com.android.mediacenter.ui.desktoplyric.IDesktopLyricService
        public void stopDesktopLyric() throws RemoteException {
        }
    }

    void startDesktopLyric(boolean z) throws RemoteException;

    void stopDesktopLyric() throws RemoteException;

    public static abstract class Stub extends Binder implements IDesktopLyricService {
        static final int TRANSACTION_startDesktopLyric = 1;
        static final int TRANSACTION_stopDesktopLyric = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IDesktopLyricService.DESCRIPTOR);
        }

        public static IDesktopLyricService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IDesktopLyricService.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDesktopLyricService)) {
                return (IDesktopLyricService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IDesktopLyricService.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IDesktopLyricService.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                startDesktopLyric(parcel.readInt() != 0);
                parcel2.writeNoException();
            } else if (i == 2) {
                stopDesktopLyric();
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class Proxy implements IDesktopLyricService {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.android.mediacenter.ui.desktoplyric.IDesktopLyricService
            public void startDesktopLyric(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDesktopLyricService.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.mediacenter.ui.desktoplyric.IDesktopLyricService
            public void stopDesktopLyric() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDesktopLyricService.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return IDesktopLyricService.DESCRIPTOR;
            }
        }
    }
}
