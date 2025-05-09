package com.huawei.openalliance.ad;

import com.google.flatbuffers.StringVector;
import com.google.flatbuffers.Table;
import com.huawei.openalliance.ad.vv;
import com.huawei.openalliance.ad.vz;
import com.huawei.openalliance.ad.wy;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes5.dex */
public final class vw extends Table {
    public String m() {
        int __offset = __offset(32);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int l() {
        int __offset = __offset(30);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int k() {
        int __offset = __offset(28);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int j() {
        int __offset = __offset(26);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int i() {
        int __offset = __offset(24);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public vz.a h() {
        return a(new vz.a());
    }

    public String g() {
        int __offset = __offset(20);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public StringVector f() {
        return b(new StringVector());
    }

    public wy.a e() {
        return a(new wy.a());
    }

    public StringVector d() {
        return a(new StringVector());
    }

    public vv.a c() {
        return a(new vv.a());
    }

    public String b() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public vw b(int i, ByteBuffer byteBuffer) {
        a(i, byteBuffer);
        return this;
    }

    public StringVector b(StringVector stringVector) {
        int __offset = __offset(18);
        if (__offset != 0) {
            return stringVector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public void a(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public wy.a a(wy.a aVar) {
        int __offset = __offset(16);
        if (__offset != 0) {
            return aVar.a(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public vz.a a(vz.a aVar) {
        int __offset = __offset(22);
        if (__offset != 0) {
            return aVar.a(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public vv.a a(vv.a aVar) {
        int __offset = __offset(10);
        if (__offset != 0) {
            return aVar.a(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public StringVector a(StringVector stringVector) {
        int __offset = __offset(14);
        if (__offset != 0) {
            return stringVector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public int a() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public static vw a(ByteBuffer byteBuffer, vw vwVar) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return vwVar.b(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public static vw a(ByteBuffer byteBuffer) {
        return a(byteBuffer, new vw());
    }
}
