package defpackage;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.service.H5ProServiceManager;
import com.huawei.health.todo.api.TodoDataApi;
import com.huawei.health.weight.WeightApi;
import health.compact.a.Base64;
import health.compact.a.LogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: classes7.dex */
public class fba {
    public static final String d = jcu.f + "NutritionTableCropImg.jpg";

    /* renamed from: a, reason: collision with root package name */
    private static float f12416a = 1.0f;

    public static Point auZ_(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        LogUtil.c("DisplayUtils", "getScreenMetrics Width = ", Integer.valueOf(i), " Height = ", Integer.valueOf(i2));
        if (i > i2) {
            i = i2;
            i2 = i;
        }
        return new Point(i, i2);
    }

    public static RectF auU_(Context context) {
        if (nsn.l() && nsn.ag(context)) {
            LogUtil.c("DisplayUtils", "getDefaultCenterRect is Tahiti wideScreen model");
            return ava_(context);
        }
        Point auZ_ = auZ_(context);
        float f = auZ_.x;
        float f2 = 0.1f * f;
        float f3 = auZ_.y / 2;
        float f4 = 0.4f * f;
        float f5 = f3 - f4;
        float f6 = f * 0.9f;
        float f7 = f3 + f4;
        LogUtil.c("DisplayUtils", "getDefaultCenterRect left:", Float.valueOf(f2), " top:", Float.valueOf(f5), " right:", Float.valueOf(f6), " bottom:", Float.valueOf(f7));
        return new RectF(f2, f5, f6, f7);
    }

    public static RectF ava_(Context context) {
        LogUtil.c("DisplayUtils", "getTahitiDefaultCenterRect");
        Point auZ_ = auZ_(context);
        int i = auZ_.x;
        int i2 = auZ_.y;
        int i3 = i > i2 ? i2 / 2 : i / 2;
        float f = (auZ_.x - i3) / 2;
        float f2 = (auZ_.y - i3) / 2;
        float f3 = i3;
        float f4 = f + f3;
        float f5 = f3 + f2;
        LogUtil.c("DisplayUtils", "left:", Float.valueOf(f), " top:", Float.valueOf(f2), " right:", Float.valueOf(f4), " bottom:", Float.valueOf(f5));
        return new RectF(f, f2, f4, f5);
    }

    public static RectF auV_(float f, float f2, float f3) {
        float f4 = f / 2.0f;
        return new RectF(f2 - f4, f3 - f4, f2 + f4, f3 + f4);
    }

    public static RectF auW_(float f, float f2, int i, RectF rectF) {
        float f3;
        float f4;
        float f5;
        float f6;
        RectF rectF2 = new RectF();
        if (i != 0) {
            if (i == 1) {
                rectF2.set(rectF.left, f2, f, rectF.bottom);
                f3 = rectF.bottom - f2;
                f6 = rectF.left;
            } else if (i == 2) {
                rectF2.set(rectF.left, rectF.top, f, f2);
                f3 = f2 - rectF.top;
                f6 = rectF.left;
            } else {
                if (i != 3) {
                    return rectF;
                }
                rectF2.set(f, rectF.top, rectF.right, f2);
                f3 = f2 - rectF.top;
                f4 = rectF.right;
            }
            f5 = f - f6;
            return (f5 > 100.0f || f3 <= 100.0f) ? rectF : rectF2;
        }
        rectF2.set(f, f2, rectF.right, rectF.bottom);
        f3 = rectF.bottom - f2;
        f4 = rectF.right;
        f5 = f4 - f;
        if (f5 > 100.0f) {
        }
    }

    public static ArrayList<RectF> avb_(RectF rectF) {
        if (rectF == null) {
            LogUtil.a("DisplayUtils", "getValidMoveRectFs centerRectF is null");
            return null;
        }
        ArrayList<RectF> arrayList = new ArrayList<>(4);
        arrayList.add(auV_(100.0f, rectF.left, rectF.top));
        arrayList.add(auV_(100.0f, rectF.right, rectF.top));
        arrayList.add(auV_(100.0f, rectF.right, rectF.bottom));
        arrayList.add(auV_(100.0f, rectF.left, rectF.bottom));
        return arrayList;
    }

