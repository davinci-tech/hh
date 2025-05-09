package com.huawei.openalliance.ad.inter.data;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.inner.AdContentData;
import com.huawei.openalliance.ad.beans.metadata.AdSource;
import com.huawei.openalliance.ad.beans.metadata.CtrlExt;
import com.huawei.openalliance.ad.beans.metadata.Om;
import com.huawei.openalliance.ad.beans.metadata.PromoteInfo;
import com.huawei.openalliance.ad.bx;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.WhiteListPkgList;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.inter.data.RewardVerifyConfig;
import com.huawei.openalliance.ad.os;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.cz;
import java.util.List;
import java.util.UUID;

/* loaded from: classes5.dex */
public abstract class a implements IAd {
    private int B;
    private long C;
    private Integer D;
    private Integer E;
    private boolean F;
    private List<AdSource> G;
    private String H;
    private String I;
    private String J;
    private List<AdvertiserInfo> K;
    private String M;
    private Integer N;
    private transient CtrlExt O;
    private String Q;
    private String S;
    private PromoteInfo T;
    private BiddingInfo U;

    /* renamed from: a, reason: collision with root package name */
    protected String f7053a;
    protected long b;
    protected String c;
    protected String d;
    protected String e;
    private transient AdContentData f;
    private String i;
    private int k;
    private int l;
    private String m;
    private String n;
    private long o;
    private int p;
    private String q;
    private String s;
    private String t;
    private String u;
    private Integer v;
    private String w;
    private String x;
    private List<Om> y;
    private String z;
    private String g = String.valueOf(ao.c());
    private boolean h = false;
    private String j = "2";
    private int r = 0;
    private String A = UUID.randomUUID().toString();
    private int L = 0;
    private boolean P = false;
    private boolean R = false;
    private Integer V = 0;

    public boolean isAutoDownloadApp() {
        return true;
    }

    public boolean z() {
        return this.R;
    }

    public Integer y() {
        return this.N;
    }

    public int x() {
        return this.L;
    }

    public int w() {
        return this.l;
    }

    public AdContentData v() {
        return this.f;
    }

    public void u(String str) {
        this.S = str;
    }

    public String u() {
        return this.H;
    }

    public void t(String str) {
        this.Q = str;
    }

