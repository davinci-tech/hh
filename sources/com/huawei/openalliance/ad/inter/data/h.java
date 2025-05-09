package com.huawei.openalliance.ad.inter.data;

import android.content.Context;
import com.huawei.hms.ads.VideoConfiguration;
import com.huawei.openalliance.ad.beans.metadata.v3.Asset;
import com.huawei.openalliance.ad.beans.metadata.v3.TemplateData;
import com.huawei.openalliance.ad.bx;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.inter.listeners.INonwifiActionListener;
import com.huawei.openalliance.ad.inter.listeners.IRewardAdStatusListener;
import com.huawei.openalliance.ad.oq;
import com.huawei.openalliance.ad.os;
import java.util.List;

/* loaded from: classes5.dex */
public class h extends a implements IRewardAd {
    private String A;
    private String B;
    private List<String> C;
    private String D;
    private String F;
    private String G;
    private transient IRewardAdStatusListener H;
    private transient INonwifiActionListener I;
    private String J;
    private String L;
    private String M;
    private long N;
    private RewardItem P;
    private String Q;
    private String T;
    private List<Asset> U;
    private TemplateData V;
    private Integer W;
    private int g;
    private String h;
    private String i;
    private String j;
    private List<ImageInfo> k;
    private List<ImageInfo> l;
    private String m;
    private long n;
    private int o;
    private String p;
    private String q;
    private VideoInfo r;
    private List<String> s;
    private AppInfo t;
    private String u;
    private String v;
    private int w;
    private List<Integer> x;
    private String z;
    private boolean f = false;
    private boolean y = false;
    private boolean E = false;
    private boolean K = false;
    private boolean O = true;
    private int R = 1;
    private boolean S = true;

    public void z(String str) {
        this.q = str;
    }

    public void y(String str) {
        this.p = str;
    }

    public void x(String str) {
        this.m = str;
    }

    public void w(String str) {
        this.j = str;
    }

