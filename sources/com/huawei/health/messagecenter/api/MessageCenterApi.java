package com.huawei.health.messagecenter.api;

import android.content.Context;
import com.huawei.health.h5pro.jsbridge.base.JsModuleBase;
import com.huawei.health.messagecenter.model.IpushBase;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.pluginmessagecenter.service.MessageObserver;
import com.huawei.pluginmessagecenter.util.HttpResCallback;
import java.util.List;

/* loaded from: classes.dex */
public interface MessageCenterApi {
    void cancelNotification(int i);

    boolean deleteMessage(String str);

    void deleteMessages(String str, String str2, IBaseResponseCallback iBaseResponseCallback);

    void deleteToken();

    void doMessagesPostReq(Context context, String str, String str2, HttpResCallback httpResCallback);

    Class<? extends JsModuleBase> getCommonJsModule(String str);

    int getMessageCenterUnreadCount();

    String getMessageId(String str, String str2);

    void getMessageId(String str, String str2, IBaseResponseCallback iBaseResponseCallback);

    List<MessageObject> getMessages(int i);

    List<MessageObject> getMessages(String str);

    List<MessageObject> getMessages(String str, String str2);

    void getMessages(String str, String str2, IBaseResponseCallback iBaseResponseCallback);

    void initHms();

    boolean insertMessage(MessageObject messageObject);

    boolean insertMessages(List<MessageObject> list);

    boolean isSystemBarPushSwitchOn();

    void refreshMessages();

    void registerMessageHandler(IpushBase ipushBase);

    void registerMessageObserver(MessageObserver messageObserver);

    void saveToken(String str);

    boolean setMessageInvisible(String str);

    boolean setMessageRead(String str);

    void showNotification(Context context, MessageObject messageObject);

    void unregisterMessageObserver(MessageObserver messageObserver);

    void updateMessageNotification(Context context, MessageObject messageObject, long j);
}
