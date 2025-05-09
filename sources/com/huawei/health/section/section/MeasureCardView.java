package com.huawei.health.section.section;

import android.content.Context;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.section.section.MeasureCardItemHolder;
import com.huawei.health.section.section.MeasureCardView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.fbp;
import defpackage.fbr;
import defpackage.fbv;
import defpackage.fbz;
import health.compact.a.util.LogUtil;
import java.util.LinkedList;
import java.util.Queue;

/* loaded from: classes3.dex */
public class MeasureCardView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private MeasureCardItemHolder f2972a;
    private ViewStub b;
    private boolean c;
    private View d;
    private final Queue<a> e;
    private View f;
    private ViewStub g;
    private MeasureCardItemHolder h;
    private HealthSubHeader i;
    private View j;
    private ViewStub l;
    private MeasureCardItemHolder m;

    public MeasureCardView(Context context) {
        super(context);
        this.c = false;
        this.e = new LinkedList();
        c(context);
    }

    public MeasureCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = false;
        this.e = new LinkedList();
        c(context);
    }

    public MeasureCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = false;
        this.e = new LinkedList();
        c(context);
    }

    private void c(Context context) {
        final View inflate = LayoutInflater.from(context).inflate(R.layout.measure_card_view_layout, (ViewGroup) this, false);
        HealthSubHeader healthSubHeader = (HealthSubHeader) inflate.findViewById(R.id.sub_header);
        this.i = healthSubHeader;
        healthSubHeader.setSubHeaderBackgroundColor(0);
        this.g = (ViewStub) inflate.findViewById(R.id.normal_card);
        this.b = (ViewStub) inflate.findViewById(R.id.arrhythmia_card);
        this.l = (ViewStub) inflate.findViewById(R.id.vascular_card);
        inflate.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.health.section.section.MeasureCardView.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (inflate.getWidth() <= 0 || inflate.getHeight() <= 0) {
                    return;
                }
                MeasureCardView.this.c = true;
                while (!MeasureCardView.this.e.isEmpty()) {
                    final a aVar = (a) MeasureCardView.this.e.poll();
                    if (aVar == null) {
                        return;
                    } else {
                        HandlerExecutor.a(new Runnable() { // from class: com.huawei.health.section.section.MeasureCardView.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MeasureCardView.this.d(aVar.d, aVar.c);
                            }
                        });
                    }
                }
                inflate.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        addView(inflate);
    }

    public void b() {
        this.i.setVisibility(8);
    }

    public void a() {
        this.i.setVisibility(0);
    }

    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void c(final fbp fbpVar) {
        if (!Looper.myLooper().isCurrentThread()) {
            HandlerExecutor.a(new Runnable() { // from class: fbx
                @Override // java.lang.Runnable
                public final void run() {
                    MeasureCardView.this.c(fbpVar);
                }
            });
        } else {
            d(fbpVar, false);
        }
    }

    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void e(final fbp fbpVar) {
        if (!Looper.myLooper().isCurrentThread()) {
            HandlerExecutor.a(new Runnable() { // from class: fby
                @Override // java.lang.Runnable
                public final void run() {
                    MeasureCardView.this.e(fbpVar);
                }
            });
        } else {
            d(fbpVar, true);
        }
    }

    private View avS_(View view, ViewStub viewStub) {
        boolean z = viewStub != null && (viewStub.getParent() instanceof ViewGroup);
        if (view == null && z) {
            view = viewStub.inflate();
        }
        if (view == null && !z) {
            LogUtil.c("MeasureCardView", "inflateCard has an exception, inflate through LayoutInflater now!");
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.measure_card_view_wrapper_with_header);
            if (linearLayout == null) {
                return view;
            }
            view = LayoutInflater.from(getContext()).inflate(R.layout.measure_card_item_layout, (ViewGroup) linearLayout, false);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                marginLayoutParams.bottomMargin = getContext().getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
                view.setLayoutParams(marginLayoutParams);
            }
            linearLayout.addView(view);
        }
        if (view == null) {
            LogUtil.e("MeasureCardView", "card inflated failed!");
        }
        return view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(fbp fbpVar, boolean z) {
        MeasureCardItemHolder measureCardItemHolder;
        if (fbpVar == null) {
            LogUtil.c("MeasureCardView", "refreshCard failed cause cardBean is null!");
            return;
        }
        if (!this.c) {
            this.e.add(new a(fbpVar, z));
            return;
        }
        MeasureCardItemHolder.MeasureType e = fbpVar.e();
        int i = AnonymousClass2.b[e.ordinal()];
        if (i == 1) {
            View avS_ = avS_(this.j, this.g);
            this.j = avS_;
            if (this.h == null && avS_ != null) {
                this.h = new fbv(avS_);
            }
            measureCardItemHolder = this.h;
        } else if (i == 2) {
            View avS_2 = avS_(this.d, this.b);
            this.d = avS_2;
            if (this.f2972a == null && avS_2 != null) {
                this.f2972a = new fbr(avS_2);
            }
            measureCardItemHolder = this.f2972a;
        } else if (i == 3) {
            View avS_3 = avS_(this.f, this.l);
            this.f = avS_3;
            if (this.m == null && avS_3 != null) {
                this.m = new fbz(avS_3);
            }
            measureCardItemHolder = this.m;
        } else {
            LogUtil.e("MeasureCardView", "refreshCard failed cause type = ", e, " is unknown");
            return;
        }
        if (measureCardItemHolder == null) {
            LogUtil.e("MeasureCardView", "cardHolder is null!");
            return;
        }
        if (!fbpVar.h()) {
            measureCardItemHolder.hide();
        } else {
            measureCardItemHolder.show();
        }
        if (z) {
            measureCardItemHolder.bindChart(fbpVar);
        } else {
            measureCardItemHolder.bindAll(fbpVar);
        }
    }

    /* renamed from: com.huawei.health.section.section.MeasureCardView$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[MeasureCardItemHolder.MeasureType.values().length];
            b = iArr;
            try {
                iArr[MeasureCardItemHolder.MeasureType.ITEM_TYPE_ECG_NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[MeasureCardItemHolder.MeasureType.ITEM_TYPE_ECG_ARRHYTHMIA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[MeasureCardItemHolder.MeasureType.ITEM_TYPE_ECG_VASCULAR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    static final class a {
        private final boolean c;
        private final fbp d;

        public a(fbp fbpVar, boolean z) {
            this.d = fbpVar;
            this.c = z;
        }
    }
}
