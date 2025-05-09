package com.huawei.android.airsharing.client;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.android.airsharing.api.AuthenticationInfo;
import com.huawei.android.airsharing.api.CapabilityResponse;
import com.huawei.android.airsharing.api.ColorSet;
import com.huawei.android.airsharing.api.ConfigInfo;
import com.huawei.android.airsharing.api.ConnectInfo;
import com.huawei.android.airsharing.api.ConnectionInfo;
import com.huawei.android.airsharing.api.DialogInfo;
import com.huawei.android.airsharing.api.EHwTransportState;
import com.huawei.android.airsharing.api.EProjectionMode;
import com.huawei.android.airsharing.api.ERepeatMode;
import com.huawei.android.airsharing.api.ERequestedCapability;
import com.huawei.android.airsharing.api.GrabState;
import com.huawei.android.airsharing.api.HwMediaInfo;
import com.huawei.android.airsharing.api.HwMediaPosition;
import com.huawei.android.airsharing.api.HwObject;
import com.huawei.android.airsharing.api.HwServer;
import com.huawei.android.airsharing.api.IAidlMediaPlayerListener;
import com.huawei.android.airsharing.api.IRemoteCtrlEventProcessor;
import com.huawei.android.airsharing.api.PlayInfo;
import com.huawei.android.airsharing.api.ProjectionDevice;
import com.huawei.android.airsharing.client.IAidlHwListener;
import com.huawei.android.airsharing.client.ICastSession;
import java.util.List;

/* loaded from: classes2.dex */
public interface IAidlHwPlayerManager extends IInterface {
    boolean appendHiSightExInfo(int i, byte[] bArr, int i2, long j) throws RemoteException;

    boolean castConnectDevice(ConnectInfo connectInfo) throws RemoteException;

    boolean castDisconnectDevice(ProjectionDevice projectionDevice) throws RemoteException;

    boolean castPlay(PlayInfo playInfo) throws RemoteException;

    boolean chooseDialogResult(DialogInfo dialogInfo) throws RemoteException;

    void clsHwSharingListener(int i, IAidlHwListener iAidlHwListener) throws RemoteException;

    void colorAdaption(ColorSet colorSet) throws RemoteException;

    boolean configGrabState(int i, long j) throws RemoteException;

    boolean connectDevice(int i, ProjectionDevice projectionDevice) throws RemoteException;

    int connectToServer(HwServer hwServer) throws RemoteException;

    String convertFilePathToDmsUrl(String str) throws RemoteException;

    ICastSession createCastSession(int i) throws RemoteException;

    void disconnect() throws RemoteException;

    boolean disconnectDevice(int i) throws RemoteException;

    ConnectionInfo getConnectionInfo(int i) throws RemoteException;

    ICastSession getCurrentCastSession(ProjectionDevice projectionDevice) throws RemoteException;

    GrabState getCurrentGrabState() throws RemoteException;

    int getHiSightServerPort() throws RemoteException;

    HwMediaInfo getMediaInfo(int i) throws RemoteException;

    int getMsdpServerPort() throws RemoteException;

    HwMediaPosition getPosition(int i) throws RemoteException;

    HwServer getRenderingServer() throws RemoteException;

    int getSdkVersion() throws RemoteException;

    int getSeekTarget(int i) throws RemoteException;

    List<HwServer> getServerList() throws RemoteException;

    String getTargetDevIndication(int i) throws RemoteException;

    String getTargetDevName(int i) throws RemoteException;

    ProjectionDevice getTargetProjectionDevice(int i) throws RemoteException;

    int getVersion() throws RemoteException;

    int getVolume(int i) throws RemoteException;

    boolean hasPlayer(int i) throws RemoteException;

    boolean inputJsonDataForCast(String str) throws RemoteException;

    boolean isCastConnected(ProjectionDevice projectionDevice, EProjectionMode eProjectionMode) throws RemoteException;

    boolean isConnected(int i) throws RemoteException;

    boolean isDisplayConnected(int i) throws RemoteException;

    boolean isDisplayConnecting(int i) throws RemoteException;

    boolean isRendering(int i) throws RemoteException;

    boolean next() throws RemoteException;

    void notifyPositionChanged(int i, HwMediaPosition hwMediaPosition) throws RemoteException;

