package defpackage;

import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.healthcloud.plugintrack.ui.map.mapdescription.MapTypeDescription;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.io.FileInputStream;
import java.io.IOException;

/* loaded from: classes4.dex */
public class hcr {

    /* renamed from: a, reason: collision with root package name */
    private static final hkt f13090a = new hkt(0.0f, 0.2f, 0.2f, 1.0f);
    private static SparseArray<String> b;
    private static SparseArray<String> c;

    public static int a(int i) {
        return i != 222 ? i != 280 ? i != 282 ? i != 257 ? i != 259 ? i != 260 ? R.drawable._2131431250_res_0x7f0b0f52 : R.drawable._2131431248_res_0x7f0b0f50 : R.drawable._2131431246_res_0x7f0b0f4e : R.drawable._2131431251_res_0x7f0b0f53 : R.drawable._2131431249_res_0x7f0b0f51 : R.drawable._2131431245_res_0x7f0b0f4d : R.drawable._2131431247_res_0x7f0b0f4f;
    }

    static {
        SparseArray<String> sparseArray = new SparseArray<>();
        b = sparseArray;
        sparseArray.put(1, "map_type_key");
        b.put(2, "google_map_type_key");
        b.put(3, "huawei_map_type_key");
        SparseArray<String> sparseArray2 = new SparseArray<>();
        c = sparseArray2;
        sparseArray2.put(1, "map_type_satellite");
        c.put(2, "map_type_normal");
        c.put(3, "map_type_normal");
    }

    public static void bai_(Activity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    public static String c(String str, double d) {
        String e;
        try {
            double parseDouble = Double.parseDouble(str);
            if (parseDouble >= 100.0d) {
                e = UnitUtil.e(parseDouble, 1, 1);
            } else {
                e = UnitUtil.e(parseDouble, 1, 2);
            }
            return Double.parseDouble(e) <= Double.parseDouble(hji.e(d)) ? e : hji.e(d);
        } catch (NumberFormatException e2) {
            LogUtil.b("DynamicTrackUiUtils", LogAnonymous.b((Throwable) e2));
            return str;
        }
    }

    public static String d(Context context, int i) {
        if (context == null || i < 0) {
            LogUtil.b("DynamicTrackUiUtils", "getPaceUnit input data error.");
            return "";
        }
        if (i == 16) {
            return d(context);
        }
        if (i == 18) {
            return context.getResources().getString(R.string._2130839766_res_0x7f0208d6);
        }
        return c(context);
    }

    public static String c(int i, float f) {
        if (i < 0 || f < 0.0f) {
            LogUtil.b("DynamicTrackUiUtils", "getPaceValue input data error.");
            return "";
        }
        if (i == 16) {
            return hji.c(f);
        }
        if (i == 17) {
            return c(f);
        }
        return e(f);
    }

    public static void bae_(View view) {
        TranslateAnimation translateAnimation;
        if (view == null) {
            return;
        }
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            translateAnimation = new TranslateAnimation(-558.0f, 0.0f, 0.0f, 0.0f);
        } else {
            translateAnimation = new TranslateAnimation(558.0f, 0.0f, 0.0f, 0.0f);
        }
        translateAnimation.setInterpolator(f13090a);
        translateAnimation.setDuration(400L);
        translateAnimation.setFillAfter(true);
        view.startAnimation(translateAnimation);
    }

