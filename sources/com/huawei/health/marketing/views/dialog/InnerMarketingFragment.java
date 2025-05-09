package com.huawei.health.marketing.views.dialog;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SinglePopUp;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.marketing.views.dialog.InnerMarketingFragment;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.watchface.videoedit.gles.Constant;
import defpackage.nsn;
import defpackage.ntd;
import health.compact.a.Services;
import java.io.IOException;

/* loaded from: classes3.dex */
public class InnerMarketingFragment extends BaseFragment implements TextureView.SurfaceTextureListener, MediaPlayer.OnVideoSizeChangedListener {
    private Context b;
    private MediaPlayer c;
    private ImageView e;
    private OnPageClickListener f;
    private int g;
    private View h;
    private int i;
    private ResourceBriefInfo j;
    private SinglePopUp l;
    private Surface n;
    private TextureView o;
    private boolean d = false;

    /* renamed from: a, reason: collision with root package name */
    private boolean f2902a = false;

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void e(int i, int i2, ResourceBriefInfo resourceBriefInfo, SinglePopUp singlePopUp) {
        this.g = i;
        this.i = i2;
        this.j = resourceBriefInfo;
        this.l = singlePopUp;
        if (i <= 0 || resourceBriefInfo == null || singlePopUp == null) {
            LogUtil.a("UIDV_TmpFragment", " Invalid parameter:", " mPositionId,mResourceBriefInfo,mSinglePopUp");
        }
    }

