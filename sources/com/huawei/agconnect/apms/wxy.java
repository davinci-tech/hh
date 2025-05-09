package com.huawei.agconnect.apms;

import android.text.TextUtils;
import com.google.gson.JsonArray;
import com.huawei.agconnect.apms.util.Session;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes2.dex */
public final class wxy implements n0 {
    public String bcd;
    public String cde;
    public String def;
    public String efg;
    public long fgh;
    public Object lkj;
    public vwx nml;
    public int ghi = 0;
    public final Object onm = new Object();
    public boolean mlk = false;
    public String jih = "";
    public String ihg = "";
    public String hgf = "";
    public String gfe = "";
    public String fed = "";
    public String edc = "";
    public String dcb = "";
    public String cba = "";

    /* renamed from: a, reason: collision with root package name */
    public String f1698a = "0";
    public String b = "";
    public long klm = System.currentTimeMillis();
    public int jkl = 0;
    public long qrs = 0;
    public long vwx = 0;
    public long lmn = -1;
    public int abc = 0;
    public int wxy = 0;
    public String hij = "";
    public String ijk = "";
    public String qpo = "";
    public String rqp = "";
    public int zab = -1;
    public int vut = -1;
    public List<nml> wvu = new CopyOnWriteArrayList();
    public List<mlk> srq = new CopyOnWriteArrayList();
    public int xwv = 0;
    public int yxw = 0;
    public int tsr = 0;
    public int uts = 0;
    public long mno = -1;
    public long nop = -1;
    public long pqr = -1;
    public long opq = -1;
    public long stu = -1;
    public long rst = -1;
    public long tuv = -1;
    public long uvw = -1;
    public long yza = -1;
    public long xyz = -1;
    public long zyx = -1;
    public JsonArray kji = new JsonArray();
    public boolean pon = false;

    public wxy() {
        Session klm = o0.jkl().klm();
        o0.jkl().abc(this);
        this.kji.add(klm.asJsonArray());
        this.cde = "";
    }

    public final boolean a() {
        if (this.rqp == null) {
            return false;
        }
        return !"".equals(r0);
    }

    public void abc(boolean z) {
        synchronized (this.onm) {
            this.pon = z;
        }
    }

    public boolean b() {
        return this.mlk;
    }

    public void bcd(String str) {
        if (c()) {
            return;
        }
        this.def = str;
    }

    public boolean c() {
        return this.abc >= 1;
    }

    public final boolean cba() {
        return ((long) this.jkl) >= 400;
    }

    public void cde(String str) {
        if (str == null || edc()) {
            return;
        }
        this.ijk = str;
    }

    public void d() {
        if (edc()) {
            return;
        }
        this.abc = 2;
        this.lmn = System.currentTimeMillis();
    }

    public boolean dcb() {
        return cba() || a();
    }

    public void def(String str) {
        String sb;
        if (str != null) {
            try {
                URL url = new URL(str.replaceAll("[\\\\#]", "/"));
                StringBuilder sb2 = new StringBuilder();
                sb2.append(url.getProtocol());
                sb2.append("://");
                sb2.append(url.getHost());
                if (url.getPort() != -1) {
                    sb2.append(":");
                    sb2.append(url.getPort());
                }
                sb2.append(url.getPath());
                sb = sb2.toString();
            } catch (Throwable unused) {
            }
            if (sb == null && !c()) {
                this.bcd = sb;
            }
            return;
        }
        sb = null;
        if (sb == null) {
            return;
        }
        this.bcd = sb;
    }

    public vwx e() {
        o0 o0Var = o0.abc;
        synchronized (o0Var.cde) {
            o0Var.cde.remove(this);
        }
        if (this.bcd == null) {
            return null;
        }
        if (this.nml == null) {
            this.nml = new vwx(this);
        }
        return this.nml;
    }

    public boolean edc() {
        return this.abc >= 2;
    }

    public long efg() {
        return this.xyz;
    }

    public void fed() {
        if (edc()) {
            return;
        }
        this.xwv++;
    }

    public String fgh() {
        return this.cde;
    }

    public boolean gfe() {
        boolean z;
        synchronized (this.onm) {
            z = this.pon;
        }
        return z;
    }

    public int ghi() {
        return this.uts;
    }

    public String hgf() {
        return this.b;
    }

    public int hij() {
        return this.tsr;
    }

    public String ihg() {
        return this.cba;
    }

    public long ijk() {
        return this.fgh;
    }

    public String jih() {
        return this.dcb;
    }

