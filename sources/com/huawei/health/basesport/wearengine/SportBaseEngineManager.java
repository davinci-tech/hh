package com.huawei.health.basesport.wearengine;

import android.content.Context;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.manager.EngineLogicBaseManager;
import defpackage.cba;
import defpackage.cbc;
import defpackage.koq;
import defpackage.spn;
import health.compact.a.HEXUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes3.dex */
public abstract class SportBaseEngineManager extends EngineLogicBaseManager {
    private List<DeviceStateListener> mConnectionStatusListeners;
    private Map<Integer, Set<DataReceiver>> mDataReceiverMap;
    private final Object mObserverLock;
    private SendCallback mSendCallback;

    protected abstract String getTag();

    static class b implements SendCallback {

        /* renamed from: a, reason: collision with root package name */
        String f2197a;

        b(String str) {
            this.f2197a = str;
        }

        @Override // com.huawei.health.deviceconnect.callback.SendCallback
        public void onSendResult(int i) {
            LogUtil.a(this.f2197a, "sendCommand errCode:", Integer.valueOf(i));
        }

        @Override // com.huawei.health.deviceconnect.callback.SendCallback
        public void onSendProgress(long j) {
            LogUtil.a(this.f2197a, "sendCommand process:", Long.valueOf(j));
        }

        @Override // com.huawei.health.deviceconnect.callback.SendCallback
        public void onFileTransferReport(String str) {
            LogUtil.a(this.f2197a, "DefaultSendCallback onFileTransferReport transferWay: ", str);
        }
    }

