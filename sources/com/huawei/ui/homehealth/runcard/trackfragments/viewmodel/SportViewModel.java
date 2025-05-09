package com.huawei.ui.homehealth.runcard.trackfragments.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import androidx.core.util.Consumer;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.fitnessadvice.api.FitnessAdviceApi;
import com.huawei.health.knit.section.listener.BasePageResTrigger;
import com.huawei.health.knit.section.listener.IPageResTrigger;
import com.huawei.health.knit.section.model.MarketingIdInfo;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.datatype.templates.TabGeneralTemplate;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.tabtemplate.BaseTemplateConfig;
import com.huawei.tabtemplate.SportSubTabConfig;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.homehealth.runcard.trackfragments.H5SportFragment;
import com.huawei.ui.homehealth.runcard.trackfragments.SportTabPageResTrigger;
import com.huawei.ui.homehealth.runcard.trackfragments.viewmodel.SportViewModel;
import com.huawei.ui.main.stories.me.util.StepCounterSupportUtil;
import defpackage.diy;
import defpackage.ixu;
import defpackage.koq;
import defpackage.nig;
import defpackage.nmk;
import defpackage.nqu;
import defpackage.nrv;
import defpackage.nsn;
import defpackage.orv;
import defpackage.osp;
import defpackage.rxx;
import defpackage.rxy;
import health.compact.a.CommonUtil;
import health.compact.a.IoUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes6.dex */
public class SportViewModel extends ViewModel {
    private boolean f;
    private rxy<nig> g;
    private rxy<nig> i;
    private boolean j;
    private List<SportSubTabConfig> k;
    private List<ResourceBriefInfo> o;
    private boolean h = false;
    private WeakReference<nqu> b = new WeakReference<>(null);

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<nqu> f9581a = new WeakReference<>(null);
    private List<String> q = new ArrayList();
    private ArrayList<nqu> l = new ArrayList<>();
    private MutableLiveData<ArrayList<nqu>> e = new MutableLiveData<>();
    private MutableLiveData<nqu> d = new MutableLiveData<>();
    private MutableLiveData<nqu> n = new MutableLiveData<>();
    private Context c = BaseApplication.e();
    private e m = new e(this);

    public void c(Context context) {
        if (context == null) {
            LogUtil.h("Track_SportViewModel", "initStepCounter context is null");
        } else {
            this.h = StepCounterSupportUtil.d(context.getApplicationContext(), new StepCounterSupportUtil.StepCounterSupportCallback() { // from class: osa
                @Override // com.huawei.ui.main.stories.me.util.StepCounterSupportUtil.StepCounterSupportCallback
                public final void onIsSupportStepCounter(boolean z) {
                    SportViewModel.this.e(z);
                }
            });
        }
    }

    public /* synthetic */ void e(boolean z) {
        this.h = z;
        nqu nquVar = this.b.get();
        if (nquVar == null) {
            return;
        }
        if (z) {
            this.d.postValue(nquVar);
        } else {
            this.n.postValue(nquVar);
        }
    }

    public void a() {
        SportSupportUtil.a(new SportSupportUtil.OnFitnessGetFilterTabListener() { // from class: osb
            @Override // com.huawei.health.sport.utils.SportSupportUtil.OnFitnessGetFilterTabListener
            public final void onFilterTabSuccess(boolean z) {
                SportViewModel.this.c(z);
            }
        });
    }

    public /* synthetic */ void c(boolean z) {
        nqu nquVar = this.f9581a.get();
        if (nquVar == null) {
            return;
        }
        if (z) {
            this.d.postValue(nquVar);
        } else {
            this.n.postValue(nquVar);
        }
    }

    private nqu h(int i, SportSubTabConfig sportSubTabConfig) {
        if (i != 137) {
            if (i == 264) {
                return g(i, sportSubTabConfig);
            }
            if (i == 286) {
                return e(i, sportSubTabConfig);
            }
            if (i == 10001) {
                return b(i, sportSubTabConfig);
            }
            if (i == 500001) {
                return c(i, sportSubTabConfig);
            }
            if (i != 282) {
                if (i != 283) {
                    switch (i) {
                    }
                    return i(i, sportSubTabConfig);
                }
                return j(i, sportSubTabConfig);
            }
            return d(i, sportSubTabConfig);
        }
        if (CommonUtil.bb()) {
            return null;
        }
        return a(i, sportSubTabConfig);
    }

