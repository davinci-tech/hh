package com.huawei.profile.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.profile.profile.DeviceProfileEx;
import com.huawei.profile.profile.Result;
import java.util.List;

/* loaded from: classes6.dex */
public interface GetDeviceInfoListenerEx extends IInterface {

    /* loaded from: classes9.dex */
    public static class Default implements GetDeviceInfoListenerEx {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.profile.service.GetDeviceInfoListenerEx
        public void onComplete(Result result, List<DeviceProfileEx> list) throws RemoteException {
        }
    }

    void onComplete(Result result, List<DeviceProfileEx> list) throws RemoteException;

    public static abstract class Stub extends Binder implements GetDeviceInfoListenerEx {
        private static final String DESCRIPTOR = "com.huawei.profile.service.GetDeviceInfoListenerEx";
        static final int TRANSACTION_onComplete = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static GetDeviceInfoListenerEx asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof GetDeviceInfoListenerEx)) {
                return (GetDeviceInfoListenerEx) queryLocalInterface;
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
            onComplete(parcel.readInt() != 0 ? Result.CREATOR.createFromParcel(parcel) : null, parcel.createTypedArrayList(DeviceProfileEx.CREATOR));
            parcel2.writeNoException();
            return true;
        }

        static class Proxy implements GetDeviceInfoListenerEx {
            public static GetDeviceInfoListenerEx sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.profile.service.GetDeviceInfoListenerEx
            public void onComplete(Result result, List<DeviceProfileEx> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (result != null) {
                        obtain.writeInt(1);
                        result.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeTypedList(list);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onComplete(result, list);
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

        public static boolean setDefaultImpl(GetDeviceInfoListenerEx getDeviceInfoListenerEx) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (getDeviceInfoListenerEx == null) {
                return false;
            }
            Proxy.sDefaultImpl = getDeviceInfoListenerEx;
            return true;
        }

        public static GetDeviceInfoListenerEx getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
