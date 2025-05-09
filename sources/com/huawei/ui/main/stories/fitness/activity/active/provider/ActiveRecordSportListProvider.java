package com.huawei.ui.main.stories.fitness.activity.active.provider;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ExpandableListView;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.active.provider.ActiveRecordSportListProvider;
import com.huawei.ui.main.stories.fitness.activity.active.writehelper.SportSimplifyItemCallback;
import com.huawei.ui.main.stories.history.SportHistoryActivity;
import com.huawei.ui.main.stories.history.adapter.SportHistoryExpandableListAdapter;
import defpackage.edr;
import defpackage.gnm;
import defpackage.gtc;
import defpackage.jdl;
import defpackage.koq;
import defpackage.nsf;
import defpackage.piz;
import defpackage.rdj;
import defpackage.rdo;
import defpackage.rdr;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes9.dex */
public class ActiveRecordSportListProvider extends MinorProvider<edr> {
    private edr b;
    private SportHistoryExpandableListAdapter d;
    private SectionBean h;
    private final CopyOnWriteArrayList<rdo> c = new CopyOnWriteArrayList<>();
    private final piz e = new piz();

    /* renamed from: a, reason: collision with root package name */
    private final e f9728a = new e(this);

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        LogUtil.a("SCUI_ActiveRecordSportListProvider", "enter loadDefaultData");
        this.h = sectionBean;
        super.loadDefaultData(sectionBean);
        ObserverManagerUtil.d(this.f9728a, "observer_sport_list_data_change");
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        LogUtil.a("SCUI_ActiveRecordSportListProvider", "enter onDestroy");
        ObserverManagerUtil.e("observer_sport_list_data_change");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.knit.data.MinorProvider
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onSetSectionBeanData(SectionBean sectionBean, edr edrVar) {
        this.b = edrVar;
        if (this.d == null) {
            SportHistoryExpandableListAdapter sportHistoryExpandableListAdapter = new SportHistoryExpandableListAdapter(BaseApplication.getContext(), null);
            this.d = sportHistoryExpandableListAdapter;
            sportHistoryExpandableListAdapter.e(1);
        }
        this.e.e(12, edrVar.t(), new SportSimplifyItemCallback() { // from class: pie
            @Override // com.huawei.ui.main.stories.fitness.activity.active.writehelper.SportSimplifyItemCallback
            public final void onResponse(int i, List list) {
                ActiveRecordSportListProvider.this.d(i, list);
            }
        });
    }

    public /* synthetic */ void d(int i, final List list) {
        HandlerExecutor.e(new Runnable() { // from class: pid
            @Override // java.lang.Runnable
            public final void run() {
                ActiveRecordSportListProvider.this.d(list);
            }
        });
    }

    public /* synthetic */ void d(List list) {
        LogUtil.a("SCUI_ActiveRecordSportListProvider", "mAllTypeGroupData is clear");
        a(list);
    }

    private ExpandableListView.OnChildClickListener dpO_() {
        return new ExpandableListView.OnChildClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.active.provider.ActiveRecordSportListProvider$$ExternalSyntheticLambda0
            @Override // android.widget.ExpandableListView.OnChildClickListener
            public final boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
                return ActiveRecordSportListProvider.this.dpQ_(expandableListView, view, i, i2, j);
            }
        };
    }

    /* synthetic */ boolean dpQ_(ExpandableListView expandableListView, View view, int i, int i2, long j) {
        LogUtil.a("SCUI_ActiveRecordSportListProvider", "ChildClickListener position ", Integer.valueOf(i2));
        rdr a2 = this.c.get(i).a(i2);
        long v = a2.v();
        long n = a2.n();
        gtc.e(BaseApplication.getActivity(), a2.t(), v, n);
        ViewClickInstrumentation.childClickOnExpandableListView(expandableListView, view, i, i2);
        return true;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void parseParams(final Context context, HashMap hashMap, edr edrVar) {
        LogUtil.a("SCUI_ActiveRecordSportListProvider", "parseParams");
        String formatDateTime = DateUtils.formatDateTime(BaseApplication.getContext(), edrVar.t(), 24);
        if (jdl.ac(edrVar.t())) {
            formatDateTime = nsf.h(R$string.IDS_hwh_home_group_today);
        }
        hashMap.put("BAR_CHART_DATE_TEXT", nsf.b(R$string.IDS_active_record_list_history_title, formatDateTime));
        hashMap.put("REMIND_ADAPTER", this.d);
        hashMap.put("COMMON_CLICK_EVENT", new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.active.provider.ActiveRecordSportListProvider$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActiveRecordSportListProvider.dpP_(context, view);
            }
        });
        hashMap.put("MIDDLE_TIP_TEXT_CLICK_EVENT", dpO_());
    }

    static /* synthetic */ void dpP_(Context context, View view) {
        LogUtil.a("SCUI_ActiveRecordSportListProvider", "go to history");
        gnm.aPB_(context, new Intent(context, (Class<?>) SportHistoryActivity.class));
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(List<rdr> list) {
        this.c.clear();
        LogUtil.a("SCUI_ActiveRecordSportListProvider", "mAllTypeGroupData is clear");
        if (koq.b(list)) {
            ObserverManagerUtil.c("observer_sport_list_show_tip", true);
            LogUtil.a("SCUI_ActiveRecordSportListProvider", "empty send one");
            c();
            return;
        }
        ObserverManagerUtil.c("observer_sport_list_show_tip", false);
        for (rdr rdrVar : list) {
            if (koq.c(this.c) && this.c.get(0).d() == 3) {
                break;
            }
            if (rdrVar != null && rdrVar.l() == null) {
                rdj rdjVar = new rdj(new HashMap(), 0, jdl.s(rdrVar.v()));
                if (this.c.isEmpty()) {
                    rdo rdoVar = new rdo(rdjVar);
                    rdoVar.d(rdrVar);
                    rdoVar.b(true);
                    this.c.add(rdoVar);
                } else {
                    this.c.get(0).d(rdrVar);
                }
                LogUtil.a("SCUI_ActiveRecordSportListProvider", "singleTrackData is ", rdrVar.toString());
            }
        }
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogUtil.a("SCUI_ActiveRecordSportListProvider", "record data changed");
        SectionBean sectionBean = this.h;
        if (sectionBean != null) {
            sectionBean.e(this.b);
            this.e.a();
        }
    }

    private void c() {
        this.d.c(this.c, 0);
        LogUtil.a("SCUI_ActiveRecordSportListProvider", "mAllTypeGroupData size is ", Integer.valueOf(this.c.size()));
        this.d.notifyDataSetChanged();
        SectionBean sectionBean = this.h;
        if (sectionBean != null) {
            sectionBean.e(this.b);
        }
    }

    public static class e implements Observer {
        private final WeakReference<ActiveRecordSportListProvider> d;

        e(ActiveRecordSportListProvider activeRecordSportListProvider) {
            this.d = new WeakReference<>(activeRecordSportListProvider);
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            if ("observer_sport_list_data_change".equals(str)) {
                ActiveRecordSportListProvider activeRecordSportListProvider = this.d.get();
                if (activeRecordSportListProvider != null) {
                    activeRecordSportListProvider.b();
                } else {
                    ReleaseLogUtil.d("SCUI_ActiveRecordSportListProvider", "ActiveRecordSportListProvider is null");
                }
            }
        }
    }
}
