package com.huawei.maps.offlinedata.service.download;

import android.text.TextUtils;
import com.huawei.hms.network.file.api.Callback;
import com.huawei.hms.network.file.api.RequestManager;
import com.huawei.hms.network.file.api.Result;
import com.huawei.hms.network.file.download.api.DownloadManager;
import com.huawei.hms.network.file.download.api.GetRequest;
import com.huawei.maps.offlinedata.handler.dto.DownloadRequest;
import com.huawei.maps.offlinedata.handler.dto.persist.OfflineDataTaskEntity;
import com.huawei.maps.offlinedata.utils.g;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static volatile a f6501a;
    private DownloadManager b = null;

    private a() {
        RequestManager.init(com.huawei.maps.offlinedata.utils.a.a());
        c();
    }

    public static a a() {
        if (f6501a == null) {
            synchronized (a.class) {
                if (f6501a == null) {
                    f6501a = new a();
                }
            }
        }
        return f6501a;
    }

    private void c() {
        this.b = new DownloadManager.Builder("offline_download_manager").commonConfig(DownloadManager.newGlobalRequestConfigBuilder().threadPoolSize(6).build()).build(com.huawei.maps.offlinedata.utils.a.a());
    }

    private boolean a(DownloadRequest downloadRequest) {
        if (TextUtils.isEmpty(downloadRequest.getDownloadUri())) {
            g.d("FileDownloadManager", "startOfflineDataDownload failed , downloadUrl is null");
            return false;
        }
        if (!TextUtils.isEmpty(downloadRequest.getFilePath())) {
            return true;
        }
        g.d("FileDownloadManager", "startOfflineDataDownload failed , local save filePath is null");
        return false;
    }

    private List<String> a(String str) {
        if (TextUtils.isEmpty(str)) {
            return new ArrayList();
        }
        String[] split = str.split(",");
        if (split.length == 0) {
            return new ArrayList();
        }
        return Arrays.asList(split);
    }

    private GetRequest b(DownloadRequest downloadRequest) {
        return DownloadManager.newGetRequestBuilder().filePath(downloadRequest.getFilePath()).backupUrls(a(downloadRequest.getBackDownloadUri())).name("offline_download_request").config(DownloadManager.newRequestConfigBuilder().retryTimes(3).build()).enableSlice(false).url(downloadRequest.getDownloadUri()).sha256(downloadRequest.getSha256()).build();
    }

    private boolean a(Result result) {
        if (result == null) {
            g.d("FileDownloadManager", "result is invalid, result is null");
            return true;
        }
        if (result.getCode() == Result.SUCCESS) {
            return false;
        }
        g.d("FileDownloadManager", "result is invalid , errorCode : " + result.getCode() + " ,msg: " + result.getMessage());
        return true;
    }

    public String a(DownloadRequest downloadRequest, OfflineDataTaskEntity offlineDataTaskEntity) {
        g.a("FileDownloadManager", "startOfflineDataDownload ...");
        if (!a(downloadRequest)) {
            return "";
        }
        GetRequest b = b(downloadRequest);
        if (a(this.b.start(b, (Callback) new com.huawei.maps.offlinedata.service.download.dto.a(offlineDataTaskEntity)))) {
            g.d("FileDownloadManager", "startOfflineDataDownload failed. result is invalid.");
            return "";
        }
        g.a("FileDownloadManager", "startOfflineDataDownload success, requestId is " + b.getId());
        return String.valueOf(b.getId());
    }

    public void b() {
        List<GetRequest> allRequests = this.b.getAllRequests();
        if (allRequests == null || allRequests.isEmpty()) {
            return;
        }
        g.a("FileDownloadManager", "pause all task from download manager, the size is " + allRequests.size());
        Iterator<GetRequest> it = allRequests.iterator();
        while (it.hasNext()) {
            this.b.pauseRequest(it.next().getId());
        }
    }

    public boolean a(long j) {
        g.a("FileDownloadManager", "cancelOfflineDataDownload ..." + j);
        if (j <= 0) {
            g.d("FileDownloadManager", "cancelOfflineDataDownloadfailed .requestId is invalid.");
            return false;
        }
        if (a(this.b.cancelRequest(j))) {
            g.d("FileDownloadManager", "cancelOfflineDataDownload failed,result is invalid.");
            return false;
        }
        g.a("FileDownloadManager", "cancelOfflineDataDownload success ...");
        return true;
    }

    public boolean a(long j, OfflineDataTaskEntity offlineDataTaskEntity) {
        g.a("FileDownloadManager", "resumeOfflineDataDownland ..." + j);
        if (j <= 0) {
            g.d("FileDownloadManager", "resumeOfflineDataDownland failed .requestId is invalid.");
            return false;
        }
        GetRequest request = this.b.getRequest(j);
        if (request == null) {
            g.d("FileDownloadManager", "resumeOfflineDataDownland failed , query getRequest by requestId is null ,can not resume.");
            return false;
        }
        if (a(this.b.resumeRequest(request, (Callback) new com.huawei.maps.offlinedata.service.download.dto.a(offlineDataTaskEntity)))) {
            g.d("FileDownloadManager", "resumeOfflineDataDownland failed, result is invalid");
            return false;
        }
        g.a("FileDownloadManager", "resumeOfflineDataDownland success ...");
        return true;
    }

    public boolean b(long j) {
        g.a("FileDownloadManager", "pauseOfflineDataDownland ..." + j);
        if (j <= 0) {
            g.d("FileDownloadManager", "pauseOfflineDataDownland failed .requestId is invalid.");
            return false;
        }
        if (a(this.b.pauseRequest(j))) {
            g.d("FileDownloadManager", "pauseOfflineDataDownland failed, result is invalid");
            return false;
        }
        g.a("FileDownloadManager", "pauseOfflineDataDownland success ...");
        return true;
    }
}
