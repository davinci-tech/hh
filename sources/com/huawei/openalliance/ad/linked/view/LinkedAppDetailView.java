package com.huawei.openalliance.ad.linked.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.openalliance.ad.analysis.h;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.dh;
import com.huawei.openalliance.ad.dk;
import com.huawei.openalliance.ad.download.app.AppStatus;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.gc;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.inter.listeners.INonwifiActionListener;
import com.huawei.openalliance.ad.rr;
import com.huawei.openalliance.ad.rt;
import com.huawei.openalliance.ad.ru;
import com.huawei.openalliance.ad.th;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.az;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.utils.m;
import com.huawei.openalliance.ad.views.AppDownloadButton;
import com.huawei.openalliance.ad.views.AppDownloadButtonStyle;
import com.huawei.openalliance.ad.views.ExtandAppDownloadButtonStyle;
import com.huawei.openalliance.ad.views.ExtandAppDownloadButtonStyleHm;
import com.huawei.openalliance.ad.views.interfaces.x;

/* loaded from: classes9.dex */
public class LinkedAppDetailView extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private Context f7158a;
    private AppDownloadButton b;
    private TextView c;
    private ImageView d;
    private AppInfo e;
    private gc f;
    private ContentRecord g;
    private AdLandingPageData h;
    private View i;
    private h j;
    private int k;
    private boolean l;
    private com.huawei.openalliance.ad.views.interfaces.c m;
    private INonwifiActionListener n;
    private boolean o;
    private View.OnClickListener p;
    private dk q;
    private MaterialClickInfo r;

    /* JADX INFO: Access modifiers changed from: private */
    public void setCancelDownloadButtonVisibility(AppStatus appStatus) {
    }

    public void setVideoCoverClickListener(View.OnClickListener onClickListener) {
        this.p = onClickListener;
    }

    public void setOnNonWifiDownloadListener(INonwifiActionListener iNonwifiActionListener) {
        this.n = iNonwifiActionListener;
    }

    public void setNeedPerBeforDownload(boolean z) {
        this.o = z;
    }

    public void setContentRecord(ContentRecord contentRecord) {
        String str;
        if (contentRecord == null) {
            return;
        }
        try {
            ho.b("LinkedPPSAppDetailView", "set ad landing data");
            this.g = contentRecord;
            AppInfo ae = contentRecord.ae();
            this.e = ae;
            if (ae == null) {
                ho.a("LinkedPPSAppDetailView", "appInfo is null, hide appDetailView");
                this.i.setVisibility(8);
            } else {
                c();
            }
        } catch (RuntimeException unused) {
            str = "setAdLandingPageData RuntimeException";
            ho.c("LinkedPPSAppDetailView", str);
        } catch (Exception unused2) {
            str = "setAdLandingPageData error";
            ho.c("LinkedPPSAppDetailView", str);
        }
    }

    public void setAppRelated(boolean z) {
        this.l = z;
        a();
    }

    public void setAppDetailClickListener(com.huawei.openalliance.ad.views.interfaces.c cVar) {
        this.m = cVar;
    }

    public void setAdLandingPageData(AdLandingPageData adLandingPageData) {
        this.h = adLandingPageData;
    }

    public AppDownloadButton getAppDownloadButton() {
        return this.b;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            int a2 = th.a(motionEvent);
            if (a2 == 0) {
                ho.a("LinkedPPSAppDetailView", "dispatchTouchEvent ACTION_DOWN");
                this.r = th.a(this, motionEvent);
            }
            if (1 == a2) {
                ho.a("LinkedPPSAppDetailView", "dispatchTouchEvent ACTION_UP");
                th.a(this, motionEvent, null, this.r);
                this.b.a(this.r);
            }
        } catch (Throwable th) {
            ho.c("LinkedPPSAppDetailView", "dispatchTouchEvent exception : %s", th.getClass().getSimpleName());
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void a(String str) {
        ContentRecord contentRecord = this.g;
        if (contentRecord != null) {
            contentRecord.c(str);
        }
        AppDownloadButton appDownloadButton = this.b;
        if (appDownloadButton != null) {
            appDownloadButton.updateContent(str);
        }
    }

    public void a() {
        AppDownloadButton appDownloadButton = this.b;
        if (appDownloadButton != null) {
            appDownloadButton.setClickable(false);
        }
    }

    private void c() {
        AppDownloadButton appDownloadButton;
        AppDownloadButtonStyle extandAppDownloadButtonStyle;
        a(this.c, this.e.getAppName());
        a(this.d, this.e.getIconUrl());
        this.b.setAdLandingPageData(this.h);
        b();
        this.b.setNeedShowPermision(this.o);
        if (bz.a(this.f7158a).g()) {
            appDownloadButton = this.b;
            extandAppDownloadButtonStyle = new ExtandAppDownloadButtonStyleHm(this.f7158a);
        } else {
            appDownloadButton = this.b;
            extandAppDownloadButtonStyle = new ExtandAppDownloadButtonStyle(this.f7158a);
        }
        appDownloadButton.setAppDownloadButtonStyle(extandAppDownloadButtonStyle);
        this.b.setOnDownloadStatusChangedListener(new AppDownloadButton.OnDownloadStatusChangedListener() { // from class: com.huawei.openalliance.ad.linked.view.LinkedAppDetailView.2
            @Override // com.huawei.openalliance.ad.views.AppDownloadButton.OnDownloadStatusChangedListener
            public void onUserCancel(AppInfo appInfo) {
            }

            @Override // com.huawei.openalliance.ad.views.AppDownloadButton.OnDownloadStatusChangedListener
            public void onStatusChanged(AppStatus appStatus) {
                LinkedAppDetailView.this.setCancelDownloadButtonVisibility(appStatus);
            }
        });
        this.b.setButtonTextWatcherInner(new AppDownloadButton.e() { // from class: com.huawei.openalliance.ad.linked.view.LinkedAppDetailView.3
            @Override // com.huawei.openalliance.ad.views.AppDownloadButton.e
            public CharSequence a(CharSequence charSequence, AppStatus appStatus) {
                return !LinkedAppDetailView.this.l ? LinkedAppDetailView.this.f7158a.getString(R.string._2130851084_res_0x7f02350c) : charSequence;
            }
        });
        this.b.setOnNonWifiDownloadListener(new AppDownloadButton.OnNonWifiDownloadListener() { // from class: com.huawei.openalliance.ad.linked.view.LinkedAppDetailView.4
            @Override // com.huawei.openalliance.ad.views.AppDownloadButton.OnNonWifiDownloadListener
            public boolean onNonWifiDownload(AppInfo appInfo, long j) {
                if (LinkedAppDetailView.this.n == null || !LinkedAppDetailView.this.n.onAppDownload(appInfo, j)) {
                    LinkedAppDetailView.this.b.b();
                    return false;
                }
                LinkedAppDetailView.this.b.setAllowedNonWifiNetwork(true);
                return true;
            }
        });
        this.b.setSource(11);
        setCancelDownloadButtonVisibility(this.b.refreshStatus());
    }

    private void b() {
        this.b.setSource(11);
        this.b.setLinkedCoverClickListener(this.p);
        if (this.l) {
            this.b.setClickActionListener(new x() { // from class: com.huawei.openalliance.ad.linked.view.LinkedAppDetailView.1
                @Override // com.huawei.openalliance.ad.views.interfaces.x
                public void b(AppDownloadButton appDownloadButton) {
                    if (LinkedAppDetailView.this.m != null) {
                        String str = AppStatus.INSTALLED == appDownloadButton.getStatus() ? "app" : "";
                        LinkedAppDetailView.this.j.a(0, 0, LinkedAppDetailView.this.g);
                        LinkedAppDetailView.this.m.a(new com.huawei.openalliance.ad.views.interfaces.b(LinkedAppDetailView.this.l, true, str));
                    }
                }

                @Override // com.huawei.openalliance.ad.views.interfaces.x
                public void a(AppDownloadButton appDownloadButton) {
                    if (LinkedAppDetailView.this.m != null) {
                        LinkedAppDetailView.this.m.a(new com.huawei.openalliance.ad.views.interfaces.b(LinkedAppDetailView.this.l, false, AppStatus.INSTALLED == appDownloadButton.getStatus() ? "app" : ""));
                    }
                }
            });
        }
    }

    private void a(TextView textView, String str) {
        int i;
        if (TextUtils.isEmpty(str)) {
            i = 8;
        } else {
            textView.setText(str);
            i = 0;
        }
        textView.setVisibility(i);
    }

    private void a(ImageView imageView, String str) {
        if (TextUtils.isEmpty(str) || imageView == null) {
            return;
        }
        ho.b("LinkedPPSAppDetailView", "load app icon:" + cz.f(str));
        m.d(new AnonymousClass5(str, imageView));
    }

    private void a(Context context) {
        String str;
        try {
            this.f7158a = context;
            this.f = fh.b(context);
            this.j = new h(context);
            this.k = ViewConfiguration.get(context).getScaledTouchSlop();
            this.i = inflate(context, R.layout.hiad_linked_app_detail, this);
            this.c = (TextView) findViewById(R.id.linked_app_name);
            this.d = (ImageView) findViewById(R.id.linked_app_icon);
            this.b = (AppDownloadButton) findViewById(R.id.linked_app_download_btn);
            this.q = dh.a(context, "normal");
            if (ao.n(context)) {
                this.c.setTextSize(1, 24.0f);
            }
        } catch (RuntimeException unused) {
            str = "init RuntimeException";
            ho.c("LinkedPPSAppDetailView", str);
        } catch (Exception unused2) {
            str = "init error";
            ho.c("LinkedPPSAppDetailView", str);
        }
    }

    /* renamed from: com.huawei.openalliance.ad.linked.view.LinkedAppDetailView$5, reason: invalid class name */
    class AnonymousClass5 implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f7163a;
        final /* synthetic */ ImageView b;

        @Override // java.lang.Runnable
        public void run() {
            rt rtVar = new rt();
            rtVar.b(false);
            rtVar.c(true);
            rtVar.a("icon");
            rtVar.c(this.f7163a);
            if (!LinkedAppDetailView.this.l) {
                rtVar.c(fh.b(LinkedAppDetailView.this.f7158a).e());
            }
            ru a2 = new rr(LinkedAppDetailView.this.f7158a, rtVar).a();
            if (a2 != null) {
                String a3 = a2.a();
                if (TextUtils.isEmpty(a3)) {
                    return;
                }
                String c = LinkedAppDetailView.this.q.c(a3);
                if (TextUtils.isEmpty(c)) {
                    return;
                }
                az.a(LinkedAppDetailView.this.f7158a, c, new az.a() { // from class: com.huawei.openalliance.ad.linked.view.LinkedAppDetailView.5.1
                    @Override // com.huawei.openalliance.ad.utils.az.a
                    public void a() {
                    }

                    @Override // com.huawei.openalliance.ad.utils.az.a
                    public void a(final Drawable drawable) {
                        if (drawable != null) {
                            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.linked.view.LinkedAppDetailView.5.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    AnonymousClass5.this.b.setBackground(null);
                                    AnonymousClass5.this.b.setImageDrawable(drawable);
                                }
                            });
                        }
                    }
                });
            }
        }

        AnonymousClass5(String str, ImageView imageView) {
            this.f7163a = str;
            this.b = imageView;
        }
    }

    public LinkedAppDetailView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.l = true;
        this.o = false;
        a(context);
    }

    public LinkedAppDetailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l = true;
        this.o = false;
        a(context);
    }

    public LinkedAppDetailView(Context context) {
        super(context);
        this.l = true;
        this.o = false;
        a(context);
    }
}
