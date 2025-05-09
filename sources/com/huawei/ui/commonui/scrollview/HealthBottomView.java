package com.huawei.ui.commonui.scrollview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.uikit.phone.hwbottomnavigationview.widget.HwBottomNavigationView;
import defpackage.jdx;
import defpackage.nrf;

/* loaded from: classes6.dex */
public class HealthBottomView extends HwBottomNavigationView {
    public HealthBottomView(Context context) {
        super(context);
    }

    public HealthBottomView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HealthBottomView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void e(final String str, final int i, final boolean z) {
        jdx.b(new Runnable() { // from class: com.huawei.ui.commonui.scrollview.HealthBottomView.4
            @Override // java.lang.Runnable
            public void run() {
                final Drawable cFD_ = HealthBottomView.this.cFD_(str);
                if (cFD_ == null) {
                    LogUtil.b("HealthBottomView", "can not get the image");
                } else {
                    HandlerExecutor.e(new Runnable() { // from class: com.huawei.ui.commonui.scrollview.HealthBottomView.4.2
                        @Override // java.lang.Runnable
                        public void run() {
                            HealthBottomView.this.dZM_(cFD_, i, z);
                        }
                    });
                }
            }
        });
    }

    public void a(final int i, final String str, final int i2, final boolean z) {
        jdx.b(new Runnable() { // from class: com.huawei.ui.commonui.scrollview.HealthBottomView.1
            @Override // java.lang.Runnable
            public void run() {
                final Drawable cFD_ = HealthBottomView.this.cFD_(str);
                if (cFD_ == null) {
                    LogUtil.b("HealthBottomView", "can not get the image");
                } else {
                    HandlerExecutor.e(new Runnable() { // from class: com.huawei.ui.commonui.scrollview.HealthBottomView.1.4
                        @Override // java.lang.Runnable
                        public void run() {
                            HealthBottomView.this.dZL_(i, cFD_, i2, z);
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Drawable cFD_(String str) {
        return nrf.cIb_(BaseApplication.getContext(), str);
    }
}