    void notifyTransportStateChanged(int i, EHwTransportState eHwTransportState) throws RemoteException;

    void notifyVolumeChanged(int i, int i2) throws RemoteException;

    boolean pause(int i) throws RemoteException;

    boolean pauseWifiDisplay(int i) throws RemoteException;

    boolean play(int i, HwMediaInfo hwMediaInfo, ProjectionDevice projectionDevice) throws RemoteException;

    boolean playMedia(int i, HwMediaInfo hwMediaInfo, boolean z, HwObject hwObject) throws RemoteException;

    boolean playMediaItem(int i) throws RemoteException;

    boolean previous() throws RemoteException;

    boolean registerMediaPlayerListener(IAidlMediaPlayerListener iAidlMediaPlayerListener) throws RemoteException;

    CapabilityResponse requestCapability(ERequestedCapability eRequestedCapability) throws RemoteException;

    boolean resume(int i) throws RemoteException;

    boolean resumeWifiDisplay(int i) throws RemoteException;

    boolean seek(int i, String str) throws RemoteException;

    boolean seekTo(int i) throws RemoteException;

    int sendRemoteCtrlData(int i, int i2, byte[] bArr) throws RemoteException;

    boolean setAuthenticationInfo(AuthenticationInfo authenticationInfo) throws RemoteException;

    String setConfigInfo(ConfigInfo configInfo) throws RemoteException;

    boolean setExtendInfo(String str, String str2) throws RemoteException;

    void setHwSharingListener(int i, IAidlHwListener iAidlHwListener) throws RemoteException;

    void setRemoteCtrlEventProcessor(IRemoteCtrlEventProcessor iRemoteCtrlEventProcessor) throws RemoteException;

    boolean setRepeatMode(ERepeatMode eRepeatMode) throws RemoteException;

    boolean setUsingCapability(int i, int i2) throws RemoteException;

    boolean setVolume(int i, int i2) throws RemoteException;

    boolean setVolumeMute(boolean z) throws RemoteException;

    boolean showProjectionActivity(ConfigInfo configInfo) throws RemoteException;

    boolean startScan(int i) throws RemoteException;

    boolean startScanDevice(int i, boolean z) throws RemoteException;

    boolean startServer(int i, String str, String str2) throws RemoteException;

    boolean stop(int i) throws RemoteException;

    boolean stopScan(int i) throws RemoteException;

    boolean stopScanDevice(int i, boolean z) throws RemoteException;

    boolean stopServer(int i) throws RemoteException;

    void subscribServers(int i, String str) throws RemoteException;

    boolean unregisterMediaPlayerListener() throws RemoteException;

    void unsubscribServers(int i, String str) throws RemoteException;

    boolean updateApplication(String str) throws RemoteException;

    boolean updatePlayInfo(PlayInfo playInfo) throws RemoteException;

