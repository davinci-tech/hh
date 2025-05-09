package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.deviceside;

import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.StickerWatchFaceInfo;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.TransmitWatchFace;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.StickerEventDTO;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import defpackage.cuk;
import defpackage.cvn;
import defpackage.cvp;
import defpackage.cvq;
import defpackage.cvr;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* loaded from: classes5.dex */
public class StickerWatchFaceService implements DataReceiveCallback {
    private static final long CONFIG_REPORT_STICKER_WATCHFACE_ID = 900500033;
    public static final String STICKER_CONFIG_APP_PACKAGE_NAME = "hw.unitedevice.watchface";
    public static final String STICKER_CONFIG_DEVICE_PACKAGE_NAME = "hw.watch.watchface.sticker";
    private static final int SUCCEED_RESPONSE = 100000;
    private static final String TAG = "StickerWatchFaceService";
    private static final String TAG_RELEASE = "R_StickerWatchFaceService";
    private static final long WATCHFACE_STICKER_EVENT_ID = 800100016;
    private ResultCallback<List<StickerEventDTO.FileInfo>> mNeedTransferFileListener;
    private final List<ResultCallback<TransmitWatchFace<String>>> mStickerConfigListenerList;
    private final List<ResultCallback<StickerEventDTO>> mStickerUIReadyListenerList;

    static class SingletonHolder {
        private static final StickerWatchFaceService INSTANCE = new StickerWatchFaceService();

        private SingletonHolder() {
        }
    }

    private StickerWatchFaceService() {
        this.mStickerConfigListenerList = new ArrayList();
        this.mStickerUIReadyListenerList = new ArrayList();
    }

    public static StickerWatchFaceService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void registerDeviceSampleListener() {
        cuk.b().registerDeviceSampleListener(STICKER_CONFIG_APP_PACKAGE_NAME, this);
    }

    public void registerNeedTransferFileListener(ResultCallback<List<StickerEventDTO.FileInfo>> resultCallback) {
        this.mNeedTransferFileListener = resultCallback;
    }

    public void unregisterNeedTransferFileListener() {
        this.mNeedTransferFileListener = null;
    }

    public void setStickerWatchFaceInfo(DeviceInfo deviceInfo, String str, ResultCallback<StickerEventDTO> resultCallback) {
        LogUtil.a(TAG, "setStickerWatchFaceInfo enter");
        synchronized (this.mStickerUIReadyListenerList) {
            this.mStickerUIReadyListenerList.add(resultCallback);
        }
        LogUtil.a(TAG, "setStickerWatchFaceInfo send");
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName(STICKER_CONFIG_APP_PACKAGE_NAME);
        cvqVar.setWearPkgName(STICKER_CONFIG_DEVICE_PACKAGE_NAME);
        cvqVar.setCommandId(1);
        cvn cvnVar = new cvn();
        cvnVar.e(CONFIG_REPORT_STICKER_WATCHFACE_ID);
        cvnVar.d(1);
        cvnVar.c(bytes);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(cvnVar);
        cvqVar.setConfigInfoList(arrayList);
        cuk.b().sendSampleConfigCommand(deviceInfo, cvqVar, TAG);
    }

    public void setStickerWatchFaceInfo(DeviceInfo deviceInfo, StickerWatchFaceInfo stickerWatchFaceInfo, ResultCallback<StickerEventDTO> resultCallback) {
        LogUtil.a(TAG, "setStickerWatchFaceInfo enter");
        synchronized (this.mStickerUIReadyListenerList) {
            this.mStickerUIReadyListenerList.add(resultCallback);
        }
        String dumps = StickerWatchFaceInfo.dumps(stickerWatchFaceInfo);
        LogUtil.a(TAG, "setStickerWatchFaceInfo json:", dumps);
        byte[] bytes = dumps.getBytes(StandardCharsets.UTF_8);
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName(STICKER_CONFIG_APP_PACKAGE_NAME);
        cvqVar.setWearPkgName(STICKER_CONFIG_DEVICE_PACKAGE_NAME);
        cvqVar.setCommandId(1);
        cvn cvnVar = new cvn();
        cvnVar.e(CONFIG_REPORT_STICKER_WATCHFACE_ID);
        cvnVar.d(1);
        cvnVar.c(bytes);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(cvnVar);
        cvqVar.setConfigInfoList(arrayList);
        cuk.b().sendSampleConfigCommand(deviceInfo, cvqVar, TAG);
    }

