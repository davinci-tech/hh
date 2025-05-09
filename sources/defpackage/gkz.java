package defpackage;

import com.huawei.health.R;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.trade.datatype.BaseDeviceBenefitInfo;
import com.huawei.trade.datatype.DeviceBenefits;
import com.huawei.trade.datatype.DeviceInboxInfo;
import com.huawei.trade.datatype.DevicePerfPurchaseInfo;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class gkz {
    public static void e(DeviceBenefits deviceBenefits) {
        if (deviceBenefits == null) {
            LogUtil.h("DeviceMessageUtils", "deviceBenefits is null.");
            return;
        }
        if (koq.c(deviceBenefits.getInboxInfos())) {
            for (DeviceInboxInfo deviceInboxInfo : deviceBenefits.getInboxInfos()) {
                e(deviceInboxInfo, deviceInboxInfo.getInboxName(), deviceBenefits.getDeviceSn(), gpp.e(deviceInboxInfo), 0);
            }
        }
        if (koq.c(deviceBenefits.getPerfPurchaseInfos())) {
            for (DevicePerfPurchaseInfo devicePerfPurchaseInfo : deviceBenefits.getPerfPurchaseInfos()) {
                if (devicePerfPurchaseInfo.isBenefitInfoValid()) {
                    LogUtil.a("DeviceMessageUtils", "setDeviceMessage DevicePerfPurchaseInfo.");
                    e(devicePerfPurchaseInfo, devicePerfPurchaseInfo.getPerfPurchaseName(), deviceBenefits.getDeviceSn(), gpp.e(devicePerfPurchaseInfo), gpn.a(devicePerfPurchaseInfo));
                }
            }
        }
    }

    private static void e(BaseDeviceBenefitInfo baseDeviceBenefitInfo, String str, String str2, String str3, int i) {
        if (baseDeviceBenefitInfo.getActiveStatus() == 2) {
            LogUtil.a("DeviceMessageUtils", "deviceBenefit is activated.");
            return;
        }
        if (c(baseDeviceBenefitInfo.getEffectiveStartTime(), baseDeviceBenefitInfo.getEffectiveEndTime())) {
            int b = eil.b(System.currentTimeMillis(), baseDeviceBenefitInfo.getEffectiveEndTime());
            if (b == 3 || b == 7) {
                LogUtil.a("DeviceMessageUtils", "setDeviceMessage will expired. expiredDay: ", Integer.valueOf(b));
                d(baseDeviceBenefitInfo, BaseApplication.getContext().getResources().getString(R.string.IDS_device_benefit_will_expired_title), BaseApplication.getContext().getResources().getString(R.string.IDS_device_benefit_will_expired, baseDeviceBenefitInfo.getDeviceType(), str, BaseApplication.getContext().getResources().getQuantityString(R.plurals.IDS_user_profile_achieve_num_day, b, UnitUtil.e(b, 1, 0))), str2);
                return;
            }
            LogUtil.a("DeviceMessageUtils", "setDeviceMessage activate.");
            d(baseDeviceBenefitInfo, BaseApplication.getContext().getResources().getString(R.string.IDS_device_benefit_activate_title), BaseApplication.getContext().getResources().getString(R.string.IDS_device_benefit_activate, gpn.e(str3, i), str), str2);
            return;
        }
        LogUtil.a("DeviceMessageUtils", "setDeviceMessage expired.");
        d(baseDeviceBenefitInfo, BaseApplication.getContext().getResources().getString(R.string.IDS_device_benefit_expired_title), BaseApplication.getContext().getResources().getString(R.string.IDS_device_benefit_expired, baseDeviceBenefitInfo.getDeviceType(), str, dpg.e(baseDeviceBenefitInfo.getEffectiveEndTime())), str2);
    }

    private static void d(BaseDeviceBenefitInfo baseDeviceBenefitInfo, String str, String str2, String str3) {
        String str4;
        MessageObject e = e();
        e.setHuid(LoginInit.getInstance(com.huawei.haf.application.BaseApplication.e()).getAccountInfo(1011));
        e.setMsgFrom(str3);
        e.setDetailUri("messagecenter://benefit?type=" + baseDeviceBenefitInfo.getLinkType() + "&key=" + gic.a(baseDeviceBenefitInfo.getLinkValue()));
        e.setMsgTitle(str);
        e.setMsgContent(str2);
        String valueOf = String.valueOf(90);
        if (baseDeviceBenefitInfo.getBenefitType() == 1) {
            valueOf = String.valueOf(70);
            str4 = MessageConstant.MEMBER_TYPE;
        } else {
            str4 = MessageConstant.BENEFIT_TYPE;
        }
        e.setModule(valueOf);
        e.setType(str4);
        MessageCenterApi messageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
        List<MessageObject> messages = messageCenterApi.getMessages(valueOf, str4);
        if (koq.c(messages)) {
            LogUtil.a("DeviceMessageUtils", "benefitMessageList is not empty.");
            Iterator<MessageObject> it = messages.iterator();
            while (it.hasNext()) {
                if (it.next().equals(e)) {
                    LogUtil.a("DeviceMessageUtils", "Device message exist.");
                    return;
                }
            }
        }
        e.setMsgId(messageCenterApi.getMessageId(valueOf, str4));
        messageCenterApi.insertMessage(e);
        messageCenterApi.showNotification(com.huawei.haf.application.BaseApplication.e(), e);
    }

    private static MessageObject e() {
        MessageObject messageObject = new MessageObject();
        messageObject.setCreateTime(System.currentTimeMillis());
        messageObject.setPosition(3);
        messageObject.setNotified(0);
        messageObject.setExpireTime(0L);
        messageObject.setFlag(0);
        return messageObject;
    }

    public static DeviceInboxInfo e(List<DeviceInboxInfo> list) {
        if (koq.b(list)) {
            return null;
        }
        for (DeviceInboxInfo deviceInboxInfo : list) {
            if (deviceInboxInfo.getBenefitType() == 2 && deviceInboxInfo.isBenefitInfoValid()) {
                LogUtil.a("DeviceMessageUtils", "ExclusiveGuardBenefit exist.");
                return deviceInboxInfo;
            }
        }
        return null;
    }

    private static boolean c(long j, long j2) {
        long h = jec.h();
        return h >= j && h <= j2;
    }
}