    public void e(OnPageClickListener onPageClickListener) {
        this.f = onPageClickListener;
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.b = context;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return aqE_();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment
    public void initViewTahiti() {
        super.initViewTahiti();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        ntd.b().cME_(this, this.h);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (getUserVisibleHint()) {
            MediaPlayer mediaPlayer = this.c;
            if (mediaPlayer == null) {
                c();
                return;
            }
            try {
                if (!mediaPlayer.isPlaying() && this.d) {
                    ImageView imageView = this.e;
                    if (imageView != null) {
                        imageView.setVisibility(8);
                    }
                    this.c.start();
                }
                if (this.d) {
                    return;
                }
                this.c.prepareAsync();
                return;
            } catch (IllegalStateException e) {
                LogUtil.a("UIDV_TmpFragment", " stop:", String.valueOf(e.getMessage()));
                return;
            }
        }
        b();
    }

    private View aqE_() {
        if (this.j.getContentType() == 45) {
            this.f2902a = true;
            return aqD_(this.l, this.i);
        }
        this.f2902a = false;
        return aqC_(this.l);
    }

    private View aqC_(final SinglePopUp singlePopUp) {
        View inflate = LayoutInflater.from(this.b).inflate(R.layout.introduction_dialog_full_img, (ViewGroup) null);
        this.h = inflate;
        this.e = (ImageView) inflate.findViewById(R.id.introduction_dialog_full_image);
        this.o = (TextureView) this.h.findViewById(R.id.introduction_dialog_full_video);
        if (TextUtils.equals(singlePopUp.getMediaType(), Constant.TYPE_PHOTO)) {
            this.o.setVisibility(8);
            this.e.setVisibility(0);
            Glide.with(this.b).load(singlePopUp.getPageMedia()).placeholder(R.drawable._2131428575_res_0x7f0b04df).into(this.e);
        } else if (TextUtils.equals(singlePopUp.getMediaType(), "video")) {
            this.o.setVisibility(0);
            this.o.setOpaque(false);
            this.o.setSurfaceTextureListener(this);
            this.e.setVisibility(0);
            this.e.setBackgroundResource(R.drawable._2131428575_res_0x7f0b04df);
        } else {
            LogUtil.h("UIDV_TmpFragment", "wrong mediaType.");
            return this.h;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        HealthButton healthButton = (HealthButton) this.h.findViewById(R.id.introduction_dialog_full_button);
        healthButton.setText(singlePopUp.getButtonName());
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: elg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InnerMarketingFragment.this.aqF_(singlePopUp, currentTimeMillis, view);
            }
        });
        return this.h;
    }

    public /* synthetic */ void aqF_(SinglePopUp singlePopUp, long j, View view) {
        if (nsn.cLk_(view)) {
            LogUtil.h("UIDV_TmpFragment", "click too fast");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            String linkValue = singlePopUp.getLinkValue();
            MarketingBiUtils.b(this.g, this.j, j);
            ((MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class)).router(linkValue);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private View aqD_(SinglePopUp singlePopUp, final int i) {
        View inflate = LayoutInflater.from(this.b).inflate(R.layout.introduction_dialog_half_img, (ViewGroup) null);
        this.h = inflate;
        this.e = (ImageView) inflate.findViewById(R.id.introduction_dialog_half_image);
        this.o = (TextureView) this.h.findViewById(R.id.introduction_dialog_half_video);
        HealthButton healthButton = (HealthButton) this.h.findViewById(R.id.introduction_dialog_half_button);
        if (TextUtils.equals(singlePopUp.getMediaType(), Constant.TYPE_PHOTO)) {
            this.o.setVisibility(8);
            this.e.setVisibility(0);
            Glide.with(this.b).load(singlePopUp.getPageMedia()).placeholder(R.drawable._2131428575_res_0x7f0b04df).into(this.e);
        } else if (TextUtils.equals(singlePopUp.getMediaType(), "video")) {
            this.o.setVisibility(0);
            this.o.setOpaque(false);
            this.e.setVisibility(0);
            this.e.setBackgroundResource(R.drawable._2131428575_res_0x7f0b04df);
            this.o.setSurfaceTextureListener(this);
        } else {
            LogUtil.h("UIDV_TmpFragment", "wrong mediaType.");
            return this.h;
        }
        TextView textView = (TextView) this.h.findViewById(R.id.introduction_dialog_half_text);
        if (!TextUtils.isEmpty(singlePopUp.getIntroduction())) {
            textView.setText(singlePopUp.getIntroduction());
        }
        healthButton.setText(singlePopUp.getButtonName());
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: elk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InnerMarketingFragment.this.aqG_(i, view);
            }
        });
        return this.h;
    }

    public /* synthetic */ void aqG_(int i, View view) {
        this.f.onPageClick(i);
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.n = new Surface(surfaceTexture);
        c();
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        b();
        return false;
    }

    private void b() {
        MediaPlayer mediaPlayer = this.c;
        if (mediaPlayer != null) {
            try {
                mediaPlayer.stop();
                this.c.release();
                this.c = null;
            } catch (IllegalStateException e) {
                LogUtil.a("UIDV_TmpFragment", " stop:", String.valueOf(e.getMessage()));
            }
        }
    }

    private void c() {
        SinglePopUp singlePopUp;
        if (this.n == null || (singlePopUp = this.l) == null || singlePopUp.getPageMedia() == null) {
            return;
        }
        ImageView imageView = this.e;
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.c = mediaPlayer;
        mediaPlayer.setAudioStreamType(3);
        this.c.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.huawei.health.marketing.views.dialog.InnerMarketingFragment.1
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer2) {
                InnerMarketingFragment.this.d = true;
                if (InnerMarketingFragment.this.e != null) {
                    InnerMarketingFragment.this.e.setVisibility(8);
                }
                InnerMarketingFragment.this.c.start();
            }
        });
        this.c.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.huawei.health.marketing.views.dialog.InnerMarketingFragment.3
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer2, int i, int i2) {
                LogUtil.a("UIDV_TmpFragment", "what:", Integer.valueOf(i), "extra:", Integer.valueOf(i2));
                return false;
            }
        });
        this.c.setOnInfoListener(new MediaPlayer.OnInfoListener() { // from class: com.huawei.health.marketing.views.dialog.InnerMarketingFragment.5
            @Override // android.media.MediaPlayer.OnInfoListener
            public boolean onInfo(MediaPlayer mediaPlayer2, int i, int i2) {
                LogUtil.a("UIDV_TmpFragment", "what:", Integer.valueOf(i), "extra:", Integer.valueOf(i2));
                return false;
            }
        });
        this.c.setOnVideoSizeChangedListener(this);
        this.c.setSurface(this.n);
        try {
            this.c.setDataSource(this.l.getPageMedia());
            this.c.setLooping(true);
            this.c.prepareAsync();
        } catch (IOException e) {
            LogUtil.a("UIDV_TmpFragment", " play:", String.valueOf(e.getMessage()));
        }
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        int measuredHeight = this.f2902a ? this.o.getMeasuredHeight() : getResources().getDimensionPixelOffset(R.dimen._2131363065_res_0x7f0a04f9);
        int measuredWidth = this.o.getMeasuredWidth();
        if (measuredHeight <= 0 || measuredWidth <= 0 || i <= 0 || i2 <= 0) {
            return;
        }
        int i3 = measuredWidth * 100;
        int i4 = (i * 100) / i2;
        if (i3 / measuredHeight != i4) {
            ViewGroup.LayoutParams layoutParams = this.o.getLayoutParams();
            if (i >= i2) {
                layoutParams.width = measuredWidth;
                layoutParams.height = i3 / i4;
            } else {
                layoutParams.width = (i4 * measuredHeight) / 100;
                layoutParams.height = measuredHeight;
            }
            this.o.setLayoutParams(layoutParams);
        }
    }
}
