package com.huawei.healthcloud.plugintrack.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.VideoModel;
import com.huawei.healthcloud.plugintrack.ui.fragment.VideoSelectFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nrh;
import defpackage.nrz;
import defpackage.nsn;
import health.compact.a.LanguageUtil;

/* loaded from: classes4.dex */
public class VideoPreviewActivity extends BaseActivity {
    private Context b;
    private int c;
    private HealthCheckBox e;
    private RelativeLayout f;
    private HealthTextView g;
    private VideoModel h;
    private VideoView i;
    private RelativeLayout j;
    private String k;
    private int n = 0;
    private boolean d = false;

    /* renamed from: a, reason: collision with root package name */
    private boolean f3678a = false;

    static /* synthetic */ int b(VideoPreviewActivity videoPreviewActivity) {
        int i = videoPreviewActivity.n;
        videoPreviewActivity.n = i - 1;
        return i;
    }

    static /* synthetic */ int e(VideoPreviewActivity videoPreviewActivity) {
        int i = videoPreviewActivity.n;
        videoPreviewActivity.n = i + 1;
        return i;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("result", this.d);
        intent.putExtra("index", this.c);
        setResult(-1, intent);
        super.onBackPressed();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = this;
        setContentView(R.layout.trackalbum_video_preview_activity);
        e();
        this.f = (RelativeLayout) findViewById(R.id.preview_video_select_title_bar);
        c();
        ImageView imageView = (ImageView) findViewById(R.id.btn_left);
        this.i = (VideoView) findViewById(R.id.play_video);
        Drawable drawable = this.b.getResources().getDrawable(R.drawable._2131431951_res_0x7f0b120f);
        if (LanguageUtil.bc(this.b)) {
            drawable = nrz.cKm_(this.b, drawable);
        }
        imageView.setImageDrawable(drawable);
        this.j = (RelativeLayout) this.f.findViewById(R.id.btn_left_l);
        d();
        this.g = (HealthTextView) this.f.findViewById(R.id.detail_title_text);
        this.e = (HealthCheckBox) this.f.findViewById(R.id.image_select_cb);
        VideoModel videoModel = this.h;
        if (videoModel == null) {
            LogUtil.h("Track_VideoPreviewActivity", "mMedia is null");
            finish();
            return;
        }
        this.d = videoModel.isSelected();
        this.e.setClickable(false);
        this.e.setChecked(this.d);
        b();
        this.g.setText(getResources().getString(R.string._2130839943_res_0x7f020987, Integer.valueOf(this.n), 1));
        if (TextUtils.isEmpty(this.k)) {
            LogUtil.a("Track_VideoPreviewActivity", "mVideoPath is empty!");
            finish();
        } else {
            this.i.setVideoURI(Uri.parse(this.k));
            this.i.start();
        }
    }

    private void b() {
        this.f.findViewById(R.id.base_health_right_l).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.VideoPreviewActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (VideoPreviewActivity.this.d) {
                    VideoPreviewActivity.this.d = false;
                    VideoPreviewActivity.this.e.setChecked(false);
                    VideoPreviewActivity.b(VideoPreviewActivity.this);
                } else if (VideoPreviewActivity.this.f3678a) {
                    VideoPreviewActivity.this.e.setChecked(false);
                    nrh.d(VideoPreviewActivity.this.b, VideoPreviewActivity.this.b.getString(R.string._2130840062_res_0x7f0209fe));
                } else {
                    VideoPreviewActivity.e(VideoPreviewActivity.this);
                    VideoPreviewActivity.this.d = true;
                    VideoPreviewActivity.this.e.setChecked(true);
                }
                VideoPreviewActivity.this.g.setText(VideoPreviewActivity.this.getResources().getString(R.string._2130839943_res_0x7f020987, Integer.valueOf(VideoPreviewActivity.this.n), 1));
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void d() {
        this.j.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.VideoPreviewActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("result", VideoPreviewActivity.this.d);
                intent.putExtra("index", VideoPreviewActivity.this.c);
                VideoPreviewActivity.this.setResult(-1, intent);
                VideoPreviewActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void c() {
        View findViewById = this.f.findViewById(R.id.statusbar_panel);
        if (findViewById == null || !(findViewById.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            return;
        }
        ((RelativeLayout.LayoutParams) findViewById.getLayoutParams()).height = nsn.r(this);
    }

    private void e() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("Track_VideoPreviewActivity", "intent is null");
            return;
        }
        Bundle extras = intent.getExtras();
        if (extras == null) {
            LogUtil.h("Track_VideoPreviewActivity", "bundle is null");
            return;
        }
        this.n = extras.getInt("size", 0);
        this.f3678a = extras.getBoolean("select", false);
        this.c = intent.getIntExtra("index", 0);
        Parcelable parcelable = extras.getParcelable("video");
        if (parcelable instanceof VideoModel) {
            VideoModel videoModel = (VideoModel) parcelable;
            this.h = videoModel;
            this.k = videoModel.getVideoPath();
        }
    }

    public static void bdd_(Context context, Pair<Boolean, VideoModel> pair, int i, int i2, VideoSelectFragment videoSelectFragment) {
        if (context == null || videoSelectFragment == null) {
            LogUtil.h("Track_VideoPreviewActivity", "context or videoSelectFragment is null");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("size", i);
        bundle.putParcelable("video", (Parcelable) pair.second);
        bundle.putBoolean("select", ((Boolean) pair.first).booleanValue());
        bundle.putInt("index", i2);
        Intent intent = new Intent(context, (Class<?>) VideoPreviewActivity.class);
        intent.putExtras(bundle);
        videoSelectFragment.startActivityForResult(intent, 4098);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.i.stopPlayback();
        this.i.destroyDrawingCache();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
