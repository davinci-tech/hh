package defpackage;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.text.TextUtils;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.uikit.hwcommon.anim.HwCubicBezierInterpolator;
import health.compact.a.LanguageUtil;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes6.dex */
public class owb {
    private HealthCardView d;
    private int g;
    private LinearLayout h;
    private owc j;
    private LinearLayout k;
    private boolean m;

    /* renamed from: a, reason: collision with root package name */
    private static final HwCubicBezierInterpolator f15981a = new HwCubicBezierInterpolator(0.38f, 1.33f, 0.6f, 1.0f);
    private static final HwCubicBezierInterpolator e = new HwCubicBezierInterpolator(0.33f, 0.0f, 0.67f, 1.0f);
    private static final HwCubicBezierInterpolator c = new HwCubicBezierInterpolator(0.4f, 0.0f, 0.2f, 1.0f);
    private Queue<owc> o = new LinkedBlockingQueue();
    private c i = new c(this);
    private owc f = new owc();
    private Context b = BaseApplication.e();

    public owb(LinearLayout linearLayout, LinearLayout linearLayout2, owc owcVar, HealthCardView healthCardView, boolean z) {
        this.h = linearLayout;
        this.k = linearLayout2;
        this.j = owcVar;
        if (owcVar != null) {
            this.g = owcVar.a();
            this.f.a(owcVar);
        }
        this.d = healthCardView;
        this.m = z;
    }

    public void b(boolean z) {
        this.m = z;
    }

    public void c(List<owc> list, boolean z) {
        if (koq.b(list)) {
            LogUtil.h("MessageAnimationManager", "messageList is empty");
            return;
        }
        LogUtil.a("MessageAnimationManager", "messageList size = ", Integer.valueOf(list.size()));
        this.o.clear();
        this.o.addAll(list);
        LogUtil.a("MessageAnimationManager", "mMessageQueue size = ", Integer.valueOf(this.o.size()));
        if (this.i.hasMessages(1) || !z) {
            return;
        }
        this.i.sendEmptyMessageDelayed(1, 500L);
    }

    public void d() {
        LogUtil.a("MessageAnimationManager", "releaseManager");
        this.o.clear();
        this.i.removeCallbacksAndMessages(null);
        this.h.clearAnimation();
        this.k.clearAnimation();
    }

    public void c() {
        LogUtil.a("MessageAnimationManager", "stopAnimate");
        this.o.clear();
        this.i.removeCallbacksAndMessages(null);
        if (this.h.getAnimation() != null) {
            this.h.getAnimation().cancel();
        }
        if (this.k.getAnimation() != null) {
            this.k.getAnimation().cancel();
        }
        this.h.clearAnimation();
        this.k.clearAnimation();
        c(this.j, true);
    }

    public void b() {
        LogUtil.a("MessageAnimationManager", "startAnimate");
        if (this.o.isEmpty()) {
            return;
        }
        LogUtil.a("MessageAnimationManager", "startAnimate mMessageQueue has msg");
        if (this.i.hasMessages(1)) {
            return;
        }
        this.i.sendEmptyMessageDelayed(1, 500L);
    }

    public boolean a() {
        return (this.j == null || this.f.c() == null || !this.f.c().equals(this.j.c())) ? false : true;
    }

    public LinearLayout diK_() {
        return this.h;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(owc owcVar, boolean z) {
        if (owcVar == null || this.f.equals(owcVar)) {
            LogUtil.h("MessageAnimationManager", "initNextMessageView messageInfo is null or need not refresh");
            return;
        }
        LogUtil.a("MessageAnimationManager", "initNextMessageView, messageInfo:", owcVar.c(), " isNeedAnimate:", Boolean.valueOf(z));
        this.f.a(owcVar);
        LinearLayout linearLayout = (LinearLayout) this.k.findViewById(R.id.title_layout);
        ImageView imageView = (ImageView) this.k.findViewById(R.id.bottom_step_icon);
        HealthTextView healthTextView = (HealthTextView) this.k.findViewById(R.id.bottom_step_text);
        HealthTextView healthTextView2 = (HealthTextView) this.k.findViewById(R.id.bottom_step_value);
        HealthTextView healthTextView3 = (HealthTextView) this.k.findViewById(R.id.divider_line_text);
        if (linearLayout == null || healthTextView3 == null) {
            LogUtil.h("MessageAnimationManager", "initNextMessageView, titleLayout is null");
            return;
        }
        imageView.setBackground(owcVar.diE_());
        healthTextView.setText(owcVar.c());
        if (TextUtils.isEmpty(owcVar.i())) {
            healthTextView2.setVisibility(8);
        } else {
            healthTextView2.setVisibility(0);
        }
        healthTextView2.setText(owcVar.i());
        if (owcVar.d() != 0) {
            float dimension = this.b.getResources().getDimension(owcVar.d());
            healthTextView.setTextSize(0, dimension);
            healthTextView2.setTextSize(0, dimension);
        }
        if (owcVar.diF_() != null) {
            Drawable diF_ = owcVar.diF_();
            diF_.setBounds(0, 0, this.b.getResources().getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446), this.b.getResources().getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446));
            healthTextView.setCompoundDrawablesRelative(null, null, diF_, null);
            healthTextView.setCompoundDrawablePadding(this.b.getResources().getDimensionPixelSize(R.dimen._2131363003_res_0x7f0a04bb));
        } else {
            healthTextView.setCompoundDrawablesRelativeWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        }
        if (!this.m) {
            if (!TextUtils.isEmpty(owcVar.i()) && !LanguageUtil.h(this.b) && owcVar != this.j) {
                healthTextView3.setVisibility(0);
            } else {
                healthTextView3.setVisibility(8);
            }
            healthTextView.setMaxLines(1);
            healthTextView2.setMaxLines(1);
            this.k.setOrientation(0);
        } else if (!TextUtils.isEmpty(owcVar.i())) {
            this.k.setOrientation(1);
        } else {
            healthTextView.setMaxLines(2);
            this.k.setOrientation(0);
        }
        this.k.setOnClickListener(owcVar.diD_());
        a(owcVar.a(), z);
        if (!TextUtils.isEmpty(owcVar.f()) && owcVar.j() != null) {
            ixx.d().d(this.b, owcVar.f(), owcVar.j(), 0);
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        if (owcVar == this.j) {
            layoutParams.setMargins(0, this.b.getResources().getDimensionPixelOffset(R.dimen._2131363004_res_0x7f0a04bc), 0, 0);
        } else {
            layoutParams.setMargins(0, 0, 0, 0);
        }
        linearLayout.setLayoutParams(layoutParams);
        LogUtil.a("MessageAnimationManager", "initNextMessageView end");
    }

