package defpackage;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.datatype.templates.FloatingBoxTemplate;
import com.huawei.health.marketing.datatype.templates.TopBannerNewTemplate;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.Utils;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class efb {
    public static final boolean a() {
        return true;
    }

    public static final boolean a(Context context) {
        return true;
    }

    public static boolean b() {
        return true;
    }

    public static final boolean h() {
        return true;
    }

    public static final boolean i() {
        return true;
    }

    public static final boolean j() {
        return true;
    }

    public static boolean n() {
        return false;
    }

    public static final boolean t() {
        return true;
    }

    public static final boolean d() {
        return (Utils.o() || CommonUtil.bb()) ? false : true;
    }

    public static final boolean b(Context context) {
        return (nsn.ae(context) || CommonUtil.bb() || Utils.l()) ? false : true;
    }

    public static final boolean c() {
        return !Utils.o();
    }

    public static final boolean e(Context context) {
        return Utils.o();
    }

    public static final boolean f() {
        return (!CommonUtil.bv() || ntk.c()) && Process.is64Bit();
    }

    public static boolean k() {
        return (Utils.o() || LoginInit.getInstance(BaseApplication.e()).isKidAccount()) ? false : true;
    }

    public static final boolean g() {
        return Build.VERSION.SDK_INT <= 26;
    }

    public static void c(List<SingleGridContent> list) {
        if (koq.b(list) || b(com.huawei.hwcommonmodel.application.BaseApplication.getContext())) {
            return;
        }
        Iterator<SingleGridContent> it = list.iterator();
        while (it.hasNext()) {
            SingleGridContent next = it.next();
            if (next != null && ("phoneSleep".equals(next.getRedDotFlag()) || "huaweischeme://healthapp/basicHealth?healthType=1&pageType=detection&fromType=3&urlType=4&pName=com.huawei.health".equals(next.getLinkValue()))) {
                LogUtil.a("VersionControlUtils", "filter HomeBanner: ", next.toString());
                it.remove();
            }
        }
        LogUtil.a("VersionControlUtils", Integer.valueOf(list.size()));
    }

    public static boolean a(TopBannerNewTemplate topBannerNewTemplate) {
        if (topBannerNewTemplate == null || b(com.huawei.hwcommonmodel.application.BaseApplication.getContext()) || !"huaweischeme://healthapp/basicHealth?healthType=1&pageType=detection&fromType=3&urlType=4&pName=com.huawei.health".equals(topBannerNewTemplate.getLinkValue())) {
            return false;
        }
        LogUtil.a("VersionControlUtils", "filter TopBannerNewTemplate: ", topBannerNewTemplate.toString());
        return true;
    }

    public static void e(List<SingleDailyMomentContent> list) {
        if (koq.b(list) || b(com.huawei.hwcommonmodel.application.BaseApplication.getContext())) {
            return;
        }
        Iterator<SingleDailyMomentContent> it = list.iterator();
        while (it.hasNext()) {
            SingleDailyMomentContent next = it.next();
            if (next != null && ("huaweischeme://healthapp/basicHealth?healthType=1&pageType=detection&fromType=3&urlType=4&pName=com.huawei.health".equals(next.getCoverLinkValue()) || "huaweischeme://healthapp/basicHealth?healthType=1&pageType=detection&fromType=3&urlType=4&pName=com.huawei.health".equals(next.getButtonLinkValue()))) {
                it.remove();
                LogUtil.a("VersionControlUtils", "filter SingleDailyMomentContent: ", next.toString());
            }
        }
    }

    public static boolean d(FloatingBoxTemplate floatingBoxTemplate) {
        if (floatingBoxTemplate == null || b(com.huawei.hwcommonmodel.application.BaseApplication.getContext()) || !"huaweischeme://healthapp/basicHealth?healthType=1&pageType=detection&fromType=3&urlType=4&pName=com.huawei.health".equals(floatingBoxTemplate.getLinkValue())) {
            return false;
        }
        LogUtil.a("VersionControlUtils", "filter FloatingBoxTemplate: ", floatingBoxTemplate.toString());
        return true;
    }

    public static boolean c(mdf mdfVar) {
        if (mdfVar == null || b(com.huawei.hwcommonmodel.application.BaseApplication.getContext()) || !"huaweischeme://healthapp/basicHealth?healthType=1&pageType=detection&fromType=3&urlType=4&pName=com.huawei.health".equals(mdfVar.e())) {
            return false;
        }
        LogUtil.a("VersionControlUtils", "filter KakaTaskInfo: ", mdfVar.toString());
        return true;
    }

    public static boolean o() {
        return (CommonUtil.bb() || EnvironmentInfo.k()) ? false : true;
    }

    public static boolean l() {
        return !Utils.l();
    }

    public static boolean m() {
        return (Utils.c(com.huawei.hwcommonmodel.application.BaseApplication.getContext()) || Utils.l()) ? false : true;
    }

    public static boolean e() {
        return Process.is64Bit() && !dkx.b();
    }
}
