package com.huawei.openalliance.ad;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.IntVector;
import com.google.flatbuffers.StringVector;
import com.google.flatbuffers.Table;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import com.huawei.openalliance.ad.vy;
import com.huawei.openalliance.ad.wb;
import com.huawei.openalliance.ad.wi;
import com.huawei.openalliance.ad.wn;
import com.huawei.openalliance.ad.wq;
import com.huawei.openalliance.ad.wt;
import com.huawei.openalliance.ad.wv;
import com.huawei.up.model.UserInfomation;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public final class we extends Table {
    public String z() {
        int __offset = __offset(76);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public String y() {
        int __offset = __offset(70);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int x() {
        int __offset = __offset(68);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public IntVector w() {
        return b(new IntVector());
    }

    public String v() {
        int __offset = __offset(62);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public String u() {
        int __offset = __offset(60);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public IntVector t() {
        return a(new IntVector());
    }

    public int s() {
        int __offset = __offset(56);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public String r() {
        int __offset = __offset(54);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public String q() {
        int __offset = __offset(52);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public wd p() {
        return a(new wd());
    }

    public String o() {
        int __offset = __offset(48);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public wq.a n() {
        return a(new wq.a());
    }

    public wt.a m() {
        return a(new wt.a());
    }

    public StringVector l() {
        return b(new StringVector());
    }

    public StringVector k() {
        return a(new StringVector());
    }

    public long j() {
        int __offset = __offset(34);
        if (__offset != 0) {
            return this.bb.getLong(__offset + this.bb_pos);
        }
        return 0L;
    }

    public String i() {
        int __offset = __offset(32);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int h() {
        int __offset = __offset(30);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public String g() {
        int __offset = __offset(28);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ws f() {
        return a(new ws());
    }

    public ww e() {
        return a(new ww());
    }

    public int d() {
        int __offset = __offset(12);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int c() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public we b(int i, ByteBuffer byteBuffer) {
        a(i, byteBuffer);
        return this;
    }

    public StringVector b(StringVector stringVector) {
        int __offset = __offset(42);
        if (__offset != 0) {
            return stringVector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public IntVector b(IntVector intVector) {
        int __offset = __offset(66);
        if (__offset != 0) {
            return intVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public long b() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getLong(__offset + this.bb_pos);
        }
        return 0L;
    }

    public int ae() {
        int __offset = __offset(168);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public String ad() {
        int __offset = __offset(MachineControlPointResponse.OP_CODE_EXTENSION_SET_SUPPRESS_AUTO_PAUSE);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public String ac() {
        int __offset = __offset(MachineControlPointResponse.OP_CODE_EXTENSION_MULTI_DATA);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public String ab() {
        int __offset = __offset(MachineControlPointResponse.OP_CODE_EXTENSION_SET_DYNAMIC_ENERGY);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public String aa() {
        int __offset = __offset(160);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
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

    public xd a(xd xdVar) {
        int __offset = __offset(128);
        if (__offset != 0) {
            return xdVar.b(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public xa a(xa xaVar) {
        int __offset = __offset(82);
        if (__offset != 0) {
            return xaVar.b(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public ww a(ww wwVar) {
        int __offset = __offset(16);
        if (__offset != 0) {
            return wwVar.b(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public wv.a a(wv.a aVar) {
        int __offset = __offset(126);
        if (__offset != 0) {
            return aVar.a(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public wt.a a(wt.a aVar) {
        int __offset = __offset(44);
        if (__offset != 0) {
            return aVar.a(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public ws a(ws wsVar) {
        int __offset = __offset(24);
        if (__offset != 0) {
            return wsVar.b(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public wq.a a(wq.a aVar) {
        int __offset = __offset(46);
        if (__offset != 0) {
            return aVar.a(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public wp a(wp wpVar) {
        int __offset = __offset(118);
        if (__offset != 0) {
            return wpVar.b(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public wn.a a(wn.a aVar) {
        int __offset = __offset(102);
        if (__offset != 0) {
            return aVar.a(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public wi.a a(wi.a aVar) {
        int __offset = __offset(114);
        if (__offset != 0) {
            return aVar.a(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public wh a(wh whVar) {
        int __offset = __offset(152);
        if (__offset != 0) {
            return whVar.b(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public wg a(wg wgVar) {
        int __offset = __offset(140);
        if (__offset != 0) {
            return wgVar.b(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public wd a(wd wdVar) {
        int __offset = __offset(50);
        if (__offset != 0) {
            return wdVar.b(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public wb.a a(wb.a aVar) {
        int __offset = __offset(OldToNewMotionPath.SPORT_TYPE_TENNIS);
        if (__offset != 0) {
            return aVar.a(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public vy.a a(vy.a aVar) {
        int __offset = __offset(154);
        if (__offset != 0) {
            return aVar.a(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public StringVector a(StringVector stringVector) {
        int __offset = __offset(40);
        if (__offset != 0) {
            return stringVector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public IntVector a(IntVector intVector) {
        int __offset = __offset(58);
        if (__offset != 0) {
            return intVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public String Z() {
        int __offset = __offset(156);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public vy.a Y() {
        return a(new vy.a());
    }

    public wh X() {
        return a(new wh());
    }

    public String W() {
        int __offset = __offset(148);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public wg V() {
        return a(new wg());
    }

    public String U() {
        int __offset = __offset(136);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public String T() {
        int __offset = __offset(134);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int S() {
        int __offset = __offset(UserInfomation.WEIGHT_DEFAULT_ENGLISH);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public wb.a R() {
        return a(new wb.a());
    }

    public xd Q() {
        return a(new xd());
    }

    public wv.a P() {
        return a(new wv.a());
    }

    public wp O() {
        return a(new wp());
    }

    public wi.a N() {
        return a(new wi.a());
    }

    public String M() {
        int __offset = __offset(112);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public String L() {
        int __offset = __offset(108);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int K() {
        int __offset = __offset(106);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int J() {
        int __offset = __offset(104);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public wn.a I() {
        return a(new wn.a());
    }

    public int H() {
        int __offset = __offset(98);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public String G() {
        int __offset = __offset(94);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public float F() {
        int __offset = __offset(92);
        if (__offset != 0) {
            return this.bb.getFloat(__offset + this.bb_pos);
        }
        return 0.0f;
    }

    public int E() {
        int __offset = __offset(90);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public String D() {
        int __offset = __offset(84);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public static final class a extends BaseVector {
        public we a(we weVar, int i) {
            return weVar.b(we.__indirect(__element(i), this.bb), this.bb);
        }

        public we a(int i) {
            return a(new we(), i);
        }

        public a a(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public xa C() {
        return a(new xa());
    }

    public String B() {
        int __offset = __offset(80);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public String A() {
        int __offset = __offset(78);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }
}
