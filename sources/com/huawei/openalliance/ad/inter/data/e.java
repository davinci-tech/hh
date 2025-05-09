package com.huawei.openalliance.ad.inter.data;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.huawei.hms.ads.AdFeedbackListener;
import com.huawei.hms.ads.VideoConfiguration;
import com.huawei.openalliance.ad.activity.FeedbackActivity;
import com.huawei.openalliance.ad.beans.metadata.ContentExt;
import com.huawei.openalliance.ad.beans.metadata.DefaultTemplate;
import com.huawei.openalliance.ad.beans.metadata.ImpEX;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.beans.metadata.Monitor;
import com.huawei.openalliance.ad.beans.metadata.v3.Asset;
import com.huawei.openalliance.ad.beans.metadata.v3.TemplateData;
import com.huawei.openalliance.ad.constant.ClickDestination;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.dc;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.ok;
import com.huawei.openalliance.ad.os;
import com.huawei.openalliance.ad.ou;
import com.huawei.openalliance.ad.pd;
import com.huawei.openalliance.ad.processor.eventbeans.a;
import com.huawei.openalliance.ad.processor.eventbeans.b;
import com.huawei.openalliance.ad.sc;
import com.huawei.openalliance.ad.sz;
import com.huawei.openalliance.ad.ta;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.vd;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class e extends a implements INativeAd {
    private int A;
    private List<Integer> B;
    private String D;
    private String E;
    private List<String> F;
    private String G;
    private String I;
    private String J;
    private String K;
    private String M;
    private List<String> N;
    private transient IAdEvent O;
    private String Q;

    @com.huawei.openalliance.ad.annotations.f
    private long R;
    private String S;
    private int T;
    private List<ImpEX> U;
    private List<ContentExt> V;
    private List<FeedbackInfo> W;
    private String X;
    private List<Asset> Y;
    private TemplateData Z;
    private String aa;
    private String ab;
    private String ac;
    private Integer ad;
    private DefaultTemplate ae;
    private byte[] ag;
    private String ah;
    private List<Monitor> ai;
    private String aj;
    private String ak;
    private String al;
    private String am;
    private transient VideoConfiguration an;
    private int g;
    private String h;
    private String i;
    private String j;
    private List<String> k;
    private ImageInfo l;
    private List<ImageInfo> m;
    private List<ImageInfo> n;
    private String o;
    private long p;
    private int q;
    private String r;
    private String s;
    private VideoInfo t;
    private List<String> u;
    private List<String> v;
    private AppInfo w;
    private String x;
    private String y;
    private AudioInfo z;
    private boolean f = false;
    private boolean C = false;
    private boolean H = false;
    private boolean L = false;
    private boolean P = false;
    private boolean af = true;

    public void z(String str) {
        this.h = str;
    }

    public void w(String str) {
        this.Q = str;
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public boolean triggerClick(Context context, Bundle bundle) {
        if (context == null || !isAdIdInWhiteList()) {
            return false;
        }
        return a(context, bundle);
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public boolean showFeedback(Context context, View view, AdFeedbackListener adFeedbackListener) {
        try {
            ho.b("INativeAd", "show feedback");
            com.huawei.openalliance.ad.views.feedback.c cVar = new com.huawei.openalliance.ad.views.feedback.c();
            cVar.a(view);
            cVar.b(adFeedbackListener);
            dc.a(this);
            FeedbackActivity.a(context, cVar);
            return true;
        } catch (Throwable th) {
            ho.c("INativeAd", "showFeedback err, %s", th.getClass().getSimpleName());
            return false;
        }
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public void setVideoConfiguration(VideoConfiguration videoConfiguration) {
        this.an = videoConfiguration;
        if (HiAd.a() != null) {
            HiAd.a().a(videoConfiguration, os.G(getCtrlSwitchs()), os.H(getCtrlSwitchs()), 3);
        }
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public boolean recordShowStartEvent(Context context, Bundle bundle) {
        if (context == null || !isAdIdInWhiteList()) {
            return false;
        }
        this.R = System.currentTimeMillis();
        ho.b("INativeAd", "recordShowStartEvent.");
        c(this.R);
        h(String.valueOf(ao.c()));
        return b(context, bundle);
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public boolean recordImpressionEvent(Context context, Bundle bundle) {
        if (context == null || !isAdIdInWhiteList()) {
            return false;
        }
        return c(context, bundle);
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public boolean recordClickEvent(Context context, Bundle bundle) {
        if (context != null && isAdIdInWhiteList()) {
            return a(context, bundle, ClickDestination.ADCONTENTINTERFACE);
        }
        ho.b("INativeAd", "record click event failed.");
        return false;
    }

    public void p(List<Monitor> list) {
        this.ai = list;
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public boolean onFeedback(Context context, int i, List<FeedbackInfo> list) {
        ho.b("INativeAd", "report feedback");
        ou c = c(context);
        if (c == null) {
            ho.b("INativeAd", "eventProcessor is null");
            return false;
        }
        if (1 == i || 3 == i) {
            c.b(list);
        } else if (2 == i) {
            c.a(list);
        }
        return true;
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public void onAdClose(Context context, List<String> list) {
        if (context == null || !isAdIdInWhiteList()) {
            return;
        }
        ho.b("INativeAd", "onAdClose, in whitelist, report onAdCloseEvent.");
        b(context).onAdClosed(list);
    }

    public void o(List<FeedbackInfo> list) {
        this.W = list;
    }

    public void n(List<ContentExt> list) {
        this.V = list;
    }

    public void m(List<ImpEX> list) {
        this.U = list;
    }

    public void m(int i) {
        this.T = i;
    }

    public void l(List<ImageInfo> list) {
        this.n = list;
    }

    public void l(int i) {
        this.A = i;
    }

    public void k(boolean z) {
        this.af = z;
    }

    public void k(List<String> list) {
        this.N = list;
    }

    public void k(int i) {
        this.q = i;
    }

    public void j(boolean z) {
        this.P = z;
    }

    public void j(List<Asset> list) {
        this.Y = list;
    }

    public void j(int i) {
        this.g = i;
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public boolean isVideoAd() {
        return this.t != null || B();
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public boolean isValid(final Context context) {
        if (this.t == null || this.L) {
            return true;
        }
        return ((Boolean) com.huawei.openalliance.ad.utils.dc.a(new Callable<Boolean>() { // from class: com.huawei.openalliance.ad.inter.data.e.1
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean call() {
                boolean z = false;
                if (bg.a(e.this.m)) {
                    return false;
                }
                if (((ImageInfo) e.this.m.get(0)).a(context) && e.this.t.a(context)) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        }, 100L, false)).booleanValue();
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public boolean isUseGaussianBlur() {
        return this.T == 1;
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public boolean isClicked() {
        return this.f;
    }

    public void i(boolean z) {
        this.L = z;
        VideoInfo videoInfo = this.t;
        if (videoInfo != null) {
            videoInfo.e(true);
        }
    }

    public void i(List<String> list) {
        this.F = list;
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public boolean hasAdvertiserInfo() {
        return !bg.a(getCompliance());
    }

    public void h(boolean z) {
        this.H = z;
    }

    public void h(List<Integer> list) {
        this.B = list;
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public VideoInfo getVideoInfo() {
        return this.t;
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public VideoConfiguration getVideoConfiguration() {
        return this.an;
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public String getTitle() {
        return this.i;
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public List<String> getSubDescription() {
        return this.k;
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public String getSlotId() {
        return this.G;
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public List<ImageInfo> getRawImageInfos() {
        return this.n;
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public String getPrivacyLink() {
        MetaData metaData = (MetaData) be.b(this.x, MetaData.class, new Class[0]);
        if (metaData != null) {
            return metaData.B();
        }
        return null;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public long getMinEffectiveShowTime() {
        return this.p;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public int getMinEffectiveShowRatio() {
        return this.q;
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public String getLandWebUrl() {
        return this.o;
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public String getJumpText(Context context) {
        return com.huawei.openalliance.ad.utils.c.a(pd.a(this), context, true);
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public List<String> getInvalidContentIds() {
        return this.N;
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public int getInterActionType() {
        return this.g;
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public String getIntentUri() {
        return cz.c(this.c);
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public List<ImageInfo> getImageInfos() {
        return this.m;
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public ImageInfo getIcon() {
        return this.l;
    }

    public List<FeedbackInfo> getFeedbackInfoList() {
        return this.W;
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public Map<String, String> getExt() {
        HashMap hashMap = new HashMap();
        if (!bg.a(this.V)) {
            for (ContentExt contentExt : this.V) {
                hashMap.put(contentExt.a(), cz.c(contentExt.b()));
            }
        }
        if (!bg.a(this.U)) {
            for (ImpEX impEX : this.U) {
                hashMap.put(impEX.a(), cz.c(impEX.b()));
            }
        }
        return hashMap;
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public String getDescription() {
        return this.j;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getCtrlSwitchs() {
        return this.E;
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public AudioInfo getAudioInfo() {
        return this.z;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public AppInfo getAppInfo() {
        return this.w;
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public List<AdvertiserInfo> getAdvertiserInfo() {
        if (hasAdvertiserInfo()) {
            return getCompliance();
        }
        return null;
    }

    @Override // com.huawei.openalliance.ad.inter.data.INativeAd
    public List<String> getAdCloseKeyWords() {
        return this.u;
    }

    public void g(boolean z) {
        this.C = z;
    }

    public void g(List<String> list) {
        this.v = list;
    }

    public void f(boolean z) {
        this.f = z;
    }

    public void f(List<String> list) {
        this.u = list;
    }

    public void f(Integer num) {
        this.ad = num;
    }

    public void e(List<ImageInfo> list) {
        this.m = list;
    }

    public void e(long j) {
        this.R = j;
    }

    public void d(List<String> list) {
        this.k = list;
    }

    public void d(long j) {
        this.p = j;
    }

    public IAdEvent b(Context context) {
        if (this.O == null) {
            if (context != null) {
                this.O = new ok(context.getApplicationContext(), this);
            } else {
                ho.b("INativeAd", " context is null, ");
            }
        }
        return this.O;
    }

    public String ax() {
        return this.ac;
    }

    public String aw() {
        return this.ab;
    }

    public String av() {
        return this.am;
    }

    public String au() {
        return this.al;
    }

    public int at() {
        return this.T;
    }

    public long as() {
        return this.R;
    }

    public boolean ar() {
        return this.L;
    }

    public String aq() {
        return this.ak;
    }

    public String ap() {
        return this.aj;
    }

    public List<Monitor> ao() {
        return this.ai;
    }

    public String an() {
        return this.ah;
    }

    public byte[] am() {
        byte[] bArr = this.ag;
        return bArr != null ? Arrays.copyOf(bArr, bArr.length) : new byte[0];
    }

    public boolean al() {
        return this.af;
    }

    public List<ContentExt> ak() {
        return this.V;
    }

    public List<ImpEX> aj() {
        return this.U;
    }

    public String ai() {
        return this.S;
    }

    public Map<String, String> ah() {
        HashMap hashMap = new HashMap();
        hashMap.put("appId", M());
        hashMap.put("thirdId", L());
        if (getVideoInfo() == null) {
            return hashMap;
        }
        hashMap.put(MapKeyNames.LINKED_CUSTOM_SHOW_ID, getShowId());
        int b = getVideoInfo().b();
        ho.b("INativeAd", "buildLinkedAdConfig, set progress from native view %s %s", Integer.valueOf(b), getVideoInfo().i());
        hashMap.put(MapKeyNames.LINKED_CUSTOM_RETURN_VIDEO_DIRECT, (aa() == null || aa().intValue() != 3 ? !getVideoInfo().f() : !ar()) ? "false" : "true");
        hashMap.put(MapKeyNames.LINKED_CUSTOM_VIDEO_SOUND_SWITCH, getVideoInfo().getSoundSwitch());
        hashMap.put(MapKeyNames.LINKED_CUSTOM_VIDEO_PROGRESS, String.valueOf(b));
        hashMap.put(MapKeyNames.LINKED_SPLASH_MEDIA_PATH, this.Q);
        hashMap.put(MapKeyNames.USE_TEMPLATE, getVideoInfo().i());
        if (getVideoConfiguration() != null) {
            hashMap.put(MapKeyNames.AUTO_PLAY_VIDEO_NETWORK, String.valueOf(getVideoConfiguration().getAutoPlayNetwork()));
            hashMap.put(MapKeyNames.PLAY_VIDEO_IS_MUTE, Boolean.toString(getVideoConfiguration().isMute()));
        }
        return hashMap;
    }

    public boolean ag() {
        return this.P;
    }

    public String af() {
        return this.M;
    }

    public String ae() {
        return this.K;
    }

    public String ad() {
        return this.J;
    }

    public String ac() {
        return this.I;
    }

    public DefaultTemplate ab() {
        return this.ae;
    }

    public Integer aa() {
        return this.ad;
    }

    public boolean a(Context context, Bundle bundle) {
        f(true);
        ta a2 = sz.a(context, pd.a(this), ah());
        boolean a3 = a2.a();
        if (a3) {
            a(context, bundle, a2.c());
        }
        return a3;
    }

    public void a(byte[] bArr) {
        if (bArr != null) {
            this.ag = Arrays.copyOf(bArr, bArr.length);
        }
    }

    public void a(VideoInfo videoInfo) {
        this.t = videoInfo;
    }

    public void a(ImageInfo imageInfo) {
        this.l = imageInfo;
    }

    public void a(AudioInfo audioInfo) {
        this.z = audioInfo;
    }

    public void a(AppInfo appInfo) {
        this.w = appInfo;
    }

    public void a(TemplateData templateData) {
        this.Z = templateData;
    }

    public void a(DefaultTemplate defaultTemplate) {
        this.ae = defaultTemplate;
    }

    public MaterialClickInfo a(Bundle bundle) {
        JSONObject b = cz.b(bundle);
        Integer valueOf = Integer.valueOf(b.optInt(MapKeyNames.CLICK_X, -111111));
        Integer valueOf2 = Integer.valueOf(b.optInt(MapKeyNames.CLICK_Y, -111111));
        String optString = b.optString(MapKeyNames.CREATIVE_SIZE, "");
        Float a2 = cz.a(b.optString(MapKeyNames.DENSITY, "-111111"), Float.valueOf(-111111.0f));
        Integer valueOf3 = Integer.valueOf(b.optInt(MapKeyNames.UP_X, -111111));
        Integer valueOf4 = Integer.valueOf(b.optInt(MapKeyNames.UP_Y, -111111));
        Integer valueOf5 = Integer.valueOf(b.optInt(MapKeyNames.SLD, -111111));
        Long valueOf6 = Long.valueOf(b.optLong(MapKeyNames.CLICK_DOWN_TIME));
        Long valueOf7 = Long.valueOf(b.optLong(MapKeyNames.CLICK_UP_TIME));
        String optString2 = b.optString(MapKeyNames.SHAKE_ANGLE, "");
        if (valueOf.intValue() == -111111) {
            valueOf = null;
        }
        if (valueOf2.intValue() == -111111) {
            valueOf2 = null;
        }
        if (!cz.p(optString)) {
            optString = null;
        }
        if (a2.floatValue() == -111111.0f) {
            a2 = null;
        }
        if (valueOf3.intValue() == -111111) {
            valueOf3 = null;
        }
        if (valueOf4.intValue() == -111111) {
            valueOf4 = null;
        }
        if (valueOf5.intValue() == -111111) {
            valueOf5 = null;
        }
        if (valueOf6.longValue() == 0) {
            valueOf6 = null;
        }
        if (valueOf7.longValue() == 0) {
            valueOf7 = null;
        }
        if (cz.b(optString2)) {
            optString2 = null;
        }
        return new MaterialClickInfo.a().a(valueOf).b(valueOf2).b(optString).c(valueOf5).a(a2).d(valueOf3).e(valueOf4).b(valueOf6).a(valueOf7).a(optString2).a();
    }

    public String Z() {
        return this.aa;
    }

    public TemplateData Y() {
        return this.Z;
    }

    public void X(String str) {
        this.ac = str;
    }

    public List<Asset> X() {
        return this.Y;
    }

    public void W(String str) {
        this.ab = str;
    }

    public String W() {
        return this.X;
    }

    public boolean V() {
        return this.H;
    }

    public void V(String str) {
        this.am = str;
    }

    public void U(String str) {
        this.al = str;
    }

    public List<String> U() {
        return this.F;
    }

    public void T(String str) {
        this.ak = str;
    }

    public String T() {
        return this.D;
    }

    public boolean S() {
        return this.C;
    }

    public void S(String str) {
        this.aj = str;
    }

    public void R(String str) {
        this.ah = str;
    }

    public List<Integer> R() {
        return this.B;
    }

    public void Q(String str) {
        this.S = str;
    }

    public int Q() {
        return this.A;
    }

    public void P(String str) {
        this.M = str;
    }

    public String P() {
        return this.y;
    }

    public void O(String str) {
        this.K = str;
    }

    public String O() {
        return this.x;
    }

    public void N(String str) {
        this.J = str;
    }

    public List<String> N() {
        return this.v;
    }

    public void M(String str) {
        this.I = str;
    }

    public String M() {
        return this.s;
    }

    public void L(String str) {
        this.aa = str;
    }

    public String L() {
        return this.r;
    }

    public void K(String str) {
        this.X = str;
    }

    public String K() {
        return this.h;
    }

    public void J(String str) {
        this.G = str;
    }

    public void I(String str) {
        this.E = str;
    }

    public void H(String str) {
        this.D = str;
    }

    public void G(String str) {
        this.y = str;
    }

    public void F(String str) {
        this.x = str;
    }

    public void E(String str) {
        this.s = str;
    }

    public void D(String str) {
        this.r = str;
    }

    public String D() {
        return this.Q;
    }

    public void C(String str) {
        this.o = str;
    }

    public void B(String str) {
        this.j = str;
    }

    public void A(String str) {
        this.i = str;
    }

    private boolean c(Context context, Bundle bundle) {
        ou c = c(context);
        if (c == null) {
            ho.b("INativeAd", "eventProcessor is null");
            return false;
        }
        ho.b("INativeAd", "api onAdShowed");
        long min = Math.min(System.currentTimeMillis() - this.R, getMinEffectiveShowTime());
        a.C0207a c0207a = new a.C0207a();
        c0207a.a(Long.valueOf(min)).a(Integer.valueOf(this.q)).b((Integer) 7).a(com.huawei.openalliance.ad.utils.b.a(context)).e(vd.a(bundle)).d(vd.b(bundle)).b(cz.a(bundle));
        c.a(c0207a.a());
        return true;
    }

    private ou c(Context context) {
        ContentRecord a2 = pd.a(this);
        if (a2 == null) {
            ho.b("INativeAd", "contentRecord is empty when convert from nativeAd");
            return null;
        }
        ou ouVar = new ou(context, sc.a(context, a2.e()));
        ouVar.a(a2);
        return ouVar;
    }

    private boolean b(Context context, Bundle bundle) {
        ou c = c(context);
        if (c == null) {
            ho.b("INativeAd", "eventProcessor is null");
            return false;
        }
        c.a(cz.a(bundle));
        return true;
    }

    private boolean a(Context context, Bundle bundle, String str) {
        ou c = c(context);
        if (c == null) {
            ho.b("INativeAd", "eventProcessor is null");
            return false;
        }
        b.a aVar = new b.a();
        MaterialClickInfo a2 = a(bundle);
        if (a2.i() == null && a2.h() == null) {
            a2.f(1);
        }
        aVar.b(str).a((Integer) 12).c(cz.a(bundle)).a(a2).d(com.huawei.openalliance.ad.utils.b.a(context));
        c.a(aVar.a());
        return true;
    }

    private boolean B() {
        List<Asset> list;
        Integer num = this.ad;
        if (num == null || num.intValue() != 3 || (list = this.Y) == null) {
            return false;
        }
        for (Asset asset : list) {
            if (asset != null && asset.e() != null) {
                return true;
            }
        }
        return false;
    }
}
