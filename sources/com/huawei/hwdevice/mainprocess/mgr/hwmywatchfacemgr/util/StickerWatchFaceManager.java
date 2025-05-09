package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.deviceside.StickerWatchFaceService;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.DiyThrowable;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.StickerWatchFaceInfo;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.TransferProgressResp;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.TransmitWatchFace;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.StickerEventDTO;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback;
import com.huawei.networkclient.ResultCallback;
import com.huawei.profile.utils.file.FileUtils;
import defpackage.cun;
import defpackage.jfq;
import defpackage.jpt;
import defpackage.jqj;
import defpackage.knl;
import defpackage.nrf;
import defpackage.snv;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* loaded from: classes9.dex */
public class StickerWatchFaceManager {
    private static final String BIN_STICKER_IMAGE_DIR = "bin";
    private static final String DES_CERT = "SystemApp";
    private static final String DES_PKGNAME = "hw.watch.watchface.sticker";
    private static final String DIY_STICKER_IMAGE_DIR = "diy";
    private static final int MAX_SIDE_LENGTH = 464;
    private static final String PRESET_STICKER_IMAGE_DIR = "preset";
    private static final String SRC_CERT = "UniteDeviceManagement";
    private static final String SRC_PKGNAME = "hw.unitedevice.watchface";
    private static final int STICKER_TYPE_PRESET = 1;
    private static final String TAG = "StickerWatchFaceManager";
    private static final String TAG_RELEASE = "DEVMGR_StickerWatchFaceManager";
    private final Object mGetStickerWatchFaceInfoLock;
    private ResultCallback<TransmitWatchFace<String>> mH5TransmitWatchFaceCallback;
    private String mStickerWatchFaceImageDir;
    private TransmitWatchFace<String> mTransmitWatchFace;

    private StickerWatchFaceManager() {
        this.mGetStickerWatchFaceInfoLock = new Object();
        try {
            this.mStickerWatchFaceImageDir = BaseApplication.e().getFilesDir().getCanonicalPath() + File.separator + "myWatchFace" + File.separator + "sticker" + File.separator;
        } catch (IOException e) {
            ReleaseLogUtil.d(TAG_RELEASE, "init StickerWatchFaceManager error:", ExceptionUtils.d(e));
        }
    }

    public String getStickerWatchFaceBinFilePath() {
        return WatchFaceUtil.getFileDirPath(getCurrentStickerBaseDir() + BIN_STICKER_IMAGE_DIR + File.separator);
    }

    public String getDIYStickerWatchFacePath() {
        return WatchFaceUtil.getFileDirPath(getCurrentStickerBaseDir() + DIY_STICKER_IMAGE_DIR + File.separator);
    }

    public String getPresetStickerWatchFacePath() {
        return WatchFaceUtil.getFileDirPath(getCurrentStickerBaseDir() + PRESET_STICKER_IMAGE_DIR + File.separator);
    }

