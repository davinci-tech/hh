package com.huawei.openalliance.ad.views.interfaces;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.huawei.openalliance.ad.inter.data.INativeAd;
import com.huawei.openalliance.ad.inter.data.ImageInfo;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import com.huawei.openalliance.ad.jk;
import com.huawei.openalliance.ad.views.VideoView;

/* loaded from: classes5.dex */
public interface INativeVideoView extends jk {
    ImageView getPreviewImageView();

    VideoView getVideoView();

    void notifyStreamError(int i);

    void onCheckVideoHashResult(VideoInfo videoInfo, boolean z);

    void onPreviewImageLoaded(ImageInfo imageInfo, Drawable drawable);

    void pause();

    void setNativeAd(INativeAd iNativeAd);

    void setPpsNativeView(IPPSNativeView iPPSNativeView);

    void showFullScreenSwitchButton(boolean z);

    void stop();

    void updateContent(String str);

    void updateStartShowTime(long j);
}
