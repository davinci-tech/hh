package com.huawei.hihealthservice.hihealthkit;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.IRealTimeDataCallback;
import com.huawei.hihealth.WearKitPermission;
import com.huawei.hihealthservice.hihealthkit.WearKitBinder;
import com.huawei.hwauthutil.HsfSignValidator;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.wearkit.IBaseCallback;
import com.huawei.wearkit.IDetectCommonCallback;
import com.huawei.wearkit.IRealTimeCallback;
import com.huawei.wearkit.IWearCommonCallback;
import com.huawei.wearkit.IWearKit;
import com.huawei.wearkit.IWearReadCallback;
import com.huawei.wearkit.IWearWriteCallback;
import com.huawei.wearkit.model.HealthLogOption;
import defpackage.iip;
import defpackage.ikc;
import defpackage.ins;
import defpackage.ioy;
import defpackage.ipd;
import defpackage.iqr;
import defpackage.iqw;
import defpackage.iqy;
import defpackage.irc;
import defpackage.ivv;
import defpackage.ivw;
import defpackage.jdx;
import defpackage.koq;
import health.compact.a.AuthorizationUtils;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class WearKitBinder extends IWearKit.Stub {
    private static final String e = BaseApplication.getAppPackage();

    /* renamed from: a, reason: collision with root package name */
    private IBaseCallback f4194a;
    private Context c;
    private Handler d;

    public WearKitBinder(Context context) {
        this.c = context;
        this.d = new Handler(context.getMainLooper()) { // from class: com.huawei.hihealthservice.hihealthkit.WearKitBinder.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what != 101) {
                    return;
                }
                try {
                    WearKitBinder.this.bBV_(message);
                } catch (RemoteException e2) {
                    ReleaseLogUtil.c("HiH_WearKitBinder", "handleMessage RemoteException: ", e2.getMessage());
                }
            }
        };
    }

    @Override // com.huawei.wearkit.IWearKit
    public void getDeviceList(int i, IRealTimeCallback iRealTimeCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_WearKitBinder", "getDeviceList enter");
        irc ircVar = new irc();
        if (iRealTimeCallback == null) {
            ReleaseLogUtil.d("HiH_WearKitBinder", "getDeviceList callback null");
            c(ircVar, "getDeviceList", 2);
            return;
        }
        if (ioy.b("getDeviceList", iRealTimeCallback)) {
            boolean z = true;
            if (d(i) && !b(1)) {
                z = false;
            }
            if (!iqw.b(this.c, 5) && z) {
                iRealTimeCallback.onResult(1001);
                c(ircVar, "getDeviceList", 1001);
            } else {
                e(ircVar, iRealTimeCallback);
            }
        }
    }

    private void e(irc ircVar, final IRealTimeCallback iRealTimeCallback) {
        ins.a(this.c).b(new IRealTimeDataCallback.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.WearKitBinder.3
            @Override // com.huawei.hihealth.IRealTimeDataCallback
            public void onResult(int i) {
                try {
                    LogUtil.a("WearKitBinder", " get wear device list errCode: ", Integer.valueOf(i));
                    iRealTimeCallback.onResult(i);
                } catch (Exception e2) {
                    ReleaseLogUtil.c("HiH_WearKitBinder", " getAllDeviceList ThirdPartApp exception：", e2.getMessage());
                }
            }

            @Override // com.huawei.hihealth.IRealTimeDataCallback
            public void onChange(int i, String str) throws RemoteException {
                LogUtil.a("WearKitBinder", " get wear device list errCode: ", Integer.valueOf(i));
                if (i == 0) {
                    iqr.a(WearKitBinder.this.c, str, iRealTimeCallback);
                } else {
                    iRealTimeCallback.onChange(i, str);
                }
            }
        });
        c(ircVar, "getDeviceList", 0);
    }

    @Override // com.huawei.wearkit.IWearKit
    public void requestAuthorization(int i, IBaseCallback iBaseCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_WearKitBinder", "requestAuthorization enter");
        irc ircVar = new irc();
        try {
            if (iBaseCallback == null) {
                ReleaseLogUtil.d("HiH_WearKitBinder", "requestAuthorization callback == null");
                c(ircVar, "requestAuthorization", 2);
                return;
            }
            int callingUid = Binder.getCallingUid();
            String nameForUid = this.c.getPackageManager().getNameForUid(callingUid);
            a(i, callingUid, nameForUid);
            this.f4194a = iBaseCallback;
            Message obtain = Message.obtain();
            obtain.what = 101;
            obtain.arg1 = callingUid;
            obtain.obj = nameForUid;
            this.d.sendMessage(obtain);
        } catch (Exception e2) {
            ReleaseLogUtil.c("HiH_WearKitBinder", "requestAuthorization Exception: ", e2.getMessage());
            c(ircVar, "requestAuthorization", 4);
            if (iBaseCallback != null) {
                iBaseCallback.onResult(4, Collections.EMPTY_MAP);
            }
        }
    }

    private void a(int i, int i2, String str) {
        LogUtil.c("WearKitBinder", "checkFlagPermission ", Integer.valueOf(i), Constants.LINK, Integer.valueOf(i2), Constants.LINK, str);
        if (i2 != i) {
            iip b = iip.b();
            int a2 = b.a(str);
            HiAppInfo c = b.c(a2);
            if (c != null) {
                int b2 = ivv.b(c.getSignature());
                String e2 = HsfSignValidator.e(this.c, str);
                if (b2 != i2) {
                    c.setSignature(ivw.a(e2, i2));
                    ReleaseLogUtil.e("HiH_WearKitBinder", "updateAppInfo result = ", Integer.valueOf(b.c(c)));
                }
            }
            if (a2 != 0) {
                ReleaseLogUtil.e("HiH_WearKitBinder", "checkFlagPermission appId = ", Integer.valueOf(a2), " result = ", Integer.valueOf(ikc.b().a(a2)));
            }
        }
    }

    @Override // com.huawei.wearkit.IWearKit
    public void readFromWearable(String str, String str2, IWearReadCallback iWearReadCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_WearKitBinder", "readFromWearable enter");
        irc ircVar = new irc();
        if (iWearReadCallback == null) {
            ReleaseLogUtil.d("HiH_WearKitBinder", "readFromWearable callback null");
            c(ircVar, "readFromWearable", 2);
        } else if (ioy.b("readFromWearable", iWearReadCallback)) {
            if (!iqw.b(this.c, 7) || b(2)) {
                c(ircVar, "readFromWearable", 1001);
                iWearReadCallback.onResult(1001, "permission denied", new byte[0]);
            } else {
                ins.a(this.c).b(str, str2, iWearReadCallback);
                c(ircVar, "readFromWearable", 0);
            }
        }
    }

    @Override // com.huawei.wearkit.IWearKit
    public void writeToWearable(String str, String str2, byte[] bArr, String str3, IWearWriteCallback iWearWriteCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_WearKitBinder", "writeToWearable enter");
        irc ircVar = new irc();
        if (iWearWriteCallback == null) {
            ReleaseLogUtil.d("HiH_WearKitBinder", "writeToWearable callback null");
            c(ircVar, "writeToWearable", 2);
        } else if (ioy.b("writeToWearable", iWearWriteCallback)) {
            if (!iqw.b(this.c, 8) || b(2)) {
                c(ircVar, "writeToWearable", 1001);
                iWearWriteCallback.onResult(1001, "permission denied");
            } else {
                ins.a(this.c).d(str, str2, bArr, str3, iWearWriteCallback);
                c(ircVar, "writeToWearable", 0);
            }
        }
    }

    @Override // com.huawei.wearkit.IWearKit
    public void pushMsgToWearable(String str, String str2, IWearCommonCallback iWearCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_WearKitBinder", "pushMsgToWearable enter");
        irc ircVar = new irc();
        if (iWearCommonCallback == null) {
            ReleaseLogUtil.d("HiH_WearKitBinder", "pushMsgToWearable callback null");
            c(ircVar, "pushMsgToWearable", 2);
        } else if (ioy.b("pushMsgToWearable", iWearCommonCallback)) {
            if (!iqw.b(this.c, 9) || b(3)) {
                iWearCommonCallback.onResult(1001, "permission denied");
                c(ircVar, "pushMsgToWearable", 1001);
            } else {
                ins.a(this.c).e(ivw.b(this.c), str, str2, iWearCommonCallback);
                c(ircVar, "pushMsgToWearable", 0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bBV_(Message message) throws RemoteException {
        String valueOf = String.valueOf(message.obj);
        irc ircVar = new irc();
        if (TextUtils.isEmpty(valueOf)) {
            this.f4194a.onResult(2, null);
            c(ircVar, "requestAuthorization", 2);
            return;
        }
        boolean a2 = AuthorizationUtils.a(this.c);
        HashMap hashMap = new HashMap(16);
        if (a2) {
            hashMap.put("flag", String.valueOf(message.arg1));
            this.f4194a.onResult(0, hashMap);
            Intent intent = new Intent();
            String str = e;
            intent.setClassName(str, "com.huawei.ui.main.stories.me.activity.thirdparty.WearKitAuthActivity");
            intent.setPackage(str);
            intent.setFlags(268435456);
            intent.addFlags(AMapEngineUtils.HALF_MAX_P20_WIDTH);
            intent.addFlags(524288);
            intent.addFlags(8388608);
            intent.putExtra(MapKeyNames.APP_INFO, c(valueOf));
            intent.putExtra("logo", true);
            c(ircVar, "requestAuthorization", 0);
            this.c.startActivity(intent);
            return;
        }
        ReleaseLogUtil.d("HiH_WearKitBinder", "auth is not permitted by user");
        this.f4194a.onResult(1003, hashMap);
        c(ircVar, "requestAuthorization", 1003);
    }

    private HiAppInfo c(String str) {
        HiAppInfo b = iip.b().b(str);
        if (b != null) {
            return b;
        }
        HiAppInfo c = ivw.c(this.c, str);
        if (c == null) {
            return null;
        }
        iip.b().e(c, 0);
        return iip.b().b(str);
    }

    private boolean d(int i) {
        int callingUid = Binder.getCallingUid();
        LogUtil.c("WearKitBinder", "isFlagValid ", Integer.valueOf(i), Constants.LINK, Integer.valueOf(callingUid));
        return i == callingUid;
    }

    private boolean b(int i) {
        if (!ioy.a(this.c, Binder.getCallingUid())) {
            return true;
        }
        List<WearKitPermission> b = ikc.b().b(iip.b().a(ivw.a(this.c)));
        if (koq.b(b)) {
            return true;
        }
        for (WearKitPermission wearKitPermission : b) {
            if (wearKitPermission != null && wearKitPermission.getScopeId() == i) {
                LogUtil.a("WearKitBinder", "checkDenied scope = ", Integer.valueOf(i), "allow = ", Integer.valueOf(wearKitPermission.getAllow()));
                return wearKitPermission.getAllow() != 1;
            }
        }
        LogUtil.a("WearKitBinder", "checkDenied true");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final irc ircVar, final String str, final int i) {
        if (ircVar == null || TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("HiH_WearKitBinder", "callWearKit illegal input");
        } else {
            final String e2 = e();
            jdx.b(new Runnable() { // from class: iot
                @Override // java.lang.Runnable
                public final void run() {
                    WearKitBinder.this.a(str, i, e2, ircVar);
                }
            });
        }
    }

    public /* synthetic */ void a(String str, int i, String str2, irc ircVar) {
        iqy iqyVar = new iqy(str, i, str2);
        iqyVar.d("1.0.0.0");
        ircVar.d(this.c, iqyVar);
    }

    private String e() {
        LogUtil.a("WearKitBinder", "enter getCallingPackageName");
        if (this.c == null) {
            LogUtil.b("WearKitBinder", "getCallingPackageName:mContext is null");
            return "";
        }
        int callingUid = Binder.getCallingUid();
        PackageManager packageManager = this.c.getPackageManager();
        if (packageManager == null) {
            LogUtil.b("WearKitBinder", "getCallingPackageName:packageManager is null");
            return "";
        }
        return packageManager.getNameForUid(callingUid);
    }

    @Override // com.huawei.wearkit.IWearKit
    public void sendDeviceCommand(int i, String str, final IRealTimeCallback iRealTimeCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_WearKitBinder", "sendDeviceCommand enter");
        irc ircVar = new irc();
        if (iRealTimeCallback == null) {
            ReleaseLogUtil.d("HiH_WearKitBinder", "sendDeviceCommand callback null");
            c(ircVar, "sendDeviceCommand", 2);
        } else if (ioy.b("sendDeviceCommand", iRealTimeCallback)) {
            if (!iqw.b(this.c, 6)) {
                c(ircVar, "sendDeviceCommand", 1001);
                iRealTimeCallback.onResult(1001);
            } else {
                ins.a(this.c).h(str, new IRealTimeDataCallback() { // from class: com.huawei.hihealthservice.hihealthkit.WearKitBinder.4
                    @Override // android.os.IInterface
                    public IBinder asBinder() {
                        return null;
                    }

                    @Override // com.huawei.hihealth.IRealTimeDataCallback
                    public void onResult(int i2) {
                        LogUtil.a("WearKitBinder", "sendDeviceCommand onResult errCode ", Integer.valueOf(i2));
                        try {
                            iRealTimeCallback.onResult(i2);
                        } catch (Exception e2) {
                            ReleaseLogUtil.c("HiH_WearKitBinder", " sendDeviceCommand ThirdPartApp exception", LogAnonymous.b((Throwable) e2));
                        }
                    }

                    @Override // com.huawei.hihealth.IRealTimeDataCallback
                    public void onChange(int i2, String str2) throws RemoteException {
                        LogUtil.a("WearKitBinder", "sendDeviceCommand onChange errCode ", Integer.valueOf(i2));
                        iRealTimeCallback.onChange(i2, str2);
                    }
                });
                c(ircVar, "sendDeviceCommand", 0);
            }
        }
    }

    @Override // com.huawei.wearkit.IWearKit
    public void isSwitchOn(int i, IDetectCommonCallback iDetectCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_WearKitBinder", "isSwitchOn enter");
        irc ircVar = new irc();
        if (iDetectCommonCallback == null) {
            ReleaseLogUtil.d("HiH_WearKitBinder", "isSwitchOn callback null");
            c(ircVar, "isSwitchOn", 2);
        } else if (ioy.b("isSwitchOn", iDetectCommonCallback)) {
            if (!iqw.b(this.c, 10)) {
                iDetectCommonCallback.onResult(1001, null, "permission denied");
                c(ircVar, "isSwitchOn", 1001);
                LogUtil.h("WearKitBinder", ipd.b(1001));
                return;
            }
            ins.a(this.c).a(i, e(ircVar, "isSwitchOn", iDetectCommonCallback));
        }
    }

    @Override // com.huawei.wearkit.IWearKit
    public void getList(int i, IDetectCommonCallback iDetectCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_WearKitBinder", "getList enter");
        irc ircVar = new irc();
        if (iDetectCommonCallback == null) {
            ReleaseLogUtil.d("HiH_WearKitBinder", "getList callback null");
            c(ircVar, "getList", 2);
        } else if (ioy.b("getList", iDetectCommonCallback)) {
            if (!iqw.b(this.c, 11)) {
                iDetectCommonCallback.onResult(1001, null, "permission denied");
                c(ircVar, "getList", 1001);
                LogUtil.h("WearKitBinder", ipd.b(1001));
                return;
            }
            ins.a(this.c).e(i, e(ircVar, "getList", iDetectCommonCallback));
        }
    }

    @Override // com.huawei.wearkit.IWearKit
    public void isLatestVersion(int i, IDetectCommonCallback iDetectCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_WearKitBinder", "isLatestVersion enter");
        irc ircVar = new irc();
        if (iDetectCommonCallback == null) {
            ReleaseLogUtil.d("HiH_WearKitBinder", "isLatestVersion callback null");
            c(ircVar, "isLatestVersion", 2);
        } else if (ioy.b("isLatestVersion", iDetectCommonCallback)) {
            if (!iqw.b(this.c, 12)) {
                iDetectCommonCallback.onResult(1001, null, "permission denied");
                c(ircVar, "isLatestVersion", 1001);
                LogUtil.h("WearKitBinder", ipd.b(1001));
                return;
            }
            ins.a(this.c).d(i, e(ircVar, "isLatestVersion", iDetectCommonCallback));
        }
    }

    @Override // com.huawei.wearkit.IWearKit
    public void goIntoPage(String str, int i, IDetectCommonCallback iDetectCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_WearKitBinder", "goIntoPage enter");
        irc ircVar = new irc();
        if (iDetectCommonCallback == null || TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("HiH_WearKitBinder", "goIntoPage param is null");
            c(ircVar, "goIntoPage", 2);
        } else if (ioy.b("goIntoPage", iDetectCommonCallback)) {
            if (!iqw.b(this.c, 13)) {
                iDetectCommonCallback.onResult(1001, null, "permission denied");
                c(ircVar, "goIntoPage", 1001);
                LogUtil.h("WearKitBinder", ipd.b(1001));
                return;
            }
            ins.a(this.c).c(str, i, e(ircVar, "goIntoPage", iDetectCommonCallback));
        }
    }

    @Override // com.huawei.wearkit.IWearKit
    public void captureLog(HealthLogOption healthLogOption, IWearCommonCallback iWearCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_WearKitBinder", "enter captureLog, action=", Integer.valueOf(healthLogOption.getActionType().geActionType()));
        irc ircVar = new irc();
        if (iWearCommonCallback == null) {
            ReleaseLogUtil.d("HiH_WearKitBinder", "captureLog callback null");
            c(ircVar, "captureLog", 2);
        } else if (ioy.b("captureLog", iWearCommonCallback)) {
            if (!iqw.b(this.c, 14)) {
                ReleaseLogUtil.d("HiH_WearKitBinder", "captureLog ", "permission denied");
                iWearCommonCallback.onResult(1001, "permission denied");
                c(ircVar, "captureLog", 1001);
            } else {
                ins.a(this.c).a(healthLogOption, iWearCommonCallback);
                c(ircVar, "captureLog", 0);
            }
        }
    }

    public void onDestroy() {
        ReleaseLogUtil.e("HiH_WearKitBinder", "onDestroy");
    }

    private IDetectCommonCallback e(final irc ircVar, final String str, final IDetectCommonCallback iDetectCommonCallback) {
        return new IDetectCommonCallback.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.WearKitBinder.5
            @Override // com.huawei.wearkit.IDetectCommonCallback
            public void onResult(int i, List list, String str2) throws RemoteException {
                iDetectCommonCallback.onResult(i, list, str2);
                WearKitBinder.this.c(ircVar, str, i);
            }
        };
    }
}
