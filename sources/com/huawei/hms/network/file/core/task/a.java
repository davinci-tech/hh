package com.huawei.hms.network.file.core.task;

import android.text.TextUtils;
import com.huawei.hms.network.file.api.RequestConfig;
import com.huawei.hms.network.file.core.Constants;
import com.huawei.hms.network.file.core.FileManagerException;
import com.huawei.hms.network.file.core.util.FLogger;
import com.huawei.hms.network.file.upload.api.BodyRequest;
import com.huawei.hms.network.file.upload.api.FileEntity;
import com.huawei.hms.network.file.upload.api.PostRequest;
import com.huawei.hms.network.file.upload.api.PutRequest;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private final i f5630a;
    private e b;
    ExecutorService c;
    ExecutorService d;
    String e;

    public void a() {
        if (this.b.c() <= 0 || this.b.a() <= 0 || this.b.c() < this.b.a()) {
            this.b.a(this.c.submit(new Callable() { // from class: com.huawei.hms.network.file.core.task.a$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    ITaskResult b;
                    b = a.this.b();
                    return b;
                }
            }));
            return;
        }
        FLogger.i("RequestProcessor", "taskId:" + this.b.f() + " already finished before, ingnore", new Object[0]);
        this.b.a(a((k) this.b));
        this.b.a(true);
        this.f5630a.a(this.b);
        this.f5630a.h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ ITaskResult b(k kVar) {
        if (kVar.u() != null) {
            return kVar.u();
        }
        l lVar = new l(Constants.ErrorCode.SUCCESS);
        lVar.setTask(kVar);
        lVar.setRawResponse(null);
        kVar.a(lVar);
        return lVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ITaskResult b() {
        g b;
        try {
            synchronized (this.f5630a) {
                if (this.f5630a.t) {
                    FLogger.w("RequestProcessor", "all task is Canceled, no need to process", new Object[0]);
                    return null;
                }
                if (!this.f5630a.b((k) this.b)) {
                    return null;
                }
                FLogger.v("RequestProcessor", "executeTasks currentUrl:" + this.f5630a.i + ",taskSize:" + this.f5630a.d.size());
                this.f5630a.a(this.b);
                int i = !(this.b instanceof com.huawei.hms.network.file.a.d) ? 1 : 0;
                if (!a(i)) {
                    b = this.f5630a.c.b();
                } else if (i == 0) {
                    String a2 = com.huawei.hms.network.file.a.h.f().a(i, b((com.huawei.hms.network.file.a.d) this.b));
                    FLogger.d("ForkTaskFuture", "protocol = " + a2, new Object[0]);
                    b = ((com.huawei.hms.network.file.a.b) this.f5630a.c).a(a2);
                } else {
                    String a3 = com.huawei.hms.network.file.a.h.f().a(i, a((com.huawei.hms.network.file.b.f) this.b));
                    FLogger.d("ForkTaskFuture", "protocol = " + a3, new Object[0]);
                    b = ((com.huawei.hms.network.file.b.d) this.f5630a.c).a(a3);
                }
                b.a(this.f5630a);
                l lVar = (l) b.a((g) this.b);
                if (this.f5630a.t) {
                    FLogger.w("RequestProcessor", "all task is Canceled, no need to finished", new Object[0]);
                    return null;
                }
                this.f5630a.h();
                return lVar;
            }
        } catch (Throwable th) {
            synchronized (this.f5630a) {
                if (this.b.h()) {
                    return null;
                }
                if (!(th instanceof FileManagerException)) {
                    this.f5630a.a(new FileManagerException(Constants.ErrorCode.TASK_UNCACHED_EXCEPTION, th.getMessage()), this.e);
                    return null;
                }
                FLogger.w("RequestProcessor", "future.exceptionally ,exceptionTask:" + this.b, new Object[0]);
                this.f5630a.a(th, this.e);
                return null;
            }
        }
    }

    private long b(com.huawei.hms.network.file.a.d dVar) {
        long a2 = a(dVar);
        FLogger.v("ForkTaskFuture", "complete file size is " + a2);
        return a2 <= 0 ? dVar.a() : a2;
    }

    private boolean a(int i) {
        return com.huawei.hms.network.file.a.h.f().a(i);
    }

    private Future<ITaskResult> a(final k kVar) {
        return this.d.submit(new Callable() { // from class: com.huawei.hms.network.file.core.task.a$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                ITaskResult b;
                b = a.b(k.this);
                return b;
            }
        });
    }

    private long a(com.huawei.hms.network.file.b.f fVar) {
        BodyRequest p = fVar.p();
        long j = 0;
        if (p instanceof PutRequest) {
            try {
                j = new com.huawei.hms.network.file.b.c(fVar, ((PutRequest) p).getFileEntityList(), null).contentLength();
            } catch (IOException e) {
                FLogger.e("ForkTaskFuture", "get requestBody contentLength error", e);
            }
        } else {
            PostRequest postRequest = (PostRequest) p;
            for (String str : postRequest.getFileEntityMap().keySet()) {
                List<FileEntity> list = postRequest.getFileEntityMap().get(str);
                if (list == null || list.isEmpty()) {
                    FLogger.w("ForkTaskFuture", "no FileEntity for:" + str, new Object[0]);
                } else {
                    FLogger.d("ForkTaskFuture", "fileParams for:" + str + ",fileListSize:" + list.size(), new Object[0]);
                    try {
                        j += new com.huawei.hms.network.file.b.c(fVar, list, null).contentLength();
                    } catch (IOException e2) {
                        FLogger.e("ForkTaskFuture", "get fileBody contentLength error", e2);
                    }
                }
            }
        }
        FLogger.d("ForkTaskFuture", "upload filesize:" + j, new Object[0]);
        return j;
    }

    private long a(com.huawei.hms.network.file.a.d dVar) {
        if (dVar != null && dVar.p() != null) {
            RequestConfig config = dVar.p().getConfig();
            if (config != null && !TextUtils.isEmpty(config.getOptions())) {
                try {
                    return new JSONObject(config.getOptions()).getLong("complete_file_size");
                } catch (JSONException e) {
                    FLogger.w("ForkTaskFuture", "get complete file size fail " + e.getMessage(), new Object[0]);
                    return 0L;
                }
            }
            FLogger.v("ForkTaskFuture", "config options is empty");
        }
        return 0L;
    }

    public a(i iVar, e eVar, ExecutorService executorService, ExecutorService executorService2, String str) {
        this.f5630a = iVar;
        this.b = eVar;
        this.c = executorService;
        this.d = executorService2;
        this.e = str;
    }
}
