package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Handler;
import android.os.Message;
import android.util.Property;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.LanguageUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes4.dex */
public class hnj {

    /* renamed from: a, reason: collision with root package name */
    private hok f13269a;
    private Handler c;
    private boolean d;
    private float f;
    private RelativeLayout g;
    private View h;
    private int j;
    private hnu l;
    private hog m;
    private hok n;
    private boolean e = true;
    private boolean i = true;
    private a b = new a();

    static /* synthetic */ boolean bma_(View view, MotionEvent motionEvent) {
        return true;
    }

    public hnj(hnu hnuVar, hog hogVar, Handler handler) {
        this.l = hnuVar;
        this.m = hogVar;
        this.c = handler;
        j();
    }

    private void j() {
        this.h = this.m.bnn_();
        this.g = this.m.p();
    }

    void e(final hok hokVar) {
        Animator animator;
        AnimatorSet.Builder play;
        LogUtil.a("TrackDataShowAnimationManager", "performDataSelectAnimation");
        this.f13269a = hokVar;
        d(false);
        if (this.m.ak()) {
            animator = null;
        } else {
            int height = this.g.getHeight();
            this.j = height;
            animator = blY_(this.g, height, this.h.getHeight());
        }
        AnimatorSet animatorSet = new AnimatorSet();
        Animator blX_ = blX_(b(hokVar, false), 1.0f, 0.0f);
        if (animator != null) {
            play = animatorSet.play(animator).with(blX_);
        } else {
            play = animatorSet.play(blX_);
        }
        blV_(hokVar, play);
        GridView gridView = (GridView) this.h.findViewById(R.id.grid_view);
        gridView.setAlpha(0.0f);
        gridView.setVisibility(0);
        HealthTextView healthTextView = (HealthTextView) this.h.findViewById(R.id.switch_data_hint_text);
        healthTextView.setAlpha(0.0f);
        healthTextView.setVisibility(0);
        this.h.findViewById(R.id.all_data_type_panel_layout).setVisibility(0);
        play.before(blX_(g(), 0.0f, 1.0f));
        animatorSet.addListener(new e() { // from class: hnj.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // hnj.e, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                if (hnj.this.h.findViewById(R.id.data_value_show_container) != null) {
                    hnj.this.h.findViewById(R.id.data_value_show_container).setVisibility(0);
                }
                hokVar.bnC_().setVisibility(4);
                hnj.this.d = true;
                hnj.this.e = true;
                hnj.this.i = false;
                hnj.this.a(false);
                hnj.this.m.bnh_().setClickable(true);
            }
        });
        animatorSet.start();
        this.d = false;
    }

    public boolean a() {
        return this.d;
    }

    private void blV_(hok hokVar, AnimatorSet.Builder builder) {
        float f;
        if (hokVar == null || builder == null) {
            LogUtil.b("TrackDataShowAnimationManager", "buildPositionChangeAnimators(), fromItem == null || builder == null");
            return;
        }
        SparseArray<hok> bno_ = this.m.bno_();
        hok hokVar2 = bno_.get(bno_.size() - 1);
        this.n = hokVar2;
        HealthTextView a2 = hokVar.a();
        a2.getLocationOnScreen(new int[2]);
        HealthTextView d = hokVar.d();
        d.getLocationOnScreen(new int[2]);
        hokVar2.a().getLocationOnScreen(new int[2]);
        hokVar2.d().getLocationOnScreen(new int[2]);
        a2.setPivotX(0.0f);
        a2.setPivotY(0.0f);
        d.setPivotX(0.0f);
        d.setPivotY(0.0f);
        if (a2.getHeight() != 0) {
            f = r10.getHeight() / a2.getHeight();
        } else {
            LogUtil.h("TrackDataShowAnimationManager", "fromDataValue.getHeight() = 0");
            f = 1.0f;
        }
        this.f = f;
        this.b.d = (r11[0] - r7[0]) - (((a2.getWidth() * f) - r10.getWidth()) / 2.0f);
        this.b.e = r11[1] - r7[1];
        this.b.b = (r12[0] - r9[0]) - ((d.getWidth() - r3.getWidth()) / 2.0f);
        this.b.c = r12[1] - r9[1];
        builder.with(ObjectAnimator.ofFloat(a2, (Property<HealthTextView, Float>) View.X, a2.getX(), a2.getX() + this.b.d)).with(ObjectAnimator.ofFloat(a2, (Property<HealthTextView, Float>) View.Y, a2.getY(), a2.getY() + this.b.e)).with(ObjectAnimator.ofFloat(d, (Property<HealthTextView, Float>) View.X, d.getX(), d.getX() + this.b.b)).with(ObjectAnimator.ofFloat(d, (Property<HealthTextView, Float>) View.Y, d.getY(), d.getY() + this.b.c)).with(ObjectAnimator.ofFloat(a2, (Property<HealthTextView, Float>) View.SCALE_X, 1.0f, f)).with(ObjectAnimator.ofFloat(a2, (Property<HealthTextView, Float>) View.SCALE_Y, 1.0f, f));
    }

    public void d() {
        this.e = false;
        this.i = false;
        a(false);
        if (this.h.findViewById(R.id.data_value_show_container) != null) {
            this.h.findViewById(R.id.data_value_show_container).setVisibility(4);
        }
        this.f13269a.bnC_().setVisibility(0);
        Animator blX_ = blX_(g(), 1.0f, 0.0f);
        blX_.addListener(new e() { // from class: hnj.2
            @Override // hnj.e, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (hnj.this.h.findViewById(R.id.grid_view) != null) {
                    hnj.this.h.findViewById(R.id.grid_view).setVisibility(8);
                } else {
                    LogUtil.h("TrackDataShowAnimationManager", "mRootView.findViewById(R.id.grid_view) == null");
                }
                if (hnj.this.h.findViewById(R.id.switch_data_hint_text) != null) {
                    hnj.this.h.findViewById(R.id.switch_data_hint_text).setVisibility(4);
                } else {
                    LogUtil.h("TrackDataShowAnimationManager", "mRootView.findViewById(R.id.switch_data_hint_text) == null");
                }
            }
        });
        AnimatorSet blW_ = blW_(blX_);
        blW_.addListener(new e() { // from class: hnj.3
            @Override // hnj.e, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                hnj.this.h();
            }
        });
        a(false);
        blW_.start();
    }

    private AnimatorSet blW_(Animator animator) {
        AnimatorSet.Builder play;
        HealthTextView a2 = this.f13269a.a();
        int[] iArr = new int[2];
        a2.getLocationOnScreen(iArr);
        HealthTextView d = this.f13269a.d();
        int[] iArr2 = new int[2];
        d.getLocationOnScreen(iArr2);
        RelativeLayout bnC_ = this.f13269a.bnC_();
        int[] iArr3 = new int[2];
        bnC_.getLocationOnScreen(iArr3);
        a2.setPivotX(0.0f);
        a2.setPivotY(0.0f);
        d.setPivotX(0.0f);
        d.setPivotY(0.0f);
        float width = (iArr[0] - iArr3[0]) + ((a2.getWidth() - bnC_.getWidth()) / 2);
        float width2 = (iArr2[0] - iArr3[0]) + ((d.getWidth() - bnC_.getWidth()) / 2);
        AnimatorSet animatorSet = new AnimatorSet();
        Animator blY_ = blY_(this.g, this.h.getHeight(), this.j);
        Animator blX_ = blX_(b(this.f13269a, true), 0.0f, 1.0f);
        if (blY_ != null) {
            play = animatorSet.play(blY_).with(blX_);
        } else {
            play = animatorSet.play(blX_);
        }
        play.with(ObjectAnimator.ofFloat(a2, (Property<HealthTextView, Float>) View.SCALE_X, this.f, 1.0f)).with(ObjectAnimator.ofFloat(a2, (Property<HealthTextView, Float>) View.SCALE_Y, this.f, 1.0f)).with(ObjectAnimator.ofFloat(a2, (Property<HealthTextView, Float>) View.X, a2.getX(), a2.getX() - width)).with(ObjectAnimator.ofFloat(a2, (Property<HealthTextView, Float>) View.Y, a2.getY(), a2.getY() - this.b.e)).with(ObjectAnimator.ofFloat(d, (Property<HealthTextView, Float>) View.X, d.getX(), d.getX() - width2)).with(ObjectAnimator.ofFloat(d, (Property<HealthTextView, Float>) View.Y, d.getY(), d.getY() - this.b.c)).after(animator);
        return animatorSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        a(true);
        d(true);
        b(0);
        this.e = true;
        this.i = true;
        this.l.a(0);
        if (this.m.bny_() != null) {
            this.m.bny_().setOnTouchListener(null);
        }
    }

    public boolean e() {
        return this.e;
    }

    public void e(boolean z) {
        this.e = z;
    }

    public boolean c() {
        return this.i;
    }

    public void b(boolean z) {
        this.i = z;
    }

    public void c(final hoj hojVar, final hok hokVar) {
        if (hojVar == null || hokVar == null) {
            LogUtil.b("TrackDataShowAnimationManager", "itemValue == null || viewHolderItem == null");
            return;
        }
        this.d = false;
        HealthTextView a2 = hokVar.a();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(a2, "alpha", 1.0f, 0.0f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(a2, (Property<HealthTextView, Float>) View.SCALE_X, 1.0f, 1.5f);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(a2, (Property<HealthTextView, Float>) View.SCALE_Y, 1.0f, 1.5f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat).with(ofFloat2).with(ofFloat3);
        animatorSet.addListener(new e() { // from class: hnj.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // hnj.e, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                hokVar.d(hojVar);
            }
        });
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(a2, "alpha", 0.0f, 1.0f);
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(a2, (Property<HealthTextView, Float>) View.SCALE_X, 0.5f, 1.0f);
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(a2, (Property<HealthTextView, Float>) View.SCALE_Y, 0.5f, 1.0f);
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.play(ofFloat4).with(ofFloat5).with(ofFloat6).after(animatorSet);
        animatorSet2.addListener(new e() { // from class: hnj.5
            @Override // hnj.e, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                hnj.this.b();
                Message obtain = Message.obtain();
                hnj.this.d = true;
                obtain.what = 3;
                hnj.this.c.sendMessage(obtain);
            }
        });
        animatorSet2.start();
    }

    public void b() {
        hok hokVar = this.f13269a;
        if (hokVar == null || this.n == null) {
            return;
        }
        HealthTextView a2 = hokVar.a();
        a2.getLocationOnScreen(new int[2]);
        HealthTextView d = this.f13269a.d();
        d.getLocationOnScreen(new int[2]);
        this.n.a().getLocationOnScreen(new int[2]);
        this.n.d().getLocationOnScreen(new int[2]);
        a2.setPivotX(0.0f);
        a2.setPivotY(0.0f);
        d.setPivotX(0.0f);
        d.setPivotY(0.0f);
        a2.setX(a2.getX() + ((r6[0] - r2[0]) - (((a2.getWidth() * this.f) - r5.getWidth()) / 2.0f)));
        a2.setY(a2.getY() + (r6[1] - r2[1]));
        d.setX(d.getX() + ((r1[0] - r4[0]) - ((d.getWidth() - r7.getWidth()) / 2.0f)));
        d.setY(d.getY() + (r1[1] - r4[1]));
    }

    public void i() {
        LinearLayout linearLayout = (LinearLayout) this.h.findViewById(R.id.layout_lockoperation);
        if (linearLayout == null) {
            return;
        }
        linearLayout.setOnTouchListener(new b());
    }

    private Animator blY_(final View view, int i, int i2) {
        if (view == null) {
            return null;
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(i, i2);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: hni
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                hnj.this.bmb_(view, valueAnimator);
            }
        });
        return ofInt;
    }

    /* synthetic */ void bmb_(View view, ValueAnimator valueAnimator) {
        Object animatedValue = valueAnimator.getAnimatedValue();
        if (animatedValue instanceof Integer) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = ((Integer) animatedValue).intValue();
            view.setLayoutParams(layoutParams);
            hnu hnuVar = this.l;
            if (hnuVar != null) {
                hnuVar.ai();
            }
        }
    }

    private Animator blX_(final List<View> list, float f, float f2) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f, f2);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: hnl
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                hnj.blZ_(list, valueAnimator);
            }
        });
        return ofFloat;
    }

    static /* synthetic */ void blZ_(List list, ValueAnimator valueAnimator) {
        Object animatedValue = valueAnimator.getAnimatedValue();
        if ((animatedValue instanceof Float) && !koq.b(list)) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                View view = (View) it.next();
                if (view != null) {
                    view.setAlpha(((Float) animatedValue).floatValue());
                }
            }
        }
    }

    private List<View> b(hok hokVar, boolean z) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(this.h.findViewById(R.id.gps_content_and_music_control));
        linkedList.add(this.h.findViewById(R.id.music_and_indoor_running_icon));
        linkedList.add(this.h.findViewById(R.id.layout_updown));
        linkedList.add(this.h.findViewById(R.id.track_main_page_suggest_show_layout));
        linkedList.add(this.h.findViewById(R.id.track_stop_content));
        linkedList.add(this.h.findViewById(R.id.layout_operation));
        linkedList.add(this.h.findViewById(R.id.layout_lockoperation));
        SparseArray<hok> bno_ = this.m.bno_();
        for (int i = 0; i < bno_.size(); i++) {
            hok hokVar2 = bno_.get(i);
            hokVar2.bnC_().setClickable(z);
            if (hokVar2 != hokVar && i != bno_.size() - 1) {
                linkedList.add(hokVar2.bnC_());
            }
        }
        return linkedList;
    }

    private List<View> g() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(this.h.findViewById(R.id.switch_data_hint_text));
        linkedList.add(this.h.findViewById(R.id.grid_view));
        return linkedList;
    }

    private void d(boolean z) {
        hog hogVar = this.m;
        if (hogVar == null) {
            LogUtil.b("TrackDataShowAnimationManager", "mViewHolderBean is null");
            return;
        }
        if (hogVar.bnq_() != null) {
            this.m.bnq_().setClickable(z);
        }
        if (this.m.bns_() != null) {
            this.m.bns_().setClickable(z);
        }
        if (this.m.bnr_() != null) {
            this.m.bnr_().setClickable(z);
        }
        if (this.m.bnt_() != null) {
            this.m.bnt_().setClickable(z);
        }
        if (this.m.al() == null) {
            LogUtil.b("TrackDataShowAnimationManager", "requestTrackMainPageBtnStop is null");
        } else if (z) {
            this.m.al().setOnTouchListener(null);
        } else {
            this.m.al().setOnTouchListener(new View.OnTouchListener() { // from class: hnm
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return hnj.bma_(view, motionEvent);
                }
            });
        }
    }

    public void a(boolean z) {
        SparseArray<hok> bno_ = this.m.bno_();
        if (bno_ == null) {
            return;
        }
        for (int i = 0; i < bno_.size(); i++) {
            hok hokVar = bno_.get(i);
            if (hokVar != null) {
                if (i == 7) {
                    hokVar.bnC_().setClickable(false);
                } else {
                    hokVar.bnC_().setClickable(z);
                }
            }
        }
    }

    public void b(int i) {
        LinearLayout bnm_ = this.m.bnm_();
        if (bnm_ == null) {
            return;
        }
        bnm_.setVisibility(i);
    }

    class e implements Animator.AnimatorListener {
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

        private e() {
        }
    }

    static class a {
        float b;
        float c;
        float d;
        float e;

        private a() {
            this.d = 0.0f;
            this.e = 0.0f;
            this.b = 0.0f;
            this.c = 0.0f;
        }
    }

    class b implements View.OnTouchListener {

        /* renamed from: a, reason: collision with root package name */
        private float f13270a;
        private float e;

        private b() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == 0) {
                this.e = motionEvent.getRawX();
            } else if (action != 1) {
                if (action == 2) {
                    this.f13270a = motionEvent.getRawX();
                }
            } else if (e()) {
                b();
            }
            return true;
        }

        private boolean e() {
            return LanguageUtil.bc(BaseApplication.getContext()) ? this.e - this.f13270a >= 200.0f : this.f13270a - this.e >= 200.0f;
        }

        private void b() {
            if (!nsn.a(700)) {
                hnj.this.l.c(false);
                hnj.this.m.bnj_().setAnimation(bmc_());
                hnj.this.m.bnj_().setVisibility(8);
                if (hnj.this.m.bnw_() != null) {
                    hnj.this.m.bnw_().setClickable(true);
                }
                View findViewById = hnj.this.m.ak() ? hnj.this.h.findViewById(R.id.layout_StopOrResume) : hnj.this.m.bnk_();
                findViewById.setAnimation(bmd_());
                findViewById.setVisibility(0);
                hnj.this.a(true);
                HashMap hashMap = new HashMap(2);
                hashMap.put("click", 1);
                hashMap.put("type", 0);
                ixx.d().d(BaseApplication.getContext(), AnalyticsValue.MOTION_TRACK_1040028.value(), hashMap, 0);
                return;
            }
            LogUtil.h("TrackDataShowAnimationManager", "view click too fast.");
        }

        private TranslateAnimation bmd_() {
            TranslateAnimation translateAnimation = new TranslateAnimation(1, -1.0f, 1, 0.0f, 1, 0.0f, 1, 0.0f);
            translateAnimation.setInterpolator(new DecelerateInterpolator());
            translateAnimation.setDuration(250L);
            return translateAnimation;
        }

        private TranslateAnimation bmc_() {
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 1.0f, 1, 0.0f, 1, 0.0f);
            translateAnimation.setInterpolator(new DecelerateInterpolator());
            translateAnimation.setDuration(250L);
            return translateAnimation;
        }
    }
}
