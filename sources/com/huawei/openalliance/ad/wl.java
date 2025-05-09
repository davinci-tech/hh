package com.huawei.openalliance.ad;

import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public final class wl extends Table {
    public wk d() {
        return a(new wk());
    }

    public int c() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public wl b(int i, ByteBuffer byteBuffer) {
        a(i, byteBuffer);
        return this;
    }

    public int b() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public void a(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public String a() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public wk a(wk wkVar) {
        int __offset = __offset(12);
        if (__offset != 0) {
            return wkVar.b(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }
}
