package com.huawei.health.suggestion.ui.view;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaMetadataRetriever;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.BaseActionDetailPlayerStrategy;
import com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.HeaderViewInterface;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.Video;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.eil;
import defpackage.fpr;
import defpackage.fpu;
import defpackage.fpv;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class ActionDetailView extends LinearLayout implements HeaderViewInterface {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f3409a;
    private HealthTextView b;
    private ImageView c;
    private ActionDetailContentView d;
    private ConstraintLayout e;
    private RelativeLayout f;
    private LinearLayout g;
    private HealthTextView h;
    private String i;
    private ImageView j;
    private boolean k;
    private ViewGroup.MarginLayoutParams l;
    private boolean m;
    private RelativeLayout n;
    private ViewGroup.LayoutParams o;
    private HealthTextView p;
    private TextureView q;
    private LinearLayout r;
    private BaseActionDetailPlayerStrategy s;
    private HealthTextView t;
    private LinearLayout v;
    private String y;

    /* loaded from: classes8.dex */
    public interface HeadStatus {
        public static final int LOADING = 0;
        public static final int LOADING_SUCCESS = 3;
        public static final int LONG_VIDEO_LOADING = 5;
        public static final int LONG_VIDEO_READY = 6;
        public static final int NO_NET = 2;
        public static final int NO_NETWORK = 8;
        public static final int NO_WIFI = 1;
        public static final int RESET_STATUS = 4;
        public static final int SHORT_VIDEO_READY = 7;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.HeaderViewInterface
    public void showCoachImage() {
    }

    public ActionDetailView(Context context) {
        super(context, null);
        this.k = true;
        this.m = true;
        a(context);
    }

    public ActionDetailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k = true;
        this.m = true;
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.sug_action_detail, (ViewGroup) this, true);
        a();
    }

    private void a() {
        this.d = (ActionDetailContentView) findViewById(R.id.sug_action_detail_content);
        this.f = (RelativeLayout) findViewById(R.id.sug_action_headView);
        this.q = (TextureView) findViewById(R.id.sug_action_video);
        this.f3409a = (ImageView) findViewById(R.id.sug_action_close);
        this.g = (LinearLayout) findViewById(R.id.sug_downloading);
        this.r = (LinearLayout) findViewById(R.id.ll_nowifi);
        this.j = (ImageView) findViewById(R.id.iv_download);
        this.h = (HealthTextView) findViewById(R.id.tv_downloading_progress);
        this.b = (HealthTextView) findViewById(R.id.tv_audio_size);
        this.t = (HealthTextView) findViewById(R.id.tv_no_wifi_msg);
        this.c = (ImageView) findViewById(R.id.sug_ation_video_pic);
        this.p = (HealthTextView) findViewById(R.id.tv_no_network_msg);
        this.n = (RelativeLayout) findViewById(R.id.sug_action_header_layout);
        this.e = (ConstraintLayout) findViewById(R.id.sug_action_detail_layout);
        this.v = (LinearLayout) findViewById(R.id.sug_wound_blast_detail);
        p();
        eil.alR_(10003, this.v);
    }

    private void p() {
        RelativeLayout relativeLayout;
        if (this.e == null || (relativeLayout = this.n) == null) {
            LogUtil.h("Suggestion_ActionDetailView", "tahitiAdaptation mActionDetailLayout or mHeaderLayout is null");
            return;
        }
        this.o = relativeLayout.getLayoutParams();
        if (nsn.ag(BaseApplication.getContext())) {
            setMarginParams(5);
        } else if (EnvironmentInfo.k()) {
            setMarginParams(3);
        } else {
            g();
        }
        this.n.setLayoutParams(this.o);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this.e);
        constraintSet.connect(this.n.getId(), 6, this.e.getId(), 6);
        constraintSet.connect(this.n.getId(), 7, this.e.getId(), 7);
        constraintSet.applyTo(this.e);
    }

    private void setMarginParams(int i) {
        float d = new HealthColumnSystem(BaseApplication.getContext(), 0).d(i);
        ViewGroup.LayoutParams layoutParams = this.o;
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            this.l = (ViewGroup.MarginLayoutParams) layoutParams;
        } else {
            this.l = new ViewGroup.MarginLayoutParams(this.o);
        }
        this.l.width = (int) d;
    }

    private void g() {
        ViewGroup.LayoutParams layoutParams = this.o;
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            this.l = (ViewGroup.MarginLayoutParams) layoutParams;
        } else {
            this.l = new ViewGroup.MarginLayoutParams(this.o);
        }
        this.l.width = BaseApplication.getContext().getResources().getDisplayMetrics().widthPixels;
    }

    public void b() {
        p();
        ActionDetailContentView.aLY_(this.d.getContentLayout());
    }

    public View getDownloadView() {
        return this.j;
    }

    public View getCloseView() {
        return this.f3409a;
    }

    public void setFitnessType(String str) {
        this.i = str;
    }

    public void b(AtomicAction atomicAction, int i, Motion motion) {
        if (i != 0 && motion != null && atomicAction == null) {
            atomicAction = e(motion);
        }
        this.d.setContent(atomicAction);
        c(i, motion, atomicAction);
        ConstraintSet constraintSet = new ConstraintSet();
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.sug_action_detail_layout);
        constraintSet.clone(constraintLayout);
        if (motion != null) {
            constraintSet.setDimensionRatio(R.id.sug_action_header_layout, a(motion.acquireMotionPath()));
        }
        constraintSet.applyTo(constraintLayout);
        requestLayout();
    }

    public String a(String str) {
        if (str == null) {
            LogUtil.h("Suggestion_ActionDetailView", "getVideoRatio url null");
            return "h,16:9";
        }
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            try {
                mediaMetadataRetriever.setDataSource(str);
                String extractMetadata = mediaMetadataRetriever.extractMetadata(18);
                String extractMetadata2 = mediaMetadataRetriever.extractMetadata(19);
                if (extractMetadata != null && !extractMetadata.equals(extractMetadata2)) {
                    h();
                    if (CommonUtil.h(extractMetadata) > 0 && CommonUtil.h(extractMetadata2) > 0) {
                        LogUtil.a("Suggestion_ActionDetailView", "getVideoRatio width:", extractMetadata, " height:", extractMetadata2);
                        return "h," + extractMetadata + ":" + extractMetadata2;
                    }
                }
                try {
                    mediaMetadataRetriever.release();
                    return "w,1:1";
                } catch (IOException e) {
                    LogUtil.b("Suggestion_ActionDetailView", "getVideoRatio release exception:", ExceptionUtils.d(e));
                    return "w,1:1";
                }
            } catch (IllegalArgumentException e2) {
                LogUtil.b("Suggestion_ActionDetailView", "MediaMetadataRetriever exception" + e2);
                try {
                    mediaMetadataRetriever.release();
                    return "w,1:1";
                } catch (IOException e3) {
                    LogUtil.b("Suggestion_ActionDetailView", "getVideoRatio release exception:", ExceptionUtils.d(e3));
                    return "w,1:1";
                }
            }
        } finally {
            try {
                mediaMetadataRetriever.release();
            } catch (IOException e4) {
                LogUtil.b("Suggestion_ActionDetailView", "getVideoRatio release exception:", ExceptionUtils.d(e4));
            }
        }
    }

    private void h() {
        ConstraintLayout constraintLayout = this.e;
        if (constraintLayout == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = constraintLayout.getLayoutParams();
        aMa_(layoutParams).setMargins(0, nsn.r(BaseApplication.getActivity()), 0, 0);
        this.e.setLayoutParams(layoutParams);
    }

    public void d(int i, int i2) {
        String str;
        if (i == i2 || i == 0 || i2 == 0) {
            return;
        }
        h();
        if (i <= 0 || i2 <= 0) {
            str = "w,1:1";
        } else {
            LogUtil.a("Suggestion_ActionDetailView", "getVideoRatio width:", Integer.valueOf(i), " height:", Integer.valueOf(i2));
            str = "h," + i + ":" + i2;
        }
        ConstraintSet constraintSet = new ConstraintSet();
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.sug_action_detail_layout);
        if (constraintLayout == null) {
            LogUtil.h("Suggestion_ActionDetailView", "updateDimensionRatio constraintLayout == null");
            return;
        }
        constraintSet.clone(constraintLayout);
        constraintSet.setDimensionRatio(R.id.sug_action_header_layout, str);
        constraintSet.applyTo(constraintLayout);
        requestLayout();
    }

    private ViewGroup.MarginLayoutParams aMa_(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return (ViewGroup.MarginLayoutParams) layoutParams;
        }
        return new ViewGroup.MarginLayoutParams(layoutParams);
    }

    private AtomicAction e(Motion motion) {
        AtomicAction atomicAction = new AtomicAction();
        if (motion == null) {
            return atomicAction;
        }
        atomicAction.setName(motion.acquireName());
        atomicAction.putExtendProperty("actionStep", motion.getActionStep());
        atomicAction.putExtendProperty("introduceLyric", motion.getIntroduceLyric());
        atomicAction.putExtendProperty("breath", motion.getBreath());
        atomicAction.putExtendProperty("feeling", motion.getFeeling());
        atomicAction.putExtendProperty("commonError", motion.getCommonError());
        atomicAction.putExtendProperty("pictures", motion.getPictures());
        atomicAction.putExtendProperty("actionVideo", d(motion.getOriginLogo()));
        return atomicAction;
    }

    private List<Video> d(String str) {
        ArrayList arrayList = new ArrayList(1);
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        Video video = new Video();
        video.saveLogoImgUrl(str);
        arrayList.add(video);
        return arrayList;
    }

    private void c(int i, Motion motion, AtomicAction atomicAction) {
        if (i == 0) {
            d(atomicAction);
            return;
        }
        if (i == 1) {
            c(motion);
        } else if (i == 2) {
            b(motion);
        } else {
            LogUtil.h("Suggestion_ActionDetailView", "initializationPlayer videoType Not defined");
        }
    }

    private void d(AtomicAction atomicAction) {
        if (atomicAction == null) {
            LogUtil.h("Suggestion_ActionDetailView", "initActionLibraryPlayer mVideoPlayerStrategy or actionInfo is null");
            return;
        }
        fpr fprVar = new fpr(this, atomicAction);
        this.s = fprVar;
        if (fprVar instanceof fpr) {
            fprVar.a(this);
        }
    }

    public BaseActionDetailPlayerStrategy getVideoPlayerStrategy() {
        return this.s;
    }

    public void e() {
        BaseActionDetailPlayerStrategy baseActionDetailPlayerStrategy = this.s;
        if (baseActionDetailPlayerStrategy == null) {
            LogUtil.h("Suggestion_ActionDetailView", "onPause mVideoPlayerStrategy is null");
        } else {
            this.k = false;
            baseActionDetailPlayerStrategy.pauseVideo();
        }
    }

    public void c() {
        BaseActionDetailPlayerStrategy baseActionDetailPlayerStrategy = this.s;
        if (baseActionDetailPlayerStrategy == null) {
            LogUtil.h("Suggestion_ActionDetailView", "onResume mVideoPlayerStrategy is null");
        } else {
            this.k = true;
            baseActionDetailPlayerStrategy.onResume();
        }
    }

    private void c(Motion motion) {
        fpu fpuVar = new fpu(this, this.i, motion);
        this.s = fpuVar;
        fpuVar.initMediaPlayer();
        this.f3409a.setVisibility(8);
    }

    private void b(Motion motion) {
        this.s = new fpv(this, motion, this.y);
        this.f3409a.setVisibility(8);
    }

    public void setLongVideoUrl(String str) {
        this.y = str;
    }

    public void setTvDownLoadingProgress(String str) {
        HealthTextView healthTextView = this.h;
        if (healthTextView != null) {
            healthTextView.setText(str);
        }
    }

    public void setVideoTextureView(SurfaceTexture surfaceTexture) {
        TextureView textureView;
        if (surfaceTexture == null || (textureView = this.q) == null) {
            LogUtil.h("Suggestion_ActionDetailView", "setVideoTextureView surfaceTexture or mVideoTextureView can not null");
        } else {
            textureView.setSurfaceTexture(surfaceTexture);
        }
    }

    public void setAudioSize(String str) {
        HealthTextView healthTextView = this.b;
        if (healthTextView != null) {
            healthTextView.setText(str);
        }
    }

    private boolean j() {
        return (this.q == null || this.c == null || this.p == null || !(this.f != null && this.g != null && this.r != null)) ? false : true;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.HeaderViewInterface
    public void refreshHeaderView(int i) {
        if (j()) {
            LogUtil.a("Suggestion_ActionDetailView", "refreshHeaderView headStatus is: ", Integer.valueOf(i));
            switch (i) {
                case 0:
                    i();
                    break;
                case 1:
                    l();
                    break;
                case 2:
                    s();
                    break;
                case 3:
                    f();
                    break;
                case 4:
                    n();
                    break;
                case 5:
                    m();
                    break;
                case 6:
                case 7:
                    o();
                    break;
                case 8:
                    k();
                    break;
                default:
                    LogUtil.h("Suggestion_ActionDetailView", "refreshHeadView headStatus is nothing");
                    break;
            }
        }
    }

    private void o() {
        this.q.setVisibility(0);
        this.f.setVisibility(8);
        this.c.setVisibility(8);
        this.p.setVisibility(8);
    }

    private void f() {
        this.g.setVisibility(8);
        this.r.setVisibility(8);
        this.p.setVisibility(8);
    }

    private void n() {
        this.f.setVisibility(8);
        this.c.setVisibility(0);
        this.c.setImageDrawable(new ColorDrawable(ContextCompat.getColor(BaseApplication.getContext(), R$color.color_normal_titlebar_title)));
        this.p.setVisibility(8);
    }

    private void s() {
        this.f.setVisibility(0);
        this.g.setVisibility(8);
        this.r.setVisibility(0);
        this.p.setVisibility(8);
        this.t.setVisibility(8);
        this.c.setVisibility(0);
    }

    private void m() {
        this.f.setVisibility(0);
        this.g.setVisibility(0);
        this.r.setVisibility(8);
        this.c.setVisibility(8);
        this.p.setVisibility(8);
    }

    private void k() {
        this.f.setVisibility(0);
        this.g.setVisibility(8);
        this.r.setVisibility(0);
        this.p.setVisibility(8);
        this.t.setVisibility(8);
        this.p.setVisibility(0);
        this.c.setVisibility(0);
    }

    private void l() {
        this.f.setVisibility(0);
        this.g.setVisibility(8);
        this.r.setVisibility(0);
        this.p.setVisibility(8);
        this.t.setVisibility(0);
        this.c.setVisibility(0);
    }

    private void i() {
        this.f.setVisibility(0);
        this.g.setVisibility(0);
        this.r.setVisibility(8);
        this.p.setVisibility(8);
        this.c.setVisibility(0);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.HeaderViewInterface
    public void setSurfaceTextureListener(TextureView.SurfaceTextureListener surfaceTextureListener) {
        if (this.q == null) {
            return;
        }
        Object[] objArr = new Object[2];
        objArr[0] = "setSurfaceTextureListener:textureListener is:";
        objArr[1] = surfaceTextureListener == null ? Constants.NULL : "not null";
        LogUtil.h("Suggestion_ActionDetailView", objArr);
        this.q.setSurfaceTextureListener(surfaceTextureListener);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.HeaderViewInterface
    public ImageView getCoachImageView() {
        return this.c;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.HeaderViewInterface
    public View getHeaderView() {
        return this.f;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.HeaderViewInterface
    public boolean getIsShowMediaPlayer() {
        return this.m;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.HeaderViewInterface
    public boolean getIsForeGround() {
        return this.k;
    }

    public void setOnDownLoadViewClickListener(View.OnClickListener onClickListener) {
        ImageView imageView = this.j;
        if (imageView == null || onClickListener == null) {
            return;
        }
        imageView.setOnClickListener(onClickListener);
    }

    public void setOnCloseImageClickListener(View.OnClickListener onClickListener) {
        ImageView imageView = this.f3409a;
        if (imageView != null) {
            imageView.setOnClickListener(onClickListener);
        }
    }

    public void d() {
        BaseActionDetailPlayerStrategy baseActionDetailPlayerStrategy = this.s;
        if (baseActionDetailPlayerStrategy != null) {
            baseActionDetailPlayerStrategy.releasePlayer();
            this.s = null;
        }
    }

    public void setShowMedia(boolean z) {
        this.m = z;
    }
}
