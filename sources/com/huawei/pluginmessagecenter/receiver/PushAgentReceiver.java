package com.huawei.pluginmessagecenter.receiver;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.R;
import com.huawei.health.messagecenter.model.GroupInvatationNotify;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.login.ui.login.util.SharedPreferenceUtil;
import com.huawei.pluginmessagecenter.manager.MCNotificationManager;
import defpackage.mqw;
import defpackage.mrq;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class PushAgentReceiver {
    private static final String GROUP_INVATATION = "GroupInvatationNotify";
    private static final String TAG = "PushAgentReceiver";
    private Context mContext;
    private mqw mPluginMessageCenter;

    public static void onToken(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "context = null or pushToken is empty");
        } else {
            SharedPreferenceManager.e(context, "push_token_caching", "push_token", str, (StorageParams) null);
        }
    }

    public void onPushMessage(Context context, String str) {
        LogUtil.a(TAG, "onPushMessage() enter");
        this.mContext = context;
        if (this.mPluginMessageCenter == null) {
            this.mPluginMessageCenter = mqw.b(context);
        }
        if (str == null) {
            LogUtil.h(TAG, "message = null");
            return;
        }
        try {
            String string = new JSONObject(str).getString(GROUP_INVATATION);
            if (string == null) {
                LogUtil.h(TAG, "result = null");
            } else {
                generateNpsMessage((GroupInvatationNotify) new Gson().fromJson(string, GroupInvatationNotify.class));
            }
        } catch (JsonSyntaxException | JSONException unused) {
            LogUtil.b(TAG, "onPushMessage JsonSyntaxException");
        }
    }

    private void generateNpsMessage(GroupInvatationNotify groupInvatationNotify) {
        LogUtil.a(TAG, "enter generateNpsMessage");
        final int translationType = translationType(groupInvatationNotify);
        if (translationType <= 0) {
            LogUtil.a(TAG, "message not format");
            return;
        }
        final int type = groupInvatationNotify.getType();
        final String nickname = groupInvatationNotify.getNickname();
        final String groupName = groupInvatationNotify.getGroupName();
        long userId = groupInvatationNotify.getUserId();
        final long groupId = groupInvatationNotify.getGroupId();
        mqw mqwVar = this.mPluginMessageCenter;
        String valueOf = String.valueOf(51);
        final String valueOf2 = String.valueOf(userId);
        mqwVar.c(valueOf, MessageConstant.GROUP_MEDAL_TYPE, new IBaseResponseCallback() { // from class: com.huawei.pluginmessagecenter.receiver.PushAgentReceiver.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0 && obj != null && (obj instanceof String)) {
                    MessageObject messageObject = new MessageObject();
                    messageObject.setMsgId((String) obj);
                    messageObject.setModule(String.valueOf(translationType));
                    messageObject.setType(MessageConstant.GROUP_MEDAL_TYPE);
                    messageObject.setMsgType(2);
                    messageObject.setPosition(3);
                    messageObject.setMsgPosition(11);
                    messageObject.setHuid(SharedPreferenceUtil.getInstance(PushAgentReceiver.this.mContext).getUserID());
                    messageObject.setMsgContent(PushAgentReceiver.this.getContentByType(type, nickname, groupName, valueOf2));
                    messageObject.setMsgTitle(PushAgentReceiver.this.mContext.getString(mrq.b(type)));
                    messageObject.setExpireTime(0L);
                    messageObject.setReadFlag(0);
                    messageObject.setCreateTime(System.currentTimeMillis());
                    messageObject.setDetailUri("messagecenter://group?type=" + type + "&groupId=" + String.valueOf(groupId));
                    PushAgentReceiver.this.mPluginMessageCenter.c(messageObject);
                    if (type == 9) {
                        messageObject.setMsgTitle(String.format(Locale.ENGLISH, PushAgentReceiver.this.mContext.getResources().getString(R.string._2130847849_res_0x7f022869), groupName));
                    }
                    PushAgentReceiver.showNotificationMessage(messageObject);
                    return;
                }
                LogUtil.a(PushAgentReceiver.TAG, "requestMessageId fail");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void showNotificationMessage(MessageObject messageObject) {
        MCNotificationManager.showNotification(BaseApplication.getContext(), messageObject);
    }

    private int translationType(GroupInvatationNotify groupInvatationNotify) {
        if (!LoginInit.getInstance(this.mContext).getIsLogined() || groupInvatationNotify == null) {
            LogUtil.a(TAG, "not Login or groupInvatationNotify = null");
            return 0;
        }
        int type = groupInvatationNotify.getType();
        LogUtil.a(TAG, "type:", Integer.valueOf(type));
        if (type == 9) {
            return 60;
        }
        switch (type) {
            case 19:
                long userId = groupInvatationNotify.getUserId();
                if (!String.valueOf(userId).equals(SharedPreferenceUtil.getInstance(this.mContext).getUserID())) {
                }
                break;
        }
        return 0;
    }

    public String getContentByType(int i, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            str = mrq.c(str3, 3, 2);
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        if (i == 9) {
            return this.mContext.getString(R.string._2130849854_res_0x7f02303e);
        }
        switch (i) {
            case 19:
                return String.format(Locale.ENGLISH, this.mContext.getResources().getString(R.string._2130847850_res_0x7f02286a), str, str2);
            case 20:
                return String.format(Locale.ENGLISH, this.mContext.getResources().getString(R.string._2130847851_res_0x7f02286b), str, str2);
            case 21:
                return String.format(Locale.ENGLISH, this.mContext.getResources().getString(R.string._2130847852_res_0x7f02286c), str, str2);
            case 22:
                return String.format(Locale.ENGLISH, this.mContext.getResources().getString(R.string._2130849856_res_0x7f023040), str, str2);
            default:
                return "";
        }
    }
}
