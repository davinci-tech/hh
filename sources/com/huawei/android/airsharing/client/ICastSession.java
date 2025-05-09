package com.huawei.android.airsharing.client;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.android.airsharing.api.CapabilityResponse;
import com.huawei.android.airsharing.api.EProjectionMode;
import com.huawei.android.airsharing.api.ERequestedCapability;
import com.huawei.android.airsharing.api.IRemoteCtrlEventProcessor;
import com.huawei.android.airsharing.api.ProjectionDevice;
import com.huawei.android.airsharing.client.IAidlHwListener;
import com.huawei.android.airsharing.client.IRemotePlayController;

/* loaded from: classes2.dex */
public interface ICastSession extends IInterface {
    boolean addDevice(ProjectionDevice projectionDevice, EProjectionMode eProjectionMode) throws RemoteException;

    boolean appendHiSightExInfo(int i, byte[] bArr, int i2, long j) throws RemoteException;

    EProjectionMode getCurrentProjectionMode() throws RemoteException;

    int getDisplayId() throws RemoteException;

    IRemotePlayController getRemotePlayController(ProjectionDevice projectionDevice) throws RemoteException;

    int getSessionIndex() throws RemoteException;

    int getSessionServerPort() throws RemoteException;

    boolean inputJsonDataForCast(String str) throws RemoteException;

    void registerListener(IAidlHwListener iAidlHwListener) throws RemoteException;

    boolean removeDevice(ProjectionDevice projectionDevice) throws RemoteException;

    CapabilityResponse requestCapability(ERequestedCapability eRequestedCapability) throws RemoteException;

    boolean requestSwitchProjectionMode(EProjectionMode eProjectionMode) throws RemoteException;

    int sendRemoteCtrlData(int i, int i2, byte[] bArr) throws RemoteException;

    void setRemoteCtrlEventProcessor(IRemoteCtrlEventProcessor iRemoteCtrlEventProcessor) throws RemoteException;

    void unRegisterListener() throws RemoteException;

