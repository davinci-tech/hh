package com.huawei.openalliance.ad;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public final class wr extends Table {
    public int i() {
        int __offset = __offset(20);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int h() {
        int __offset = __offset(18);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int g() {
        int __offset = __offset(16);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public String f() {
        int __offset = __offset(14);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public String e() {
        int __offset = __offset(12);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public long d() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return this.bb.getLong(__offset + this.bb_pos);
        }
        return 0L;
    }

    public int c() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public wr b(int i, ByteBuffer byteBuffer) {
        a(i, byteBuffer);
        return this;
    }

    public static final class a extends BaseVector {
        public wr a(wr wrVar, int i) {
            return wrVar.b(wr.__indirect(__element(i), this.bb), this.bb);
        }

        public wr a(int i) {
            return a(new wr(), i);
        }

        public a a(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public int b() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
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
