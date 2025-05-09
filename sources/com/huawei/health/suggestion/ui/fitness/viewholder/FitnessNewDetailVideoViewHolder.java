package com.huawei.health.suggestion.ui.fitness.viewholder;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.mvp.VideoPlayer;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitnessNewDetailVideoViewHolder;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.ShowLayout;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.nrh;
import health.compact.a.CommonUtil;

/* loaded from: classes4.dex */
public class FitnessNewDetailVideoViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f3215a;
    private VideoPlayer b;
    private HealthProgressBar c;
    private ShowLayout d;
    private FrameLayout e;

    public FitnessNewDetailVideoViewHolder(ViewGroup viewGroup, VideoPlayer videoPlayer) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.new_view_detail_info_video, viewGroup, false));
        this.b = videoPlayer;
        videoPlayer.c(true);
        this.e = (FrameLayout) this.itemView.findViewById(R.id.surface_view);
        this.c = (HealthProgressBar) this.itemView.findViewById(R.id.state_progressBar);
        this.f3215a = (ImageView) this.itemView.findViewById(R.id.item_iv);
        this.e.setOnClickListener(new View.OnClickListener() { // from class: fsi
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FitnessNewDetailVideoViewHolder.this.aFE_(view);
            }
        });
        this.f3215a.setOnClickListener(new View.OnClickListener() { // from class: fse
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FitnessNewDetailVideoViewHolder.this.aFF_(view);
            }
        });
    }

    public /* synthetic */ void aFE_(View view) {
        VideoPlayer videoPlayer = this.b;
        if (videoPlayer == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        videoPlayer.f();
        this.f3215a.setVisibility(0);
        this.c.setVisibility(4);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void aFF_(View view) {
        if (this.d == null || this.b == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (!CommonUtil.aa(BaseApplication.e())) {
            LogUtil.h("Suggestion_VideoViewHolder", "Network is not Connected!");
            nrh.e(BaseApplication.e(), R.string._2130841083_res_0x7f020dfb);
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (CommonUtil.l(BaseApplication.e()) != 1) {
            nrh.b(BaseApplication.e(), R.string._2130841376_res_0x7f020f20);
        }
        this.b.b(new VideoPlayer.VideoPlayStateListener() { // from class: fsf
            @Override // com.huawei.health.suggestion.ui.fitness.mvp.VideoPlayer.VideoPlayStateListener
            public final void onPlayStateChange(int i) {
                FitnessNewDetailVideoViewHolder.this.a(i);
            }
        });
        this.b.aEV_(this.e);
        this.b.d(this.d.getVideoUrl(), getAdapterPosition());
        this.f3215a.setVisibility(8);
        this.c.setVisibility(0);
        LogUtil.a("Suggestion_VideoViewHolder", "mVideoCover.setOnClickListener ok");
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void a(int i) {
        if (i == 1) {
            LogUtil.a("Suggestion_VideoViewHolder", "state VideoPlayer gone");
            this.c.setVisibility(8);
        }
    }

    public void a(ShowLayout showLayout, int i) {
        if (showLayout == null || TextUtils.isEmpty(showLayout.getVideoCoverUrl())) {
            return;
        }
        this.d = showLayout;
        e();
        if (i == getAdapterPosition()) {
            this.f3215a.setVisibility(8);
            LogUtil.a("Suggestion_VideoViewHolder", "mVideoCover = INVISIBLE");
        } else {
            this.f3215a.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, int i2) {
        if (this.b == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = this.e.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            layoutParams.width = i;
            layoutParams.height = i2;
            this.e.setLayoutParams(layoutParams);
        }
    }

    private void e() {
        Glide.with(BaseApplication.e()).load(this.d.getVideoCoverUrl()).apply((BaseRequestOptions<?>) new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL)).into((RequestBuilder<Drawable>) new CustomTarget<Drawable>() { // from class: com.huawei.health.suggestion.ui.fitness.viewholder.FitnessNewDetailVideoViewHolder.1
            @Override // com.bumptech.glide.request.target.Target
            public void onLoadCleared(Drawable drawable) {
            }

            @Override // com.bumptech.glide.request.target.Target
            /* renamed from: aFG_, reason: merged with bridge method [inline-methods] */
            public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                if (drawable.getIntrinsicWidth() == 0) {
                    return;
                }
                FitnessNewDetailVideoViewHolder.this.b(FitnessNewDetailVideoViewHolder.this.f3215a.getWidth(), (int) ((FitnessNewDetailVideoViewHolder.this.f3215a.getWidth() * drawable.getIntrinsicHeight()) / drawable.getIntrinsicWidth()));
                Glide.with(BaseApplication.e()).load(drawable).diskCacheStrategy(DiskCacheStrategy.NONE).into(FitnessNewDetailVideoViewHolder.this.f3215a);
            }

            @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
            public void onLoadFailed(Drawable drawable) {
                LogUtil.h("Suggestion_VideoViewHolder", "loadRoundRectangle onLoadFailed,coverUrl():", FitnessNewDetailVideoViewHolder.this.d.getVideoCoverUrl());
                super.onLoadFailed(drawable);
            }
        });
    }
}
