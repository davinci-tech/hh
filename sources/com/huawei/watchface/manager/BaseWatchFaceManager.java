package com.huawei.watchface.manager;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import com.huawei.watchface.R$plurals;
import com.huawei.watchface.R$string;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.api.HwWatchFaceManager;
import com.huawei.watchface.cl;
import com.huawei.watchface.cm;
import com.huawei.watchface.cn;
import com.huawei.watchface.dz;
import com.huawei.watchface.e;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.TransferFileReqType;
import com.huawei.watchface.mvp.model.datatype.WatchFaceInfo;
import com.huawei.watchface.mvp.model.datatype.WatchFaceSupportInfo;
import com.huawei.watchface.mvp.model.filedownload.FilePuller;
import com.huawei.watchface.mvp.model.filedownload.PullListenerInterface;
import com.huawei.watchface.mvp.model.filedownload.PullResult;
import com.huawei.watchface.mvp.model.filedownload.PullTask;
import com.huawei.watchface.mvp.model.latona.LatonaWatchFaceProvider;
import com.huawei.watchface.mvp.model.latona.LatonaWatchFaceThemeAlbumInfo;
import com.huawei.watchface.mvp.model.latona.provider.ElementsProvider;
import com.huawei.watchface.mvp.model.latona.provider.WatchFaceProvider;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.DensityUtil;
import com.huawei.watchface.utils.FileHelper;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.callback.IAppTransferFileResultAIDLCallback;
import com.huawei.watchface.utils.callback.IBaseResponseCallback;
import com.huawei.watchface.utils.callback.IPackageNamePathCallback;
import com.huawei.watchface.utils.callback.IPhotoFileCallback;
import com.huawei.watchface.utils.callback.OperateWatchFaceCallback;
import java.io.File;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public abstract class BaseWatchFaceManager implements IPackageNamePathCallback {
    protected static final int ABILITY_UNSUPPORTED = 0;
    public static final String BG_SUFFIX = "_bg";
    protected static final int EMPTY_BACKGROUND_COUNT = 0;
    protected static final int EMPTY_PROGRESS_PERCENT = 0;
    public static final String FG_SUFFIX = "_fg";
    protected static final int FILE_TRANSFER_ONE_BACKGROUND = 1;
    public static final String ORIGINBG_SUFFIX = "_originbg";
    public static final String ORIGINFG_SUFFIX = "_originfg";
    public static final String PNG_SUFFIX = ".png";
    protected static final String SYMBOL_SPLIT_POINT = ".";
    protected static final String WATCHFACE_RES_PATH = "res";
    protected String c;
    protected String d;
    protected String g;
    private cn h;
    private cl i;
    private cl.a j;
    private HandlerThread k;
    private Handler l;
    private Handler m;
    protected String mBackgroundDir;
    protected Context mContext;
    protected HwWatchFaceBtManager mHwWatchFaceBtManager;
    protected String mHwtOperateDir;
    protected boolean mIsSyncWatchFace;
    protected OperateWatchFaceCallback mOperateWatchFaceCallback;
    protected String mPackageName;
    protected String mWatchFaceName;
    protected LatonaWatchFaceProvider mWatchFaceProvider;
    private Handler n;
    private cm o;

    /* renamed from: a, reason: collision with root package name */
    protected boolean f11059a = false;
    protected boolean b = false;
    protected int mTransferTotalCount = 0;
    protected int mTransferCompleteCount = 0;
    protected int mCurrentTransferPercent = 0;
    protected int e = 1;
    public long f = 0;
    protected IAppTransferFileResultAIDLCallback mIAppTransferFileResultAIDLCallback = new IAppTransferFileResultAIDLCallback() { // from class: com.huawei.watchface.manager.BaseWatchFaceManager.1
        @Override // com.huawei.watchface.utils.callback.IAppTransferFileResultAIDLCallback
        public void onFileTransferState(int i, String str) {
            HwLog.i("BaseWatchFaceManager", "mIAppTransferFileResultAIDLCallback onFileTransferState() percentage =" + i);
            if (BaseWatchFaceManager.this.devicesCallbackFileInfoCheckFail(str)) {
                HwLog.i("BaseWatchFaceManager", "mIAppTransferFileResultAIDLCallback onFileTransferState() check fail =" + str);
            } else if (i < BaseWatchFaceManager.this.mCurrentTransferPercent) {
                HwLog.i("BaseWatchFaceManager", "mIAppTransferFileResultAIDLCallback onFileTransferState() percentage backing");
            } else {
                BaseWatchFaceManager.this.mCurrentTransferPercent = i;
                BaseWatchFaceManager.this.k();
            }
        }

        @Override // com.huawei.watchface.utils.callback.IAppTransferFileResultAIDLCallback
        public void onUpgradeFailed(int i, String str) {
            HwLog.e("BaseWatchFaceManager", "mIAppTransferFileResultAIDLCallback onUpgradeFailed(): " + i);
            if (BaseWatchFaceManager.this.devicesCallbackFileInfoCheckFail(str)) {
                HwLog.i("BaseWatchFaceManager", "mIAppTransferFileResultAIDLCallback onUpgradeFailed() check fail =" + str);
            } else {
                if (i != 141001 && i != 141000 && i != 30004 && i != 140006) {
                    if (BaseWatchFaceManager.this.b(i)) {
                        BaseWatchFaceManager.this.a(i);
                        return;
                    }
                    BaseWatchFaceManager.this.mTransferCompleteCount++;
                    BaseWatchFaceManager.this.mCurrentTransferPercent = 0;
                    HwLog.i("BaseWatchFaceManager", "mIAppTransferFileResultAIDLCallback onFailure() Call show");
                    BaseWatchFaceManager.this.k();
                    return;
                }
                BaseWatchFaceManager.this.onSaveFailed();
            }
        }

        @Override // com.huawei.watchface.utils.callback.IAppTransferFileResultAIDLCallback
        public void onFileRespond(int i, String str) {
            HwLog.i("BaseWatchFaceManager", "IPhotoFileCallback onSuccess():" + i);
            if (BaseWatchFaceManager.this.devicesCallbackFileInfoCheckFail(str)) {
                HwLog.i("BaseWatchFaceManager", "mIAppTransferFileResultAIDLCallback onFileRespond() check fail =" + str);
            } else {
                BaseWatchFaceManager.this.mTransferCompleteCount++;
                BaseWatchFaceManager.this.mCurrentTransferPercent = 0;
                BaseWatchFaceManager.this.k();
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(int i) {
        return i == 140004 || i == 140009;
    }

    protected abstract void clearInvalidData();

    protected abstract boolean devicesCallbackFileInfoCheckFail(String str);

    protected abstract void getDeviceInfoByBt();

    protected abstract List<Integer> getFileType();

    protected abstract void getNextFileByBt(int i);

    protected abstract void notifyWatchFaceReady(boolean z);

    protected abstract void parseCustomWatchFaceConfig(ElementsProvider elementsProvider);

    protected void preTransferFinish() {
    }

    protected abstract void saveFileFromDevice(String str, int i);

    protected abstract void sendWatchFaceInfoToDevice(LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo);

    protected abstract void setTransferTotalCount(LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo);

    protected abstract void transmitWatchFaceInfo(String str);

    protected abstract void transmitWatchFaceInstallResult(int i);

    protected abstract void transmitWatchFaceLoadingProgress(String str);

    protected abstract void updateNativeFiles(LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo);

    public BaseWatchFaceManager(Context context, String str, boolean z) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mHwWatchFaceBtManager = HwWatchFaceBtManager.getInstance(applicationContext);
        this.mWatchFaceProvider = LatonaWatchFaceProvider.getInstance(this.mContext);
        this.c = this.mContext.getFilesDir() + str;
        this.d = this.c + "hwt/";
        if (z) {
            this.mBackgroundDir = this.c;
        } else {
            this.mBackgroundDir = this.c + "background/";
        }
        this.mHwtOperateDir = this.c + "parsing/";
    }

    public void setWatchFaceCallback(OperateWatchFaceCallback operateWatchFaceCallback) {
        this.mOperateWatchFaceCallback = operateWatchFaceCallback;
    }

    public void a(boolean z, boolean z2) {
        this.mIsSyncWatchFace = z;
        this.f11059a = z2;
    }

    public void a() {
        HwLog.d("BaseWatchFaceManager", "destroyTransferProgressDialog");
        this.i = null;
    }

    private String d() {
        if (HwWatchFaceBtManager.getInstance(this.mContext).getConnectWatchDeviceInfo() == null) {
            return "";
        }
        String deviceName = HwWatchFaceApi.getInstance(this.mContext).getDeviceName();
        String softVersion = HwWatchFaceApi.getInstance(this.mContext).getSoftVersion();
        if (TextUtils.isEmpty(deviceName) || TextUtils.isEmpty(softVersion)) {
            HwLog.w("BaseWatchFaceManager", "getDeviceNameVersion() deviceName or deviceVersion is null");
            return "";
        }
        if (deviceName.length() > 3) {
            deviceName = SafeString.substring(deviceName, deviceName.length() - 3);
        }
        if (softVersion.contains(".")) {
            softVersion = SafeString.substring(softVersion, softVersion.lastIndexOf(".") + 1);
        }
        HwLog.i("BaseWatchFaceManager", "getDeviceNameVersion() deviceName: " + deviceName + ", deviceVersion: " + softVersion);
        return deviceName + softVersion;
    }

    private void a(ElementsProvider elementsProvider, boolean z) {
        HwLog.i("BaseWatchFaceManager", "parseWatchFaceConfig() enter.");
        this.mWatchFaceProvider.parseLatonaWatchFaceInfo(elementsProvider, z);
        parseCustomWatchFaceConfig(elementsProvider);
    }

    private String a(boolean z) {
        if (z) {
            return WatchFaceProvider.getInstance(this.mContext).transmitWatchFaceInfo();
        }
        return this.mWatchFaceProvider.transmitLatonaWatchFaceInfo();
    }

    private void e() {
        WatchFaceSupportInfo watchFaceSupportInfo = this.mHwWatchFaceBtManager.getWatchFaceSupportInfo();
        if (watchFaceSupportInfo != null && watchFaceSupportInfo.getWatchFaceHeight() > 0) {
            HwLog.i("BaseWatchFaceManager", "initWatchFaceSize() setWatchFaceSize :" + watchFaceSupportInfo.getWatchFaceHeight());
            this.mWatchFaceProvider.setWatchFaceSize(watchFaceSupportInfo.getWatchFaceWidth(), watchFaceSupportInfo.getWatchFaceHeight());
            return;
        }
        this.mHwWatchFaceBtManager.getDeviceInfoForUi(new IBaseResponseCallback() { // from class: com.huawei.watchface.manager.BaseWatchFaceManager$$ExternalSyntheticLambda2
            @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
            public final void onResponse(int i, Object obj) {
                BaseWatchFaceManager.this.a(i, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, Object obj) {
        HwLog.i("BaseWatchFaceManager", "initWatchFaceSize() getDeviceInfoForUi :" + i);
        if (i == 101) {
            WatchFaceSupportInfo watchFaceSupportInfo = this.mHwWatchFaceBtManager.getWatchFaceSupportInfo();
            if (watchFaceSupportInfo != null) {
                this.mWatchFaceProvider.setWatchFaceSize(watchFaceSupportInfo.getWatchFaceWidth(), watchFaceSupportInfo.getWatchFaceHeight());
                return;
            }
            return;
        }
        this.mWatchFaceProvider.setWatchFaceSize(454, 454);
    }

    private void f() {
        File file = new File(this.d);
        if (!file.isDirectory()) {
            HwLog.i("BaseWatchFaceManager", "initWatchFacePath() watchFaceRootFile.mkdirs() :" + file.mkdirs());
        }
        File file2 = new File(this.mBackgroundDir);
        if (!file2.isDirectory()) {
            HwLog.i("BaseWatchFaceManager", "initWatchFacePath() backgroundFile.mkdirs() :" + file2.mkdirs());
        }
        if (file2.exists() && file2.isDirectory()) {
            this.mWatchFaceProvider.setLatonaBackgroundSavedPath(this.mBackgroundDir);
        }
        File file3 = new File(this.mHwtOperateDir);
        if (!file3.isDirectory()) {
            HwLog.i("BaseWatchFaceManager", "initWatchFacePath() operateFile.mkdirs() :" + file3.mkdirs());
        }
        this.mPackageName = null;
    }

    public void saveWatchFaceInfo(String str) {
        if (Math.abs(System.currentTimeMillis() - this.f) < 1000) {
            HwLog.i("BaseWatchFaceManager", "saveWatchFaceInfo disabled Click");
            return;
        }
        this.f = System.currentTimeMillis();
        HwLog.iBetaLog("BaseWatchFaceManager", "saveWatchFaceInfo() watchFaceThemeInfo: " + str);
        try {
            dz.b(String.valueOf(System.currentTimeMillis()));
            LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo = (LatonaWatchFaceThemeAlbumInfo) new Gson().fromJson(str, LatonaWatchFaceThemeAlbumInfo.class);
            if (latonaWatchFaceThemeAlbumInfo == null) {
                HwLog.w("BaseWatchFaceManager", "saveWatchFaceInfo() watchFaceInfo is null.");
                return;
            }
            if (latonaWatchFaceThemeAlbumInfo.isClickBackToSave()) {
                HwLog.i("BaseWatchFaceManager", "saveWatchFaceInfo() isClickBackToSave.");
                a(latonaWatchFaceThemeAlbumInfo);
                return;
            }
            HwLog.i("BaseWatchFaceManager", "saveWatchFaceInfo() isClickSaveButton.");
            b();
            updateNativeFiles(latonaWatchFaceThemeAlbumInfo);
            setTransferTotalCount(latonaWatchFaceThemeAlbumInfo);
            sendWatchFaceInfoToDevice(latonaWatchFaceThemeAlbumInfo);
        } catch (JsonSyntaxException unused) {
            HwLog.e("BaseWatchFaceManager", "saveWatchFaceInfo() JsonSyntaxException occured.");
        }
    }

    public void a(final LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo) {
        OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateWatchFaceCallback;
        if (operateWatchFaceCallback == null) {
            return;
        }
        final Context customWebViewContext = operateWatchFaceCallback.getCustomWebViewContext();
        if (customWebViewContext == null) {
            HwLog.w("BaseWatchFaceManager", "showSaveDialog() context is null.");
        } else if (customWebViewContext instanceof Activity) {
            ((Activity) customWebViewContext).runOnUiThread(new Runnable() { // from class: com.huawei.watchface.manager.BaseWatchFaceManager.2
                @Override // java.lang.Runnable
                public void run() {
                    BaseWatchFaceManager.this.a(customWebViewContext, latonaWatchFaceThemeAlbumInfo);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, final LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo) {
        cn a2 = new cn.a(context).a(DensityUtil.getStringById(R$string.photo_album_dialog_should_save_change)).b(DensityUtil.getStringById(R$string.dialog_cancel_comment_discard), new View.OnClickListener() { // from class: com.huawei.watchface.manager.BaseWatchFaceManager.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HwLog.i("BaseWatchFaceManager", "showSaveDialog, CancelSaveWatchFace.");
                if (BaseWatchFaceManager.this.h != null && BaseWatchFaceManager.this.h.isShowing()) {
                    BaseWatchFaceManager.this.h.dismiss();
                    BaseWatchFaceManager.this.h = null;
                }
                BaseWatchFaceManager.this.a(1, latonaWatchFaceThemeAlbumInfo);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a(DensityUtil.getStringById(R$string.photo_album_dialog_save_change), new View.OnClickListener() { // from class: com.huawei.watchface.manager.BaseWatchFaceManager.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HwLog.i("BaseWatchFaceManager", "showSaveDialog, SaveWatchFace.");
                if (BaseWatchFaceManager.this.h != null && BaseWatchFaceManager.this.h.isShowing()) {
                    BaseWatchFaceManager.this.h.dismiss();
                    BaseWatchFaceManager.this.h = null;
                }
                BaseWatchFaceManager.this.b();
                BaseWatchFaceManager.this.updateNativeFiles(latonaWatchFaceThemeAlbumInfo);
                BaseWatchFaceManager.this.setTransferTotalCount(latonaWatchFaceThemeAlbumInfo);
                BaseWatchFaceManager.this.sendWatchFaceInfoToDevice(latonaWatchFaceThemeAlbumInfo);
                BaseWatchFaceManager.this.a(2, latonaWatchFaceThemeAlbumInfo);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        this.h = a2;
        if (!a2.isShowing()) {
            HwLog.i("BaseWatchFaceManager", "mNoTitleCustomAlertDialog is Showing");
            this.h.setCancelable(false);
            this.h.show();
        }
        HwLog.i("BaseWatchFaceManager", "mNoTitleCustomAlertDialog has showed");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, LatonaWatchFaceThemeAlbumInfo latonaWatchFaceThemeAlbumInfo) {
        if (latonaWatchFaceThemeAlbumInfo == null) {
            HwLog.w("BaseWatchFaceManager", "clickBackToSaveWatchFace, watchFaceInfo is null.");
        } else if (latonaWatchFaceThemeAlbumInfo.isClickBackToSave()) {
            HwLog.i("BaseWatchFaceManager", "clickBackToSaveWatchFace, isClickBackToSave.");
            HwWatchFaceManager.getInstance(this.mContext).saveSuccess();
            this.mOperateWatchFaceCallback.resetWatchFaceAlbumInfoStatus(i);
        }
    }

    public void getWatchFaceInfo(String str, String str2, String str3, String str4) {
        HwLog.i("BaseWatchFaceManager", "getWatchFaceInfo" + str + str2);
        this.b = false;
        g();
        clearInvalidData();
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            HwLog.w("BaseWatchFaceManager", "getWatchFaceInfo() hiTopId or version is empty");
            return;
        }
        HwLog.i("BaseWatchFaceManager", "getWatchFaceInfo() hiTopId: " + str);
        this.mWatchFaceName = d() + str + str2;
        e();
        f();
        File file = new File(this.d + this.mWatchFaceName + WatchFaceConstant.HWT_SUFFIX);
        if (file.exists() && !file.isDirectory()) {
            HwLog.i("BaseWatchFaceManager", "getWatchFaceInfo() hwt cache exist");
            h();
        } else {
            a(str, str2, str3, str4);
        }
    }

    public void a(String str) {
        HwLog.i("BaseWatchFaceManager", "syncWatchFaceInfo msg is:" + str);
        this.b = true;
        g();
        clearInvalidData();
        e();
        f();
        WatchFacePhotoAlbumManager.getInstance(Environment.getApplicationContext()).e(str);
    }

    private void g() {
        HwWatchFaceApi hwWatchFaceApi = HwWatchFaceApi.getInstance(this.mContext);
        if (hwWatchFaceApi.getDeviceConnectState() == 1) {
            String deviceIdentify = hwWatchFaceApi.getDeviceIdentify();
            if (TextUtils.isEmpty(this.g)) {
                this.g = deviceIdentify;
            } else if (!TextUtils.equals(this.g, deviceIdentify)) {
                this.g = deviceIdentify;
                this.mIsSyncWatchFace = false;
            }
        }
    }

    private void h() {
        HwLog.i("BaseWatchFaceManager", "didDownloadHwtResource() enter.");
        String str = this.d + this.mWatchFaceName + WatchFaceConstant.HWT_SUFFIX;
        String str2 = this.mHwtOperateDir + this.mWatchFaceName;
        String str3 = str2 + ".zip";
        if (!new File(str3).exists()) {
            HwLog.i("BaseWatchFaceManager", "didDownloadHwtResource() watchFaceName.zip does not exists, copy file");
            FileHelper.copyFileToParsingDir(str, str3);
        }
        File file = new File(str2);
        if (!file.exists() || !file.isDirectory() || ArrayUtils.isEmpty(file.listFiles())) {
            int b = FileHelper.b(str3, str2);
            HwLog.i("BaseWatchFaceManager", "didDownloadHwtResource() unzipHwtResult amount =" + b);
            if (b <= 0) {
                HwLog.w("BaseWatchFaceManager", "didDownloadHwtResource() unzipHwtFile failed");
                return;
            }
        }
        File file2 = new File(str2);
        File[] listFiles = file2.listFiles();
        if (file2.isDirectory() && listFiles != null) {
            int length = listFiles.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                File file3 = listFiles[i];
                HwLog.d("BaseWatchFaceManager", "didDownloadHwtResource() WatchFaceDir Contains :" + file3.getName());
                if (file3.getName().endsWith(".watchface")) {
                    this.mPackageName = file3.getName();
                    HwLog.i("BaseWatchFaceManager", "didDownloadHwtResource() PackageName :" + this.mPackageName);
                    break;
                }
                i++;
            }
        }
        if (!TextUtils.isEmpty(this.mPackageName)) {
            b(this.mIsSyncWatchFace);
        }
        HwLog.i("BaseWatchFaceManager", "didDownloadHwtResource() mIsSyncWatchFace:" + this.mIsSyncWatchFace);
        if (this.mIsSyncWatchFace) {
            return;
        }
        if (this.j == null) {
            HwLog.d("BaseWatchFaceManager", "mTransferProgressDialogBuilder == null");
            this.mTransferCompleteCount = 0;
            this.mCurrentTransferPercent = 0;
        }
        getDeviceInfoByBt();
    }

    private void a(final String str, final String str2, String str3, String str4) {
        HwLog.i("BaseWatchFaceManager", "downloadHwtResource() enter.");
        StringBuffer stringBuffer = new StringBuffer(str);
        stringBuffer.append("_");
        stringBuffer.append(str2);
        String stringBuffer2 = stringBuffer.toString();
        WatchFaceInfo watchFaceInfo = new WatchFaceInfo();
        watchFaceInfo.configFileType(String.valueOf(0));
        watchFaceInfo.configDigest(str4);
        a(stringBuffer2, str3, this.d + this.mWatchFaceName + WatchFaceConstant.HWT_SUFFIX, watchFaceInfo, new IBaseResponseCallback() { // from class: com.huawei.watchface.manager.BaseWatchFaceManager$$ExternalSyntheticLambda6
            @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
            public final void onResponse(int i, Object obj) {
                BaseWatchFaceManager.this.a(str, str2, i, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(String str, String str2, int i, Object obj) {
        if (i == 1) {
            HwLog.i("BaseWatchFaceManager", "downloadHwtResource() success");
            h();
            FilePuller.getInstance(this.mContext).downloadSuccessRemoveTask(str, str2);
        } else if (i == -15 || i == -3 || i == -14 || i == -106) {
            FilePuller.getInstance(this.mContext).cancelTask(str, str2);
        }
    }

    private void b(boolean z) {
        a(new e(this.mHwtOperateDir + this.mWatchFaceName, this.mPackageName, this.mHwtOperateDir, this).a(false), z);
        StringBuilder sb = new StringBuilder("parseLocalConfig() parseLocalConfig :");
        sb.append(a(false));
        HwLog.iBetaLog("BaseWatchFaceManager", sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        HwLog.i("BaseWatchFaceManager", "notifyWatchFaceLoadingProgress() enter.");
        OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateWatchFaceCallback;
        if (operateWatchFaceCallback == null) {
            return;
        }
        Context customWebViewContext = operateWatchFaceCallback.getCustomWebViewContext();
        if (customWebViewContext == null) {
            HwLog.w("BaseWatchFaceManager", "notifyWatchFaceLoadingProgress() context is null.");
        } else if (customWebViewContext instanceof Activity) {
            ((Activity) customWebViewContext).runOnUiThread(new Runnable() { // from class: com.huawei.watchface.manager.BaseWatchFaceManager$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    BaseWatchFaceManager.this.p();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void p() {
        int j = j();
        HwLog.i("BaseWatchFaceManager", "notifyWatchFaceLoadingProgress() progress: " + j);
        String str = DensityUtil.getStringById(R$string.eu3_tm_dl_loading_new) + CommonUtils.a(j, 2, 0);
        HwLog.i("BaseWatchFaceManager", "notifyWatchFaceLoadingProgress() message: " + str);
        transmitWatchFaceLoadingProgress(str);
    }

    protected void getFileByBt(final int i, final int i2, int i3, String str) {
        HwLog.i("BaseWatchFaceManager", "getFileByBt() TransferImagesFromDevice index: " + i + ", size: " + i2 + ", fileType: " + i3 + ", fileName: " + str);
        if (i2 < i) {
            HwLog.w("BaseWatchFaceManager", "getFileByBt() mNoContainsPhotos is error");
        } else {
            if (TextUtils.isEmpty(str)) {
                HwLog.w("BaseWatchFaceManager", "getFileByBt() fileName is empty");
                return;
            }
            this.mIsSyncWatchFace = true;
            this.f11059a = false;
            HwDeviceConfigManager.getInstance(this.mContext).a(str, i3, false, new IPhotoFileCallback() { // from class: com.huawei.watchface.manager.BaseWatchFaceManager.5
                @Override // com.huawei.watchface.utils.callback.IPhotoFileCallback
                public void onSuccess(int i4, String str2, String str3) {
                    HwLog.i("BaseWatchFaceManager", "getFileByBt() transfer successful :" + i4);
                    if (BaseWatchFaceManager.this.f11059a) {
                        HwLog.e("BaseWatchFaceManager", "getFileByBt onSuccess isDestroy");
                    } else {
                        BaseWatchFaceManager.this.saveFileFromDevice(str2, i);
                        BaseWatchFaceManager.this.a(i, i2);
                    }
                }

                @Override // com.huawei.watchface.utils.callback.IPhotoFileCallback
                public void onFailure(int i4, String str2) {
                    HwLog.i("BaseWatchFaceManager", "getFileByBt() TransferImagesFromDeviceFailed errorCode:" + i4);
                    if (!BaseWatchFaceManager.this.f11059a) {
                        BaseWatchFaceManager.this.a(i, i2);
                        BaseWatchFaceManager.this.c(i4);
                    } else {
                        HwLog.e("BaseWatchFaceManager", "getFileByBt onFailure isDestroy");
                    }
                }

                @Override // com.huawei.watchface.utils.callback.IPhotoFileCallback
                public void onProgress(int i4, String str2) {
                    HwLog.i("BaseWatchFaceManager", "getFileByBt() onFileTransferState percentage =" + i4);
                    if (BaseWatchFaceManager.this.f11059a) {
                        HwLog.d("BaseWatchFaceManager", "getFileByBt onProgress isDestroy");
                    } else {
                        if (i4 <= BaseWatchFaceManager.this.mCurrentTransferPercent || i4 - BaseWatchFaceManager.this.mCurrentTransferPercent >= 10) {
                            return;
                        }
                        BaseWatchFaceManager.this.mCurrentTransferPercent = i4;
                        BaseWatchFaceManager.this.i();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2) {
        int i3 = i + 1;
        if (i3 < i2) {
            HwLog.i("BaseWatchFaceManager", "getFileByBt() ContinueTransferImage");
            this.mTransferCompleteCount++;
            this.mCurrentTransferPercent = 0;
            getNextFileByBt(i3);
            i();
            return;
        }
        HwLog.i("BaseWatchFaceManager", "getFileByBt() TransferImagesFromDeviceComplete");
        this.mTransferCompleteCount = i2;
        this.mCurrentTransferPercent = 0;
        preTransferFinish();
        i();
        notifyWatchFaceReady(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        if (i == 141001 || i == 141000 || i == 30004 || i == 30003 || i == 140006) {
            this.mIsSyncWatchFace = false;
        }
        if (this.mTransferCompleteCount >= this.mTransferTotalCount) {
            this.mIsSyncWatchFace = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(IBaseResponseCallback iBaseResponseCallback, PullTask pullTask, PullResult pullResult) {
        iBaseResponseCallback.onResponse(pullResult.fetchStatus(), pullResult);
    }

    private void a(String str, String str2, String str3, WatchFaceInfo watchFaceInfo, final IBaseResponseCallback iBaseResponseCallback) {
        a(str, str2, str3, watchFaceInfo, new PullListenerInterface() { // from class: com.huawei.watchface.manager.BaseWatchFaceManager$$ExternalSyntheticLambda5
            @Override // com.huawei.watchface.mvp.model.filedownload.PullListenerInterface
            public final void onPullingChange(PullTask pullTask, PullResult pullResult) {
                BaseWatchFaceManager.a(IBaseResponseCallback.this, pullTask, pullResult);
            }
        });
    }

    private void a(String str, String str2, String str3, WatchFaceInfo watchFaceInfo, PullListenerInterface pullListenerInterface) {
        PullTask pullTask = new PullTask();
        pullTask.configDestPath(str3);
        pullTask.configHttpUrl(str2);
        pullTask.configFileUrlJson(null);
        pullTask.configListener(pullListenerInterface);
        pullTask.configOption(CommonUtils.b(watchFaceInfo.fetchFileType()));
        pullTask.configTotalSize(watchFaceInfo.fetchFileSize());
        pullTask.configParam(b((String) null));
        pullTask.configUUID(str);
        pullTask.configFiledID(null);
        pullTask.configDigest(watchFaceInfo.fetchDigest());
        FilePuller.getInstance(this.mContext).addTask(pullTask);
    }

    private String b(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(RecommendConstants.FILE_ID, str);
            jSONObject.put(RecommendConstants.VER, "0");
        } catch (JSONException unused) {
            HwLog.e("BaseWatchFaceManager", "getDownloadParam error JSONException");
        }
        return jSONObject.toString();
    }

    protected void onSaveSuccess(Object obj) {
        m();
        HwDeviceConfigManager.getInstance(this.mContext).registerPhotoAndVideoCallback(this.mIAppTransferFileResultAIDLCallback, getFileType(), TransferFileReqType.DEVICE_UNREGISTRATION);
        transmitWatchFaceInstallResult(1);
        c();
        this.mTransferCompleteCount = this.mTransferTotalCount * this.e;
        this.mCurrentTransferPercent = 0;
        HwLog.i("BaseWatchFaceManager", "onSaveSuccess() showProgressDialog");
        k();
        l();
        if (obj instanceof Integer) {
            int intValue = ((Integer) obj).intValue();
            HwLog.i("BaseWatchFaceManager", "onSaveSuccess() failCount :" + intValue);
            if (intValue > 0) {
                this.mOperateWatchFaceCallback.showToast(DensityUtil.getQuantityStringById(R$plurals.photo_album_dialog_loading_failed_count, intValue, Integer.valueOf(intValue)));
            }
        }
    }

    protected void onSaveToTransferBackgrounds(Object obj, int i) {
        if (obj instanceof Integer) {
            if (i > 0) {
                this.mTransferTotalCount = ((Integer) obj).intValue() / i;
            } else {
                this.mTransferTotalCount = ((Integer) obj).intValue();
            }
        }
        this.e = i;
        this.mTransferCompleteCount = 0;
        c();
        HwLog.i("BaseWatchFaceManager", "onSaveToTransferBackgrounds() showProgressDialog");
        k();
    }

    protected void onSaveFailed() {
        HwLog.i("BaseWatchFaceManager", "enter onSaveFailed");
        m();
        HwDeviceConfigManager.getInstance(this.mContext).registerPhotoAndVideoCallback(this.mIAppTransferFileResultAIDLCallback, getFileType(), TransferFileReqType.DEVICE_UNREGISTRATION);
        c();
        l();
        String stringById = DensityUtil.getStringById(R$string.photo_album_dialog_loading_install_failed);
        OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateWatchFaceCallback;
        if (operateWatchFaceCallback == null) {
            return;
        }
        operateWatchFaceCallback.showToast(stringById);
        transmitWatchFaceInstallResult(-1);
    }

    protected void a(int i) {
        String stringById;
        HwLog.i("BaseWatchFaceManager", "enter onSaveFailed errorCode:" + i);
        m();
        HwDeviceConfigManager.getInstance(this.mContext).registerPhotoAndVideoCallback(this.mIAppTransferFileResultAIDLCallback, getFileType(), TransferFileReqType.DEVICE_UNREGISTRATION);
        c();
        l();
        if (i == 140004) {
            stringById = DensityUtil.getStringById(R$string.number_of_dials_has_reached_maximum_notice);
        } else if (i == 140009) {
            stringById = DensityUtil.getStringById(R$string.IDS_watchface_device_insufficient_space_new);
        } else {
            stringById = DensityUtil.getStringById(R$string.photo_album_dialog_loading_install_failed);
        }
        OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateWatchFaceCallback;
        if (operateWatchFaceCallback == null) {
            return;
        }
        if (i == 140009 && (operateWatchFaceCallback.getCustomWebViewContext() instanceof Activity)) {
            c(stringById);
        } else {
            this.mOperateWatchFaceCallback.showToast(stringById);
        }
        transmitWatchFaceInstallResult(-1);
    }

    private void c(final String str) {
        final Activity activity = (Activity) this.mOperateWatchFaceCallback.getCustomWebViewContext();
        if (activity == null) {
            HwLog.i("BaseWatchFaceManager", "onSaveFailed() mCustomTextAlertDialog is null.");
        } else {
            activity.runOnUiThread(new Runnable() { // from class: com.huawei.watchface.manager.BaseWatchFaceManager$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    BaseWatchFaceManager.this.a(activity, str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(Activity activity, String str) {
        cm a2 = new cm.a(activity).a("").b(str).a(R$string.IDS_user_permission_know, new View.OnClickListener() { // from class: com.huawei.watchface.manager.BaseWatchFaceManager$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseWatchFaceManager.lambda$onClick$hianalytics1(BaseWatchFaceManager.this, view);
            }
        }).a();
        this.o = a2;
        if (!a2.isShowing()) {
            HwLog.i("BaseWatchFaceManager", "onSaveFailed() mCustomTextAlertDialog is Showing.");
            this.o.setCancelable(false);
            this.o.show();
        }
        HwLog.i("BaseWatchFaceManager", "onSaveFailed() mCustomTextAlertDialog has showed.");
    }

    private /* synthetic */ void a(View view) {
        cm cmVar = this.o;
        if (cmVar != null) {
            cmVar.dismiss();
            this.o = null;
        }
    }

    private void b(int i, int i2) {
        if (this.l == null) {
            HandlerThread handlerThread = new HandlerThread("BaseWatchFaceManager");
            this.k = handlerThread;
            handlerThread.start();
            this.l = new a(this.k.getLooper());
        }
        this.l.sendEmptyMessageDelayed(i, i2);
    }

    protected void b() {
        OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateWatchFaceCallback;
        if (operateWatchFaceCallback == null) {
            return;
        }
        Context customWebViewContext = operateWatchFaceCallback.getCustomWebViewContext();
        if (customWebViewContext == null) {
            HwLog.i("BaseWatchFaceManager", "showLoadingDialog() context is null.");
        } else if (customWebViewContext instanceof Activity) {
            ((Activity) customWebViewContext).runOnUiThread(new Runnable() { // from class: com.huawei.watchface.manager.BaseWatchFaceManager$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    BaseWatchFaceManager.this.o();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o() {
        HwLog.i("BaseWatchFaceManager", "showLoadingDialog() run.");
        this.mOperateWatchFaceCallback.showLoadingDialog(DensityUtil.getStringById(R$string.saving));
    }

    protected void c() {
        OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateWatchFaceCallback;
        if (operateWatchFaceCallback == null) {
            return;
        }
        Context customWebViewContext = operateWatchFaceCallback.getCustomWebViewContext();
        if (customWebViewContext == null) {
            HwLog.i("BaseWatchFaceManager", "dismissLoadingDialog() context is null.");
        } else if (customWebViewContext instanceof Activity) {
            ((Activity) customWebViewContext).runOnUiThread(new Runnable() { // from class: com.huawei.watchface.manager.BaseWatchFaceManager.6
                @Override // java.lang.Runnable
                public void run() {
                    HwLog.i("BaseWatchFaceManager", "dismissLoadingDialog() run.");
                    if (BaseWatchFaceManager.this.mOperateWatchFaceCallback == null) {
                        HwLog.i("BaseWatchFaceManager", "dismissLoadingDialog mOperateWatchFaceCallback is null.");
                    } else {
                        BaseWatchFaceManager.this.mOperateWatchFaceCallback.hideLoadingDialog();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int j() {
        if (this.mTransferTotalCount > 0) {
            double d = 100.0d / (r0 * this.e);
            int ceil = (int) Math.ceil((this.mTransferCompleteCount * d) + ((this.mCurrentTransferPercent * d) / 100.0d));
            if (ceil > 100) {
                return 100;
            }
            if (ceil >= 0) {
                HwLog.i("BaseWatchFaceManager", "Right progress value");
                return ceil;
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateWatchFaceCallback;
        if (operateWatchFaceCallback == null || this.mTransferTotalCount == 0) {
            HwLog.w("BaseWatchFaceManager", "showProgressDialog() error");
            return;
        }
        final Context customWebViewContext = operateWatchFaceCallback.getCustomWebViewContext();
        if (customWebViewContext == null) {
            HwLog.w("BaseWatchFaceManager", "showProgressDialog() context is null.");
        } else if (customWebViewContext instanceof Activity) {
            ((Activity) customWebViewContext).runOnUiThread(new Runnable() { // from class: com.huawei.watchface.manager.BaseWatchFaceManager.7
                @Override // java.lang.Runnable
                public void run() {
                    int i = (BaseWatchFaceManager.this.mTransferCompleteCount / BaseWatchFaceManager.this.e) + 1;
                    if (i > BaseWatchFaceManager.this.mTransferTotalCount) {
                        i = BaseWatchFaceManager.this.mTransferTotalCount;
                    }
                    String stringById = DensityUtil.getStringById(R$string.photo_album_dialog_saving_count, Integer.valueOf(i), Integer.valueOf(BaseWatchFaceManager.this.mTransferTotalCount));
                    int j = BaseWatchFaceManager.this.j();
                    String a2 = CommonUtils.a(j, 2, 0);
                    HwLog.i("BaseWatchFaceManager", "showProgressDialog() Desc :" + stringById + ", progress :" + j + ", percentText :" + a2);
                    BaseWatchFaceManager.this.a(stringById, j, a2, customWebViewContext);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, int i, String str2, Context context) {
        cl clVar = this.i;
        if (clVar != null && clVar.isShowing() && this.j != null) {
            HwLog.i("BaseWatchFaceManager", "showProgress() ProgressDialogExist, update immediately");
            this.j.a(str);
            this.j.a(i);
            this.j.b(str2);
            if (i >= 100) {
                n();
                b(1001, 5000);
                return;
            }
            return;
        }
        HwLog.i("BaseWatchFaceManager", "showProgress() ProgressDialogDoesNotExist, create new");
        if (context == null || ((Activity) context).isDestroyed()) {
            HwLog.i("BaseWatchFaceManager", "showProgress() context is null.");
            return;
        }
        this.i = new cl(context);
        cl.a aVar = new cl.a(context);
        this.j = aVar;
        aVar.a(str);
        cl a2 = this.j.a();
        this.i = a2;
        a2.setCancelable(false);
        this.i.setCanceledOnTouchOutside(false);
        this.j.a(i);
        this.j.b(str2);
        if (this.i.isShowing()) {
            return;
        }
        HwLog.i("BaseWatchFaceManager", "showProgress() ShowProgressDialog");
        this.i.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        HwLog.i("BaseWatchFaceManager", "Enter dismissProgressDialog");
        if (this.m == null) {
            this.m = new Handler(Looper.getMainLooper()) { // from class: com.huawei.watchface.manager.BaseWatchFaceManager.8
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    if (message != null) {
                        super.handleMessage(message);
                        BaseWatchFaceManager.this.a(message);
                    }
                }
            };
        }
        this.m.sendEmptyMessageDelayed(100, 500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Message message) {
        cl clVar;
        StringBuilder sb = new StringBuilder("HandleDismissProgressDialog :");
        sb.append(this.i != null);
        HwLog.i("BaseWatchFaceManager", sb.toString());
        if (this.i != null) {
            HwLog.i("BaseWatchFaceManager", "ProgressIsShowing :" + this.i.isShowing());
        }
        if (message.what == 100 && (clVar = this.i) != null && clVar.isShowing()) {
            HwLog.i("BaseWatchFaceManager", "DismissProgressDialog");
            n();
            this.i.dismiss();
            this.i = null;
            this.j = null;
            this.mTransferCompleteCount = 0;
            this.mTransferTotalCount = 0;
            this.mCurrentTransferPercent = 0;
        }
        this.m = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        this.mIsSyncWatchFace = false;
        n();
    }

    private void n() {
        Handler handler = this.l;
        if (handler == null || !handler.hasMessages(1001)) {
            return;
        }
        this.l.removeMessages(1001);
        HwLog.i("BaseWatchFaceManager", "removeResponseTimeoutHandler remove response timeout");
    }

    @Override // com.huawei.watchface.utils.callback.IPackageNamePathCallback
    public void notifyPathChanged() {
        this.mPackageName += "/watchface";
        HwLog.i("BaseWatchFaceManager", "notifyPathChanged() mPackageName: " + this.mPackageName);
    }

    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message != null) {
                super.handleMessage(message);
                if (message.what == 1001) {
                    HwLog.i("BaseWatchFaceManager", "TransferTimeOutHandler() DEVICE_NOT_RESPONSE_TIMEOUT_MESSAGE");
                    BaseWatchFaceManager.this.m();
                    HwDeviceConfigManager.getInstance(BaseWatchFaceManager.this.mContext).registerPhotoAndVideoCallback(BaseWatchFaceManager.this.mIAppTransferFileResultAIDLCallback, BaseWatchFaceManager.this.getFileType(), TransferFileReqType.DEVICE_UNREGISTRATION);
                    BaseWatchFaceManager.this.transmitWatchFaceInstallResult(1);
                    BaseWatchFaceManager.this.c();
                    BaseWatchFaceManager baseWatchFaceManager = BaseWatchFaceManager.this;
                    baseWatchFaceManager.mTransferCompleteCount = baseWatchFaceManager.mTransferTotalCount;
                    BaseWatchFaceManager.this.mCurrentTransferPercent = 0;
                    BaseWatchFaceManager.this.l();
                    return;
                }
                HwLog.w("BaseWatchFaceManager", "TransferTimeOutHandler default");
            }
        }
    }

    public void onDestroy() {
        HwLog.i("BaseWatchFaceManager", "onDestroy() enter.");
        Handler handler = this.l;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.l = null;
        }
        Handler handler2 = this.m;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
            this.m = null;
        }
        if (this.i != null) {
            this.i = null;
        }
        if (this.mOperateWatchFaceCallback != null) {
            this.mOperateWatchFaceCallback = null;
        }
    }

    protected void notifyH5Ready(final String str) {
        if (this.n == null) {
            this.n = new Handler(Looper.getMainLooper()) { // from class: com.huawei.watchface.manager.BaseWatchFaceManager.9
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    if (message == null) {
                        return;
                    }
                    super.handleMessage(message);
                    if (message.what == 101 && BaseWatchFaceManager.this.mOperateWatchFaceCallback != null) {
                        HwLog.i("BaseWatchFaceManager", "notifyH5Ready() transmitWatchFaceInfo.");
                        BaseWatchFaceManager.this.transmitWatchFaceInfo(str);
                    }
                    BaseWatchFaceManager.this.n = null;
                }
            };
        }
        this.n.sendEmptyMessageDelayed(101, 500L);
    }

    public void resetSyncWatchFace() {
        this.mIsSyncWatchFace = false;
    }

    public void refreshData() {
        this.mIsSyncWatchFace = false;
        getDeviceInfoByBt();
    }

    static void lambda$onClick$hianalytics1(BaseWatchFaceManager baseWatchFaceManager, View view) {
        baseWatchFaceManager.a(view);
        ViewClickInstrumentation.clickOnView(view);
    }
}