    private nqu a(int i, SportSubTabConfig sportSubTabConfig) {
        if (Utils.o() || !LanguageUtil.m(BaseApplication.e())) {
            return null;
        }
        return i(i, sportSubTabConfig);
    }

    private nqu b(int i, SportSubTabConfig sportSubTabConfig) {
        if (!SportSupportUtil.a()) {
            return null;
        }
        nqu i2 = i(i, sportSubTabConfig);
        this.f9581a = new WeakReference<>(i2);
        return i2;
    }

    private nqu d(int i, SportSubTabConfig sportSubTabConfig) {
        if (CommonUtil.bv() || CommonUtil.as() || CommonUtil.bu()) {
            return null;
        }
        return i(i, sportSubTabConfig);
    }

    private nqu e(int i, SportSubTabConfig sportSubTabConfig) {
        if (SportSupportUtil.l()) {
            return i(i, sportSubTabConfig);
        }
        return null;
    }

    private nqu c(int i, SportSubTabConfig sportSubTabConfig) {
        if (Utils.o() || !LanguageUtil.m(BaseApplication.e())) {
            if (CommonUtil.cc() || CommonUtil.aq()) {
                return i(i, sportSubTabConfig);
            }
            return null;
        }
        return i(i, sportSubTabConfig);
    }

    private nqu j(int i, SportSubTabConfig sportSubTabConfig) {
        if (!SportSupportUtil.m()) {
            LogUtil.a("Track_SportViewModel", "device not support skipping");
            return null;
        }
        return i(i, sportSubTabConfig);
    }

    private nqu g(int i, SportSubTabConfig sportSubTabConfig) {
        if (!this.h) {
            return null;
        }
        nqu i2 = i(i, sportSubTabConfig);
        this.b = new WeakReference<>(i2);
        return i2;
    }

    private nqu i(int i, SportSubTabConfig sportSubTabConfig) {
        int i2;
        LogUtil.a("Track_SportViewModel", "buildFragmentSubTabPagerBean sportType = ", Integer.valueOf(i));
        if (((FitnessAdviceApi) Services.a("PluginFitnessAdvice", FitnessAdviceApi.class)) == null) {
            return null;
        }
        String a2 = nrv.a(sportSubTabConfig);
        Bundle bundle = new Bundle();
        boolean z = false;
        if (i == 283) {
            i2 = R.string.IDS_indoor_skipper_rope_sport_type;
        } else if (i == 137) {
            i2 = R.string.IDS_start_fitness_type_yoga;
        } else if (i == 258) {
            i2 = R.string.IDS_start_track_sport_type_outdoor_run;
        } else if (i == 264) {
            i2 = R.string.IDS_start_track_sport_type_indoor_run;
        } else if (i == 260) {
            i2 = R.string.IDS_motiontrack_climb_hill_tip;
        } else if (i == 282) {
            i2 = R.string.IDS_hwh_sport_type_hiking;
        } else if (i == 257) {
            i2 = R.string._2130842252_res_0x7f02128c;
        } else if (i == 259) {
            i2 = R.string._2130842145_res_0x7f021221;
        } else if (i == 286) {
            i2 = R.string.IDS_motiontrack_golf_tip;
        } else if (i == 500001) {
            i2 = R.string._2130841729_res_0x7f021081;
            z = true;
        } else if (i == 10001) {
            i2 = R.string._2130837711_res_0x7f0200cf;
        } else {
            LogUtil.b("Track_SportViewModel", "no sportType found for KnitFragment type = ", Integer.valueOf(i));
            i2 = R.string.IDS_device_setting_other;
        }
        IPageResTrigger dgv_ = dgv_(i, bundle);
        if (dgv_ != null) {
            dgv_.setExtra(bundle).setIsNeedToLoadEmptyLayout(z);
        }
        KnitFragment newInstance = KnitFragment.newInstance(a2, dgv_);
        if (i == 283) {
            newInstance.setOnActivityResultListener(new KnitFragment.OnActivityResultListener() { // from class: ors
                @Override // com.huawei.health.knit.ui.KnitFragment.OnActivityResultListener
                public final void onActivityResult(int i3, int i4, Intent intent) {
                    SportViewModel.dgw_(i3, i4, intent);
                }
            });
        }
        nqu nquVar = new nqu(newInstance, this.c.getString(i2), i);
        nquVar.b(dgv_);
        return nquVar;
    }

