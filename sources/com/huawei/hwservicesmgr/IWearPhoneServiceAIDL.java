package com.huawei.hwservicesmgr;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.clockwork.companion.partnerapi.SmartWatchInfo;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.DeviceParameter;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.hwcommonmodel.datatypes.TransferFileInfo;
import com.huawei.hwdevice.phoneprocess.mgr.connectmgr.DeviceDialogMessage;
import com.huawei.hwdevice.phoneprocess.mgr.hwmenstrualmanager.bean.MenstrualSwitchStatus;
import com.huawei.hwdevicemgr.framework.INoitifyPhoneServiceCallback;
import com.huawei.hwservicesmgr.HwMusicMgrCallback;
import com.huawei.hwservicesmgr.IAddDeviceStateAIDLCallback;
import com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback;
import com.huawei.hwservicesmgr.IBaseCallback;
import com.huawei.hwservicesmgr.IBluetoothDialogAidlCallback;
import com.huawei.hwservicesmgr.IDeviceStateAIDLCallback;
import com.huawei.hwservicesmgr.IHeartRateStateAIDLCallback;
import com.huawei.hwservicesmgr.IOTAResultAIDLCallback;
import com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback;
import com.huawei.hwservicesmgr.LinkageDeviceCommandListener;
import com.huawei.syncmgr.SyncOption;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public interface IWearPhoneServiceAIDL extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hwservicesmgr.IWearPhoneServiceAIDL";

    void addDevice(DeviceParameter deviceParameter, String str, IAddDeviceStateAIDLCallback iAddDeviceStateAIDLCallback) throws RemoteException;

    String dialogMessage(DeviceDialogMessage deviceDialogMessage, IBluetoothDialogAidlCallback iBluetoothDialogAidlCallback) throws RemoteException;

    void forceConnectDevice() throws RemoteException;

    List<DeviceInfo> getDeviceList(int i, HwGetDevicesParameter hwGetDevicesParameter, String str) throws RemoteException;

    void getFile(TransferFileInfo transferFileInfo, ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback) throws RemoteException;

    SmartWatchInfo getPendingConnectWatchInfo() throws RemoteException;

    boolean isCoreSleepSyncing() throws RemoteException;

    boolean isCouldGetWifiConfigurationInformation() throws RemoteException;

    void isLoudspeakerMuteOpenOrCloseHeartRate(int i, int i2, IHeartRateStateAIDLCallback iHeartRateStateAIDLCallback) throws RemoteException;

    boolean isOutgoingCall() throws RemoteException;

    boolean isPrompt() throws RemoteException;

    void migrateUsedDeviceList(List<DeviceInfo> list) throws RemoteException;

    void notifyAllSyncTask() throws RemoteException;

    boolean notifyPhoneService(String str, DeviceInfo deviceInfo, int i, String str2) throws RemoteException;

    void openSystemBluetoothSwitch(boolean z) throws RemoteException;

    Map queryDeviceCapability(int i, String str, String str2) throws RemoteException;

    void registMusicMgrCallback(HwMusicMgrCallback hwMusicMgrCallback) throws RemoteException;

    void registerBinder(IBinder iBinder, String str, String str2) throws RemoteException;

    void registerCallback(String str, INoitifyPhoneServiceCallback iNoitifyPhoneServiceCallback) throws RemoteException;

    void registerCoreSleepProgressListener(IBaseCallback iBaseCallback) throws RemoteException;

    void registerDeviceStateCallBack(IDeviceStateAIDLCallback iDeviceStateAIDLCallback) throws RemoteException;

    void registerLinkageDeviceCommandListener(LinkageDeviceCommandListener linkageDeviceCommandListener) throws RemoteException;

    void registerLinkageDeviceDataListener(IBaseCallback iBaseCallback) throws RemoteException;

    void registerWorkOutCallback(IBaseCallback iBaseCallback) throws RemoteException;

    void sendDeviceData(DeviceCommand deviceCommand) throws RemoteException;

    void sendEcgBlockList(String str, IBaseCallback iBaseCallback) throws RemoteException;

    void sendMenstrualSwitch(MenstrualSwitchStatus menstrualSwitchStatus) throws RemoteException;

    void sendOTAFileData(DeviceInfo deviceInfo, String str, String str2, int i, IOTAResultAIDLCallback iOTAResultAIDLCallback) throws RemoteException;

    void sendWifiConfigurationInformation() throws RemoteException;

    void setAwFileCallback(IBaseCallback iBaseCallback) throws RemoteException;

    void setBinder(String str, IBinder iBinder, IBaseCallback iBaseCallback) throws RemoteException;

    void setSuggestionBinder(IBinder iBinder) throws RemoteException;

    void starSycnCoreSleep(SyncOption syncOption, IBaseCallback iBaseCallback) throws RemoteException;

    void startRequestFile(String str, ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback) throws RemoteException;

    void startSyncWorkOut() throws RemoteException;

    void stopRequestFile(String str, IBaseCallback iBaseCallback) throws RemoteException;

    void stopTransferFile(String str, int i, IBaseCallback iBaseCallback) throws RemoteException;

    void switchDevice(List<DeviceInfo> list, String str) throws RemoteException;

    void transFileUnite(String str, String str2, IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    void unPair(List<String> list, boolean z) throws RemoteException;

    void unRegisterDeviceStateCallBack(IDeviceStateAIDLCallback iDeviceStateAIDLCallback) throws RemoteException;

    void unRegisterMusicMgrCallback() throws RemoteException;

    void unregisterCallback(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IWearPhoneServiceAIDL {
        static final int TRANSACTION_addDevice = 6;
        static final int TRANSACTION_dialogMessage = 27;
        static final int TRANSACTION_forceConnectDevice = 10;
        static final int TRANSACTION_getDeviceList = 35;
        static final int TRANSACTION_getFile = 11;
        static final int TRANSACTION_getPendingConnectWatchInfo = 25;
        static final int TRANSACTION_isCoreSleepSyncing = 40;
        static final int TRANSACTION_isCouldGetWifiConfigurationInformation = 34;
        static final int TRANSACTION_isLoudspeakerMuteOpenOrCloseHeartRate = 13;
        static final int TRANSACTION_isOutgoingCall = 18;
        static final int TRANSACTION_isPrompt = 17;
        static final int TRANSACTION_migrateUsedDeviceList = 8;
        static final int TRANSACTION_notifyAllSyncTask = 30;
        static final int TRANSACTION_notifyPhoneService = 36;
        static final int TRANSACTION_openSystemBluetoothSwitch = 12;
        static final int TRANSACTION_queryDeviceCapability = 2;
        static final int TRANSACTION_registMusicMgrCallback = 23;
        static final int TRANSACTION_registerBinder = 1;
        static final int TRANSACTION_registerCallback = 37;
        static final int TRANSACTION_registerCoreSleepProgressListener = 41;
        static final int TRANSACTION_registerDeviceStateCallBack = 3;
        static final int TRANSACTION_registerLinkageDeviceCommandListener = 42;
        static final int TRANSACTION_registerLinkageDeviceDataListener = 43;
        static final int TRANSACTION_registerWorkOutCallback = 15;
        static final int TRANSACTION_sendDeviceData = 5;
        static final int TRANSACTION_sendEcgBlockList = 31;
        static final int TRANSACTION_sendMenstrualSwitch = 29;
        static final int TRANSACTION_sendOTAFileData = 9;
        static final int TRANSACTION_sendWifiConfigurationInformation = 33;
        static final int TRANSACTION_setAwFileCallback = 26;
        static final int TRANSACTION_setBinder = 28;
        static final int TRANSACTION_setSuggestionBinder = 32;
        static final int TRANSACTION_starSycnCoreSleep = 39;
        static final int TRANSACTION_startRequestFile = 21;
        static final int TRANSACTION_startSyncWorkOut = 14;
        static final int TRANSACTION_stopRequestFile = 22;
        static final int TRANSACTION_stopTransferFile = 20;
        static final int TRANSACTION_switchDevice = 7;
        static final int TRANSACTION_transFileUnite = 19;
        static final int TRANSACTION_unPair = 16;
        static final int TRANSACTION_unRegisterDeviceStateCallBack = 4;
        static final int TRANSACTION_unRegisterMusicMgrCallback = 24;
        static final int TRANSACTION_unregisterCallback = 38;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IWearPhoneServiceAIDL.DESCRIPTOR);
        }

        public static IWearPhoneServiceAIDL asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IWearPhoneServiceAIDL.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IWearPhoneServiceAIDL)) {
                return (IWearPhoneServiceAIDL) queryLocalInterface;
            }
            return new e(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IWearPhoneServiceAIDL.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IWearPhoneServiceAIDL.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    registerBinder(parcel.readStrongBinder(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    Map queryDeviceCapability = queryDeviceCapability(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeMap(queryDeviceCapability);
                    return true;
                case 3:
                    registerDeviceStateCallBack(IDeviceStateAIDLCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    unRegisterDeviceStateCallBack(IDeviceStateAIDLCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    sendDeviceData((DeviceCommand) a.bRT_(parcel, DeviceCommand.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    addDevice((DeviceParameter) a.bRT_(parcel, DeviceParameter.CREATOR), parcel.readString(), IAddDeviceStateAIDLCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    switchDevice(parcel.createTypedArrayList(DeviceInfo.CREATOR), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    migrateUsedDeviceList(parcel.createTypedArrayList(DeviceInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    sendOTAFileData((DeviceInfo) a.bRT_(parcel, DeviceInfo.CREATOR), parcel.readString(), parcel.readString(), parcel.readInt(), IOTAResultAIDLCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    forceConnectDevice();
                    parcel2.writeNoException();
                    return true;
                case 11:
                    getFile((TransferFileInfo) a.bRT_(parcel, TransferFileInfo.CREATOR), ITransferSleepAndDFXFileCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 12:
                    openSystemBluetoothSwitch(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 13:
                    isLoudspeakerMuteOpenOrCloseHeartRate(parcel.readInt(), parcel.readInt(), IHeartRateStateAIDLCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 14:
                    startSyncWorkOut();
                    parcel2.writeNoException();
                    return true;
                case 15:
                    registerWorkOutCallback(IBaseCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 16:
                    unPair(parcel.createStringArrayList(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 17:
                    boolean isPrompt = isPrompt();
                    parcel2.writeNoException();
                    parcel2.writeInt(isPrompt ? 1 : 0);
                    return true;
                case 18:
                    boolean isOutgoingCall = isOutgoingCall();
                    parcel2.writeNoException();
                    parcel2.writeInt(isOutgoingCall ? 1 : 0);
                    return true;
                case 19:
                    transFileUnite(parcel.readString(), parcel.readString(), IAppTransferFileResultAIDLCallback.Stub.asInterface(parcel.readStrongBinder()), (ParcelFileDescriptor) a.bRT_(parcel, ParcelFileDescriptor.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 20:
                    stopTransferFile(parcel.readString(), parcel.readInt(), IBaseCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 21:
                    startRequestFile(parcel.readString(), ITransferSleepAndDFXFileCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 22:
                    stopRequestFile(parcel.readString(), IBaseCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 23:
                    registMusicMgrCallback(HwMusicMgrCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 24:
                    unRegisterMusicMgrCallback();
                    parcel2.writeNoException();
                    return true;
                case 25:
                    SmartWatchInfo pendingConnectWatchInfo = getPendingConnectWatchInfo();
                    parcel2.writeNoException();
                    a.bRV_(parcel2, pendingConnectWatchInfo, 1);
                    return true;
                case 26:
                    setAwFileCallback(IBaseCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 27:
                    String dialogMessage = dialogMessage((DeviceDialogMessage) a.bRT_(parcel, DeviceDialogMessage.CREATOR), IBluetoothDialogAidlCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeString(dialogMessage);
                    return true;
                case 28:
                    setBinder(parcel.readString(), parcel.readStrongBinder(), IBaseCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 29:
                    sendMenstrualSwitch((MenstrualSwitchStatus) a.bRT_(parcel, MenstrualSwitchStatus.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 30:
                    notifyAllSyncTask();
                    parcel2.writeNoException();
                    return true;
                case 31:
                    sendEcgBlockList(parcel.readString(), IBaseCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 32:
                    setSuggestionBinder(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 33:
                    sendWifiConfigurationInformation();
                    parcel2.writeNoException();
                    return true;
                case 34:
                    boolean isCouldGetWifiConfigurationInformation = isCouldGetWifiConfigurationInformation();
                    parcel2.writeNoException();
                    parcel2.writeInt(isCouldGetWifiConfigurationInformation ? 1 : 0);
                    return true;
                case 35:
                    List<DeviceInfo> deviceList = getDeviceList(parcel.readInt(), (HwGetDevicesParameter) a.bRT_(parcel, HwGetDevicesParameter.CREATOR), parcel.readString());
                    parcel2.writeNoException();
                    a.bRU_(parcel2, deviceList, 1);
                    return true;
                case 36:
                    boolean notifyPhoneService = notifyPhoneService(parcel.readString(), (DeviceInfo) a.bRT_(parcel, DeviceInfo.CREATOR), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(notifyPhoneService ? 1 : 0);
                    return true;
                case 37:
                    registerCallback(parcel.readString(), INoitifyPhoneServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 38:
                    unregisterCallback(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 39:
                    starSycnCoreSleep((SyncOption) a.bRT_(parcel, SyncOption.CREATOR), IBaseCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 40:
                    boolean isCoreSleepSyncing = isCoreSleepSyncing();
                    parcel2.writeNoException();
                    parcel2.writeInt(isCoreSleepSyncing ? 1 : 0);
                    return true;
                case 41:
                    registerCoreSleepProgressListener(IBaseCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 42:
                    registerLinkageDeviceCommandListener(LinkageDeviceCommandListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 43:
                    registerLinkageDeviceDataListener(IBaseCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* loaded from: classes5.dex */
        static class e implements IWearPhoneServiceAIDL {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f6392a;

            e(IBinder iBinder) {
                this.f6392a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f6392a;
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void registerBinder(IBinder iBinder, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.f6392a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public Map queryDeviceCapability(int i, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.f6392a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readHashMap(getClass().getClassLoader());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void registerDeviceStateCallBack(IDeviceStateAIDLCallback iDeviceStateAIDLCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    obtain.writeStrongInterface(iDeviceStateAIDLCallback);
                    this.f6392a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void unRegisterDeviceStateCallBack(IDeviceStateAIDLCallback iDeviceStateAIDLCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    obtain.writeStrongInterface(iDeviceStateAIDLCallback);
                    this.f6392a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void sendDeviceData(DeviceCommand deviceCommand) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    a.bRV_(obtain, deviceCommand, 0);
                    this.f6392a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void addDevice(DeviceParameter deviceParameter, String str, IAddDeviceStateAIDLCallback iAddDeviceStateAIDLCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    a.bRV_(obtain, deviceParameter, 0);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iAddDeviceStateAIDLCallback);
                    this.f6392a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void switchDevice(List<DeviceInfo> list, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    a.bRU_(obtain, list, 0);
                    obtain.writeString(str);
                    this.f6392a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void migrateUsedDeviceList(List<DeviceInfo> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    a.bRU_(obtain, list, 0);
                    this.f6392a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void sendOTAFileData(DeviceInfo deviceInfo, String str, String str2, int i, IOTAResultAIDLCallback iOTAResultAIDLCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    a.bRV_(obtain, deviceInfo, 0);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iOTAResultAIDLCallback);
                    this.f6392a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void forceConnectDevice() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    this.f6392a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void getFile(TransferFileInfo transferFileInfo, ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    a.bRV_(obtain, transferFileInfo, 0);
                    obtain.writeStrongInterface(iTransferSleepAndDFXFileCallback);
                    this.f6392a.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void openSystemBluetoothSwitch(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.f6392a.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void isLoudspeakerMuteOpenOrCloseHeartRate(int i, int i2, IHeartRateStateAIDLCallback iHeartRateStateAIDLCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeStrongInterface(iHeartRateStateAIDLCallback);
                    this.f6392a.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void startSyncWorkOut() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    this.f6392a.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void registerWorkOutCallback(IBaseCallback iBaseCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    obtain.writeStrongInterface(iBaseCallback);
                    this.f6392a.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void unPair(List<String> list, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    obtain.writeStringList(list);
                    obtain.writeInt(z ? 1 : 0);
                    this.f6392a.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public boolean isPrompt() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    this.f6392a.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public boolean isOutgoingCall() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    this.f6392a.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void transFileUnite(String str, String str2, IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongInterface(iAppTransferFileResultAIDLCallback);
                    a.bRV_(obtain, parcelFileDescriptor, 0);
                    this.f6392a.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void stopTransferFile(String str, int i, IBaseCallback iBaseCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iBaseCallback);
                    this.f6392a.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void startRequestFile(String str, ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iTransferSleepAndDFXFileCallback);
                    this.f6392a.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void stopRequestFile(String str, IBaseCallback iBaseCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iBaseCallback);
                    this.f6392a.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void registMusicMgrCallback(HwMusicMgrCallback hwMusicMgrCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    obtain.writeStrongInterface(hwMusicMgrCallback);
                    this.f6392a.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void unRegisterMusicMgrCallback() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    this.f6392a.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public SmartWatchInfo getPendingConnectWatchInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    this.f6392a.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                    return (SmartWatchInfo) a.bRT_(obtain2, SmartWatchInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void setAwFileCallback(IBaseCallback iBaseCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    obtain.writeStrongInterface(iBaseCallback);
                    this.f6392a.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public String dialogMessage(DeviceDialogMessage deviceDialogMessage, IBluetoothDialogAidlCallback iBluetoothDialogAidlCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    a.bRV_(obtain, deviceDialogMessage, 0);
                    obtain.writeStrongInterface(iBluetoothDialogAidlCallback);
                    this.f6392a.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void setBinder(String str, IBinder iBinder, IBaseCallback iBaseCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeStrongInterface(iBaseCallback);
                    this.f6392a.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void sendMenstrualSwitch(MenstrualSwitchStatus menstrualSwitchStatus) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    a.bRV_(obtain, menstrualSwitchStatus, 0);
                    this.f6392a.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void notifyAllSyncTask() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    this.f6392a.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void sendEcgBlockList(String str, IBaseCallback iBaseCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iBaseCallback);
                    this.f6392a.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void setSuggestionBinder(IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    this.f6392a.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void sendWifiConfigurationInformation() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    this.f6392a.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public boolean isCouldGetWifiConfigurationInformation() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    this.f6392a.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public List<DeviceInfo> getDeviceList(int i, HwGetDevicesParameter hwGetDevicesParameter, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    obtain.writeInt(i);
                    a.bRV_(obtain, hwGetDevicesParameter, 0);
                    obtain.writeString(str);
                    this.f6392a.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(DeviceInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public boolean notifyPhoneService(String str, DeviceInfo deviceInfo, int i, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    obtain.writeString(str);
                    a.bRV_(obtain, deviceInfo, 0);
                    obtain.writeInt(i);
                    obtain.writeString(str2);
                    this.f6392a.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void registerCallback(String str, INoitifyPhoneServiceCallback iNoitifyPhoneServiceCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iNoitifyPhoneServiceCallback);
                    this.f6392a.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void unregisterCallback(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    obtain.writeString(str);
                    this.f6392a.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void starSycnCoreSleep(SyncOption syncOption, IBaseCallback iBaseCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    a.bRV_(obtain, syncOption, 0);
                    obtain.writeStrongInterface(iBaseCallback);
                    this.f6392a.transact(39, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public boolean isCoreSleepSyncing() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    this.f6392a.transact(40, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void registerCoreSleepProgressListener(IBaseCallback iBaseCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    obtain.writeStrongInterface(iBaseCallback);
                    this.f6392a.transact(41, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void registerLinkageDeviceCommandListener(LinkageDeviceCommandListener linkageDeviceCommandListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    obtain.writeStrongInterface(linkageDeviceCommandListener);
                    this.f6392a.transact(42, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
            public void registerLinkageDeviceDataListener(IBaseCallback iBaseCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearPhoneServiceAIDL.DESCRIPTOR);
                    obtain.writeStrongInterface(iBaseCallback);
                    this.f6392a.transact(43, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    /* loaded from: classes5.dex */
    public static class a {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T bRT_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void bRV_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void bRU_(Parcel parcel, List<T> list, int i) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                bRV_(parcel, list.get(i2), i);
            }
        }
    }
}
