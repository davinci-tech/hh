package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class hmk extends AnimBaseHelper {
    private static final int b = nsn.c(BaseApplication.e(), 80.0f);

    /* renamed from: a, reason: collision with root package name */
    private AnimationSet f13251a;
    private boolean c;
    private ViewGroup d;
    private boolean e;
    private final Map<String, Bitmap> f;
    private String h;
    private boolean j;

    public hmk(View view) {
        super(view);
        this.f = new HashMap();
        this.e = false;
        this.c = false;
        this.j = false;
        if (view instanceof ViewGroup) {
            this.d = (ViewGroup) view;
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public void initResource() {
        if (this.e) {
            return;
        }
        this.e = true;
        c();
    }

    private void c() {
        this.f.clear();
        for (int i = 0; i < 10; i++) {
            Bitmap aWO_ = gyr.e().aWO_("pic_num_" + i);
            if (aWO_ != null) {
                this.f.put(String.valueOf(i), aWO_);
            }
        }
        String str = this.j ? "public_cm" : "pic_x";
        Bitmap aWO_2 = gyr.e().aWO_(str);
        if (aWO_2 != null) {
            this.f.put(str, aWO_2);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public void startAnimation() {
        Bitmap bitmap;
        super.startAnimation();
        ViewGroup viewGroup = this.d;
        if (viewGroup == null || this.h == null) {
            LogUtil.h("Track_NumberAnimHelper", "mContainerViewGroup or mNumStr is null, pls check.");
            return;
        }
        viewGroup.removeAllViews();
        ImageView bkT_ = bkT_(this.d.getContext());
        if (this.j) {
            bitmap = this.f.get("public_cm");
        } else {
            bitmap = this.f.get("pic_x");
        }
        if (bitmap == null) {
            LogUtil.h("Track_NumberAnimHelper", "joinerBitmap is null, pls check.");
            return;
        }
        bkT_.setImageBitmap(bitmap);
        if (!this.j) {
            this.d.addView(bkT_, bkR_());
        }
        for (int i = 0; i < this.h.length(); i++) {
            char charAt = this.h.charAt(i);
            ImageView bkT_2 = bkT_(this.d.getContext());
            Bitmap bitmap2 = this.f.get(String.valueOf(charAt));
            if (bitmap2 == null) {
                LogUtil.h("Track_NumberAnimHelper", "numBitmap is null, pls check. key:", Character.valueOf(charAt));
            } else {
                bkT_2.setImageBitmap(bitmap2);
                this.d.addView(bkT_2, bkR_());
            }
        }
        bkU_(bkT_);
        this.d.setVisibility(0);
        this.d.startAnimation(bkQ_());
    }

    private void bkU_(ImageView imageView) {
        if (this.j) {
            this.d.addView(imageView, bkS_(nsn.c(BaseApplication.e(), 50.0f)));
            if (imageView.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
                layoutParams.gravity = 80;
                imageView.setLayoutParams(layoutParams);
            }
        }
    }

    private ImageView bkT_(Context context) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setAdjustViewBounds(true);
        int i = b;
        imageView.setMaxHeight(i);
        imageView.setMaxWidth(i);
        return imageView;
    }

    private ViewGroup.MarginLayoutParams bkR_() {
        return bkS_(b);
    }

    private ViewGroup.MarginLayoutParams bkS_(int i) {
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-2, i);
        marginLayoutParams.setMarginStart(0);
        marginLayoutParams.setMarginEnd(0);
        return marginLayoutParams;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public void startAnimation(Object obj) {
        if (obj instanceof String) {
            this.h = (String) obj;
        }
        startAnimation();
    }

    private AnimationSet bkQ_() {
        if (this.f13251a == null) {
            e();
            this.f13251a.setAnimationListener(new Animation.AnimationListener() { // from class: hmk.5
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    hmk.this.c = true;
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    hmk.this.c = false;
                    hmk.this.d.setVisibility(8);
                }
            });
        }
        return this.f13251a;
    }

    private void e() {
        if (this.j) {
            this.f13251a = hka.bhu_(-100);
        } else {
            this.f13251a = hjx.bhg_();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public boolean isAnimRunning() {
        return this.c;
    }

    public void b(boolean z) {
        this.j = z;
    }
}
