package defpackage;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.swiperefreshlayout.HealthSwipeRefreshLayout;
import com.huawei.ui.homehealth.threecirclecard.IAnimationCompletedCallback;
import health.compact.a.util.LogUtil;
import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes6.dex */
public class oum {
    public static boolean b = false;
    public static long d;

    /* renamed from: a, reason: collision with root package name */
    private String f15960a;
    private int c;
    private ImageView f;
    private LinearLayout g;
    private HealthSwipeRefreshLayout h;
    private ImageView i;
    private WeakReference<Context> j;
    private ImageView l;
    private int m;
    private Bitmap n;
    private Bitmap o;
    private int p;
    private int r;
    private int t;
    private int w;
    private PathInterpolator s = new PathInterpolator(0.3f, 0.0f, 0.5f, 0.0f);
    private PathInterpolator q = new PathInterpolator(0.0f, 0.3f, 0.0f, 0.5f);
    private float k = c().getResources().getDimensionPixelOffset(R.dimen._2131362922_res_0x7f0a046a);
    private int y = nrr.e(c(), 12.0f);
    private int e = nrr.e(c(), 12.0f);

    public oum(Context context, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, ImageView imageView3, HealthSwipeRefreshLayout healthSwipeRefreshLayout) {
        this.j = new WeakReference<>(context);
        this.i = imageView;
        this.f = imageView2;
        this.l = imageView3;
        this.g = linearLayout;
        this.h = healthSwipeRefreshLayout;
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
        this.w = dimensionPixelSize + ((Integer) safeRegionWidth.first).intValue();
        this.c = dimensionPixelSize2 + ((Integer) safeRegionWidth.second).intValue();
    }

    public void a(HealthRecycleView healthRecycleView, ovg ovgVar, int i, IAnimationCompletedCallback iAnimationCompletedCallback) {
        Bitmap dhP_ = dhP_(healthRecycleView, 1);
        if (dhP_ == null) {
            LogUtil.e("StepsSwitchAnimateManager", "bitmap is null");
            d(ovgVar);
            return;
        }
        Bitmap dhM_ = dhM_(dhN_(dhP_));
        this.n = dhM_;
        this.p = dhM_.getWidth();
        int height = this.n.getHeight();
        this.r = height;
        a(this.p, height, i);
        this.l.setVisibility(0);
        this.l.setImageBitmap(this.n);
        e(this.p, this.r, i);
        this.i.setAlpha(1.0f);
        this.i.setImageBitmap(null);
        this.i.setBackground(null);
        this.i.setVisibility(0);
        d();
        this.f.setVisibility(0);
        iAnimationCompletedCallback.onCompleted(null);
    }