    public void v(String str) {
        this.i = str;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IRewardAd
    public void show(Context context, IRewardAdStatusListener iRewardAdStatusListener) {
        ho.b("RewardAd", ParamConstants.CallbackMethod.ON_SHOW);
        a(iRewardAdStatusListener);
        if (this.t != null) {
            ho.a("RewardAd", "appName:" + this.t.getAppName() + ", uniqueId:" + getUniqueId() + ", appuniqueId:" + this.t.getUniqueId());
        }
        bx.a(context, this);
        oq.a(context).b(context);
    }

    @Override // com.huawei.openalliance.ad.inter.data.IRewardAd
    public void setVideoConfiguration(VideoConfiguration videoConfiguration) {
        if (videoConfiguration == null) {
            return;
        }
        if (HiAd.a() != null) {
            HiAd.a().a(videoConfiguration, os.G(getCtrlSwitchs()), os.H(getCtrlSwitchs()), 7);
        }
        if (videoConfiguration.getAutoPlayNetwork() == 1) {
            setMobileDataAlertSwitch(false);
        } else {
            setMobileDataAlertSwitch(true);
        }
        setMute(videoConfiguration.isMute());
    }

    @Override // com.huawei.openalliance.ad.inter.data.IRewardAd
    public void setNonwifiActionListener(INonwifiActionListener iNonwifiActionListener) {
        this.I = iNonwifiActionListener;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IRewardAd
    public void setMute(boolean z) {
        this.O = z;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IRewardAd
    public void setMobileDataAlertSwitch(boolean z) {
        this.S = z;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IRewardAd
    public void setAudioFocusType(int i) {
        this.R = i;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IRewardAd
    public boolean isValid() {
        return (aj() != null && aj().intValue() == 3) || this.r != null;
    }

    public void i(List<Asset> list) {
        this.U = list;
    }

    public void i(int i) {
        this.w = i;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IRewardAd
    public boolean hasShown() {
        return M();
    }

    public void h(boolean z) {
        this.K = z;
    }

    public void h(List<String> list) {
        this.C = list;
    }

    public void h(int i) {
        this.o = i;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IRewardAd
    public RewardItem getRewardItem() {
        return this.P;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public long getMinEffectiveShowTime() {
        return this.n;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public int getMinEffectiveShowRatio() {
        return this.o;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getCtrlSwitchs() {
        return this.B;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public AppInfo getAppInfo() {
        return this.t;
    }

    public void g(boolean z) {
        this.E = z;
    }

    public void g(List<Integer> list) {
        this.x = list;
    }

    public void g(int i) {
        this.g = i;
    }

    public void f(boolean z) {
        this.y = z;
    }

    public void f(List<String> list) {
        this.s = list;
    }

    public void f(Integer num) {
        this.W = num;
    }

    public void e(boolean z) {
        this.f = z;
    }

    public void e(List<ImageInfo> list) {
        this.l = list;
    }

    public void e(long j) {
        this.N = j;
    }

    public void d(List<ImageInfo> list) {
        this.k = list;
    }

    public void d(long j) {
        this.n = j;
    }

    public Integer aj() {
        return this.W;
    }

    public TemplateData ai() {
        return this.V;
    }

    public List<Asset> ah() {
        return this.U;
    }

    public String ag() {
        return this.T;
    }

    public String af() {
        return this.Q;
    }

    public int ae() {
        return this.R;
    }

    public boolean ad() {
        if (!os.G(getCtrlSwitchs())) {
            return this.S;
        }
        ho.b("RewardAd", "server switch first, need data alert.");
        return true;
    }

    public long ac() {
        return this.N;
    }

    public String ab() {
        return this.M;
    }

    public String aa() {
        return this.L;
    }

    public void a(IRewardAdStatusListener iRewardAdStatusListener) {
        this.H = iRewardAdStatusListener;
    }

    public void a(VideoInfo videoInfo) {
        this.r = videoInfo;
    }

    public void a(RewardItem rewardItem) {
        this.P = rewardItem;
    }

    public void a(AppInfo appInfo) {
        this.t = appInfo;
    }

    public void a(TemplateData templateData) {
        this.V = templateData;
    }

    public boolean Z() {
        return this.K;
    }

    public String Y() {
        return this.J;
    }

    public IRewardAdStatusListener X() {
        return this.H;
    }

    public boolean W() {
        if (!os.H(getCtrlSwitchs())) {
            return this.O;
        }
        ho.b("RewardAd", "server switch first, mute.");
        return true;
    }

    public INonwifiActionListener V() {
        return this.I;
    }

    public String U() {
        return this.G;
    }

    public String T() {
        return this.v;
    }

    public String S() {
        return this.h;
    }

    public String R() {
        return this.F;
    }

    public boolean Q() {
        return this.E;
    }

    public String P() {
        return this.D;
    }

    public List<String> O() {
        return this.C;
    }

    public void N(String str) {
        this.T = str;
    }

    public String N() {
        return this.A;
    }

    public boolean M() {
        return this.y;
    }

    public void M(String str) {
        this.Q = str;
    }

    public void L(String str) {
        this.M = str;
    }

    public int L() {
        return this.w;
    }

    public void K(String str) {
        this.L = str;
    }

    public List<Integer> K() {
        return this.x;
    }

    public void J(String str) {
        this.z = str;
    }

    public String J() {
        return this.u;
    }

    public void I(String str) {
        this.J = str;
    }

    public VideoInfo I() {
        return this.r;
    }

    public void H(String str) {
        this.G = str;
    }

    public String H() {
        return this.q;
    }

    public void G(String str) {
        this.v = str;
    }

    public String G() {
        return this.p;
    }

    public void F(String str) {
        this.h = str;
    }

    public String F() {
        return this.m;
    }

    public void E(String str) {
        this.F = str;
    }

    public List<ImageInfo> E() {
        return this.l;
    }

    public void D(String str) {
        this.D = str;
    }

    public List<ImageInfo> D() {
        return this.k;
    }

    public void C(String str) {
        this.B = str;
    }

    public String C() {
        return this.i;
    }

    public void B(String str) {
        this.A = str;
    }

    public int B() {
        return this.g;
    }

    public void A(String str) {
        this.u = str;
    }
}
