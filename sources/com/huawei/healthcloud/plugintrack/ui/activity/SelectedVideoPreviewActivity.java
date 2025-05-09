package com.huawei.healthcloud.plugintrack.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.VideoView;
import androidx.core.content.ContextCompat;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.VideoModel;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.nsf;

/* loaded from: classes4.dex */
public class SelectedVideoPreviewActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private String f3666a;
    private CustomTitleBar b;
    private VideoModel c;
    private VideoView e;

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.trackalbum_selected_video_preview_activity);
        this.e = (VideoView) findViewById(R.id.play_video);
        d();
        c();
        if (this.c == null || TextUtils.isEmpty(this.f3666a)) {
            LogUtil.h("Track_VideoPreviewActivity", "mMedia or mVideoPath is null");
            finish();
        } else {
            this.e.setVideoURI(Uri.parse(this.f3666a));
            this.e.start();
        }
    }

    private void c() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.selected_video_preview_titlebar);
        this.b = customTitleBar;
        customTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.SelectedVideoPreviewActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SelectedVideoPreviewActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.b.setRightButtonClickable(true);
        this.b.setRightButtonVisibility(0);
        this.b.setRightButtonDrawable(ContextCompat.getDrawable(this, R.drawable._2131428441_res_0x7f0b0459), nsf.h(R.string._2130841397_res_0x7f020f35));
        this.b.setTitleText(getResources().getString(R.string._2130839943_res_0x7f020987, 1, 1));
        this.b.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.SelectedVideoPreviewActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SelectedVideoPreviewActivity.this.setResult(-1, new Intent());
                SelectedVideoPreviewActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void d() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("Track_VideoPreviewActivity", "intent is null");
            return;
        }
        Parcelable parcelableExtra = intent.getParcelableExtra("video");
        if (parcelableExtra instanceof VideoModel) {
            VideoModel videoModel = (VideoModel) parcelableExtra;
            this.c = videoModel;
            this.f3666a = videoModel.getVideoPath();
        }
    }

    public static void e(Context context, VideoModel videoModel) {
        if (context == null) {
            LogUtil.h("Track_VideoPreviewActivity", "context is null");
            return;
        }
        Intent intent = new Intent(context, (Class<?>) SelectedVideoPreviewActivity.class);
        intent.putExtra("video", videoModel);
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, 6);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.e.stopPlayback();
        this.e.destroyDrawingCache();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
