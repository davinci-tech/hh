package com.huawei.openalliance.ad;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Table;
import com.huawei.openalliance.ad.wb;
import com.huawei.openalliance.ad.wm;
import com.huawei.openalliance.ad.wr;
import com.huawei.openalliance.ad.xd;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public final class wy extends Table {
    public int m() {
        int __offset = __offset(34);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public xd.a l() {
        return a(new xd.a());
    }

    public int k() {
        int __offset = __offset(30);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public wb.a j() {
        return a(new wb.a());
    }

    public xd i() {
        return a(new xd());
    }

    public int h() {
        int __offset = __offset(22);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public xh g() {
        return a(new xh());
    }

    public wr.a f() {
        return a(new wr.a());
    }

    public String e() {
        int __offset = __offset(16);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public wm.a d() {
        return a(new wm.a());
    }

    public int c() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public String b() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public wy b(int i, ByteBuffer byteBuffer) {
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

    public xh a(xh xhVar) {
        int __offset = __offset(20);
        if (__offset != 0) {
            return xhVar.b(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public xd a(xd xdVar) {
        int __offset = __offset(26);
        if (__offset != 0) {
            return xdVar.b(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public xd.a a(xd.a aVar) {
        int __offset = __offset(32);
        if (__offset != 0) {
            return aVar.a(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public static final class a extends BaseVector {
        public wy a(wy wyVar, int i) {
            return wyVar.b(wy.__indirect(__element(i), this.bb), this.bb);
        }

        public wy a(int i) {
            return a(new wy(), i);
        }

        public a a(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public wr.a a(wr.a aVar) {
        int __offset = __offset(18);
        if (__offset != 0) {
            return aVar.a(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public wm.a a(wm.a aVar) {
        int __offset = __offset(14);
        if (__offset != 0) {
            return aVar.a(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public wb.a a(wb.a aVar) {
        int __offset = __offset(28);
        if (__offset != 0) {
            return aVar.a(__vector(__offset), 4, this.bb);
        }
        return null;
    }
}
