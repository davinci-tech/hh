package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import health.compact.a.CommonUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes6.dex */
public class mfp {
    private mfp() {
    }

    public static Bitmap cgG_(View view, View... viewArr) {
        synchronized (mfp.class) {
            if (view == null) {
                LogUtil.h("PLGACHIEVE_ScreenShotUtils", "screenshotMultiView: mainView is null");
                return null;
            }
            Bitmap cgD_ = cgD_(view);
            if (cgD_ == null) {
                LogUtil.h("PLGACHIEVE_ScreenShotUtils", "screenshotMultiView: mainViewBitmap is null");
                return null;
            }
            if (viewArr != null && viewArr.length != 0) {
                view.getLocationOnScreen(new int[2]);
                Canvas canvas = new Canvas(cgD_);
                Bitmap bitmap = null;
                Bitmap bitmap2 = null;
                for (int i = 0; i < viewArr.length; i++) {
                    View view2 = viewArr[i];
                    if (view2 == null) {
                        LogUtil.h("PLGACHIEVE_ScreenShotUtils", "screenshotMultiView: secondaryView is null -> " + i);
                    } else if (view2.getWidth() == 0 || view2.getHeight() == 0) {
                        LogUtil.h("PLGACHIEVE_ScreenShotUtils", "screenshotMultiView: width or height is 0 -> " + i);
                    } else {
                        try {
                            bitmap2 = Bitmap.createBitmap(view2.getWidth(), view2.getHeight(), Bitmap.Config.ARGB_4444);
                            view2.draw(new Canvas(bitmap2));
                        } catch (OutOfMemoryError e) {
                            if (bitmap2 != null) {
                                bitmap2.recycle();
                            } else {
                                bitmap = bitmap2;
                            }
                            LogUtil.b("PLGACHIEVE_ScreenShotUtils", "screenshotMultiView: error -> " + e.getMessage());
                            bitmap2 = bitmap;
                        }
                        if (bitmap2 == null) {
                            bitmap = null;
                        } else {
                            view2.getLocationOnScreen(new int[2]);
                            canvas.drawBitmap(bitmap2, r8[0] - r6[0], r8[1] - r6[1], (Paint) null);
                            bitmap2.recycle();
                            bitmap = null;
                            bitmap2 = null;
                        }
                    }
                }
                return cgD_;
            }
            return cgD_;
        }
    }

    public static Bitmap cgK_(HealthScrollView healthScrollView) {
        return cgE_(healthScrollView);
    }

    public static Bitmap cgJ_(View view) {
        return cgD_(view);
    }

    private static Bitmap cgz_(Bitmap bitmap, Bitmap bitmap2) {
        Bitmap createBitmap = Bitmap.createBitmap(Math.max(bitmap.getWidth(), bitmap2.getWidth()), bitmap.getHeight() + bitmap2.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        canvas.drawBitmap(bitmap2, 0.0f, bitmap.getHeight(), (Paint) null);
        return createBitmap;
    }

    private static Bitmap cgE_(HealthScrollView healthScrollView) {
        Bitmap bitmap = null;
        if (healthScrollView == null || healthScrollView.getHeight() == 0) {
            LogUtil.h("PLGACHIEVE_ScreenShotUtils", "getBitmapByView scrollview is null or scrollView.height == 0");
            return null;
        }
        int i = 0;
        for (int i2 = 0; i2 < healthScrollView.getChildCount(); i2++) {
            i += healthScrollView.getChildAt(i2).getHeight();
        }
        if (healthScrollView.getWidth() > 0 && i > 0) {
            try {
                bitmap = Bitmap.createBitmap(healthScrollView.getWidth(), i, Bitmap.Config.RGB_565);
            } catch (OutOfMemoryError unused) {
                LogUtil.b("PLGACHIEVE_ScreenShotUtils", "getBitmapByView scrollView OutOfMemoryError");
            }
            if (bitmap == null) {
                return bitmap;
            }
            healthScrollView.draw(new Canvas(bitmap));
        }
        return bitmap;
    }

    private static Bitmap cgD_(View view) {
        Bitmap bitmap = null;
        if (view == null) {
            LogUtil.h("PLGACHIEVE_ScreenShotUtils", "getBitmapByView view == null");
            return null;
        }
        if (view.getHeight() == 0) {
            LogUtil.h("PLGACHIEVE_ScreenShotUtils", "getBitmapByView view view.height == 0");
            return null;
        }
        try {
            bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.RGB_565);
        } catch (OutOfMemoryError unused) {
            LogUtil.b("PLGACHIEVE_ScreenShotUtils", "getBitmapByView view OutOfMemoryError");
        }
        if (bitmap == null) {
            return bitmap;
        }
        view.draw(new Canvas(bitmap));
        return bitmap;
    }

    public static Bitmap cgI_(View view, HealthScrollView healthScrollView, View view2) {
        try {
            Bitmap cgD_ = cgD_(view);
            Bitmap cgE_ = cgE_(healthScrollView);
            Bitmap cgD_2 = cgD_(view2);
            Bitmap cgz_ = (cgD_ == null || cgE_ == null) ? null : cgz_(cgD_, cgE_);
            if (cgz_ == null || cgD_2 == null) {
                return null;
            }
            return cgz_(cgz_, cgD_2);
        } catch (Exception e) {
            LogUtil.b("PLGACHIEVE_ScreenShotUtils", "shareMedalBitmap Exception:", ExceptionUtils.d(e));
            return null;
        }
    }

