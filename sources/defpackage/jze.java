package defpackage;

import android.app.job.JobService;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.ContactsDataSender;
import com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.Bean;
import com.huawei.hwdevice.phoneprocess.service.ContactSyncJobService;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes5.dex */
public class jze {
    private static final Lock b;
    private static final Object c = new Object();
    private static volatile jze d;
    private static final Condition e;
    private ContentResolver f;
    private ConcurrentLinkedQueue<String> h;
    private ContentObserver i;
    private ConcurrentLinkedQueue<Bean> l;
    private ConcurrentLinkedQueue<List<String>> m;
    private HandlerThread n;
    private Handler o;
    private ConcurrentLinkedQueue<List<Bean>> r;
    private ExtendHandler t;
    private e k = new e();

    /* renamed from: a, reason: collision with root package name */
    private AtomicInteger f14221a = new AtomicInteger(0);
    private AtomicInteger s = new AtomicInteger(0);
    private AtomicInteger g = new AtomicInteger(0);
    private Map<String, Boolean> j = new HashMap(16);
    private ContactsDataSender.SendFileCallback p = new ContactsDataSender.SendFileCallback() { // from class: jze.2
        @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.ContactsDataSender.SendFileCallback
        public void onTransferring(int i) {
        }

        @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.ContactsDataSender.SendFileCallback
        public void onTransferredFailed(int i, String str) {
            jze.this.t();
            ReleaseLogUtil.e("R_ContactsSyncUtils", "onTransferredFailed: update zip file, errorCode: ", Integer.valueOf(i));
            if (i == 140004) {
                LogUtil.a("ContactsSyncUtils", "onTransferredFailed: update zip file, device has no space.");
            } else if (i == 141001) {
                LogUtil.a("ContactsSyncUtils", "onTransferredFailed: update zip file, connect lost.");
            } else if (i == 140005) {
                LogUtil.a("ContactsSyncUtils", "onTransferredFailed: update zip file, device file information is abnormal.");
            } else if (i == 141000) {
                LogUtil.a("ContactsSyncUtils", "onTransferredFailed: ", str);
            } else if (i == 20000) {
                LogUtil.a("ContactsSyncUtils", "onTransferredFailed: transfer file non existent.");
            } else if (i == 140001) {
                LogUtil.a("ContactsSyncUtils", "onTransferredFailed: ", str);
            } else {
                jze.this.c(kal.a(), 1);
                LogUtil.a("ContactsSyncUtils", "onTransferredFailed: update zip file transfer failed.");
            }
            jze.this.d(kal.a(), false);
        }

        @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.ContactsDataSender.SendFileCallback
        public void onTransferredSucceed() {
            jze.this.d(kal.a(), true);
            jze.this.s.set(0);
            jze.this.g();
            jze.this.b(kal.a());
        }
    };
    private ContactsDataSender.SendFileCallback q = new ContactsDataSender.SendFileCallback() { // from class: jze.1
        @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.ContactsDataSender.SendFileCallback
        public void onTransferring(int i) {
        }

        @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.ContactsDataSender.SendFileCallback
        public void onTransferredFailed(int i, String str) {
            jze.this.t();
            ReleaseLogUtil.e("R_ContactsSyncUtils", "onTransferredFailed: delete csv file, errorCode: ", Integer.valueOf(i));
            if (i == 140004) {
                LogUtil.a("ContactsSyncUtils", "onTransferredFailed: delete csv file, device has no space. ");
            } else if (i == 141001) {
                LogUtil.a("ContactsSyncUtils", "onTransferredFailed: delete csv file, connect lost. ");
            } else if (i == 140005) {
                LogUtil.a("ContactsSyncUtils", "onTransferredFailed: delete csv file, device file information is abnormal.");
            } else if (i == 141000) {
                LogUtil.a("ContactsSyncUtils", "onTransferredFailed: ", str);
            } else if (i == 20000) {
                LogUtil.a("ContactsSyncUtils", "onTransferredFailed: transfer file non existent.");
            } else if (i == 140001) {
                LogUtil.a("ContactsSyncUtils", "onTransferredFailed: ", str);
            } else {
                jze.this.d(kal.a(), 2);
                LogUtil.a("ContactsSyncUtils", "onTransferredFailed: delete csv file transfer failed. ");
            }
            jze.this.d(kal.a(), false);
        }

        @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.ContactsDataSender.SendFileCallback
        public void onTransferredSucceed() {
            jze.this.d(kal.a(), true);
            jze.this.g.set(0);
            jze.this.i();
            jze.this.d(kal.a());
        }
    };

