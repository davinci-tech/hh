package defpackage;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hihealth.ICommonCallback;
import com.huawei.hihealth.model.DurationGoal;
import com.huawei.hihealth.model.EventTypeInfo;
import com.huawei.hihealth.model.Goal;
import com.huawei.hihealth.model.GoalInfo;
import com.huawei.hihealth.model.HealthAlarmInfo;
import com.huawei.hihealth.model.MetricGoal;
import com.huawei.hihealth.model.ReceiverFilter;
import com.huawei.hihealth.model.Recurrence;
import com.huawei.hihealth.model.SampleEvent;
import com.huawei.hihealth.model.SportStatusInfo;
import com.huawei.hihealth.model.Subscriber;
import com.huawei.hihealth.model.Subscription;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealth.util.HiScopeUtil;
import com.huawei.hihealthservice.hihealthkit.model.CloudSubscriber;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.HealthAccessTokenUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import com.huawei.openalliance.ad.constant.VastAttribute;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class ipw {
    public static String e(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("Content-type", "application/json");
        hashMap.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + str2);
        hashMap.put("x-thirdApp-AT", str3);
        final String[] strArr = new String[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        lqi.d().c(str, hashMap, new HashMap(), String.class, new ResultCallback<String>() { // from class: ipw.4
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str4) {
                LogUtil.a("HMSAuthHttps", "getReq success.");
                strArr[0] = str4;
                countDownLatch.countDown();
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                ReleaseLogUtil.c("HiH_HMSAuthHttps", "getReq failure,", th.getMessage());
                if (!(th instanceof lqj)) {
                    strArr[0] = ipw.a(-1, th.getMessage());
                } else {
                    lqj lqjVar = (lqj) th;
                    strArr[0] = ipw.a(lqjVar.e(), lqjVar.getMessage());
                }
                countDownLatch.countDown();
            }
        });
        try {
            ReleaseLogUtil.e("HiH_HMSAuthHttps", "getReq isOnTime: ", Boolean.valueOf(countDownLatch.await(20000L, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException e) {
            ReleaseLogUtil.c("HiH_HMSAuthHttps", "getReq InterruptedException.", LogAnonymous.b((Throwable) e));
        }
        LogUtil.c("HMSAuthHttps", "getReq result:", strArr[0]);
        return strArr[0];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String a(int i, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(VastAttribute.ERROR, i);
            jSONObject.put("message", str);
            return jSONObject.toString();
        } catch (JSONException e) {
            ReleaseLogUtil.c("HiH_HMSAuthHttps", "JSONException occurs", LogAnonymous.b((Throwable) e));
            return "";
        }
    }

    public static int b(String str, final List<CloudSubscriber> list) {
        final irr irrVar = new irr();
        list.addAll(irrVar.a(str));
        if (koq.c(list)) {
            return 0;
        }
        String a2 = a("/healthkit-healthapp/v1/subscribers");
        Map<String, String> e = e();
        HashMap hashMap = new HashMap();
        hashMap.put("appId", str);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final int[] iArr = {0};
        lqi.d().c(a2, e, hashMap, String.class, new ResultCallback<String>() { // from class: ipw.5
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str2) {
                try {
                    if (!TextUtils.isEmpty(str2) && str2.contains("subscriberId")) {
                        ReleaseLogUtil.e("HiH_HMSAuthHttps", "downloadSubscriber onSuccess");
                        List list2 = (List) HiJsonUtil.b(str2, new TypeToken<List<CloudSubscriber>>() { // from class: ipw.5.1
                        }.getType());
                        list.addAll(list2);
                        List<CloudSubscriber> e2 = irrVar.e();
                        e2.addAll(list2);
                        irrVar.d(e2);
                    } else {
                        iArr[0] = 2;
                    }
                } catch (ClassCastException e3) {
                    iArr[0] = 4;
                    ReleaseLogUtil.c("HiH_HMSAuthHttps", "class cast exception", LogAnonymous.b((Throwable) e3));
                }
                countDownLatch.countDown();
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                ReleaseLogUtil.c("HiH_HMSAuthHttps", "downloadSubscriber onFailure.", th.getMessage());
                iArr[0] = ipw.c(th);
                countDownLatch.countDown();
            }
        });
        try {
            if (!countDownLatch.await(5000L, TimeUnit.MILLISECONDS)) {
                ReleaseLogUtil.c("HiH_HMSAuthHttps", "get subscriber has timed out");
            }
        } catch (InterruptedException e2) {
            ReleaseLogUtil.c("HiH_HMSAuthHttps", "get subscriber interrupt exception", LogAnonymous.b((Throwable) e2));
            iArr[0] = 4;
        }
        return iArr[0];
    }

    private static Map<String, String> e() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1015);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.d("HiH_HMSAuthHttps", "getAccessToken is empty, refreshAccessToken.");
            accountInfo = HealthAccessTokenUtil.getAtInstance().refreshAccessToken();
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put("Content-type", HealthEngineRequestManager.CONTENT_TYPE);
        hashMap.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + accountInfo);
        hashMap.put("x-client-id", HiScopeUtil.c(BaseApplication.getContext(), BaseApplication.getContext().getPackageName()));
        hashMap.put("x-version", CommonUtil.c(BaseApplication.getContext()));
        hashMap.put("x-caller-trace-id", String.valueOf(jec.h()) + Math.random());
        return hashMap;
    }

    public static int e(String str, SampleEvent sampleEvent) {
        String replace = a("/healthkit-healthapp/v1/subscriptions/{subscriptionId}/sampleEvent").replace("{subscriptionId}", str);
        Map<String, String> e = e();
        JsonObject c = c(sampleEvent);
        if (c == null || c.get("metaData") == null) {
            ReleaseLogUtil.c("HiH_HMSAuthHttps", "notifyCloud onFailure, params is wrong");
            return 2;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final int[] iArr = {0};
        lqi.d().b(replace, e, lql.b(c), String.class, new ResultCallback<String>() { // from class: ipw.8
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str2) {
                ReleaseLogUtil.e("HiH_HMSAuthHttps", "notifyCloud success.");
                countDownLatch.countDown();
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                ReleaseLogUtil.c("HiH_HMSAuthHttps", "notifyCloud onFailure.", th.getMessage());
                iArr[0] = ipw.c(th);
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(5000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e2) {
            ReleaseLogUtil.c("HiH_HMSAuthHttps", "get subscriber interrupt exception", LogAnonymous.b((Throwable) e2));
            iArr[0] = 4;
        }
        return iArr[0];
    }

    public static void b(List<String> list, List<String> list2, List<String> list3) {
        String a2 = a("/healthkit-healthapp/v1/subscriptions?");
        if (koq.c(list)) {
            a2 = a2 + d("type=", list);
        }
        if (koq.c(list2)) {
            a2 = a2 + d("subscriptionMode=", list2);
        }
        if (koq.c(list3)) {
            a2 = a2 + d("eventType=", list3);
        }
        lqi.d().c(a2, e(), new HashMap(), String.class, new ResultCallback<String>() { // from class: ipw.10
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                irr irrVar = new irr();
                irrVar.b(Long.valueOf(System.currentTimeMillis()));
                if (!TextUtils.isEmpty(str) && str.contains("subscriptionId")) {
                    List<Subscription> d = ipw.d(str);
                    if (koq.b(d)) {
                        ReleaseLogUtil.c("HiH_HMSAuthHttps", "download serviceSubscriptions is empty.");
                        return;
                    }
                    ReleaseLogUtil.e("HiH_HMSAuthHttps", "downloadSubscription success and insertSubscription");
                    List<Subscription> a3 = irrVar.a();
                    if (koq.c(a3)) {
                        ipw.e(d, a3);
                    }
                    irrVar.e(d);
                    ipw.c(d);
                    return;
                }
                ReleaseLogUtil.c("HiH_HMSAuthHttps", "downloadSubscription result is ", str);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                ReleaseLogUtil.c("HiH_HMSAuthHttps", "downloadSubscription onFailure.", th.getMessage());
            }
        });
    }

    public static int b(Subscriber subscriber, final EventTypeInfo eventTypeInfo) {
        final String a2 = iwi.a(BaseApplication.getContext());
        LogUtil.a("HMSAuthHttps", "calling package name is ", a2);
        String a3 = a("/healthkit-healthapp/v1/subscriptions");
        Map<String, String> e = e();
        HashMap hashMap = new HashMap();
        hashMap.put("appId", subscriber.getAppId());
        hashMap.put("subscriberId", subscriber.getSubscriberId());
        hashMap.put("eventTypes", d(eventTypeInfo));
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final int[] iArr = {1004};
        lqi.d().b(a3, e, lql.b(hashMap), String.class, new ResultCallback<String>() { // from class: ipw.6
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                LogUtil.a("HMSAuthHttps", "upload subscription success");
                List d = ipw.d(str);
                if (ipw.a(a2, eventTypeInfo.getOpenId(), ((Subscription) d.get(0)).getOpenId())) {
                    iArr[0] = 0;
                } else {
                    iArr[0] = 2;
                }
                eventTypeInfo.setOpenId(((Subscription) d.get(0)).getOpenId());
                irr irrVar = new irr();
                List<Subscription> a4 = irrVar.a();
                ipw.c((List<Subscription>) d, a4);
                irrVar.e(a4);
                countDownLatch.countDown();
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                ReleaseLogUtil.c("HiH_HMSAuthHttps", "uploadSubscription onFailure.", th.getMessage());
                iArr[0] = ipw.c(th);
                countDownLatch.countDown();
            }
        });
        try {
            ReleaseLogUtil.e("HiH_HMSAuthHttps", "uploadSubscription wait:", Boolean.valueOf(countDownLatch.await(5000L, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException e2) {
            ReleaseLogUtil.c("HiH_HMSAuthHttps", "get subscriber interrupt exception", LogAnonymous.b((Throwable) e2));
        }
        return iArr[0];
    }

    public static int e(EventTypeInfo eventTypeInfo, Subscriber subscriber) {
        final int[] iArr = {0};
        List<Subscription> a2 = new irr().a();
        ReleaseLogUtil.e("HiH_HMSAuthHttps", "unRegisterSubscription spSubscriptions:", moj.e(a2));
        EventTypeInfo c = c(a2, eventTypeInfo, subscriber);
        if (c == null) {
            ReleaseLogUtil.c("HiH_HMSAuthHttps", "unRegisterSubscription success.There is no subscription.");
            return iArr[0];
        }
        final String subscriptionId = c.getSubscriptionId();
        String str = a("/healthkit-healthapp/v1/subscriptions/") + c.getSubscriptionId();
        Map<String, String> e = e();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        lqi.d().e(str, e, String.class, new ResultCallback<String>() { // from class: ipw.9
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str2) {
                ReleaseLogUtil.e("HiH_HMSAuthHttps", "unRegisterSubscription success.");
                irr irrVar = new irr();
                List<Subscription> a3 = irrVar.a();
                if (koq.c(a3)) {
                    ReleaseLogUtil.e("HiH_HMSAuthHttps", "delete spSubscription, id is ", subscriptionId);
                    ipw.e(subscriptionId, a3);
                    irrVar.e(a3);
                }
                countDownLatch.countDown();
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                ReleaseLogUtil.c("HiH_HMSAuthHttps", "unRegisterSubscription onFailure.", th.getMessage());
                iArr[0] = ipw.c(th);
                countDownLatch.countDown();
            }
        });
        try {
            if (!countDownLatch.await(5000L, TimeUnit.MILLISECONDS)) {
                ReleaseLogUtil.c("HiH_HMSAuthHttps", "unRegisterSubscription has timed out");
            }
        } catch (InterruptedException e2) {
            ReleaseLogUtil.c("HiH_HMSAuthHttps", "unRegisterSubscription interrupt exception", LogAnonymous.b((Throwable) e2));
            iArr[0] = 4;
        }
        return iArr[0];
    }

    public static void a(EventTypeInfo eventTypeInfo, Subscriber subscriber) {
        irr irrVar = new irr();
        List<Subscription> a2 = irrVar.a();
        EventTypeInfo c = c(a2, eventTypeInfo, subscriber);
        if (c == null) {
            ReleaseLogUtil.e("HiH_HMSAuthHttps", "updateSpSubscription success.There is no subscription.");
            return;
        }
        Iterator<Subscription> it = a2.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Subscription next = it.next();
            if (next.getSubscriptionId().equals(c.getSubscriptionId())) {
                next.setLastNotifyDate(subscriber.getLastNotifyDate());
                break;
            }
        }
        irrVar.e(a2);
    }

    public static List<Subscriber> d() {
        ArrayList arrayList = new ArrayList(16);
        List<CloudSubscriber> e = new irr().e();
        for (CloudSubscriber cloudSubscriber : e) {
            cloudSubscriber.setFilter((ReceiverFilter) HiJsonUtil.b(HiJsonUtil.e(cloudSubscriber.getPendingIntent()), new TypeToken<ReceiverFilter>() { // from class: ipw.7
            }.getType()));
        }
        arrayList.addAll(e);
        return arrayList;
    }

    public static EventTypeInfo e(Subscription subscription) {
        EventTypeInfo eventTypeInfo;
        if (Objects.equals(subscription.getType(), SampleEvent.Type.SCENARIO_GOAL_EVENT)) {
            eventTypeInfo = d(subscription);
        } else if (Objects.equals(subscription.getType(), SampleEvent.Type.HEALTH_ALARM_EVENT)) {
            eventTypeInfo = c(subscription);
        } else if (Objects.equals(subscription.getType(), SampleEvent.Type.SPORT_STATUS_EVENT)) {
            eventTypeInfo = b(subscription);
        } else {
            ReleaseLogUtil.c("HiH_HMSAuthHttps", "not support event type!");
            eventTypeInfo = null;
        }
        if (eventTypeInfo != null) {
            eventTypeInfo.setSubscriptionId(subscription.getSubscriptionId());
            eventTypeInfo.setSubscriptionMode(subscription.getSubscriptionMode());
        }
        return eventTypeInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<Subscription> d(String str) {
        ArrayList arrayList = new ArrayList(10);
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                Subscription subscription = new Subscription(SampleEvent.Type.valueOf(jSONObject.getString("type")), jSONObject.getString("subType"), jSONObject.getString("openId"), 0);
                subscription.setAppId(jSONObject.getString("appId"));
                subscription.setEventType(jSONObject.getString("eventType"));
                subscription.setSubscriberId(jSONObject.getString("subscriberId"));
                subscription.setSubscriptionId(jSONObject.getString("subscriptionId"));
                subscription.setDetailInfo(jSONObject.getString("goalInfo"));
                subscription.setSubscriptionMode(jSONObject.getInt("subscriptionMode"));
                arrayList.add(subscription);
            }
        } catch (JSONException e) {
            ReleaseLogUtil.c("HiH_HMSAuthHttps", "parse serviceSubscription JSONException.", LogAnonymous.b((Throwable) e));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(List<Subscription> list, List<Subscription> list2) {
        for (Subscription subscription : list) {
            Subscription a2 = a(subscription.getSubscriptionId(), list2);
            if (a2 != null) {
                a2.setDetailInfo(subscription.getDetailInfo());
            } else {
                ReleaseLogUtil.e("HiH_HMSAuthHttps", "add new subscription.");
                list2.add(subscription);
            }
        }
    }

    private static Subscription a(String str, List<Subscription> list) {
        for (Subscription subscription : list) {
            if (str.equals(subscription.getSubscriptionId())) {
                return subscription;
            }
        }
        return null;
    }

    private static String d(String str, List<String> list) {
        StringBuilder sb = new StringBuilder();
        try {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                sb.append(str + URLEncoder.encode(it.next(), "UTF-8") + "&");
            }
        } catch (UnsupportedEncodingException e) {
            ReleaseLogUtil.c("HiH_HMSAuthHttps", "getUrlQueryParams error!", LogAnonymous.b((Throwable) e));
        }
        return sb.toString();
    }

    private static JsonArray d(EventTypeInfo eventTypeInfo) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", eventTypeInfo.getType().getName());
        jsonObject.addProperty("subType", eventTypeInfo.getSubType());
        jsonObject.addProperty("eventType", eventTypeInfo.getEventType());
        if (eventTypeInfo instanceof GoalInfo) {
            GoalInfo goalInfo = (GoalInfo) eventTypeInfo;
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty("startDay", Integer.valueOf(goalInfo.getStartDay()));
            jsonObject2.add("recurrence", JsonParser.parseString(HiJsonUtil.e(goalInfo.getRecurrence())));
            JsonArray jsonArray = new JsonArray();
            for (Goal goal : goalInfo.getGoals()) {
                if ((goal instanceof MetricGoal) || (goal instanceof DurationGoal)) {
                    JsonObject jsonObject3 = new JsonObject();
                    jsonObject3.addProperty("goalType", Integer.valueOf(goal.getGoalType()));
                    d(goal, jsonObject3);
                    jsonArray.add(jsonObject3);
                }
            }
            jsonObject2.add("goals", jsonArray);
            jsonObject.add("goalInfo", jsonObject2);
        }
        JsonArray jsonArray2 = new JsonArray();
        jsonArray2.add(jsonObject);
        return jsonArray2;
    }

    private static void d(Goal goal, JsonObject jsonObject) {
        if (goal instanceof MetricGoal) {
            jsonObject.add("metricGoal", JsonParser.parseString(HiJsonUtil.e(goal)));
        }
        if (goal instanceof DurationGoal) {
            jsonObject.add("durationGoal", JsonParser.parseString(HiJsonUtil.e(goal)));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0084, code lost:
    
        r2.setMetaValue(r1.toString());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.google.gson.JsonObject c(com.huawei.hihealth.model.SampleEvent r9) {
        /*
            java.util.List r0 = r9.getMetaData()
            java.util.Iterator r1 = r0.iterator()
        L8:
            boolean r2 = r1.hasNext()
            java.lang.String r3 = "goalStatus"
            r4 = 0
            if (r2 == 0) goto L26
            java.lang.Object r2 = r1.next()
            com.huawei.hihealth.model.MetaData r2 = (com.huawei.hihealth.model.MetaData) r2
            java.lang.String r5 = r2.getMetaKey()
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L8
            java.lang.String r1 = r2.getMetaValue()
            goto L27
        L26:
            r1 = r4
        L27:
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            java.lang.String r5 = "HiH_HMSAuthHttps"
            if (r2 == 0) goto L3e
            java.lang.String r9 = "get metaData fail."
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r5, r9)
            com.google.gson.JsonObject r9 = new com.google.gson.JsonObject
            r9.<init>()
            return r9
        L3e:
            com.google.gson.JsonElement r1 = com.google.gson.JsonParser.parseString(r1)     // Catch: java.lang.IllegalStateException -> La1
            com.google.gson.JsonArray r1 = r1.getAsJsonArray()     // Catch: java.lang.IllegalStateException -> La1
            r2 = 0
            r6 = r2
        L48:
            int r7 = r1.size()     // Catch: java.lang.IllegalStateException -> La1
            if (r6 >= r7) goto L6a
            com.google.gson.JsonElement r7 = r1.get(r6)     // Catch: java.lang.IllegalStateException -> La1
            com.google.gson.JsonObject r7 = r7.getAsJsonObject()     // Catch: java.lang.IllegalStateException -> La1
            java.lang.String r8 = "goalAchieve"
            com.google.gson.JsonArray r7 = r7.getAsJsonArray(r8)     // Catch: java.lang.IllegalStateException -> La1
            com.google.gson.JsonElement r7 = r7.get(r2)     // Catch: java.lang.IllegalStateException -> La1
            com.google.gson.JsonObject r7 = r7.getAsJsonObject()     // Catch: java.lang.IllegalStateException -> La1
            c(r7)     // Catch: java.lang.IllegalStateException -> La1
            int r6 = r6 + 1
            goto L48
        L6a:
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.IllegalStateException -> La1
        L6e:
            boolean r2 = r0.hasNext()     // Catch: java.lang.IllegalStateException -> La1
            if (r2 == 0) goto L8b
            java.lang.Object r2 = r0.next()     // Catch: java.lang.IllegalStateException -> La1
            com.huawei.hihealth.model.MetaData r2 = (com.huawei.hihealth.model.MetaData) r2     // Catch: java.lang.IllegalStateException -> La1
            java.lang.String r6 = r2.getMetaKey()     // Catch: java.lang.IllegalStateException -> La1
            boolean r6 = r3.equals(r6)     // Catch: java.lang.IllegalStateException -> La1
            if (r6 == 0) goto L6e
            java.lang.String r0 = r1.toString()     // Catch: java.lang.IllegalStateException -> La1
            r2.setMetaValue(r0)     // Catch: java.lang.IllegalStateException -> La1
        L8b:
            java.lang.String r9 = com.huawei.hihealth.util.HiJsonUtil.e(r9)
            com.google.gson.JsonElement r9 = com.google.gson.JsonParser.parseString(r9)
            com.google.gson.JsonObject r9 = r9.getAsJsonObject()
            java.lang.String r0 = "timeZone"
            java.lang.String r1 = health.compact.a.HiDateUtil.d(r4)
            r9.addProperty(r0, r1)
            return r9
        La1:
            r9 = move-exception
            java.lang.String r0 = "getSampleEvent parse json failed."
            java.lang.String r9 = health.compact.a.LogAnonymous.b(r9)
            java.lang.Object[] r9 = new java.lang.Object[]{r0, r9}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r5, r9)
            com.google.gson.JsonObject r9 = new com.google.gson.JsonObject
            r9.<init>()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ipw.c(com.huawei.hihealth.model.SampleEvent):com.google.gson.JsonObject");
    }

    private static void c(JsonObject jsonObject) {
        JsonObject jsonObject2 = new JsonObject();
        if (jsonObject.get("goalType").getAsInt() == 1) {
            jsonObject2.addProperty("dataType", jsonObject.get("dataType").getAsString());
            jsonObject2.addProperty("fieldName", jsonObject.get("fieldName").getAsString());
            jsonObject2.addProperty("value", Double.valueOf(jsonObject.get("value").getAsDouble()));
            jsonObject.add("metricGoal", jsonObject2);
        }
        if (jsonObject.get("goalType").getAsInt() == 2) {
            jsonObject2.addProperty("duration", Long.valueOf(jsonObject.get("duration").getAsLong()));
            jsonObject2.addProperty("dataType", jsonObject.get("dataType").getAsString());
            jsonObject.add("durationGoal", jsonObject2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(String str, List<Subscription> list) {
        Iterator<Subscription> it = list.iterator();
        while (it.hasNext()) {
            if (str.equals(it.next().getSubscriptionId())) {
                it.remove();
            }
        }
    }

    private static String a(String str) {
        return GRSManager.a(BaseApplication.getContext()).getNoCheckUrl(HealthEngineRequestManager.GRS_KEY, "com.huawei.health" + ipo.d, "") + str;
    }

    public static void c(List<Subscription> list) {
        try {
            ReleaseLogUtil.e("HiH_HMSAuthHttps", "register subscription size ", Integer.valueOf(list.size()));
            ICommonCallback.Stub stub = new ICommonCallback.Stub() { // from class: ipw.14
                @Override // com.huawei.hihealth.ICommonCallback
                public void onResult(int i, String str) {
                    ReleaseLogUtil.e("HiH_HMSAuthHttps", "register subscription callback result ", Integer.valueOf(i));
                }
            };
            HashMap hashMap = new HashMap();
            for (Subscription subscription : list) {
                Subscriber subscriber = (Subscriber) hashMap.get(subscription.getSubscriberId());
                if (subscriber == null) {
                    subscriber = d(subscription.getAppId(), subscription.getSubscriberId());
                    hashMap.put(subscription.getSubscriberId(), subscriber);
                }
                EventTypeInfo e = e(subscription);
                if (e != null) {
                    Subscriber subscriber2 = new Subscriber(subscriber.getAppId(), subscriber.getSubscriberId(), subscriber.getSecret(), subscriber.getFilter());
                    subscriber2.setLastNotifyDate(subscription.getLastNotifyDate());
                    inv.c(BaseApplication.getContext()).b(subscriber2, e, stub, (irc) null);
                }
            }
        } catch (RemoteException e2) {
            ReleaseLogUtil.c("HiH_HMSAuthHttps", "autoRegisterSubscription failed.", LogAnonymous.b((Throwable) e2));
        }
    }

    private static Subscriber d(String str, String str2) {
        ArrayList<CloudSubscriber> arrayList = new ArrayList(10);
        b(str, arrayList);
        for (CloudSubscriber cloudSubscriber : arrayList) {
            if (str2.equals(cloudSubscriber.getSubscriberId())) {
                return new Subscriber(cloudSubscriber.getAppId(), cloudSubscriber.getSubscriberId(), cloudSubscriber.getSecret(), (ReceiverFilter) HiJsonUtil.b(HiJsonUtil.e(cloudSubscriber.getPendingIntent()), new TypeToken<ReceiverFilter>() { // from class: ipw.15
                }.getType()));
            }
        }
        return null;
    }

    private static GoalInfo d(Subscription subscription) {
        JsonObject asJsonObject = JsonParser.parseString(subscription.getDetailInfo()).getAsJsonObject();
        JsonArray asJsonArray = asJsonObject.getAsJsonArray("goals");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < asJsonArray.size(); i++) {
            JsonObject asJsonObject2 = asJsonArray.get(i).getAsJsonObject();
            String e = HiJsonUtil.e(asJsonObject2.get("metricGoal"));
            if (asJsonObject2.get("goalType").getAsInt() == 1) {
                MetricGoal metricGoal = (MetricGoal) HiJsonUtil.b(e, new TypeToken<MetricGoal>() { // from class: ipw.3
                }.getType());
                metricGoal.setGoalType(1);
                arrayList.add(metricGoal);
            } else {
                String e2 = HiJsonUtil.e(asJsonObject2.get("durationGoal"));
                if (asJsonObject2.get("goalType").getAsInt() == 2) {
                    DurationGoal durationGoal = (DurationGoal) HiJsonUtil.b(e2, new TypeToken<DurationGoal>() { // from class: ipw.1
                    }.getType());
                    durationGoal.setGoalType(2);
                    arrayList.add(durationGoal);
                }
            }
        }
        return new GoalInfo(arrayList, asJsonObject.get("startDay").getAsInt(), (Recurrence) HiJsonUtil.b(asJsonObject.get("recurrence").toString(), new TypeToken<Recurrence>() { // from class: ipw.2
        }.getType()), subscription.getOpenId());
    }

    private static HealthAlarmInfo c(Subscription subscription) {
        return new HealthAlarmInfo(subscription.getSubType(), subscription.getOpenId());
    }

    private static SportStatusInfo b(Subscription subscription) {
        return new SportStatusInfo(subscription.getSubType(), subscription.getOpenId(), subscription.getStartDay());
    }

    private static EventTypeInfo c(List<Subscription> list, EventTypeInfo eventTypeInfo, Subscriber subscriber) {
        for (Subscription subscription : list) {
            if (subscription.getSubscriberId().equals(subscriber.getSubscriberId()) && subscription.getEventType().equals(eventTypeInfo.getEventType())) {
                EventTypeInfo e = e(subscription);
                if (eventTypeInfo.equals(e)) {
                    return e;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(List<Subscription> list, List<Subscription> list2) {
        for (Subscription subscription : list) {
            Subscription a2 = a(subscription.getSubscriptionId(), list2);
            if (a2 != null) {
                subscription.setLastNotifyDate(a2.getLastNotifyDate());
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int c(Throwable th) {
        if (th instanceof lqj) {
            int e = ((lqj) th).e();
            if (e == 400) {
                return 2;
            }
            if (e == 401 || e == 403) {
                return 1001;
            }
        }
        return 1004;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean a(String str, String str2, String str3) {
        if ("com.huawei.hiai".equals(str)) {
            new irr().e(str3);
            return true;
        }
        if (str3.equals(str2)) {
            return true;
        }
        ReleaseLogUtil.c("HiH_HMSAuthHttps", "wrong openId");
        return false;
    }
}