    public static void baj_(LinearLayout linearLayout, boolean z) {
        if (linearLayout == null) {
            LogUtil.b("DynamicTrackUiUtils", "initBarrageAnimation error layout");
            return;
        }
        LayoutTransition layoutTransition = new LayoutTransition();
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat((Object) null, "scaleX", 0.0f, 1.0f), ObjectAnimator.ofFloat((Object) null, "scaleY", 0.0f, 1.0f), ObjectAnimator.ofFloat((Object) null, "pivotX", 0.0f), ObjectAnimator.ofFloat((Object) null, "pivotY", 0.0f), ObjectAnimator.ofFloat((Object) null, "Alpha", 0.0f, 1.0f));
        layoutTransition.setAnimator(2, animatorSet);
        layoutTransition.setStartDelay(2, 0L);
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(ObjectAnimator.ofFloat((Object) null, "Alpha", 1.0f, 0.0f), ObjectAnimator.ofFloat((Object) null, "translationY", 0.0f, 200.0f));
        animatorSet2.setDuration(350L);
        layoutTransition.setAnimator(3, animatorSet2);
        layoutTransition.setStartDelay(3, 0L);
        bac_(linearLayout, z, layoutTransition);
        linearLayout.setLayoutTransition(layoutTransition);
    }

    private static void bac_(final LinearLayout linearLayout, final boolean z, LayoutTransition layoutTransition) {
        layoutTransition.addTransitionListener(new LayoutTransition.TransitionListener() { // from class: hcr.5
            @Override // android.animation.LayoutTransition.TransitionListener
            public void endTransition(LayoutTransition layoutTransition2, ViewGroup viewGroup, View view, int i) {
            }

            @Override // android.animation.LayoutTransition.TransitionListener
            public void startTransition(LayoutTransition layoutTransition2, ViewGroup viewGroup, View view, int i) {
                Drawable drawable;
                Drawable drawable2;
                Drawable drawable3;
                if (view == null) {
                    LogUtil.b("DynamicTrackUiUtils", "transition view is null");
                    return;
                }
                Context context = view.getContext();
                if (context == null) {
                    LogUtil.b("DynamicTrackUiUtils", "transition context is null");
                    return;
                }
                Context applicationContext = context.getApplicationContext();
                if (i == 2) {
                    if (LanguageUtil.bc(applicationContext)) {
                        if (z) {
                            drawable3 = applicationContext.getResources().getDrawable(R.drawable._2131431218_res_0x7f0b0f32);
                        } else {
                            drawable3 = applicationContext.getResources().getDrawable(R.drawable._2131431217_res_0x7f0b0f31);
                        }
                        view.setBackground(drawable3);
                    } else {
                        if (z) {
                            drawable = applicationContext.getResources().getDrawable(R.drawable._2131431221_res_0x7f0b0f35);
                        } else {
                            drawable = applicationContext.getResources().getDrawable(R.drawable._2131431220_res_0x7f0b0f34);
                        }
                        view.setBackground(drawable);
                    }
                    if (linearLayout.getChildCount() > 0) {
                        View childAt = linearLayout.getChildAt(0);
                        if (z) {
                            drawable2 = applicationContext.getResources().getDrawable(R.drawable._2131431216_res_0x7f0b0f30);
                        } else {
                            drawable2 = applicationContext.getResources().getDrawable(R.drawable._2131431215_res_0x7f0b0f2f);
                        }
                        childAt.setBackground(drawable2);
                    }
                }
            }
        });
    }

    public static void bad_(View view) {
        if (view == null) {
            return;
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setInterpolator(f13090a);
        alphaAnimation.setDuration(400L);
        alphaAnimation.setFillAfter(true);
        view.startAnimation(alphaAnimation);
    }

    public static void baf_(View view) {
        TranslateAnimation translateAnimation;
        if (view == null) {
            return;
        }
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            translateAnimation = new TranslateAnimation(0.0f, -558.0f, 0.0f, 0.0f);
        } else {
            translateAnimation = new TranslateAnimation(0.0f, 558.0f, 0.0f, 0.0f);
        }
        translateAnimation.setInterpolator(f13090a);
        translateAnimation.setDuration(400L);
        translateAnimation.setFillAfter(true);
        view.startAnimation(translateAnimation);
    }

    public static String b() {
        return ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).getShareNickName();
    }

    public static Bitmap bah_(Context context) {
        if (context == null) {
            LogUtil.b("DynamicTrackUiUtils", "getUserHeader context null.");
            return null;
        }
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class);
        if (userProfileMgrApi != null) {
            UserInfomation userInfo = userProfileMgrApi.getUserInfo();
            String picPath = userInfo != null ? userInfo.getPicPath() : null;
            if (!TextUtils.isEmpty(picPath)) {
                return nrf.cIe_(context, picPath);
            }
        }
        return null;
    }

    public static void bam_(int i, Object obj, int i2, Handler handler) {
        if (i < 0 || obj == null || i2 < 0 || handler == null) {
            LogUtil.b("DynamicTrackUiUtils", "sendMessage error input data.");
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = obj;
        handler.sendMessageDelayed(obtain, i2);
    }

    public static void bak_(int i, int i2, Handler handler) {
        if (i < 0 || i2 < 0 || handler == null) {
            LogUtil.b("DynamicTrackUiUtils", "sendMessage error input data.");
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = i;
        handler.sendMessageDelayed(obtain, i2);
    }

    public static void bal_(int i, int i2, Handler handler, String str) {
        if (i < 0 || i2 < 0 || handler == null || str == null) {
            LogUtil.b("DynamicTrackUiUtils", "sendMessage error input data.");
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = str;
        handler.sendMessageDelayed(obtain, i2);
    }

    public static MapTypeDescription.MapType d(String str) {
        if ("map_type_normal".equals(str)) {
            return MapTypeDescription.MapType.MAP_TYPE_NORMAL;
        }
        if ("map_type_night".equals(str)) {
            return MapTypeDescription.MapType.MAP_TYPE_NIGHT;
        }
        if ("map_type_navi".equals(str)) {
            return MapTypeDescription.MapType.MAP_TYPE_NAVI;
        }
        if ("map_type_custom".equals(str)) {
            return MapTypeDescription.MapType.MAP_TYPE_CUSTOM;
        }
        return MapTypeDescription.MapType.MAP_TYPE_SATELLITE;
    }

    public static String e(MapTypeDescription.MapType mapType) {
        if (mapType == MapTypeDescription.MapType.MAP_TYPE_SATELLITE) {
            return "map_type_satellite";
        }
        if (mapType == MapTypeDescription.MapType.MAP_TYPE_NIGHT) {
            return "map_type_night";
        }
        if (mapType == MapTypeDescription.MapType.MAP_TYPE_NAVI) {
            return "map_type_navi";
        }
        if (mapType == MapTypeDescription.MapType.MAP_TYPE_NORMAL) {
            return "map_type_normal";
        }
        if (mapType == MapTypeDescription.MapType.MAP_TYPE_CUSTOM) {
            return "map_type_custom";
        }
        LogUtil.a("DynamicTrackUiUtils", "error inMapType");
        return "map_type_satellite";
    }

    public static MapTypeDescription.MapType d(int i) {
        if (i == 1) {
            return MapTypeDescription.MapType.MAP_TYPE_NORMAL;
        }
        if (i == 3) {
            return MapTypeDescription.MapType.MAP_TYPE_NIGHT;
        }
        if (i == 4) {
            return MapTypeDescription.MapType.MAP_TYPE_NAVI;
        }
        if (i == 2) {
            return MapTypeDescription.MapType.MAP_TYPE_SATELLITE;
        }
        if (i == 5) {
            return MapTypeDescription.MapType.DEFAULT_MAP_THREE_D;
        }
        return MapTypeDescription.MapType.MAP_TYPE_CUSTOM;
    }

    public static String c(int i) {
        if (i == 1) {
            return "custom_gaode_map_information_69";
        }
        if (i == 2) {
            return "new_custom_google_map_information";
        }
        if (i == 3) {
            return "new_custom_hsm_map_information";
        }
        LogUtil.b("DynamicTrackUiUtils", "type error");
        return null;
    }

    public static String c(double d) {
        if (d < 0.0d) {
            LogUtil.b("DynamicTrackUiUtils", "requestSpeed input data error.");
            return "";
        }
        if (UnitUtil.h()) {
            d = UnitUtil.e(d, 3);
        }
        return UnitUtil.e(d, 1, 2);
    }

    public static String e(double d) {
        if (d < 0.0d) {
            LogUtil.b("DynamicTrackUiUtils", "inputStepsMin input data error.");
            return "";
        }
        return UnitUtil.e(d, 1, 0);
    }

    public static String c(Context context) {
        if (context == null) {
            LogUtil.b("DynamicTrackUiUtils", "requestSpeedUnit context is null.");
            return "";
        }
        if (UnitUtil.h()) {
            return context.getString(R.string._2130844079_res_0x7f0219af);
        }
        return context.getString(R.string._2130844078_res_0x7f0219ae);
    }

    public static String d(Context context) {
        if (context == null) {
            LogUtil.b("DynamicTrackUiUtils", "requestPaceUnit context is null.");
            return "";
        }
        if (UnitUtil.h()) {
            return "/" + context.getResources().getString(R.string._2130844081_res_0x7f0219b1);
        }
        return context.getResources().getQuantityString(R.plurals._2130903132_res_0x7f03005c, 1, "");
    }

    public static String a(double d) {
        if (UnitUtil.h()) {
            return UnitUtil.e(UnitUtil.e(d, 1), 1, 2);
        }
        return UnitUtil.e(d, 1, 1);
    }

    public static String a(Context context) {
        if (context == null) {
            LogUtil.b("DynamicTrackUiUtils", "requestAltitudeUnit context is null.");
            return "";
        }
        if (UnitUtil.h()) {
            return context.getResources().getQuantityString(R.plurals._2130903238_res_0x7f0300c6, 1, "");
        }
        return context.getString(R.string._2130841568_res_0x7f020fe0);
    }

    public static String e(double d, Context context) {
        if (d < 0.0d || context == null) {
            LogUtil.b("DynamicTrackUiUtils", "requestTotalDistance input data error.");
            return "";
        }
        if (d == 0.0d) {
            return context.getString(R.string._2130850262_res_0x7f0231d6);
        }
        return hji.e(d);
    }

    public static String b(Context context) {
        if (context == null) {
            LogUtil.b("DynamicTrackUiUtils", "requestDistanceUnit context is null.");
            return "";
        }
        if (UnitUtil.h()) {
            return context.getResources().getString(R.string._2130844081_res_0x7f0219b1);
        }
        return context.getResources().getString(R.string._2130844082_res_0x7f0219b2);
    }

    public static String d(double d) {
        if (d < 0.0d) {
            LogUtil.b("DynamicTrackUiUtils", "requestHeartRate input data error.");
            return "";
        }
        return UnitUtil.e(d, 1, 0);
    }

    public static String e(int i) {
        return b.get(i);
    }

    public static String b(int i) {
        return c.get(i);
    }

    public static Bitmap bag_(Context context, String str) {
        Bitmap bitmap = null;
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.h("DynamicTrackUiUtils", "createBitmap:context is null or path is empty!");
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            try {
                bitmap = BitmapFactory.decodeStream(fileInputStream);
                fileInputStream.close();
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException unused) {
            LogUtil.h("DynamicTrackUiUtils", "createBitmap:IOException");
        } catch (OutOfMemoryError unused2) {
            LogUtil.h("DynamicTrackUiUtils", "createBitmap:OutOfMemoryError");
        }
        return bitmap;
    }
}
