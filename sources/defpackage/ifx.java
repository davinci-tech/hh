package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.hihealth.HiAccountInfo;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.HiAuthorizationOption;
import com.huawei.hihealth.HiDataAggregateProOption;
import com.huawei.hihealth.HiDataDeleteOption;
import com.huawei.hihealth.HiDataDeleteProOption;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiDataSourceFetchOption;
import com.huawei.hihealth.HiDataUpdateOption;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiGoalInfo;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthUnit;
import com.huawei.hihealth.HiHealthUserPermission;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.HiSampleConfigProcessOption;
import com.huawei.hihealth.HiSportStatDataAggregateOption;
import com.huawei.hihealth.HiSubscribeTrigger;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.IAggregateListener;
import com.huawei.hihealth.IAggregateListenerEx;
import com.huawei.hihealth.IAuthorizationListener;
import com.huawei.hihealth.ICommonCallback;
import com.huawei.hihealth.ICommonListener;
import com.huawei.hihealth.IDataClientListener;
import com.huawei.hihealth.IDataHiDeviceInfoListener;
import com.huawei.hihealth.IDataOperateListener;
import com.huawei.hihealth.IDataReadResultListener;
import com.huawei.hihealth.IDeleteListenerEx;
import com.huawei.hihealth.IHiHealth;
import com.huawei.hihealth.IMultiDataClientListener;
import com.huawei.hihealth.IRegisterClientListener;
import com.huawei.hihealth.ISubscribeListener;
import com.huawei.hihealth.ISupportedTypesListener;
import com.huawei.hihealth.IUnSubscribeListener;
import com.huawei.hihealth.WearKitPermission;
import com.huawei.hihealth.data.constant.HiErrorCode;
import com.huawei.hihealth.util.HiBroadcastUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealth.util.WidgetHelperUtils;
import com.huawei.hihealthservice.InsertExecutor;
import com.huawei.hihealthservice.auth.HiAuthException;
import com.huawei.hihealthservice.auth.HiAuthManager;
import com.huawei.hihealthservice.auth.HiUserAuth;
import com.huawei.hihealthservice.runnable.SingleRunnable;
import com.huawei.hihealthservice.updatemanager.command.UpdateCommand;
import com.huawei.hms.framework.common.hianalytics.CrashHianalyticsData;
import com.huawei.hms.mlsdk.common.internal.client.event.MonitorResult;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OperationKey;
import com.huawei.utils.FoundationCommonUtil;
import health.compact.a.CommonUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HuaweiHealth;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes.dex */
public class ifx extends IHiHealth.Stub {
    private static ArrayList<Integer> b;
    private Context c;
    private ivm d;
    private InsertExecutor e;

    static {
        ArrayList<Integer> arrayList = new ArrayList<>(3);
        b = arrayList;
        arrayList.add(50001);
        b.add(101003);
        b.add(101202);
    }

