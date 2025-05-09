package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ImageDecoder;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.gif.GifOptions;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.utils.AsyncLoadDrawableCallback;
import com.huawei.ui.commonui.utils.TagResourcePolicy;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import huawei.android.hwcolorpicker.HwColorPicker;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.commons.io.FileUtils;

/* loaded from: classes6.dex */
public class nrf {
    public static final int e = (int) BaseApplication.e().getResources().getDimension(R.dimen._2131362361_res_0x7f0a0239);
    public static final int d = (int) BaseApplication.e().getResources().getDimension(R.dimen._2131362006_res_0x7f0a00d6);
    public static final int c = (int) BaseApplication.e().getResources().getDimension(R.dimen._2131364527_res_0x7f0a0aaf);

    private static boolean b(int i, int i2) {
        return i > 0 && i2 > 0;
    }

    public static int c(int i) {
        return -1;
    }

    public static Bitmap cHM_(byte[] bArr) {
        if (bArr == null) {
            LogUtil.b("CommonUI_BitmapUtils", "getBitmapByBytes data == null");
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public static byte[] cHp_(Bitmap bitmap, boolean z) {
        if (bitmap == null || bitmap.isRecycled()) {
            LogUtil.h("CommonUI_BitmapUtils", "bitmapToByteArray sourceBitmap is null or recycled");
            return null;
        }
        return cHo_(bitmap, 100, z);
    }

    private static byte[] cHo_(Bitmap bitmap, int i, boolean z) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(i == 100 ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        if (z) {
            bitmap.recycle();
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
        } catch (IOException unused) {
            LogUtil.b("CommonUI_BitmapUtils", "bitmapToByteArray IOException");
        }
        return byteArray;
    }

    public static Bitmap cJj_(Bitmap bitmap, int i, Bitmap bitmap2, int i2) {
        Bitmap createBitmap;
        if (bitmap == null || bitmap2 == null) {
            LogUtil.h("CommonUI_BitmapUtils", "mergeBitmap:firstBmp/secondBmp is null!");
            return bitmap;
        }
        Bitmap bitmap3 = null;
        try {
            createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap2.getHeight() + i2, bitmap.getConfig());
        } catch (IllegalArgumentException | OutOfMemoryError unused) {
        }
        try {
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawBitmap(bitmap, 0.0f, i, (Paint) null);
            canvas.drawBitmap(bitmap2, 0.0f, i2, (Paint) null);
            return createBitmap;
        } catch (IllegalArgumentException | OutOfMemoryError unused2) {
            bitmap3 = createBitmap;
            LogUtil.h("CommonUI_BitmapUtils", "mergeBitmap:IllegalArgumentException|OutOfMemoryError!");
            return bitmap3;
        }
    }

    private static Bitmap cJk_(Bitmap bitmap, Bitmap bitmap2, Context context, int i) {
        if (bitmap != null && !bitmap.isRecycled() && bitmap2 != null && !bitmap2.isRecycled()) {
            float f = context.getResources().getDisplayMetrics().density;
            try {
                Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
                new Canvas(copy).drawBitmap(bitmap2, nsn.c(context, r8), (bitmap.getHeight() - (i * f)) - bitmap2.getHeight(), (Paint) null);
                return copy;
            } catch (OutOfMemoryError unused) {
                LogUtil.b("CommonUI_BitmapUtils", "mergeBitmap OutOfMemoryError");
            }
        }
        return bitmap;
    }

    public static Bitmap cHA_(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("CommonUI_BitmapUtils", "decodeFromFile() pathName is empty.");
            return null;
        }
        String c2 = CommonUtil.c(str);
        if (TextUtils.isEmpty(c2)) {
            LogUtil.h("CommonUI_BitmapUtils", "decodeFromFile() filePath is empty.");
            return null;
        }
        return cHz_(new File(c2));
    }

    public static Bitmap cHz_(File file) {
        try {
            return BitmapFactory.decodeFile(file.getCanonicalPath());
        } catch (IOException unused) {
            LogUtil.h("CommonUI_BitmapUtils", "Make the canonical path error");
            return null;
        } catch (OutOfMemoryError unused2) {
            LogUtil.h("CommonUI_BitmapUtils", "Decode bitmap OutOfMemoryError");
            return null;
        }
    }

