package defpackage;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.manager.util.UnitUtilExt;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ohos.localability.FormCallback;
import com.huawei.ohos.localability.FormException;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import health.compact.a.DaemonServiceSpUtils;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LogAnonymous;
import health.compact.a.LogicalStepCounter;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.StepsRecord;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class dlc {

    /* renamed from: a, reason: collision with root package name */
    private static ReentrantReadWriteLock f11705a = new ReentrantReadWriteLock();
    private StepsRecord j;
    private Map<Long, dle> d = new HashMap();
    private Set<Long> h = new HashSet();
    private Set<Long> i = new HashSet();
    private Map<Long, Integer> f = new ConcurrentHashMap();
    private long b = SystemClock.elapsedRealtime();
    private long g = 0;
    private volatile boolean e = false;
    private ExtendHandler c = HandlerCenter.e("Step_FaFormManager");

    /* loaded from: classes3.dex */
    static class c {
        private static dlc e = new dlc();
    }

    public static dlc c() {
        return c.e;
    }

    public List<dle> d() {
        f11705a.readLock().lock();
        try {
            return new ArrayList(this.d.values());
        } finally {
            f11705a.readLock().unlock();
        }
    }

    public dle a(dle dleVar) {
        if (dleVar == null) {
            ReleaseLogUtil.a("Step_FaFormManager", "addFaForm failed with null paras.");
            return null;
        }
        if (!dleVar.c()) {
            ReleaseLogUtil.a("Step_FaFormManager", "addFaForm ignore with other faForm paras.");
            return dleVar;
        }
        f11705a.writeLock().lock();
        try {
            e(true);
            dle put = this.d.put(Long.valueOf(dleVar.e()), dleVar);
            if (this.h.contains(Long.valueOf(dleVar.e()))) {
                this.i.add(Long.valueOf(dleVar.e()));
            }
            ReleaseLogUtil.b("Step_FaFormManager", "add faForm: ", dleVar);
            k();
            f11705a.writeLock().unlock();
            d(this.j, true);
            return put;
        } catch (Throwable th) {
            f11705a.writeLock().unlock();
            throw th;
        }
    }

    public dle WH_(ContentValues contentValues) {
        if (contentValues == null) {
            ReleaseLogUtil.a("Step_FaFormManager", "addFaForm failed with null paras.");
            return null;
        }
        dle dleVar = new dle();
        dleVar.d(contentValues.getAsLong("cardId").longValue());
        dleVar.a(contentValues.getAsInteger("cardType").intValue());
        dleVar.b(contentValues.getAsInteger("cardSize").intValue());
        return a(dleVar);
    }

    public long b(long j) {
        f11705a.writeLock().lock();
        try {
            e(true);
            dle remove = this.d.remove(Long.valueOf(j));
            this.i.remove(Long.valueOf(j));
            ReleaseLogUtil.b("Step_FaFormManager", " removeFaForm faCardId:", Long.valueOf(j), " faForm:", remove);
            k();
            if (remove != null) {
                return j;
            }
            ReleaseLogUtil.a("Step_FaFormManager", "removeFaForm ignore with not exit.");
            f11705a.writeLock().unlock();
            return 0L;
        } finally {
            f11705a.writeLock().unlock();
        }
    }

    public void d(StepsRecord stepsRecord, final boolean z) {
        if (stepsRecord == null) {
            return;
        }
        this.j = stepsRecord;
        if (b()) {
            this.c.removeTasksAndMessages();
            this.c.postTask(new Runnable() { // from class: dlg
                @Override // java.lang.Runnable
                public final void run() {
                    dlc.this.c(z);
                }
            }, EnvironmentInfo.j() ? Math.max(0L, (this.g + 1005) - SystemClock.elapsedRealtime()) : 0L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x001f, code lost:
    
        if ((android.os.SystemClock.elapsedRealtime() - r12.b) > 5000) goto L6;
     */
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void c(boolean r13) {
        /*
            r12 = this;
            java.lang.String r0 = "updateForm failed. cardId:"
            java.util.concurrent.locks.ReentrantReadWriteLock r1 = defpackage.dlc.f11705a
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r1 = r1.readLock()
            r1.lock()
            java.lang.String r1 = "Step_FaFormManager"
            r2 = 3
            r3 = 2
            r4 = 1
            r5 = 0
            if (r13 != 0) goto L21
            long r6 = android.os.SystemClock.elapsedRealtime()     // Catch: java.lang.Throwable -> Ld8
            long r8 = r12.b     // Catch: java.lang.Throwable -> Ld8
            long r6 = r6 - r8
            r8 = 5000(0x1388, double:2.4703E-320)
            int r13 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r13 <= 0) goto L48
        L21:
            long r6 = android.os.SystemClock.elapsedRealtime()     // Catch: java.lang.Throwable -> Ld8
            r12.b = r6     // Catch: java.lang.Throwable -> Ld8
            r13 = 4
            java.lang.Object[] r13 = new java.lang.Object[r13]     // Catch: java.lang.Throwable -> Ld8
            java.lang.String r6 = "updateForm. cIds:"
            r13[r5] = r6     // Catch: java.lang.Throwable -> Ld8
            java.util.Set<java.lang.Long> r6 = r12.i     // Catch: java.lang.Throwable -> Ld8
            r13[r4] = r6     // Catch: java.lang.Throwable -> Ld8
            java.lang.String r6 = " recd:"
            r13[r3] = r6     // Catch: java.lang.Throwable -> Ld8
            health.compact.a.StepsRecord r6 = r12.j     // Catch: java.lang.Throwable -> Ld8
            if (r6 == 0) goto L42
            int r6 = r6.g     // Catch: java.lang.Throwable -> Ld8
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch: java.lang.Throwable -> Ld8
            goto L43
        L42:
            r6 = 0
        L43:
            r13[r2] = r6     // Catch: java.lang.Throwable -> Ld8
            health.compact.a.ReleaseLogUtil.b(r1, r13)     // Catch: java.lang.Throwable -> Ld8
        L48:
            java.util.Set<java.lang.Long> r13 = r12.i     // Catch: java.lang.Throwable -> Ld8
            boolean r13 = r13.isEmpty()     // Catch: java.lang.Throwable -> Ld8
            if (r13 != 0) goto Lce
            health.compact.a.StepsRecord r13 = r12.j     // Catch: java.lang.Throwable -> Ld8
            if (r13 != 0) goto L56
            goto Lce
        L56:
            lsg r13 = r12.b(r13)     // Catch: java.lang.Throwable -> Ld8
            long r6 = android.os.SystemClock.elapsedRealtime()     // Catch: java.lang.Throwable -> Ld8
            r12.g = r6     // Catch: java.lang.Throwable -> Ld8
            java.util.Set<java.lang.Long> r6 = r12.i     // Catch: java.lang.Throwable -> Ld8
            java.util.Iterator r6 = r6.iterator()     // Catch: java.lang.Throwable -> Ld8
        L66:
            boolean r7 = r6.hasNext()     // Catch: java.lang.Throwable -> Ld8
            if (r7 == 0) goto Lce
            java.lang.Object r7 = r6.next()     // Catch: java.lang.Throwable -> Ld8
            java.lang.Long r7 = (java.lang.Long) r7     // Catch: java.lang.Throwable -> Ld8
            long r7 = r7.longValue()     // Catch: java.lang.Throwable -> Ld8
            android.content.Context r9 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Exception -> L88 com.huawei.ohos.localability.FormException -> L9d java.lang.Throwable -> Ld8
            boolean r9 = defpackage.lse.d(r7, r9, r13)     // Catch: java.lang.Exception -> L88 com.huawei.ohos.localability.FormException -> L9d java.lang.Throwable -> Ld8
            if (r9 == 0) goto L84
            r12.e(r7)     // Catch: java.lang.Exception -> L88 com.huawei.ohos.localability.FormException -> L9d java.lang.Throwable -> Ld8
            goto L66
        L84:
            r12.a(r7)     // Catch: java.lang.Exception -> L88 com.huawei.ohos.localability.FormException -> L9d java.lang.Throwable -> Ld8
            goto L66
        L88:
            r9 = move-exception
            java.lang.Object[] r10 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> Ld8
            r10[r5] = r0     // Catch: java.lang.Throwable -> Ld8
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch: java.lang.Throwable -> Ld8
            r10[r4] = r7     // Catch: java.lang.Throwable -> Ld8
            java.lang.String r7 = com.huawei.haf.common.exception.ExceptionUtils.d(r9)     // Catch: java.lang.Throwable -> Ld8
            r10[r3] = r7     // Catch: java.lang.Throwable -> Ld8
            health.compact.a.ReleaseLogUtil.c(r1, r10)     // Catch: java.lang.Throwable -> Ld8
            goto L66
        L9d:
            r9 = move-exception
            java.lang.Object[] r10 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> Ld8
            r10[r5] = r0     // Catch: java.lang.Throwable -> Ld8
            java.lang.Long r11 = java.lang.Long.valueOf(r7)     // Catch: java.lang.Throwable -> Ld8
            r10[r4] = r11     // Catch: java.lang.Throwable -> Ld8
            java.lang.String r11 = com.huawei.haf.common.exception.ExceptionUtils.d(r9)     // Catch: java.lang.Throwable -> Ld8
            r10[r3] = r11     // Catch: java.lang.Throwable -> Ld8
            health.compact.a.ReleaseLogUtil.c(r1, r10)     // Catch: java.lang.Throwable -> Ld8
            com.huawei.ohos.localability.FormException$FormError r10 = com.huawei.ohos.localability.FormException.FormError.INTERNAL_ERROR     // Catch: java.lang.Throwable -> Ld8
            com.huawei.ohos.localability.FormException$FormError r11 = r9.c()     // Catch: java.lang.Throwable -> Ld8
            boolean r10 = r10.equals(r11)     // Catch: java.lang.Throwable -> Ld8
            if (r10 != 0) goto L66
            com.huawei.ohos.localability.FormException$FormError r10 = com.huawei.ohos.localability.FormException.FormError.MAX_REQUEST     // Catch: java.lang.Throwable -> Ld8
            com.huawei.ohos.localability.FormException$FormError r9 = r9.c()     // Catch: java.lang.Throwable -> Ld8
            boolean r9 = r10.equals(r9)     // Catch: java.lang.Throwable -> Ld8
            if (r9 == 0) goto Lca
            goto L66
        Lca:
            r12.a(r7)     // Catch: java.lang.Throwable -> Ld8
            goto L66
        Lce:
            java.util.concurrent.locks.ReentrantReadWriteLock r13 = defpackage.dlc.f11705a
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r13 = r13.readLock()
            r13.unlock()
            return
        Ld8:
            r13 = move-exception
            java.util.concurrent.locks.ReentrantReadWriteLock r0 = defpackage.dlc.f11705a
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r0 = r0.readLock()
            r0.unlock()
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.dlc.c(boolean):void");
    }

    public void d(StepsRecord stepsRecord) {
        try {
            BaseApplication.e().getContentResolver().insert(Uri.parse("content://com.huawei.ohos.health.HealthDataAbility"), WG_(stepsRecord));
        } catch (Exception e) {
            ReleaseLogUtil.c("Step_FaFormManager", "refreshData failed with error.", ExceptionUtils.d(e));
        }
    }

    private ContentValues WG_(StepsRecord stepsRecord) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MedalConstants.EVENT_STEPS, Integer.valueOf(stepsRecord.g));
        contentValues.put("distance", Integer.valueOf(stepsRecord.f13139a));
        contentValues.put("carior", Integer.valueOf(stepsRecord.d));
        contentValues.put("goalSteps", Integer.valueOf(stepsRecord.i));
        return contentValues;
    }

    public boolean b() {
        return this.e;
    }

    public void a() {
        if (this.e) {
            ReleaseLogUtil.a("Step_FaFormManager", "updateRefreshType ignore with mActiveRefreshEnable:", Boolean.valueOf(this.e));
        } else {
            e(10);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final int i) {
        ThreadPoolManager.d().d("Step_FaFormManager", new Runnable() { // from class: dlc.4
            @Override // java.lang.Runnable
            public void run() {
                ReleaseLogUtil.b("Step_FaFormManager", "updateRefreshType :", Integer.valueOf(i));
                String d = DaemonServiceSpUtils.d();
                String h = dlc.this.h();
                if (!TextUtils.isEmpty(h)) {
                    DaemonServiceSpUtils.a(h);
                    d = h;
                } else if (TextUtils.isEmpty(d)) {
                    d = null;
                }
                boolean b = dlc.this.b(d, 0);
                if (b || i <= 0) {
                    dlc.this.e(b);
                } else {
                    dlc.this.m();
                    HandlerExecutor.d(new Runnable() { // from class: dlc.4.4
                        @Override // java.lang.Runnable
                        public void run() {
                            dlc.this.e(i - 1);
                        }
                    }, 2000L);
                }
            }
        });
    }

    public void c(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        DaemonServiceSpUtils.a(str);
        e(b(str, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z) {
        ReleaseLogUtil.b("Step_FaFormManager", "enableActiveRefresh() new enabled:", Boolean.valueOf(z), " old enabled:", Boolean.valueOf(this.e));
        if (this.e) {
            a(false);
        }
        if (this.e == z) {
            return;
        }
        this.e = z;
        if (this.e) {
            g();
            a(true);
        } else {
            o();
            j();
        }
    }

    private void g() {
        try {
            ReleaseLogUtil.b("Step_FaFormManager", " registerFormsVisibleNotifier().");
            lse.a(BaseApplication.e(), new FormCallback() { // from class: dlc.1
                @Override // com.huawei.ohos.localability.FormCallback
                public void onFormsVisible(List<Long> list) {
                    ReleaseLogUtil.b("Step_FaFormManager", "FormsVisibleNotifier visibleList:", list);
                    dlc.this.e(list);
                    if (koq.b(list)) {
                        return;
                    }
                    dlc dlcVar = dlc.this;
                    dlcVar.d(dlcVar.j, true);
                }
            });
        } catch (FormException e) {
            ReleaseLogUtil.c("Step_FaFormManager", "registerFormsVisibleNotifier failed. please check", ExceptionUtils.d(e));
        }
    }

    private void o() {
        try {
            ReleaseLogUtil.b("Step_FaFormManager", " unregisterFormsVisibleNotifier().");
            lse.b(BaseApplication.e());
        } catch (FormException e) {
            ReleaseLogUtil.c("Step_FaFormManager", "unregisterFormsVisibleNotifier failed. please check", ExceptionUtils.d(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<Long> list) {
        f11705a.writeLock().lock();
        this.h.clear();
        this.i.clear();
        if (koq.b(list)) {
            f11705a.writeLock().unlock();
            return;
        }
        for (Long l : list) {
            if (l != null) {
                this.h.add(l);
                if (this.d.containsKey(l)) {
                    this.i.add(l);
                }
            }
        }
        f11705a.writeLock().unlock();
    }

    private void a(final boolean z) {
        ReleaseLogUtil.b("Step_FaFormManager", "load data start. isFirst:", Boolean.valueOf(z));
        ThreadPoolManager.d().d("Step_FaFormManager", new Runnable() { // from class: dlc.2
            @Override // java.lang.Runnable
            public void run() {
                List i = dlc.this.i();
                if (koq.b(i) && z) {
                    i = dlc.this.f();
                }
                dlc.this.c((List<dle>) i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<dle> f() {
        String e = DaemonServiceSpUtils.e();
        ReleaseLogUtil.b("Step_FaFormManager", " getFaFormWithSp: ", e);
        ArrayList arrayList = new ArrayList();
        if (e != null && !e.isEmpty()) {
            try {
                List list = (List) moj.b(e, new TypeToken<List<dle>>() { // from class: dlc.3
                }.getType());
                if (list != null) {
                    arrayList.addAll(list);
                }
            } catch (JsonParseException | NullPointerException e2) {
                ReleaseLogUtil.a("Step_FaFormManager", " getFaFormWithSp: ", ExceptionUtils.d(e2));
            }
        }
        return arrayList;
    }

    private void k() {
        try {
            String e = moj.e(d());
            ReleaseLogUtil.b("Step_FaFormManager", " updateFaFormToSp: ", e);
            DaemonServiceSpUtils.c(e);
        } catch (JsonParseException e2) {
            ReleaseLogUtil.a("Step_FaFormManager", " updateFaFormToSp: ", ExceptionUtils.d(e2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<dle> list) {
        f11705a.writeLock().lock();
        try {
            for (dle dleVar : list) {
                this.d.put(Long.valueOf(dleVar.e()), dleVar);
                if (this.h.contains(Long.valueOf(dleVar.e()))) {
                    this.i.add(Long.valueOf(dleVar.e()));
                }
            }
            k();
            f11705a.writeLock().unlock();
            d(this.j, true);
        } catch (Throwable th) {
            f11705a.writeLock().unlock();
            throw th;
        }
    }

    private void j() {
        f11705a.writeLock().lock();
        this.d.clear();
        this.h.clear();
        this.f.clear();
        this.i.clear();
        f11705a.writeLock().unlock();
    }

    private void a(final long j) {
        ThreadPoolManager.d().d("Step_FaFormManager", new Runnable() { // from class: dld
            @Override // java.lang.Runnable
            public final void run() {
                dlc.this.c(j);
            }
        });
    }

    /* synthetic */ void c(long j) {
        Integer num = this.f.get(Long.valueOf(j));
        if (num == null) {
            num = 0;
        }
        Integer valueOf = Integer.valueOf(num.intValue() + 1);
        ReleaseLogUtil.a("Step_FaFormManager", "updateFromFailed. cardId:", Long.valueOf(j), " failed times:", valueOf);
        if (EnvironmentInfo.j() || valueOf.intValue() < 50) {
            this.f.put(Long.valueOf(j), valueOf);
        } else {
            b(j);
            this.f.remove(Long.valueOf(j));
        }
    }

    private void e(long j) {
        this.f.remove(Long.valueOf(j));
    }

    private lsg b(StepsRecord stepsRecord) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sports_count", UnitUtilExt.e(BaseApplication.e(), stepsRecord.g, 1, 0));
            jSONObject.put("sports_percent", Math.min(Math.max(0.0d, stepsRecord.i == 0 ? 0.0d : new BigDecimal((stepsRecord.g * 100.0d) / stepsRecord.i).setScale(1, RoundingMode.HALF_UP).doubleValue()), 100.0d));
            jSONObject.put("step_unit", BaseApplication.e().getResources().getString(R.string.IDS_plugindameon_hw_phonecounter_widget_step_unit));
            jSONObject.put("app_authorized", LogicalStepCounter.c(BaseApplication.e()).b());
        } catch (JSONException e) {
            LogUtil.h("Step_FaFormManager", "getJsFormBindingData failed. pls check", ExceptionUtils.d(e));
        }
        return new lsg(jSONObject.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<dle> i() {
        Uri parse = Uri.parse("content://com.huawei.ohos.health.HealthDataAbility");
        ArrayList arrayList = new ArrayList();
        if (!e()) {
            ReleaseLogUtil.c("Step_FaFormManager", "getFaFormWithProvider uri is invalid");
            return arrayList;
        }
        String[] strArr = {"cardId", "cardSize", "cardType"};
        try {
            Cursor query = BaseApplication.e().getContentResolver().query(parse, strArr, null, null, null);
            while (query != null) {
                try {
                    if (!query.moveToNext()) {
                        break;
                    }
                    dle dleVar = new dle();
                    dleVar.d(query.getLong(query.getColumnIndex(strArr[0])));
                    dleVar.b(query.getInt(query.getColumnIndex(strArr[1])));
                    dleVar.a(query.getInt(query.getColumnIndex(strArr[2])));
                    if (dleVar.c()) {
                        arrayList.add(dleVar);
                    }
                } finally {
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            ReleaseLogUtil.b("Step_FaFormManager", "getFaFormWithProvider failed:", ExceptionUtils.d(e));
        }
        ReleaseLogUtil.b("Step_FaFormManager", "getFaFormWithProvider.  Forms:", arrayList);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String h() {
        if (!e()) {
            ReleaseLogUtil.c("Step_FaFormManager", "querySupportActiveRefresh uri is invalid");
            return "";
        }
        String[] strArr = {"HealthFaAbility"};
        try {
            Cursor query = BaseApplication.e().getContentResolver().query(Uri.parse("content://com.huawei.ohos.health.HealthDataAbility"), strArr, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToNext()) {
                        int columnIndex = query.getColumnIndex(strArr[0]);
                        if (columnIndex < 0) {
                            ReleaseLogUtil.b("Step_FaFormManager", "get ability failed with wrong result");
                            if (query != null) {
                                query.close();
                            }
                            return "";
                        }
                        String string = query.getString(columnIndex);
                        ReleaseLogUtil.b("Step_FaFormManager", "get ability set is:", string);
                        if (query != null) {
                            query.close();
                        }
                        return string;
                    }
                } finally {
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            ReleaseLogUtil.b("Step_FaFormManager", "get isFaVersion2 failed:", ExceptionUtils.d(e));
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        ReleaseLogUtil.b("Step_FaFormManager", "startHealthFaProcess enter.");
        try {
            Intent intent = new Intent();
            intent.setFlags(268435456);
            intent.setClassName("com.huawei.ohos.health", "com.huawei.ohos.health.SpringBoardAbilityShellActivity");
            BaseApplication.e().startActivity(intent);
        } catch (Exception e) {
            ReleaseLogUtil.a("Step_FaFormManager", "startHealthFaProcess failed", ExceptionUtils.d(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(String str, int i) {
        return str != null && i < str.length() && '1' == str.charAt((str.length() - 1) - i);
    }

    public boolean e() {
        Uri parse = Uri.parse("content://com.huawei.ohos.health.HealthDataAbility");
        if (parse == null) {
            LogUtil.h("Step_FaFormManager", "isValidUri  uri is null ");
            return false;
        }
        try {
            PackageManager packageManager = BaseApplication.e().getPackageManager();
            ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(parse.getAuthority(), 0);
            if (resolveContentProvider == null) {
                ReleaseLogUtil.a("Step_FaFormManager", "isValidUri providerInfo == null ");
                return false;
            }
            ApplicationInfo applicationInfo = resolveContentProvider.applicationInfo;
            if (applicationInfo == null) {
                ReleaseLogUtil.a("Step_FaFormManager", "isValidUri applicationInfo == null ");
                return false;
            }
            String str = applicationInfo.packageName;
            if (!TextUtils.isEmpty(str)) {
                return (packageManager.checkSignatures("com.huawei.ohos.health", str) == 0) || ((applicationInfo.flags & 1) == 1);
            }
            ReleaseLogUtil.a("Step_FaFormManager", "isValidUri packageName is null ");
            return false;
        } catch (RuntimeException e) {
            ReleaseLogUtil.c("Step_FaFormManager", "isValidUri exception", LogAnonymous.b((Throwable) e));
            return false;
        }
    }
}
