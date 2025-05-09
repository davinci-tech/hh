package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import com.huawei.up.utils.Constants;
import health.compact.a.Services;
import java.sql.Timestamp;
import java.util.Map;
import java.util.TimeZone;

/* loaded from: classes6.dex */
public class mcx {
    public static boolean d(Context context) {
        if (context == null) {
            return false;
        }
        Object systemService = context.getApplicationContext().getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = systemService instanceof ConnectivityManager ? ((ConnectivityManager) systemService).getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String c(Context context) {
        PluginAchieveAdapter adapter = mcv.d(context).getAdapter();
        return adapter != null ? adapter.getInfo(new String[]{Constants.METHOD_GET_USER_INFO}).get("picPath") : "";
    }

    public static String a(Context context) {
        PluginAchieveAdapter adapter = mcv.d(context).getAdapter();
        return adapter != null ? adapter.getInfo(new String[]{Constants.METHOD_GET_USER_INFO}).get("name") : "";
    }

    public static String b() {
        return ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).getShareNickName();
    }

    public static boolean e() {
        return ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).isShowUserInfo();
    }

    public static void cfN_(Context context, Bitmap bitmap, String str, Map<String, Object> map) {
        fdu fduVar = new fdu(1);
        fduVar.c((String) null);
        fduVar.a((String) null);
        fduVar.awp_(bitmap);
        fduVar.f(null);
        fduVar.b(str);
        fduVar.i(true);
        fduVar.e(1);
        fduVar.c(false);
        if (map != null) {
            fduVar.b(map);
        }
        if (AnalyticsValue.SUCCESSES_SHARE_1100011.value().equals(str)) {
            fduVar.b(5);
        }
        ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).exeShare(fduVar, context);
    }

    public static void d(Context context, String str, String str2, Map<String, Object> map) {
        fdu fduVar = new fdu(4);
        fduVar.c((String) null);
        fduVar.a((String) null);
        fduVar.d(str);
        fduVar.f(null);
        fduVar.b(str2);
        fduVar.i(false);
        fduVar.e(1);
        fduVar.c(false);
        if (AnalyticsValue.SUCCESSES_SHARE_1100011.value().equals(str2)) {
            fduVar.b(5);
        }
        fduVar.b(map);
        ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).exeShare(fduVar, context);
    }

    public static void cfM_(String str, final ImageView imageView) {
        LogUtil.a("PluginAchieveUtils", "get headImg with PortraitUrl");
        if (!TextUtils.isEmpty(str)) {
            nrf.b(str, new CustomTarget<Bitmap>() { // from class: mcx.2
                @Override // com.bumptech.glide.request.target.Target
                public void onLoadCleared(Drawable drawable) {
                }

                @Override // com.bumptech.glide.request.target.Target
                /* renamed from: cfO_, reason: merged with bridge method [inline-methods] */
                public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                    ImageView imageView2;
                    final Bitmap cgw_ = mfc.cgw_(bitmap);
                    if (cgw_ == null || (imageView2 = imageView) == null) {
                        return;
                    }
                    imageView2.post(new Runnable() { // from class: mcx.2.2
                        @Override // java.lang.Runnable
                        public void run() {
                            imageView.setImageBitmap(cgw_);
                        }
                    });
                }

                @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
                public void onLoadFailed(Drawable drawable) {
                    LogUtil.h("PluginAchieveUtils", "get user head image error");
                    super.onLoadFailed(drawable);
                }
            });
        } else {
            LogUtil.a("PluginAchieveUtils", "showUserHeadImage headImgPath is null! ");
        }
    }

    public static boolean d(String str) {
        boolean z;
        LogUtil.a("PluginAchieveUtils", "lastCheckInTime = ", str);
        boolean z2 = false;
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            if (str.length() > 10) {
                return e(str);
            }
            String replaceAll = String.valueOf(new Timestamp(System.currentTimeMillis())).substring(0, 10).replaceAll(com.huawei.openalliance.ad.constant.Constants.LINK, "");
            int parseInt = Integer.parseInt(str);
            int parseInt2 = Integer.parseInt(replaceAll);
            try {
                if (parseInt != parseInt2) {
                    if (Math.abs(parseInt - parseInt2) <= 1) {
                        mct.b(BaseApplication.getContext(), "kakaLastCheckInTime", "");
                    } else {
                        LogUtil.a("PluginAchieveUtils", "not check in today");
                        z = false;
                        LogUtil.a("PluginAchieveUtils", "isTodayCheckedIn = ", Boolean.valueOf(z));
                        return z;
                    }
                }
                LogUtil.a("PluginAchieveUtils", "isTodayCheckedIn = ", Boolean.valueOf(z));
                return z;
            } catch (NumberFormatException unused) {
                z2 = z;
                LogUtil.b("PluginAchieveUtils", "isTodayCheckedIn NumberFormatException");
                return z2;
            }
            z = true;
        } catch (NumberFormatException unused2) {
        }
    }

    private static boolean e(String str) {
        NumberFormatException e;
        boolean z;
        long parseLong;
        try {
            String[] split = str.split(":");
            LogUtil.a("PluginAchieveUtils", "checkTime length = ", Integer.valueOf(split.length));
            if (split.length >= 2) {
                long parseLong2 = Long.parseLong(split[0]);
                int parseInt = Integer.parseInt(split[1]);
                int rawOffset = TimeZone.getDefault().getRawOffset();
                parseLong = (rawOffset + parseLong2) - parseInt;
                LogUtil.a("PluginAchieveUtils", "time = ", Long.valueOf(parseLong2), " rawOffset = ", Integer.valueOf(parseInt), " curRawOffset = ", Integer.valueOf(rawOffset));
                LogUtil.a("PluginAchieveUtils", "lastCheckInTime = ", Long.valueOf(parseLong));
            } else {
                parseLong = Long.parseLong(str);
            }
            z = !mle.a(parseLong, System.currentTimeMillis());
            try {
                LogUtil.a("PluginAchieveUtils", "isTodayCheckedInOld = ", Boolean.valueOf(z));
            } catch (NumberFormatException e2) {
                e = e2;
                LogUtil.b("PluginAchieveUtils", "isTodayCheckedInOld: exception -> " + e.getMessage());
                return z;
            }
        } catch (NumberFormatException e3) {
            e = e3;
            z = false;
        }
        return z;
    }
}
