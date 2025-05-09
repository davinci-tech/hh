package defpackage;

import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes3.dex */
public class djz {
    private static boolean e = false;

    public static void c(String str) {
        LogUtil.a("DeviceVoiceUtils", "init and start service");
        if (UnitUtil.h()) {
            LogUtil.a("DeviceVoiceUtils", "unexpected imperial unit status, not init voice service");
        } else {
            gxd.a().h(str);
            e = true;
        }
    }

    public static void d(String str) {
        LogUtil.a("DeviceVoiceUtils", "Destroy and stop service");
        if (e) {
            gxd.a().f(str);
            e = false;
        }
    }

    public static void b() {
        LogUtil.c("DeviceVoiceUtils", "playConnectSuccessVoice");
        if (dks.c(BaseApplication.getContext())) {
            gxd.a().c(R.raw._2131886151_res_0x7f120047);
        } else {
            LogUtil.h("DeviceVoiceUtils", "will play voice but language not chinese");
        }
    }
}
