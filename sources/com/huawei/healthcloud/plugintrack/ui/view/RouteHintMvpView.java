package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.healthcloud.plugintrack.ui.view.RouteHintMvpView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.utils.AsyncLoadDrawableCallback;
import defpackage.nrf;
import health.compact.a.util.LogUtil;

/* loaded from: classes4.dex */
public class RouteHintMvpView extends LinearLayout {
    private static int d = 90;

    /* renamed from: a, reason: collision with root package name */
    private View f3787a;
    private ImageView b;
    private HealthTextView e;

    public RouteHintMvpView(Context context) {
        this(context, null);
    }

    public RouteHintMvpView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        d(context);
    }

    private void d(Context context) {
        View inflate = View.inflate(context, R.layout.route_hint_mvp_layout, this);
        this.f3787a = inflate;
        this.e = (HealthTextView) inflate.findViewById(R.id.route_mvp);
        this.b = (ImageView) this.f3787a.findViewById(R.id.iv_head);
    }

    public void setHeadView(String str, final BaseResponseCallback baseResponseCallback) {
        LogUtil.d("RouteHintView", "setHeadView imageUrl=", str);
        if (TextUtils.isEmpty(str)) {
            this.b.setImageDrawable(ContextCompat.getDrawable(BaseApplication.e(), R.mipmap._2131821050_res_0x7f1101fa));
            if (baseResponseCallback != null) {
                baseResponseCallback.onResponse(0, null);
                return;
            }
            return;
        }
        nrf.cIT_(this.b, str, d, 0, new AsyncLoadDrawableCallback() { // from class: hll
            @Override // com.huawei.ui.commonui.utils.AsyncLoadDrawableCallback
            public final void getDrawable(Drawable drawable) {
                RouteHintMvpView.this.biH_(baseResponseCallback, drawable);
            }
        });
    }

    public /* synthetic */ void biH_(BaseResponseCallback baseResponseCallback, Drawable drawable) {
        if (drawable == null) {
            this.b.setImageDrawable(ContextCompat.getDrawable(BaseApplication.e(), R.mipmap._2131821050_res_0x7f1101fa));
        }
        if (baseResponseCallback != null) {
            baseResponseCallback.onResponse(0, null);
        }
    }

    public void setContent(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.d("RouteHintView", "setContent content isEmpty");
        } else {
            this.e.setText(str);
        }
    }
}
