package com.huawei.openalliance.ad;

import com.google.flatbuffers.StringVector;
import com.google.flatbuffers.Table;
import com.huawei.openalliance.ad.vx;
import com.huawei.openalliance.ad.wm;
import com.huawei.openalliance.ad.xe;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public final class ws extends Table {
    public String z() {
        int __offset = __offset(62);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public String y() {
        int __offset = __offset(60);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public wr x() {
        return a(new wr());
    }

    public long w() {
        int __offset = __offset(52);
        if (__offset != 0) {
            return this.bb.getLong(__offset + this.bb_pos);
        }
        return 0L;
    }

    public String v() {
        int __offset = __offset(46);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public wa u() {
        return a(new wa());
    }

    public xb t() {
        return a(new xb());
    }

    public wc s() {
        return a(new wc());
    }

    public xh r() {
        return a(new xh());
    }

    public String q() {
        int __offset = __offset(36);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public String p() {
        int __offset = __offset(34);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public String o() {
        int __offset = __offset(32);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public String n() {
        int __offset = __offset(30);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public wm m() {
        return a(new wm());
    }

    public int l() {
        int __offset = __offset(26);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int k() {
        int __offset = __offset(24);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public String j() {
        int __offset = __offset(22);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public String i() {
        int __offset = __offset(20);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public wm.a h() {
        return b(new wm.a());
    }

    public wm.a g() {
        return a(new wm.a());
    }

    public StringVector f() {
        return a(new StringVector());
    }

    public String e() {
        int __offset = __offset(12);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int d() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public String c() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public xe.a b() {
        return a(new xe.a());
    }

    public ws b(int i, ByteBuffer byteBuffer) {
        a(i, byteBuffer);
        return this;
    }

    public wm.a b(wm.a aVar) {
        int __offset = __offset(18);
        if (__offset != 0) {
            return aVar.a(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public StringVector b(StringVector stringVector) {
        int __offset = __offset(70);
        if (__offset != 0) {
            return stringVector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
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
        int __offset = __offset(38);
        if (__offset != 0) {
            return xhVar.b(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public xe.a a(xe.a aVar) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return aVar.a(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public xb a(xb xbVar) {
        int __offset = __offset(42);
        if (__offset != 0) {
            return xbVar.b(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public wz a(wz wzVar) {
        int __offset = __offset(90);
        if (__offset != 0) {
            return wzVar.b(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public wr a(wr wrVar) {
        int __offset = __offset(54);
        if (__offset != 0) {
            return wrVar.b(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public wm a(wm wmVar) {
        int __offset = __offset(28);
        if (__offset != 0) {
            return wmVar.b(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public wm.a a(wm.a aVar) {
        int __offset = __offset(16);
        if (__offset != 0) {
            return aVar.a(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public wc a(wc wcVar) {
        int __offset = __offset(40);
        if (__offset != 0) {
            return wcVar.b(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public wa a(wa waVar) {
        int __offset = __offset(44);
        if (__offset != 0) {
            return waVar.b(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public vx.a a(vx.a aVar) {
        int __offset = __offset(86);
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

    public wz L() {
        return a(new wz());
    }

    public int K() {
        int __offset = __offset(88);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public vx.a J() {
        return a(new vx.a());
    }

    public String I() {
        int __offset = __offset(84);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public String H() {
        int __offset = __offset(82);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int G() {
        int __offset = __offset(80);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int F() {
        int __offset = __offset(78);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int E() {
        int __offset = __offset(76);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int D() {
        int __offset = __offset(72);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public StringVector C() {
        return b(new StringVector());
    }

    public String B() {
        int __offset = __offset(66);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public String A() {
        int __offset = __offset(64);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }
}
