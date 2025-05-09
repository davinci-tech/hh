package com.huawei.watchface.mvp.ui.activity;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.secure.android.common.activity.SafeFragmentActivity;
import com.huawei.uikit.phone.hwbutton.widget.HwButton;
import com.huawei.watchface.R$id;
import com.huawei.watchface.R$layout;
import com.huawei.watchface.R$string;
import com.huawei.watchface.cn;
import com.huawei.watchface.dd;
import com.huawei.watchface.de;
import com.huawei.watchface.l;
import com.huawei.watchface.mvp.model.filedownload.threadpool.ThreadPoolManager;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.DensityUtil;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.SafeHandler;
import com.huawei.watchface.videoedit.utils.FileUtils;

/* loaded from: classes7.dex */
public class ScanImageActivity extends SafeFragmentActivity {
    public static final float FLOAT_DEFAULT = 1.0f;
    public static final int REFRESH_IMAGE_VIEW_MESSAGE = 1000;
    public static final int SCAN_IMAGE_ALPHA = 200;
    public static final int SCAN_IMAGE_DURATION = 1200;
    public static final int SCAN_IMAGE_HALF = 2;
    public static final int SCAN_IMAGE_LENGTH_LIMIT = 5242880;
    public static final int SCAN_IMAGE_MAX_HEIGHT = 300;
    public static final int SCAN_IMAGE_PORTION = 3;
    public static final int SCAN_IMAGE_ROTATION = 180;
    public static final String SOURCE_IMAGE_PATH = "sourceImagePath";
    public static final String SOURCE_IMAGE_URI = "sourceImageUri";
    private static final String TAG = "ScanImageActivity";
    private ImageView imageView;
    private Context mConText;
    private Handler mHandler;
    private HwButton mHwButton;
    private AnimationDrawable mLoadingAnimation;
    private ImageView mScanLoadingView;
    private Bitmap scanBitmap;
    private ImageView searchImageView;
    private int bitmapHeight = 0;
    boolean isShowNotLegalDialog = false;
    boolean isImageIllegal = false;

