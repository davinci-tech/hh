package com.huawei.openalliance.ad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import com.huawei.openalliance.ad.activity.CustomLandingDetailActivity;
import com.huawei.openalliance.ad.activity.DomesticDsaActivity;
import com.huawei.openalliance.ad.activity.LandingDetailsActivity;
import com.huawei.openalliance.ad.activity.OpenPrivacyPerActivity;
import com.huawei.openalliance.ad.activity.PPSInterstitialAdActivity;
import com.huawei.openalliance.ad.activity.PPSRewardActivity;
import com.huawei.openalliance.ad.activity.PPSShareActivity;
import com.huawei.openalliance.ad.activity.SafeIntent;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.constant.MapNameConstants;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.inter.data.IAd;
import com.huawei.openalliance.ad.inter.data.ImageInfo;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;

/* loaded from: classes5.dex */
public class bx {

    /* renamed from: a, reason: collision with root package name */
    private static long f6666a;

    private static Class b() {
        return Build.VERSION.SDK_INT == 26 ? CustomLandingDetailActivity.class : LandingDetailsActivity.class;
    }

    private static boolean a(com.huawei.openalliance.ad.inter.data.e eVar) {
        String str;
        if (com.huawei.openalliance.ad.utils.ad.a()) {
            str = "repeat click too fast";
        } else {
            if (eVar != null) {
                return false;
            }
            str = "nativeAd is null";
        }
        ho.a("ActivityStarter", str);
        return true;
    }

