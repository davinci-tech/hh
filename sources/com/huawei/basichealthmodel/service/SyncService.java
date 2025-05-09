package com.huawei.basichealthmodel.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.basichealthmodel.bean.TaskConfigBean;
import com.huawei.basichealthmodel.service.SyncService;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.aue;
import defpackage.auh;
import defpackage.auo;
import defpackage.aur;
import defpackage.aus;
import defpackage.awq;
import defpackage.azi;
import defpackage.bao;
import defpackage.bby;
import defpackage.bck;
import defpackage.bcm;
import defpackage.bcr;
import defpackage.jdl;
import defpackage.koq;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.GRSManager;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class SyncService extends IntentService {

    /* renamed from: a, reason: collision with root package name */
    private int f1914a;
    private Context b;
    private String c;
    private int d;
    private int e;
    private String h;
    private SparseArray<TaskConfigBean> i;

    public SyncService() {
        super("HealthLife_SyncService");
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.IntentService, android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        LogUtil.a("HealthLife_SyncService", "onStartCommand flags ", Integer.valueOf(i));
        return 2;
    }

    @Override // android.app.IntentService
    public void onHandleIntent(Intent intent) {
        kR_(intent);
    }

    private boolean j() {
        if (TextUtils.isEmpty(azi.g())) {
            aur a2 = aue.e().a(this.c);
            if (a2 == null || a2.a() != 0) {
                return false;
            }
            int c = a2.c();
            boolean z = c > DateFormatUtil.b(System.currentTimeMillis());
            if (z) {
                azi.af();
            }
            bao.e("health_model_is_join_future", String.valueOf(z));
            bao.e(c, true);
            bao.a(a2.e(), true);
            bao.e("health_model_challenge_join", String.valueOf(a2.b()));
            azi.al();
        }
        return azi.aa();
    }

    private void kO_(Intent intent) {
        if (this.b == null) {
            this.b = getApplicationContext();
        }
        if (intent == null) {
            LogUtil.h("HealthLife_SyncService", "initData intent is null");
            return;
        }
        this.e = intent.getIntExtra("sync_starttime", 0);
        this.f1914a = intent.getIntExtra("sync_endtime", 0);
        this.d = intent.getIntExtra("sync_type", 0);
        this.h = intent.getStringExtra("sync_user_id");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: kQ_, reason: merged with bridge method [inline-methods] */
    public void kR_(final Intent intent) {
        if (HandlerExecutor.c()) {
            azi.b(ThreadPoolManager.d(), "HealthModelSyncCloudData_" + this.d, new Runnable() { // from class: axw
                @Override // java.lang.Runnable
                public final void run() {
                    SyncService.this.kR_(intent);
                }
            });
            return;
        }
        if (CommonUtil.aa(BaseApplication.e()) && Utils.k()) {
            LogUtil.a("HealthLife_SyncService", "syncCloudData mSyncType ", Integer.valueOf(this.d));
            kO_(intent);
            this.c = GRSManager.a(BaseApplication.e()).getUrl("achievementUrl");
            if (j()) {
                this.i = awq.e().kD_();
                int i = this.d;
                if (i == 0) {
                    f();
                    b();
                    ObserverManagerUtil.c("PluginHealthModel", new Object[0]);
                    ReleaseLogUtil.b("HealthLife_SyncService", "syncCloudData SYNC_ALL");
                    return;
                }
                if (i == 1) {
                    f();
                    return;
                }
                if (i == 2) {
                    bck.ni_(this.h, false, aue.e().e(this.c, this.e, this.f1914a), this.i);
                } else if (i != 4) {
                    if (i != 5) {
                        return;
                    }
                    bck.c(this.c, this.h);
                } else {
                    if (this.e == 0) {
                        this.e = azi.t();
                    }
                    if (this.f1914a == 0) {
                        this.f1914a = DateFormatUtil.b(System.currentTimeMillis());
                    }
                    bck.b(this.c, this.e, this.f1914a, this.h);
                }
            }
        }
    }

    private void b() {
        int b = DateFormatUtil.b(System.currentTimeMillis());
        if (!e(aue.e().c(this.c, b, b), true, true)) {
            LogUtil.h("HealthLife_SyncService", "parseConfigJsonObject today fail");
        }
        int b2 = DateFormatUtil.b(jdl.y(DateFormatUtil.c(b)));
        if (e(aue.e().c(this.c, b2, b2), true, false)) {
            return;
        }
        LogUtil.h("HealthLife_SyncService", "parseConfigJsonObject tomorrow fail");
    }

    private void f() {
        bby.i();
        h();
        i();
        bck.b(this.c, azi.t(), DateFormatUtil.b(System.currentTimeMillis()), this.h);
        bck.c(this.c, this.h);
        a();
    }

    private void a() {
        final int b = DateFormatUtil.b(System.currentTimeMillis());
        awq.e().a(this.h, b, new ResponseCallback() { // from class: axr
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                SyncService.kP_(b, i, (SparseArray) obj);
            }
        });
    }

    public static /* synthetic */ void kP_(int i, int i2, SparseArray sparseArray) {
        HealthLifeBean healthLifeBean;
        LogUtil.a("HealthLife_SyncService", "exitChallenge date ", Integer.valueOf(i), " resultCode ", Integer.valueOf(i2), " sparseArray ", sparseArray);
        if (sparseArray == null || (healthLifeBean = (HealthLifeBean) sparseArray.get(1)) == null) {
            return;
        }
        int dataSource = healthLifeBean.getDataSource();
        azi.n(dataSource);
        if (dataSource == 1) {
            bcr.a().a(new ResponseCallback() { // from class: axy
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i3, Object obj) {
                    LogUtil.a("HealthLife_SyncService", "exitChallenge status=", Integer.valueOf(i3));
                }
            });
        }
    }

    private void i() {
        long h = nsn.h(bao.e("health_life_last_version"));
        long c = c();
        long j = h;
        do {
            j = bck.ni_(this.h, true, aue.e().c(this.c, j), this.i);
            if (j == 0 || j >= c) {
                break;
            }
        } while (BaseApplication.j());
        LogUtil.a("HealthLife_SyncService", "finish sync health record by version, localVersion=", Long.valueOf(h), " version=", Long.valueOf(j));
    }

    private long c() {
        auh e = aue.e().e(this.c);
        if (e == null) {
            return 0L;
        }
        LogUtil.c("HealthLife_SyncService", "getHealthLifeRecordVersion data: ", e.toString());
        int a2 = e.a();
        if (a2 != 0) {
            LogUtil.h("HealthLife_SyncService", "syncHealthLifeRecordByVersion resultCode != 0,resultCode=", Integer.valueOf(a2));
            return 0L;
        }
        return e.d();
    }

    private void h() {
        int h = CommonUtils.h(bao.e("health_life_last_sync_date"));
        if (h <= 0) {
            h = azi.t();
            azi.k(azi.t());
        }
        long currentTimeMillis = System.currentTimeMillis();
        int b = DateFormatUtil.b(currentTimeMillis);
        int b2 = DateFormatUtil.b(jdl.y(currentTimeMillis));
        int i = h;
        while (true) {
            if (i > b2 || !BaseApplication.j()) {
                break;
            }
            int b3 = DateFormatUtil.b(jdl.d(DateFormatUtil.c(i), 29));
            auo c = aue.e().c(this.c, i, b3);
            if (!e(c, false, false)) {
                LogUtil.h("HealthLife_SyncService", "syncHealthLifeConfigAll result ", c, " endDay ", Integer.valueOf(b2), " syncDate ", Integer.valueOf(h), " tempTimeStart ", Integer.valueOf(i), " endTimeTemp ", Integer.valueOf(b3));
                break;
            } else {
                if (b3 <= b) {
                    azi.k(b3);
                }
                i = b3;
            }
        }
        LogUtil.a("HealthLife_SyncService", "sync health config finish endDay=", Integer.valueOf(b2));
    }

    private boolean e(auo auoVar, boolean z, boolean z2) {
        if (auoVar == null || koq.b(auoVar.e())) {
            LogUtil.h("HealthLife_SyncService", "parseConfigJsonObject result is null");
            return false;
        }
        LogUtil.a("HealthLife_SyncService", "parseConfigJsonObject result ", auoVar.toString());
        int a2 = auoVar.a();
        if (a2 != 0) {
            LogUtil.b("HealthLife_SyncService", "parseConfigJsonObject resultCode = ", Integer.valueOf(a2));
            return false;
        }
        List<HealthLifeBean> e = bcm.e(auoVar.e());
        if (z) {
            bby.c(c(e), z2, bby.e());
        }
        if (!koq.c(e) || aus.c(e, this.h) != -1) {
            return true;
        }
        LogUtil.b("HealthLife_SyncService", "parseConfigJsonObject insertTaskList fail");
        return false;
    }

    private static List<HealthLifeBean> c(List<HealthLifeBean> list) {
        LogUtil.a("HealthLife_SyncService", "getMaxDay old list:", Integer.valueOf(list.size()));
        ArrayList<HealthLifeBean> arrayList = new ArrayList(list.size());
        Iterator<HealthLifeBean> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            int recordDay = it.next().getRecordDay();
            if (recordDay >= i) {
                i = recordDay;
            }
        }
        for (HealthLifeBean healthLifeBean : list) {
            if (healthLifeBean.getRecordDay() == i) {
                arrayList.add(healthLifeBean);
            }
        }
        ArrayList arrayList2 = new ArrayList(16);
        Iterator it2 = arrayList.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            HealthLifeBean healthLifeBean2 = (HealthLifeBean) it2.next();
            if (healthLifeBean2.getId() == 1) {
                arrayList2.addAll(Arrays.asList(healthLifeBean2.getTarget().split(",")));
                break;
            }
        }
        for (HealthLifeBean healthLifeBean3 : arrayList) {
            if (arrayList2.contains(String.valueOf(healthLifeBean3.getId()))) {
                healthLifeBean3.setAddStatus(1);
            }
        }
        LogUtil.a("HealthLife_SyncService", "getMaxDay new list:", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    public static void d() {
        LogUtil.a("HealthLife_SyncService", "syncData");
        if (!Utils.k()) {
            LogUtil.h("HealthLife_SyncService", "syncData() not support HealthModel.");
        } else {
            azi.b(ThreadPoolManager.d(), "HealthModelSyncData", new Runnable() { // from class: axs
                @Override // java.lang.Runnable
                public final void run() {
                    awj.a().b(azi.p(), 0, 0, 0);
                }
            });
        }
    }

    @Override // android.app.IntentService, android.app.Service
    public void onDestroy() {
        LogUtil.a("HealthLife_SyncService", "DataSyncService onDestroy");
        super.onDestroy();
    }
}