    public static abstract class Stub extends Binder implements IAidlHwPlayerManager {
        private static final String DESCRIPTOR = "com.huawei.android.airsharing.client.IAidlHwPlayerManager";
        static final int TRANSACTION_appendHiSightExInfo = 40;
        static final int TRANSACTION_castConnectDevice = 51;
        static final int TRANSACTION_castDisconnectDevice = 64;
        static final int TRANSACTION_castPlay = 52;
        static final int TRANSACTION_chooseDialogResult = 70;
        static final int TRANSACTION_clsHwSharingListener = 9;
        static final int TRANSACTION_colorAdaption = 49;
        static final int TRANSACTION_configGrabState = 73;
        static final int TRANSACTION_connectDevice = 28;
        static final int TRANSACTION_connectToServer = 6;
        static final int TRANSACTION_convertFilePathToDmsUrl = 45;
        static final int TRANSACTION_createCastSession = 75;
        static final int TRANSACTION_disconnect = 7;
        static final int TRANSACTION_disconnectDevice = 29;
        static final int TRANSACTION_getConnectionInfo = 47;
        static final int TRANSACTION_getCurrentCastSession = 76;
        static final int TRANSACTION_getCurrentGrabState = 72;
        static final int TRANSACTION_getHiSightServerPort = 39;
        static final int TRANSACTION_getMediaInfo = 18;
        static final int TRANSACTION_getMsdpServerPort = 44;
        static final int TRANSACTION_getPosition = 11;
        static final int TRANSACTION_getRenderingServer = 25;
        static final int TRANSACTION_getSdkVersion = 37;
        static final int TRANSACTION_getSeekTarget = 19;
        static final int TRANSACTION_getServerList = 3;
        static final int TRANSACTION_getTargetDevIndication = 31;
        static final int TRANSACTION_getTargetDevName = 30;
        static final int TRANSACTION_getTargetProjectionDevice = 38;
        static final int TRANSACTION_getVersion = 74;
        static final int TRANSACTION_getVolume = 20;
        static final int TRANSACTION_hasPlayer = 17;
        static final int TRANSACTION_inputJsonDataForCast = 50;
        static final int TRANSACTION_isCastConnected = 65;
        static final int TRANSACTION_isConnected = 32;
        static final int TRANSACTION_isDisplayConnected = 43;
        static final int TRANSACTION_isDisplayConnecting = 33;
        static final int TRANSACTION_isRendering = 16;
        static final int TRANSACTION_next = 57;
        static final int TRANSACTION_notifyPositionChanged = 23;
        static final int TRANSACTION_notifyTransportStateChanged = 24;
        static final int TRANSACTION_notifyVolumeChanged = 22;
        static final int TRANSACTION_pause = 13;
        static final int TRANSACTION_pauseWifiDisplay = 35;
        static final int TRANSACTION_play = 34;
        static final int TRANSACTION_playMedia = 10;
        static final int TRANSACTION_playMediaItem = 59;
        static final int TRANSACTION_previous = 56;
        static final int TRANSACTION_registerMediaPlayerListener = 53;
        static final int TRANSACTION_requestCapability = 69;
        static final int TRANSACTION_resume = 14;
        static final int TRANSACTION_resumeWifiDisplay = 36;
        static final int TRANSACTION_seek = 12;
        static final int TRANSACTION_seekTo = 62;
        static final int TRANSACTION_sendRemoteCtrlData = 42;
        static final int TRANSACTION_setAuthenticationInfo = 46;
        static final int TRANSACTION_setConfigInfo = 68;
        static final int TRANSACTION_setExtendInfo = 67;
        static final int TRANSACTION_setHwSharingListener = 8;
        static final int TRANSACTION_setRemoteCtrlEventProcessor = 41;
        static final int TRANSACTION_setRepeatMode = 58;
        static final int TRANSACTION_setUsingCapability = 48;
        static final int TRANSACTION_setVolume = 21;
        static final int TRANSACTION_setVolumeMute = 63;
        static final int TRANSACTION_showProjectionActivity = 61;
        static final int TRANSACTION_startScan = 60;
        static final int TRANSACTION_startScanDevice = 26;
        static final int TRANSACTION_startServer = 1;
        static final int TRANSACTION_stop = 15;
        static final int TRANSACTION_stopScan = 66;
        static final int TRANSACTION_stopScanDevice = 27;
        static final int TRANSACTION_stopServer = 2;
        static final int TRANSACTION_subscribServers = 4;
        static final int TRANSACTION_unregisterMediaPlayerListener = 54;
        static final int TRANSACTION_unsubscribServers = 5;
        static final int TRANSACTION_updateApplication = 71;
        static final int TRANSACTION_updatePlayInfo = 55;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAidlHwPlayerManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAidlHwPlayerManager)) {
                return (IAidlHwPlayerManager) queryLocalInterface;
            }
            return new b(iBinder);
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
                    boolean startServer = startServer(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(startServer ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean stopServer = stopServer(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(stopServer ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<HwServer> serverList = getServerList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(serverList);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    subscribServers(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    unsubscribServers(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int connectToServer = connectToServer(parcel.readInt() != 0 ? HwServer.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(connectToServer);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    disconnect();
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    setHwSharingListener(parcel.readInt(), IAidlHwListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    clsHwSharingListener(parcel.readInt(), IAidlHwListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean playMedia = playMedia(parcel.readInt(), parcel.readInt() != 0 ? HwMediaInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0, parcel.readInt() != 0 ? HwObject.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(playMedia ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    HwMediaPosition position = getPosition(parcel.readInt());
                    parcel2.writeNoException();
                    if (position != null) {
                        parcel2.writeInt(1);
                        position.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean seek = seek(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(seek ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean pause = pause(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(pause ? 1 : 0);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean resume = resume(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(resume ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean stop = stop(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(stop ? 1 : 0);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isRendering = isRendering(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(isRendering ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean hasPlayer = hasPlayer(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(hasPlayer ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    HwMediaInfo mediaInfo = getMediaInfo(parcel.readInt());
                    parcel2.writeNoException();
                    if (mediaInfo != null) {
                        parcel2.writeInt(1);
                        mediaInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    int seekTarget = getSeekTarget(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(seekTarget);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    int volume = getVolume(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(volume);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean volume2 = setVolume(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(volume2 ? 1 : 0);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyVolumeChanged(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyPositionChanged(parcel.readInt(), parcel.readInt() != 0 ? HwMediaPosition.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyTransportStateChanged(parcel.readInt(), parcel.readInt() != 0 ? EHwTransportState.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    HwServer renderingServer = getRenderingServer();
                    parcel2.writeNoException();
                    if (renderingServer != null) {
                        parcel2.writeInt(1);
                        renderingServer.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean startScanDevice = startScanDevice(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(startScanDevice ? 1 : 0);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean stopScanDevice = stopScanDevice(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(stopScanDevice ? 1 : 0);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean connectDevice = connectDevice(parcel.readInt(), parcel.readInt() != 0 ? ProjectionDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(connectDevice ? 1 : 0);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean disconnectDevice = disconnectDevice(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(disconnectDevice ? 1 : 0);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    String targetDevName = getTargetDevName(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(targetDevName);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    String targetDevIndication = getTargetDevIndication(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(targetDevIndication);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isConnected = isConnected(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(isConnected ? 1 : 0);
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isDisplayConnecting = isDisplayConnecting(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(isDisplayConnecting ? 1 : 0);
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean play = play(parcel.readInt(), parcel.readInt() != 0 ? HwMediaInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? ProjectionDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(play ? 1 : 0);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean pauseWifiDisplay = pauseWifiDisplay(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(pauseWifiDisplay ? 1 : 0);
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean resumeWifiDisplay = resumeWifiDisplay(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(resumeWifiDisplay ? 1 : 0);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    int sdkVersion = getSdkVersion();
                    parcel2.writeNoException();
                    parcel2.writeInt(sdkVersion);
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    ProjectionDevice targetProjectionDevice = getTargetProjectionDevice(parcel.readInt());
                    parcel2.writeNoException();
                    if (targetProjectionDevice != null) {
                        parcel2.writeInt(1);
                        targetProjectionDevice.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    int hiSightServerPort = getHiSightServerPort();
                    parcel2.writeNoException();
                    parcel2.writeInt(hiSightServerPort);
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean appendHiSightExInfo = appendHiSightExInfo(parcel.readInt(), parcel.createByteArray(), parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(appendHiSightExInfo ? 1 : 0);
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    setRemoteCtrlEventProcessor(IRemoteCtrlEventProcessor.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    int sendRemoteCtrlData = sendRemoteCtrlData(parcel.readInt(), parcel.readInt(), parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(sendRemoteCtrlData);
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isDisplayConnected = isDisplayConnected(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(isDisplayConnected ? 1 : 0);
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    int msdpServerPort = getMsdpServerPort();
                    parcel2.writeNoException();
                    parcel2.writeInt(msdpServerPort);
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    String convertFilePathToDmsUrl = convertFilePathToDmsUrl(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(convertFilePathToDmsUrl);
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean authenticationInfo = setAuthenticationInfo(parcel.readInt() != 0 ? AuthenticationInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(authenticationInfo ? 1 : 0);
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    ConnectionInfo connectionInfo = getConnectionInfo(parcel.readInt());
                    parcel2.writeNoException();
                    if (connectionInfo != null) {
                        parcel2.writeInt(1);
                        connectionInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 48:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean usingCapability = setUsingCapability(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(usingCapability ? 1 : 0);
                    return true;
                case 49:
                    parcel.enforceInterface(DESCRIPTOR);
                    colorAdaption(parcel.readInt() != 0 ? ColorSet.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 50:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean inputJsonDataForCast = inputJsonDataForCast(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(inputJsonDataForCast ? 1 : 0);
                    return true;
                case 51:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean castConnectDevice = castConnectDevice(parcel.readInt() != 0 ? ConnectInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(castConnectDevice ? 1 : 0);
                    return true;
                case 52:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean castPlay = castPlay(parcel.readInt() != 0 ? PlayInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(castPlay ? 1 : 0);
                    return true;
                case 53:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean registerMediaPlayerListener = registerMediaPlayerListener(IAidlMediaPlayerListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(registerMediaPlayerListener ? 1 : 0);
                    return true;
                case 54:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean unregisterMediaPlayerListener = unregisterMediaPlayerListener();
                    parcel2.writeNoException();
                    parcel2.writeInt(unregisterMediaPlayerListener ? 1 : 0);
                    return true;
                case 55:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean updatePlayInfo = updatePlayInfo(parcel.readInt() != 0 ? PlayInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(updatePlayInfo ? 1 : 0);
                    return true;
                case 56:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean previous = previous();
                    parcel2.writeNoException();
                    parcel2.writeInt(previous ? 1 : 0);
                    return true;
                case 57:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean next = next();
                    parcel2.writeNoException();
                    parcel2.writeInt(next ? 1 : 0);
                    return true;
                case 58:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean repeatMode = setRepeatMode(parcel.readInt() != 0 ? ERepeatMode.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(repeatMode ? 1 : 0);
                    return true;
                case 59:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean playMediaItem = playMediaItem(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(playMediaItem ? 1 : 0);
                    return true;
                case 60:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean startScan = startScan(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(startScan ? 1 : 0);
                    return true;
                case 61:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean showProjectionActivity = showProjectionActivity(parcel.readInt() != 0 ? ConfigInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(showProjectionActivity ? 1 : 0);
                    return true;
                case 62:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean seekTo = seekTo(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(seekTo ? 1 : 0);
                    return true;
                case 63:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean volumeMute = setVolumeMute(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(volumeMute ? 1 : 0);
                    return true;
                case 64:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean castDisconnectDevice = castDisconnectDevice(parcel.readInt() != 0 ? ProjectionDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(castDisconnectDevice ? 1 : 0);
                    return true;
                case 65:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isCastConnected = isCastConnected(parcel.readInt() != 0 ? ProjectionDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? EProjectionMode.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(isCastConnected ? 1 : 0);
                    return true;
                case 66:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean stopScan = stopScan(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(stopScan ? 1 : 0);
                    return true;
                case 67:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean extendInfo = setExtendInfo(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(extendInfo ? 1 : 0);
                    return true;
                case 68:
                    parcel.enforceInterface(DESCRIPTOR);
                    String configInfo = setConfigInfo(parcel.readInt() != 0 ? ConfigInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeString(configInfo);
                    return true;
                case 69:
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
                case 70:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean chooseDialogResult = chooseDialogResult(parcel.readInt() != 0 ? DialogInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(chooseDialogResult ? 1 : 0);
                    return true;
                case 71:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean updateApplication = updateApplication(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(updateApplication ? 1 : 0);
                    return true;
                case 72:
                    parcel.enforceInterface(DESCRIPTOR);
                    GrabState currentGrabState = getCurrentGrabState();
                    parcel2.writeNoException();
                    if (currentGrabState != null) {
                        parcel2.writeInt(1);
                        currentGrabState.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 73:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean configGrabState = configGrabState(parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(configGrabState ? 1 : 0);
                    return true;
                case 74:
                    parcel.enforceInterface(DESCRIPTOR);
                    int version = getVersion();
                    parcel2.writeNoException();
                    parcel2.writeInt(version);
                    return true;
                case 75:
                    parcel.enforceInterface(DESCRIPTOR);
                    ICastSession createCastSession = createCastSession(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(createCastSession != null ? createCastSession.asBinder() : null);
                    return true;
                case 76:
                    parcel.enforceInterface(DESCRIPTOR);
                    ICastSession currentCastSession = getCurrentCastSession(parcel.readInt() != 0 ? ProjectionDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(currentCastSession != null ? currentCastSession.asBinder() : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class b implements IAidlHwPlayerManager {
            public static IAidlHwPlayerManager b;

            /* renamed from: a, reason: collision with root package name */
            private IBinder f1823a;

            b(IBinder iBinder) {
                this.f1823a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f1823a;
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean startServer(int i, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.f1823a.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startServer(i, str, str2);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean stopServer(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.f1823a.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopServer(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public List<HwServer> getServerList() throws RemoteException {
                List<HwServer> createTypedArrayList;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f1823a.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createTypedArrayList = Stub.getDefaultImpl().getServerList();
                    } else {
                        obtain2.readException();
                        createTypedArrayList = obtain2.createTypedArrayList(HwServer.CREATOR);
                    }
                    return createTypedArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public void subscribServers(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (!this.f1823a.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().subscribServers(i, str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public void unsubscribServers(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (!this.f1823a.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unsubscribServers(i, str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public int connectToServer(HwServer hwServer) throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (hwServer != null) {
                        obtain.writeInt(1);
                        hwServer.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.f1823a.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().connectToServer(hwServer);
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

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public void disconnect() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f1823a.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disconnect();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public void setHwSharingListener(int i, IAidlHwListener iAidlHwListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iAidlHwListener != null ? iAidlHwListener.asBinder() : null);
                    if (!this.f1823a.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setHwSharingListener(i, iAidlHwListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public void clsHwSharingListener(int i, IAidlHwListener iAidlHwListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iAidlHwListener != null ? iAidlHwListener.asBinder() : null);
                    if (!this.f1823a.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clsHwSharingListener(i, iAidlHwListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean playMedia(int i, HwMediaInfo hwMediaInfo, boolean z, HwObject hwObject) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (hwMediaInfo != null) {
                        obtain.writeInt(1);
                        hwMediaInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(z ? 1 : 0);
                    if (hwObject != null) {
                        obtain.writeInt(1);
                        hwObject.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.f1823a.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playMedia(i, hwMediaInfo, z, hwObject);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public HwMediaPosition getPosition(int i) throws RemoteException {
                HwMediaPosition createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.f1823a.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = Stub.getDefaultImpl().getPosition(i);
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? HwMediaPosition.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean seek(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (!this.f1823a.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().seek(i, str);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean pause(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.f1823a.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().pause(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean resume(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.f1823a.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().resume(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean stop(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.f1823a.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stop(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean isRendering(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.f1823a.transact(16, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isRendering(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean hasPlayer(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.f1823a.transact(17, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasPlayer(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public HwMediaInfo getMediaInfo(int i) throws RemoteException {
                HwMediaInfo createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.f1823a.transact(18, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = Stub.getDefaultImpl().getMediaInfo(i);
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? HwMediaInfo.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public int getSeekTarget(int i) throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.f1823a.transact(19, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getSeekTarget(i);
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

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public int getVolume(int i) throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.f1823a.transact(20, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getVolume(i);
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

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean setVolume(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.f1823a.transact(21, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setVolume(i, i2);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public void notifyVolumeChanged(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.f1823a.transact(22, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyVolumeChanged(i, i2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public void notifyPositionChanged(int i, HwMediaPosition hwMediaPosition) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (hwMediaPosition != null) {
                        obtain.writeInt(1);
                        hwMediaPosition.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.f1823a.transact(23, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyPositionChanged(i, hwMediaPosition);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public void notifyTransportStateChanged(int i, EHwTransportState eHwTransportState) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (eHwTransportState != null) {
                        obtain.writeInt(1);
                        eHwTransportState.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.f1823a.transact(24, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyTransportStateChanged(i, eHwTransportState);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public HwServer getRenderingServer() throws RemoteException {
                HwServer createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f1823a.transact(25, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = Stub.getDefaultImpl().getRenderingServer();
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? HwServer.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean startScanDevice(int i, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.f1823a.transact(26, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startScanDevice(i, z);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean stopScanDevice(int i, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.f1823a.transact(27, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopScanDevice(i, z);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean connectDevice(int i, ProjectionDevice projectionDevice) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (projectionDevice != null) {
                        obtain.writeInt(1);
                        projectionDevice.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.f1823a.transact(28, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().connectDevice(i, projectionDevice);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean disconnectDevice(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.f1823a.transact(29, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disconnectDevice(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public String getTargetDevName(int i) throws RemoteException {
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.f1823a.transact(30, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readString = Stub.getDefaultImpl().getTargetDevName(i);
                    } else {
                        obtain2.readException();
                        readString = obtain2.readString();
                    }
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public String getTargetDevIndication(int i) throws RemoteException {
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.f1823a.transact(31, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readString = Stub.getDefaultImpl().getTargetDevIndication(i);
                    } else {
                        obtain2.readException();
                        readString = obtain2.readString();
                    }
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean isConnected(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.f1823a.transact(32, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isConnected(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean isDisplayConnecting(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.f1823a.transact(33, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDisplayConnecting(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean play(int i, HwMediaInfo hwMediaInfo, ProjectionDevice projectionDevice) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (hwMediaInfo != null) {
                        obtain.writeInt(1);
                        hwMediaInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (projectionDevice != null) {
                        obtain.writeInt(1);
                        projectionDevice.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.f1823a.transact(34, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().play(i, hwMediaInfo, projectionDevice);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean pauseWifiDisplay(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.f1823a.transact(35, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().pauseWifiDisplay(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean resumeWifiDisplay(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.f1823a.transact(36, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().resumeWifiDisplay(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public int getSdkVersion() throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f1823a.transact(37, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getSdkVersion();
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

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public ProjectionDevice getTargetProjectionDevice(int i) throws RemoteException {
                ProjectionDevice createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.f1823a.transact(38, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = Stub.getDefaultImpl().getTargetProjectionDevice(i);
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? ProjectionDevice.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public int getHiSightServerPort() throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f1823a.transact(39, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getHiSightServerPort();
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

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
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
                        if (!this.f1823a.transact(40, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
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

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public void setRemoteCtrlEventProcessor(IRemoteCtrlEventProcessor iRemoteCtrlEventProcessor) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iRemoteCtrlEventProcessor != null ? iRemoteCtrlEventProcessor.asBinder() : null);
                    if (!this.f1823a.transact(41, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRemoteCtrlEventProcessor(iRemoteCtrlEventProcessor);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public int sendRemoteCtrlData(int i, int i2, byte[] bArr) throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeByteArray(bArr);
                    if (!this.f1823a.transact(42, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
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

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean isDisplayConnected(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.f1823a.transact(43, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDisplayConnected(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public int getMsdpServerPort() throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f1823a.transact(44, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getMsdpServerPort();
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

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public String convertFilePathToDmsUrl(String str) throws RemoteException {
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.f1823a.transact(45, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readString = Stub.getDefaultImpl().convertFilePathToDmsUrl(str);
                    } else {
                        obtain2.readException();
                        readString = obtain2.readString();
                    }
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean setAuthenticationInfo(AuthenticationInfo authenticationInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (authenticationInfo != null) {
                        obtain.writeInt(1);
                        authenticationInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.f1823a.transact(46, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAuthenticationInfo(authenticationInfo);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public ConnectionInfo getConnectionInfo(int i) throws RemoteException {
                ConnectionInfo createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.f1823a.transact(47, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = Stub.getDefaultImpl().getConnectionInfo(i);
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? ConnectionInfo.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean setUsingCapability(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.f1823a.transact(48, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setUsingCapability(i, i2);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public void colorAdaption(ColorSet colorSet) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (colorSet != null) {
                        obtain.writeInt(1);
                        colorSet.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.f1823a.transact(49, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().colorAdaption(colorSet);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean inputJsonDataForCast(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.f1823a.transact(50, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().inputJsonDataForCast(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean castConnectDevice(ConnectInfo connectInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (connectInfo != null) {
                        obtain.writeInt(1);
                        connectInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.f1823a.transact(51, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().castConnectDevice(connectInfo);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean castPlay(PlayInfo playInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (playInfo != null) {
                        obtain.writeInt(1);
                        playInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.f1823a.transact(52, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().castPlay(playInfo);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean registerMediaPlayerListener(IAidlMediaPlayerListener iAidlMediaPlayerListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAidlMediaPlayerListener != null ? iAidlMediaPlayerListener.asBinder() : null);
                    if (!this.f1823a.transact(53, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerMediaPlayerListener(iAidlMediaPlayerListener);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean unregisterMediaPlayerListener() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f1823a.transact(54, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterMediaPlayerListener();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean updatePlayInfo(PlayInfo playInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (playInfo != null) {
                        obtain.writeInt(1);
                        playInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.f1823a.transact(55, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updatePlayInfo(playInfo);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean previous() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f1823a.transact(56, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().previous();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean next() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f1823a.transact(57, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().next();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean setRepeatMode(ERepeatMode eRepeatMode) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (eRepeatMode != null) {
                        obtain.writeInt(1);
                        eRepeatMode.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.f1823a.transact(58, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setRepeatMode(eRepeatMode);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean playMediaItem(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.f1823a.transact(59, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playMediaItem(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean startScan(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.f1823a.transact(60, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startScan(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean showProjectionActivity(ConfigInfo configInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (configInfo != null) {
                        obtain.writeInt(1);
                        configInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.f1823a.transact(61, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().showProjectionActivity(configInfo);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean seekTo(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.f1823a.transact(62, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().seekTo(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean setVolumeMute(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.f1823a.transact(63, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setVolumeMute(z);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean castDisconnectDevice(ProjectionDevice projectionDevice) throws RemoteException {
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
                    if (!this.f1823a.transact(64, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().castDisconnectDevice(projectionDevice);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean isCastConnected(ProjectionDevice projectionDevice, EProjectionMode eProjectionMode) throws RemoteException {
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
                    if (!this.f1823a.transact(65, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isCastConnected(projectionDevice, eProjectionMode);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean stopScan(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.f1823a.transact(66, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopScan(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean setExtendInfo(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.f1823a.transact(67, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setExtendInfo(str, str2);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public String setConfigInfo(ConfigInfo configInfo) throws RemoteException {
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (configInfo != null) {
                        obtain.writeInt(1);
                        configInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.f1823a.transact(68, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readString = Stub.getDefaultImpl().setConfigInfo(configInfo);
                    } else {
                        obtain2.readException();
                        readString = obtain2.readString();
                    }
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
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
                    if (!this.f1823a.transact(69, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
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

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean chooseDialogResult(DialogInfo dialogInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (dialogInfo != null) {
                        obtain.writeInt(1);
                        dialogInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.f1823a.transact(70, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().chooseDialogResult(dialogInfo);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean updateApplication(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.f1823a.transact(71, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateApplication(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public GrabState getCurrentGrabState() throws RemoteException {
                GrabState createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f1823a.transact(72, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = Stub.getDefaultImpl().getCurrentGrabState();
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? GrabState.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public boolean configGrabState(int i, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    if (!this.f1823a.transact(73, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().configGrabState(i, j);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public int getVersion() throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.f1823a.transact(74, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getVersion();
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

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public ICastSession createCastSession(int i) throws RemoteException {
                ICastSession asInterface;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.f1823a.transact(75, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        asInterface = Stub.getDefaultImpl().createCastSession(i);
                    } else {
                        obtain2.readException();
                        asInterface = ICastSession.Stub.asInterface(obtain2.readStrongBinder());
                    }
                    return asInterface;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.android.airsharing.client.IAidlHwPlayerManager
            public ICastSession getCurrentCastSession(ProjectionDevice projectionDevice) throws RemoteException {
                ICastSession asInterface;
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
                    if (!this.f1823a.transact(76, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        asInterface = Stub.getDefaultImpl().getCurrentCastSession(projectionDevice);
                    } else {
                        obtain2.readException();
                        asInterface = ICastSession.Stub.asInterface(obtain2.readStrongBinder());
                    }
                    return asInterface;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAidlHwPlayerManager iAidlHwPlayerManager) {
            if (b.b != null || iAidlHwPlayerManager == null) {
                return false;
            }
            b.b = iAidlHwPlayerManager;
            return true;
        }

        public static IAidlHwPlayerManager getDefaultImpl() {
            return b.b;
        }
    }
}
