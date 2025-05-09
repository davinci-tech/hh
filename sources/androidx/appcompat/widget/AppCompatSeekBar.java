package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.SeekBar;
import com.huawei.health.R;

/* loaded from: classes2.dex */
public class AppCompatSeekBar extends SeekBar {
    private final AppCompatSeekBarHelper mAppCompatSeekBarHelper;

    public AppCompatSeekBar(Context context) {
        this(context, null);
    }

    public AppCompatSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131101081_res_0x7f060599);
    }

    public AppCompatSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ThemeUtils.checkAppCompatTheme(this, getContext());
        AppCompatSeekBarHelper appCompatSeekBarHelper = new AppCompatSeekBarHelper(this);
        this.mAppCompatSeekBarHelper = appCompatSeekBarHelper;
        appCompatSeekBarHelper.loadFromAttributes(attributeSet, i);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void onDraw(Canvas canvas) {
        synchronized (this) {
            super.onDraw(canvas);
            this.mAppCompatSeekBarHelper.drawTickMarks(canvas);
        }
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.mAppCompatSeekBarHelper.drawableStateChanged();
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        this.mAppCompatSeekBarHelper.jumpDrawablesToCurrentState();
    }
}
