package com.huawei.maps.offlinedata.service.download.dto;

import com.huawei.hms.network.file.api.Progress;
import com.huawei.hms.network.file.api.Response;
import com.huawei.hms.network.file.api.Result;
import com.huawei.hms.network.file.api.exception.InterruptedException;
import com.huawei.hms.network.file.api.exception.NetworkException;
import com.huawei.hms.network.file.download.api.FileRequestCallback;
import com.huawei.hms.network.file.download.api.GetRequest;
import com.huawei.maps.offlinedata.handler.dto.persist.OfflineDataTaskEntity;
import com.huawei.maps.offlinedata.logpush.dto.a;
import com.huawei.maps.offlinedata.logpush.dto.b;
import com.huawei.maps.offlinedata.service.device.f;
import com.huawei.maps.offlinedata.service.persist.b;
import com.huawei.maps.offlinedata.utils.c;
import com.huawei.maps.offlinedata.utils.d;
import com.huawei.maps.offlinedata.utils.g;
import com.huawei.secure.android.common.util.SecurityCommonException;
import com.huawei.secure.android.common.util.ZipUtil;
import java.io.Closeable;
import java.io.File;
import java.util.List;

/* loaded from: classes5.dex */
public class a extends FileRequestCallback {

    /* renamed from: a, reason: collision with root package name */
    private volatile OfflineDataTaskEntity f6503a;
    private String b;
    private boolean c;
    private final com.huawei.maps.offlinedata.logpush.dto.a d = a.C0167a.b().a("FileDownloadCallback").c();

    public a(OfflineDataTaskEntity offlineDataTaskEntity) {
        this.c = true;
        this.f6503a = offlineDataTaskEntity;
        if (this.f6503a == null) {
            this.c = false;
        }
    }

    public OfflineDataTaskEntity a() {
        if (this.f6503a == null) {
            this.f6503a = b.a().b(this.b);
            if (this.f6503a == null) {
                g.d("FileDownloadCallback", "the task is null.");
            }
        }
        return this.f6503a;
    }

    @Override // com.huawei.hms.network.file.api.Callback
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public GetRequest onStart(GetRequest getRequest) {
        this.b = String.valueOf(getRequest.getId());
        g.a("FileDownloadCallback", "onStart, requestId is " + this.b);
        b();
        return getRequest;
    }

    @Override // com.huawei.hms.network.file.api.Callback
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onProgress(GetRequest getRequest, Progress progress) {
        int progress2 = progress.getProgress();
        g.a("FileDownloadCallback", "onProgress: " + progress2 + ", requestId:" + this.b);
        a().setDownloadProgress(Integer.valueOf(progress2));
        a(progress2);
    }

    @Override // com.huawei.hms.network.file.api.Callback
    public void onSuccess(Response<GetRequest, File, Closeable> response) {
        g.a("FileDownloadCallback", "onSuccess.");
        a(response.getContent());
    }

    @Override // com.huawei.hms.network.file.api.Callback
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onException(GetRequest getRequest, NetworkException networkException, Response<GetRequest, File, Closeable> response) {
        g.d("FileDownloadCallback", "onException e:" + networkException.getMessage());
        if (response != null) {
            g.d("FileDownloadCallback", "onException code:" + response.getCode() + ", message:" + response.getMessage());
        }
        if (networkException instanceof InterruptedException) {
            int statusCode = ((InterruptedException) networkException).getStatusCode();
            g.a("FileDownloadCallback", "downloadData onException errorCode：" + statusCode);
            if (Result.CANCEL == statusCode) {
                g.a("FileDownloadCallback", "cancel file download");
                c.b(new File(getRequest.getFilePath()));
                a().setTaskState("{\"download\":\"error\"}");
            } else if (Result.PAUSE == statusCode) {
                g.a("FileDownloadCallback", "pause file download");
                a().setTaskState("{\"download\":\"pause\"}");
            }
        }
        com.huawei.maps.offlinedata.logpush.c.a(new b.a().a("FILE_DOWNLOAD_FAILED").b(networkException.getMessage()).a());
        c();
    }

