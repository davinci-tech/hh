package com.huawei.hms.network.file.core.task;

import com.huawei.hms.framework.common.NetworkUtil;
import com.huawei.hms.network.embedded.n2;
import com.huawei.hms.network.embedded.x2;
import com.huawei.hms.network.file.api.Callback;
import com.huawei.hms.network.file.api.Progress;
import com.huawei.hms.network.file.api.Request;
import com.huawei.hms.network.file.api.RequestConfig;
import com.huawei.hms.network.file.api.Response;
import com.huawei.hms.network.file.api.Result;
import com.huawei.hms.network.file.core.Constants;
import com.huawei.hms.network.file.core.FileManagerException;
import com.huawei.hms.network.file.core.task.e;
import com.huawei.hms.network.file.core.util.FLogger;
import com.huawei.hms.network.file.core.util.Utils;
import com.huawei.hms.network.httpclient.RequestFinishedInfo;
import com.huawei.hms.network.httpclient.Submit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

/* loaded from: classes4.dex */
public class i<R extends Request> implements f<k<R>> {

    /* renamed from: a, reason: collision with root package name */
    volatile R f5634a;
    Callback b;
    c c;
    volatile List<k> d;
    com.huawei.hms.network.file.core.d f;
    List<String> g;
    h j;
    ExecutorService k;
    ExecutorService l;
    ExecutorService m;
    j o;
    int s;
    com.huawei.hms.network.file.core.util.f u;
    RequestFinishedInfo w;
    volatile Throwable x;
    volatile Submit y;
    String z;
    private final Object e = new Object();
    int h = 0;
    volatile String i = "";
    volatile long n = 0;
    private com.huawei.hms.network.file.core.a p = com.huawei.hms.network.file.core.a.a();
    volatile boolean q = false;
    volatile boolean r = false;
    volatile boolean t = false;
    private final Object v = new Object();
    private boolean A = false;

    public Result k() {
        FLogger.i("RequestProcessor", "requestProcessor start:" + this.f5634a.getId(), new Object[0]);
        Result a2 = this.c.a((c) this.f5634a);
        if (a2.getCode() != Constants.ErrorCode.SUCCESS.getErrorCode()) {
            FLogger.e("RequestProcessor", "start checkParams failed:" + a2);
            return a2;
        }
        this.c.b((c) this.f5634a);
        this.d = d(this.c.c((c) this.f5634a));
        a(true);
        this.o.d(this.f5634a);
        this.p.c(this.f5634a.getId());
        return a2;
    }

    void j() {
        this.n = 0L;
    }

    public Result i() {
        synchronized (this) {
            boolean n = n();
            FLogger.i("RequestProcessor", "pause request:" + this.f5634a.getId() + ",isTaskExecuteFinished:" + n, new Object[0]);
            if (n) {
                return Result.RESULT_STATUS_FAILED;
            }
            this.o.c((j) this.f5634a);
            this.r = false;
            b();
            h hVar = this.j;
            if (hVar != null) {
                hVar.a();
            }
            o();
            return Result.RESULT_SUCCESS;
        }
    }

    public void h() {
        h hVar;
        synchronized (this) {
            e.a d = d();
            FLogger.w("RequestProcessor", "onFinished status:" + d, new Object[0]);
            if (d != e.a.PROCESS) {
                return;
            }
            if (this.q && (hVar = this.j) != null && !hVar.d()) {
                FLogger.i("RequestProcessor", "task is finished", new Object[0]);
                return;
            }
            for (k kVar : this.d) {
                FLogger.d("RequestProcessor", "task is Finished:" + kVar.b(), new Object[0]);
                if (!kVar.b()) {
                    FLogger.w("RequestProcessor", "task is still process", new Object[0]);
                    return;
                }
            }
            this.q = true;
            if (this.w != null) {
                k.a(this.d, this.w);
            }
            h hVar2 = new h(this, this.d, this.m);
            this.j = hVar2;
            hVar2.c();
        }
    }

    void g() {
        FLogger.i("RequestProcessor", "notifyRequestFinished", new Object[0]);
        this.f.a(this.f5634a);
    }

