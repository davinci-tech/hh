package com.huawei.operation.h5pro.ble;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import androidx.webkit.ProxyConfig;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.HealthAccessTokenUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ProgressListener;
import com.huawei.operation.adapter.PluginOperationAdapter;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.br.BrOperatorCompactImpl;
import com.huawei.operation.h5pro.H5proUtil;
import com.huawei.operation.h5pro.ble.BleJsInteractionCompact;
import com.huawei.operation.h5pro.ble.bean.JsDownloadBean;
import com.huawei.operation.h5pro.ble.bean.JsReadFileBean;
import com.huawei.operation.h5pro.devicekind.LuminousRope;
import com.huawei.operation.h5pro.devicekind.MassageGun;
import com.huawei.operation.h5pro.jsmodules.InnerApi;
import com.huawei.operation.js.BleJsInteraction;
import com.huawei.operation.operation.PluginOperation;
import com.huawei.operation.utils.BleJsBiOperate;
import com.huawei.pluginbase.PluginBaseAdapter;
import defpackage.cez;
import defpackage.cke;
import defpackage.cpp;
import defpackage.gxf;
import defpackage.jdx;
import defpackage.jeg;
import defpackage.lqg;
import defpackage.lqi;
import defpackage.mol;
import defpackage.nrt;
import defpackage.sqd;
import health.compact.a.CommonUtil;
import health.compact.a.HEXUtils;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class BleJsInteractionCompact extends BleJsInteraction {
    private static final int BUFFER_SIZE = 10485760;
    private static final String ERR_CODE_DOWNLOAD_FAIL = "90011";
    private static final int READ_FILE_BUFFER_SIZE = 3145728;
    private static final String RELEASE_TAG = "R_PluginOperation_BleJsInteractionCompact";
    private static final Set<String> SINGLE_TOP_MODE_TYPE;
    private static final int STRING_BUILDER_DEFAULT_LENGTH = 16;
    private static final String TAG = "PluginOperation_BleJsInteractionCompact";
    private static ContentValues sDeviceInfo;
    private BleOperatorCompactImpl mBleOperatorCompact;
    private String mBrMac;
    private BrOperatorCompactImpl mBrOperatorCompact;
    private Context mContext;
    private String mDeviceId;
    private String mDeviceType;
    private Handler mHandler;
    private H5ProInstance mInstance;
    private String mName;
    private String mPathPara;
    private String mUniqueId;

    /* loaded from: classes9.dex */
    interface OnProgressCallback {
        void onProgress(String str, long j, long j2, boolean z);
    }

    static {
        HashSet hashSet = new HashSet(10);
        SINGLE_TOP_MODE_TYPE = hashSet;
        hashSet.add("HDK_ROWING_MACHINE");
        hashSet.add("HDK_SMART_PILLOW");
        hashSet.add("HDK_MASSAGE_GUN");
        hashSet.add("HDK_BLOOD_OXYGEN");
        hashSet.add("HDK_ECG");
    }

    public void startH5Pro(Context context, String str, ContentValues contentValues, String str2) {
        ContentValues contentValues2;
        LogUtil.a(TAG, "startH5Pro pkgName = ", str);
        sDeviceInfo = contentValues;
        if (context == null || TextUtils.isEmpty(str) || (contentValues2 = sDeviceInfo) == null) {
            LogUtil.b(TAG, "compactInit context or pkgName or sDeviceInfo is null");
            return;
        }
        this.mProductId = contentValues2.getAsString("productId");
        this.mUniqueId = sDeviceInfo.getAsString("uniqueId");
        this.mName = sDeviceInfo.getAsString("name");
        this.mDeviceType = sDeviceInfo.getAsString("deviceType");
        this.mDeviceId = sDeviceInfo.getAsString("deviceId");
        this.mBrMac = sDeviceInfo.getAsString(BleConstants.KEY_BR_MAC);
        this.mPathPara = str2;
        sDeviceInfo.put(BleConstants.KEY_PATH, str2);
        H5proUtil.initH5pro();
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addCustomizeJsModule(BleConstants.BLE_HI_LINK, BleJsInteractionCompact.class);
        builder.addCustomizeJsModule("innerapi", InnerApi.class);
        builder.showStatusBar();
        builder.setImmerse();
        builder.setStatusBarTextBlack(true);
        builder.setForceDarkMode(1);
        if (needSingleTopLaunchMode(this.mDeviceType)) {
            builder.setActivityStartFlag(603979776);
        }
        if (!TextUtils.isEmpty(str2)) {
            builder.addPath(str2);
        }
        setDevicesUseTime(this.mUniqueId);
        if (cez.c()) {
            H5ProTestUtil.startH5ProTest(context, builder, str);
        } else {
            H5ProClient.startH5MiniProgram(context, str, builder.build());
        }
    }

    private void setDevicesUseTime(String str) {
        if (str != null) {
            new cke("deviceUsedTime").a(cpp.a(), str, System.currentTimeMillis());
        } else {
            LogUtil.h(TAG, "setDevicesUseTime uniqueId is null");
        }
    }

    private void checkLogin() {
        if (TextUtils.isEmpty(getSharedPreference(BleConstants.REGISTER_NUM))) {
            HealthAccessTokenUtil.getAtInstance().registerReceiverToGetAt();
            setSharedPreference(BleConstants.REGISTER_NUM, "1");
        }
    }

    private boolean needSingleTopLaunchMode(String str) {
        return SINGLE_TOP_MODE_TYPE.contains(str);
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onMount(Context context, H5ProInstance h5ProInstance) {
        LogUtil.a(TAG, "onMount", " BleJsInteractionCompact.this = ", this);
        this.mInstance = h5ProInstance;
        this.mContext = context;
        checkDarkMode();
        checkLogin();
        if (sDeviceInfo == null) {
            ContentValues contentValues = new ContentValues();
            sDeviceInfo = contentValues;
            contentValues.put("productId", this.mProductId);
            sDeviceInfo.put("uniqueId", this.mUniqueId);
            sDeviceInfo.put("name", this.mName);
            sDeviceInfo.put("deviceType", this.mDeviceType);
            sDeviceInfo.put("deviceId", this.mDeviceId);
            sDeviceInfo.put(BleConstants.KEY_BR_MAC, this.mBrMac);
            sDeviceInfo.put(BleConstants.KEY_PATH, this.mPathPara);
        }
        compactInit();
        if (CommonUtil.cg()) {
            createHpkInstalled();
        }
    }

    private void compactInit() {
        this.mProductId = sDeviceInfo.getAsString("productId");
        this.mBrMac = sDeviceInfo.getAsString(BleConstants.KEY_BR_MAC);
        this.mPathPara = sDeviceInfo.getAsString(BleConstants.KEY_PATH);
        BleOperatorCompactImpl bleOperatorCompactImpl = new BleOperatorCompactImpl(this.mContext, this.mInstance, sDeviceInfo, this.mProductId);
        this.mBleOperatorCompact = bleOperatorCompactImpl;
        this.mBleOperator = bleOperatorCompactImpl;
        if (TextUtils.isEmpty(this.mBrMac)) {
            return;
        }
        this.mBrOperatorCompact = new BrOperatorCompactImpl(this.mInstance, this.mProductId);
    }

    private void createHpkInstalled() {
        PluginBaseAdapter adapter = PluginOperation.getInstance(this.mContext).getAdapter();
        if (adapter instanceof PluginOperationAdapter) {
            PluginOperationAdapter pluginOperationAdapter = (PluginOperationAdapter) adapter;
            String productDir = pluginOperationAdapter.getProductDir();
            StringBuilder sb = new StringBuilder(16);
            sb.append(productDir);
            sb.append(File.separator);
            sb.append(this.mProductId);
            pluginOperationAdapter.createHpkInstalled(sb.toString(), this.mProductId);
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        jeg.d().d(strArr, iArr);
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onActivityResult(int i, int i2, Intent intent) {
        BleOperatorCompactImpl bleOperatorCompactImpl;
        LogUtil.a(TAG, "onActivityResult");
        if (i != 20002 || (bleOperatorCompactImpl = this.mBleOperatorCompact) == null) {
            return;
        }
        bleOperatorCompactImpl.handleScanCodeResult(i2, intent);
    }

    @JavascriptInterface
    public void unbindDevice(String str) {
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "js call unbindDevice start, function = ", CommonUtil.l(str));
        } else {
            LogUtil.a(TAG, "js call unbindDevice start, function = ", str);
        }
        BleOperatorCompactImpl bleOperatorCompactImpl = this.mBleOperatorCompact;
        if (bleOperatorCompactImpl != null) {
            bleOperatorCompactImpl.unbindDevice(str);
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js call unbindDevice end");
    }

    @JavascriptInterface
    public void downloadFile(String str, final String str2) {
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "downloadFile enter, callbackName = ", CommonUtil.l(str2));
        } else {
            LogUtil.a(TAG, "downloadFile enter, callbackName = ", str2);
        }
        try {
            JsDownloadBean jsDownloadBean = (JsDownloadBean) new Gson().fromJson(str, JsDownloadBean.class);
            if (jsDownloadBean == null) {
                LogUtil.h(TAG, "downloadFile jsDownloadBean1 is null");
                this.mBleOperatorCompact.downloadFile("", BleConstants.ERRCODE_COMMON_ERR, str2);
            } else {
                final double[] dArr = {0.0d};
                doDownloadFile(jsDownloadBean.getUrl(), jsDownloadBean.getFileName(), str2, new OnProgressCallback() { // from class: com.huawei.operation.h5pro.ble.BleJsInteractionCompact$$ExternalSyntheticLambda0
                    @Override // com.huawei.operation.h5pro.ble.BleJsInteractionCompact.OnProgressCallback
                    public final void onProgress(String str3, long j, long j2, boolean z) {
                        BleJsInteractionCompact.this.m696x7cdf800d(dArr, str2, str3, j, j2, z);
                    }
                });
            }
        } catch (JsonSyntaxException e) {
            LogUtil.b(TAG, "wrong json bean from h5, store fail:", e.getMessage());
            this.mBleOperatorCompact.downloadFile("", BleConstants.ERRCODE_COMMON_ERR, str2);
        }
    }

    /* renamed from: lambda$downloadFile$0$com-huawei-operation-h5pro-ble-BleJsInteractionCompact, reason: not valid java name */
    /* synthetic */ void m696x7cdf800d(double[] dArr, String str, String str2, long j, long j2, boolean z) {
        if (j2 != 0 && j2 != -1) {
            double doubleValue = new BigDecimal(j / j2).setScale(1, 1).doubleValue();
            if (CommonUtil.c(doubleValue - dArr[0])) {
                dArr[0] = dArr[0] + 0.1d;
                this.mBleOperatorCompact.downloadFile(String.valueOf(doubleValue), BleConstants.DOWNLOAD_IN_PROGRESS, str);
                return;
            }
            return;
        }
        this.mBleOperatorCompact.downloadFile(str2, BleConstants.ERRCODE_COMMON_ERR, str);
    }

    @JavascriptInterface
    public void downloadFile(String str, String str2, String str3) {
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "downloadFile enter, fileName = ", CommonUtil.l(str2), ", callbackName = ", CommonUtil.l(str3));
        } else {
            LogUtil.a(TAG, "downloadFile enter, fileName = ", str2, ", callbackName = ", str3);
        }
        doDownloadFile(str, str2, str3, null);
    }

    private void doDownloadFile(String str, String str2, String str3, OnProgressCallback onProgressCallback) {
        if (checkBleOperatorCompact()) {
            return;
        }
        if (TextUtils.isEmpty(str) || !str.startsWith(ProxyConfig.MATCH_HTTPS)) {
            this.mBleOperatorCompact.downloadFile("", BleConstants.ERRCODE_COMMON_ERR, str3);
        }
        String b = mol.b(str + str2);
        if (TextUtils.isEmpty(b)) {
            this.mBleOperatorCompact.downloadFile("", BleConstants.ERRCODE_COMMON_ERR, str3);
            return;
        }
        File file = new File(BaseApplication.getContext().getCacheDir(), b);
        if (file.exists() && file.length() > 0) {
            this.mBleOperatorCompact.downloadFile(b, "0", str3);
            return;
        }
        if (this.mHandler == null) {
            this.mHandler = new Handler(Looper.myLooper());
        }
        lqi.d().d(new lqg(str, null, file, new AnonymousClass1(b, str3, str, onProgressCallback)));
    }

    /* renamed from: com.huawei.operation.h5pro.ble.BleJsInteractionCompact$1, reason: invalid class name */
    class AnonymousClass1 implements ProgressListener<File> {
        final /* synthetic */ String val$callbackName;
        final /* synthetic */ String val$name;
        final /* synthetic */ OnProgressCallback val$onProgress;
        final /* synthetic */ String val$url;

        AnonymousClass1(String str, String str2, String str3, OnProgressCallback onProgressCallback) {
            this.val$name = str;
            this.val$callbackName = str2;
            this.val$url = str3;
            this.val$onProgress = onProgressCallback;
        }

        @Override // com.huawei.networkclient.ProgressListener
        public void onFinish(File file) {
            LogUtil.a(BleJsInteractionCompact.TAG, "onFinish, data = ", file);
            if (BleJsInteractionCompact.this.checkBleOperatorCompact()) {
                return;
            }
            Handler handler = BleJsInteractionCompact.this.mHandler;
            final String str = this.val$name;
            final String str2 = this.val$callbackName;
            handler.post(new Runnable() { // from class: com.huawei.operation.h5pro.ble.BleJsInteractionCompact$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    BleJsInteractionCompact.AnonymousClass1.this.m706x712cf4ec(str, str2);
                }
            });
            BleJsBiOperate bleJsBiOperate = new BleJsBiOperate();
            bleJsBiOperate.init();
            bleJsBiOperate.tickBiOtaDownloadComplete(BleJsInteractionCompact.this.mProductId, BleJsInteractionCompact.this.mUniqueId, this.val$url);
        }

        /* renamed from: lambda$onFinish$0$com-huawei-operation-h5pro-ble-BleJsInteractionCompact$1, reason: not valid java name */
        /* synthetic */ void m706x712cf4ec(String str, String str2) {
            BleJsInteractionCompact.this.mBleOperatorCompact.downloadFile(str, "0", str2);
        }

        @Override // com.huawei.networkclient.ProgressListener
        public void onProgress(long j, long j2, boolean z) {
            OnProgressCallback onProgressCallback = this.val$onProgress;
            if (onProgressCallback != null) {
                onProgressCallback.onProgress(this.val$name, j, j2, z);
            }
        }

        @Override // com.huawei.networkclient.ProgressListener
        public void onFail(Throwable th) {
            LogUtil.a(BleJsInteractionCompact.TAG, "onFail, throwable.getMessage() = ", th.getMessage());
            if (BleJsInteractionCompact.this.checkBleOperatorCompact()) {
                return;
            }
            Handler handler = BleJsInteractionCompact.this.mHandler;
            final String str = this.val$name;
            final String str2 = this.val$callbackName;
            handler.post(new Runnable() { // from class: com.huawei.operation.h5pro.ble.BleJsInteractionCompact$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    BleJsInteractionCompact.AnonymousClass1.this.m705x667fffa0(str, str2);
                }
            });
        }

        /* renamed from: lambda$onFail$1$com-huawei-operation-h5pro-ble-BleJsInteractionCompact$1, reason: not valid java name */
        /* synthetic */ void m705x667fffa0(String str, String str2) {
            BleJsInteractionCompact.this.mBleOperatorCompact.downloadFile(str, BleJsInteractionCompact.ERR_CODE_DOWNLOAD_FAIL, str2);
        }
    }

    @JavascriptInterface
    public void readLocalFile(String str, String str2) {
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "readLocalFile enter, callbackName = ", CommonUtil.l(str2));
        } else {
            LogUtil.a(TAG, "readLocalFile enter, callbackName = ", str2);
        }
        if (checkBleOperatorCompact()) {
            return;
        }
        try {
            JsReadFileBean jsReadFileBean = (JsReadFileBean) new Gson().fromJson(str, JsReadFileBean.class);
            if (jsReadFileBean == null) {
                LogUtil.h(TAG, "readLocalFile jsDownloadBean1 is null");
                this.mBleOperatorCompact.downloadFile("", BleConstants.ERRCODE_COMMON_ERR, str2);
            } else {
                readFile(jsReadFileBean.getFileName(), jsReadFileBean.getStartIndex().longValue(), jsReadFileBean.getEndIndex().longValue(), str2);
            }
        } catch (JsonSyntaxException e) {
            LogUtil.b(TAG, "wrong json bean from h5, store fail:", e.getMessage());
            this.mBleOperatorCompact.downloadFile("", BleConstants.ERRCODE_COMMON_ERR, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkBleOperatorCompact() {
        Object[] objArr = new Object[2];
        objArr[0] = "bleOperator is null ? ";
        objArr[1] = Boolean.valueOf(this.mBleOperatorCompact == null);
        LogUtil.h(TAG, objArr);
        return this.mBleOperatorCompact == null;
    }

    private void readFile(String str, final long j, final long j2, final String str2) {
        final File file = new File(BaseApplication.getContext().getCacheDir(), str);
        try {
            if (!sqd.d(file, BaseApplication.getContext().getCacheDir().getCanonicalPath())) {
                LogUtil.h(TAG, "readFile path is not valid");
                return;
            }
            if (this.mHandler == null) {
                this.mHandler = new Handler(Looper.myLooper());
            }
            jdx.b(new Runnable() { // from class: com.huawei.operation.h5pro.ble.BleJsInteractionCompact$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    BleJsInteractionCompact.this.m704x9393debb(file, j, j2, str2);
                }
            });
        } catch (IOException e) {
            LogUtil.b(TAG, "readFile", e.toString());
        }
    }

    /* renamed from: lambda$readFile$4$com-huawei-operation-h5pro-ble-BleJsInteractionCompact, reason: not valid java name */
    /* synthetic */ void m704x9393debb(File file, long j, long j2, final String str) {
        RandomAccessFile randomAccessFile;
        Throwable th;
        final String b;
        try {
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "r");
            try {
                long length = randomAccessFile2.length();
                try {
                    if (!checkFile(file, j, j2, length)) {
                        try {
                            this.mHandler.post(new Runnable() { // from class: com.huawei.operation.h5pro.ble.BleJsInteractionCompact$$ExternalSyntheticLambda4
                                @Override // java.lang.Runnable
                                public final void run() {
                                    BleJsInteractionCompact.this.m701x8ff0e61e(str);
                                }
                            });
                            randomAccessFile2.close();
                            return;
                        } catch (Throwable th2) {
                            th = th2;
                            randomAccessFile = randomAccessFile2;
                            try {
                                randomAccessFile.close();
                                throw th;
                            } catch (Throwable th3) {
                                th.addSuppressed(th3);
                                throw th;
                            }
                        }
                    }
                    long min = Math.min(j2, length);
                    randomAccessFile = randomAccessFile2;
                    try {
                        randomAccessFile.seek(j);
                        int min2 = (int) Math.min(j2 - j, 3145728L);
                        LogUtil.a(TAG, "bufferLength ", Integer.valueOf(min2));
                        byte[] bArr = new byte[min2];
                        while (randomAccessFile.getFilePointer() < min) {
                            if (randomAccessFile.getFilePointer() + min2 > min) {
                                byte[] bArr2 = new byte[(int) (min - randomAccessFile.getFilePointer())];
                                b = HEXUtils.b(bArr2, randomAccessFile.read(bArr2));
                            } else {
                                b = HEXUtils.b(bArr, randomAccessFile.read(bArr));
                            }
                            this.mHandler.post(new Runnable() { // from class: com.huawei.operation.h5pro.ble.BleJsInteractionCompact$$ExternalSyntheticLambda5
                                @Override // java.lang.Runnable
                                public final void run() {
                                    BleJsInteractionCompact.this.m702x912738fd(b, str);
                                }
                            });
                        }
                        if (min == length) {
                            file.delete();
                        }
                        randomAccessFile.close();
                    } catch (Throwable th4) {
                        th = th4;
                        th = th;
                        randomAccessFile.close();
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    randomAccessFile = randomAccessFile2;
                }
            } catch (Throwable th6) {
                th = th6;
                randomAccessFile = randomAccessFile2;
            }
        } catch (IOException unused) {
            LogUtil.b(TAG, "readFile exception");
            this.mHandler.post(new Runnable() { // from class: com.huawei.operation.h5pro.ble.BleJsInteractionCompact$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    BleJsInteractionCompact.this.m703x925d8bdc(str);
                }
            });
        }
    }

    /* renamed from: lambda$readFile$1$com-huawei-operation-h5pro-ble-BleJsInteractionCompact, reason: not valid java name */
    /* synthetic */ void m701x8ff0e61e(String str) {
        this.mBleOperatorCompact.readFileSync("", String.valueOf(2), str);
    }

    /* renamed from: lambda$readFile$2$com-huawei-operation-h5pro-ble-BleJsInteractionCompact, reason: not valid java name */
    /* synthetic */ void m702x912738fd(String str, String str2) {
        BleOperatorCompactImpl bleOperatorCompactImpl = this.mBleOperatorCompact;
        if (bleOperatorCompactImpl != null) {
            bleOperatorCompactImpl.readFileSync(str, String.valueOf(0), str2);
        }
    }

    /* renamed from: lambda$readFile$3$com-huawei-operation-h5pro-ble-BleJsInteractionCompact, reason: not valid java name */
    /* synthetic */ void m703x925d8bdc(String str) {
        this.mBleOperatorCompact.readFileSync("", BleConstants.ERRCODE_COMMON_ERR, str);
    }

    @JavascriptInterface
    public void getStorageSync(String str, final String str2) {
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "getStorageSync enter, callbackName = ", CommonUtil.l(str2));
        } else {
            LogUtil.a(TAG, "getStorageSync enter, callbackName = ", str2);
        }
        if (checkBleOperatorCompact()) {
            return;
        }
        final File file = new File(BaseApplication.getContext().getCacheDir(), str);
        try {
            if (!sqd.d(file, BaseApplication.getContext().getCacheDir().getCanonicalPath())) {
                LogUtil.h(TAG, "getStorageSync path is not valid");
                return;
            }
            if (this.mHandler == null) {
                this.mHandler = new Handler(Looper.myLooper());
            }
            jdx.b(new Runnable() { // from class: com.huawei.operation.h5pro.ble.BleJsInteractionCompact$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    BleJsInteractionCompact.this.m700x35fe8569(file, str2);
                }
            });
        } catch (IOException e) {
            LogUtil.b(TAG, "getStorageSync", e.toString());
        }
    }

    /* renamed from: lambda$getStorageSync$8$com-huawei-operation-h5pro-ble-BleJsInteractionCompact, reason: not valid java name */
    /* synthetic */ void m700x35fe8569(File file, final String str) {
        try {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    if (file.exists() && file.length() > 0) {
                        byte[] bArr = new byte[BUFFER_SIZE];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read <= -1) {
                                break;
                            }
                            final String b = HEXUtils.b(bArr, read);
                            this.mHandler.post(new Runnable() { // from class: com.huawei.operation.h5pro.ble.BleJsInteractionCompact$$ExternalSyntheticLambda1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    BleJsInteractionCompact.this.m697x325b8ccc(b, str);
                                }
                            });
                        }
                    }
                    fileInputStream.close();
                    file.delete();
                    this.mHandler.post(new Runnable() { // from class: com.huawei.operation.h5pro.ble.BleJsInteractionCompact$$ExternalSyntheticLambda3
                        @Override // java.lang.Runnable
                        public final void run() {
                            BleJsInteractionCompact.this.m699x34c8328a(str);
                        }
                    });
                } catch (Throwable th) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (IOException unused) {
                LogUtil.b(TAG, "getStorageSync exception");
                this.mHandler.post(new Runnable() { // from class: com.huawei.operation.h5pro.ble.BleJsInteractionCompact$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        BleJsInteractionCompact.this.m698x3391dfab(str);
                    }
                });
                file.delete();
            }
        } catch (Throwable th3) {
            file.delete();
            throw th3;
        }
    }

    /* renamed from: lambda$getStorageSync$5$com-huawei-operation-h5pro-ble-BleJsInteractionCompact, reason: not valid java name */
    /* synthetic */ void m697x325b8ccc(String str, String str2) {
        this.mBleOperatorCompact.getStorageSync(str, 0, str2);
    }

    /* renamed from: lambda$getStorageSync$6$com-huawei-operation-h5pro-ble-BleJsInteractionCompact, reason: not valid java name */
    /* synthetic */ void m698x3391dfab(String str) {
        this.mBleOperatorCompact.getStorageSync("", -1, str);
    }

    /* renamed from: lambda$getStorageSync$7$com-huawei-operation-h5pro-ble-BleJsInteractionCompact, reason: not valid java name */
    /* synthetic */ void m699x34c8328a(String str) {
        this.mBleOperatorCompact.getStorageSync("", 0, str);
    }

    @JavascriptInterface
    public void getBannerInfo(int i, String str) {
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "js call getBoundDeviceInfo start, positionId = ", Integer.valueOf(i), ", function = ", CommonUtil.l(str));
        } else {
            LogUtil.a(TAG, "js call getBoundDeviceInfo start, positionId = ", Integer.valueOf(i), ", function = ", str);
        }
        new MassageGun(this.mContext, this.mBleOperatorCompact, this.mInstance, sDeviceInfo, this.mProductId).getBanner(i, str);
    }

    @JavascriptInterface
    public void requestHeartRateInterval(String str, String str2) {
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "requestHeartRateInterval type ", str, " function = ", CommonUtil.l(str2));
        } else {
            LogUtil.a(TAG, "requestHeartRateInterval enter, function = ", str2);
        }
        new LuminousRope(this.mBleOperatorCompact).requestHeartRateInterval(str, str2);
    }

    @JavascriptInterface
    public void getBoundDeviceInfo(String str, String str2) {
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "js call getBoundDeviceInfo start, function = ", str2, ", productId = ", CommonUtil.l(str));
        } else {
            LogUtil.a(TAG, "js call getBoundDeviceInfo start, function = ", str2, ", productId = ", str);
        }
        BleOperatorCompactImpl bleOperatorCompactImpl = this.mBleOperatorCompact;
        if (bleOperatorCompactImpl != null) {
            bleOperatorCompactImpl.getBoundDeviceInfo(str, str2);
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js call getBoundDeviceInfo end");
    }

    @JavascriptInterface
    public void gotoNative(String str) {
        ReleaseLogUtil.e(RELEASE_TAG, "gotoNative enter");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "gotoNative uri is empty");
            return;
        }
        Intent intent = new Intent();
        intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
        intent.setData(Uri.parse(str));
        gxf.d().startActivity(intent);
    }

    @JavascriptInterface
    public void getCourseRecommend(String str, String str2) {
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "js call getCourseRecommend start, function = ", CommonUtil.l(str2));
        } else {
            LogUtil.a(TAG, "js call getCourseRecommend start, function = ", str2);
        }
        new MassageGun(this.mContext, this.mBleOperatorCompact, this.mInstance, sDeviceInfo, this.mProductId).getCourseRecommend(str, str2);
    }

    @JavascriptInterface
    public void getUserId(String str) {
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "getUserId enter, function = ", CommonUtil.l(str));
        } else {
            LogUtil.a(TAG, "getUserId enter, function = ", str);
        }
        String accountInfo = LoginInit.getInstance(this.mContext).getAccountInfo(1011);
        BleOperatorCompactImpl bleOperatorCompactImpl = this.mBleOperatorCompact;
        boolean isEmpty = TextUtils.isEmpty(accountInfo);
        bleOperatorCompactImpl.callBackJsResult(String.valueOf(isEmpty ? 1 : 0), str, mol.b(accountInfo));
    }

    @JavascriptInterface
    public void setCallBackBrReceiveData(String str, String str2) {
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "js call setCallBackBrReceivedData start, function = ", CommonUtil.l(str2));
        } else {
            LogUtil.a(TAG, "js call setCallBackBrReceivedData start, function = ", str2);
        }
        BrOperatorCompactImpl brOperatorCompactImpl = this.mBrOperatorCompact;
        if (brOperatorCompactImpl != null) {
            brOperatorCompactImpl.onBrReceivedData(str, str2, BrOperatorCompactImpl.ON_BRRECEIVED_DATA);
        }
        LogUtil.a(TAG, "js call setCallBackBrReceivedData end");
    }

    @JavascriptInterface
    public void createBrConnection(String str, String str2) {
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "js call createBrConnection start, function = ", CommonUtil.l(str2));
        } else {
            LogUtil.a(TAG, "js call createBrConnection start, function = ", str2);
        }
        BrOperatorCompactImpl brOperatorCompactImpl = this.mBrOperatorCompact;
        if (brOperatorCompactImpl != null) {
            brOperatorCompactImpl.connect(str, str2);
        }
        LogUtil.a(TAG, "js call createBrConnection end");
    }

    @JavascriptInterface
    public String closeBrConnection(String str) {
        ReleaseLogUtil.e(RELEASE_TAG, "js call closeBrConnection start");
        BrOperatorCompactImpl brOperatorCompactImpl = this.mBrOperatorCompact;
        if (brOperatorCompactImpl != null) {
            return brOperatorCompactImpl.closeBrConnect(str);
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js call closeBrConnection end");
        return String.valueOf(2);
    }

    @JavascriptInterface
    public void brSendData(String str, String str2) {
        if (CommonUtil.bv()) {
            ReleaseLogUtil.e(RELEASE_TAG, "js call brSendMsg start, function = ", CommonUtil.l(str2));
        } else {
            LogUtil.a(TAG, "js call brSendMsg start, function = ", str2);
        }
        BrOperatorCompactImpl brOperatorCompactImpl = this.mBrOperatorCompact;
        if (brOperatorCompactImpl != null) {
            brOperatorCompactImpl.sendMsg(str, str2);
        }
        ReleaseLogUtil.e(RELEASE_TAG, "js call brSendMsg end");
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        LogUtil.a(TAG, "onDestroy");
        if (!TextUtils.isEmpty(this.mPathPara) && this.mPathPara.contains("#/?type=signInPage")) {
            ObserverManagerUtil.c("H5_PAGE_EXIT", 1);
        }
        saveInfoToSharedPreference();
        BleOperatorCompactImpl bleOperatorCompactImpl = this.mBleOperatorCompact;
        if (bleOperatorCompactImpl != null) {
            bleOperatorCompactImpl.stopBleState();
            this.mBleOperatorCompact = null;
        }
        ContentValues contentValues = sDeviceInfo;
        if (contentValues != null) {
            contentValues.clear();
            sDeviceInfo = null;
        }
        BrOperatorCompactImpl brOperatorCompactImpl = this.mBrOperatorCompact;
        if (brOperatorCompactImpl != null) {
            brOperatorCompactImpl.releaseResource();
            this.mBrOperatorCompact = null;
        }
        this.mBleOperator = null;
        this.mContext = null;
        this.mInstance = null;
    }

    private void checkDarkMode() {
        String sharedPreference = getSharedPreference(BleConstants.DARK_MODE);
        if (TextUtils.isEmpty(sharedPreference) || sharedPreference.equals(String.valueOf(nrt.a(this.mContext)))) {
            return;
        }
        LogUtil.a(TAG, "The pattern is different");
        initInfo();
    }

    private boolean checkFile(File file, long j, long j2, long j3) {
        return file.exists() && file.length() > 0 && j <= j3 && j >= 0 && j2 >= 0 && j2 >= j;
    }

    private void saveInfoToSharedPreference() {
        setSharedPreference("productId", this.mProductId);
        setSharedPreferenceEncryption("uniqueId", this.mUniqueId);
        setSharedPreference("deviceName", this.mName);
        setSharedPreference("deviceType", this.mDeviceType);
        setSharedPreference("deviceId", this.mDeviceId);
        setSharedPreference(BleConstants.DARK_MODE, String.valueOf(nrt.a(this.mContext)));
        setSharedPreference(BleConstants.KEY_BR_MAC, this.mBrMac);
        LogUtil.a(TAG, "saveInfoToSharedPreference end");
    }

    private void initInfo() {
        if (TextUtils.isEmpty(this.mProductId)) {
            this.mProductId = getSharedPreference("productId");
        }
        if (TextUtils.isEmpty(this.mUniqueId)) {
            this.mUniqueId = getSharedPreference("uniqueId");
        }
        if (TextUtils.isEmpty(this.mName)) {
            this.mName = getSharedPreference("name");
        }
        if (TextUtils.isEmpty(this.mDeviceType)) {
            this.mDeviceType = getSharedPreference("deviceType");
        }
        if (TextUtils.isEmpty(this.mDeviceId)) {
            this.mDeviceId = getSharedPreference("deviceId");
        }
        if (TextUtils.isEmpty(this.mBrMac)) {
            this.mBrMac = getSharedPreference(BleConstants.KEY_BR_MAC);
        }
    }

    private void setSharedPreference(String str, String str2) {
        SharedPreferenceManager.e(this.mContext, String.valueOf(10000), str, str2, new StorageParams(0));
    }

    private void setSharedPreferenceEncryption(String str, String str2) {
        SharedPreferenceManager.e(this.mContext, String.valueOf(10000), str, str2, new StorageParams(1));
    }

    private String getSharedPreference(String str) {
        return SharedPreferenceManager.b(this.mContext, String.valueOf(10000), str);
    }
}
