package com.huawei.indoorequip.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import java.lang.ref.WeakReference;

/* loaded from: classes9.dex */
public class WaitingDotView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private Context f6435a;
    private Handler b;
    private int c;
    private HealthTextView[] d;

    static /* synthetic */ int a(WaitingDotView waitingDotView) {
        int i = waitingDotView.c;
        waitingDotView.c = i + 1;
        return i;
    }

    static class e implements Runnable {
        private WeakReference<WaitingDotView> b;

        e(WaitingDotView waitingDotView) {
            this.b = new WeakReference<>(waitingDotView);
        }

        @Override // java.lang.Runnable
        public void run() {
            WaitingDotView waitingDotView = this.b.get();
            if (waitingDotView != null) {
                if (waitingDotView.c < 3) {
                    waitingDotView.d[waitingDotView.c].setTextColor(Color.rgb(26, 26, 26));
                    WaitingDotView.a(waitingDotView);
                } else {
                    for (int i = 0; i < 3; i++) {
                        waitingDotView.d[i].setTextColor(0);
                    }
                    waitingDotView.c = 0;
                }
                waitingDotView.b.postDelayed(this, 600L);
            }
        }
    }

    public WaitingDotView(Context context) {
        this(context, null);
    }

    public WaitingDotView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WaitingDotView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = 1;
        this.b = new Handler();
        this.f6435a = context;
        b();
        d();
    }

    private void b() {
        this.d = new HealthTextView[3];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        for (int i = 0; i < 3; i++) {
            this.d[i] = new HealthTextView(this.f6435a);
            this.d[i].setText(Html.fromHtml("&#8226;"));
            this.d[i].setTextSize(10.0f);
            if (i == 0) {
                this.d[i].setTextColor(Color.rgb(26, 26, 26));
            } else {
                this.d[i].setTextColor(0);
                layoutParams.leftMargin = 12;
            }
            this.d[i].setLayoutParams(layoutParams);
            addView(this.d[i]);
        }
    }

    private void d() {
        this.b.postDelayed(new e(this), 600L);
    }
}