    boolean f() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.f5634a.getUrl());
        if (!Utils.isEmpty(this.f5634a.getBackupUrls())) {
            arrayList.addAll(this.f5634a.getBackupUrls());
        }
        return !Utils.isDeepEqual(arrayList, this.g);
    }

    boolean e() {
        boolean z;
        synchronized (this) {
            z = this.x != null;
        }
        return z;
    }

    public e.a d() {
        return this.o.c();
    }

    public R c() {
        R r;
        synchronized (this.v) {
            r = this.f5634a;
        }
        return r;
    }

    boolean b(List<ITaskResult> list) {
        this.o.a((j) this.f5634a);
        FLogger.i("RequestProcessor", "2.compose results", new Object[0]);
        try {
            ITaskResult a2 = this.c.a((c) this.f5634a, (List) this.d);
            if (!Constants.a(a2.getErrorCode())) {
                FLogger.w("RequestProcessor", a2.getErrorMessage(), new Object[0]);
                a(new FileManagerException(a2.getErrorCode(), a2.getErrorMessage()), c(list));
                return true;
            }
            FLogger.i("RequestProcessor", "3.convertResponse", new Object[0]);
            try {
                final Response response = new Response(a2.getErrorCode(), a2.getErrorMessage(), this.f5634a, a2.getRawResponse(), this.f5634a.getConverter() != null ? this.f5634a.getConverter().convertResponse(this.f5634a, a2) : null);
                FLogger.i("RequestProcessor", "4.runAsyncForResponse success:" + this.f5634a.getId(), new Object[0]);
                int b = this.p.b(this.f5634a.getId());
                a(response, b);
                LinkedHashMap<String, String> a3 = a(b);
                a(new Runnable() { // from class: com.huawei.hms.network.file.core.task.i$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        i.this.a(response);
                    }
                });
                this.p.a(this.f5634a.getId());
                this.u.a(this.d, null, this.o, a3);
                return false;
            } catch (Throwable th) {
                FLogger.e("RequestProcessor", "5.task failed ", th);
                a(new FileManagerException("convert exception:" + th), c(list));
                return true;
            }
        } catch (FileManagerException e) {
            a(e, c(list));
            return true;
        }
    }

    boolean b(final Throwable th) {
        FileManagerException fileManagerException;
        if (!c(th)) {
            if (!(th instanceof FileManagerException)) {
                if (!(th.getCause() instanceof FileManagerException)) {
                    fileManagerException = new FileManagerException(Constants.ErrorCode.TASK_UNCACHED_EXCEPTION, th);
                    return !a(fileManagerException);
                }
                th = th.getCause();
            }
            fileManagerException = (FileManagerException) Utils.cast(th);
            return !a(fileManagerException);
        }
        final Constants.ErrorCode errorCode = Constants.ErrorCode.TASK_PAUSE_EXCEPTION;
        if (d() == e.a.CANCEL) {
            errorCode = Constants.ErrorCode.TASK_CANCEL_EXCEPTION;
        }
        if (this.b != null) {
            final Response b = b(errorCode.getErrorCode());
            a(b, this.p.b(this.f5634a.getId()));
            this.p.a(this.f5634a.getId());
            a(new Runnable() { // from class: com.huawei.hms.network.file.core.task.i$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    i.this.a(errorCode, th, b);
                }
            });
        }
        return true;
    }

    public boolean b(k<R> kVar) {
        synchronized (this) {
            if (!this.r) {
                this.r = true;
                if (!b((i<R>) this.f5634a)) {
                    this.r = false;
                    return false;
                }
                this.c.c();
                this.u.a();
                this.o.a((j) this.f5634a, (List) this.d);
            }
            return true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x008e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x002b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0074 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void b() {
        /*
            r7 = this;
            monitor-enter(r7)
            r0 = 1
            r7.t = r0     // Catch: java.lang.Throwable -> L98
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L98
            java.lang.String r2 = "cancelTasks isTaskNull:"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L98
            java.util.List<com.huawei.hms.network.file.core.task.k> r2 = r7.d     // Catch: java.lang.Throwable -> L98
            r3 = 0
            if (r2 != 0) goto L12
            r2 = r0
            goto L13
        L12:
            r2 = r3
        L13:
            java.lang.String r4 = "RequestProcessor"
            r1.append(r2)     // Catch: java.lang.Throwable -> L98
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L98
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L98
            com.huawei.hms.network.file.core.util.FLogger.w(r4, r1, r2)     // Catch: java.lang.Throwable -> L98
            java.util.List<com.huawei.hms.network.file.core.task.k> r1 = r7.d     // Catch: java.lang.Throwable -> L98
            if (r1 == 0) goto L96
            java.util.List<com.huawei.hms.network.file.core.task.k> r1 = r7.d     // Catch: java.lang.Throwable -> L98
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Throwable -> L98
        L2b:
            boolean r2 = r1.hasNext()     // Catch: java.lang.Throwable -> L98
            if (r2 == 0) goto L96
            java.lang.Object r2 = r1.next()     // Catch: java.lang.Throwable -> L98
            com.huawei.hms.network.file.core.task.e r2 = (com.huawei.hms.network.file.core.task.e) r2     // Catch: java.lang.Throwable -> L98
            r2.b(r0)     // Catch: java.lang.Throwable -> L98
            com.huawei.hms.network.httpclient.Submit r4 = r7.y     // Catch: java.lang.Throwable -> L98
            if (r4 == 0) goto L6e
            com.huawei.hms.network.httpclient.Submit r4 = r7.y     // Catch: java.lang.Exception -> L62 java.lang.RuntimeException -> L66 java.lang.Throwable -> L98
            r4.cancel()     // Catch: java.lang.Exception -> L62 java.lang.RuntimeException -> L66 java.lang.Throwable -> L98
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L62 java.lang.RuntimeException -> L66 java.lang.Throwable -> L98
            r4.<init>()     // Catch: java.lang.Exception -> L62 java.lang.RuntimeException -> L66 java.lang.Throwable -> L98
            java.lang.String r5 = "RequestProcessor"
            java.lang.String r6 = "submit.cancel state:"
            r4.append(r6)     // Catch: java.lang.Exception -> L62 java.lang.RuntimeException -> L66 java.lang.Throwable -> L98
            com.huawei.hms.network.httpclient.Submit r6 = r7.y     // Catch: java.lang.Exception -> L62 java.lang.RuntimeException -> L66 java.lang.Throwable -> L98
            boolean r6 = r6.isCanceled()     // Catch: java.lang.Exception -> L62 java.lang.RuntimeException -> L66 java.lang.Throwable -> L98
            r4.append(r6)     // Catch: java.lang.Exception -> L62 java.lang.RuntimeException -> L66 java.lang.Throwable -> L98
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Exception -> L62 java.lang.RuntimeException -> L66 java.lang.Throwable -> L98
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch: java.lang.Exception -> L62 java.lang.RuntimeException -> L66 java.lang.Throwable -> L98
            com.huawei.hms.network.file.core.util.FLogger.i(r5, r4, r6)     // Catch: java.lang.Exception -> L62 java.lang.RuntimeException -> L66 java.lang.Throwable -> L98
            goto L6e
        L62:
            r4 = move-exception
            java.lang.String r5 = "submit.cancel exception"
            goto L69
        L66:
            r4 = move-exception
            java.lang.String r5 = "submit.cancel runtimeException"
        L69:
            java.lang.String r6 = "RequestProcessor"
            com.huawei.hms.network.file.core.util.FLogger.e(r6, r5, r4)     // Catch: java.lang.Throwable -> L98
        L6e:
            com.huawei.hms.network.httpclient.Submit r4 = r2.g()     // Catch: java.lang.Throwable -> L98
            if (r4 == 0) goto L88
            com.huawei.hms.network.httpclient.Submit r4 = r2.g()     // Catch: java.lang.Exception -> L7c java.lang.RuntimeException -> L80 java.lang.Throwable -> L98
            r4.cancel()     // Catch: java.lang.Exception -> L7c java.lang.RuntimeException -> L80 java.lang.Throwable -> L98
            goto L88
        L7c:
            r4 = move-exception
            java.lang.String r5 = "submit.cancel exception"
            goto L83
        L80:
            r4 = move-exception
            java.lang.String r5 = "submit.cancel runtimeException"
        L83:
            java.lang.String r6 = "RequestProcessor"
            com.huawei.hms.network.file.core.util.FLogger.e(r6, r5, r4)     // Catch: java.lang.Throwable -> L98
        L88:
            java.util.concurrent.Future r4 = r2.e()     // Catch: java.lang.Throwable -> L98
            if (r4 == 0) goto L2b
            java.util.concurrent.Future r2 = r2.e()     // Catch: java.lang.Throwable -> L98
            r2.cancel(r0)     // Catch: java.lang.Throwable -> L98
            goto L2b
        L96:
            monitor-exit(r7)
            return
        L98:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.file.core.task.i.b():void");
    }

    boolean a(Throwable th) {
        boolean b;
        synchronized (this) {
            this.o.a((j) this.f5634a);
            if (!this.t) {
                b();
            }
            if (th != null && this.x == null) {
                this.x = th;
            }
            b = b(this.x);
        }
        return b;
    }

    boolean a(String str) {
        if (Utils.isUrlValid(this.f5634a.getUrl()) && Utils.isUrlValid(this.f5634a.getBackupUrls())) {
            return true;
        }
        FLogger.e("RequestProcessor", "checkAllUrlsValid failed for:" + str);
        a(new FileManagerException(Constants.ErrorCode.REQUEST_URL_EMPTY), (Response) null);
        return false;
    }

    @Override // com.huawei.hms.network.file.core.task.f
    public boolean a(long j) {
        FLogger.i("RequestProcessor", "onFileLengthUpdated fileSize:" + j, new Object[0]);
        if (this.t) {
            FLogger.w("RequestProcessor", "onFileLengthUpdated, all task is Canceled, no need to process", new Object[0]);
            return false;
        }
        List<k> a2 = this.c.a(this.f5634a, this.d, j);
        if (Utils.isEmpty(a2)) {
            return false;
        }
        FLogger.i("RequestProcessor", "onFileLengthUpdated, newTaskListSize:" + a2.size(), new Object[0]);
        this.d.addAll(a2);
        a(a2);
        return true;
    }

    void a(boolean z) {
        synchronized (this) {
            if (Utils.isEmpty(this.d)) {
                FLogger.i("RequestProcessor", "startCore getCachedTasks", new Object[0]);
                this.d = d(this.c.b(this.f5634a.getId()));
                this.o.a(this.o.c(this.d));
            }
            if (Utils.isEmpty(this.d)) {
                FLogger.i("RequestProcessor", "startCore decompose", new Object[0]);
                this.d = d(this.c.c((c) this.f5634a));
            }
            if (z) {
                this.i = l();
            }
            if (Utils.isEmpty(this.d)) {
                a(new FileManagerException(Constants.ErrorCode.REQUEST_TASKS_EMPTY), (Response) null);
                return;
            }
            if (this.t) {
                k a2 = k.a(this.d);
                if (a2.r() != null) {
                    this.w = a2.r();
                }
                this.d = d(k.b(this.d));
            }
            this.t = false;
            a(this.d);
        }
    }

    void a(List<k> list) {
        synchronized (this) {
            if (this.k.isShutdown() || this.m.isShutdown()) {
                FLogger.e("RequestProcessor", "executorService is shutdown");
                return;
            }
            if (this.j != null) {
                FLogger.i("RequestProcessor", "disable last allfuture", new Object[0]);
                this.j.b();
            }
            FLogger.i("RequestProcessor", "executeTasks size:" + list.size(), new Object[0]);
            this.x = null;
            this.z = UUID.randomUUID().toString();
            if (Utils.isEmpty(list)) {
                FLogger.e("RequestProcessor", "executeTasks tasks is null");
                a(new FileManagerException(Constants.ErrorCode.REQUEST_TASKS_EMPTY), (Response) null);
                return;
            }
            this.u.b();
            Iterator<k> it = list.iterator();
            while (it.hasNext()) {
                try {
                    new a(this, it.next(), this.k, this.m, this.z).a();
                } catch (RejectedExecutionException e) {
                    FLogger.e("RequestProcessor", "RejectedExecutionException for CompletableFuture.supplyAsync");
                    b();
                    a(new FileManagerException(Constants.ErrorCode.TASK_SUBMIT_EXCEPTION, e), (Response) null);
                    return;
                }
            }
        }
    }

    void a(Throwable th, String str) {
        synchronized (this) {
            if (this.A) {
                FLogger.w("RequestProcessor", "has throw exception, this time ignore", new Object[0]);
                return;
            }
            if (this.x == null) {
                FLogger.i("RequestProcessor", "onTaskFutureException set throwable", new Object[0]);
                this.x = th;
            }
            b();
            this.A = a(this.x);
        }
    }

    void a(Runnable runnable) {
        String str;
        if (this.l.isShutdown()) {
            str = "runAsyncForResponse respExecutorService is shut down";
        } else {
            try {
                this.l.submit(runnable);
                return;
            } catch (RejectedExecutionException unused) {
                str = "runAsyncForResponse failed for RejectedExecutionException";
            }
        }
        FLogger.e("RequestProcessor", str);
    }

    @Override // com.huawei.hms.network.file.core.task.f
    public void a(Submit submit) {
        this.y = submit;
    }

    @Override // com.huawei.hms.network.file.core.task.f
    public void a(k<R> kVar) {
        final Progress a2;
        if (this.b == null) {
            return;
        }
        synchronized (this.e) {
            if (this.t) {
                FLogger.i("RequestProcessor", "onProgress no need for status:" + d(), new Object[0]);
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            if ((this.n <= 0 || currentTimeMillis - this.n > 1000) && (a2 = this.o.a(this.f5634a, this.i, this.d)) != null) {
                this.n = currentTimeMillis;
                this.c.b(this.d);
                this.c.b(this.f5634a, d());
                a(new Runnable() { // from class: com.huawei.hms.network.file.core.task.i$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        i.this.a(a2);
                    }
                });
            }
        }
    }

    void a(e eVar) {
        synchronized (this) {
            eVar.a(this.i);
            eVar.a((e) this.f5634a);
            eVar.b(false);
        }
    }

    void a(FileManagerException fileManagerException, List<ITaskResult> list) {
        a(fileManagerException, c(list), false);
    }

    void a(final FileManagerException fileManagerException, Response response, boolean z) {
        final Response response2;
        FLogger.i("RequestProcessor", "onRequestException isNetworkException:" + z, new Object[0]);
        if (!z) {
            this.c.a(this.f5634a.getId(), false);
        }
        int b = this.p.b(this.f5634a.getId());
        if (response != null) {
            response2 = new Response(fileManagerException.getErrorCode(), fileManagerException.getMessage(), response.getRequest(), response.getRawResponse(), null);
            a(response2, b);
        } else {
            response2 = null;
        }
        LinkedHashMap<String, String> a2 = a(b);
        this.o.a((j) this.f5634a, fileManagerException);
        this.p.a(this.f5634a.getId());
        if (this.b != null) {
            a(new Runnable() { // from class: com.huawei.hms.network.file.core.task.i$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    i.this.b(fileManagerException, response2);
                }
            });
        }
        this.u.a(this.d, fileManagerException, this.o, a2);
    }

    void a(FileManagerException fileManagerException, Response response) {
        a(fileManagerException, response, false);
    }

    public void a(R r, Callback callback, boolean z) {
        synchronized (this) {
            if (!Utils.isEmpty(this.d) && !this.c.a(this.d)) {
                this.d.clear();
            }
            this.o.e(r);
            c((i<R>) r);
            this.p.c(r.getId());
            this.b = callback;
            this.A = false;
            FLogger.i("RequestProcessor", "resume :" + r.getId() + ";resume url :" + Utils.anonymizeMessage(r.getUrl()), new Object[0]);
            a(z);
        }
    }

    public Result a() {
        synchronized (this) {
            boolean n = n();
            FLogger.i("RequestProcessor", "cancel request:" + this.f5634a.getId() + ",isTaskExecuteFinished:" + n, new Object[0]);
            if (n) {
                return Result.RESULT_STATUS_FAILED;
            }
            e.a d = d();
            this.o.b((j) this.f5634a);
            if (d != e.a.PAUSE) {
                b();
                h hVar = this.j;
                if (hVar != null) {
                    hVar.a();
                }
            }
            this.c.a(this.f5634a.getId());
            o();
            return Result.RESULT_SUCCESS;
        }
    }

    Request a(R r) {
        try {
            return this.b.onStart(r);
        } catch (Throwable th) {
            FLogger.e("RequestProcessor", "customRequestCallback.onStart exception", th);
            return null;
        }
    }

    private void o() {
        a((Throwable) new CancellationException("task is Interrupt"));
    }

    private boolean n() {
        synchronized (this) {
            h hVar = this.j;
            if (hVar != null) {
                if (hVar.e()) {
                    return true;
                }
            }
            return false;
        }
    }

    private List<String> m() {
        ArrayList arrayList = new ArrayList();
        if (Utils.isUrlValid(this.f5634a.getUrl())) {
            arrayList.add(this.f5634a.getUrl());
        }
        if (Utils.isUrlValid(this.f5634a.getBackupUrls())) {
            arrayList.addAll(this.f5634a.getBackupUrls());
        }
        return arrayList;
    }

    private String l() {
        if (Utils.isEmpty(this.g)) {
            this.g = m();
        }
        if (this.h >= this.g.size()) {
            return null;
        }
        FLogger.i("RequestProcessor", "getNextUrl urlIndex :" + this.h, new Object[0]);
        List<String> list = this.g;
        int i = this.h;
        this.h = i + 1;
        return list.get(i);
    }

    private List<k> d(List<k> list) {
        return (list == null || list.size() <= 0) ? list : Collections.synchronizedList(list);
    }

    private boolean c(Throwable th) {
        return (th instanceof CancellationException) || (th.getCause() instanceof CancellationException);
    }

    private void c(R r) {
        synchronized (this.v) {
            this.f5634a = r;
        }
    }

    private Response c(List<ITaskResult> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        for (ITaskResult iTaskResult : list) {
            if (iTaskResult.getRawResponse() != null) {
                return new Response(iTaskResult.getErrorCode(), this.f5634a, iTaskResult.getRawResponse(), null);
            }
        }
        return new Response(list.get(0).getErrorCode(), this.f5634a, null, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean b(final R r) {
        if (this.b == null) {
            return a("performOnStart null callback");
        }
        Request a2 = a((i<R>) r);
        if (a2 == null || a2 == r) {
            return a("performOnStart no update");
        }
        FLogger.i("RequestProcessor", "performOnStart update request:" + a2, new Object[0]);
        if (r.getId() != a2.getId()) {
            FLogger.e("RequestProcessor", "performOnStart, onStart return another request");
            g();
            final Response b = b(Constants.ErrorCode.REQUEST_UPDATE_REQUEST_ERROR.getErrorCode());
            a(b, this.p.b(r.getId()));
            this.p.a(r.getId());
            a(new Runnable() { // from class: com.huawei.hms.network.file.core.task.i$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    i.this.a(r, b);
                }
            });
            return false;
        }
        c((i<R>) a2);
        if (!a("after on Start")) {
            return false;
        }
        if (f()) {
            List<String> list = this.g;
            if (list != null) {
                list.clear();
            }
            this.h = 0;
            this.i = l();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(FileManagerException fileManagerException, Response response) {
        g();
        this.b.onException(this.f5634a, com.huawei.hms.network.file.core.util.b.a(fileManagerException), response);
    }

    private Response b(int i) {
        if (this.d.size() > 0) {
            k a2 = k.a(this.d);
            if (a2.u() != null) {
                return new Response(i, this.f5634a, a2.u().getRawResponse(), null);
            }
        }
        return new Response(i, this.f5634a, null, null);
    }

    private boolean a(FileManagerException fileManagerException) {
        FLogger.w("RequestProcessor", "notifyTaskException(will cancel other tasks)", new Object[0]);
        boolean a2 = com.huawei.hms.network.file.core.util.b.a(fileManagerException.getCause());
        boolean z = d() == e.a.PROCESS && this.h < this.g.size();
        FLogger.i("RequestProcessor", "notifyTaskException isNetworkException:" + a2 + ",needRetry:" + z + ",getStatus:" + d(), new Object[0]);
        if (z) {
            k a3 = k.a(this.d);
            boolean z2 = a3 == null || a3.d() == e.b.UPLOAD;
            if (!a2 || z2) {
                this.c.a(c().getId(), true);
                this.d.clear();
            }
            FLogger.i("RequestProcessor", "notifyTaskException try next url", new Object[0]);
            j();
            a(true);
        } else {
            FLogger.e("RequestProcessor", "processFileManagerException", fileManagerException);
            a(fileManagerException, b(fileManagerException.getErrorCode()), a2);
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(Constants.ErrorCode errorCode, Throwable th, Response response) {
        this.b.onException(this.f5634a, com.huawei.hms.network.file.core.util.b.a(new FileManagerException(errorCode, th)), response);
    }

    private void a(Response response, int i) {
        if (!Utils.isEmpty(this.d)) {
            k a2 = k.a(this.d);
            if (a2.d() == e.b.UPLOAD) {
                ((com.huawei.hms.network.file.b.f) Utils.cast(a2)).a(response.getExtraInfo());
            }
            response.getExtraInfo().put(x2.PROTOCOL, a2.n());
            response.getExtraInfo().put("protocol_impl", a2.o());
            response.getExtraInfo().put(n2.CONGESTION_CONTROL_TYPE, a2.i());
            response.getExtraInfo().put("concurrent_task_num", Integer.valueOf(i));
            response.getExtraInfo().put("multipath_algorithm", Integer.valueOf(a2.l()));
        }
        RequestConfig config = this.f5634a.getConfig();
        if (config != null) {
            response.getExtraInfo().put("association_id", Utils.getValueFromOptions("association_id", config.getOptions()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(Response response) {
        Callback callback = this.b;
        if (callback != null) {
            try {
                callback.onSuccess(response);
            } catch (Throwable th) {
                FLogger.e("RequestProcessor", "customRequestCallback.onSuccess exception", th);
            }
        }
        FLogger.i("RequestProcessor", "5.remove data after onSuccess", new Object[0]);
        this.o.f(this.f5634a);
        g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(Request request, Response response) {
        this.b.onException(request, com.huawei.hms.network.file.core.util.b.a(new FileManagerException(Constants.ErrorCode.REQUEST_UPDATE_REQUEST_ERROR)), response);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(Progress progress) {
        if (this.t) {
            FLogger.i("RequestProcessor", "response onProgress no need for status:" + d(), new Object[0]);
        } else {
            FLogger.v("RequestProcessor", "onProgress:" + progress);
            this.b.onProgress(this.f5634a, progress);
        }
    }

    private LinkedHashMap<String, String> a(int i) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        int i2 = this.h;
        linkedHashMap.put("retry_url_index", String.valueOf(i2 == 0 ? 0 : i2 - 1));
        linkedHashMap.put("domain", NetworkUtil.getHost(this.i));
        linkedHashMap.put(x2.API_ID, Utils.anonymizeMessage(Utils.getUrlPath(this.i)));
        linkedHashMap.put("task_num", String.valueOf(this.s));
        linkedHashMap.put("concurrent_task_num", String.valueOf(i));
        return linkedHashMap;
    }

    public i(R r, c cVar, Callback callback, com.huawei.hms.network.file.core.d dVar, com.huawei.hms.network.file.core.f fVar) {
        c((i<R>) r);
        this.c = cVar;
        this.o = cVar.a();
        this.b = callback;
        this.f = dVar;
        this.k = fVar.d();
        this.l = fVar.c();
        this.m = fVar.a();
        this.s = fVar.b();
        this.u = new com.huawei.hms.network.file.core.util.f(dVar);
    }
}
