package com.huawei.hms.network.file.a;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.network.embedded.g2;
import com.huawei.hms.network.file.api.GlobalRequestConfig;
import com.huawei.hms.network.file.api.Result;
import com.huawei.hms.network.file.core.Constants;
import com.huawei.hms.network.file.core.FileManagerException;
import com.huawei.hms.network.file.core.task.ITaskResult;
import com.huawei.hms.network.file.core.task.e;
import com.huawei.hms.network.file.core.task.j;
import com.huawei.hms.network.file.core.task.k;
import com.huawei.hms.network.file.core.task.l;
import com.huawei.hms.network.file.core.util.FLogger;
import com.huawei.hms.network.file.core.util.Utils;
import com.huawei.hms.network.file.download.api.GetRequest;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.inner.utils.CheckConfigUtils;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes4.dex */
public class b implements com.huawei.hms.network.file.core.task.c<GetRequest, d, e> {

    /* renamed from: a, reason: collision with root package name */
    com.huawei.hms.network.file.a.k.b.e f5597a;
    GlobalRequestConfig b;
    com.huawei.hms.network.file.core.c c;
    String d;
    String e;

    void d(long j) {
        com.huawei.hms.network.file.a.k.b.e eVar = this.f5597a;
        if (eVar == null) {
            return;
        }
        String a2 = eVar.a(j, this.d);
        this.f5597a.b(j, this.d);
        Utils.deleteFile(d.a(j, a2));
    }