    public ifx(Context context, InsertExecutor insertExecutor) {
        this.d = null;
        if (context == null || insertExecutor == null) {
            throw new iwu("HiHealthBinder param is null");
        }
        this.c = context.getApplicationContext();
        this.e = insertExecutor;
        ivm ivmVar = new ivm();
        this.d = ivmVar;
        ivmVar.e(context, this.e);
        CommonUtil.p(this.c);
        ify.e().a(context, insertExecutor);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void initHiHealth(final HiAppInfo hiAppInfo) throws RemoteException {
        if (c()) {
            if (hiAppInfo == null) {
                LogUtil.h("HiH_HiHealthBinder", "initHiHealth appInfo = null");
                ikq.c(this.c);
                HiBroadcastUtil.c(this.c, 0);
                HiBroadcastUtil.i(this.c);
                ivg.c().a(200, "initHiHealth", new ikv(this.c.getPackageName()));
                return;
            }
            ReleaseLogUtil.e("HiH_HiHealthBinder", "initHiHealth packageName:", hiAppInfo.getPackageName());
            this.e.execute(new SingleRunnable() { // from class: ifx.2
                @Override // com.huawei.hihealthservice.runnable.SingleRunnable
                public void doRun() {
                    String packageName = hiAppInfo.getPackageName();
                    if (!ivw.a(ifx.this.c, packageName)) {
                        LogUtil.b("HiH_HiHealthBinder", "initHiHealth() packageName = null packageName = ", packageName);
                    } else {
                        ism.f().p();
                        LogUtil.a("HiH_HiHealthBinder", "initHiHealth() app = ", Integer.valueOf(iip.b().a(packageName)));
                    }
                }
            });
        }
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void unBindHiHealth() throws RemoteException {
        if (c()) {
            LogUtil.c("HiH_HiHealthBinder", "unBindHiHealth");
        }
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void requestAuthorization(final HiAuthorizationOption hiAuthorizationOption, IAuthorizationListener iAuthorizationListener) throws RemoteException {
        int i;
        List list;
        int i2;
        int i3;
        final int[] iArr = {0};
        final ikv e = igd.b().e();
        final String a2 = ivw.a(this.c);
        final int d = igd.b().d();
        LogUtil.a("HiH_HiHealthBinder", "requestAuthorization authorOption = ", hiAuthorizationOption, " packageName = ", a2, " appType = ", Integer.valueOf(d));
        if (e(iAuthorizationListener, a2, d)) {
            return;
        }
        try {
            try {
                try {
                    try {
                        i = 2;
                    } catch (TimeoutException e2) {
                        e = e2;
                        i = 2;
                    }
                } catch (Throwable th) {
                    th = th;
                    list = null;
                    igb.e(iAuthorizationListener, iArr[0], list);
                    throw th;
                }
            } catch (TimeoutException e3) {
                e = e3;
                i = 2;
                list = null;
            }
        } catch (InterruptedException e4) {
            e = e4;
            i = 2;
        } catch (ExecutionException e5) {
            e = e5;
            i = 2;
        }
        try {
            this.e.submit(new Runnable() { // from class: ifx.4
                @Override // java.lang.Runnable
                public void run() {
                    ifx.this.b(d, a2, hiAuthorizationOption, iArr, e);
                }
            }).get(20000L, TimeUnit.MILLISECONDS);
            i2 = iArr[0];
            list = null;
        } catch (InterruptedException e6) {
            e = e6;
            Object[] objArr = new Object[i];
            objArr[0] = "requestAuthorization InterruptedException = ";
            objArr[1] = e.getMessage();
            ReleaseLogUtil.c("HiH_HiHealthBinder", objArr);
            i3 = 22;
            iArr[0] = 22;
            igb.e(iAuthorizationListener, i3, null);
            return;
        } catch (ExecutionException e7) {
            e = e7;
            Object[] objArr2 = new Object[i];
            objArr2[0] = "requestAuthorization ExecutionException = ";
            objArr2[1] = e.getMessage();
            ReleaseLogUtil.c("HiH_HiHealthBinder", objArr2);
            iArr[0] = 22;
            i3 = 22;
            igb.e(iAuthorizationListener, i3, null);
            return;
        } catch (TimeoutException e8) {
            e = e8;
            list = null;
            try {
                Object[] objArr3 = new Object[i];
                objArr3[0] = "requestAuthorization TimeoutException = ";
                objArr3[1] = e.getMessage();
                ReleaseLogUtil.c("HiH_HiHealthBinder", objArr3);
                i2 = 21;
                iArr[0] = 21;
                igb.e(iAuthorizationListener, i2, list);
            } catch (Throwable th2) {
                th = th2;
                igb.e(iAuthorizationListener, iArr[0], list);
                throw th;
            }
        }
        igb.e(iAuthorizationListener, i2, list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(9:0|1|(1:3)(1:(1:29)(6:30|5|6|7|8|(2:10|11)(4:13|(2:15|(2:17|18))|19|(2:21|22)(2:23|24))))|4|5|6|7|8|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0050, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0051, code lost:
    
        health.compact.a.hwlogsmodel.ReleaseLogUtil.c("HiH_HiHealthBinder", "requestAuthorization UnsupportedEncodingException e = ", r0.getMessage());
        r0 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x007d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(int r16, java.lang.String r17, com.huawei.hihealth.HiAuthorizationOption r18, int[] r19, defpackage.ikv r20) {
        /*
            r15 = this;
            r1 = r15
            r2 = r17
            r8 = r20
            r3 = 0
            java.lang.String r4 = "HiH_HiHealthBinder"
            r0 = 1
            r5 = r16
            if (r5 != r0) goto L26
            ikw r5 = defpackage.ikw.b()
            com.huawei.hihealthservice.auth.WhiteListApp r5 = r5.d(r2)
            java.lang.String r5 = r5.getAppId()
            ikw r6 = defpackage.ikw.b()
            com.huawei.hihealthservice.auth.WhiteListApp r6 = r6.d(r2)
            java.lang.String r6 = r6.getAccessToken()
            goto L31
        L26:
            if (r18 == 0) goto L34
            java.lang.String r5 = r18.getAppId()
            java.lang.String r6 = r18.getAccessToken()
            r0 = r3
        L31:
            r7 = r5
            r5 = r0
            goto L42
        L34:
            java.lang.String r0 = "authorOption = null"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.c(r4, r0)
            java.lang.String r5 = ""
            r6 = r5
            r7 = r6
            r5 = r3
        L42:
            igm r0 = defpackage.igm.e()
            r0.a(r6)
            java.lang.String r0 = "UTF-8"
            java.lang.String r0 = java.net.URLEncoder.encode(r6, r0)     // Catch: java.io.UnsupportedEncodingException -> L50
            goto L60
        L50:
            r0 = move-exception
            java.lang.String r9 = "requestAuthorization UnsupportedEncodingException e = "
            java.lang.String r0 = r0.getMessage()
            java.lang.Object[] r0 = new java.lang.Object[]{r9, r0}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r4, r0)
            r0 = 0
        L60:
            igm r9 = defpackage.igm.e()
            java.lang.String r9 = r9.a()
            boolean r10 = health.compact.a.HiCommonUtil.b(r9)
            if (r10 == 0) goto L7d
            java.lang.String r0 = "requestAuthorization who is null or empty"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.h(r4, r0)
            r0 = 24
            r19[r3] = r0
            return
        L7d:
            igm r10 = defpackage.igm.e()
            com.huawei.hihealth.HiAccountInfo r10 = r10.c(r9, r8)
            boolean r11 = r15.b(r6, r10, r8)
            if (r11 == 0) goto Lb6
            long r11 = r10.getExpiresIn()
            java.lang.String r13 = "requestAuthorization atValidTime = "
            java.lang.Long r14 = java.lang.Long.valueOf(r11)
            java.lang.Object[] r13 = new java.lang.Object[]{r13, r14}
            com.huawei.hwlogsmodel.LogUtil.c(r4, r13)
            long r13 = java.lang.System.currentTimeMillis()
            int r13 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r13 > 0) goto Lb6
            java.lang.String r0 = "requestAuthorization accessToken is valid expire_in = "
            java.lang.Long r2 = java.lang.Long.valueOf(r11)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r2}
            com.huawei.hwlogsmodel.LogUtil.a(r4, r0)
            r19[r3] = r3
            return
        Lb6:
            com.huawei.hihealthservice.auth.HiAuthorization r11 = defpackage.ivy.e(r5, r2, r7)
            com.huawei.hihealthservice.auth.HiUserAuth r0 = defpackage.ivy.c(r5, r2, r0)
            java.util.List r12 = defpackage.ioy.e(r11)
            boolean r5 = r15.e(r0, r12)
            if (r5 == 0) goto Ld7
            java.lang.String r0 = "requestAuthorization userAuth = null, or permissionTables is null or empty."
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.h(r4, r0)
            r0 = 8
            r19[r3] = r0
            return
        Ld7:
            igd r3 = defpackage.igd.b()
            r3.d(r7, r11, r2)
            igm r2 = defpackage.igm.e()
            r3 = r6
            r4 = r9
            r5 = r10
            r6 = r0
            r7 = r20
            r2.e(r3, r4, r5, r6, r7)
            igd r2 = defpackage.igd.b()
            r2.d(r9, r12, r0, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ifx.b(int, java.lang.String, com.huawei.hihealth.HiAuthorizationOption, int[], ikv):void");
    }

    private boolean e(IAuthorizationListener iAuthorizationListener, String str, int i) {
        if (i == -1) {
            LogUtil.h("HiH_HiHealthBinder", "requestAuthorization appType is invalid");
            igb.e(iAuthorizationListener, 17, null);
            return true;
        }
        if (i == 0) {
            LogUtil.h("HiH_HiHealthBinder", "requestAuthorization do not need requestAuth");
            igb.e(iAuthorizationListener, 0, null);
            return true;
        }
        if (a(str, i)) {
            LogUtil.a("HiH_HiHealthBinder", "requestAuthorization appType = ", Integer.valueOf(i));
            iwk.a();
        }
        return false;
    }

    private boolean b(String str, HiAccountInfo hiAccountInfo, ikv ikvVar) {
        return ikvVar.e() == hiAccountInfo.getAppId() && str.equals(hiAccountInfo.getAccessToken());
    }

    private boolean e(HiUserAuth hiUserAuth, List<igs> list) {
        return HiCommonUtil.d(list) || hiUserAuth == null;
    }

    private boolean a(String str, int i) {
        return i == 1 && ikw.b().d(str) == null;
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void fetchSupportedTypes(int i, ISupportedTypesListener iSupportedTypesListener) throws RemoteException {
        if (c()) {
            LogUtil.c("HiH_HiHealthBinder", "fetchSupportedTypes:", Integer.valueOf(i), " , ", iSupportedTypesListener);
        }
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void subscribeHiHealthData(List list, ISubscribeListener iSubscribeListener) throws RemoteException {
        int c = igd.b().c();
        int d = igm.e().d();
        int d2 = igd.b().d();
        ArrayList arrayList = new ArrayList(10);
        ArrayList arrayList2 = new ArrayList(10);
        if (d2 == -1) {
            LogUtil.h("HiH_HiHealthBinder", "subscribeHiHealthData appType is invalid");
            arrayList.add(17);
            igb.b(iSubscribeListener, (List) null, arrayList);
            return;
        }
        if (d <= 0) {
            LogUtil.h("HiH_HiHealthBinder", "subscribeHiHealthData() who <= 0 ,app = ", Integer.valueOf(c));
            igb.b(iSubscribeListener, arrayList, arrayList2);
            return;
        }
        LogUtil.a("HiH_HiHealthBinder", "subscribeHiHealthData() checkAppType  ", Integer.valueOf(d2), " appId = ", Integer.valueOf(c));
        if (d2 != 0) {
            if (igm.e().c(c) != 0) {
                igb.b(iSubscribeListener, arrayList, arrayList2);
                return;
            }
            try {
                HiAuthManager.getInstance().checkReadAuth(c, d, igd.b().a((List<Integer>) list));
            } catch (HiAuthException e) {
                ReleaseLogUtil.c("HiH_HiHealthBinder", "subscribeHiHealthData() HiAuthException e = ", e.getMessage(), " appId = ", Integer.valueOf(c), " who = ", Integer.valueOf(d));
                igb.b(iSubscribeListener, arrayList, arrayList2);
                return;
            }
        }
        ivg.c().b(list, iSubscribeListener, arrayList, arrayList2);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public boolean addExternalSubscribeTrigger(int i, int i2, HiSubscribeTrigger hiSubscribeTrigger) throws RemoteException {
        int d = igd.b().d();
        LogUtil.c("HiH_HiHealthBinder", "addExternalSubscribeTrigger:", Integer.valueOf(i), " , ", Integer.valueOf(i2), " , ", hiSubscribeTrigger);
        if (d != -1) {
            return false;
        }
        LogUtil.h("HiH_HiHealthBinder", "addExternalSubscribeTrigger appType is invalid");
        return false;
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void unSubscribeHiHealthData(List list, IUnSubscribeListener iUnSubscribeListener) throws RemoteException {
        if (igd.b().d() == -1) {
            LogUtil.h("HiH_HiHealthBinder", "unSubscribeHiHealthData appType is invalid");
            igb.a(iUnSubscribeListener, false);
        } else {
            ivg.c().d(list, iUnSubscribeListener);
        }
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void insertHiHealthData(HiDataInsertOption hiDataInsertOption, IDataOperateListener iDataOperateListener) throws RemoteException {
        ify.e().d(hiDataInsertOption, iDataOperateListener, false, true);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void insertRealTimeHiHealthData(HiDataInsertOption hiDataInsertOption, IDataOperateListener iDataOperateListener) throws RemoteException {
        ify.e().d(hiDataInsertOption, iDataOperateListener, true, true);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void readHiHealthData(HiDataReadOption hiDataReadOption, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        ify.e().e(hiDataReadOption, iDataReadResultListener, true);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void aggregateHiHealthData(HiAggregateOption hiAggregateOption, IAggregateListener iAggregateListener) throws RemoteException {
        ify.e().e(hiAggregateOption, iAggregateListener, true);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void insertFitnessData(String str) {
        WidgetHelperUtils.e().e(str);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void aggregateHiHealthDataEx(List list, IAggregateListenerEx iAggregateListenerEx) throws RemoteException {
        ify.e().a(list, iAggregateListenerEx, true);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void deleteAllKitHealthData(final int i, final IDataOperateListener iDataOperateListener) throws RemoteException {
        final int[] iArr = {0};
        final ArrayList<Object> arrayList = new ArrayList<>(10);
        arrayList.add(HiErrorCode.d(0));
        if (b(i, iDataOperateListener, arrayList)) {
            return;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        final ikv e = igd.b().e();
        if (d(iDataOperateListener, arrayList)) {
            return;
        }
        this.e.execute(new SingleRunnable() { // from class: ifx.3
            @Override // com.huawei.hihealthservice.runnable.SingleRunnable
            public void doRun() {
                IDataOperateListener iDataOperateListener2;
                int i2;
                try {
                    try {
                        ifx.this.d(iArr, i, arrayList, e);
                        iDataOperateListener2 = iDataOperateListener;
                        i2 = iArr[0];
                    } catch (Exception e2) {
                        ReleaseLogUtil.c("HiH_HiHealthBinder", "deleteAllKitHealthData() Exception:", LogAnonymous.b((Throwable) e2));
                        iArr[0] = 2;
                        arrayList.add(HiErrorCode.d(iArr[0]) + "delete exception");
                        iDataOperateListener2 = iDataOperateListener;
                        i2 = iArr[0];
                    }
                    igb.b(iDataOperateListener2, i2, arrayList);
                    LogUtil.a("HiH_HiHealthBinder", "deleteAllKitHealthData() end totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                } catch (Throwable th) {
                    igb.b(iDataOperateListener, iArr[0], arrayList);
                    throw th;
                }
            }
        });
    }

    private boolean b(int i, IDataOperateListener iDataOperateListener, ArrayList<Object> arrayList) {
        if (i > 0) {
            return false;
        }
        LogUtil.b("HiH_HiHealthBinder", "deleteAllKitHealthData() appId <= 0");
        igb.b(iDataOperateListener, 7, arrayList);
        return true;
    }

    private boolean d(IDataOperateListener iDataOperateListener, ArrayList<Object> arrayList) {
        if (igd.b().d() == 0) {
            return false;
        }
        LogUtil.a("HiH_HiHealthBinder", "deleteHiHealthData() appType is invalid");
        igb.b(iDataOperateListener, 17, arrayList);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int[] iArr, int i, ArrayList<Object> arrayList, ikv ikvVar) {
        iArr[0] = isa.d(this.c).a(i);
        isf.a(this.c).doRealTimeHealthDataStat();
        ReleaseLogUtil.e("HiH_HiHealthBinder", "deleteAllKitHealthData() errorCode = ", Integer.valueOf(iArr[0]));
        arrayList.add(HiErrorCode.d(iArr[0]));
        ivg.c().e(iwd.b(new int[]{10001, 10002}), ArkUIXConstants.DELETE, ikvVar);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void deleteHiHealthData(HiDataDeleteOption hiDataDeleteOption, IDataOperateListener iDataOperateListener) throws RemoteException {
        ify.e().b(hiDataDeleteOption, iDataOperateListener, false, true);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void updateHiHealthData(HiDataUpdateOption hiDataUpdateOption, IDataOperateListener iDataOperateListener) throws RemoteException {
        long currentTimeMillis = System.currentTimeMillis();
        int d = igd.b().d();
        ArrayList arrayList = new ArrayList(10);
        if (d == -1) {
            LogUtil.a("HiH_HiHealthBinder", "updateHiHealthData() appType is invalid");
            if (iDataOperateListener != null) {
                arrayList.add(HiErrorCode.d(17));
                iDataOperateListener.onResult(17, arrayList);
                return;
            }
            return;
        }
        if (hiDataUpdateOption == null) {
            if (iDataOperateListener != null) {
                arrayList.add(HiErrorCode.d(18));
                iDataOperateListener.onResult(18, arrayList);
                return;
            }
            return;
        }
        int type = hiDataUpdateOption.getType();
        LogUtil.a("HiH_HiHealthBinder", "updateHiHealthData() type is ", Integer.valueOf(type));
        if (type == 101 && !igd.b().i()) {
            LogUtil.a("HiH_HiHealthBinder", "updateHiHealthData() do default user login ");
            igd.b().f(ivw.a(this.c));
            if (iDataOperateListener != null) {
                arrayList.add(HiErrorCode.d(0));
                iDataOperateListener.onResult(0, arrayList);
                return;
            }
        }
        UpdateCommand a2 = ivn.a(type);
        if (a2 != null) {
            a2.execute(hiDataUpdateOption, iDataOperateListener, this.c);
            return;
        }
        if (iDataOperateListener != null) {
            arrayList.add(HiErrorCode.d(18));
            iDataOperateListener.onResult(18, arrayList);
        }
        LogUtil.a("HiH_HiHealthBinder", "updateHiHealthData() end totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    @Override // com.huawei.hihealth.IHiHealth
    public int addHiHealthDataCustomType(String str) throws RemoteException {
        LogUtil.c("HiH_HiHealthBinder", "addHiHealthDataCustomType:", str);
        return !c() ? -2 : 0;
    }

    @Override // com.huawei.hihealth.IHiHealth
    public int fetchHiHealthDataCustomType(String str) throws RemoteException {
        LogUtil.c("HiH_HiHealthBinder", "fetchHiHealthDataCustomType:", str);
        return !c() ? -2 : 0;
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void registerDataClient(final HiDeviceInfo hiDeviceInfo, final List list, final IRegisterClientListener iRegisterClientListener) throws RemoteException {
        if (igd.b().d() == -1) {
            ReleaseLogUtil.e("HiH_HiHealthBinder", "registerDataClient() appType is invalid");
            igb.a(iRegisterClientListener, (HiHealthClient) null);
            return;
        }
        final int a2 = igd.b().a();
        if (!iwv.e(hiDeviceInfo)) {
            LogUtil.b("HiH_HiHealthBinder", "device is invalid, deviceInfo=", hiDeviceInfo);
            igb.a(iRegisterClientListener, (HiHealthClient) null);
        } else {
            this.e.execute(new SingleRunnable() { // from class: ifx.1
                @Override // com.huawei.hihealthservice.runnable.SingleRunnable
                public void doRun() {
                    LogUtil.a("HiH_HiHealthBinder", "registerDataClient() deviceInfo = ", hiDeviceInfo, ",supportedList = ", list, " appId is ", Integer.valueOf(a2));
                    HiHealthClient saveDeviceInfo = isb.e(ifx.this.c).saveDeviceInfo(hiDeviceInfo, a2);
                    LogUtil.a("HiH_HiHealthBinder", "registerDataClient() client = ", saveDeviceInfo);
                    igb.a(iRegisterClientListener, saveDeviceInfo);
                }
            });
        }
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void registerDataClientWithUserInfo(final HiDeviceInfo hiDeviceInfo, final HiUserInfo hiUserInfo, final List list, final IRegisterClientListener iRegisterClientListener) throws RemoteException {
        if (igd.b().d() == -1) {
            LogUtil.a("HiH_HiHealthBinder", "registerDataClientWithUserInfo() appType is invalid");
            igb.a(iRegisterClientListener, (HiHealthClient) null);
        } else if (!iwv.e(hiDeviceInfo)) {
            LogUtil.b("HiH_HiHealthBinder", "device is invalid, deviceInfo=", hiDeviceInfo);
            igb.a(iRegisterClientListener, (HiHealthClient) null);
        } else {
            final int a2 = igd.b().a();
            this.e.execute(new SingleRunnable() { // from class: ifx.5
                @Override // com.huawei.hihealthservice.runnable.SingleRunnable
                public void doRun() {
                    LogUtil.c("HiH_HiHealthBinder", "registerDataClientWithUserInfo() deviceInfo = ", hiDeviceInfo, ", userid = ", hiUserInfo, ",supportedList = ", list);
                    HiHealthClient saveDeviceInfoWithUserInfo = isb.e(ifx.this.c).saveDeviceInfoWithUserInfo(hiDeviceInfo, hiUserInfo, a2);
                    LogUtil.c("HiH_HiHealthBinder", "registerDataClientWithUserInfo() client = ", saveDeviceInfoWithUserInfo);
                    igb.a(iRegisterClientListener, saveDeviceInfoWithUserInfo);
                }
            });
        }
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void fetchBuildInDataClient(IDataClientListener iDataClientListener) throws RemoteException {
        if (igd.b().d() == -1) {
            LogUtil.a("HiH_HiHealthBinder", "fetchBuildInDataClient() appType is invalid");
            igb.c(iDataClientListener, (List) null);
        } else {
            LogUtil.a("HiH_HiHealthBinder", "fetchBuildInDataClient");
            igb.c(iDataClientListener, isb.e(this.c).getHealthClientList(FoundationCommonUtil.getAndroidId(this.c), igd.b().a()));
        }
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void fetchManualDataClient(IDataClientListener iDataClientListener) throws RemoteException {
        if (igd.b().d() == -1) {
            LogUtil.a("HiH_HiHealthBinder", "fetchManualDataClient() appType is invalid");
            igb.c(iDataClientListener, (List) null);
        } else {
            LogUtil.a("HiH_HiHealthBinder", "fetchManualDataClient");
            igb.c(iDataClientListener, isb.e(this.c).getHealthClientList("-1", igd.b().a()));
        }
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void fetchPhoneDataClient(IDataClientListener iDataClientListener) throws RemoteException {
        if (igd.b().d() == -1) {
            LogUtil.a("HiH_HiHealthBinder", "fetchPhoneDataClient() appType is invalid");
            igb.c(iDataClientListener, (List) null);
        } else {
            LogUtil.a("HiH_HiHealthBinder", "fetchPhoneDataClient");
            igb.c(iDataClientListener, isb.e(this.c).getHealthClientList(FoundationCommonUtil.getAndroidId(this.c), igd.b().a()));
        }
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void fetchRegisteredDataClient(int i, IDataClientListener iDataClientListener) throws RemoteException {
        if (igd.b().d() == -1) {
            LogUtil.a("HiH_HiHealthBinder", "fetchRegisteredDataClient() appType is invalid");
            igb.c(iDataClientListener, (List) null);
        } else {
            LogUtil.a("HiH_HiHealthBinder", "fetchRegisteredDataClient");
            igb.c(iDataClientListener, isb.e(this.c).getAllHealthClientList(igd.b().a()));
        }
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void fetchDataClientByUniqueID(int i, String str, IDataClientListener iDataClientListener) throws RemoteException {
        if (igd.b().d() == -1) {
            LogUtil.a("HiH_HiHealthBinder", "fetchDataClientByUniqueID() appType is invalid");
            igb.c(iDataClientListener, (List) null);
            return;
        }
        LogUtil.a("HiH_HiHealthBinder", "fetchDataClientByUniqueID");
        if (HiCommonUtil.b(str)) {
            LogUtil.h("HiH_HiHealthBinder", "fetchDataClientByUniqueID uniqueID = null");
            igb.c(iDataClientListener, (List) null);
        } else {
            igb.c(iDataClientListener, isb.e(this.c).getHealthClientList(str, igd.b().a()));
        }
    }

    @Override // com.huawei.hihealth.IHiHealth
    public HiHealthUnit fetchPreferUnit(int i) throws RemoteException {
        LogUtil.c("HiH_HiHealthBinder", "fetchPreferUnit:", Integer.valueOf(i));
        if (c()) {
            return new HiHealthUnit();
        }
        return null;
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void setPreferUnit(int i, HiHealthUnit hiHealthUnit) throws RemoteException {
        LogUtil.c("HiH_HiHealthBinder", "setPreferUnit:", Integer.valueOf(i), " , ", hiHealthUnit);
        c();
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void fetchDataSourceByType(int i, HiTimeInterval hiTimeInterval, IDataClientListener iDataClientListener) throws RemoteException {
        ify.e().a(i, hiTimeInterval, iDataClientListener);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void fetchDataSourceByTypes(List list, HiTimeInterval hiTimeInterval, boolean z, IMultiDataClientListener iMultiDataClientListener) throws RemoteException {
        ify.e().c(list, hiTimeInterval, z, iMultiDataClientListener);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void setUserData(final HiUserInfo hiUserInfo, final ICommonListener iCommonListener) throws RemoteException {
        final long currentTimeMillis = System.currentTimeMillis();
        final ArrayList<Boolean> arrayList = new ArrayList<>(10);
        arrayList.add(false);
        if (hiUserInfo == null) {
            LogUtil.h("HiH_HiHealthBinder", "userInfo is null!");
            igb.c(iCommonListener, 14, arrayList);
            return;
        }
        int d = igd.b().d();
        if (hiUserInfo.getUser() != 1073741824) {
            if (d != -1) {
                b(hiUserInfo, iCommonListener, arrayList);
            }
        } else if (hiUserInfo.getModifiedIntent() == 0) {
            LogUtil.h("HiH_HiHealthBinder", "error set ALL data to UserInfo,user:", Integer.valueOf(hiUserInfo.getUser()), " modifiedIntent", Integer.valueOf(hiUserInfo.getModifiedIntent()));
            igb.c(iCommonListener, 14, arrayList);
        } else if (d == -1) {
            LogUtil.a("HiH_HiHealthBinder", "setUserData() appType is invalid");
            igb.c(iCommonListener, 17, arrayList);
        } else {
            final ikv e = igd.b().e();
            this.e.execute(new SingleRunnable() { // from class: ifx.9
                @Override // com.huawei.hihealthservice.runnable.SingleRunnable
                public void doRun() {
                    LogUtil.a("HiH_HiHealthBinder", "setUserData userInfo = ", hiUserInfo, " package is ", e.f());
                    long userData = igm.e().setUserData(hiUserInfo, e);
                    if (userData > 0) {
                        LogUtil.a("HiH_HiHealthBinder", "setUserData() success , result = ", Long.valueOf(userData));
                        HiBroadcastUtil.c(ifx.this.c, 5);
                        ivg.c().a(100, "setUserData", e);
                        arrayList.set(0, true);
                        igb.b(iCommonListener, (int) userData, arrayList);
                        HiBroadcastUtil.d(ifx.this.c, 5);
                    } else {
                        LogUtil.a("HiH_HiHealthBinder", "setUserData() fail , result = ", Long.valueOf(userData));
                        igb.c(iCommonListener, 14, arrayList);
                    }
                    LogUtil.a("HiH_HiHealthBinder", "setUserData() end! cost time is ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                }
            });
        }
    }

    private void b(HiUserInfo hiUserInfo, ICommonListener iCommonListener, ArrayList<Boolean> arrayList) {
        LogUtil.c("HiH_HiHealthBinder", "setUserData caller:", Integer.valueOf(Binder.getCallingPid()));
        UUID randomUUID = UUID.randomUUID();
        this.d.d(randomUUID.toString(), iCommonListener);
        Intent intent = new Intent();
        intent.setClassName(this.c.getPackageName(), "com.huawei.featureuserprofile.UserInfoModifyService");
        intent.setAction("modifyUserData");
        Bundle bundle = new Bundle();
        bundle.putString("uuid", randomUUID.toString());
        bundle.putParcelable("userInfo", hiUserInfo);
        intent.putExtras(bundle);
        ReleaseLogUtil.e("HiH_HiHealthBinder", "setUserData componentName ", this.c.startService(intent));
        arrayList.set(0, true);
        igb.b(iCommonListener, 0, arrayList);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void fetchUserData(ICommonListener iCommonListener) throws RemoteException {
        ify.e().c(iCommonListener, true);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void synCloud(HiSyncOption hiSyncOption, ICommonListener iCommonListener) throws RemoteException {
        int c = igd.b().c(HuaweiHealth.b());
        ArrayList arrayList = new ArrayList(10);
        if (igd.b().d() == -1) {
            LogUtil.a("HiH_HiHealthBinder", "synCloud() appType is invalid");
            arrayList.add(false);
            igb.c(iCommonListener, 17, arrayList);
        } else {
            a(hiSyncOption.getStackTrace());
            if (iuz.a() && !HiCommonUtil.f13122a) {
                d(hiSyncOption, c);
            } else {
                b(hiSyncOption, c);
            }
        }
    }

    private void d(final HiSyncOption hiSyncOption, final int i) {
        HandlerCenter.d().e(new Runnable() { // from class: ifu
            @Override // java.lang.Runnable
            public final void run() {
                ifx.this.b(hiSyncOption, i);
            }
        }, iuz.b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void b(final HiSyncOption hiSyncOption, final int i) {
        final boolean equals = HuaweiHealth.b().equals(ivw.a(this.c));
        final boolean equals2 = "com.huawei.bone".equals(ivw.a(this.c));
        this.e.execute(new SingleRunnable() { // from class: ifx.6
            @Override // com.huawei.hihealthservice.runnable.SingleRunnable
            public void doRun() {
                LogUtil.a("HiH_HiHealthBinder", "synCloud");
                iso.e(ifx.this.c).e();
                if (equals) {
                    iwe.g(ifx.this.c, iwe.j(ifx.this.c) + 1);
                } else if (equals2) {
                    iwe.f(ifx.this.c, iwe.i(ifx.this.c) + 1);
                } else {
                    LogUtil.c("HiH_HiHealthBinder", "appType is invalid");
                }
                ism.f().a(hiSyncOption, i);
            }
        });
    }

    private void a(String str) {
        if (CommonUtil.as()) {
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
            linkedHashMap.put("dev_manufacturer", Build.MANUFACTURER);
            linkedHashMap.put("sync_minute", String.valueOf(jdl.l(System.currentTimeMillis())));
            linkedHashMap.put(CrashHianalyticsData.STACK_TRACE, str);
            ivz.d(BaseApplication.e()).e(OperationKey.HEALTH_APP_SYNC_CLOUD_TRIGGERED_2129021.value(), linkedHashMap, false);
        }
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void checkDataStatus(List list, ICommonListener iCommonListener) throws RemoteException {
        ArrayList arrayList = new ArrayList(10);
        if (igd.b().d() == -1) {
            LogUtil.a("HiH_HiHealthBinder", "checkDataStatus() appType is invalid");
            arrayList.add(false);
            igb.c(iCommonListener, 17, arrayList);
            return;
        }
        LogUtil.a("HiH_HiHealthBinder", "checkDataStatus");
        if (HiCommonUtil.d(list)) {
            LogUtil.h("HiH_HiHealthBinder", "checkDataStatus healthTypes is null");
            arrayList.add(false);
            igb.c(iCommonListener, 7, arrayList);
            return;
        }
        int d = ijl.d(this.c, igd.b().a());
        if (d <= 0) {
            LogUtil.h("HiH_HiHealthBinder", "checkDataStatus error who is ", Integer.valueOf(d));
            arrayList.add(false);
            igb.c(iCommonListener, 2, arrayList);
        } else if (iuz.e(this.c, d, (List<Integer>) list)) {
            arrayList.add(true);
            igb.b(iCommonListener, 0, arrayList);
        } else {
            LogUtil.a("HiH_HiHealthBinder", "checkDataStatus() fail");
            arrayList.add(false);
            igb.c(iCommonListener, 0, arrayList);
        }
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void synCloudCancel() throws RemoteException {
        if (c()) {
            LogUtil.c("HiH_HiHealthBinder", "synCloudCancel");
        }
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void hiLogin(HiAccountInfo hiAccountInfo, ICommonListener iCommonListener) throws RemoteException {
        ify.e().c(hiAccountInfo, iCommonListener);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void hiLogout(ICommonListener iCommonListener) throws RemoteException {
        ify.e().e(iCommonListener);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void fetchAccountInfo(ICommonListener iCommonListener) throws RemoteException {
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList(10);
        if (igd.b().d() == -1) {
            LogUtil.a("HiH_HiHealthBinder", "fetchAccountInfo() appType is invalid");
            arrayList.add(false);
            igb.c(iCommonListener, 17, arrayList);
            return;
        }
        HiAccountInfo fetchAccountInfo = igm.e().fetchAccountInfo(igd.b().e());
        if (fetchAccountInfo != null) {
            ArrayList arrayList2 = new ArrayList(10);
            arrayList2.add(fetchAccountInfo);
            igb.b(iCommonListener, 0, arrayList2);
        } else {
            LogUtil.a("HiH_HiHealthBinder", "fetchAccountInfo() fail ");
            arrayList.add(false);
            igb.c(iCommonListener, 15, arrayList);
        }
        LogUtil.a("HiH_HiHealthBinder", "fetchAccountInfo() end! totalTime is ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void setGoalInfo(final int i, final List list, final ICommonListener iCommonListener) throws RemoteException {
        final long currentTimeMillis = System.currentTimeMillis();
        final ikv e = igd.b().e();
        final ArrayList arrayList = new ArrayList(10);
        if (igd.b().d() == -1) {
            LogUtil.a("HiH_HiHealthBinder", "setGoalInfo() appType is invalid");
            arrayList.add(false);
            igb.c(iCommonListener, 17, arrayList);
            return;
        }
        this.e.execute(new SingleRunnable() { // from class: ifx.8
            @Override // com.huawei.hihealthservice.runnable.SingleRunnable
            public void doRun() {
                LogUtil.c("HiH_HiHealthBinder", "setGoalInfo goalInfos = ", list, "userId = ", Integer.valueOf(i));
                List list2 = list;
                if (list2 == null || list2.isEmpty()) {
                    arrayList.add(false);
                    igb.c(iCommonListener, 7, arrayList);
                    return;
                }
                boolean z = false;
                for (Object obj : list) {
                    if (obj instanceof HiGoalInfo) {
                        z = ifx.this.e(z, (HiGoalInfo) obj, i, e);
                    }
                }
                LogUtil.a("HiH_HiHealthBinder", "setGoalInfo flag = ", Boolean.valueOf(z));
                if (z) {
                    HiBroadcastUtil.c(ifx.this.c, 6);
                    ivg.c().a(101, "setGoalInfo", e);
                    arrayList.add(true);
                    igb.b(iCommonListener, 0, arrayList);
                } else {
                    LogUtil.a("HiH_HiHealthBinder", "setGoalInfo() fail");
                    arrayList.add(false);
                    igb.c(iCommonListener, 16, arrayList);
                }
                LogUtil.a("HiH_HiHealthBinder", "setGoalInfo() end! totalTime is ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(boolean z, HiGoalInfo hiGoalInfo, int i, ikv ikvVar) {
        if (igm.e().setGoalInfo(i, ikvVar.e(), hiGoalInfo)) {
            return true;
        }
        return z;
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void fetchGoalInfo(int i, int i2, ICommonListener iCommonListener) throws RemoteException {
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList(10);
        if (igd.b().d() == -1) {
            ReleaseLogUtil.e("HiH_HiHealthBinder", "fetchGoalInfo() appType is invalid");
            arrayList.add(false);
            igb.c(iCommonListener, 17, arrayList);
            return;
        }
        ReleaseLogUtil.e("HiH_HiHealthBinder", "fetchGoalInfo who = ", Integer.valueOf(i));
        List<HiGoalInfo> goalInfo = igm.e().getGoalInfo(i, i2, igd.b().a());
        if (goalInfo != null) {
            igb.b(iCommonListener, 0, goalInfo);
        } else {
            LogUtil.a("HiH_HiHealthBinder", "fetchGoalInfo() fail");
            arrayList.add(false);
            igb.c(iCommonListener, 16, arrayList);
        }
        ReleaseLogUtil.e("HiH_HiHealthBinder", " fetchGoalInfo() end! totalTime is ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    @Override // com.huawei.hihealth.IHiHealth
    public boolean setUserPreference(HiUserPreference hiUserPreference, boolean z) throws RemoteException {
        if (igd.b().d() == -1) {
            ReleaseLogUtil.e("HiH_HiHealthBinder", "setUserPreference appTp=invalid");
            return false;
        }
        if (hiUserPreference == null) {
            ReleaseLogUtil.d("HiH_HiHealthBinder", "setUserPreference userPreference=null");
            return false;
        }
        ikv e = igd.b().e();
        int d = igm.e().d();
        if (d <= 0) {
            ReleaseLogUtil.d("HiH_HiHealthBinder", "setUserPreference userID<=0 appID=", Integer.valueOf(e.e()));
            return false;
        }
        hiUserPreference.setUserId(d);
        boolean b2 = ijy.c(this.c).b(hiUserPreference);
        if ("custom.wear_common_setting".equals(hiUserPreference.getKey())) {
            LogUtil.a("HiH_HiHealthBinder", "wear setting is ", hiUserPreference, ", isSuccess=", Boolean.valueOf(b2));
        }
        ReleaseLogUtil.e("HiH_HiHealthBinder", "setUserPreference result=", Boolean.valueOf(b2));
        if (b2 && z) {
            HiBroadcastUtil.a(this.c, hiUserPreference.getKey());
            ivg.c().a(102, hiUserPreference.getKey(), e);
            HiBroadcastUtil.d(this.c, 7);
        }
        return b2;
    }

    @Override // com.huawei.hihealth.IHiHealth
    @Deprecated
    public HiUserPreference getUserPreference(String str) throws RemoteException {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(str);
        List<HiUserPreference> userPreferenceList = getUserPreferenceList(arrayList);
        if (HiCommonUtil.d(userPreferenceList)) {
            return new HiUserPreference();
        }
        return userPreferenceList.get(0);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public List<HiUserPreference> getUserPreferenceList(List<String> list) throws RemoteException {
        if (igd.b().d() == -1) {
            ReleaseLogUtil.d("HiH_HiHealthBinder", "getUserPreferenceList appTp=invalid");
            return new ArrayList(0);
        }
        if (HiCommonUtil.d(list)) {
            ReleaseLogUtil.d("HiH_HiHealthBinder", "getUserPreferenceList keys=null");
            return new ArrayList(0);
        }
        int a2 = igd.b().a();
        int d = igm.e().d();
        long currentTimeMillis = System.currentTimeMillis();
        if (d <= 0) {
            ReleaseLogUtil.d("HiH_HiHealthBinder", "getUserPreferenceList who<=0 app=", Integer.valueOf(a2));
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(10);
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(ijy.c(this.c).a(d, it.next()));
        }
        ReleaseLogUtil.e("HiH_HiHealthBinder", "getUPList ", HiJsonUtil.e(list), " CT=", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return arrayList;
    }

    @Override // com.huawei.hihealth.IHiHealth
    public boolean checkHiHealthLogin(String str) {
        if (!igd.b().i()) {
            return true;
        }
        boolean a2 = ijl.a(this.c, str);
        LogUtil.c("HiH_HiHealthBinder", "checkHiHealthLogin isLogin = ", Boolean.valueOf(a2));
        return a2;
    }

    @Override // com.huawei.hihealth.IHiHealth
    public int getHiHealthVersionCode() {
        try {
            if (!c()) {
                return -2;
            }
            LogUtil.a("HiH_HiHealthBinder", "getHiHealthVersionCode = ", 20000001);
            return 20000001;
        } catch (RemoteException e) {
            ReleaseLogUtil.d("HiH_HiHealthBinder", "getHiHealthVersionCode : ", e.getMessage());
            return -2;
        }
    }

    public void a() {
        ReleaseLogUtil.e("HiH_HiHealthBinder", "onDestroy ");
        try {
            if (c()) {
                isf.a(this.c).onDestroy();
                if (ivg.c() != null) {
                    ivg.c().a();
                }
                ivm ivmVar = this.d;
                if (ivmVar != null) {
                    ivmVar.e();
                }
                igm.e().b();
            }
        } catch (RemoteException e) {
            ReleaseLogUtil.d("HiH_HiHealthBinder", "onDestroy : ", e.getMessage());
        }
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void readDeviceInfo(IDataHiDeviceInfoListener iDataHiDeviceInfoListener) throws RemoteException {
        if (iDataHiDeviceInfoListener == null) {
            LogUtil.h("HiH_HiHealthBinder", "readDeviceInfo listener null");
            return;
        }
        LogUtil.a("HiH_HiHealthBinder", "readDeviceInfo enter");
        int e = ijz.c().e(iio.c().a(iip.b().a(this.c.getPackageName())), 0);
        List<Integer> c = iis.d().c(e);
        if (HiCommonUtil.d(c)) {
            LogUtil.a("HiH_HiHealthBinder", "getAllHealthClientList() deviceIDs is null who = ", Integer.valueOf(e));
        }
        List<HiDeviceInfo> b2 = ijf.d(this.c).b(c);
        if (b2 == null || b2.isEmpty()) {
            LogUtil.a("HiH_HiHealthBinder", "getAllHealthClientList() deviceInfos is null devices = ", c);
        }
        LogUtil.a("HiH_HiHealthBinder", "readDeviceInfo deviceInfos = ", b2);
        iDataHiDeviceInfoListener.onResult(b2);
    }

    public boolean c() throws RemoteException {
        return igd.b().d() != -1;
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void queryKitAppInfo(IDataReadResultListener iDataReadResultListener) throws RemoteException {
        LogUtil.a("HiH_HiHealthBinder", "queryKitAppInfo enter");
        if (iDataReadResultListener == null) {
            return;
        }
        if (!c()) {
            LogUtil.h("HiH_HiHealthBinder", "queryKitAppInfo !checkPermission()");
            iDataReadResultListener.onResult(null, -1, -1);
            return;
        }
        List<HiHealthUserPermission> c = ijm.e(this.c).c();
        List<HiAppInfo> c2 = iip.b().c();
        if (koq.b(c) || koq.b(c2)) {
            iDataReadResultListener.onResult(null, 0, 0);
            LogUtil.a("HiH_HiHealthBinder", "queryKitAppInfo userPermissions appInfoList null");
            return;
        }
        ArrayList arrayList = new ArrayList(10);
        for (HiHealthUserPermission hiHealthUserPermission : c) {
            if (hiHealthUserPermission != null && !arrayList.contains(Integer.valueOf(hiHealthUserPermission.getAppId()))) {
                arrayList.add(Integer.valueOf(hiHealthUserPermission.getAppId()));
            }
        }
        ArrayList arrayList2 = new ArrayList(10);
        for (HiAppInfo hiAppInfo : c2) {
            if (hiAppInfo != null && arrayList.contains(Integer.valueOf(hiAppInfo.getAppId()))) {
                arrayList2.add(hiAppInfo);
                arrayList.remove(Integer.valueOf(hiAppInfo.getAppId()));
            }
        }
        LogUtil.c("HiH_HiHealthBinder", "queryKitAppInfo finalAppList size = ", Integer.valueOf(arrayList2.size()));
        iDataReadResultListener.onResult(arrayList2, 0, 0);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void queryHealthKitPermission(int i, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        LogUtil.a("HiH_HiHealthBinder", "queryHealthKitPermission enter");
        if (iDataReadResultListener == null) {
            LogUtil.h("HiH_HiHealthBinder", "queryHealthKitPermission listener null");
        } else if (!c()) {
            LogUtil.h("HiH_HiHealthBinder", "queryHealthKitPermission !checkPermission()");
            iDataReadResultListener.onResult(null, -1, -1);
        } else {
            iDataReadResultListener.onResult(ijm.e(this.c).b(i), 0, 0);
        }
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void updateHealthKitPermission(int i, int i2, int i3, boolean z, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        LogUtil.a("HiH_HiHealthBinder", "updateHealthKitPermission enter");
        if (iDataReadResultListener == null) {
            LogUtil.h("HiH_HiHealthBinder", "updateHealthKitPermission listener null");
            return;
        }
        if (!c()) {
            LogUtil.h("HiH_HiHealthBinder", "updateHealthKitPermission !checkPermission()");
            iDataReadResultListener.onResult(null, -1, -1);
            return;
        }
        ijm.e(this.c).e(i, i2, i3, z);
        iDataReadResultListener.onResult(null, 0, 0);
        if (z || !b.contains(Integer.valueOf(i2))) {
            return;
        }
        LogUtil.a("HiH_HiHealthBinder", "updateHealthKitPermission appId = ", Integer.valueOf(i), " scopeId = ", Integer.valueOf(i2));
        HiAppInfo c = iip.b().c(i);
        if (c == null) {
            iDataReadResultListener.onResult(null, -1, -1);
            LogUtil.h("HiH_HiHealthBinder", "updateHealthKitPermission hiAppInfo null");
        } else {
            irh.a(i2, c.getPackageName(), this.c);
        }
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void queryWearKitAppInfo(IDataReadResultListener iDataReadResultListener) throws RemoteException {
        LogUtil.a("HiH_HiHealthBinder", "queryKitAppInfo enter");
        if (iDataReadResultListener == null) {
            LogUtil.h("HiH_HiHealthBinder", "queryWearKitAppInfo callback null");
            return;
        }
        if (!c()) {
            LogUtil.h("HiH_HiHealthBinder", "queryKitAppInfo !checkPermission()");
            iDataReadResultListener.onResult(null, -1, -1);
            return;
        }
        List<WearKitPermission> c = ikc.b().c();
        List<HiAppInfo> c2 = iip.b().c();
        if (koq.b(c) || koq.b(c2)) {
            iDataReadResultListener.onResult(null, 0, 0);
            LogUtil.a("HiH_HiHealthBinder", "queryKitAppInfo userPermissions appInfoList null");
            return;
        }
        ArrayList arrayList = new ArrayList(10);
        for (WearKitPermission wearKitPermission : c) {
            if (wearKitPermission != null && !arrayList.contains(Integer.valueOf(wearKitPermission.getAppId()))) {
                arrayList.add(Integer.valueOf(wearKitPermission.getAppId()));
            }
        }
        ArrayList arrayList2 = new ArrayList(10);
        for (HiAppInfo hiAppInfo : c2) {
            if (hiAppInfo != null && arrayList.contains(Integer.valueOf(hiAppInfo.getAppId()))) {
                arrayList2.add(hiAppInfo);
                arrayList.remove(Integer.valueOf(hiAppInfo.getAppId()));
            }
        }
        LogUtil.c("HiH_HiHealthBinder", "queryKitAppInfo finalAppList size = ", Integer.valueOf(arrayList2.size()));
        iDataReadResultListener.onResult(arrayList2, 0, 0);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void queryWearKitPermission(int i, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        LogUtil.a("HiH_HiHealthBinder", "queryWearKitPermission enter");
        if (iDataReadResultListener == null) {
            LogUtil.h("HiH_HiHealthBinder", "queryWearKitPermission listener null");
        } else if (!c()) {
            LogUtil.h("HiH_HiHealthBinder", "queryWearKitPermission !checkPermission()");
            iDataReadResultListener.onResult(null, -1, -1);
        } else {
            iDataReadResultListener.onResult(ikc.b().b(i), 0, 0);
        }
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void updateWearKitPermission(int i, int i2, boolean z, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        LogUtil.a("HiH_HiHealthBinder", "updateWearKitPermission enter");
        if (iDataReadResultListener == null) {
            LogUtil.h("HiH_HiHealthBinder", "updateWearKitPermission listener null");
        } else if (!c()) {
            LogUtil.h("HiH_HiHealthBinder", "updateWearKitPermission !checkPermission()");
            iDataReadResultListener.onResult(null, -1, -1);
        } else {
            ikc.b().a(i, i2, z);
            iDataReadResultListener.onResult(null, 0, 0);
        }
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void setBinder(String str, IBinder iBinder, ICommonCallback iCommonCallback) throws RemoteException {
        LogUtil.a("HiH_HiHealthBinder", "setBinder enter");
        if (iCommonCallback == null) {
            LogUtil.h("HiH_HiHealthBinder", "setBinder listener null");
            return;
        }
        char c = 65535;
        if (!c()) {
            LogUtil.h("HiH_HiHealthBinder", "setBinder !checkPermission()");
            iCommonCallback.onResult(-1, "permission denied");
            return;
        }
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode != -1883740319) {
            if (hashCode != -831090986) {
                if (hashCode == -511440464 && str.equals("KitWearBinderFromPhoneService")) {
                    c = 2;
                }
            } else if (str.equals("TrackDeveloperService")) {
                c = 1;
            }
        } else if (str.equals("HealthCapabilityService")) {
            c = 0;
        }
        if (c == 0) {
            ikx.a(this.c).bBA_(iBinder);
            iCommonCallback.onResult(0, MonitorResult.SUCCESS);
        } else if (c == 1) {
            ino.b(this.c).bBQ_(iBinder);
            iCommonCallback.onResult(0, MonitorResult.SUCCESS);
        } else {
            if (c != 2) {
                return;
            }
            ins.a(this.c).bBT_(iBinder);
            iCommonCallback.onResult(0, MonitorResult.SUCCESS);
        }
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void fetchSportTypeList(HiDataReadOption hiDataReadOption, ICommonListener iCommonListener) throws RemoteException {
        ify.e().c(hiDataReadOption, iCommonListener, true);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void fetchSequenceDataBySportType(HiDataReadOption hiDataReadOption, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        ify.e().c(hiDataReadOption, iDataReadResultListener, true);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void aggregateSportStatData(HiSportStatDataAggregateOption hiSportStatDataAggregateOption, IAggregateListener iAggregateListener) throws RemoteException {
        ify.e().c(hiSportStatDataAggregateOption, iAggregateListener, true);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void fetchDataSource(HiDataSourceFetchOption hiDataSourceFetchOption, IDataClientListener iDataClientListener) throws RemoteException {
        ify.e().b(hiDataSourceFetchOption, iDataClientListener, true);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void readHiHealthDataPro(HiDataReadProOption hiDataReadProOption, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        ify.e().e(hiDataReadProOption, iDataReadResultListener, true);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void readHiHealthDataEx(List list, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        ify.e().a(list, iDataReadResultListener, true);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void aggregateHiHealthDataPro(HiDataAggregateProOption hiDataAggregateProOption, IAggregateListener iAggregateListener) throws RemoteException {
        ify.e().b(hiDataAggregateProOption, iAggregateListener, true);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void aggregateHiHealthDataProEx(List list, IAggregateListenerEx iAggregateListenerEx) throws RemoteException {
        ify.e().b((List<HiDataAggregateProOption>) list, iAggregateListenerEx, true);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void deleteHiHealthDataPro(HiDataDeleteProOption hiDataDeleteProOption, IDataOperateListener iDataOperateListener) throws RemoteException {
        ify.e().e(hiDataDeleteProOption, iDataOperateListener, false, true);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void deleteHiHealthDataProEx(List list, IDeleteListenerEx iDeleteListenerEx) throws RemoteException {
        ify.e().e((List<HiDataDeleteProOption>) list, iDeleteListenerEx, false, true);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void setSampleConfig(List list, IDataOperateListener iDataOperateListener) throws RemoteException {
        ify.e().e((List<HiSampleConfig>) list, iDataOperateListener, true);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void getSampleConfig(HiSampleConfigProcessOption hiSampleConfigProcessOption, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        ify.e().a(hiSampleConfigProcessOption, iDataReadResultListener, true);
    }

    @Override // com.huawei.hihealth.IHiHealth
    public void deleteSampleConfig(HiSampleConfigProcessOption hiSampleConfigProcessOption, IDataOperateListener iDataOperateListener) throws RemoteException {
        ify.e().c(hiSampleConfigProcessOption, iDataOperateListener, true);
    }
}
