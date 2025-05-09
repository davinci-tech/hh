package com.huawei.openalliance.ad;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public final class xd extends Table {
    public String e() {
        int __offset = __offset(12);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public String d() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public xc c() {
        return a(new xc());
    }

    public String b() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public xd b(int i, ByteBuffer byteBuffer) {
        a(i, byteBuffer);
        return this;
    }

    public static final class a extends BaseVector {
        public xd a(xd xdVar, int i) {
            return xdVar.b(xd.__indirect(__element(i), this.bb), this.bb);
        }

        public xd a(int i) {
            return a(new xd(), i);
        }

        public a a(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
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

    public xc a(xc xcVar) {
        int __offset = __offset(8);
        if (__offset != 0) {
            return xcVar.b(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }
}
