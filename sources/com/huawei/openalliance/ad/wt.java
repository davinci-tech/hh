package com.huawei.openalliance.ad;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.StringVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public final class wt extends Table {
    public int c() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public wt b(int i, ByteBuffer byteBuffer) {
        a(i, byteBuffer);
        return this;
    }

    public StringVector b() {
        return a(new StringVector());
    }

    public static final class a extends BaseVector {
        public wt a(wt wtVar, int i) {
            return wtVar.b(wt.__indirect(__element(i), this.bb), this.bb);
        }

        public wt a(int i) {
            return a(new wt(), i);
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

    public StringVector a(StringVector stringVector) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return stringVector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }
}
