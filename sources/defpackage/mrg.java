package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.messagecenter.model.MessageExt;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class mrg {
    public static MessageObject cog_(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        MessageObject messageObject = new MessageObject();
        messageObject.setMsgId(col_(cursor, "msgId"));
        messageObject.setMsgType(coi_(cursor, "msgType"));
        messageObject.setModule(col_(cursor, "module"));
        messageObject.setType(col_(cursor, "type"));
        messageObject.setMetadata(col_(cursor, "metadata"));
        messageObject.setFlag(coi_(cursor, "flag"));
        messageObject.setWeight(coi_(cursor, "weight"));
        messageObject.setReadFlag(coi_(cursor, CommonUtil.READ_FLAG));
        messageObject.setMsgTitle(col_(cursor, CommonUtil.MSG_TITLE));
        messageObject.setMsgContent(col_(cursor, CommonUtil.MSG_CONTENT));
        messageObject.setCreateTime(coj_(cursor, "createTime"));
        messageObject.setExpireTime(coj_(cursor, "expireTime"));
        messageObject.setReceiveTime(coj_(cursor, CommonUtil.RECEIVE_TIME));
        messageObject.setImgUri(col_(cursor, CommonUtil.IMG_URI));
        messageObject.setImgBigUri(col_(cursor, CommonUtil.IMG_BIG_URI));
        messageObject.setDetailUri(col_(cursor, CommonUtil.DETAIL_URI));
        messageObject.setDetailUriExt(col_(cursor, CommonUtil.DETAIL_URI_EXTERNAL));
        messageObject.setMsgFrom(col_(cursor, CommonUtil.MSG_FROM));
        messageObject.setPosition(coi_(cursor, "position"));
        messageObject.setMsgPosition(coi_(cursor, CommonUtil.MSG_POSITION));
        messageObject.setHuid(col_(cursor, "huid"));
        messageObject.setImei(col_(cursor, CommonUtil.IMEI));
        messageObject.setNotified(coi_(cursor, CommonUtil.MSG_NOTIFY));
        messageObject.setInfoClassify(col_(cursor, CommonUtil.INFO_CLASSIFY));
        messageObject.setHeatMapCity(col_(cursor, CommonUtil.HEAT_MAP_CITY));
        messageObject.setInfoRecommend(coi_(cursor, CommonUtil.INFO_RECOMMEND));
        messageObject.setMsgUserLabel(col_(cursor, CommonUtil.MSG_USER_LABEL));
        return messageObject;
    }

    public static boolean d(MessageObject messageObject) {
        if (messageObject == null) {
            return true;
        }
        long b = mrk.b(System.currentTimeMillis());
        long expireTime = messageObject.getExpireTime();
        if (expireTime == 0 || expireTime == 1) {
            LogUtil.a("UIDV_MessageUtils", "expiredTime: ", Long.valueOf(expireTime));
            return false;
        }
        boolean z = expireTime < b;
        LogUtil.a("UIDV_MessageUtils", "isExpired:", Boolean.valueOf(z), " expireTime:", Long.valueOf(expireTime));
        return z;
    }

    public static boolean b(MessageObject messageObject) {
        if (messageObject == null) {
            return false;
        }
        int u = jec.u(jec.f(jec.e(messageObject.getCreateTime())));
        int d = jec.d();
        LogUtil.a("UIDV_MessageUtils", "createYear: ", Integer.valueOf(u), " nowYear: ", Integer.valueOf(d));
        return u == d;
    }

    public static MessageObject cof_(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        MessageObject messageObject = new MessageObject();
        messageObject.setMsgId(col_(cursor, "msgId"));
        messageObject.setMsgType(coi_(cursor, "msgType"));
        messageObject.setModule(col_(cursor, "module"));
        messageObject.setType(col_(cursor, "type"));
        messageObject.setMetadata(col_(cursor, "metadata"));
        messageObject.setFlag(coi_(cursor, "flag"));
        messageObject.setWeight(coi_(cursor, "weight"));
        messageObject.setReadFlag(coi_(cursor, CommonUtil.READ_FLAG));
        messageObject.setMsgTitle(col_(cursor, CommonUtil.MSG_TITLE));
        messageObject.setMsgContent(col_(cursor, CommonUtil.MSG_CONTENT));
        messageObject.setCreateTime(coj_(cursor, "createTime"));
        messageObject.setExpireTime(coj_(cursor, "expireTime"));
        messageObject.setReceiveTime(coj_(cursor, CommonUtil.RECEIVE_TIME));
        messageObject.setImgUri(col_(cursor, CommonUtil.IMG_URI));
        messageObject.setImgBigUri(col_(cursor, CommonUtil.IMG_BIG_URI));
        messageObject.setDetailUri(col_(cursor, CommonUtil.DETAIL_URI));
        messageObject.setDetailUriExt(col_(cursor, CommonUtil.DETAIL_URI_EXTERNAL));
        messageObject.setMsgFrom(col_(cursor, CommonUtil.MSG_FROM));
        messageObject.setPosition(coi_(cursor, "position"));
        messageObject.setMsgPosition(coi_(cursor, CommonUtil.MSG_POSITION));
        messageObject.setHuid(col_(cursor, "huid"));
        messageObject.setImei(col_(cursor, CommonUtil.IMEI));
        messageObject.setNotified(coi_(cursor, CommonUtil.MSG_NOTIFY));
        messageObject.setInfoClassify(col_(cursor, CommonUtil.INFO_CLASSIFY));
        messageObject.setHeatMapCity(col_(cursor, CommonUtil.HEAT_MAP_CITY));
        messageObject.setInfoRecommend(coi_(cursor, CommonUtil.INFO_RECOMMEND));
        messageObject.setMsgUserLabel(col_(cursor, CommonUtil.MSG_USER_LABEL));
        messageObject.setLayout(coi_(cursor, CommonUtil.LAYOUT));
        messageObject.setMessageExtList(d(col_(cursor, CommonUtil.MESSAGE_EXTERNAL_LIST)));
        messageObject.setPageType(coi_(cursor, CommonUtil.PAGE_TYPE));
        messageObject.setImageTextSeparateSwitch(coi_(cursor, CommonUtil.IMAGE_TEXT_SEPARATE_SWITCH));
        messageObject.setHarmonyImgUrl(col_(cursor, CommonUtil.HARMONY_IMAGE_URL));
        messageObject.setHarmonyImageSize(col_(cursor, CommonUtil.HARMONY_IMAGE_SIZE));
        messageObject.setLatestGetMsgTimestamp(col_(cursor, CommonUtil.RESERVE_PARAM_ONE));
        return messageObject;
    }

    private static List<MessageExt> d(String str) {
        Gson gson = new Gson();
        ArrayList arrayList = new ArrayList(16);
        try {
            return !TextUtils.isEmpty(str) ? (List) gson.fromJson(str, new TypeToken<List<MessageExt>>() { // from class: mrg.2
            }.getType()) : arrayList;
        } catch (JsonSyntaxException unused) {
            LogUtil.b("UIDV_MessageUtils", "getMessageExtList JsonSyntaxException fromJson fail");
            return arrayList;
        }
    }

    private static String col_(Cursor cursor, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex != -1) {
            return cursor.getString(columnIndex);
        }
        LogUtil.b("UIDV_MessageUtils", "getStringColumn wrong columnName = ", str);
        return "";
    }

    private static int coi_(Cursor cursor, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex != -1) {
            return cursor.getInt(columnIndex);
        }
        LogUtil.b("UIDV_MessageUtils", "getIntColumn wrong columnName = ", str);
        return 0;
    }

    private static long coj_(Cursor cursor, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex != -1) {
            return cursor.getLong(columnIndex);
        }
        LogUtil.b("UIDV_MessageUtils", "getLongColumn wrong columnName = ", str);
        return 0L;
    }

    public static boolean c(MessageObject messageObject) {
        if (messageObject == null) {
            return false;
        }
        if (TextUtils.isEmpty(messageObject.getMsgId())) {
            LogUtil.a("UIDV_MessageUtils", "Message ID can't be null");
            return false;
        }
        if (TextUtils.isEmpty(messageObject.getMsgTitle())) {
            LogUtil.a("UIDV_MessageUtils", "Message title can't be null");
            return false;
        }
        if (messageObject.getCreateTime() < 0) {
            LogUtil.a("UIDV_MessageUtils", "Message create time less than 0");
            return false;
        }
        if (messageObject.getPosition() != 0) {
            return true;
        }
        LogUtil.a("UIDV_MessageUtils", "Message position is invalid");
        return false;
    }

    public static boolean a(MessageObject messageObject) {
        if (messageObject == null) {
            return false;
        }
        return messageObject.getMsgPosition() == 12 || messageObject.getMsgPosition() == 24 || messageObject.getMsgPosition() == 25 || messageObject.getMsgPosition() == 27 || messageObject.getMsgPosition() == 28 || messageObject.getMsgPosition() == 41;
    }

    public static ContentValues cok_(String str, String str2, String str3) {
        MessageObject messageObject = new MessageObject();
        ContentValues contentValues = new ContentValues();
        contentValues.put("huid", str);
        contentValues.put("module", str2);
        contentValues.put("type", str3);
        contentValues.put("msgId", messageObject.getMsgId());
        contentValues.put("msgType", Integer.valueOf(messageObject.getMsgType()));
        contentValues.put("flag", Integer.valueOf(messageObject.getFlag()));
        contentValues.put("weight", Integer.valueOf(messageObject.getWeight()));
        contentValues.put(CommonUtil.READ_FLAG, Integer.valueOf(messageObject.getReadFlag()));
        contentValues.put(CommonUtil.MSG_TITLE, messageObject.getMsgTitle());
        contentValues.put("createTime", Long.valueOf(messageObject.getCreateTime()));
        contentValues.put(CommonUtil.RECEIVE_TIME, Long.valueOf(System.currentTimeMillis()));
        contentValues.put("expireTime", Long.valueOf(messageObject.getExpireTime()));
        contentValues.put(CommonUtil.DETAIL_URI, messageObject.getDetailUri());
        contentValues.put("position", Integer.valueOf(messageObject.getPosition()));
        contentValues.put(CommonUtil.MSG_POSITION, Integer.valueOf(messageObject.getMsgPosition()));
        contentValues.put(CommonUtil.MSG_NOTIFY, (Integer) 0);
        return contentValues;
    }

    public static ContentValues coh_(MessageObject messageObject) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("metadata", messageObject.getMetadata());
        contentValues.put("msgType", Integer.valueOf(messageObject.getMsgType()));
        contentValues.put("flag", Integer.valueOf(messageObject.getFlag()));
        contentValues.put("weight", Integer.valueOf(messageObject.getWeight()));
        contentValues.put(CommonUtil.READ_FLAG, Integer.valueOf(messageObject.getReadFlag()));
        contentValues.put(CommonUtil.MSG_TITLE, messageObject.getMsgTitle());
        contentValues.put(CommonUtil.MSG_CONTENT, messageObject.getMsgContent());
        contentValues.put("createTime", Long.valueOf(messageObject.getCreateTime()));
        contentValues.put(CommonUtil.RECEIVE_TIME, Long.valueOf(System.currentTimeMillis()));
        contentValues.put("expireTime", Long.valueOf(messageObject.getExpireTime()));
        contentValues.put(CommonUtil.IMG_URI, messageObject.getImgUri());
        contentValues.put(CommonUtil.IMG_BIG_URI, messageObject.getImgBigUri());
        contentValues.put(CommonUtil.DETAIL_URI, messageObject.getDetailUri());
        contentValues.put(CommonUtil.DETAIL_URI_EXTERNAL, messageObject.getDetailUriExt());
        contentValues.put(CommonUtil.MSG_FROM, messageObject.getMsgFrom());
        contentValues.put("position", Integer.valueOf(messageObject.getPosition()));
        contentValues.put(CommonUtil.MSG_POSITION, Integer.valueOf(messageObject.getMsgPosition()));
        contentValues.put("huid", messageObject.getHuid());
        contentValues.put(CommonUtil.IMEI, messageObject.getImei());
        contentValues.put(CommonUtil.MSG_NOTIFY, Integer.valueOf(messageObject.getNotified()));
        contentValues.put(CommonUtil.INFO_CLASSIFY, messageObject.getInfoClassify());
        contentValues.put(CommonUtil.HEAT_MAP_CITY, messageObject.getHeatMapCity());
        contentValues.put(CommonUtil.INFO_RECOMMEND, Integer.valueOf(messageObject.getInfoRecommend()));
        contentValues.put(CommonUtil.MSG_USER_LABEL, messageObject.getMsgUserLabel());
        contentValues.put(CommonUtil.LAYOUT, Integer.valueOf(messageObject.getLayout()));
        contentValues.put(CommonUtil.PAGE_TYPE, Integer.valueOf(messageObject.getPageType()));
        contentValues.put(CommonUtil.IMAGE_TEXT_SEPARATE_SWITCH, Integer.valueOf(messageObject.getImageTextSeparateSwitch()));
        contentValues.put(CommonUtil.HARMONY_IMAGE_SIZE, messageObject.getHarmonyImageSize());
        contentValues.put(CommonUtil.HARMONY_IMAGE_URL, messageObject.getHarmonyImgUrl());
        if (!TextUtils.isEmpty(messageObject.getLatestGetMsgTimestamp())) {
            contentValues.put(CommonUtil.RESERVE_PARAM_ONE, messageObject.getLatestGetMsgTimestamp());
        }
        List<MessageExt> messageExtList = messageObject.getMessageExtList();
        if (messageExtList != null) {
            contentValues.put(CommonUtil.MESSAGE_EXTERNAL_LIST, new Gson().toJson(messageExtList));
        }
        return contentValues;
    }

    public static String d(Object obj) {
        return (obj == null || Constants.NULL.equals(obj.toString())) ? "" : obj.toString().trim();
    }
}
