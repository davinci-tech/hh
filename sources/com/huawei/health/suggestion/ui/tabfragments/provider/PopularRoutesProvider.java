package com.huawei.health.suggestion.ui.tabfragments.provider;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.motiontrack.model.runningroute.HotPathCityInfo;
import com.huawei.health.sport.utils.RunPopularRoutesUtil;
import com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider;
import com.huawei.health.trackprocess.model.GpsPoint;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.model.ICityLatLonCallBack;
import defpackage.ash;
import defpackage.ebw;
import defpackage.ema;
import defpackage.emc;
import defpackage.emp;
import defpackage.emz;
import defpackage.ene;
import defpackage.enf;
import defpackage.enm;
import defpackage.jdi;
import defpackage.koq;
import defpackage.npq;
import defpackage.npv;
import defpackage.nrv;
import defpackage.nsn;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class PopularRoutesProvider extends FitnessEntranceProvider {
    private static final String[] e = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};

    /* renamed from: a, reason: collision with root package name */
    private Context f3393a;
    private boolean b = true;
    private HotPathCityInfo c;
    private HotPathCityInfo d;
    private SectionBean i;
    private ene j;

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getCourseCategory() {
        return 258;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getPageType() {
        return 4;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getType() {
        return 0;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public boolean isCustomActive(Context context) {
        return !Utils.o() && LoginInit.getInstance(context).getIsLogined() && this.b;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void c(Context context, SectionBean sectionBean) {
        LogUtil.a("PopularRoutesProvider", "loadData enter");
        this.f3393a = context;
        this.i = sectionBean;
        e();
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap hashMap, Object obj) {
        ebw ebwVar;
        LogUtil.a("PopularRoutesProvider", "parseParams enter");
        if (obj == null || (ebwVar = (ebw) obj) == null) {
            return;
        }
        d(ebwVar, (Map<String, Object>) hashMap);
        d(ebwVar, hashMap);
    }

    private void a() {
        npq userLocation;
        ene.a aVar = new ene.a();
        HotPathCityInfo hotPathCityInfo = this.c;
        if (hotPathCityInfo != null) {
            aVar.c(hotPathCityInfo.getCityId());
        }
        if ((this.f3393a instanceof Activity) && (userLocation = ema.c().getUserLocation((Activity) this.f3393a)) != null) {
            LogUtil.a("PopularRoutesProvider", "get location success");
            aVar.d(userLocation.f15429a);
            aVar.e(userLocation.c);
        }
        aVar.i(1);
        aVar.h(0);
        aVar.a(0);
        aVar.b(1);
        aVar.c(5);
        this.j = aVar.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.c == null) {
            LogUtil.a("PopularRoutesProvider", "mCityInfo is null");
            c();
        } else {
            a();
            emc.d().getHotPaths(this.j, new UiCallback<emz>() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.PopularRoutesProvider.3
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(emz emzVar) {
                    LogUtil.a("PopularRoutesProvider", "loadData onSuccess");
                    List<enf> e2 = emzVar.e();
                    if (!koq.b(e2)) {
                        PopularRoutesProvider.this.d(e2);
                    } else {
                        PopularRoutesProvider.this.c();
                    }
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.a("PopularRoutesProvider", "loadData onFailure");
                    PopularRoutesProvider.this.c();
                }
            });
        }
    }

    private void e() {
        if (!jdi.c(this.f3393a, e)) {
            String b = ash.b("RUNNING_PATH_CITY_INFO");
            if (!TextUtils.isEmpty(b)) {
                this.c = (HotPathCityInfo) nrv.b(b, HotPathCityInfo.class);
            }
            b();
            return;
        }
        d();
    }

    private void d() {
        ema.c().getLocation(this.f3393a, new ICityLatLonCallBack() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.PopularRoutesProvider.4
            @Override // com.huawei.ui.commonui.model.ICityLatLonCallBack
            public void onPointBack(npv npvVar) {
                if (npvVar == null || npvVar.d() == null) {
                    PopularRoutesProvider.this.c();
                    return;
                }
                npq d = npvVar.d();
                GpsPoint gpsPoint = new GpsPoint(d.f15429a, d.c);
                PopularRoutesProvider.this.d = new HotPathCityInfo();
                PopularRoutesProvider.this.d.setCityName(npvVar.b());
                PopularRoutesProvider.this.d.setCityId("INVALIDE_CITY_ID");
                PopularRoutesProvider.this.d.setCityCenter(gpsPoint);
            }
        }, new UiCallback<emp>() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.PopularRoutesProvider.1
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.h("PopularRoutesProvider", "getLocalCity onFailure");
                PopularRoutesProvider.this.c();
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(emp empVar) {
                LogUtil.a("PopularRoutesProvider", "getLocation onSuccess");
                HotPathCityInfo c = empVar.c();
                if (c != null) {
                    PopularRoutesProvider.this.c = c;
                } else if (PopularRoutesProvider.this.d != null) {
                    PopularRoutesProvider popularRoutesProvider = PopularRoutesProvider.this;
                    popularRoutesProvider.c = popularRoutesProvider.d;
                }
                ash.a("RUNNING_PATH_CITY_INFO", nrv.a(PopularRoutesProvider.this.c));
                PopularRoutesProvider.this.b();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<enf> list) {
        LogUtil.a("PopularRoutesProvider", "detailData enter");
        ebw ebwVar = new ebw();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        for (enf enfVar : list) {
            arrayList.add(enfVar.i());
            arrayList2.add(enfVar.h());
            arrayList3.add(Double.valueOf(enfVar.m()));
            arrayList4.add(Long.valueOf(enfVar.c()));
            d(enfVar.f(), arrayList5);
        }
        ebwVar.a(arrayList);
        ebwVar.e(arrayList2);
        ebwVar.b(arrayList3);
        ebwVar.d(arrayList4);
        ebwVar.c(arrayList5);
        this.b = true;
        this.i.e(ebwVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.b = false;
        this.i.e((Object) null);
    }

    private void d(enm enmVar, List<String> list) {
        if (enmVar != null) {
            list.add(enmVar.b());
        } else {
            LogUtil.a("PopularRoutesProvider", "pathImageInfo is null");
        }
    }

    private void d(ebw ebwVar, Map<String, Object> map) {
        map.put("IMAGE", ebwVar.c());
        map.put("NAME", ebwVar.d());
        map.put("TOTAL_DISTANCE", ebwVar.b());
        map.put("PARTICIPATE_USER_NUM", ebwVar.e());
        map.put("TITLE", this.f3393a.getString(R$string.IDS_hw_show_popular_routes_string));
        map.put("SUBTITLE", this.f3393a.getString(R$string.IDS_user_profile_more));
    }

    private void d(final ebw ebwVar, HashMap hashMap) {
        hashMap.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.PopularRoutesProvider.5
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                if (nsn.o()) {
                    return;
                }
                PopularRoutesProvider.this.b(str);
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                if (nsn.o()) {
                    return;
                }
                PopularRoutesProvider.this.d(ebwVar, i);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        if ("ALL_CLICK_EVENT".equals(str)) {
            RunPopularRoutesUtil.e(this.f3393a, 2, new RunPopularRoutesUtil.DialogCallBack() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.PopularRoutesProvider.2
                @Override // com.huawei.health.sport.utils.RunPopularRoutesUtil.DialogCallBack
                public void goNext() {
                    LogUtil.a("PopularRoutesProvider", "Start jump To RunningRouteListActivity");
                    ComponentName componentName = new ComponentName(PopularRoutesProvider.this.f3393a.getPackageName(), "com.huawei.healthcloud.plugintrack.ui.activity.RunningRouteListActivity");
                    Intent intent = new Intent();
                    intent.setComponent(componentName);
                    intent.putExtra("RUNNING_PATH_CITY_INFO", PopularRoutesProvider.this.c);
                    PopularRoutesProvider.this.f3393a.startActivity(intent);
                }

                @Override // com.huawei.health.sport.utils.RunPopularRoutesUtil.DialogCallBack
                public void notGoNext() {
                    LogUtil.a("PopularRoutesProvider", "Cancle start jump To RunningRouteListActivity");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final ebw ebwVar, final int i) {
        RunPopularRoutesUtil.e(this.f3393a, 2, new RunPopularRoutesUtil.DialogCallBack() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.PopularRoutesProvider.6
            @Override // com.huawei.health.sport.utils.RunPopularRoutesUtil.DialogCallBack
            public void goNext() {
                if (koq.d(ebwVar.a(), i)) {
                    LogUtil.a("PopularRoutesProvider", "Start jump To Item");
                    String str = ebwVar.a().get(i);
                    ComponentName componentName = new ComponentName(PopularRoutesProvider.this.f3393a.getPackageName(), "com.huawei.healthcloud.plugintrack.ui.activity.ClockingRankActivity");
                    Intent intent = new Intent();
                    intent.setComponent(componentName);
                    intent.putExtra("PATH_ID", str);
                    intent.putExtra("RUNNING_PATH_CITY_INFO", PopularRoutesProvider.this.c);
                    intent.putExtra("ENTRANCE_ACTIVITY", 3);
                    PopularRoutesProvider.this.f3393a.startActivity(intent);
                }
            }

            @Override // com.huawei.health.sport.utils.RunPopularRoutesUtil.DialogCallBack
            public void notGoNext() {
                LogUtil.a("PopularRoutesProvider", "Cancle jump To Item");
            }
        });
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public String getSubViewTitle() {
        return "";
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "PopularRoutesProvider";
    }
}
