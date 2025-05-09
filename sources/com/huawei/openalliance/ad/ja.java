package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.beans.metadata.Ad30;
import com.huawei.openalliance.ad.beans.metadata.Content;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.constant.ErrorCode;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.LinkedSplashAd;
import com.huawei.openalliance.ad.inter.listeners.LinkedAdListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class ja extends ix {
    boolean p;
    private final int q;
    private boolean r;
    private boolean s;
    private boolean t;
    private ContentRecord u;
    private boolean v;

    private boolean a(boolean z, boolean z2, boolean z3) {
        if (z && z2) {
            return true;
        }
        return z2 && z3;
    }

    @Override // com.huawei.openalliance.ad.ix
    protected String z() {
        return "RealtimeAdMediator" + this.q;
    }

    @Override // com.huawei.openalliance.ad.ix
    protected String r() {
        return String.valueOf(2);
    }

    @Override // com.huawei.openalliance.ad.ix
    protected void c(ContentRecord contentRecord) {
        ho.b(z(), "on content loaded, content record: " + com.huawei.openalliance.ad.utils.cz.b(contentRecord));
        this.u = contentRecord;
        this.e.a(contentRecord);
        d(contentRecord);
        if (contentRecord == null) {
            ho.a(z(), "ERROR_CODE_EMPTY_REQ_PARAM: contentRecord is null. ");
            c(ErrorCode.ERROR_CODE_NO_AD_RECORD);
            B();
            return;
        }
        com.huawei.openalliance.ad.views.interfaces.l p = p();
        if (p == null) {
            c(ErrorCode.ERROR_CODE_NULL_AD_VIEW);
            B();
            return;
        }
        Context context = p.getContext();
        da daVar = new da(context);
        if (daVar.a()) {
            c(ErrorCode.ERROR_CODE_TRIGGER_DISTURB);
            B();
            return;
        }
        if (contentRecord.D() != 12) {
            if (!this.r && !this.s) {
                ho.b(z(), "slogan hasn't reach min show time or end, show ad later");
                return;
            }
            if (daVar.a()) {
                c(ErrorCode.ERROR_CODE_TRIGGER_DISTURB);
                B();
                return;
            }
            boolean b = b(contentRecord);
            this.p = true;
            if (b) {
                return;
            }
            b(ErrorCode.ERROR_CODE_NULL_AD_VIEW);
            return;
        }
        if (d() == 1 && (c() instanceof LinkedAdListener)) {
            ho.b(z(), "on linked loaded, sloganShowEnd:" + this.s);
            if (!this.s) {
                LinkedAdListener linkedAdListener = (LinkedAdListener) c();
                LinkedSplashAd a2 = oz.a(context, contentRecord);
                ho.b(z(), "on content loaded, linkedAd loaded. ");
                this.l.b(System.currentTimeMillis());
                linkedAdListener.onLinkedAdLoaded(a2);
                this.k = contentRecord;
                this.v = true;
                g(200);
                return;
            }
        }
        com.huawei.openalliance.ad.utils.cv.a(new Runnable() { // from class: com.huawei.openalliance.ad.ja.5
            @Override // java.lang.Runnable
            public void run() {
                ja.this.c(1200);
                ja.this.B();
            }
        });
    }

    public void b(boolean z) {
        this.p = z;
    }

    @Override // com.huawei.openalliance.ad.qw
    public List<ContentRecord> b(AdContentRsp adContentRsp) {
        List<Ad30> list;
        ArrayList arrayList;
        ArrayList arrayList2 = new ArrayList();
        if (adContentRsp == null) {
            return arrayList2;
        }
        List<Ad30> c = adContentRsp.c();
        if (com.huawei.openalliance.ad.utils.bg.a(c)) {
            return arrayList2;
        }
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        String str = "";
        String str2 = str;
        Content content = null;
        Content content2 = null;
        Content content3 = null;
        boolean z3 = false;
        boolean z4 = false;
        while (i < c.size() && !z3) {
            Ad30 ad30 = c.get(i);
            String a2 = ad30.a();
            String g = ad30.g();
            int b = ad30.b();
            if (200 != b) {
                list = c;
                ho.b("RealtimeAdMediator", "ad failed, retcode30: %s, slotId: %s.", Integer.valueOf(b), a2);
            } else {
                list = c;
            }
            List<Content> c2 = ad30.c();
            if (com.huawei.openalliance.ad.utils.bg.a(c2)) {
                return arrayList2;
            }
            ArrayList arrayList3 = new ArrayList();
            Iterator<Content> it = c2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    arrayList = arrayList2;
                    break;
                }
                Content next = it.next();
                if (a(z2, z, z4)) {
                    arrayList = arrayList2;
                    z3 = true;
                    break;
                }
                ArrayList arrayList4 = arrayList2;
                if (!z2 && next.f() == 12) {
                    content = next;
                    z2 = true;
                } else if (!z && next.f() != 12 && next.J() == 0) {
                    content2 = next;
                    z = true;
                } else if (!z4 && next.f() != 12 && next.J() != 0) {
                    content3 = next;
                    z4 = true;
                }
                arrayList3.add(next.g());
                arrayList2 = arrayList4;
            }
            a(p().getContext().getApplicationContext(), arrayList3);
            i++;
            str = a2;
            str2 = g;
            c = list;
            arrayList2 = arrayList;
        }
        ho.b(z(), "linkedSupportMode:%s, firstNormal: %s, firstLink:%s, firstSpare: %s", Integer.valueOf(d()), content2, content, content3);
        String str3 = str2;
        a(content, content2, content3, adContentRsp, str, str3);
        return a(adContentRsp, str, content, content2, content3, str3);
    }

    @Override // com.huawei.openalliance.ad.qw
    public List<ContentRecord> a(AdContentRsp adContentRsp) {
        return pg.b(adContentRsp, this.n);
    }

    public boolean C() {
        return this.p;
    }

    @Override // com.huawei.openalliance.ad.jb
    public void B() {
        boolean ba = this.c.ba();
        ho.b(z(), "onAdFailToDisplay - finishSplashOnMinSlogan: %s reachMinSloganShowTime: %s sloganShowEnd: %s", Boolean.valueOf(ba), Boolean.valueOf(this.r), Boolean.valueOf(this.s));
        this.t = true;
        if ((ba && this.r) || this.s) {
            j();
        }
    }

    @Override // com.huawei.openalliance.ad.jb
    public void A() {
        ho.b(z(), "start");
        com.huawei.openalliance.ad.views.interfaces.l p = p();
        if (p == null) {
            c(-4);
            j();
        } else {
            m();
            com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.ja.1
                @Override // java.lang.Runnable
                public void run() {
                    ja.this.h();
                }
            });
            p.a(new hl() { // from class: com.huawei.openalliance.ad.ja.2
                @Override // com.huawei.openalliance.ad.hl
                public void b() {
                    com.huawei.openalliance.ad.utils.cv.a(new Runnable() { // from class: com.huawei.openalliance.ad.ja.2.2
                        @Override // java.lang.Runnable
                        public void run() {
                            ja.this.D();
                        }
                    });
                }

                @Override // com.huawei.openalliance.ad.hl
                public void a() {
                    com.huawei.openalliance.ad.utils.cv.a(new Runnable() { // from class: com.huawei.openalliance.ad.ja.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ja.this.E();
                        }
                    });
                }
            });
            n();
        }
    }

    private void h(int i) {
        c(i);
        j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ContentRecord b(AdContentRsp adContentRsp, String str, Content content, String str2) {
        if (content == null) {
            return null;
        }
        content.a(adContentRsp.h(), this.n);
        ContentRecord a2 = pf.a(str, content, this.n, str2);
        if (a2 != null) {
            a2.D(adContentRsp.p());
            a2.E(adContentRsp.q());
            a2.T(adContentRsp.u());
        }
        return a2;
    }

    private void a(final Content content, final Content content2, final Content content3, final AdContentRsp adContentRsp, final String str, final String str2) {
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.ja.4
            @Override // java.lang.Runnable
            public void run() {
                ArrayList arrayList = new ArrayList();
                arrayList.add(ja.this.b(adContentRsp, str, content, str2));
                arrayList.add(ja.this.b(adContentRsp, str, content2, str2));
                arrayList.add(ja.this.b(adContentRsp, str, content3, str2));
                ja jaVar = ja.this;
                jaVar.a(arrayList, jaVar.k().b(), 2);
            }
        });
    }

    private void a(final Context context, final List<String> list) {
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.ja.3
            @Override // java.lang.Runnable
            public void run() {
                dh.a(context, "normal").a(list, false);
                dh.a(context, "ar").a(list, false);
            }
        });
    }

    private List<ContentRecord> a(AdContentRsp adContentRsp, String str, Content content, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(b(adContentRsp, str, content, str2));
        return arrayList;
    }

    private List<ContentRecord> a(AdContentRsp adContentRsp, String str, Content content, Content content2, Content content3, String str2) {
        ContentRecord contentRecord;
        if (d() != 1 || content == null) {
            return a(adContentRsp, content3, content2, str, str2);
        }
        if (content2 != null) {
            content2.a(adContentRsp.h(), this.n);
            contentRecord = pf.a(str, content2, this.n, str2);
        } else {
            contentRecord = null;
        }
        dc.a(contentRecord);
        return a(adContentRsp, str, content, str2);
    }

    private List<ContentRecord> a(AdContentRsp adContentRsp, Content content, Content content2, String str, String str2) {
        dc.a((ContentRecord) null);
        if (content != null) {
            content.a(adContentRsp.h(), this.n);
            dc.b(pf.a(str, content, this.n, str2));
        } else {
            dc.b((ContentRecord) null);
        }
        if (content2 != null) {
            return a(adContentRsp, str, content2, str2);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E() {
        ho.b(z(), "doOnReachMinSloganShowTime, adHasShown:%s ", Boolean.valueOf(this.p));
        this.r = true;
        if (!this.p && this.u != null && !this.v) {
            ho.b(z(), "doOnReachMinSloganShowTime Ad has been loaded, but not shown yet");
            c(this.u);
            return;
        }
        boolean ba = this.c.ba();
        ho.b(z(), "doOnReachMinSloganShowTime finish splash: %s adFailToDisplay: %s", Boolean.valueOf(ba), Boolean.valueOf(this.t));
        if (ba && this.t) {
            ho.b(z(), "ad fail to load when reach min slogan show time");
            c(ErrorCode.ERROR_CODE_OTHER);
            j();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D() {
        ho.b(z(), "doOnShowSloganEnd, adHasShown:%s ", Boolean.valueOf(this.p));
        this.s = true;
        if (this.t) {
            ho.b(z(), "Ad fails to display or loading timeout, ad dismiss");
            c(ErrorCode.ERROR_CODE_OTHER);
            j();
            return;
        }
        if (this.p) {
            return;
        }
        ho.b(z(), "doOnShowSloganEnd Ad has been loaded, but not shown yet");
        if (!this.v) {
            if (this.u != null) {
                ho.b(z(), "show splash");
                c(this.u);
                return;
            } else {
                synchronized (this) {
                    if (!d(-2)) {
                        ho.b(z(), "no ad to display when slogan ends");
                        h(-2);
                    }
                }
                return;
            }
        }
        ContentRecord g = dc.g();
        this.u = g;
        if (g == null) {
            ho.b(z(), "linked loaded, do not call play");
            h(-6);
        } else {
            ho.b(z(), "linked loaded, display normal when slogan ends");
            c(this.u);
            dc.a((ContentRecord) null);
            e(1202);
        }
    }

    public ja(com.huawei.openalliance.ad.views.interfaces.l lVar) {
        super(lVar);
        this.q = hashCode();
        this.r = false;
        this.s = false;
        this.p = false;
        this.t = false;
        this.v = false;
    }
}
