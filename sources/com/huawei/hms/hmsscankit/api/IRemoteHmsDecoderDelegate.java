package com.huawei.hms.hmsscankit.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.hms.feature.dynamic.IObjectWrapper;
import com.huawei.hms.hmsscankit.DetailRect;
import com.huawei.hms.ml.scan.HmsScan;

/* loaded from: classes4.dex */
public interface IRemoteHmsDecoderDelegate extends IInterface {

    /* loaded from: classes9.dex */
    public static class Default implements IRemoteHmsDecoderDelegate {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hms.hmsscankit.api.IRemoteHmsDecoderDelegate
        public HmsScan[] decodeInBitmap(DetailRect detailRect, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException {
            return null;
        }

        @Override // com.huawei.hms.hmsscankit.api.IRemoteHmsDecoderDelegate
        public HmsScan[] detectWithByteBuffer(DetailRect detailRect, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException {
            return null;
        }
    }

    HmsScan[] decodeInBitmap(DetailRect detailRect, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException;

    HmsScan[] detectWithByteBuffer(DetailRect detailRect, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException;

    public static abstract class Stub extends Binder implements IRemoteHmsDecoderDelegate {
        private static final String DESCRIPTOR = "com.huawei.hms.hmsscankit.api.IRemoteHmsDecoderDelegate";
        static final int TRANSACTION_decodeInBitmap = 1;
        static final int TRANSACTION_detectWithByteBuffer = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRemoteHmsDecoderDelegate asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IRemoteHmsDecoderDelegate)) ? new Proxy(iBinder) : (IRemoteHmsDecoderDelegate) queryLocalInterface;
        }

        public static IRemoteHmsDecoderDelegate getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IRemoteHmsDecoderDelegate iRemoteHmsDecoderDelegate) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRemoteHmsDecoderDelegate == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRemoteHmsDecoderDelegate;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                HmsScan[] decodeInBitmap = decodeInBitmap(parcel.readInt() != 0 ? DetailRect.CREATOR.createFromParcel(parcel) : null, IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeTypedArray(decodeInBitmap, 1);
                return true;
            }
            if (i != 2) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            parcel.enforceInterface(DESCRIPTOR);
            HmsScan[] detectWithByteBuffer = detectWithByteBuffer(parcel.readInt() != 0 ? DetailRect.CREATOR.createFromParcel(parcel) : null, IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            parcel2.writeTypedArray(detectWithByteBuffer, 1);
            return true;
        }

        static class Proxy implements IRemoteHmsDecoderDelegate {
            public static IRemoteHmsDecoderDelegate sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.hms.hmsscankit.api.IRemoteHmsDecoderDelegate
            public HmsScan[] decodeInBitmap(DetailRect detailRect, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (detailRect != null) {
                        obtain.writeInt(1);
                        detailRect.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    obtain.writeStrongBinder(iObjectWrapper2 != null ? iObjectWrapper2.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().decodeInBitmap(detailRect, iObjectWrapper, iObjectWrapper2);
                    }
                    obtain2.readException();
                    return (HmsScan[]) obtain2.createTypedArray(HmsScan.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hms.hmsscankit.api.IRemoteHmsDecoderDelegate
            public HmsScan[] detectWithByteBuffer(DetailRect detailRect, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (detailRect != null) {
                        obtain.writeInt(1);
                        detailRect.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    obtain.writeStrongBinder(iObjectWrapper2 != null ? iObjectWrapper2.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().detectWithByteBuffer(detailRect, iObjectWrapper, iObjectWrapper2);
                    }
                    obtain2.readException();
                    return (HmsScan[]) obtain2.createTypedArray(HmsScan.CREATOR);
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
