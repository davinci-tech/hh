package com.huawei.ui.main.stories.guidepage.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/* loaded from: classes6.dex */
public class PlayVideo extends VideoView {
    public PlayVideo(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public PlayVideo(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PlayVideo(Context context) {
        super(context);
    }

    @Override // android.widget.VideoView, android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(getDefaultSize(0, i), getDefaultSize(0, i2));
    }
}
