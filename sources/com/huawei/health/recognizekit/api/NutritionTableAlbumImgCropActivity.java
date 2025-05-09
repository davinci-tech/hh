package com.huawei.health.recognizekit.api;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.recognizekit.api.NutritionTableAlbumImgCropActivity;
import com.huawei.health.recognizekit.view.OverLayerTopView;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.fba;
import defpackage.fbc;
import defpackage.mph;
import defpackage.nrf;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.LogUtil;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

/* loaded from: classes7.dex */
public class NutritionTableAlbumImgCropActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f2959a;
    private HealthButton b;
    private RectF c;
    private float e;
    private Context f;
    private ImageView h;
    private e i;
    private HealthTextView j;
    private LinearLayout k;
    private String m;
    private Point n;
    private OverLayerTopView o;
    private HealthTextView t;
    private Bitmap d = null;
    private boolean g = false;
    private boolean l = false;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_nutrition_table_img_crop);
        LogUtil.c("NutritionTableAlbumImgCropActivity", "onCreate start crop img from album.");
        e();
        auK_(getIntent());
    }

    private void e() {
        OverLayerTopView overLayerTopView;
        this.f = this;
        this.o = (OverLayerTopView) findViewById(R.id.crop_overLayerTopView);
        this.f2959a = (ImageView) findViewById(R.id.ocr_album_iv);
        this.b = (HealthButton) findViewById(R.id.ocr_start_scan_bt);
        ImageView imageView = (ImageView) findViewById(R.id.ocr_crop_back_page);
        this.h = imageView;
        imageView.setImageResource(LanguageUtil.bc(this.f) ? R$drawable.ic_back_white_reverse : R$drawable.ic_back_white);
        this.t = (HealthTextView) findViewById(R.id.ocr_crop_action_title);
        this.j = (HealthTextView) findViewById(R.id.ocr_crop_action_tips);
        int r = nsn.r(this.f);
        this.k = (LinearLayout) findViewById(R.id.ocr_crop_back_ly);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        boolean z = false;
        layoutParams.setMargins(0, r, 0, 0);
        this.k.setLayoutParams(layoutParams);
        c();
        this.b.setText(getString(R$string.IDS_ocr_start_scan).toUpperCase());
        this.b.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.c = fba.auU_(this);
        this.n = fba.auZ_(this);
        this.e = r0.x;
        this.g = nsn.l();
        if (nsn.l() && nsn.ag(this.f)) {
            z = true;
        }
        this.l = z;
        if (z && (overLayerTopView = this.o) != null) {
            overLayerTopView.a();
        }
        LogUtil.c("NutritionTableAlbumImgCropActivity", "mIsFoldable=", Boolean.valueOf(this.g), " mIsTahitiModel=", Boolean.valueOf(this.l));
        this.i = new e(this);
    }

    private void c() {
        this.t.setMaxLines(2);
        this.t.setAutoTextSize(2, 18.0f);
        this.t.setAutoTextInfo(12, 2, 2);
        this.j.setMaxLines(5);
        this.j.setAutoTextSize(2, 14.0f);
        this.j.setAutoTextInfo(12, 2, 2);
    }

    private void auK_(final Intent intent) {
        LogUtil.c("NutritionTableAlbumImgCropActivity", "parserIntent");
        if (intent == null || intent.getData() == null) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: fat
            @Override // java.lang.Runnable
            public final void run() {
                NutritionTableAlbumImgCropActivity.this.auL_(intent);
            }
        });
    }

    public /* synthetic */ void auL_(Intent intent) {
        InputStream inputStream = null;
        try {
            try {
                inputStream = getContentResolver().openInputStream(intent.getData());
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                this.d = decodeStream;
                this.d = fbc.avg_(decodeStream, intent.getIntExtra(ParamConstants.Param.ORIENTATION, 0));
                this.i.sendEmptyMessage(2);
            } catch (FileNotFoundException e2) {
                LogUtil.e("NutritionTableAlbumImgCropActivity", "parserIntent FileNotFoundException, ", ExceptionUtils.d(e2));
            }
        } finally {
            FileUtils.d(inputStream);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Bitmap bitmap = this.d;
        if (bitmap != null) {
            Bitmap ave_ = fba.ave_(bitmap);
            this.d = ave_;
            this.o.setMaxDragRange(ave_);
            this.f2959a.setImageBitmap(this.d);
            return;
        }
        LogUtil.e("NutritionTableAlbumImgCropActivity", "showSelectImg parserIntent bitmap is null.");
        finish();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.b) {
            if (this.d != null) {
                LogUtil.c("NutritionTableAlbumImgCropActivity", "onClick start scan.");
                RectF centerRect = this.o.getCenterRect();
                this.c = centerRect;
                Bitmap auY_ = fba.auY_(centerRect, this.d, this.f);
                this.d = auY_;
                LogUtil.c("NutritionTableAlbumImgCropActivity", "album crop img isSaved= ", Boolean.valueOf(nrf.cJt_(auY_, fba.d)));
                this.t.setVisibility(0);
                this.t.setText(getString(R$string.IDS_ocr_recognizing_nutritiontable));
                this.j.setText(getString(R$string.IDS_ocr_recognizing_nutritiontable_tips));
                this.b.setVisibility(8);
                this.o.b();
                ThreadPoolManager.d().execute(new Runnable() { // from class: fam
                    @Override // java.lang.Runnable
                    public final void run() {
                        NutritionTableAlbumImgCropActivity.this.d();
                    }
                });
            } else {
                LogUtil.e("NutritionTableAlbumImgCropActivity", "start scan btn click,bitmap is null.");
            }
        }
        if (view == this.h) {
            finish();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void d() {
        String nutritionTableOcrResult = mph.b().getNutritionTableOcrResult(this.f, this.d);
        this.m = nutritionTableOcrResult;
        LogUtil.c("NutritionTableAlbumImgCropActivity", "onClick crop img recognize result = ", nutritionTableOcrResult);
        this.i.sendEmptyMessage(1);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Bitmap bitmap = this.d;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.d.recycle();
        }
        e eVar = this.i;
        if (eVar != null) {
            eVar.removeCallbacksAndMessages(null);
        }
        mph.b().release();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        super.initViewTahiti();
        boolean z = nsn.ag(getApplicationContext()) && nsn.l();
        LogUtil.c("NutritionTableAlbumImgCropActivity", "initViewTahiti isTahitiModel: ", Boolean.valueOf(z), " mIsTahitiModel = ", Boolean.valueOf(this.l));
        if (this.l != z) {
            LogUtil.c("NutritionTableAlbumImgCropActivity", "refresh center rectF.");
            this.l = z;
            this.o.a();
        }
    }

    static class e extends Handler {
        private WeakReference<NutritionTableAlbumImgCropActivity> e;

        e(NutritionTableAlbumImgCropActivity nutritionTableAlbumImgCropActivity) {
            this.e = new WeakReference<>(nutritionTableAlbumImgCropActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            NutritionTableAlbumImgCropActivity nutritionTableAlbumImgCropActivity = this.e.get();
            if (nutritionTableAlbumImgCropActivity == null) {
                LogUtil.e("NutritionTableAlbumImgCropActivity", "handleMessage activity is null.");
                return;
            }
            if (message.what == 1) {
                nutritionTableAlbumImgCropActivity.o.e();
                if (fba.b(nutritionTableAlbumImgCropActivity.m)) {
                    fba.a(nutritionTableAlbumImgCropActivity.f, nutritionTableAlbumImgCropActivity.m);
                } else {
                    Intent intent = new Intent(nutritionTableAlbumImgCropActivity, (Class<?>) HealthRecognizeActivity.class);
                    intent.putExtra("ocrResult", "default");
                    nutritionTableAlbumImgCropActivity.startActivity(intent);
                }
                nutritionTableAlbumImgCropActivity.finish();
            }
            if (message.what == 2) {
                nutritionTableAlbumImgCropActivity.b();
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (mph.b().isPluginAvaiable()) {
            return;
        }
        mph.b().a(null);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        fba.avd_(getResources());
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return fba.avc_(super.getResources());
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onMultiWindowModeChanged(boolean z, Configuration configuration) {
        super.onMultiWindowModeChanged(z, configuration);
        LogUtil.c("NutritionTableAlbumImgCropActivity", "onMultiWindowModeChanged isInMultiWindowMode = ", Boolean.valueOf(z));
        OverLayerTopView overLayerTopView = this.o;
        if (overLayerTopView != null) {
            overLayerTopView.a();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
