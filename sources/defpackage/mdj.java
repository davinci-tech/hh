package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.AchieveMessage;
import com.huawei.pluginachievement.manager.model.EventRecord;
import com.huawei.pluginachievement.manager.model.MedalBasic;
import com.huawei.pluginachievement.manager.model.MedalConfigInfo;
import com.huawei.pluginachievement.manager.model.MedalLocation;
import com.huawei.pluginachievement.manager.model.MessageReminder;
import com.huawei.pluginachievement.manager.model.MultiLanguageRes;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
class mdj {
    static UserAchieveWrapper b(String str) {
        try {
            String optString = new JSONObject(str).optString("resultCode");
            LogUtil.a("PLGACHIEVE_AchieveMedalParser", "parseTakeMedal resultCode:", optString);
            if (!mdn.e(optString)) {
                UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(7);
                userAchieveWrapper.setResultCode(optString);
                return userAchieveWrapper;
            }
            return new UserAchieveWrapper(7);
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchieveMedalParser", "parseTakeMedal Exception:", e.getMessage());
            return new UserAchieveWrapper(7);
        }
    }

    static UserAchieveWrapper d(String str) {
        try {
            LogUtil.a("PLGACHIEVE_AchieveMedalParser", "parseMsgRemind result:", str);
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("resultCode");
            if (!mdn.e(optString)) {
                UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(4);
                userAchieveWrapper.setResultCode(optString);
                return userAchieveWrapper;
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("messages");
            if (optJSONArray == null) {
                return new UserAchieveWrapper(4);
            }
            ArrayList arrayList = new ArrayList(16);
            for (int i = 0; i < optJSONArray.length(); i++) {
                Object opt = optJSONArray.opt(i);
                if (!(opt instanceof JSONObject)) {
                    LogUtil.h("PLGACHIEVE_AchieveMedalParser", "parseMsgRemind not JSONObject ");
                } else {
                    arrayList.add(d((JSONObject) opt));
                }
            }
            LogUtil.a("PLGACHIEVE_AchieveMedalParser", "parseMsgRemind messages:", arrayList);
            MessageReminder messageReminder = new MessageReminder(arrayList);
            UserAchieveWrapper userAchieveWrapper2 = new UserAchieveWrapper(4);
            userAchieveWrapper2.setMsgReminder(messageReminder);
            return userAchieveWrapper2;
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchieveMedalParser", "parseMsgRemind Exception:", e.getMessage());
            return new UserAchieveWrapper(4);
        }
    }

    private static AchieveMessage d(JSONObject jSONObject) {
        AchieveMessage achieveMessage = new AchieveMessage();
        int optInt = jSONObject.optInt("msgType");
        achieveMessage.setMsgType(optInt);
        if (optInt == 2) {
            achieveMessage.saveMedalType(jSONObject.optString(ParsedFieldTag.MEDAL_TYPE));
            achieveMessage.saveGainCount(mdn.e(ParsedFieldTag.GAIN_COUNT, jSONObject));
        } else if (optInt == 4) {
            c(jSONObject);
        } else if (optInt == 3) {
            LogUtil.a("PLGACHIEVE_AchieveMedalParser", "new level upgrade，level = ", achieveMessage.getContent());
            mct.b(BaseApplication.getContext(), "levelTypeUpgraded", "" + System.currentTimeMillis());
        } else {
            LogUtil.a("PLGACHIEVE_AchieveMedalParser", "parseMsgRemind msgType = ", Integer.valueOf(optInt));
        }
        achieveMessage.saveMedalLevel(jSONObject.optInt("level"));
        achieveMessage.saveMedalId(mdn.b(ParsedFieldTag.MEDAL_ID, jSONObject));
        achieveMessage.setGainTime(mdn.b(ParsedFieldTag.TAKE_DATE, jSONObject));
        String b = mdn.b(ParsedFieldTag.LAST_TAKE_DATE, jSONObject);
        String b2 = mdn.b(ParsedFieldTag.MEDAL_TYPE, jSONObject);
        if (!TextUtils.isEmpty(b) && mlb.n(b2)) {
            achieveMessage.setGainTime(b);
        }
        achieveMessage.setContent(mdn.b("content", jSONObject));
        return achieveMessage;
    }

    static UserAchieveWrapper c(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("resultCode");
            if (!mdn.e(optString)) {
                UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(6);
                userAchieveWrapper.setResultCode(optString);
                return userAchieveWrapper;
            }
            UserAchieveWrapper userAchieveWrapper2 = new UserAchieveWrapper(6);
            MultiLanguageRes multiLanguageRes = new MultiLanguageRes(jSONObject.optString(ParsedFieldTag.LANGUAGE_VERSION), jSONObject.optString("url"));
            userAchieveWrapper2.setLanguageRes(multiLanguageRes);
            LogUtil.c("PLGACHIEVE_AchieveMedalParser", "parseLanguageResUrl languageRes:", multiLanguageRes);
            return userAchieveWrapper2;
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchieveMedalParser", "parseLanguageResUrl Exception:", e.getMessage());
            return new UserAchieveWrapper(6);
        }
    }

