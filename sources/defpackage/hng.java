package defpackage;

import android.animation.AnimatorSet;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsportmodel.StandFlexionAchieveType;
import health.compact.a.LanguageUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class hng extends AnimBaseHelper {

    /* renamed from: a, reason: collision with root package name */
    private Map<Integer, Bitmap> f13267a;
    private Handler b;
    private Object c;
    private ImageView d;
    private boolean e;
    private String f;

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public boolean isAnimRunning() {
        return false;
    }

    public hng(View view) {
        super(view);
        this.f13267a = new HashMap();
        this.f = "zh-CN";
        this.e = false;
        this.b = new e(Looper.getMainLooper(), this);
        if (view instanceof ImageView) {
            this.d = (ImageView) view;
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public void initResource() {
        Bitmap blC_;
        if (b()) {
            this.e = true;
            this.f13267a.clear();
            for (StandFlexionAchieveType standFlexionAchieveType : StandFlexionAchieveType.values()) {
                if (standFlexionAchieveType != null && standFlexionAchieveType.getPicPath() != null && (blC_ = blC_(standFlexionAchieveType.getPicPath())) != null) {
                    this.f13267a.put(Integer.valueOf(standFlexionAchieveType.getScore()), blC_);
                }
            }
        }
    }

    private Bitmap blC_(String str) {
        String c = gyr.e().c(str, "png");
        LogUtil.a("Track_StandFlexionEncourageAnimHelper", "getSportExamBitmap() imagePath: ", c);
        return gyr.e().aWN_(c);
    }

    private boolean b() {
        String str = LanguageUtil.j(BaseApplication.e()) ? "zh-CN" : "en";
        if (this.e && str.equals(this.f)) {
            return false;
        }
        this.f = str;
        return true;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public void startAnimation() {
        ImageView imageView;
        super.startAnimation();
        Object[] objArr = new Object[3];
        objArr[0] = "startAnimation score:";
        objArr[1] = this.c;
        objArr[2] = Boolean.valueOf(this.d == null);
        LogUtil.a("Track_StandFlexionEncourageAnimHelper", objArr);
        Bitmap bitmap = this.f13267a.get(this.c);
        if (bitmap != null && (imageView = this.d) != null) {
            imageView.setImageBitmap(bitmap);
            this.d.setVisibility(0);
            AnimatorSet bhi_ = hjx.bhi_();
            bhi_.setTarget(this.d);
            bhi_.start();
        }
        this.b.removeMessages(1);
        Handler handler = this.b;
        handler.sendMessageDelayed(handler.obtainMessage(1), 2000L);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public void startAnimation(Object obj) {
        if (obj == null) {
            LogUtil.h("Track_StandFlexionEncourageAnimHelper", "startAnimation input data is null, pls check.");
        } else {
            this.c = obj;
            startAnimation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        ImageView imageView = this.d;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
    }

    static class e extends BaseHandler<hng> {
        e(Looper looper, hng hngVar) {
            super(looper, hngVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: blD_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(hng hngVar, Message message) {
            if (message == null) {
                LogUtil.h("Track_StandFlexionEncourageAnimHelper", "handleMessage message is null");
            } else if (message.what == 1) {
                hngVar.d();
            }
        }
    }
}