    public List<AdSource> t() {
        return this.G;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public void showAppDetailPage(Context context) {
        bx.a(context, this, (MaterialClickInfo) null);
    }

    public void setUserId(String str) {
        this.e = cz.d(str);
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public void setRewardVerifyConfig(RewardVerifyConfig rewardVerifyConfig) {
        if (rewardVerifyConfig != null) {
            setCustomData(rewardVerifyConfig.getData());
            setUserId(rewardVerifyConfig.getUserId());
        }
    }

    public void setCustomData(String str) {
        this.d = cz.d(str);
    }

    public void setAutoDownloadApp(boolean z) {
        this.h = z;
    }

    public boolean s() {
        return this.F;
    }

    public void s(String str) {
        this.J = str;
    }

    public void r(String str) {
        this.I = str;
    }

    public Integer r() {
        return this.E;
    }

    public void q(String str) {
        this.H = str;
    }

    public Integer q() {
        return this.D;
    }

    public void p(String str) {
        this.z = str;
    }

    public long p() {
        return this.C;
    }

    public void o(String str) {
        this.x = str;
    }

    public int o() {
        return this.B;
    }

    public void n(String str) {
        this.w = str;
    }

    public String n() {
        return this.z;
    }

    public void m(String str) {
        this.A = str;
    }

    public List<Om> m() {
        return this.y;
    }

    public void l(String str) {
        this.u = str;
    }

    public String l() {
        return this.e;
    }

    public void k(String str) {
        this.t = str;
    }

    public String k() {
        return this.d;
    }

    public void j(String str) {
        this.s = str;
    }

    public String j() {
        return this.x;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public boolean isTransparencyOpen() {
        return this.R && !TextUtils.isEmpty(this.Q);
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public boolean isShowAppElement() {
        Integer num = this.N;
        return num != null && num.intValue() == 1;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public boolean isExpired() {
        return this.b < System.currentTimeMillis();
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public boolean isAdIdInWhiteList() {
        Context d;
        if (os.k(getCtrlSwitchs())) {
            return true;
        }
        com.huawei.openalliance.ad.inter.f a2 = HiAd.a();
        if (a2 == null || (d = a2.d()) == null) {
            return false;
        }
        String packageName = d.getPackageName();
        return WhiteListPkgList.inWhiteList(packageName, com.huawei.openalliance.ad.utils.i.e(d, packageName));
    }

    public void i(String str) {
        this.q = str;
    }

    public String i() {
        return this.w;
    }

    public int hashCode() {
        String str = this.f7053a;
        return (str != null ? str.hashCode() : -1) & super.hashCode();
    }

    public void h(String str) {
        this.g = str;
    }

    public Integer h() {
        return this.v;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public void gotoWhyThisAdPage(Context context) {
        a(context);
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getWhyThisAd() {
        return TextUtils.isEmpty(this.s) ? Constants.WHY_THIS_AD_DEFAULT_URL : this.s;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getUniqueId() {
        return this.A;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getTransparencyTplUrl() {
        return this.Q;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getTaskId() {
        return this.m;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public long getStartTime() {
        return this.o;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getShowId() {
        return this.g;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public RewardVerifyConfig getRewardVerifyConfig() {
        RewardVerifyConfig.Builder builder = new RewardVerifyConfig.Builder();
        builder.setData(k());
        builder.setUserId(l());
        return builder.build();
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public PromoteInfo getPromoteInfo() {
        return this.T;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getLabel() {
        return this.n;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public int getInterfaceDownloadConfig() {
        return os.E(getCtrlSwitchs());
    }

    public String getIntent() {
        return this.c;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getHwChannelId() {
        return this.J;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public long getEndTime() {
        return this.b;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getDspName() {
        AdSource a2 = AdSource.a(this.G);
        if (a2 != null) {
            return cz.c(a2.a());
        }
        return null;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getDspLogo() {
        AdSource a2 = AdSource.a(this.G);
        if (a2 != null) {
            return a2.b();
        }
        return null;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getCta() {
        return this.i;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public int getCreativeType() {
        return this.k;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getContentId() {
        return this.f7053a;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public List<AdvertiserInfo> getCompliance() {
        return this.K;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public BiddingInfo getBiddingInfo() {
        return this.U;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getAdSign() {
        return this.j;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getAdChoiceUrl() {
        return this.t;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getAdChoiceIcon() {
        return this.u;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getAbilityDetailInfo() {
        return this.I;
    }

    public void g(String str) {
        this.c = str;
    }

    public int g() {
        return this.r;
    }

    public void f(String str) {
        this.n = str;
    }

    public void f(int i) {
        this.L = i;
    }

    public String f() {
        return this.q;
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a) || (str = this.f7053a) == null) {
            return false;
        }
        return TextUtils.equals(str, ((a) obj).f7053a);
    }

    public void e(String str) {
        this.m = str;
    }

    public void e(Integer num) {
        this.N = num;
    }

    public void e(int i) {
        this.l = i;
    }

    public int e() {
        return this.p;
    }

    public void d(boolean z) {
        this.R = z;
    }

    public void d(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.j = str;
    }

    public void d(Integer num) {
        this.E = num;
    }

    public void d(int i) {
        this.B = i;
    }

    public CtrlExt d() {
        if (this.O == null) {
            this.O = (CtrlExt) be.b(this.M, CtrlExt.class, new Class[0]);
        }
        return this.O;
    }

    public void c(boolean z) {
        this.F = z;
    }

    public void c(List<AdvertiserInfo> list) {
        this.K = list;
    }

    public void c(String str) {
        this.f7053a = str;
    }

    public void c(Integer num) {
        this.D = num;
    }

    public void c(long j) {
        this.C = j;
    }

    public void c(int i) {
        this.r = i;
    }

    public String c() {
        return this.M;
    }

    public boolean b() {
        return this.P;
    }

    public void b(boolean z) {
        AdContentData adContentData = this.f;
        if (adContentData != null) {
            adContentData.a(z);
        }
    }

    public void b(List<AdSource> list) {
        this.G = list;
    }

    public void b(String str) {
        this.i = str;
    }

    public void b(Integer num) {
        this.v = num;
    }

    public void b(long j) {
        this.b = j;
    }

    public void b(int i) {
        this.p = i;
    }

    public void a(boolean z) {
        this.P = z;
    }

    public void a(List<Om> list) {
        this.y = list;
    }

    public void a(String str) {
        this.M = str;
    }

    public void a(Integer num) {
        this.V = num;
    }

    public void a(BiddingInfo biddingInfo) {
        this.U = biddingInfo;
    }

    public void a(PromoteInfo promoteInfo) {
        this.T = promoteInfo;
    }

    public void a(AdContentData adContentData) {
        this.f = adContentData;
    }

    public void a(Context context, MaterialClickInfo materialClickInfo) {
        bx.a(context, this, materialClickInfo);
    }

    public void a(Context context) {
        if (context == null) {
            ho.c("BaseAd", "context is null not call gotoWhyThisAdPage method");
        } else {
            ao.a(context, this);
        }
    }

    public void a(long j) {
        this.o = j;
    }

    public void a(int i) {
        this.k = i;
        AdContentData adContentData = this.f;
        if (adContentData != null) {
            adContentData.c(i);
        }
    }

    public Integer a() {
        return this.V;
    }

    public String A() {
        return this.S;
    }
}