    static ArrayList<UserAchieveWrapper> c(String str, Context context) {
        LogUtil.a("PLGACHIEVE_AchieveMedalParser", "enter parseMedalBasic");
        ArrayList<UserAchieveWrapper> arrayList = new ArrayList<>(16);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("resultCode");
            if (!mdn.e(optString)) {
                UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(8);
                userAchieveWrapper.setResultCode(optString);
                arrayList.add(userAchieveWrapper);
                return arrayList;
            }
            JSONArray jSONArray = jSONObject.getJSONArray(ParsedFieldTag.MEDAL_INFO);
            if (jSONArray == null) {
                arrayList.add(new UserAchieveWrapper(8));
                return arrayList;
            }
            return c(context, jSONArray);
        } catch (JSONException unused) {
            LogUtil.b("PLGACHIEVE_AchieveMedalParser", "parseMedalBasic Exception");
            return arrayList;
        }
    }

    private static void c(JSONObject jSONObject) {
        LogUtil.a("PLGACHIEVE_AchieveMedalParser", "enter insertKakaExpirationMessage.");
        if (jSONObject == null) {
            LogUtil.h("PLGACHIEVE_AchieveMedalParser", "msgObj is null.");
            return;
        }
        String optString = jSONObject.optString("content");
        if (TextUtils.isEmpty(optString)) {
            LogUtil.h("PLGACHIEVE_AchieveMedalParser", "insertKakaExpirationMessage, contentStr is null or empty.");
            return;
        }
        try {
            JSONObject jSONObject2 = new JSONObject(optString);
            String optString2 = jSONObject2.optString(ParsedFieldTag.KAKA_EXPIRATION_KAKA);
            Long valueOf = Long.valueOf(jSONObject2.optLong("updateTime"));
            try {
                int parseInt = Integer.parseInt(optString2);
                if (parseInt <= 0 || System.currentTimeMillis() > valueOf.longValue()) {
                    LogUtil.h("PLGACHIEVE_AchieveMedalParser", "expirationKaka is: ", optString2, "currentTime: ", Long.valueOf(System.currentTimeMillis()));
                    return;
                }
                String string = BaseApplication.getContext().getResources().getString(R.string._2130847680_res_0x7f0227c0, BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903212_res_0x7f0300ac, parseInt, optString2), nsj.b(valueOf.longValue()));
                MessageCenterApi messageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
                MessageObject c = c(string, valueOf.longValue());
                if (c != null) {
                    ArrayList arrayList = new ArrayList(1);
                    arrayList.add(c);
                    boolean insertMessages = messageCenterApi.insertMessages(arrayList);
                    LogUtil.a("PLGACHIEVE_AchieveMedalParser", "insertKakaExpirationMessage, insert KakaMessages to DB, success is: ", Boolean.valueOf(insertMessages));
                    if (insertMessages) {
                        messageCenterApi.showNotification(BaseApplication.getContext(), c);
                    }
                }
            } catch (NumberFormatException e) {
                LogUtil.b("PLGACHIEVE_AchieveMedalParser", "insertKakaExpirationMessage: exception -> " + e.getMessage());
            }
        } catch (JSONException e2) {
            LogUtil.b("PLGACHIEVE_AchieveMedalParser", "insertKakaExpirationMessage, contentJsonObj JSONException, e is: ", e2.getMessage());
        }
    }

    private static MessageObject c(String str, long j) {
        MessageObject messageObject = new MessageObject();
        messageObject.setPosition(3);
        messageObject.setMsgType(2);
        messageObject.setDetailUri("messagecenter://kakaMessage?module=17&type=kakaExpiration");
        messageObject.setModule(String.valueOf(17));
        messageObject.setMsgTitle(BaseApplication.getContext().getString(R.string._2130847679_res_0x7f0227bf));
        messageObject.setMsgContent(str);
        messageObject.setExpireTime(j);
        messageObject.setMsgId("17");
        messageObject.setReceiveTime(System.currentTimeMillis());
        messageObject.setReadFlag(0);
        messageObject.setType("kakaExpiration");
        return messageObject;
    }

    private static ArrayList<UserAchieveWrapper> c(Context context, JSONArray jSONArray) {
        String str;
        long j;
        String str2;
        List<mcz> list;
        StringBuilder sb;
        String str3 = "PLGACHIEVE_AchieveMedalParser";
        ArrayList<UserAchieveWrapper> arrayList = new ArrayList<>(16);
        StringBuilder sb2 = new StringBuilder(16);
        StringBuilder sb3 = new StringBuilder(16);
        HashMap<String, String> hashMap = new HashMap<>(16);
        StringBuilder sb4 = new StringBuilder(16);
        String b = mct.b(context, "activity_medal_id_shown_name");
        String b2 = mct.b(BaseApplication.getContext(), "MedalConfigTime");
        List<mcz> b3 = meh.c(context.getApplicationContext()).b(9, new HashMap(2));
        long a2 = mht.a(b2);
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                JSONObject jSONObject2 = jSONObject.getJSONObject(ParsedFieldTag.MEDAL_BASIC);
                JSONObject jSONObject3 = jSONObject.getJSONObject(ParsedFieldTag.MEDAL_RULE);
                String str4 = str3;
                if (jSONObject2 == null || jSONObject3 == null) {
                    arrayList.add(new UserAchieveWrapper(8));
                    return arrayList;
                }
                try {
                    String string = jSONObject2.getString("type");
                    int i2 = i;
                    String str5 = b;
                    StringBuilder sb5 = sb2;
                    long d = mdn.d("timestamp", jSONObject2);
                    if (d(d, a2, string)) {
                        j = a2;
                    } else {
                        String string2 = jSONObject3.getString("level");
                        j = a2;
                        String string3 = jSONObject.getString(ParsedFieldTag.MEDAL_ID);
                        if (mdq.c(string2, string, jSONObject2.getString(ParsedFieldTag.MEDAL_URL))) {
                            arrayList.add(new UserAchieveWrapper(8));
                            return arrayList;
                        }
                        if (!mlb.g(string)) {
                            a(context, string3, jSONObject2, b3);
                            sb3.append(string3);
                            sb3.append(",");
                            str2 = str5;
                            e(jSONObject3, str2, sb4);
                            String b4 = mdn.b("name", jSONObject2);
                            MedalLocation e = e(jSONObject.getJSONObject(ParsedFieldTag.MEDAL_LOCATION), string3, b4, d);
                            MedalConfigInfo e2 = e(jSONObject2, jSONObject3, string3, b4, d);
                            list = b3;
                            if (mdq.a(string3, mdn.b("endTime", jSONObject3), mdn.b(ParsedFieldTag.TAKE_EFFECT_TIME, jSONObject3))) {
                                e2.saveIsNewConfig(1);
                                sb = sb5;
                                sb.append(string3);
                                sb.append(",");
                                hashMap = mdn.c(hashMap, e.acquireFirstTabDesc(), string3);
                            } else {
                                sb = sb5;
                                e2.saveIsNewConfig(0);
                            }
                            arrayList.add(d(jSONObject, e, e2, d));
                            i = i2 + 1;
                            b = str2;
                            sb2 = sb;
                            b3 = list;
                            str3 = str4;
                            a2 = j;
                        }
                    }
                    list = b3;
                    sb = sb5;
                    str2 = str5;
                    i = i2 + 1;
                    b = str2;
                    sb2 = sb;
                    b3 = list;
                    str3 = str4;
                    a2 = j;
                } catch (JSONException unused) {
                    str = str4;
                    LogUtil.b(str, "parseMedalBasic Exception");
                    return arrayList;
                }
            } catch (JSONException unused2) {
                str = str3;
            }
        }
        String str6 = str3;
        String str7 = b;
        StringBuilder sb6 = sb2;
        str = str6;
        try {
            LogUtil.a(str, "getUserAchieveWrapperList:", Integer.valueOf(arrayList.size()), " cloudDownMedals ", sb3.toString());
            if (arrayList.size() != 0) {
                e(context, sb6, hashMap, str7, sb4);
            }
        } catch (JSONException unused3) {
            LogUtil.b(str, "parseMedalBasic Exception");
            return arrayList;
        }
        return arrayList;
    }

    private static void e(JSONObject jSONObject, String str, StringBuilder sb) {
        int e = mdn.e("activityId", jSONObject);
        String b = mdn.b("activityName", jSONObject);
        String str2 = e + "_" + b;
        if (e == 0 || TextUtils.isEmpty(b) || str.contains(str2) || sb.toString().contains(str2)) {
            return;
        }
        sb.append(str2);
        sb.append(",");
    }

    private static boolean d(long j, long j2, String str) {
        return (j > j2 || "A5".equals(str) || mlb.n(str)) ? false : true;
    }

    private static UserAchieveWrapper d(JSONObject jSONObject, MedalLocation medalLocation, MedalConfigInfo medalConfigInfo, long j) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(ParsedFieldTag.MEDAL_BASIC);
            JSONObject jSONObject3 = jSONObject.getJSONObject(ParsedFieldTag.MEDAL_RULE);
            String string = jSONObject2.getString("type");
            String string2 = jSONObject3.getString("level");
            String string3 = jSONObject2.getString(ParsedFieldTag.MEDAL_URL);
            String string4 = jSONObject.getString(ParsedFieldTag.MEDAL_ID);
            return c(a(string3, mdn.d(string, string2), string4, j), medalLocation, medalConfigInfo, b(jSONObject3, string4, mdn.b("name", jSONObject2), j));
        } catch (JSONException unused) {
            LogUtil.b("PLGACHIEVE_AchieveMedalParser", "parseMedalBasic Exception");
            return null;
        }
    }

    private static MedalConfigInfo e(JSONObject jSONObject, JSONObject jSONObject2, String str, String str2, long j) {
        MedalConfigInfo e = e(jSONObject, jSONObject2);
        e.saveMedalID(str);
        e.saveMedalName(str2);
        e.saveTimestamp(j);
        return e;
    }

    private static void e(Context context, StringBuilder sb, HashMap<String, String> hashMap, String str, StringBuilder sb2) {
        LogUtil.a("PLGACHIEVE_AchieveMedalParser", "myMedalRedPointShow str =", sb.toString());
        mct.b(context, "medalIsException", "");
        mct.b(context, "my_medal_red_point", sb.toString());
        mdq.a(context, hashMap);
        if (TextUtils.isEmpty(sb2)) {
            return;
        }
        String str2 = str + "," + ((Object) sb2);
        LogUtil.a("PLGACHIEVE_AchieveMedalParser", "getUserAchieveWrapperList activityList :", str2);
        mct.b(context, "activity_medal_id_shown_name", str2);
    }

    private static MedalLocation e(JSONObject jSONObject, String str, String str2, long j) {
        MedalLocation a2 = a(jSONObject);
        a2.saveMedalID(str);
        a2.saveMedalName(str2);
        a2.saveTimestamp(j);
        return a2;
    }

    private static void a(Context context, String str, JSONObject jSONObject, List<mcz> list) {
        if (TextUtils.isEmpty(mct.b(context, "medalIsException"))) {
            mdq.c(str, jSONObject, list);
            mdq.a(str, context);
        }
    }

    private static MedalBasic a(String str, String str2, String str3, long j) {
        LogUtil.c("PLGACHIEVE_AchieveMedalParser", "medalName=", str2, ParsedFieldTag.MEDAL_URL, str);
        MedalBasic medalBasic = new MedalBasic();
        medalBasic.saveVeinUrl(str);
        medalBasic.saveMedalName(str2);
        medalBasic.saveMedalID(str3);
        medalBasic.saveTimestamp(j);
        return medalBasic;
    }

    private static UserAchieveWrapper c(MedalBasic medalBasic, MedalLocation medalLocation, MedalConfigInfo medalConfigInfo, EventRecord eventRecord) {
        UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(8);
        userAchieveWrapper.saveMedalTexture(medalBasic);
        userAchieveWrapper.saveMedalLocation(medalLocation);
        userAchieveWrapper.saveMedalConfigInfo(medalConfigInfo);
        userAchieveWrapper.saveEventRecord(eventRecord);
        return userAchieveWrapper;
    }

    private static EventRecord b(JSONObject jSONObject, String str, String str2, long j) {
        EventRecord c = mdn.c(jSONObject);
        if (c != null) {
            c.saveMedalID(str);
            c.saveMedalName(str2);
            c.saveTimestamp(j);
        }
        return c;
    }

    private static MedalConfigInfo e(JSONObject jSONObject, JSONObject jSONObject2) {
        MedalConfigInfo medalConfigInfo = new MedalConfigInfo();
        medalConfigInfo.saveMedalType(mdn.b("type", jSONObject));
        medalConfigInfo.saveMessage(mdn.b("message", jSONObject));
        medalConfigInfo.saveGrayDescription(mdn.b(ParsedFieldTag.GRAY_DESC, jSONObject));
        medalConfigInfo.saveGrayDetailStyle(mdn.b(ParsedFieldTag.GRAY_DETAIL_STYLE, jSONObject));
        medalConfigInfo.saveGrayPromotionName(mdn.b(ParsedFieldTag.GRAY_PRO_NAME, jSONObject));
        medalConfigInfo.saveGrayPromotionUrl(mdn.b(ParsedFieldTag.GARY_PRO_URL, jSONObject));
        medalConfigInfo.saveGrayListStyle(mdn.b(ParsedFieldTag.GRAY_LIST_STYLE, jSONObject));
        medalConfigInfo.saveLightDescription(mdn.b(ParsedFieldTag.LIGHT_DESC, jSONObject));
        medalConfigInfo.saveLightDetailStyle(mdn.b(ParsedFieldTag.LIGHT_DETAIL_STYLE, jSONObject));
        medalConfigInfo.saveLightPromotionName(mdn.b(ParsedFieldTag.LIGHT_PRO_NAME, jSONObject));
        medalConfigInfo.saveLightPromotionUrl(mdn.b(ParsedFieldTag.LIGHT_PRO_URL, jSONObject));
        medalConfigInfo.saveLightListStyle(mdn.b(ParsedFieldTag.LIGHT_LIST_STYLE, jSONObject));
        medalConfigInfo.saveShareImageUrl(mdn.b(ParsedFieldTag.SHARE_IMAGE_URL, jSONObject));
        medalConfigInfo.saveMedalLabel(mdn.e("label", jSONObject));
        medalConfigInfo.saveActionType(mdn.e(ParsedFieldTag.ACTION_TYPE, jSONObject2));
        medalConfigInfo.saveGoal(mdn.e(ParsedFieldTag.GOAL, jSONObject2));
        medalConfigInfo.saveLocation(mdn.b("location", jSONObject2));
        medalConfigInfo.saveActivityId(mdn.e("activityId", jSONObject2));
        medalConfigInfo.saveMedalLevel(mdn.e("level", jSONObject2));
        medalConfigInfo.saveMedalUnit(mdn.e("unit", jSONObject2));
        medalConfigInfo.saveStartTime(mdn.b("startTime", jSONObject2));
        medalConfigInfo.saveEndTime(mdn.b("endTime", jSONObject2));
        medalConfigInfo.saveTakeEffectTime(mdn.b(ParsedFieldTag.TAKE_EFFECT_TIME, jSONObject2));
        medalConfigInfo.saveRepeatable(mdn.e(ParsedFieldTag.REPEATABLE, jSONObject2));
        medalConfigInfo.setClientTypes(mdn.b(ParsedFieldTag.CLIENT_TYPES, jSONObject2));
        medalConfigInfo.setPhoneTypes(mdn.b(ParsedFieldTag.PHONE_TYPES, jSONObject2));
        medalConfigInfo.saveEventStatus(0);
        return medalConfigInfo;
    }

    private static MedalLocation a(JSONObject jSONObject) {
        MedalLocation medalLocation = new MedalLocation();
        medalLocation.saveMedalWeight(mdn.e(ParsedFieldTag.MEDAL_WEIGHT, jSONObject));
        medalLocation.saveFirstTabPriority(mdn.e(ParsedFieldTag.FIRST_TAB_PRIORITY, jSONObject));
        medalLocation.saveSecondTabPriority(mdn.e(ParsedFieldTag.SECOND_TAB_PRIORITY, jSONObject));
        medalLocation.saveFirstTabDesc(mdn.b(ParsedFieldTag.FIRST_TAB_DESC, jSONObject));
        medalLocation.saveSecondTabDesc(mdn.b(ParsedFieldTag.SECOND_TAB_DESC, jSONObject));
        medalLocation.saveMedalGainedTime("");
        medalLocation.saveGainedCount(0);
        return medalLocation;
    }

    static ArrayList<UserAchieveWrapper> e(String str) {
        LogUtil.a("PLGACHIEVE_AchieveMedalParser", "enter parsePersonal UserAchieveWrapper");
        ArrayList<UserAchieveWrapper> arrayList = new ArrayList<>(16);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("resultCode");
            if (!mdn.e(optString)) {
                UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(0);
                userAchieveWrapper.setResultCode(optString);
                arrayList.add(userAchieveWrapper);
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList(16);
            StringBuilder sb = new StringBuilder(16);
            JSONArray jSONArray = jSONObject.getJSONArray(ParsedFieldTag.USER_MEDALS);
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                Object opt = jSONArray.opt(i);
                if (!(opt instanceof JSONObject)) {
                    LogUtil.h("PLGACHIEVE_AchieveMedalParser", "parsePersonal: not JSONObject");
                } else {
                    arrayList.add(d((ArrayList<String>) arrayList2, sb, (JSONObject) opt));
                }
            }
            LogUtil.a("PLGACHIEVE_AchieveMedalParser", "parsePersonal threeCircleBuilder:", sb.toString());
            if (e(jSONArray.toString(), (ArrayList<String>) arrayList2)) {
                return arrayList;
            }
            ArrayList<UserAchieveWrapper> arrayList3 = new ArrayList<>(16);
            UserAchieveWrapper userAchieveWrapper2 = new UserAchieveWrapper(0);
            userAchieveWrapper2.setResultCode(optString);
            arrayList3.add(userAchieveWrapper2);
            return arrayList3;
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchieveMedalParser", "parsePersonal Exception:", e.getMessage());
            return arrayList;
        }
    }

    private static UserAchieveWrapper d(ArrayList<String> arrayList, StringBuilder sb, JSONObject jSONObject) {
        MedalLocation medalLocation = new MedalLocation();
        medalLocation.saveMedalID(mdn.b(ParsedFieldTag.MEDAL_ID, jSONObject));
        arrayList.add(mdn.b(ParsedFieldTag.MEDAL_ID, jSONObject));
        medalLocation.saveMedalGainedTime(mdn.b(ParsedFieldTag.TAKE_DATE, jSONObject));
        medalLocation.saveGainedCount(mdn.e(ParsedFieldTag.GAIN_COUNT, jSONObject));
        String b = mdn.b(ParsedFieldTag.LAST_TAKE_DATE, jSONObject);
        String b2 = mdn.b(ParsedFieldTag.MEDAL_TYPE, jSONObject);
        if (!TextUtils.isEmpty(b) && mlb.n(b2)) {
            sb.append(medalLocation.acquireMedalID());
            sb.append("_");
            sb.append(b);
            sb.append("_");
            sb.append(medalLocation.acquireGainedCount());
            sb.append(",");
            medalLocation.saveMedalGainedTime(b);
        }
        UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(0);
        LogUtil.c("PLGACHIEVE_AchieveMedalParser", "location=", medalLocation.toString());
        userAchieveWrapper.saveMedalLocation(medalLocation);
        return userAchieveWrapper;
    }

    private static boolean e(String str, ArrayList<String> arrayList) {
        String b = mct.b(BaseApplication.getContext(), "_medalGainLoadInfo");
        LogUtil.a("PLGACHIEVE_AchieveMedalParser", "isLoadChanged medalGainStr.size ", Integer.valueOf(arrayList.size()));
        if (b.equals(str) && arrayList.size() == meh.c(BaseApplication.getContext()).p().size()) {
            LogUtil.a("PLGACHIEVE_AchieveMedalParser", "isLoadChanged medalGainStr=lastGainStr,return!");
            return false;
        }
        LogUtil.a("PLGACHIEVE_AchieveMedalParser", "isLoadChanged medalGainStr=", str);
        mct.b(BaseApplication.getContext(), "_medalGainLoadInfo", str);
        return true;
    }
}
