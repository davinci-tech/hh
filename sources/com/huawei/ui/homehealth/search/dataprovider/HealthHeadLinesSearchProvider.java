package com.huawei.ui.homehealth.search.dataprovider;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.R;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.request.GlobalSearchContent;
import com.huawei.health.marketing.request.GlobalSearchResult;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.homehealth.search.GlobalSearchActivity;
import com.huawei.ui.homehealth.search.dataprovider.HealthHeadLinesSearchProvider;
import defpackage.arx;
import defpackage.eiw;
import defpackage.fbh;
import defpackage.ffy;
import defpackage.jdx;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nsf;
import defpackage.otb;
import health.compact.a.LogUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class HealthHeadLinesSearchProvider extends BaseKnitDataProvider<List<GlobalSearchContent>> {
    public static /* synthetic */ void a(int i, Object obj) {
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return true;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public /* synthetic */ void parseParams(Context context, HashMap hashMap, Object obj) {
        a(context, (HashMap<String, Object>) hashMap, (List<GlobalSearchContent>) obj);
    }

    public void a(Context context, HashMap<String, Object> hashMap, List<GlobalSearchContent> list) {
        b(context, hashMap, list);
    }

    private void b(Context context, HashMap<String, Object> hashMap, List<GlobalSearchContent> list) {
        hashMap.clear();
        boolean a2 = otb.a(this);
        hashMap.put("TITLE", BaseApplication.getContext().getResources().getString(R.string.IDS_health_headlines_title));
        if (a2) {
            hashMap.put("SHOWMORE", context.getString(R.string.IDS_device_show_more_heartrate_device));
            otb.a(context, "SHOW_MORE_CLICK_EVENT", hashMap, a.A);
            hashMap.put("ITEM_LIMIT", 3);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        HashSet hashSet = new HashSet();
        for (GlobalSearchContent globalSearchContent : list) {
            if (globalSearchContent != null) {
                LogUtil.c("HealthHeadLinesSearchProvider", "content: ", HiJsonUtil.e(globalSearchContent));
                arrayList3.add(b(globalSearchContent, arrayList7));
                arrayList.add(globalSearchContent.getId());
                arrayList6.add(globalSearchContent.getPicUrl());
                arrayList2.add(globalSearchContent.getLessonName());
                otb.b(globalSearchContent, hashSet);
                otb.e(globalSearchContent, arrayList5, arrayList4, hashSet);
            }
        }
        hashMap.put("ITEM_IMAGE", arrayList6);
        hashMap.put("ITEM_TITLE", arrayList2);
        hashMap.put("ITEM_SUBTITLE", arrayList3);
        hashMap.put("HIGHLIGHTED_TEXT", otb.b(hashSet));
        hashMap.put("ITEM_DESCRIPTION", arrayList4);
        hashMap.put("ITEM_PAGE_ATTRIBUTE", arrayList5);
        hashMap.put("RIGHT_ICON_IMAGE", arrayList7);
        b(context, hashMap, list, a2);
    }

    private String b(GlobalSearchContent globalSearchContent, List<Object> list) {
        if (globalSearchContent.getHeatCount() == 0 && globalSearchContent.getDuration() == 0 && TextUtils.isEmpty(globalSearchContent.getAuthor())) {
            LogUtil.a("HealthHeadLinesSearchProvider", "getHealthHeadingSubTitle all info is zero");
            return "";
        }
        LogUtil.c("HealthHeadLinesSearchProvider", "getHealthHeadingSubTitle join：", Long.valueOf(globalSearchContent.getHeatCount()), " duration: ", Integer.valueOf(globalSearchContent.getDuration()), " author: ", globalSearchContent.getAuthor());
        list.add(nrf.cHF_(nsf.cKq_(R.drawable.ic_device_earphone)));
        StringBuilder sb = new StringBuilder();
        sb.append(otb.c(globalSearchContent, false));
        if (globalSearchContent.getDuration() / 60 > 0) {
            sb.append(ffy.d(arx.b(), R.string._2130837534_res_0x7f02001e, (globalSearchContent.getDuration() / 60) + ""));
        }
        if (!TextUtils.isEmpty(globalSearchContent.getAuthor())) {
            sb.append("  ");
            sb.append(globalSearchContent.getAuthor());
        }
        return sb.toString();
    }

    private void b(final Context context, Map<String, Object> map, final List<GlobalSearchContent> list, final boolean z) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.ui.homehealth.search.dataprovider.HealthHeadLinesSearchProvider.1
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                GlobalSearchContent globalSearchContent;
                if (koq.b(list, i) || (globalSearchContent = (GlobalSearchContent) list.get(i)) == null) {
                    return;
                }
                if (TextUtils.isEmpty(globalSearchContent.getId()) && !TextUtils.isEmpty(globalSearchContent.getDate())) {
                    LogUtil.e("HealthHeadLinesSearchProvider", "GlobalSearchContent id or date is null");
                    return;
                }
                HealthHeadLinesSearchProvider.this.d(context, "huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.health-headlines?h5pro=true&urlType=4&pName=com.huawei.health&isImmerse&type=2&enterPosition=7&pullfrom=globalsearch&workoutId=" + globalSearchContent.getId() + "&time=" + globalSearchContent.getDate());
                fbh.b(context, GlobalSearchActivity.d(), a.A, globalSearchContent.getId(), globalSearchContent.getLessonName(), z);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Context context, String str) {
        if (LoginInit.getInstance(context).isBrowseMode()) {
            LoginInit.getInstance(context).browsingToLogin(new IBaseResponseCallback() { // from class: osu
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    HealthHeadLinesSearchProvider.a(i, obj);
                }
            }, "");
            return;
        }
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi == null) {
            LogUtil.e("HealthHeadLinesSearchProvider", "jumpToHealthHeadingDetail marketRouterApi == null");
        } else {
            marketRouterApi.router(str);
            LogUtil.c("HealthHeadLinesSearchProvider", "jumpToHealthHeadingDetail url: ", str);
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void c(final Context context, final SectionBean sectionBean) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            jdx.b(new Runnable() { // from class: ota
                @Override // java.lang.Runnable
                public final void run() {
                    HealthHeadLinesSearchProvider.this.c(context, sectionBean);
                }
            });
        } else if (otb.a(this)) {
            otb.c(context, sectionBean, a.A);
        } else {
            eiw.c(GlobalSearchActivity.d(), "healthheadlines", new UiCallback<List<GlobalSearchResult>>() { // from class: com.huawei.ui.homehealth.search.dataprovider.HealthHeadLinesSearchProvider.5
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.c("HealthHeadLinesSearchProvider", "request failed, errorCode: ", Integer.valueOf(i), " errorInfo: ", str);
                    otb.c(context, sectionBean, a.A);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<GlobalSearchResult> list) {
                    otb.b(list, sectionBean, context, "healthheadlines", a.A);
                }
            });
        }
    }
}
