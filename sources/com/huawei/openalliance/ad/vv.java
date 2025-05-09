package com.huawei.openalliance.ad;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Table;
import com.huawei.openalliance.ad.vy;
import com.huawei.openalliance.ad.we;
import com.huawei.openalliance.ad.wn;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public final class vv extends Table {
    public wj h() {
        return a(new wj());
    }

    public vy.a g() {
        return a(new vy.a());
    }

    public int f() {
        int __offset = __offset(16);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public wn.a e() {
        return a(new wn.a());
    }

    public we.a d() {
        return a(new we.a());
    }

    public String c() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public vv b(int i, ByteBuffer byteBuffer) {
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

    public wn.a a(wn.a aVar) {
        int __offset = __offset(14);
        if (__offset != 0) {
            return aVar.a(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public static final class a extends BaseVector {
        public vv a(vv vvVar, int i) {
            return vvVar.b(vv.__indirect(__element(i), this.bb), this.bb);
        }

        public vv a(int i) {
            return a(new vv(), i);
        }

        public a a(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public wj a(wj wjVar) {
        int __offset = __offset(20);
        if (__offset != 0) {
            return wjVar.b(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public we.a a(we.a aVar) {
        int __offset = __offset(10);
        if (__offset != 0) {
            return aVar.a(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public vy.a a(vy.a aVar) {
        int __offset = __offset(18);
        if (__offset != 0) {
            return aVar.a(__vector(__offset), 4, this.bb);
        }
        return null;
    }
}
