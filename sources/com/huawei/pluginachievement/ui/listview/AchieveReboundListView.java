package com.huawei.pluginachievement.ui.listview;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AbsListView;
import com.huawei.ui.commonui.listview.StatusBarListenListView;

/* loaded from: classes9.dex */
public class AchieveReboundListView extends StatusBarListenListView implements AbsListView.OnScrollListener {

    /* renamed from: a, reason: collision with root package name */
    private boolean f8452a;
    private boolean b;
    private float c;
    private boolean d;
    private boolean e;
    private int f;
    private int g;
    private Handler h;
    private float i;
    private int j;
    private int m;
    private boolean n;
    private int o;

    public AchieveReboundListView(Context context) {
        super(context);
        this.n = true;
        this.f8452a = true;
        this.h = new Handler();
        a();
    }

    public AchieveReboundListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.n = true;
        this.f8452a = true;
        this.h = new Handler();
        a();
    }

    public AchieveReboundListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.n = true;
        this.f8452a = true;
        this.h = new Handler();
        a();
    }

    public void setTopNotChange() {
        this.n = false;
    }

    private void a() {
        this.f = getPaddingLeft();
        this.m = getPaddingRight();
        this.o = getPaddingTop();
        this.g = getPaddingBottom();
        setOnScrollListener(this);
        setOverScrollMode(2);
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.c = motionEvent.getY();
        } else if (action != 1) {
            if (action == 2) {
                this.h.removeCallbacksAndMessages(null);
                float y = motionEvent.getY();
                this.i = y;
                int i = (int) ((y - this.c) / 2.5f);
                boolean z = i > 0;
                this.d = z;
                if (z) {
                    if (this.n && this.b && this.j != 2) {
                        setPadding(this.f, i + this.o, this.m, this.g);
                        setSelection(0);
                    }
                } else if (this.f8452a && this.e && this.j != 2) {
                    setPadding(this.f, this.o, this.m, -(i - this.g));
                    setSelection(getCount() - 1);
                }
            }
        } else if (this.d) {
            d();
        } else {
            e();
        }
        return super.onTouchEvent(motionEvent);
    }

    private void d() {
        if (this.n) {
            final int paddingTop = getPaddingTop();
            int i = 0;
            while (paddingTop > this.o) {
                paddingTop -= 20;
                i += 10;
                this.h.postDelayed(new Runnable() { // from class: com.huawei.pluginachievement.ui.listview.AchieveReboundListView.4
                    @Override // java.lang.Runnable
                    public void run() {
                        if (paddingTop < AchieveReboundListView.this.o) {
                            AchieveReboundListView achieveReboundListView = AchieveReboundListView.this;
                            achieveReboundListView.setPadding(achieveReboundListView.f, AchieveReboundListView.this.o, AchieveReboundListView.this.m, AchieveReboundListView.this.g);
                        } else {
                            AchieveReboundListView achieveReboundListView2 = AchieveReboundListView.this;
                            achieveReboundListView2.setPadding(achieveReboundListView2.f, paddingTop, AchieveReboundListView.this.m, AchieveReboundListView.this.g);
                        }
                    }
                }, i);
            }
        }
    }

    private void e() {
        if (this.f8452a) {
            final int paddingBottom = getPaddingBottom();
            int i = 0;
            while (paddingBottom > this.g) {
                paddingBottom -= 20;
                i += 10;
                this.h.postDelayed(new Runnable() { // from class: com.huawei.pluginachievement.ui.listview.AchieveReboundListView.5
                    @Override // java.lang.Runnable
                    public void run() {
                        if (paddingBottom < AchieveReboundListView.this.g) {
                            AchieveReboundListView achieveReboundListView = AchieveReboundListView.this;
                            achieveReboundListView.setPadding(achieveReboundListView.f, AchieveReboundListView.this.o, AchieveReboundListView.this.m, AchieveReboundListView.this.g);
                        } else {
                            AchieveReboundListView achieveReboundListView2 = AchieveReboundListView.this;
                            achieveReboundListView2.setPadding(achieveReboundListView2.f, AchieveReboundListView.this.o, AchieveReboundListView.this.m, paddingBottom);
                        }
                    }
                }, i);
            }
        }
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.b = i == 0;
        this.e = i + i2 == i3;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
        this.j = i;
    }
}
