package com.huawei.agconnect.apms;

import com.google.gson.JsonArray;
import java.util.List;

/* loaded from: classes2.dex */
public class vwx {
    public final Object abc = new Object();
    public long bcd;
    public String cde;
    public String def;
    public String efg;
    public String fgh;
    public long ghi;
    public String hij;
    public String ijk;
    public String jih;
    public int jkl;
    public String kji;
    public long klm;
    public String lkj;
    public int lmn;
    public String mlk;
    public long mno;
    public String nml;
    public long nop;
    public String onm;
    public long opq;
    public String pon;
    public long pqr;
    public String qpo;
    public long qrs;
    public String rqp;
    public long rst;
    public String srq;
    public long stu;
    public String tsr;
    public long tuv;
    public JsonArray uts;
    public long uvw;
    public int vut;
    public long vwx;
    public String wvu;
    public int wxy;
    public String xwv;
    public int xyz;
    public List<mlk> yxw;
    public List<nml> yza;
    public int zab;
    public int zyx;

    public vwx(wxy wxyVar) {
        this.nml = "";
        this.mlk = "";
        this.lkj = "0";
        this.kji = "";
        this.jih = "";
        String nml = wxyVar.nml();
        int indexOf = nml.indexOf(63);
        if (indexOf < 0 && (indexOf = nml.indexOf(59)) < 0) {
            indexOf = nml.length();
        }
        wxyVar.abc();
        this.cde = nml.substring(0, indexOf);
        this.def = wxyVar.fgh();
        this.efg = wxyVar.qrs();
        this.fgh = wxyVar.jkl();
        this.ghi = wxyVar.ijk();
        this.bcd = wxyVar.efg() == -1 ? wxyVar.pon() : wxyVar.efg();
        this.klm = bcd(wxyVar);
        if (wxyVar.b()) {
            this.klm += abc(wxyVar);
        }
        this.jkl = wxyVar.onm();
        this.xwv = wxyVar.opq();
        this.qrs = wxyVar.def();
        this.vwx = wxyVar.cde();
        this.hij = wxyVar.nop();
        this.lmn = wxyVar.pqr();
        this.ijk = wxyVar.xwv();
        this.yza = wxyVar.lmn();
        this.wxy = wxyVar.klm();
        this.xyz = wxyVar.mno();
        this.yxw = wxyVar.rqp();
        this.zab = wxyVar.ghi();
        this.zyx = wxyVar.hij();
        this.mno = wxyVar.xyz();
        this.nop = wxyVar.wxy();
        this.opq = wxyVar.vwx();
        this.pqr = wxyVar.uvw();
        this.rst = wxyVar.yxw();
        this.stu = wxyVar.zyx();
        this.tuv = wxyVar.zab();
        this.uvw = wxyVar.yza();
        this.wvu = wxyVar.qpo();
        this.vut = wxyVar.tuv();
        this.uts = wxyVar.srq();
        this.qpo = wxyVar.wvu();
        this.srq = wxyVar.vut();
        this.rqp = wxyVar.tsr();
        this.tsr = wxyVar.uts();
        this.pon = wxyVar.kji();
        this.onm = wxyVar.lkj();
        this.nml = wxyVar.jih();
        this.mlk = wxyVar.ihg();
        this.lkj = wxyVar.mlk();
        this.kji = wxyVar.hgf();
        this.jih = "";
    }

    public long abc() {
        return this.vwx;
    }

    public long bcd() {
        return this.qrs;
    }

    public String cde() {
        return this.def;
    }

    public int def() {
        return this.zab;
    }

    public int efg() {
        return this.zyx;
    }

    public long fgh() {
        return this.ghi;
    }

    public String ghi() {
        return this.fgh;
    }

    public int hij() {
        return this.wxy;
    }

    public List<nml> ijk() {
        return this.yza;
    }

    public int jkl() {
        return this.xyz;
    }

    public String kji() {
        return this.cde;
    }

    public String klm() {
        return this.hij;
    }

    public long lkj() {
        return this.klm;
    }

    public String lmn() {
        String str;
        synchronized (this.abc) {
            str = this.xwv;
        }
        return str;
    }

    public int mlk() {
        return this.jkl;
    }

    public int mno() {
        return this.lmn;
    }

    public long nml() {
        return this.bcd;
    }

    public String nop() {
        return this.efg;
    }

    public String onm() {
        return this.wvu;
    }

    public int opq() {
        return this.vut;
    }

    public List<mlk> pon() {
        return this.yxw;
    }

    public String pqr() {
        return this.lkj;
    }

    public JsonArray qpo() {
        return this.uts;
    }

    public String qrs() {
        return this.onm;
    }

    public String rqp() {
        return this.rqp;
    }

    public String rst() {
        return this.pon;
    }

    public String srq() {
        return this.tsr;
    }

    public String stu() {
        return this.nml;
    }

    public String tsr() {
        return this.srq;
    }

    public String tuv() {
        return this.mlk;
    }

    public String uts() {
        return this.qpo;
    }

    public String uvw() {
        return this.kji;
    }

    public String vut() {
        return this.ijk;
    }

    public String vwx() {
        return this.jih;
    }

    public long wvu() {
        return this.rst;
    }

    public long wxy() {
        return this.pqr;
    }

    public long xwv() {
        return this.stu;
    }

    public long xyz() {
        return this.opq;
    }

    public long yxw() {
        return this.tuv;
    }

    public long yza() {
        return this.nop;
    }

    public long zab() {
        return this.mno;
    }

    public long zyx() {
        return this.uvw;
    }

    public final long abc(wxy wxyVar) {
        nml rst = wxyVar.rst();
        if (rst != null && rst.efg()) {
            return rst.cde - rst.bcd;
        }
        return 0L;
    }

    public final long bcd(wxy wxyVar) {
        if (wxyVar.lmn > this.bcd) {
            return (int) (r0 - r2);
        }
        return -1L;
    }
}