    public String getCurrentStickerBaseDir() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, TAG_RELEASE);
        if (deviceInfo == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "getStickerDeviceDirName currentDevice is null");
            return "";
        }
        String d = knl.d(deviceInfo.getDeviceIdentify());
        String replaceAll = d.replaceAll("[^a-zA-Z0-9]", "");
        ReleaseLogUtil.e(TAG_RELEASE, "getStickerDeviceDirName encryptedDeviceId:", d, ", replacedDeviceId:", replaceAll);
        return WatchFaceUtil.getFileDirPath(this.mStickerWatchFaceImageDir + replaceAll + File.separator);
    }

    public void setStickerWatchFaceInfo(String str, final ResultCallback<TransferProgressResp> resultCallback) {
        LogUtil.a(TAG, "setStickerInfo enter");
        DeviceInfo d = jpt.d(TAG);
        if (d == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "setStickerInfo device is null");
            resultCallback.onFailure(new DiyThrowable(-1, "setStickerWatchFaceInfo fail"));
        } else {
            StickerWatchFaceService.getInstance().registerNeedTransferFileListener(buildTransferFileToDeviceFileCallback(resultCallback));
            StickerWatchFaceService.getInstance().setStickerWatchFaceInfo(d, str, new ResultCallback<StickerEventDTO>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.StickerWatchFaceManager.1
                @Override // com.huawei.networkclient.ResultCallback
                public void onSuccess(StickerEventDTO stickerEventDTO) {
                    StickerWatchFaceService.getInstance().unregisterNeedTransferFileListener();
                    if (!Objects.equals(0, stickerEventDTO.getResult())) {
                        ReleaseLogUtil.d(StickerWatchFaceManager.TAG_RELEASE, "setStickerWatchFaceInfo device ui error");
                        resultCallback.onFailure(new DiyThrowable(-1, "setStickerWatchFaceInfo fail"));
                    } else {
                        LogUtil.a(StickerWatchFaceManager.TAG, "setStickerWatchFaceInfo success");
                        TransferProgressResp transferProgressResp = new TransferProgressResp();
                        transferProgressResp.setResultCode(0);
                        resultCallback.onSuccess(transferProgressResp);
                    }
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    StickerWatchFaceService.getInstance().unregisterNeedTransferFileListener();
                    ReleaseLogUtil.d(StickerWatchFaceManager.TAG_RELEASE, "setStickerWatchFaceInfo error:", ExceptionUtils.d(th));
                    resultCallback.onFailure(th);
                }
            });
        }
    }

    public void setStickerWatchFaceInfo(StickerWatchFaceInfo stickerWatchFaceInfo, final ResultCallback<TransferProgressResp> resultCallback) {
        LogUtil.a(TAG, "setStickerInfo enter");
        LogUtil.a(TAG, "setStickerInfo obj:", stickerWatchFaceInfo);
        DeviceInfo d = jpt.d(TAG);
        if (d == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "setStickerInfo device is null");
            resultCallback.onFailure(new DiyThrowable(-1, "setStickerWatchFaceInfo fail"));
        } else {
            StickerWatchFaceService.getInstance().registerNeedTransferFileListener(buildTransferFileToDeviceFileCallback(resultCallback));
            StickerWatchFaceService.getInstance().setStickerWatchFaceInfo(d, stickerWatchFaceInfo, new ResultCallback<StickerEventDTO>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.StickerWatchFaceManager.2
                @Override // com.huawei.networkclient.ResultCallback
                public void onSuccess(StickerEventDTO stickerEventDTO) {
                    StickerWatchFaceService.getInstance().unregisterNeedTransferFileListener();
                    if (!Objects.equals(0, stickerEventDTO.getResult())) {
                        ReleaseLogUtil.d(StickerWatchFaceManager.TAG_RELEASE, "setStickerWatchFaceInfo device ui error");
                        resultCallback.onFailure(new DiyThrowable(-1, "setStickerWatchFaceInfo fail"));
                    } else {
                        LogUtil.a(StickerWatchFaceManager.TAG, "setStickerWatchFaceInfo success");
                        TransferProgressResp transferProgressResp = new TransferProgressResp();
                        transferProgressResp.setResultCode(0);
                        resultCallback.onSuccess(transferProgressResp);
                    }
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    StickerWatchFaceService.getInstance().unregisterNeedTransferFileListener();
                    ReleaseLogUtil.d(StickerWatchFaceManager.TAG_RELEASE, "setStickerWatchFaceInfo error:", ExceptionUtils.d(th));
                    resultCallback.onFailure(th);
                }
            });
        }
    }

    private void createBinImage(String str, String str2) {
        LogUtil.a(TAG, "createBinImage enter");
        if (!FileUtils.fileExists(str)) {
            ReleaseLogUtil.d(TAG_RELEASE, "createBinImage file not exist:", str);
            return;
        }
        Bitmap cHA_ = nrf.cHA_(str);
        if (cHA_ == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "createBinImage stickerImageBitMap image is null");
            return;
        }
        int width = cHA_.getWidth();
        int height = cHA_.getHeight();
        if (width > MAX_SIDE_LENGTH || height > MAX_SIDE_LENGTH) {
            float f = width;
            float f2 = height;
            float max = Math.max(f / 464.0f, f2 / 464.0f);
            int i = (int) (f / max);
            int i2 = (int) (f2 / max);
            LogUtil.a(TAG, "createBinImage after scale:", Integer.valueOf(i), ",", Integer.valueOf(i2));
            nrf.cJs_(Bitmap.createScaledBitmap(cHA_, i, i2, true), str);
        }
        LogUtil.a(TAG, "createBinImage start");
        DiyWatchFaceBitmapUtils.createBinFile(str, str2, 3);
    }

    public void getStickerWatchFaceInfo(String str, ResultCallback<TransmitWatchFace<String>> resultCallback) {
        LogUtil.a(TAG, "getStickerWatchFaceInfo enter");
        DeviceInfo d = jpt.d(TAG);
        if (d == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "getStickerWatchFaceInfo device is null");
            resultCallback.onFailure(new DiyThrowable(-1, "getStickerWatchFaceInfo fail"));
            return;
        }
        if (!Objects.equals(d.getDeviceIdentify(), str)) {
            ReleaseLogUtil.d(TAG_RELEASE, "getStickerWatchFaceInfo device id changed");
            resultCallback.onFailure(new DiyThrowable(-1, "getStickerWatchFaceInfo fail"));
            return;
        }
        synchronized (this.mGetStickerWatchFaceInfoLock) {
            this.mH5TransmitWatchFaceCallback = resultCallback;
            TransmitWatchFace<String> transmitWatchFace = this.mTransmitWatchFace;
            if (transmitWatchFace != null && transmitWatchFace.getResultCode() == 2) {
                this.mH5TransmitWatchFaceCallback.onSuccess(this.mTransmitWatchFace);
            } else {
                StickerWatchFaceService.getInstance().getStickerConfig(d, new ResultCallback<TransmitWatchFace<String>>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.StickerWatchFaceManager.3
                    @Override // com.huawei.networkclient.ResultCallback
                    public void onSuccess(TransmitWatchFace<String> transmitWatchFace2) {
                        List appNotExistFileList = StickerWatchFaceManager.this.getAppNotExistFileList(transmitWatchFace2.getWatchFaceInfo());
                        if (CollectionUtils.d(appNotExistFileList)) {
                            synchronized (StickerWatchFaceManager.this.mGetStickerWatchFaceInfoLock) {
                                StickerWatchFaceManager.this.mTransmitWatchFace = transmitWatchFace2;
                                StickerWatchFaceManager.this.mH5TransmitWatchFaceCallback.onSuccess(transmitWatchFace2);
                            }
                            return;
                        }
                        ReleaseLogUtil.e(StickerWatchFaceManager.TAG_RELEASE, "getStickerWatchFaceInfo request file count:", Integer.valueOf(appNotExistFileList.size()));
                        StickerWatchFaceManager.this.requestDeviceFileList(appNotExistFileList, 0, transmitWatchFace2);
                    }

                    @Override // com.huawei.networkclient.ResultCallback
                    public void onFailure(Throwable th) {
                        ReleaseLogUtil.d(StickerWatchFaceManager.TAG_RELEASE, "getStickerWatchFaceInfo fail");
                        synchronized (StickerWatchFaceManager.this.mGetStickerWatchFaceInfoLock) {
                            StickerWatchFaceManager.this.mTransmitWatchFace = null;
                            StickerWatchFaceManager.this.mH5TransmitWatchFaceCallback.onFailure(th);
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestDeviceFileList(final List<String> list, final int i, final TransmitWatchFace<String> transmitWatchFace) {
        if (list.size() <= i) {
            synchronized (this.mGetStickerWatchFaceInfoLock) {
                this.mTransmitWatchFace = transmitWatchFace;
                this.mH5TransmitWatchFaceCallback.onSuccess(transmitWatchFace);
            }
            return;
        }
        final int size = list.size();
        final String str = list.get(i);
        LogUtil.a(TAG, "requestDeviceFileList name:", str, ", index:", Integer.valueOf(i));
        jqj jqjVar = new jqj();
        jqjVar.a(str);
        jqjVar.d(10);
        jqjVar.a(true);
        jqjVar.c((String) null);
        jqjVar.h("hw.unitedevice.watchface");
        jqjVar.g(SRC_CERT);
        jqjVar.d("hw.watch.watchface.sticker");
        jqjVar.e("SystemApp");
        jfq.c().c(jqjVar, new ITransferSleepAndDFXFileCallback.Stub() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.StickerWatchFaceManager.4
            private int mLastProgress = 0;

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onSuccess(int i2, String str2, String str3) {
                LogUtil.a(StickerWatchFaceManager.TAG, "getWatchFaceBgFileFromDevice onSuccess transferDataContent:", str2);
                StickerWatchFaceManager.this.saveFileFromDevice(str2);
                StickerWatchFaceManager.this.requestDeviceFileList(list, i + 1, transmitWatchFace);
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onFailure(int i2, String str2) {
                ReleaseLogUtil.d(StickerWatchFaceManager.TAG_RELEASE, "requestDeviceFileList onFailure errorCode:", Integer.valueOf(i2));
                StickerWatchFaceManager.this.requestDeviceFileList(list, i + 1, transmitWatchFace);
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onProgress(int i2, String str2) {
                if (i2 == this.mLastProgress) {
                    return;
                }
                if (i2 % 5 == 0) {
                    LogUtil.a(StickerWatchFaceManager.TAG, "transferFileListToDevice onFileTransferState, progress: ", Integer.valueOf(i2), " fileName: ", str);
                }
                this.mLastProgress = i2;
                TransmitWatchFace transmitWatchFace2 = new TransmitWatchFace();
                transmitWatchFace2.setResultCode(2);
                transmitWatchFace2.setCurrentProgress(StickerWatchFaceManager.this.calculateProgress(size, i, i2));
                synchronized (StickerWatchFaceManager.this.mGetStickerWatchFaceInfoLock) {
                    StickerWatchFaceManager.this.mTransmitWatchFace = transmitWatchFace2;
                    StickerWatchFaceManager.this.mH5TransmitWatchFaceCallback.onSuccess(transmitWatchFace2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveFileFromDevice(String str) {
        LogUtil.a(TAG, "saveFileFromDevice ", str);
        String c = CommonUtil.c(str);
        if (TextUtils.isEmpty(c)) {
            ReleaseLogUtil.d(TAG_RELEASE, "saveFileFromDevice() safePath is empty");
            return;
        }
        File file = new File(c);
        String name = file.getName();
        LogUtil.a(TAG, "saveFileFromDevice() fileName :", name, ",length:", Long.valueOf(file.length()));
        if (name.contains(".")) {
            name = name.substring(0, name.lastIndexOf("."));
        }
        snv.b().e(c, getDIYStickerWatchFacePath() + name + ".png");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<String> getAppNotExistFileList(String str) {
        StickerWatchFaceInfo loads = StickerWatchFaceInfo.loads(str);
        ArrayList arrayList = new ArrayList();
        if (loads == null) {
            LogUtil.h(TAG, "getAppNotExistFileList stickerWatchFaceInfo is null");
            return arrayList;
        }
        List<StickerWatchFaceInfo.StickerTPLInfo> stickerTplInfo = loads.getStickerTplInfo();
        ArrayList<String> arrayList2 = new ArrayList();
        for (StickerWatchFaceInfo.StickerTPLInfo stickerTPLInfo : stickerTplInfo) {
            if (stickerTPLInfo.getTplType().intValue() != 1) {
                StickerWatchFaceInfo.CustomResource customResource = stickerTPLInfo.getCustomResource();
                if (customResource == null) {
                    LogUtil.a(TAG, "getAppNotExistFileList customResource is empty");
                } else {
                    String customStickerName = customResource.getCustomStickerName();
                    if (TextUtils.isEmpty(customStickerName)) {
                        LogUtil.a(TAG, "getAppNotExistFileList customStickerName is empty");
                    } else {
                        arrayList2.add(customStickerName);
                    }
                }
            }
        }
        String c = CommonUtil.c(getDIYStickerWatchFacePath());
        if (TextUtils.isEmpty(c)) {
            ReleaseLogUtil.d(TAG_RELEASE, "getAppNotExistFileList safePath is empty");
            return arrayList;
        }
        File file = new File(c);
        String[] list = file.list();
        if (!file.exists() || CollectionUtils.b(list)) {
            return arrayList2;
        }
        ArrayList arrayList3 = new ArrayList(Arrays.asList(list));
        for (String str2 : arrayList2) {
            if (!arrayList3.contains(str2)) {
                arrayList.add(str2);
            }
        }
        return arrayList;
    }

    private ResultCallback<List<StickerEventDTO.FileInfo>> buildTransferFileToDeviceFileCallback(final ResultCallback<TransferProgressResp> resultCallback) {
        return new ResultCallback<List<StickerEventDTO.FileInfo>>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.StickerWatchFaceManager.5
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(List<StickerEventDTO.FileInfo> list) {
                if (CollectionUtils.d(list)) {
                    LogUtil.a(StickerWatchFaceManager.TAG, "buildNeedTransferFileListener file is empty");
                } else {
                    ReleaseLogUtil.e(StickerWatchFaceManager.TAG_RELEASE, "need transfer size:", Integer.valueOf(list.size()));
                    StickerWatchFaceManager.this.transferFileListToDevice(list, resultCallback, 0);
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.h(StickerWatchFaceManager.TAG, "buildNeedTransferFileListener error:", ExceptionUtils.d(th));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void transferFileListToDevice(final List<StickerEventDTO.FileInfo> list, final ResultCallback<TransferProgressResp> resultCallback, final int i) {
        if (i >= list.size()) {
            LogUtil.a(TAG, "transferFileListToDevice all file transfer success");
            return;
        }
        final int size = list.size();
        StickerEventDTO.FileInfo fileInfo = list.get(i);
        final String fileName = fileInfo.getFileName();
        String str = getDIYStickerWatchFacePath() + fileName;
        String str2 = getPresetStickerWatchFacePath() + fileName;
        final String str3 = getStickerWatchFaceBinFilePath() + fileName;
        if (fileInfo.getFileType() == 1) {
            str = str2;
        }
        createBinImage(str, str3);
        LogUtil.a(TAG, "transferFileListToDevice start transfer file:", fileName, " index:", Integer.valueOf(i));
        DeviceInfo d = jpt.d(TAG);
        if (d == null || d.getDeviceConnectState() != 2) {
            ReleaseLogUtil.e(TAG_RELEASE, "transferFileListToDevice device not connected");
            resultCallback.onFailure(new DiyThrowable(10001, "device disconnect"));
        } else {
            StickerHiWearEngineManager.getInstance().sendStickerImageToDevice(str3, new SendCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.StickerWatchFaceManager.6
                private long lastProgress = 0;

                @Override // com.huawei.health.deviceconnect.callback.SendCallback
                public void onSendResult(int i2) {
                    ReleaseLogUtil.e(StickerWatchFaceManager.TAG_RELEASE, "send sticker errorCode: ", Integer.valueOf(i2));
                    File file = new File(str3);
                    if (file.exists()) {
                        if (!file.delete()) {
                            LogUtil.h(StickerWatchFaceManager.TAG, "Failed to delete sticker file: ", file.getName());
                        } else {
                            LogUtil.a(StickerWatchFaceManager.TAG, "Successfully delete sticker file: ", file.getName());
                        }
                    }
                    LogUtil.a(StickerWatchFaceManager.TAG, "transferFileListToDevice at: ", Integer.valueOf(i));
                    StickerWatchFaceManager.this.transferFileListToDevice(list, resultCallback, i + 1);
                }

                @Override // com.huawei.health.deviceconnect.callback.SendCallback
                public void onSendProgress(long j) {
                    if (j == this.lastProgress) {
                        return;
                    }
                    this.lastProgress = j;
                    if (j % 5 == 0) {
                        LogUtil.a(StickerWatchFaceManager.TAG, "transferFileListToDevice onFileTransferState, progress: ", Long.valueOf(j), " fileName: ", fileName);
                    }
                    TransferProgressResp transferProgressResp = new TransferProgressResp();
                    transferProgressResp.setResultCode(2);
                    transferProgressResp.setTotalNum(size);
                    transferProgressResp.setCurrentNum(i + 1);
                    transferProgressResp.setCurrentProgress(StickerWatchFaceManager.this.calculateProgress(size, i, (int) j));
                    resultCallback.onSuccess(transferProgressResp);
                }

                @Override // com.huawei.health.deviceconnect.callback.SendCallback
                public void onFileTransferReport(String str4) {
                    LogUtil.a(StickerWatchFaceManager.TAG, "transferFileListToDevice response file transferWay: ", str4);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int calculateProgress(int i, int i2, int i3) {
        if (i <= 0) {
            return 0;
        }
        double d = 100.0d / i;
        int ceil = (int) Math.ceil((i2 * d) + ((i3 * d) / 100.0d));
        if (ceil > 100) {
            return 100;
        }
        if (ceil < 0) {
            return 0;
        }
        LogUtil.c(TAG, "progress value is normal");
        return ceil;
    }

    public static StickerWatchFaceManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    static class SingletonHolder {
        private static final StickerWatchFaceManager INSTANCE = new StickerWatchFaceManager();

        private SingletonHolder() {
        }
    }
}
