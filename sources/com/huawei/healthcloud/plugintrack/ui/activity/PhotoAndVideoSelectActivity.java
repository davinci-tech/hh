package com.huawei.healthcloud.plugintrack.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.trackanimation.decoder.VideoDecoder;
import com.huawei.healthcloud.plugintrack.trackanimation.decoder.VideoDecoderCallback;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.PhotoOrVideoSelectChangedListener;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.VideoModel;
import com.huawei.healthcloud.plugintrack.ui.fragment.PhotoSelectFragment;
import com.huawei.healthcloud.plugintrack.ui.fragment.VideoSelectFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.uikit.hwviewpager.widget.HwViewPager;
import defpackage.koq;
import defpackage.nqx;
import defpackage.nrh;
import defpackage.nrz;
import defpackage.nsn;
import defpackage.smy;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class PhotoAndVideoSelectActivity extends BaseActivity {
    private RelativeLayout b;
    private HealthTextView d;
    private smy f;
    private HealthTextView g;
    private RelativeLayout h;
    private int i;
    private PhotoSelectFragment j;
    private nqx m;
    private HealthSubTabWidget n;
    private RelativeLayout o;
    private VideoSelectFragment q;
    private VideoDecoder s;
    private HealthViewPager t;

    /* renamed from: a, reason: collision with root package name */
    private Context f3651a = null;
    private int p = 0;
    private int r = 0;
    private ArrayList<PhotoModel> k = new ArrayList<>(3);
    private ArrayList<VideoModel> l = new ArrayList<>(1);
    private int c = 0;
    private boolean e = false;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.trackalbum_photo_and_video_select_activity);
        this.f3651a = this;
        a();
        e();
        b();
        d();
    }

    private void a() {
        Intent intent = getIntent();
        if (intent != null) {
            try {
                this.i = intent.getIntExtra("key_data_max_photo_count", 9);
                this.p = intent.getIntExtra("total_selected_image_num_key", 0);
                this.k = intent.getParcelableArrayListExtra("selected_image_path_key");
            } catch (ArrayIndexOutOfBoundsException e) {
                LogUtil.b("Track_PhotoAndVideoSelectActivity", "get list error:", LogAnonymous.b((Throwable) e));
            }
        }
    }

    private void e() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.image_select_title_bar);
        this.o = relativeLayout;
        this.h = (RelativeLayout) relativeLayout.findViewById(R.id.base_health_right_l);
        HealthTextView healthTextView = (HealthTextView) this.o.findViewById(R.id.detail_title_text);
        this.d = healthTextView;
        healthTextView.setText(this.f3651a.getText(R.string._2130840064_res_0x7f020a00));
        ImageView imageView = (ImageView) this.o.findViewById(R.id.btn_left);
        Drawable drawable = this.f3651a.getResources().getDrawable(R.drawable._2131431952_res_0x7f0b1210);
        if (LanguageUtil.bc(this.f3651a)) {
            drawable = nrz.cKm_(this.f3651a, drawable);
        }
        imageView.setImageDrawable(drawable);
        this.b = (RelativeLayout) this.o.findViewById(R.id.btn_left_l);
        this.g = (HealthTextView) this.o.findViewById(R.id.image_limit_text);
        h();
        this.b.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.PhotoAndVideoSelectActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PhotoAndVideoSelectActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        if (this.k != null) {
            this.g.setText(getResources().getString(R.string._2130839943_res_0x7f020987, Integer.valueOf(this.k.size()), 3));
        } else {
            this.g.setText(getResources().getString(R.string._2130839943_res_0x7f020987, 0, 3));
        }
    }

    private void b() {
        this.n = (HealthSubTabWidget) findViewById(R.id.photo_and_video_tab);
        HealthViewPager healthViewPager = (HealthViewPager) findViewById(R.id.photo_and_video_viewpager);
        this.t = healthViewPager;
        if (healthViewPager == null) {
            LogUtil.h("Track_PhotoAndVideoSelectActivity", "initViewPager mViewPager is null");
            return;
        }
        healthViewPager.setScrollHeightArea(200);
        this.m = new nqx(this, this.t, this.n);
        PhotoSelectFragment photoSelectFragment = new PhotoSelectFragment();
        this.j = photoSelectFragment;
        photoSelectFragment.a(this.k, this.p, this.i);
        VideoSelectFragment videoSelectFragment = new VideoSelectFragment();
        this.q = videoSelectFragment;
        videoSelectFragment.b(this.l, this.r);
        smy c = this.n.c(this.f3651a.getResources().getString(R.string._2130840063_res_0x7f0209ff));
        this.f = c;
        this.m.c(c, this.j, true);
        this.m.c(this.n.c(this.f3651a.getResources().getString(R.string._2130850480_res_0x7f0232b0)), this.q, false);
        this.t.addOnPageChangeListener(new HwViewPager.OnPageChangeListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.PhotoAndVideoSelectActivity.4
            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                PhotoAndVideoSelectActivity.this.c = i;
                if (i == 0) {
                    if (PhotoAndVideoSelectActivity.this.l != null && PhotoAndVideoSelectActivity.this.l.size() > 0) {
                        nrh.c(PhotoAndVideoSelectActivity.this.f3651a, PhotoAndVideoSelectActivity.this.f3651a.getString(R.string._2130840089_res_0x7f020a19));
                    }
                    if (PhotoAndVideoSelectActivity.this.k == null) {
                        PhotoAndVideoSelectActivity.this.g.setText(PhotoAndVideoSelectActivity.this.getResources().getString(R.string._2130839943_res_0x7f020987, 0, 3));
                        return;
                    } else {
                        PhotoAndVideoSelectActivity.this.g.setText(PhotoAndVideoSelectActivity.this.getResources().getString(R.string._2130839943_res_0x7f020987, Integer.valueOf(PhotoAndVideoSelectActivity.this.k.size()), 3));
                        return;
                    }
                }
                if (PhotoAndVideoSelectActivity.this.k != null && PhotoAndVideoSelectActivity.this.k.size() > 0) {
                    nrh.c(PhotoAndVideoSelectActivity.this.f3651a, PhotoAndVideoSelectActivity.this.f3651a.getString(R.string._2130840089_res_0x7f020a19));
                }
                PhotoAndVideoSelectActivity.this.g.setText(PhotoAndVideoSelectActivity.this.getResources().getString(R.string._2130839943_res_0x7f020987, Integer.valueOf(PhotoAndVideoSelectActivity.this.l.size()), 1));
            }
        });
    }

    private void h() {
        View findViewById = this.o.findViewById(R.id.statusbar_panel);
        if (findViewById == null || !(findViewById.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            return;
        }
        ((RelativeLayout.LayoutParams) findViewById.getLayoutParams()).height = nsn.r(this);
    }

    public boolean c() {
        return this.e;
    }

    private void d() {
        this.q.a(new PhotoOrVideoSelectChangedListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.PhotoAndVideoSelectActivity.2
            @Override // com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.PhotoOrVideoSelectChangedListener
            public void setSelectData(Object obj, int i) {
                if (koq.e(obj, VideoModel.class)) {
                    PhotoAndVideoSelectActivity.this.l = (ArrayList) obj;
                    PhotoAndVideoSelectActivity.this.g.setText(PhotoAndVideoSelectActivity.this.getResources().getString(R.string._2130839943_res_0x7f020987, Integer.valueOf(PhotoAndVideoSelectActivity.this.l.size()), 1));
                    return;
                }
                LogUtil.h("Track_PhotoAndVideoSelectActivity", "select data not instance of list");
            }
        });
        this.j.c(new PhotoOrVideoSelectChangedListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.PhotoAndVideoSelectActivity.3
            @Override // com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.PhotoOrVideoSelectChangedListener
            public void setSelectData(Object obj, int i) {
                if (koq.e(obj, PhotoModel.class)) {
                    PhotoAndVideoSelectActivity.this.k = (ArrayList) obj;
                    PhotoAndVideoSelectActivity photoAndVideoSelectActivity = PhotoAndVideoSelectActivity.this;
                    photoAndVideoSelectActivity.e = photoAndVideoSelectActivity.k.size() > 0;
                    PhotoAndVideoSelectActivity.this.g.setText(PhotoAndVideoSelectActivity.this.getResources().getString(R.string._2130839943_res_0x7f020987, Integer.valueOf(PhotoAndVideoSelectActivity.this.k.size()), 3));
                    return;
                }
                LogUtil.h("Track_PhotoAndVideoSelectActivity", "select data not instance of list");
            }
        });
        this.h.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.PhotoAndVideoSelectActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent();
                if (PhotoAndVideoSelectActivity.this.c == 0) {
                    intent.putParcelableArrayListExtra("selected_image_list", PhotoAndVideoSelectActivity.this.k);
                    PhotoAndVideoSelectActivity.this.setResult(-1, intent);
                    PhotoAndVideoSelectActivity.this.finish();
                } else if (PhotoAndVideoSelectActivity.this.l.size() <= 0 || ((VideoModel) PhotoAndVideoSelectActivity.this.l.get(0)).getDuration() <= 10200) {
                    intent.putParcelableArrayListExtra("selected_video_list", PhotoAndVideoSelectActivity.this.l);
                    PhotoAndVideoSelectActivity.this.setResult(-1, intent);
                    PhotoAndVideoSelectActivity.this.finish();
                } else {
                    PhotoAndVideoSelectActivity photoAndVideoSelectActivity = PhotoAndVideoSelectActivity.this;
                    photoAndVideoSelectActivity.bcf_(intent, (VideoModel) photoAndVideoSelectActivity.l.get(0));
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bcf_(final Intent intent, final VideoModel videoModel) {
        VideoDecoder videoDecoder = new VideoDecoder(this.f3651a);
        this.s = videoDecoder;
        videoDecoder.a(new VideoDecoderCallback() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.PhotoAndVideoSelectActivity.9
            @Override // com.huawei.healthcloud.plugintrack.trackanimation.decoder.VideoDecoderCallback
            public void onResult(boolean z, String str) {
                if (!z) {
                    nrh.c(PhotoAndVideoSelectActivity.this.f3651a, PhotoAndVideoSelectActivity.this.f3651a.getString(R.string._2130840090_res_0x7f020a1a));
                    return;
                }
                videoModel.setVideoPath(str);
                intent.putParcelableArrayListExtra("selected_video_list", PhotoAndVideoSelectActivity.this.l);
                intent.putExtra("selected_video_duration_is_long", true);
                PhotoAndVideoSelectActivity.this.setResult(-1, intent);
                PhotoAndVideoSelectActivity.this.finish();
            }
        });
        this.s.e(videoModel.getVideoPath(), 0, 10);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        VideoDecoder videoDecoder = this.s;
        if (videoDecoder != null) {
            videoDecoder.b();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
