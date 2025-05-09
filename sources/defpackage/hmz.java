package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LanguageUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class hmz extends AnimBaseHelper {

    /* renamed from: a, reason: collision with root package name */
    private AnimationSet f13261a;
    private boolean b;
    private int c;
    private int d;
    private ViewGroup e;
    private String f;
    private boolean g;
    private int h;
    private String i;
    private int j;
    private int k;
    private Map<String, Bitmap> n;

    public hmz(View view) {
        super(view);
        this.n = new HashMap();
        this.i = "zh-CN";
        this.b = false;
        this.g = false;
        if (view instanceof ViewGroup) {
            this.e = (ViewGroup) view;
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public void initResource() {
        if (e()) {
            this.b = true;
            d();
            c();
        }
    }

    private void c() {
        this.n.clear();
        for (int i = 0; i < 10; i++) {
            Bitmap bly_ = bly_("pic_num_" + i);
            if (bly_ != null) {
                this.n.put(String.valueOf(i), bly_);
            }
        }
        Bitmap bly_2 = bly_("pic_x");
        if (bly_2 != null) {
            this.n.put("x", bly_2);
        }
        Bitmap bly_3 = bly_("pic_continuity");
        if (bly_3 != null) {
            this.n.put(HwExerciseConstants.JSON_NAME_LONGEST_STREAK, bly_3);
        }
    }

    private Bitmap bly_(String str) {
        String d = gyn.d().d(str, "png", this.i);
        LogUtil.a("Track_SkipContinuesAnimHelper", "getSkipBitmap() imagePath: ", d);
        return gyn.d().aWQ_(d);
    }

    private void d() {
        if ("zh-CN".equals(this.i)) {
            this.c = -24;
            this.k = -34;
            this.h = -40;
            this.d = 96;
            return;
        }
        this.c = -16;
        this.k = -22;
        this.h = -24;
        this.d = 60;
    }

    private boolean e() {
        String str = LanguageUtil.j(BaseApplication.e()) ? "zh-CN" : "en";
        if (this.b && str.equals(this.i)) {
            return false;
        }
        this.i = str;
        return true;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public void startAnimation() {
        ViewGroup.MarginLayoutParams blw_;
        super.startAnimation();
        ViewGroup viewGroup = this.e;
        if (viewGroup == null || this.f == null) {
            LogUtil.h("Track_SkipContinuesAnimHelper", "mContainerViewGroup or mNumStr is null, pls check.");
            return;
        }
        viewGroup.removeAllViews();
        ImageView blx_ = blx_(this.e.getContext());
        Bitmap bitmap = this.n.get(HwExerciseConstants.JSON_NAME_LONGEST_STREAK);
        if (bitmap == null) {
            LogUtil.h("Track_SkipContinuesAnimHelper", "longestStreakBitmap is null, pls check.");
            return;
        }
        blx_.setImageBitmap(bitmap);
        this.e.addView(blx_, blw_(0, this.j));
        ImageView blx_2 = blx_(this.e.getContext());
        Bitmap bitmap2 = this.n.get("x");
        if (bitmap2 == null) {
            LogUtil.h("Track_SkipContinuesAnimHelper", "joinerBitmap is null, pls check.");
            return;
        }
        blx_2.setImageBitmap(bitmap2);
        ViewGroup viewGroup2 = this.e;
        viewGroup2.addView(blx_2, blw_(nsn.c(viewGroup2.getContext(), this.c), this.j));
        for (int i = 0; i < this.f.length(); i++) {
            char charAt = this.f.charAt(i);
            ImageView blx_3 = blx_(this.e.getContext());
            Bitmap bitmap3 = this.n.get(charAt + "");
            if (bitmap3 == null) {
                LogUtil.h("Track_SkipContinuesAnimHelper", "numBitmap is null, pls check. key:", Character.valueOf(charAt));
                return;
            }
            blx_3.setImageBitmap(bitmap3);
            if (i == 0) {
                blw_ = blw_(nsn.c(this.e.getContext(), this.k), this.j);
            } else {
                blw_ = blw_(nsn.c(this.e.getContext(), this.h), this.j);
            }
            this.e.addView(blx_3, blw_);
        }
        this.e.setVisibility(0);
        this.e.startAnimation(blv_());
    }

    private ImageView blx_(Context context) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setAdjustViewBounds(true);
        imageView.setMaxHeight(this.j);
        imageView.setMaxWidth(this.j * 5);
        return imageView;
    }

    private ViewGroup.MarginLayoutParams blw_(int i, int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-2, i2);
        marginLayoutParams.setMarginStart(i);
        marginLayoutParams.setMarginEnd(0);
        return marginLayoutParams;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public void startAnimation(Object obj) {
        if (obj instanceof String) {
            this.f = (String) obj;
        }
        String str = this.f;
        if (str != null && str.length() > 4) {
            this.j = nsn.c(BaseApplication.e(), (this.d * 2.0f) / 3.0f);
        } else {
            this.j = nsn.c(BaseApplication.e(), this.d);
        }
        startAnimation();
    }

    private AnimationSet blv_() {
        if (this.f13261a == null) {
            AnimationSet bhg_ = hjx.bhg_();
            this.f13261a = bhg_;
            bhg_.setAnimationListener(new Animation.AnimationListener() { // from class: hmz.4
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    hmz.this.g = true;
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    hmz.this.g = false;
                    hmz.this.e.setVisibility(8);
                }
            });
        }
        return this.f13261a;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public boolean isAnimRunning() {
        return this.g;
    }
}
