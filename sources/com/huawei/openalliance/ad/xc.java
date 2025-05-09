package com.huawei.openalliance.ad;

import com.google.flatbuffers.Table;
import com.huawei.openalliance.ad.wu;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public final class xc extends Table {
    public String d() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public wu.a c() {
        return a(new wu.a());
    }

    public String b() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public xc b(int i, ByteBuffer byteBuffer) {
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

    public wu.a a(wu.a aVar) {
        int __offset = __offset(8);
        if (__offset != 0) {
            return aVar.a(__vector(__offset), 4, this.bb);
        }
        return null;
    }
}
