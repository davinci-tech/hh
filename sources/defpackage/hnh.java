package defpackage;

import android.animation.AnimatorSet;
import android.graphics.Bitmap;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class hnh extends AnimBaseHelper {

    /* renamed from: a, reason: collision with root package name */
    private boolean f13268a;
    private int b;
    private int d;

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public boolean isAnimRunning() {
        return false;
    }

    public hnh(View view) {
        super(view);
        this.f13268a = false;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public void initResource() {
        if (this.f13268a) {
            return;
        }
        this.f13268a = true;
        if (this.mAnimContainer == null) {
            LogUtil.h("Track_SkipRippleAnimHelper", "initResource mAnimContainer is null, pls check.");
            return;
        }
        ImageView imageView = (ImageView) this.mAnimContainer.findViewById(R.id.skip_animation_img);
        String b = gyn.d().b("pic_ripple_big", "png");
        LogUtil.a("Track_SkipRippleAnimHelper", "pic_ripple_big imagePath: ", b);
        Bitmap aWQ_ = gyn.d().aWQ_(b);
        if (aWQ_ == null || imageView == null) {
            return;
        }
        imageView.setImageBitmap(aWQ_);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public void startAnimation() {
        super.startAnimation();
        if (this.mAnimContainer == null) {
            LogUtil.h("Track_SkipRippleAnimHelper", "startAnimation mAnimContainer is null, pls check.");
            return;
        }
        this.mAnimContainer.setTranslationX(this.b - ((this.mAnimContainer.getWidth() / 2) + this.mAnimContainer.getLeft()));
        this.mAnimContainer.setTranslationY(this.d - ((this.mAnimContainer.getHeight() / 2) + this.mAnimContainer.getTop()));
        this.mAnimContainer.setVisibility(0);
        AnimatorSet bhj_ = hjx.bhj_();
        bhj_.setTarget(this.mAnimContainer);
        bhj_.start();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public void startAnimation(Object obj) {
        if (obj instanceof Pair) {
            Pair pair = (Pair) obj;
            this.b = ((Float) pair.first).intValue();
            this.d = ((Float) pair.second).intValue();
            startAnimation();
        }
    }
}
