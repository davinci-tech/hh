package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.DataCallback;
import com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.UiCallback;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class pum {
    private volatile List<pus> b;
    private UiCallback<String> d;
    private List<pus> e;
    private long f;
    private long h;
    private volatile boolean i;

    /* renamed from: a, reason: collision with root package name */
    private Handler f16264a = new Handler(Looper.getMainLooper()) { // from class: pum.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (pum.this.i) {
                LogUtil.h("Stress_DownloadTask", "cancel task!");
                return;
            }
            int i = message.what;
            if (i == 1) {
                removeCallbacksAndMessages(null);
                pum.this.d.onSuccess(null);
                return;
            }
            if (i == 2) {
                pum.this.i = true;
                removeCallbacksAndMessages(null);
                pum.this.d.onFailure(message.arg1, String.valueOf(message.obj));
                return;
            }
            if (i == 3) {
                long currentTimeMillis = System.currentTimeMillis();
                long j = currentTimeMillis - pum.this.f;
                if (j < 0 || j > 10) {
                    pum.this.f = currentTimeMillis;
                    pum.this.d.onProgress(puh.a(pum.this.e), pum.this.h);
                    return;
                } else {
                    LogUtil.a("Stress_DownloadTask", "dTime !< 0 or dTime !> 10");
                    return;
                }
            }
            if (i != 4) {
                if (i == 5) {
                    pum.this.b((pus) message.obj);
                    return;
                } else {
                    LogUtil.a("Stress_DownloadTask", "DownloadTask handleMessage default");
                    return;
                }
            }
            pum.this.i = true;
            removeCallbacksAndMessages(null);
            LogUtil.a("Stress_DownloadTask", "DownloadTask cancle");
            pum.this.d.onSuccess("cancel");
            pum.this.d = null;
        }
    };
    private volatile List<pus> c = new LinkedList();
    private AtomicInteger g = new AtomicInteger();

    public pum(List<pus> list, long j, UiCallback<String> uiCallback) {
        this.e = list;
        this.b = a(list);
        this.h = j;
        this.d = new e(uiCallback);
    }

    public void e() {
        LogUtil.a("Stress_DownloadTask", "run() = ", 0);
        UiCallback<String> uiCallback = this.d;
        if (uiCallback == null || uiCallback.isCanceled()) {
            return;
        }
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d() {
        pus a2 = a();
        if (a2 == null) {
            return false;
        }
        LogUtil.a("Stress_DownloadTask", "getDownloadFlag() in");
        UiCallback<String> uiCallback = this.d;
        if (uiCallback != null && uiCallback.isCanceled()) {
            this.f16264a.obtainMessage(4, -2, 0, puq.b(-2)).sendToTarget();
            return false;
        }
        b(a2);
        return true;
    }

    private pus a() {
        pus pusVar;
        synchronized (this) {
            if (this.b.size() != 0) {
                pusVar = this.b.get(0);
                this.b.remove(pusVar);
                this.c.add(pusVar);
            } else {
                pusVar = null;
            }
        }
        return pusVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(pus pusVar) {
        synchronized (this) {
            this.c.remove(pusVar);
            if (this.c.size() == 0) {
                if (this.b.size() == 0) {
                    return true;
                }
            }
            return false;
        }
    }

    private List<pus> a(List<pus> list) {
        LinkedList linkedList = new LinkedList();
        for (pus pusVar : list) {
            if (!pusVar.e()) {
                linkedList.add(pusVar);
            }
        }
        return linkedList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final pus pusVar) {
        if (this.i) {
            return;
        }
        if (this.d.isCanceled()) {
            this.f16264a.obtainMessage(4, -2, 0, puq.b(-2)).sendToTarget();
        } else {
            LogUtil.a("Stress_DownloadTask", "startDownload() in");
            pug.a().downloadFile(pusVar.a(), pusVar.d(), new DataCallback() { // from class: pum.5
                @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.DataCallback
                public void onFailure(int i, String str) {
                    if (pum.this.d.isCanceled()) {
                        return;
                    }
                    if (pum.this.g.addAndGet(1) > 3 || i == -2) {
                        pum.this.f16264a.obtainMessage(2, i, 0, str).sendToTarget();
                    } else {
                        pusVar.a(0L);
                        pum.this.f16264a.sendMessageDelayed(pum.this.f16264a.obtainMessage(5, pusVar), 1000L);
                    }
                }

                @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    pusVar.b(true);
                    pus pusVar2 = pusVar;
                    pusVar2.a(pusVar2.c());
                    LogUtil.a("Stress_DownloadTask", "startDownload() ", pusVar.a(), " downLoadSuccess");
                    if (pum.this.d != null) {
                        pum.this.f16264a.sendEmptyMessage(3);
                        if (!pum.this.e(pusVar)) {
                            LogUtil.a("Stress_DownloadTask", "startDownload() no end，keep on");
                            pum.this.d();
                        } else {
                            LogUtil.a("Stress_DownloadTask", "startDownload() all downLoad end");
                            pum.this.f16264a.sendEmptyMessage(1);
                        }
                    }
                }

                @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.DataCallback
                public void onProgress(long j, long j2, boolean z) {
                    pus pusVar2 = pusVar;
                    pusVar2.a(Math.min(j, pusVar2.c()));
                    pum.this.f16264a.sendEmptyMessage(3);
                }
            });
        }
    }

    static class e extends UiCallback {

        /* renamed from: a, reason: collision with root package name */
        private UiCallback f16265a;

        e(UiCallback uiCallback) {
            this.f16265a = uiCallback;
        }

        @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.UiCallback
        public void onFailure(int i, String str) {
            if (this.f16265a.isCanceled()) {
                return;
            }
            this.f16265a.onFailure(i, str);
        }

        @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.UiCallback
        public void onSuccess(Object obj) {
            if (this.f16265a.isCanceled()) {
                return;
            }
            this.f16265a.onSuccess(obj);
        }

        @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.UiCallback
        public void onProgress(long j, long j2) {
            if (this.f16265a.isCanceled()) {
                return;
            }
            this.f16265a.onProgress(j, j2);
        }

        @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.UiCallback
        public boolean isCanceled() {
            return this.f16265a.isCanceled();
        }
    }
}
