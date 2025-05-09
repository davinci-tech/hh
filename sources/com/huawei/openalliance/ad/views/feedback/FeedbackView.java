package com.huawei.openalliance.ad.views.feedback;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.FeedbackInfo;
import com.huawei.openalliance.ad.utils.Cdo;
import com.huawei.openalliance.ad.utils.ad;
import com.huawei.openalliance.ad.utils.az;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.views.h;
import java.util.List;

/* loaded from: classes5.dex */
public class FeedbackView extends com.huawei.openalliance.ad.views.h implements g, h {
    private LinearLayout k;
    private LinearLayout l;
    private FlowLayoutView m;
    private FlowLayoutView n;
    private ViewStub o;
    private com.huawei.openalliance.ad.views.feedback.b p;
    private boolean q;
    private boolean r;
    private d s;
    private a t;
    private c u;

    @Override // com.huawei.openalliance.ad.views.h
    public void setScreenLockCallBack(h.a aVar) {
        this.t.a(aVar);
    }

    @Override // com.huawei.openalliance.ad.views.h
    public void setFeedbackListener(com.huawei.openalliance.ad.views.feedback.b bVar) {
        this.p = bVar;
    }

    @Override // com.huawei.openalliance.ad.views.h
    public void setAdContent(ContentRecord contentRecord) {
        d dVar = this.s;
        if (dVar != null) {
            dVar.a(getContext().getApplicationContext(), contentRecord, this);
        }
    }

    @Override // com.huawei.openalliance.ad.views.h
    public void e() {
        d dVar = this.s;
        if (dVar == null) {
            ho.b("FeedbackView", "feedback presenter is null");
            return;
        }
        boolean f = dVar.f();
        if (ho.a()) {
            ho.a("FeedbackView", "click to complain: %s", Boolean.valueOf(f));
        }
    }

