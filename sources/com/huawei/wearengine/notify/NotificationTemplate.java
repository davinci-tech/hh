package com.huawei.wearengine.notify;

/* loaded from: classes7.dex */
public enum NotificationTemplate {
    NOTIFICATION_TEMPLATE_NO_BUTTON(50),
    NOTIFICATION_TEMPLATE_ONE_BUTTON(51),
    NOTIFICATION_TEMPLATE_TWO_BUTTONS(52),
    NOTIFICATION_TEMPLATE_THREE_BUTTONS(53);

    private int mTemplateId;

    NotificationTemplate(int i) {
        this.mTemplateId = i;
    }

    public int value() {
        return this.mTemplateId;
    }

    public static NotificationTemplate getTemplateForTemplateId(int i) {
        for (NotificationTemplate notificationTemplate : values()) {
            if (i == notificationTemplate.value()) {
                return notificationTemplate;
            }
        }
        return null;
    }
}