    static {
        ReentrantLock reentrantLock = new ReentrantLock();
        b = reentrantLock;
        e = reentrantLock.newCondition();
    }

    private jze() {
        n();
    }

    public static jze a() {
        jze jzeVar;
        synchronized (c) {
            if (d == null) {
                d = new jze();
            }
            jzeVar = d;
        }
        return jzeVar;
    }

    private static void p() {
        synchronized (c) {
            d = null;
        }
    }

    public void b(final String str, final int i) {
        if (!l()) {
            LogUtil.a("ContactsSyncUtils", "syncContactsData not support contactDataSync");
            a(str, i);
        } else {
            if (CommonUtil.bh() && CommonUtil.ch()) {
                LogUtil.a("ContactsSyncUtils", "syncContactsData is up emui110");
                if (o()) {
                    a(str, i);
                    return;
                }
                return;
            }
            jqi.a().getSwitchSetting("contacts_data_sync_switch", new IBaseResponseCallback() { // from class: jze.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    boolean z = !Utils.o();
                    if (i2 == 0 && (obj instanceof String)) {
                        z = !"0".equals(obj);
                        ReleaseLogUtil.e("R_ContactsSyncUtils", "syncContactsData isChecked:", Boolean.valueOf(z));
                    }
                    if (z) {
                        jze.this.a(str, i);
                    } else {
                        LogUtil.h("ContactsSyncUtils", "syncContactsData switch is false return");
                    }
                }
            });
        }
    }

    private boolean o() {
        int i;
        try {
            i = Settings.System.getInt(BaseApplication.getContext().getContentResolver(), "contactsSwitch");
        } catch (Settings.SettingNotFoundException unused) {
            LogUtil.b("ContactsSyncUtils", "isSyncContactData SettingNotFoundException");
            i = -1;
        }
        LogUtil.a("ContactsSyncUtils", "isSyncContactData contactsSwitch:", Integer.valueOf(i));
        boolean z = true;
        if (i != 1 && i != -1) {
            z = false;
        }
        LogUtil.a("ContactsSyncUtils", "isSyncContactData isSync:", Boolean.valueOf(z));
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, int i) {
        boolean c2 = jzy.c(str);
        LogUtil.a("ContactsSyncUtils", "syncContactsData： isSynchronizedOnce: ", Boolean.valueOf(c2));
        if (c2) {
            e(str, i);
        } else {
            a(str);
        }
    }

    private boolean l() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "ContactsSyncUtils");
        if (deviceList.size() == 0) {
            LogUtil.h("ContactsSyncUtils", "isSupportContactDataSync deviceInfo is null");
            return false;
        }
        return cwi.c(deviceList.get(0), 23);
    }

    public void e(final DeviceInfo deviceInfo) {
        if (!kae.c()) {
            LogUtil.h("ContactsSyncUtils", "syncContactsExtraData: ", "have no contacts permissions");
            return;
        }
        if (!l()) {
            LogUtil.a("ContactsSyncUtils", "syncContactsExtraData not support contactDataSync");
            a(deviceInfo);
        } else {
            if (CommonUtil.bh() && CommonUtil.ch()) {
                LogUtil.a("ContactsSyncUtils", "syncContactsExtraData is up emui110");
                if (o()) {
                    a(deviceInfo);
                    return;
                }
                return;
            }
            jqi.a().getSwitchSetting("contacts_data_sync_switch", new IBaseResponseCallback() { // from class: jze.5
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    boolean z = !Utils.o();
                    if (i == 0 && (obj instanceof String)) {
                        z = !"0".equals(obj);
                        ReleaseLogUtil.e("R_ContactsSyncUtils", "syncContactsExtraData isChecked:", Boolean.valueOf(z));
                    }
                    if (z) {
                        jze.this.a(deviceInfo);
                    } else {
                        LogUtil.h("ContactsSyncUtils", "syncContactsExtraData switch is false return");
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(DeviceInfo deviceInfo) {
        ReleaseLogUtil.e("R_ContactsSyncUtils", "syncContactsExtraDataEnable: start to sync lite data.");
        ContactsDataSender.e().d(kah.d(), kah.b(), kah.c(), deviceInfo);
    }

    public void b(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.h("ContactsSyncUtils", "startListen: context or deviceId is invalid.");
            return;
        }
        if (kai.b(context, 65281)) {
            LogUtil.h("ContactsSyncUtils", "The ContactSyncJobService is Running.");
            return;
        }
        e(context);
        if (kae.e()) {
            kai.b(context, (Class<? extends JobService>) ContactSyncJobService.class);
        } else {
            kai.bMo_(bLV_(context), bLU_(this.o));
        }
    }

    public void e(Context context) {
        kai.b(context);
    }

    public void e() {
        LogUtil.a("ContactsSyncUtils", "storeSyncContactExtras: SYNC CONTACTS EXTRA DATA SUCCESSFULLY.");
        this.f14221a.set(0);
    }

    public void b(DeviceInfo deviceInfo) {
        LogUtil.a("ContactsSyncUtils", "tryAgainContactExtras: try again SYNC CONTACT-EXTRAS-DATA failed count：", Integer.valueOf(this.s.get()));
        if (this.f14221a.incrementAndGet() > 3) {
            this.f14221a.set(0);
        } else {
            e(deviceInfo);
        }
    }

    public void b() {
        g();
        i();
        f();
        h();
    }

    public void c() {
        f();
        h();
        ExtendHandler extendHandler = this.t;
        if (extendHandler != null) {
            extendHandler.removeTasksAndMessages();
            this.t = null;
        }
        this.n.quit();
        p();
    }

    private void n() {
        this.r = new ConcurrentLinkedQueue<>();
        this.l = new ConcurrentLinkedQueue<>();
        this.m = new ConcurrentLinkedQueue<>();
        this.h = new ConcurrentLinkedQueue<>();
        this.n = new HandlerThread("observe_contacts_change_thread");
        this.t = HandlerCenter.yt_(this.k, "ContactsSyncUtils");
        this.n.start();
        this.o = new Handler(this.n.getLooper());
        jzy.c();
    }

    private List<jzt> k() {
        if (this.l.isEmpty()) {
            LogUtil.a("ContactsSyncUtils", "getCurrentSynced: current update queue is empty.");
            return Collections.emptyList();
        }
        LogUtil.a("ContactsSyncUtils", "getCurrentSynced: start");
        ArrayList arrayList = new ArrayList(this.l.size());
        Iterator<Bean> it = this.l.iterator();
        while (it.hasNext()) {
            Bean next = it.next();
            if (next instanceof kaa) {
                kaa kaaVar = (kaa) next;
                String uid = kaaVar.getUid();
                kaaVar.setUid("0");
                String d2 = kak.d(kaf.b(kaaVar));
                int e2 = CommonUtil.e(kaaVar.getId(), -1);
                if (e2 != -1) {
                    arrayList.add(new jzt(e2, uid, d2));
                }
            }
        }
        LogUtil.a("ContactsSyncUtils", "getCurrentSynced: currentUpdated list's size: ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    private List<String> j() {
        if (this.h.isEmpty()) {
            LogUtil.h("ContactsSyncUtils", "mCurrentDeleteContactsQueue is or empty.");
            return Collections.emptyList();
        }
        LogUtil.a("ContactsSyncUtils", "getCurrentDeleted: currentDeleted list's size: ", Integer.valueOf(this.h.size()));
        return new ArrayList(this.h);
    }

    private void b(List<Bean> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("ContactsSyncUtils", "sendUpdatedContactsToDevice: failure. contactList is null or empty.");
            d(kal.a(), true);
            return;
        }
        this.r.offer(list);
        ExtendHandler extendHandler = this.t;
        if (extendHandler != null) {
            extendHandler.sendEmptyMessage(16);
        }
    }

    private void d(List<String> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("ContactsSyncUtils", "sendDeletedContactsToDevice: failure. deletedUidList is null or empty. ");
            d(kal.a(), true);
            return;
        }
        this.m.offer(list);
        ExtendHandler extendHandler = this.t;
        if (extendHandler != null) {
            extendHandler.sendEmptyMessage(32);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0058, code lost:
    
        com.huawei.hwlogsmodel.LogUtil.h("ContactsSyncUtils", "sendZip: vcard text write to file failed. ");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void s() {
        /*
            r7 = this;
            java.lang.String r0 = "sendZip: zip vcard package failed."
            java.util.concurrent.ConcurrentLinkedQueue<java.util.List<com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.Bean>> r1 = r7.r
            int r1 = r1.size()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r2 = "sendZip: queue size: "
            java.lang.Object[] r1 = new java.lang.Object[]{r2, r1}
            java.lang.String r2 = "ContactsSyncUtils"
            com.huawei.hwlogsmodel.LogUtil.a(r2, r1)
            java.util.concurrent.locks.Lock r1 = defpackage.jze.b
            r1.lock()
            r7.g()     // Catch: java.lang.Throwable -> Lb5
        L21:
            java.util.concurrent.ConcurrentLinkedQueue<java.util.List<com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.Bean>> r1 = r7.r     // Catch: java.lang.Throwable -> Lb5
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> Lb5
            if (r1 != 0) goto L62
            java.util.concurrent.ConcurrentLinkedQueue<java.util.List<com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.Bean>> r1 = r7.r     // Catch: java.lang.Throwable -> Lb5
            java.lang.Object r1 = r1.poll()     // Catch: java.lang.Throwable -> Lb5
            java.util.List r1 = (java.util.List) r1     // Catch: java.lang.Throwable -> Lb5
            if (r1 == 0) goto L21
            boolean r3 = r1.isEmpty()     // Catch: java.lang.Throwable -> Lb5
            if (r3 == 0) goto L3a
            goto L21
        L3a:
            java.util.concurrent.ConcurrentLinkedQueue<com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.Bean> r3 = r7.l     // Catch: java.lang.Throwable -> Lb5
            r3.clear()     // Catch: java.lang.Throwable -> Lb5
            java.util.concurrent.ConcurrentLinkedQueue<com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.Bean> r3 = r7.l     // Catch: java.lang.Throwable -> Lb5
            r3.addAll(r1)     // Catch: java.lang.Throwable -> Lb5
            java.util.List r1 = defpackage.kaf.d(r1)     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r3 = ".vcf"
            java.lang.String r3 = r7.c(r3)     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r4 = defpackage.jzi.c     // Catch: java.lang.Throwable -> Lb5
            boolean r1 = defpackage.kam.d(r1, r4, r3)     // Catch: java.lang.Throwable -> Lb5
            r3 = 0
            r4 = 1
            if (r1 != 0) goto L68
            java.lang.Object[] r0 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r1 = "sendZip: vcard text write to file failed. "
            r0[r3] = r1     // Catch: java.lang.Throwable -> Lb5
            com.huawei.hwlogsmodel.LogUtil.h(r2, r0)     // Catch: java.lang.Throwable -> Lb5
        L62:
            java.util.concurrent.locks.Lock r0 = defpackage.jze.b
            r0.unlock()
            return
        L68:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb5
            r1.<init>()     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r5 = "upgrade"
            r1.append(r5)     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r5 = ".zip"
            java.lang.String r5 = r7.c(r5)     // Catch: java.lang.Throwable -> Lb5
            r1.append(r5)     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r5 = defpackage.jzi.c     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r6 = defpackage.jzi.b     // Catch: java.lang.Throwable -> Lb5
            boolean r5 = defpackage.kam.d(r5, r6, r1)     // Catch: java.lang.Throwable -> Lb5
            if (r5 == 0) goto La9
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb5
            r3.<init>()     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r4 = defpackage.jzi.b     // Catch: java.lang.Throwable -> Lb5
            r3.append(r4)     // Catch: java.lang.Throwable -> Lb5
            r3.append(r1)     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> Lb5
            com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.ContactsDataSender r4 = com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.ContactsDataSender.e()     // Catch: java.lang.Throwable -> Lb5
            com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.ContactsDataSender$SendFileCallback r5 = r7.p     // Catch: java.lang.Throwable -> Lb5
            r4.d(r3, r1, r5)     // Catch: java.lang.Throwable -> Lb5
            r7.m()     // Catch: java.lang.Throwable -> Lb5
            goto L21
        La9:
            java.lang.Object[] r1 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> Lb5
            r1[r3] = r0     // Catch: java.lang.Throwable -> Lb5
            com.huawei.hwlogsmodel.LogUtil.h(r2, r1)     // Catch: java.lang.Throwable -> Lb5
            defpackage.sqo.j(r0)     // Catch: java.lang.Throwable -> Lb5
            goto L21
        Lb5:
            r0 = move-exception
            java.util.concurrent.locks.Lock r1 = defpackage.jze.b
            r1.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jze.s():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        LogUtil.a("ContactsSyncUtils", "sendCsv: queue size: ", Integer.valueOf(this.m.size()));
        b.lock();
        try {
            i();
            while (!this.m.isEmpty()) {
                List<String> poll = this.m.poll();
                if (poll != null && !poll.isEmpty()) {
                    this.h.clear();
                    this.h.addAll(poll);
                    String str = "delete" + c(".csv");
                    if (kam.a(poll, jzi.f14224a, str)) {
                        ContactsDataSender.e().b(jzi.f14224a + str, str, this.q);
                        m();
                    } else {
                        LogUtil.h("ContactsSyncUtils", "sendCsv: failed to write csv text to file.");
                    }
                }
            }
        } finally {
            b.unlock();
        }
    }

    private void m() {
        try {
            LogUtil.a("ContactsSyncUtils", "lock: is timeout: ", Boolean.valueOf(e.await(300000L, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException e2) {
            LogUtil.b("ContactsSyncUtils", "lock: InterruptedException occurred on locking thread.", Thread.currentThread().getName(), e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        Lock lock = b;
        lock.lock();
        try {
            e.signalAll();
            lock.unlock();
        } catch (Throwable th) {
            b.unlock();
            throw th;
        }
    }

    private String c(String str) {
        return "_" + System.currentTimeMillis() + str;
    }

    private void a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ContactsSyncUtils", "syncAllContacts: deviceId is null or empty.");
            return;
        }
        if (!kae.c()) {
            ReleaseLogUtil.d("R_ContactsSyncUtils", "syncAllContacts: no contacts permissions");
        } else if (jzy.c(str)) {
            LogUtil.a("ContactsSyncUtils", "syncAllContacts: has already synchronized all contacts once.");
        } else {
            jzy.a(str);
            b(jzn.d());
        }
    }

    private void e(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ContactsSyncUtils", "syncChangedContacts: failure, parameter is null");
            return;
        }
        if (!kae.c()) {
            ReleaseLogUtil.d("R_ContactsSyncUtils", "syncChangedContacts: have no contacts permissions");
            return;
        }
        List<Bean> c2 = jzn.c(jzy.d(str));
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        kad.a(c2, str, arrayList, arrayList2);
        if (i == 1) {
            b(arrayList);
        } else if (i == 2) {
            d(arrayList2);
        } else {
            b(arrayList);
            d(arrayList2);
        }
        ReleaseLogUtil.e("R_ContactsSyncUtils", "syncChangedContacts: updated size: ", Integer.valueOf(arrayList.size()), " deleted size: ", Integer.valueOf(arrayList2.size()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        LogUtil.a("ContactsSyncUtils", "deleteUpdatedCache.");
        kam.c(jzi.b);
        kam.c(jzi.c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        LogUtil.a("ContactsSyncUtils", "deleteDeletedCache.");
        kam.c(jzi.f14224a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ContactsSyncUtils", "storeLastUpdated: deviceId is null or empty.");
            return;
        }
        ReleaseLogUtil.e("R_ContactsSyncUtils", "storeLastUpdated.");
        List<jzt> k = k();
        jzy.c(str, k);
        t();
        LogUtil.a("ContactsSyncUtils", "storeLastUpdated: SYNC UPDATED CONTACTS SUCCESSFULLY. updated list's Size: ", Integer.valueOf(k.size()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ContactsSyncUtils", "storeLastDeleted: deviceId is null or empty.");
            return;
        }
        ReleaseLogUtil.e("R_ContactsSyncUtils", "storeLastDeleted.");
        List<String> j = j();
        jzy.d(str, j);
        t();
        LogUtil.a("ContactsSyncUtils", "storeLastDeleted: SYNC DELETED CONTACTS SUCCESSFULLY. deleted list's size: ", Integer.valueOf(j.size()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ContactsSyncUtils", "tryAgainUpdated: deviceId is null or empty.");
            return;
        }
        LogUtil.a("ContactsSyncUtils", "tryAgainUpdated: try again SYNC UPDATE failed count:", Integer.valueOf(this.s.get()));
        if (this.s.incrementAndGet() > 3) {
            this.s.set(0);
            g();
        } else {
            b(str, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ContactsSyncUtils", "tryAgainDeleted: deviceId is null or empty.");
            return;
        }
        LogUtil.a("ContactsSyncUtils", "tryAgainDeleted: try again SYNC DELETE failed count: ", Integer.valueOf(this.g.get()));
        if (this.g.incrementAndGet() > 3) {
            this.g.set(0);
            i();
        } else {
            b(str, i);
        }
    }

    private void f() {
        this.r.clear();
        this.l.clear();
        this.m.clear();
        this.h.clear();
    }

    private void h() {
        ExtendHandler extendHandler = this.t;
        if (extendHandler != null) {
            extendHandler.removeTasksAndMessages();
        }
        this.o.removeCallbacksAndMessages(null);
    }

    private ContentObserver bLU_(Handler handler) {
        if (this.i == null) {
            this.i = new jzh(handler);
        }
        return this.i;
    }

    private ContentResolver bLV_(Context context) {
        if (this.f == null) {
            this.f = context.getContentResolver();
        }
        return this.f;
    }

    public void d() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "ContactsSyncUtils");
        if (deviceList.size() != 0) {
            Iterator<DeviceInfo> it = deviceList.iterator();
            while (it.hasNext()) {
                d(it.next());
            }
        }
    }

    private void d(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("ContactsSyncUtils", "setContactListenerChange: deviceInfo is null.");
        } else if (jst.d(deviceInfo.getDeviceIdentify()).isSupportSyncContacts()) {
            this.j.put("contact_sync_" + kak.b(deviceInfo.getDeviceIdentify()), false);
        }
    }

    public Boolean e(String str) {
        return this.j.get(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ContactsSyncUtils", "resetContactChangeValue: deviceId is null or empty.");
            return;
        }
        if (this.j.containsKey(str)) {
            this.j.remove(str);
        }
        this.j.put(str, Boolean.valueOf(z));
    }

    class e implements Handler.Callback {
        private e() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 16) {
                jze.this.s();
                return true;
            }
            if (i == 32) {
                jze.this.r();
                return true;
            }
            LogUtil.h("ContactsSyncUtils", "handleMessage: fail in default case. what: ", Integer.valueOf(message.what));
            return false;
        }
    }
}