    @Override // com.huawei.hms.network.file.core.task.c
    /* renamed from: c, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void b(GetRequest getRequest) {
        com.huawei.hms.network.file.a.k.b.e eVar = this.f5597a;
        if (eVar == null) {
            return;
        }
        eVar.a(getRequest, this.d);
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public void c() {
        this.c.f();
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public com.huawei.hms.network.file.core.e<GetRequest> c(long j) {
        com.huawei.hms.network.file.core.e<GetRequest> c;
        com.huawei.hms.network.file.a.k.b.e eVar = this.f5597a;
        if (eVar == null || (c = eVar.c(j, this.d)) == null) {
            return null;
        }
        if (!k.d(c.b())) {
            FLogger.d("DownloadRequestHandler", "getCachedRequest:" + c.a(), new Object[0]);
            return c;
        }
        FLogger.i("DownloadRequestHandler", "getCachedRequest request is finished, delete it:" + j + ",status:" + c.b(), new Object[0]);
        this.f5597a.a(j, this.d);
        return null;
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public void b(List<d> list) {
        if (this.f5597a == null) {
            return;
        }
        this.f5597a.a(c(list), this.d);
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public void b(GetRequest getRequest, e.a aVar) {
        com.huawei.hms.network.file.a.k.b.e eVar = this.f5597a;
        if (eVar == null) {
            return;
        }
        eVar.a(getRequest, aVar, this.d);
    }

    @Override // com.huawei.hms.network.file.core.task.c
    /* renamed from: b, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public List<d> c(GetRequest getRequest) {
        List<d> a2 = a(getRequest, getRequest.getFileSize());
        a(getRequest.getId(), a2);
        return a2;
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public List<d> b(long j) {
        com.huawei.hms.network.file.a.k.b.e eVar = this.f5597a;
        if (eVar == null) {
            return null;
        }
        List<d> d = eVar.d(j, this.d);
        com.huawei.hms.network.file.core.e<GetRequest> c = c(j);
        if (c == null) {
            FLogger.w("DownloadRequestHandler", "getCachedTasks requestInfo not exist:" + j, new Object[0]);
        } else {
            if (b(j, d) || c.a() == null) {
                if (c.a() != null) {
                    a aVar = new a();
                    a(c.a(), c.a().getFileSize(), aVar);
                    Iterator<d> it = d.iterator();
                    while (it.hasNext()) {
                        it.next().b(aVar.a());
                    }
                }
                return d;
            }
            Utils.deleteFile(d.a(j, c.a().getFilePath()));
            this.f5597a.b(j, this.d);
        }
        return Collections.emptyList();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.huawei.hms.network.file.core.task.c
    public e b() {
        return new e(this.b, this.c.a());
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public boolean a(List<d> list) {
        k a2 = k.a(list);
        if (com.huawei.hms.network.file.core.util.c.a(((d) Utils.cast(a2)).B()).exists()) {
            return true;
        }
        FLogger.w("DownloadRequestHandler", "task tmpFile not exist taskid:" + a2.f(), new Object[0]);
        return false;
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public void a(GetRequest getRequest, e.a aVar) {
        if (this.f5597a == null) {
            return;
        }
        if (aVar == e.a.INVALID) {
            FLogger.i("DownloadRequestHandler", "onRequestStatusChanged delete request data," + getRequest.getId() + ",status:" + aVar, new Object[0]);
            d(getRequest.getId());
            return;
        }
        FLogger.i("DownloadRequestHandler", "onRequestStatusChanged update, " + getRequest.getId() + ",status:" + aVar, new Object[0]);
        this.f5597a.b(getRequest, aVar, this.d);
    }

    void a(d dVar, d dVar2) {
        dVar2.a(dVar.A(), dVar.y());
        dVar2.e(dVar.t());
        dVar2.b(dVar.k());
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public void a(long j, boolean z) {
        com.huawei.hms.network.file.a.k.b.e eVar = this.f5597a;
        if (eVar == null) {
            return;
        }
        eVar.b(j, this.d);
        if (z) {
            return;
        }
        String a2 = this.f5597a.a(j, this.d);
        if (Utils.isBlank(a2)) {
            return;
        }
        Utils.deleteFile(d.a(j, a2));
    }

    public void a(long j, List<d> list) {
        com.huawei.hms.network.file.a.k.b.e eVar = this.f5597a;
        if (eVar == null) {
            return;
        }
        eVar.a(j, list, this.d);
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public void a(long j) {
        FLogger.i("DownloadRequestHandler", "onRequestCanceled:" + j, new Object[0]);
        d(j);
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public Set<Long> a(int i) {
        com.huawei.hms.network.file.a.k.b.e eVar = this.f5597a;
        if (eVar == null) {
            return null;
        }
        return eVar.a(this.d, i);
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public List<GetRequest> a(boolean z) {
        com.huawei.hms.network.file.a.k.b.e eVar = this.f5597a;
        if (eVar == null) {
            return null;
        }
        List<com.huawei.hms.network.file.core.e<GetRequest>> a2 = eVar.a(this.d);
        ArrayList arrayList = new ArrayList();
        if (!Utils.isEmpty(a2)) {
            for (com.huawei.hms.network.file.core.e<GetRequest> eVar2 : a2) {
                if (!z || !k.d(eVar2.b())) {
                    arrayList.add(eVar2.a());
                }
            }
        }
        return arrayList;
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public List<d> a(GetRequest getRequest, List<d> list, long j) {
        int e = com.huawei.hms.network.file.core.b.e();
        if (j < e) {
            FLogger.i("DownloadRequestHandler", "onTaskChange no need slice again for size:" + j + ",sliceThreshold:" + e, new Object[0]);
        } else {
            List<d> a2 = a(getRequest, j);
            if (!Utils.isEmpty(a2) && a2.size() > 1) {
                a(a2.get(0), list.get(0));
                List<d> subList = a2.subList(1, a2.size());
                ArrayList arrayList = new ArrayList();
                arrayList.add(list.get(0));
                arrayList.addAll(subList);
                com.huawei.hms.network.file.a.k.b.e eVar = this.f5597a;
                if (eVar != null) {
                    eVar.b(getRequest.getId(), arrayList, this.d);
                }
                return subList;
            }
            FLogger.i("DownloadRequestHandler", "onTaskChange newTasks size is too small", new Object[0]);
        }
        return Collections.emptyList();
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public j a() {
        return new c(this);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public com.huawei.hms.network.file.core.task.g a(String str) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == -1575355461) {
            if (str.equals("h3_pcc_multipath")) {
                c = 3;
            }
            c = 65535;
        } else if (hashCode == -1267495076) {
            if (str.equals("h3_pcc")) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode != 0) {
            switch (hashCode) {
                case 3273:
                    if (str.equals("h1")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 3274:
                    if (str.equals(CheckConfigUtils.Constants.HTTP_2)) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 3275:
                    if (str.equals(g2.H3)) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
        } else {
            if (str.equals("")) {
                c = 5;
            }
            c = 65535;
        }
        if (c == 0) {
            HttpClient b = this.c.b();
            GlobalRequestConfig globalRequestConfig = this.b;
            if (b == null) {
                b = this.c.a();
            }
            return new e(globalRequestConfig, b);
        }
        if (c == 1) {
            HttpClient c2 = this.c.c();
            GlobalRequestConfig globalRequestConfig2 = this.b;
            if (c2 == null) {
                c2 = this.c.a();
            }
            return new e(globalRequestConfig2, c2);
        }
        if (c == 2) {
            HttpClient d = this.c.d();
            GlobalRequestConfig globalRequestConfig3 = this.b;
            if (d == null) {
                d = this.c.a();
            }
            return new e(globalRequestConfig3, d);
        }
        if (c != 3) {
            return new e(this.b, this.c.a());
        }
        HttpClient e = this.c.e();
        GlobalRequestConfig globalRequestConfig4 = this.b;
        if (e == null) {
            e = this.c.a();
        }
        return new e(globalRequestConfig4, e);
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public ITaskResult a(GetRequest getRequest, List<d> list) {
        FLogger.d("DownloadRequestHandler", "download compose start", new Object[0]);
        d dVar = (d) k.a(list);
        String B = dVar.B();
        File a2 = com.huawei.hms.network.file.core.util.c.a(B);
        if (!a2.exists()) {
            FLogger.e("DownloadRequestHandler", "download compose tmpfile not exist");
            return new l(Constants.ErrorCode.TASK_COMPOSE_TMPFILE_ERROR);
        }
        a(getRequest, B);
        File a3 = com.huawei.hms.network.file.core.util.c.a(dVar.z());
        if (!a2.renameTo(a3)) {
            FLogger.e("DownloadRequestHandler", "download compose rename file failed!");
            if (a3.isDirectory()) {
                FLogger.w("DownloadRequestHandler", "The download file path is a directory. Please add the file name to the download file path!", new Object[0]);
            }
            return new l(Constants.ErrorCode.TASK_COMPOSE_TMPFILE_ERROR);
        }
        FLogger.d("DownloadRequestHandler", "download compose success", new Object[0]);
        l lVar = new l(Constants.ErrorCode.SUCCESS);
        lVar.setTask(dVar);
        if (dVar.u() != null) {
            lVar.setRawResponse(dVar.u().getRawResponse());
        } else {
            FLogger.e("DownloadRequestHandler", "resultResult is null:" + getRequest.getId());
        }
        return lVar;
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public Result a(GetRequest getRequest) {
        return (Utils.isUrlValid(getRequest.getUrl()) && Utils.isUrlValid(getRequest.getBackupUrls())) ? Result.RESULT_SUCCESS : new Result(Constants.ErrorCode.REQUEST_URL_EMPTY);
    }

    private List<d> c(List<d> list) {
        ArrayList arrayList = new ArrayList();
        for (d dVar : list) {
            if (dVar.c() > 0) {
                arrayList.add(new d(dVar.p(), dVar.c() - 1, dVar.A(), dVar.y(), dVar.z(), dVar.t(), dVar.f()));
            } else {
                arrayList.add(dVar);
            }
        }
        return arrayList;
    }

    private boolean b(GetRequest getRequest, String str) {
        String sha256 = getRequest.getSha256();
        if (Utils.isBlank(sha256)) {
            FLogger.w("DownloadRequestHandler", "checkDownloadedFile warning: file sha256 is null", new Object[0]);
            return true;
        }
        String fileHashData = Utils.getFileHashData(str, "SHA-256");
        if (sha256.equalsIgnoreCase(fileHashData)) {
            return true;
        }
        FLogger.w("DownloadRequestHandler", "file sha256 check failed expect:" + StringUtils.anonymizeMessage(sha256) + ", actual:" + StringUtils.anonymizeMessage(fileHashData), new Object[0]);
        return false;
    }

    private boolean b(long j, List<d> list) {
        if (Utils.isEmpty(list)) {
            return true;
        }
        com.huawei.hms.network.file.core.e<GetRequest> c = c(j);
        if (c == null) {
            FLogger.w("DownloadRequestHandler", "checkAndFillDownloadTasks requestInfo not exist:" + j, new Object[0]);
            return false;
        }
        int size = list.size();
        String str = null;
        for (d dVar : list) {
            dVar.a((d) c.a());
            dVar.b(c.a().getFileSize());
            dVar.e(size);
            if (Utils.isBlank(dVar.z())) {
                if (Utils.isBlank(str)) {
                    str = c.a().getFilePath();
                    dVar.f(str);
                    FLogger.w("DownloadRequestHandler", "checkDownloadTasks fix filePath:" + str, new Object[0]);
                } else {
                    dVar.f(str);
                }
            }
            if (!com.huawei.hms.network.file.core.util.c.a(dVar.B()).exists()) {
                FLogger.w("DownloadRequestHandler", "cached task tmpFile not exist taskid:" + dVar.f(), new Object[0]);
                return false;
            }
            if (dVar.y() > 0 && dVar.A() > dVar.y()) {
                FLogger.w("DownloadRequestHandler", "task pos invfalid:" + dVar, new Object[0]);
                return false;
            }
        }
        return true;
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private int f5598a = 0;

        void b() {
            this.f5598a |= 1;
        }

        int a() {
            return this.f5598a;
        }

        a() {
        }
    }

    private void a(GetRequest getRequest, String str) {
        try {
            try {
                RandomAccessFile a2 = com.huawei.hms.network.file.core.util.c.a(str, "r");
                if (getRequest.getFileSize() > 0 && a2.length() != getRequest.getFileSize()) {
                    String str2 = " fileSize= " + a2.length() + " vs " + getRequest.getFileSize();
                    FLogger.e("DownloadRequestHandler", "file length error:" + str2);
                    throw new FileManagerException(Constants.ErrorCode.FILE_SIZE_ERROR, str2);
                }
                if (!b(getRequest, str)) {
                    FLogger.e("DownloadRequestHandler", "downloadTask : " + getRequest.getId() + "check file hash failed");
                    throw new FileManagerException(Constants.ErrorCode.CHECK_FILE_HASH_FAILED);
                }
                FLogger.i("DownloadRequestHandler", "downloadTask id: " + getRequest.getId() + " checkDownloadedFile succeed", new Object[0]);
                Utils.close(a2);
            } catch (IOException unused) {
                throw new FileManagerException(Constants.ErrorCode.FILE_IO_EXCEPTION);
            }
        } catch (Throwable th) {
            Utils.close(null);
            throw th;
        }
    }

    private List<d> a(GetRequest getRequest, long j) {
        b bVar = this;
        a aVar = new a();
        int a2 = bVar.a(getRequest, j, aVar);
        long j2 = j / a2;
        ArrayList arrayList = new ArrayList();
        String str = "";
        int i = 0;
        while (i < a2) {
            long j3 = i * j2;
            long j4 = i == a2 + (-1) ? j - 1 : (j3 + j2) - 1;
            long offset = getRequest.getOffset();
            long offset2 = j4 < 0 ? -1L : j4 + getRequest.getOffset();
            if (TextUtils.isEmpty(str)) {
                str = getRequest.getFilePath();
                if (TextUtils.isEmpty(str)) {
                    str = bVar.e + "hwdownloadfile_" + getRequest.getId();
                }
            }
            String str2 = str;
            ArrayList arrayList2 = arrayList;
            d dVar = new d(getRequest, 0L, offset + j3, offset2, str2, a2);
            dVar.b(aVar.a());
            arrayList2.add(dVar);
            i++;
            arrayList = arrayList2;
            str = str2;
            bVar = this;
        }
        return arrayList;
    }

    private int a(GetRequest getRequest, long j, a aVar) {
        if (getRequest.getConfig() != null) {
            boolean isEnableSlice = getRequest.isEnableSlice();
            long speedLimit = getRequest.getSpeedLimit();
            if (!isEnableSlice || speedLimit > 0) {
                return 1;
            }
        }
        int e = com.huawei.hms.network.file.core.b.e();
        if (j < e) {
            return 1;
        }
        int a2 = com.huawei.hms.network.file.a.j.a.b.b().a(j, 1000L);
        if (a2 > 0) {
            FLogger.i("DownloadRequestHandler", "getSliceNum use predictSliceNum:" + a2, new Object[0]);
            aVar.b();
        } else {
            a2 = com.huawei.hms.network.file.core.b.d();
        }
        FLogger.i("DownloadRequestHandler", "getConfigSliceNum sliceThreshold:" + e + ",sliceNum:" + a2, new Object[0]);
        return a2;
    }

    public b(Context context, String str, GlobalRequestConfig globalRequestConfig, String str2) {
        this.b = globalRequestConfig;
        this.c = new com.huawei.hms.network.file.core.c(context, globalRequestConfig, str);
        this.d = str;
        this.e = str2;
        com.huawei.hms.network.file.a.k.b.e a2 = com.huawei.hms.network.file.a.k.b.e.a(context);
        this.f5597a = a2;
        if (a2.a()) {
            return;
        }
        FLogger.e("DownloadRequestHandler", "create DB RunTimeException init DB failed!");
        this.f5597a = null;
    }
}
