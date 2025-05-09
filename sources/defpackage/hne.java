package defpackage;

import android.animation.AnimatorSet;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsportmodel.SupineLegAchieveType;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class hne extends AnimBaseHelper {

    /* renamed from: a, reason: collision with root package name */
    private Object f13265a;
    private Handler b;
    private boolean c;
    private ImageView d;
    private Map<Integer, Bitmap> e;

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public boolean isAnimRunning() {
        return false;
    }

    public hne(View view) {
        super(view);
        this.e = new HashMap();
        this.c = false;
        this.b = new e(Looper.getMainLooper(), this);
        if (view instanceof ImageView) {
            this.d = (ImageView) view;
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public void initResource() {
        Bitmap aWO_;
        if (this.c) {
            return;
        }
        this.c = true;
        this.e.clear();
        for (SupineLegAchieveType supineLegAchieveType : SupineLegAchieveType.values()) {
            if (supineLegAchieveType != null && supineLegAchieveType.getPicPath() != null && (aWO_ = gyr.e().aWO_(supineLegAchieveType.getPicPath())) != null) {
                this.e.put(Integer.valueOf(supineLegAchieveType.getContinuesTimes()), aWO_);
            }
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public void startAnimation() {
        ImageView imageView;
        super.startAnimation();
        Object[] objArr = new Object[3];
        objArr[0] = "startAnimation score:";
        objArr[1] = this.f13265a;
        objArr[2] = Boolean.valueOf(this.d == null);
        LogUtil.a("Track_SupineLegEncourageAnimHelper", objArr);
        Bitmap bitmap = this.e.get(this.f13265a);
        if (bitmap == null || (imageView = this.d) == null) {
            return;
        }
        imageView.setImageBitmap(bitmap);
        this.d.setVisibility(0);
        AnimatorSet bhi_ = hjx.bhi_();
        bhi_.setTarget(this.d);
        bhi_.start();
        this.b.removeMessages(1);
        Handler handler = this.b;
        handler.sendMessageDelayed(handler.obtainMessage(1), 2000L);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.AnimBaseHelper
    public void startAnimation(Object obj) {
        if (obj == null) {
            LogUtil.h("Track_SupineLegEncourageAnimHelper", "startAnimation input data is null, pls check.");
        } else {
            this.f13265a = obj;
            startAnimation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        ImageView imageView = this.d;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
    }

    static class e extends BaseHandler<hne> {
        e(Looper looper, hne hneVar) {
            super(looper, hneVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: blE_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(hne hneVar, Message message) {
            if (message == null) {
                LogUtil.h("Track_SupineLegEncourageAnimHelper", "handleMessage message is null");
            } else if (message.what == 1) {
                hneVar.c();
            }
        }
    }
}
