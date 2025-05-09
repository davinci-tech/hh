package com.huawei.hwsmartinteractmgr.data.msgcontent;

/* loaded from: classes5.dex */
public class NotificationMsgContent {
    public static final String MSG_TYPE_HISTORY_BEST_MSG = "historyBestMessage";
    public static final String MSG_TYPE_KAKA = "kakaMessage";
    public static final String MSG_TYPE_SPORT_REPORT = "sportReport";
    private String content;
    private String notificationId;
    private String type;
    private String url;

    public String getNotificationId() {
        return this.notificationId;
    }

    public void setNotificationId(String str) {
        this.notificationId = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }
}
