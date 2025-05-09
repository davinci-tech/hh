package defpackage;

import android.content.res.Resources;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.utils.versioncontrol.VersionControlUtil;
import com.huawei.ui.main.R$string;
import defpackage.plr;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes6.dex */
public class pln extends plr {
    public void e() {
        if (VersionControlUtil.isSupportSleepManagement()) {
            ReleaseLogUtil.e("SEND_MSG_FOR_INTEL_SLEEP", "SendMsgForMonthly sendMsg");
            plr.b bVar = new plr.b();
            Resources resources = BaseApplication.getContext().getResources();
            bVar.d(resources.getString(R$string.IDS_Intel_sleep_improvement_title));
            bVar.a(resources.getString(R$string.IDS_intel_sleep_bubble_3) + resources.getString(R$string.IDS_intel_sleep_see_details) + ">>");
            StringBuilder sb = new StringBuilder("huaweihealth://huaweihealth.app/");
            sb.append(b("openwith?address=com.huawei.health.h5.sleep-management?h5pro=true&pName=com.huawei.health&path=sleepReport&statusBarTextBlack&isImmerse&isNeedLogin=true", 2));
            bVar.e(sb.toString());
            bVar.b("huaweischeme://healthapp/router/" + b("openwith?address=com.huawei.health.h5.sleep-management?h5pro=true&pName=com.huawei.health&path=sleepReport&statusBarTextBlack&isImmerse&isNeedLogin=true", 1));
            ReleaseLogUtil.e("SEND_MSG_FOR_INTEL_SLEEP", bVar.toString());
            ppy.b(bVar);
        }
    }
}