    public static Bitmap cgH_(View view, HealthScrollView healthScrollView, View view2, int i) {
        Bitmap cgI_ = cgI_(view, healthScrollView, view2);
        LogUtil.a("PLGACHIEVE_ScreenShotUtils", "shareAndSaveBitmap gainNum=", Integer.valueOf(i));
        if (i < 100) {
            return cgI_;
        }
        try {
            return cgB_(cgI_);
        } catch (Exception e) {
            LogUtil.b("PLGACHIEVE_ScreenShotUtils", "shareAndSaveBitmap Exception:", ExceptionUtils.d(e));
            return cgI_;
        }
    }

    private static Bitmap cgB_(Bitmap bitmap) {
        cgF_(BaseApplication.getContext(), bitmap, "all_medal_share_temp.jpg");
        try {
            Bitmap cgA_ = cgA_(CommonUtil.j(BaseApplication.getContext()).getCanonicalPath() + "/Huawei/Health/all_medal_share_temp.jpg");
            if (cgA_ != null && cgA_.getHeight() <= 20000) {
                LogUtil.a("PLGACHIEVE_ScreenShotUtils", "compressMedal compressBitmap != null,Height=", Integer.valueOf(cgA_.getHeight()));
                return cgA_;
            }
            Bitmap cgC_ = cgC_(bitmap, cgA_);
            return cgC_ != null ? cgC_ : bitmap;
        } catch (IOException unused) {
            LogUtil.h("PLGACHIEVE_ScreenShotUtils", "compressShareMedal IOException");
            return bitmap;
        }
    }

    public static String cgF_(Context context, Bitmap bitmap, String str) {
        String str2 = "";
        if (bitmap == null) {
            return "";
        }
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.h("PLGACHIEVE_ScreenShotUtils", "saveScreenShotImage: untrusted -> " + str);
            return "";
        }
        if (Environment.getExternalStorageState().equals("mounted")) {
            try {
                str2 = CommonUtil.j(context).getCanonicalPath() + "/Huawei/Health/";
            } catch (IOException unused) {
                LogUtil.a("PLGACHIEVE_ScreenShotUtils", "getCanonicalPath IOException");
            }
            if (meb.b(str2)) {
                str2 = str2 + str;
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(CommonUtil.c(str2));
                    try {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    } finally {
                    }
                } catch (FileNotFoundException e) {
                    LogUtil.a("PLGACHIEVE_ScreenShotUtils", "saveScreenShotImage e1=", e.getMessage());
                } catch (IOException e2) {
                    LogUtil.a("PLGACHIEVE_ScreenShotUtils", "saveScreenShotImage e2=", e2.getMessage());
                }
            }
        }
        return str2;
    }

    public static int cgy_(Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        return bitmap.getAllocationByteCount();
    }

    private static Bitmap cgA_(String str) {
        Bitmap bitmap = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (file.exists()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inSampleSize = 2;
            options.inJustDecodeBounds = false;
            try {
                bitmap = BitmapFactory.decodeFile(str, options);
            } catch (OutOfMemoryError unused) {
                LogUtil.b("PLGACHIEVE_ScreenShotUtils", "compressMedal loadImage:OutOfMemoryError");
            }
            LogUtil.h("PLGACHIEVE_ScreenShotUtils", "compressMedal showMedalBitmap is delete:", Boolean.valueOf(FileUtils.d(file)));
        }
        if (bitmap == null) {
            LogUtil.h("PLGACHIEVE_ScreenShotUtils", "compressMedal showMedalBitmap loadImage is null!");
        }
        return bitmap;
    }

    public static Bitmap cgC_(Bitmap bitmap, Bitmap bitmap2) {
        Object[] objArr = new Object[2];
        objArr[0] = "compressMedal ensureMaxHeight compressBitmap.Height=";
        objArr[1] = Integer.valueOf(bitmap2 != null ? bitmap2.getHeight() : 0);
        LogUtil.a("PLGACHIEVE_ScreenShotUtils", objArr);
        if (bitmap == null) {
            LogUtil.b("PLGACHIEVE_ScreenShotUtils", "compressMedal ensureMaxHeight fail:params error");
            return null;
        }
        int height = bitmap.getHeight();
        if (height <= 20000) {
            LogUtil.a("PLGACHIEVE_ScreenShotUtils", "compressMedal ensureMaxHeight pass: height not exceed");
            return bitmap;
        }
        LogUtil.a("PLGACHIEVE_ScreenShotUtils", "compressMedal ensureMaxHeight height exceeds, rescale it");
        Matrix matrix = new Matrix();
        float f = 20000.0f / height;
        matrix.setScale(f, f);
        try {
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), height, matrix, true);
        } catch (Exception e) {
            LogUtil.h("PLGACHIEVE_ScreenShotUtils", "compressMedal ensureMaxHeight:exception: ", e.getMessage());
            return null;
        } catch (OutOfMemoryError unused) {
            LogUtil.h("PLGACHIEVE_ScreenShotUtils", "compressMedal ensureMaxHeight:OutOfMemoryError");
            return null;
        }
    }
}
