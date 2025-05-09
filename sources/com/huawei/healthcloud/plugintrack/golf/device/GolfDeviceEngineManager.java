package com.huawei.healthcloud.plugintrack.golf.device;

import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.maps.offlinedata.health.p2p.OfflineMapWearEngineManager;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.constant.WearEngineModule;
import com.huawei.unitedevice.manager.EngineLogicBaseManager;
import defpackage.cun;
import defpackage.cwi;
import defpackage.snt;
import defpackage.sph;
import defpackage.spn;
import health.compact.a.HEXUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class GolfDeviceEngineManager extends EngineLogicBaseManager {
    private static final int DEFAULT_COURSE_LENGTH = 4;
    private static final int DEFAULT_ERROR_CODE = -1;
    public static final int HARMONY_WATCH_TYPE = 1;
    private static final Object LOCK = new Object();
    public static final int SPORT_WATCH_TYPE = 0;
    private static final String TAG = "Track_GolfDeviceEngineManager";
    private static volatile GolfDeviceEngineManager sInstance;
    private final Object mCourseMapLock;
    private Map<Integer, Set<GolfDataReceiver>> mDataReceiverMap;
    private SendCallback mDefaultSendCallback;
    private Map<Integer, Integer> mMissingCourseNumMap;
    private final Object mObserverLock;

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onDeviceConnectionChange(ConnectState connectState, DeviceInfo deviceInfo) {
    }

    public enum WatchType {
        SPORT_WATCH(0, "com.huawei.health.wear.golf", OfflineMapWearEngineManager.WEAR_FINGERPRINT),
        HARMONY_WATCH(1, "com.huawei.sport.workout", "com.huawei.sport.workout_BO2t/bvVsvJi32wxoQnpxZwLsOp31g7NVRM5bgxjEyD+7UzFWoNcyB8VkMgg0fABbknE4tU+Y34vfAzpSD8TJ5g=");

        private final int index;
        private final String packageName;
        private final String wearFingerPrint;

        WatchType(int i, String str, String str2) {
            this.index = i;
            this.packageName = str;
            this.wearFingerPrint = str2;
        }

        public static WatchType getWatchType(int i) {
            for (WatchType watchType : values()) {
                if (watchType.index == i) {
                    return watchType;
                }
            }
            return null;
        }
    }

    private GolfDeviceEngineManager() {
        super(BaseApplication.getContext());
        this.mObserverLock = new Object();
        this.mCourseMapLock = new Object();
        this.mDefaultSendCallback = new SendCallback() { // from class: com.huawei.healthcloud.plugintrack.golf.device.GolfDeviceEngineManager.1
            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendResult(int i) {
                LogUtil.a(GolfDeviceEngineManager.TAG, "sendCommand errCode:", Integer.valueOf(i));
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendProgress(long j) {
                LogUtil.a(GolfDeviceEngineManager.TAG, "sendCommand process:", Long.valueOf(j));
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onFileTransferReport(String str) {
                LogUtil.a(GolfDeviceEngineManager.TAG, "mDefaultSendCallback onFileTransferReport transferWay: ", str);
            }
        };
        this.mDataReceiverMap = new ConcurrentHashMap();
        LogUtil.a(TAG, "GolfDeviceEngineManager");
    }

    public static GolfDeviceEngineManager getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new GolfDeviceEngineManager();
                }
            }
        }
        return sInstance;
    }

    public void registerAction() {
        LogUtil.a(TAG, "GolfDeviceEngineManager registerAction");
        registerNotificationAction();
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public WearEngineModule getManagerModule() {
        return WearEngineModule.GOLF_MODULE;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearPkgName() {
        WatchType watchType = WatchType.getWatchType(getWatchType());
        if (watchType != null) {
            return watchType.packageName;
        }
        return null;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearFingerprint() {
        WatchType watchType = WatchType.getWatchType(getWatchType());
        if (watchType != null) {
            return watchType.wearFingerPrint;
        }
        return null;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onReceiveDeviceCommand(DeviceInfo deviceInfo, int i, spn spnVar) {
        LogUtil.a(getTag(), "onReceiveDeviceCommand errorCode ", Integer.valueOf(i));
        if (spnVar != null) {
            if (spnVar.d() == 2) {
                ReleaseLogUtil.d(getTag(), "onReceiveDeviceCommand type is file, pls check.");
                return;
            }
            byte[] b = spnVar.b();
            if (b == null || b.length <= GolfMsgHeader.getByteSize()) {
                ReleaseLogUtil.d(getTag(), "data illegal");
                return;
            }
            LogUtil.h(getTag(), "dataInfo", HEXUtils.a(b));
            GolfMsgHeader golfMsgHeader = new GolfMsgHeader(Arrays.copyOfRange(b, 0, GolfMsgHeader.getByteSize()));
            LogUtil.h(getTag(), "onReceiveDeviceCommand msgHead:", golfMsgHeader.toString());
            dispatchReceiveData(golfMsgHeader, Arrays.copyOfRange(b, GolfMsgHeader.getByteSize(), b.length));
            return;
        }
        LogUtil.b(getTag(), "onReceiveDeviceCommand Message is null");
    }

    private void dispatchReceiveData(GolfMsgHeader golfMsgHeader, byte[] bArr) {
        if (golfMsgHeader == null) {
            ReleaseLogUtil.c(getTag(), "dispatchReceiveData with null MsgHead, pls check");
            return;
        }
        Set<GolfDataReceiver> set = this.mDataReceiverMap.get(Integer.valueOf(golfMsgHeader.getBusinessType()));
        if (set == null) {
            LogUtil.a(TAG, "dispatchReceiveData receive request initiated by device");
            GolfDataReceiver createGolfDataReceiver = GolfDataReceiverFactory.createGolfDataReceiver(golfMsgHeader.getBusinessType(), golfMsgHeader.getMessageId());
            if (createGolfDataReceiver != null) {
                LogUtil.a(TAG, "receiver create success! start process data …");
                createGolfDataReceiver.onDataReceived(golfMsgHeader, bArr.length, bArr);
                return;
            } else {
                ReleaseLogUtil.c(TAG, "invalid bussType!");
                return;
            }
        }
        ReleaseLogUtil.e(getTag(), "dispatchReceiveData , type is ", Integer.valueOf(golfMsgHeader.getBusinessType()), "observe size is", Integer.valueOf(set.size()));
        for (GolfDataReceiver golfDataReceiver : set) {
            if (golfDataReceiver.isMatch(golfMsgHeader.getMessageId())) {
                golfDataReceiver.onDataReceived(golfMsgHeader, bArr.length, bArr);
                if (isMessageFinished(golfMsgHeader, bArr)) {
                    unRegisterReceiver(golfDataReceiver, set);
                    return;
                }
                return;
            }
        }
    }

    private boolean isMessageFinished(GolfMsgHeader golfMsgHeader, byte[] bArr) {
        Integer valueOf;
        if (golfMsgHeader.getBusinessType() != GolfHiWearBusinessType.GOLF_LOCAL_COURSE_LIST.getTypeValue()) {
            return true;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        int i = wrap.getInt();
        int length = (bArr.length - 4) / 8;
        if (i == length) {
            return true;
        }
        int messageId = golfMsgHeader.getMessageId();
        synchronized (this.mCourseMapLock) {
            if (this.mMissingCourseNumMap == null) {
                this.mMissingCourseNumMap = new ConcurrentHashMap(10);
            }
            Integer num = this.mMissingCourseNumMap.get(Integer.valueOf(messageId));
            ReleaseLogUtil.e(TAG, "totalCourseNum is ", Integer.valueOf(i), " msgCourseNum is ", Integer.valueOf(length), " needCourseNum is ", num);
            if (num == null) {
                valueOf = Integer.valueOf(i - length);
            } else {
                valueOf = Integer.valueOf(num.intValue() - length);
            }
            if (valueOf.intValue() == 0) {
                this.mMissingCourseNumMap.remove(Integer.valueOf(messageId));
                return true;
            }
            this.mMissingCourseNumMap.put(Integer.valueOf(messageId), valueOf);
            return false;
        }
    }

    public boolean registerReceiver(GolfDataReceiver golfDataReceiver, int i) {
        boolean add;
        if (golfDataReceiver == null) {
            return false;
        }
        if (!this.mDataReceiverMap.containsKey(Integer.valueOf(i))) {
            this.mDataReceiverMap.put(Integer.valueOf(i), new HashSet(10));
        }
        synchronized (this.mObserverLock) {
            add = this.mDataReceiverMap.get(Integer.valueOf(i)).add(golfDataReceiver);
        }
        return add;
    }

    private void unRegisterReceiver(GolfDataReceiver golfDataReceiver, Set<GolfDataReceiver> set) {
        if (golfDataReceiver == null) {
            ReleaseLogUtil.d(getTag(), "callback is null in unRegisterReceiver");
            return;
        }
        synchronized (this.mObserverLock) {
            LogUtil.a(getTag(), "unRegisterReceiver is ", Boolean.valueOf(set.remove(golfDataReceiver)));
        }
    }

    public void sendMsgToDevice(spn spnVar) {
        sendMsgToDevice(spnVar, this.mDefaultSendCallback);
    }

    public void sendMsgToDevice(spn spnVar, SendCallback sendCallback) {
        sendComand(spnVar, sendCallback);
    }

    public void sendMsgToDevice(spn spnVar, GolfDataReceiver golfDataReceiver, int i, SendCallback sendCallback) {
        if (golfDataReceiver != null) {
            registerReceiver(golfDataReceiver, i);
        }
        sendComand(spnVar, sendCallback);
    }

    public void sendCancelCommand(String str, SendCallback sendCallback) {
        snt sntVar = new snt();
        if (str == null) {
            ReleaseLogUtil.c(TAG, "invalid cancel param!");
        }
        String[] split = str.split("/");
        sntVar.e(str);
        sntVar.a(split[split.length - 1]);
        sntVar.i(getWearPkgName());
        sntVar.e(getManagerModule());
        sntVar.f(getWearFingerprint());
        cancelCommand(sph.d(WearEngineModule.GOLF_MODULE.getValue()), sntVar, sendCallback);
    }

    public int getWatchType() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, TAG);
        if (deviceInfo != null) {
            return isVectorMap(deviceInfo) ? 1 : 0;
        }
        ReleaseLogUtil.c(TAG, "getWatchType failed");
        return -1;
    }

    public boolean isVectorMap(DeviceInfo deviceInfo) {
        if (cwi.c(deviceInfo, 159)) {
            return true;
        }
        int productType = deviceInfo.getProductType();
        LogUtil.a(TAG, "getProductType: ", Integer.valueOf(productType));
        return productType == 57 || productType == 78;
    }

    public void sendPingToDevices(PingCallback pingCallback, String str) {
        pingComand(pingCallback, str);
    }

    protected String getTag() {
        return TAG;
    }
}
