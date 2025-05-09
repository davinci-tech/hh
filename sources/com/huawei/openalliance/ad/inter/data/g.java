package com.huawei.openalliance.ad.inter.data;

import android.content.Context;
import com.huawei.openalliance.ad.constant.MimeType;
import com.huawei.openalliance.ad.pi;
import com.huawei.openalliance.ad.utils.bg;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class g extends a implements IPlacementAd, Comparable {
    private List<String> A;
    private String B;
    private String C;
    private String D;
    private String g;
    private int h;
    private String i;
    private String j;
    private List<Integer> k;
    private String l;
    private String m;
    private int n;
    private String o;
    private long p;
    private int q;
    private String r;
    private String s;
    private AppInfo t;
    private PlacementMediaFile u;
    private int w;
    private String y;
    private boolean f = false;
    private List<PlacementMediaFile> v = new ArrayList(4);
    private boolean x = false;
    private boolean z = false;

    public void z(String str) {
        this.j = str;
    }

    public void y(String str) {
        this.s = str;
    }

    public void x(String str) {
        this.r = str;
    }

    public void w(String str) {
        this.o = str;
    }

    public void v(String str) {
        this.i = str;
    }

    public void j(int i) {
        this.n = i;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IPlacementAd
    public boolean isVideoAd() {
        PlacementMediaFile placementMediaFile = this.u;
        return placementMediaFile != null && "video/mp4".equals(placementMediaFile.getMime());
    }

    @Override // com.huawei.openalliance.ad.inter.data.IPlacementAd
    public boolean isShown() {
        return this.x;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IPlacementAd
    public boolean isImageAd() {
        PlacementMediaFile placementMediaFile = this.u;
        return placementMediaFile != null && (MimeType.JPEG.equals(placementMediaFile.getMime()) || MimeType.GIF.equals(this.u.getMime()) || MimeType.JPG.equals(this.u.getMime()) || "image/png".equals(this.u.getMime()));
    }

    @Override // com.huawei.openalliance.ad.inter.data.IPlacementAd
    public boolean isClicked() {
        return this.f;
    }

    public void i(int i) {
        this.w = i;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IPlacementAd
    public boolean hasAdvertiserInfo() {
        return !bg.a(getCompliance());
    }

    public void h(int i) {
        this.q = i;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IPlacementAd
    public String getWebConfig() {
        return this.l;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IPlacementAd
    public int getShowLandingPageTitleFlag() {
        return this.w;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IPlacementAd
    public int getSequence() {
        return this.n;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IPlacementAd
    public String getPromotionChannel() {
        return this.r;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public long getMinEffectiveShowTime() {
        return this.p;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public int getMinEffectiveShowRatio() {
        return this.q;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IPlacementAd
    public PlacementMediaFile getMediaFile() {
        return this.u;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IPlacementAd
    public String getLandWebUrl() {
        return this.o;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IPlacementAd
    public String getJumpText(Context context) {
        return com.huawei.openalliance.ad.utils.c.a(pi.a(this), context, true);
    }

    @Override // com.huawei.openalliance.ad.inter.data.IPlacementAd
    public int getInterActionType() {
        return this.h;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IPlacementAd
    public String getEncodedeMonitors() {
        return this.j;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IPlacementAd
    public String getEncodedParamFromServer() {
        return this.i;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public String getCtrlSwitchs() {
        return this.m;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IPlacementAd
    public List<Integer> getClickActionList() {
        return this.k;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IPlacementAd
    public String getAppMarketAppId() {
        return this.s;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IAd
    public AppInfo getAppInfo() {
        return this.t;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IPlacementAd
    public List<AdvertiserInfo> getAdvertiserInfo() {
        if (hasAdvertiserInfo()) {
            return getCompliance();
        }
        return null;
    }

    @Override // com.huawei.openalliance.ad.inter.data.IPlacementAd
    public int getAdinteractiontype() {
        return this.h;
    }

    public void g(boolean z) {
        this.z = z;
    }

    public void g(int i) {
        this.h = i;
    }

    public void f(boolean z) {
        this.x = z;
    }

    public void f(List<String> list) {
        this.A = list;
    }

    public void e(boolean z) {
        this.f = z;
    }

    public void e(List<PlacementMediaFile> list) {
        this.v = list;
    }

    public void d(List<Integer> list) {
        this.k = list;
    }

    public void d(long j) {
        this.p = j;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        return ((obj instanceof g) && ((g) obj).getSequence() <= getSequence()) ? 1 : -1;
    }

    public void a(PlacementMediaFile placementMediaFile) {
        this.u = placementMediaFile;
    }

    public void a(AppInfo appInfo) {
        this.t = appInfo;
    }

    public String I() {
        return this.D;
    }

    public String H() {
        return this.C;
    }

    public void G(String str) {
        this.D = str;
    }

    public String G() {
        return this.B;
    }

    public void F(String str) {
        this.C = str;
    }

    public String F() {
        return this.g;
    }

    public void E(String str) {
        this.B = str;
    }

    public List<String> E() {
        return this.A;
    }

    public boolean D() {
        return this.z;
    }

    public void D(String str) {
        this.g = str;
    }

    public void C(String str) {
        this.y = str;
    }

    public String C() {
        return this.y;
    }

    public void B(String str) {
        this.m = str;
    }

    public List<PlacementMediaFile> B() {
        return this.v;
    }

    public void A(String str) {
        this.l = str;
    }
}
