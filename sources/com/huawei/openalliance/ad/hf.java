package com.huawei.openalliance.ad;

import android.graphics.drawable.Drawable;
import com.huawei.openalliance.ad.inter.data.ImageInfo;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import com.huawei.openalliance.ad.linked.view.LinkedAppDetailView;
import com.huawei.openalliance.ad.views.VideoView;

/* loaded from: classes9.dex */
public interface hf extends jk {
    void a(ImageInfo imageInfo, Drawable drawable);

    void a(VideoInfo videoInfo, boolean z);

    void b(String str);

    void g();

    gt getLinkedNativeAd();

    hd getLinkedVideoControlBridge();

    VideoView getVideoView();

    LinkedAppDetailView h();

    void i();

    void setLinkedLandView(he heVar);

    void setLinkedNativeAd(gs gsVar);

    void setVideoView(VideoView videoView);
}
