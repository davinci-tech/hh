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
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.homehealth.search.GlobalSearchActivity;
import com.huawei.ui.homehealth.search.dataprovider.ReliefMusicSearchProvider;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class ReliefMusicSearchProvider extends BaseKnitDataProvider<List<GlobalSearchContent>> {
    public static /* synthetic */ void c(int i, Object obj) {
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return true;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, List<GlobalSearchContent> list) {
        e(context, hashMap, list);
    }

    private void e(Context context, HashMap<String, Object> hashMap, List<GlobalSearchContent> list) {
        hashMap.clear();
        boolean a2 = otb.a(this);
        hashMap.put("TITLE", BaseApplication.getContext().getResources().getString(R.string._2130839700_res_0x7f020894));
        if (a2) {
            hashMap.put("SHOWMORE", context.getString(R.string.IDS_device_show_more_heartrate_device));
            otb.a(context, "SHOW_MORE_CLICK_EVENT", hashMap, a.z);
            hashMap.put("ITEM_LIMIT", 3);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        ArrayList arrayList8 = new ArrayList();
        HashSet hashSet = new HashSet();
        Iterator<GlobalSearchContent> it = list.iterator();
        while (it.hasNext()) {
            GlobalSearchContent next = it.next();
            if (next != null) {
                Iterator<GlobalSearchContent> it2 = it;
                LogUtil.c("ReliefMusicSearchProvider", "content: ", HiJsonUtil.e(next));
                arrayList4.add(c(next));
                arrayList.add(next.getId());
                arrayList7.add(next.getPicUrl());
                arrayList8.add(nrf.cHF_(nsf.cKq_(R.drawable.ic_device_earphone)));
                arrayList2.add(next.getLessonName());
                arrayList3.add(next.getCommodityFlag() == 2 ? context.getString(R.string._2130847510_res_0x7f022716) : "");
                otb.b(next, hashSet);
                otb.e(next, arrayList6, arrayList5, hashSet);
                it = it2;
            }
        }
        hashMap.put("ITEM_IMAGE", arrayList7);
        hashMap.put("ITEM_TITLE", arrayList2);
        hashMap.put("ITEM_SUBTITLE", arrayList4);
        hashMap.put("ITEM_TAG", arrayList3);
        hashMap.put("HIGHLIGHTED_TEXT", otb.b(hashSet));
        hashMap.put("ITEM_DESCRIPTION", arrayList5);
        hashMap.put("ITEM_PAGE_ATTRIBUTE", arrayList6);
        hashMap.put("RIGHT_ICON_IMAGE", arrayList8);
        b(context, hashMap, list, a2);
    }

    private String c(GlobalSearchContent globalSearchContent) {
        String str;
        LogUtil.c("ReliefMusicSearchProvider", "getReliefMusicSubTitle join：", Long.valueOf(globalSearchContent.getHeatCount()), " duration: ", Integer.valueOf(globalSearchContent.getDuration()));
        StringBuilder sb = new StringBuilder();
        sb.append(otb.c(globalSearchContent, false));
        if (globalSearchContent.getAudioType() == 1) {
            str = HealthZonePushReceiver.SLEEP_SCORE_NOTIFY;
        } else if (globalSearchContent.getDuration() / 60 > 0) {
            str = (globalSearchContent.getDuration() / 60) + "";
        } else {
            return sb.toString();
        }
        sb.append(ffy.d(arx.b(), R.string._2130837534_res_0x7f02001e, str));
        return sb.toString();
    }

    private void b(final Context context, Map<String, Object> map, final List<GlobalSearchContent> list, final boolean z) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.ui.homehealth.search.dataprovider.ReliefMusicSearchProvider.3
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
                if (koq.b(list, i)) {
                    return;
                }
                GlobalSearchContent globalSearchContent = (GlobalSearchContent) list.get(i);
                if (globalSearchContent == null || TextUtils.isEmpty(globalSearchContent.getId())) {
                    LogUtil.e("ReliefMusicSearchProvider", "jumpToReliefMusicDetail course id is null");
                    return;
                }
                ReliefMusicSearchProvider.this.a(context, "huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.sleeping-music?h5pro=true&urlType=4&pName=com.huawei.health&path=audioDetail&type=sleepAudio&statusBarTextBlack&isImmerse&pullfrom=globalsearch&id=" + globalSearchContent.getId());
                fbh.b(context, GlobalSearchActivity.d(), a.z, globalSearchContent.getId(), globalSearchContent.getLessonName(), z);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, String str) {
        if (LoginInit.getInstance(context).isBrowseMode()) {
            LoginInit.getInstance(context).browsingToLogin(new IBaseResponseCallback() { // from class: osx
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    ReliefMusicSearchProvider.c(i, obj);
                }
            }, "");
            return;
        }
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi == null) {
            LogUtil.e("ReliefMusicSearchProvider", "jumpToH5Detail marketRouterApi == null");
        } else {
            marketRouterApi.router(str);
            LogUtil.c("ReliefMusicSearchProvider", "jumpToH5Detail url: ", str);
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData, reason: merged with bridge method [inline-methods] */
    public void e(final Context context, final SectionBean sectionBean) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            jdx.b(new Runnable() { // from class: otc
                @Override // java.lang.Runnable
                public final void run() {
                    ReliefMusicSearchProvider.this.e(context, sectionBean);
                }
            });
        } else if (otb.a(this)) {
            otb.c(context, sectionBean, a.z);
        } else {
            eiw.c(GlobalSearchActivity.d(), "stressreliefmusic", new UiCallback<List<GlobalSearchResult>>() { // from class: com.huawei.ui.homehealth.search.dataprovider.ReliefMusicSearchProvider.4
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.c("ReliefMusicSearchProvider", "request failed, errorCode: ", Integer.valueOf(i), " errorInfo: ", str);
                    otb.c(context, sectionBean, a.z);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<GlobalSearchResult> list) {
                    otb.b(list, sectionBean, context, "stressreliefmusic", a.z);
                }
            });
        }
    }
}