    public static /* synthetic */ void dgw_(int i, int i2, Intent intent) {
        if (i == 102 && i2 == -1) {
            diy.c(BaseApplication.wa_(), true);
        }
    }

    private IPageResTrigger dgv_(int i, Bundle bundle) {
        orv orvVar;
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (i == 137) {
            orvVar = new orv(4020, 23, 23, 3024);
            arrayList.add(1008);
        } else if (i == 283) {
            orvVar = new orv(4019, 390, 27, 3023);
            arrayList.add(1013);
        } else if (i == 258) {
            orvVar = new orv(4012, 4, 4, 3016);
            arrayList.add(1005);
        } else if (i == 264) {
            orvVar = new orv(4013, GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN, 4, 3017);
            arrayList.add(1006);
        } else if (i == 260) {
            orvVar = new orv(4022, 26, 26, 3026);
            arrayList.add(1011);
        } else if (i == 282) {
            orvVar = new orv(4023, 25, 25, 3027);
            arrayList.add(1012);
        } else if (i == 257) {
            orvVar = new orv(4015, 5, 5, 3019);
            arrayList.add(1009);
        } else if (i == 259) {
            orvVar = new orv(4016, 6, 6, 3020);
            arrayList.add(1010);
        } else if (i == 286) {
            orvVar = new orv(4056, 420, 29, 3043);
        } else if (i == 500001) {
            orvVar = new orv(4040, 410, 0, 3042);
            bundle.putBoolean(BasePageResTrigger.KET_EXTRA_IS_SET_BI_ON_VISIBLE, true);
        } else if (i == 10001) {
            orvVar = new orv(4014, 22, 22, 3018);
            arrayList.add(1007);
        } else {
            LogUtil.h("Track_SportViewModel", "no res pos id found for sportType = ", Integer.valueOf(i));
            return null;
        }
        bundle.putIntegerArrayList(BasePageResTrigger.KEY_EXTRA_RES_POS_ID_LIST, arrayList);
        return new SportTabPageResTrigger(this.c, orvVar.c, new MarketingIdInfo.e().b(orvVar.f15923a).e(orvVar.d).a());
    }

    public void g() {
        if (this.e == null) {
            LogUtil.b("Track_SportViewModel", "mFragmentSubTabPagerBeans is null.");
            return;
        }
        if (koq.c(this.l)) {
            this.e.postValue(this.l);
            return;
        }
        this.k = l();
        this.q = osp.e();
        this.l = d(this.k);
        a(this.q);
        this.e.postValue(this.l);
    }

    public void j() {
        if (this.e == null) {
            LogUtil.b("Track_SportViewModel", "mFragmentSubTabPagerBeans is null.");
        } else {
            if (koq.c(this.l)) {
                return;
            }
            this.k = l();
            this.q = osp.e();
            this.l = d(this.k);
            a(this.q);
        }
    }

    public void h() {
        this.l.clear();
    }

    private List<SportSubTabConfig> l() {
        e(this.c, "SportTabConstructor.json");
        c("SportTabConstructor");
        rxy o = o();
        if (o != null) {
            BaseTemplateConfig b = o.b();
            if (b instanceof nig) {
                return ((nig) b).d();
            }
        }
        return null;
    }

