package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.health.devicemgr.business.constant.TransferFileReqType;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwdevice.api.KaleidoscopeWatchFaceApi;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.commonservice.WatchFaceWearService;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.deviceside.DiyWatchFaceDeviceService;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.kaleidoscope.basic.KaleidoscopeDraw;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.DiyThrowable;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.KaleidoscopePreview;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.TransferProgressResp;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.TransmitKaleidoscopeWatchFace;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.TransmitWatchFace;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.KaleidoscopeWatchFaceInfo;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.DiyWatchFaceBitmapUtils;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.WatchFaceUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.secure.android.common.util.SafeString;
import defpackage.jls;
import defpackage.snv;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class KaleidoscopeWatchFaceManger extends DiyWatchFace<KaleidoscopeWatchFaceInfo> implements KaleidoscopeWatchFaceApi {
    private static final String BG_NAME_HONEYCOMB = "honeycomb.png";
    private static final String BG_NAME_RADIA = "radia.png";
    private static final String TAG = "KaleidoscopeWatchFaceManger";
    private static final String TAG_RELEASE = "DEVMGR_KaleidoscopeWatchFaceManger";
    private static final int TYPE_HONEYCOMB = 1;
    private static final int TYPE_RADIA = 2;
    private final ArrayList<String> mBackgroundList;
    private DiyWatchFaceDeviceService mDiyWatchFaceDeviceService;
    private List<Integer> mFileTypeMaterialImages;
    private ResultCallback<TransmitWatchFace<KaleidoscopeWatchFaceInfo>> mGetWatchFaceCallback;
    private boolean mIsSyncing;
    private KaleidoscopeWatchFaceInfo mKaleidoscopeWatchFaceInfo;
    private ArrayList<String> mLocalNoContainsMaterialImages;
    private ResultCallback<TransferProgressResp> mSetWatchFaceCallback;
    private final Object mSyncLock;

    private KaleidoscopeWatchFaceManger() {
        super(WatchFaceConstant.ROOT_PATH_KALEIDOSCOPE, true);
        this.mLocalNoContainsMaterialImages = new ArrayList<>(16);
        this.mFileTypeMaterialImages = new ArrayList(16);
        this.mBackgroundList = new ArrayList<>(16);
        this.mIsSyncing = false;
        this.mSyncLock = new Object();
        LogUtil.a(TAG, "create KaleidoscopeWatchFaceManger.");
        this.mDiyWatchFaceDeviceService = DiyWatchFaceDeviceService.getInstance();
    }

    public static KaleidoscopeWatchFaceManger getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override // com.huawei.hwdevice.api.KaleidoscopeWatchFaceApi
    public void getKaleidoscopeWatchFaceInfo(String str, final ResultCallback<TransmitWatchFace<KaleidoscopeWatchFaceInfo>> resultCallback) {
        this.mDiyWatchFaceDeviceService.getKaleidoscopeWatchFaceInfo(new IBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.KaleidoscopeWatchFaceManger.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e(KaleidoscopeWatchFaceManger.TAG_RELEASE, "getKaleidoscopeWatchFaceInfo errorCode：", Integer.valueOf(i));
                if (i == 101) {
                    if (obj instanceof KaleidoscopeWatchFaceInfo) {
                        KaleidoscopeWatchFaceManger.this.handleDeviceWatchFace((KaleidoscopeWatchFaceInfo) obj, resultCallback);
                        return;
                    } else {
                        KaleidoscopeWatchFaceManger.this.resetIsSync();
                        resultCallback.onFailure(new DiyThrowable(-1, "objectData is not KaleidoscopeWatchFaceInfo"));
                        return;
                    }
                }
                KaleidoscopeWatchFaceManger.this.resetIsSync();
                resultCallback.onFailure(new DiyThrowable(-1, "getKaleidoscopeWatchFaceInfo error"));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDeviceWatchFace(KaleidoscopeWatchFaceInfo kaleidoscopeWatchFaceInfo, ResultCallback<TransmitWatchFace<KaleidoscopeWatchFaceInfo>> resultCallback) {
        ArrayList<KaleidoscopeWatchFaceInfo.MaterialImage> materialImageList = kaleidoscopeWatchFaceInfo.getMaterialImageList();
        if (CollectionUtils.d(materialImageList)) {
            ReleaseLogUtil.d(TAG_RELEASE, "handleDeviceWatchFace materialImageList is empty");
            this.mKaleidoscopeWatchFaceInfo = kaleidoscopeWatchFaceInfo;
            TransmitKaleidoscopeWatchFace transmitKaleidoscopeWatchFace = new TransmitKaleidoscopeWatchFace();
            transmitKaleidoscopeWatchFace.setResultCode(0);
            transmitKaleidoscopeWatchFace.setResult(kaleidoscopeWatchFaceInfo);
            resultCallback.onSuccess(transmitKaleidoscopeWatchFace);
            deleteDeviceBgFile(null);
            resetIsSync();
            return;
        }
        synchronized (this.mSyncLock) {
            ReleaseLogUtil.e(TAG_RELEASE, "handleDeviceWatchFace mIsSyncing:", Boolean.valueOf(this.mIsSyncing));
            if (this.mIsSyncing) {
                notifyWatchFaceLoadingProgress();
                return;
            }
            this.mKaleidoscopeWatchFaceInfo = kaleidoscopeWatchFaceInfo;
            this.mLocalNoContainsMaterialImages.clear();
            ArrayList<String> noContainsMaterialImage = getNoContainsMaterialImage(materialImageList);
            this.mLocalNoContainsMaterialImages = noContainsMaterialImage;
            this.mGetWatchFaceCallback = resultCallback;
            if (!CollectionUtils.d(noContainsMaterialImage)) {
                this.mIsSyncing = true;
                ReleaseLogUtil.e(TAG_RELEASE, "handleDeviceWatchFace set mIsSyncing:", true);
                this.mTransferCompleteCount = 0;
                this.mCurrentTransferPercent = 0;
                this.mTransferTotalCount = this.mLocalNoContainsMaterialImages.size();
                getWatchFaceBgFileFromDevice(0, this.mLocalNoContainsMaterialImages.size(), 23, this.mLocalNoContainsMaterialImages.get(0));
            } else {
                notifyWatchFaceReady();
            }
        }
    }

    private ArrayList<String> getNoContainsMaterialImage(List<KaleidoscopeWatchFaceInfo.MaterialImage> list) {
        ArrayList<String> arrayList = new ArrayList<>(16);
        String c = CommonUtil.c(getDeviceBgFilePath());
        if (TextUtils.isEmpty(c)) {
            ReleaseLogUtil.d(TAG_RELEASE, "getNoContainsMaterialImage safePath is empty");
            return arrayList;
        }
        File file = new File(c);
        String[] list2 = file.list();
        if (!file.exists() || CollectionUtils.b(list2)) {
            for (KaleidoscopeWatchFaceInfo.MaterialImage materialImage : list) {
                String materialImageName = materialImage.getMaterialImageName();
                replaceImageNameBinToPng(materialImage, materialImageName);
                arrayList.add(materialImageName);
            }
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList(Arrays.asList(list2));
        ArrayList arrayList3 = new ArrayList(16);
        for (KaleidoscopeWatchFaceInfo.MaterialImage materialImage2 : list) {
            String materialImageName2 = materialImage2.getMaterialImageName();
            if (!arrayList2.contains(replaceImageNameBinToPng(materialImage2, materialImageName2))) {
                arrayList.add(materialImageName2);
            } else {
                arrayList3.add(materialImageName2);
            }
        }
        deleteDeviceBgFile(arrayList3);
        ReleaseLogUtil.d(TAG_RELEASE, "getNoContainsMaterialImage noContainsMaterialImages size:", Integer.valueOf(arrayList.size()), ", containsPhotos:", Integer.valueOf(arrayList3.size()));
        return arrayList;
    }

    private String replaceImageNameBinToPng(KaleidoscopeWatchFaceInfo.MaterialImage materialImage, String str) {
        KaleidoscopeWatchFaceInfo kaleidoscopeWatchFaceInfo = this.mKaleidoscopeWatchFaceInfo;
        if (kaleidoscopeWatchFaceInfo == null || kaleidoscopeWatchFaceInfo.getMaterialImageType() != 2 || !str.contains(WatchFaceConstant.BIN_SUFFIX)) {
            return str;
        }
        String replace = SafeString.replace(str, WatchFaceConstant.BIN_SUFFIX, ".png");
        materialImage.setMaterialImageName(replace);
        return replace;
    }

    @Override // com.huawei.hwdevice.api.KaleidoscopeWatchFaceApi
    public void setKaleidoscopeWatchFaceInfo(String str, ResultCallback<TransferProgressResp> resultCallback) {
        sendWatchFaceInfoToDevice(str, resultCallback);
    }

    @Override // com.huawei.hwdevice.api.KaleidoscopeWatchFaceApi
    public void getKaleidoscopeWatchFacePath(ResponseCallback<String> responseCallback) {
        responseCallback.onResponse(0, getDeviceBgFilePath());
    }

    @Override // com.huawei.hwdevice.api.KaleidoscopeWatchFaceApi
    public void getKaleidoscopeTypeInfo(String str, ResultCallback<List<KaleidoscopePreview>> resultCallback) {
        resultCallback.onSuccess(generateKaleidoscopeInfo(str));
    }

    private List<KaleidoscopePreview> generateKaleidoscopeInfo(String str) {
        ArrayList arrayList = new ArrayList(16);
        String str2 = SafeString.substring(str, 0, str.lastIndexOf(".")) + File.separator;
        generateKaleidoscopeBg(KaleidoscopeDraw.EnumPattern.HONEYCOMB.toString(), str, str2, BG_NAME_HONEYCOMB);
        KaleidoscopePreview kaleidoscopePreview = new KaleidoscopePreview();
        kaleidoscopePreview.setDesPath(str2 + BG_NAME_HONEYCOMB);
        kaleidoscopePreview.setKaleidoscopeType(1);
        arrayList.add(kaleidoscopePreview);
        generateKaleidoscopeBg(KaleidoscopeDraw.EnumPattern.RADIAL.toString(), str, str2, BG_NAME_RADIA);
        KaleidoscopePreview kaleidoscopePreview2 = new KaleidoscopePreview();
        kaleidoscopePreview2.setDesPath(str2 + BG_NAME_RADIA);
        kaleidoscopePreview2.setKaleidoscopeType(2);
        arrayList.add(kaleidoscopePreview2);
        return arrayList;
    }

    private void generateKaleidoscopeBg(String str, String str2, String str3, String str4) {
        if (WatchFaceUtil.getFile(str3 + str4).exists()) {
            ReleaseLogUtil.d(TAG_RELEASE, "generateKaleidoscopeBg() already exist:");
        } else {
            DiyWatchFaceBitmapUtils.saveBitmapToFile(new KaleidoscopeDraw(BaseApplication.e(), str2, str).preDraw(), Bitmap.CompressFormat.PNG, str3, str4);
        }
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.DiyWatchFace
    protected void getNextFileByBt(int i) {
        getWatchFaceBgFileFromDevice(i, this.mLocalNoContainsMaterialImages.size(), 23, this.mLocalNoContainsMaterialImages.get(i));
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.DiyWatchFace
    protected void saveFileFromDevice(String str, int i) {
        String c = CommonUtil.c(str);
        if (TextUtils.isEmpty(c)) {
            ReleaseLogUtil.d(TAG_RELEASE, "saveFileFromDevice() safePath is empty");
            return;
        }
        File file = new File(c);
        String name = file.getName();
        if (name.contains(".")) {
            name = name.substring(0, name.lastIndexOf("."));
        }
        String str2 = getDeviceBgFilePath() + name + ".png";
        KaleidoscopeWatchFaceInfo kaleidoscopeWatchFaceInfo = this.mKaleidoscopeWatchFaceInfo;
        if (kaleidoscopeWatchFaceInfo == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "saveFileFromDevice() mKaleidoscopeWatchFaceInfo is null");
            return;
        }
        if (kaleidoscopeWatchFaceInfo.getMaterialImageType() == 2) {
            snv.b().e(c, str2);
            return;
        }
        LogUtil.a(TAG, "support background type is png, so copy");
        try {
            FileUtils.d(file, new File(str2));
        } catch (IOException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "saveFileFromDevice copy file IOException:", ExceptionUtils.d(e));
        }
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.DiyWatchFace
    protected void notifyWatchFaceReady() {
        resetIsSync();
        if (this.mKaleidoscopeWatchFaceInfo == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "notifyWatchFaceReady KaleidoscopeWatchFaceInfo is null");
            return;
        }
        TransmitKaleidoscopeWatchFace transmitKaleidoscopeWatchFace = new TransmitKaleidoscopeWatchFace();
        transmitKaleidoscopeWatchFace.setResultCode(0);
        transmitKaleidoscopeWatchFace.setResult(this.mKaleidoscopeWatchFaceInfo);
        this.mGetWatchFaceCallback.onSuccess(transmitKaleidoscopeWatchFace);
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.DiyWatchFace
    protected void notifyWatchFaceError(int i, String str) {
        ResultCallback<TransmitWatchFace<KaleidoscopeWatchFaceInfo>> resultCallback = this.mGetWatchFaceCallback;
        if (resultCallback != null) {
            resultCallback.onFailure(new DiyThrowable(i, str));
        }
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.DiyWatchFace
    protected void resetIsSync() {
        this.mIsSyncing = false;
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.DiyWatchFace
    protected void sendWatchFaceInfoToDevice(String str, ResultCallback<TransferProgressResp> resultCallback) {
        LogUtil.a(TAG, "sendWatchFaceInfoToDevice enter");
        WatchFaceWearService.getInstance().refreshMyWatchFaceApplyTime();
        KaleidoscopeWatchFaceInfo kaleidoscopeWatchFaceInfo = (KaleidoscopeWatchFaceInfo) HiJsonUtil.e(str, KaleidoscopeWatchFaceInfo.class);
        ArrayList<KaleidoscopeWatchFaceInfo.MaterialImage> materialImageList = kaleidoscopeWatchFaceInfo.getMaterialImageList();
        ReleaseLogUtil.e(TAG_RELEASE, "sendWatchFaceInfoToDevice kaleidoscopeWatchFaceInfo materialImage size:", Integer.valueOf(materialImageList.size()));
        ArrayList<KaleidoscopeWatchFaceInfo.MaterialImage> arrayList = new ArrayList<>(16);
        ArrayList arrayList2 = new ArrayList(16);
        for (KaleidoscopeWatchFaceInfo.MaterialImage materialImage : materialImageList) {
            if (materialImage.isPreset()) {
                LogUtil.a(TAG, "sendWatchFaceInfoToDevice isPreset");
                arrayList.add(materialImage);
            } else {
                String substring = SafeString.substring(materialImage.getMaterialImageName(), materialImage.getMaterialImageName().lastIndexOf("/") + 1);
                KaleidoscopeWatchFaceInfo kaleidoscopeWatchFaceInfo2 = this.mKaleidoscopeWatchFaceInfo;
                if (kaleidoscopeWatchFaceInfo2 != null && kaleidoscopeWatchFaceInfo2.getMaterialImageType() == 2) {
                    String replace = SafeString.replace(substring, "png", "bin");
                    arrayList2.add(replace);
                    arrayList2.add(substring);
                    substring = replace;
                } else {
                    arrayList2.add(substring);
                }
                materialImage.setMaterialImageName(substring);
                arrayList.add(materialImage);
            }
        }
        this.mBackgroundList.clear();
        this.mBackgroundList.addAll(arrayList2);
        createBinBackground(this.mBackgroundList);
        kaleidoscopeWatchFaceInfo.setMaterialImageList(arrayList);
        registerTransferFileCallback(this.mIAppTransferFileResultAIDLCallback, getFileType(), TransferFileReqType.DEVICE_REGISTRATION);
        this.mSetWatchFaceCallback = resultCallback;
        this.mTransferTotalCount = 0;
        this.mTransferCompleteCount = 0;
        this.mCurrentTransferPercent = 0;
        this.mDiyWatchFaceDeviceService.saveKaleidoscopeWatchFaceToDevice(kaleidoscopeWatchFaceInfo, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.KaleidoscopeWatchFaceManger.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e(KaleidoscopeWatchFaceManger.TAG_RELEASE, "saveKaleidoscopeWatchFaceToDevice errorCode：", Integer.valueOf(i));
                if (i == 101) {
                    KaleidoscopeWatchFaceManger.this.onSaveSuccess();
                    return;
                }
                if (i == 111) {
                    if (obj instanceof Integer) {
                        KaleidoscopeWatchFaceManger.this.onSaveToTransferBackgrounds(((Integer) obj).intValue(), 1);
                        return;
                    }
                    return;
                }
                KaleidoscopeWatchFaceManger.this.onSaveFailed(new DiyThrowable(-1, "saveKaleidoscopeWatchFaceToDevice fail"));
            }
        });
    }

    private void createBinBackground(ArrayList<String> arrayList) {
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next.endsWith(".png")) {
                String str = getDeviceBgFilePath() + next;
                KaleidoscopeWatchFaceInfo kaleidoscopeWatchFaceInfo = this.mKaleidoscopeWatchFaceInfo;
                if (kaleidoscopeWatchFaceInfo != null && kaleidoscopeWatchFaceInfo.getMaterialImageType() == 2) {
                    String replace = SafeString.replace(next, ".png", WatchFaceConstant.BIN_SUFFIX);
                    jls.b(str, getDeviceBgFilePath() + replace, 0);
                    copyWatchFaceToTransferDir(replace);
                } else {
                    copyWatchFaceToTransferDir(next);
                }
            }
        }
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.DiyWatchFace
    protected List<Integer> getFileType() {
        if (!CollectionUtils.d(this.mFileTypeMaterialImages)) {
            return this.mFileTypeMaterialImages;
        }
        this.mFileTypeMaterialImages.add(14);
        return this.mFileTypeMaterialImages;
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.DiyWatchFace
    protected boolean devicesCallbackFileInfoCheckFail(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString(ContentResource.FILE_NAME);
            int optInt = jSONObject.optInt("fileType");
            if (TextUtils.isEmpty(optString)) {
                ReleaseLogUtil.d(TAG_RELEASE, "devicesCallbackFileInfoCheckFail fileName is empty");
                return true;
            }
            if (optInt != 14) {
                ReleaseLogUtil.d(TAG_RELEASE, "devicesCallbackFileInfoCheckFail fileType: ", Integer.valueOf(optInt));
                return true;
            }
            if (CollectionUtils.d(this.mBackgroundList)) {
                ReleaseLogUtil.d(TAG_RELEASE, "devicesCallbackPhotoInfoCheck mBackgroundList is empty");
                return true;
            }
            return !this.mBackgroundList.contains(optString);
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "devicesCallbackFileInfoCheckFailJSONException :", ExceptionUtils.d(e));
            return true;
        }
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.DiyWatchFace
    protected ResultCallback<TransferProgressResp> getTransferCallback() {
        return this.mSetWatchFaceCallback;
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.DiyWatchFace
    protected ResultCallback<TransmitWatchFace<KaleidoscopeWatchFaceInfo>> getTransmitWatchFaceCallback() {
        return this.mGetWatchFaceCallback;
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.DiyWatchFace
    protected void deleteSaveWatchFace() {
        deleteSaveBgFile(this.mBackgroundList);
    }

    /* loaded from: classes9.dex */
    static class SingletonHolder {
        static final KaleidoscopeWatchFaceManger INSTANCE = new KaleidoscopeWatchFaceManger();

        private SingletonHolder() {
        }
    }
}