    public String jkl() {
        return this.efg;
    }

    public String kji() {
        return this.fed;
    }

    public int klm() {
        return this.yxw;
    }

    public String lkj() {
        return this.edc;
    }

    public List<nml> lmn() {
        return this.wvu;
    }

    public String mlk() {
        return this.f1698a;
    }

    public int mno() {
        return this.xwv;
    }

    public String nml() {
        return this.bcd;
    }

    public String nop() {
        return this.hij;
    }

    public int onm() {
        return this.jkl;
    }

    public String opq() {
        return this.rqp;
    }

    public long pon() {
        return this.klm;
    }

    public int pqr() {
        return this.wxy;
    }

    public String qpo() {
        return this.qpo;
    }

    public String qrs() {
        return this.def;
    }

    public List<mlk> rqp() {
        return this.srq;
    }

    public nml rst() {
        int i = this.zab;
        if (i < 0) {
            i = this.wvu.size() - 1;
            this.zab = i;
        }
        this.zab = i;
        if (i < 0) {
            return null;
        }
        return this.wvu.get(i);
    }

    public JsonArray srq() {
        return this.kji;
    }

    public mlk stu() {
        int i = this.vut;
        if (i < 0) {
            i = this.srq.size() - 1;
            this.vut = i;
        }
        this.vut = i;
        if (i < 0) {
            return null;
        }
        return this.srq.get(i);
    }

    public String tsr() {
        return this.hgf;
    }

    public int tuv() {
        return this.ghi;
    }

    public String uts() {
        return this.jih;
    }

    public long uvw() {
        return this.opq;
    }

    public String vut() {
        return this.ihg;
    }

    public long vwx() {
        return this.pqr;
    }

    public String wvu() {
        return this.gfe;
    }

    public long wxy() {
        return this.nop;
    }

    public String xwv() {
        return this.ijk;
    }

    public long xyz() {
        return this.mno;
    }

    public long yxw() {
        return this.stu;
    }

    public long yza() {
        return this.uvw;
    }

    public long zab() {
        return this.tuv;
    }

    public long zyx() {
        return this.rst;
    }

    public void efg(String str) {
        this.edc = str;
    }

    public void fgh(String str) {
        this.fed = str;
    }

    public void cde(long j) {
        if (edc()) {
            return;
        }
        this.xyz = j;
    }

    public void abc(nml nmlVar) {
        if (edc()) {
            return;
        }
        this.wvu.add(nmlVar);
        this.zab++;
    }

    public void bcd(int i) {
        if (edc()) {
            return;
        }
        this.jkl = i;
    }

    public long cde() {
        return this.vwx;
    }

    public void abc(int i) {
        long j = i;
        if (j > this.yza) {
            this.yza = j;
        }
    }

    public void bcd(long j) {
        if (edc()) {
            return;
        }
        this.qrs = j;
        this.abc = 1;
    }

    public void abc(String str) {
        if (!edc()) {
            this.rqp = str;
            return;
        }
        vwx vwxVar = this.nml;
        if (vwxVar != null) {
            synchronized (vwxVar.abc) {
                vwxVar.xwv = str;
            }
        }
    }

    public final void bcd() {
        if (TextUtils.isEmpty(this.hij)) {
            try {
                this.hij = new URL(this.bcd).getHost();
            } catch (Throwable unused) {
                this.hij = "";
            }
        }
    }

    public void abc(long j) {
        if (j >= 0) {
            this.vwx = j;
        }
    }

    @Override // com.huawei.agconnect.apms.n0
    public void abc(Session session) {
        if (session == null) {
            return;
        }
        this.kji.add(session.asJsonArray());
    }

    public void abc() {
        nml rst = rst();
        if (rst != null && !rst.efg()) {
            this.yxw++;
        }
        if (this.tsr - 1 != this.uts) {
            int i = 0;
            for (mlk mlkVar : this.srq) {
                if (mlkVar != null && !mlkVar.jkl) {
                    i++;
                }
            }
            this.uts = i;
        }
        bcd();
        if (TextUtils.isEmpty(this.hij)) {
            bcd();
        }
        if (TextUtils.isEmpty(this.ijk)) {
            String str = xyz.bcd.get(this.hij);
            if (str == null) {
                str = "";
            }
            this.ijk = str;
        }
    }

    public void def(long j) {
        if (edc() || j < 0) {
            return;
        }
        this.fgh = j;
    }

    public long def() {
        return this.qrs;
    }
}
