package com.huawei.healthcloud.plugintrack.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel;
import com.huawei.healthcloud.plugintrack.ui.fragment.PhotoSelectFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import defpackage.nrf;
import defpackage.nrz;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import java.io.Serializable;

/* loaded from: classes4.dex */
public class PhotoPreviewActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private int f3654a;
    private Context b;
    private HealthCheckBox c;
    private ImageView d;
    private PhotoModel f;
    private RelativeLayout h;
    private RelativeLayout i;
    private HealthTextView j;
    private int l = 0;
    private boolean g = false;
    private boolean e = false;

    static /* synthetic */ int b(PhotoPreviewActivity photoPreviewActivity) {
        int i = photoPreviewActivity.l;
        photoPreviewActivity.l = i + 1;
        return i;
    }

    static /* synthetic */ int d(PhotoPreviewActivity photoPreviewActivity) {
        int i = photoPreviewActivity.l;
        photoPreviewActivity.l = i - 1;
        return i;
    }

    public static void bcg_(Context context, Pair<Boolean, PhotoModel> pair, int i, int i2, PhotoSelectFragment photoSelectFragment) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) PhotoPreviewActivity.class);
        intent.putExtra("size", i);
        intent.putExtra(PresenterUtils.PHOTO, (Parcelable) pair.second);
        intent.putExtra("select", (Serializable) pair.first);
        intent.putExtra("index", i2);
        if (photoSelectFragment != null) {
            photoSelectFragment.startActivityForResult(intent, 4097);
        } else if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, 4097);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("result", this.g);
        intent.putExtra("index", this.f3654a);
        setResult(-1, intent);
        super.onBackPressed();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = this;
        setContentView(R.layout.trackalbum_photo_preview_activity);
        c();
        this.h = (RelativeLayout) findViewById(R.id.preview_image_select_title_bar);
        a();
        this.d = (ImageView) findViewById(R.id.previewImage);
        ImageView imageView = (ImageView) findViewById(R.id.btn_left);
        Drawable drawable = this.b.getResources().getDrawable(R.drawable._2131431951_res_0x7f0b120f);
        if (LanguageUtil.bc(this.b)) {
            drawable = nrz.cKm_(this.b, drawable);
        }
        imageView.setImageDrawable(drawable);
        this.i = (RelativeLayout) this.h.findViewById(R.id.btn_left_l);
        b();
        this.j = (HealthTextView) this.h.findViewById(R.id.detail_title_text);
        this.c = (HealthCheckBox) this.h.findViewById(R.id.image_select_cb);
        PhotoModel photoModel = this.f;
        if (photoModel == null) {
            LogUtil.b("Track_PhotoPreviewActivity", "mMedia is null");
            finish();
            return;
        }
        this.g = photoModel.isSelected();
        this.c.setClickable(false);
        this.c.setChecked(this.g);
        e();
        this.j.setText(getResources().getString(R.string._2130839943_res_0x7f020987, Integer.valueOf(this.l), 3));
        if (this.b instanceof Activity) {
            nrf.cIH_(this.f.getPath(), new RequestOptions().priority(Priority.IMMEDIATE).fitCenter().skipMemoryCache(true), 0.2f, DrawableTransitionOptions.withCrossFade(), this.d);
        }
    }

    private void e() {
        this.h.findViewById(R.id.base_health_right_l).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.PhotoPreviewActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PhotoPreviewActivity.this.g) {
                    PhotoPreviewActivity.this.g = false;
                    PhotoPreviewActivity.this.c.setChecked(false);
                    PhotoPreviewActivity.d(PhotoPreviewActivity.this);
                } else if (PhotoPreviewActivity.this.e) {
                    PhotoPreviewActivity.this.c.setChecked(false);
                    Toast.makeText(PhotoPreviewActivity.this.b, R.string._2130839942_res_0x7f020986, 0).show();
                } else {
                    PhotoPreviewActivity.b(PhotoPreviewActivity.this);
                    PhotoPreviewActivity.this.g = true;
                    PhotoPreviewActivity.this.c.setChecked(true);
                }
                PhotoPreviewActivity.this.j.setText(PhotoPreviewActivity.this.getResources().getString(R.string._2130839943_res_0x7f020987, Integer.valueOf(PhotoPreviewActivity.this.l), 3));
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void b() {
        this.i.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.PhotoPreviewActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("result", PhotoPreviewActivity.this.g);
                intent.putExtra("index", PhotoPreviewActivity.this.f3654a);
                PhotoPreviewActivity.this.setResult(-1, intent);
                PhotoPreviewActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void a() {
        View findViewById = this.h.findViewById(R.id.statusbar_panel);
        if (findViewById == null || !(findViewById.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            return;
        }
        ((RelativeLayout.LayoutParams) findViewById.getLayoutParams()).height = nsn.r(this);
    }

    private void c() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        this.l = intent.getIntExtra("size", 0);
        this.e = intent.getBooleanExtra("select", false);
        Parcelable parcelableExtra = intent.getParcelableExtra(PresenterUtils.PHOTO);
        if (parcelableExtra instanceof PhotoModel) {
            this.f = (PhotoModel) parcelableExtra;
        }
        this.f3654a = intent.getIntExtra("index", 0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