    private void a(int i, boolean z) {
        if (z) {
            this.k.setVisibility(0);
            diJ_(this.h, this.k, i);
        } else {
            this.i.removeMessages(2);
            this.d.setCardBackgroundColor(this.b.getColor(i));
            this.g = i;
        }
        LinearLayout linearLayout = this.h;
        this.h = this.k;
        this.k = linearLayout;
        linearLayout.setVisibility(8);
        this.h.setVisibility(0);
    }

    private void diJ_(LinearLayout linearLayout, LinearLayout linearLayout2, final int i) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.7f, 1.0f, 0.7f, linearLayout.getWidth() / 2.0f, 0.0f);
        HwCubicBezierInterpolator hwCubicBezierInterpolator = c;
        scaleAnimation.setInterpolator(hwCubicBezierInterpolator);
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, linearLayout.getHeight() * 1.5f);
        translateAnimation.setInterpolator(hwCubicBezierInterpolator);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        HwCubicBezierInterpolator hwCubicBezierInterpolator2 = e;
        alphaAnimation.setInterpolator(hwCubicBezierInterpolator2);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(300L);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        int width = linearLayout2.getWidth();
        if (width == 0) {
            width = linearLayout.getWidth();
        }
        ScaleAnimation scaleAnimation2 = new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, width / 2.0f, 0.0f);
        scaleAnimation2.setDuration(400L);
        HwCubicBezierInterpolator hwCubicBezierInterpolator3 = f15981a;
        scaleAnimation2.setInterpolator(hwCubicBezierInterpolator3);
        TranslateAnimation translateAnimation2 = new TranslateAnimation(0.0f, 0.0f, -(linearLayout.getHeight() * 1.5f), 0.0f);
        translateAnimation2.setDuration(400L);
        translateAnimation2.setInterpolator(hwCubicBezierInterpolator3);
        AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation2.setDuration(300L);
        alphaAnimation2.setInterpolator(hwCubicBezierInterpolator2);
        AnimationSet animationSet2 = new AnimationSet(true);
        animationSet2.addAnimation(translateAnimation2);
        animationSet2.addAnimation(alphaAnimation2);
        animationSet2.addAnimation(scaleAnimation2);
        linearLayout.startAnimation(animationSet);
        linearLayout2.startAnimation(animationSet2);
        ValueAnimator ofObject = ValueAnimator.ofObject(new ArgbEvaluator(), Integer.valueOf(this.b.getResources().getColor(this.g)), Integer.valueOf(this.b.getResources().getColor(i)));
        ofObject.setDuration(250L);
        ofObject.setInterpolator(hwCubicBezierInterpolator2);
        ofObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: owb.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                owb.this.d.setCardBackgroundColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
                owb.this.g = i;
            }
        });
        ofObject.start();
        this.i.removeMessages(2);
        Message obtainMessage = this.i.obtainMessage(2);
        obtainMessage.arg1 = i;
        this.i.sendMessageDelayed(obtainMessage, 250L);
    }

    static class c extends BaseHandler<owb> {
        c(owb owbVar) {
            super(owbVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: diL_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(owb owbVar, Message message) {
            int i = message.what;
            if (i != 1) {
                if (i != 2) {
                    return;
                }
                owbVar.d.setCardBackgroundColor(BaseApplication.e().getColor(message.arg1));
                return;
            }
            LogUtil.a("MessageAnimationManager", "AnimationHandler CHANGE_MESSAGE_MSG");
            if (owbVar.o.isEmpty()) {
                owbVar.c(owbVar.j, true);
                return;
            }
            owc owcVar = (owc) owbVar.o.poll();
            while (owcVar == null && !owbVar.o.isEmpty()) {
                owcVar = (owc) owbVar.o.poll();
            }
            if (owcVar != null) {
                LogUtil.a("MessageAnimationManager", "AnimationHandler mMessageQueue size=", Integer.valueOf(owbVar.o.size()));
                owbVar.c(owcVar, true);
                sendEmptyMessageDelayed(1, 5000L);
                return;
            }
            sendEmptyMessage(1);
        }
    }
}