    public void d(final HealthRecycleView healthRecycleView, final ovg ovgVar, final int i, String str) {
        LogUtil.d("StepsSwitchAnimateManager", "startAnimator");
        this.f15960a = str;
        final CountDownLatch countDownLatch = new CountDownLatch(3);
        this.l.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: oum.4
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                oum.this.l.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                oum.this.a(countDownLatch, ovgVar, i, healthRecycleView);
            }
        });
        this.i.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: oum.5
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                oum.this.i.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                oum.this.a(countDownLatch, ovgVar, i, healthRecycleView);
            }
        });
        this.f.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: oum.3
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                oum.this.f.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                oum.this.a(countDownLatch, ovgVar, i, healthRecycleView);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Context c() {
        WeakReference<Context> weakReference = this.j;
        if (weakReference == null) {
            return BaseApplication.e();
        }
        Context context = weakReference.get();
        return context == null ? BaseApplication.e() : context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(CountDownLatch countDownLatch, ovg ovgVar, int i, HealthRecycleView healthRecycleView) {
        countDownLatch.countDown();
        if (countDownLatch.getCount() == 0) {
            c(ovgVar, i, healthRecycleView);
        }
    }

    private void c(final ovg ovgVar, final int i, final HealthRecycleView healthRecycleView) {
        Bitmap bitmap = this.n;
        if (bitmap == null || bitmap.isRecycled()) {
            LogUtil.c("StepsSwitchAnimateManager", "startPreAnimator mPreBitmap is null or recycled");
            return;
        }
        Bitmap dhR_ = dhR_(this.n);
        int i2 = this.p;
        int i3 = this.r;
        ImageView dhQ_ = dhQ_(dhS_(dhR_, i2, i3, i3), i);
        dhV_(this.g, dhQ_);
        dhQ_.setCameraDistance(nsn.g(c()) * 10000.0f);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(dhQ_, "rotationX", 180.0f, 90.0f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(dhQ_, "alpha", 1.0f, 0.0f);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.f, "alpha", 1.0f, 0.0f);
        ofFloat.setInterpolator(this.s);
        ofFloat2.setInterpolator(this.s);
        ofFloat3.setInterpolator(this.s);
        ofFloat.addListener(new Animator.AnimatorListener() { // from class: oum.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                LogUtil.d("StepsSwitchAnimateManager", "startAnimator onAnimationStart");
                HandlerExecutor.d(new Runnable() { // from class: oum.1.5
                    @Override // java.lang.Runnable
                    public void run() {
                        oum.this.b(ovgVar);
                        if (oum.this.c(ovgVar, healthRecycleView)) {
                            oum.this.a(oum.this.t, oum.this.m, i);
                            LogUtil.d("StepsSwitchAnimateManager", "startPreAnimator nextBitmapHeight=", Integer.valueOf(oum.this.m));
                            oum.this.i.setBackground(oum.this.c().getResources().getDrawable(R$drawable.three_circle_animator_bg));
                            oum.this.l.setBackground(oum.this.c().getResources().getDrawable(R$drawable.three_circle_animator_bg_whole));
                            oum.this.l.setImageBitmap(null);
                            return;
                        }
                        LogUtil.c("StepsSwitchAnimateManager", "startPreAnimator getNextBitmapFail. return");
                    }
                }, 50L);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                LogUtil.d("StepsSwitchAnimateManager", "startAnimator onAnimationEnd");
                oum.this.b(ovgVar, i, healthRecycleView);
            }
        });
        ofFloat.setDuration(500L).start();
        ofFloat2.setDuration(500L).start();
        ofFloat3.setDuration(500L).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(ovg ovgVar, HealthRecycleView healthRecycleView) {
        Bitmap dhP_ = dhP_(healthRecycleView, 1);
        if (dhP_ == null) {
            LogUtil.e("StepsSwitchAnimateManager", "bitmap is null");
            d(ovgVar);
            return false;
        }
        Bitmap dhM_ = dhM_(dhN_(dhP_));
        this.o = dhM_;
        this.t = dhM_.getWidth();
        this.m = this.o.getHeight();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(ovg ovgVar) {
        if (ovgVar == null) {
            LogUtil.c("StepsSwitchAnimateManager", "notifyCardChanged twoModelCardData is null");
        } else if ("threeCircleCard".equals(this.f15960a)) {
            ovgVar.d("threeLeafCard", false);
        } else {
            ovgVar.d("threeCircleCard", false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final ovg ovgVar, int i, HealthRecycleView healthRecycleView) {
        LogUtil.d("StepsSwitchAnimateManager", "startNextAnimator");
        if (this.o == null && !c(ovgVar, healthRecycleView)) {
            LogUtil.c("StepsSwitchAnimateManager", "getNextBitmapFail. return");
            return;
        }
        a(this.t, this.m, i);
        e(this.t, this.m, i);
        LogUtil.d("StepsSwitchAnimateManager", "startNextAnimator nextBitmapHeight=", Integer.valueOf(this.m));
        ImageView dhQ_ = dhQ_(dhO_(this.o), i);
        dhV_(this.g, dhQ_);
        dhQ_.setCameraDistance(nsn.g(c()) * 10000.0f);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(dhQ_, "rotationX", 90.0f, 0.0f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(dhQ_, "alpha", 0.01f, 1.0f);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.i, "alpha", 0.01f, 1.0f);
        ofFloat.setInterpolator(this.q);
        ofFloat2.setInterpolator(this.q);
        ofFloat3.setInterpolator(this.q);
        ofFloat.addListener(new Animator.AnimatorListener() { // from class: oum.2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                LogUtil.d("StepsSwitchAnimateManager", "startNextAnimator onAnimationStart");
                oum.this.i.setAlpha(0.01f);
                if (!oum.this.o.isRecycled()) {
                    oum.this.b();
                    oum.this.f.setAlpha(1.0f);
                    oum.this.f.setImageBitmap(null);
                    oum.this.f.setBackground(oum.this.c().getResources().getDrawable(R$drawable.three_circle_animator_bg_bottom));
                    return;
                }
                LogUtil.c("StepsSwitchAnimateManager", "onAnimationStart reset");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                LogUtil.d("StepsSwitchAnimateManager", "startNextAnimator onAnimationEnd");
                oum.this.d(ovgVar);
            }
        });
        ofFloat.setDuration(800L).start();
        ofFloat2.setDuration(800L).start();
        ofFloat3.setDuration(800L).start();
    }

    public void d(ovg ovgVar) {
        this.i.setVisibility(8);
        this.f.setVisibility(8);
        this.g.setVisibility(8);
        this.l.setVisibility(8);
        HealthSwipeRefreshLayout healthSwipeRefreshLayout = this.h;
        if (healthSwipeRefreshLayout != null) {
            healthSwipeRefreshLayout.setInterceptTouchEvent(false);
        }
        if (ovgVar != null) {
            ovgVar.k();
        }
        dhT_(this.n);
        dhT_(this.o);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        int i = this.t;
        int i2 = (int) (this.m / 2.0f);
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        new Canvas(createBitmap).drawBitmap(this.o, new Rect(0, 0, i, i2), new Rect(0, 0, i, i2), (Paint) null);
        this.i.setImageBitmap(createBitmap);
    }

    private void d() {
        int i = this.p;
        int i2 = (int) (this.r / 2.0f);
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        if (this.n.isRecycled()) {
            LogUtil.c("StepsSwitchAnimateManager", "drawBitmapToImageView2 mPreBitmap has been recycled.");
        } else {
            canvas.drawBitmap(this.n, new Rect(0, i2, i, i2 * 2), new Rect(0, 0, i, i2), (Paint) null);
            this.f.setImageBitmap(createBitmap);
        }
    }

    private Bitmap dhP_(HealthRecycleView healthRecycleView, int i) {
        if (healthRecycleView == null) {
            LogUtil.c("StepsSwitchAnimateManager", "getFirstBitmap recyclerView is null");
            return null;
        }
        RecyclerView.LayoutManager layoutManager = healthRecycleView.getLayoutManager();
        if (layoutManager == null) {
            LogUtil.e("StepsSwitchAnimateManager", "layoutManager is null");
            return null;
        }
        View findViewByPosition = layoutManager.findViewByPosition(i);
        if (findViewByPosition == null) {
            LogUtil.e("StepsSwitchAnimateManager", "item is null");
            return null;
        }
        findViewByPosition.setDrawingCacheEnabled(true);
        Bitmap drawingCache = findViewByPosition.getDrawingCache();
        if (drawingCache == null) {
            LogUtil.e("StepsSwitchAnimateManager", "drawingCache is null");
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(drawingCache);
        findViewByPosition.setDrawingCacheEnabled(false);
        return createBitmap;
    }

    private Bitmap dhM_(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setShader(bitmapShader);
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.save();
        RectF rectF = new RectF(0.0f, 0.0f, width, height);
        float f = this.k;
        canvas.drawRoundRect(rectF, f, f, paint);
        canvas.restore();
        dhT_(bitmap);
        return createBitmap;
    }

    private ImageView dhQ_(Bitmap bitmap, int i) {
        ImageView imageView = new ImageView(c());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(bitmap.getWidth(), bitmap.getHeight());
        layoutParams.setMargins(this.w, i + this.y, this.c, 0);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageBitmap(bitmap);
        return imageView;
    }

    private void dhV_(ViewGroup viewGroup, ImageView imageView) {
        viewGroup.removeAllViews();
        viewGroup.requestLayout();
        viewGroup.addView(imageView);
        viewGroup.invalidate();
        viewGroup.setVisibility(0);
    }

    private void e(int i, int i2, int i3) {
        dhU_(this.i, i, i2, true, i3);
        dhU_(this.f, i, i2, false, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2, int i3) {
        ViewGroup.LayoutParams layoutParams = this.l.getLayoutParams();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
            layoutParams2.width = i;
            layoutParams2.height = i2;
            layoutParams2.setMargins(this.w, this.y + i3, this.c, this.e);
            this.l.setLayoutParams(layoutParams2);
        }
    }

    private void dhU_(ImageView imageView, int i, int i2, boolean z, int i3) {
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.width = i;
            layoutParams2.height = (int) (i2 / 2.0f);
            if (z) {
                layoutParams2.setMargins(this.w, this.y + i3, this.c, 0);
            } else {
                layoutParams2.setMargins(this.w, nrr.e(c(), 0.0f), this.c, 0);
            }
            imageView.setLayoutParams(layoutParams2);
        }
    }

    private Bitmap dhR_(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Matrix matrix = new Matrix();
        matrix.postScale(1.0f, -1.0f);
        canvas.drawBitmap(Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true), new Rect(0, 0, width, height), new Rect(0, 0, width, height), (Paint) null);
        return createBitmap;
    }

    private Bitmap dhS_(Bitmap bitmap, int i, int i2, int i3) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i3, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        if (i > bitmap.getWidth()) {
            i = bitmap.getWidth();
        }
        if (i2 > bitmap.getHeight()) {
            i2 = bitmap.getHeight();
        }
        canvas.drawBitmap(bitmap, new Rect(0, (int) (i2 / 2.0f), i, i2), new Rect(0, (int) (i3 / 2.0f), i, i3), (Paint) null);
        return createBitmap;
    }

    private Bitmap dhN_(Bitmap bitmap) {
        int height = (bitmap.getHeight() - this.y) - this.e;
        int width = (bitmap.getWidth() - this.w) - this.c;
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.save();
        int i = this.w;
        int i2 = this.y;
        canvas.drawBitmap(bitmap, new Rect(i, i2, i + width, i2 + height), new Rect(0, 0, width, height), (Paint) null);
        canvas.restore();
        dhT_(bitmap);
        return createBitmap;
    }

    private void dhT_(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        bitmap.recycle();
    }

    private Bitmap dhO_(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        if (bitmap.isRecycled()) {
            LogUtil.e("StepsSwitchAnimateManager", "getBottomHalfBitmap failed, cause bitmap is recycled!");
            return createBitmap;
        }
        int i = (int) (height / 2.0f);
        new Canvas(createBitmap).drawBitmap(bitmap, new Rect(0, i, width, height), new Rect(0, i, width, height), (Paint) null);
        return createBitmap;
    }
}
