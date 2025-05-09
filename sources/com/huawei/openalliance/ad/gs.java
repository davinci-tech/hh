package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.ads.VideoConfiguration;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.inter.data.ImageInfo;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

/* loaded from: classes9.dex */
public class gs implements gt {
    private static ArrayList<Integer> c = new ArrayList<Integer>() { // from class: com.huawei.openalliance.ad.gs.1
        {
            add(3);
            add(1);
        }
    };
    private static ArrayList<Integer> d = new ArrayList<Integer>() { // from class: com.huawei.openalliance.ad.gs.2
        {
            add(12);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    protected AdLandingPageData f6889a;
    protected ContentRecord b;
    private final String e;
    private final String f;
    private gz g;
    private boolean h;
    private boolean i;
    private boolean j;
    private VideoInfo k;
    private ImageInfo l;
    private long m;
    private boolean n;
    private boolean o;
    private int p;
    private String q;
    private dk r;
    private Context s;
    private VideoConfiguration t;

    @Override // com.huawei.openalliance.ad.gt
    public VideoConfiguration m() {
        return this.t;
    }

    @Override // com.huawei.openalliance.ad.gt
    public boolean l() {
        return (j() == 3 && "1".equals(this.b.ad()) && "4".equals(this.b.h().I())) ? false : true;
    }

    @Override // com.huawei.openalliance.ad.gt
    public boolean k() {
        int j = j();
        if (!c.contains(Integer.valueOf(j)) || f() == null) {
            return false;
        }
        if (j != 1) {
            return true;
        }
        return d.contains(Integer.valueOf(d()));
    }

    @Override // com.huawei.openalliance.ad.gt
    public int j() {
        return this.p;
    }

    public ImageInfo i() {
        String str;
        dk a2;
        if (this.l == null) {
            gz gzVar = this.g;
            if (gzVar != null) {
                this.l = a(gzVar.b());
            }
            ImageInfo imageInfo = this.l;
            if (imageInfo != null && imageInfo.getUrl() != null) {
                String url = this.l.getUrl();
                if (url.startsWith("http")) {
                    String b = com.huawei.openalliance.ad.utils.az.b(this.s, url);
                    dk dkVar = this.r;
                    str = dkVar.c(dkVar.e(b));
                    if (com.huawei.openalliance.ad.utils.cz.b(str)) {
                        a2 = dh.a(this.s, Constants.TPLATE_CACHE);
                        this.r = a2;
                        url = a2.e(b);
                        str = a2.c(url);
                    }
                    this.l.c(str);
                } else {
                    if (dk.i(url)) {
                        str = this.r.c(url);
                        if (!com.huawei.openalliance.ad.utils.ae.d(new File(str))) {
                            a2 = dh.a(this.s, Constants.TPLATE_CACHE);
                            this.r = a2;
                            str = a2.c(url);
                        }
                    } else {
                        str = null;
                    }
                    this.l.c(str);
                }
            }
        }
        return this.l;
    }

    public int hashCode() {
        String b = b();
        return (b != null ? b.hashCode() : -1) & super.hashCode();
    }

    public String h() {
        gz gzVar = this.g;
        if (gzVar != null) {
            return gzVar.f();
        }
        return null;
    }

    @Override // com.huawei.openalliance.ad.gt
    public String g() {
        AdLandingPageData adLandingPageData = this.f6889a;
        if (adLandingPageData != null) {
            return adLandingPageData.f();
        }
        return null;
    }

    public VideoInfo f() {
        if (this.k == null) {
            gz gzVar = this.g;
            if (gzVar != null && gzVar.a() != null) {
                VideoInfo videoInfo = new VideoInfo(this.g.a());
                this.k = videoInfo;
                videoInfo.e("y");
                int c2 = this.g.c();
                ho.b("LinkedNativeAd", "obtain progress from native view %s %s", Integer.valueOf(c2), this.g.h());
                this.k.e(this.g.e());
                this.k.e(c2);
                this.k.g(this.g.h());
                this.k.b("y");
            }
            this.b = pf.a(this.f6889a);
        }
        return this.k;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof gs)) {
            return false;
        }
        if (obj.hashCode() == hashCode()) {
            return true;
        }
        String b = b();
        if (b != null) {
            return TextUtils.equals(b, ((gs) obj).b());
        }
        return false;
    }

    @Override // com.huawei.openalliance.ad.gt
    public String e() {
        gz gzVar = this.g;
        return gzVar != null ? gzVar.g() : String.valueOf(com.huawei.openalliance.ad.utils.ao.c());
    }

    public int d() {
        AdLandingPageData adLandingPageData = this.f6889a;
        if (adLandingPageData != null) {
            return adLandingPageData.r();
        }
        return 0;
    }

    public AdLandingPageData c() {
        return this.f6889a;
    }

    public String b() {
        AdLandingPageData adLandingPageData = this.f6889a;
        if (adLandingPageData != null) {
            return adLandingPageData.getContentId();
        }
        return null;
    }

    public ContentRecord a() {
        return this.b;
    }

    public static ImageInfo a(com.huawei.openalliance.ad.beans.metadata.ImageInfo imageInfo) {
        if (imageInfo != null) {
            return new ImageInfo(imageInfo);
        }
        return null;
    }

    public gs(Context context, AdLandingPageData adLandingPageData, gz gzVar) {
        String uuid = UUID.randomUUID().toString();
        this.e = uuid;
        this.h = false;
        this.i = false;
        this.j = false;
        this.m = -1L;
        this.n = false;
        this.o = false;
        this.p = -1;
        this.f6889a = adLandingPageData;
        this.g = gzVar;
        this.r = dh.a(context, "normal");
        AdLandingPageData adLandingPageData2 = this.f6889a;
        if (adLandingPageData2 != null) {
            this.p = adLandingPageData2.getAdType();
            this.f6889a.j(uuid);
        }
        if (gzVar != null) {
            this.q = gzVar.f();
            this.t = gzVar.i();
        }
        this.s = context;
        this.f = null;
    }
}