    public void i() {
        if (CommonUtil.aa(this.c)) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.viewmodel.SportViewModel.4
                    @Override // java.lang.Runnable
                    public void run() {
                        SportViewModel.this.i();
                    }
                });
                return;
            }
            if (koq.b(this.k)) {
                this.k = l();
            }
            MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
            if (marketingApi != null) {
                Task<Map<Integer, ResourceResultInfo>> resourceResultInfo = marketingApi.getResourceResultInfo(7001);
                LogUtil.a("Track_SportViewModel", "start marketingApi getResourceResultInfo");
                resourceResultInfo.addOnSuccessListener(new OnSuccessListener<Map<Integer, ResourceResultInfo>>() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.viewmodel.SportViewModel.5
                    @Override // com.huawei.hmf.tasks.OnSuccessListener
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(Map<Integer, ResourceResultInfo> map) {
                        LogUtil.a("Track_SportViewModel", "getResourceResultInfo onSuccess = ", map);
                        if (map == null) {
                            LogUtil.h("Track_SportViewModel", "resourceResultInfoMap is null");
                            return;
                        }
                        ResourceResultInfo resourceResultInfo2 = map.get(7001);
                        if (resourceResultInfo2 == null) {
                            LogUtil.b("Track_SportViewModel", "resourceResultInfoMap not have key 7001 error");
                            return;
                        }
                        SportViewModel.this.o = resourceResultInfo2.getResources();
                        SportViewModel.this.m();
                    }
                });
            }
            d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (!this.j) {
            LogUtil.a("Track_SportViewModel", "[dealMergedSportTabData]: getSportTypeList is not ready.");
            return;
        }
        if (this.e == null) {
            LogUtil.b("Track_SportViewModel", "mFragmentSubTabPagerBeans is null.");
            return;
        }
        if (this.f && koq.c(this.l)) {
            this.f = false;
            this.e.postValue(this.l);
        }
        List<ResourceBriefInfo> list = this.o;
        if (list == null || list.size() == 0) {
            LogUtil.b("Track_SportViewModel", "resourceBriefInfoList.isEmpty()");
        } else {
            b(e(this.o), this.k);
            this.j = false;
        }
    }

    private int d(ResourceBriefInfo resourceBriefInfo) {
        TabGeneralTemplate a2;
        if (resourceBriefInfo == null || (a2 = a(resourceBriefInfo)) == null || a2.getSelectedTab() != 600001) {
            return -1;
        }
        LogUtil.a("Track_SportViewModel", "tabGeneralTemplate is ", a2.toString());
        return a2.getTabPosition();
    }

    private List<ResourceBriefInfo> e(List<ResourceBriefInfo> list) {
        if (koq.b(list)) {
            return null;
        }
        Collections.sort(list, new Comparator<ResourceBriefInfo>() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.viewmodel.SportViewModel.2
            @Override // java.util.Comparator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public int compare(ResourceBriefInfo resourceBriefInfo, ResourceBriefInfo resourceBriefInfo2) {
                return resourceBriefInfo2.getPriority() - resourceBriefInfo.getPriority();
            }
        });
        if (koq.b(this.q)) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.q.size(); i++) {
            for (ResourceBriefInfo resourceBriefInfo : list) {
                if (resourceBriefInfo != null && String.valueOf(c(resourceBriefInfo)).equals(this.q.get(i))) {
                    arrayList.add(resourceBriefInfo);
                }
            }
        }
        if (arrayList.size() != list.size()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                if (!arrayList.contains(list.get(i2))) {
                    arrayList.add(i2, list.get(i2));
                }
            }
        }
        return arrayList;
    }

    private nqu e(ResourceBriefInfo resourceBriefInfo) {
        H5SportFragment h5SportFragment = new H5SportFragment();
        Bundle bundle = new Bundle();
        TabGeneralTemplate a2 = a(resourceBriefInfo);
        int d = d(resourceBriefInfo);
        if (a2 == null) {
            return null;
        }
        bundle.putString("url", a2.getLinkValue());
        h5SportFragment.setArguments(bundle);
        return new nqu(h5SportFragment, b(resourceBriefInfo), d + 600001);
    }

    private void b(List<ResourceBriefInfo> list, List<SportSubTabConfig> list2) {
        nqu e2;
        if (koq.b(list) || koq.b(list2)) {
            return;
        }
        HashSet hashSet = new HashSet();
        ArrayList<nqu> arrayList = new ArrayList<>();
        HashMap hashMap = new HashMap();
        for (ResourceBriefInfo resourceBriefInfo : list) {
            if (resourceBriefInfo != null) {
                int c = c(resourceBriefInfo);
                if (!hashSet.contains(Integer.valueOf(c)) || c == 600001) {
                    if (c == 600001) {
                        int d = d(resourceBriefInfo);
                        ReleaseLogUtil.e("Track_SportViewModel", "dealMergedSportTabBeans sport");
                        e2 = e(resourceBriefInfo);
                        hashMap.put(Integer.valueOf(d), e2);
                    } else {
                        e2 = b(resourceBriefInfo, b(c, list2));
                        if (e2 != null) {
                            hashSet.add(Integer.valueOf(c));
                            arrayList.add(e2);
                        }
                    }
                    c(resourceBriefInfo, e2);
                }
            }
        }
        LogUtil.a("Track_SportViewModel", "request market config and show ui");
        if (b(arrayList, hashMap)) {
            if (this.l == null) {
                this.l = new ArrayList<>();
            }
            nqu.c(arrayList, this.l);
            g(arrayList);
            for (Map.Entry<Integer, nqu> entry : hashMap.entrySet()) {
                LogUtil.a("Track_SportViewModel", "position is ", entry.getKey());
                this.l.remove(entry.getValue());
                if (koq.d(this.l, entry.getKey().intValue())) {
                    this.l.add(entry.getKey().intValue(), entry.getValue());
                } else {
                    this.l.add(entry.getValue());
                }
            }
            if (n()) {
                return;
            }
            this.e.postValue(this.l);
        }
    }

    private void g(List<nqu> list) {
        nqu nquVar;
        for (nqu nquVar2 : list) {
            if (nquVar2 != null && nquVar2.c() == 500001) {
                Iterator<nqu> it = this.l.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        nquVar = null;
                        break;
                    }
                    nquVar = it.next();
                    if (nquVar != null && nquVar.c() == 500001) {
                        it.remove();
                        break;
                    }
                }
                if (nquVar != null) {
                    this.l.add(0, nquVar);
                    return;
                }
                return;
            }
        }
    }

    private boolean n() {
        ArrayList<nqu> arrayList = this.l;
        if (arrayList == null) {
            return false;
        }
        Iterator<nqu> it = arrayList.iterator();
        while (it.hasNext()) {
            final nqu next = it.next();
            if (next != null && next.c() == 500001) {
                Object e2 = next.e();
                if (!(e2 instanceof BasePageResTrigger)) {
                    return false;
                }
                final BasePageResTrigger basePageResTrigger = (BasePageResTrigger) e2;
                basePageResTrigger.onPageLoadOnlineSections(new Consumer() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.viewmodel.SportViewModel$$ExternalSyntheticLambda4
                    @Override // androidx.core.util.Consumer
                    public final void accept(Object obj) {
                        SportViewModel.this.e(basePageResTrigger, next, (List) obj);
                    }
                }, null);
                return true;
            }
        }
        return false;
    }

    /* synthetic */ void e(BasePageResTrigger basePageResTrigger, nqu nquVar, List list) {
        if (!koq.b(list)) {
            basePageResTrigger.setCacheBeansList(list);
        } else {
            this.l.remove(nquVar);
        }
        this.e.postValue(this.l);
    }

    private nqu f() {
        FitnessAdviceApi fitnessAdviceApi = (FitnessAdviceApi) Services.a("PluginFitnessAdvice", FitnessAdviceApi.class);
        BaseFragment createIntelligencePlanTabFragment = fitnessAdviceApi != null ? fitnessAdviceApi.createIntelligencePlanTabFragment() : null;
        if (createIntelligencePlanTabFragment == null) {
            LogUtil.h("Track_SportViewModel", "constructSportPlanFragment  tabFragment == null");
            return null;
        }
        return new nqu(createIntelligencePlanTabFragment, this.c.getString(R.string._2130848489_res_0x7f022ae9), 2);
    }

    private boolean b(ArrayList<nqu> arrayList, Map<Integer, nqu> map) {
        if (!map.isEmpty()) {
            return true;
        }
        if (!koq.b(arrayList) && !koq.b(this.l)) {
            if (arrayList.size() != this.l.size()) {
                return true;
            }
            for (int i = 0; i < arrayList.size(); i++) {
                if (arrayList.get(i).c() != this.l.get(i).c()) {
                    return true;
                }
            }
        }
        return false;
    }

    private int c(ResourceBriefInfo resourceBriefInfo) {
        TabGeneralTemplate a2;
        if (resourceBriefInfo == null || (a2 = a(resourceBriefInfo)) == null) {
            return -1;
        }
        LogUtil.a("Track_SportViewModel", "tabGeneralTemplate is ", a2.toString());
        if (a2.getSelectedTab() != 0) {
            return a2.getSelectedTab();
        }
        return -1;
    }

    private String b(ResourceBriefInfo resourceBriefInfo) {
        TabGeneralTemplate a2 = a(resourceBriefInfo);
        return a2 != null ? a2.getName() : "";
    }

    private TabGeneralTemplate a(ResourceBriefInfo resourceBriefInfo) {
        if (resourceBriefInfo == null) {
            return null;
        }
        try {
            return (TabGeneralTemplate) new Gson().fromJson(resourceBriefInfo.getContent().getContent(), TabGeneralTemplate.class);
        } catch (JsonSyntaxException e2) {
            LogUtil.b("Track_SportViewModel", "getOperationSportType exception = ", LogAnonymous.b((Throwable) e2));
            return null;
        }
    }

    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void d() {
        if (HandlerExecutor.b()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: orw
                @Override // java.lang.Runnable
                public final void run() {
                    SportViewModel.this.d();
                }
            });
            return;
        }
        List<String> c = osp.c();
        if (c != null) {
            if (koq.b(this.q) && !koq.b(c)) {
                a(c);
                this.f = true;
                osp.c(c);
            }
            this.q = c;
        }
        this.m.sendEmptyMessage(100);
    }

    private void e(Context context, String str) {
        InputStream inputStream = null;
        AssetManager assets = context != null ? context.getAssets() : null;
        try {
            if (assets == null) {
                return;
            }
            try {
                inputStream = assets.open(str);
                this.g = (rxy) ixu.e(inputStream, new TypeToken<rxy<nig>>() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.viewmodel.SportViewModel.3
                });
            } catch (IOException | NullPointerException e2) {
                LogUtil.b("Track_SportViewModel", "sport template assets load failed: ", LogAnonymous.b(e2));
            }
        } finally {
            IoUtils.e(inputStream);
        }
    }

    private void c(String str) {
        this.i = (rxy) ixu.e(rxx.b(str), new TypeToken<rxy<nig>>() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.viewmodel.SportViewModel.1
        });
    }

    private rxy o() {
        rxy<nig> rxyVar = this.g;
        String c = rxyVar != null ? rxyVar.c() : null;
        rxy<nig> rxyVar2 = this.i;
        String c2 = rxyVar2 != null ? rxyVar2.c() : null;
        if (TextUtils.isEmpty(c)) {
            return this.i;
        }
        if (TextUtils.isEmpty(c2)) {
            return this.g;
        }
        return CommonUtil.d(c, c2) > 0 ? this.g : this.i;
    }

    private ArrayList<nqu> d(List<SportSubTabConfig> list) {
        ArrayList<nqu> arrayList = new ArrayList<>();
        if (koq.b(list)) {
            return arrayList;
        }
        LogUtil.c("Track_SportViewModel", "tabConfigList size: ", Integer.valueOf(list.size()));
        Iterator it = new CopyOnWriteArrayList(list).iterator();
        while (it.hasNext()) {
            SportSubTabConfig sportSubTabConfig = (SportSubTabConfig) it.next();
            if (sportSubTabConfig.getPageType() != 500001 || (!CommonUtil.bu() && !LoginInit.getInstance(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).isKidAccount() && !Utils.o() && LanguageUtil.m(com.huawei.hwcommonmodel.application.BaseApplication.getContext()))) {
                nqu b = b((ResourceBriefInfo) null, sportSubTabConfig);
                if (b != null) {
                    arrayList.add(b);
                }
            }
        }
        return arrayList;
    }

    private SportSubTabConfig b(int i, List<SportSubTabConfig> list) {
        if (list == null) {
            return null;
        }
        for (SportSubTabConfig sportSubTabConfig : list) {
            if (sportSubTabConfig != null && i == sportSubTabConfig.getPageType()) {
                return sportSubTabConfig;
            }
        }
        return null;
    }

    private nqu b(ResourceBriefInfo resourceBriefInfo, SportSubTabConfig sportSubTabConfig) {
        LogUtil.a("Track_SportViewModel", "getFragmentSubTabPagerBean");
        if (resourceBriefInfo == null && sportSubTabConfig == null) {
            return null;
        }
        int pageType = sportSubTabConfig != null ? sportSubTabConfig.getPageType() : 0;
        if (resourceBriefInfo != null) {
            pageType = c(resourceBriefInfo);
        }
        if (pageType != 0 && pageType != -1) {
            LogUtil.a("Track_SportViewModel", "tabConfig sportType: ", Integer.valueOf(pageType));
            if (pageType == 2) {
                return f();
            }
            if (nsn.ae(com.huawei.hwcommonmodel.application.BaseApplication.getContext()) && (pageType == 258 || pageType == 264 || pageType == 257 || pageType == 259 || pageType == 282 || pageType == 260)) {
                return null;
            }
            return h(pageType, sportSubTabConfig);
        }
        LogUtil.h("Track_SportViewModel", "sportType is default");
        return null;
    }

    private void c(ResourceBriefInfo resourceBriefInfo, nqu nquVar) {
        TabGeneralTemplate a2;
        if (resourceBriefInfo == null || nquVar == null || (a2 = a(resourceBriefInfo)) == null) {
            return;
        }
        LogUtil.a("Track_SportViewModel", "tabGeneralTemplate is ", a2.toString());
        nquVar.e(a2.getName());
        nquVar.c(a2.getPicture());
        nquVar.a(a2.getCornerIcon());
    }

    public void b(BaseFragment baseFragment, Observer<ArrayList<nqu>> observer) {
        this.e.observe(baseFragment, observer);
    }

    public void a(BaseFragment baseFragment, Observer<nqu> observer) {
        this.d.observe(baseFragment, observer);
    }

    public void d(BaseFragment baseFragment, Observer<nqu> observer) {
        this.n.observe(baseFragment, observer);
    }

    public ArrayList<nqu> b(List<nmk> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        final ArrayList arrayList = new ArrayList();
        for (nmk nmkVar : list) {
            if (nmkVar != null) {
                arrayList.add(String.valueOf(nmkVar.a()));
            }
        }
        if (HandlerExecutor.b()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: ort
                @Override // java.lang.Runnable
                public final void run() {
                    SportViewModel.c(arrayList);
                }
            });
        }
        return f(list);
    }

    public static /* synthetic */ void c(List list) {
        osp.c(list);
        osp.b((List<String>) list);
    }

    private ArrayList<nqu> f(List<nmk> list) {
        if (this.l == null) {
            return null;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.l.size(); i2++) {
            if (this.l.get(i2).c() == 1) {
                i++;
            } else {
                int i3 = i2 - i;
                if (koq.d(list, i3) && list.get(i3).a() != this.l.get(i2).c()) {
                    return i(list);
                }
            }
        }
        return null;
    }

    private ArrayList<nqu> i(List<nmk> list) {
        ArrayList<nqu> arrayList = new ArrayList<>();
        for (nmk nmkVar : list) {
            Iterator<nqu> it = this.l.iterator();
            while (it.hasNext()) {
                nqu next = it.next();
                if (next.c() == nmkVar.a()) {
                    arrayList.add(next);
                }
            }
        }
        for (int i = 0; i < this.l.size(); i++) {
            if (this.l.get(i).c() == 1) {
                arrayList.add(i, this.l.get(i));
            }
        }
        this.l.clear();
        this.l.addAll(arrayList);
        return arrayList;
    }

    private void a(List<String> list) {
        if (this.l == null || koq.b(list)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        TreeMap treeMap = new TreeMap();
        Iterator<nqu> it = this.l.iterator();
        while (it.hasNext()) {
            nqu next = it.next();
            if (next != null) {
                treeMap.put(Integer.valueOf(next.c()), next);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            int h = CommonUtil.h(list.get(i));
            if (treeMap.containsKey(Integer.valueOf(h))) {
                arrayList.add((nqu) treeMap.get(Integer.valueOf(h)));
                treeMap.remove(Integer.valueOf(h));
            }
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            if (this.l == null) {
                break;
            }
            ArrayList arrayList2 = new ArrayList(this.l);
            for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                nqu nquVar = (nqu) arrayList2.get(i2);
                if (nquVar != null && nquVar.c() == ((Integer) entry.getKey()).intValue()) {
                    if (i2 <= arrayList.size()) {
                        arrayList.add(i2, (nqu) entry.getValue());
                    } else {
                        arrayList.add((nqu) entry.getValue());
                    }
                }
            }
        }
        ArrayList<nqu> arrayList3 = new ArrayList<>();
        this.l = arrayList3;
        arrayList3.addAll(arrayList);
    }

    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        ArrayList<nqu> arrayList = this.l;
        if (arrayList != null) {
            arrayList.clear();
            this.l = null;
        }
        List<SportSubTabConfig> list = this.k;
        if (list != null) {
            list.clear();
            this.k = null;
        }
        List<ResourceBriefInfo> list2 = this.o;
        if (list2 != null) {
            list2.clear();
            this.o = null;
        }
        this.e = null;
    }

    static class e extends BaseHandler<SportViewModel> {
        e(SportViewModel sportViewModel) {
            super(sportViewModel);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dgx_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(SportViewModel sportViewModel, Message message) {
            if (message.what == 100) {
                sportViewModel.j = true;
                sportViewModel.m();
            }
        }
    }

    public ArrayList<nqu> b() {
        return this.l;
    }

    public void c() {
        ArrayList<nqu> arrayList = this.l;
        if (arrayList != null) {
            arrayList.clear();
        }
    }
}
