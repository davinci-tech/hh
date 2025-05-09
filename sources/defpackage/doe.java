package defpackage;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class doe {
    public static void Xl_(String str, ImageView imageView) {
        Xm_(str, imageView, R.drawable._2131429902_res_0x7f0b0a0e);
    }

    public static void Xm_(final String str, final ImageView imageView, int i) {
        if (str == null || str.length() == 0) {
            LogUtil.h("ViewUtil", "url is invalid");
            return;
        }
        imageView.setImageResource(i);
        LogUtil.a("ViewUtil", "loadImage  url=", str);
        ThreadPoolManager.d().execute(new Runnable() { // from class: doi
            @Override // java.lang.Runnable
            public final void run() {
                doe.Xk_(str, imageView);
            }
        });
    }

    static /* synthetic */ void Xk_(String str, final ImageView imageView) {
        final Drawable cIb_ = nrf.cIb_(BaseApplication.getContext(), str);
        if (cIb_ != null) {
            HandlerExecutor.a(new Runnable() { // from class: dof
                @Override // java.lang.Runnable
                public final void run() {
                    doe.Xj_(imageView, cIb_);
                }
            });
        } else {
            LogUtil.h("ViewUtil", "loadImage drawable is null");
        }
    }

    static /* synthetic */ void Xj_(ImageView imageView, Drawable drawable) {
        LogUtil.a("ViewUtil", "loadImage success");
        imageView.setImageDrawable(drawable);
    }
}
