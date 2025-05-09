package com.huawei.hms.network.embedded;

import java.io.IOException;

/* loaded from: classes9.dex */
public class m8 extends gb {
    public boolean b;

    public void a(IOException iOException) {
    }

    @Override // com.huawei.hms.network.embedded.gb, com.huawei.hms.network.embedded.yb
    public void write(bb bbVar, long j) throws IOException {
        if (this.b) {
            bbVar.skip(j);
            return;
        }
        try {
            super.write(bbVar, j);
        } catch (IOException e) {
            this.b = true;
            a(e);
        }
    }

    @Override // com.huawei.hms.network.embedded.gb, com.huawei.hms.network.embedded.yb, java.io.Flushable
    public void flush() throws IOException {
        if (this.b) {
            return;
        }
        try {
            super.flush();
        } catch (IOException e) {
            this.b = true;
            a(e);
        }
    }

    @Override // com.huawei.hms.network.embedded.gb, com.huawei.hms.network.embedded.yb, java.lang.AutoCloseable, java.nio.channels.Channel
    public void close() throws IOException {
        if (this.b) {
            return;
        }
        try {
            super.close();
        } catch (IOException e) {
            this.b = true;
            a(e);
        }
    }

    public m8(yb ybVar) {
        super(ybVar);
    }
}
