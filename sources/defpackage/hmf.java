package defpackage;

import android.animation.AnimatorSet;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class hmf extends AnimBaseHelper {

    /* renamed from: a, reason: collision with root package name */
    private String f13246a;
    private Handler b;
    private boolean c;
    private AnimationDrawable d;
    private kxa e;
    private int f;
    private int g;
    private int h;
    private ViewGroup i;
    private ImageView j;
    private List<Drawable> o;

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public boolean isAnimRunning() {
        return false;
    }

    public hmf(View view, View view2) {
        super(view);
        this.o = new ArrayList();
        this.f13246a = "zh-CN";
        this.c = false;
        this.b = new e(Looper.getMainLooper(), this);
        if (view instanceof ViewGroup) {
            this.i = (ViewGroup) view;
        }
        if (view2 instanceof ImageView) {
            this.j = (ImageView) view2;
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public void initResource() {
        if (this.c) {
            return;
        }
        this.c = true;
        this.o.clear();
        this.o = b();
        Bitmap bkN_ = bkN_("light_beam", "webp");
        Bitmap bkN_2 = bkN_("light_beam_bottom", "webp");
        ImageView imageView = (ImageView) this.i.findViewById(R.id.flexion_beam_animation_img);
        ImageView imageView2 = (ImageView) this.i.findViewById(R.id.flexion_beam_animation_img_bottom);
        if (imageView != null) {
            imageView.setImageBitmap(bkN_);
            imageView2.setImageBitmap(bkN_2);
        }
    }

    private List<Drawable> b() {
        Drawable cHq_;
        for (int i = 0; i < 35; i++) {
            Bitmap bkN_ = bkN_("frame_light_ware_" + i, "png");
            if (bkN_ != null && (cHq_ = nrf.cHq_(bkN_)) != null) {
                this.o.add(cHq_);
            }
        }
        return this.o;
    }

    private void bkM_(View view) {
        if (view == null) {
            LogUtil.h("Track_LightAnimHelper", "animationDrawableForWare view == null");
            return;
        }
        AnimationDrawable animationDrawable = this.d;
        if (animationDrawable != null) {
            animationDrawable.start();
            return;
        }
        this.d = new AnimationDrawable();
        for (Drawable drawable : b()) {
            if (drawable != null) {
                this.d.addFrame(drawable, 41);
            }
        }
        this.d.setOneShot(false);
        view.setBackground(this.d);
    }

    private Bitmap bkN_(String str, String str2) {
        String c = gyr.e().c(str, str2);
        LogUtil.a("Track_LightAnimHelper", "getSportExamBitmap() imagePath: ", c);
        return gyr.e().aWN_(c);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public void startAnimation() {
        super.startAnimation();
        ImageView imageView = this.j;
        if (imageView == null || this.i == null) {
            LogUtil.h("Track_LightAnimHelper", "mLightWareView == null || mLightBeamView == null");
            return;
        }
        if (this.g >= 40) {
            bkM_(imageView);
        }
        LogUtil.a("Track_LightAnimHelper", "width:", Integer.valueOf(this.i.getWidth()), "height:", Integer.valueOf(this.i.getHeight()), "top:", Integer.valueOf(this.i.getTop()), "left:", Integer.valueOf(this.i.getLeft()));
        this.i.setTranslationX(this.f - ((r0.getWidth() / 2) + this.i.getLeft()));
        this.i.setTranslationY(this.h - (r0.getHeight() + this.i.getTop()));
        this.j.setTranslationX(this.f - ((this.i.getWidth() / 2) + this.i.getLeft()));
        this.j.setTranslationY((this.h - (this.i.getTop() + (this.i.getHeight() / 2))) - 50);
        if (this.g >= 70) {
            c();
            return;
        }
        this.b.removeMessages(1);
        Handler handler = this.b;
        handler.sendMessage(handler.obtainMessage(1));
    }

    private void c() {
        ViewGroup viewGroup = this.i;
        if (viewGroup != null) {
            viewGroup.setVisibility(0);
            AnimatorSet bhw_ = hka.bhw_();
            bhw_.setTarget(this.i);
            bhw_.start();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public void startAnimation(Object obj) {
        if (obj == null) {
            LogUtil.h("Track_LightAnimHelper", "startAnimation input data is null, pls check.");
            return;
        }
        if (obj instanceof Pair) {
            Pair pair = (Pair) obj;
            this.e = (kxa) pair.first;
            int intValue = ((Integer) pair.second).intValue();
            this.g = intValue;
            if (intValue >= 40) {
                this.j.setVisibility(0);
                e();
                startAnimation();
            } else {
                this.b.removeMessages(1);
                Handler handler = this.b;
                handler.sendMessage(handler.obtainMessage(1));
            }
        }
    }

    private void e() {
        kxa kxaVar = this.e;
        if (kxaVar == null) {
            LogUtil.h("Track_LightAnimHelper", "mBodyPoint is null");
            return;
        }
        this.f = (int) kxaVar.i();
        this.h = (int) this.e.h();
        LogUtil.a("Track_LightAnimHelper", "initLightLocation mPositionX: ", Integer.valueOf(this.f), "mPositionY: ", Integer.valueOf(this.h));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        AnimationDrawable animationDrawable = this.d;
        if (animationDrawable != null && animationDrawable.isRunning()) {
            this.d.stop();
        }
        ImageView imageView = this.j;
        if (imageView != null && this.g < 40) {
            imageView.setVisibility(4);
        }
        ViewGroup viewGroup = this.i;
        if (viewGroup == null || this.g >= 70) {
            return;
        }
        viewGroup.setVisibility(4);
    }

    static class e extends BaseHandler<hmf> {
        e(Looper looper, hmf hmfVar) {
            super(looper, hmfVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: bkO_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(hmf hmfVar, Message message) {
            if (message == null) {
                LogUtil.h("Track_LightAnimHelper", "handleMessage message is null");
            } else if (message.what == 1) {
                hmfVar.a();
            }
        }
    }

    public void d() {
        List<Drawable> list = this.o;
        if (list != null) {
            list.clear();
            this.o = null;
        }
        this.d = null;
    }
}
