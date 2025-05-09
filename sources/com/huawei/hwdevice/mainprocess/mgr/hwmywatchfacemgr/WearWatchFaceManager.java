package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr;

import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.constant.TransferFileReqType;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwdevice.api.WearWatchFaceApi;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.commonservice.WatchFaceCloudService;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.commonservice.WatchFaceWearService;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.deviceside.DiyWatchFaceDeviceService;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.DiyThrowable;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.TransferProgressResp;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.TransmitWatchFace;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.WearFilterPreview;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.WearWatchFaceParam;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud.PackageInfoResponse;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud.WatchAbility;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WearWatchFaceInfo;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.DiyWatchFaceBitmapUtils;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.WatchFaceUtil;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.WearWatchFaceGenerator;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ProgressListener;
import com.huawei.networkclient.ResultCallback;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.secure.android.common.util.SafeString;
import defpackage.abo;
import defpackage.cun;
import defpackage.dql;
import defpackage.drd;
import defpackage.dro;
import defpackage.jls;
import defpackage.snv;
import defpackage.sqo;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class WearWatchFaceManager extends DiyWatchFace<WearWatchFaceInfo> implements WearWatchFaceApi {
    private static final char CLOCK_CAPABILITY_SUPPORTED = '1';
    private static final String CLOCK_COLOR_LIST = "clock_color_list";
    private static final String CLOCK_TYPE = "clock_type";
    private static final int DEVICE_NAME_LAST_THREE = 3;
    private static final String TAG = "WearWatchFaceManager";
    private static final String TAG_RELEASE = "DEVMGR_WearWatchFaceManager";
    private static final String WATCHFACE_SQUARE_TABLE_PATH = "resources";
    private static final String WATCH_FACE_CONFIG_ID = "com.huawei.health_watch_face_config";
    private static final String WATCH_FACE_SUFFIX = ".watchface";
    private static final String WEAR_TEMPLATES_FILE_ID = "wearTemplates";
    private DiyWatchFaceDeviceService mDiyWatchFaceDeviceService;
    private List<Integer> mFileTypePhotos;
    private ResultCallback<TransmitWatchFace<WearWatchFaceInfo>> mGetWearWatchFaceCallback;
    protected String mHwtFileDir;
    protected String mHwtOperateDir;
    private volatile boolean mIsProcessWearResources;
    private volatile boolean mIsSyncing;
    private ArrayList<String> mLocalNoContainsPhotos;
    protected String mPackageName;
    protected final String mResourceWearPath;
    private ResultCallback<TransferProgressResp> mSetWearWatchFaceCallback;
    private final Map<Integer, Integer> mSupportedClockTypeMap;
    private final Object mSyncLock;
    private String mWatchFaceName;
    private ArrayList<String> mWearList;
    private WearWatchFaceInfo mWearWatchFaceInfo;
    private WearWatchFaceParam mWearWatchFaceParam;

    private WearWatchFaceManager() {
        super(WatchFaceConstant.WEAR_PREVIEW_ROOT_PATH, true);
        this.mLocalNoContainsPhotos = new ArrayList<>(16);
        this.mFileTypePhotos = new ArrayList(16);
        this.mWearList = new ArrayList<>(16);
        this.mIsSyncing = false;
        this.mSyncLock = new Object();
        this.mIsProcessWearResources = false;
        this.mSupportedClockTypeMap = new HashMap<Integer, Integer>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WearWatchFaceManager.1
            private static final long serialVersionUID = 4970895796906924736L;

            {
                put(0, 1);
                put(1, 2);
                put(2, 3);
                put(3, 4);
                put(4, 7);
                put(5, 8);
                put(6, 9);
                put(7, 10);
                put(8, 11);
            }
        };
        LogUtil.a(TAG, "create WearWatchFaceManager.");
        this.mDiyWatchFaceDeviceService = DiyWatchFaceDeviceService.getInstance();
        this.mResourceWearPath = this.mWatchFaceRootDir + "healthResources" + File.separator;
        this.mHwtFileDir = this.mWatchFaceRootDir + "healthHwt" + File.separator;
        this.mHwtOperateDir = this.mWatchFaceRootDir + "healthParsing" + File.separator;
    }

    @Override // com.huawei.hwdevice.api.WearWatchFaceApi
    public void checkLocalResourceExist(String str, ResultCallback<Boolean> resultCallback) {
        JSONException e;
        String str2;
        String str3 = null;
        try {
            JSONObject jSONObject = new JSONObject(str);
            str2 = jSONObject.getString("watchFaceVersion");
            try {
                str3 = jSONObject.getString("watchFaceId");
            } catch (JSONException e2) {
                e = e2;
                ReleaseLogUtil.c(TAG_RELEASE, "checkLocalResourceExist JSONException:", ExceptionUtils.d(e));
                if (!TextUtils.isEmpty(str3)) {
                }
                ReleaseLogUtil.d(TAG_RELEASE, "checkLocalResourceExist param is error");
                resultCallback.onFailure(new Throwable("param is error"));
                return;
            }
        } catch (JSONException e3) {
            e = e3;
            str2 = null;
        }
        if (!TextUtils.isEmpty(str3) || TextUtils.isEmpty(str2)) {
            ReleaseLogUtil.d(TAG_RELEASE, "checkLocalResourceExist param is error");
            resultCallback.onFailure(new Throwable("param is error"));
            return;
        }
        this.mWatchFaceName = getDeviceNameVersion() + str3 + str2;
        if (isTemplateFilExists() && (isNotNeedCheckCustomTemplate(str2) || isResourceFileExists())) {
            ReleaseLogUtil.e(TAG, "checkLocalResourceExist has exist");
            resultCallback.onSuccess(true);
        } else {
            ReleaseLogUtil.e(TAG, "checkLocalResourceExist not exist");
            resultCallback.onSuccess(false);
        }
    }

    public static WearWatchFaceManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override // com.huawei.hwdevice.api.WearWatchFaceApi
    public void getWearWatchFaceInfo(String str, ResultCallback<TransmitWatchFace<WearWatchFaceInfo>> resultCallback) {
        this.mGetWearWatchFaceCallback = resultCallback;
        WearWatchFaceParam wearWatchFaceParam = (WearWatchFaceParam) HiJsonUtil.e(str, WearWatchFaceParam.class);
        this.mWearWatchFaceParam = wearWatchFaceParam;
        if (wearWatchFaceParam == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "getWearWatchFaceInfo mWearWatchFaceParam is null");
            callbackWearWatchFaceFail(-1, "WearWatchFaceParam is null");
            resetIsSync();
        } else if (this.mIsSyncing) {
            notifyWatchFaceLoadingProgress();
        } else if (this.mIsProcessWearResources) {
            LogUtil.a(TAG, "getWearWatchFaceInfo process wear resources");
        } else {
            this.mIsProcessWearResources = true;
            initWatchFaceSize(new ResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WearWatchFaceManager$$ExternalSyntheticLambda1
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    WearWatchFaceManager.this.m656x60476836(i, (String) obj);
                }
            });
        }
    }

    /* renamed from: lambda$getWearWatchFaceInfo$0$com-huawei-hwdevice-mainprocess-mgr-hwmywatchfacemgr-WearWatchFaceManager, reason: not valid java name */
    /* synthetic */ void m656x60476836(int i, String str) {
        if (isTemplateFilExists()) {
            getResourceAndWatchFaceInfo();
        } else {
            downloadWearTemplates(new ResultCallback<String>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WearWatchFaceManager.2
                @Override // com.huawei.networkclient.ResultCallback
                public void onSuccess(String str2) {
                    WearWatchFaceManager.this.getResourceAndWatchFaceInfo();
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    ReleaseLogUtil.d(WearWatchFaceManager.TAG_RELEASE, "downloadWearTemplates onFailure:", th.getMessage());
                    WearWatchFaceManager.this.callbackWearWatchFaceFail(10005, th.getMessage());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getResourceAndWatchFaceInfo() {
        String watchFaceVersion = this.mWearWatchFaceParam.getWatchFaceVersion();
        if (isNotNeedCheckCustomTemplate(watchFaceVersion)) {
            ReleaseLogUtil.e(TAG_RELEASE, "getResourceAndWatchFaceInfo isNotNeedCheckCustomTemplate");
            getDeviceWatchFace();
            return;
        }
        this.mWatchFaceName = getDeviceNameVersion() + this.mWearWatchFaceParam.getWatchFaceId() + watchFaceVersion;
        if (isResourceFileExists()) {
            ReleaseLogUtil.e(TAG_RELEASE, "getResourceAndWatchFaceInfo resource cache exist");
            getDeviceWatchFace();
        } else {
            downloadHwtResource();
        }
    }

    private void downloadWearTemplates(final ResultCallback<String> resultCallback) {
        drd.e(new dql(WATCH_FACE_CONFIG_ID, new HashMap()), WEAR_TEMPLATES_FILE_ID, 0, new DownloadCallback<File>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WearWatchFaceManager.3
            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onFinish(File file) {
                LogUtil.a(WearWatchFaceManager.TAG, "downloadWearTemplates success");
                String c = CommonUtil.c(file.getPath());
                if (!WatchFaceUtil.createFileDir(WearWatchFaceManager.this.mResourceWearPath)) {
                    ReleaseLogUtil.d(WearWatchFaceManager.TAG_RELEASE, "downloadWearTemplates isCreated false");
                    resultCallback.onFailure(new Throwable("downloadWearTemplates fail"));
                    return;
                }
                int e = dro.e(c, WearWatchFaceManager.this.mResourceWearPath);
                ReleaseLogUtil.e(WearWatchFaceManager.TAG_RELEASE, "unzipWatchFace amount:", Integer.valueOf(e));
                if (e <= 0) {
                    resultCallback.onFailure(new Throwable("unzipResource fail"));
                } else {
                    resultCallback.onSuccess("unzipResource success");
                }
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onProgress(long j, long j2, boolean z, String str) {
                LogUtil.c(WearWatchFaceManager.TAG, "downloadWearTemplates onProgress, already: ", Long.valueOf(j), ", total: ", Long.valueOf(j2), ", fileId: ", str);
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onFail(int i, Throwable th) {
                ReleaseLogUtil.c(WearWatchFaceManager.TAG_RELEASE, "downloadWearTemplates failed :", Integer.valueOf(i));
                sqo.an("downloadWearTemplates failed errCode:" + i);
                resultCallback.onFailure(new Throwable("downloadWearTemplates failed :" + i));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackWearWatchFaceFail(int i, String str) {
        resetIsSync();
        ResultCallback<TransmitWatchFace<WearWatchFaceInfo>> resultCallback = this.mGetWearWatchFaceCallback;
        if (resultCallback != null) {
            resultCallback.onFailure(new DiyThrowable(i, str));
        }
    }

    private void downloadHwtResource() {
        LogUtil.a(TAG, "downloadHwtResource enter");
        String deviceId = this.mWearWatchFaceParam.getDeviceId();
        final String deviceType = this.mWearWatchFaceParam.getDeviceType();
        WatchFaceManager.getInstance().queryWatchAbility(deviceId, new ResponseCallback<List<WatchAbility>>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WearWatchFaceManager.4
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public void onResponse(int i, List<WatchAbility> list) {
                WatchFaceCloudService.getInstance().fetchPackageInfo(WearWatchFaceManager.this.mWearWatchFaceParam.getWatchFaceId(), deviceType, list, new ResultCallback<PackageInfoResponse>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WearWatchFaceManager.4.1
                    @Override // com.huawei.networkclient.ResultCallback
                    public void onSuccess(PackageInfoResponse packageInfoResponse) {
                        PackageInfoResponse.PackageInfo packageInfo = packageInfoResponse.getPackageInfo();
                        ReleaseLogUtil.e(WearWatchFaceManager.TAG_RELEASE, "downloadHwtResource packageInfo:", packageInfo.getWatchFaceId());
                        WearWatchFaceManager.this.startDownloadHwt(WearWatchFaceManager.this.mWearWatchFaceParam.getWatchFaceId(), WearWatchFaceManager.this.mWearWatchFaceParam.getWatchFaceVersion(), packageInfo.getPackageUrl());
                    }

                    @Override // com.huawei.networkclient.ResultCallback
                    public void onFailure(Throwable th) {
                        ReleaseLogUtil.d(WearWatchFaceManager.TAG_RELEASE, "downloadHwtResource onFailure:", CommonUtil.l(th.getMessage()));
                        sqo.an("downloadHwtResource onFailure");
                        WearWatchFaceManager.this.callbackWearWatchFaceFail(10005, CommonUtil.l(th.getMessage()));
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startDownloadHwt(String str, String str2, String str3) {
        WatchFaceCloudService.getInstance().downloadPackage(str3, new File(this.mHwtFileDir + getDeviceNameVersion() + str + str2 + WatchFaceConstant.HWT_SUFFIX), new ProgressListener<File>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WearWatchFaceManager.5
            @Override // com.huawei.networkclient.ProgressListener
            public void onFinish(File file) {
                ReleaseLogUtil.e(WearWatchFaceManager.TAG_RELEASE, "startDownloadHwt data:", Long.valueOf(file.length()));
                WearWatchFaceManager.this.handleHwtResource(file);
            }

            @Override // com.huawei.networkclient.ProgressListener
            public void onProgress(long j, long j2, boolean z) {
                LogUtil.c(WearWatchFaceManager.TAG, "download file totalSize: ", Long.valueOf(j2), ", receiveSize: ", Long.valueOf(j));
            }

            @Override // com.huawei.networkclient.ProgressListener
            public void onFail(Throwable th) {
                ReleaseLogUtil.d(WearWatchFaceManager.TAG_RELEASE, "startDownloadHwt onFail:", CommonUtil.l(th.getMessage()));
                sqo.an("startDownloadHwt onFail");
                WearWatchFaceManager.this.callbackWearWatchFaceFail(10005, CommonUtil.l(th.getMessage()));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleHwtResource(File file) {
        LogUtil.a(TAG, "handleHwtResource() enter.");
        if (unzipWatchFaceHwt(file, this.mHwtOperateDir + this.mWatchFaceName) <= 0) {
            ReleaseLogUtil.d(TAG_RELEASE, "handleHwtResource unzipHwtFile failed");
            callbackWearWatchFaceFail(10005, "handleHwtResource unzipHwtFile failed");
        } else {
            getDeviceWatchFace();
        }
    }

    public boolean isNotNeedCheckCustomTemplate(String str) {
        ReleaseLogUtil.e(TAG_RELEASE, "isNotNeedCheckCustomTemplate watchFaceVersion:", str);
        String[] split = str.split("[.]");
        if (CollectionUtils.b(split) || split.length < 2) {
            ReleaseLogUtil.d(TAG_RELEASE, "isNotNeedCheckCustomTemplate versionCode is not right");
            return false;
        }
        boolean z = CommonUtil.e(split[0], 0) == 2 && CommonUtil.e(split[1], 0) == 2;
        boolean z2 = this.mDeviceWidth == 466 && this.mDeviceHeight == 466;
        ReleaseLogUtil.e(TAG_RELEASE, "isNotNeedCheckCustomTemplate isVersionCodeRight:", Boolean.valueOf(z), ", isDeviceScreenRight:", Boolean.valueOf(z2));
        return z2 && z;
    }

    private boolean isResourceFileExists() {
        File file = new File(this.mHwtOperateDir + this.mWatchFaceName);
        File[] listFiles = file.listFiles();
        if (file.isDirectory() && listFiles != null) {
            int length = listFiles.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                File file2 = listFiles[i];
                if (file2.getName().endsWith(WATCH_FACE_SUFFIX) && file2.isDirectory()) {
                    this.mPackageName = file2.getName();
                    break;
                }
                i++;
            }
        }
        File file3 = new File(CommonUtil.c(getCustomResourcePath()));
        if (!file3.exists()) {
            ReleaseLogUtil.e(TAG_RELEASE, "isResourceFilExists, watchFaceCustomFile not exists");
            return false;
        }
        if (!file3.isDirectory()) {
            return false;
        }
        if (CollectionUtils.b(file3.list(new FilenameFilter() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WearWatchFaceManager$$ExternalSyntheticLambda2
            @Override // java.io.FilenameFilter
            public final boolean accept(File file4, String str) {
                boolean equals;
                equals = WatchFaceConstant.TEMPLATES_CUSTOM_NAME.equals(str);
                return equals;
            }
        }))) {
            ReleaseLogUtil.e(TAG_RELEASE, "need download");
            return false;
        }
        ReleaseLogUtil.e(TAG_RELEASE, "not need download");
        return true;
    }

    private boolean isTemplateFilExists() {
        File file = new File(this.mResourceWearPath + WatchFaceConstant.RESOURCES_ASSETS + File.separator);
        if (!file.exists() || !file.isDirectory() || CollectionUtils.b(file.list(new FilenameFilter() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WearWatchFaceManager$$ExternalSyntheticLambda0
            @Override // java.io.FilenameFilter
            public final boolean accept(File file2, String str) {
                boolean equals;
                equals = WatchFaceConstant.TEMPLATES_CIRCLE_NAME.equals(str);
                return equals;
            }
        }))) {
            return false;
        }
        ReleaseLogUtil.e(TAG_RELEASE, "isTemplateFilExists template has exists");
        return true;
    }

    private int unzipWatchFaceHwt(File file, String str) {
        boolean createFileDir = WatchFaceUtil.createFileDir(str);
        File file2 = new File(file.getPath().replace(WatchFaceConstant.HWT_SUFFIX, ".zip"));
        if (file2.exists()) {
            ReleaseLogUtil.e(TAG_RELEASE, "unzipWatchFaceHwt watchFaceZip isDelete :", Boolean.valueOf(FileUtils.d(file2)));
        }
        boolean renameTo = file.renameTo(file2);
        ReleaseLogUtil.e(TAG_RELEASE, "unzipWatchFaceHwt isCreated:", Boolean.valueOf(createFileDir), ", isRenamed:", Boolean.valueOf(renameTo));
        if (!createFileDir || !renameTo) {
            return -1;
        }
        File file3 = new File(str);
        File[] listFiles = file3.listFiles();
        if (!file3.exists() || !file3.isDirectory() || CollectionUtils.b(listFiles)) {
            int e = dro.e(file2.getPath(), str);
            if (e < 0) {
                ReleaseLogUtil.d(TAG_RELEASE, "unzipWatchFaceHwt failed");
                return e;
            }
            ReleaseLogUtil.e(TAG_RELEASE, "unzipWatchFaceHwt unzip success isDelete:", Boolean.valueOf(FileUtils.d(file2)));
            findWatchFacePackage(str);
            return unzipWatchFace(str, this.mPackageName);
        }
        return listFiles.length;
    }

    private void findWatchFacePackage(String str) {
        File file = new File(str);
        File[] listFiles = file.listFiles();
        if (!file.isDirectory() || listFiles == null) {
            return;
        }
        for (File file2 : listFiles) {
            if (file2.getName().endsWith(WATCH_FACE_SUFFIX) && file2.isFile()) {
                this.mPackageName = file2.getName();
                return;
            }
        }
    }

    private int unzipWatchFace(String str, String str2) {
        File watchFaceBinFile = getWatchFaceBinFile(str, str2);
        if (watchFaceBinFile == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "unzipWatchFace watchFacBinFile is null");
            return -1;
        }
        try {
            String canonicalPath = new File(str, str2).getCanonicalPath();
            String str3 = canonicalPath + ".zip";
            File file = new File(str3);
            if (file.exists()) {
                ReleaseLogUtil.e(TAG_RELEASE, "unzipWatchFace isDelete :", Boolean.valueOf(FileUtils.d(file)));
            }
            if (!watchFaceBinFile.renameTo(file)) {
                ReleaseLogUtil.d(TAG_RELEASE, "unzipWatchFace isRenamedWatchFace false");
                return -1;
            }
            if (!WatchFaceUtil.createFileDir(canonicalPath)) {
                ReleaseLogUtil.d(TAG_RELEASE, "unzipWatchFace isCreated false");
                return -1;
            }
            int e = dro.e(str3, canonicalPath);
            if (e >= 0) {
                ReleaseLogUtil.e(TAG_RELEASE, "unzipWatchFace watchFaceZip isDelete:", Boolean.valueOf(FileUtils.d(file)));
            }
            return e;
        } catch (IOException e2) {
            ReleaseLogUtil.c(TAG_RELEASE, "unzipWatchFace IOException:", ExceptionUtils.d(e2));
            return -1;
        } catch (Exception e3) {
            ReleaseLogUtil.c(TAG_RELEASE, "unzipWatchFace Exception:", ExceptionUtils.d(e3));
            return -1;
        }
    }

    private File getWatchFaceBinFile(String str, final String str2) {
        File file = new File(str);
        if (!file.exists() && !file.mkdir()) {
            ReleaseLogUtil.d(TAG_RELEASE, "getWatchFaceBinFile mkdir failed");
            return null;
        }
        File[] listFiles = file.listFiles(new FilenameFilter() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WearWatchFaceManager.6
            @Override // java.io.FilenameFilter
            public boolean accept(File file2, String str3) {
                return str3.startsWith(str2);
            }
        });
        if (CollectionUtils.b(listFiles)) {
            ReleaseLogUtil.d(TAG_RELEASE, "getWatchFaceBinFile watchFaceList is empty");
            return null;
        }
        for (File file2 : listFiles) {
            if (file2.isFile()) {
                return file2;
            }
        }
        return null;
    }

    private String getDeviceNameVersion() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, TAG_RELEASE);
        if (deviceInfo == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "openMyWatchFace currentDevice is null");
            return "";
        }
        String deviceName = deviceInfo.getDeviceName();
        String softVersion = deviceInfo.getSoftVersion();
        if (TextUtils.isEmpty(deviceName) || TextUtils.isEmpty(softVersion)) {
            ReleaseLogUtil.d(TAG_RELEASE, "getDeviceNameVersion() deviceName or deviceVersion is null");
            return "";
        }
        if (deviceName.length() > 3) {
            deviceName = SafeString.substring(deviceName, deviceName.length() - 3);
        }
        if (softVersion.contains(".")) {
            softVersion = SafeString.substring(softVersion, softVersion.lastIndexOf(".") + 1);
        }
        LogUtil.a(TAG, "getDeviceNameVersion() deviceName: ", deviceName, ", deviceVersion: ", softVersion);
        return deviceName + softVersion;
    }

    private void getDeviceWatchFace() {
        this.mDiyWatchFaceDeviceService.getWearWatchFaceInfo(new IBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WearWatchFaceManager.7
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e(WearWatchFaceManager.TAG_RELEASE, "getWearWatchFaceInfo errorCode：", Integer.valueOf(i));
                if (i != 101) {
                    WearWatchFaceManager.this.callbackWearWatchFaceFail(-1, "getWearWatchFaceInfo error");
                } else if (obj instanceof WearWatchFaceInfo) {
                    WearWatchFaceManager.this.handleDeviceWatchFace((WearWatchFaceInfo) obj);
                } else {
                    WearWatchFaceManager.this.callbackWearWatchFaceFail(-1, "objectData is not WearWatchFaceInfo");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDeviceWatchFace(WearWatchFaceInfo wearWatchFaceInfo) {
        long clockTypeCapability = wearWatchFaceInfo.getClockTypeCapability();
        if (clockTypeCapability >= 0) {
            this.mWearWatchFaceInfo = wearWatchFaceInfo;
            setSupportedClocks(getSupportedClockCapability(Long.toBinaryString(clockTypeCapability)));
        } else {
            ReleaseLogUtil.d(TAG_RELEASE, "handleDeviceWatchFace clockTypeCapability is not supported.");
        }
        if (CollectionUtils.d(wearWatchFaceInfo.getWearStyleList())) {
            ReleaseLogUtil.d(TAG_RELEASE, "handleDeviceWatchFace wearStyleList is empty");
            this.mWearWatchFaceInfo = wearWatchFaceInfo;
            TransmitWatchFace<WearWatchFaceInfo> transmitWatchFace = new TransmitWatchFace<>();
            transmitWatchFace.setResultCode(0);
            transmitWatchFace.setWatchFaceInfo(wearWatchFaceInfo);
            ResultCallback<TransmitWatchFace<WearWatchFaceInfo>> resultCallback = this.mGetWearWatchFaceCallback;
            if (resultCallback != null) {
                resultCallback.onSuccess(transmitWatchFace);
            }
            deleteDeviceBgFile(null);
            resetIsSync();
            return;
        }
        synchronized (this.mSyncLock) {
            ReleaseLogUtil.d(TAG_RELEASE, "handleDeviceWatchFace mIsSyncing:", Boolean.valueOf(this.mIsSyncing));
            if (this.mIsSyncing) {
                notifyWatchFaceLoadingProgress();
                return;
            }
            this.mWearWatchFaceInfo = wearWatchFaceInfo;
            ArrayList<WearWatchFaceInfo.WearStyleStruct> unDefaultWearStructs = getUnDefaultWearStructs(transformWearList(wearWatchFaceInfo.getWearStyleList()));
            this.mLocalNoContainsPhotos.clear();
            ArrayList<String> noContainsWear = getNoContainsWear(unDefaultWearStructs);
            this.mLocalNoContainsPhotos = noContainsWear;
            if (!CollectionUtils.d(noContainsWear)) {
                this.mIsSyncing = true;
                ReleaseLogUtil.e(TAG_RELEASE, "handleDeviceWatchFace set mIsSyncing:", Boolean.valueOf(this.mIsSyncing));
                this.mTransferCompleteCount = 0;
                this.mCurrentTransferPercent = 0;
                this.mTransferTotalCount = this.mLocalNoContainsPhotos.size();
                getWatchFaceBgFileFromDevice(0, this.mLocalNoContainsPhotos.size(), 25, this.mLocalNoContainsPhotos.get(0));
            } else {
                notifyWatchFaceReady();
            }
        }
    }

    private ArrayList<WearWatchFaceInfo.WearStyleStruct> getUnDefaultWearStructs(List<WearWatchFaceInfo.WearStyleStruct> list) {
        ArrayList<WearWatchFaceParam.PresetWatchFace> preWearWatchFaceInfo = this.mWearWatchFaceParam.getPreWearWatchFaceInfo();
        ArrayList<WearWatchFaceInfo.WearStyleStruct> arrayList = new ArrayList<>(16);
        arrayList.addAll(list);
        if (CollectionUtils.d(preWearWatchFaceInfo)) {
            ReleaseLogUtil.d(TAG_RELEASE, "getUnDefaultWearStructs() presetWatchFaces is empty");
            return arrayList;
        }
        for (WearWatchFaceInfo.WearStyleStruct wearStyleStruct : list) {
            Iterator<WearWatchFaceParam.PresetWatchFace> it = preWearWatchFaceInfo.iterator();
            while (it.hasNext()) {
                WearWatchFaceParam.PresetWatchFace next = it.next();
                if (SafeString.substring(next.getResPreview(), next.getResPreview().lastIndexOf("/") + 1).contains(wearStyleStruct.getPreviewName())) {
                    arrayList.remove(wearStyleStruct);
                }
            }
        }
        LogUtil.a(TAG, "getUnDefaultWearStructs() unDefaultWearStructs.", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    private List<WearWatchFaceInfo.WearStyleStruct> transformWearList(List<WearWatchFaceInfo.WearStyleStruct> list) {
        for (WearWatchFaceInfo.WearStyleStruct wearStyleStruct : list) {
            if (wearStyleStruct.getPreviewName() != null) {
                wearStyleStruct.setPreviewName(SafeString.replace(wearStyleStruct.getPreviewName(), WatchFaceConstant.BIN_SUFFIX, ".png"));
            }
        }
        return list;
    }

    private ArrayList<Integer> getSupportedClockCapability(String str) {
        Integer num;
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (str.length() == 0) {
            ReleaseLogUtil.e(TAG_RELEASE, "getSupportedClockCapability() supportCapability is empty.");
            return arrayList;
        }
        try {
            String sb = new StringBuilder(str).reverse().toString();
            for (int i = 0; i < sb.length(); i++) {
                if (sb.charAt(i) == '1' && (num = this.mSupportedClockTypeMap.get(Integer.valueOf(i))) != null) {
                    arrayList.add(num);
                }
            }
        } catch (Exception e) {
            ReleaseLogUtil.c(TAG_RELEASE, "getSupportedClockCapability()  Exception:", ExceptionUtils.d(e));
        }
        return arrayList;
    }

    private ArrayList<String> getNoContainsWear(List<WearWatchFaceInfo.WearStyleStruct> list) {
        ArrayList<String> arrayList = new ArrayList<>(16);
        String c = CommonUtil.c(getDeviceBgFilePath());
        if (TextUtils.isEmpty(c)) {
            ReleaseLogUtil.d(TAG_RELEASE, "getNoContainsWear safePath is empty");
            return arrayList;
        }
        File file = new File(c);
        String[] list2 = file.list();
        if (!file.exists() || CollectionUtils.b(list2)) {
            Iterator<WearWatchFaceInfo.WearStyleStruct> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(SafeString.replace(it.next().getPreviewName(), ".png", WatchFaceConstant.BIN_SUFFIX));
            }
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList(Arrays.asList(list2));
        ArrayList arrayList3 = new ArrayList(16);
        Iterator<WearWatchFaceInfo.WearStyleStruct> it2 = list.iterator();
        while (it2.hasNext()) {
            String previewName = it2.next().getPreviewName();
            if (!arrayList2.contains(previewName)) {
                arrayList.add(SafeString.replace(previewName, ".png", WatchFaceConstant.BIN_SUFFIX));
            } else {
                arrayList3.add(previewName);
            }
        }
        deleteDeviceBgFile(arrayList3);
        ReleaseLogUtil.d(TAG_RELEASE, "getNoContainsWear noContainsWears size:", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    @Override // com.huawei.hwdevice.api.WearWatchFaceApi
    public void setWearWatchFaceInfo(String str, ResultCallback<TransferProgressResp> resultCallback) {
        ReleaseLogUtil.e(TAG_RELEASE, "setWearWatchFaceInfo enter");
        sendWatchFaceInfoToDevice(str, resultCallback);
    }

    @Override // com.huawei.hwdevice.api.WearWatchFaceApi
    public void getWearWatchFacePath(int i, ResponseCallback<String> responseCallback) {
        String deviceBgFilePath;
        if (i == 1) {
            deviceBgFilePath = getDeviceBgFilePath();
        } else if (i == 2) {
            deviceBgFilePath = getClipPhotoFilePath();
        } else {
            LogUtil.a(TAG, "getWearWatchFacePath type:", Integer.valueOf(i));
            deviceBgFilePath = "";
        }
        responseCallback.onResponse(0, deviceBgFilePath);
    }

    @Override // com.huawei.hwdevice.api.WearWatchFaceApi
    public void createWearWatchFace(String str, ResultCallback<List<WearFilterPreview>> resultCallback) {
        WearWatchFaceGenerator.createWearWatchFace(str, resultCallback);
    }

    @Override // com.huawei.hwdevice.api.WearWatchFaceApi
    public void deleteRedundantWearWatchFace(String str, ResultCallback<String> resultCallback) {
        List list = (List) HiJsonUtil.b(str, new TypeToken<List<WearFilterPreview>>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WearWatchFaceManager.8
        }.getType());
        if (CollectionUtils.a(str)) {
            ReleaseLogUtil.d(TAG_RELEASE, "deleteRedundantWearWatchFace no need delete");
            resultCallback.onFailure(new Throwable("no need delete"));
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            final String replace = SafeString.replace(SafeString.replace(((WearFilterPreview) it.next()).getPreviewName(), File.separator, ""), "_preview.png", "");
            FileUtils.d(WatchFaceUtil.getFile(getWatchFaceRootDir()), new FilenameFilter() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WearWatchFaceManager.9
                @Override // java.io.FilenameFilter
                public boolean accept(File file, String str2) {
                    return str2.startsWith(replace);
                }
            });
        }
        resultCallback.onSuccess("delete success");
    }

    private void setSupportedClocks(ArrayList<Integer> arrayList) {
        abo.b(arrayList);
        abo.c(this.mDeviceWidth, this.mDeviceHeight);
        WearWatchFaceInfo wearWatchFaceInfo = this.mWearWatchFaceInfo;
        if (wearWatchFaceInfo != null) {
            LogUtil.a(TAG, "setSupportedClocks watchFaceWearInfo.getTintClockOption :", Integer.valueOf(wearWatchFaceInfo.getTintClockOption()));
            abo.b(this.mWearWatchFaceInfo.getTintClockOption() != 0);
        } else {
            LogUtil.a(TAG, "setSupportedClocks watchFaceWearInfo is null");
            abo.b(true);
        }
    }

    public String getWatchFaceRootDir() {
        return getDeviceBgFilePath();
    }

    public String getWatchFaceResourcePath() {
        return this.mResourceWearPath;
    }

    public String getCustomResourcePath() {
        return this.mHwtOperateDir + this.mWatchFaceName + File.separator + this.mPackageName + File.separator + WATCHFACE_SQUARE_TABLE_PATH + File.separator;
    }

    public WearWatchFaceParam getWearWatchFaceParam() {
        return this.mWearWatchFaceParam;
    }

    private String getClipPhotoFilePath() {
        return WatchFaceUtil.getFileDirPath(getDeviceBgFilePath() + "clipBg" + File.separator);
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.DiyWatchFace
    protected void getNextFileByBt(int i) {
        getWatchFaceBgFileFromDevice(i, this.mLocalNoContainsPhotos.size(), 25, this.mLocalNoContainsPhotos.get(i));
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
        WearWatchFaceInfo wearWatchFaceInfo = this.mWearWatchFaceInfo;
        if (wearWatchFaceInfo == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "saveFileFromDevice() mWearWatchFaceInfo is null");
            return;
        }
        if (wearWatchFaceInfo.getWearStyleType() == 2) {
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
        ReleaseLogUtil.e(TAG_RELEASE, "notifyWatchFaceReady mWearWatchFaceInfo :", this.mWearWatchFaceInfo);
        resetIsSync();
        if (this.mWearWatchFaceInfo == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "notifyWatchFaceReady mWearWatchFaceInfo is null");
            return;
        }
        TransmitWatchFace<WearWatchFaceInfo> transmitWatchFace = new TransmitWatchFace<>();
        transmitWatchFace.setResultCode(0);
        transmitWatchFace.setWatchFaceInfo(this.mWearWatchFaceInfo);
        this.mGetWearWatchFaceCallback.onSuccess(transmitWatchFace);
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.DiyWatchFace
    protected void notifyWatchFaceError(int i, String str) {
        ResultCallback<TransmitWatchFace<WearWatchFaceInfo>> resultCallback = this.mGetWearWatchFaceCallback;
        if (resultCallback != null) {
            resultCallback.onFailure(new DiyThrowable(i, str));
        }
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.DiyWatchFace
    protected void resetIsSync() {
        this.mIsSyncing = false;
        this.mIsProcessWearResources = false;
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.DiyWatchFace
    protected void sendWatchFaceInfoToDevice(final String str, final ResultCallback<TransferProgressResp> resultCallback) {
        WatchFaceWearService.getInstance().refreshMyWatchFaceApplyTime();
        ThreadPoolManager.d().d(TAG, new Runnable() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WearWatchFaceManager.10
            @Override // java.lang.Runnable
            public void run() {
                ReleaseLogUtil.e(WearWatchFaceManager.TAG_RELEASE, "sendWatchFaceInfoToDevice enter");
                WearWatchFaceInfo sendDeviceWearInfo = WearWatchFaceManager.this.sendDeviceWearInfo((WearWatchFaceInfo) HiJsonUtil.e(str, WearWatchFaceInfo.class));
                LogUtil.a(WearWatchFaceManager.TAG, "sendWatchFaceInfoToDevice mWearList:", Arrays.toString(WearWatchFaceManager.this.mWearList.toArray()));
                WearWatchFaceManager wearWatchFaceManager = WearWatchFaceManager.this;
                wearWatchFaceManager.createRoundRectPngRes(wearWatchFaceManager.mWearList);
                WearWatchFaceManager wearWatchFaceManager2 = WearWatchFaceManager.this;
                wearWatchFaceManager2.createBinBackground(wearWatchFaceManager2.mWearList);
                WearWatchFaceManager wearWatchFaceManager3 = WearWatchFaceManager.this;
                wearWatchFaceManager3.registerTransferFileCallback(wearWatchFaceManager3.mIAppTransferFileResultAIDLCallback, WearWatchFaceManager.this.getFileType(), TransferFileReqType.DEVICE_REGISTRATION);
                WearWatchFaceManager.this.mSetWearWatchFaceCallback = resultCallback;
                WearWatchFaceManager.this.mTransferTotalCount = 0;
                WearWatchFaceManager.this.mTransferCompleteCount = 0;
                WearWatchFaceManager.this.mCurrentTransferPercent = 0;
                WearWatchFaceManager.this.mDiyWatchFaceDeviceService.saveWearWatchFaceToDevice(sendDeviceWearInfo, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WearWatchFaceManager.10.1
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        ReleaseLogUtil.e(WearWatchFaceManager.TAG_RELEASE, "saveWearWatchFaceToDevice errorCode：", Integer.valueOf(i));
                        if (i == 101) {
                            WearWatchFaceManager.this.onSaveSuccess();
                            return;
                        }
                        if (i == 111) {
                            if (obj instanceof Integer) {
                                WearWatchFaceManager.this.onSaveToTransferBackgrounds(((Integer) obj).intValue(), 2);
                                return;
                            }
                            return;
                        }
                        WearWatchFaceManager.this.onSaveFailed(new DiyThrowable(-1, "saveWearWatchFaceToDevice fail"));
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createRoundRectPngRes(ArrayList<String> arrayList) {
        WearWatchFaceInfo wearWatchFaceInfo = this.mWearWatchFaceInfo;
        int rectRadius = wearWatchFaceInfo != null ? wearWatchFaceInfo.getRectRadius() : 0;
        LogUtil.a(TAG, "createRoundRectPngRes radius:", Integer.valueOf(rectRadius));
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next.contains(WatchFaceConstant.PREVIEW_RES) && next.endsWith(".png")) {
                String str = getDeviceBgFilePath() + next;
                String replace = SafeString.replace(str, WatchFaceConstant.PREVIEW_RES, WatchFaceConstant.WALLPAPER_RES);
                try {
                    DiyWatchFaceBitmapUtils.cropPngToRectF(str, this.mDeviceWidth, this.mDeviceHeight, rectRadius);
                    DiyWatchFaceBitmapUtils.cropPngToRectF(replace, this.mDeviceWidth, this.mDeviceHeight, rectRadius);
                } catch (IOException e) {
                    ReleaseLogUtil.c(TAG_RELEASE, "createRoundRectPngRes() exception msg:", ExceptionUtils.d(e));
                } catch (Exception e2) {
                    ReleaseLogUtil.c(TAG_RELEASE, "createRoundRectPngRes()  exception msg:", ExceptionUtils.d(e2));
                } catch (OutOfMemoryError e3) {
                    ReleaseLogUtil.c(TAG_RELEASE, "createRoundRectPngRes:OutOfMemoryError:", ExceptionUtils.d(e3));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createBinBackground(ArrayList<String> arrayList) {
        int binFileType = getBinFileType();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next.contains(WatchFaceConstant.PREVIEW_RES) && next.endsWith(".png")) {
                String replace = SafeString.replace(next, ".png", WatchFaceConstant.BIN_SUFFIX);
                String str = getDeviceBgFilePath() + next;
                String str2 = getDeviceBgFilePath() + replace;
                String replace2 = SafeString.replace(str, WatchFaceConstant.PREVIEW_RES, WatchFaceConstant.WALLPAPER_RES);
                String replace3 = SafeString.replace(str2, WatchFaceConstant.PREVIEW_RES, WatchFaceConstant.WALLPAPER_RES);
                jls.b(str, str2, binFileType);
                jls.b(replace2, replace3, binFileType);
                copyWatchFaceToTransferDir(replace);
                copyWatchFaceToTransferDir(SafeString.replace(replace, WatchFaceConstant.PREVIEW_RES, WatchFaceConstant.WALLPAPER_RES));
            }
        }
    }

    private int getBinFileType() {
        WearWatchFaceInfo wearWatchFaceInfo = this.mWearWatchFaceInfo;
        if (wearWatchFaceInfo != null) {
            return wearWatchFaceInfo.getWearImageOption();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WearWatchFaceInfo sendDeviceWearInfo(WearWatchFaceInfo wearWatchFaceInfo) {
        WearWatchFaceInfo wearWatchFaceInfo2 = new WearWatchFaceInfo();
        ArrayList arrayList = new ArrayList(16);
        List<WearWatchFaceInfo.WearStyleStruct> wearStyleList = wearWatchFaceInfo.getWearStyleList();
        if (!CollectionUtils.d(wearStyleList)) {
            for (WearWatchFaceInfo.WearStyleStruct wearStyleStruct : wearStyleList) {
                String substring = SafeString.substring(wearStyleStruct.getPreviewName(), wearStyleStruct.getPreviewName().lastIndexOf("/") + 1);
                if (!substring.contains(".png") && !substring.contains(WatchFaceConstant.PREVIEW_RES)) {
                    LogUtil.a(TAG, "sendDeviceWearInfo() filter out xml resource.");
                } else if (!wearStyleStruct.isPreset()) {
                    WearWatchFaceInfo wearWatchFaceInfo3 = this.mWearWatchFaceInfo;
                    if (wearWatchFaceInfo3 != null && wearWatchFaceInfo3.getWearStyleType() == 2) {
                        arrayList.add(SafeString.replace(substring, ".png", WatchFaceConstant.BIN_SUFFIX));
                    }
                    arrayList.add(substring);
                }
            }
        }
        ArrayList<WearWatchFaceInfo.WearStyleStruct> updateWearStructListByXmlRes = updateWearStructListByXmlRes(wearWatchFaceInfo.getWearStyleList());
        this.mWearList.clear();
        this.mWearList.addAll(arrayList);
        wearWatchFaceInfo2.setWearStyleNum(wearWatchFaceInfo.getWearStyleNum());
        wearWatchFaceInfo2.setWearStyleList(updateWearStructListByXmlRes);
        wearWatchFaceInfo2.setCurWearImageIndex(wearWatchFaceInfo.getCurWearImageIndex());
        return wearWatchFaceInfo2;
    }

    private ArrayList<WearWatchFaceInfo.WearStyleStruct> updateWearStructListByXmlRes(List<WearWatchFaceInfo.WearStyleStruct> list) {
        ArrayList<WearWatchFaceInfo.WearStyleStruct> arrayList = new ArrayList<>();
        File file = WatchFaceUtil.getFile(getDeviceBgFilePath());
        if (file == null || !file.exists()) {
            ReleaseLogUtil.e(TAG_RELEASE, "updateWearStructListByXmlRes file does not exist.");
            return arrayList;
        }
        try {
            String canonicalPath = file.getCanonicalPath();
            for (WearWatchFaceInfo.WearStyleStruct wearStyleStruct : list) {
                String previewName = wearStyleStruct.getPreviewName();
                if (TextUtils.isEmpty(previewName)) {
                    LogUtil.h(TAG, "updateWearStructListByXmlRes wearResName is empty");
                } else if (previewName.contains(WatchFaceConstant.PRESET_RES) || wearStyleStruct.isPreset()) {
                    LogUtil.h(TAG, "updateWearStructListByXmlRes filter out preset resource.");
                    arrayList.add(wearStyleStruct);
                } else {
                    File file2 = WatchFaceUtil.getFile(SafeString.replace(canonicalPath + File.separator + SafeString.replace(previewName, ".png", WatchFaceConstant.XML_SUFFIX), WatchFaceConstant.PREVIEW_RES, WatchFaceConstant.WALLPAPER_RES));
                    if (file2 == null || !file2.exists()) {
                        LogUtil.h(TAG, "updateWearStructListByXmlRes() resource file does not exist.");
                        String previewName2 = wearStyleStruct.getPreviewName();
                        if (!TextUtils.isEmpty(previewName2)) {
                            String replace = SafeString.replace(previewName2, ".png", WatchFaceConstant.BIN_SUFFIX);
                            wearStyleStruct.setBgImageName(SafeString.replace(SafeString.replace(previewName2, "preview.png", "wallpaper.bin"), "preview.bin", "wallpaper.bin"));
                            wearStyleStruct.setPreviewName(replace);
                        }
                        arrayList.add(wearStyleStruct);
                    } else {
                        setResourceInfoModel(loadProperties(file2), arrayList, wearStyleStruct, previewName);
                    }
                }
            }
            return arrayList;
        } catch (IOException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "updateWearStructListByXmlRes exception :", ExceptionUtils.d(e));
            return arrayList;
        }
    }

    private Properties loadProperties(File file) {
        Properties properties = new Properties();
        if (!file.exists()) {
            ReleaseLogUtil.d(TAG_RELEASE, "loadProperties() not found wallpaper resource");
            return null;
        }
        if (!file.getName().contains(WatchFaceConstant.XML_SUFFIX)) {
            ReleaseLogUtil.d(TAG_RELEASE, "loadProperties() fileName is not match with resource.");
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                properties.loadFromXML(fileInputStream);
                fileInputStream.close();
                return properties;
            } finally {
            }
        } catch (IOException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "loadProperties() IOException :", ExceptionUtils.d(e));
            return null;
        }
    }

    private void setResourceInfoModel(Properties properties, ArrayList<WearWatchFaceInfo.WearStyleStruct> arrayList, WearWatchFaceInfo.WearStyleStruct wearStyleStruct, String str) {
        if (properties == null || properties.isEmpty()) {
            ReleaseLogUtil.d(TAG_RELEASE, "setResourceInfoModel property is null or empty.");
            return;
        }
        WearWatchFaceInfo.WearStyleStruct wearStyleStruct2 = new WearWatchFaceInfo.WearStyleStruct();
        wearStyleStruct2.setPreviewName(SafeString.replace(wearStyleStruct.getPreviewName(), ".png", WatchFaceConstant.BIN_SUFFIX));
        wearStyleStruct2.setWearStyleId(wearStyleStruct.getWearStyleId());
        Object obj = properties.get(CLOCK_TYPE);
        if (obj instanceof String) {
            wearStyleStruct2.setClockStyleIndex(CommonUtil.e((String) obj, 1));
        } else {
            wearStyleStruct2.setClockStyleIndex(1);
        }
        wearStyleStruct2.setBgImageName(SafeString.replace(SafeString.replace(str, "preview.png", "wallpaper.bin"), "preview.bin", "wallpaper.bin"));
        Object obj2 = properties.get(CLOCK_COLOR_LIST);
        if (obj2 instanceof String) {
            wearStyleStruct2.setClockStyleColor((String) obj2);
        } else {
            wearStyleStruct2.setClockStyleColor("");
        }
        arrayList.add(wearStyleStruct2);
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.DiyWatchFace
    protected List<Integer> getFileType() {
        if (!CollectionUtils.d(this.mFileTypePhotos)) {
            return this.mFileTypePhotos;
        }
        this.mFileTypePhotos.add(16);
        this.mFileTypePhotos.add(17);
        return this.mFileTypePhotos;
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
            if (optInt != 16 && optInt != 17) {
                ReleaseLogUtil.d(TAG_RELEASE, "devicesCallbackFileInfoCheckFail fileType: ", Integer.valueOf(optInt));
                return true;
            }
            if (CollectionUtils.d(this.mWearList)) {
                ReleaseLogUtil.d(TAG_RELEASE, "devicesCallbackFileInfoCheckFail childFileNames is empty");
                return true;
            }
            return !this.mWearList.contains(SafeString.replace(optString, WatchFaceConstant.WALLPAPER_RES, WatchFaceConstant.PREVIEW_RES));
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "devicesCallbackFileInfoCheckFailJSONException :", ExceptionUtils.d(e));
            return true;
        }
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.DiyWatchFace
    protected ResultCallback<TransferProgressResp> getTransferCallback() {
        return this.mSetWearWatchFaceCallback;
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.DiyWatchFace
    protected ResultCallback<TransmitWatchFace<WearWatchFaceInfo>> getTransmitWatchFaceCallback() {
        return this.mGetWearWatchFaceCallback;
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.DiyWatchFace
    protected void deleteSaveWatchFace() {
        deleteSaveBgFile(this.mWearList);
    }

    /* loaded from: classes9.dex */
    static class SingletonHolder {
        static final WearWatchFaceManager INSTANCE = new WearWatchFaceManager();

        private SingletonHolder() {
        }
    }
}
