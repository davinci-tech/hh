package com.huawei.ui.main.stories.soical.member;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.vip.api.VipApi;
import com.huawei.health.vip.api.VipCallback;
import com.huawei.health.vip.datatypes.VipPrivilegeBean;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class MemberRightsColumnProvider extends BaseKnitDataProvider {

    /* renamed from: a, reason: collision with root package name */
    private Context f10488a;
    private SectionBean b;
    private final Handler e = new Handler(Looper.getMainLooper()) { // from class: com.huawei.ui.main.stories.soical.member.MemberRightsColumnProvider.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                ReleaseLogUtil.c("MemberRightsColumnProvider", "msg is null!");
                return;
            }
            super.handleMessage(message);
            if (message.what == 1) {
                ReleaseLogUtil.e("MemberRightsColumnProvider", "UPDATE DATAS!");
                MemberRightsColumnProvider.this.b.e(MemberRightsColumnProvider.this.d);
            }
        }
    };
    private Map<String, Object> d = new HashMap();

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return true;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        LogUtil.a("MemberRightsColumnProvider", "loadDefaultData");
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        LogUtil.a("MemberRightsColumnProvider", "loadData");
        this.f10488a = context;
        this.b = sectionBean;
        d();
    }

    private void d() {
        VipApi vipApi = (VipApi) Services.a("vip", VipApi.class);
        if (vipApi == null) {
            LogUtil.h("MemberRightsColumnProvider", "refreshVipStateData VipApi is null");
        } else {
            vipApi.getVipTypePrivilege(new VipCallback() { // from class: com.huawei.ui.main.stories.soical.member.MemberRightsColumnProvider.1
                @Override // com.huawei.health.vip.api.VipCallback
                public void onFailure(int i, String str) {
                }

                @Override // com.huawei.health.vip.api.VipCallback
                public void onSuccess(Object obj) {
                    LogUtil.a("MemberRightsColumnProvider", "getVipType back");
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    ArrayList arrayList3 = new ArrayList();
                    if (!koq.e(obj, VipPrivilegeBean.class)) {
                        LogUtil.a("MemberRightsColumnProvider", "getVipType back is null or not list");
                        return;
                    }
                    for (VipPrivilegeBean vipPrivilegeBean : (List) obj) {
                        String benefitName = vipPrivilegeBean.getBenefitName();
                        String deeplink = vipPrivilegeBean.getDeeplink();
                        String iconUrl = vipPrivilegeBean.getIconUrl();
                        arrayList.add(benefitName);
                        arrayList2.add(deeplink);
                        arrayList3.add(iconUrl);
                    }
                    MemberRightsColumnProvider.this.d.put("NAME_LIST", arrayList);
                    MemberRightsColumnProvider.this.d.put("DEEP_LINK_LIST", arrayList2);
                    MemberRightsColumnProvider.this.d.put("IMG_URL_LIST", arrayList3);
                    MemberRightsColumnProvider.this.e.sendEmptyMessage(1);
                }
            });
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap hashMap, Object obj) {
        char c;
        LogUtil.a("MemberRightsColumnProvider", "parseParams");
        if (!(obj instanceof HashMap)) {
            LogUtil.a("MemberRightsColumnProvider", "data is not map");
            return;
        }
        for (Map.Entry entry : ((HashMap) obj).entrySet()) {
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            str.hashCode();
            int hashCode = str.hashCode();
            if (hashCode == -1228468566) {
                if (str.equals("IMG_URL_LIST")) {
                    c = 0;
                }
                c = 65535;
            } else if (hashCode != -790929872) {
                if (hashCode == -245183022 && str.equals("NAME_LIST")) {
                    c = 2;
                }
                c = 65535;
            } else {
                if (str.equals("DEEP_LINK_LIST")) {
                    c = 1;
                }
                c = 65535;
            }
            if (c != 0) {
                if (c != 1) {
                    if (c == 2 && (value instanceof List)) {
                        hashMap.put("NAME", value);
                    }
                } else if (value instanceof List) {
                    hashMap.put("WEBVIEW_URL", value);
                }
            } else if (value instanceof List) {
                hashMap.put("ITEM_IMAGE", value);
            }
        }
    }
}