    public static String cJr_(String str, Bitmap bitmap, Bitmap.CompressFormat compressFormat, boolean z) {
        BufferedOutputStream bufferedOutputStream;
        File e2;
        if (bitmap == null || TextUtils.isEmpty(str)) {
            LogUtil.h("CommonUI_BitmapUtils", "bitmap == null or fileName is empty");
            return str;
        }
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                e2 = e(str, z);
                bufferedOutputStream = new BufferedOutputStream(FileUtils.openOutputStream(e2));
            } catch (Throwable th) {
                th = th;
                bufferedOutputStream = null;
            }
        } catch (IOException unused) {
        }
        try {
            bitmap.compress(compressFormat, 100, bufferedOutputStream);
            String canonicalPath = e2.getCanonicalPath();
            e(bufferedOutputStream);
            return canonicalPath;
        } catch (IOException unused2) {
            bufferedOutputStream2 = bufferedOutputStream;
            LogUtil.h("CommonUI_BitmapUtils", "saveBitmap IOException");
            e(bufferedOutputStream2);
            return str;
        } catch (Throwable th2) {
            th = th2;
            e(bufferedOutputStream);
            throw th;
        }
    }

    public static Bitmap cJp_(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("CommonUI_BitmapUtils", "fileName is empty");
            return null;
        }
        try {
            return cJo_(e(str, z).getCanonicalPath());
        } catch (IOException unused) {
            LogUtil.h("CommonUI_BitmapUtils", "readBitmap IOException");
            return null;
        }
    }

    public static boolean b(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("CommonUI_BitmapUtils", "fileName is empty");
            return false;
        }
        try {
            return e(str, z).delete();
        } catch (IOException unused) {
            LogUtil.h("CommonUI_BitmapUtils", "clearBitmap IOException");
            return false;
        }
    }

    public static Bitmap cJo_(String str) {
        return BitmapFactory.decodeFile(str);
    }

    private static File e(String str, boolean z) throws IOException {
        Context e2 = BaseApplication.e();
        File canonicalFile = new File(z ? e2.getCacheDir() : e2.getFilesDir(), "save_bitmap" + File.separator + str).getCanonicalFile();
        File parentFile = canonicalFile.getParentFile();
        if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
            LogUtil.h("CommonUI_BitmapUtils", "mkdirs file error");
        }
        return canonicalFile;
    }

    private static void e(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                LogUtil.b("CommonUI_BitmapUtils", "An exception occurred while closing the 'Closeable' object.");
            }
        }
    }

    public static Bitmap cHD_(byte[] bArr, int i, int i2) {
        if (bArr == null || bArr.length == 0) {
            LogUtil.h("CommonUI_BitmapUtils", "decodeSampledBitmapFromBytes fileName is empty.");
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        try {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            options.inSampleSize = cHr_(options, i, i2);
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        } catch (OutOfMemoryError e2) {
            LogUtil.b("CommonUI_BitmapUtils", "decodeSampledBitmapFromBytes", ExceptionUtils.d(e2));
            return null;
        }
    }

    private static int cHr_(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        if (i2 <= 0 || i <= 0 || (i3 <= i2 && i4 <= i)) {
            return 1;
        }
        int min = Math.min(Math.round(i3 / i2), Math.round(i4 / i));
        while ((i4 * i3) / (min * min) > i * i2 * 2) {
            min++;
        }
        return min;
    }

    public static byte[] cHV_(Bitmap bitmap, boolean z) {
        byte[] bArr = null;
        if (bitmap == null) {
            LogUtil.h("CommonUI_BitmapUtils", "getByteArrayOfThumbBitmap() sourceBitmap = null!");
            return null;
        }
        Bitmap cHH_ = cHH_(bitmap);
        if (cHH_ == null) {
            LogUtil.h("CommonUI_BitmapUtils", "getByteArrayOfThumbBitmap() sourceBitmap == null!");
        } else {
            int i = 100;
            int i2 = 0;
            int i3 = 0;
            int i4 = 100;
            while (true) {
                i2++;
                byte[] cHo_ = cHo_(cHH_, i, false);
                if (i2 == 1 && cHo_.length < 27648) {
                    bArr = cHo_;
                    break;
                }
                int i5 = i - i3;
                if (i5 <= 10) {
                    break;
                }
                if (cHo_.length >= 27648) {
                    i4 = i;
                    i -= i5 / 2;
                } else {
                    i3 = i;
                    i = ((i4 - i) / 2) + i;
                    bArr = cHo_;
                }
            }
            if (z) {
                bitmap.recycle();
            }
        }
        if (bArr != null) {
            LogUtil.a("CommonUI_BitmapUtils", "getByteArrayOfThumbBitmap() result size = ", Integer.valueOf(bArr.length));
        }
        return bArr;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: IfRegionVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r9v7 double, still in use, count: 2, list:
          (r9v7 double) from 0x0041: ARITH (4.0d double) * (r9v7 double) A[WRAPPED]
          (r9v7 double) from 0x0050: PHI (r9v4 double) = (r9v2 double), (r9v7 double) binds: [B:14:0x004d, B:9:0x0044] A[DONT_GENERATE, DONT_INLINE]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.dex.visitors.regions.TernaryMod.makeTernaryInsn(TernaryMod.java:114)
        	at jadx.core.dex.visitors.regions.TernaryMod.processRegion(TernaryMod.java:62)
        	at jadx.core.dex.visitors.regions.TernaryMod.enterRegion(TernaryMod.java:45)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:67)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.TernaryMod.process(TernaryMod.java:35)
        	at jadx.core.dex.visitors.regions.IfRegionVisitor.process(IfRegionVisitor.java:34)
        	at jadx.core.dex.visitors.regions.IfRegionVisitor.visit(IfRegionVisitor.java:30)
        */
    private static android.graphics.Bitmap cHH_(android.graphics.Bitmap r11) {
        /*
            java.lang.String r0 = "CommonUI_BitmapUtils"
            if (r11 != 0) goto Le
            java.lang.String r1 = "extractMaxThumbNail() sourceBitmap == null!"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r1)
            return r11
        Le:
            int r1 = r11.getWidth()
            int r2 = r11.getHeight()
            java.lang.String r3 = "extractMaxThumbNail() original width : "
            java.lang.Integer r4 = java.lang.Integer.valueOf(r1)
            java.lang.String r5 = " original height : "
            java.lang.Integer r6 = java.lang.Integer.valueOf(r2)
            java.lang.String r7 = "size = "
            int r8 = r11.getByteCount()
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r4, r5, r6, r7, r8}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r3)
            r3 = 4616189618054758400(0x4010000000000000, double:4.0)
            r5 = 4648488871632306176(0x4082c00000000000, double:600.0)
            r7 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            if (r2 <= r1) goto L47
            double r9 = (double) r2
            double r9 = r9 * r7
            double r9 = r9 / r5
            double r3 = r3 * r9
            int r3 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r3 > 0) goto L50
            goto L51
        L47:
            double r9 = (double) r1
            double r9 = r9 * r7
            double r9 = r9 / r5
            double r3 = r3 * r9
            int r3 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r3 > 0) goto L50
            goto L51
        L50:
            r7 = r9
        L51:
            double r2 = (double) r2
            double r2 = r2 / r7
            int r2 = (int) r2
            double r3 = (double) r1
            double r3 = r3 / r7
            int r1 = (int) r3
            android.graphics.Bitmap r3 = android.media.ThumbnailUtils.extractThumbnail(r11, r1, r2)
            java.lang.String r4 = "extractMaxThumbNail() after width : "
            java.lang.Integer r5 = java.lang.Integer.valueOf(r1)
            java.lang.String r6 = " original height : "
            java.lang.Integer r7 = java.lang.Integer.valueOf(r2)
            java.lang.String r8 = "size = "
            int r11 = r11.getByteCount()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r11)
            java.lang.Object[] r11 = new java.lang.Object[]{r4, r5, r6, r7, r8, r9}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r11)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nrf.cHH_(android.graphics.Bitmap):android.graphics.Bitmap");
    }

    public static Bitmap cJx_(Bitmap bitmap, int i, int i2) {
        if (bitmap == null) {
            LogUtil.h("CommonUI_BitmapUtils", "scaleThumbBitmap sourceBitmap null");
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap bitmap2 = bitmap;
        while (true) {
            if (width > i || height > i2 || bitmap2.getByteCount() > 32768) {
                LogUtil.a("CommonUI_BitmapUtils", "scaleThumbBitmap error bitmap too big , width = ", Integer.valueOf(width), " height = ", Integer.valueOf(height), "bytecount = ", Integer.valueOf(bitmap2.getByteCount()));
                width = (int) ((width / Math.sqrt(2.0d)) + 0.5d);
                height = (int) ((height / Math.sqrt(2.0d)) + 0.5d);
                bitmap2 = Bitmap.createScaledBitmap(bitmap, width, height, true);
            } else {
                LogUtil.a("CommonUI_BitmapUtils", "scaleThumbBitmap picture size = ", Integer.valueOf(bitmap2.getByteCount()), "width = ", Integer.valueOf(width), " height = ", Integer.valueOf(height));
                return bitmap2;
            }
        }
    }

    @Deprecated
    public static void cIu_(String str, ImageView imageView) {
        cID_(str, imageView);
    }

    public static void d(String str, HealthImageView healthImageView) {
        cID_(str, healthImageView);
    }

    private static void cID_(String str, ImageView imageView) {
        if (imageView == null) {
            return;
        }
        Glide.with(cHK_(imageView)).load(str).into(imageView);
    }

    @Deprecated
    public static void cIn_(int i, ImageView imageView) {
        cIy_(i, imageView);
    }

    public static void b(int i, HealthImageView healthImageView) {
        cIy_(i, healthImageView);
    }

    private static void cIy_(int i, ImageView imageView) {
        if (imageView == null) {
            return;
        }
        Glide.with(cHK_(imageView)).load(Integer.valueOf(i)).into(imageView);
    }

    @Deprecated
    public static void cIs_(File file, ImageView imageView) {
        cIB_(file, imageView);
    }

    public static void b(File file, HealthImageView healthImageView) {
        cIB_(file, healthImageView);
    }

    private static void cIB_(File file, ImageView imageView) {
        if (imageView == null) {
            return;
        }
        Glide.with(cHK_(imageView)).load(file).into(imageView);
    }

    @Deprecated
    public static void cIp_(Drawable drawable, ImageView imageView) {
        cIA_(drawable, imageView);
    }

    private static void cIA_(Drawable drawable, ImageView imageView) {
        if (imageView == null) {
            return;
        }
        Glide.with(cHK_(imageView)).load(drawable).into(imageView);
    }

    @Deprecated
    public static void cIv_(String str, RequestOptions requestOptions, ImageView imageView) {
        cIE_(str, requestOptions, imageView);
    }

    private static void cIE_(String str, RequestOptions requestOptions, ImageView imageView) {
        if (imageView == null) {
            return;
        }
        Glide.with(cHK_(imageView)).load(str).apply((BaseRequestOptions<?>) requestOptions).into(imageView);
    }

    @Deprecated
    public static void cIt_(File file, RequestOptions requestOptions, ImageView imageView) {
        cIC_(file, requestOptions, imageView);
    }

    private static void cIC_(File file, RequestOptions requestOptions, ImageView imageView) {
        if (imageView == null) {
            return;
        }
        Glide.with(cHK_(imageView)).load(file).apply((BaseRequestOptions<?>) requestOptions).into(imageView);
    }

    @Deprecated
    public static void cIo_(int i, RequestOptions requestOptions, ImageView imageView) {
        cIz_(i, requestOptions, imageView);
    }

    private static void cIz_(int i, RequestOptions requestOptions, ImageView imageView) {
        if (imageView == null) {
            return;
        }
        Glide.with(cHK_(imageView)).load(Integer.valueOf(i)).apply((BaseRequestOptions<?>) requestOptions).into(imageView);
    }

    public static void d(String str, Target<Drawable> target) {
        Glide.with(c()).load(str).into((RequestBuilder<Drawable>) target);
    }

    public static void cIq_(Drawable drawable, Target<Drawable> target) {
        Glide.with(c()).load(drawable).into((RequestBuilder<Drawable>) target);
    }

    public static void b(String str, RequestOptions requestOptions, Target<Drawable> target) {
        Glide.with(c()).load(str).apply((BaseRequestOptions<?>) requestOptions).into((RequestBuilder<Drawable>) target);
    }

    @Deprecated
    public static void cIH_(String str, RequestOptions requestOptions, float f, DrawableTransitionOptions drawableTransitionOptions, ImageView imageView) {
        cII_(str, requestOptions, f, drawableTransitionOptions, imageView);
    }

    private static void cII_(String str, RequestOptions requestOptions, float f, DrawableTransitionOptions drawableTransitionOptions, ImageView imageView) {
        if (imageView == null) {
            return;
        }
        Glide.with(cHK_(imageView)).load(str).apply((BaseRequestOptions<?>) requestOptions).thumbnail(f).transition(drawableTransitionOptions).into(imageView);
    }

    public static void b(String str, Target<Bitmap> target) {
        Glide.with(c()).asBitmap().load(str).into((RequestBuilder<Bitmap>) target);
    }

    @Deprecated
    public static void cIF_(String str, RequestOptions requestOptions, RequestListener<Drawable> requestListener, ImageView imageView, boolean z) {
        cIG_(str, requestOptions, requestListener, imageView, z);
    }

    private static void cIG_(String str, RequestOptions requestOptions, RequestListener<Drawable> requestListener, ImageView imageView, boolean z) {
        if (imageView == null) {
            return;
        }
        if (z) {
            Glide.with(imageView.getContext()).load(str).apply((BaseRequestOptions<?>) requestOptions).listener(requestListener).transition(DrawableTransitionOptions.withCrossFade()).into(imageView);
        } else {
            Glide.with(imageView.getContext()).load(str).apply((BaseRequestOptions<?>) requestOptions).addListener(requestListener).into(imageView);
        }
    }

    public static void cIw_(String str, RequestOptions requestOptions, ImageView imageView) {
        cIx_(str, requestOptions, imageView, null, null);
    }

    public static void cIx_(String str, RequestOptions requestOptions, ImageView imageView, Drawable drawable, RequestListener<GifDrawable> requestListener) {
        if (imageView == null) {
            return;
        }
        Glide.with(cHK_(imageView)).asGif().load(str).placeholder(drawable).apply((BaseRequestOptions<?>) requestOptions).listener(requestListener).set(GifOptions.DECODE_FORMAT, DecodeFormat.PREFER_ARGB_8888).into(imageView);
    }

    public static void d(int i, HealthImageView healthImageView) {
        cIJ_(i, healthImageView);
    }

    private static void cIJ_(int i, ImageView imageView) {
        if (imageView == null || i == 0) {
            LogUtil.a("CommonUI_BitmapUtils", "imageView is null");
        } else {
            LogUtil.a("CommonUI_BitmapUtils", "loadLocalImageAsGif");
            Glide.with(cHK_(imageView)).asGif().load(Integer.valueOf(i)).into(imageView);
        }
    }

    public static void d(Context context, String str, RequestOptions requestOptions, RequestListener<Drawable> requestListener) {
        Glide.with(b(context)).load(str).apply((BaseRequestOptions<?>) requestOptions).listener(requestListener).preload();
    }

    public static void a(Context context, String str) {
        Glide.with(b(context)).load(str).diskCacheStrategy(DiskCacheStrategy.DATA).preload();
    }

    public static void cHt_(Context context, View view) {
        Glide.with(b(context)).clear(view);
    }

    @Deprecated
    public static void cJy_(String str, ImageView imageView) {
        cJz_(str, imageView);
    }

    private static void cJz_(String str, ImageView imageView) {
        if (TextUtils.isEmpty(str) || imageView == null) {
            return;
        }
        if (CommonUtil.bu()) {
            Glide.with(cHK_(imageView)).load("file:///android_asset/suggestion/img/" + str).into(imageView);
            return;
        }
        Glide.with(cHK_(imageView)).load("file:///android_asset/img/" + str).into(imageView);
    }

    @Deprecated
    public static void cJA_(String str, int i, int i2, ImageView imageView) {
        cJC_(str, i, i2, imageView);
    }

    private static void cJC_(String str, int i, int i2, ImageView imageView) {
        if (TextUtils.isEmpty(str) || imageView == null) {
            return;
        }
        RequestOptions centerCrop = new RequestOptions().override(i, i2).centerCrop();
        if (str.startsWith("http")) {
            Glide.with(cHK_(imageView)).load(str).apply((BaseRequestOptions<?>) centerCrop).into(imageView);
            return;
        }
        if (CommonUtil.bu()) {
            Glide.with(cHK_(imageView)).load("file:///android_asset/suggestion/img/" + str).apply((BaseRequestOptions<?>) centerCrop).into(imageView);
            return;
        }
        Glide.with(cHK_(imageView)).load("file:///android_asset/img/" + str).apply((BaseRequestOptions<?>) centerCrop).into(imageView);
    }

    @Deprecated
    public static void cJB_(String str, ImageView imageView) {
        cJD_(str, imageView);
    }

    private static void cJD_(String str, ImageView imageView) {
        if (TextUtils.isEmpty(str) || imageView == null) {
            return;
        }
        if (str.startsWith("http")) {
            Glide.with(cHK_(imageView)).load(str).into(imageView);
            return;
        }
        if (CommonUtil.bu()) {
            Glide.with(cHK_(imageView)).load("file:///android_asset/suggestion/img/" + str).into(imageView);
            return;
        }
        Glide.with(cHK_(imageView)).load("file:///android_asset/img/" + str).into(imageView);
    }

    public static void e(String str, HealthImageView healthImageView, RequestOptions requestOptions) {
        cJE_(str, healthImageView, requestOptions);
    }

    private static void cJE_(String str, ImageView imageView, RequestOptions requestOptions) {
        if (TextUtils.isEmpty(str) || imageView == null) {
            return;
        }
        if (str.startsWith("http")) {
            Glide.with(cHK_(imageView)).load(str).apply((BaseRequestOptions<?>) requestOptions).into(imageView);
            return;
        }
        if (CommonUtil.bu()) {
            Glide.with(cHK_(imageView)).load("file:///android_asset/suggestion/img/" + str).apply((BaseRequestOptions<?>) requestOptions).into(imageView);
            return;
        }
        Glide.with(cHK_(imageView)).load("file:///android_asset/img/" + str).apply((BaseRequestOptions<?>) requestOptions).into(imageView);
    }

    @Deprecated
    public static void cJF_(Uri uri, ImageView imageView) {
        cJG_(uri, imageView);
    }

    private static void cJG_(Uri uri, ImageView imageView) {
        if (imageView == null) {
            LogUtil.h("CommonUI_BitmapUtils", "setRoundImageUrl imageView is null");
            return;
        }
        if (CommonUtil.bu()) {
            Glide.with(cHK_(imageView)).load("file:///android_asset/suggestion/img/" + uri).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform()).into(imageView);
            return;
        }
        Glide.with(cHK_(imageView)).load(uri).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform()).into(imageView);
    }

    public static Bitmap cHF_(Drawable drawable) {
        return cHG_(drawable, 0);
    }

    public static Bitmap cHG_(Drawable drawable, int i) {
        Bitmap bitmap = null;
        if (drawable == null) {
            return null;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            intrinsicHeight = i;
        } else {
            i = intrinsicWidth;
        }
        try {
            bitmap = Bitmap.createBitmap(i, intrinsicHeight, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            drawable.setBounds(0, 0, i, intrinsicHeight);
            drawable.draw(new Canvas(bitmap));
            return bitmap;
        } catch (IllegalArgumentException | OutOfMemoryError e2) {
            LogUtil.b("CommonUI_BitmapUtils", "drawableToBitmap() ", ExceptionUtils.d(e2));
            return bitmap;
        }
    }

    public static Drawable cHq_(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable(bitmap);
    }

    private static int[] c(int[] iArr) {
        int i;
        if (iArr == null || iArr.length <= 0) {
            i = -7829368;
        } else {
            if (iArr.length != 1) {
                return iArr;
            }
            i = iArr[0];
        }
        return new int[]{i, i};
    }

    public static Drawable cJH_(Drawable drawable, int i) {
        Drawable cHW_ = cHW_(drawable);
        DrawableCompat.setTint(cHW_, i);
        return cHW_;
    }

    private static Drawable cHW_(Drawable drawable) {
        if (drawable == null) {
            return drawable;
        }
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState != null) {
            drawable = constantState.newDrawable();
        }
        return DrawableCompat.wrap(drawable).mutate();
    }

    public static Bitmap cIj_(Bitmap bitmap, int[] iArr, boolean z) {
        if (bitmap == null || bitmap.isRecycled()) {
            LogUtil.h("CommonUI_BitmapUtils", "linearGradientBitmap source is null or is recycled");
            return bitmap;
        }
        try {
            Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            if (copy != null && !copy.isRecycled()) {
                int width = copy.getWidth();
                int height = copy.getHeight();
                if (width > 0 && height > 0) {
                    Paint paint = new Paint();
                    if (z) {
                        paint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, height, c(iArr), (float[]) null, Shader.TileMode.CLAMP));
                    } else {
                        paint.setShader(new LinearGradient(0.0f, 0.0f, width, 0.0f, c(iArr), (float[]) null, Shader.TileMode.CLAMP));
                    }
                    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                    new Canvas(copy).drawRect(0.0f, 0.0f, width, height, paint);
                    return copy;
                }
                LogUtil.h("CommonUI_BitmapUtils", "linearGradientBitmap width or height less than or equal to zero");
                return copy;
            }
            LogUtil.h("CommonUI_BitmapUtils", "linearGradientBitmap bitmap is null or is recycled");
            return bitmap;
        } catch (OutOfMemoryError unused) {
            LogUtil.b("CommonUI_BitmapUtils", "linearGradientBitmap outOfMemoryError");
            return bitmap;
        }
    }

    public static Bitmap cJI_(Bitmap bitmap, float f) {
        if (bitmap == null || bitmap.isRecycled()) {
            LogUtil.h("CommonUI_BitmapUtils", "zoomBitmap bitmap is null or is recycled");
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(f, f);
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            if (createBitmap != null) {
                return createBitmap;
            }
            LogUtil.h("CommonUI_BitmapUtils", "zoomBitmap zoomBitmap is null");
            return bitmap;
        } catch (IllegalArgumentException | OutOfMemoryError e2) {
            LogUtil.b("CommonUI_BitmapUtils", "zoomBitmap exception happened ", ExceptionUtils.d(e2));
            return bitmap;
        }
    }

    public static Bitmap cJJ_(Bitmap bitmap, int i, int i2) {
        if (bitmap == null || bitmap.isRecycled()) {
            LogUtil.h("CommonUI_BitmapUtils", "zoomBitmap bitmap is null or is recycled");
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= 0 || height <= 0) {
            LogUtil.h("CommonUI_BitmapUtils", "zoomBitmap bitmapWidth or bitmapHeight less than or equal to zero");
            return bitmap;
        }
        if (i <= 0) {
            i = width;
        }
        if (i2 <= 0) {
            i2 = height;
        }
        return cJI_(bitmap, Math.max(i / width, i2 / height));
    }

    @Deprecated
    public static Bitmap cJK_(Bitmap bitmap, ImageView imageView) {
        return cJM_(bitmap, imageView);
    }

    public static Bitmap cJL_(Bitmap bitmap, HealthImageView healthImageView) {
        return cJM_(bitmap, healthImageView);
    }

    private static Bitmap cJM_(Bitmap bitmap, ImageView imageView) {
        if (imageView == null) {
            LogUtil.h("CommonUI_BitmapUtils", "zoomBitmap imageView is null");
            return bitmap;
        }
        int[] iArr = {0, 0};
        return !cIf_(imageView, iArr) ? bitmap : cJJ_(bitmap, iArr[0], iArr[1]);
    }

    @Deprecated
    public static boolean cIf_(ImageView imageView, int[] iArr) {
        return cIg_(imageView, iArr);
    }

    private static boolean cIg_(ImageView imageView, int[] iArr) {
        if (imageView == null || iArr == null) {
            LogUtil.b("CommonUI_BitmapUtils", "imageview or array is invalid");
            return false;
        }
        int width = imageView.getWidth();
        int height = imageView.getHeight();
        if (!b(width, height)) {
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            if (layoutParams == null) {
                return false;
            }
            width = layoutParams.width;
            height = layoutParams.height;
            if (!b(width, height)) {
                LogUtil.b("CommonUI_BitmapUtils", "get imageView width or height fail");
                return false;
            }
        }
        iArr[0] = width;
        iArr[1] = height;
        return true;
    }

    private static boolean cHU_(Bitmap bitmap, int[] iArr) {
        if (bitmap == null || bitmap.isRecycled() || iArr == null) {
            LogUtil.b("CommonUI_BitmapUtils", "bitmap or array is invalid");
            return false;
        }
        if (!b(bitmap.getWidth(), bitmap.getHeight())) {
            LogUtil.b("CommonUI_BitmapUtils", "get bitmap width or height fail");
            return false;
        }
        iArr[0] = bitmap.getWidth();
        iArr[1] = bitmap.getHeight();
        return true;
    }

    public static Bitmap cHv_(Bitmap bitmap, int i, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        if (bitmap == null || bitmap.isRecycled()) {
            LogUtil.h("CommonUI_BitmapUtils", "cropBitmap bitmap is null or is recycled");
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        switch (i3) {
            case 1:
                i4 = 0;
                i5 = 0;
                break;
            case 2:
                i6 = height - i2;
                i5 = i6;
                i4 = 0;
                break;
            case 3:
                i4 = width - i;
                i5 = 0;
                break;
            case 4:
                i4 = width - i;
                i5 = height - i2;
                break;
            case 5:
                i6 = (int) ((height - i2) / 2.0f);
                i5 = i6;
                i4 = 0;
                break;
            case 6:
                i4 = width - i;
                i5 = (int) ((height - i2) / 2.0f);
                break;
            default:
                i4 = (int) ((width - i) / 2.0f);
                i5 = (int) ((height - i2) / 2.0f);
                break;
        }
        if (i2 > height) {
            i2 = height;
            i5 = 0;
        }
        if (i > width) {
            i = width;
            i4 = 0;
        }
        if (i4 < 0 || i5 < 0) {
            LogUtil.h("CommonUI_BitmapUtils", "originHorizontal or originVertical < 0");
            return bitmap;
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, i4, i5, i, i2);
            if (createBitmap != null) {
                return createBitmap;
            }
            LogUtil.h("CommonUI_BitmapUtils", "cropBitmap cropBitmap is null");
            return bitmap;
        } catch (IllegalArgumentException | OutOfMemoryError e2) {
            LogUtil.b("CommonUI_BitmapUtils", "cropBitmap throwable=", ExceptionUtils.d(e2));
            return bitmap;
        }
    }

    @Deprecated
    public static Bitmap cHw_(Bitmap bitmap, ImageView imageView, int i) {
        return cHx_(bitmap, imageView, i);
    }

    private static Bitmap cHx_(Bitmap bitmap, ImageView imageView, int i) {
        if (imageView == null) {
            LogUtil.h("CommonUI_BitmapUtils", "cropBitmap imageView is null");
            return bitmap;
        }
        int[] iArr = {0, 0};
        return !cIf_(imageView, iArr) ? bitmap : cHv_(bitmap, iArr[0], iArr[1], i);
    }

    public static Bitmap cHy_(Bitmap bitmap, int i, int i2, int i3, int i4) {
        if (bitmap == null) {
            return null;
        }
        return Bitmap.createBitmap(bitmap.copy(bitmap.getConfig(), true), i, i2, i3, i4);
    }

    public static Bitmap cJq_(Bitmap bitmap, int i, int i2, int i3) {
        if (bitmap == null || bitmap.isRecycled() || !b(i, i2)) {
            Object[] objArr = new Object[6];
            objArr[0] = "roundRectangleBitmap fail, isBitmapNull: ";
            objArr[1] = Boolean.valueOf(bitmap == null);
            objArr[2] = ", isRecycled: ";
            objArr[3] = bitmap == null ? Constants.NULL : Boolean.valueOf(bitmap.isRecycled());
            objArr[4] = ", isWidthHeightValid: ";
            objArr[5] = Boolean.valueOf(b(i, i2));
            LogUtil.h("CommonUI_BitmapUtils", objArr);
            return bitmap;
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            if (createBitmap == null) {
                LogUtil.h("CommonUI_BitmapUtils", "roundRectangleBitmap roundRectangleBitmap is null");
                return bitmap;
            }
            Paint paint = new Paint();
            Canvas canvas = new Canvas(createBitmap);
            paint.setAntiAlias(true);
            paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
            RectF rectF = new RectF(new Rect(0, 0, i, i2));
            float f = i3;
            canvas.drawRoundRect(rectF, f, f, paint);
            paint.setFilterBitmap(true);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
            return createBitmap;
        } catch (OutOfMemoryError unused) {
            LogUtil.b("CommonUI_BitmapUtils", "roundRectangleBitmap outOfMemoryError");
            return bitmap;
        }
    }

    @Deprecated
    public static void cIV_(ImageView imageView, Bitmap bitmap, int i) {
        cIW_(imageView, bitmap, i);
    }

    private static void cIW_(ImageView imageView, Bitmap bitmap, int i) {
        if (imageView == null || bitmap == null) {
            LogUtil.h("CommonUI_BitmapUtils", "imageView or bitmap is null");
            return;
        }
        int[] iArr = {0, 0};
        if (bitmap.isRecycled()) {
            LogUtil.h("CommonUI_BitmapUtils", "roundRectangleBitmap bitmap is recycled");
            return;
        }
        if (!cIf_(imageView, iArr) && !cHU_(bitmap, iArr)) {
            imageView.setImageBitmap(bitmap);
            LogUtil.h("CommonUI_BitmapUtils", "get imageview height/width fail");
            return;
        }
        try {
            if (Bitmap.createBitmap(iArr[0], iArr[1], Bitmap.Config.ARGB_8888) == null) {
                LogUtil.h("CommonUI_BitmapUtils", "roundRectangleBitmap roundRectangleBitmap is null");
                return;
            }
            RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(BaseApplication.e().getResources(), bitmap);
            create.setCornerRadius(i);
            create.setAntiAlias(true);
            nsy.cMm_(imageView, create);
        } catch (OutOfMemoryError unused) {
            LogUtil.b("CommonUI_BitmapUtils", "roundRectangleBitmap outOfMemoryError");
        }
    }

    @Deprecated
    public static void cIQ_(ImageView imageView, Bitmap bitmap, int i, int i2) {
        cJa_(imageView, bitmap, i, i2);
    }

    private static void cJa_(ImageView imageView, Bitmap bitmap, int i, int i2) {
        if (imageView == null) {
            LogUtil.h("CommonUI_BitmapUtils", "roundRectangleBitmap imageView is null");
        } else {
            cIV_(imageView, cHw_(cJK_(bitmap, imageView), imageView, i2), i);
        }
    }

    @Deprecated
    public static void cIR_(ImageView imageView, Drawable drawable, int i, int i2) {
        cJb_(imageView, drawable, i, i2);
    }

    private static void cJb_(ImageView imageView, Drawable drawable, int i, int i2) {
        if (drawable instanceof BitmapDrawable) {
            cIQ_(imageView, ((BitmapDrawable) drawable).getBitmap(), i, i2);
        } else {
            LogUtil.h("CommonUI_BitmapUtils", "loadRoundRectangle Drawable is not BitmapDrawable");
        }
    }

    @Deprecated
    public static void cIT_(ImageView imageView, String str, int i, int i2, AsyncLoadDrawableCallback asyncLoadDrawableCallback) {
        cJd_(imageView, str, i, i2, asyncLoadDrawableCallback);
    }

    private static void cJd_(final ImageView imageView, final String str, final int i, final int i2, final AsyncLoadDrawableCallback asyncLoadDrawableCallback) {
        if (imageView == null || str == null) {
            LogUtil.h("CommonUI_BitmapUtils", "loadRoundRectangle imageView or url is null");
        } else {
            Glide.with(cHK_(imageView)).load(str).into((RequestBuilder<Drawable>) new CustomTarget<Drawable>() { // from class: nrf.3
                @Override // com.bumptech.glide.request.target.Target
                public void onLoadCleared(Drawable drawable) {
                }

                @Override // com.bumptech.glide.request.target.Target
                /* renamed from: cJN_, reason: merged with bridge method [inline-methods] */
                public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                    ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                    if (layoutParams.width <= 0 || layoutParams.height <= 0) {
                        if (imageView.getWidth() > 0 && imageView.getHeight() > 0) {
                            nrf.cIR_(imageView, drawable, i, i2);
                            return;
                        }
                        nrf.cIU_(str, imageView, i);
                    } else {
                        nrf.cIR_(imageView, drawable, i, i2);
                    }
                    AsyncLoadDrawableCallback asyncLoadDrawableCallback2 = asyncLoadDrawableCallback;
                    if (asyncLoadDrawableCallback2 != null) {
                        asyncLoadDrawableCallback2.getDrawable(drawable);
                    }
                }

                @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
                public void onLoadFailed(Drawable drawable) {
                    LogUtil.h("CommonUI_BitmapUtils", "loadRoundRectangle onLoadFailed");
                    super.onLoadFailed(drawable);
                    AsyncLoadDrawableCallback asyncLoadDrawableCallback2 = asyncLoadDrawableCallback;
                    if (asyncLoadDrawableCallback2 != null) {
                        asyncLoadDrawableCallback2.getDrawable(null);
                    }
                }
            });
        }
    }

    @Deprecated
    public static void cJf_(ImageView imageView, String str, int i, String str2, TagResourcePolicy tagResourcePolicy) {
        cJg_(imageView, str, i, str2, tagResourcePolicy);
    }

    private static void cJg_(final ImageView imageView, final String str, final int i, final String str2, final TagResourcePolicy tagResourcePolicy) {
        if (imageView == null || str == null) {
            LogUtil.h("CommonUI_BitmapUtils", "loadRoundRectangle imageView or url is null");
        } else {
            Glide.with(cHK_(imageView)).load(str).into((RequestBuilder<Drawable>) new CustomTarget<Drawable>() { // from class: nrf.1
                @Override // com.bumptech.glide.request.target.Target
                public void onLoadCleared(Drawable drawable) {
                }

                @Override // com.bumptech.glide.request.target.Target
                /* renamed from: cJP_, reason: merged with bridge method [inline-methods] */
                public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                    TagResourcePolicy tagResourcePolicy2 = TagResourcePolicy.this;
                    if (tagResourcePolicy2 == null || tagResourcePolicy2.filterTagResource(str2)) {
                        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                        if (layoutParams.width <= 0 || layoutParams.height <= 0) {
                            if (imageView.getWidth() > 0 && imageView.getHeight() > 0) {
                                nrf.cIR_(imageView, drawable, i, 0);
                                return;
                            } else {
                                nrf.cIU_(str, imageView, i);
                                return;
                            }
                        }
                        nrf.cIR_(imageView, drawable, i, 0);
                    }
                }

                @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
                public void onLoadFailed(Drawable drawable) {
                    LogUtil.b("CommonUI_BitmapUtils", "loadRoundRectangleWithTag onLoadFailed");
                }
            });
        }
    }

    @Deprecated
    public static void cIS_(ImageView imageView, String str, int i, int i2, int i3) {
        cJc_(imageView, str, i, i2, i3);
    }

    public static void c(HealthImageView healthImageView, String str, int i, int i2, int i3) {
        cJc_(healthImageView, str, i, i2, i3);
    }

    private static void cJc_(final ImageView imageView, final String str, final int i, final int i2, final int i3) {
        if (imageView == null || str == null) {
            LogUtil.h("CommonUI_BitmapUtils", "loadRoundRectangle imageView or url is null");
        } else {
            Glide.with(cHK_(imageView)).load(str).addListener(new RequestListener<Drawable>() { // from class: nrf.4
                @Override // com.bumptech.glide.request.RequestListener
                /* renamed from: cJR_, reason: merged with bridge method [inline-methods] */
                public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                    return false;
                }

                @Override // com.bumptech.glide.request.RequestListener
                public boolean onLoadFailed(GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                    LogUtil.h("CommonUI_BitmapUtils", "loadRoundRectangleInner failed url=", str);
                    nrf.a(glideException);
                    return false;
                }
            }).into((RequestBuilder<Drawable>) new CustomTarget<Drawable>() { // from class: nrf.2
                @Override // com.bumptech.glide.request.target.Target
                public void onLoadCleared(Drawable drawable) {
                }

                @Override // com.bumptech.glide.request.target.Target
                /* renamed from: cJQ_, reason: merged with bridge method [inline-methods] */
                public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                    ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                    if (layoutParams.width <= 0 || layoutParams.height <= 0) {
                        if (imageView.getWidth() > 0 && imageView.getHeight() > 0) {
                            nrf.cIR_(imageView, drawable, i, i2);
                            return;
                        } else {
                            nrf.cIU_(str, imageView, i);
                            return;
                        }
                    }
                    nrf.cIR_(imageView, drawable, i, i2);
                }

                @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
                public void onLoadFailed(Drawable drawable) {
                    LogUtil.h("CommonUI_BitmapUtils", "loadRoundRectangle onLoadFailed");
                    int i4 = i3;
                    if (i4 == 0) {
                        super.onLoadFailed(drawable);
                    } else {
                        nrf.cIM_(i4, imageView, i);
                    }
                }
            });
        }
    }

    @Deprecated
    public static void cIP_(ImageView imageView, int i, int i2, int i3, int i4) {
        cIZ_(imageView, i, i2, i3, i4);
    }

    public static void d(HealthImageView healthImageView, int i, int i2, int i3, int i4) {
        cIZ_(healthImageView, i, i2, i3, i4);
    }

    private static void cIZ_(final ImageView imageView, final int i, final int i2, final int i3, final int i4) {
        if (imageView == null) {
            LogUtil.h("CommonUI_BitmapUtils", "loadRoundRectangle imageView is null");
        } else {
            Glide.with(cHK_(imageView)).load(Integer.valueOf(i)).into((RequestBuilder<Drawable>) new CustomTarget<Drawable>() { // from class: nrf.10
                @Override // com.bumptech.glide.request.target.Target
                public void onLoadCleared(Drawable drawable) {
                }

                @Override // com.bumptech.glide.request.target.Target
                /* renamed from: cJS_, reason: merged with bridge method [inline-methods] */
                public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                    ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                    if (layoutParams.width <= 0 || layoutParams.height <= 0) {
                        if (imageView.getWidth() > 0 && imageView.getHeight() > 0) {
                            nrf.cIR_(imageView, drawable, i2, i3);
                            return;
                        } else {
                            nrf.cIM_(i, imageView, i2);
                            return;
                        }
                    }
                    nrf.cIR_(imageView, drawable, i2, i3);
                }

                @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
                public void onLoadFailed(Drawable drawable) {
                    LogUtil.h("CommonUI_BitmapUtils", "loadRoundRectangle onLoadFailed");
                    int i5 = i4;
                    if (i5 == 0) {
                        super.onLoadFailed(drawable);
                    } else {
                        nrf.cIM_(i5, imageView, i2);
                    }
                }
            });
        }
    }

    @Deprecated
    public static void cIk_(String str, ImageView imageView, int i, AsyncLoadDrawableCallback asyncLoadDrawableCallback) {
        cIl_(str, imageView, i, asyncLoadDrawableCallback, null);
    }

    @Deprecated
    public static void cIl_(String str, ImageView imageView, int i, AsyncLoadDrawableCallback asyncLoadDrawableCallback, Drawable drawable) {
        cIm_(str, imageView, i, asyncLoadDrawableCallback, drawable);
    }

    private static void cIm_(final String str, ImageView imageView, int i, final AsyncLoadDrawableCallback asyncLoadDrawableCallback, Drawable drawable) {
        if (TextUtils.isEmpty(str) || imageView == null) {
            LogUtil.h("CommonUI_BitmapUtils", "setRoundAngleImage, url or imageView is null");
            return;
        }
        RequestOptions diskCacheStrategy = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
        float f = d;
        RequestOptions transform = diskCacheStrategy.transform(new nrp(f, f, f, f));
        e(transform, i);
        if (drawable != null) {
            transform.placeholder(drawable).dontAnimate();
        }
        Glide.with(cHK_(imageView)).load(str).apply((BaseRequestOptions<?>) transform).addListener(new RequestListener<Drawable>() { // from class: nrf.6
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                AsyncLoadDrawableCallback asyncLoadDrawableCallback2 = AsyncLoadDrawableCallback.this;
                if (asyncLoadDrawableCallback2 != null) {
                    asyncLoadDrawableCallback2.getDrawable(null);
                }
                LogUtil.h("CommonUI_BitmapUtils", "loadEmuiCornerImageInner failed url=", str);
                nrf.a(glideException);
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            /* renamed from: cJT_, reason: merged with bridge method [inline-methods] */
            public boolean onResourceReady(Drawable drawable2, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                AsyncLoadDrawableCallback asyncLoadDrawableCallback2 = AsyncLoadDrawableCallback.this;
                if (asyncLoadDrawableCallback2 == null) {
                    return false;
                }
                asyncLoadDrawableCallback2.getDrawable(drawable2);
                return false;
            }
        }).into(imageView);
    }

    @Deprecated
    public static void cIM_(int i, ImageView imageView, int i2) {
        cIN_(i, imageView, i2, true);
    }

    public static void a(int i, HealthImageView healthImageView, int i2) {
        d(i, healthImageView, i2, true);
    }

    @Deprecated
    public static void cIN_(int i, ImageView imageView, int i2, boolean z) {
        cIX_(i, imageView, i2, z);
    }

    public static void d(int i, HealthImageView healthImageView, int i2, boolean z) {
        cIX_(i, healthImageView, i2, z);
    }

    private static void cIX_(int i, ImageView imageView, int i2, boolean z) {
        if (imageView == null) {
            LogUtil.h("CommonUI_BitmapUtils", "loadRoundRectangle imageView is null");
        } else {
            Glide.with(cHK_(imageView)).load(Integer.valueOf(i)).apply((BaseRequestOptions<?>) new RequestOptions().skipMemoryCache(z).diskCacheStrategy(DiskCacheStrategy.ALL).transform(new nrp(imageView.getContext().getApplicationContext(), i2))).into(imageView);
        }
    }

    @Deprecated
    public static void cIU_(String str, ImageView imageView, int i) {
        cJe_(str, imageView, i);
    }

    public static void b(String str, HealthImageView healthImageView, int i) {
        cJe_(str, healthImageView, i);
    }

    private static void cJe_(final String str, ImageView imageView, int i) {
        if (imageView == null) {
            LogUtil.h("CommonUI_BitmapUtils", "loadRoundRectangle imageView is null");
        } else {
            Glide.with(cHK_(imageView)).load(str).addListener(new RequestListener<Drawable>() { // from class: nrf.8
                @Override // com.bumptech.glide.request.RequestListener
                /* renamed from: cJU_, reason: merged with bridge method [inline-methods] */
                public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                    return false;
                }

                @Override // com.bumptech.glide.request.RequestListener
                public boolean onLoadFailed(GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                    LogUtil.h("CommonUI_BitmapUtils", "loadRoundRectangleInner load failed url=", str);
                    nrf.a(glideException);
                    return false;
                }
            }).apply((BaseRequestOptions<?>) new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).transform(new nrp(imageView.getContext().getApplicationContext(), i))).into(imageView);
        }
    }

    @Deprecated
    public static void cIK_(String str, ImageView imageView, float f, float f2, float f3, float f4) {
        cIL_(str, imageView, f, f2, f3, f4);
    }

    public static void a(String str, HealthImageView healthImageView, float f, float f2, float f3, float f4) {
        cIL_(str, healthImageView, f, f2, f3, f4);
    }

    private static void cIL_(String str, final ImageView imageView, final float f, final float f2, final float f3, final float f4) {
        if (imageView == null || TextUtils.isEmpty(str)) {
            LogUtil.h("CommonUI_BitmapUtils", "loadPlanRectangle imageView is null or url is empty", str);
        } else {
            Glide.with(cHK_(imageView)).load(str).apply((BaseRequestOptions<?>) new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.RESOURCE)).into((RequestBuilder<Drawable>) new CustomTarget<Drawable>() { // from class: nrf.9
                @Override // com.bumptech.glide.request.target.Target
                public void onLoadCleared(Drawable drawable) {
                }

                @Override // com.bumptech.glide.request.target.Target
                /* renamed from: cJV_, reason: merged with bridge method [inline-methods] */
                public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                    int c2 = nsn.c(imageView.getContext().getApplicationContext(), 18.0f);
                    int intrinsicWidth = (drawable.getIntrinsicWidth() * c2) / drawable.getIntrinsicHeight();
                    Glide.with(nrf.cHK_(imageView)).load(drawable).override(intrinsicWidth, c2).apply((BaseRequestOptions<?>) new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).transform(new nrp(f, f2, f3, f4))).into(imageView);
                }

                @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
                public void onLoadFailed(Drawable drawable) {
                    LogUtil.h("CommonUI_BitmapUtils", "loadRoundRectangle onLoadFailed");
                    super.onLoadFailed(drawable);
                }
            });
        }
    }

    @Deprecated
    public static void cHI_(String str, ImageView imageView, int i) {
        cHJ_(str, imageView, i);
    }

    public static void c(String str, HealthImageView healthImageView, int i) {
        cHJ_(str, healthImageView, i);
    }

    private static void cHJ_(final String str, ImageView imageView, int i) {
        if (imageView == null) {
            LogUtil.h("CommonUI_BitmapUtils", "loadRoundRectangle imageView is null");
        } else {
            Glide.with(cHK_(imageView)).load(str).addListener(new RequestListener<Drawable>() { // from class: nrf.7
                @Override // com.bumptech.glide.request.RequestListener
                /* renamed from: cJW_, reason: merged with bridge method [inline-methods] */
                public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                    return false;
                }

                @Override // com.bumptech.glide.request.RequestListener
                public boolean onLoadFailed(GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                    LogUtil.h("CommonUI_BitmapUtils", "fitnessLoadRoundRectangleInner url=", str);
                    nrf.a(glideException);
                    return false;
                }
            }).apply((BaseRequestOptions<?>) new RequestOptions().dontAnimate().skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL).transform(new nrp(imageView.getContext().getApplicationContext(), i))).into(imageView);
        }
    }

    @Deprecated
    public static void cIO_(Drawable drawable, ImageView imageView, int i) {
        cIY_(drawable, imageView, i);
    }

    private static void cIY_(Drawable drawable, ImageView imageView, int i) {
        if (imageView == null) {
            LogUtil.h("CommonUI_BitmapUtils", "setRoundAngleImage, imageView is null");
        } else {
            Glide.with(cHK_(imageView)).load(drawable).apply((BaseRequestOptions<?>) new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).transform(new nrp(imageView.getContext().getApplicationContext(), i))).into(imageView);
        }
    }

    public static Drawable cIb_(Context context, String str) {
        try {
            return Glide.with(b(context)).load(str).submit().get();
        } catch (InterruptedException | ExecutionException e2) {
            LogUtil.b("CommonUI_BitmapUtils", "getDrawableGlide failed, url=", str, " exception: ", ExceptionUtils.d(e2));
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Drawable cIc_(Context context, String str) {
        try {
            return (Drawable) Glide.with(b(context)).load(str).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).submit().get();
        } catch (InterruptedException | ExecutionException e2) {
            LogUtil.b("CommonUI_BitmapUtils", "getDrawableGlide failed, url=", str, " exception: ", ExceptionUtils.d(e2));
            return null;
        }
    }

    public static Drawable cId_(Context context, String str, int i) {
        try {
            return Glide.with(c()).load(str).apply((BaseRequestOptions<?>) new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE).transform(new nrp(context, i))).submit().get();
        } catch (InterruptedException | ExecutionException e2) {
            LogUtil.b("CommonUI_BitmapUtils", "getDrawableGlide failed, url=", str, "  exception: ", ExceptionUtils.d(e2));
            return null;
        }
    }

    public static Bitmap cHT_(Context context, String str) {
        try {
            return Glide.with(b(context)).asBitmap().load(str).submit().get(3000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("CommonUI_BitmapUtils", "load bitmap from url is interrupted");
            return null;
        } catch (ExecutionException unused2) {
            LogUtil.b("CommonUI_BitmapUtils", "load bitmap from url execute fail");
            return null;
        } catch (TimeoutException unused3) {
            LogUtil.b("CommonUI_BitmapUtils", "load bitmap from url timeout");
            return null;
        }
    }

    public static Bitmap cIe_(Context context, String str) {
        if (context == null || str == null) {
            LogUtil.h("CommonUI_BitmapUtils", "getImage context or localPath null");
            return null;
        }
        String c2 = CommonUtil.c(str);
        if (c2 == null) {
            LogUtil.h("CommonUI_BitmapUtils", "localPath is null");
            return null;
        }
        if (!new File(c2).exists()) {
            return null;
        }
        Bitmap cIi_ = cIi_(c2, 200);
        return cIi_ == null ? cIi_ : cHX_(cIi_);
    }

    public static Bitmap cIi_(String str, int i) {
        Bitmap bitmap = null;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("CommonUI_BitmapUtils", "filePathString is null");
            return null;
        }
        if (!new File(str).exists()) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        options.inJustDecodeBounds = false;
        if (options.outWidth >= i || options.outHeight >= i) {
            options.inSampleSize = 2;
        } else {
            options.inSampleSize = 1;
        }
        if (decodeFile == null || decodeFile.isRecycled()) {
            bitmap = decodeFile;
        } else {
            decodeFile.recycle();
        }
        try {
            return BitmapFactory.decodeFile(str, options);
        } catch (OutOfMemoryError unused) {
            LogUtil.b("CommonUI_BitmapUtils", "getThumbnailImage OutOfMemoryError");
            return bitmap;
        }
    }

    public static Bitmap cHY_(Bitmap bitmap, int i) {
        Bitmap createBitmap;
        if (bitmap == null || bitmap.isRecycled()) {
            LogUtil.h("CommonUI_BitmapUtils", "getCircleBitmap bitmap is null or is recycled");
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int min = Math.min(height, width);
        if (min <= 0) {
            LogUtil.h("CommonUI_BitmapUtils", "getCircleBitmap width or height is zero");
            return bitmap;
        }
        try {
            if (width > height) {
                createBitmap = Bitmap.createBitmap(bitmap, (int) ((width - height) / 2.0f), 0, height, height, (Matrix) null, false);
            } else {
                createBitmap = Bitmap.createBitmap(bitmap, 0, (int) ((height - width) / 2.0f), width, width, (Matrix) null, false);
            }
            float f = i;
            float f2 = (2.0f * f) / min;
            Matrix matrix = new Matrix();
            matrix.setScale(f2, f2);
            BitmapShader bitmapShader = new BitmapShader(createBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            bitmapShader.setLocalMatrix(matrix);
            Bitmap createBitmap2 = Bitmap.createBitmap(min, min, bitmap.getConfig());
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setShader(bitmapShader);
            new Canvas(createBitmap2).drawCircle(f, f, f, paint);
            return createBitmap2;
        } catch (OutOfMemoryError unused) {
            LogUtil.b("CommonUI_BitmapUtils", "getCircleBitmap outOfMemoryError");
            return bitmap;
        }
    }

    public static Bitmap cHX_(Bitmap bitmap) {
        float f;
        float f2;
        float f3;
        float f4;
        if (bitmap == null) {
            LogUtil.h("CommonUI_BitmapUtils", "bitmap is null.");
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= height) {
            f2 = width;
            f4 = f2 / 2.0f;
            f3 = 0.0f;
            f = f2;
        } else {
            f = height;
            float f5 = (width - height) / 2.0f;
            f2 = width - f5;
            width = height;
            f3 = f5;
            f4 = f / 2.0f;
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-1);
        canvas.drawCircle(f4, f4, f4, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        int i = (int) f;
        canvas.drawBitmap(bitmap, new Rect((int) f3, 0, (int) f2, i), new Rect(0, 0, i, i), paint);
        return createBitmap;
    }

    public static Bitmap cJv_(Bitmap bitmap, float f, float f2) {
        if (bitmap == null) {
            return null;
        }
        Bitmap copy = bitmap.copy(bitmap.getConfig(), true);
        Matrix matrix = new Matrix();
        matrix.postScale(f, f2);
        return Bitmap.createBitmap(copy, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap cJu_(Bitmap bitmap) {
        if (bitmap == null) {
            LogUtil.h("CommonUI_BitmapUtils", "scaleBitmap() if (sourceBitmap == null)");
            return bitmap;
        }
        LogUtil.a("CommonUI_BitmapUtils", "scaleBitmap() btmwidth=", Integer.valueOf(bitmap.getWidth()), ", btmheight=", Integer.valueOf(bitmap.getHeight()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(byteArrayInputStream, null, options);
        options.inJustDecodeBounds = false;
        float f = options.outWidth;
        float f2 = options.outHeight;
        float f3 = 1.0f;
        float f4 = (f <= f2 || f <= 720.0f) ? (f >= f2 || f2 <= 1280.0f) ? 1.0f : f2 / 1280.0f : f / 720.0f;
        if (f4 > 0.0f && f4 < 3.0f) {
            f3 = f4;
        }
        options.inSampleSize = (int) f3;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), null, options);
    }

    public static Bitmap cJw_(Bitmap bitmap, int i) {
        if (bitmap == null) {
            LogUtil.h("CommonUI_BitmapUtils", "scaleBitmapBySize() if (btm == null)");
            return bitmap;
        }
        LogUtil.a("CommonUI_BitmapUtils", "scaleBitmapBySize() btmwidth=", Integer.valueOf(bitmap.getWidth()), ", btmheight=", Integer.valueOf(bitmap.getHeight()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(byteArrayInputStream, null, options);
        options.inJustDecodeBounds = false;
        options.inSampleSize = i;
        LogUtil.a("CommonUI_BitmapUtils", "scaleBitmapBySize compressionRatio : ", Integer.valueOf(i));
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), null, options);
    }

    public static Bitmap cHm_(Context context, Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap == null || context == null) {
            LogUtil.h("CommonUI_BitmapUtils", "addWaterMarkPic() if (srcBitmap == null || context == null)");
            return bitmap;
        }
        LogUtil.a("CommonUI_BitmapUtils", "addWaterMarkPic() srcBitmap width=", Integer.valueOf(bitmap.getWidth()), "bmpHeight=", Integer.valueOf(bitmap.getHeight()));
        Bitmap.Config config = bitmap.getConfig();
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        Bitmap copy = bitmap.copy(config, true);
        if (bitmap2 == null) {
            LogUtil.h("CommonUI_BitmapUtils", "addWaterMarkPic() if (waterMarkBitmap == null)");
            return copy;
        }
        float width = (copy.getWidth() - bitmap2.getWidth()) / 2.0f;
        float height = copy.getHeight() - (bitmap2.getHeight() * 2.0f);
        LogUtil.a("CommonUI_BitmapUtils", "addWaterMarkPic() newbmpLeft=", Float.valueOf(width), "newbmpTop=", Float.valueOf(height));
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        Canvas canvas = new Canvas(copy);
        canvas.drawBitmap(bitmap2, width, height, paint);
        canvas.save();
        canvas.restore();
        return copy;
    }

    public static Bitmap cIa_(Bitmap bitmap, Bitmap bitmap2, String str, Context context) {
        return cJk_(bitmap, cHE_(bitmap2, str, context, cHZ_(bitmap, context, 16)), context, 16);
    }

    private static int cHZ_(Bitmap bitmap, Context context, int i) {
        float f = context.getResources().getDisplayMetrics().density;
        float f2 = i;
        return cIh_(bitmap, new Rect(nsn.c(context, f2), bitmap.getHeight() - ((int) ((f2 * f) + 40.0f)), (int) (41.0f * f), bitmap.getHeight() - ((int) (f * 12.0f))));
    }

    public static Bitmap cHE_(Bitmap bitmap, String str, Context context, int i) {
        if (TextUtils.isEmpty(str)) {
            return bitmap;
        }
        int i2 = str.length() <= 3 ? 15 : 10;
        try {
            Bitmap.Config config = bitmap.getConfig();
            if (config == null) {
                config = Bitmap.Config.ARGB_8888;
            }
            Paint paint = new Paint(1);
            paint.setAlpha(150);
            paint.setColor(i);
            paint.setTextSize(nsn.c(context, i2));
            paint.getTextBounds(str, 0, str.length(), new Rect());
            float f = context.getResources().getDisplayMetrics().density;
            Bitmap copy = bitmap.copy(config, true);
            if (f <= 0.0f) {
                return copy;
            }
            int i3 = (int) (2.0f * f);
            new Canvas(copy).drawText(str, ((copy.getWidth() - r8.width()) / i3) * f, ((copy.getHeight() + r8.height()) / i3) * f, paint);
            return copy;
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("CommonUI_BitmapUtils", "drawTextToBitmap NotFoundException");
            return bitmap;
        }
    }

    public static Bitmap cHN_(int i, Context context) {
        try {
            return BitmapFactory.decodeResource(context.getResources(), i);
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("CommonUI_BitmapUtils", "getBitmapByResID NotFoundException");
            return null;
        }
    }

    public static int cIh_(Bitmap bitmap, Rect rect) {
        if (bitmap == null) {
            return Color.argb(125, 0, 0, 0);
        }
        return cJm_(bitmap, rect);
    }

    private static boolean e(HwColorPicker.PickedColor pickedColor) {
        int state = pickedColor.getState();
        if (state == -1) {
            LogUtil.a("CommonUI_BitmapUtils", "check exception return ");
            return false;
        }
        if (state != 1) {
            return true;
        }
        LogUtil.a("CommonUI_BitmapUtils", "algorithm error return ");
        return false;
    }

    public static int cJm_(Bitmap bitmap, Rect rect) {
        if (bitmap == null || rect == null) {
            LogUtil.h("CommonUI_BitmapUtils", "obtainWidgetColor:bitmap/rect is null!");
            return -1;
        }
        HwColorPicker.PickedColor processBitmap = HwColorPicker.processBitmap(bitmap, rect);
        if (!e(processBitmap)) {
            return -1;
        }
        int i = processBitmap.get(HwColorPicker.ResultType.Widget);
        LogUtil.a("CommonUI_BitmapUtils", "widget ", Integer.valueOf(i));
        return i;
    }

    public static int cJl_(Bitmap bitmap, Rect rect) {
        if (bitmap == null || rect == null) {
            LogUtil.h("CommonUI_BitmapUtils", "obtainWidgetColor:bitmap/rect is null!");
            return -1;
        }
        HwColorPicker.PickedColor processBitmap = HwColorPicker.processBitmap(bitmap, rect);
        if (!e(processBitmap)) {
            return -1;
        }
        int i = processBitmap.get(HwColorPicker.ResultType.Domain);
        LogUtil.a("CommonUI_BitmapUtils", "Domain ", Integer.valueOf(i));
        return i;
    }

    public static int a(int i) {
        return nrn.e(i) == 1 ? -1 : -16777216;
    }

    public static Bitmap cHO_(View view) {
        Bitmap bitmap = null;
        if (view == null) {
            LogUtil.b("CommonUI_BitmapUtils", "getBitmapByView error: view is null");
            return null;
        }
        view.setDrawingCacheEnabled(true);
        if (view.getMeasuredHeight() == 0) {
            LogUtil.a("CommonUI_BitmapUtils", "getBitmapByView 2 view.height == 0");
            return null;
        }
        for (int i = 0; bitmap == null && i < 2; i++) {
            try {
                bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            } catch (OutOfMemoryError e2) {
                LogUtil.h("CommonUI_BitmapUtils", e2.getMessage());
            }
        }
        if (bitmap == null) {
            LogUtil.h("CommonUI_BitmapUtils", "getBitmapByView view No enough memory");
            return bitmap;
        }
        Canvas canvas = new Canvas(bitmap);
        long currentTimeMillis = System.currentTimeMillis();
        view.draw(canvas);
        LogUtil.a("CommonUI_BitmapUtils", "getViewBitmap_time2 ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return bitmap;
    }

    public static float e(int i, float f) {
        return TypedValue.applyDimension(i, f, Resources.getSystem().getDisplayMetrics());
    }

    public static ArrayList<Bitmap> cJn_(Bitmap bitmap, int i, int i2, int i3) {
        int i4 = i;
        int i5 = i2;
        if (bitmap == null || i4 <= 0 || i5 <= 0 || i3 <= 0) {
            LogUtil.h("CommonUI_BitmapUtils", "parseSprite bitmap ", bitmap, " frame ", Integer.valueOf(i), " column ", Integer.valueOf(i2), " row ", Integer.valueOf(i3));
            return new ArrayList<>(16);
        }
        ArrayList<Bitmap> arrayList = new ArrayList<>(i4);
        Matrix matrix = new Matrix();
        matrix.preScale(1.0f, 1.0f);
        double d2 = i5;
        int a2 = (int) UnitUtil.a(bitmap.getWidth() / d2, 0);
        int a3 = (int) UnitUtil.a(bitmap.getHeight() / i3, 0);
        int i6 = 0;
        while (i6 < i4) {
            try {
                int i7 = i6;
                int i8 = a2;
                arrayList.add(Bitmap.createBitmap(bitmap, (int) UnitUtil.a((i6 % i5) * a2, 0), (int) UnitUtil.a((i6 / d2) * a3, 0), a2, a3, matrix, true));
                i6 = i7 + 1;
                i4 = i;
                i5 = i2;
                a2 = i8;
            } catch (IllegalArgumentException | OutOfMemoryError e2) {
                LogUtil.b("CommonUI_BitmapUtils", "parseSprite exception ", ExceptionUtils.d(e2));
            }
        }
        if (!bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return arrayList;
    }

    public static Bitmap cHR_(int i) {
        Context e2 = BaseApplication.e();
        try {
            try {
                return BitmapFactory.decodeResource(e2.getResources(), i);
            } catch (IllegalArgumentException e3) {
                LogUtil.b("CommonUI_BitmapUtils", "getBitmapForResourceId exception ", ExceptionUtils.d(e3));
                Drawable drawable = ContextCompat.getDrawable(e2, i);
                if (drawable instanceof BitmapDrawable) {
                    return ((BitmapDrawable) drawable).getBitmap();
                }
                return null;
            }
        } catch (Resources.NotFoundException e4) {
            LogUtil.b("CommonUI_BitmapUtils", "getBitmapForResourceId exception ", ExceptionUtils.d(e4));
            return null;
        }
    }

    public static Bitmap cHQ_(String str) {
        String c2 = CommonUtil.c(str);
        Bitmap bitmap = null;
        if (j(c2)) {
            LogUtil.h("CommonUI_BitmapUtils", "getBitmapForPath path is invalid file");
            return null;
        }
        try {
            bitmap = BitmapFactory.decodeFile(c2);
        } catch (IllegalArgumentException e2) {
            LogUtil.b("CommonUI_BitmapUtils", "getBitmapForPath exception ", ExceptionUtils.d(e2));
        }
        if (bitmap != null) {
            return bitmap;
        }
        LogUtil.h("CommonUI_BitmapUtils", "getBitmapForPath bitmap is null");
        return new BitmapDrawable(BaseApplication.e().getResources(), c2).getBitmap();
    }

    public static BitmapDrawable cHP_(String str) {
        String c2 = CommonUtil.c(str);
        Bitmap bitmap = null;
        if (j(c2)) {
            LogUtil.h("CommonUI_BitmapUtils", "getBitmapDrawableForPath path is invalid file");
            return null;
        }
        try {
            bitmap = BitmapFactory.decodeFile(c2);
        } catch (IllegalArgumentException e2) {
            LogUtil.b("CommonUI_BitmapUtils", "getBitmapDrawableForPath exception ", ExceptionUtils.d(e2));
        }
        Resources resources = BaseApplication.e().getResources();
        if (bitmap == null) {
            LogUtil.h("CommonUI_BitmapUtils", "getBitmapDrawableForPath bitmap is null");
            return new BitmapDrawable(resources, c2);
        }
        return new BitmapDrawable(resources, bitmap);
    }

    private static boolean j(String str) {
        File file;
        if (TextUtils.isEmpty(str) || (file = FileUtils.getFile(str)) == null || !file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return file.length() <= 0;
        }
        File[] listFiles = file.listFiles();
        return listFiles == null || listFiles.length <= 0;
    }

    public static boolean cJt_(Bitmap bitmap, String str) {
        boolean z = false;
        if (bitmap == null || TextUtils.isEmpty(str)) {
            LogUtil.a("CommonUI_BitmapUtils", "saveBmpToFile: bitmap is null or path is empty");
            return false;
        }
        try {
            FileOutputStream openOutputStream = FileUtils.openOutputStream(new File(str));
            try {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, openOutputStream);
                openOutputStream.flush();
                z = true;
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
            LogUtil.h("CommonUI_BitmapUtils", "saveBmpToFile:IOException exception: ", e2.getMessage());
        } catch (IllegalArgumentException e3) {
            e = e3;
            LogUtil.h("CommonUI_BitmapUtils", "saveBmpToFile fail: IllegalArgumentException|IllegalStateException exception: ", e.getMessage());
        } catch (IllegalStateException e4) {
            e = e4;
            LogUtil.h("CommonUI_BitmapUtils", "saveBmpToFile fail: IllegalArgumentException|IllegalStateException exception: ", e.getMessage());
        }
        return z;
    }

    public static String cJs_(Bitmap bitmap, String str) {
        if (bitmap == null || TextUtils.isEmpty(str)) {
            LogUtil.a("CommonUI_BitmapUtils", "saveBmpToFile: bitmap is null or path is empty");
            return "";
        }
        try {
            FileOutputStream openOutputStream = FileUtils.openOutputStream(new File(str));
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, openOutputStream);
                openOutputStream.flush();
                if (openOutputStream == null) {
                    return str;
                }
                openOutputStream.close();
                return str;
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
            LogUtil.h("CommonUI_BitmapUtils", "saveBmpToFile:IOException exception: ", e2.getMessage());
            return "";
        } catch (IllegalArgumentException e3) {
            e = e3;
            LogUtil.h("CommonUI_BitmapUtils", "saveBmpToFile fail: IllegalArgumentException|IllegalStateException exception: ", e.getMessage());
            return "";
        } catch (IllegalStateException e4) {
            e = e4;
            LogUtil.h("CommonUI_BitmapUtils", "saveBmpToFile fail: IllegalArgumentException|IllegalStateException exception: ", e.getMessage());
            return "";
        }
    }

    public static Bitmap cHC_(String str, BitmapFactory.Options options) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return BitmapFactory.decodeFile(str, options);
            } catch (OutOfMemoryError unused) {
                LogUtil.h("CommonUI_BitmapUtils", "decodeImageFromFile OOM");
            }
        }
        return null;
    }

    public static Bitmap cHB_(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return BitmapFactory.decodeFile(str);
            } catch (OutOfMemoryError unused) {
                LogUtil.h("CommonUI_BitmapUtils", "decodeImageFromFile OOM");
            }
        }
        return null;
    }

    @Deprecated
    public static void cJh_(String str, ImageView imageView, int i) {
        cJi_(str, imageView, i);
    }

    private static void cJi_(String str, final ImageView imageView, final int i) {
        if (imageView == null || TextUtils.isEmpty(str)) {
            LogUtil.h("CommonUI_BitmapUtils", "loadUrlBackGround imageView is null or url is empty", str);
        } else {
            Glide.with(cHK_(imageView)).load(str).apply((BaseRequestOptions<?>) new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).transform(new nrp(imageView.getContext().getApplicationContext(), i))).into((RequestBuilder<Drawable>) new CustomTarget<Drawable>() { // from class: nrf.5
                @Override // com.bumptech.glide.request.target.Target
                public void onLoadCleared(Drawable drawable) {
                }

                @Override // com.bumptech.glide.request.target.Target
                /* renamed from: cJO_, reason: merged with bridge method [inline-methods] */
                public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                    if (LanguageUtil.bc(imageView.getContext().getApplicationContext())) {
                        drawable = nrz.cKm_(imageView.getContext().getApplicationContext(), drawable);
                    }
                    nrf.cIO_(drawable, imageView, i);
                }

                @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
                public void onLoadFailed(Drawable drawable) {
                    LogUtil.h("CommonUI_BitmapUtils", "loadUrlBackGround onLoadFailed");
                    super.onLoadFailed(drawable);
                }
            });
        }
    }

    public static byte[] cHu_(Bitmap bitmap, int i) {
        if (bitmap == null) {
            LogUtil.h("CommonUI_BitmapUtils", "compressBitmapBySize bitmap is null");
            return new byte[0];
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 100;
        do {
            try {
                byteArrayOutputStream.reset();
                bitmap.compress(Bitmap.CompressFormat.JPEG, i2, byteArrayOutputStream);
                i2 -= 10;
                LogUtil.a("CommonUI_BitmapUtils", "compressBitmapBySize options = ", Integer.valueOf(i2), ",size = ", Integer.valueOf(byteArrayOutputStream.toByteArray().length));
                if (byteArrayOutputStream.toByteArray().length / 1024 <= i) {
                    break;
                }
            } catch (Throwable th) {
                e(byteArrayOutputStream);
                throw th;
            }
        } while (i2 > 10);
        e(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Bitmap cHS_(Uri uri) {
        Bitmap bitmap;
        if (uri.toString().startsWith("http")) {
            return cHT_(c(), uri.toString());
        }
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(BaseApplication.e().getContentResolver(), uri));
            } else {
                bitmap = MediaStore.Images.Media.getBitmap(BaseApplication.e().getContentResolver(), uri);
            }
            return bitmap;
        } catch (IOException e2) {
            LogUtil.b("CommonUI_BitmapUtils", "getBitmapForUri fail", ExceptionUtils.d(e2));
            return null;
        }
    }

    public static void e(RequestOptions requestOptions, int i) {
        if (requestOptions == null) {
            LogUtil.b("CommonUI_BitmapUtils", "RequestOptions is null in setRequestOptionWithError");
        } else if (BaseApplication.e().getResources().getResourceName(i) == null) {
            LogUtil.b("CommonUI_BitmapUtils", "setRequestOptionWithError ResId: ", Integer.valueOf(i), " is null");
        } else {
            requestOptions.error(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Context cHK_(ImageView imageView) {
        if (imageView == null || imageView.getContext() == null) {
            LogUtil.a("CommonUI_BitmapUtils", "get imageview context fail");
            return c();
        }
        Context context = imageView.getContext();
        boolean z = context instanceof Activity;
        boolean z2 = context instanceof ContextWrapper;
        Object[] objArr = new Object[8];
        objArr[0] = "isActivity: ";
        objArr[1] = Boolean.valueOf(z);
        objArr[2] = ", topActivity == null ";
        objArr[3] = Boolean.valueOf(BaseApplication.wa_() == null);
        objArr[4] = ", isContextWrapper = ";
        objArr[5] = Boolean.valueOf(z2);
        objArr[6] = ", context == null ";
        objArr[7] = Boolean.valueOf(context == null);
        LogUtil.a("CommonUI_BitmapUtils", objArr);
        if ((z || z2) && !(z && ((Activity) context).isDestroyed())) {
            return cHs_((ContextWrapper) context) ? BaseApplication.e() : context;
        }
        return c();
    }

    private static boolean cHs_(ContextWrapper contextWrapper) {
        Context baseContext = contextWrapper.getBaseContext();
        if (baseContext == null || baseContext.getApplicationContext() == null) {
            return true;
        }
        if (baseContext instanceof Activity) {
            boolean isDestroyed = ((Activity) baseContext).isDestroyed();
            LogUtil.a("CommonUI_BitmapUtils", "checkContextWrapperActivityDestroyed baseContext = ", baseContext, ", isDestroyed = ", Boolean.valueOf(isDestroyed));
            return isDestroyed;
        }
        if (baseContext instanceof ContextWrapper) {
            return cHs_((ContextWrapper) baseContext);
        }
        return true;
    }

    private static Context b(Context context) {
        return (!(context instanceof Activity) || ((Activity) context).isDestroyed()) ? c() : context;
    }

    private static Context c() {
        Activity wa_ = BaseApplication.wa_();
        return (wa_ == null || wa_.isDestroyed()) ? BaseApplication.e() : wa_;
    }

    @Deprecated
    public static void cIr_(ImageView imageView, String str, Target<Drawable> target) {
        Glide.with(cHK_(imageView)).load(str).into((RequestBuilder<Drawable>) target);
    }

    public static Bitmap cHL_() {
        Drawable loadIcon = com.huawei.hwcommonmodel.application.BaseApplication.getContext().getApplicationInfo().loadIcon(com.huawei.hwcommonmodel.application.BaseApplication.getContext().getPackageManager());
        if (loadIcon instanceof BitmapDrawable) {
            return ((BitmapDrawable) loadIcon).getBitmap();
        }
        return null;
    }

    public static Bitmap cHn_(String str) {
        byte[] decode = Base64.decode(str.replace("data:image/png;base64,", "").replace("data:image/jpeg;base64,", "").getBytes(Charset.defaultCharset()), 0);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(GlideException glideException) {
        if (glideException == null) {
            LogUtil.b("CommonUI_BitmapUtils", "logRootCauses exception is null");
            return;
        }
        List<Throwable> rootCauses = glideException.getRootCauses();
        int size = rootCauses.size();
        for (int i = 0; i < size; i++) {
            if (rootCauses.get(i) != null) {
                if (rootCauses.get(i).getCause() != null) {
                    LogUtil.b("CommonUI_BitmapUtils", "Inner Root cause (" + (i + 1) + " of " + size + Constants.RIGHT_BRACKET_ONLY, rootCauses.get(i), "\n", rootCauses.get(i).getCause().toString(), c(rootCauses.get(i).getCause().getStackTrace()));
                } else {
                    LogUtil.b("CommonUI_BitmapUtils", "Root cause (" + (i + 1) + " of " + size + Constants.RIGHT_BRACKET_ONLY, rootCauses.get(i), c(rootCauses.get(i).getStackTrace()));
                }
            }
        }
    }

    private static String c(StackTraceElement[] stackTraceElementArr) {
        if (stackTraceElementArr == null || stackTraceElementArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            sb.append("\n");
            sb.append(stackTraceElement.toString());
        }
        return sb.toString();
    }
}
