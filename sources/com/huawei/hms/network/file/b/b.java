package com.huawei.hms.network.file.b;

import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.network.base.common.MediaType;
import com.huawei.hms.network.base.common.MultipartBody;
import com.huawei.hms.network.file.api.GlobalRequestConfig;
import com.huawei.hms.network.file.core.Constants;
import com.huawei.hms.network.file.core.FileManagerException;
import com.huawei.hms.network.file.core.task.l;
import com.huawei.hms.network.file.core.util.FLogger;
import com.huawei.hms.network.file.upload.api.BodyRequest;
import com.huawei.hms.network.file.upload.api.FileEntity;
import com.huawei.hms.network.file.upload.api.PostRequest;
import com.huawei.hms.network.file.upload.api.PutRequest;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.Response;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class b implements com.huawei.hms.network.file.core.task.b<BodyRequest, f> {
    static final MediaType e = MediaType.parse(RequestBody.HEAD_VALUE_CONTENT_TYPE_FORM_DATA);

    /* renamed from: a, reason: collision with root package name */
    GlobalRequestConfig f5617a;
    g b;
    f c;
    HttpClient d;

    @Override // com.huawei.hms.network.file.core.task.b
    public Closeable a(f fVar, com.huawei.hms.network.file.core.task.f fVar2) {
        com.huawei.hms.network.httpclient.RequestBody build;
        this.c = fVar;
        this.b = new g(this.f5617a, this.d);
        BodyRequest p = fVar.p();
        long j = 0;
        if (p instanceof PutRequest) {
            build = new c(fVar, ((PutRequest) p).getFileEntityList(), fVar2);
            try {
                j = build.contentLength();
            } catch (IOException e2) {
                FLogger.e("UploadImpl", "get requestBody contentLength error", e2);
            }
        } else {
            PostRequest postRequest = (PostRequest) p;
            MultipartBody.Builder type = new MultipartBody.Builder().setType(e);
            if (!postRequest.getParams().isEmpty()) {
                for (Map.Entry<String, String> entry : postRequest.getParams().entrySet()) {
                    FLogger.d("UploadImpl", "FormDataPart:" + entry, new Object[0]);
                    type.addFormDataPart(entry.getKey(), entry.getValue());
                }
            }
            for (String str : postRequest.getFileEntityMap().keySet()) {
                List<FileEntity> list = postRequest.getFileEntityMap().get(str);
                if (list == null || list.isEmpty()) {
                    FLogger.w("UploadImpl", "no FileEntity for:" + str, new Object[0]);
                } else {
                    FLogger.d("UploadImpl", "fileParams for:" + str + ",fileListSize:" + list.size(), new Object[0]);
                    String name = list.get(0).getName();
                    String fileName = list.get(0).getFileName();
                    c cVar = new c(fVar, list, fVar2);
                    try {
                        j += cVar.contentLength();
                    } catch (IOException e3) {
                        FLogger.e("UploadImpl", "get fileBody contentLength error", e3);
                    }
                    type.addFormDataPart(name, fileName, cVar);
                }
            }
            build = type.build();
        }
        fVar.e(j);
        Response a2 = this.b.a(build, fVar, fVar2);
        l lVar = new l(Constants.ErrorCode.SUCCESS);
        lVar.setTask(fVar);
        lVar.setRawResponse(a2);
        this.c.a(lVar);
        a(a2.getCode());
        return a2;
    }

    private void a(int i) {
        String str;
        if (i == -1) {
            throw new FileManagerException(Constants.ErrorCode.SERVER_EXCEPTION, " upload failed,response null");
        }
        if (i != 416) {
            if (i / 100 == 2) {
                return;
            }
            throw new FileManagerException(i, "thread Upload failed:bad http response [responseCode=" + i + "]");
        }
        if (this.c != null) {
            str = "server file is wrong : 416 response [name= " + this.c.f() + ", uploadSize=" + this.c.a() + "]";
        } else {
            str = "server file is wrong : 416 response";
        }
        throw new FileManagerException(i, str);
    }

    public b(GlobalRequestConfig globalRequestConfig, HttpClient httpClient) {
        this.f5617a = globalRequestConfig;
        this.d = httpClient;
    }
}
