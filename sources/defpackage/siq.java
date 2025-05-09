package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Looper;
import android.os.Message;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.thirdpartservice.syncdata.SyncDataManager;
import com.huawei.ui.thirdpartservice.syncdata.receiver.SyncBroadcastReceiver;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes7.dex */
public class siq {

    /* renamed from: a, reason: collision with root package name */
    private b f17071a;
    private volatile boolean b;
    private final e c;
    private List<Integer> d;
    private volatile boolean e;
    private SyncBroadcastReceiver h;
    private SyncDataManager i;

    private siq() {
        this.b = false;
        this.e = false;
        this.c = new e(Looper.getMainLooper(), this);
    }

    public static siq a() {
        return a.d;
    }

    public void e() {
        this.c.sendEmptyMessage(512);
    }

    static class a {
        private static final siq d = new siq();
    }

    public void b() {
        d();
        e();
    }

    public void d() {
        if (this.h == null) {
            LogUtil.a("SyncDataToThirdPartManager", "registerSyncDataToThirdPart");
            this.h = new SyncBroadcastReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.huawei.plugin.account.login");
            intentFilter.addAction("com.huawei.sync_activity_to_third_platform");
            LocalBroadcastManager.getInstance(BaseApplication.e()).registerReceiver(this.h, intentFilter);
        }
        HiHealthNativeApi.a(BaseApplication.e()).subscribeHiHealthData(4, new HiSubscribeListener() { // from class: siq.2
            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onResult(List<Integer> list, List<Integer> list2) {
                LogUtil.a("SyncDataToThirdPartManager", "onResult successList= ", list, "failList= ", list2);
                siq.this.d = list;
            }

            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
                LogUtil.a("SyncDataToThirdPartManager", "SportHiSubscribeListener onChange type= ", i + " changeKey= " + str);
                siq.a().e();
            }
        });
    }

    public void c() {
        if (this.h != null) {
            LogUtil.a("SyncDataToThirdPartManager", "unregisterSyncDataToThirdPart");
            LocalBroadcastManager.getInstance(BaseApplication.e()).unregisterReceiver(this.h);
            this.h = null;
        }
        HiHealthNativeApi.a(BaseApplication.e()).unSubscribeHiHealthData(this.d, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        if (Utils.h()) {
            if (!CommonUtil.aa(BaseApplication.e())) {
                ReleaseLogUtil.e("SyncDataToThirdPartManager", "No network!");
                return;
            }
            ReleaseLogUtil.e("SyncDataToThirdPartManager", "Start SyncDataToThirdPartManager!");
            g();
            j();
        }
    }

    private void g() {
        if (this.i == null) {
            LogUtil.a("SyncDataToThirdPartManager", "prepare SyncDataToThirdPartManager!");
            this.i = new SyncDataManager(BaseApplication.e(), new SyncDataManager.SyncStatusListener() { // from class: siq.5
                @Override // com.huawei.ui.thirdpartservice.syncdata.SyncDataManager.SyncStatusListener
                public void endSyncData() {
                    LogUtil.a("SyncDataToThirdPartManager", "SyncDataManager endSyncData!");
                    siq.this.c.sendEmptyMessage(HealthData.TIMELINE);
                }
            });
            this.f17071a = new b(this.c);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.huawei.plugin.account.logout");
            LocalBroadcastManager.getInstance(BaseApplication.e()).registerReceiver(this.f17071a, intentFilter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        Object[] objArr = new Object[2];
        objArr[0] = "release SyncDataToThirdPartManager!";
        objArr[1] = Boolean.valueOf(this.i != null);
        ReleaseLogUtil.e("SyncDataToThirdPartManager", objArr);
        if (this.i != null) {
            LocalBroadcastManager.getInstance(BaseApplication.e()).unregisterReceiver(this.f17071a);
            this.f17071a = null;
            this.i.b();
            this.i = null;
        }
        this.b = false;
        this.e = false;
    }

    private void j() {
        if (this.e) {
            LogUtil.h("SyncDataToThirdPartManager", "mIsPendingSync!");
            return;
        }
        if (this.b) {
            LogUtil.a("SyncDataToThirdPartManager", "In sync!");
            this.e = true;
        } else {
            LogUtil.a("SyncDataToThirdPartManager", "startSync!");
            this.b = true;
            this.i.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (!Utils.h()) {
            f();
        } else {
            if (this.e) {
                LogUtil.a("SyncDataToThirdPartManager", "start pending sync!");
                this.e = false;
                this.i.c();
                return;
            }
            this.c.sendEmptyMessage(256);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        SyncDataManager syncDataManager;
        LogUtil.a("SyncDataToThirdPartManager", "User logout");
        if (this.b && (syncDataManager = this.i) != null) {
            syncDataManager.d();
        }
        f();
    }

    static class e extends BaseHandler<siq> {
        private e(Looper looper, siq siqVar) {
            super(looper, siqVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dXZ_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(siq siqVar, Message message) {
            int i = message.what;
            if (i == 256) {
                siqVar.f();
                return;
            }
            if (i == 512) {
                siqVar.l();
            } else if (i == 768) {
                siqVar.i();
            } else {
                if (i != 1024) {
                    return;
                }
                siqVar.h();
            }
        }
    }

    static class b extends BroadcastReceiver {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<e> f17072a;

        private b(e eVar) {
            this.f17072a = new WeakReference<>(eVar);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            e eVar;
            if (!"com.huawei.plugin.account.logout".equals(intent.getAction()) || (eVar = this.f17072a.get()) == null) {
                return;
            }
            eVar.sendEmptyMessage(1024);
        }
    }
}
