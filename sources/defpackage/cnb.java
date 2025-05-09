package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.health.device.ui.measure.activity.WifiDeviceShareActivity;
import com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.webview.WebViewActivity;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class cnb {
    public static void d(Context context, String str, String str2, String str3, HagridDeviceManagerFragment hagridDeviceManagerFragment) {
        String str4;
        if (nsn.o()) {
            LogUtil.h("JumpDetailsManager", "openSettingHelp:click too fast.");
            return;
        }
        if (!(ceo.d().d(str, true) instanceof cxh)) {
            LogUtil.a("JumpDetailsManager", "openSettingHelp showSelectBindDeviceDialog");
            hagridDeviceManagerFragment.showSelectBindDeviceDialog();
            return;
        }
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HOME_1010034.value(), new HashMap(16), 0);
        if (coz.d(BaseApplication.getContext(), str)) {
            String e = jah.c().e("scale_new_honor_help");
            if (!TextUtils.isEmpty(e)) {
                str3 = e;
            }
            str4 = str3 + cpa.o(e);
        } else {
            str4 = str3 + cpa.e(str2);
        }
        LogUtil.c("JumpDetailsManager", "openSettingHelp url:", str4);
        e(context, str4);
    }

    private static void e(Context context, String str) {
        Intent intent = new Intent(context, (Class<?>) WebViewActivity.class);
        intent.putExtra("WebViewActivity.REQUEST_URL_KEY", str);
        intent.putExtra(Constants.JUMP_MODE_KEY, 8);
        context.startActivity(intent);
    }

    public static void IT_(Context context, String str, ctk ctkVar, ContentValues contentValues, HagridDeviceManagerFragment hagridDeviceManagerFragment) {
        if (nsn.o()) {
            LogUtil.h("JumpDetailsManager", "startDeviceShare: click too fast.");
            return;
        }
        if (!(ceo.d().d(str, true) instanceof cxh)) {
            LogUtil.a("JumpDetailsManager", "startDeviceShare showSelectBindDeviceDialog");
            hagridDeviceManagerFragment.showSelectBindDeviceDialog();
        } else if (ctkVar != null) {
            Intent intent = new Intent(context, (Class<?>) WifiDeviceShareActivity.class);
            intent.putExtra("deviceId", ctkVar.d());
            intent.putExtra("commonDeviceInfo", contentValues);
            intent.putExtra("isSubUser", ctkVar.b().k() != 1);
            context.startActivity(intent);
        }
    }

    public static void a(Context context, String str, String str2) {
        cve cveVar;
        if (context == null) {
            return;
        }
        List<cve> e = e();
        if (e == null) {
            LogUtil.h("JumpDetailsManager", "startPairingGuideActivity deviceInfos is null");
            return;
        }
        Iterator<cve> it = e.iterator();
        while (true) {
            if (!it.hasNext()) {
                cveVar = null;
                break;
            } else {
                cveVar = it.next();
                if (cveVar.ac().contains(str)) {
                    break;
                }
            }
        }
        if (cveVar == null) {
            LogUtil.h("JumpDetailsManager", "startPairingGuideActivity pluginInfoBean is empty");
            return;
        }
        String t = cveVar.t();
        if (TextUtils.isEmpty(t)) {
            LogUtil.h("JumpDetailsManager", "pairGuid is empty");
            return;
        }
        String r = cveVar.r();
        if (TextUtils.isEmpty(r)) {
            LogUtil.h("JumpDetailsManager", "kindId is empty");
            return;
        }
        List<String> ac = cveVar.ac();
        if (koq.b(ac)) {
            LogUtil.h("JumpDetailsManager", "uuidList is empty");
            return;
        }
        Iterator<String> it2 = ac.iterator();
        while (it2.hasNext()) {
            LogUtil.c("JumpDetailsManager", "uuidList uuid = ", it2.next());
        }
        LogUtil.h("JumpDetailsManager", "pairGuide = ", t, "kindId = ", r);
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setClassName(context, "com.huawei.ui.device.activity.adddevice.PairingGuideActivity");
        if (ac instanceof ArrayList) {
            intent.putStringArrayListExtra("uuid_list", (ArrayList) ac);
        }
        intent.putExtra("is_invalidation", true);
        intent.putExtra("kind_id", r);
        intent.putExtra("pair_guide", t);
        intent.putExtra("uniqueId", str2);
        context.startActivity(intent);
    }

    private static List<cve> e() {
        return ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getDeviceList();
    }
}