    public void getStickerConfig(DeviceInfo deviceInfo, ResultCallback<TransmitWatchFace<String>> resultCallback) {
        LogUtil.a(TAG, "getStickerConfig enter");
        synchronized (this.mStickerConfigListenerList) {
            this.mStickerConfigListenerList.add(resultCallback);
        }
        cvp cvpVar = new cvp();
        cvpVar.a(WATCHFACE_STICKER_EVENT_ID);
        cvpVar.b(0);
        cvpVar.setSrcPkgName(STICKER_CONFIG_APP_PACKAGE_NAME);
        cvpVar.setWearPkgName(STICKER_CONFIG_DEVICE_PACKAGE_NAME);
        StickerEventDTO stickerEventDTO = new StickerEventDTO();
        stickerEventDTO.setRequest(0);
        cvpVar.e(StickerEventDTO.dumps(stickerEventDTO).getBytes(StandardCharsets.UTF_8));
        cuk.b().sendSampleEventCommand(deviceInfo, cvpVar, TAG);
    }

    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        LogUtil.a(TAG, "onDataReceived enter");
        if (i != 100000) {
            ReleaseLogUtil.c(TAG_RELEASE, "onDataReceived error: ", Integer.valueOf(i));
            return;
        }
        if (deviceInfo == null || cvrVar == null) {
            ReleaseLogUtil.c(TAG_RELEASE, "onDataReceived error device or message is empty");
            return;
        }
        String srcPkgName = cvrVar.getSrcPkgName();
        String wearPkgName = cvrVar.getWearPkgName();
        if (!isCheckPkgName(srcPkgName, wearPkgName)) {
            ReleaseLogUtil.d(TAG_RELEASE, "channel onDataReceived pkg error. srcPkgNam: ", srcPkgName, ", wearPkgName:", wearPkgName);
            return;
        }
        LogUtil.a(TAG, "onDataReceived dispatch sample");
        if (cvrVar instanceof cvq) {
            handleSampleConfig(deviceInfo, (cvq) cvrVar);
        } else if (cvrVar instanceof cvp) {
            handleSampleEvent(deviceInfo, (cvp) cvrVar);
        } else {
            ReleaseLogUtil.d(TAG, "onDataReceived unsupported sample type");
        }
    }

    private void handleSampleEvent(DeviceInfo deviceInfo, cvp cvpVar) {
        LogUtil.a("handleSampleEvent enter", new Object[0]);
        if (!Objects.equals(Long.valueOf(cvpVar.e()), Long.valueOf(WATCHFACE_STICKER_EVENT_ID))) {
            ReleaseLogUtil.d(TAG_RELEASE, "handleSampleEvent event id not supported:", Long.valueOf(cvpVar.e()));
            return;
        }
        StickerEventDTO loads = StickerEventDTO.loads(new String(cvpVar.c(), StandardCharsets.UTF_8));
        List<StickerEventDTO.FileInfo> fileInfo = loads.getFileInfo();
        if (fileInfo != null) {
            LogUtil.a(TAG, "handleSampleEvent need transfer file");
            ResultCallback<List<StickerEventDTO.FileInfo>> resultCallback = this.mNeedTransferFileListener;
            if (resultCallback == null) {
                ReleaseLogUtil.d(TAG_RELEASE, "handleSampleEvent transfer listener not set");
                return;
            } else {
                resultCallback.onSuccess(fileInfo);
                this.mNeedTransferFileListener = null;
                return;
            }
        }
        if (loads.getResult() != null) {
            LogUtil.a(TAG, "handleSampleEvent set sticker success");
            synchronized (this.mStickerUIReadyListenerList) {
                if (CollectionUtils.d(this.mStickerUIReadyListenerList)) {
                    ReleaseLogUtil.d(TAG_RELEASE, "handleSampleEvent callback error");
                    return;
                }
                List<ResultCallback<StickerEventDTO>> list = this.mStickerUIReadyListenerList;
                list.get(list.size() - 1).onSuccess(loads);
                List<ResultCallback<StickerEventDTO>> list2 = this.mStickerUIReadyListenerList;
                list2.remove(list2.size() - 1);
                return;
            }
        }
        ReleaseLogUtil.d(TAG_RELEASE, "handleSampleEvent unsupported event data");
    }

    private void handleSampleConfig(DeviceInfo deviceInfo, cvq cvqVar) {
        LogUtil.a(TAG, "handleSampleConfig enter config:", cvqVar);
        if (CollectionUtils.d(cvqVar.getConfigInfoList())) {
            ReleaseLogUtil.d(TAG_RELEASE, "handleSampleConfig config info list is empty");
            return;
        }
        cvn cvnVar = cvqVar.getConfigInfoList().get(0);
        long a2 = cvnVar.a();
        if (!Objects.equals(Long.valueOf(CONFIG_REPORT_STICKER_WATCHFACE_ID), Long.valueOf(a2))) {
            ReleaseLogUtil.d(TAG_RELEASE, "handleSampleConfig bed config id:", Long.valueOf(a2));
            return;
        }
        String str = new String(cvnVar.b(), StandardCharsets.UTF_8);
        LogUtil.a(TAG, "handleSampleConfig sticker json:", str);
        synchronized (this.mStickerConfigListenerList) {
            if (CollectionUtils.d(this.mStickerConfigListenerList)) {
                ReleaseLogUtil.d(TAG_RELEASE, "handleSampleConfig callback error");
                return;
            }
            List<ResultCallback<TransmitWatchFace<String>>> list = this.mStickerConfigListenerList;
            ResultCallback<TransmitWatchFace<String>> resultCallback = list.get(list.size() - 1);
            TransmitWatchFace<String> transmitWatchFace = new TransmitWatchFace<>();
            transmitWatchFace.setWatchFaceInfo(str);
            transmitWatchFace.setResultCode(0);
            resultCallback.onSuccess(transmitWatchFace);
            List<ResultCallback<TransmitWatchFace<String>>> list2 = this.mStickerConfigListenerList;
            list2.remove(list2.size() - 1);
        }
    }

    private boolean isCheckPkgName(String str, String str2) {
        return STICKER_CONFIG_DEVICE_PACKAGE_NAME.equals(str) && STICKER_CONFIG_APP_PACKAGE_NAME.equals(str2);
    }
}
