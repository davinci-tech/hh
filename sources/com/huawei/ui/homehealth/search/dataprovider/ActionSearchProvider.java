package com.huawei.ui.homehealth.search.dataprovider;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.request.GlobalSearchContent;
import com.huawei.health.marketing.request.GlobalSearchResult;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.homehealth.search.GlobalSearchActivity;
import com.huawei.ui.homehealth.search.dataprovider.ActionSearchProvider;
import defpackage.eiw;
import defpackage.fbh;
import defpackage.jdx;
import defpackage.koq;
import defpackage.otb;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class ActionSearchProvider extends BaseKnitDataProvider<List<GlobalSearchContent>> {
    public static /* synthetic */ void b(int i, Object obj) {
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return true;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, List<GlobalSearchContent> list) {
        d(context, hashMap, list);
    }

    private void d(Context context, HashMap<String, Object> hashMap, List<GlobalSearchContent> list) {
        hashMap.clear();
        boolean a2 = otb.a(this);
        hashMap.put("TITLE", BaseApplication.getContext().getResources().getString(R.string._2130839701_res_0x7f020895));
        if (a2) {
            hashMap.put("SHOWMORE", context.getString(R.string.IDS_device_show_more_heartrate_device));
            otb.a(context, "SHOW_MORE_CLICK_EVENT", hashMap, a.C);
            hashMap.put("ITEM_LIMIT", 3);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        HashSet hashSet = new HashSet();
        for (GlobalSearchContent globalSearchContent : list) {
            if (globalSearchContent != null) {
                LogUtil.a("ActionSearchProvider", "content: ", HiJsonUtil.e(globalSearchContent));
                arrayList.add(globalSearchContent.getId());
                arrayList3.add(globalSearchContent.getPicUrl());
                arrayList2.add(globalSearchContent.getLessonName());
                otb.b(globalSearchContent, hashSet);
            }
        }
        hashMap.put("ITEM_IMAGE", arrayList3);
        hashMap.put("ITEM_TITLE", arrayList2);
        hashMap.put("HIGHLIGHTED_TEXT", otb.b(hashSet));
        e(context, hashMap, list, a2);
    }

    private void e(final Context context, Map<String, Object> map, final List<GlobalSearchContent> list, final boolean z) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.ui.homehealth.search.dataprovider.ActionSearchProvider.2
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
                ActionSearchProvider.this.a(context, globalSearchContent, z);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, GlobalSearchContent globalSearchContent, boolean z) {
        if (TextUtils.isEmpty(globalSearchContent.getId())) {
            LogUtil.b("ActionSearchProvider", "jumpToActionLibraryDetail course id is null");
            return;
        }
        if (LoginInit.getInstance(context).isBrowseMode()) {
            if (LoginInit.getInstance(context).isBrowseMode()) {
                LoginInit.getInstance(context).browsingToLogin(new IBaseResponseCallback() { // from class: osq
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        ActionSearchProvider.b(i, obj);
                    }
                }, "");
            }
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("action_id", globalSearchContent.getId());
            bundle.putString("action_version", globalSearchContent.getVersion());
            AppRouter.b("/PluginFitnessAdvice/FitnessActionDetailActivity").zF_(bundle).c(context);
            LogUtil.a("ActionSearchProvider", "jumpToActionLibraryDetail id: ", globalSearchContent.getId());
            fbh.b(context, GlobalSearchActivity.d(), a.C, globalSearchContent.getId(), globalSearchContent.getLessonName(), z);
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData, reason: merged with bridge method [inline-methods] */
    public void c(final Context context, final SectionBean sectionBean) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            jdx.b(new Runnable() { // from class: osm
                @Override // java.lang.Runnable
                public final void run() {
                    ActionSearchProvider.this.c(context, sectionBean);
                }
            });
        } else if (otb.a(this)) {
            otb.c(context, sectionBean, a.C);
        } else {
            eiw.c(GlobalSearchActivity.d(), "actionlibrary", new UiCallback<List<GlobalSearchResult>>() { // from class: com.huawei.ui.homehealth.search.dataprovider.ActionSearchProvider.5
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.a("ActionSearchProvider", "request failed, errorCode: ", Integer.valueOf(i), " errorInfo: ", str);
                    otb.c(context, sectionBean, a.C);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<GlobalSearchResult> list) {
                    otb.b(list, sectionBean, context, "actionlibrary", a.C);
                }
            });
        }
    }
}
