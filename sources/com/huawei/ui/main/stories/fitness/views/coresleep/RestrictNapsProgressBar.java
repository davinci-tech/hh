package com.huawei.ui.main.stories.fitness.views.coresleep;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;

/* loaded from: classes.dex */
public class RestrictNapsProgressBar extends FrameLayout {
    private HealthProgressBar b;
    private Guideline d;
    private HealthTextView e;

    public RestrictNapsProgressBar(Context context) {
        super(context);
        b(context);
    }

    public RestrictNapsProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b(context);
    }

    public RestrictNapsProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b(context);
    }

    public void setProgress(int i) {
        this.b.setProgress(i);
        ViewGroup.LayoutParams layoutParams = this.d.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            ((ConstraintLayout.LayoutParams) layoutParams).guidePercent = i / 100.0f;
            this.d.setLayoutParams(layoutParams);
        }
    }

    public void setTile(String str) {
        this.e.setText(str);
    }

    private void b(Context context) {
        setClipToPadding(false);
        setClipChildren(false);
        LayoutInflater.from(context).inflate(R.layout.sleep_task_progress_bar, this);
        this.b = (HealthProgressBar) findViewById(R.id.sleep_task_progress);
        this.e = (HealthTextView) findViewById(R.id.sleep_naps_time);
        this.d = (Guideline) findViewById(R.id.progress_guide_line);
    }
}
