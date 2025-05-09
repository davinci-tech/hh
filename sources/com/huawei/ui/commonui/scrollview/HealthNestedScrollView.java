package com.huawei.ui.commonui.scrollview;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;
import androidx.core.widget.NestedScrollView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.secure.android.common.intent.SafeBroadcastReceiver;
import com.huawei.ui.commonui.R$styleable;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes6.dex */
public class HealthNestedScrollView extends NestedScrollView {

    /* renamed from: a, reason: collision with root package name */
    private d f8942a;
    private boolean e;

    public HealthNestedScrollView(Context context) {
        super(context);
    }

    public HealthNestedScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        cFG_(context, attributeSet);
    }

    public HealthNestedScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        cFG_(context, attributeSet);
    }

    private void cFG_(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.HealthNestedScrollView);
        this.e = obtainStyledAttributes.getBoolean(R$styleable.HealthNestedScrollView_isNeedScrollToTop, false);
        obtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    public boolean isFocused() {
        if (getParent() instanceof ScrollView) {
            return true;
        }
        return super.isFocused();
    }

    @Override // androidx.core.widget.NestedScrollView, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.e) {
            this.f8942a = new d(this, true);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Constants.CLICK_STATUS_BAR_ACTION);
            BroadcastManagerUtil.bFA_(BaseApplication.e(), this.f8942a, intentFilter, Constants.SYSTEM_UI_PERMISSION, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        int computeVerticalScrollOffset = computeVerticalScrollOffset();
        int computeVerticalScrollExtent = computeVerticalScrollExtent() * 3;
        if (computeVerticalScrollOffset > computeVerticalScrollExtent) {
            scrollBy(0, computeVerticalScrollExtent - computeVerticalScrollOffset);
        }
        LogUtil.a("HealthNestedScrollView", "scrollOverDistanceToTopProc");
        smoothScrollTo(0, 0);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!this.e || this.f8942a == null) {
            return;
        }
        BaseApplication.e().unregisterReceiver(this.f8942a);
        this.f8942a = null;
    }

    class d extends SafeBroadcastReceiver {
        private boolean c;
        private View d;

        public d(View view, boolean z) {
            this.d = view;
            this.c = z;
        }

        @Override // com.huawei.secure.android.common.intent.SafeBroadcastReceiver
        public void onReceiveMsg(Context context, Intent intent) {
            if (!this.c || this.d == null || intent == null || !Constants.CLICK_STATUS_BAR_ACTION.equals(intent.getAction())) {
                return;
            }
            HealthNestedScrollView.this.e();
        }
    }

    @Override // androidx.core.widget.NestedScrollView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        try {
            super.onRestoreInstanceState(parcelable);
        } catch (Exception e) {
            ReleaseLogUtil.c("HealthNestedScrollView", "onRestoreInstanceState exception: ", ExceptionUtils.d(e));
        }
    }
}