    @Override // com.huawei.openalliance.ad.views.h
    public void c() {
        try {
            this.r = bz.a(getContext()).d();
            ho.b("FeedbackView", "adapterView mFeedbackViewPaddingLeft = %s, mFeedbackViewPaddingRight= %s", Integer.valueOf(this.g), Integer.valueOf(this.h));
            if (d()) {
                this.b.setPadding(this.g, 0, this.h, 0);
                d dVar = this.s;
                if (dVar != null) {
                    List<FeedbackInfo> a2 = dVar.a();
                    List<FeedbackInfo> b2 = this.s.b();
                    FeedbackInfo c2 = this.s.c();
                    if (ad.a(a2)) {
                        Cdo.a((View) this.k, true);
                        a(this.m, a2, 2);
                    } else {
                        Cdo.a((View) this.k, false);
                    }
                    if (ad.a(b2)) {
                        Cdo.a((View) this.l, true);
                        a(this.n, b2, 1);
                    } else {
                        Cdo.a((View) this.l, false);
                    }
                    a(c2);
                }
                this.b.requestLayout();
                this.b.getViewTreeObserver().addOnGlobalLayoutListener(this.j);
            }
        } catch (Throwable th) {
            ho.c("FeedbackView", "adapterView error, %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.views.h
    public void b(Context context) {
        boolean d = bz.a(context).d();
        this.r = d;
        ho.a("FeedbackView", "isChinaRom = %s", Boolean.valueOf(d));
        ViewStub viewStub = this.o;
        if (viewStub == null) {
            return;
        }
        viewStub.setLayoutResource(R.layout.hiad_feedback_viewstub);
        this.o.inflate();
        ImageView imageView = (ImageView) this.f8087a.findViewById(R.id.right_arrow);
        ImageView imageView2 = (ImageView) this.f8087a.findViewById(R.id.dsa_right_arrow);
        setArrowBitMap(imageView);
        setArrowBitMap(imageView2);
    }

    @Override // com.huawei.openalliance.ad.views.feedback.h
    public void b() {
        Context context = getContext();
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }

    @Override // com.huawei.openalliance.ad.views.h
    public void a(Context context) {
        try {
            this.f8087a = LayoutInflater.from(context).inflate(R.layout.hiad_feedback_view, this);
            this.k = (LinearLayout) this.f8087a.findViewById(R.id.feedback_positive_ll);
            this.l = (LinearLayout) this.f8087a.findViewById(R.id.feedback_negative_ll);
            this.b = this.f8087a.findViewById(R.id.feedback_view_root);
            this.c = this.f8087a.findViewById(R.id.feedback_scrollview);
            this.m = (FlowLayoutView) this.f8087a.findViewById(R.id.feedback_positive_flv);
            this.n = (FlowLayoutView) this.f8087a.findViewById(R.id.feedback_negative_flv);
            this.o = (ViewStub) this.f8087a.findViewById(R.id.feedback_viewstub);
            this.s = new d(this);
            this.t = new a(getContext());
            this.u = new c(getContext());
            this.t.a(this.s);
            this.u.a(this.s);
        } catch (Throwable th) {
            ho.c("FeedbackView", "initView error, %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.views.feedback.h
    public void a() {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.feedback.FeedbackView.2
            @Override // java.lang.Runnable
            public void run() {
                if (FeedbackView.this.p != null) {
                    FeedbackView.this.p.a(3, FeedbackView.this.s.c());
                }
            }
        });
    }

    private void setArrowBitMap(ImageView imageView) {
        if (imageView != null) {
            Drawable drawable = getResources().getDrawable(R.drawable._2131428545_res_0x7f0b04c1);
            if (dd.c()) {
                imageView.setImageBitmap(az.b(drawable));
            }
        }
    }

    private boolean f() {
        d dVar = this.s;
        return (dVar == null || !dVar.d() || cz.b(this.s.e())) ? false : true;
    }

    private void a(FlowLayoutView flowLayoutView, List<FeedbackInfo> list, final int i) {
        flowLayoutView.removeAllViews();
        if (bg.a(list)) {
            ho.b("FeedbackView", "feedbackInfoList is null");
            return;
        }
        ho.b("FeedbackView", "initFlowLayout, feedType: %s, feedbackList.size: %s", Integer.valueOf(i), Integer.valueOf(list.size()));
        for (final FeedbackInfo feedbackInfo : list) {
            if (feedbackInfo != null && !TextUtils.isEmpty(feedbackInfo.getLabel())) {
                String label = feedbackInfo.getLabel();
                View inflate = LayoutInflater.from(getContext()).inflate(R.layout.hiad_unlike_label_item, (ViewGroup) flowLayoutView, false);
                if (inflate instanceof TextView) {
                    TextView textView = (TextView) inflate;
                    textView.setText(label);
                    textView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.feedback.FeedbackView.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            try {
                            } catch (Throwable th) {
                                ho.c("FeedbackView", "onClick error, %s", th.getClass().getSimpleName());
                            }
                            if (!FeedbackView.this.q) {
                                ViewClickInstrumentation.clickOnView(view);
                                return;
                            }
                            FeedbackView.this.q = false;
                            view.setSelected(!view.isSelected());
                            view.postDelayed(new Runnable() { // from class: com.huawei.openalliance.ad.views.feedback.FeedbackView.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    FeedbackView.this.q = true;
                                    FeedbackView.this.a(i, feedbackInfo);
                                }
                            }, 200L);
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    });
                    flowLayoutView.addView(textView);
                }
            }
        }
        flowLayoutView.setDefaultDisplayMode(dd.c() ? -1 : 1);
    }

    private void a(FeedbackInfo feedbackInfo) {
        View findViewById = this.f8087a.findViewById(R.id.complain_extra_area);
        View findViewById2 = this.f8087a.findViewById(R.id.dsa_extra_area);
        if (this.r) {
            if (findViewById != null) {
                if (feedbackInfo == null || !feedbackInfo.b()) {
                    findViewById.setVisibility(8);
                } else {
                    findViewById.setVisibility(0);
                    ((TextView) this.f8087a.findViewById(R.id.complain_tv)).setText(feedbackInfo.getLabel());
                    findViewById.setOnClickListener(this.t);
                }
            }
            if (findViewById2 == null) {
                return;
            }
            if (!f()) {
                findViewById2.setVisibility(8);
                return;
            }
        } else {
            if (findViewById != null) {
                findViewById.setVisibility(8);
            }
            if (findViewById2 == null) {
                return;
            }
        }
        findViewById2.setVisibility(0);
        findViewById2.setOnClickListener(this.u);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, FeedbackInfo feedbackInfo) {
        com.huawei.openalliance.ad.views.feedback.b bVar = this.p;
        if (bVar != null) {
            bVar.a(i, feedbackInfo);
        }
    }

    protected static class a extends b {
        private h.a c;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f8067a == null) {
                ho.b("FeedbackView", "feedback presenter is null.");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (dd.b(this.b)) {
                this.c.l();
            } else {
                boolean f = this.f8067a.f();
                if (ho.a()) {
                    ho.a("FeedbackView", "click to complain: %s", Boolean.valueOf(f));
                }
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        public void a(h.a aVar) {
            this.c = aVar;
        }

        protected a(Context context) {
            super(context);
        }
    }

    public FeedbackView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.q = true;
        this.r = true;
    }

    static abstract class b implements View.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        protected d f8067a;
        protected final Context b;

        public void a(d dVar) {
            this.f8067a = dVar;
        }

        protected b(Context context) {
            this.b = context;
        }
    }

    protected static class c extends b {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f8067a == null) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            boolean a2 = this.f8067a.a(this.b);
            ho.a("FeedbackView", "click to why this ad:%s", Boolean.valueOf(a2));
            if (a2 && (this.b instanceof Activity)) {
                ((Activity) this.b).finish();
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        protected c(Context context) {
            super(context);
        }
    }

    public FeedbackView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.q = true;
        this.r = true;
    }

    public FeedbackView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.q = true;
        this.r = true;
    }

    public FeedbackView(Context context) {
        super(context);
        this.q = true;
        this.r = true;
    }
}
