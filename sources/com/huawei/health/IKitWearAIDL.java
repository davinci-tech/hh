package com.huawei.health;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.health.IBaseCommonCallback;
import com.huawei.health.IDetectCommonCallback;
import java.util.List;

/* loaded from: classes.dex */
public interface IKitWearAIDL extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.health.IKitWearAIDL";

    void bindDevice(String str, String str2, String str3, int i, IBaseCommonCallback iBaseCommonCallback) throws RemoteException;

    void captureLog(String str, IBaseCommonCallback iBaseCommonCallback) throws RemoteException;

    void getDeviceList(IBaseCommonCallback iBaseCommonCallback) throws RemoteException;

    void getList(int i, IDetectCommonCallback iDetectCommonCallback) throws RemoteException;

    void getRealTimeData(String str, int i, int i2, IBaseCommonCallback iBaseCommonCallback) throws RemoteException;

    void getSwitch(String str, IBaseCommonCallback iBaseCommonCallback) throws RemoteException;

    void goIntoPage(String str, int i, IDetectCommonCallback iDetectCommonCallback) throws RemoteException;

    void isLatestVersion(int i, IDetectCommonCallback iDetectCommonCallback) throws RemoteException;

    void isSwitchOn(int i, IDetectCommonCallback iDetectCommonCallback) throws RemoteException;

    void pushMsgToWearable(HiAppInfo hiAppInfo, String str, String str2, IBaseCommonCallback iBaseCommonCallback) throws RemoteException;

    void readFromWear(String str, String str2, IBaseCommonCallback iBaseCommonCallback) throws RemoteException;

    void registerSingleAtrialCallback(IBaseCommonCallback iBaseCommonCallback) throws RemoteException;

    void removeLog(List<String> list, IBaseCommonCallback iBaseCommonCallback) throws RemoteException;

    void sendDeviceCommand(String str, IBaseCommonCallback iBaseCommonCallback) throws RemoteException;

    void unRegisterSingleAtrialCallback(IBaseCommonCallback iBaseCommonCallback) throws RemoteException;

    void uploadLog(int i, String str, List<String> list, IBaseCommonCallback iBaseCommonCallback) throws RemoteException;

    void writeToWear(String str, String str2, byte[] bArr, String str3, IBaseCommonCallback iBaseCommonCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IKitWearAIDL {
        static final int TRANSACTION_bindDevice = 14;
        static final int TRANSACTION_captureLog = 15;
        static final int TRANSACTION_getDeviceList = 2;
        static final int TRANSACTION_getList = 11;
        static final int TRANSACTION_getRealTimeData = 1;
        static final int TRANSACTION_getSwitch = 9;
        static final int TRANSACTION_goIntoPage = 13;
        static final int TRANSACTION_isLatestVersion = 12;
        static final int TRANSACTION_isSwitchOn = 10;
        static final int TRANSACTION_pushMsgToWearable = 8;
        static final int TRANSACTION_readFromWear = 7;
        static final int TRANSACTION_registerSingleAtrialCallback = 4;
        static final int TRANSACTION_removeLog = 17;
        static final int TRANSACTION_sendDeviceCommand = 3;
        static final int TRANSACTION_unRegisterSingleAtrialCallback = 5;
        static final int TRANSACTION_uploadLog = 16;
        static final int TRANSACTION_writeToWear = 6;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IKitWearAIDL.DESCRIPTOR);
        }

        public static IKitWearAIDL asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IKitWearAIDL.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IKitWearAIDL)) {
                return (IKitWearAIDL) queryLocalInterface;
            }
            return new c(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IKitWearAIDL.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IKitWearAIDL.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    getRealTimeData(parcel.readString(), parcel.readInt(), parcel.readInt(), IBaseCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    getDeviceList(IBaseCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    sendDeviceCommand(parcel.readString(), IBaseCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    registerSingleAtrialCallback(IBaseCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    unRegisterSingleAtrialCallback(IBaseCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    writeToWear(parcel.readString(), parcel.readString(), parcel.createByteArray(), parcel.readString(), IBaseCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    readFromWear(parcel.readString(), parcel.readString(), IBaseCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    pushMsgToWearable((HiAppInfo) d.AD_(parcel, HiAppInfo.CREATOR), parcel.readString(), parcel.readString(), IBaseCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    getSwitch(parcel.readString(), IBaseCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    isSwitchOn(parcel.readInt(), IDetectCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 11:
                    getList(parcel.readInt(), IDetectCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 12:
                    isLatestVersion(parcel.readInt(), IDetectCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 13:
                    goIntoPage(parcel.readString(), parcel.readInt(), IDetectCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 14:
                    bindDevice(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), IBaseCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 15:
                    captureLog(parcel.readString(), IBaseCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 16:
                    uploadLog(parcel.readInt(), parcel.readString(), parcel.createStringArrayList(), IBaseCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 17:
                    removeLog(parcel.createStringArrayList(), IBaseCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* loaded from: classes7.dex */
        static class c implements IKitWearAIDL {
            private IBinder b;

            c(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.health.IKitWearAIDL
            public void getRealTimeData(String str, int i, int i2, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IKitWearAIDL.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeStrongInterface(iBaseCommonCallback);
                    this.b.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IKitWearAIDL
            public void getDeviceList(IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IKitWearAIDL.DESCRIPTOR);
                    obtain.writeStrongInterface(iBaseCommonCallback);
                    this.b.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IKitWearAIDL
            public void sendDeviceCommand(String str, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IKitWearAIDL.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iBaseCommonCallback);
                    this.b.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IKitWearAIDL
            public void registerSingleAtrialCallback(IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IKitWearAIDL.DESCRIPTOR);
                    obtain.writeStrongInterface(iBaseCommonCallback);
                    this.b.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IKitWearAIDL
            public void unRegisterSingleAtrialCallback(IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IKitWearAIDL.DESCRIPTOR);
                    obtain.writeStrongInterface(iBaseCommonCallback);
                    this.b.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IKitWearAIDL
            public void writeToWear(String str, String str2, byte[] bArr, String str3, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IKitWearAIDL.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeByteArray(bArr);
                    obtain.writeString(str3);
                    obtain.writeStrongInterface(iBaseCommonCallback);
                    this.b.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IKitWearAIDL
            public void readFromWear(String str, String str2, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IKitWearAIDL.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongInterface(iBaseCommonCallback);
                    this.b.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IKitWearAIDL
            public void pushMsgToWearable(HiAppInfo hiAppInfo, String str, String str2, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IKitWearAIDL.DESCRIPTOR);
                    d.AE_(obtain, hiAppInfo, 0);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongInterface(iBaseCommonCallback);
                    this.b.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IKitWearAIDL
            public void getSwitch(String str, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IKitWearAIDL.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iBaseCommonCallback);
                    this.b.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IKitWearAIDL
            public void isSwitchOn(int i, IDetectCommonCallback iDetectCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IKitWearAIDL.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iDetectCommonCallback);
                    this.b.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IKitWearAIDL
            public void getList(int i, IDetectCommonCallback iDetectCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IKitWearAIDL.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iDetectCommonCallback);
                    this.b.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IKitWearAIDL
            public void isLatestVersion(int i, IDetectCommonCallback iDetectCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IKitWearAIDL.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iDetectCommonCallback);
                    this.b.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IKitWearAIDL
            public void goIntoPage(String str, int i, IDetectCommonCallback iDetectCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IKitWearAIDL.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iDetectCommonCallback);
                    this.b.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IKitWearAIDL
            public void bindDevice(String str, String str2, String str3, int i, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IKitWearAIDL.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iBaseCommonCallback);
                    this.b.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IKitWearAIDL
            public void captureLog(String str, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IKitWearAIDL.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iBaseCommonCallback);
                    this.b.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IKitWearAIDL
            public void uploadLog(int i, String str, List<String> list, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IKitWearAIDL.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeStringList(list);
                    obtain.writeStrongInterface(iBaseCommonCallback);
                    this.b.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IKitWearAIDL
            public void removeLog(List<String> list, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IKitWearAIDL.DESCRIPTOR);
                    obtain.writeStringList(list);
                    obtain.writeStrongInterface(iBaseCommonCallback);
                    this.b.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    /* loaded from: classes3.dex */
    public static class d {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T AD_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void AE_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
