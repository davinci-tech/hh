package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.IoUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.embedded.h1;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public class a4 extends u7 {
    public static final String b = "OkRequestBody";

    /* renamed from: a, reason: collision with root package name */
    public final h1.e f5155a;

    @Override // com.huawei.hms.network.embedded.u7
    public void writeTo(cb cbVar) throws IOException {
        b4 b4Var = new b4(cbVar);
        try {
            this.f5155a.writeTo(b4Var);
            if (isDuplex()) {
                return;
            }
            b4Var.close();
        } catch (FileNotFoundException e) {
            Logger.w(b, "the requestBody with writeTo has error! and the FileNotFound must be changed to InterruptedIOException");
            IoUtils.closeSecure((OutputStream) b4Var);
            throw new InterruptedIOException(e.getMessage());
        } catch (IOException e2) {
            Logger.w(b, "the requestBody with writeTo has other error");
            IoUtils.closeSecure((OutputStream) b4Var);
            throw e2;
        }
    }

    @Override // com.huawei.hms.network.embedded.u7
    public boolean isDuplex() {
        return this.f5155a.isDuplex();
    }

    @Override // com.huawei.hms.network.embedded.u7
    @Nullable
    public o7 contentType() {
        String contentType = this.f5155a.contentType();
        if (contentType == null) {
            return null;
        }
        return o7.b(contentType);
    }

    @Override // com.huawei.hms.network.embedded.u7
    public long contentLength() throws IOException {
        return this.f5155a.contentLength();
    }

    public a4(h1.e eVar) {
        this.f5155a = eVar;
    }
}
