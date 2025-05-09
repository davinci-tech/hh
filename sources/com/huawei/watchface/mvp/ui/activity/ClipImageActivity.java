package com.huawei.watchface.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.secure.android.common.intent.SafeIntent;
import com.huawei.skinner.base.SkinBaseFragmentActivity;
import com.huawei.watchface.R$id;
import com.huawei.watchface.R$layout;
import com.huawei.watchface.de;
import com.huawei.watchface.mvp.model.datatype.ClipOptions;
import com.huawei.watchface.mvp.ui.widget.ClipImageView;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.SafeHandler;
import com.huawei.watchface.videoedit.utils.Utils;

/* loaded from: classes7.dex */
public class ClipImageActivity extends SkinBaseFragmentActivity implements View.OnClickListener {
    public static final String CLIP_RESULT_PATH = "clippedImagePath";
    public static final String CLIP_TARGET_HEIGHT = "clipTargetHeight";
    public static final String CLIP_TARGET_SHAPE = "clipTargetShape";
    public static final String CLIP_TARGET_WIDTH = "clipTargetWidth";
    public static final int DEFAULT_CLICK_TIME = 3000;
    public static final int DEFAULT_TARGET_HEIGHT = 454;
    public static final int DEFAULT_TARGET_WIDTH = 454;
    public static final String IMAGE_SUFFIX = "imageSuffix";
    public static final String IMAGE_SUFFIX_IS_NOT_SUPPORT = "imageSuffixIsNotSupport";
    public static final int IMAGE_TYPE_NOT_SUPPORT = 2000;
    public static final String ORG_IMAGE_PATH = "orgImagePath";
    public static final String ORG_WINDOWS_HEIGHT = "orgWindowsHeight";
    public static final String ORG_WINDOWS_WIDTH = "orgWindowsWidth";
    public static final int REFRESH_IMAGE_VIEW_MESSAGE = 1000;
    public static final String SOURCE_IMAGE_PATH = "sourceImagePath";
    public static final String SOURCE_IMAGE_SCALE_X = "scaleX";
    public static final String SOURCE_IMAGE_SCALE_Y = "scaleY";
    public static final String SOURCE_IMAGE_TRANS_X = "transX";
    public static final String SOURCE_IMAGE_TRANS_Y = "transY";
    public static final String SOURCE_IMAGE_URI = "sourceImageUri";
    private static final String TAG = "ClipImageActivity";
    private ImageView confirmButton;
    private long lastSaveAlbumClick;
    private Bitmap mClipBitmap;
    private ImageView mClipLoadingView;
    private ClipOptions mClipOptions;
    private Handler mHandler;
    private ClipImageView mImageView;
    private AnimationDrawable mLoadingAnimation;
    private Bitmap mOriginBitmap;