    private void b() {
        if (a() != null && !com.huawei.maps.offlinedata.service.download.b.a().c().contains(a())) {
            com.huawei.maps.offlinedata.service.download.b.a().c().add(a());
        }
        if (!com.huawei.maps.offlinedata.service.a.a().c()) {
            this.c = true;
        }
        if (!this.c) {
            com.huawei.maps.offlinedata.jsbridge.b.a().b("downloadHandler.start", this.b);
            return;
        }
        a().setTaskState("{\"download\":\"doing\"}");
        com.huawei.maps.offlinedata.service.persist.b.a().b(a().getId(), a().getItemState(), a().getTaskState());
        com.huawei.maps.offlinedata.jsbridge.b.a().b("syncData", d.a(a()));
    }

    private void a(File file) {
        this.d.b("0");
        com.huawei.maps.offlinedata.logpush.b.a(this.d);
        if (!com.huawei.maps.offlinedata.service.a.a().c()) {
            this.c = true;
        }
        com.huawei.maps.offlinedata.service.download.b.a().c().remove(a());
        if (!this.c) {
            com.huawei.maps.offlinedata.jsbridge.b.a().b("downloadHandler.success", this.b);
            return;
        }
        a().setDownloadProgress(100);
        a().setTaskState("{\"download\":\"finish\"}");
        com.huawei.maps.offlinedata.service.persist.b.a().a(a().getId(), a().getDownloadProgress().intValue(), 0);
        com.huawei.maps.offlinedata.service.persist.b.a().b(a().getId(), a().getItemState(), a().getTaskState());
        com.huawei.maps.offlinedata.jsbridge.b.a().b("syncData", d.a(a()));
        if (com.huawei.maps.offlinedata.service.a.a().c()) {
            return;
        }
        com.huawei.maps.offlinedata.service.download.b.a().f();
        if (!a(file.getPath(), com.huawei.maps.offlinedata.service.storage.a.a().e() + file.getName()) || com.huawei.maps.offlinedata.service.a.a().c()) {
            return;
        }
        f.a().b(a());
    }

    private boolean a(String str, String str2) {
        a().setTaskState("{\"decompress\":\"error\"}");
        boolean z = false;
        try {
            g.a("FileDownloadCallback", "start unZip offline map data");
            List<File> unZipNew = ZipUtil.unZipNew(str, str2, 5368709120L, 1000, true);
            c.b(new File(str));
            if (unZipNew != null && !unZipNew.isEmpty()) {
                a().setTaskState("{\"transmit\":\"queue\"}");
                z = true;
            }
            g.a("FileDownloadCallback", "unZip offline map data success.");
        } catch (SecurityCommonException unused) {
            c.a(new File(str2));
            g.d("FileDownloadCallback", "unZip: SecurityCommonException: ");
        } catch (IllegalArgumentException unused2) {
            c.a(new File(str2));
            g.d("FileDownloadCallback", "unZip: IllegalArgumentException: ");
        } catch (Exception unused3) {
            c.a(new File(str2));
            g.d("FileDownloadCallback", "unZip: Exception: ");
        }
        com.huawei.maps.offlinedata.service.persist.b.a().b(this.f6503a.getId(), this.f6503a.getItemState(), this.f6503a.getTaskState());
        return z;
    }

    private void a(int i) {
        if (a() != null && !com.huawei.maps.offlinedata.service.download.b.a().c().contains(a())) {
            com.huawei.maps.offlinedata.service.download.b.a().c().add(a());
        }
        if (!com.huawei.maps.offlinedata.service.a.a().c()) {
            this.c = true;
        }
        if (!this.c) {
            com.huawei.maps.offlinedata.jsbridge.b.a().b("downloadHandler.progress", this.b, String.valueOf(i));
        } else {
            com.huawei.maps.offlinedata.service.persist.b.a().a(a().getId(), i, 0);
            com.huawei.maps.offlinedata.jsbridge.b.a().b("syncData", d.a(a()));
        }
    }

    private void c() {
        this.d.b("060015");
        com.huawei.maps.offlinedata.logpush.b.a(this.d);
        if (!com.huawei.maps.offlinedata.service.a.a().c()) {
            this.c = true;
        }
        com.huawei.maps.offlinedata.service.download.b.a().c().remove(a());
        if (!this.c) {
            com.huawei.maps.offlinedata.jsbridge.b.a().b("downloadHandler.error", this.b);
            return;
        }
        com.huawei.maps.offlinedata.service.persist.b.a().b(a().getId(), a().getItemState(), a().getTaskState());
        com.huawei.maps.offlinedata.jsbridge.b.a().b("syncData", d.a(a()));
        if (com.huawei.maps.offlinedata.service.a.a().c()) {
            return;
        }
        com.huawei.maps.offlinedata.service.download.b.a().f();
    }
}
