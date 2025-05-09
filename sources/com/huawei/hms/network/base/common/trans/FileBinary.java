package com.huawei.hms.network.base.common.trans;

import com.huawei.hms.framework.common.IoUtils;
import com.huawei.hms.framework.common.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes4.dex */
public class FileBinary implements Binary {
    public static final String HEAD_VALUE_CONTENT_TYPE_OCTET_STREAM = "application/octet-stream";
    private static final String b = "FileBinary";

    /* renamed from: a, reason: collision with root package name */
    private File f5134a;

    @Override // com.huawei.hms.network.base.common.trans.Binary
    public void onWriteBinary(OutputStream outputStream) {
        InputStream inputStream = null;
        try {
            try {
                inputStream = getInputStream();
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        return;
                    } else {
                        outputStream.write(bArr, 0, read);
                    }
                }
            } catch (IOException e) {
                Logger.w(b, "the file is reading but has error!");
                throw e;
            }
        } finally {
            IoUtils.closeSecure(inputStream);
        }
    }

    @Override // com.huawei.hms.network.base.common.trans.Binary
    public long getLength() {
        return this.f5134a.length();
    }

    public InputStream getInputStream() {
        return new FileInputStream(this.f5134a);
    }

    public FileBinary(File file, String str) {
        if (file != null) {
            this.f5134a = file;
        } else {
            throw new IllegalArgumentException("File is null: " + str);
        }
    }

    public FileBinary(File file) {
        this(file, file == null ? "" : file.getName());
    }
}
