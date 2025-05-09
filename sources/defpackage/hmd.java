package defpackage;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class hmd extends AnimBaseHelper {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f13245a;
    private AnimationDrawable b;
    private List<Drawable> c;
    private int d;
    private List<Drawable> e;
    private boolean f;
    private String g;
    private int h;
    private Handler i;
    private int j;

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public boolean isAnimRunning() {
        return false;
    }

    public hmd(View view) {
        super(view);
        this.c = new ArrayList();
        this.e = new ArrayList();
        this.g = "zh-CN";
        this.f = false;
        this.i = new b(Looper.getMainLooper(), this);
        this.d = -1;
        this.h = 1;
        if (view instanceof ImageView) {
            this.f13245a = (ImageView) view;
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public void initResource() {
        if (this.f) {
            return;
        }
        this.f = true;
        this.c.clear();
        this.e.clear();
        b();
    }

    private void b() {
        for (int i = 0; i < 16; i++) {
            Bitmap bkq_ = bkq_("frame_bend_" + i);
            if (bkq_ != null) {
                Drawable cHq_ = nrf.cHq_(bkq_);
                if (cHq_ != null) {
                    this.c.add(cHq_);
                }
                BitmapDrawable cKm_ = nrz.cKm_(BaseApplication.e(), cHq_);
                if (cKm_ != null) {
                    this.e.add(cKm_);
                }
            }
        }
    }

    private List<Drawable> e() {
        if (this.d == 0) {
            return this.e;
        }
        return this.c;
    }

    private void bkp_(View view) {
        if (view == null) {
            LogUtil.h("Track_BendSuggestionAnimHelper", "animationDrawableForBend view == null");
            return;
        }
        AnimationDrawable animationDrawable = this.b;
        if (animationDrawable != null && this.h == this.d) {
            animationDrawable.start();
            return;
        }
        this.b = null;
        this.b = new AnimationDrawable();
        this.h = this.d;
        if (koq.b(e())) {
            LogUtil.h("Track_BendSuggestionAnimHelper", "getOriginalRes null");
            return;
        }
        for (int i = 0; i < e().size(); i++) {
            Drawable drawable = e().get(i);
            if (drawable != null) {
                this.b.addFrame(drawable, 66);
            }
        }
        this.b.setOneShot(false);
        view.setBackground(this.b);
        this.b.start();
    }

    private Bitmap bkq_(String str) {
        String a2 = gyr.e().a(str, "png", this.g);
        LogUtil.a("Track_BendSuggestionAnimHelper", "getSportExamBitmap() imagePath: ", a2);
        return gyr.e().aWN_(a2);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public void startAnimation() {
        super.startAnimation();
        ImageView imageView = this.f13245a;
        if (imageView == null) {
            LogUtil.h("Track_BendSuggestionAnimHelper", "mLightWareView == null || mLightBeamView == null");
        } else {
            if (this.j < 40) {
                bkp_(imageView);
                return;
            }
            this.i.removeMessages(1);
            Handler handler = this.i;
            handler.sendMessage(handler.obtainMessage(1));
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public void startAnimation(Object obj) {
        if (obj == null) {
            LogUtil.h("Track_BendSuggestionAnimHelper", "startAnimation input data is null, pls check.");
            return;
        }
        if (obj instanceof Pair) {
            Pair pair = (Pair) obj;
            LogUtil.a("Track_BendSuggestionAnimHelper", "startAnimation mBodyDirection:", Integer.valueOf(this.d));
            this.d = ((Integer) pair.first).intValue();
            int intValue = ((Integer) pair.second).intValue();
            this.j = intValue;
            if (intValue < 40) {
                this.f13245a.setVisibility(0);
                startAnimation();
            } else {
                this.i.removeMessages(1);
                Handler handler = this.i;
                handler.sendMessage(handler.obtainMessage(1));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        AnimationDrawable animationDrawable = this.b;
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
        ImageView imageView = this.f13245a;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
    }

    public void d() {
        Handler handler = this.i;
        if (handler == null) {
            LogUtil.h("Track_BendSuggestionAnimHelper", "sendHideAnimLayoutMsg mHandler == null");
            return;
        }
        handler.removeMessages(1);
        Handler handler2 = this.i;
        handler2.sendMessage(handler2.obtainMessage(1));
    }

    static class b extends BaseHandler<hmd> {
        b(Looper looper, hmd hmdVar) {
            super(looper, hmdVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: bkr_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(hmd hmdVar, Message message) {
            if (message == null) {
                LogUtil.h("Track_BendSuggestionAnimHelper", "handleMessage message is null");
            } else if (message.what == 1) {
                hmdVar.c();
            }
        }
    }

    public void a() {
        List<Drawable> list = this.c;
        if (list != null) {
            list.clear();
            this.c = null;
        }
        List<Drawable> list2 = this.e;
        if (list2 != null) {
            list2.clear();
            this.e = null;
        }
        this.b = null;
    }
}
