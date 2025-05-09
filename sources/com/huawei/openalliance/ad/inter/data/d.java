package com.huawei.openalliance.ad.inter.data;

import android.content.Context;
import com.huawei.hms.ads.VideoConfiguration;
import com.huawei.openalliance.ad.beans.metadata.v3.Asset;
import com.huawei.openalliance.ad.beans.metadata.v3.TemplateData;
import com.huawei.openalliance.ad.bx;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.oq;
import com.huawei.openalliance.ad.os;
import com.huawei.openalliance.ad.utils.bg;
import java.util.List;

/* loaded from: classes5.dex */
public class d extends a implements b {
    private List<String> A;
    private String B;
    private String D;
    private String E;
    private com.huawei.openalliance.ad.inter.listeners.a G;
    private RewardItem H;
    private String I;
    private String K;
    private String L;
    private long M;
    private String N;
    private VideoConfiguration O;
    private Integer Q;
    private String R;
    private List<Asset> S;
    private TemplateData T;
    private int g;
    private String h;
    private String i;
    private String j;
    private List<ImageInfo> k;
    private String l;
    private long m;
    private int n;
    private String o;
    private String p;
    private VideoInfo q;
    private List<String> r;
    private AppInfo s;
    private String t;
    private String u;
    private int v;
    private List<Integer> w;
    private String y;
    private String z;
    private boolean f = false;
    private boolean x = false;
    private boolean C = false;
    private boolean F = true;
    private boolean J = false;
    private boolean P = true;

    public void z(String str) {
        this.o = str;
    }

    public void y(String str) {
        this.l = str;
    }

    public void x(String str) {
        this.j = str;
    }

    public void w(String str) {
        this.i = str;
    }

    public void v(String str) {
        this.h = str;
    }

    public void i(int i) {
        this.v = i;
    }

    public void h(boolean z) {
        this.P = z;
    }

    public void h(List<Integer> list) {
        this.w = list;
    }

    public void h(int i) {
        this.n = i;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public long getMinEffectiveShowTime() {
        return this.m;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public int getMinEffectiveShowRatio() {
        return this.n;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getCtrlSwitchs() {
        return this.z;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public AppInfo getAppInfo() {
        return this.s;
    }

    public void g(boolean z) {
        this.C = z;
    }

    public void g(List<Asset> list) {
        this.S = list;
    }

    public void g(int i) {
        this.g = i;
    }

    public void f(boolean z) {
        this.F = z;
    }

    public void f(List<String> list) {
        this.A = list;
    }

    public void f(Integer num) {
        this.Q = num;
    }

    public void e(boolean z) {
        this.x = z;
    }

    public void e(List<String> list) {
        this.r = list;
    }

    public void e(long j) {
        this.M = j;
    }

    @Override // com.huawei.openalliance.ad.inter.data.b
    public boolean d_() {
        Integer num;
        return (this.S == null || (num = this.Q) == null || num.intValue() != 3) ? false : true;
    }

    public void d(List<ImageInfo> list) {
        this.k = list;
    }

    public void d(long j) {
        this.m = j;
    }

    @Override // com.huawei.openalliance.ad.inter.data.b
    public boolean c_() {
        if (d_()) {
            return true;
        }
        int creativeType = getCreativeType();
        return (creativeType == 2 || creativeType == 4) ? !bg.a(this.k) : creativeType == 9 && this.q != null;
    }

    @Override // com.huawei.openalliance.ad.inter.data.b
    public boolean b_() {
        return this.x;
    }

    public boolean ac() {
        if (!os.G(getCtrlSwitchs())) {
            return this.P;
        }
        ho.b("InnerInterstitialAd", "server switch first, need data alert.");
        return true;
    }

    public com.huawei.openalliance.ad.inter.listeners.a ab() {
        return this.G;
    }

    public String aa() {
        return this.N;
    }

    public void a(com.huawei.openalliance.ad.inter.listeners.a aVar) {
        this.G = aVar;
    }

    public void a(VideoInfo videoInfo) {
        this.q = videoInfo;
    }

    public void a(RewardItem rewardItem) {
        this.H = rewardItem;
    }

    public void a(AppInfo appInfo) {
        this.s = appInfo;
    }

    public void a(TemplateData templateData) {
        this.T = templateData;
    }

    @Override // com.huawei.openalliance.ad.inter.data.b
    public void a(VideoConfiguration videoConfiguration) {
        if (videoConfiguration == null) {
            return;
        }
        this.O = videoConfiguration;
        if (HiAd.a() != null) {
            HiAd.a().a(videoConfiguration, os.G(getCtrlSwitchs()), os.H(getCtrlSwitchs()), 12);
        }
        if (videoConfiguration.getAutoPlayNetwork() == 1) {
            h(false);
        } else {
            h(true);
        }
        f(videoConfiguration.isMute());
    }

    @Override // com.huawei.openalliance.ad.inter.data.b
    public void a(Context context, com.huawei.openalliance.ad.inter.listeners.a aVar) {
        if (context == null) {
            return;
        }
        e(true);
        a(aVar);
        AppInfo appInfo = getAppInfo();
        if (appInfo != null) {
            ho.a("InnerInterstitialAd", "appName:%s, uniqueId:%s, appuniqueId:%s", appInfo.getAppName(), getUniqueId(), appInfo.getUniqueId());
        }
        bx.a(context, this);
        oq.a(context).b(context);
    }

    public List<Integer> Z() {
        return this.w;
    }

    public List<Asset> Y() {
        return this.S;
    }

    public TemplateData X() {
        return this.T;
    }

    public String W() {
        return this.R;
    }

    public Integer V() {
        return this.Q;
    }

    public long U() {
        return this.M;
    }

    public String T() {
        return this.L;
    }

    public boolean S() {
        if (!os.H(getCtrlSwitchs())) {
            return this.F;
        }
        ho.b("InnerInterstitialAd", "server switch first, mute.");
        return true;
    }

    public String R() {
        return this.E;
    }

    public String Q() {
        return this.D;
    }

    public boolean P() {
        return this.C;
    }

    public String O() {
        return this.B;
    }

    public List<String> N() {
        return this.A;
    }

    public void M(String str) {
        this.N = str;
    }

    public String M() {
        return this.y;
    }

    public void L(String str) {
        this.R = str;
    }

    public int L() {
        return this.v;
    }

    public void K(String str) {
        this.L = str;
    }

    public String K() {
        return this.u;
    }

    public void J(String str) {
        this.K = str;
    }

    public String J() {
        return this.t;
    }

    public void I(String str) {
        this.I = str;
    }

    public VideoInfo I() {
        return this.q;
    }

    public void H(String str) {
        this.E = str;
    }

    public String H() {
        return this.p;
    }

    public void G(String str) {
        this.D = str;
    }

    public String G() {
        return this.o;
    }

    public void F(String str) {
        this.B = str;
    }

    public String F() {
        return this.l;
    }

    public void E(String str) {
        this.z = str;
    }

    public String E() {
        return this.i;
    }

    public void D(String str) {
        this.y = str;
    }

    public String D() {
        return this.h;
    }

    public void C(String str) {
        this.u = str;
    }

    public int C() {
        return this.g;
    }

    public void B(String str) {
        this.t = str;
    }

    public RewardItem B() {
        return this.H;
    }

    public void A(String str) {
        this.p = str;
    }
}
