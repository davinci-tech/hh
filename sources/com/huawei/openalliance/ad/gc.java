package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.beans.metadata.App;
import com.huawei.openalliance.ad.beans.metadata.FlowControl;
import com.huawei.openalliance.ad.beans.metadata.Location;
import com.huawei.openalliance.ad.beans.metadata.OaidRecord;
import com.huawei.openalliance.ad.beans.server.AppConfigRsp;
import com.huawei.openalliance.ad.beans.tags.TagCfgModel;
import com.huawei.openalliance.ad.constant.AdLoadMode;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public interface gc extends fr {
    boolean A(String str);

    int B(String str);

    int C(String str);

    void D(String str);

    String a();

    String a(Context context, String str);

    void a(Location location);

    void a(String str, long j);

    void a(String str, FlowControl flowControl);

    void a(String str, OaidRecord oaidRecord);

    void a(String str, AppConfigRsp appConfigRsp, boolean z);

    void a(String str, boolean z);

    void a(List<String> list, Map<String, Boolean> map);

    void a(Map<String, String> map);

    void a(Set<String> set);

    void a(boolean z);

    boolean a(Long l);

    long aA();

    long aB();

    boolean aD();

    int aF();

    long aG();

    long aH();

    int aI();

    int aJ();

    String aK();

    boolean aL();

    boolean aM();

    String aN();

    long aO();

    String aP();

    String aQ();

    boolean aR();

    boolean aS();

    int aT();

    long aU();

    int aV();

    int aW();

    int aX();

    int aY();

    int aZ();

    String ar();

    String as();

    String at();

    int au();

    int av();

    long aw();

    int ax();

    AdLoadMode ay();

    int az();

    void b(int i);

    void b(long j);

    void b(List<String> list);

    void b(Set<String> set);

    void b(boolean z);

    Long bA();

    Long bB();

    int bC();

    int bD();

    long bE();

    String bF();

    long bG();

    List<App> bH();

    String bI();

    boolean bJ();

    boolean bK();

    String bL();

    String bM();

    Location bN();

    int bO();

    int bP();

    int bQ();

    int bR();

    int bS();

    long bT();

    long bU();

    int bV();

    int bW();

    boolean bX();

    String bZ();

    boolean ba();

    boolean bb();

    boolean bc();

    String bd();

    boolean be();

    boolean bf();

    int bg();

    int bh();

    int bi();

    int bj();

    Set<String> bk();

    int bl();

    String bm();

    boolean bn();

    long bo();

    long bp();

    long bq();

    long br();

    long bs();

    boolean bt();

    Set<String> bu();

    int bv();

    String bw();

    float bx();

    int by();

    int bz();

    void c(int i);

    void c(long j);

    void c(String str);

    void c(boolean z);

    String ca();

    boolean cb();

    long cc();

    Set<String> cd();

    boolean ce();

    long cf();

    int cg();

    int ch();

    String ci();

    long cj();

    List<TagCfgModel> ck();

    long cl();

    long cm();

    String cn();

    int d();

    String d(String str);

    void d(int i);

    void d(long j);

    void d(boolean z);

    int e();

    String e(String str);

    void e(int i);

    void e(long j);

    void e(boolean z);

    long f(int i);

    void f(long j);

    void f(String str);

    void f(boolean z);

    void g(int i);

    void g(long j);

    void g(String str);

    void h(int i);

    void h(long j);

    void h(String str);

    void i(int i);

    void i(long j);

    void i(String str);

    void j(int i);

    void j(long j);

    boolean j(String str);

    void k(int i);

    void k(long j);

    boolean k(String str);

    Boolean l(String str);

    void l(int i);

    void l(long j);

    long m(String str);

    void m(int i);

    void m(long j);

    void n(long j);

    void n(String str);

    void o(long j);

    void o(String str);

    void p(long j);

    void p(String str);

    void q(String str);

    OaidRecord r(String str);

    void s(String str);

    void t(String str);

    void u(String str);

    FlowControl v(String str);

    void w(String str);

    int x(String str);

    void y(String str);

    void z(String str);
}
