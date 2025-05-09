package com.huawei.ui.homehealth.search.dataprovider;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.homehealth.search.GlobalSearchActivity;
import com.huawei.ui.main.R$string;
import defpackage.fbh;
import defpackage.koq;
import defpackage.otj;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes9.dex */
public class RecentSearchProvider extends BaseKnitDataProvider<List<String>> {
    private Set<String> c = new HashSet();

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return true;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, List<String> list) {
        c(context, hashMap, list);
    }

    private void c(Context context, HashMap<String, Object> hashMap, List<String> list) {
        hashMap.put("TITLE", BaseApplication.getContext().getResources().getString(R$string.IDS_global_search_recent_search));
        if (koq.b(list)) {
            hashMap.put("IS_LOAD_DEFAULT_VIEW", true);
        } else {
            for (String str : list) {
                if (!this.c.contains(str)) {
                    this.c.add(str);
                    fbh.d(context, str);
                }
            }
            hashMap.put("ITEM_TITLE", list);
            hashMap.put("ITEM_RIGHT_BTN", BaseApplication.getContext().getResources().getString(R$string.IDS_album_dialog_clear));
            hashMap.put("IS_LOAD_DEFAULT_VIEW", false);
        }
        b(context, hashMap);
    }

    private void b(final Context context, Map<String, Object> map) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.ui.homehealth.search.dataprovider.RecentSearchProvider.1
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                if ("RIGHT_TOP_TEXT_CLICK_EVENT".equals(str)) {
                    RecentSearchProvider.this.c();
                }
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
                Activity activity = BaseApplication.getActivity();
                fbh.e(context, str);
                if (activity instanceof GlobalSearchActivity) {
                    ((GlobalSearchActivity) activity).d(str);
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        List<String> b = otj.b(BaseApplication.getContext());
        if (koq.b(b)) {
            sectionBean.e((Object) null);
        } else {
            sectionBean.e(b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        Activity activity = BaseApplication.getActivity();
        if (!dgZ_(activity)) {
            LogUtil.b("RecentSearchProvider", "activity is wrong or destroyed");
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(activity);
        builder.e(R$string.IDS_clear_recent_search);
        builder.czC_(R$string.IDS_yes, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.search.dataprovider.RecentSearchProvider.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("RecentSearchProvider", "clear recent search confirmed");
                otj.d(BaseApplication.getActivity(), new ArrayList());
                Activity activity2 = BaseApplication.getActivity();
                if (RecentSearchProvider.this.dgZ_(activity2)) {
                    ((GlobalSearchActivity) activity2).c();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.czz_(R$string.IDS_no, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.search.dataprovider.RecentSearchProvider.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("RecentSearchProvider", "clear recent search canceled");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e(true);
        builder.e().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean dgZ_(Activity activity) {
        return (!(activity instanceof GlobalSearchActivity) || activity.isFinishing() || activity.isDestroyed()) ? false : true;
    }
}
