package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.trade.datatype.DeviceBenefitProductInfo;
import com.huawei.trade.datatype.DeviceInboxInfo;
import com.huawei.trade.datatype.DevicePerfPurchaseInfo;
import com.huawei.trade.datatype.InboxBenefitInfo;

/* loaded from: classes4.dex */
public class gpp {
    public static String e(DeviceInboxInfo deviceInboxInfo) {
        DeviceBenefitProductInfo productInfo;
        if (deviceInboxInfo == null || koq.b(deviceInboxInfo.getInboxBenefitInfos())) {
            LogUtil.h("BenefitUtils", "seviceInboxInfo is null.");
            return "";
        }
        for (InboxBenefitInfo inboxBenefitInfo : deviceInboxInfo.getInboxBenefitInfos()) {
            if (inboxBenefitInfo.isMainBenefit() && (productInfo = inboxBenefitInfo.getProductInfo()) != null) {
                return productInfo.getDuration();
            }
        }
        return "";
    }

    public static String e(DevicePerfPurchaseInfo devicePerfPurchaseInfo) {
        if (devicePerfPurchaseInfo == null) {
            LogUtil.h("BenefitUtils", "seviceInboxInfo is null.");
            return "";
        }
        if (devicePerfPurchaseInfo.getAutoRenew() != 1) {
            return devicePerfPurchaseInfo.getSubscribeProductInfo() == null ? "" : devicePerfPurchaseInfo.getSubscribeProductInfo().getDuration();
        }
        LogUtil.a("BenefitUtils", "user autoRenew = ", Integer.valueOf(devicePerfPurchaseInfo.getAutoRenew()));
        return devicePerfPurchaseInfo.getProductInfo() == null ? "" : devicePerfPurchaseInfo.getProductInfo().getDuration();
    }

    public static DeviceBenefitProductInfo b(DeviceInboxInfo deviceInboxInfo) {
        if (deviceInboxInfo == null || koq.b(deviceInboxInfo.getInboxBenefitInfos())) {
            LogUtil.h("BenefitUtils", "DeviceInboxInfo is null. ");
            return null;
        }
        for (InboxBenefitInfo inboxBenefitInfo : deviceInboxInfo.getInboxBenefitInfos()) {
            if (inboxBenefitInfo.isMainBenefit()) {
                return inboxBenefitInfo.getProductInfo();
            }
        }
        return null;
    }

    public static DeviceBenefitProductInfo a(DevicePerfPurchaseInfo devicePerfPurchaseInfo) {
        if (devicePerfPurchaseInfo == null) {
            LogUtil.h("BenefitUtils", "perfPurchaseInfo is null. ");
            return null;
        }
        LogUtil.a("BenefitUtils", "user autoRenew = ", Integer.valueOf(devicePerfPurchaseInfo.getAutoRenew()));
        if (devicePerfPurchaseInfo.getAutoRenew() == 1) {
            return devicePerfPurchaseInfo.getProductInfo();
        }
        return devicePerfPurchaseInfo.getSubscribeProductInfo();
    }
}
