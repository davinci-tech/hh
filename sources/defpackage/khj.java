package defpackage;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil;
import com.huawei.hwdevicemgr.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes5.dex */
public class khj {
    public static List<jje> c() {
        jje e;
        ArrayList arrayList = new ArrayList(16);
        List<String> i = NotificationContentProviderUtil.i();
        i.addAll(new ArrayList(a()));
        LogUtil.a("HwNotificationAppInfoUtil", "getNotificationPushAppList size: ", Integer.valueOf(i.size()));
        if (i.isEmpty()) {
            return arrayList;
        }
        for (String str : i) {
            if (bfg.e.equals(str)) {
                e = e();
            } else {
                e = e(str);
            }
            if (e != null) {
                arrayList.add(e);
            }
        }
        return arrayList;
    }

    private static Set<String> a() {
        HashSet hashSet = new HashSet();
        if (NotificationContentProviderUtil.j()) {
            boolean a2 = SharedPreferenceManager.a("SUPPORT_NOTIFY_LIVE_LEVEL_CAPABILITY", "SUPPORT_NOTIFY_LIVE_LEVEL_CAPABILITY", false);
            LogUtil.a("HwNotificationAppInfoUtil", "getLiveCloudList notifyLiveLevelCapability=", Boolean.valueOf(a2));
            if (a2) {
                String e = SharedPreferenceManager.e("NOTIFY_LIVE_LEVEL_VALUE", "NOTIFY_LIVE_LEVEL_VALUE", "levelDefault");
                LogUtil.a("HwNotificationAppInfoUtil", "getLiveCloudList notifyLiveLevel=", e);
                if (!TextUtils.equals(e, "levelDefault")) {
                    hashSet.addAll(NotificationContentProviderUtil.c(e));
                } else {
                    hashSet.addAll(NotificationContentProviderUtil.c("levelDefault"));
                }
            }
        }
        return hashSet;
    }

    public static List<jje> d() {
        LogUtil.a("HwNotificationAppInfoUtil", "getDefaultPushAppList enter");
        ArrayList arrayList = new ArrayList(16);
        HashSet hashSet = new HashSet();
        hashSet.addAll(a());
        hashSet.addAll(bfg.b);
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            jje e = e((String) it.next());
            if (e != null) {
                arrayList.add(e);
            }
        }
        jje e2 = e();
        if (e2 != null) {
            arrayList.add(e2);
        }
        return arrayList;
    }

    public static jje e(String str) {
        LogUtil.a("HwNotificationAppInfoUtil", "obtainSingleAppInfo enter packageName: ", str);
        String b = jrg.b(str);
        if (TextUtils.isEmpty(b) || TextUtils.equals(str, b)) {
            LogUtil.h("HwNotificationAppInfoUtil", "obtainSingleAppInfo appName is invalid packageName: ", str);
            return null;
        }
        PackageInfo bJd_ = jrg.bJd_(str);
        if (bJd_ == null) {
            LogUtil.h("HwNotificationAppInfoUtil", "obtainSingleAppInfo packageInfo is null, packageName: ", str);
            return null;
        }
        jje jjeVar = new jje();
        jjeVar.c(str);
        jjeVar.d(String.valueOf(bJd_.versionCode));
        jjeVar.a(b);
        jjeVar.b(1);
        if (TextUtils.equals(kiq.e("HwNotificationAppInfoUtil"), str)) {
            kiq.b("HwNotificationAppInfoUtil");
            jjeVar.a(BaseApplication.getContext().getResources().getString(R$string.IDS_short_message));
        }
        return jjeVar;
    }

    public static Drawable bNS_(String str) {
        if (TextUtils.equals(kiq.e("HwNotificationAppInfoUtil"), str)) {
            return new BitmapDrawable(BaseApplication.getContext().getResources(), jrg.bJb_(BaseApplication.getContext(), "notification_icon_sms"));
        }
        if (bfg.e.equals(str)) {
            try {
                PackageManager packageManager = BaseApplication.getContext().getPackageManager();
                return packageManager.getApplicationIcon(packageManager.getApplicationInfo(bfg.e, 128));
            } catch (PackageManager.NameNotFoundException unused) {
                LogUtil.b("HwNotificationAppInfoUtil", "getIconByPackageName NameNotFoundException");
            }
        } else {
            PackageInfo bJd_ = jrg.bJd_(str);
            if (bJd_ != null) {
                return BaseApplication.getContext().getPackageManager().getApplicationIcon(bJd_.applicationInfo);
            }
        }
        return null;
    }

    public static jje e() {
        PackageManager packageManager = BaseApplication.getContext().getPackageManager();
        String str = "";
        boolean z = false;
        try {
            str = packageManager.getApplicationLabel(packageManager.getApplicationInfo(bfg.e, 128)).toString();
            LogUtil.a("HwNotificationAppInfoUtil", "getIntelligent appName is: ", str);
            z = true;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("HwNotificationAppInfoUtil", "getIntelligent NameNotFoundException");
        }
        if (z) {
            z = khu.a(BaseApplication.getContext()).e();
        }
        if (!z) {
            LogUtil.h("HwNotificationAppInfoUtil", "getIntelligent is not support");
            return null;
        }
        jje jjeVar = new jje();
        jjeVar.a(str);
        jjeVar.c(bfg.e);
        jjeVar.b(1);
        if (!TextUtils.isEmpty(CommonUtil.r())) {
            jjeVar.d(CommonUtil.r());
        } else {
            PackageInfo bJd_ = jrg.bJd_(bfg.e);
            if (bJd_ != null) {
                LogUtil.a("HwNotificationAppInfoUtil", "packageInfo.versionCode: ", String.valueOf(bJd_.versionCode));
                jjeVar.d(String.valueOf(bJd_.versionCode));
            } else {
                LogUtil.h("HwNotificationAppInfoUtil", "getIntelligent packageInfo is null.");
                return jjeVar;
            }
        }
        return jjeVar;
    }

    public static Bitmap bNR_(Drawable drawable) {
        Bitmap bitmap = drawable instanceof BitmapDrawable ? ((BitmapDrawable) drawable).getBitmap() : null;
        return bitmap == null ? nrf.cHF_(drawable) : bitmap;
    }

    public static String c(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("HwNotificationAppInfoUtil", "getAppInfoHash parameter is empty: ", str);
            return "";
        }
        return cvx.b((str + str2).hashCode());
    }
}
