package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.beans.metadata.ImageInfo;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.co;
import com.huawei.openalliance.ad.constant.ClickDestination;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.download.app.AppStatus;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.gc;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.inter.listeners.INonwifiActionListener;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.vc;
import java.util.List;

/* loaded from: classes5.dex */
public class s extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private Context f8140a;
    private gc b;
    private AdLandingPageData c;
    private AppInfo d;
    private String e;
    private MetaData f;
    private View g;
    private AppDownloadButton h;
    private INonwifiActionListener i;
    private ImageView j;
    private TextView k;
    private TextView l;
    private boolean m;
    private String n;
    private com.huawei.openalliance.ad.views.interfaces.d o;
    private co p;
    private int q;
    private boolean r;
    private SixElementsView s;
    private ContentRecord t;
    private boolean u;
    private boolean v;
    private View.OnTouchListener w;

    public void setOnNonWifiDownloadListener(INonwifiActionListener iNonwifiActionListener) {
        this.i = iNonwifiActionListener;
    }

    public void setInterType(int i) {
        this.q = i;
    }

    public void setEndCardClickListener(com.huawei.openalliance.ad.views.interfaces.d dVar) {
        this.o = dVar;
    }

    public void setButtonAndSixElementsClickInfo(MaterialClickInfo materialClickInfo) {
        AppDownloadButton appDownloadButton = this.h;
        if (appDownloadButton != null) {
            appDownloadButton.a(materialClickInfo);
        }
        SixElementsView sixElementsView = this.s;
        if (sixElementsView != null) {
            sixElementsView.setOrgClickInfo(materialClickInfo);
        }
    }

    public void setAppRelated(boolean z) {
        this.m = z;
        c();
    }

    public void setAdLandingPageData(AdLandingPageData adLandingPageData) {
        String str;
        if (adLandingPageData == null) {
            return;
        }
        try {
            ho.b("PPSRewardEndCardView", "set ad landing data.");
            this.c = adLandingPageData;
            this.d = adLandingPageData.getAppInfo();
            MetaData metaData = (MetaData) be.b(this.c.s(), MetaData.class, new Class[0]);
            this.f = metaData;
            if (metaData != null) {
                this.n = cz.c(metaData.a());
            }
            this.r = adLandingPageData.z();
            e();
        } catch (RuntimeException unused) {
            str = "setAdLandingPageData RuntimeException.";
            ho.c("PPSRewardEndCardView", str);
        } catch (Exception unused2) {
            str = "setAdLandingPageDate error.";
            ho.c("PPSRewardEndCardView", str);
        }
    }

    public void d() {
        AppDownloadButton appDownloadButton = this.h;
        if (appDownloadButton != null) {
            appDownloadButton.performClick();
        }
    }

    public void c() {
        AppDownloadButton appDownloadButton = this.h;
        if (appDownloadButton != null) {
            appDownloadButton.setClickable(false);
        }
    }

    public void b() {
        View view = this.g;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    public void a(String str) {
        AppDownloadButton appDownloadButton = this.h;
        if (appDownloadButton != null) {
            appDownloadButton.updateContent(str);
        }
    }

    public void a(long j) {
        AppDownloadButton appDownloadButton = this.h;
        if (appDownloadButton != null) {
            appDownloadButton.updateStartShowTime(j);
        }
    }

    public void a() {
        a(this.j, this.e);
        View view = this.g;
        if (view != null) {
            view.setVisibility(0);
        }
        SixElementsView sixElementsView = this.s;
        if (sixElementsView != null) {
            sixElementsView.a(this.v);
        }
    }

    private String getImageUrl() {
        MetaData metaData = this.f;
        if (metaData == null) {
            return null;
        }
        List<ImageInfo> g = metaData.g();
        if (bg.a(g) || TextUtils.isEmpty(g.get(0).c())) {
            g = this.f.o();
            if (bg.a(g)) {
                return null;
            }
        }
        return g.get(0).c();
    }

    private void g() {
        if (this.m) {
            this.h.setClickActionListener(new com.huawei.openalliance.ad.views.interfaces.x() { // from class: com.huawei.openalliance.ad.views.s.4
                @Override // com.huawei.openalliance.ad.views.interfaces.x
                public void b(AppDownloadButton appDownloadButton) {
                    if (s.this.o != null) {
                        s.this.o.a(s.this.m, true, AppStatus.INSTALLED == appDownloadButton.getStatus() ? "app" : "", true);
                    }
                }

                @Override // com.huawei.openalliance.ad.views.interfaces.x
                public void a(AppDownloadButton appDownloadButton) {
                    if (s.this.o != null) {
                        String str = s.this.q == 9 ? ClickDestination.HARMONY_SERVICE : ClickDestination.WEB;
                        if (AppStatus.INSTALLED == appDownloadButton.getStatus()) {
                            str = "app";
                        }
                        s.this.o.a(s.this.m, false, str, true);
                    }
                }
            });
        } else {
            this.h.a(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.s.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (s.this.o != null) {
                        s.this.o.a(false, false, s.this.q == 9 ? ClickDestination.HARMONY_SERVICE : ClickDestination.WEB, false);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    private boolean f() {
        int B = this.t.B();
        AppInfo ae = this.t.ae();
        if (B == 0 || B == 1 || B == 2) {
            return false;
        }
        if ((B == 3 || B == 5) && ae != null && !com.huawei.openalliance.ad.utils.i.a(getContext(), ae.getPackageName())) {
            return false;
        }
        this.v = true;
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0037, code lost:
    
        if (android.text.TextUtils.isEmpty(r0) != false) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void e() {
        /*
            r3 = this;
            boolean r0 = r3.m
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "PPSRewardEndCardView"
            java.lang.String r2 = "refresh UI, isAppRelated: %s"
            com.huawei.openalliance.ad.ho.b(r1, r2, r0)
            boolean r0 = r3.m
            if (r0 == 0) goto L3a
            android.widget.TextView r0 = r3.k
            com.huawei.openalliance.ad.inter.data.AppInfo r1 = r3.d
            java.lang.String r1 = r1.getAppName()
            r3.a(r0, r1)
            android.widget.TextView r0 = r3.l
            com.huawei.openalliance.ad.inter.data.AppInfo r1 = r3.d
            java.lang.String r1 = r1.getAppDesc()
            r3.a(r0, r1)
            com.huawei.openalliance.ad.inter.data.AppInfo r0 = r3.d
            java.lang.String r0 = r0.getIconUrl()
            r3.e = r0
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L60
            goto L5a
        L3a:
            com.huawei.openalliance.ad.beans.metadata.MetaData r0 = r3.f
            if (r0 == 0) goto L5a
            android.widget.TextView r1 = r3.k
            java.lang.String r0 = r0.e()
            java.lang.String r0 = com.huawei.openalliance.ad.utils.cz.c(r0)
            r3.a(r1, r0)
            android.widget.TextView r0 = r3.l
            com.huawei.openalliance.ad.beans.metadata.MetaData r1 = r3.f
            java.lang.String r1 = r1.f()
            java.lang.String r1 = com.huawei.openalliance.ad.utils.cz.c(r1)
            r3.a(r0, r1)
        L5a:
            java.lang.String r0 = r3.getImageUrl()
            r3.e = r0
        L60:
            com.huawei.openalliance.ad.views.SixElementsView r0 = r3.s
            r1 = 8
            r0.setTitleTextVisibility(r1)
            com.huawei.openalliance.ad.views.SixElementsView r0 = r3.s
            com.huawei.openalliance.ad.inter.data.AdLandingPageData r1 = r3.c
            r0.a(r1)
            android.view.View r0 = r3.g
            android.view.View$OnTouchListener r1 = r3.w
            r0.setOnTouchListener(r1)
            com.huawei.openalliance.ad.views.AppDownloadButton r0 = r3.h
            com.huawei.openalliance.ad.inter.data.AdLandingPageData r1 = r3.c
            r0.setAdLandingPageData(r1)
            com.huawei.openalliance.ad.co r0 = r3.p
            boolean r0 = r0.g()
            if (r0 == 0) goto L8e
            com.huawei.openalliance.ad.views.AppDownloadButton r0 = r3.h
            com.huawei.openalliance.ad.views.ExtandAppDownloadButtonStyleHm r1 = new com.huawei.openalliance.ad.views.ExtandAppDownloadButtonStyleHm
            android.content.Context r2 = r3.f8140a
            r1.<init>(r2)
            goto L97
        L8e:
            com.huawei.openalliance.ad.views.AppDownloadButton r0 = r3.h
            com.huawei.openalliance.ad.views.ExtandAppDownloadButtonStyle r1 = new com.huawei.openalliance.ad.views.ExtandAppDownloadButtonStyle
            android.content.Context r2 = r3.f8140a
            r1.<init>(r2)
        L97:
            r0.setAppDownloadButtonStyle(r1)
            com.huawei.openalliance.ad.views.AppDownloadButton r0 = r3.h
            com.huawei.openalliance.ad.views.s$1 r1 = new com.huawei.openalliance.ad.views.s$1
            r1.<init>()
            r0.setButtonTextWatcherInner(r1)
            com.huawei.openalliance.ad.views.AppDownloadButton r0 = r3.h
            com.huawei.openalliance.ad.views.s$2 r1 = new com.huawei.openalliance.ad.views.s$2
            r1.<init>()
            r0.setOnNonWifiDownloadListener(r1)
            com.huawei.openalliance.ad.views.AppDownloadButton r0 = r3.h
            r1 = 5
            r0.setSource(r1)
            r3.g()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.views.s.e():void");
    }

    private void a(TextView textView, String str) {
        if (TextUtils.isEmpty(str)) {
            textView.setVisibility(8);
        } else {
            textView.setText(str);
        }
    }

    private void a(ImageView imageView, String str) {
        if (TextUtils.isEmpty(str) || imageView == null) {
            return;
        }
        ho.b("PPSRewardEndCardView", "load app icon: %s", cz.f(str));
        com.huawei.openalliance.ad.utils.m.d(new vc(this.f8140a, str, imageView, Boolean.valueOf(this.m)));
    }

    private void a(Context context, int i, ContentRecord contentRecord) {
        View inflate;
        int i2;
        this.f8140a = context;
        this.t = contentRecord;
        this.b = fh.b(context);
        boolean d = bz.a(context).d();
        this.u = d;
        if (d && f()) {
            inflate = inflate(context, 1 == i ? R.layout.hiad_reward_endcard : R.layout.hiad_reward_land_endcard, this);
            this.g = inflate;
            i2 = R.id.reward_end_card_six_elements_with_jump;
        } else {
            inflate = inflate(context, 1 == i ? R.layout.hiad_reward_six_elements_endcard : R.layout.hiad_reward_six_elements_land_endcard, this);
            this.g = inflate;
            i2 = R.id.reward_end_card_six_elements;
        }
        this.s = (SixElementsView) inflate.findViewById(i2);
        this.j = (ImageView) this.g.findViewById(R.id.endcard_icon);
        this.k = (TextView) this.g.findViewById(R.id.endcard_title);
        this.l = (TextView) this.g.findViewById(R.id.endcard_desc);
        this.h = (AppDownloadButton) this.g.findViewById(R.id.endcard_download_btn);
        if (ao.n(context)) {
            this.k.setTextSize(1, 36.0f);
            this.l.setTextSize(1, 28.0f);
        }
        this.p = bz.a(this.f8140a.getApplicationContext());
    }

    public s(Context context, int i, ContentRecord contentRecord) {
        super(context);
        this.m = true;
        this.r = true;
        this.u = true;
        this.v = false;
        this.w = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.s.5
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ho.a("PPSRewardEndCardView", "action:%s", Integer.valueOf(motionEvent.getAction()));
                if (s.this.h != null && motionEvent.getAction() == 1) {
                    if (!s.this.m) {
                        if (s.this.o != null) {
                            s.this.o.a(false, false, ClickDestination.WEB, false);
                        }
                    } else {
                        s.this.o.a(s.this.m, true, AppStatus.INSTALLED == s.this.h.getStatus() ? "app" : "", false);
                    }
                }
                return true;
            }
        };
        a(context, i, contentRecord);
    }
}
