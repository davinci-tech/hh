package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import com.huawei.wear.oversea.overseamanger.OverSeaMangerUtil;
import com.huawei.wear.oversea.overseamanger.SatcomExistInfoCallback;
import com.huawei.wear.oversea.overseamanger.SatcomQueryCallBack;
import com.huawei.wear.oversea.satcomcard.SatcomCardSupportInfo;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes6.dex */
public class ocp {
    private static Map<String, String> b = new HashMap(16);

    public static std a(DeviceInfo deviceInfo) {
        ReleaseLogUtil.e("R_WalletSdkWrapper", "getOverSeaBaseInfo enter");
        std stdVar = new std();
        stdVar.l(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
        stdVar.b(BaseApplication.getContext().getPackageName());
        stdVar.h(LoginInit.getInstance(BaseApplication.getContext()).getDeviceId());
        stdVar.f(LoginInit.getInstance(BaseApplication.getContext()).getDeviceType());
        stdVar.e(ThirdLoginDataStorageUtil.getTokenTypeValue());
        stdVar.e(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1007));
        stdVar.o(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1008));
        stdVar.c(true);
        if (deviceInfo == null) {
            ReleaseLogUtil.d("R_WalletSdkWrapper", "getOverSeaBaseInfo deviceInfo is null");
            return stdVar;
        }
        stdVar.j(deviceInfo.getSoftVersion());
        stdVar.k(String.valueOf(CommonUtil.d(BaseApplication.getContext())));
        stdVar.d(deviceInfo.getCertModel());
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009);
        stdVar.d(CommonUtil.m(BaseApplication.getContext(), accountInfo));
        String accountInfo2 = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1014);
        stdVar.a(accountInfo2);
        stdVar.m(deviceInfo.getCertModel() + " " + deviceInfo.getSoftVersion());
        if (TextUtils.isEmpty(deviceInfo.getUuid())) {
            stdVar.g(deviceInfo.getDeviceIdentify());
        } else {
            stdVar.g(deviceInfo.getUuid());
        }
        ReleaseLogUtil.e("R_WalletSdkWrapper", "ServiceCountryCode:", accountInfo2, ", stSite:", accountInfo);
        String valueOf = String.valueOf(deviceInfo.getDeviceOtaAreaType());
        stdVar.i(valueOf);
        stdVar.e(cwi.c(deviceInfo, 50));
        stdVar.n(deviceInfo.getDeviceUdid());
        ReleaseLogUtil.e("R_WalletSdkWrapper", "getOverSeaBaseInfo otaAreaType:", valueOf);
        return stdVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0052, code lost:
    
        if (r2.await(200, java.util.concurrent.TimeUnit.MILLISECONDS) == false) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String e(java.lang.String r7) {
        /*
            java.lang.String r0 = "getCplc enter"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "R_WalletSdkWrapper"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r1, r0)
            java.lang.String r0 = ""
            java.lang.String[] r0 = new java.lang.String[]{r0}
            java.util.Map<java.lang.String, java.lang.String> r2 = defpackage.ocp.b
            boolean r2 = r2.containsKey(r7)
            r3 = 0
            if (r2 == 0) goto L33
            java.util.Map<java.lang.String, java.lang.String> r2 = defpackage.ocp.b
            java.lang.Object r2 = r2.get(r7)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L33
            java.util.Map<java.lang.String, java.lang.String> r1 = defpackage.ocp.b
            java.lang.Object r7 = r1.get(r7)
            java.lang.String r7 = (java.lang.String) r7
            r0[r3] = r7
            return r7
        L33:
            java.util.Map<java.lang.String, java.lang.String> r2 = defpackage.ocp.b
            r2.clear()
            java.util.concurrent.CountDownLatch r2 = new java.util.concurrent.CountDownLatch
            r4 = 1
            r2.<init>(r4)
            com.huawei.hwdevice.mainprocess.mgr.hwpaymgr.HwPayManager r4 = com.huawei.hwdevice.mainprocess.mgr.hwpaymgr.HwPayManager.a()
            ocp$5 r5 = new ocp$5
            r5.<init>()
            r4.e(r5)
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.InterruptedException -> L55
            r5 = 200(0xc8, double:9.9E-322)
            boolean r2 = r2.await(r5, r4)     // Catch: java.lang.InterruptedException -> L55
            if (r2 != 0) goto L6e
            goto L65
        L55:
            r2 = move-exception
            java.lang.String r4 = "getCplc exception:"
            java.lang.String r2 = com.huawei.haf.common.exception.ExceptionUtils.d(r2)
            java.lang.Object[] r2 = new java.lang.Object[]{r4, r2}
            java.lang.String r4 = "WalletSdkWrapper"
            com.huawei.hwlogsmodel.LogUtil.b(r4, r2)
        L65:
            java.lang.String r2 = "getCplc timeout"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r1, r2)
        L6e:
            java.util.Map<java.lang.String, java.lang.String> r1 = defpackage.ocp.b
            r2 = r0[r3]
            r1.put(r7, r2)
            r7 = r0[r3]
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ocp.e(java.lang.String):java.lang.String");
    }

    public static void cVT_(String str, Activity activity, boolean z, boolean z2) {
        ReleaseLogUtil.e("R_WalletSdkWrapper", "startWalletOobeActivity enter");
        try {
            Intent intent = new Intent();
            intent.setClassName(BaseApplication.getContext(), "com.huawei.wear.wallet.ui.SatcomGuideActivity");
            intent.putExtra("isFromWear", z);
            intent.putExtra("device_id", str);
            intent.putExtra("wear_home_class_name", "com.huawei.ui.homewear21.home.WearHomeActivity");
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("WalletSdkWrapper", "ActivityNotFoundException e:", e.getMessage());
        }
        if (z2) {
            activity.finish();
        }
    }

    public static void d(DeviceInfo deviceInfo, SatcomQueryCallBack satcomQueryCallBack) {
        OverSeaMangerUtil.c(BaseApplication.getContext()).e(BaseApplication.getContext(), a(deviceInfo), satcomQueryCallBack);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x005c, code lost:
    
        if (r3.await(200, java.util.concurrent.TimeUnit.MILLISECONDS) == false) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static defpackage.ock b(final com.huawei.health.devicemgr.business.entity.DeviceInfo r8, final boolean r9) {
        /*
            java.lang.String r0 = "querySupportSatcom enter"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "R_WalletSdkWrapper"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r1, r0)
            ock r0 = new ock
            r0.<init>()
            java.lang.String r2 = "WalletSdkWrapper"
            if (r8 != 0) goto L1e
            java.lang.String r8 = "querySatcomStatus deviceInfo is null"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r2, r8)
            return r0
        L1e:
            java.lang.String r3 = r8.getDeviceIdentify()
            com.huawei.health.devicemgr.business.entity.DeviceCapability r4 = defpackage.cvs.e(r3)
            boolean r3 = c(r3, r4)
            if (r3 != 0) goto L36
            java.lang.String r8 = "querySatcomStatus not support wallet"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r2, r8)
            return r0
        L36:
            java.util.concurrent.CountDownLatch r3 = new java.util.concurrent.CountDownLatch
            r4 = 1
            r3.<init>(r4)
            std r4 = a(r8)
            android.content.Context r5 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            com.huawei.wear.oversea.overseamanger.OverSeaMangerUtil r5 = com.huawei.wear.oversea.overseamanger.OverSeaMangerUtil.c(r5)
            android.content.Context r6 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            ocp$4 r7 = new ocp$4
            r7.<init>()
            r5.e(r6, r4, r7)
            java.util.concurrent.TimeUnit r8 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.InterruptedException -> L5f
            r4 = 200(0xc8, double:9.9E-322)
            boolean r8 = r3.await(r4, r8)     // Catch: java.lang.InterruptedException -> L5f
            if (r8 != 0) goto L76
            goto L6d
        L5f:
            r8 = move-exception
            java.lang.String r9 = "querySupportSatcom exception:"
            java.lang.String r8 = com.huawei.haf.common.exception.ExceptionUtils.d(r8)
            java.lang.Object[] r8 = new java.lang.Object[]{r9, r8}
            com.huawei.hwlogsmodel.LogUtil.b(r2, r8)
        L6d:
            java.lang.String r8 = "querySupportSatcom timeout"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r1, r8)
        L76:
            java.lang.String r8 = "querySupportSatcom:"
            java.lang.Object[] r8 = new java.lang.Object[]{r8, r0}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r1, r8)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ocp.b(com.huawei.health.devicemgr.business.entity.DeviceInfo, boolean):ock");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(final CountDownLatch countDownLatch, DeviceInfo deviceInfo, final ock ockVar) {
        std a2 = a(deviceInfo);
        String e = e(deviceInfo.getDeviceIdentify());
        a2.c(e);
        ReleaseLogUtil.e("R_WalletSdkWrapper", "queryOpenedSatcomCardStatus cplc:", e);
        OverSeaMangerUtil.c(BaseApplication.getContext()).e(BaseApplication.getContext(), a2, new SatcomExistInfoCallback() { // from class: ocp.1
            @Override // com.huawei.wear.oversea.overseamanger.SatcomExistInfoCallback
            public void onResult(int i, int i2) {
                ReleaseLogUtil.e("R_WalletSdkWrapper", "queryOpenedSatcomCardStatus onResult resultCode:", Integer.valueOf(i), ", existSatomCard:", Integer.valueOf(i2));
                boolean z = i != 0 || i2 == 0;
                boolean d = ock.this.d();
                int b2 = ock.this.b();
                ock.this.a(z);
                if (d && !z && b2 != 0) {
                    ssz.e(ocj.e());
                    ReleaseLogUtil.e("R_WalletSdkWrapper", "queryOpenedSatcomCardStatus set HealthAdapter");
                }
                countDownLatch.countDown();
            }
        });
    }

    public static void e(final DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            ReleaseLogUtil.d("WalletSdkWrapper", "preLoadSatcomStatus deviceInfo is null");
            return;
        }
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        if (!c(deviceIdentify, cvs.e(deviceIdentify))) {
            ReleaseLogUtil.d("R_WalletSdkWrapper", "preLoadSatcomStatus not support wallet");
        } else {
            OverSeaMangerUtil.c(BaseApplication.getContext()).e(BaseApplication.getContext(), a(deviceInfo), new SatcomQueryCallBack() { // from class: ocp.2
                @Override // com.huawei.wear.oversea.overseamanger.SatcomQueryCallBack
                public int onSuccess(SatcomCardSupportInfo satcomCardSupportInfo) {
                    int e = satcomCardSupportInfo.e();
                    ReleaseLogUtil.e("R_WalletSdkWrapper", "preLoadSatcomStatus querySupportSatcomCardUtil onSuccess, isSupportSatcomCard:", Boolean.valueOf(satcomCardSupportInfo.c()));
                    if (satcomCardSupportInfo.c()) {
                        ocp.c(DeviceInfo.this);
                    }
                    return e;
                }

                @Override // com.huawei.wear.oversea.overseamanger.SatcomQueryCallBack
                public int onFail(SatcomCardSupportInfo satcomCardSupportInfo) {
                    int e = satcomCardSupportInfo.e();
                    ReleaseLogUtil.d("R_WalletSdkWrapper", "preLoadSatcomStatus querySupportSatcomCardUtil fail returnCode is:", Integer.valueOf(e));
                    return e;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(DeviceInfo deviceInfo) {
        std a2 = a(deviceInfo);
        a2.c(e(deviceInfo.getDeviceIdentify()));
        OverSeaMangerUtil.c(BaseApplication.getContext()).e(BaseApplication.getContext(), a2, new SatcomExistInfoCallback() { // from class: ocp.3
            @Override // com.huawei.wear.oversea.overseamanger.SatcomExistInfoCallback
            public void onResult(int i, int i2) {
                ReleaseLogUtil.e("R_WalletSdkWrapper", "preLoadOpenedCardStatus querySatcomExistInfo onResult, resultCode:", Integer.valueOf(i), ", existSatomCard:", Integer.valueOf(i2));
            }
        });
    }

    public static boolean c(String str, DeviceCapability deviceCapability) {
        if (deviceCapability == null) {
            ReleaseLogUtil.d("R_WalletSdkWrapper", "isShowPayEntrance deviceCapability is null");
            return false;
        }
        ReleaseLogUtil.e("R_WalletSdkWrapper", "isShowPayEntrance isSupportWalletOpenCard", Boolean.valueOf(deviceCapability.isSupportWalletOpenCard()), " isSupportPay", Boolean.valueOf(deviceCapability.isSupportPay()));
        if (!deviceCapability.isSupportWalletOpenCard() && !deviceCapability.isSupportPay()) {
            return false;
        }
        if (b(str)) {
            return true;
        }
        if (deviceCapability.isHideWalletEntrance()) {
            ReleaseLogUtil.d("R_WalletSdkWrapper", "getShowWalletEntrance not support");
            return false;
        }
        if (c()) {
            if (d()) {
                ReleaseLogUtil.e("R_WalletSdkWrapper", "serviceCountryCode is cn and OpenCardNewWay is true");
                return true;
            }
            if (LanguageUtil.m(BaseApplication.getContext()) || LanguageUtil.p(BaseApplication.getContext())) {
                return true;
            }
        }
        return false;
    }

    public static boolean b(String str) {
        DeviceInfo e;
        if (TextUtils.isEmpty(str)) {
            e = cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "WalletSdkWrapper");
        } else {
            e = jpt.e(str, "WalletSdkWrapper");
        }
        boolean c = cwi.c(e, 50);
        ReleaseLogUtil.e("R_WalletSdkWrapper", "is support index 50:", Boolean.valueOf(c));
        return c;
    }

    private static boolean c() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1014);
        if (d() && "CN".equals(accountInfo)) {
            return true;
        }
        String accountInfo2 = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        if ("CN".equals(accountInfo) && "CN".equals(accountInfo2)) {
            return true;
        }
        ReleaseLogUtil.d("R_WalletSdkWrapper", "country not support");
        return false;
    }

    private static boolean d() {
        LogUtil.a("WalletSdkWrapper", "isOpenCardNewWay");
        if (ixb.p() != null) {
            return ixb.p().isWalletOpenCard();
        }
        return false;
    }
}