    @Override // com.huawei.skinner.base.SkinBaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        boolean z;
        String str;
        super.onCreate(bundle);
        setContentView(R$layout.watchface_activity_watch_face_photo_album_image_clip);
        setRequestedOrientation(1);
        this.mHandler = new a(this);
        this.mImageView = (ClipImageView) findViewById(R$id.clip_image_view);
        CommonUtils.a(getWindow(), 0, false);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R$id.action_button_layout);
        if (relativeLayout.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) relativeLayout.getLayoutParams();
            marginLayoutParams.topMargin = CommonUtils.a((Context) this);
            relativeLayout.setLayoutParams(marginLayoutParams);
        }
        ImageView imageView = (ImageView) findViewById(R$id.clip_image_loading);
        this.mClipLoadingView = imageView;
        this.mLoadingAnimation = (AnimationDrawable) imageView.getDrawable();
        ImageView imageView2 = (ImageView) findViewById(R$id.cancel_clip);
        this.confirmButton = (ImageView) findViewById(R$id.confirm_clip);
        imageView2.setOnClickListener(this);
        this.confirmButton.setOnClickListener(this);
        Intent intent = getIntent();
        if (intent == null) {
            HwLog.e(TAG, "onCreate() intent is null.");
            setResult(0, null);
            finish();
            return;
        }
        try {
            str = intent.getStringExtra("sourceImagePath");
            z = intent.getBooleanExtra("isSecondEdit", false);
        } catch (ClassCastException unused) {
            HwLog.e(TAG, "onCreate() ClassCastException.");
            z = false;
            str = null;
        }
        if (str == null) {
            HwLog.e(TAG, "onCreate() sourcePath is null.");
            setResult(0, null);
            finish();
            return;
        }
        displayClipBitmap(str, z, intent);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x014d A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void displayClipBitmap(final java.lang.String r19, final boolean r20, android.content.Intent r21) {
        /*
            Method dump skipped, instructions count: 390
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.mvp.ui.activity.ClipImageActivity.displayClipBitmap(java.lang.String, boolean, android.content.Intent):void");
    }

    /* renamed from: lambda$displayClipBitmap$0$com-huawei-watchface-mvp-ui-activity-ClipImageActivity, reason: not valid java name */
    /* synthetic */ void m920x7d715b68(boolean z, String str, String str2, String str3) {
        HwLog.i(TAG, "start load bitmap isSecondEdit:" + z);
        if (z) {
            this.mClipBitmap = Utils.getPicture(str);
            this.mClipOptions.setClipOrgFilePath(str);
        } else {
            Uri parse = Uri.parse(str2);
            if (de.a(str3) == 0) {
                this.mClipBitmap = de.a(this, str3, parse);
            } else {
                if (de.a(str3) == 1) {
                    this.mClipBitmap = null;
                    Message obtainMessage = this.mHandler.obtainMessage();
                    obtainMessage.what = 2000;
                    obtainMessage.obj = de.c(str3);
                    this.mHandler.sendMessage(obtainMessage);
                    return;
                }
                this.mClipBitmap = null;
            }
        }
        HwLog.i(TAG, "end load bitmap");
        Message obtainMessage2 = this.mHandler.obtainMessage();
        obtainMessage2.what = 1000;
        this.mHandler.sendMessage(obtainMessage2);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R$id.cancel_clip) {
            setResult(0, null);
        } else {
            HwLog.i(TAG, "onClick() ConfirmButton Click!");
            if (Math.abs(System.currentTimeMillis() - this.lastSaveAlbumClick) < 3000) {
                HwLog.i(TAG, "onClick disabled Click");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            this.lastSaveAlbumClick = System.currentTimeMillis();
            ClipOptions resultClipOptions = this.mImageView.getResultClipOptions();
            if (resultClipOptions == null) {
                HwLog.e(TAG, "onClick() options is null!");
                setResult(0, null);
                finish();
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            this.mOriginBitmap = null;
            if (this.mImageView.getDrawable() instanceof BitmapDrawable) {
                this.mOriginBitmap = ((BitmapDrawable) this.mImageView.getDrawable()).getBitmap();
            }
            Bitmap bitmap = this.mOriginBitmap;
            if (bitmap == null) {
                setResult(0, null);
                finish();
                ViewClickInstrumentation.clickOnView(view);
                return;
            } else {
                de.b(bitmap, resultClipOptions);
                Intent intent = new Intent();
                if (!resultClipOptions.getResultPaths().isEmpty()) {
                    intent.putExtra(CLIP_RESULT_PATH, resultClipOptions.getResultPaths().get(0));
                    setResult(-1, intent);
                } else {
                    setResult(0, null);
                }
            }
        }
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.skinner.base.SkinBaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        HwLog.i(TAG, "onDestroy() enter.");
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
        Bitmap bitmap = this.mClipBitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.mClipBitmap.recycle();
            this.mClipBitmap = null;
        }
        Bitmap bitmap2 = this.mOriginBitmap;
        if (bitmap2 == null || bitmap2.isRecycled()) {
            return;
        }
        HwLog.i(TAG, "onDestroy() mOriginBitmap recycle");
        this.mOriginBitmap.recycle();
        this.mOriginBitmap = null;
    }

    static class a extends SafeHandler<ClipImageActivity> {
        public a(ClipImageActivity clipImageActivity) {
            super(clipImageActivity);
        }

        @Override // com.huawei.watchface.utils.SafeHandler
        public void handlerMessageAction(Message message) {
            if (message == null) {
                return;
            }
            HwLog.i(ClipImageActivity.TAG, "handlerMessageAction() message.what: " + message.what);
            if (message.what == 1000) {
                getObj().mLoadingAnimation.stop();
                getObj().mClipLoadingView.setVisibility(8);
                getObj().confirmButton.setEnabled(true);
                if (getObj().mClipBitmap != null && getObj().mImageView != null) {
                    getObj().mImageView.setClipOptions(getObj().mClipOptions);
                    getObj().mImageView.setImageBitmap(getObj().mClipBitmap);
                    return;
                } else {
                    getObj().setResult(0, null);
                    getObj().finish();
                    HwLog.w(ClipImageActivity.TAG, "handlerMessageAction() mClipBitmap or mImageView is null.");
                    return;
                }
            }
            if (message.what == 2000) {
                Object obj = message.obj;
                String str = obj instanceof String ? (String) obj : "";
                HwLog.e(ClipImageActivity.TAG, "IMAGE_TYPE_NOT_SUPPORT suffix:" + str);
                getObj().mLoadingAnimation.stop();
                getObj().mClipLoadingView.setVisibility(8);
                getObj().confirmButton.setEnabled(true);
                SafeIntent safeIntent = new SafeIntent(new Intent());
                safeIntent.putExtra(ClipImageActivity.IMAGE_SUFFIX_IS_NOT_SUPPORT, 1);
                safeIntent.putExtra(ClipImageActivity.IMAGE_SUFFIX, str);
                getObj().setResult(0, safeIntent);
                getObj().finish();
                HwLog.w(ClipImageActivity.TAG, "handlerMessageAction() image type is not support.");
                return;
            }
            HwLog.w(ClipImageActivity.TAG, "handlerMessageAction() Handler default.");
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