    @Override // com.huawei.secure.android.common.activity.SafeFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        final String str;
        final String str2;
        super.onCreate(bundle);
        setContentView(R$layout.watchface_activity_scan);
        this.mConText = this;
        this.mHandler = new a(this);
        dd.a(this);
        this.imageView = (ImageView) findViewById(R$id.imageView);
        this.mHwButton = (HwButton) findViewById(R$id.watch_search_cancel);
        this.searchImageView = (ImageView) findViewById(R$id.search_imageView);
        ImageView imageView = (ImageView) findViewById(R$id.scan_image_loading);
        this.mScanLoadingView = imageView;
        this.mLoadingAnimation = (AnimationDrawable) imageView.getDrawable();
        this.imageView.setImageAlpha(200);
        Intent intent = getIntent();
        if (intent == null) {
            HwLog.e(TAG, "onCreate() intent is null.");
            finish();
            return;
        }
        this.mScanLoadingView.setVisibility(0);
        AnimationDrawable animationDrawable = this.mLoadingAnimation;
        if (animationDrawable != null) {
            animationDrawable.start();
        }
        try {
            str = intent.getStringExtra("sourceImagePath");
            str2 = intent.getStringExtra("sourceImageUri");
        } catch (Exception unused) {
            HwLog.e(TAG, "onCreate() ClassCastException.");
            str = null;
            str2 = null;
        }
        if (de.b(str) != 0) {
            HwLog.i(TAG, "checkSearchFilePath is not support");
            this.isShowNotLegalDialog = true;
        }
        this.mHwButton.setWidth(dd.e());
        ThreadPoolManager.getInstance().execute(new Runnable() { // from class: com.huawei.watchface.mvp.ui.activity.ScanImageActivity$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                ScanImageActivity.this.m923x56a9e347(str2, str);
            }
        });
        this.mHwButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.watchface.mvp.ui.activity.ScanImageActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ScanImageActivity.this.m924x57e03626(view);
            }
        });
    }

    /* renamed from: lambda$onCreate$0$com-huawei-watchface-mvp-ui-activity-ScanImageActivity, reason: not valid java name */
    /* synthetic */ void m923x56a9e347(String str, String str2) {
        this.scanBitmap = de.a(this, str2, Uri.parse(str));
        boolean saveAndCompressAndValidImage = saveAndCompressAndValidImage();
        HwLog.i(TAG, "end load bitmap");
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 1000;
        obtainMessage.arg1 = saveAndCompressAndValidImage ? 1 : 0;
        this.mHandler.sendMessage(obtainMessage);
    }

    /* renamed from: lambda$onCreate$1$com-huawei-watchface-mvp-ui-activity-ScanImageActivity, reason: not valid java name */
    /* synthetic */ void m924x57e03626(View view) {
        l.a().a(true, true);
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    private boolean saveAndCompressAndValidImage() {
        String a2 = de.a(this.scanBitmap);
        if (TextUtils.isEmpty(a2)) {
            HwLog.i(TAG, "saveAndCompressAndValidImage saveSearchImagePath is null");
            showValidImageDialog();
            return false;
        }
        int fileSize = FileUtils.getFileSize(a2);
        if (fileSize < 5242880 && !this.isShowNotLegalDialog) {
            HwLog.i(TAG, "saveAndCompressImage success");
            this.isImageIllegal = true;
            l.a().a(a2);
            return true;
        }
        this.isImageIllegal = false;
        HwLog.i(TAG, "saveAndCompressImage saveSearchImagePath is null or fileSize is too large:" + fileSize);
        showValidImageDialog();
        return false;
    }

    private void showValidImageDialog() {
        BackgroundTaskUtils.postInMainThread(new Runnable() { // from class: com.huawei.watchface.mvp.ui.activity.ScanImageActivity$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                ScanImageActivity.this.m927xf651181a();
            }
        });
    }

    /* renamed from: lambda$showValidImageDialog$4$com-huawei-watchface-mvp-ui-activity-ScanImageActivity, reason: not valid java name */
    /* synthetic */ void m927xf651181a() {
        cn a2 = new cn.a(this.mConText).a(DensityUtil.getStringById(R$string.search_image_dialog_content)).b(DensityUtil.getStringById(R$string.cancel), new View.OnClickListener() { // from class: com.huawei.watchface.mvp.ui.activity.ScanImageActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ScanImageActivity.this.m925xf3e4725c(view);
            }
        }).a(DensityUtil.getStringById(R$string.re_upload), new View.OnClickListener() { // from class: com.huawei.watchface.mvp.ui.activity.ScanImageActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ScanImageActivity.this.m926xf51ac53b(view);
            }
        }).a();
        if (a2.isShowing()) {
            return;
        }
        HwLog.i(TAG, "mNoTitleCustomAlertDialog is Showing");
        a2.setCancelable(false);
        a2.show();
    }

    /* renamed from: lambda$showValidImageDialog$2$com-huawei-watchface-mvp-ui-activity-ScanImageActivity, reason: not valid java name */
    /* synthetic */ void m925xf3e4725c(View view) {
        HwLog.i(TAG, "showSaveDialog, CancelSaveWatchFace.");
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$showValidImageDialog$3$com-huawei-watchface-mvp-ui-activity-ScanImageActivity, reason: not valid java name */
    /* synthetic */ void m926xf51ac53b(View view) {
        HwLog.i(TAG, "showValidImageDialog, re upload.");
        l.a().a(true, this.isImageIllegal);
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAnimation(int i) {
        HwLog.i(TAG, "startAnimation dx:" + i);
        this.bitmapHeight = i;
        startLoopAnimation(i, true);
    }

    private void startLoopAnimation(int i, final boolean z) {
        ImageView imageView = this.imageView;
        if (imageView == null) {
            HwLog.w(TAG, "startLoopAnimation imageView is null");
            return;
        }
        imageView.setVisibility(0);
        ImageView imageView2 = this.imageView;
        imageView2.setRotation(imageView2.getRotation() + 180.0f);
        this.imageView.animate().translationY(i).setDuration(1200L).setInterpolator(new LinearInterpolator()).withEndAction(new Runnable() { // from class: com.huawei.watchface.mvp.ui.activity.ScanImageActivity$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                ScanImageActivity.this.m928xe3ffdeff(z);
            }
        }).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.huawei.watchface.mvp.ui.activity.ScanImageActivity$$ExternalSyntheticLambda6
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ScanImageActivity.this.m929xe53631de(valueAnimator);
            }
        }).start();
    }

    /* renamed from: lambda$startLoopAnimation$5$com-huawei-watchface-mvp-ui-activity-ScanImageActivity, reason: not valid java name */
    /* synthetic */ void m928xe3ffdeff(boolean z) {
        if (!z) {
            finish();
        } else {
            startLoopAnimation(0, false);
        }
    }

    /* renamed from: lambda$startLoopAnimation$6$com-huawei-watchface-mvp-ui-activity-ScanImageActivity, reason: not valid java name */
    /* synthetic */ void m929xe53631de(ValueAnimator valueAnimator) {
        updateImageViewHeight();
    }

    private void updateImageViewHeight() {
        int i = this.bitmapHeight < 900 ? (int) ((r0 / 3) * 1.0f) : 300;
        float translationY = this.imageView.getTranslationY();
        float f = i;
        if (translationY >= f) {
            float f2 = i * 2;
            float f3 = this.bitmapHeight - translationY;
            translationY = f3 < f2 ? f * (f3 / (f2 * 1.0f)) : f;
        }
        ViewGroup.LayoutParams layoutParams = this.imageView.getLayoutParams();
        layoutParams.height = (int) translationY;
        this.imageView.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getAndInitImageViewDx() {
        ImageView imageView = this.searchImageView;
        if (imageView == null || this.scanBitmap == null) {
            HwLog.w(TAG, "getAndInitImageViewDx searchImageView or scanBitmap is null");
            return 0;
        }
        int height = imageView.getHeight();
        float width = this.searchImageView.getWidth();
        float height2 = (this.scanBitmap.getHeight() * 1.0f) / this.scanBitmap.getWidth();
        if ((height * 1.0f) / width > height2) {
            float f = height2 * width;
            initImageViewTopPosition(height, f);
            return (int) f;
        }
        this.searchImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return height;
    }

    private void initImageViewTopPosition(int i, float f) {
        ImageView imageView = this.imageView;
        if (imageView == null) {
            HwLog.w(TAG, "initImageViewTopPosition imageView is null");
            return;
        }
        int i2 = (int) ((i - f) / 2.0f);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.setMargins(0, i2, 0, 0);
        this.imageView.setLayoutParams(layoutParams);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        HwLog.i(TAG, "onBackPressed");
        l.a().a(true, this.isImageIllegal);
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        dd.a(configuration, "onConfigurationChanged");
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.secure.android.common.activity.SafeFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        dd.a(getResources().getConfiguration(), "onResume");
        HwLog.i(TAG, "onResume() enter.");
        HwButton hwButton = this.mHwButton;
        if (hwButton != null) {
            hwButton.setWidth(dd.e());
        }
        super.onResume();
    }

    @Override // com.huawei.secure.android.common.activity.SafeFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        HwLog.i(TAG, "onDestroy() enter.");
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
        Bitmap bitmap = this.scanBitmap;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        this.scanBitmap.recycle();
        this.scanBitmap = null;
    }

    public class a extends SafeHandler<ScanImageActivity> {
        public a(ScanImageActivity scanImageActivity) {
            super(scanImageActivity);
        }

        @Override // com.huawei.watchface.utils.SafeHandler
        public void handlerMessageAction(Message message) {
            if (message != null && getObj() != null) {
                if (getObj().mLoadingAnimation == null || getObj().mScanLoadingView == null) {
                    HwLog.i(ScanImageActivity.TAG, "handlerMessageAction() mLoadingAnimation is null or mScanLoadingView is null");
                    return;
                }
                HwLog.i(ScanImageActivity.TAG, "handlerMessageAction() message.what: " + message.what);
                if (message.what == 1000) {
                    getObj().mLoadingAnimation.stop();
                    getObj().mScanLoadingView.setVisibility(8);
                    if (getObj().scanBitmap != null && getObj().searchImageView != null) {
                        getObj().searchImageView.setImageBitmap(getObj().scanBitmap);
                        int andInitImageViewDx = ScanImageActivity.this.getAndInitImageViewDx();
                        if (message.arg1 != 0) {
                            ScanImageActivity.this.startAnimation(andInitImageViewDx);
                            return;
                        } else {
                            HwLog.w(ScanImageActivity.TAG, "handlerMessageAction() msg.arg1 is 0");
                            return;
                        }
                    }
                    getObj().finish();
                    HwLog.w(ScanImageActivity.TAG, "handlerMessageAction() scanBitmap or searchImageView is null.");
                    return;
                }
                return;
            }
            HwLog.i(ScanImageActivity.TAG, "handlerMessageAction() msg is null or getObj() is null");
        }
    }
}
