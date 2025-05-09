package com.huawei.unitedevice.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BadParcelableException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.unitedevice.callback.ITransferFileCallback;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.constant.WearEngineModule;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.unitedevice.p2p.IdentityInfo;
import com.huawei.unitedevice.p2p.P2pReceiver;
import defpackage.bin;
import defpackage.blc;
import defpackage.bll;
import defpackage.iyv;
import defpackage.snq;
import defpackage.snt;
import defpackage.sol;
import defpackage.spd;
import defpackage.sph;
import defpackage.spn;
import defpackage.spv;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes7.dex */
public abstract class EngineLogicBaseManager {
    private static final String DEVICE_PULL = ".device_pull";
    private static final String UNITE_DEVICE_FINGER_PRINT = "UniteDeviceManagement";
    private static final String UNITE_DEVICE_PACKAGE = "hw.unitedevice.";
    private static final Set<String> whiteWearPkgNameSet = new HashSet();
    private spd engineLogicFileManager;
    private Context mContext;
    private Handler mCpHandler;
    private HandlerThread mDbHandlerThread;
    private final Object LOCK = new Object();
    private String mTag = "EngineLogicBaseManager";
    private UniteDevice mUniteDevice = null;
    private boolean isRegisterReceiverConnectChange = false;
    private P2pReceiver receiver = new P2pReceiver() { // from class: com.huawei.unitedevice.manager.EngineLogicBaseManager.2
        @Override // com.huawei.unitedevice.p2p.P2pReceiver
        public void onReceiveMessage(DeviceInfo deviceInfo, spn spnVar) {
            EngineLogicBaseManager.this.onReceiveDeviceCommand(blc.d(deviceInfo), 530003, spnVar);
        }
    };
    private final BroadcastReceiver mDeviceStatusReceiver = new BroadcastReceiver() { // from class: com.huawei.unitedevice.manager.EngineLogicBaseManager.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Parcelable parcelable;
            ConnectState connectState;
            if (intent == null) {
                LogUtil.a(EngineLogicBaseManager.this.mTag, "mDeviceStatusReceiver onReceive intent is null.");
                return;
            }
            String action = intent.getAction();
            if (TextUtils.isEmpty(action)) {
                LogUtil.a(EngineLogicBaseManager.this.mTag, "mDeviceStatusReceiver onReceive action is null");
                return;
            }
            LogUtil.c(EngineLogicBaseManager.this.mTag, "mDeviceStatusReceiver onReceive action: ", action);
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(action)) {
                try {
                    parcelable = intent.getParcelableExtra("deviceinfo");
                } catch (BadParcelableException unused) {
                    LogUtil.e(EngineLogicBaseManager.this.mTag, "fuzzy test exception, no care this.");
                    parcelable = null;
                }
                com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo = parcelable instanceof com.huawei.health.devicemgr.business.entity.DeviceInfo ? (com.huawei.health.devicemgr.business.entity.DeviceInfo) parcelable : null;
                if (deviceInfo != null) {
                    int deviceConnectState = deviceInfo.getDeviceConnectState();
                    if (deviceConnectState == 1) {
                        connectState = ConnectState.CONNECTING;
                    } else if (deviceConnectState == 2) {
                        snq.c().a(EngineLogicBaseManager.this.mContext);
                        EngineLogicBaseManager.this.registerReceiverToEngine();
                        connectState = ConnectState.CONNECTED;
                    } else {
                        connectState = (deviceConnectState == 3 || deviceConnectState == 4) ? ConnectState.DISCONNECTED : null;
                    }
                    if (connectState != null) {
                        EngineLogicBaseManager.this.onDeviceConnectionChange(connectState, deviceInfo);
                    }
                }
                EngineLogicBaseManager.this.mUniteDevice = null;
            }
        }
    };

    public abstract WearEngineModule getManagerModule();

    public abstract String getWearFingerprint();

    public abstract String getWearPkgName();

    protected abstract void onDeviceConnectionChange(ConnectState connectState, com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo);

    protected abstract void onReceiveDeviceCommand(com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo, int i, spn spnVar);

    public EngineLogicBaseManager(Context context) {
        this.mDbHandlerThread = null;
        this.mContext = context;
        snq.c().a(this.mContext);
        registerReceiverToEngine();
        snq.c().e(getManagerModule(), this);
        registerBroadcastReceiver();
        HandlerThread handlerThread = new HandlerThread(this.mTag);
        this.mDbHandlerThread = handlerThread;
        handlerThread.start();
        this.mCpHandler = new Handler(this.mDbHandlerThread.getLooper());
    }

    protected void sendCommand(UniteDevice uniteDevice, snt sntVar, SendCallback sendCallback) {
        snq.c().p2pSendForWearEngine(this.mContext, uniteDevice, sntVar, sendCallback);
    }

    protected void sendCommand(spn spnVar, SendCallback sendCallback, com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo) {
        snt sntVar = new snt();
        sntVar.e(getManagerModule());
        sntVar.i(getWearPkgName());
        sntVar.f(getWearFingerprint());
        sntVar.e(spnVar);
        sendCommand(sph.e(deviceInfo), sntVar, sendCallback);
    }

    public void sendComand(spn spnVar, SendCallback sendCallback) {
        snt sntVar = new snt();
        WearEngineModule managerModule = getManagerModule();
        sntVar.e(managerModule);
        sntVar.i(getWearPkgName());
        sntVar.f(getWearFingerprint());
        sntVar.e(spnVar);
        UniteDevice uniteDevice = this.mUniteDevice;
        if (uniteDevice == null) {
            uniteDevice = sph.d(managerModule != null ? managerModule.getValue() : null);
            this.mUniteDevice = uniteDevice;
        }
        sendCommand(uniteDevice, sntVar, sendCallback);
    }

    protected void sendComand(spn spnVar, SendCallback sendCallback, String str, int i) {
        if (spnVar == null) {
            LogUtil.a(this.mTag, "sendComand message is null");
            if (sendCallback != null) {
                sendCallback.onSendResult(5);
                return;
            }
            return;
        }
        if (i == 2 && !whiteWearPkgNameSet.contains(str)) {
            LogUtil.a(this.mTag, "sendComand packageName is not in the whitelist or is not urgent message");
            if (sendCallback != null) {
                sendCallback.onSendResult(5);
            }
            throw new IllegalStateException("send urgent message(prior =2), packageName must be added to the whiteWearPkgNameSet");
        }
        if (i == 2) {
            ReleaseLogUtil.e(this.mTag, "sendComand packageName is ", str, ", prior is ", Integer.valueOf(i));
            iyv.c("P2PMessage", "P2PMessage sendCommand urgent message, packageName is " + str);
        }
        sendComand(convertP2pMessage(spnVar, i), sendCallback);
    }

    private spn convertP2pMessage(spn spnVar, int i) {
        spn.b bVar = new spn.b();
        if (spnVar.d() == 1) {
            bVar.c(spnVar.b());
        } else if (spnVar.d() == 2) {
            bVar.a(spnVar.a());
        } else {
            LogUtil.c(this.mTag, "convertP2pMessage message type is other ");
        }
        bVar.e(spnVar.e());
        bVar.d(spnVar.g());
        bVar.c(i);
        return bVar.e();
    }

    protected void cancelCommand(UniteDevice uniteDevice, snt sntVar, final SendCallback sendCallback) {
        sol solVar = new sol();
        solVar.a(sntVar.d());
        solVar.m(7);
        solVar.e(sntVar.c());
        solVar.h(sntVar.i());
        solVar.b(sntVar.j());
        solVar.f(sntVar.m());
        solVar.d(sntVar.h());
        solVar.g(sntVar.g());
        solVar.c(sntVar.e());
        solVar.ejU_(sntVar.ejD_());
        solVar.e(true);
        if (uniteDevice == null) {
            WearEngineModule managerModule = getManagerModule();
            uniteDevice = sph.d(managerModule != null ? managerModule.getValue() : null);
        }
        solVar.d(uniteDevice);
        snq.c().stopTransferFileToWear(solVar, new ITransferFileCallback.Stub() { // from class: com.huawei.unitedevice.manager.EngineLogicBaseManager.1
            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onFailure(int i, String str) throws RemoteException {
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onSuccess(int i, String str, String str2) throws RemoteException {
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onProgress(int i, String str) throws RemoteException {
                sendCallback.onSendProgress(i);
                LogUtil.c(EngineLogicBaseManager.this.mTag, "cancelCommand onSendProgress: ", Integer.valueOf(i));
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onResponse(int i, String str) throws RemoteException {
                sendCallback.onSendResult(i);
                LogUtil.c(EngineLogicBaseManager.this.mTag, "cancelCommand onSendResult: ", Integer.valueOf(i));
            }
        });
    }

    public void pingComand(PingCallback pingCallback, String str) {
        pingCommand(sph.d(str), pingCallback, str);
    }

    protected void pingCommand(UniteDevice uniteDevice, PingCallback pingCallback, String str) {
        snq.c().pingSendForWearEngine(this.mContext, uniteDevice, str, pingCallback, 1);
    }

    protected void pingComand(PingCallback pingCallback, String str, int i) {
        if (i == 2 && !whiteWearPkgNameSet.contains(str)) {
            LogUtil.a(this.mTag, "pingComand packageName is not in the whitelist or is not urgent message");
            if (pingCallback != null) {
                pingCallback.onPingResult(5);
            }
            throw new IllegalStateException("send urgent message(prior =2), packageName must be added to the whiteWearPkgNameSet");
        }
        if (i == 2) {
            ReleaseLogUtil.e(this.mTag, "pingComand packageName is ", str, ", prior is ", Integer.valueOf(i));
            iyv.c("P2PMessage", "P2PMessage PingCommand urgent message, packageName is " + str);
        }
        snq.c().pingSendForWearEngine(this.mContext, sph.d(str), str, pingCallback, i);
    }

    public void onConnectionChange(ConnectState connectState, com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo) {
        LogUtil.c(this.mTag, "onConnectionChange :", connectState);
    }

    protected void registerNotificationAction() {
        Handler handler = this.mCpHandler;
        if (handler == null) {
            LogUtil.a(this.mTag, "registerNotificationAction handler is null");
        } else {
            handler.post(new Runnable() { // from class: com.huawei.unitedevice.manager.EngineLogicBaseManager.5
                @Override // java.lang.Runnable
                public void run() {
                    spv.a(EngineLogicBaseManager.this.getWearPkgName(), EngineLogicBaseManager.this.getAction());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getAction() {
        return getWearPkgName() + DEVICE_PULL;
    }

    protected void unregisterNotificationAction() {
        Handler handler = this.mCpHandler;
        if (handler == null) {
            LogUtil.e(this.mTag, "handler is null");
        } else {
            handler.post(new Runnable() { // from class: com.huawei.unitedevice.manager.EngineLogicBaseManager.3
                @Override // java.lang.Runnable
                public void run() {
                    spv.c(EngineLogicBaseManager.this.getWearPkgName());
                }
            });
        }
    }

    public void onDestroy() {
        if (this.mContext != null && this.receiver != null) {
            snt sntVar = new snt();
            sntVar.e(getManagerModule());
            sntVar.i(getWearPkgName());
            snq.c().unregisterReceiver(this.mContext, this.receiver, sntVar);
        }
        snq.c().e(getManagerModule());
        if (this.mCpHandler != null) {
            this.mCpHandler = null;
        }
        this.mDbHandlerThread.quit();
        spd spdVar = this.engineLogicFileManager;
        if (spdVar != null) {
            spdVar.c();
        }
        synchronized (this.LOCK) {
            try {
                if (this.isRegisterReceiverConnectChange) {
                    BaseApplication.e().unregisterReceiver(this.mDeviceStatusReceiver);
                }
                this.isRegisterReceiverConnectChange = false;
            } catch (IllegalArgumentException e) {
                LogUtil.e(this.mTag, "onDestroy: ", bll.a(e));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerReceiverToEngine() {
        snt sntVar = new snt();
        sntVar.e(getManagerModule());
        sntVar.i(getWearPkgName());
        sntVar.f(getWearFingerprint());
        snq.c().registerReceiver(this.mContext, sntVar, this.receiver, new SendCallback() { // from class: com.huawei.unitedevice.manager.EngineLogicBaseManager.6
            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendProgress(long j) {
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendResult(int i) {
                LogUtil.c(EngineLogicBaseManager.this.mTag, "registerReceiverToEngine errCode: ", Integer.valueOf(i));
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onFileTransferReport(String str) {
                LogUtil.c(EngineLogicBaseManager.this.mTag, "registerReceiverToEngine onFileTransferReport transferWay: ", str);
            }
        });
        IdentityInfo identityInfo = new IdentityInfo(UNITE_DEVICE_PACKAGE + getManagerModule().getValue(), UNITE_DEVICE_FINGER_PRINT);
        if (this.engineLogicFileManager == null) {
            this.engineLogicFileManager = new spd(identityInfo, this.receiver);
        }
    }

    private void registerBroadcastReceiver() {
        LogUtil.c(this.mTag, "enter registerBroadcastReceiver");
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
            BroadcastManagerUtil.bFC_(BaseApplication.e(), this.mDeviceStatusReceiver, intentFilter, bin.d, null);
            this.isRegisterReceiverConnectChange = true;
        } catch (IllegalStateException e) {
            LogUtil.e(this.mTag, "registerBroadcastReceiver: ", bll.a(e));
        }
    }
}
