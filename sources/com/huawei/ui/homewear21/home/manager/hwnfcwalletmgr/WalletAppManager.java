package com.huawei.ui.homewear21.home.manager.hwnfcwalletmgr;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.h5pro.jsmodules.device.BasePairManagerJsApi;
import com.huawei.operation.h5pro.jsmodules.device.DeviceManagerService;
import com.huawei.ui.homewear21.home.manager.hwnfcwalletmgr.CloudWalletApplicationConfig;
import com.huawei.wear.oversea.overseamanger.OverSeaMangerUtil;
import com.huawei.wear.oversea.overseamanger.OverSeaQueryCallBack;
import defpackage.bzs;
import defpackage.cun;
import defpackage.knl;
import defpackage.koq;
import defpackage.ocp;
import defpackage.pep;
import defpackage.sqd;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes.dex */
public class WalletAppManager {
    private static final String ANDROID_FLAG = "android";
    private static final String BASE64_IMG_PREFIX = "data:image/png;base64,";
    private static final String H5_PATH = "#/wallet";
    private static final String HUAWEI_WALLET_ICON = "wallet_huawei_wallet";
    private static final String HUAWEI_WALLET_NAME = "HUAWEI Wallet";
    private static final String ICON_LOCAL_RESOURCE_SUFFIX = ".png";
    private static final String SUPPORT_WALLET_CACHE_KEY_PREFIX = "support_wallet";
    private static final String TAG = "NfcWalletManager";
    private static final String TAG_RELEASE = "R_NfcWalletManager";
    private static final String WALLET_APPLICATION_MODULE_ID = "wallet_application";
    private static final String WALLET_CONFIG_JSON_NAME = "wallet_app.json";
    private static final String WALLET_DEFAULT_ICON_NAME = "default_wallet_app.png";
    private static final String WALLET_HUAWEI_ICON_NAME = "oversea_wallet_huawei_icon.png";
    private static final String WALLET_ICON_DIR_NAME = "wallet_icon";
    private static final String WALLET_ROOT_DIR_NAME = "walletApplication";
    private String mWalletIconDir;
    private String mWalletJsonFilePath;
    private String mWalletManagerRootDir;

    /* loaded from: classes6.dex */
    static class SingletonHolder {
        private static final WalletAppManager INSTANCE = new WalletAppManager();

        private SingletonHolder() {
        }
    }

