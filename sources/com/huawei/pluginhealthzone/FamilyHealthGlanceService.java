package com.huawei.pluginhealthzone;

import android.content.Context;
import android.net.Uri;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.facade.service.PretreatmentService;
import com.huawei.health.pluginhealthzone.FamilyHealthZoneApi;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginhealthzone.FamilyHealthGlanceService;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes.dex */
public class FamilyHealthGlanceService implements PretreatmentService {
    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    @Override // com.huawei.haf.router.facade.service.PretreatmentService
    public boolean onPretreatment(Context context, Guidepost guidepost) {
        ReleaseLogUtil.e("FamilyHealthGlanceService", "FamilyHealthGlanceService onPretreatment");
        Uri zN_ = guidepost.zN_();
        if (zN_ == null) {
            LogUtil.h("FamilyHealthGlanceService", "onPretreatment uri is null!");
            return false;
        }
        String cmR_ = cmR_(zN_);
        FamilyHealthZoneApi familyHealthZoneApi = (FamilyHealthZoneApi) Services.a("PluginHealthZone", FamilyHealthZoneApi.class);
        if (familyHealthZoneApi == null) {
            LogUtil.h("FamilyHealthGlanceService", "healthZoneApi is null");
            return false;
        }
        if (cmR_ == null) {
            ReleaseLogUtil.d("FamilyHealthGlanceService", "hashHuid is null");
            return false;
        }
        familyHealthZoneApi.notifyMemberCheckHealth(cmR_, new IBaseResponseCallback() { // from class: mpk
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                FamilyHealthGlanceService.b(i, obj);
            }
        });
        return false;
    }

    public static /* synthetic */ void b(int i, Object obj) {
        if (i == 0) {
            ReleaseLogUtil.e("FamilyHealthGlanceService", "notifyMemberCheckHealth success");
        } else {
            ReleaseLogUtil.d("FamilyHealthGlanceService", "notifyMemberCheckHealth failed, errorCode:", Integer.valueOf(i));
        }
    }

    private String cmR_(Uri uri) {
        try {
            return uri.getQueryParameter(HealthZonePushReceiver.MEMBER_HUID);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("FamilyHealthGlanceService", "getMemberHuidFromDeeplink IllegalArgumentException:");
            return null;
        }
    }
}
