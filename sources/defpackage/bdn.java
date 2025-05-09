package defpackage;

import android.content.Intent;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.service.HandleIntentService;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.watchface.videoedit.gles.Constant;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes8.dex */
public class bdn {
    public void ps_(StatusBarNotification statusBarNotification) {
        try {
            Map<String, Object> pp_ = pp_(statusBarNotification);
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setExclusionStrategies(new b());
            String json = gsonBuilder.create().toJson(pp_);
            Bundle bundle = new Bundle();
            bundle.putString("LIVE_NOTIFICATION_PUSH_TYPE", "LIVE_NOTIFICATION_PUSH");
            bundle.putString("LIVE_NOTIFICATION_PUSH_JSON_STRING", json);
            pr_(bundle);
        } catch (Exception e) {
            LogUtil.b("NotificLivePush", e.toString());
        }
    }

    private Map<String, Object> pp_(StatusBarNotification statusBarNotification) {
        Bundle bundle = statusBarNotification.getNotification().extras;
        HashMap hashMap = new HashMap();
        hashMap.put("title", bdo.pf_(bundle));
        hashMap.put(Constant.TEXT, bdo.pd_(bundle));
        hashMap.put("packageName", statusBarNotification.getPackageName());
        hashMap.put("notificationId", Integer.valueOf(statusBarNotification.getId()));
        hashMap.put("notificationKey", statusBarNotification.getKey());
        hashMap.put("notification.live.operation", Integer.valueOf(bundle.getInt("notification.live.operation")));
        hashMap.put("notification.live.keepTime", Long.valueOf(bundle.getLong("notification.live.keepTime")));
        hashMap.put("notification.live.type", Integer.valueOf(bundle.getInt("notification.live.type", 0)));
        hashMap.put("notification.live.event", bdo.ph_(bundle, "notification.live.event", "OTHER"));
        hashMap.put("notification.live.titleOverlay", bdo.ph_(bundle, "notification.live.titleOverlay", ""));
        hashMap.put("notification.live.contentOverlay", bdo.ph_(bundle, "notification.live.contentOverlay", ""));
        hashMap.put("notification.live.externalEnable", Boolean.valueOf(bundle.getBoolean("notification.live.externalEnable", false)));
        hashMap.put("notification.live.externalTitle", bdo.ph_(bundle, "notification.live.externalTitle", ""));
        hashMap.put("notification.live.externalBody", bdo.ph_(bundle, "notification.live.externalBody", ""));
        Bundle bundle2 = bundle.getBundle("notification.live.feature");
        if (bundle2 != null) {
            hashMap.put("notification.live.feature", pq_(bundle2));
        } else {
            LogUtil.b("NotificLivePush", "liveFeature is null;");
        }
        Bundle bundle3 = bundle.getBundle("notification.live.capsule");
        if (bundle3 != null) {
            hashMap.put("notification.live.capsule", pq_(bundle3));
        } else {
            LogUtil.b("NotificLivePush", "liveCapsule is null;");
        }
        return hashMap;
    }

    private Map<String, Object> pq_(Bundle bundle) {
        HashMap hashMap = new HashMap();
        for (String str : bundle.keySet()) {
            hashMap.put(str, bundle.get(str));
        }
        return hashMap;
    }

    private void pr_(Bundle bundle) {
        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) HandleIntentService.class);
        intent.setAction("com.huawei.bone.ACTION_NOTIFICATION_PUSH");
        intent.putExtras(bundle);
        ReleaseLogUtil.e("Notify_NotificLivePush", "LiveNotificationHandle start to push notification msg");
        try {
            BaseApplication.getContext().startService(intent);
        } catch (IllegalStateException | SecurityException e) {
            ReleaseLogUtil.c("Notify_NotificLivePush", "LiveNotificationHandle startService: ", ExceptionUtils.d(e));
        }
        if (CommonUtil.ai(BaseApplication.getContext())) {
            return;
        }
        LogUtil.a("NotificLivePush", "LiveNotificationHandle phoneService not running");
        iyv.c(1001);
    }

    class b implements ExclusionStrategy {
        @Override // com.google.gson.ExclusionStrategy
        public boolean shouldSkipClass(Class<?> cls) {
            return false;
        }

        b() {
        }

        @Override // com.google.gson.ExclusionStrategy
        public boolean shouldSkipField(FieldAttributes fieldAttributes) {
            return fieldAttributes.getName().equals("mChangingConfigurations");
        }
    }
}
