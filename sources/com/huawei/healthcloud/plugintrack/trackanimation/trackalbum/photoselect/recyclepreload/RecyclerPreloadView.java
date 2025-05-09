package com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;

/* loaded from: classes4.dex */
public class RecyclerPreloadView extends HealthRecycleView {
    private ScrollFastListener c;
    private boolean e;

    public interface ScrollFastListener {
        void scrollFast();

        void scrollSlow();
    }

    public RecyclerPreloadView(Context context) {
        super(context);
        this.c = null;
        this.e = false;
    }

    public RecyclerPreloadView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = null;
        this.e = false;
    }

    public RecyclerPreloadView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = null;
        this.e = false;
    }

    public void setListenter(ScrollFastListener scrollFastListener) {
        this.c = scrollFastListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void onScrollStateChanged(int i) {
        super.onScrollStateChanged(i);
        if (i == 0) {
            this.c.scrollSlow();
            this.e = false;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void onScrolled(int i, int i2) {
        LogUtil.a("RecyclerPreloadView", "dy is ", Integer.valueOf(Math.abs(i2)));
        boolean z = Math.abs(i2) >= 90;
        if (z != this.e) {
            this.e = z;
            if (z) {
                this.c.scrollFast();
            } else {
                this.c.scrollSlow();
            }
        }
        super.onScrolled(i, i2);
    }
}
