package com.huawei.openalliance.ad;

import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public final class wj extends Table {
    public wj b(int i, ByteBuffer byteBuffer) {
        a(i, byteBuffer);
        return this;
    }

    public long b() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getLong(__offset + this.bb_pos);
        }
        return 0L;
    }

    public void a(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public int a() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }
}
