package defpackage;

import android.animation.AnimatorSet;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class hma extends AnimBaseHelper {

    /* renamed from: a, reason: collision with root package name */
    private List<Drawable> f13244a;
    private ImageView b;
    private Drawable c;
    private AnimationDrawable d;
    private ImageView e;
    private int f;
    private Handler g;
    private List<Drawable> h;
    private kxa i;
    private boolean j;
    private int l;
    private Drawable m;
    private boolean n;

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public boolean isAnimRunning() {
        return false;
    }

    public hma(View view) {
        super(view);
        this.h = new ArrayList();
        this.f13244a = new ArrayList();
        this.j = false;
        this.g = new d(Looper.getMainLooper(), this);
        this.l = 1;
        this.n = false;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public void initResource() {
        if (this.j) {
            return;
        }
        this.j = true;
        this.h.clear();
        this.f13244a.clear();
        j();
        Bitmap bkn_ = bkn_("frame_flexion_0");
        ImageView imageView = (ImageView) this.mAnimContainer.findViewById(R.id.flexion_knee_img);
        this.b = imageView;
        imageView.setImageBitmap(bkn_);
        g();
        ImageView imageView2 = (ImageView) this.mAnimContainer.findViewById(R.id.flexion_arrow_img);
        this.e = imageView2;
        imageView2.setBackground(bkl_());
    }

    private void j() {
        for (int i = 0; i < 48; i++) {
            Bitmap bkn_ = bkn_("frame_flexion_" + i);
            if (bkn_ != null) {
                Drawable cHq_ = nrf.cHq_(bkn_);
                if (cHq_ != null) {
                    this.h.add(cHq_);
                }
                BitmapDrawable cKm_ = nrz.cKm_(BaseApplication.e(), cHq_);
                if (cKm_ != null) {
                    this.f13244a.add(cKm_);
                }
            }
        }
    }

    private void g() {
        Bitmap bkn_ = bkn_("public_flexion_arrow");
        if (bkn_ == null) {
            LogUtil.h("Track_BendSuggestionAnimHelper", "arrowBitmap == null");
            return;
        }
        Drawable cHq_ = nrf.cHq_(bkn_);
        this.c = cHq_;
        this.m = nrz.cKm_(BaseApplication.e(), cHq_);
    }

    private Bitmap bkm_(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate(270.0f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private Drawable bkl_() {
        int i = this.f;
        if (i == 1) {
            return this.c;
        }
        if (i == 0) {
            return this.m;
        }
        return this.c;
    }

    private List<Drawable> i() {
        int i = this.f;
        if (i == 1) {
            return this.h;
        }
        if (i == 0) {
            return this.f13244a;
        }
        return this.h;
    }

    private void c() {
        if (this.b == null) {
            LogUtil.h("Track_BendSuggestionAnimHelper", "animationDrawableForBend view == null");
            return;
        }
        if (this.l == this.f && this.mAnimContainer.getVisibility() == 0) {
            return;
        }
        this.mAnimContainer.setVisibility(0);
        AnimationDrawable animationDrawable = this.d;
        if (animationDrawable != null && this.l == this.f) {
            animationDrawable.start();
            return;
        }
        o();
        this.d = null;
        this.d = new AnimationDrawable();
        this.l = this.f;
        for (Drawable drawable : i()) {
            if (drawable != null) {
                this.d.addFrame(drawable, 41);
            }
        }
        this.d.setOneShot(false);
        this.b.setBackground(this.d);
        this.d.start();
    }

    private Bitmap bkn_(String str) {
        String c = gyr.e().c(str, "png");
        LogUtil.a("Track_BendSuggestionAnimHelper", "getSportExamBitmap() imagePath: ", c);
        Bitmap aWN_ = gyr.e().aWN_(c);
        return this.n ? bkm_(aWN_) : aWN_;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public void startAnimation() {
        super.startAnimation();
        if (this.mAnimContainer == null) {
            LogUtil.h("Track_BendSuggestionAnimHelper", "mAnimContainer == null");
            return;
        }
        if (this.i == null) {
            LogUtil.h("Track_BendSuggestionAnimHelper", "mBodyPoint == null");
            return;
        }
        d();
        e();
        c();
        final int b = (int) this.i.b();
        final int a2 = (int) this.i.a();
        if (this.n) {
            b = (int) this.i.g();
            a2 = (int) this.i.j();
        }
        this.b.post(new Runnable() { // from class: hma.1
            @Override // java.lang.Runnable
            public void run() {
                hma.this.mAnimContainer.setTranslationX((b - ((hma.this.mAnimContainer.getWidth() / 2) + hma.this.mAnimContainer.getLeft())) + (hma.this.n ? hma.this.b.getWidth() / 2 : 0));
                hma.this.mAnimContainer.setTranslationY(a2 - (hma.this.mAnimContainer.getTop() + hma.this.mAnimContainer.getHeight()));
            }
        });
    }

    private void d() {
        LogUtil.a("Track_BendSuggestionAnimHelper", "HipY:", Float.valueOf(this.i.j()), "AnkleY:", Float.valueOf(this.i.a()));
        if (this.n) {
            this.b.getLayoutParams().height = -2;
            this.b.getLayoutParams().width = (int) Math.abs(this.i.b() - this.i.g());
        } else {
            this.b.getLayoutParams().height = (int) Math.abs(this.i.j() - this.i.a());
            this.b.getLayoutParams().width = -2;
        }
        this.b.requestLayout();
    }

    private void e() {
        if (!(this.mAnimContainer instanceof ConstraintLayout)) {
            LogUtil.h("Track_BendSuggestionAnimHelper", "mAnimContainer not instanceof ConstraintLayout");
            return;
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone((ConstraintLayout) this.mAnimContainer);
        if (this.f == 1) {
            constraintSet.connect(R.id.flexion_arrow_img, 6, R.id.flexion_knee_img, 6);
        } else {
            constraintSet.connect(R.id.flexion_arrow_img, 6, R.id.flexion_knee_img, 7);
        }
        constraintSet.applyTo((ConstraintLayout) this.mAnimContainer);
    }

    private void o() {
        AnimatorSet bhy_ = hka.bhy_(this.f, this.n);
        ImageView imageView = this.e;
        if (imageView != null) {
            imageView.setBackground(bkl_());
            bhy_.setTarget(this.e);
            bhy_.start();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public void startAnimation(Object obj) {
        if (obj == null) {
            LogUtil.h("Track_BendSuggestionAnimHelper", "startAnimation input data is null, pls check.");
        } else if (obj instanceof kxa) {
            kxa kxaVar = (kxa) obj;
            this.i = kxaVar;
            this.f = kxaVar.e();
            startAnimation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.mAnimContainer != null) {
            this.mAnimContainer.setVisibility(8);
        }
    }

    public void b() {
        Handler handler = this.g;
        if (handler == null) {
            LogUtil.h("Track_BendSuggestionAnimHelper", "sendHideAnimLayoutMsg mHandler == null");
            return;
        }
        handler.removeMessages(1);
        Handler handler2 = this.g;
        handler2.sendMessage(handler2.obtainMessage(1));
    }

    static class d extends BaseHandler<hma> {
        d(Looper looper, hma hmaVar) {
            super(looper, hmaVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: bko_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(hma hmaVar, Message message) {
            if (message == null) {
                LogUtil.h("Track_BendSuggestionAnimHelper", "handleMessage message is null");
            } else {
                if (message.what != 1) {
                    return;
                }
                hmaVar.f();
            }
        }
    }

    public void a() {
        List<Drawable> list = this.h;
        if (list != null) {
            list.clear();
            this.h = null;
        }
        List<Drawable> list2 = this.f13244a;
        if (list2 != null) {
            list2.clear();
            this.f13244a = null;
        }
        this.d = null;
    }

    public void d(boolean z) {
        this.n = z;
    }
}
