package com.huawei.ui.device.utlis.clouddevice;

import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.nfc.PluginPayAdapter;
import defpackage.ixx;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class DevicePopTipBiUtil {

    public enum TipType {
        POP(1),
        TIP(2);

        int value;

        TipType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum Content {
        LOCATION_SERVICES(1),
        LOCATION_PERMISSIONS(2),
        CONTACT_RIGHTS(3),
        PHONE_RIGHTS(4),
        THIRD_PARTY_KEEP_ALIVE(5),
        ALIPAY_OPEN(6),
        WECHAT_DEVICE_BINDING(7),
        SENSITIVE_NOTIFICATION(8),
        SENSITIVE_NOTIFICATION_SECONDARY(9),
        REOPEN_NOTIFICATION(10),
        REOPEN_NOTIFICATION_DIALOG(11);

        int value;

        Content(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum Action {
        NONE(0),
        CANCEL(1),
        DETERMINED(2),
        NOT_PROMPT_AGAIN(3),
        PROHIBITED(4),
        ONLY_ALLOWED_USE(5),
        ALL_ALLOWED(6);

        int value;

        Action(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum Page {
        DEVICE_TAB("Device tab"),
        DEVICE_DETAIL("Device detail");

        String value;

        Page(String str) {
            this.value = str;
        }

        public String getValue() {
            return this.value;
        }
    }

    public enum Event {
        SHOW(1),
        CLICK(2);

        int value;

        Event(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static void b(TipType tipType, Content content, Action action, String str, String str2, String str3, Page page, Event event) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("tipType", Integer.valueOf(tipType.getValue()));
        hashMap.put("Content", Integer.valueOf(content.getValue()));
        hashMap.put("Action", Integer.valueOf(action.getValue()));
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, str);
        hashMap.put(DeviceCategoryFragment.DEVICE_TYPE, str2);
        hashMap.put("HiLinkDeviceId", str3);
        hashMap.put("Page", page.getValue());
        hashMap.put(com.huawei.hianalytics.core.storage.Event.TAG, Integer.valueOf(event.getValue()));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.DEVICE_POP_TIP_EVENT_VALUE.value(), hashMap, 0);
    }
}
