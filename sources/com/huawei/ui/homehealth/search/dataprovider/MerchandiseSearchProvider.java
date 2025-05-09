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
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.homehealth.search.GlobalSearchActivity;
import defpackage.eiw;
import defpackage.jdx;
import defpackage.koq;
import defpackage.otb;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class MerchandiseSearchProvider extends BaseKnitDataProvider<List<GlobalSearchContent>> {
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
        d(context, hashMap, list);
    }

    private void d(Context context, HashMap<String, Object> hashMap, List<GlobalSearchContent> list) {
        if (koq.b(list)) {
            return;
        }
        if (otb.a(this)) {
            hashMap.put("SHOWMORE", context.getString(R.string._2130841847_res_0x7f0210f7));
            otb.a(context, "SHOW_MORE_CLICK_EVENT", hashMap, 205);
            hashMap.put("ITEM_LIMIT", 3);
        }
        hashMap.put("TITLE", context.getResources().getString(R.string._2130844449_res_0x7f021b21));
        hashMap.put("SEARCH_CONTENT", list);
        c(context, hashMap, list);
    }

    private void c(final Context context, Map<String, Object> map, final List<GlobalSearchContent> list) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.ui.homehealth.search.dataprovider.MerchandiseSearchProvider.2
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
                otb.a((List<GlobalSearchContent>) list, i, true, context, true);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(final Context context, final SectionBean sectionBean) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            jdx.b(new Runnable() { // from class: com.huawei.ui.homehealth.search.dataprovider.MerchandiseSearchProvider.3
                @Override // java.lang.Runnable
                public void run() {
                    MerchandiseSearchProvider.this.loadData(context, sectionBean);
                }
            });
            return;
        }
        if (LoginInit.getInstance(context).isKidAccount()) {
            sectionBean.e((Object) null);
        }
        if (otb.a(this)) {
            otb.c(context, sectionBean, 205);
        } else {
            eiw.c(GlobalSearchActivity.d(), "vmall", new UiCallback<List<GlobalSearchResult>>() { // from class: com.huawei.ui.homehealth.search.dataprovider.MerchandiseSearchProvider.5
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.a("MerchandiseSearchProvider", "request failed, errorCode: ", Integer.valueOf(i), " errorInfo: ", str);
                    otb.c(context, sectionBean, 205);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<GlobalSearchResult> list) {
                    otb.b(list, sectionBean, context, "vmall", 205);
                }
            });
        }
    }
}
