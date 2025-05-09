package com.huawei.healthcloud.plugintrack.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.motiontrack.model.runningroute.HotPathCityInfo;
import com.huawei.health.trackprocess.model.GpsPoint;
import com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteLocationHelper;
import com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteUtils;
import com.huawei.healthcloud.plugintrack.ui.activity.RunningCitysActivity;
import com.huawei.healthcloud.plugintrack.ui.adapter.RouteCityExpandableListAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlocationmgr.model.ILoactionCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.BaseGroupDataAdapter;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.model.ICityLatLonCallBack;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.emc;
import defpackage.emm;
import defpackage.emo;
import defpackage.emp;
import defpackage.gzi;
import defpackage.hje;
import defpackage.jdi;
import defpackage.koq;
import defpackage.ktg;
import defpackage.ktj;
import defpackage.npq;
import defpackage.npv;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class RunningCitysActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HotPathCityInfo f3658a;
    private ExpandableListView b;
    private List<hje> c = new ArrayList();
    private HealthTextView d;
    private HotPathCityInfo e;
    private RouteCityExpandableListAdapter f;
    private RunningRouteLocationHelper g;
    private HealthSubHeader h;
    private HealthButton i;
    private HealthSubHeader j;
    private CustomTitleBar k;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawable(null);
        setContentView(R.layout.activity_running_citys);
        f();
        a();
        c();
    }

    private void f() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.title_bar);
        this.k = customTitleBar;
        customTitleBar.setTitleText(getString(R.string._2130840071_res_0x7f020a07));
        this.k.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: hev
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RunningCitysActivity.this.bcp_(view);
            }
        });
    }

    public /* synthetic */ void bcp_(View view) {
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a() {
        this.b = (ExpandableListView) findViewById(R.id.expandable_country_list);
        RouteCityExpandableListAdapter routeCityExpandableListAdapter = new RouteCityExpandableListAdapter();
        this.f = routeCityExpandableListAdapter;
        this.b.setAdapter(routeCityExpandableListAdapter);
        View inflate = LayoutInflater.from(this).inflate(R.layout.running_citys_head, (ViewGroup) null);
        bcl_(inflate);
        this.b.addHeaderView(inflate);
        this.b.setSelector(new ColorDrawable(0));
        this.f.setOnItemClickListener(new BaseGroupDataAdapter.OnItemClickListener() { // from class: hes
            @Override // com.huawei.ui.commonui.adapter.BaseGroupDataAdapter.OnItemClickListener
            public final void onItemClickListener(int i, int i2) {
                RunningCitysActivity.this.a(i, i2);
            }
        });
    }

    public /* synthetic */ void a(int i, int i2) {
        if (nsn.a(1200)) {
            LogUtil.a("Track_RunningCitysActivity", "isClickFast");
        } else {
            if (koq.b(this.c, i) || this.c.get(i) == null) {
                return;
            }
            c(this.c.get(i).d(i2));
        }
    }

    private void c() {
        this.g = new RunningRouteLocationHelper();
        HotPathCityInfo d = gzi.d();
        this.f3658a = d;
        if (d != null && !TextUtils.isEmpty(d.getCityName())) {
            this.k.setTitleText(getString(R.string._2130840078_res_0x7f020a0e, new Object[]{this.f3658a.getCityName()}));
        }
        b();
        gzi.d(false);
        d();
    }

    private void d() {
        emc.d().getAllCityInfoList(new emm(), new UiCallback<emo>() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.RunningCitysActivity.3
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.a("Track_RunningCitysActivity", "getCityInfoList onFailure errorCode = ", Integer.valueOf(i), " errorInfo = ", str);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(emo emoVar) {
                if (emoVar == null) {
                    LogUtil.a("Track_RunningCitysActivity", "getCityInfoList data == null");
                    return;
                }
                Map<String, List<HotPathCityInfo>> a2 = emoVar.a();
                if (!CollectionUtils.e(a2)) {
                    RunningCitysActivity.this.e(a2);
                } else {
                    LogUtil.a("Track_RunningCitysActivity", "getCityInfoList map is empty");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Map<String, List<HotPathCityInfo>> map) {
        this.c.clear();
        hje hjeVar = null;
        for (Map.Entry<String, List<HotPathCityInfo>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<HotPathCityInfo> value = entry.getValue();
            if (!koq.b(value)) {
                hje hjeVar2 = new hje(key, value.get(0).getCountryName());
                value.sort(new Comparator() { // from class: heq
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        int compare;
                        compare = Collator.getInstance().compare(((HotPathCityInfo) obj).getCityName(), ((HotPathCityInfo) obj2).getCityName());
                        return compare;
                    }
                });
                hjeVar2.setChildList(value);
                HotPathCityInfo hotPathCityInfo = this.f3658a;
                if (hotPathCityInfo == null || !key.equals(hotPathCityInfo.getCountryId())) {
                    this.c.add(hjeVar2);
                } else {
                    hjeVar = hjeVar2;
                }
            }
        }
        this.c.sort(new Comparator() { // from class: hen
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Collator.getInstance().compare(((hje) obj).e(), ((hje) obj2).e());
                return compare;
            }
        });
        if (hjeVar != null) {
            this.c.add(0, hjeVar);
        }
        LogUtil.a("Track_RunningCitysActivity", "initListData mAllCountryData size ", Integer.valueOf(this.c.size()));
        this.f.refresh(this.c);
        if (koq.b(this.c)) {
            return;
        }
        this.b.expandGroup(0);
    }

    private boolean j() {
        boolean e2 = e();
        if (e2 && ktj.e(BaseApplication.getContext()) && CommonUtil.aa(BaseApplication.getContext())) {
            return true;
        }
        new Handler(Looper.myLooper()).postDelayed(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.RunningCitysActivity.2
            @Override // java.lang.Runnable
            public void run() {
                if (RunningCitysActivity.this.isFinishing() && RunningCitysActivity.this.isDestroyed()) {
                    return;
                }
                RunningCitysActivity.this.d.setText(RunningCitysActivity.this.getString(R.string._2130840075_res_0x7f020a0b));
            }
        }, 300L);
        if (!e2) {
            RunningRouteUtils.a(1, "Unauthorized");
            return false;
        }
        RunningRouteUtils.a(1, "PositioningFailed");
        return false;
    }

    private boolean e() {
        return jdi.c(BaseApplication.getContext(), new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"});
    }

    private void b() {
        this.e = null;
        this.d.setText(getString(R.string._2130840074_res_0x7f020a0a));
        if (j()) {
            ktg.c().e(new e(this), "Track_RunningCitysActivity");
            this.g.getLocation(null, new ICityLatLonCallBack() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.RunningCitysActivity.5
                @Override // com.huawei.ui.commonui.model.ICityLatLonCallBack
                public void onPointBack(npv npvVar) {
                    npq npqVar;
                    RunningCitysActivity.this.e = new HotPathCityInfo();
                    if (npvVar != null) {
                        RunningCitysActivity.this.e.setCityName(npvVar.b());
                        npqVar = npvVar.d();
                    } else {
                        npqVar = null;
                    }
                    RunningCitysActivity.this.e.setCityId("INVALIDE_CITY_ID");
                    if (npqVar != null) {
                        RunningCitysActivity.this.e.setCityCenter(new GpsPoint(npqVar.f15429a, npqVar.c));
                    }
                    if (TextUtils.isEmpty(RunningCitysActivity.this.e.getCityName())) {
                        return;
                    }
                    nsy.cMr_(RunningCitysActivity.this.d, RunningCitysActivity.this.e.getCityName());
                }
            }, new UiCallback<emp>() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.RunningCitysActivity.4
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    if (RunningCitysActivity.this.e == null) {
                        RunningCitysActivity.this.d.setText(RunningCitysActivity.this.getString(R.string._2130840075_res_0x7f020a0b));
                    }
                    if (RunningCitysActivity.this.g.isLocationFailed(i)) {
                        RunningRouteUtils.a(1, "PositioningFailed");
                    }
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(emp empVar) {
                    RunningRouteUtils.a(1, (String) null);
                    RunningRouteUtils.e(empVar.a());
                    HotPathCityInfo c = empVar.c();
                    if (c != null) {
                        RunningCitysActivity.this.e = c;
                        RunningCitysActivity.this.d.setText(c.getCityName());
                    }
                }
            });
        }
    }

    private void c(HotPathCityInfo hotPathCityInfo) {
        Intent intent = new Intent();
        if (hotPathCityInfo != null) {
            boolean z = gzi.d() == null || !TextUtils.equals(gzi.d().getCityId(), hotPathCityInfo.getCityId());
            ReleaseLogUtil.e("Track_RunningCitysActivity", "isCityChange, ", Boolean.valueOf(z));
            gzi.d(hotPathCityInfo);
            intent.putExtra("IS_CITY_CHANGE", z);
        }
        intent.putExtra("IS_SHOW_PATH_DETAIL", false);
        setResult(-1, intent);
        finish();
    }

    private void bcl_(View view) {
        this.h = (HealthSubHeader) view.findViewById(R.id.subHeaderCurrentLocation);
        this.j = (HealthSubHeader) view.findViewById(R.id.subHeaderTopCitys);
        this.h.setSubHeaderBackgroundColor(getResources().getColor(R.color._2131299296_res_0x7f090be0));
        this.j.setSubHeaderBackgroundColor(getResources().getColor(R.color._2131299296_res_0x7f090be0));
        this.h.setHeadTitleText(getString(R.string._2130840076_res_0x7f020a0c));
        this.j.setHeadTitleText(getString(R.string._2130840077_res_0x7f020a0d));
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.text1);
        this.d = healthTextView;
        healthTextView.setText(getString(R.string._2130840071_res_0x7f020a07));
        HealthButton healthButton = (HealthButton) view.findViewById(R.id.btn);
        this.i = healthButton;
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: heu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RunningCitysActivity.this.bcn_(view2);
            }
        });
        this.d.setOnClickListener(new View.OnClickListener() { // from class: het
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RunningCitysActivity.this.bco_(view2);
            }
        });
    }

    public /* synthetic */ void bcn_(View view) {
        b();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void bco_(View view) {
        HotPathCityInfo hotPathCityInfo = this.e;
        if (hotPathCityInfo != null) {
            c(hotPathCityInfo);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void bcm_(Activity activity, HotPathCityInfo hotPathCityInfo, int i) {
        gzi.d(hotPathCityInfo);
        activity.startActivityForResult(new Intent(activity, (Class<?>) RunningCitysActivity.class), i);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ReleaseLogUtil.e("Track_RunningCitysActivity", "onDestroy");
        ktg.c().a("Track_RunningCitysActivity");
    }

    static class e implements ILoactionCallback {
        private final WeakReference<RunningCitysActivity> d;

        public e(RunningCitysActivity runningCitysActivity) {
            this.d = new WeakReference<>(runningCitysActivity);
        }

        @Override // com.huawei.hwlocationmgr.model.ILoactionCallback
        public void dispatchLocationChanged(Location location) {
            LogUtil.a("Track_RunningCitysActivity", "onLocationChange with ", location);
            if (this.d.get() != null) {
                ktg.c().a("Track_RunningCitysActivity");
                ReleaseLogUtil.e("Track_RunningCitysActivity", "removeLocationUpdates");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
