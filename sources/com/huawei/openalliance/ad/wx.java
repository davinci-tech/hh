package com.huawei.openalliance.ad;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public final class wx extends Table {
    public String b() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public static final class a extends BaseVector {
        public wx a(wx wxVar, int i) {
            return wxVar.b(wx.__indirect(__element(i), this.bb), this.bb);
        }

        public wx a(int i) {
            return a(new wx(), i);
        }

        public a a(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public wx b(int i, ByteBuffer byteBuffer) {
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
