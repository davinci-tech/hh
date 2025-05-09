package defpackage;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.helper.MediaHelper;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginfitnessadvice.Attribute;
import com.huawei.pluginfitnessadvice.Coordinate;
import com.huawei.pluginfitnessadvice.Cover;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import health.compact.a.UnitUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class fnw extends HealthPagerAdapter implements TextureView.SurfaceTextureListener, MediaPlayer.OnVideoSizeChangedListener {
    private LinearLayout b;
    private List c;
    private LinearLayout e;
    private MediaHelper f;
    private HealthTextView g;
    private HealthTextView h;
    private Motion i;
    private int j;
    private HealthTextView k;
    private ImageView l;
    private HealthTextView m;
    private TextureView n;
    private ImageView o;
    private Surface p;
    private HealthTextView q;
    private HealthTextView r;
    private HealthTextView s;
    private String t;
    private SparseArray<View> y;

    /* renamed from: a, reason: collision with root package name */
    private boolean f12578a = true;
    private Handler d = new Handler(Looper.getMainLooper());

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public fnw(List list, int i) {
        this.c = list;
        this.j = i;
        this.y = new SparseArray<>(list.size());
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        if (this.c.size() > 1) {
            return 1;
        }
        return this.c.size();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view = this.y.get(i);
        if (view == null) {
            view = View.inflate(viewGroup.getContext(), this.j, null);
            aBB_(view);
        }
        if (i == 0) {
            if (!(this.c.get(i) instanceof Motion)) {
                return view;
            }
            this.i = (Motion) this.c.get(i);
            c();
            e(viewGroup.getContext());
            aBC_(viewGroup);
        } else {
            b(i);
        }
        this.y.put(i, view);
        viewGroup.addView(view);
        return view;
    }

    private void b(int i) {
        this.n.setVisibility(8);
        this.o.setVisibility(0);
        if (koq.b(this.c, i)) {
            LogUtil.h("Suggestion_CoachVideoPlayPageAdapter", "setCoachPicImgDes position is out of mData.");
            return;
        }
        if (this.c.get(i) instanceof Cover) {
            Cover cover = (Cover) this.c.get(i);
            if (cover == null) {
                LogUtil.h("Suggestion_CoachVideoPlayPageAdapter", "setCoachPicImgDes cover is null.");
                return;
            }
            nrf.cIu_(cover.getUrl(), this.o);
            this.e.setVisibility(8);
            List<Coordinate> coordinates = cover.getCoordinates();
            if (koq.b(coordinates)) {
                LogUtil.h("Suggestion_CoachVideoPlayPageAdapter", "setCoachPicImgDes coordinates is null.");
                return;
            }
            String str = "";
            int i2 = 0;
            while (i2 < coordinates.size()) {
                int i3 = i2 + 1;
                str = BaseApplication.getContext().getString(R.string._2130851530_res_0x7f0236ca, str, BaseApplication.getContext().getString(R.string._2130848729_res_0x7f022bd9, UnitUtil.e(i3, 1, 0), coordinates.get(i2).getTip()));
                i2 = i3;
            }
            this.h.setText(str);
        }
    }

    private void aBC_(ViewGroup viewGroup) {
        this.g.setText(this.i.acquireName());
        this.r.setText(ffy.a(viewGroup.getContext().getApplicationContext(), this.i.acquireDifficulty()));
        this.q.setText(ffy.e(this.i.getTrainingPoints()));
        this.s.setText(viewGroup.getContext().getResources().getString(R.string._2130848728_res_0x7f022bd8, ffy.e(this.i.getTrainingPoints())));
        List<Attribute> equipments = this.i.getEquipments();
        if (koq.b(equipments)) {
            this.k.setText(viewGroup.getContext().getResources().getString(R.string._2130848730_res_0x7f022bda));
        } else {
            this.k.setText(ffy.d(equipments));
        }
        this.h.setText(ffy.awS_(viewGroup.getContext().getApplicationContext(), this.i.getDescription(), Constants.LINK, R.drawable._2131431626_res_0x7f0b10ca));
    }

    private void c() {
        if (this.f12578a) {
            this.n.setVisibility(0);
            this.n.setOutlineProvider(new gfm(nrr.e(BaseApplication.getContext(), 8.0f)));
            this.n.setClipToOutline(true);
            this.n.setSurfaceTextureListener(this);
            MediaHelper mediaHelper = new MediaHelper();
            this.f = mediaHelper;
            mediaHelper.b(this.t);
            this.f.setSdSources(this.i.acquireMotionPath());
            return;
        }
        this.n.setVisibility(8);
        this.o.setVisibility(0);
        nrf.cJB_(this.i.acquirePicUrl(), this.o);
        LogUtil.a("Suggestion_CoachVideoPlayPageAdapter", "instantiateItem2");
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        View view = this.y.get(i);
        if (view != null) {
            viewGroup.removeView(view);
        }
    }

    private void aBB_(View view) {
        this.o = (ImageView) view.findViewById(R.id.sug_coachi_iv_pic);
        this.l = (ImageView) view.findViewById(R.id.sug_iv_coach_intro_orign);
        this.m = (HealthTextView) view.findViewById(R.id.sug_coach_intro_orign_new_textview);
        this.e = (LinearLayout) view.findViewById(R.id.sug_coach_ll_first);
        this.n = (TextureView) view.findViewById(R.id.sug_coachi_sv_pic);
        this.g = (HealthTextView) view.findViewById(R.id.sug_coachi_tv_actiontitle);
        this.r = (HealthTextView) view.findViewById(R.id.sug_coachi_tv_traindif);
        this.s = (HealthTextView) view.findViewById(R.id.sug_tv_trainpoint);
        this.q = (HealthTextView) view.findViewById(R.id.sug_coachi_tv_trainpoint);
        this.k = (HealthTextView) view.findViewById(R.id.sug_coachi_tv_equipment);
        this.h = (HealthTextView) view.findViewById(R.id.sug_coachi_tv_des);
        this.b = (LinearLayout) view.findViewById(R.id.sug_coachi_bottom);
        this.o.setOutlineProvider(new gfm(nrr.e(BaseApplication.getContext(), 8.0f)));
        this.o.setClipToOutline(true);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        LogUtil.a("Suggestion_CoachVideoPlayPageAdapter", "onSurfaceTextureAvailable");
        Surface surface = new Surface(surfaceTexture);
        this.p = surface;
        MediaHelper mediaHelper = this.f;
        if (mediaHelper == null) {
            LogUtil.h("Suggestion_CoachVideoPlayPageAdapter", "onSurfaceTextureAvailable mMediaHelper is null");
            return;
        }
        mediaHelper.aCu_(surface);
        if (this.f.aCq_() == null) {
            LogUtil.h("Suggestion_CoachVideoPlayPageAdapter", "onSurfaceTextureAvailable player is null");
            return;
        }
        this.f.aCq_().setOnVideoSizeChangedListener(this);
        this.f.start();
        this.d.postDelayed(new Runnable() { // from class: fob
            @Override // java.lang.Runnable
            public final void run() {
                fnw.this.e();
            }
        }, 500L);
    }

    /* synthetic */ void e() {
        LogUtil.a("Suggestion_CoachVideoPlayPageAdapter", "mSugCoachImg.setVisibility(View.GONE);", Long.valueOf(System.currentTimeMillis()));
        ImageView imageView = this.o;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        LogUtil.a("Suggestion_CoachVideoPlayPageAdapter", "onSurfaceTextureDestroyed");
        this.f.release();
        return false;
    }

    public void e(String str) {
        this.t = str;
    }

    private void e(Context context) {
        if (context == null) {
            LogUtil.h("Suggestion_CoachVideoPlayPageAdapter", "hideOrShowCoachOrigin context is null");
        } else {
            if (TextUtils.isEmpty(this.i.getOriginLogo())) {
                this.b.setVisibility(4);
                return;
            }
            this.b.setVisibility(0);
            this.m.setText(context.getApplicationContext().getString(R.string._2130848551_res_0x7f022b27));
            nrf.cJB_(this.i.getOriginLogo(), this.l);
        }
    }

    private void b(int i, int i2) {
        if (this.n == null) {
            LogUtil.h("Suggestion_CoachVideoPlayPageAdapter", "updateTextureViewSizeCenterCrop mSugCoachPic is null");
            return;
        }
        float f = i;
        float f2 = i2;
        Matrix matrix = new Matrix();
        float max = Math.max(r0.getWidth() / f, this.n.getHeight() / f2);
        matrix.preTranslate((this.n.getWidth() - i) / 2.0f, (this.n.getHeight() - i2) / 2.0f);
        matrix.preScale(f / this.n.getWidth(), f2 / this.n.getHeight());
        matrix.postScale(max, max, this.n.getWidth() / 2.0f, this.n.getHeight() / 2.0f);
        this.n.setTransform(matrix);
        this.n.postInvalidate();
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        b(i, i2);
    }
}
