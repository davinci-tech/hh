package com.huawei.health.suggestion.ui.fitness.helper.actionstrategy;

import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;

/* loaded from: classes4.dex */
public interface HeaderViewInterface {
    ImageView getCoachImageView();

    View getHeaderView();

    boolean getIsForeGround();

    boolean getIsShowMediaPlayer();

    void refreshHeaderView(int i);

    void setSurfaceTextureListener(TextureView.SurfaceTextureListener surfaceTextureListener);

    void showCoachImage();
}
