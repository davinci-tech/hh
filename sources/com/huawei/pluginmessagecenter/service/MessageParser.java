package com.huawei.pluginmessagecenter.service;

import android.content.Context;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.R;
import com.huawei.health.messagecenter.model.Group;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.health.userlabelmgr.api.UserLabelServiceApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginmessagecenter.manager.MCNotificationManager;
import com.huawei.pluginmessagecenter.provider.data.CloudMessageObject;
import defpackage.koq;
import defpackage.mqw;
import defpackage.mrk;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class MessageParser {
    private static final int DEFAULT_LIST_SIZE = 5;
    private static final int DEFAULT_POSITION = 0;
    private static final int MESSAGE_CENTER_LIST_POSITION = 11;
    private static final int MSG_TYPE_MSG_POSITION_SPORT_BANNER_PAGE = 42;
    private static final int NO_SHOW_MESSAGE = 2;
    private static final int PUSH_MESSAGE_POSITION = 3;
    private static final int SHOW_MESSAGE = 1;
    private static final String TAG = "MessageParser";

    private MessageParser() {
    }

    public static List<MessageObject> parseMessageArray(Context context, String str, String str2) {
        ArrayList arrayList = new ArrayList(16);
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        try {
            CloudMessageObject[] cloudMessageObjectArr = (CloudMessageObject[]) new Gson().fromJson(CommonUtil.z(str), CloudMessageObject[].class);
            return cloudMessageObjectArr != null ? parseCloudMessageObject(cloudMessageObjectArr, context, str2) : arrayList;
        } catch (JsonSyntaxException e) {
            LogUtil.b(TAG, "JsonSyntaxException:", e.getMessage());
            return arrayList;
        }
    }

    private static List<MessageObject> parseCloudMessageObject(CloudMessageObject[] cloudMessageObjectArr, Context context, String str) {
        ArrayList arrayList = new ArrayList(16);
        List<String> allLabels = getAllLabels(context);
        for (CloudMessageObject cloudMessageObject : cloudMessageObjectArr) {
            if (cloudMessageObject != null) {
                MessageObject messageObject = new MessageObject();
                messageObject.setMsgId(cloudMessageObject.getMsgId());
                messageObject.setMsgType(cloudMessageObject.getMsgType());
                messageObject.setMsgTitle(cloudMessageObject.getMsgTitle());
                messageObject.setExpireTime(mrk.b(cloudMessageObject.getExpireTime()));
                messageObject.setMsgContent(cloudMessageObject.getMsgContext());
                messageObject.setFlag(cloudMessageObject.getFlag());
                messageObject.setWeight(cloudMessageObject.getWeight());
                messageObject.setImgUri(cloudMessageObject.getImgUri());
                messageObject.setImgBigUri(cloudMessageObject.getImgBigUri());
                messageObject.setDetailUri(cloudMessageObject.getDetailUri());
                messageObject.setMsgFrom(cloudMessageObject.getFrom());
                messageObject.setCreateTime(cloudMessageObject.getCreateTime());
                messageObject.setPosition(getPosition(cloudMessageObject.getPosition()));
                messageObject.setImei(cloudMessageObject.getMsgDevice());
                messageObject.setMsgPosition(cloudMessageObject.getMsgPosition());
                messageObject.setInfoClassify(cloudMessageObject.getInfoClassify());
                messageObject.setHeatMapCity(cloudMessageObject.getHeatMapCity());
                messageObject.setInfoRecommend(cloudMessageObject.getInfoRecommend());
                messageObject.setMsgUserLabel(cloudMessageObject.getMsgUserLabel());
                messageObject.setModule(String.valueOf(cloudMessageObject.getMsgMaterial()));
                messageObject.setLayout(cloudMessageObject.getLayout());
                messageObject.setMessageExtList(cloudMessageObject.getMessageExtList());
                messageObject.setPageType(cloudMessageObject.getPageType());
                messageObject.setImageTextSeparateSwitch(cloudMessageObject.getImageTextSeparateSwitch());
                messageObject.setHarmonyImgUrl(cloudMessageObject.getHarmonyImgUrl());
                messageObject.setHarmonyImageSize(cloudMessageObject.getHmImgSize());
                messageObject.setLatestGetMsgTimestamp(str);
                if (cloudMessageObject.getIsShowToNotice() == 2) {
                    messageObject.setNotified(1);
                }
                String normalize = Normalizer.normalize(cloudMessageObject.getMsgId(), Normalizer.Form.NFKC);
                if (normalize != null && normalize.startsWith(ExifInterface.LATITUDE_SOUTH)) {
                    messageObject.setHuid(LoginInit.getInstance(context).getAccountInfo(1011));
                }
                MessageObject dealGroupMessage = dealGroupMessage(messageObject);
                isNeedShowNotificationMessage(cloudMessageObject, dealGroupMessage, allLabels, context);
                arrayList.add(dealGroupMessage);
            }
        }
        return arrayList;
    }

    private static MessageObject dealGroupMessage(MessageObject messageObject) {
        int i;
        Group group;
        try {
            i = Integer.parseInt(messageObject.getModule());
        } catch (NumberFormatException unused) {
            LogUtil.b(TAG, "NumberFormatException");
            i = 0;
        }
        if (i == 51 || i == 52) {
            String msgContent = messageObject.getMsgContent();
            if (TextUtils.isEmpty(msgContent)) {
                LogUtil.h(TAG, "messageContent is empty");
                return messageObject;
            }
            try {
                group = (Group) new Gson().fromJson(msgContent, Group.class);
            } catch (JsonSyntaxException unused2) {
                LogUtil.b(TAG, "onPushMessage JsonSyntaxException");
                group = null;
            }
            if (group == null) {
                LogUtil.h(TAG, "group = null");
                return messageObject;
            }
            setMessageObjectInfo(messageObject, i, group);
        }
        return messageObject;
    }

    private static void setMessageObjectInfo(MessageObject messageObject, int i, Group group) {
        String str;
        String activityId = group.getActivityId();
        String activityName = group.getActivityName();
        String groupId = group.getGroupId();
        String groupName = group.getGroupName();
        if (i == 51) {
            messageObject.setMsgContent(String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130849853_res_0x7f02303d), groupName, activityName));
            messageObject.setDetailUriExt(String.valueOf(51));
            messageObject.setMsgTitle(BaseApplication.getContext().getString(R.string._2130849912_res_0x7f023078));
            str = "messagecenter://group?type=51&groupId=" + String.valueOf(groupId) + "&activityId=" + activityId;
        } else {
            messageObject.setMsgContent(String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130849855_res_0x7f02303f), groupName));
            messageObject.setDetailUriExt(String.valueOf(52));
            messageObject.setMsgTitle(BaseApplication.getContext().getString(R.string._2130849913_res_0x7f023079));
            str = "messagecenter://group?type=52&groupId=" + String.valueOf(groupId);
        }
        messageObject.setDetailUri(str);
        messageObject.setModule(String.valueOf(51));
        messageObject.setMsgPosition(11);
    }

    private static void isNeedShowNotificationMessage(CloudMessageObject cloudMessageObject, MessageObject messageObject, List<String> list, Context context) {
        if (cloudMessageObject.getIsShowToNotice() == 1 && mqw.e(messageObject, list)) {
            try {
                if (Integer.parseInt(messageObject.getModule()) <= 14 && !com.huawei.health.messagecenter.model.CommonUtil.isRecommendSwitchOpen(context)) {
                    LogUtil.h(TAG, "RECOMMENDATION_MESSAGE not allow recommaned");
                } else if (messageObject.getMsgPosition() == 42 && !com.huawei.health.messagecenter.model.CommonUtil.isRecommendSwitchOpen(context)) {
                    LogUtil.h(TAG, "MSG_TYPE_MSG_POSITION_SPORT_BANNER_PAGE not allow recommaned");
                } else {
                    showNotificationMessage(messageObject);
                }
            } catch (NumberFormatException unused) {
                LogUtil.b(TAG, "NumberFormatException");
            }
        }
    }

    private static List<String> getAllLabels(Context context) {
        ArrayList arrayList = new ArrayList(5);
        List<String> allLabels = ((UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class)).getAllLabels(LoginInit.getInstance(context).getAccountInfo(1011));
        return koq.e((Object) allLabels, String.class) ? allLabels : arrayList;
    }

    private static void showNotificationMessage(MessageObject messageObject) {
        messageObject.setPosition(3);
        MCNotificationManager.showNotification(BaseApplication.getContext(), messageObject);
    }

    public static List<String> parseRevokeIdArray(String str) {
        LogUtil.c(TAG, "parseRevokeIdArray json = ", str);
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        try {
            return (List) new Gson().fromJson(CommonUtil.z(str), new TypeToken<List<String>>() { // from class: com.huawei.pluginmessagecenter.service.MessageParser.1
            }.getType());
        } catch (JsonSyntaxException e) {
            LogUtil.b(TAG, "JsonSyntaxException:", e.getMessage());
            return arrayList;
        }
    }

    private static int getPosition(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            LogUtil.b(TAG, "getPosition() NumberFormatException");
            return 0;
        }
    }
}
