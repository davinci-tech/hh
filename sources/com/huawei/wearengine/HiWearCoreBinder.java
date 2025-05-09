package com.huawei.wearengine;

import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.knit.ui.KnitHealthDetailActivity;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hms.network.embedded.r3;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.wearengine.HiWearCoreBinder;
import com.huawei.wearengine.HiWearEngineService;
import com.huawei.wearengine.capability.EnumWearEngineCapabilityItem;
import com.huawei.wearengine.channel.WearEngineChannel;
import com.huawei.wearengine.common.WearEngineBiOperate;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.device.FoundListener;
import com.huawei.wearengine.device.GetAttributeListener;
import com.huawei.wearengine.monitor.MonitorCallback;
import com.huawei.wearengine.monitor.MonitorMessage;
import com.huawei.wearengine.monitor.QueryDataCallback;
import com.huawei.wearengine.monitor.SwitchStatusCallback;
import com.huawei.wearengine.notify.NotificationParcel;
import com.huawei.wearengine.notify.NotifySendCallback;
import com.huawei.wearengine.p2p.CancelFileTransferCallBackProxy;
import com.huawei.wearengine.p2p.FileIdentificationParcel;
import com.huawei.wearengine.p2p.IdentityInfo;
import com.huawei.wearengine.p2p.MessageParcelExtra;
import com.huawei.wearengine.p2p.P2pCancelFileTransferCallBack;
import com.huawei.wearengine.p2p.P2pPingCallback;
import com.huawei.wearengine.p2p.P2pReceiverCallback;
import com.huawei.wearengine.p2p.P2pSendCallback;
import com.huawei.wearengine.p2p.SendFileCallbackCleanup;
import com.huawei.wearengine.p2p.SendFileCallbackProxy;
import com.huawei.wearengine.utils.DeviceProcessor;
import defpackage.cun;
import defpackage.jys;
import defpackage.jyx;
import defpackage.tns;
import defpackage.tnv;
import defpackage.toc;
import defpackage.tod;
import defpackage.tog;
import defpackage.toj;
import defpackage.tpo;
import defpackage.tpr;
import defpackage.tqd;
import defpackage.tqu;
import defpackage.tqy;
import defpackage.trb;
import defpackage.trc;
import health.compact.a.utils.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public class HiWearCoreBinder extends HiWearEngineService.Stub implements SendFileCallbackCleanup {
    private static final String ASSISTANT_PACKAGE_NAME = "com.huawei.deveco.assistant";
    private static final String BI_NAME_P2PCHANNEL_STATE = "wearEngineP2pChannelStateClosed";
    private static final String GENERATE_ERROR_RESULT = "generateErrorResult remoteException";
    private static final String PERMISSION_FAILED = "Permission failed";
    private static final String TAG = "WearEngine_HiWearCoreBinder";
    private List<SendFileCallbackProxy> mSendFileProxyList = new ArrayList();
    private List<CancelFileTransferCallBackProxy> mCancelFileTransferProxyList = new ArrayList();
    private final Map<String, Map<String, List<String>>> mFileTransferredRecordMap = new HashMap();
    private final Object mFileTransferRecordLock = new Object();
    private IBinder.DeathRecipient deviceDataDeathRecipient = new IBinder.DeathRecipient() { // from class: com.huawei.wearengine.HiWearCoreBinder.5
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            tnv.d().c();
        }
    };
    private IBinder.DeathRecipient monitorCallbackDeathRecipient = new IBinder.DeathRecipient() { // from class: com.huawei.wearengine.HiWearCoreBinder.1
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            tns.b().d();
        }
    };

    public HiWearCoreBinder() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: tnw
            @Override // java.lang.Runnable
            public final void run() {
                HiWearCoreBinder.lambda$new$0();
            }
        });
    }

    public static /* synthetic */ void lambda$new$0() {
        LogUtil.a(TAG, "loadProductMapConfig");
        ProductMapParseUtil.b(BaseApplication.e());
    }

    @Override // com.huawei.wearengine.HiWearEngineService
    public void getBondedDevices(FoundListener foundListener, String str, boolean z) {
        LogUtil.a(TAG, "getBondedDevices enter");
        if (!checkPermission()) {
            LogUtil.b(TAG, PERMISSION_FAILED);
        } else if (foundListener == null) {
            LogUtil.h(TAG, "getBondedDevices listener is null");
        } else {
            tnv.d().e(foundListener, str, z);
        }
    }

    @Override // com.huawei.wearengine.HiWearEngineService
    public void getAllBondedDevices(FoundListener foundListener, String str) {
        LogUtil.a(TAG, "getAllBondedDevices enter");
        if (!checkPermission()) {
            LogUtil.b(TAG, PERMISSION_FAILED);
        } else if (foundListener == null) {
            LogUtil.h(TAG, "getAllBondedDevices listener is null");
        } else {
            tnv.d().a(foundListener, str);
        }
    }

    @Override // com.huawei.wearengine.HiWearEngineService
    public void getCommonDevices(FoundListener foundListener, String str, boolean z) {
        LogUtil.a(TAG, "getCommonDevices enter");
        if (!checkPermission()) {
            LogUtil.b(TAG, PERMISSION_FAILED);
        } else if (foundListener == null) {
            LogUtil.h(TAG, "getCommonDevices listener is null");
        } else {
            tnv.d().c(foundListener, str, z);
        }
    }

    @Override // com.huawei.wearengine.HiWearEngineService
    public void getHiLinkDeviceId(Device device, GetAttributeListener getAttributeListener) {
        LogUtil.a(TAG, "getHiLinkDeviceId enter");
        if (!checkPermission()) {
            LogUtil.b(TAG, PERMISSION_FAILED);
            throw new IllegalStateException(String.valueOf(12));
        }
        if (device == null) {
            LogUtil.b(TAG, "getHiLinkDeviceId device is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        String d = tnv.d().d(device.getUuid());
        LogUtil.c(TAG, "getHiLinkDeviceId identify is:", d);
        if (StringUtils.g(d)) {
            LogUtil.b(TAG, "getHiLinkDeviceId device identify is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        tnv.d().d(device, getAttributeListener);
    }

    @Override // com.huawei.wearengine.HiWearEngineService
    public void queryDeviceCapability(Device device, int i, GetAttributeListener getAttributeListener) throws RemoteException {
        LogUtil.a(TAG, "queryDeviceCapability enter");
        if (!checkPermission()) {
            LogUtil.b(TAG, PERMISSION_FAILED);
            throw new IllegalStateException(String.valueOf(12));
        }
        if (device == null) {
            LogUtil.b(TAG, "queryDeviceCapability device is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        String d = tnv.d().d(device.getUuid());
        LogUtil.c(TAG, "queryDeviceCapability identify is:", d);
        if (StringUtils.g(d)) {
            LogUtil.b(TAG, "queryDeviceCapability device identify is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        tnv.d().c(device, i, getAttributeListener);
    }

    @Override // com.huawei.wearengine.HiWearEngineService
    public void ping(Device device, String str, String str2, P2pPingCallback p2pPingCallback) {
        LogUtil.a(TAG, "ping enter");
        String pingPreCheckAndGetIdentity = pingPreCheckAndGetIdentity(device, str, str2, p2pPingCallback);
        if (isSuperCall(str)) {
            ping(pingPreCheckAndGetIdentity, str, str2, p2pPingCallback);
        } else {
            handleThreePartyPingCall(pingPreCheckAndGetIdentity, device.getUuid(), str, str2, p2pPingCallback);
        }
    }

    private void ping(String str, String str2, String str3, P2pPingCallback p2pPingCallback) {
        int b = tnv.d().b();
        LogUtil.a(TAG, "ping sequenceNum is:", Integer.valueOf(b));
        DeviceCommand e = trc.e(b, 1, str2, str3);
        if (e == null) {
            LogUtil.b(TAG, "ping deviceCommand is null");
            generateErrorResult(p2pPingCallback, 12);
            throw new IllegalStateException(String.valueOf(12));
        }
        LogUtil.a(TAG, "ping deviceCommand is:", e.toString());
        e.setmIdentify(str);
        tqy.a(e);
        tnv.d().b(b, (Object) p2pPingCallback);
    }

    private String pingPreCheckAndGetIdentity(Device device, String str, String str2, P2pPingCallback p2pPingCallback) {
        if (p2pPingCallback == null) {
            LogUtil.h(TAG, "ping pingCallback is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        LogUtil.a(TAG, "ping srcPkgName is:", str, ", destPkgName is:", str2);
        if (device == null || !trc.b(str) || !trc.b(str2)) {
            LogUtil.h(TAG, "ping device or pkgName is invalid");
            generateErrorResult(p2pPingCallback, 5);
            throw new IllegalStateException(String.valueOf(5));
        }
        String uuid = device.getUuid();
        if (TextUtils.isEmpty(uuid)) {
            LogUtil.b(TAG, "ping deviceId is empty");
            throw new IllegalStateException(String.valueOf(5));
        }
        String d = tnv.d().d(uuid);
        LogUtil.c(TAG, "ping identify is:", d);
        if (TextUtils.isEmpty(d)) {
            LogUtil.b(TAG, "ping device identify is null");
            generateErrorResult(p2pPingCallback, 5);
            throw new IllegalStateException(String.valueOf(5));
        }
        if (tog.c(uuid, EnumWearEngineCapabilityItem.POINT_TO_POINT_PING_AND_SEND_BYTES_ENUM)) {
            return d;
        }
        LogUtil.b(TAG, "ping device is not support");
        throw new IllegalStateException(String.valueOf(13));
    }

    private boolean isSuperCall(String str) {
        Set<String> superCallerSet = DeviceProcessor.getSuperCallerSet();
        superCallerSet.add(BaseApplication.d());
        boolean contains = superCallerSet.contains(str);
        LogUtil.c(TAG, "isSuperCall is ", Boolean.valueOf(contains));
        return contains;
    }

    private P2pPingCallback getPingCallBackProxy(final String str, final String str2, final String str3, final P2pPingCallback p2pPingCallback) {
        return new P2pPingCallback.Stub() { // from class: com.huawei.wearengine.HiWearCoreBinder.3
            @Override // com.huawei.wearengine.p2p.P2pPingCallback
            public void onResult(int i) throws RemoteException {
                LogUtil.a(HiWearCoreBinder.TAG, "proxyPingCallBack resultCode is ", Integer.valueOf(i));
                HiWearCoreBinder.this.handleProxyPingCallBack(i, str, str2, str3, p2pPingCallback);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleProxyPingCallBack(int i, String str, String str2, String str3, P2pPingCallback p2pPingCallback) {
        int i2;
        int i3 = 1;
        if (i == 200) {
            i2 = 1;
            i3 = 0;
        } else {
            i2 = 0;
        }
        toc e = toc.e();
        if (getWearEngineP2pChannelList(str, str2, str3).size() == 0) {
            LogUtil.a(TAG, "pingCallBackProxy add new P2pChannel");
            toj tojVar = new toj();
            tojVar.updateChannelState(i2);
            e.e(str, str2, str3, tojVar);
        } else {
            LogUtil.a(TAG, "pingCallBackProxy update P2pChannel");
            tod todVar = new tod();
            todVar.b(i3);
            todVar.b(str3);
            e.b(str, todVar);
        }
        if (i == 200) {
            WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
            wearEngineBiOperate.init();
            wearEngineBiOperate.biApiCalling(BaseApplication.e(), str2, BI_NAME_P2PCHANNEL_STATE, String.valueOf(0));
        }
        generateErrorResult(p2pPingCallback, i);
    }

    private void handleThreePartyPingCall(String str, String str2, String str3, String str4, P2pPingCallback p2pPingCallback) {
        if (!tog.d(str2, EnumWearEngineCapabilityItem.WEAR_APP_INSTALLATION_REPORT_ENUM)) {
            LogUtil.a(TAG, "handleThreePartyPingCall isWearAppInstallationSupport is't support");
            ping(str, str3, str4, p2pPingCallback);
            return;
        }
        P2pPingCallback pingCallBackProxy = getPingCallBackProxy(str, str3, str4, p2pPingCallback);
        List<WearEngineChannel> wearEngineP2pChannelList = getWearEngineP2pChannelList(str, str3, str4);
        if (wearEngineP2pChannelList.size() == 0) {
            LogUtil.a(TAG, "handleThreePartyPingCall p2pChannelSize is zero");
            ping(str, str3, str4, pingCallBackProxy);
        } else if (checkWearEngineP2pChannel(r3.r, wearEngineP2pChannelList)) {
            LogUtil.a(TAG, "handleThreePartyPingCall P2pChannel is open");
            ping(str, str3, str4, pingCallBackProxy);
        } else {
            LogUtil.a(TAG, "handleThreePartyPingCall srcPkgName ", str3, "，P2pChannel is close");
            generateErrorResult(p2pPingCallback, 200);
        }
    }

    private boolean checkWearEngineP2pChannel(String str, List<WearEngineChannel> list) {
        if (list.size() == 0) {
            return true;
        }
        Iterator<WearEngineChannel> it = list.iterator();
        while (it.hasNext()) {
            if (!it.next().isChannelOpen()) {
                LogUtil.a(TAG, str, " wearEngineChannel is close");
                return false;
            }
        }
        return true;
    }

    private List<WearEngineChannel> getWearEngineP2pChannelList(String str, String str2, String str3) {
        return toc.e().d(str, str2, str3);
    }

    private void generateErrorResult(Object obj, int i) {
        try {
            if (obj instanceof P2pPingCallback) {
                ((P2pPingCallback) obj).onResult(i);
            } else if (obj instanceof QueryDataCallback) {
                ((QueryDataCallback) obj).onDataReceived(i, new MonitorMessage());
            } else {
                LogUtil.b(TAG, "generateErrorResult callback not target class");
            }
        } catch (RemoteException unused) {
            LogUtil.b(TAG, GENERATE_ERROR_RESULT);
        }
    }

    @Override // com.huawei.wearengine.HiWearEngineService
    public void send(String str, MessageParcelExtra messageParcelExtra, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pSendCallback p2pSendCallback) {
        LogUtil.a(TAG, "send enter");
        if (!checkSendParams(str, messageParcelExtra, identityInfo, identityInfo2, p2pSendCallback)) {
            throw new IllegalStateException(String.valueOf(5));
        }
        String d = tnv.d().d(str);
        LogUtil.c(TAG, "send identify is:", d);
        if (TextUtils.isEmpty(d)) {
            LogUtil.b(TAG, "send device identify is null");
            sendErrorResult(p2pSendCallback, 5);
            throw new IllegalStateException(String.valueOf(5));
        }
        tqd tqdVar = new tqd(identityInfo, identityInfo2);
        if (messageParcelExtra.getType() == 1) {
            sendData(d, str, messageParcelExtra, tqdVar, p2pSendCallback);
        } else {
            sendFile(str, messageParcelExtra, identityInfo, identityInfo2, p2pSendCallback);
        }
    }

    private boolean checkSendParams(String str, MessageParcelExtra messageParcelExtra, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pSendCallback p2pSendCallback) {
        if (p2pSendCallback == null) {
            LogUtil.h(TAG, "checkSendParams sendCallback is null");
            return false;
        }
        if (messageParcelExtra == null) {
            LogUtil.h(TAG, "checkSendParams message is null");
            sendErrorResult(p2pSendCallback, 12);
            return false;
        }
        if (identityInfo == null || identityInfo2 == null) {
            sendErrorResult(p2pSendCallback, 5);
            LogUtil.h(TAG, "checkSendParams pkgInfo is null");
            return false;
        }
        LogUtil.a(TAG, "checkSendParams srcPkgName is:", identityInfo.getPackageName(), " destPkgName is:", identityInfo2.getPackageName());
        if (!TextUtils.isEmpty(str) && trc.b(identityInfo.getPackageName()) && trc.b(identityInfo2.getPackageName())) {
            return true;
        }
        LogUtil.h(TAG, "checkSendParams device id is null or pkgName is invalid");
        sendErrorResult(p2pSendCallback, 5);
        return false;
    }

    private void sendData(String str, String str2, MessageParcelExtra messageParcelExtra, tqd tqdVar, P2pSendCallback p2pSendCallback) {
        byte[] data = messageParcelExtra.getData();
        if (data == null || data.length == 0) {
            LogUtil.h(TAG, "send message data is invalid");
            sendErrorResult(p2pSendCallback, 5);
            throw new IllegalStateException(String.valueOf(5));
        }
        if (!tog.c(str2, EnumWearEngineCapabilityItem.POINT_TO_POINT_PING_AND_SEND_BYTES_ENUM)) {
            LogUtil.b(TAG, "send device is not support");
            throw new IllegalStateException(String.valueOf(13));
        }
        String packageName = tqdVar.a().getPackageName();
        String packageName2 = tqdVar.b().getPackageName();
        if (isSuperCall(packageName)) {
            innerSendData(str, tqdVar, getSendCallbackProxy(str, packageName, packageName2, p2pSendCallback, false), data);
        } else {
            handleThreePartySendDataCall(str, str2, messageParcelExtra, tqdVar, p2pSendCallback);
        }
    }

    private void handleThreePartySendDataCall(String str, String str2, MessageParcelExtra messageParcelExtra, tqd tqdVar, P2pSendCallback p2pSendCallback) {
        byte[] data = messageParcelExtra.getData();
        String packageName = tqdVar.a().getPackageName();
        String packageName2 = tqdVar.b().getPackageName();
        if (!tog.c(str2, EnumWearEngineCapabilityItem.WEAR_APP_INSTALLATION_REPORT_ENUM)) {
            LogUtil.a(TAG, "handleThreePartySendDataCall isWearAppInstallationSupport is't support");
            innerSendData(str, tqdVar, getSendCallbackProxy(str, packageName, packageName2, p2pSendCallback, false), data);
            return;
        }
        P2pSendCallback sendCallbackProxy = getSendCallbackProxy(str, packageName, packageName2, p2pSendCallback, true);
        List<WearEngineChannel> wearEngineP2pChannelList = getWearEngineP2pChannelList(str, packageName, packageName2);
        if (wearEngineP2pChannelList.size() == 0) {
            LogUtil.a(TAG, "handleThreePartySendDataCall p2pChannelSize is zero");
            innerSendData(str, tqdVar, sendCallbackProxy, data);
        } else if (checkWearEngineP2pChannel("sendData", wearEngineP2pChannelList)) {
            LogUtil.a(TAG, "handleThreePartySendDataCall P2pChannel is open");
            innerSendData(str, tqdVar, sendCallbackProxy, data);
        } else {
            LogUtil.a(TAG, "handleThreePartySendDataCall srcName ", packageName, "，P2pChannel is close");
            sendErrorResult(p2pSendCallback, 206);
        }
    }

    private P2pSendCallback getSendCallbackProxy(final String str, final String str2, final String str3, final P2pSendCallback p2pSendCallback, final boolean z) {
        return new P2pSendCallback.Stub() { // from class: com.huawei.wearengine.HiWearCoreBinder.4
            @Override // com.huawei.wearengine.p2p.P2pSendCallback
            public void onFileTransferReport(String str4) throws RemoteException {
            }

            @Override // com.huawei.wearengine.p2p.P2pSendCallback
            public void onSendResult(int i) {
                LogUtil.a(HiWearCoreBinder.TAG, "sendData resultCode is ", Integer.valueOf(i));
                int i2 = i == 200 ? 206 : i;
                if (z) {
                    HiWearCoreBinder.this.addOrUpdateWearEngineP2pChannel(i, str, str2, str3);
                }
                LogUtil.a(HiWearCoreBinder.TAG, "sendData sendBack resultCode is ", Integer.valueOf(i2));
                sendCallback(i2);
            }

            @Override // com.huawei.wearengine.p2p.P2pSendCallback
            public void onSendProgress(long j) throws RemoteException {
                p2pSendCallback.onSendProgress(j);
            }

            private void sendCallback(int i) {
                try {
                    p2pSendCallback.onSendResult(i);
                } catch (Exception unused) {
                    LogUtil.b(HiWearCoreBinder.TAG, "SendErrorResult e");
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addOrUpdateWearEngineP2pChannel(int i, String str, String str2, String str3) {
        int i2;
        int i3 = 1;
        if (i == 200) {
            i2 = 1;
            i3 = 0;
        } else {
            i2 = 0;
        }
        toc e = toc.e();
        if (getWearEngineP2pChannelList(str, str2, str3).size() == 0) {
            LogUtil.a(TAG, "addOrUpdateWearEngineP2pChannel add new P2pChannel");
            toj tojVar = new toj();
            tojVar.updateChannelState(i2);
            e.e(str, str2, str3, tojVar);
        } else {
            LogUtil.a(TAG, "addOrUpdateWearEngineP2pChannel update P2pChannel");
            tod todVar = new tod();
            todVar.b(i3);
            todVar.b(str3);
            e.b(str, todVar);
        }
        if (i == 200) {
            WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
            wearEngineBiOperate.init();
            wearEngineBiOperate.biApiCalling(BaseApplication.e(), str2, BI_NAME_P2PCHANNEL_STATE, String.valueOf(0));
        }
    }

    private void innerSendData(String str, tqd tqdVar, P2pSendCallback p2pSendCallback, byte[] bArr) {
        LogUtil.a(TAG, "send message data is:", Arrays.toString(bArr));
        int b = tnv.d().b();
        LogUtil.a(TAG, "send sequenceNum is:", Integer.valueOf(b));
        DeviceCommand a2 = trc.a(b, tqdVar.a(), tqdVar.b(), bArr);
        if (a2 == null) {
            LogUtil.b(TAG, "ping deviceCommand is null");
            sendErrorResult(p2pSendCallback, 12);
            throw new IllegalStateException(String.valueOf(12));
        }
        LogUtil.a(TAG, "send deviceCommand is:", a2.toString());
        a2.setmIdentify(str);
        tnv.d().b(b, p2pSendCallback);
        tqy.a(a2);
    }

    private void sendFile(String str, MessageParcelExtra messageParcelExtra, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pSendCallback p2pSendCallback) {
        LogUtil.a(TAG, "sendFile enter");
        String packageName = identityInfo.getPackageName();
        String packageName2 = identityInfo2.getPackageName();
        if (!ASSISTANT_PACKAGE_NAME.equals(packageName)) {
            if (!tog.c(str, EnumWearEngineCapabilityItem.POINT_TO_POINT_SEND_FILES_TO_WATCH_ENUM)) {
                LogUtil.b(TAG, "sendFile device is not support");
                throw new IllegalStateException(String.valueOf(13));
            }
        } else {
            LogUtil.a(TAG, "The watch capability does not need to be verified.");
        }
        SendFileCallbackProxy sendFileCallbackProxy = new SendFileCallbackProxy(this, p2pSendCallback, packageName, packageName2, messageParcelExtra.getFileName());
        this.mSendFileProxyList.add(sendFileCallbackProxy);
        addSrcFileRecord(packageName, packageName2, messageParcelExtra.getFileName());
        jyx.a().b(getSendFileInfo(messageParcelExtra, identityInfo, identityInfo2, sendFileCallbackProxy), getDevice(str));
    }

    private jys getSendFileInfo(MessageParcelExtra messageParcelExtra, IdentityInfo identityInfo, IdentityInfo identityInfo2, SendFileCallbackProxy sendFileCallbackProxy) {
        jys jysVar = new jys();
        jysVar.bLf_(messageParcelExtra.getParcelFileDescriptor());
        jysVar.e(messageParcelExtra.getFileName());
        jysVar.h(7);
        jysVar.d(messageParcelExtra.getDescription());
        jysVar.i(identityInfo.getPackageName());
        jysVar.b(identityInfo2.getPackageName());
        jysVar.j(identityInfo.getFingerPrint());
        jysVar.c(identityInfo2.getFingerPrint());
        jysVar.f(messageParcelExtra.getFileSha256());
        jysVar.b(sendFileCallbackProxy);
        return jysVar;
    }

    @Override // com.huawei.wearengine.HiWearEngineService
    public void subscribeDeviceDataReceiver(P2pReceiverCallback p2pReceiverCallback) {
        if (!checkPermission()) {
            LogUtil.b(TAG, PERMISSION_FAILED);
            return;
        }
        tnv.d().d(p2pReceiverCallback);
        if (p2pReceiverCallback != null) {
            try {
                p2pReceiverCallback.asBinder().linkToDeath(this.deviceDataDeathRecipient, 0);
            } catch (RemoteException unused) {
                LogUtil.b(TAG, "subscribeDevice discoverDevices remoteException");
            }
        }
    }

    @Override // com.huawei.wearengine.HiWearEngineService
    public void unsubscribeDeviceDataReceiver() {
        P2pReceiverCallback a2 = tnv.d().a();
        if (a2 != null) {
            a2.asBinder().unlinkToDeath(this.deviceDataDeathRecipient, 0);
        }
        tnv.d().c();
    }

    private void sendErrorResult(P2pSendCallback p2pSendCallback, int i) {
        try {
            p2pSendCallback.onSendResult(i);
            p2pSendCallback.onSendProgress(0L);
        } catch (Exception unused) {
            LogUtil.b(TAG, "SendErrorResult e");
        }
    }

    @Override // com.huawei.wearengine.HiWearEngineService
    public void query(Device device, String str, QueryDataCallback queryDataCallback) {
        LogUtil.a(TAG, "query enter");
        if (queryDataCallback == null) {
            LogUtil.h(TAG, "queryDataCallback is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        if (str == null || device == null) {
            generateErrorResult(queryDataCallback, 5);
            LogUtil.h(TAG, "query device or eventType is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        if (str.equals("powerMode")) {
            handlePowerModeQuery(device, queryDataCallback);
            return;
        }
        String uuid = device.getUuid();
        if (TextUtils.isEmpty(uuid)) {
            LogUtil.b(TAG, "queryDataCallback deviceId is empty");
            throw new IllegalStateException(String.valueOf(5));
        }
        String d = tnv.d().d(uuid);
        LogUtil.c(TAG, "query identify is:", d);
        if (StringUtils.g(d)) {
            LogUtil.b(TAG, "query device identify is null");
            generateErrorResult(queryDataCallback, 5);
            throw new IllegalStateException(String.valueOf(5));
        }
        checkDeviceQueryCapability(uuid, str);
        tns.b().a(d, str, queryDataCallback);
    }

    private void handlePowerModeQuery(Device device, QueryDataCallback queryDataCallback) {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, TAG);
        if (deviceList == null || deviceList.isEmpty()) {
            LogUtil.b(TAG, "handlePowerModeQuery deviceInfoList is empty");
            throw new IllegalStateException(String.valueOf(16));
        }
        DeviceInfo e = tqy.e(device, deviceList);
        if (e == null) {
            LogUtil.b(TAG, "handlePowerModeQuery deviceInfo is null");
            throw new IllegalStateException(String.valueOf(16));
        }
        try {
            Device c = tqy.c(e);
            if (c == null) {
                LogUtil.b(TAG, "handlePowerModeQuery hiWearDevice is null");
                throw new IllegalStateException(String.valueOf(12));
            }
            queryDataCallback.onDataReceived(0, new MonitorMessage().setMonitorItemType("powerMode").setDeviceId(c.getUuid()).setProductType(c.getProductType()).setIntData(tqy.b(e)));
        } catch (RemoteException unused) {
            LogUtil.b(TAG, "handlePowerModeQuery RemoteException");
        }
    }

    @Override // com.huawei.wearengine.HiWearEngineService
    public void subscribeMonitorReceiver(MonitorCallback monitorCallback) {
        if (!checkPermission()) {
            LogUtil.b(TAG, PERMISSION_FAILED);
            return;
        }
        tns.b().e(monitorCallback);
        if (monitorCallback != null) {
            try {
                monitorCallback.asBinder().linkToDeath(this.monitorCallbackDeathRecipient, 0);
            } catch (RemoteException unused) {
                LogUtil.b(TAG, "subscribeDevice discoverDevices remoteException");
            }
        }
    }

    @Override // com.huawei.wearengine.HiWearEngineService
    public void unsubscribeMonitorReceiver() {
        MonitorCallback e = tns.b().e();
        if (e != null) {
            e.asBinder().unlinkToDeath(this.monitorCallbackDeathRecipient, 0);
        }
        tns.b().d();
    }

    @Override // com.huawei.wearengine.HiWearEngineService
    public void notify(String str, NotificationParcel notificationParcel, NotifySendCallback notifySendCallback) {
        LogUtil.a(TAG, "notify enter");
        if (notifySendCallback == null || notificationParcel == null) {
            LogUtil.h(TAG, "notifySendCallback or notificationParcel is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "notify deviceId is empty");
            try {
                notifySendCallback.onError(notificationParcel, 5);
            } catch (RemoteException unused) {
                LogUtil.b(TAG, GENERATE_ERROR_RESULT);
            } catch (Exception unused2) {
                LogUtil.b(TAG, "notifySendCallback onError has exception");
            }
            throw new IllegalStateException(String.valueOf(5));
        }
        String d = tnv.d().d(str);
        LogUtil.c(TAG, "notify identify is:", d);
        if (StringUtils.g(d)) {
            LogUtil.b(TAG, "notify device identify is null");
            try {
                notifySendCallback.onError(notificationParcel, 5);
            } catch (RemoteException unused3) {
                LogUtil.b(TAG, GENERATE_ERROR_RESULT);
            } catch (Exception unused4) {
                LogUtil.b(TAG, "notifySendCallback onError has exception");
            }
            throw new IllegalStateException(String.valueOf(5));
        }
        if (!tog.c(str, EnumWearEngineCapabilityItem.SEND_NOTIFY_TO_WATCH_ENUM)) {
            LogUtil.b(TAG, "notify device is not support");
            throw new IllegalStateException(String.valueOf(13));
        }
        String a2 = tpr.e().a();
        LogUtil.a(TAG, "notificationId is:", a2);
        DeviceCommand d2 = trb.d(notificationParcel, a2);
        LogUtil.a(TAG, "notification command is:", d2.toString());
        d2.setmIdentify(d);
        tpr.e().c(a2, notificationParcel, notifySendCallback);
        tqy.a(d2);
    }

    @Override // com.huawei.wearengine.HiWearEngineService
    public void switchMonitorStatus(int i, String str, String str2, SwitchStatusCallback switchStatusCallback) {
        if (switchStatusCallback == null) {
            LogUtil.h(TAG, "callback is null in switchMonitorStatus");
            throw new IllegalStateException(String.valueOf(5));
        }
        if (i == 0 || str2 == null) {
            LogUtil.h(TAG, "switchStatus invalid or eventType is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "deviceId is empty in switchMonitorStatus");
            throw new IllegalStateException(String.valueOf(5));
        }
        String d = tnv.d().d(str);
        LogUtil.c(TAG, "switchMonitorStatus identify is:", d);
        if (StringUtils.g(d)) {
            LogUtil.b(TAG, "switchMonitorStatus device identify is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        checkDeviceReportCapability(str, str2);
        DeviceCommand c = tqu.c(i, str2);
        if (c == null) {
            LogUtil.b(TAG, "transform switch command is null");
            throw new IllegalStateException(String.valueOf(12));
        }
        LogUtil.a(TAG, "transform switch command is:", c.toString());
        c.setmIdentify(d);
        tpo.d().d(str2, i, switchStatusCallback);
        tqy.a(c);
    }

    private void checkDeviceReportCapability(String str, String str2) {
        EnumWearEngineCapabilityItem reportCapabilityItemByEventType = getReportCapabilityItemByEventType(str2);
        if (reportCapabilityItemByEventType == null) {
            LogUtil.b(TAG, "checkDeviceReportCapability eventType not support report");
            throw new IllegalStateException(String.valueOf(5));
        }
        if (tog.c(str, reportCapabilityItemByEventType)) {
            return;
        }
        LogUtil.b(TAG, "checkDeviceReportCapability device is not support");
        throw new IllegalStateException(String.valueOf(13));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private EnumWearEngineCapabilityItem getReportCapabilityItemByEventType(String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -1943109551:
                if (str.equals("wearStatus")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1837176303:
                if (str.equals("lowPower")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -615154554:
                if (str.equals("sportStatus")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 628829609:
                if (str.equals(KnitHealthDetailActivity.KEY_SLEEP_STATUS)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1206544299:
                if (str.equals("heartRateAlarm")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1267337158:
                if (str.equals("chargeStatus")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            return EnumWearEngineCapabilityItem.WEAR_STATUS_QUERY_AND_REPORT_ENUM;
        }
        if (c == 1) {
            return EnumWearEngineCapabilityItem.LOW_POWER_REPORT_ENUM;
        }
        if (c == 2) {
            return EnumWearEngineCapabilityItem.SPORT_STATUS_QUERY_AND_REPORT_ENUM;
        }
        if (c == 3) {
            return EnumWearEngineCapabilityItem.SLEEP_STATUS_QUERY_AND_REPORT_ENUM;
        }
        if (c == 4) {
            return EnumWearEngineCapabilityItem.HEART_RATE_ALARM_REPORT_ENUM;
        }
        if (c != 5) {
            return null;
        }
        return EnumWearEngineCapabilityItem.CHARGING_STATUS_QUERY_AND_REPORT_ENUM;
    }

    private void checkDeviceQueryCapability(String str, String str2) {
        EnumWearEngineCapabilityItem queryCapabilityItemByEventType = getQueryCapabilityItemByEventType(str2);
        if (queryCapabilityItemByEventType == null) {
            LogUtil.b(TAG, "checkDeviceQueryCapability eventType not support query");
            throw new IllegalStateException(String.valueOf(5));
        }
        if (tog.c(str, queryCapabilityItemByEventType)) {
            return;
        }
        LogUtil.b(TAG, "checkDeviceQueryCapability device is not support");
        throw new IllegalStateException(String.valueOf(13));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private EnumWearEngineCapabilityItem getQueryCapabilityItemByEventType(String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -1943109551:
                if (str.equals("wearStatus")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -615154554:
                if (str.equals("sportStatus")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 440773975:
                if (str.equals("powerStatus")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 628829609:
                if (str.equals(KnitHealthDetailActivity.KEY_SLEEP_STATUS)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1267337158:
                if (str.equals("chargeStatus")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1623401406:
                if (str.equals("userAvailableKbytes")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            return EnumWearEngineCapabilityItem.WEAR_STATUS_QUERY_AND_REPORT_ENUM;
        }
        if (c == 1) {
            return EnumWearEngineCapabilityItem.SPORT_STATUS_QUERY_AND_REPORT_ENUM;
        }
        if (c == 2) {
            return EnumWearEngineCapabilityItem.POWER_STATUS_QUERY_ENUM;
        }
        if (c == 3) {
            return EnumWearEngineCapabilityItem.SLEEP_STATUS_QUERY_AND_REPORT_ENUM;
        }
        if (c == 4) {
            return EnumWearEngineCapabilityItem.CHARGING_STATUS_QUERY_AND_REPORT_ENUM;
        }
        if (c != 5) {
            return null;
        }
        return EnumWearEngineCapabilityItem.QUERY_DEVICE_AVAILABLE_SPACE_ENUM;
    }

    private boolean checkPermission() {
        return Binder.getCallingUid() == getCallingUid();
    }

    @Override // com.huawei.wearengine.p2p.SendFileCallbackCleanup
    public void onCleanup(SendFileCallbackProxy sendFileCallbackProxy, String str, String str2, String str3) {
        LogUtil.c(TAG, "onCleanup enter");
        if (!checkPermission()) {
            LogUtil.b(TAG, PERMISSION_FAILED);
        } else {
            this.mSendFileProxyList.remove(sendFileCallbackProxy);
            removeFileRecord(str, str2, str3);
        }
    }

    @Override // com.huawei.wearengine.p2p.SendFileCallbackCleanup
    public void onCleanupCancelFileTransfer(CancelFileTransferCallBackProxy cancelFileTransferCallBackProxy, String str, String str2, String str3) {
        LogUtil.c(TAG, "onCleanupCancelFileTransfer enter");
        if (!checkPermission()) {
            LogUtil.b(TAG, PERMISSION_FAILED);
        } else {
            this.mCancelFileTransferProxyList.remove(cancelFileTransferCallBackProxy);
            removeFileRecord(str, str2, str3);
        }
    }

    public void setHwWearEngineNative(HwWearEngineNative hwWearEngineNative) {
        LogUtil.a(TAG, "setHwWearEngineNative enter");
        if (!checkPermission()) {
            LogUtil.b(TAG, PERMISSION_FAILED);
        } else {
            tnv.d().e(hwWearEngineNative);
        }
    }

    @Override // com.huawei.wearengine.HiWearEngineService
    public void getDeviceAppVersionCode(Device device, String str, String str2, P2pPingCallback p2pPingCallback) {
        LogUtil.a(TAG, "getDeviceAppVersionCode enter");
        if (p2pPingCallback == null) {
            LogUtil.h(TAG, "getDeviceAppVersionCode versionCodeCallback is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        if (device == null || !trc.b(str) || !trc.b(str2)) {
            LogUtil.b(TAG, "getDeviceAppVersionCode device or pkgName is invalid");
            throw new IllegalStateException(String.valueOf(5));
        }
        String uuid = device.getUuid();
        if (TextUtils.isEmpty(uuid)) {
            LogUtil.b(TAG, "getDeviceAppVersionCode deviceId is empty");
            throw new IllegalStateException(String.valueOf(5));
        }
        String d = tnv.d().d(uuid);
        LogUtil.c(TAG, "getDeviceAppVersionCode identify is:", d);
        if (TextUtils.isEmpty(d)) {
            LogUtil.b(TAG, "getDeviceAppVersionCode device identify is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        if (!tog.c(uuid, EnumWearEngineCapabilityItem.QUERY_DEVICE_APP_INSTALL_INFO_ENUM)) {
            LogUtil.b(TAG, "getDeviceAppVersionCode device is not support");
            throw new IllegalStateException(String.valueOf(13));
        }
        int b = tnv.d().b();
        LogUtil.a(TAG, "getDeviceAppVersionCode sequenceNum is:", Integer.valueOf(b));
        DeviceCommand e = trc.e(b, 4, str, str2);
        if (e != null) {
            LogUtil.a(TAG, "getDeviceAppVersionCode deviceCommand is:", e.toString());
            e.setmIdentify(d);
            tqy.a(e);
            tnv.d().b(b, (Object) p2pPingCallback);
            return;
        }
        LogUtil.b(TAG, "getDeviceAppVersionCode deviceCommand is null");
        throw new IllegalStateException(String.valueOf(12));
    }

    @Override // com.huawei.wearengine.HiWearEngineService
    public void cancelFileTransfer(Device device, FileIdentificationParcel fileIdentificationParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pCancelFileTransferCallBack p2pCancelFileTransferCallBack) {
        LogUtil.a(TAG, "cancelFileTransfer enter");
        if (!checkCancelFileTransferParams(device, fileIdentificationParcel, identityInfo, identityInfo2, p2pCancelFileTransferCallBack)) {
            throw new IllegalStateException(String.valueOf(5));
        }
        String d = tnv.d().d(device.getUuid());
        LogUtil.c(TAG, "cancelFileTransfer identify is:", d);
        if (TextUtils.isEmpty(d)) {
            LogUtil.b(TAG, "cancelFileTransfer device identify is null");
            cancelFileTransferErrorResult(p2pCancelFileTransferCallBack, 5, "Invalid argument");
            throw new IllegalStateException(String.valueOf(5));
        }
        if (!checkFileRecordExist(identityInfo.getPackageName(), identityInfo2.getPackageName(), fileIdentificationParcel.getFileName())) {
            LogUtil.b(TAG, "cancelFileTransfer file transfer not exit");
            cancelFileTransferErrorResult(p2pCancelFileTransferCallBack, 5, "Invalid argument");
            throw new IllegalStateException(String.valueOf(5));
        }
        if (!ASSISTANT_PACKAGE_NAME.equals(identityInfo.getPackageName())) {
            if (!tog.c(device.getUuid(), EnumWearEngineCapabilityItem.POINT_TO_POINT_SEND_FILES_TO_WATCH_ENUM)) {
                LogUtil.b(TAG, "cancelFileTransfer device is not support");
                throw new IllegalStateException(String.valueOf(13));
            }
        } else {
            LogUtil.a(TAG, "The watch capability does not need to be verified.");
        }
        CancelFileTransferCallBackProxy cancelFileTransferCallBackProxy = new CancelFileTransferCallBackProxy(this, p2pCancelFileTransferCallBack, identityInfo.getPackageName(), identityInfo2.getPackageName(), fileIdentificationParcel.getFileName());
        this.mCancelFileTransferProxyList.add(cancelFileTransferCallBackProxy);
        jyx.a().b(getCancelFileInfo(fileIdentificationParcel, identityInfo, identityInfo2, cancelFileTransferCallBackProxy), cancelFileTransferCallBackProxy, getDevice(device.getUuid()));
    }

    private void cancelFileTransferErrorResult(P2pCancelFileTransferCallBack p2pCancelFileTransferCallBack, int i, String str) {
        try {
            p2pCancelFileTransferCallBack.onCancelFileTransferResult(i, str);
        } catch (Exception unused) {
            LogUtil.b(TAG, "stopSendFileErrorResult e");
        }
    }

    private boolean checkCancelFileTransferParams(Device device, FileIdentificationParcel fileIdentificationParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pCancelFileTransferCallBack p2pCancelFileTransferCallBack) {
        if (p2pCancelFileTransferCallBack == null) {
            LogUtil.b(TAG, "checkCancelFileTransferParams p2pStopSendFileCallBack is null");
            return false;
        }
        if (fileIdentificationParcel == null || TextUtils.isEmpty(fileIdentificationParcel.getFileName())) {
            LogUtil.b(TAG, "checkCancelFileTransferParams fileIdentificationParcel is null or fileName is empty");
            cancelFileTransferErrorResult(p2pCancelFileTransferCallBack, 5, "Invalid argument");
            return false;
        }
        if (device == null) {
            LogUtil.b(TAG, "checkCancelFileTransferParams device is null");
            cancelFileTransferErrorResult(p2pCancelFileTransferCallBack, 5, "Invalid argument");
            return false;
        }
        if (identityInfo == null || identityInfo2 == null) {
            cancelFileTransferErrorResult(p2pCancelFileTransferCallBack, 5, "Invalid argument");
            LogUtil.b(TAG, "checkCancelFileTransferParams pkgInfo is null");
            return false;
        }
        LogUtil.a(TAG, "checkCancelFileTransferParams srcPkgName is:", identityInfo.getPackageName(), " destPkgName is:", identityInfo2.getPackageName());
        if (trc.b(identityInfo.getPackageName()) && trc.b(identityInfo2.getPackageName())) {
            return true;
        }
        cancelFileTransferErrorResult(p2pCancelFileTransferCallBack, 5, "Invalid argument");
        LogUtil.b(TAG, "checkCancelFileTransferParams device id is null or pkgName is invalid");
        return false;
    }

    private jys getCancelFileInfo(FileIdentificationParcel fileIdentificationParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2, CancelFileTransferCallBackProxy cancelFileTransferCallBackProxy) {
        jys jysVar = new jys();
        jysVar.e(fileIdentificationParcel.getFileName());
        jysVar.h(7);
        jysVar.i(identityInfo.getPackageName());
        jysVar.b(identityInfo2.getPackageName());
        return jysVar;
    }

    private void addSrcFileRecord(String str, String str2, String str3) {
        synchronized (this.mFileTransferRecordLock) {
            Map<String, List<String>> map = this.mFileTransferredRecordMap.get(str);
            if (map == null) {
                LogUtil.h(TAG, "addExistSrcFileRecord srcFileRecordMap is null");
                map = new HashMap<>(16);
            }
            List<String> list = map.get(str2);
            if (list == null) {
                list = new ArrayList<>();
            }
            if (!list.contains(str3)) {
                list.add(str3);
            }
            map.put(str2, list);
            this.mFileTransferredRecordMap.put(str, map);
            LogUtil.a(TAG, "addSrcFileRecord srcPkgName: ", str, " destPkgName: " + str2 + " file size is: ", Integer.valueOf(list.size()));
        }
    }

    private void removeFileRecord(String str, String str2, String str3) {
        synchronized (this.mFileTransferRecordLock) {
            Map<String, List<String>> map = this.mFileTransferredRecordMap.get(str);
            if (map == null) {
                LogUtil.h(TAG, "removeFileRecord srcFileRecordMap is null");
                return;
            }
            List<String> list = map.get(str2);
            if (list == null) {
                LogUtil.h(TAG, "removeFileRecord destFileRecordList is null");
                return;
            }
            LogUtil.a(TAG, "removeFileRecord srcPackageName: ", str, " destPackageName: " + str2 + " file size: ", Integer.valueOf(list.size()));
            for (int size = list.size() - 1; size >= 0; size--) {
                if (str3.equals(list.get(size))) {
                    list.remove(size);
                    LogUtil.a(TAG, "removeFileRecord srcPackageName: ", str, " destPackageName: " + str2 + " file size: ", Integer.valueOf(list.size()));
                    return;
                }
            }
        }
    }

    private boolean checkFileRecordExist(String str, String str2, String str3) {
        synchronized (this.mFileTransferRecordLock) {
            Map<String, List<String>> map = this.mFileTransferredRecordMap.get(str);
            if (map == null) {
                LogUtil.h(TAG, "checkFileRecordExist srcFileRecordMap is null");
                return false;
            }
            List<String> list = map.get(str2);
            if (list != null && !list.isEmpty()) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (str3.equals(list.get(size))) {
                        return true;
                    }
                }
                return false;
            }
            LogUtil.h(TAG, "checkFileRecordExist destFileRecordList is null or isEmpty");
            return false;
        }
    }

    private UniteDevice getDevice(String str) {
        DeviceInfo deviceInfo;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "getDevice deviceId s empty");
            return null;
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, TAG);
        if (deviceList.size() == 0) {
            return null;
        }
        Iterator<DeviceInfo> it = deviceList.iterator();
        while (true) {
            if (!it.hasNext()) {
                deviceInfo = null;
                break;
            }
            deviceInfo = it.next();
            if (str.equals(deviceInfo.getDeviceUdid()) || str.equals(deviceInfo.getUuid())) {
                break;
            }
        }
        if (deviceInfo == null) {
            return null;
        }
        com.huawei.devicesdk.entity.DeviceInfo deviceInfo2 = new com.huawei.devicesdk.entity.DeviceInfo();
        deviceInfo2.setDeviceBtType(deviceInfo.getDeviceBluetoothType());
        deviceInfo2.setDeviceName(deviceInfo.getDeviceName());
        deviceInfo2.setDeviceMac(deviceInfo.getDeviceIdentify());
        deviceInfo2.setDeviceType(deviceInfo.getProductType());
        UniteDevice uniteDevice = new UniteDevice();
        uniteDevice.build(deviceInfo2.getDeviceMac(), deviceInfo2, null);
        return uniteDevice;
    }
}
