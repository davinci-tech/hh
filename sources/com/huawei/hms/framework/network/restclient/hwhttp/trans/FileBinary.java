package com.huawei.hms.framework.network.restclient.hwhttp.trans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes4.dex */
public class FileBinary implements Binary {
    private static final String TAG = "FileBinary";
    private final com.huawei.hms.network.base.common.trans.FileBinary proxyFileBinary;

    public FileBinary(File file) {
        this.proxyFileBinary = new com.huawei.hms.network.base.common.trans.FileBinary(file);
    }

    public FileBinary(File file, String str) {
        this.proxyFileBinary = new com.huawei.hms.network.base.common.trans.FileBinary(file, str);
    }

    @Override // com.huawei.hms.framework.network.restclient.hwhttp.trans.Binary
    public long getLength() {
        return this.proxyFileBinary.getLength();
    }

    public InputStream getInputStream() throws IOException {
        return this.proxyFileBinary.getInputStream();
    }

    @Override // com.huawei.hms.framework.network.restclient.hwhttp.trans.Binary
    public void onWriteBinary(OutputStream outputStream) throws IOException {
        this.proxyFileBinary.onWriteBinary(outputStream);
    }
}