    public SportBaseEngineManager(Context context) {
        super(context);
        this.mObserverLock = new Object();
        this.mSendCallback = new b(getTag());
        this.mDataReceiverMap = new ConcurrentHashMap();
        this.mConnectionStatusListeners = new CopyOnWriteArrayList();
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onReceiveDeviceCommand(DeviceInfo deviceInfo, int i, spn spnVar) {
        ReleaseLogUtil.e(getTag(), "onReceiveDeviceCommand errorCode ", Integer.valueOf(i));
        if (spnVar != null) {
            if (spnVar.d() == 2) {
                ReleaseLogUtil.d(getTag(), "onReceiveDeviceCommand type is file, pls check.");
                return;
            }
            byte[] b2 = spnVar.b();
            if (b2 == null || b2.length <= cba.c()) {
                ReleaseLogUtil.d(getTag(), "data illegal");
                return;
            }
            LogUtil.h(getTag(), "dataInfo", HEXUtils.a(b2));
            cba cbaVar = new cba(Arrays.copyOfRange(b2, 0, cba.c()));
            LogUtil.h(getTag(), "onReceiveDeviceCommand msgHead:", cbaVar.toString());
            dispatchReceiveData(cbaVar, Arrays.copyOfRange(b2, cba.c(), b2.length));
            return;
        }
        LogUtil.b(getTag(), "onReceiveDeviceCommand Message is null");
    }

    public boolean registerCallback(DataReceiver dataReceiver, int i) {
        boolean add;
        if (dataReceiver == null) {
            return false;
        }
        synchronized (this.mObserverLock) {
            if (!this.mDataReceiverMap.containsKey(Integer.valueOf(i))) {
                this.mDataReceiverMap.put(Integer.valueOf(i), new HashSet(10));
            }
            add = this.mDataReceiverMap.get(Integer.valueOf(i)).add(dataReceiver);
        }
        LogUtil.a(getTag(), "mDataReceiverMap size:", Integer.valueOf(this.mDataReceiverMap.size()), " businessType:", Integer.valueOf(i));
        return add;
    }

    public boolean registerDeviceStateListener(DeviceStateListener deviceStateListener) {
        LogUtil.a(getTag(), "registerDeviceStateListener enter");
        if (deviceStateListener == null) {
            ReleaseLogUtil.c(getTag(), "registerDeviceStateListener listener is null");
            return false;
        }
        return this.mConnectionStatusListeners.add(deviceStateListener);
    }

    public boolean unregisterDeviceStateListener(DeviceStateListener deviceStateListener) {
        LogUtil.a(getTag(), "unregisterDeviceStateListener enter");
        if (deviceStateListener == null) {
            ReleaseLogUtil.c(getTag(), "unregisterDeviceStateListener listener is null");
            return false;
        }
        return this.mConnectionStatusListeners.remove(deviceStateListener);
    }

    public boolean unregisterCallback(DataReceiver dataReceiver, int i) {
        if (dataReceiver == null) {
            LogUtil.h(getTag(), "callback == null");
            return false;
        }
        synchronized (this.mObserverLock) {
            if (!this.mDataReceiverMap.containsKey(Integer.valueOf(i))) {
                LogUtil.h(getTag(), "callback not exist");
                return false;
            }
            Set<DataReceiver> set = this.mDataReceiverMap.get(Integer.valueOf(i));
            boolean remove = set != null ? set.remove(dataReceiver) : false;
            LogUtil.a(getTag(), "unregisterCallback businessType:", Integer.valueOf(i));
            return remove;
        }
    }

    protected void dispatchReceiveData(cba cbaVar, byte[] bArr) {
        if (cbaVar == null) {
            LogUtil.b(getTag(), "dispatchReceiveData with null MsgHead, pls check");
            return;
        }
        Set<DataReceiver> set = this.mDataReceiverMap.get(Integer.valueOf(cbaVar.d()));
        if (koq.b(set)) {
            ReleaseLogUtil.d(getTag(), "dispatchReceiveData with the type empty observe , type is ", Integer.valueOf(cbaVar.d()));
            return;
        }
        ReleaseLogUtil.e(getTag(), "dispatchReceiveData , type is ", Integer.valueOf(cbaVar.d()), "observe size is", Integer.valueOf(set.size()));
        for (DataReceiver dataReceiver : set) {
            if (dataReceiver.isMatch(cbaVar.a())) {
                dataReceiver.onDataReceived(cbaVar, bArr.length, bArr);
            }
        }
    }

    public void sendCommandToDevice(spn spnVar) {
        sendComand(spnVar, this.mSendCallback);
    }

    public void sendCommandToDevice(spn spnVar, SendCallback sendCallback) {
        if (sendCallback == null) {
            sendCommandToDevice(spnVar);
        } else {
            sendComand(spnVar, sendCallback);
        }
    }

    public void sendCommandToDevice(final String str, final spn spnVar, final cbc cbcVar, final IBaseResponseCallback iBaseResponseCallback) {
        if (cbcVar != null) {
            registerCallback(cbcVar, cbcVar.a());
        }
        sendCommandToDevice(spnVar, new SendCallback() { // from class: com.huawei.health.basesport.wearengine.SportBaseEngineManager.5
            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendResult(int i) {
                if (i != 500000 && i != 207) {
                    ReleaseLogUtil.c(SportBaseEngineManager.this.getTag(), str, " send failed, resultCode is", Integer.valueOf(i));
                    SportBaseEngineManager.this.releaseMsgResource(spnVar);
                    cbc cbcVar2 = cbcVar;
                    if (cbcVar2 != null) {
                        SportBaseEngineManager.this.unregisterCallback(cbcVar2, cbcVar2.a());
                    }
                    iBaseResponseCallback.d(1, Integer.valueOf(i));
                    return;
                }
                if (i == 207) {
                    ReleaseLogUtil.e(SportBaseEngineManager.this.getTag(), str, " send success, resultCode is", Integer.valueOf(i));
                    if (cbcVar == null) {
                        iBaseResponseCallback.d(0, Integer.valueOf(i));
                    }
                    SportBaseEngineManager.this.releaseMsgResource(spnVar);
                    return;
                }
                ReleaseLogUtil.d(SportBaseEngineManager.this.getTag(), str, " send called success, resultCode is", Integer.valueOf(i));
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendProgress(long j) {
                LogUtil.a(SportBaseEngineManager.this.getTag(), str, " send progress is", Long.valueOf(j));
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onFileTransferReport(String str2) {
                LogUtil.a(SportBaseEngineManager.this.getTag(), "sendCommandToDevice onFileTransferReport transferWay: ", str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseMsgResource(spn spnVar) {
        if (spnVar == null) {
            LogUtil.h(getTag(), " releaseMsgResource failed with null message.");
        } else if (spnVar.d() == 2) {
            FileUtils.i(spnVar.a());
        }
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onDeviceConnectionChange(ConnectState connectState, DeviceInfo deviceInfo) {
        if (connectState == null) {
            ReleaseLogUtil.c(getTag(), " onDeviceConnectionChange error with null connectState.");
            return;
        }
        if (koq.b(this.mConnectionStatusListeners)) {
            ReleaseLogUtil.c(getTag(), " onDeviceConnectionChange mConnectionStatusListeners is empty.");
            return;
        }
        ReleaseLogUtil.e(getTag(), "onDeviceConnectionChange state is:", connectState.name(), " listeners size is:", Integer.valueOf(this.mConnectionStatusListeners.size()));
        int i = AnonymousClass3.f2195a[connectState.ordinal()];
        if (i == 1) {
            Iterator<DeviceStateListener> it = this.mConnectionStatusListeners.iterator();
            while (it.hasNext()) {
                it.next().onConnected();
            }
        } else if (i == 2) {
            Iterator<DeviceStateListener> it2 = this.mConnectionStatusListeners.iterator();
            while (it2.hasNext()) {
                it2.next().onDisconnected();
            }
        } else {
            if (i != 3) {
                return;
            }
            Iterator<DeviceStateListener> it3 = this.mConnectionStatusListeners.iterator();
            while (it3.hasNext()) {
                it3.next().onConnecting();
            }
        }
    }

    /* renamed from: com.huawei.health.basesport.wearengine.SportBaseEngineManager$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2195a;

        static {
            int[] iArr = new int[ConnectState.values().length];
            f2195a = iArr;
            try {
                iArr[ConnectState.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2195a[ConnectState.DISCONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2195a[ConnectState.CONNECTING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onDestroy() {
        super.onDestroy();
        this.mDataReceiverMap.clear();
        this.mConnectionStatusListeners.clear();
    }
}
