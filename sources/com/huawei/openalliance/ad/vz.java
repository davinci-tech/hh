package com.huawei.openalliance.ad;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.StringVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public final class vz extends Table {
    public vz b(int i, ByteBuffer byteBuffer) {
        a(i, byteBuffer);
        return this;
    }

    public int b() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public static final class a extends BaseVector {
        public vz a(vz vzVar, int i) {
            return vzVar.b(vz.__indirect(__element(i), this.bb), this.bb);
        }

        public vz a(int i) {
            return a(new vz(), i);
        }

        public a a(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public void a(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public StringVector a(StringVector stringVector) {
        int __offset = __offset(4);
        if (__offset != 0) {
            return stringVector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public StringVector a() {
        return a(new StringVector());
    }
}