    public static boolean a(Context context, com.huawei.openalliance.ad.inter.data.h hVar) {
        if (context == null || hVar == null) {
            return false;
        }
        try {
            Intent intent = new Intent();
            intent.setClassName(context, Constants.REWARD_ACTIVITY_NAME);
            String uniqueId = hVar.getUniqueId();
            PPSRewardActivity.a(uniqueId, hVar.X());
            PPSRewardActivity.a(uniqueId, hVar.V());
            dc.a(hVar);
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
            }
            intent.setClipData(Constants.CLIP_DATA);
            context.startActivity(intent);
            return true;
        } catch (Throwable th) {
            ho.a(3, th);
            ho.b("ActivityStarter", "startRewardActivty error, %s", th.getClass().getSimpleName());
            return false;
        }
    }

    public static boolean a(Context context, com.huawei.openalliance.ad.inter.data.d dVar) {
        if (context == null || dVar == null) {
            return false;
        }
        try {
            Intent intent = new Intent();
            intent.setClassName(context, Constants.INTERSTITIAL_ACTIVITY_NAME);
            PPSInterstitialAdActivity.a(dVar.getUniqueId(), dVar.ab());
            dc.b(dVar);
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
            }
            intent.setClipData(Constants.CLIP_DATA);
            context.startActivity(intent);
            return true;
        } catch (Throwable th) {
            ho.a(3, th);
            ho.b("ActivityStarter", "startInterstitialActivity error, %s", th.getClass().getSimpleName());
            return false;
        }
    }

    private static boolean a() {
        if (System.currentTimeMillis() - f6666a < 500) {
            return true;
        }
        f6666a = System.currentTimeMillis();
        return false;
    }

    private static void a(Intent intent, gz gzVar) {
        if (intent == null || gzVar == null) {
            return;
        }
        ho.b("ActivityStarter", "parseLinkedAdConfig.");
        intent.putExtra(MapKeyNames.LINKED_AD_DATA, com.huawei.openalliance.ad.utils.be.b(gzVar));
    }

    public static void a(Context context, String str) {
        if (a()) {
            ho.b("ActivityStarter", "start privacy or permission, fast click.");
            return;
        }
        try {
            Intent intent = new Intent(context, (Class<?>) OpenPrivacyPerActivity.class);
            intent.putExtra("url", str);
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
            }
            com.huawei.openalliance.ad.utils.dd.a(context, intent);
        } catch (Throwable th) {
            ho.c("ActivityStarter", "start PriPer err, %s", th.getClass().getSimpleName());
        }
    }

    public static void a(Context context, com.huawei.openalliance.ad.inter.data.e eVar, ContentRecord contentRecord) {
        ImageInfo imageInfo;
        ho.a("ActivityStarter", "startComplianceActivity");
        if (a(eVar)) {
            return;
        }
        try {
            Intent intent = new Intent(context, (Class<?>) PPSShareActivity.class);
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
            }
            intent.putExtra("title", eVar.getTitle());
            intent.putExtra("description", eVar.getDescription());
            if (eVar.getImageInfos() != null && !eVar.getImageInfos().isEmpty() && (imageInfo = eVar.getImageInfos().get(0)) != null) {
                intent.putExtra("imageUrl", imageInfo.getUrl());
            }
            intent.putExtra(MapKeyNames.CONTENT_RECORD, com.huawei.openalliance.ad.utils.be.b(contentRecord));
            intent.putExtra(JsbMapKeyNames.H5_CSHARE_URL, eVar.av());
            com.huawei.openalliance.ad.utils.dd.a(context, intent);
        } catch (Throwable th) {
            ho.c("ActivityStarter", "start Activity error: %s", th.getClass().getSimpleName());
        }
    }

    public static void a(Context context, IAd iAd, MaterialClickInfo materialClickInfo) {
        ho.b("ActivityStarter", "start landing detail activity external jump start.");
        if (iAd == null || iAd.getAppInfo() == null || TextUtils.isEmpty(iAd.getAppInfo().getAppDetailUrl())) {
            ho.b("ActivityStarter", "start landing detail activity native detail url is empty.");
            return;
        }
        SafeIntent safeIntent = new SafeIntent(new Intent(context, (Class<?>) b()));
        if (iAd instanceof com.huawei.openalliance.ad.inter.data.d) {
            dc.a((com.huawei.openalliance.ad.inter.data.d) iAd);
        } else {
            safeIntent.putExtra(MapKeyNames.APP_DETAIL_DATA, iAd);
        }
        if (materialClickInfo != null && com.huawei.openalliance.ad.utils.cz.p(materialClickInfo.c()) && materialClickInfo.a() != null) {
            safeIntent.putExtra("click_info", com.huawei.openalliance.ad.utils.be.b(materialClickInfo));
        }
        a(context, safeIntent);
    }

    public static void a(Context context, AdLandingPageData adLandingPageData, MaterialClickInfo materialClickInfo) {
        ho.b("ActivityStarter", "start landing detail activity internal jump start.");
        if (adLandingPageData == null || adLandingPageData.getAppInfo() == null || TextUtils.isEmpty(adLandingPageData.getAppInfo().getAppDetailUrl())) {
            ho.b("ActivityStarter", "start landing detail activity landingPageData detail url is empty.");
            return;
        }
        SafeIntent safeIntent = new SafeIntent(new Intent(context, (Class<?>) b()));
        safeIntent.putExtra(MapKeyNames.APP_DETAIL_DATA, adLandingPageData);
        if (materialClickInfo != null && com.huawei.openalliance.ad.utils.cz.p(materialClickInfo.c()) && materialClickInfo.a() != null) {
            safeIntent.putExtra("click_info", com.huawei.openalliance.ad.utils.be.b(materialClickInfo));
        }
        a(context, safeIntent);
    }

    public static void a(Context context, AdLandingPageData adLandingPageData, gz gzVar, ContentRecord contentRecord) {
        try {
            Intent intent = new Intent();
            intent.setClassName(context, "com.huawei.openalliance.ad.activity.PPSActivity");
            intent.putExtra(MapNameConstants.AD_LANDING_PAGE_DATA, adLandingPageData);
            a(intent, gzVar);
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
            }
            intent.setClipData(Constants.CLIP_DATA);
            context.startActivity(intent);
        } catch (Throwable th) {
            ho.a(3, th);
            ho.b("ActivityStarter", "startAdActivity error, %s", th.getClass().getSimpleName());
        }
    }

    private static void a(Context context, SafeIntent safeIntent) {
        try {
            safeIntent.setFlags(65536);
            if (!(context instanceof Activity)) {
                safeIntent.addFlags(268435456);
            }
            com.huawei.openalliance.ad.utils.dd.a(context, safeIntent);
        } catch (Throwable th) {
            ho.d("ActivityStarter", "start landing detail Activity error: %s", th.getClass().getSimpleName());
        }
    }

    public static void a(Context context, View view, int[] iArr, ContentRecord contentRecord) {
        String str;
        ho.b("ActivityStarter", "start domestic dsa activity");
        if (view == null) {
            return;
        }
        if (contentRecord == null || !contentRecord.bc() || com.huawei.openalliance.ad.utils.cz.b(contentRecord.bb())) {
            str = "start domestic dsa activity failed, switch close or empty url.";
        } else {
            if (iArr != null && iArr.length == 2) {
                a(context, view, contentRecord, iArr);
                return;
            }
            str = "invalid location array.";
        }
        ho.b("ActivityStarter", str);
    }

    private static void a(Context context, View view, ContentRecord contentRecord, int[] iArr) {
        int[] iArr2 = new int[2];
        view.getLocationOnScreen(iArr2);
        view.getViewTreeObserver().addOnGlobalLayoutListener(new hg(view, context, iArr2));
        int[] iArr3 = {view.getMeasuredWidth(), view.getMeasuredHeight()};
        SafeIntent safeIntent = new SafeIntent(new Intent(context, (Class<?>) DomesticDsaActivity.class));
        safeIntent.putExtra(MapKeyNames.DSA_URL, contentRecord.bb());
        safeIntent.putExtra(MapKeyNames.ANCHOR_LOCATION, iArr);
        safeIntent.putExtra(MapKeyNames.ANCHOR_SIZE, iArr3);
        safeIntent.setFlags(65536);
        if (!(context instanceof Activity)) {
            safeIntent.addFlags(268435456);
        }
        safeIntent.setClipData(Constants.CLIP_DATA);
        com.huawei.openalliance.ad.utils.dd.a(context, safeIntent);
    }

    public static void a(Context context, View view, ContentRecord contentRecord) {
        ho.b("ActivityStarter", "start domestic dsa activity");
        if (view == null) {
            return;
        }
        if (contentRecord == null || !contentRecord.bc() || com.huawei.openalliance.ad.utils.cz.b(contentRecord.bb())) {
            ho.b("ActivityStarter", "start domestic dsa activity failed, switch close or empty url.");
            return;
        }
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        ho.b("ActivityStarter", "startDomesticDsaActivity, anchorView.getLocationInWindow [x,y]= %d, %d", Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]));
        a(context, view, contentRecord, iArr);
    }
}
