package defpackage;

import android.animation.AnimatorSet;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper;
import com.huawei.healthcloud.plugintrack.ui.viewholder.ExplodeAnimationHelper;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsportmodel.SkipAchieveType;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class hna extends AnimBaseHelper {

    /* renamed from: a, reason: collision with root package name */
    private Map<Integer, Bitmap> f13262a;
    private Object b;
    private ExplodeAnimationHelper c;
    private ViewGroup d;
    private ImageView e;
    private String f;
    private Handler g;
    private boolean i;
    private List<List<Bitmap>> j;

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public boolean isAnimRunning() {
        return false;
    }

    public hna(View view, View view2) {
        super(view);
        this.f13262a = new HashMap();
        this.j = new ArrayList();
        this.f = "zh-CN";
        this.i = false;
        this.g = new e(Looper.getMainLooper(), this);
        if (view instanceof ImageView) {
            this.e = (ImageView) view;
        }
        if (view2 instanceof ViewGroup) {
            this.d = (ViewGroup) view2;
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public void initResource() {
        Bitmap blz_;
        if (e()) {
            this.i = true;
            this.f13262a.clear();
            for (SkipAchieveType skipAchieveType : SkipAchieveType.values()) {
                if (skipAchieveType != null && skipAchieveType.getPicPath() != null && (blz_ = blz_(skipAchieveType.getPicPath())) != null) {
                    this.f13262a.put(Integer.valueOf(skipAchieveType.getLevel()), blz_);
                }
            }
            this.j.clear();
            this.j.add(b("particle1_00000", "particle1_00001", "particle1_00002", "particle1_00003", "particle1_00004"));
            this.j.add(b("particle2_00000", "particle2_00001", "particle2_00002", "particle2_00003", "particle2_00004"));
            this.j.add(b("particle3_00000"));
            this.j.add(b("particle4_00000"));
            ExplodeAnimationHelper explodeAnimationHelper = new ExplodeAnimationHelper(this.j, this.d, new PointF(nrs.c(BaseApplication.e()) / 2.0f, nrs.d(BaseApplication.e()) / 2.0f), null);
            this.c = explodeAnimationHelper;
            explodeAnimationHelper.bkE_(new Size(60, 60));
        }
    }

    private List<Bitmap> b(String... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            Bitmap blz_ = blz_(str);
            if (blz_ != null) {
                arrayList.add(blz_);
            }
        }
        return arrayList;
    }

    private Bitmap blz_(String str) {
        String d = gyn.d().d(str, "png", this.f);
        LogUtil.a("Track_SkipEncourageAnimHelper", "getSkipBitmap() imagePath: ", d);
        return gyn.d().aWQ_(d);
    }

    private boolean e() {
        String str = LanguageUtil.j(BaseApplication.e()) ? "zh-CN" : "en";
        if (this.i && str.equals(this.f)) {
            return false;
        }
        this.f = str;
        return true;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public void startAnimation() {
        ImageView imageView;
        super.startAnimation();
        Bitmap bitmap = this.f13262a.get(this.b);
        if (bitmap == null || (imageView = this.e) == null || this.d == null) {
            LogUtil.h("Track_SkipEncourageAnimHelper", "startAnimation encourage bitmap or mEncourageImg or mExplodeAnimation is null, pls check. bitmap:", bitmap, " mEncourageImg:", this.e, " mExplodeAnimation:", this.d);
            return;
        }
        imageView.setImageBitmap(bitmap);
        this.e.setVisibility(0);
        AnimatorSet bhi_ = hjx.bhi_();
        bhi_.setTarget(this.e);
        bhi_.start();
        this.c.a();
        this.c.bkF_(this.d);
        this.g.removeMessages(4);
        Handler handler = this.g;
        handler.sendMessageDelayed(handler.obtainMessage(4), 2000L);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public void startAnimation(Object obj) {
        if (obj == null) {
            LogUtil.h("Track_SkipEncourageAnimHelper", "startAnimation input data is null, pls check.");
        } else {
            this.b = obj;
            startAnimation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        ImageView imageView = this.e;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
    }

    static class e extends BaseHandler<hna> {
        e(Looper looper, hna hnaVar) {
            super(looper, hnaVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: blA_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(hna hnaVar, Message message) {
            if (message == null) {
                LogUtil.h("Track_SkipEncourageAnimHelper", "handleMessage message is null");
            } else {
                if (message.what != 4) {
                    return;
                }
                hnaVar.c();
            }
        }
    }
}
