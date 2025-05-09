package defpackage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.datatype.templates.BackgroundTemplate;
import com.huawei.health.marketing.datatype.templates.NavigationTemplate;
import com.huawei.health.marketing.datatype.templates.TrackPointSkinTemplate;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class gtv {

    /* renamed from: a, reason: collision with root package name */
    private static int f12934a;
    private static List<Integer> b;
    private static MarketingApi d;

    static /* synthetic */ int b() {
        int i = f12934a;
        f12934a = i + 1;
        return i;
    }

    public static void e() {
        LogUtil.a("Track_SportAdManager", "requestMarketingSkin()");
        if (!CommonUtil.aa(BaseApplication.e())) {
            LogUtil.h("Track_SportAdManager", "requestMarketingSkin() network is not connect");
            return;
        }
        if (d == null) {
            d = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        }
        if (d != null) {
            b = Arrays.asList(13002, 14001, 15001, 16001, 14002, 15002, 16002);
            e eVar = new e();
            d.getResourceResultInfo(b).addOnFailureListener(eVar).addOnSuccessListener(eVar);
            return;
        }
        LogUtil.b("Track_SportAdManager", "requestMarketingSkin() mMarketingApi = null");
    }

    static class e implements OnSuccessListener<Map<Integer, ResourceResultInfo>>, OnFailureListener {
        private e() {
        }

        @Override // com.huawei.hmf.tasks.OnFailureListener
        public void onFailure(Exception exc) {
            LogUtil.b("Track_SportAdManager", "OnMarketingResponseListener onFailure(): ", LogAnonymous.b((Throwable) exc));
            if (gtv.f12934a == 0) {
                gtv.b();
                gtv.e();
            }
        }

        @Override // com.huawei.hmf.tasks.OnSuccessListener
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Map<Integer, ResourceResultInfo> map) {
            gtv.b(map, gtv.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Map<Integer, ResourceResultInfo> map, List<Integer> list) {
        if (d == null) {
            d = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        }
        if (d == null || koq.b(list) || map == null || map.isEmpty()) {
            LogUtil.b("Track_SportAdManager", "handleMarketingSkin() marketingApi or skinPositions or resInfoMap is null or empty");
            return;
        }
        Map<Integer, ResourceResultInfo> filterMarketingRules = d.filterMarketingRules(new MarketingOption.Builder().setContext(BaseApplication.e()).setTypeId(50).setResultInfoMap(map).build());
        LogUtil.a("Track_SportAdManager", "handleMarketingSkin() first filterResInfoMap size: ", Integer.valueOf(filterMarketingRules.size()));
        for (Map.Entry<Integer, ResourceResultInfo> entry : filterMarketingRules.entrySet()) {
            int intValue = entry.getKey().intValue();
            ResourceResultInfo value = entry.getValue();
            if (value == null || value.getTotalNum() == 0 || koq.b(value.getResources())) {
                HashMap hashMap = new HashMap();
                hashMap.put(Integer.valueOf(intValue), map.get(Integer.valueOf(intValue)));
                filterMarketingRules.put(Integer.valueOf(intValue), d.filterMarketingRules(hashMap).get(Integer.valueOf(intValue)));
            }
        }
        LogUtil.a("Track_SportAdManager", "handleMarketingSkin() second filterResInfoMap size: ", Integer.valueOf(filterMarketingRules.size()));
        j();
        HashMap hashMap2 = new HashMap();
        for (Map.Entry<Integer, ResourceResultInfo> entry2 : filterMarketingRules.entrySet()) {
            if (list.contains(entry2.getKey())) {
                b(entry2.getValue(), entry2.getKey(), hashMap2);
            }
        }
        if (hashMap2.isEmpty()) {
            LogUtil.h("Track_SportAdManager", "handleMarketingSkin() skinsMap is empty");
        } else {
            LogUtil.a("Track_SportAdManager", "handleMarketingSkin() skinsMap size: ", Integer.valueOf(hashMap2.size()));
        }
    }

    private static void b(ResourceResultInfo resourceResultInfo, Integer num, Map<Integer, MessageObject> map) {
        LogUtil.h("Track_SportAdManager", "parseMarketingSkin() resourceId: ", num);
        if (resourceResultInfo == null || koq.b(resourceResultInfo.getResources())) {
            LogUtil.h("Track_SportAdManager", "parseMarketingSkin() resourceResultInfo is null or empty");
            return;
        }
        List<ResourceBriefInfo> resources = resourceResultInfo.getResources();
        Collections.sort(resources, new Comparator() { // from class: gtt
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return gtv.d((ResourceBriefInfo) obj, (ResourceBriefInfo) obj2);
            }
        });
        Gson gson = new Gson();
        for (ResourceBriefInfo resourceBriefInfo : resources) {
            if (resourceBriefInfo == null || resourceBriefInfo.getContent() == null || TextUtils.isEmpty(resourceBriefInfo.getContent().getContent()) || !eil.a(resourceBriefInfo.getEffectiveTime(), resourceBriefInfo.getExpirationTime())) {
                LogUtil.a("Track_SportAdManager", "resourceBriefInfo or content is null or empty");
            } else {
                MessageObject d2 = d(resourceBriefInfo, resourceBriefInfo.getContent().getContent(), resourceBriefInfo.getContentType(), gson, num.intValue());
                if (d2 != null) {
                    d2.setResBriefInfo(resourceBriefInfo);
                    map.put(num, d2);
                    return;
                }
            }
        }
    }

    static /* synthetic */ int d(ResourceBriefInfo resourceBriefInfo, ResourceBriefInfo resourceBriefInfo2) {
        int priority = resourceBriefInfo2.getPriority() - resourceBriefInfo.getPriority();
        return priority == 0 ? Long.compare(resourceBriefInfo2.getModifyTime(), resourceBriefInfo.getModifyTime()) : priority;
    }

    private static MessageObject d(ResourceBriefInfo resourceBriefInfo, String str, int i, Gson gson, int i2) {
        NavigationTemplate navigationTemplate;
        BackgroundTemplate backgroundTemplate;
        LogUtil.a("Track_SportAdManager", "parseSingleTemplate() contentStr: ", str, ", contentType: ", Integer.valueOf(i), ", resourceId: ", Integer.valueOf(i2));
        if (i == 56 || i == 57) {
            try {
                navigationTemplate = (NavigationTemplate) gson.fromJson(str, NavigationTemplate.class);
            } catch (JsonSyntaxException e2) {
                LogUtil.b("Track_SportAdManager", "NavigationTemplate exception: ", LogAnonymous.b((Throwable) e2));
                navigationTemplate = null;
            }
            if (navigationTemplate == null || TextUtils.isEmpty(navigationTemplate.getPicture())) {
                LogUtil.h("Track_SportAdManager", "NavigationTemplate Picture is NULL");
                return null;
            }
            MessageObject messageObject = new MessageObject();
            messageObject.setImgUri(navigationTemplate.getPicture());
            String e3 = moj.e(c(resourceBriefInfo, navigationTemplate));
            if (i == 56) {
                d(resourceBriefInfo, i2, navigationTemplate, e3);
                return messageObject;
            }
            b(resourceBriefInfo, i2, navigationTemplate, e3);
            return messageObject;
        }
        if (i != 58) {
            if (i != 55) {
                LogUtil.h("Track_SportAdManager", "parseSingleTemplate() error contentType: ", Integer.valueOf(i));
                return null;
            }
            return e(resourceBriefInfo, str, gson);
        }
        try {
            backgroundTemplate = (BackgroundTemplate) gson.fromJson(str, BackgroundTemplate.class);
        } catch (JsonSyntaxException e4) {
            LogUtil.a("Track_SportAdManager", "BackgroundTemplate exception: ", LogAnonymous.b((Throwable) e4));
            backgroundTemplate = null;
        }
        if (backgroundTemplate == null || TextUtils.isEmpty(backgroundTemplate.getMediaUrl()) || TextUtils.isEmpty(backgroundTemplate.getMediaType())) {
            LogUtil.a("Track_SportAdManager", "BackgroundTemplate MediaUrl or MediaType is NULL");
            return null;
        }
        MessageObject messageObject2 = new MessageObject();
        messageObject2.setImgUri(backgroundTemplate.getMediaUrl());
        messageObject2.setImgBigUri(backgroundTemplate.getTahitiMediaUrl());
        messageObject2.setType(backgroundTemplate.getMediaType());
        b(resourceBriefInfo, i2, backgroundTemplate);
        return messageObject2;
    }

    private static void b(ResourceBriefInfo resourceBriefInfo, int i, BackgroundTemplate backgroundTemplate) {
        gxu gxuVar = new gxu();
        gxuVar.a(resourceBriefInfo.getEffectiveTime());
        gxuVar.d(resourceBriefInfo.getExpirationTime());
        gxuVar.a(backgroundTemplate.getMediaUrl());
        gxuVar.c(backgroundTemplate.getTahitiMediaUrl());
        String e2 = moj.e(gxuVar);
        if (i == 16001) {
            LogUtil.h("Track_SportAdManager", "BackgroundTemplate outdoor jsonStr: ", e2);
            c("SportAdSportingCountdownBgOutdoor", e2);
            e(backgroundTemplate.getMediaUrl());
            e(backgroundTemplate.getTahitiMediaUrl());
            d(i, resourceBriefInfo);
            return;
        }
        if (i == 16002) {
            LogUtil.h("Track_SportAdManager", "BackgroundTemplate indoor jsonStr: ", e2);
            c("SportAdSportingCountdownBgIndoor", e2);
            e(backgroundTemplate.getMediaUrl());
            e(backgroundTemplate.getTahitiMediaUrl());
            d(i, resourceBriefInfo);
            return;
        }
        LogUtil.h("Track_SportAdManager", "BackgroundTemplate error resourceId: ", Integer.valueOf(i));
    }

    private static void b(ResourceBriefInfo resourceBriefInfo, int i, NavigationTemplate navigationTemplate, String str) {
        if (i == 15001) {
            LogUtil.a("Track_SportAdManager", "NavigationTemplate pause sport outdoor jsonStr: ", str);
            c("SportAdSportingPauseIconOutdoor", str);
            e(navigationTemplate.getPicture());
            d(i, resourceBriefInfo);
            return;
        }
        if (i == 15002) {
            LogUtil.a("Track_SportAdManager", "NavigationTemplate pause sport outdoor jsonStr: ", str);
            c("SportAdSportingPauseIconIndoor", str);
            e(navigationTemplate.getPicture());
            d(i, resourceBriefInfo);
            return;
        }
        LogUtil.h("Track_SportAdManager", "NavigationTemplate pause sport error resourceId: ", Integer.valueOf(i));
    }

    private static void d(ResourceBriefInfo resourceBriefInfo, int i, NavigationTemplate navigationTemplate, String str) {
        if (i == 14001) {
            LogUtil.a("Track_SportAdManager", "NavigationTemplate start sport outdoor jsonStr: ", str);
            c("SportAdStartSportIconOutdoor", str);
            e(navigationTemplate.getPicture());
            d(i, resourceBriefInfo);
        } else if (i == 14002) {
            LogUtil.a("Track_SportAdManager", "NavigationTemplate start sport indoor jsonStr: ", str);
            c("SportAdStartSportIconIndoor", str);
            e(navigationTemplate.getPicture());
            d(i, resourceBriefInfo);
        } else {
            LogUtil.h("Track_SportAdManager", "NavigationTemplate start sport error resourceId: ", Integer.valueOf(i));
            return;
        }
        Intent intent = new Intent("refreshSportAdIconReceiver");
        intent.setPackage(BaseApplication.d());
        BaseApplication.e().sendBroadcast(intent, gso.b);
    }

    private static gxu c(ResourceBriefInfo resourceBriefInfo, NavigationTemplate navigationTemplate) {
        gxu gxuVar = new gxu();
        gxuVar.a(resourceBriefInfo.getEffectiveTime());
        gxuVar.d(resourceBriefInfo.getExpirationTime());
        gxuVar.a(navigationTemplate.getPicture());
        return gxuVar;
    }

    private static MessageObject e(final ResourceBriefInfo resourceBriefInfo, String str, Gson gson) {
        TrackPointSkinTemplate trackPointSkinTemplate;
        try {
            trackPointSkinTemplate = (TrackPointSkinTemplate) gson.fromJson(str, TrackPointSkinTemplate.class);
        } catch (JsonSyntaxException e2) {
            LogUtil.a("Track_SportAdManager", "TrackPointSkinTemplate exception: ", LogAnonymous.b((Throwable) e2));
            trackPointSkinTemplate = null;
        }
        if (trackPointSkinTemplate == null || TextUtils.isEmpty(trackPointSkinTemplate.getPicture()) || TextUtils.isEmpty(trackPointSkinTemplate.getBackgroundIcon())) {
            LogUtil.a("Track_SportAdManager", "TrackPointSkinTemplate picture or background is NULL");
            return null;
        }
        MessageObject messageObject = new MessageObject();
        messageObject.setImgUri(trackPointSkinTemplate.getPicture());
        messageObject.setImgBigUri(trackPointSkinTemplate.getBackgroundIcon());
        d(13002, resourceBriefInfo);
        e(trackPointSkinTemplate.getPicture());
        nrf.b(trackPointSkinTemplate.getPicture(), new CustomTarget<Bitmap>() { // from class: gtv.1
            @Override // com.bumptech.glide.request.target.Target
            /* renamed from: aTJ_, reason: merged with bridge method [inline-methods] */
            public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                LogUtil.a("Track_SportAdManager", "TrackPointSkinTemplate icon onResourceReady()");
                String aTI_ = gtv.aTI_(bitmap, ResourceBriefInfo.this, true);
                LogUtil.a("Track_SportAdManager", "TrackPointSkinTemplate icon jsonStr: ", aTI_);
                gtv.c("SportAdSportingIconOutdoor", aTI_);
                gtv.g();
            }

            @Override // com.bumptech.glide.request.target.Target
            public void onLoadCleared(Drawable drawable) {
                LogUtil.a("Track_SportAdManager", " TrackPointSkinTemplate icon onLoadCleared()");
            }
        });
        e(trackPointSkinTemplate.getBackgroundIcon());
        nrf.b(trackPointSkinTemplate.getBackgroundIcon(), new CustomTarget<Bitmap>() { // from class: gtv.3
            @Override // com.bumptech.glide.request.target.Target
            /* renamed from: aTK_, reason: merged with bridge method [inline-methods] */
            public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                LogUtil.a("Track_SportAdManager", "TrackPointSkinTemplate background icon onResourceReady()");
                String aTI_ = gtv.aTI_(bitmap, ResourceBriefInfo.this, false);
                LogUtil.a("Track_SportAdManager", "TrackPointSkinTemplate background icon jsonStr: ", aTI_);
                gtv.c("SportAdSportingIconBgOutdoor", aTI_);
                gtv.g();
            }

            @Override // com.bumptech.glide.request.target.Target
            public void onLoadCleared(Drawable drawable) {
                LogUtil.a("Track_SportAdManager", "TrackPointSkinTemplate background icon onLoadCleared()");
            }
        });
        return messageObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String aTI_(Bitmap bitmap, ResourceBriefInfo resourceBriefInfo, boolean z) {
        gxu gxuVar = new gxu();
        gxuVar.a(resourceBriefInfo.getEffectiveTime());
        gxuVar.d(resourceBriefInfo.getExpirationTime());
        String cJr_ = nrf.cJr_("Track_SportAdManager_" + System.currentTimeMillis(), bitmap, Bitmap.CompressFormat.PNG, true);
        if (z) {
            gxuVar.a(cJr_);
        } else {
            gxuVar.b(cJr_);
        }
        return moj.e(gxuVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void g() {
        LogUtil.a("Track_SportAdManager", "sendRefreshSportingIconDataBroadcast()");
        Intent intent = new Intent("refreshSportingIconDataReceiver");
        intent.setPackage(BaseApplication.d());
        BaseApplication.e().sendBroadcast(intent, gso.b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(String str, String str2) {
        SharedPreferenceManager.e(BaseApplication.e(), Integer.toString(20002), str, str2, (StorageParams) null);
    }

    private static void d(int i, ResourceBriefInfo resourceBriefInfo) {
        c("SportAdMarketingBi_" + i, moj.e(resourceBriefInfo));
    }

    public static gxu a(String str) {
        String b2 = SharedPreferenceManager.b(BaseApplication.e(), Integer.toString(20002), str);
        LogUtil.a("Track_SportAdManager", "getSportAdData() read key: ", str, ", jsonStr: ", b2);
        gxu gxuVar = (gxu) moj.e(b2, gxu.class);
        if (gxuVar == null || !gxuVar.c()) {
            return null;
        }
        return gxuVar;
    }

    public static ResourceBriefInfo b(int i) {
        String b2 = SharedPreferenceManager.b(BaseApplication.e(), Integer.toString(20002), "SportAdMarketingBi_" + i);
        LogUtil.a("Track_SportAdManager", "getSportAdMarketingBiObj() read resPositionId: ", Integer.valueOf(i), ", jsonStr: ", b2);
        return (ResourceBriefInfo) moj.e(b2, ResourceBriefInfo.class);
    }

    private static void e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Track_SportAdManager", "preloadImage() error, imageUrl is null or empty");
            return;
        }
        nrf.d(BaseApplication.e(), str, new RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA), new RequestListener<Drawable>() { // from class: gtv.2
            @Override // com.bumptech.glide.request.RequestListener
            /* renamed from: aTL_, reason: merged with bridge method [inline-methods] */
            public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                return true;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                return true;
            }
        });
    }

    private static void j() {
        f();
        i();
        c("SportAdStartSportIconOutdoor", "");
        c("SportAdStartSportIconIndoor", "");
        c("SportAdSportingCountdownBgOutdoor", "");
        c("SportAdSportingCountdownBgIndoor", "");
        c("SportAdSportingIconOutdoor", "");
        c("SportAdSportingIconBgOutdoor", "");
        c("SportAdSportingPauseIconOutdoor", "");
        c("SportAdSportingPauseIconIndoor", "");
    }

    private static void i() {
        gxu a2 = a("SportAdSportingIconBgOutdoor");
        if (a2 == null) {
            LogUtil.a("Track_SportAdManager", "deleteAdBackground: oldSportAdDataBackground is null");
            return;
        }
        String d2 = a2.d();
        if (TextUtils.isEmpty(d2)) {
            LogUtil.a("Track_SportAdManager", "deleteAdBackground: iconPath is empty");
            return;
        }
        File file = new File(d2);
        if (!file.exists()) {
            LogUtil.a("Track_SportAdManager", "deleteAdBackground: file is not exists");
        } else if (file.delete()) {
            LogUtil.a("Track_SportAdManager", "TrackPointSkinTemplate delete old background icon success, oldIconPath: ", d2);
        } else {
            LogUtil.a("Track_SportAdManager", "TrackPointSkinTemplate delete old background icon failed");
        }
    }

    private static void f() {
        gxu a2 = a("SportAdSportingIconOutdoor");
        if (a2 == null) {
            LogUtil.a("Track_SportAdManager", "deleteAdIcon: oldSportAdDataIcon is null");
            return;
        }
        String a3 = a2.a();
        if (TextUtils.isEmpty(a3)) {
            LogUtil.a("Track_SportAdManager", "deleteAdIcon: iconPath is empty");
            return;
        }
        File file = new File(a3);
        if (!file.exists()) {
            LogUtil.a("Track_SportAdManager", "deleteAdIcon: file is not exists");
        } else if (file.delete()) {
            LogUtil.a("Track_SportAdManager", "resetSportAdAllData() delete old icon success, oldIconPath: ", a3);
        } else {
            LogUtil.a("Track_SportAdManager", "resetSportAdAllData() delete old icon failed");
        }
    }
}
