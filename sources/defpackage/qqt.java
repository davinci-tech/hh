package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.trade.datatype.DeviceInboxInfo;
import com.huawei.ui.commonui.webview.WebViewActivity;

/* loaded from: classes7.dex */
public class qqt {
    public static boolean b(DeviceInboxInfo deviceInboxInfo) {
        if (deviceInboxInfo == null) {
            LogUtil.h("DeviceInboxUtil", "isEffectiveTime() mDeviceInboxInfo is null");
            return false;
        }
        long effectiveStartTime = deviceInboxInfo.getEffectiveStartTime();
        long effectiveEndTime = deviceInboxInfo.getEffectiveEndTime();
        long h = jec.h();
        return h >= effectiveStartTime && h <= effectiveEndTime;
    }

    public static boolean c(DeviceInboxInfo deviceInboxInfo) {
        if (deviceInboxInfo != null) {
            return deviceInboxInfo.getActiveStatus() == 1;
        }
        LogUtil.h("DeviceInboxUtil", "isActive() mDeviceInboxInfo is null");
        return false;
    }

    public static boolean a(DeviceInboxInfo deviceInboxInfo) {
        if (deviceInboxInfo == null) {
            LogUtil.h("DeviceInboxUtil", "checkBaseConditions mDeviceInboxInfo is null");
            return false;
        }
        if (c(deviceInboxInfo) && b(deviceInboxInfo)) {
            return true;
        }
        LogUtil.h("DeviceInboxUtil", "product isActived or Equity Expiration");
        return false;
    }

    public static boolean e(DeviceInboxInfo deviceInboxInfo) {
        if (deviceInboxInfo != null) {
            return a(deviceInboxInfo) && deviceInboxInfo.getBenefitType() == 2;
        }
        LogUtil.h("DeviceInboxUtil", "isShowServiceTips mDeviceInboxInfo is null");
        return false;
    }

    public static void e(Context context, int i, String str) {
        if (str == null) {
            LogUtil.h("DeviceInboxUtil", "clickDeviceInboxInfo can not jump");
            return;
        }
        try {
            if (i == 1) {
                Intent intent = new Intent();
                intent.setClass(context, WebViewActivity.class);
                intent.putExtra("url", str);
                context.startActivity(intent);
            } else if (i == 2) {
                Intent intent2 = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str));
                intent2.addCategory("android.intent.category.DEFAULT").addFlags(268435456);
                context.startActivity(intent2);
            } else {
                LogUtil.h("DeviceInboxUtil", "clickDeviceInboxInfo error linkType");
            }
        } catch (ActivityNotFoundException e) {
            LogUtil.b("DeviceInboxUtil", "ActivityNotFoundException", e.getMessage());
        }
    }

    public static String d(int i, String str) {
        if (!TextUtils.isEmpty(str)) {
            return BaseApplication.getContext().getResources().getString(i, str);
        }
        LogUtil.h("DeviceInboxUtil", "getTextTips timeStr is null");
        return null;
    }
}