    public static Bitmap auY_(RectF rectF, Bitmap bitmap, Context context) {
        if (rectF == null || bitmap == null) {
            LogUtil.e("DisplayUtils", "getRectBitmap centerRect|bitmap is null");
            return bitmap;
        }
        Point auZ_ = auZ_(context);
        int i = (int) (rectF.right - rectF.left);
        int i2 = (int) (rectF.bottom - rectF.top);
        int i3 = (int) rectF.left;
        int i4 = (int) rectF.top;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (i >= width && i2 >= height) {
            return bitmap;
        }
        LogUtil.c("DisplayUtils", "getRectCropBitmap before bitmapX0=", Integer.valueOf(i3), " bitmapY0=", Integer.valueOf(i4), " width0=", Integer.valueOf(i), " height0=", Integer.valueOf(i2), " bitmapWidth=", Integer.valueOf(width), " bitmapHeight=", Integer.valueOf(height));
        RectF auS_ = auS_(bitmap, auZ_);
        if (width < auZ_.x || auS_.left > 0.0f) {
            i3 = (int) (rectF.left <= auS_.left ? rectF.left : rectF.left - auS_.left);
        }
        if (height < auZ_.y || auS_.top > 0.0f) {
            i4 = (int) Math.abs(rectF.top - auS_.top);
        }
        if (i + i3 > width) {
            i = width - i3;
        }
        if (i2 + i4 > height) {
            i2 = height - i4;
        }
        LogUtil.c("DisplayUtils", "getRectCropBitmap after bitmapX=", Integer.valueOf(i3), " bitmapY=", Integer.valueOf(i4), " width=", Integer.valueOf(i), " height=", Integer.valueOf(i2));
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, i3, i4, i, i2);
        bitmap.recycle();
        return createBitmap;
    }

    public static RectF auS_(Bitmap bitmap, Point point) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = point.x;
        int i2 = point.y;
        if (height > i2) {
            height = i2;
        }
        int i3 = i2 / 2;
        float abs = Math.abs((i / 2) - (width / 2));
        int i4 = height / 2;
        float abs2 = Math.abs(i3 - i4);
        float f = width + abs;
        float f2 = i3 + i4;
        LogUtil.c("DisplayUtils", "getBitmapRect left=", Float.valueOf(abs), " top=", Float.valueOf(abs2), " right=", Float.valueOf(f), " bottom=", Float.valueOf(f2));
        return new RectF(abs, abs2, f, f2);
    }

    public static RectF auT_(int i, int i2, Context context) {
        Point auZ_ = auZ_(context);
        int i3 = auZ_.x;
        int i4 = auZ_.y;
        if (i2 >= i4) {
            return auU_(context);
        }
        float f = i >= i3 ? i3 * 2 * 0.4f : i * 0.4f * 2.0f;
        float f2 = i2 >= i4 ? i4 * 2 * 0.4f : i2 * 0.4f * 2.0f;
        float f3 = (i3 - f) / 2.0f;
        float f4 = (i4 - f2) / 2.0f;
        return new RectF(f3, f4, f + f3, f2 + f4);
    }

    public static Bitmap ave_(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        LogUtil.c("DisplayUtils", "zoomBitmapMatchScreen bitmap ori width=", Integer.valueOf(width), " ori height=", Integer.valueOf(height));
        Point auZ_ = auZ_(BaseApplication.e());
        int i = auZ_.x;
        int i2 = auZ_.y;
        if (width == i || height == i2) {
            return bitmap;
        }
        float f = i / width;
        float f2 = i2 / height;
        LogUtil.c("DisplayUtils", "zoomBitmapMatchScreen scaleX=", Float.valueOf(f), " scaleY=", Float.valueOf(f2));
        float min = Math.min(f, f2);
        LogUtil.c("DisplayUtils", "zoomBitmapMatchScreen scale=", Float.valueOf(min));
        Matrix matrix = new Matrix();
        matrix.postScale(min, min);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        bitmap.recycle();
        return createBitmap;
    }

    public static void a(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.e("DisplayUtils", "showSnackControlPage context|ocrResult is null.");
            return;
        }
        SharedPreferenceManager.e("nutritionTableOcr", "isTrialUseFinish", true);
        bzs.e().initH5Pro();
        H5ProLaunchOption build = new H5ProLaunchOption.Builder().addPath("#overview?scanResult=" + str).setImmerse().showStatusBar().enableOnResumeCallback().setNeedSoftInputAdapter().addCustomizeJsModule("ocrJsModule", fas.class).addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).build();
        H5ProServiceManager.getInstance().registerService(((TodoDataApi) Services.c("HWUserProfileMgr", TodoDataApi.class)).getTodoJsClass());
        WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
        if (weightApi != null) {
            H5ProServiceManager.getInstance().registerService(weightApi.getDietDiaryRepositoryApi());
        } else {
            LogUtil.a("DisplayUtils", "weightApi is null.");
        }
        LogUtil.c("DisplayUtils", "start h5 SnackControlPage after recognize success");
        H5ProClient.startH5MiniProgram(context, "com.huawei.health.h5.snack-control", build);
    }

    public static String auR_(Bitmap bitmap) {
        if (bitmap != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            try {
                try {
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                    String a2 = Base64.a(byteArrayOutputStream.toByteArray());
                    try {
                        byteArrayOutputStream.flush();
                        byteArrayOutputStream.close();
                        return a2;
                    } catch (IOException e) {
                        LogUtil.e("DisplayUtils", "bitmapToBase64Encode exception2: ", ExceptionUtils.d(e));
                        return a2;
                    }
                } catch (IOException e2) {
                    LogUtil.e("DisplayUtils", "bitmapToBase64Encode exception1: ", ExceptionUtils.d(e2));
                }
            } finally {
                try {
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                } catch (IOException e3) {
                    LogUtil.e("DisplayUtils", "bitmapToBase64Encode exception2: ", ExceptionUtils.d(e3));
                }
            }
        } else {
            LogUtil.e("DisplayUtils", "bitmapToBase64Encode bitmap is null.");
        }
        return null;
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
        if (asJsonObject.has("resultCode")) {
            String asString = asJsonObject.get("resultCode").getAsString();
            if ("200".equals(asString)) {
                return true;
            }
            LogUtil.a("DisplayUtils", "parserOcrResult resultCode=", asString);
        }
        return false;
    }

    public static void avd_(Resources resources) {
        if (resources == null || resources.getConfiguration() == null) {
            return;
        }
        Configuration configuration = resources.getConfiguration();
        configuration.fontScale = f12416a;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    public static Resources avc_(Resources resources) {
        if (resources == null || resources.getConfiguration() == null) {
            LogUtil.a("DisplayUtils", "setLargeFontScaleMode resources or configuration is null");
            return resources;
        }
        Configuration configuration = resources.getConfiguration();
        float f = configuration.fontScale;
        f12416a = f;
        float f2 = f - 1.3f;
        if (f2 > 0.0f || nor.a(f2, 0.0f)) {
            configuration.fontScale = 1.3f;
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        }
        return resources;
    }
}
