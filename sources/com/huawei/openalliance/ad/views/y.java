package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.IPlacementAd;
import com.huawei.openalliance.ad.inter.data.PlacementMediaFile;
import com.huawei.openalliance.ad.media.MediaState;
import com.huawei.openalliance.ad.media.listener.MediaPlayerReleaseListener;
import com.huawei.openalliance.ad.media.listener.MuteListener;
import com.huawei.openalliance.ad.nn;
import com.huawei.openalliance.ad.oc;

/* loaded from: classes5.dex */
public class y extends PlacementMediaView implements com.huawei.openalliance.ad.views.interfaces.o {
    private ImageView g;
    private PlacementMediaFile h;
    private oc i;
    private MuteListener j;

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    void a(int i) {
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    void a(MediaPlayerReleaseListener mediaPlayerReleaseListener) {
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    void b() {
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    MediaState getMediaState() {
        return null;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.p
    public VideoView getVideoView() {
        return null;
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    void setMediaPlayerReleaseListener(MediaPlayerReleaseListener mediaPlayerReleaseListener) {
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void setPlacementAd(IPlacementAd iPlacementAd) {
        super.setPlacementAd(iPlacementAd);
        ho.a("PlacementImageView", "setPlacementAd");
        if (this.f7996a != null) {
            PlacementMediaFile mediaFile = this.f7996a.getMediaFile();
            this.h = mediaFile;
            if (mediaFile.isVideo()) {
                return;
            }
            this.i.a(this.f7996a);
            this.b = this.h.b();
        }
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    ImageView getLastFrame() {
        return this.g;
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView, com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void destroyView() {
        this.g.setImageDrawable(null);
        super.destroyView();
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void d() {
        MuteListener muteListener = this.j;
        if (muteListener != null) {
            muteListener.onUnmute();
        }
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void c() {
        MuteListener muteListener = this.j;
        if (muteListener != null) {
            muteListener.onMute();
        }
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void b(MuteListener muteListener) {
        this.j = null;
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void a(MuteListener muteListener) {
        this.j = muteListener;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.o
    public void a(PlacementMediaFile placementMediaFile, Drawable drawable) {
        this.d = true;
        if (placementMediaFile == null || drawable == null) {
            this.e = false;
        } else if (this.h != null && TextUtils.equals(placementMediaFile.getUrl(), this.h.getUrl())) {
            this.e = true;
            this.g.setImageDrawable(drawable);
        }
        if (this.f) {
            a(true, true);
        }
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    void a() {
        this.g.setImageDrawable(null);
    }

    private void a(Context context) {
        this.i = new nn(getContext(), this);
        this.g = new ImageView(context);
        addView(this.g, new RelativeLayout.LayoutParams(-1, -1));
        this.g.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    public y(Context context) {
        super(context);
        a(context);
    }
}
