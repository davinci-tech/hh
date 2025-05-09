package com.huawei.ui.commonui.listview;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ExpandableListView;
import com.huawei.ui.commonui.utils.StatusBarClickedListener;

/* loaded from: classes6.dex */
public class HealthExpandableListView extends ExpandableListView {

    /* renamed from: a, reason: collision with root package name */
    private final HealthExpandableListView f8910a;
    private StatusBarClickedListener b;
    private boolean e;

    public HealthExpandableListView(Context context) {
        super(context);
        this.e = true;
        this.f8910a = this;
    }

    public HealthExpandableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = true;
        this.f8910a = this;
    }

    public HealthExpandableListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = true;
        this.f8910a = this;
    }

    public void e() {
        if (this.e) {
            smoothScrollToPositionFromTop(0, 0);
            new Handler().postDelayed(new Runnable() { // from class: com.huawei.ui.commonui.listview.HealthExpandableListView.4
                @Override // java.lang.Runnable
                public void run() {
                    if (HealthExpandableListView.this.getFirstVisiblePosition() > 0) {
                        HealthExpandableListView.this.setSelectionAfterHeaderView();
                    }
                }
            }, 100L);
        }
    }

    public void setScrollTopEnable(boolean z) {
        this.e = z;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchWindowFocusChanged(boolean z) {
        super.dispatchWindowFocusChanged(z);
        setScrollTopEnable(z);
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i("HealthExpandableListView", "onAttachedToWindow");
        if (this.b == null) {
            this.b = new StatusBarClickedListener() { // from class: com.huawei.ui.commonui.listview.HealthExpandableListView.3
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (HealthExpandableListView.this.f8910a != null) {
                        HealthExpandableListView.this.f8910a.e();
                    }
                }
            };
        }
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i("HealthExpandableListView", "onDetachedFromWindow");
        StatusBarClickedListener statusBarClickedListener = this.b;
        if (statusBarClickedListener != null) {
            statusBarClickedListener.unregister();
            this.b = null;
        }
    }
}
