package com.huawei.ui.homehealth.search.dataprovider;

import android.content.Context;
import android.os.Looper;
import android.view.View;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.R;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.request.GlobalSearchContent;
import com.huawei.health.marketing.request.GlobalSearchResult;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.homehealth.search.GlobalSearchActivity;
import com.huawei.ui.homehealth.search.SearchResultFragment;
import com.huawei.ui.main.R$string;
import defpackage.eiw;
import defpackage.fbh;
import defpackage.jdx;
import defpackage.otb;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class InformationSearchProvider extends BaseKnitDataProvider<List<GlobalSearchContent>> {
    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return true;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, List<GlobalSearchContent> list) {
        c(context, hashMap, list);
    }

    private void c(Context context, HashMap<String, Object> hashMap, List<GlobalSearchContent> list) {
        hashMap.put("TITLE", BaseApplication.getContext().getResources().getString(R$string.IDS_social_information));
        boolean a2 = otb.a(this);
        if (a2) {
            hashMap.put("ITEM_LIMIT", 3);
        }
        hashMap.put("SUBTITLE", a2 ? context.getString(R.string.IDS_device_show_more_heartrate_device) : "");
        hashMap.put("IS_SUBTITLE_VISIBLE", Boolean.valueOf(a2));
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        HashSet hashSet = new HashSet();
        for (GlobalSearchContent globalSearchContent : list) {
            if (globalSearchContent != null) {
                arrayList.add(globalSearchContent.getId());
                arrayList3.add(globalSearchContent.getPicUrl());
                arrayList2.add(globalSearchContent.getTitle());
                otb.b(globalSearchContent, hashSet);
            }
        }
        hashMap.put("ITEM_BI_EVENT_MAP", otb.b(202, a2 ? 200 : 202, GlobalSearchActivity.d(), arrayList, arrayList2));
        hashMap.put("BI_OBSERVER_LABEL", fbh.d(a2 ? 200 : 202));
        hashMap.put("SCHOLASTIC_TYPE_IMAGE", arrayList3);
        hashMap.put("SCHOLASTIC_TYPE_CONTENT", arrayList2);
        hashMap.put("HIGHLIGHTED_TEXT", otb.b(hashSet));
        e(context, hashMap, list, a2);
    }

    private void e(final Context context, Map<String, Object> map, final List<GlobalSearchContent> list, final boolean z) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.ui.homehealth.search.dataprovider.InformationSearchProvider.3
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
                otb.a((List<GlobalSearchContent>) list, i, false, context, z);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SearchResultFragment d = SearchResultFragment.d();
                if (d == null) {
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    d.c(202);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void c(final Context context, final SectionBean sectionBean) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            jdx.b(new Runnable() { // from class: com.huawei.ui.homehealth.search.dataprovider.InformationSearchProvider.5
                @Override // java.lang.Runnable
                public void run() {
                    InformationSearchProvider.this.c(context, sectionBean);
                }
            });
            return;
        }
        if (LoginInit.getInstance(context).isKidAccount()) {
            sectionBean.e((Object) null);
        } else if (otb.a(this)) {
            otb.c(context, sectionBean, 202);
        } else {
            eiw.c(GlobalSearchActivity.d(), "message", new UiCallback<List<GlobalSearchResult>>() { // from class: com.huawei.ui.homehealth.search.dataprovider.InformationSearchProvider.4
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.a("InformationSearchProvider", "request failed, errorCode: ", Integer.valueOf(i), " errorInfo: ", str);
                    otb.c(context, sectionBean, 202);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<GlobalSearchResult> list) {
                    otb.b(list, sectionBean, context, "message", 202);
                }
            });
        }
    }
}
