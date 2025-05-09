package defpackage;

import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.networkclient.IDownloadStrategy;
import com.huawei.networkclient.ProgressListener;
import health.compact.a.LogUtil;
import java.io.File;
import java.util.Map;

/* loaded from: classes5.dex */
public class lqg implements IDownloadStrategy<File> {

    /* renamed from: a, reason: collision with root package name */
    private Map<String, String> f14831a;
    private File b;
    private String c;
    private ProgressListener<File> d;
    private int e;
    private String i;
    private File j;

    public lqg(String str, Map<String, String> map, File file, ProgressListener<File> progressListener, String str2, int i) {
        this.j = null;
        this.i = str;
        this.d = progressListener;
        this.b = file;
        this.f14831a = map;
        this.e = i;
        this.c = str2;
    }

    public lqg(String str, Map<String, String> map, File file, File file2, ProgressListener<File> progressListener) {
        this.i = str;
        this.d = progressListener;
        this.b = file;
        this.f14831a = map;
        this.j = file2;
        this.c = null;
        this.e = 1;
    }

    public lqg(String str, Map<String, String> map, File file, ProgressListener<File> progressListener) {
        this(str, map, file, progressListener, null, 1);
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return this.i;
    }

    @Override // com.huawei.networkclient.IDownloadStrategy
    public int getRequestMethod() {
        return this.e;
    }

    @Override // com.huawei.networkclient.IDownloadStrategy
    public String getRequestBody() {
        return this.c;
    }

    @Override // com.huawei.networkclient.IDownloadStrategy
    public Map<String, String> getHeaders() {
        return this.f14831a;
    }

    @Override // com.huawei.networkclient.IDownloadStrategy
    public ProgressListener<File> getListener() {
        return this.d;
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x00a9, code lost:
    
        if (r13 == null) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00dd, code lost:
    
        com.huawei.haf.common.os.FileUtils.d(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00e0, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00db, code lost:
    
        r13 = r12.b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00d9, code lost:
    
        if (r13 != null) goto L47;
     */
    @Override // com.huawei.networkclient.IDownloadStrategy
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleResponseBody(com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody r13, int r14) {
        /*
            Method dump skipped, instructions count: 304
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lqg.handleResponseBody(com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody, int):void");
    }

    @Override // com.huawei.networkclient.IDownloadStrategy
    public void handleException(Throwable th, int i) {
        LogUtil.e("FileDownloadStrategy", "get exception:", ExceptionUtils.d(th), " errCode:", Integer.valueOf(i));
        File file = this.j;
        if (file == null) {
            FileUtils.i(this.b);
        } else {
            FileUtils.i(file);
        }
        this.d.onFail(th);
    }
}
