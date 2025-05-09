package com.huawei.hms.hmsscankit.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.hms.feature.dynamic.IObjectWrapper;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.ml.scan.HmsScanResult;

/* loaded from: classes4.dex */
public interface IRemoteDecoderDelegate extends IInterface {

    /* loaded from: classes9.dex */
    public static class Default implements IRemoteDecoderDelegate {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hms.hmsscankit.api.IRemoteDecoderDelegate
        public IObjectWrapper buildBitmap(IObjectWrapper iObjectWrapper) throws RemoteException {
            return null;
        }

        @Override // com.huawei.hms.hmsscankit.api.IRemoteDecoderDelegate
        public void buildBitmapLog(IObjectWrapper iObjectWrapper) throws RemoteException {
        }

        @Override // com.huawei.hms.hmsscankit.api.IRemoteDecoderDelegate
        public HmsScan[] decodeWithBitmap(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException {
            return null;
        }

        @Override // com.huawei.hms.hmsscankit.api.IRemoteDecoderDelegate
        public HmsScanResult decodeWithBuffer(byte[] bArr, int i, int i2, IObjectWrapper iObjectWrapper) throws RemoteException {
            return null;
        }

        @Override // com.huawei.hms.hmsscankit.api.IRemoteDecoderDelegate
        public IObjectWrapper queryDeepLinkInfo(IObjectWrapper iObjectWrapper) throws RemoteException {
            return null;
        }
    }

    IObjectWrapper buildBitmap(IObjectWrapper iObjectWrapper) throws RemoteException;

    void buildBitmapLog(IObjectWrapper iObjectWrapper) throws RemoteException;

    HmsScan[] decodeWithBitmap(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException;

    HmsScanResult decodeWithBuffer(byte[] bArr, int i, int i2, IObjectWrapper iObjectWrapper) throws RemoteException;

    IObjectWrapper queryDeepLinkInfo(IObjectWrapper iObjectWrapper) throws RemoteException;

    public static abstract class Stub extends Binder implements IRemoteDecoderDelegate {
        private static final String DESCRIPTOR = "com.huawei.hms.hmsscankit.api.IRemoteDecoderDelegate";
        static final int TRANSACTION_buildBitmap = 4;
        static final int TRANSACTION_buildBitmapLog = 3;
        static final int TRANSACTION_decodeWithBitmap = 1;
        static final int TRANSACTION_decodeWithBuffer = 2;
        static final int TRANSACTION_queryDeepLinkInfo = 5;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRemoteDecoderDelegate asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IRemoteDecoderDelegate)) ? new Proxy(iBinder) : (IRemoteDecoderDelegate) queryLocalInterface;
        }

        public static IRemoteDecoderDelegate getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IRemoteDecoderDelegate iRemoteDecoderDelegate) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRemoteDecoderDelegate == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRemoteDecoderDelegate;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                HmsScan[] decodeWithBitmap = decodeWithBitmap(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeTypedArray(decodeWithBitmap, 1);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                HmsScanResult decodeWithBuffer = decodeWithBuffer(parcel.createByteArray(), parcel.readInt(), parcel.readInt(), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                if (decodeWithBuffer != null) {
                    parcel2.writeInt(1);
                    decodeWithBuffer.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                buildBitmapLog(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                IObjectWrapper buildBitmap = buildBitmap(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeStrongBinder(buildBitmap != null ? buildBitmap.asBinder() : null);
                return true;
            }
            if (i != 5) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            IObjectWrapper queryDeepLinkInfo = queryDeepLinkInfo(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            parcel2.writeStrongBinder(queryDeepLinkInfo != null ? queryDeepLinkInfo.asBinder() : null);
            return true;
        }

        static class Proxy implements IRemoteDecoderDelegate {
            public static IRemoteDecoderDelegate sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.hms.hmsscankit.api.IRemoteDecoderDelegate
            public IObjectWrapper buildBitmap(IObjectWrapper iObjectWrapper) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().buildBitmap(iObjectWrapper);
                    }
                    obtain2.readException();
                    return IObjectWrapper.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.hmsscankit.api.IRemoteDecoderDelegate
            public void buildBitmapLog(IObjectWrapper iObjectWrapper) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().buildBitmapLog(iObjectWrapper);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.hmsscankit.api.IRemoteDecoderDelegate
            public HmsScan[] decodeWithBitmap(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    obtain.writeStrongBinder(iObjectWrapper2 != null ? iObjectWrapper2.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().decodeWithBitmap(iObjectWrapper, iObjectWrapper2);
                    }
                    obtain2.readException();
                    return (HmsScan[]) obtain2.createTypedArray(HmsScan.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.hmsscankit.api.IRemoteDecoderDelegate
            public HmsScanResult decodeWithBuffer(byte[] bArr, int i, int i2, IObjectWrapper iObjectWrapper) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().decodeWithBuffer(bArr, i, i2, iObjectWrapper);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? HmsScanResult.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.hmsscankit.api.IRemoteDecoderDelegate
            public IObjectWrapper queryDeepLinkInfo(IObjectWrapper iObjectWrapper) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryDeepLinkInfo(iObjectWrapper);
                    }
                    obtain2.readException();
                    return IObjectWrapper.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }
        }
    }
}
