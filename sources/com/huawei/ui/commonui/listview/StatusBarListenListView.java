package com.huawei.ui.commonui.listview;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ListView;
import com.huawei.ui.commonui.utils.StatusBarClickedListener;

/* loaded from: classes9.dex */
public class StatusBarListenListView extends ListView {
    private StatusBarClickedListener b;
    private final StatusBarListenListView d;
    private boolean e;

    public StatusBarListenListView(Context context) {
        super(context);
        this.e = true;
        this.d = this;
    }

    public StatusBarListenListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = true;
        this.d = this;
    }

    public StatusBarListenListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = true;
        this.d = this;
    }

    public void b() {
        if (this.e) {
            smoothScrollToPosition(0);
            new Handler().postDelayed(new Runnable() { // from class: com.huawei.ui.commonui.listview.StatusBarListenListView.3
                @Override // java.lang.Runnable
                public void run() {
                    if (StatusBarListenListView.this.getFirstVisiblePosition() > 0) {
                        StatusBarListenListView.this.setSelectionAfterHeaderView();
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
        Log.i("StatusBarListenListView", "onAttachedToWindow");
        if (this.b == null) {
            this.b = new StatusBarClickedListener() { // from class: com.huawei.ui.commonui.listview.StatusBarListenListView.2
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (StatusBarListenListView.this.d != null) {
                        StatusBarListenListView.this.d.b();
                    }
                }
            };
        }
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i("StatusBarListenListView", "onDetachedFromWindow");
        StatusBarClickedListener statusBarClickedListener = this.b;
        if (statusBarClickedListener != null) {
            statusBarClickedListener.unregister();
            this.b = null;
        }
    }
}