    public static abstract class Stub extends Binder implements ICastSession {
        private static final String DESCRIPTOR = "com.huawei.android.airsharing.client.ICastSession";
        static final int TRANSACTION_addDevice = 5;
        static final int TRANSACTION_appendHiSightExInfo = 10;
        static final int TRANSACTION_getCurrentProjectionMode = 15;
        static final int TRANSACTION_getDisplayId = 4;
        static final int TRANSACTION_getRemotePlayController = 13;
        static final int TRANSACTION_getSessionIndex = 1;
        static final int TRANSACTION_getSessionServerPort = 12;
        static final int TRANSACTION_inputJsonDataForCast = 7;
        static final int TRANSACTION_registerListener = 2;
        static final int TRANSACTION_removeDevice = 6;
        static final int TRANSACTION_requestCapability = 11;
        static final int TRANSACTION_requestSwitchProjectionMode = 14;
        static final int TRANSACTION_sendRemoteCtrlData = 8;
        static final int TRANSACTION_setRemoteCtrlEventProcessor = 9;
        static final int TRANSACTION_unRegisterListener = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICastSession asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ICastSession)) {
                return (ICastSession) queryLocalInterface;
            }
            return new d(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    int sessionIndex = getSessionIndex();
                    parcel2.writeNoException();
                    parcel2.writeInt(sessionIndex);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerListener(IAidlHwListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    unRegisterListener();
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int displayId = getDisplayId();
                    parcel2.writeNoException();
                    parcel2.writeInt(displayId);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean addDevice = addDevice(parcel.readInt() != 0 ? ProjectionDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? EProjectionMode.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(addDevice ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean removeDevice = removeDevice(parcel.readInt() != 0 ? ProjectionDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(removeDevice ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean inputJsonDataForCast = inputJsonDataForCast(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(inputJsonDataForCast ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int sendRemoteCtrlData = sendRemoteCtrlData(parcel.readInt(), parcel.readInt(), parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(sendRemoteCtrlData);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    setRemoteCtrlEventProcessor(IRemoteCtrlEventProcessor.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean appendHiSightExInfo = appendHiSightExInfo(parcel.readInt(), parcel.createByteArray(), parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(appendHiSightExInfo ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    CapabilityResponse requestCapability = requestCapability(parcel.readInt() != 0 ? ERequestedCapability.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (requestCapability != null) {
                        parcel2.writeInt(1);
                        requestCapability.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    int sessionServerPort = getSessionServerPort();
                    parcel2.writeNoException();
                    parcel2.writeInt(sessionServerPort);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    IRemotePlayController remotePlayController = getRemotePlayController(parcel.readInt() != 0 ? ProjectionDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(remotePlayController != null ? remotePlayController.asBinder() : null);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean requestSwitchProjectionMode = requestSwitchProjectionMode(parcel.readInt() != 0 ? EProjectionMode.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(requestSwitchProjectionMode ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    EProjectionMode currentProjectionMode = getCurrentProjectionMode();
                    parcel2.writeNoException();
                    if (currentProjectionMode != null) {
                        parcel2.writeInt(1);
                        currentProjectionMode.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class d implements ICastSession {
            public static ICastSession b;
            private IBinder e;

            d(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.huawei.android.airsharing.client.ICastSession
            public int getSessionIndex() throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.e.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getSessionIndex();
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

            @Override // com.huawei.android.airsharing.client.ICastSession
            public void registerListener(IAidlHwListener iAidlHwListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAidlHwListener != null ? iAidlHwListener.asBinder() : null);
                    if (!this.e.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerListener(iAidlHwListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.ICastSession
            public void unRegisterListener() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.e.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unRegisterListener();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.ICastSession
            public int getDisplayId() throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.e.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getDisplayId();
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

            @Override // com.huawei.android.airsharing.client.ICastSession
            public boolean addDevice(ProjectionDevice projectionDevice, EProjectionMode eProjectionMode) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (projectionDevice != null) {
                        obtain.writeInt(1);
                        projectionDevice.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (eProjectionMode != null) {
                        obtain.writeInt(1);
                        eProjectionMode.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.e.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addDevice(projectionDevice, eProjectionMode);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.ICastSession
            public boolean removeDevice(ProjectionDevice projectionDevice) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (projectionDevice != null) {
                        obtain.writeInt(1);
                        projectionDevice.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.e.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeDevice(projectionDevice);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.ICastSession
            public boolean inputJsonDataForCast(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.e.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().inputJsonDataForCast(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.ICastSession
            public int sendRemoteCtrlData(int i, int i2, byte[] bArr) throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeByteArray(bArr);
                    if (!this.e.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().sendRemoteCtrlData(i, i2, bArr);
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

            @Override // com.huawei.android.airsharing.client.ICastSession
            public void setRemoteCtrlEventProcessor(IRemoteCtrlEventProcessor iRemoteCtrlEventProcessor) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iRemoteCtrlEventProcessor != null ? iRemoteCtrlEventProcessor.asBinder() : null);
                    if (!this.e.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRemoteCtrlEventProcessor(iRemoteCtrlEventProcessor);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.ICastSession
            public boolean appendHiSightExInfo(int i, byte[] bArr, int i2, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(i2);
                    obtain.writeLong(j);
                    try {
                        if (!this.e.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                            boolean appendHiSightExInfo = Stub.getDefaultImpl().appendHiSightExInfo(i, bArr, i2, j);
                            obtain2.recycle();
                            obtain.recycle();
                            return appendHiSightExInfo;
                        }
                        obtain2.readException();
                        boolean z = obtain2.readInt() != 0;
                        obtain2.recycle();
                        obtain.recycle();
                        return z;
                    } catch (Throwable th) {
                        th = th;
                        obtain2.recycle();
                        obtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // com.huawei.android.airsharing.client.ICastSession
            public CapabilityResponse requestCapability(ERequestedCapability eRequestedCapability) throws RemoteException {
                CapabilityResponse createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (eRequestedCapability != null) {
                        obtain.writeInt(1);
                        eRequestedCapability.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.e.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = Stub.getDefaultImpl().requestCapability(eRequestedCapability);
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? CapabilityResponse.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.ICastSession
            public int getSessionServerPort() throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.e.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getSessionServerPort();
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

            @Override // com.huawei.android.airsharing.client.ICastSession
            public IRemotePlayController getRemotePlayController(ProjectionDevice projectionDevice) throws RemoteException {
                IRemotePlayController asInterface;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (projectionDevice != null) {
                        obtain.writeInt(1);
                        projectionDevice.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.e.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        asInterface = Stub.getDefaultImpl().getRemotePlayController(projectionDevice);
                    } else {
                        obtain2.readException();
                        asInterface = IRemotePlayController.Stub.asInterface(obtain2.readStrongBinder());
                    }
                    return asInterface;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.ICastSession
            public boolean requestSwitchProjectionMode(EProjectionMode eProjectionMode) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (eProjectionMode != null) {
                        obtain.writeInt(1);
                        eProjectionMode.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.e.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestSwitchProjectionMode(eProjectionMode);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.ICastSession
            public EProjectionMode getCurrentProjectionMode() throws RemoteException {
                EProjectionMode createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.e.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = Stub.getDefaultImpl().getCurrentProjectionMode();
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? EProjectionMode.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICastSession iCastSession) {
            if (d.b != null || iCastSession == null) {
                return false;
            }
            d.b = iCastSession;
            return true;
        }

        public static ICastSession getDefaultImpl() {
            return d.b;
        }
    }
}
