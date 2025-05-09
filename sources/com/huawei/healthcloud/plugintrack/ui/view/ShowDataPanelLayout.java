package com.huawei.healthcloud.plugintrack.ui.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.view.ShowDataPanelLayout;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.columnlayout.HealthColumnLinearLayout;
import com.huawei.ui.commonui.columnlayout.HealthColumnRelativeLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.hnu;
import defpackage.jcf;
import defpackage.nrs;
import defpackage.nsf;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class ShowDataPanelLayout extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f3790a;
    private int b;
    private Context c;
    private ViewGroup d;
    private RelativeLayout e;
    private int f;
    private List<RelativeLayout> g;
    private boolean h;
    private boolean i;
    private boolean j;
    private int k;
    private LinearLayout l;
    private int m;
    private int n;
    private hnu o;
    private View p;
    private int q;
    private HealthColumnRelativeLayout r;
    private ImageButton s;
    private int t;
    private View y;

    public ShowDataPanelLayout(Context context) {
        this(context, null);
    }

    public ShowDataPanelLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ShowDataPanelLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = false;
        this.i = false;
        this.j = false;
        this.c = context;
        d(context);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int abs;
        if (motionEvent == null) {
            return false;
        }
        if (a(R.id.grid_view)) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        hnu hnuVar = this.o;
        if (hnuVar != null && hnuVar.q()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            biR_(motionEvent);
        } else if (action == 2 && (abs = Math.abs(this.k - ((int) motionEvent.getRawY()))) > Math.abs(this.f - ((int) motionEvent.getRawX())) && abs >= 20) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 1) {
            if (action == 2) {
                if (getHeight() < 10 || this.j) {
                    return false;
                }
                biS_(motionEvent);
                hnu hnuVar = this.o;
                if (hnuVar != null) {
                    hnuVar.ai();
                }
                return true;
            }
            if (action != 3) {
                return super.onTouchEvent(motionEvent);
            }
        }
        if (this.j) {
            return false;
        }
        this.j = true;
        if (e()) {
            return false;
        }
        int height = getHeight();
        if (height >= 0 && height < this.n) {
            d(this.h);
        } else if (height >= this.n && height < this.b) {
            c(this.h);
        } else if (height >= this.b && height < this.m) {
            e(this.h);
        } else if (height >= this.m) {
            a(this.h);
        }
        return true;
    }

    public void setMainViewHolder(hnu hnuVar) {
        this.o = hnuVar;
    }

    private boolean e() {
        Context context = getContext();
        if (!(context instanceof Activity)) {
            return true;
        }
        Activity activity = (Activity) context;
        return activity.isDestroyed() || activity.isFinishing();
    }

    private void d(Context context) {
        View inflate = View.inflate(context, R.layout.track_sport_real_time_data_layout, this);
        this.p = inflate;
        this.d = (ViewGroup) inflate.findViewById(R.id.data_third_line_container);
        this.f3790a = (LinearLayout) this.p.findViewById(R.id.halffragment);
        this.y = this.p.findViewById(R.id.wholefragment);
        this.e = (RelativeLayout) this.p.findViewById(R.id.all_data_type_panel_layout);
        this.s = (ImageButton) this.p.findViewById(R.id.track_main_page_btn_updown);
        this.r = (HealthColumnRelativeLayout) this.p.findViewById(R.id.layout_operation);
        this.l = (LinearLayout) this.p.findViewById(R.id.layout_lockoperation);
        if (!b()) {
            ((HealthTextView) this.p.findViewById(R.id.track_main_page_text_targetValue)).setTextSize(1, 60.0f);
            ((HealthTextView) this.p.findViewById(R.id.text_targetUnit)).setTextSize(1, 12.0f);
            setDataSize(R.id.data_first_line_left);
            setDataSize(R.id.data_first_line_right);
            setDataSize(R.id.data_second_line_left);
            setDataSize(R.id.data_second_line_right);
            setDataSize(R.id.data_third_line_left);
            setDataSize(R.id.data_third_line_right);
            biU_((ViewGroup) this.p.findViewById(R.id.data_second_line_container), nsn.c(this.c, 16.0f));
            biU_((ViewGroup) this.p.findViewById(R.id.data_third_line_container), nsn.c(this.c, 16.0f));
        }
        setLongClickContentDescription(R.string._2130850606_res_0x7f02332e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLongClickContentDescription(int i) {
        jcf.bEH_(this.s, nsf.b(R.string._2130850677_res_0x7f023375, nsf.h(i)), nsf.h(R.string._2130850613_res_0x7f023335));
    }

    private void setDataSize(int i) {
        if (this.p.findViewById(i) != null) {
            HealthTextView healthTextView = (HealthTextView) this.p.findViewById(i).findViewById(R.id.data_value);
            if (healthTextView != null) {
                healthTextView.setTextSize(1, 34.0f);
            }
            HealthTextView healthTextView2 = (HealthTextView) this.p.findViewById(i).findViewById(R.id.data_name);
            if (healthTextView != null) {
                healthTextView2.setTextSize(1, 12.0f);
            }
        }
    }

    private boolean a(int i) {
        View findViewById;
        View view = this.p;
        return (view == null || (findViewById = view.findViewById(i)) == null || findViewById.getVisibility() != 0) ? false : true;
    }

    private void d(boolean z) {
        if (this.p == null) {
            LogUtil.b("Track_ShowDataPanelLayout", "performActionUpLowerThanMinHeight(), mRootView == null");
            return;
        }
        setAllItemClickable(false);
        int height = getHeight();
        if (z) {
            if (this.f3790a.getVisibility() != 0) {
                this.y.setVisibility(8);
                this.e.setVisibility(8);
                this.r.setVisibility(8);
                this.l.setVisibility(8);
                this.f3790a.setVisibility(0);
            }
        } else if (this.f3790a.getVisibility() != 0) {
            this.y.setVisibility(8);
            this.e.setVisibility(8);
            this.r.setVisibility(8);
            this.l.setVisibility(8);
            this.f3790a.setVisibility(0);
            this.f3790a.setAlpha(1.0f);
        }
        Animator biN_ = biN_(this, height, this.n);
        if (biN_ == null) {
            return;
        }
        biN_.addListener(new a() { // from class: com.huawei.healthcloud.plugintrack.ui.view.ShowDataPanelLayout.1
            @Override // com.huawei.healthcloud.plugintrack.ui.view.ShowDataPanelLayout.a, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ShowDataPanelLayout.this.s.setBackground(nsf.cKq_(R.drawable._2131431530_res_0x7f0b106a));
                ShowDataPanelLayout.this.setAllItemClickable(true);
                ShowDataPanelLayout.this.j = false;
                ShowDataPanelLayout.this.setLongClickContentDescription(R.string._2130850604_res_0x7f02332c);
            }
        });
        biN_.start();
    }

    private void c(boolean z) {
        int height = getHeight();
        setAllItemClickable(false);
        AnimatorSet animatorSet = new AnimatorSet();
        if (z) {
            biP_(height, animatorSet);
        } else {
            biO_(height, animatorSet);
        }
        animatorSet.start();
    }

    private void biO_(int i, AnimatorSet animatorSet) {
        if (this.f3790a.getVisibility() != 0) {
            this.e.setVisibility(8);
            this.f3790a.setVisibility(0);
        }
        View findViewById = this.p.findViewById(R.id.layout_operation);
        View findViewById2 = this.p.findViewById(R.id.layout_lockoperation);
        findViewById.setVisibility(8);
        findViewById2.setVisibility(8);
        Animator biM_ = biM_(this.y, 1.0f, 0.0f);
        biM_.addListener(new a() { // from class: com.huawei.healthcloud.plugintrack.ui.view.ShowDataPanelLayout.5
            @Override // com.huawei.healthcloud.plugintrack.ui.view.ShowDataPanelLayout.a, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ShowDataPanelLayout.this.y.setVisibility(8);
                if (ShowDataPanelLayout.this.o != null) {
                    ShowDataPanelLayout.this.o.d(false);
                }
            }
        });
        animatorSet.play(biN_(this, i, this.n)).with(biM_).before(biM_(this.f3790a, 0.0f, 1.0f));
        animatorSet.addListener(new a() { // from class: com.huawei.healthcloud.plugintrack.ui.view.ShowDataPanelLayout.4
            @Override // com.huawei.healthcloud.plugintrack.ui.view.ShowDataPanelLayout.a, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ShowDataPanelLayout.this.s.setBackground(nsf.cKq_(R.drawable._2131431530_res_0x7f0b106a));
                ShowDataPanelLayout.this.setAllItemClickable(true);
                ShowDataPanelLayout.this.j = false;
                ShowDataPanelLayout.this.setLongClickContentDescription(R.string._2130850604_res_0x7f02332c);
            }
        });
    }

    private void biP_(int i, AnimatorSet animatorSet) {
        this.e.setVisibility(4);
        this.y.setVisibility(0);
        this.d.setVisibility(8);
        a();
        animatorSet.play(biM_(this.f3790a, 1.0f, 0.0f)).with(biN_(this, i, this.b)).before(biM_(this.y, 0.0f, 1.0f));
        animatorSet.addListener(new a() { // from class: com.huawei.healthcloud.plugintrack.ui.view.ShowDataPanelLayout.2
            @Override // com.huawei.healthcloud.plugintrack.ui.view.ShowDataPanelLayout.a, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ShowDataPanelLayout.this.f3790a.setVisibility(4);
                ShowDataPanelLayout.this.s.setBackground(nsf.cKq_(R.drawable._2131431529_res_0x7f0b1069));
                ShowDataPanelLayout.this.setAllItemClickable(true);
                if (ShowDataPanelLayout.this.o != null) {
                    ShowDataPanelLayout.this.o.d(true);
                }
                ShowDataPanelLayout.this.j = false;
                ShowDataPanelLayout.this.setLongClickContentDescription(R.string._2130850606_res_0x7f02332e);
            }
        });
    }

    private void a() {
        View findViewById = this.p.findViewById(R.id.layout_operation);
        View findViewById2 = this.p.findViewById(R.id.layout_lockoperation);
        hnu hnuVar = this.o;
        if (hnuVar != null) {
            if (!hnuVar.q()) {
                findViewById.setVisibility(0);
                findViewById2.setVisibility(4);
            } else {
                findViewById.setVisibility(4);
                findViewById2.setVisibility(0);
            }
        }
    }

    private void e(boolean z) {
        int height = getHeight();
        setAllItemClickable(false);
        if (z) {
            c(height);
        } else {
            e(height);
        }
    }

    private void e(int i) {
        this.e.setVisibility(0);
        Animator biN_ = biN_(this, i, this.b);
        if (biN_ == null) {
            return;
        }
        biN_.addListener(new a() { // from class: com.huawei.healthcloud.plugintrack.ui.view.ShowDataPanelLayout.3
            @Override // com.huawei.healthcloud.plugintrack.ui.view.ShowDataPanelLayout.a, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (ShowDataPanelLayout.this.p != null) {
                    HealthColumnLinearLayout healthColumnLinearLayout = (HealthColumnLinearLayout) ShowDataPanelLayout.this.p.findViewById(R.id.data_third_line_container);
                    if (healthColumnLinearLayout != null) {
                        healthColumnLinearLayout.setVisibility(8);
                    }
                    if (ShowDataPanelLayout.this.s != null) {
                        ShowDataPanelLayout.this.s.setBackground(nsf.cKq_(R.drawable._2131431529_res_0x7f0b1069));
                    }
                    ShowDataPanelLayout.this.setAllItemClickable(true);
                    ShowDataPanelLayout.this.j = false;
                    ShowDataPanelLayout.this.setLongClickContentDescription(R.string._2130850606_res_0x7f02332e);
                    return;
                }
                LogUtil.b("Track_ShowDataPanelLayout", "onAnimationEnd mRootView is null");
            }
        });
        biN_.start();
        int i2 = this.b;
        biT_(this.d, (i - i2) / (this.m - i2), 0.0f);
    }

    private void c(int i) {
        this.f3790a.setVisibility(4);
        this.e.setVisibility(4);
        this.y.setVisibility(0);
        this.y.setAlpha(1.0f);
        a();
        Animator biN_ = biN_(this, i, this.m);
        int i2 = this.b;
        Animator biM_ = biM_(this.d, (i - i2) / (this.m - i2), 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(biN_).with(biM_);
        animatorSet.addListener(new a() { // from class: com.huawei.healthcloud.plugintrack.ui.view.ShowDataPanelLayout.7
            @Override // com.huawei.healthcloud.plugintrack.ui.view.ShowDataPanelLayout.a, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ShowDataPanelLayout.this.s.setBackground(nsf.cKq_(R.drawable._2131431528_res_0x7f0b1068));
                ShowDataPanelLayout.this.setAllItemClickable(true);
                ShowDataPanelLayout.this.j = false;
                ShowDataPanelLayout.this.setLongClickContentDescription(R.string._2130850671_res_0x7f02336f);
            }
        });
        animatorSet.start();
    }

    private void a(boolean z) {
        int height = getHeight();
        setAllItemClickable(false);
        if (z) {
            this.f3790a.setVisibility(4);
            this.e.setVisibility(4);
            this.y.setVisibility(0);
            this.y.setAlpha(1.0f);
            a();
            Animator biN_ = biN_(this, height, this.m);
            if (biN_ == null) {
                return;
            }
            biN_.addListener(new a() { // from class: com.huawei.healthcloud.plugintrack.ui.view.ShowDataPanelLayout.9
                @Override // com.huawei.healthcloud.plugintrack.ui.view.ShowDataPanelLayout.a, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    ShowDataPanelLayout.this.s.setBackground(nsf.cKq_(R.drawable._2131431528_res_0x7f0b1068));
                    ShowDataPanelLayout.this.setAllItemClickable(true);
                    ShowDataPanelLayout.this.j = false;
                    ShowDataPanelLayout.this.setLongClickContentDescription(R.string._2130850671_res_0x7f02336f);
                }
            });
            biN_.start();
            return;
        }
        this.e.setVisibility(0);
        Animator biN_2 = biN_(this, height, this.m);
        if (biN_2 == null) {
            return;
        }
        biN_2.addListener(new a() { // from class: com.huawei.healthcloud.plugintrack.ui.view.ShowDataPanelLayout.8
            @Override // com.huawei.healthcloud.plugintrack.ui.view.ShowDataPanelLayout.a, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ShowDataPanelLayout.this.s.setBackground(nsf.cKq_(R.drawable._2131431529_res_0x7f0b1069));
                ShowDataPanelLayout.this.setAllItemClickable(true);
                ShowDataPanelLayout.this.j = false;
                ShowDataPanelLayout.this.setLongClickContentDescription(R.string._2130850606_res_0x7f02332e);
            }
        });
        biN_2.start();
    }

    private void biR_(MotionEvent motionEvent) {
        this.k = (int) motionEvent.getRawY();
        this.f = (int) motionEvent.getRawX();
        this.q = this.k;
        if (this.i || this.p == null) {
            return;
        }
        this.i = true;
        this.b = getHeight();
        if (this.p.getParent() instanceof ViewGroup) {
            this.t = ((ViewGroup) this.p.getParent()).getHeight();
        } else {
            this.t = nrs.d(this.c);
        }
        boolean z = nsn.ag(this.c) && nsn.l();
        if (b() || z) {
            this.m = (int) (this.t * 0.825f);
        } else {
            this.m = (int) (this.t * 0.9f);
        }
        View findViewById = this.p.findViewById(R.id.layout_updown);
        View findViewById2 = this.p.findViewById(R.id.halffragment);
        if (findViewById2 != null) {
            this.n = findViewById2.getHeight() + findViewById2.getPaddingBottom() + findViewById.getHeight();
        }
    }

    private boolean b() {
        if (this.p.getParent() instanceof ViewGroup) {
            return ((float) ((ViewGroup) this.p.getParent()).getHeight()) / ((float) ((ViewGroup) this.p.getParent()).getWidth()) > 1.7777778f;
        }
        return nrs.a(this.c);
    }

    private boolean c() {
        View view = this.p;
        if (view == null || !(view.getParent() instanceof ViewGroup) || ((ViewGroup) this.p.getParent()).getWidth() == 0) {
            return nrs.e(this.c);
        }
        return ((float) ((ViewGroup) this.p.getParent()).getHeight()) / ((float) ((ViewGroup) this.p.getParent()).getWidth()) > 2.0f;
    }

    private void biU_(ViewGroup viewGroup, int i) {
        ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.topMargin = i;
            viewGroup.setLayoutParams(marginLayoutParams);
        }
    }

    private void biS_(MotionEvent motionEvent) {
        ViewGroup viewGroup = (ViewGroup) this.p.findViewById(R.id.data_third_line_container);
        int rawY = (int) (this.k - motionEvent.getRawY());
        int rawY2 = (int) (this.q - motionEvent.getRawY());
        this.h = rawY > 0;
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = getHeight() + rawY2;
        setLayoutParams(layoutParams);
        this.q = (int) motionEvent.getRawY();
        int height = getHeight();
        if (c()) {
            if (this.h) {
                int i = this.m;
                if (height > i) {
                    viewGroup.setVisibility(0);
                    viewGroup.setAlpha(1.0f);
                    return;
                }
                int i2 = this.b;
                if (height >= i2 && height <= i) {
                    viewGroup.setVisibility(0);
                    int i3 = this.b;
                    viewGroup.setAlpha((height - i3) / (this.m - i3));
                    return;
                } else {
                    if (height < this.n || height >= i2) {
                        return;
                    }
                    viewGroup.setVisibility(4);
                    return;
                }
            }
            int i4 = this.b;
            if (height >= i4 && height <= this.m) {
                viewGroup.setVisibility(0);
                int i5 = this.b;
                viewGroup.setAlpha((height - i5) / (this.m - i5));
            } else {
                if (height < this.n || height >= i4) {
                    return;
                }
                viewGroup.setVisibility(4);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAllItemClickable(boolean z) {
        if (this.g == null) {
            d();
        }
        for (int i = 0; i < this.g.size(); i++) {
            RelativeLayout relativeLayout = this.g.get(i);
            if (relativeLayout != null) {
                if (i == 7) {
                    relativeLayout.setClickable(false);
                } else {
                    relativeLayout.setClickable(z);
                }
            }
        }
    }

    private void d() {
        ArrayList arrayList = new ArrayList();
        this.g = arrayList;
        View view = this.p;
        if (view == null) {
            LogUtil.b("Track_ShowDataPanelLayout", "initItems(), mRootView == null");
            return;
        }
        arrayList.add((RelativeLayout) view.findViewById(R.id.track_main_page_tuba_layout));
        this.g.add((RelativeLayout) this.p.findViewById(R.id.data_first_line_left));
        this.g.add((RelativeLayout) this.p.findViewById(R.id.data_first_line_right));
        this.g.add((RelativeLayout) this.p.findViewById(R.id.data_second_line_left));
        this.g.add((RelativeLayout) this.p.findViewById(R.id.data_second_line_right));
        this.g.add((RelativeLayout) this.p.findViewById(R.id.data_third_line_left));
        this.g.add((RelativeLayout) this.p.findViewById(R.id.data_third_line_right));
        this.g.add((RelativeLayout) this.p.findViewById(R.id.data_value_show_container));
    }

    private Animator biN_(final View view, int i, int i2) {
        if (view == null) {
            return null;
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(i, i2);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: hlr
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ShowDataPanelLayout.this.biV_(view, valueAnimator);
            }
        });
        return ofInt;
    }

    public /* synthetic */ void biV_(View view, ValueAnimator valueAnimator) {
        Object animatedValue = valueAnimator.getAnimatedValue();
        if (animatedValue instanceof Integer) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = ((Integer) animatedValue).intValue();
            view.setLayoutParams(layoutParams);
            hnu hnuVar = this.o;
            if (hnuVar != null) {
                hnuVar.ai();
            }
        }
    }

    private Animator biM_(final View view, float f, float f2) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f, f2);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: hlt
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ShowDataPanelLayout.biQ_(view, valueAnimator);
            }
        });
        return ofFloat;
    }

    public static /* synthetic */ void biQ_(View view, ValueAnimator valueAnimator) {
        Object animatedValue = valueAnimator.getAnimatedValue();
        if (animatedValue instanceof Float) {
            view.setAlpha(((Float) animatedValue).floatValue());
        }
    }

    private void biT_(View view, float f, float f2) {
        if (view == null) {
            return;
        }
        biM_(view, f, f2).start();
    }

    class a implements Animator.AnimatorListener {
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        private a() {
        }
    }
}
