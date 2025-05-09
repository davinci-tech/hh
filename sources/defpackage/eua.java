package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Media;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.health.sport.utils.ResultUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.jsoperation.JsUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class eua {
    private c c;
    private List<Media> e;
    private volatile boolean h;
    private volatile List<Media> i;
    private long j;
    private long o;

    /* renamed from: a, reason: collision with root package name */
    private Handler f12320a = new Handler(Looper.getMainLooper()) { // from class: eua.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (eua.this.h || message == null) {
                return;
            }
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                eua.this.f12320a.removeCallbacksAndMessages(null);
                eua.this.c.onSuccess(null);
                return;
            }
            if (i == 2) {
                eua.this.h = true;
                eua.this.f12320a.removeCallbacksAndMessages(null);
                eua.this.c.onFailure(message.arg1, String.valueOf(message.obj));
                return;
            }
            if (i == 3) {
                long currentTimeMillis = System.currentTimeMillis();
                long j = currentTimeMillis - eua.this.o;
                if (j < 0 || j > 10) {
                    eua.this.o = currentTimeMillis;
                    LogUtil.a("Suggestion_DownloadTask", "progress info: ", Long.valueOf(etn.d((List<Media>) eua.this.e)), ",", Long.valueOf(eua.this.j));
                    eua.this.c.onProgress(etn.d((List<Media>) eua.this.e), eua.this.j);
                    return;
                }
                return;
            }
            if (i == 4) {
                eua.this.h = true;
                eua.this.b();
                eua.this.f12320a.removeCallbacksAndMessages(null);
            } else if (i == 5 && (message.obj instanceof Media)) {
                eua.this.e((Media) message.obj);
            }
        }
    };
    private volatile Map<String, Media> d = new ConcurrentHashMap();
    private volatile Map<String, Media> b = new ConcurrentHashMap();
    private volatile Map<String, List<Media>> g = new ConcurrentHashMap();
    private AtomicInteger f = new AtomicInteger();

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogUtil.a("Suggestion_DownloadTask", "start clean downloading task ");
        for (String str : this.d.keySet()) {
            eqa.a().cancelDownloadFile(str);
            LogUtil.a("Suggestion_DownloadTask", "clean downloading task ", str);
        }
    }

    public eua(List<Media> list, long j, UiCallback<String> uiCallback) {
        this.e = list;
        this.i = e(list);
        this.j = j;
        this.c = new c(uiCallback);
        LogUtil.a("Suggestion_DownloadTask", "construct downloadTask with pendingSize=", Integer.valueOf(this.i.size()), ", totalLength=", Long.valueOf(this.j), ", symbolicEnable=", true);
    }

    public void e() {
        for (int i = 0; i < 3 && a(); i++) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a() {
        Media d = d();
        if (d == null) {
            return false;
        }
        e(d);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Media media) {
        if (this.h) {
            return;
        }
        if (this.c.isCanceled()) {
            this.f12320a.obtainMessage(4, -2, 0, ResultUtil.d(-2)).sendToTarget();
            return;
        }
        String url = media.getUrl();
        if (!this.b.containsKey(url) && !this.g.containsKey(url)) {
            eqa.a().downloadFile(url, media.getPath(), new a(media));
        } else {
            d(media);
            a();
        }
    }

    private Media d() {
        Media media;
        synchronized (this) {
            if (this.i.size() > 0) {
                media = this.i.get(0);
                String url = media.getUrl();
                if (!this.d.containsKey(url) && !this.b.containsKey(url)) {
                    this.d.put(url, media);
                    this.i.remove(media);
                }
                if (this.g.get(url) == null) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(media);
                    this.g.put(url, arrayList);
                } else {
                    this.g.get(url).add(media);
                }
                this.i.remove(media);
            } else {
                media = null;
            }
        }
        return media;
    }

    private boolean b(Media media) {
        boolean z;
        synchronized (this) {
            String url = media.getUrl();
            if (!this.b.containsKey(url)) {
                this.b.put(url, media);
            }
            this.d.remove(url);
            if (this.d.size() == 0) {
                z = this.i.size() == 0;
            }
        }
        return z;
    }

    private List<Media> e(List<Media> list) {
        LinkedList linkedList = new LinkedList();
        for (Media media : list) {
            if (media != null && !media.isFinished() && !TextUtils.isEmpty(media.getUrl())) {
                linkedList.add(media);
            }
        }
        return linkedList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c() {
        try {
            Iterator<Map.Entry<String, List<Media>>> it = this.g.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, List<Media>> next = it.next();
                List<Media> value = next.getValue();
                if (CollectionUtils.d(value)) {
                    LogUtil.b("Suggestion_DownloadTask", "processPaths() URL[", next.getKey(), "] no need to copy");
                } else {
                    Media media = this.b.get(next.getKey());
                    if (media == null) {
                        LogUtil.b("Suggestion_DownloadTask", "processPaths() URL[", next.getKey(), "] have no source");
                    } else {
                        File file = new File(media.getPath());
                        if (file.exists() && !file.isDirectory()) {
                            for (Media media2 : value) {
                                if (!media2.getPath().equals(media.getPath())) {
                                    File file2 = new File(media2.getPath());
                                    c(file, file2);
                                    e(media2, new JSONObject());
                                    squ.e(file2.getPath(), file2.lastModified());
                                }
                            }
                            it.remove();
                        }
                        LogUtil.b("Suggestion_DownloadTask", "processPaths() FILE[", file.getName(), "] is directory or not exist");
                    }
                }
            }
        } catch (IOException e) {
            LogUtil.b("Suggestion_DownloadTask", "CheckedUiCallback onSuccess() IOException, MSG=", e.getMessage());
        }
        return this.g.isEmpty();
    }

    private void c(File file, File file2) throws IOException {
        boolean bv = CommonUtil.bv();
        if (Files.isSymbolicLink(file2.toPath())) {
            if (bv) {
                ReleaseLogUtil.d("Suggestion_DownloadTask", "copySingleFile() skip by exist symbolic: ", file2.getName());
                return;
            } else {
                LogUtil.h("Suggestion_DownloadTask", "copySingleFile() skip copy: file[", file2.getAbsolutePath(), " is already symbolic by file[", file2.getCanonicalPath(), "]");
                return;
            }
        }
        c(file2);
        Files.createSymbolicLink(file2.toPath(), file.toPath(), new FileAttribute[0]);
        if (bv) {
            ReleaseLogUtil.e("Suggestion_DownloadTask", "copySingleFile() symbolic create success, file: ", file2.getName());
        } else {
            LogUtil.a("Suggestion_DownloadTask", "copySingleFile() create symbolic file[", file2.getAbsolutePath(), "] from file[", file2.getCanonicalPath(), "] at ", Long.valueOf(file2.lastModified()));
        }
    }

    private void c(File file) throws IOException {
        if (file.isDirectory()) {
            FileUtils.e(file);
        }
        if (!file.exists() || file.delete()) {
            return;
        }
        throw new IOException(file.getName() + " delete failed!");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(Media media, JSONObject jSONObject) {
        String url;
        media.setFinished(true);
        if (media.getType() == 3) {
            String optString = jSONObject.optString(JsUtil.DATA_LIST);
            if (!TextUtils.isEmpty(optString)) {
                File file = new File(optString);
                squ.e(file.getPath(), file.lastModified());
            }
        }
        if (CommonUtil.bv()) {
            url = new File(media.getPath()).getName();
        } else {
            url = media.getUrl();
        }
        ReleaseLogUtil.e("Suggestion_DownloadTask", url, " download success");
        d(media);
        return b(media);
    }

    private void d(Media media) {
        media.setDownloadLength(media.getLength());
        this.f12320a.sendEmptyMessage(3);
    }

    static class c extends UiCallback {

        /* renamed from: a, reason: collision with root package name */
        private UiCallback f12321a;

        c(UiCallback uiCallback) {
            if (uiCallback == null) {
                this.f12321a = new UiCallback() { // from class: eua.c.1
                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    public void onFailure(int i, String str) {
                    }

                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    public void onSuccess(Object obj) {
                    }
                };
            } else {
                this.f12321a = uiCallback;
            }
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            if (this.f12321a.isCanceled()) {
                return;
            }
            this.f12321a.onFailure(i, str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onSuccess(Object obj) {
            if (this.f12321a.isCanceled()) {
                return;
            }
            this.f12321a.onSuccess(obj);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onProgress(long j, long j2) {
            if (this.f12321a.isCanceled()) {
                return;
            }
            this.f12321a.onProgress(j, j2);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public boolean isCanceled() {
            return this.f12321a.isCanceled();
        }
    }

    class a extends DataCallback {
        private final Media e;

        a(Media media) {
            this.e = media;
        }

        @Override // com.huawei.basefitnessadvice.callback.DataCallback
        public void onFailure(int i, String str) {
            LogUtil.h("Suggestion_DownloadTask", "startDownload:", "errorCode=", "", Integer.valueOf(i), "   errorInfo=", LogAnonymous.b(str));
            int addAndGet = eua.this.f.addAndGet(1);
            if (addAndGet > 3 || i == -2) {
                eua.this.f12320a.obtainMessage(2, i, 0, str).sendToTarget();
                return;
            }
            LogUtil.b("Suggestion_DownloadTask", "download error url:", this.e.getUrl(), " download error retry times:", Integer.valueOf(addAndGet));
            this.e.setDownloadLength(0L);
            eua.this.f12320a.sendMessageDelayed(eua.this.f12320a.obtainMessage(5, this.e), 1000L);
        }

        @Override // com.huawei.basefitnessadvice.callback.DataCallback
        public void onSuccess(JSONObject jSONObject) {
            if (eua.this.e(this.e, jSONObject)) {
                if (eua.this.c()) {
                    LogUtil.a("Suggestion_DownloadTask", this.e.getUrl(), "MESSAGE_SUCCESS");
                    eua.this.f12320a.sendEmptyMessage(1);
                    return;
                } else {
                    eua.this.f12320a.obtainMessage(2, 200011, 0, "Copy duplicated files failed").sendToTarget();
                    return;
                }
            }
            eua.this.a();
        }

        @Override // com.huawei.basefitnessadvice.callback.DataCallback
        public void onProgress(long j, long j2, boolean z) {
            if (eua.this.c.isCanceled()) {
                eua.this.f12320a.obtainMessage(4, -2, 0, ResultUtil.d(-2)).sendToTarget();
                return;
            }
            Media media = this.e;
            media.setDownloadLength(Math.min(j, media.getLength()));
            eua.this.f12320a.sendEmptyMessage(3);
        }
    }
}
