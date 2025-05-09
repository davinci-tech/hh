package com.huawei.openalliance.ad;

import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public final class wo extends Table {
    public String c() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public String b() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public wo b(int i, ByteBuffer byteBuffer) {
        a(i, byteBuffer);
        return this;
    }

    public void a(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public String a() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }
}
