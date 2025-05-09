package com.huawei.pluginmessagecenter.provider.data;

import com.huawei.health.messagecenter.model.MessageObject;

/* loaded from: classes8.dex */
public class MessageCenterList {
    private String mDate;
    private MessageObject mMessageObject;
    private String mModule;
    private int mUnread;

    public String getModule() {
        return this.mModule;
    }

    public void setModule(String str) {
        this.mModule = str;
    }

    public int getUnread() {
        return this.mUnread;
    }

    public void setUnread(int i) {
        this.mUnread = i;
    }

    public MessageObject getMessageObject() {
        return this.mMessageObject;
    }

    public void setMessageObject(MessageObject messageObject) {
        this.mMessageObject = messageObject;
    }

    public String getDate() {
        return this.mDate;
    }

    public void setDate(String str) {
        this.mDate = str;
    }
}
