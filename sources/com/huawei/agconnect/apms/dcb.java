package com.huawei.agconnect.apms;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.ResponseBody;
import java.io.IOException;
import okio.BufferedSource;
import okio.Okio;

/* loaded from: classes8.dex */
public class dcb extends ResponseBody {
    public wxy abc;
    public ResponseBody bcd;
    public BufferedSource cde;

    public dcb(ResponseBody responseBody, wxy wxyVar) {
        this.abc = wxyVar;
        this.bcd = responseBody;
    }

    public void close() throws IOException {
        this.bcd.close();
    }

    public long contentLength() throws IOException {
        return this.bcd.contentLength();
    }

    public MediaType contentType() {
        return this.bcd.contentType();
    }

    public BufferedSource source() throws IOException {
        if (this.cde == null) {
            this.cde = Okio.buffer(new edc(this, this.bcd.source()));
        }
        return this.cde;
    }
}
