package com.huawei.openalliance.ad.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.dh;
import com.huawei.openalliance.ad.download.app.AppStatus;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.inter.listeners.AppDownloadListener;
import com.huawei.openalliance.ad.rr;
import com.huawei.openalliance.ad.rt;
import com.huawei.openalliance.ad.ru;
import com.huawei.openalliance.ad.th;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.az;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dj;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class t extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private c f8146a;
    private Context b;
    private AdLandingPageData c;
    private AppInfo d;
    private String e;
    private View f;
    private TextView g;
    private ImageView h;
    private SixElementsView i;
    private TextView j;
    private com.huawei.openalliance.ad.views.interfaces.aa k;
    private AlertDialog l;
    private MaterialClickInfo m;
    private a n;

    public interface a {
        void a();
    }

    public void setPopUpClickListener(com.huawei.openalliance.ad.views.interfaces.aa aaVar) {
        this.k = aaVar;
    }

    public void setDismissListener(a aVar) {
        this.n = aVar;
    }

    public void setAdPopupData(AdLandingPageData adLandingPageData) {
        String str;
        if (adLandingPageData == null) {
            return;
        }
        try {
            ho.b("PPSRewardPopUpView", "set popup data");
            this.c = adLandingPageData;
            AppInfo appInfo = adLandingPageData.getAppInfo();
            this.d = appInfo;
            if (appInfo != null && "11".equals(appInfo.getPriorInstallWay()) && this.g != null && this.j != null) {
                this.g.setText(this.d.A() == 1 ? R.string._2130851130_res_0x7f02353a : R.string._2130851081_res_0x7f023509);
                this.j.setText(R.string._2130851128_res_0x7f023538);
            }
            e();
        } catch (RuntimeException unused) {
            str = "setAdPopupData RuntimeException.";
            ho.c("PPSRewardPopUpView", str);
        } catch (Exception unused2) {
            str = "setAdPopupData error.";
            ho.c("PPSRewardPopUpView", str);
        }
    }

    public AlertDialog getDialog() {
        return this.l;
    }

    public MaterialClickInfo getClickInfo() {
        return this.m;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            int a2 = th.a(motionEvent);
            if (a2 == 0) {
                this.m = th.a(this, motionEvent);
            }
            if (1 == a2) {
                th.a(this, motionEvent, null, this.m);
                SixElementsView sixElementsView = this.i;
                if (sixElementsView != null) {
                    sixElementsView.setOrgClickInfo(this.m);
                }
            }
        } catch (Throwable th) {
            ho.c("PPSRewardPopUpView", "dispatchTouchEvent exception : %s", th.getClass().getSimpleName());
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void c() {
        com.huawei.openalliance.ad.download.app.d.a().c(this.f8146a);
    }

    public void b() {
        if (this.f == null || this.l == null) {
            return;
        }
        ho.b("PPSRewardPopUpView", "Dialog has been dismissed");
        if (this.l.isShowing()) {
            this.l.dismiss();
        }
        this.l = null;
    }

    public boolean a() {
        AlertDialog alertDialog;
        if (!d()) {
            return false;
        }
        a(this.h, this.e);
        if (this.f == null || (alertDialog = this.l) == null) {
            ho.c("PPSRewardPopUpView", "rootView or dialog is null");
            return false;
        }
        alertDialog.show();
        SixElementsView sixElementsView = this.i;
        if (sixElementsView == null) {
            return true;
        }
        sixElementsView.a(false);
        return true;
    }

    private void f() {
        AlertDialog create = com.huawei.openalliance.ad.utils.y.a(this.b).create();
        this.l = create;
        create.setView(this.f);
        this.l.setCanceledOnTouchOutside(false);
        this.l.getWindow().setDimAmount(0.2f);
    }

    private void e() {
        ho.b("PPSRewardPopUpView", "refresh UI");
        this.i.a(this.c);
        this.e = this.d.getIconUrl();
        if (ao.n(this.b)) {
            this.g.setTextSize(1, 30.0f);
            this.j.setTextSize(1, 30.0f);
        }
        this.g.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.t.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                t.this.k.a();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.j.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.t.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                t.this.k.b();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private boolean d() {
        String str;
        Context context = this.b;
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (!activity.isFinishing() && !activity.isDestroyed()) {
                return true;
            }
            str = "can't show dialog due to activity status!";
        } else {
            str = "context not activity";
        }
        ho.b("PPSRewardPopUpView", str);
        return false;
    }

    static class c implements AppDownloadListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<t> f8153a;

        @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListener
        public void onAppOpen(AppInfo appInfo) {
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListener
        public void onAppOpen(String str) {
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListener
        public void onDownloadProgress(AppInfo appInfo, int i) {
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListener
        public void onUserCancel(AppInfo appInfo) {
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.AppDownloadListener
        public void onStatusChanged(AppStatus appStatus, AppInfo appInfo) {
            t tVar = this.f8153a.get();
            if (ho.a()) {
                ho.a("PopupStatusListener", "onStatusChange, status: %s", appStatus);
            }
            if (tVar == null || tVar.l == null || appStatus == AppStatus.DOWNLOAD) {
                return;
            }
            ho.b("PopupStatusListener", "download start, dismissView");
            if (tVar.l.isShowing() && tVar.n != null) {
                ho.b("PopupStatusListener", "download trigger dismissView");
                tVar.n.a();
            }
            tVar.b();
        }

        public c(t tVar) {
            this.f8153a = new WeakReference<>(tVar);
        }
    }

    private void a(ImageView imageView, String str) {
        if (TextUtils.isEmpty(str) || imageView == null) {
            return;
        }
        ho.b("PPSRewardPopUpView", "load app icon:" + cz.f(str));
        com.huawei.openalliance.ad.utils.m.d(new b(this.b, imageView, str));
    }

    static class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        String f8150a;
        Context b;
        private ImageView c;

        @Override // java.lang.Runnable
        public void run() {
            if (this.b == null) {
                ho.c("PPSRewardPopUpView", "para is null");
                return;
            }
            rt rtVar = new rt();
            rtVar.b(false);
            rtVar.c(true);
            rtVar.a("icon");
            rtVar.c(this.f8150a);
            ru a2 = new rr(this.b, rtVar).a();
            if (a2 != null) {
                String a3 = a2.a();
                if (TextUtils.isEmpty(a3)) {
                    return;
                }
                String c = dh.a(this.b, "normal").c(a3);
                if (TextUtils.isEmpty(c)) {
                    return;
                }
                az.a(this.b, c, new az.a() { // from class: com.huawei.openalliance.ad.views.t.b.1
                    @Override // com.huawei.openalliance.ad.utils.az.a
                    public void a() {
                    }

                    @Override // com.huawei.openalliance.ad.utils.az.a
                    public void a(final Drawable drawable) {
                        if (drawable != null) {
                            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.t.b.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    b.this.c.setImageDrawable(drawable);
                                }
                            });
                        }
                    }
                });
            }
        }

        public b(Context context, ImageView imageView, String str) {
            this.b = context == null ? null : context.getApplicationContext();
            this.c = imageView;
            this.f8150a = str;
        }
    }

    public static void a(Context context, String str, ContentRecord contentRecord) {
        ho.b("PPSRewardPopUpView", "report Type is " + str);
        new com.huawei.openalliance.ad.analysis.h(context).d(contentRecord, str);
    }

    private void a(Context context, int i) {
        this.b = context;
        View inflate = inflate(context, R.layout.hiad_reward_popup, this);
        this.f = inflate;
        inflate.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.t.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (t.this.k != null) {
                    t.this.k.c();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.h = (ImageView) this.f.findViewById(R.id.popup_icon);
        this.i = (SixElementsView) this.f.findViewById(R.id.popup_icon_six_elements);
        this.g = (TextView) this.f.findViewById(R.id.popup_download_btn);
        TextView textView = (TextView) this.f.findViewById(R.id.abort_downlaod_btn);
        this.j = textView;
        textView.setEllipsize(TextUtils.TruncateAt.END);
        f();
        this.f8146a = new c(this);
        com.huawei.openalliance.ad.download.app.d.a().b(this.f8146a);
    }

    public t(Context context, int i) {
        super(context);
        a(context, i);
    }
}
