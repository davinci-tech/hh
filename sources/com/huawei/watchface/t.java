package com.huawei.watchface;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.secure.android.common.intent.SafeIntent;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.api.HwWatchFaceManager;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.InstallWatchFaceBean;
import com.huawei.watchface.mvp.model.datatype.SpCacheWaitTryoutBean;
import com.huawei.watchface.mvp.model.datatype.SystemParamsSupportTryOutBean;
import com.huawei.watchface.mvp.model.datatype.TryoutBean;
import com.huawei.watchface.mvp.model.datatype.WatchResourcesInfo;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.mvp.model.helper.systemparam.NewSystemParamManager;
import com.huawei.watchface.mvp.ui.service.TryOutWatchFaceService;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.callback.IBaseResponseCallback;
import com.huawei.watchface.utils.callback.OperateWatchFaceCallback;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes7.dex */
public class t {
    private static final String c = "t";
    private static t d;
    private OperateWatchFaceCallback e;
    private List<String> f;
    private String g;
    private String h;
    private ConcurrentHashMap<String, TryoutBean> i;
    private ConcurrentHashMap<String, LinkedHashMap<String, String>> j;
    private String k;
    private String l;
    private String m;
    private a n;
    private b o;

    /* renamed from: a, reason: collision with root package name */
    public boolean f11180a = false;
    public String b = null;
    private IBaseResponseCallback p = new IBaseResponseCallback() { // from class: com.huawei.watchface.t.1
        @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
        public void onResponse(int i, Object obj) {
            t.this.b(i, obj);
        }
    };

    class a implements IBaseResponseCallback {
        private TryoutBean b;

        private a() {
        }

        public void a(TryoutBean tryoutBean) {
            this.b = tryoutBean;
        }

        @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
        public void onError(int i) {
            BackgroundTaskUtils.removeTaskFromMainWorker(t.this.o);
            this.b = null;
            t.this.l = null;
            t.this.m = null;
        }

        @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
        public void onResponse(int i, Object obj) {
            BackgroundTaskUtils.removeTaskFromMainWorker(t.this.o);
            t.this.m = null;
            t.this.a(i, obj, this.b);
            this.b = null;
            t.this.l = null;
        }
    }

