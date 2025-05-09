package com.huawei.hms.network.file.b;

import android.content.Context;
import com.huawei.hms.network.embedded.g2;
import com.huawei.hms.network.file.api.GlobalRequestConfig;
import com.huawei.hms.network.file.api.Result;
import com.huawei.hms.network.file.core.Constants;
import com.huawei.hms.network.file.core.task.ITaskResult;
import com.huawei.hms.network.file.core.task.e;
import com.huawei.hms.network.file.core.task.j;
import com.huawei.hms.network.file.core.task.l;
import com.huawei.hms.network.file.core.util.FLogger;
import com.huawei.hms.network.file.core.util.Utils;
import com.huawei.hms.network.file.upload.api.BodyRequest;
import com.huawei.hms.network.file.upload.api.PostRequest;
import com.huawei.hms.network.file.upload.api.PutRequest;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.inner.utils.CheckConfigUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/* loaded from: classes4.dex */
public class d implements com.huawei.hms.network.file.core.task.c<BodyRequest, f, h> {

    /* renamed from: a, reason: collision with root package name */
    GlobalRequestConfig f5619a;
    com.huawei.hms.network.file.core.c b;

    @Override // com.huawei.hms.network.file.core.task.c
    public List<f> a(BodyRequest bodyRequest, List<f> list, long j) {
        return null;
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public List<BodyRequest> a(boolean z) {
        return null;
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public Set<Long> a(int i) {
        return null;
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public void a(long j) {
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public void a(long j, boolean z) {
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public void a(BodyRequest bodyRequest, e.a aVar) {
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public boolean a(List<f> list) {
        return true;
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public void b(BodyRequest bodyRequest, e.a aVar) {
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public void b(List<f> list) {
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public com.huawei.hms.network.file.core.e<BodyRequest> c(long j) {
        return null;
    }

    @Override // com.huawei.hms.network.file.core.task.c
    /* renamed from: c, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void b(BodyRequest bodyRequest) {
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public void c() {
        this.b.f();
    }

    @Override // com.huawei.hms.network.file.core.task.c
    /* renamed from: b, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public List<f> c(BodyRequest bodyRequest) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new f(bodyRequest));
        return arrayList;
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public List<f> b(long j) {
        return Collections.emptyList();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.huawei.hms.network.file.core.task.c
    public h b() {
        return new h(this.f5619a, this.b.a());
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public j a() {
        return new e(this);
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
            HttpClient b = this.b.b();
            GlobalRequestConfig globalRequestConfig = this.f5619a;
            if (b == null) {
                b = this.b.a();
            }
            return new h(globalRequestConfig, b);
        }
        if (c == 1) {
            HttpClient c2 = this.b.c();
            GlobalRequestConfig globalRequestConfig2 = this.f5619a;
            if (c2 == null) {
                c2 = this.b.a();
            }
            return new h(globalRequestConfig2, c2);
        }
        if (c == 2) {
            HttpClient d = this.b.d();
            GlobalRequestConfig globalRequestConfig3 = this.f5619a;
            if (d == null) {
                d = this.b.a();
            }
            return new h(globalRequestConfig3, d);
        }
        if (c != 3) {
            return new h(this.f5619a, this.b.a());
        }
        HttpClient e = this.b.e();
        GlobalRequestConfig globalRequestConfig4 = this.f5619a;
        if (e == null) {
            e = this.b.a();
        }
        return new h(globalRequestConfig4, e);
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public ITaskResult a(BodyRequest bodyRequest, List<f> list) {
        l lVar = new l(Constants.ErrorCode.SUCCESS);
        lVar.setTask(list.get(0));
        if (list.get(0).u() != null) {
            lVar.setRawResponse(list.get(0).u().getRawResponse());
        } else {
            FLogger.e("UploadRequestHandler", "resultResult is null:" + bodyRequest.getId());
        }
        return lVar;
    }

    @Override // com.huawei.hms.network.file.core.task.c
    public Result a(BodyRequest bodyRequest) {
        if (!Utils.isUrlValid(bodyRequest.getUrl()) || !Utils.isUrlValid(bodyRequest.getBackupUrls())) {
            return new Result(Constants.ErrorCode.REQUEST_URL_EMPTY);
        }
        if ((bodyRequest instanceof PutRequest) && Utils.isEmpty(((PutRequest) bodyRequest).getFileEntityList())) {
            return new Result(Constants.ErrorCode.TASK_UPLOAD_PARAMS_COMMON_ERROR.getErrorCode(), Constants.ErrorCode.TASK_UPLOAD_PARAMS_COMMON_ERROR.getErrorMessage() + "fileList can not be empty");
        }
        if (!(bodyRequest instanceof PostRequest) || !((PostRequest) bodyRequest).getFileEntityMap().isEmpty()) {
            return Result.RESULT_SUCCESS;
        }
        return new Result(Constants.ErrorCode.TASK_UPLOAD_PARAMS_COMMON_ERROR.getErrorCode(), Constants.ErrorCode.TASK_UPLOAD_PARAMS_COMMON_ERROR.getErrorMessage() + "fileList can not be empty");
    }

    public d(Context context, String str, GlobalRequestConfig globalRequestConfig) {
        this.f5619a = globalRequestConfig;
        this.b = new com.huawei.hms.network.file.core.c(context, globalRequestConfig, str);
    }
}
