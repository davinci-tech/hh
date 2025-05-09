package com.huawei.hms.push;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hms.push.utils.DateUtil;
import com.huawei.hms.push.utils.JsonUtil;
import com.huawei.hms.support.api.push.PushException;
import com.huawei.hms.support.log.HMSLog;
import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class RemoteMessage implements Parcelable {
    public static final Parcelable.Creator<RemoteMessage> CREATOR;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_NORMAL = 2;
    public static final int PRIORITY_UNKNOWN = 0;
    private static final String[] c;
    private static final int[] d;
    private static final long[] e;
    private static final HashMap<String, Object> f;
    private static final HashMap<String, Object> g;
    private static final HashMap<String, Object> h;
    private static final HashMap<String, Object> i;
    private static final HashMap<String, Object> j;

    /* renamed from: a, reason: collision with root package name */
    private Bundle f5670a;
    private Notification b;

    /* loaded from: classes9.dex */
    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private final Bundle f5671a;
        private final Map<String, String> b;

        public Builder(String str) {
            Bundle bundle = new Bundle();
            this.f5671a = bundle;
            this.b = new HashMap();
            bundle.putString("to", str);
        }

        public Builder addData(String str, String str2) {
            if (str == null) {
                throw new IllegalArgumentException("add data failed, key is null.");
            }
            this.b.put(str, str2);
            return this;
        }

        public RemoteMessage build() {
            Bundle bundle = new Bundle();
            JSONObject jSONObject = new JSONObject();
            try {
                for (Map.Entry<String, String> entry : this.b.entrySet()) {
                    jSONObject.put(entry.getKey(), entry.getValue());
                }
                try {
                    String jSONObject2 = jSONObject.toString();
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put(RemoteMessageConst.COLLAPSE_KEY, this.f5671a.getString(RemoteMessageConst.COLLAPSE_KEY));
                    jSONObject3.put("ttl", this.f5671a.getInt("ttl"));
                    jSONObject3.put(RemoteMessageConst.SEND_MODE, this.f5671a.getInt(RemoteMessageConst.SEND_MODE));
                    jSONObject3.put(RemoteMessageConst.RECEIPT_MODE, this.f5671a.getInt(RemoteMessageConst.RECEIPT_MODE));
                    JSONObject jSONObject4 = new JSONObject();
                    if (jSONObject.length() != 0) {
                        jSONObject4.put("data", jSONObject2);
                    }
                    jSONObject4.put("msgId", this.f5671a.getString("msgId"));
                    jSONObject3.put("msgContent", jSONObject4);
                    bundle.putByteArray(RemoteMessageConst.MSGBODY, jSONObject3.toString().getBytes(m.f5679a));
                    bundle.putString("to", this.f5671a.getString("to"));
                    bundle.putString(RemoteMessageConst.MSGTYPE, this.f5671a.getString(RemoteMessageConst.MSGTYPE));
                    return new RemoteMessage(bundle);
                } catch (JSONException unused) {
                    HMSLog.w("RemoteMessage", "JSONException: parse message body failed.");
                    throw new PushException(PushException.EXCEPTION_SEND_FAILED);
                }
            } catch (JSONException unused2) {
                HMSLog.w("RemoteMessage", "JSONException: parse data to json failed.");
                throw new PushException(PushException.EXCEPTION_SEND_FAILED);
            }
        }

        public Builder clearData() {
            this.b.clear();
            return this;
        }

        public Builder setCollapseKey(String str) {
            this.f5671a.putString(RemoteMessageConst.COLLAPSE_KEY, str);
            return this;
        }

        public Builder setData(Map<String, String> map) {
            this.b.clear();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                this.b.put(entry.getKey(), entry.getValue());
            }
            return this;
        }

        public Builder setMessageId(String str) {
            this.f5671a.putString("msgId", str);
            return this;
        }

        public Builder setMessageType(String str) {
            this.f5671a.putString(RemoteMessageConst.MSGTYPE, str);
            return this;
        }

        public Builder setReceiptMode(int i) {
            if (i != 1 && i != 0) {
                throw new IllegalArgumentException("receipt mode can only be 0 or 1.");
            }
            this.f5671a.putInt(RemoteMessageConst.RECEIPT_MODE, i);
            return this;
        }

        public Builder setSendMode(int i) {
            if (i != 0 && i != 1) {
                throw new IllegalArgumentException("send mode can only be 0 or 1.");
            }
            this.f5671a.putInt(RemoteMessageConst.SEND_MODE, i);
            return this;
        }

        public Builder setTtl(int i) {
            if (i < 1 || i > 1296000) {
                throw new IllegalArgumentException("ttl must be greater than or equal to 1 and less than or equal to 1296000");
            }
            this.f5671a.putInt("ttl", i);
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface MessagePriority {
    }

    public static class Notification implements Serializable {
        private final long[] A;
        private final String B;

        /* renamed from: a, reason: collision with root package name */
        private final String f5672a;
        private final String b;
        private final String[] c;
        private final String d;
        private final String e;
        private final String[] f;
        private final String g;
        private final String h;
        private final String i;
        private final String j;
        private final String k;
        private final String l;
        private final String m;
        private final Uri n;
        private final int o;
        private final String p;
        private final int q;
        private final int r;
        private final int s;
        private final int[] t;
        private final String u;
        private final int v;
        private final String w;
        private final int x;
        private final String y;
        private final String z;

        /* synthetic */ Notification(Bundle bundle, a aVar) {
            this(bundle);
        }

        private Integer a(String str) {
            if (str != null) {
                try {
                    return Integer.valueOf(str);
                } catch (NumberFormatException unused) {
                    HMSLog.w("RemoteMessage", "NumberFormatException: get " + str + " failed.");
                }
            }
            return null;
        }

        public Integer getBadgeNumber() {
            return a(this.w);
        }

        public String getBody() {
            return this.d;
        }

        public String[] getBodyLocalizationArgs() {
            String[] strArr = this.f;
            return strArr == null ? new String[0] : (String[]) strArr.clone();
        }

        public String getBodyLocalizationKey() {
            return this.e;
        }

        public String getChannelId() {
            return this.m;
        }

        public String getClickAction() {
            return this.k;
        }

        public String getColor() {
            return this.j;
        }

        public String getIcon() {
            return this.g;
        }

        public Uri getImageUrl() {
            String str = this.p;
            if (str == null) {
                return null;
            }
            return Uri.parse(str);
        }

        public Integer getImportance() {
            return a(this.y);
        }

        public String getIntentUri() {
            return this.l;
        }

        public int[] getLightSettings() {
            int[] iArr = this.t;
            return iArr == null ? new int[0] : (int[]) iArr.clone();
        }

        public Uri getLink() {
            return this.n;
        }

        public int getNotifyId() {
            return this.o;
        }

        public String getSound() {
            return this.h;
        }

        public String getTag() {
            return this.i;
        }

        public String getTicker() {
            return this.z;
        }

        public String getTitle() {
            return this.f5672a;
        }

        public String[] getTitleLocalizationArgs() {
            String[] strArr = this.c;
            return strArr == null ? new String[0] : (String[]) strArr.clone();
        }

        public String getTitleLocalizationKey() {
            return this.b;
        }

        public long[] getVibrateConfig() {
            long[] jArr = this.A;
            return jArr == null ? new long[0] : (long[]) jArr.clone();
        }

        public Integer getVisibility() {
            return a(this.B);
        }

        public Long getWhen() {
            if (!TextUtils.isEmpty(this.u)) {
                try {
                    return Long.valueOf(DateUtil.parseUtcToMillisecond(this.u));
                } catch (StringIndexOutOfBoundsException unused) {
                    HMSLog.w("RemoteMessage", "StringIndexOutOfBoundsException: parse when failed.");
                } catch (ParseException unused2) {
                    HMSLog.w("RemoteMessage", "ParseException: parse when failed.");
                }
            }
            return null;
        }

        public boolean isAutoCancel() {
            return this.x == 1;
        }

        public boolean isDefaultLight() {
            return this.q == 1;
        }

        public boolean isDefaultSound() {
            return this.r == 1;
        }

        public boolean isDefaultVibrate() {
            return this.s == 1;
        }

        public boolean isLocalOnly() {
            return this.v == 1;
        }

        private Notification(Bundle bundle) {
            this.f5672a = bundle.getString(RemoteMessageConst.Notification.NOTIFY_TITLE);
            this.d = bundle.getString("content");
            this.b = bundle.getString(RemoteMessageConst.Notification.TITLE_LOC_KEY);
            this.e = bundle.getString(RemoteMessageConst.Notification.BODY_LOC_KEY);
            this.c = bundle.getStringArray(RemoteMessageConst.Notification.TITLE_LOC_ARGS);
            this.f = bundle.getStringArray(RemoteMessageConst.Notification.BODY_LOC_ARGS);
            this.g = bundle.getString("icon");
            this.j = bundle.getString("color");
            this.h = bundle.getString(RemoteMessageConst.Notification.SOUND);
            this.i = bundle.getString("tag");
            this.m = bundle.getString("channelId");
            this.k = bundle.getString(RemoteMessageConst.Notification.CLICK_ACTION);
            this.l = bundle.getString(RemoteMessageConst.Notification.INTENT_URI);
            this.o = bundle.getInt(RemoteMessageConst.Notification.NOTIFY_ID);
            String string = bundle.getString("url");
            this.n = !TextUtils.isEmpty(string) ? Uri.parse(string) : null;
            this.p = bundle.getString(RemoteMessageConst.Notification.NOTIFY_ICON);
            this.q = bundle.getInt(RemoteMessageConst.Notification.DEFAULT_LIGHT_SETTINGS);
            this.r = bundle.getInt(RemoteMessageConst.Notification.DEFAULT_SOUND);
            this.s = bundle.getInt(RemoteMessageConst.Notification.DEFAULT_VIBRATE_TIMINGS);
            this.t = bundle.getIntArray(RemoteMessageConst.Notification.LIGHT_SETTINGS);
            this.u = bundle.getString(RemoteMessageConst.Notification.WHEN);
            this.v = bundle.getInt(RemoteMessageConst.Notification.LOCAL_ONLY);
            this.w = bundle.getString(RemoteMessageConst.Notification.BADGE_SET_NUM, null);
            this.x = bundle.getInt(RemoteMessageConst.Notification.AUTO_CANCEL);
            this.y = bundle.getString("priority", null);
            this.z = bundle.getString(RemoteMessageConst.Notification.TICKER);
            this.A = bundle.getLongArray(RemoteMessageConst.Notification.VIBRATE_TIMINGS);
            this.B = bundle.getString("visibility", null);
        }
    }

    class a implements Parcelable.Creator<RemoteMessage> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public RemoteMessage createFromParcel(Parcel parcel) {
            return new RemoteMessage(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public RemoteMessage[] newArray(int i) {
            return new RemoteMessage[i];
        }

        a() {
        }
    }

    public RemoteMessage(Bundle bundle) {
        this.f5670a = a(bundle);
    }

    private Bundle a(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        JSONObject b = b(bundle);
        JSONObject a2 = a(b);
        String string = JsonUtil.getString(a2, "data", null);
        bundle2.putString(RemoteMessageConst.ANALYTIC_INFO, JsonUtil.getString(a2, RemoteMessageConst.ANALYTIC_INFO, null));
        bundle2.putString(RemoteMessageConst.DEVICE_TOKEN, bundle.getString(RemoteMessageConst.DEVICE_TOKEN));
        JSONObject d2 = d(a2);
        JSONObject b2 = b(d2);
        JSONObject c2 = c(d2);
        if (bundle.getInt(RemoteMessageConst.INPUT_TYPE) == 1 && d.a(a2, d2, string)) {
            bundle2.putString("data", com.huawei.hms.push.a.a(bundle.getByteArray(RemoteMessageConst.MSGBODY)));
            return bundle2;
        }
        String string2 = bundle.getString("to");
        String string3 = bundle.getString(RemoteMessageConst.MSGTYPE);
        String string4 = JsonUtil.getString(a2, "msgId", null);
        bundle2.putString("to", string2);
        bundle2.putString("data", string);
        bundle2.putString("msgId", string4);
        bundle2.putString(RemoteMessageConst.MSGTYPE, string3);
        JsonUtil.transferJsonObjectToBundle(b, bundle2, f);
        bundle2.putBundle(RemoteMessageConst.NOTIFICATION, a(b, a2, d2, b2, c2));
        return bundle2;
    }

    private static JSONObject b(Bundle bundle) {
        try {
            return new JSONObject(com.huawei.hms.push.a.a(bundle.getByteArray(RemoteMessageConst.MSGBODY)));
        } catch (JSONException unused) {
            HMSLog.w("RemoteMessage", "JSONException:parse message body failed.");
            return null;
        }
    }

    private static JSONObject c(JSONObject jSONObject) {
        if (jSONObject != null) {
            return jSONObject.optJSONObject(RemoteMessageConst.MessageBody.PARAM);
        }
        return null;
    }

    private static JSONObject d(JSONObject jSONObject) {
        if (jSONObject != null) {
            return jSONObject.optJSONObject(RemoteMessageConst.MessageBody.PS_CONTENT);
        }
        return null;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public String getAnalyticInfo() {
        return this.f5670a.getString(RemoteMessageConst.ANALYTIC_INFO);
    }

    public Map<String, String> getAnalyticInfoMap() {
        HashMap hashMap = new HashMap();
        String string = this.f5670a.getString(RemoteMessageConst.ANALYTIC_INFO);
        if (string != null && !string.trim().isEmpty()) {
            try {
                JSONObject jSONObject = new JSONObject(string);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String valueOf = String.valueOf(keys.next());
                    hashMap.put(valueOf, String.valueOf(jSONObject.get(valueOf)));
                }
            } catch (JSONException unused) {
                HMSLog.w("RemoteMessage", "JSONException: get analyticInfo from map failed.");
            }
        }
        return hashMap;
    }

    public String getCollapseKey() {
        return this.f5670a.getString(RemoteMessageConst.COLLAPSE_KEY);
    }

    public String getData() {
        return this.f5670a.getString("data");
    }

    public Map<String, String> getDataOfMap() {
        HashMap hashMap = new HashMap();
        String string = this.f5670a.getString("data");
        if (string != null && !string.trim().isEmpty()) {
            try {
                JSONObject jSONObject = new JSONObject(string);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String valueOf = String.valueOf(keys.next());
                    hashMap.put(valueOf, String.valueOf(jSONObject.get(valueOf)));
                }
            } catch (JSONException unused) {
                HMSLog.w("RemoteMessage", "JSONException: get data from map failed");
            }
        }
        return hashMap;
    }

    public String getFrom() {
        return this.f5670a.getString("from");
    }

    public String getMessageId() {
        return this.f5670a.getString("msgId");
    }

    public String getMessageType() {
        return this.f5670a.getString(RemoteMessageConst.MSGTYPE);
    }

    public Notification getNotification() {
        Bundle bundle = this.f5670a.getBundle(RemoteMessageConst.NOTIFICATION);
        a aVar = null;
        if (this.b == null && bundle != null) {
            this.b = new Notification(bundle, aVar);
        }
        if (this.b == null) {
            this.b = new Notification(new Bundle(), aVar);
        }
        return this.b;
    }

    public int getOriginalUrgency() {
        int i2 = this.f5670a.getInt(RemoteMessageConst.ORI_URGENCY);
        if (i2 == 1 || i2 == 2) {
            return i2;
        }
        return 0;
    }

    public int getReceiptMode() {
        return this.f5670a.getInt(RemoteMessageConst.RECEIPT_MODE);
    }

    public int getSendMode() {
        return this.f5670a.getInt(RemoteMessageConst.SEND_MODE);
    }

    public long getSentTime() {
        try {
            String string = this.f5670a.getString(RemoteMessageConst.SEND_TIME);
            if (TextUtils.isEmpty(string)) {
                return 0L;
            }
            return Long.parseLong(string);
        } catch (NumberFormatException unused) {
            HMSLog.w("RemoteMessage", "NumberFormatException: get sendTime error.");
            return 0L;
        }
    }

    public String getTo() {
        return this.f5670a.getString("to");
    }

    public String getToken() {
        return this.f5670a.getString(RemoteMessageConst.DEVICE_TOKEN);
    }

    public int getTtl() {
        return this.f5670a.getInt("ttl");
    }

    public int getUrgency() {
        int i2 = this.f5670a.getInt(RemoteMessageConst.URGENCY);
        if (i2 == 1 || i2 == 2) {
            return i2;
        }
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeBundle(this.f5670a);
        parcel.writeSerializable(this.b);
    }

    public RemoteMessage(Parcel parcel) {
        this.f5670a = parcel.readBundle();
        this.b = (Notification) parcel.readSerializable();
    }

    private static JSONObject b(JSONObject jSONObject) {
        if (jSONObject != null) {
            return jSONObject.optJSONObject(RemoteMessageConst.MessageBody.NOTIFY_DETAIL);
        }
        return null;
    }

    static {
        String[] strArr = new String[0];
        c = strArr;
        int[] iArr = new int[0];
        d = iArr;
        long[] jArr = new long[0];
        e = jArr;
        HashMap<String, Object> hashMap = new HashMap<>(8);
        f = hashMap;
        hashMap.put("from", "");
        hashMap.put(RemoteMessageConst.COLLAPSE_KEY, "");
        hashMap.put(RemoteMessageConst.SEND_TIME, "");
        hashMap.put("ttl", 86400);
        hashMap.put(RemoteMessageConst.URGENCY, 2);
        hashMap.put(RemoteMessageConst.ORI_URGENCY, 2);
        hashMap.put(RemoteMessageConst.SEND_MODE, 0);
        hashMap.put(RemoteMessageConst.RECEIPT_MODE, 0);
        HashMap<String, Object> hashMap2 = new HashMap<>(8);
        g = hashMap2;
        hashMap2.put(RemoteMessageConst.Notification.TITLE_LOC_KEY, "");
        hashMap2.put(RemoteMessageConst.Notification.BODY_LOC_KEY, "");
        hashMap2.put(RemoteMessageConst.Notification.NOTIFY_ICON, "");
        hashMap2.put(RemoteMessageConst.Notification.TITLE_LOC_ARGS, strArr);
        hashMap2.put(RemoteMessageConst.Notification.BODY_LOC_ARGS, strArr);
        hashMap2.put(RemoteMessageConst.Notification.TICKER, "");
        hashMap2.put(RemoteMessageConst.Notification.NOTIFY_TITLE, "");
        hashMap2.put("content", "");
        HashMap<String, Object> hashMap3 = new HashMap<>(8);
        h = hashMap3;
        hashMap3.put("icon", "");
        hashMap3.put("color", "");
        hashMap3.put(RemoteMessageConst.Notification.SOUND, "");
        hashMap3.put(RemoteMessageConst.Notification.DEFAULT_LIGHT_SETTINGS, 1);
        hashMap3.put(RemoteMessageConst.Notification.LIGHT_SETTINGS, iArr);
        hashMap3.put(RemoteMessageConst.Notification.DEFAULT_SOUND, 1);
        hashMap3.put(RemoteMessageConst.Notification.DEFAULT_VIBRATE_TIMINGS, 1);
        hashMap3.put(RemoteMessageConst.Notification.VIBRATE_TIMINGS, jArr);
        HashMap<String, Object> hashMap4 = new HashMap<>(8);
        i = hashMap4;
        hashMap4.put("tag", "");
        hashMap4.put(RemoteMessageConst.Notification.WHEN, "");
        hashMap4.put(RemoteMessageConst.Notification.LOCAL_ONLY, 1);
        hashMap4.put(RemoteMessageConst.Notification.BADGE_SET_NUM, "");
        hashMap4.put("priority", "");
        hashMap4.put(RemoteMessageConst.Notification.AUTO_CANCEL, 1);
        hashMap4.put("visibility", "");
        hashMap4.put("channelId", "");
        HashMap<String, Object> hashMap5 = new HashMap<>(3);
        j = hashMap5;
        hashMap5.put(RemoteMessageConst.Notification.CLICK_ACTION, "");
        hashMap5.put(RemoteMessageConst.Notification.INTENT_URI, "");
        hashMap5.put("url", "");
        CREATOR = new a();
    }

    private Bundle a(JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, JSONObject jSONObject5) {
        Bundle bundle = new Bundle();
        JsonUtil.transferJsonObjectToBundle(jSONObject3, bundle, g);
        JsonUtil.transferJsonObjectToBundle(jSONObject4, bundle, h);
        JsonUtil.transferJsonObjectToBundle(jSONObject, bundle, i);
        JsonUtil.transferJsonObjectToBundle(jSONObject5, bundle, j);
        bundle.putInt(RemoteMessageConst.Notification.NOTIFY_ID, JsonUtil.getInt(jSONObject2, RemoteMessageConst.Notification.NOTIFY_ID, 0));
        return bundle;
    }

    private static JSONObject a(JSONObject jSONObject) {
        if (jSONObject != null) {
            return jSONObject.optJSONObject("msgContent");
        }
        return null;
    }
}