    class b implements Runnable {
        private b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            HwLog.i(t.c, "FinishTryOutWatchFaceProtectRunnable -- run()");
            t.this.n.onResponse(-1, null);
        }
    }

    private t() {
        this.f = new CopyOnWriteArrayList();
        this.i = new ConcurrentHashMap<>();
        this.j = new ConcurrentHashMap<>();
        this.n = new a();
        this.o = new b();
        String str = c;
        HwLog.i(str, "HwWatchFaceTryOutManager()");
        String a2 = dz.a("sp_key_wait_try_out_info");
        if (TextUtils.isEmpty(a2)) {
            return;
        }
        HwLog.i(str, "HwWatchFaceTryOutManager -- init cache");
        SpCacheWaitTryoutBean spCacheWaitTryoutBean = (SpCacheWaitTryoutBean) GsonHelper.fromJson(a2, SpCacheWaitTryoutBean.class);
        if (spCacheWaitTryoutBean == null) {
            HwLog.i(str, "HwWatchFaceTryOutManager -- spCacheWaitTryoutBean is null");
            return;
        }
        this.f = spCacheWaitTryoutBean.getWaitTryOutWatchFaceIdList();
        this.i = spCacheWaitTryoutBean.getWaitTryOutBeanMap();
        this.j = spCacheWaitTryoutBean.getTryoutWfDownUrlMap();
    }

    public static t a() {
        if (d == null) {
            synchronized (t.class) {
                if (d == null) {
                    d = new t();
                }
            }
        }
        return d;
    }

    public void b() {
        this.n = null;
        this.p = null;
        this.e = null;
        k();
    }

    private static void k() {
        synchronized (t.class) {
            if (d != null) {
                HwLog.i(c, "destroyInstance() enter.");
                d = null;
            }
        }
    }

    public void a(OperateWatchFaceCallback operateWatchFaceCallback) {
        this.e = operateWatchFaceCallback;
    }

    private void a(String str, String str2, String str3) {
        if (this.e == null) {
            return;
        }
        HwLog.i(c, "transmitTryOutOver -- enter");
        this.e.transmitTryOutOver(str, str2, str3);
    }

    public void a(final TryoutBean tryoutBean) {
        e(tryoutBean);
        String tryOutHiTopId = tryoutBean.getTryOutHiTopId();
        if (!TextUtils.isEmpty(this.m) && this.m.equals(tryOutHiTopId)) {
            HwLog.i(c, "installTryoutWatchFace -- click same watch face try out.Method return");
            return;
        }
        this.m = tryOutHiTopId;
        this.l = null;
        if (!TextUtils.isEmpty(this.g)) {
            HwLog.i(c, "installTryoutWatchFace -- mTryOutWatchFaceId not empty.Cancel the try out first");
            String str = this.g.split("_")[0];
            String str2 = this.g.split("_")[1];
            this.n.a(tryoutBean);
            this.l = str;
            BackgroundTaskUtils.removeTaskFromMainWorker(this.o);
            BackgroundTaskUtils.postDelayedInMainThread(this.o, 4000L);
            HwWatchFaceManager.getInstance(Environment.getApplicationContext()).notifyTryoutWatchFaceFinish(str, str2, this.n);
            return;
        }
        a(tryoutBean.getTryOutHiTopId(), tryoutBean.getVersion(), tryoutBean);
        h(tryoutBean.getTryOutHiTopId(), tryoutBean.getVersion());
        BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.t.2
            @Override // java.lang.Runnable
            public void run() {
                cg f = t.f(tryoutBean);
                if (f == null) {
                    HwLog.e(t.c, "installTryoutWatchFace -- requestTryOutInfo error.TryOutWatchFaceInfo is null");
                    t.this.m = null;
                    t.this.n(tryoutBean.getTryOutHiTopId(), tryoutBean.getVersion());
                } else if (!t.this.i(tryoutBean.getTryOutHiTopId(), tryoutBean.getVersion())) {
                    HwLog.e(t.c, "installTryoutWatchFace -- requestTryOutInfo not match list tryoutBean is remove ");
                    t.this.n(tryoutBean.getTryOutHiTopId(), tryoutBean.getVersion());
                } else {
                    final String b2 = f.b();
                    final String a2 = f.a();
                    t.this.a(tryoutBean.getTryOutHiTopId(), tryoutBean.getVersion(), b2, a2);
                    BackgroundTaskUtils.postInMainThread(new Runnable() { // from class: com.huawei.watchface.t.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            HwLog.i(t.c, "installTryoutWatchFace -- start install WatchFace");
                            HwWatchFaceManager.getInstance(Environment.getApplicationContext()).installWatchFace(t.this.a(tryoutBean, b2, a2));
                            t.this.m = null;
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InstallWatchFaceBean a(TryoutBean tryoutBean, String str, String str2) {
        InstallWatchFaceBean installWatchFaceBean = new InstallWatchFaceBean();
        installWatchFaceBean.setWatchFaceHiTopId(tryoutBean.getTryOutHiTopId());
        installWatchFaceBean.setVersion(tryoutBean.getVersion());
        installWatchFaceBean.setFileUrl(str);
        installWatchFaceBean.setFileSize(tryoutBean.getFileSize());
        installWatchFaceBean.setHashCode(tryoutBean.getHashCode());
        installWatchFaceBean.setWatchScreen(tryoutBean.getWatchScreen());
        installWatchFaceBean.setHashCode(str2);
        installWatchFaceBean.setZip(tryoutBean.isZip());
        installWatchFaceBean.setTitle(tryoutBean.getTitle());
        installWatchFaceBean.setCnTitle(tryoutBean.getCnTitle());
        installWatchFaceBean.setProductId(tryoutBean.getProductId());
        installWatchFaceBean.setInstallationType(1);
        installWatchFaceBean.setResourceType("2");
        return installWatchFaceBean;
    }

    private void e(TryoutBean tryoutBean) {
        if (ArrayUtils.isEmpty(this.f)) {
            HwLog.i(c, "cancelAnotherWatchFaceInstall -- mWaitTryOutWatchFaceIdList is empty");
            return;
        }
        HwWatchFaceManager hwWatchFaceManager = HwWatchFaceManager.getInstance(Environment.getApplicationContext());
        for (String str : this.f) {
            if (!TextUtils.isEmpty(str)) {
                String[] split = str.split("_");
                String str2 = split[0];
                String str3 = split[1];
                if (!TextUtils.equals(tryoutBean.getTryOutHiTopId(), str2) || !TextUtils.equals(tryoutBean.getVersion(), str3)) {
                    HwLog.i(c, "cancelAnotherWatchFaceInstall -- cancel install waitTryOutHiTopId:" + str2 + "  waitTryOutVersion:" + str3);
                    hwWatchFaceManager.cancelInstallWatchFace(str2, str3);
                } else {
                    HwLog.i(c, "cancelAnotherWatchFaceInstall -- waitTryOutHiTopId equals current hitopId.Not cancel");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static cg f(TryoutBean tryoutBean) {
        if (!CommonUtils.f()) {
            HwLog.e(c, "requestTryOutInfo -- productId is empty or net is error");
            return null;
        }
        bs bsVar = new bs(tryoutBean.getProductId(), tryoutBean.getVersionCode(), tryoutBean.getTryOutHiTopId(), 1);
        int i = 3;
        cg cgVar = null;
        while (i > 0) {
            i--;
            cgVar = bsVar.c(bsVar.a(""));
            if (cgVar != null && cgVar.c() == 0) {
                break;
            }
        }
        if (cgVar == null || !TextUtils.isEmpty(cgVar.b())) {
            return cgVar;
        }
        HwLog.e(c, "requestTryOutInfo -- downUrl is empty");
        return null;
    }

    public void b(TryoutBean tryoutBean) {
        if (tryoutBean == null) {
            HwLog.e(c, "cancelTryOutWatchFace -- tryoutBean is null");
            return;
        }
        this.f11180a = true;
        this.b = tryoutBean.getTryOutHiTopId();
        a(tryoutBean, "2", false, false);
    }

    public void a(TryoutBean tryoutBean, String str, boolean z) {
        a(tryoutBean, str, z, true);
    }

    public void a(TryoutBean tryoutBean, String str, boolean z, boolean z2) {
        if (tryoutBean == null) {
            HwLog.e(c, "cancelTryOutWatchFace -- tryoutBean is null");
            return;
        }
        if (!k(tryoutBean.getTryOutHiTopId(), tryoutBean.getVersion())) {
            HwLog.w(c, "cancelTryOutWatchFace -- not is try out hitopId");
            return;
        }
        HwLog.i(c, "cancelTryOutWatchFace -- isFromDevice:" + z + "  pullType:" + str);
        if (!z) {
            HwWatchFaceManager.getInstance(Environment.getApplicationContext()).notifyTryoutWatchFaceFinish(tryoutBean.getTryOutHiTopId(), tryoutBean.getVersion(), this.n);
        }
        f();
        d();
        a("1", tryoutBean.getTryOutHiTopId(), str);
        if (z2) {
            c(tryoutBean.getDetailUrl(), str);
        }
    }

    public void a(String str, String str2) {
        if (!k(str, str2)) {
            HwLog.w(c, "cancelTryOutWatchFaceByH5 -- not is try out hitopId");
            return;
        }
        HwLog.i(c, "cancelTryOutWatchFaceByH5 -- enter");
        this.l = str;
        HwWatchFaceManager.getInstance(Environment.getApplicationContext()).notifyTryoutWatchFaceFinish(str, str2, this.n);
    }

    public void b(String str, String str2) {
        String str3 = c;
        HwLog.i(str3, "dealTryoutWatchPaidSuccess -- enter.hitopId:" + str);
        if (i(str, str2)) {
            HwLog.i(str3, "dealTryoutWatchPaidSuccess -- isWaitTryOutWatchFaceId , clearWaitTryOutWatchFaceId");
            n(str, str2);
        }
        if (!k(str, str2)) {
            HwLog.i(str3, "dealTryoutWatchPaidSuccess -- not try out watch face");
            return;
        }
        f();
        d();
        a("1", str, "1");
        HwWatchFaceManager.getInstance(Environment.getApplicationContext()).notifyTryOutWatchFacePaid(str, str2, this.p);
    }

    public void c() {
        BackgroundTaskUtils.a(new Runnable() { // from class: com.huawei.watchface.t$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                t.this.o();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o() {
        String str = c;
        HwLog.i(str, "dealTryoutWatchPayVipSuccess enter");
        new ce(null).c();
        if (!ab.a().f()) {
            HwLog.i(str, "dealTryoutWatchPayVipSuccess isVipMembership false");
            return;
        }
        if (TextUtils.isEmpty(this.g)) {
            HwLog.i(str, "dealTryoutWatchPayVipSuccess mTryOutWatchFaceId is null");
            return;
        }
        if (!TextUtils.equals(this.h, String.valueOf(2)) && !TextUtils.equals(this.h, String.valueOf(4))) {
            HwLog.i(str, "dealTryoutWatchPayVipSuccess mTryOutWatchFaceId is not vip free");
            return;
        }
        String[] split = this.g.split("_");
        if (split == null || split.length < 2) {
            HwLog.i(str, "dealTryoutWatchPayVipSuccess mTryOutWatchFaceId splits is null");
            return;
        }
        String str2 = split[0];
        String str3 = split[1];
        TryoutBean tryoutBean = new TryoutBean();
        tryoutBean.setTryOutHiTopId(str2);
        tryoutBean.setVersion(str3);
        a(tryoutBean, "4", false, false);
    }

    public void c(TryoutBean tryoutBean) {
        HwLog.i(c, "startTryOutService -- enter");
        try {
            d();
            SafeIntent safeIntent = new SafeIntent(new Intent(Environment.getApplicationContext(), (Class<?>) TryOutWatchFaceService.class));
            safeIntent.setAction("0");
            safeIntent.putExtra("key_try_out_bean", tryoutBean);
            Environment.getApplicationContext().startForegroundService(safeIntent);
        } catch (RuntimeException e) {
            HwLog.e(c, "startTryOut -- RuntimeException e:" + HwLog.printException((Exception) e));
        }
    }

    public void d() {
        HwLog.i(c, "cancelTryoutService");
        Environment.getApplicationContext().stopService(new Intent(Environment.getApplicationContext(), (Class<?>) TryOutWatchFaceService.class));
        fk.a().a(100);
    }

    public void c(String str, String str2) {
        String str3 = c;
        HwLog.i(str3, "goToH5Detail() -- enter.");
        String l = l(str, str2);
        if (TextUtils.isEmpty(l)) {
            HwLog.w(str3, "goToH5Detail() deepLinkUrl is null");
            return;
        }
        try {
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(l));
            intent.setPackage(Environment.b());
            intent.addFlags(268435456);
            Environment.getApplicationContext().startActivity(intent);
        } catch (ActivityNotFoundException | IllegalArgumentException e) {
            HwLog.i(c, "goToH5Detail() ActivityNotFoundException e :" + HwLog.printException((Exception) e));
        }
    }

    private String l(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        return "huaweihealth://huaweihealth.app/openwith?type=aar&address=" + str + "&operateType=" + str2;
    }

    public String d(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        return str + "&operateType=" + str2;
    }

    public String e() {
        return HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getTryOutWatchFacePackageName();
    }

    private void a(String str, String str2, TryoutBean tryoutBean) {
        String str3 = str + "_" + str2;
        HwLog.i(c, "putWaitTryOutBeanToMap -- hitopIdKey :" + str3);
        this.i.put(str3, tryoutBean);
        l();
    }

    private TryoutBean m(String str, String str2) {
        String str3 = str + "_" + str2;
        HwLog.i(c, "getWaitTryOutBean -- hitopIdKey :" + str3);
        return this.i.get(str3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, String str3, String str4) {
        String str5 = str + "_" + str2;
        HwLog.i(c, "putTryOutDownUrlToMap -- hitopIdKey :" + str5);
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(WatchFaceConstant.HASHCODE, str4);
        linkedHashMap.put(RecommendConstants.DOWNLOAD_URL, str3);
        this.j.put(str5, linkedHashMap);
        l();
    }

    public HashMap<String, String> e(String str, String str2) {
        String str3 = str + "_" + str2;
        HwLog.i(c, "getTryOutDownUrl -- hitopIdKey :" + str3);
        return this.j.get(str3);
    }

    public void f(String str, String str2) {
        if (i(str, str2)) {
            HwLog.i(c, "clearWaitTryOutIdFromCancelInstall -- hitopId : " + str);
            n(str, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(String str, String str2) {
        HwLog.i(c, "clearWaitTryOutWatchFaceId");
        String str3 = str + "_" + str2;
        this.f.remove(str3);
        this.i.remove(str3);
        this.j.remove(str3);
        l();
    }

    public void g(String str, String str2) {
        String str3 = str + "_" + str2;
        if (!this.f.contains(str3)) {
            HwLog.i(c, "clearWaitTryOutIdFromNormalDownload -- hitop id not wait try out id. hitopIdValue:" + str3);
            return;
        }
        n(str, str2);
    }

    private void l() {
        synchronized (this) {
            HwLog.i(c, "updateWaitTryOutInfoCache enter");
            dz.a("sp_key_wait_try_out_info", GsonHelper.toJson(new SpCacheWaitTryoutBean(this.f, this.i, this.j)));
        }
    }

    public void f() {
        HwLog.i(c, "clearTryOutWatchFaceId enter");
        this.g = null;
        this.k = null;
        this.h = null;
    }

    public void h(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        String str3 = str + "_" + str2;
        if (!this.f.contains(str3)) {
            this.f.add(str3);
            l();
        }
        HwLog.i(c, "setWaitTryOutWatchFaceId -- mWaitTryOutWatchFaceIdList size:" + this.f.size());
    }

    public boolean i(String str, String str2) {
        if (ArrayUtils.isEmpty(this.f)) {
            HwLog.i(c, "isWaitTryOutWatchFaceId -- false.mWaitTryOutWatchFaceIdList is null");
            return false;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            HwLog.i(c, "isWaitTryOutWatchFaceId -- false.hitopId or version is null");
            return false;
        }
        boolean contains = this.f.contains(str + "_" + str2);
        String str3 = c;
        StringBuilder sb = new StringBuilder("isWaitTryOutWatchFaceId -- ");
        sb.append(contains);
        HwLog.i(str3, sb.toString());
        return contains;
    }

    public void j(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        this.g = str + "_" + str2;
        String str3 = c;
        StringBuilder sb = new StringBuilder("setTryOutWatchFaceId -- mTryOutWatchFaceId:");
        sb.append(this.g);
        HwLog.i(str3, sb.toString());
    }

    public boolean k(String str, String str2) {
        boolean z = false;
        try {
        } catch (Exception e) {
            HwLog.i(c, "isTryOutWatchFaceId Exception:" + HwLog.printException(e));
        }
        if (TextUtils.isEmpty(this.g)) {
            HwLog.i(c, "isTryOutWatchFaceId -- false.mTryOutWatchFaceId is null");
            return false;
        }
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            if (this.g.contains(str) && this.g.contains(str2)) {
                z = true;
            }
            HwLog.i(c, "isTryOutWatchFaceId -- " + z);
            return z;
        }
        HwLog.i(c, "isTryOutWatchFaceId -- false.hitopId or version is null");
        return false;
    }

    public void a(String str, String str2, int i) {
        if (i(str, str2)) {
            String str3 = c;
            HwLog.i(str3, "dealSendToDeviceStartTryOutResult -- enter.errorCode:" + i);
            if (105 == i) {
                return;
            }
            if (i != 103) {
                if (i != 104) {
                    return;
                }
                HwLog.i(str3, "dealSendToDeviceStartTryOutResult -- CALLBACK_TYPE_OPERATE_FAILED");
                return;
            }
            j(str, str2);
            TryoutBean m = m(str, str2);
            if (m == null) {
                HwLog.w(str3, "dealSendToDeviceStartTryOutResult -- waitTryOutBean == null");
                return;
            }
            this.h = m.getResourceType();
            this.k = m.getDetailUrl();
            HwLog.i(str3, "dealSendToDeviceStartTryOutResult -- CALLBACK_TYPE_OPERATE_SUCCESS");
            if (TextUtils.equals(m.getTryOutHiTopId(), str) && TextUtils.equals(m.getVersion(), str2)) {
                c(m);
            }
            n(str, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(int r6, java.lang.Object r7, com.huawei.watchface.mvp.model.datatype.TryoutBean r8) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof java.lang.String
            if (r0 == 0) goto L15
            r0 = r7
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = "_"
            java.lang.String[] r0 = r0.split(r1)
            int r1 = r0.length
            r2 = 2
            if (r1 < r2) goto L15
            r1 = 0
            r0 = r0[r1]
            goto L17
        L15:
            java.lang.String r0 = ""
        L17:
            r1 = 103(0x67, float:1.44E-43)
            java.lang.String r2 = "1"
            if (r6 != r1) goto L44
            java.lang.String r6 = com.huawei.watchface.t.c
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "dealSendToDeviceFinishTryOutResult success objectData: "
            r1.<init>(r3)
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            com.huawei.watchface.utils.HwLog.i(r6, r7)
            r5.f()
            r5.d()
            r5.a(r2, r0, r2)
            if (r8 == 0) goto L7c
            java.lang.String r7 = "dealSendToDeviceFinishTryOutResult CALLBACK_TYPE_OPERATE_SUCCESS.installTryoutWatchFace"
            com.huawei.watchface.utils.HwLog.i(r6, r7)
            r5.a(r8)
            goto L7c
        L44:
            java.lang.String r1 = com.huawei.watchface.t.c
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "dealSendToDeviceFinishTryOutResult failed objectData: "
            r3.<init>(r4)
            r3.append(r7)
            java.lang.String r7 = "   errorCode:"
            r3.append(r7)
            r3.append(r6)
            java.lang.String r6 = r3.toString()
            com.huawei.watchface.utils.HwLog.i(r1, r6)
            java.lang.String r6 = "0"
            r5.a(r6, r0, r2)
            if (r8 == 0) goto L7c
            java.lang.String r6 = "dealSendToDeviceFinishTryOutResult failed.Install next try out watch face."
            com.huawei.watchface.utils.HwLog.i(r1, r6)
            java.lang.String r6 = r5.g
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 != 0) goto L79
            r5.f()
            r5.d()
        L79:
            r5.a(r8)
        L7c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.t.a(int, java.lang.Object, com.huawei.watchface.mvp.model.datatype.TryoutBean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, Object obj) {
        String str = c;
        HwLog.i(str, "dealSendToDeviceTryOutPaidResult enter");
        String a2 = a(obj);
        String b2 = b(obj);
        if (this.e == null) {
            HwLog.e(str, "dealSendToDeviceTryOutPaidResult -- mOperateCallback is null");
        } else {
            a(i, obj, a2, b2);
        }
    }

    private String a(Object obj) {
        if (!(obj instanceof String)) {
            return "";
        }
        String[] split = ((String) obj).split("_");
        return split.length >= 2 ? split[0] : "";
    }

    private String b(Object obj) {
        if (!(obj instanceof String)) {
            return "";
        }
        String[] split = ((String) obj).split("_");
        return split.length >= 2 ? split[1] : "";
    }

    private void a(int i, Object obj, String str, String str2) {
        if (i == 103) {
            HwLog.i(c, "dealSendToDeviceTryOutPaidResult CALLBACK_TYPE_OPERATE_SUCCESS objectData: " + obj);
            this.e.transmitSetDefaultWatchFaceResult(str, str2, 1);
            return;
        }
        if (i == 105) {
            HwLog.i(c, "dealSendToDeviceTryOutPaidResult CALLBACK_TYPE_OPERATE_RECEIVE objectData: " + obj);
            this.e.transmitSetDefaultWatchFaceResult(str, str2, 2);
            return;
        }
        HwLog.i(c, "dealSendToDeviceTryOutPaidResult errorCode：" + i + " objectData: " + obj);
        this.e.transmitSetDefaultWatchFaceResult(str, str2, 2);
    }

    public void a(int i, Object obj) {
        String str = c;
        HwLog.i(str, "dealDeviceReportResult -- enter. errorCode = " + i);
        if (!(obj instanceof WatchResourcesInfo)) {
            HwLog.w(str, "dealDeviceReportResult -- objectData not instanceof WatchResourcesInfo");
            return;
        }
        WatchResourcesInfo watchResourcesInfo = (WatchResourcesInfo) obj;
        String watchInfoId = watchResourcesInfo.getWatchInfoId();
        String watchInfoVersion = watchResourcesInfo.getWatchInfoVersion();
        if (i == 106) {
            a(watchInfoId);
        } else if (i == 109) {
            m();
        } else {
            if (i != 110) {
                return;
            }
            o(watchInfoId, watchInfoVersion);
        }
    }

    private void o(String str, String str2) {
        if (TextUtils.isEmpty(this.g)) {
            HwLog.i(c, "dealDeviceReportResult -- CALLBACK_WATCH_FACE_FINISH_TRY_OUT.  mTryOutWatchFaceId is null");
            return;
        }
        if (!k(str, str2)) {
            HwLog.e(c, "dealDeviceReportResult -- CALLBACK_WATCH_FACE_FINISH_TRY_OUT. not try out id.  hitopId:" + str + "   mTryOutWatchFaceId:" + this.g);
            return;
        }
        if (TextUtils.equals(this.l, str)) {
            this.l = null;
            HwLog.i(c, "dealDeviceReportResult -- CALLBACK_WATCH_FACE_FINISH_TRY_OUT. isCancelingPrevHiTopId = true. Not deal");
            return;
        }
        HwLog.i(c, "dealDeviceReportResult -- CALLBACK_WATCH_FACE_FINISH_TRY_OUT enter.");
        TryoutBean tryoutBean = new TryoutBean();
        tryoutBean.setTryOutHiTopId(str);
        tryoutBean.setVersion(str2);
        tryoutBean.setDetailUrl(this.k);
        if (n()) {
            a(tryoutBean, "3", true, false);
        } else {
            a(tryoutBean, "3", true);
        }
    }

    private void m() {
        HwLog.i(c, "dealDeviceReportResult -- CALLBACK_WATCH_FACE_START_TRY_OUT.");
        dt.a(false);
    }

    private void a(String str) {
        if (TextUtils.isEmpty(this.g)) {
            HwLog.i(c, "dealDeviceReportResult -- CALLBACK_CURRENT_CHANGE. mTryOutWatchFaceId is null");
            return;
        }
        String str2 = c;
        HwLog.i(str2, "dealDeviceReportResult -- CALLBACK_CURRENT_CHANGE. hitopId:" + str + "   mTryOutWatchFaceId:" + this.g);
        String str3 = this.g.split("_")[0];
        if (TextUtils.equals(str3, str)) {
            HwLog.i(str2, "dealDeviceReportResult -- CALLBACK_CURRENT_CHANGE. mTryOutWatchFaceId is current hitopId");
            return;
        }
        f();
        d();
        a("1", str3, "2");
    }

    private boolean n() {
        String a2;
        try {
            a2 = dz.a();
        } catch (NumberFormatException e) {
            HwLog.e(c, "isMyWatchApply NumberFormatException " + HwLog.printException((Exception) e));
        } catch (Exception e2) {
            HwLog.e(c, "isMyWatchApply Exception " + HwLog.printException(e2));
        }
        if (TextUtils.isEmpty(a2)) {
            HwLog.i(c, "isMyWatchApply myWatchApplyStr is empty");
            return false;
        }
        String str = c;
        HwLog.i(str, "isMyWatchApply myWatchApplyStr is:" + a2);
        if (System.currentTimeMillis() - Long.valueOf(a2).longValue() < 300000) {
            HwLog.i(str, "isMyWatchApply is true");
            return true;
        }
        return false;
    }

    public void g() {
        String str = c;
        HwLog.i(str, "dealWatchInfoListReset: enter");
        if (TextUtils.isEmpty(this.g)) {
            WatchResourcesInfo tryOutWatchFaceInfo = HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getTryOutWatchFaceInfo();
            if (tryOutWatchFaceInfo == null) {
                HwLog.i(str, "dealWatchInfoListReset -- mTryOutWatchFaceId is null and tryOutWatchFace is null");
                return;
            } else {
                HwLog.i(str, "dealWatchInfoListReset -- mTryOutWatchFaceId is null and tryOutWatchFace is not null");
                j(tryOutWatchFaceInfo.getWatchInfoId(), tryOutWatchFaceInfo.getWatchInfoVersion());
                return;
            }
        }
        String e = e();
        if (!TextUtils.isEmpty(e) && this.g.contains(e)) {
            HwLog.i(str, "dealWatchInfoListReset -- is try out id");
            return;
        }
        String str2 = this.g.split("_")[0];
        String str3 = this.g.split("_")[1];
        TryoutBean tryoutBean = new TryoutBean();
        tryoutBean.setTryOutHiTopId(str2);
        tryoutBean.setVersion(str3);
        tryoutBean.setDetailUrl(this.k);
        a(tryoutBean, "3", true);
    }

    public void a(boolean z) {
        if (!z) {
            HwLog.i(c, "dealDeviceChange -- isDeviceChange == false");
        } else if (TextUtils.isEmpty(this.g)) {
            HwLog.i(c, "dealDeviceChange -- mTryOutWatchFaceId is null");
        } else {
            f();
            d();
        }
    }

    public void h() {
        this.m = null;
        this.l = null;
    }

    public boolean i() {
        boolean z;
        String a2 = NewSystemParamManager.a("client_support_tryout_watch_face");
        String str = c;
        HwLog.i(str, "isSystemParamsSupportTryOut -- supportTryOutJson:" + a2);
        if (TextUtils.isEmpty(a2)) {
            HwLog.i(str, "isSystemParamsSupportTryOut -- supportTryOutJson is empty.Return true");
            return true;
        }
        SystemParamsSupportTryOutBean systemParamsSupportTryOutBean = (SystemParamsSupportTryOutBean) GsonHelper.fromJson(a2, SystemParamsSupportTryOutBean.class);
        if (systemParamsSupportTryOutBean == null) {
            HwLog.i(str, "isSystemParamsSupportTryOut --json parse error.supportTryOutBean is null.Return true");
            return true;
        }
        if (Environment.sIsAarInTheme) {
            String supportTryoutThemeAppCode = systemParamsSupportTryOutBean.getSupportTryoutThemeAppCode();
            if (TextUtils.isEmpty(supportTryoutThemeAppCode)) {
                HwLog.i(str, "isSystemParamsSupportTryOut -- Is aar in theme.supportTryoutThemeAppCode is empty.Return true");
                return true;
            }
            int a3 = dh.a(supportTryoutThemeAppCode, 0);
            int versionCode = CommonUtils.getVersionCode();
            z = versionCode >= a3;
            HwLog.i(str, "isSystemParamsSupportTryOut -- Is aar in theme.supportCode:" + a3 + "  appCode:" + versionCode + "  isSupport:" + z);
            return z;
        }
        String supportTryoutHealthAppCode = systemParamsSupportTryOutBean.getSupportTryoutHealthAppCode();
        if (TextUtils.isEmpty(supportTryoutHealthAppCode)) {
            HwLog.i(str, "isSystemParamsSupportTryOut -- supportTryoutHealthAppCode is empty.Return true");
            return true;
        }
        int a4 = dh.a(supportTryoutHealthAppCode, 0);
        int versionCode2 = CommonUtils.getVersionCode();
        z = versionCode2 >= a4;
        HwLog.i(str, "isSystemParamsSupportTryOut -- supportCode:" + a4 + "  appCode:" + versionCode2 + "  isSupport:" + z);
        return z;
    }
}
