package com.huawei.hms.network.file.b;

import android.text.TextUtils;
import com.huawei.hms.network.base.common.trans.FileBinary;
import com.huawei.hms.network.file.api.RequestManager;
import com.huawei.hms.network.file.core.Constants;
import com.huawei.hms.network.file.core.FileManagerException;
import com.huawei.hms.network.file.core.util.Utils;
import com.huawei.hms.network.file.upload.api.FileEntity;
import com.huawei.hms.network.httpclient.RequestBody;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class c extends RequestBody {

    /* renamed from: a, reason: collision with root package name */
    private final f f5618a;
    private com.huawei.hms.network.file.core.task.f b;
    List<FileEntity> c;
    long d;

    /* JADX WARN: Code restructure failed: missing block: B:60:0x01ef, code lost:
    
        r25.b.a((com.huawei.hms.network.file.core.task.f) r25.f5618a);
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0269, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0266, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0263, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0260, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:114:? -> B:110:0x01f7). Please report as a decompilation issue!!! */
    @Override // com.huawei.hms.network.httpclient.RequestBody
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void writeTo(java.io.OutputStream r26) {
        /*
            Method dump skipped, instructions count: 762
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.file.b.c.writeTo(java.io.OutputStream):void");
    }

    @Override // com.huawei.hms.network.httpclient.RequestBody
    public String contentType() {
        Map<String, String> headers = this.f5618a.p().getHeaders();
        return (headers.isEmpty() || TextUtils.isEmpty(headers.get("Content-Type"))) ? FileBinary.HEAD_VALUE_CONTENT_TYPE_OCTET_STREAM : headers.get("Content-Type");
    }

    @Override // com.huawei.hms.network.httpclient.RequestBody
    public long contentLength() {
        return this.d;
    }

    private void a(List<FileEntity> list) {
        this.c = new ArrayList();
        this.d = 0L;
        if (Utils.isEmpty(list)) {
            return;
        }
        this.c.addAll(list);
        Iterator<FileEntity> it = this.c.iterator();
        while (it.hasNext()) {
            this.d += it.next().getUploadSize();
        }
    }

    private void a(FileEntity fileEntity, boolean z) {
        if (z) {
            if (!new File(fileEntity.getFile().getPath()).exists()) {
                throw new FileManagerException(Constants.ErrorCode.FILE_UPLOAD_NO_EXIST.getErrorCode(), "upload file is no exists");
            }
            return;
        }
        try {
            try {
                Utils.close(RequestManager.getAppContext().getContentResolver().openInputStream(fileEntity.getUri()));
            } catch (FileNotFoundException unused) {
                throw new FileManagerException(Constants.ErrorCode.FILE_UPLOAD_NO_EXIST.getErrorCode(), "upload file is no exists for uri");
            }
        } catch (Throwable th) {
            Utils.close(null);
            throw th;
        }
    }

    public c(f fVar, List<FileEntity> list, com.huawei.hms.network.file.core.task.f fVar2) {
        this.f5618a = fVar;
        this.b = fVar2;
        a(list);
    }
}