    public static WalletAppManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private WalletAppManager() {
        try {
            this.mWalletManagerRootDir = BaseApplication.getContext().getFilesDir().getCanonicalPath() + File.separator + WALLET_ROOT_DIR_NAME;
            this.mWalletIconDir = this.mWalletManagerRootDir + File.separator + WALLET_ICON_DIR_NAME;
            this.mWalletJsonFilePath = this.mWalletManagerRootDir + File.separator + WALLET_CONFIG_JSON_NAME;
            FileUtils.a(new File(this.mWalletManagerRootDir));
        } catch (IOException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "NfcWalletManager mWalletManagerRootDir IOException:", ExceptionUtils.d(e));
        }
    }

    public void startNfcWalletH5() {
        ReleaseLogUtil.e(TAG_RELEASE, "startNfcWalletH5 enter");
        if (cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, TAG_RELEASE) == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "startNfcWalletH5 device not connected");
            return;
        }
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        bzs e = bzs.e();
        builder.addPath(H5_PATH);
        builder.addCustomizeJsModule("innerapi", e.getCommonJsModule("innerapi"));
        builder.addCustomizeJsModule("device", e.getCommonJsModule("device"));
        builder.addCustomizeJsModule("BasePairManager", BasePairManagerJsApi.class);
        builder.setImmerse().showStatusBar();
        builder.setForceDarkMode(1);
        e.initH5Pro();
        H5ProClient.getServiceManager().registerService(WalletAppJs.class);
        H5ProClient.getServiceManager().registerService(DeviceManagerService.class);
        H5ProClient.startH5MiniProgram(BaseApplication.getContext(), "com.huawei.health.h5.dm-ccbk", builder.build());
    }

    public void startHuaweiWalletActivity() {
        ReleaseLogUtil.e(TAG_RELEASE, "startHuaweiWalletActivity enter");
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, TAG_RELEASE);
        Context context = BaseApplication.getContext();
        pep.b(context);
        pep.c(context, deviceInfo.getDeviceIdentify());
    }

    public void getWalletApplicationList(final ResponseCallback<List<WalletApplication>> responseCallback) {
        ReleaseLogUtil.e(TAG_RELEASE, "getWalletApplicationList enter");
        if (responseCallback != null) {
            DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, TAG_RELEASE);
            if (deviceInfo == null) {
                ReleaseLogUtil.d(TAG_RELEASE, "getWalletApplicationList device info is null");
                responseCallback.onResponse(-1, null);
                return;
            } else {
                getValidWalletApplication(deviceInfo.getDeviceIdentify(), new ResponseCallback() { // from class: com.huawei.ui.homewear21.home.manager.hwnfcwalletmgr.WalletAppManager$$ExternalSyntheticLambda1
                    @Override // com.huawei.hwbasemgr.ResponseCallback
                    public final void onResponse(int i, Object obj) {
                        WalletAppManager.this.m800xbe81bed7(responseCallback, i, (List) obj);
                    }
                });
                return;
            }
        }
        ReleaseLogUtil.d(TAG_RELEASE, "getWalletApplicationList callback is null");
    }

    /* renamed from: lambda$getWalletApplicationList$1$com-huawei-ui-homewear21-home-manager-hwnfcwalletmgr-WalletAppManager, reason: not valid java name */
    /* synthetic */ void m800xbe81bed7(final ResponseCallback responseCallback, int i, final List list) {
        if (koq.b(list)) {
            ReleaseLogUtil.d(TAG_RELEASE, "getWalletApplicationList support wallet is empty");
            responseCallback.onResponse(-1, list);
        } else {
            WalletAppCloudService.getInstance().downloadIconResources(this.mWalletManagerRootDir, new Runnable() { // from class: com.huawei.ui.homewear21.home.manager.hwnfcwalletmgr.WalletAppManager$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    WalletAppManager.this.m799xdb560b96(list, responseCallback);
                }
            });
        }
    }

    /* renamed from: lambda$getWalletApplicationList$0$com-huawei-ui-homewear21-home-manager-hwnfcwalletmgr-WalletAppManager, reason: not valid java name */
    /* synthetic */ void m799xdb560b96(List list, ResponseCallback responseCallback) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            WalletApplication walletApplication = (WalletApplication) it.next();
            walletApplication.getIcon();
            walletApplication.setIcon(getIconBase64(walletApplication));
        }
        ReleaseLogUtil.e(TAG_RELEASE, "getWalletApplicationList response");
        responseCallback.onResponse(0, list);
    }

    public void getValidWalletApplication(String str, final ResponseCallback<List<WalletApplication>> responseCallback) {
        ReleaseLogUtil.e(TAG_RELEASE, "getValidWalletApplication enter");
        HwGetDevicesParameter hwGetDevicesParameter = new HwGetDevicesParameter();
        hwGetDevicesParameter.setDeviceIdentify(str);
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.IDENTIFY_DEVICES, hwGetDevicesParameter, TAG);
        if (koq.b(deviceList)) {
            ReleaseLogUtil.d(TAG_RELEASE, "getValidWalletApplication device info is empty");
            responseCallback.onResponse(-1, null);
        } else {
            final DeviceInfo deviceInfo = deviceList.get(0);
            final CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
            ThreadPoolManager.a(1, 1).d(TAG_RELEASE, new Runnable() { // from class: com.huawei.ui.homewear21.home.manager.hwnfcwalletmgr.WalletAppManager$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    WalletAppManager.this.m798x2b0b692(deviceInfo, copyOnWriteArrayList, responseCallback);
                }
            });
        }
    }

    /* renamed from: lambda$getValidWalletApplication$4$com-huawei-ui-homewear21-home-manager-hwnfcwalletmgr-WalletAppManager, reason: not valid java name */
    /* synthetic */ void m798x2b0b692(DeviceInfo deviceInfo, final List list, ResponseCallback responseCallback) {
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        isSupportOverseaHuaweiWallet(deviceInfo, new ResponseCallback() { // from class: com.huawei.ui.homewear21.home.manager.hwnfcwalletmgr.WalletAppManager$$ExternalSyntheticLambda4
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                WalletAppManager.lambda$getValidWalletApplication$2(list, countDownLatch, i, (Boolean) obj);
            }
        });
        getValidThirdPartWallet(deviceInfo, new ResponseCallback() { // from class: com.huawei.ui.homewear21.home.manager.hwnfcwalletmgr.WalletAppManager$$ExternalSyntheticLambda5
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                WalletAppManager.lambda$getValidWalletApplication$3(list, countDownLatch, i, (List) obj);
            }
        });
        try {
            countDownLatch.await();
            ReleaseLogUtil.e(TAG_RELEASE, "getValidWalletApplication size:", Integer.valueOf(list.size()));
            responseCallback.onResponse(0, list);
        } catch (InterruptedException unused) {
            responseCallback.onResponse(-1, list);
        }
    }

    static /* synthetic */ void lambda$getValidWalletApplication$2(List list, CountDownLatch countDownLatch, int i, Boolean bool) {
        if (bool.booleanValue()) {
            list.add(0, new WalletApplication(HUAWEI_WALLET_NAME, HUAWEI_WALLET_ICON));
        }
        countDownLatch.countDown();
    }

    static /* synthetic */ void lambda$getValidWalletApplication$3(List list, CountDownLatch countDownLatch, int i, List list2) {
        if (koq.c(list2)) {
            list.addAll(list2);
        }
        countDownLatch.countDown();
    }

    public boolean isOverseaService() {
        return !Objects.equals(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1014), "CN");
    }

    public boolean getIsSupportWalletApp(String str) {
        String a2 = knl.a(SUPPORT_WALLET_CACHE_KEY_PREFIX + str);
        boolean a3 = SharedPreferenceManager.a(WALLET_APPLICATION_MODULE_ID, a2, false);
        ReleaseLogUtil.e(TAG_RELEASE, "getIsSupportWalletApp ", a2, " support:", Boolean.valueOf(a3));
        return a3;
    }

    public void setIsSupportWalletApp(String str, boolean z) {
        String a2 = knl.a(SUPPORT_WALLET_CACHE_KEY_PREFIX + str);
        SharedPreferenceManager.e(WALLET_APPLICATION_MODULE_ID, a2, z);
        ReleaseLogUtil.e(TAG_RELEASE, "setIsSupportWalletApp ", a2, " support:", Boolean.valueOf(z));
    }

    private void isSupportOverseaHuaweiWallet(DeviceInfo deviceInfo, final ResponseCallback<Boolean> responseCallback) {
        if (!ocp.b(deviceInfo.getDeviceIdentify())) {
            responseCallback.onResponse(0, false);
        } else {
            OverSeaMangerUtil.c(BaseApplication.getContext()).a(ocp.a(deviceInfo), new OverSeaQueryCallBack() { // from class: com.huawei.ui.homewear21.home.manager.hwnfcwalletmgr.WalletAppManager.1
                @Override // com.huawei.wear.oversea.overseamanger.OverSeaQueryCallBack
                public int onSuccess(int i) {
                    ReleaseLogUtil.e(WalletAppManager.TAG_RELEASE, "getHuaweiWallet onSuccess");
                    responseCallback.onResponse(0, true);
                    return i;
                }

                @Override // com.huawei.wear.oversea.overseamanger.OverSeaQueryCallBack
                public int onFail(int i) {
                    ReleaseLogUtil.e(WalletAppManager.TAG_RELEASE, "getHuaweiWallet onFail returnCode is:", Integer.valueOf(i));
                    responseCallback.onResponse(-1, false);
                    return i;
                }
            });
        }
    }

    private void getValidThirdPartWallet(DeviceInfo deviceInfo, final ResponseCallback<List<WalletApplication>> responseCallback) {
        final String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1014);
        ReleaseLogUtil.e(TAG_RELEASE, "getValidThirdPartWallet enter: ", accountInfo);
        final String deviceModel = deviceInfo.getDeviceModel();
        final ArrayList arrayList = new ArrayList();
        WalletAppCloudService.getInstance().downloadWalletAppListConfig(this.mWalletJsonFilePath, new ResponseCallback() { // from class: com.huawei.ui.homewear21.home.manager.hwnfcwalletmgr.WalletAppManager$$ExternalSyntheticLambda2
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                WalletAppManager.this.m797xa9e4b40f(responseCallback, arrayList, accountInfo, deviceModel, i, (CloudWalletApplicationConfig) obj);
            }
        });
    }

    /* renamed from: lambda$getValidThirdPartWallet$5$com-huawei-ui-homewear21-home-manager-hwnfcwalletmgr-WalletAppManager, reason: not valid java name */
    /* synthetic */ void m797xa9e4b40f(ResponseCallback responseCallback, List list, String str, String str2, int i, CloudWalletApplicationConfig cloudWalletApplicationConfig) {
        if (i != 0 || cloudWalletApplicationConfig == null) {
            ReleaseLogUtil.c(TAG_RELEASE, "getWalletApplicationList error:", Integer.valueOf(i));
            responseCallback.onResponse(-1, list);
            return;
        }
        List<CloudWalletApplicationConfig.WalletApplicationCountryConfig> walletConfig = cloudWalletApplicationConfig.getWalletConfig();
        if (koq.b(walletConfig)) {
            ReleaseLogUtil.e(TAG_RELEASE, "getWalletApplicationList app country config is empty");
            responseCallback.onResponse(-1, list);
            return;
        }
        ReleaseLogUtil.e(TAG_RELEASE, "getValidThirdPartWallet handle config");
        Iterator<CloudWalletApplicationConfig.WalletApplicationCountryConfig> it = walletConfig.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            CloudWalletApplicationConfig.WalletApplicationCountryConfig next = it.next();
            if (next.getCountryCode().equalsIgnoreCase(str)) {
                ReleaseLogUtil.e(TAG_RELEASE, "getWalletApplicationList country found");
                List<CloudWalletApplicationConfig.WalletApplicationConfig> applications = next.getApplications();
                if (koq.b(applications)) {
                    ReleaseLogUtil.d(TAG_RELEASE, "getWalletApplicationList applications is empty");
                    break;
                }
                for (CloudWalletApplicationConfig.WalletApplicationConfig walletApplicationConfig : applications) {
                    List<String> platforms = walletApplicationConfig.getPlatforms();
                    List<String> deviceModels = walletApplicationConfig.getDeviceModels();
                    if (koq.b(platforms) || koq.b(deviceModels)) {
                        ReleaseLogUtil.d(TAG_RELEASE, "getWalletApplicationList platforms or device models is empty");
                    } else if (containsIgnoreCase(platforms, "android") && containsIgnoreCase(deviceModels, str2)) {
                        ReleaseLogUtil.e(TAG_RELEASE, "getWalletApplicationList found:", walletApplicationConfig.getName());
                        list.add(new WalletApplication(walletApplicationConfig.getName(), walletApplicationConfig.getIcon(), walletApplicationConfig.getDeeplink()));
                    }
                }
            }
        }
        responseCallback.onResponse(0, list);
    }

    private String getIconBase64(WalletApplication walletApplication) {
        String icon = walletApplication.getIcon();
        if (walletApplication.isHuaweiWallet()) {
            return getWalletIconResourceBase64(WALLET_HUAWEI_ICON_NAME);
        }
        File file = new File(this.mWalletIconDir, icon + ".png");
        if (!file.exists()) {
            LogUtil.a(TAG, "getIconBase64 icon not found:", icon);
            return getWalletIconResourceBase64(WALLET_DEFAULT_ICON_NAME);
        }
        byte[] c = sqd.c(file);
        if (c == null) {
            ReleaseLogUtil.c(TAG_RELEASE, "getIconBase64 read file error");
            return getWalletIconResourceBase64(WALLET_DEFAULT_ICON_NAME);
        }
        return BASE64_IMG_PREFIX + Base64.encodeToString(c, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x008a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String getWalletIconResourceBase64(java.lang.String r9) {
        /*
            r8 = this;
            java.lang.String r0 = "getDefaultIconBase64 close file error"
            java.io.File r1 = new java.io.File
            java.lang.String r2 = r8.mWalletManagerRootDir
            r1.<init>(r2, r9)
            boolean r2 = r1.exists()
            r3 = 0
            java.lang.String r4 = "R_NfcWalletManager"
            r5 = 0
            if (r2 != 0) goto L96
            java.lang.String r2 = "oversea_wallet_huawei_icon.png"
            boolean r2 = r2.equals(r9)
            if (r2 == 0) goto L2c
            android.content.Context r2 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            android.content.res.Resources r2 = r2.getResources()
            r6 = 2131430954(0x7f0b0e2a, float:1.8483624E38)
            android.graphics.Bitmap r2 = android.graphics.BitmapFactory.decodeResource(r2, r6)
            goto L45
        L2c:
            java.lang.String r2 = "default_wallet_app.png"
            boolean r2 = r2.equals(r9)
            if (r2 == 0) goto L44
            android.content.Context r2 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            android.content.res.Resources r2 = r2.getResources()
            r6 = 2131427996(0x7f0b029c, float:1.8477624E38)
            android.graphics.Bitmap r2 = android.graphics.BitmapFactory.decodeResource(r2, r6)
            goto L45
        L44:
            r2 = r5
        L45:
            if (r2 != 0) goto L51
            java.lang.String r0 = "getWalletIconResourceBase64 bitmap is null, icon:"
            java.lang.Object[] r9 = new java.lang.Object[]{r0, r9}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r4, r9)
            return r5
        L51:
            java.io.FileOutputStream r9 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L6c java.io.FileNotFoundException -> L6e
            r9.<init>(r1)     // Catch: java.lang.Throwable -> L6c java.io.FileNotFoundException -> L6e
            android.graphics.Bitmap$CompressFormat r6 = android.graphics.Bitmap.CompressFormat.PNG     // Catch: java.lang.Throwable -> L69 java.io.FileNotFoundException -> L6f
            r7 = 100
            r2.compress(r6, r7, r9)     // Catch: java.lang.Throwable -> L69 java.io.FileNotFoundException -> L6f
            r9.close()     // Catch: java.io.IOException -> L61
            goto L96
        L61:
            java.lang.Object[] r9 = new java.lang.Object[]{r0}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r4, r9)
            goto L96
        L69:
            r1 = move-exception
            r5 = r9
            goto L87
        L6c:
            r9 = move-exception
            goto L88
        L6e:
            r9 = r5
        L6f:
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L69
            java.lang.String r2 = "getDefaultIconBase64 create file error"
            r1[r3] = r2     // Catch: java.lang.Throwable -> L69
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r4, r1)     // Catch: java.lang.Throwable -> L69
            if (r9 == 0) goto L86
            r9.close()     // Catch: java.io.IOException -> L7f
            goto L86
        L7f:
            java.lang.Object[] r9 = new java.lang.Object[]{r0}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r4, r9)
        L86:
            return r5
        L87:
            r9 = r1
        L88:
            if (r5 == 0) goto L95
            r5.close()     // Catch: java.io.IOException -> L8e
            goto L95
        L8e:
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r4, r0)
        L95:
            throw r9
        L96:
            byte[] r9 = defpackage.sqd.c(r1)
            if (r9 != 0) goto La6
            java.lang.String r9 = "getDefaultIconBase64 read file error"
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r4, r9)
            return r5
        La6:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "data:image/png;base64,"
            r0.<init>(r1)
            java.lang.String r9 = android.util.Base64.encodeToString(r9, r3)
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.homewear21.home.manager.hwnfcwalletmgr.WalletAppManager.getWalletIconResourceBase64(java.lang.String):java.lang.String");
    }

    private boolean containsIgnoreCase(List<String> list, String str) {
        if (koq.b(list) || TextUtils.isEmpty(str)) {
            return false;
        }
        for (String str2 : list) {
            if (!TextUtils.isEmpty(str2) && str2.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
}
