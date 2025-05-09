package com.huawei.pluginsocialshare.camera;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.ble.BleConstants;
import defpackage.jcu;
import defpackage.mud;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/* loaded from: classes6.dex */
public class MyCropActivity extends Activity implements View.OnClickListener {
    private ClipImageLayout c = null;
    private ImageView d;
    private ImageView e;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_clip_image);
        this.e = (ImageView) findViewById(R.id.iv_back);
        ImageView imageView = (ImageView) findViewById(R.id.tv_use);
        this.d = imageView;
        imageView.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.c = (ClipImageLayout) findViewById(R.id.clipImageLayout);
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.a("Share_MyCropActivity", "intent null");
            finish();
            return;
        }
        String c = CommonUtil.c(intent.getStringExtra(BleConstants.KEY_PATH));
        if (TextUtils.isEmpty(c)) {
            LogUtil.a("Share_MyCropActivity", "path null");
            finish();
            return;
        }
        int c2 = c(c);
        Bitmap cpF_ = cpF_(c);
        if (cpF_ == null) {
            finish();
        } else if (c2 == 0) {
            this.c.setImageBitmap(cpF_);
        } else {
            this.c.setImageBitmap(cpG_(c2, cpF_));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.e) {
            finish();
        }
        if (view == this.d) {
            String cpH_ = cpH_(this.c.cpu_());
            Intent intent = new Intent();
            intent.putExtra("bitmap", cpH_);
            setResult(-1, intent);
            finish();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private Bitmap cpF_(String str) {
        Bitmap bitmap = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String c = CommonUtil.c(str);
        if (TextUtils.isEmpty(c)) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        if (!(getApplicationContext().getSystemService(Constants.NATIVE_WINDOW_SUB_DIR) instanceof WindowManager)) {
            LogUtil.a("Share_MyCropActivity", "windowManager=null");
            return null;
        }
        if (((WindowManager) getApplicationContext().getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay().isWideColorGamut()) {
            options.inPreferredColorSpace = ColorSpace.get(ColorSpace.Named.DISPLAY_P3);
        }
        BitmapFactory.decodeFile(c, options);
        int i = options.outHeight * options.outWidth * 2;
        int c2 = (int) (mud.c() / 4);
        if (c2 < 1048576) {
            c2 = mud.c() > ((long) 1048576) * 2 ? 1048576 : (int) (mud.c() / 2);
        }
        int ceil = i > c2 ? (int) Math.ceil(i / c2) : 1;
        int ceil2 = options.outWidth > 4032 ? (int) Math.ceil(options.outWidth / 4032) : 1;
        if (ceil <= ceil2) {
            ceil = ceil2;
        }
        options.inSampleSize = ceil;
        LogUtil.a("Share_MyCropActivity", "inSampleSize:", Integer.valueOf(options.inSampleSize));
        options.inJustDecodeBounds = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inDither = false;
        try {
            FileInputStream fileInputStream = new FileInputStream(c);
            try {
                bitmap = BitmapFactory.decodeFileDescriptor(fileInputStream.getFD(), null, options);
                fileInputStream.close();
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException | IllegalArgumentException | OutOfMemoryError unused) {
            LogUtil.a("Share_MyCropActivity", "createBitmap Exception");
        }
        return bitmap;
    }

    private String cpH_(Bitmap bitmap) {
        File file = new File(jcu.f);
        String str = null;
        if (!file.exists() && !file.mkdirs()) {
            LogUtil.h("Share_MyCropActivity", "saveBitmapToFile:mkdirs error");
            return null;
        }
        if (bitmap == null) {
            return null;
        }
        File file2 = new File(file, "MyCropActivity_cropImage.jpg");
        try {
            str = file2.getCanonicalPath();
        } catch (IOException e) {
            LogUtil.h("Share_MyCropActivity", "getCanonicalPath", LogAnonymous.b((Throwable) e));
        }
        try {
            FileOutputStream openOutputStream = FileUtils.openOutputStream(file2);
            try {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 95, openOutputStream);
                file2.deleteOnExit();
                openOutputStream.flush();
                if (openOutputStream != null) {
                    openOutputStream.close();
                }
            } catch (Throwable th) {
                if (openOutputStream != null) {
                    try {
                        openOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException e2) {
            LogUtil.h("Share_MyCropActivity", "saveBitmapToFile:IOException ", LogAnonymous.b((Throwable) e2));
        } catch (IllegalArgumentException e3) {
            LogUtil.h("Share_MyCropActivity", "saveBitmapToFile:IllegalArgumentException ", LogAnonymous.b((Throwable) e3));
        }
        return str;
    }

    private int c(String str) {
        int attributeInt;
        try {
            attributeInt = new ExifInterface(str).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 1);
        } catch (IOException unused) {
            LogUtil.a("Share_MyCropActivity", "eIOException e");
        }
        if (attributeInt == 3) {
            return 180;
        }
        if (attributeInt != 6) {
            return attributeInt != 8 ? 0 : 270;
        }
        return 90;
    }

    private Bitmap cpG_(int i, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        try {
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
        } catch (IllegalArgumentException unused) {
            LogUtil.h("Share_MyCropActivity", "rotateBitmap fail: IllegalArgumentException");
            return bitmap;
        } catch (OutOfMemoryError unused2) {
            LogUtil.h("Share_MyCropActivity", "rotateBitmap fail: OutOfMemoryError");
            return bitmap;
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
