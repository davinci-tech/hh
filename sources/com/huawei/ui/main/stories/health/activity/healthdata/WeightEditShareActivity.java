package com.huawei.ui.main.stories.health.activity.healthdata;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.github.mikephil.charting.components.XAxis;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.commonui.view.ShareSquareLayout;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.WeightEditShareActivity;
import com.huawei.ui.main.stories.health.fragment.WeightEditShareFragment;
import com.huawei.ui.main.stories.health.interactors.healthdata.WeightShareEditListener;
import com.huawei.ui.main.stories.health.model.weight.card.CardConstants;
import com.huawei.ui.main.stories.health.weight.callback.FastingLiteCbk;
import com.huawei.ui.main.stories.health.weight.callback.FastingLiteRepository;
import com.huawei.ui.main.stories.nps.interactors.mode.TypeParams;
import com.huawei.uikit.hwviewpager.widget.HwViewPager;
import defpackage.cpa;
import defpackage.dph;
import defpackage.koq;
import defpackage.nov;
import defpackage.now;
import defpackage.nqx;
import defpackage.nrf;
import defpackage.nrr;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.qrp;
import defpackage.qsj;
import defpackage.quv;
import defpackage.qva;
import defpackage.qvc;
import health.compact.a.HiDateUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class WeightEditShareActivity extends BaseActivity implements WeightShareEditListener {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f10096a = {"A", "B", TypeParams.SEARCH_CODE};
    private static final String[] c = {"weight_share_background_1", "weight_share_background_2", "weight_share_background_3"};
    private SocialShareCenterApi aa;
    private CustomTitleBar ab;
    private HealthViewPager ac;
    private nqx ad;
    private HealthTextView af;
    private CustomViewDialog ag;
    private HealthTextView ah;
    private Bitmap b;
    private HealthSubTabWidget d;
    private WeightEditShareFragment e;
    private HealthTextView f;
    private HealthColumnSystem g;
    private float h;
    private Context i;
    private HealthTextView j;
    private e k;
    private WeightEditShareFragment m;
    private int n;
    private float o;
    private float p;
    private RelativeLayout r;
    private HwHealthLineChart s;
    private HealthScrollView t;
    private HealthTextView w;
    private ShareSquareLayout x;
    private int y;
    private HealthTextView z;
    private int l = -1;
    private int u = 0;
    private int q = 0;
    private int v = 0;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        overridePendingTransition(R.anim._2130772057_res_0x7f010059, R.anim._2130772061_res_0x7f01005d);
        super.onCreate(bundle);
        setContentView(R.layout.activity_weight_edit_share);
        cancelAdaptRingRegion();
        this.i = this;
        this.g = new HealthColumnSystem(this, 1);
        this.k = new e(this);
        this.aa = (SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class);
        k();
        n();
        j();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        n();
        i();
        refreshWeightShareData(0, false);
        refreshWeightShareData(1, false);
        refreshWeightShareData(2, false);
    }

    private void n() {
        Context context;
        if (this.x == null || (context = this.i) == null) {
            return;
        }
        if (nsn.ag(context)) {
            this.p = this.i.getResources().getDimension(R.dimen._2131364635_res_0x7f0a0b1b);
            if (nsn.ae(this.i)) {
                this.h = this.g.d(6);
            } else {
                this.h = this.g.d(4);
            }
            this.o = this.p + this.h;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.height = (int) this.h;
            layoutParams.width = (int) this.h;
            layoutParams.addRule(14);
            layoutParams.setMargins(nsn.c(this.i, this.o), nsn.c(this.i, 28.0f), nsn.c(this.i, this.o), nsn.c(this.i, 0.0f));
            this.x.setLayoutParams(layoutParams);
            int c2 = nsn.c(this.i, 28.0f);
            int d2 = (int) (this.g.d(2) + nrr.b(this.i) + this.p);
            if (nsn.ae(this.i)) {
                d2 = (int) (this.g.d(1) + nrr.b(this.i) + this.p);
            }
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, c2 + nsn.c(this.i, 8.0f));
            layoutParams2.setMarginStart(d2);
            layoutParams2.setMarginEnd(d2);
            this.r.setLayoutParams(layoutParams2);
            return;
        }
        this.x.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.height = nsn.c(this.i, 48.0f);
        this.r.setLayoutParams(layoutParams3);
    }

    private void j() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: qfj
            @Override // java.lang.Runnable
            public final void run() {
                WeightEditShareActivity.this.d();
            }
        });
    }

    public /* synthetic */ void d() {
        FastingLiteRepository.getFastingLiteTasks(new FastingLiteCbk<qva[]>() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.WeightEditShareActivity.5
            @Override // com.huawei.ui.main.stories.health.weight.callback.FastingLiteCbk
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(qva[] qvaVarArr) {
                if (WeightEditShareActivity.this.k == null || qvaVarArr == null || qvaVarArr.length <= 0) {
                    return;
                }
                int i = 0;
                qva qvaVar = qvaVarArr[0];
                long d2 = qvaVar.d() * 1000;
                LogUtil.a("WeightEditShareActivity", "joinTime = ", HiDateUtil.b(d2, "yyyy-MM-dd HH:mm:ss:SSS"));
                int c2 = HiDateUtil.c(d2);
                int c3 = HiDateUtil.c(System.currentTimeMillis());
                if (c2 == c3) {
                    LogUtil.a("WeightEditShareActivity", "startDay = currentDay");
                    d2 = HiDateUtil.t(d2);
                }
                WeightEditShareActivity.this.b(d2);
                try {
                    i = HiDateUtil.b(c2, c3, "yyyyMMdd");
                } catch (ParseException unused) {
                    LogUtil.b("WeightEditShareActivity", "getJoinDays ParseException");
                }
                LogUtil.a("WeightEditShareActivity", "startDay = ", Integer.valueOf(c2), ", currentDay = ", Integer.valueOf(c3), ", joinDays = ", Integer.valueOf(i));
                WeightEditShareActivity.this.d(qvaVar, i);
            }

            @Override // com.huawei.ui.main.stories.health.weight.callback.FastingLiteCbk
            public void onFailure(int i, String str) {
                LogUtil.h("WeightEditShareActivity", "getFastingLiteTasks onFailure errorCode = ", Integer.valueOf(i), ",msg = ", str);
            }
        }, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(qva qvaVar, int i) {
        int d2;
        int d3;
        quv a2 = qvaVar.a();
        if (a2 != null) {
            d2 = d(a2.c(), a2.b(), true);
            d3 = d(a2.c(), a2.b(), false);
        } else {
            d2 = d(0, null, true);
            d3 = d(0, null, false);
        }
        Message obtainMessage = this.k.obtainMessage();
        obtainMessage.what = 0;
        obtainMessage.arg1 = d2;
        obtainMessage.arg2 = d3;
        obtainMessage.obj = Integer.valueOf(i);
        this.k.sendMessage(obtainMessage);
    }

    private int d(int i, qvc qvcVar, boolean z) {
        if (i == 0) {
            return z ? 10 : 14;
        }
        if (i == 1) {
            return z ? 8 : 16;
        }
        if (i == 2) {
            return z ? 6 : 18;
        }
        if (i != 3) {
            return d(qvcVar, z);
        }
        return z ? 4 : 20;
    }

    private int d(qvc qvcVar, boolean z) {
        if (qvcVar == null) {
            LogUtil.h("WeightEditShareActivity", "getCustomFastingOrDietHours setting is null");
            return z ? 10 : 14;
        }
        if (z) {
            return (int) (qvcVar.c() / 3600);
        }
        return (int) ((qvcVar.a() - qvcVar.c()) / 3600);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(long j) {
        LogUtil.c("WeightEditShareActivity", "getLastWeightData startJoinTimeStamp = ", Long.valueOf(j));
        long[] a2 = qsj.a(new Date(), 12);
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeRange(a2[1], a2[0]);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setType(new int[]{10006});
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setFilter("NULL");
        hiAggregateOption.setConstantsKey(new String[]{BleConstants.WEIGHT_KEY});
        a(j, hiAggregateOption);
    }

    private void a(final long j, HiAggregateOption hiAggregateOption) {
        HiHealthManager.d(BaseApplication.e()).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.WeightEditShareActivity.4
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                double d2;
                long j2;
                double d3;
                if (koq.b(list)) {
                    LogUtil.h("WeightEditShareActivity", "getLastWeightData data is empty ");
                    WeightEditShareActivity.this.e(-1, 0.0d, 0.0d, 0.0d);
                    return;
                }
                int i3 = 0;
                while (true) {
                    d2 = 0.0d;
                    if (i3 >= list.size()) {
                        j2 = 0;
                        d3 = 0.0d;
                        break;
                    }
                    HiHealthData hiHealthData = list.get(i3);
                    double d4 = hiHealthData.getDouble("bodyWeight");
                    if (d4 > 0.0d) {
                        d2 = WeightEditShareActivity.this.a(hiHealthData.getInt("trackdata_deviceType"), d4);
                        double d5 = hiHealthData.getDouble(BleConstants.BODY_FAT_RATE);
                        j2 = hiHealthData.getStartTime();
                        d3 = d5;
                        break;
                    }
                    i3++;
                }
                LogUtil.c("WeightEditShareActivity", "requestHealthData latestWeight = ", Double.valueOf(d2), ", latestBodyFate = ", Double.valueOf(d3), ", lastWeightTime = ", HiDateUtil.b(j2, "yyyy-MM-dd HH:mm:ss:SSS"));
                if (j2 == 0) {
                    LogUtil.a("WeightEditShareActivity", "requestHealthData can not find weightData");
                    WeightEditShareActivity.this.e(-1, 0.0d, 0.0d, 0.0d);
                    return;
                }
                long j3 = j;
                if (j2 > j3) {
                    WeightEditShareActivity.this.c(j3, d2, d3);
                } else {
                    LogUtil.a("WeightEditShareActivity", "requestHealthData lastWeightTime <= startJoinTimeStamp");
                    WeightEditShareActivity.this.e(-1, d2, d3, 0.0d);
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
                LogUtil.a("WeightEditShareActivity", "requestHealthData onResultIntent errorCode:", Integer.valueOf(i2));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(long j, final double d2, final double d3) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setType(new int[]{10006});
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setStartTime(j);
        hiAggregateOption.setEndTime(System.currentTimeMillis());
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setFilter("NULL");
        hiAggregateOption.setConstantsKey(new String[]{BleConstants.WEIGHT_KEY});
        HiHealthManager.d(BaseApplication.e()).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.WeightEditShareActivity.3
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                double d4;
                if (koq.b(list)) {
                    LogUtil.h("WeightEditShareActivity", "getJoinLatestWeight data is empty ");
                    WeightEditShareActivity.this.e(-1, d2, d3, 0.0d);
                    return;
                }
                int size = list.size();
                while (true) {
                    size--;
                    if (size < 0) {
                        d4 = 0.0d;
                        break;
                    }
                    HiHealthData hiHealthData = list.get(size);
                    double d5 = hiHealthData.getDouble("bodyWeight");
                    if (d5 > 0.0d) {
                        d4 = WeightEditShareActivity.this.a(hiHealthData.getInt("trackdata_deviceType"), d5);
                        break;
                    }
                }
                LogUtil.c("WeightEditShareActivity", "getWeightDiffData joinLatestWeight = ", Double.valueOf(d4));
                if (d4 > 0.0d) {
                    WeightEditShareActivity.this.e(0, d2, d3, d4);
                } else {
                    WeightEditShareActivity.this.e(-1, d2, d3, d4);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, double d2, double d3, double d4) {
        e eVar = this.k;
        if (eVar == null) {
            LogUtil.h("WeightEditShareActivity", "mHandler = null");
            return;
        }
        Message obtainMessage = eVar.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.arg1 = i;
        Bundle bundle = new Bundle();
        bundle.putDouble("latest_weight_key", d2);
        bundle.putDouble("latest_body_fate_key", d3);
        bundle.putDouble("join_latest_weight_key", d4);
        obtainMessage.setData(bundle);
        this.k.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public double a(int i, double d2) {
        int i2 = UnitUtil.a() == 1 ? 2 : 1;
        if (cpa.b(i)) {
            LogUtil.a("WeightEditShareActivity", "isHuaweiOrHonorDataType");
            i2 = Math.round(Math.pow(10.0d, 2.0d) * UnitUtil.a(d2)) % 10 == 0 ? 1 : 2;
        }
        return UnitUtil.a(d2, i2);
    }

    private void k() {
        this.ab = (CustomTitleBar) findViewById(R.id.weight_edit_share_titlebar);
        this.t = (HealthScrollView) findViewById(R.id.weight_edit_share_scrollview);
        this.r = (RelativeLayout) findViewById(R.id.weight_edit_share_log);
        ShareSquareLayout shareSquareLayout = (ShareSquareLayout) findViewById(R.id.weight_edit_share_show);
        this.x = shareSquareLayout;
        shareSquareLayout.setBackgroundDrawable(CardConstants.dFc_(this.i, "weight_share_background_1"));
        this.l = 0;
        this.z = (HealthTextView) findViewById(R.id.weight_edit_share_title);
        this.w = (HealthTextView) findViewById(R.id.weight_edit_share_describe);
        this.j = (HealthTextView) findViewById(R.id.weight_edit_share_current_weight);
        this.f = (HealthTextView) findViewById(R.id.weight_edit_share_current_bmi);
        this.ah = (HealthTextView) findViewById(R.id.weight_edit_share_weight_change);
        this.af = (HealthTextView) findViewById(R.id.weight_edit_share_weight_change_describe);
        setViewSafeRegion(false, (LinearLayout) findViewById(R.id.weight_multiple_source_choose));
        HealthScrollView healthScrollView = (HealthScrollView) findViewById(R.id.weight_edit_share_scrollview);
        this.t = healthScrollView;
        healthScrollView.setOverScrollable(false);
        b();
        g();
        f();
        h();
    }

    private void b() {
        HwHealthLineChart hwHealthLineChart = (HwHealthLineChart) findViewById(R.id.weight_change_line_chart);
        this.s = hwHealthLineChart;
        if (hwHealthLineChart.getRenderer() instanceof nov) {
            ((nov) this.s.getRenderer()).a(1);
        }
        this.s.disableLabelsForce();
        this.s.setTouchEnabled(false);
        this.s.getDescription().setEnabled(false);
    }

    private void d(double d2, double d3) {
        double a2 = UnitUtil.a(d2);
        int i = Math.round(Math.pow(10.0d, 2.0d) * a2) % 10 == 0 ? 1 : 2;
        if (a2 == 0.0d) {
            dBw_(this.j, String.format(Locale.ENGLISH, getResources().getString(R$string.IDS_wl_food_share_c_w), qsj.c()));
        } else {
            dBw_(this.j, String.format(Locale.ENGLISH, getResources().getString(R$string.IDS_wl_food_share_c_w), qsj.e(a2, i)));
        }
        if (dph.j(d3)) {
            dBw_(this.f, String.format(Locale.ENGLISH, getResources().getString(R$string.IDS_wl_food_share_c_bmi), UnitUtil.e(d3, 1, 1)));
        } else {
            dBw_(this.f, String.format(Locale.ENGLISH, getResources().getString(R$string.IDS_wl_food_share_c_bmi), "-/-"));
        }
    }

    private void dBw_(TextView textView, String str) {
        if (textView == null || TextUtils.isEmpty(str)) {
            return;
        }
        textView.setText(UnitUtil.bCR_(this.i, "[^(\\-|\\+)?\\d+(\\.\\d+)?$]", str, R.style.health_weight_edit_share_text, R.style.health_weight_edit_share_value));
    }

    private void h() {
        this.d = (HealthSubTabWidget) findViewById(R.id.weight_source_choose_tab);
        this.ac = (HealthViewPager) findViewById(R.id.weight_source_choose_viewpager);
        if (LanguageUtil.bc(this.i)) {
            this.ac.setRotationY(180.0f);
        }
        this.ac.setOffscreenPageLimit(2);
        i();
    }

    private void i() {
        ArrayList<d> arrayList = new ArrayList(2);
        arrayList.add(new d(this.e, getResources().getString(R$string.IDS_hwh_edit_share_background), 0));
        arrayList.add(new d(this.m, getResources().getString(R$string.IDS_hwh_edit_share_data_mark), 1));
        this.d.h();
        this.ad = new nqx(this, this.ac, this.d);
        this.ac.addOnPageChangeListener(new HwViewPager.OnPageChangeListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.WeightEditShareActivity.2
            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                WeightEditShareActivity.this.y = i;
            }
        });
        for (d dVar : arrayList) {
            WeightEditShareFragment a2 = dVar.a();
            String c2 = dVar.c();
            int d2 = dVar.d();
            if (a2 != null) {
                this.ad.c(this.d.c(c2), a2, this.y == d2);
            }
        }
        if (nsn.ag(this.i)) {
            int g = (int) (this.p + this.g.g() + nrr.b(this.i));
            this.ac.setPadding(g, 0, g, 0);
            return;
        }
        this.ac.setPadding(0, 0, 0, 0);
    }

    private void f() {
        if (this.e == null) {
            WeightEditShareFragment weightEditShareFragment = new WeightEditShareFragment();
            this.e = weightEditShareFragment;
            weightEditShareFragment.d(this);
            Bundle bundle = new Bundle();
            bundle.putInt("share_source_type", 0);
            this.e.setArguments(bundle);
        }
        if (this.m == null) {
            WeightEditShareFragment weightEditShareFragment2 = new WeightEditShareFragment();
            this.m = weightEditShareFragment2;
            weightEditShareFragment2.d(this);
            Bundle bundle2 = new Bundle();
            bundle2.putInt("share_source_type", 1);
            this.m.setArguments(bundle2);
        }
    }

    private void g() {
        this.ab.setLeftButtonVisibility(0);
        this.ab.setRightButtonVisibility(0);
        this.ab.setLeftButtonDrawable(ContextCompat.getDrawable(this, R.drawable._2131430037_res_0x7f0b0a95), nsf.h(R$string.accessibility_close));
        this.ab.setRightButtonDrawable(ContextCompat.getDrawable(this, R.drawable._2131430035_res_0x7f0b0a93), nsf.h(R$string.accessibility_share));
        this.ab.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.WeightEditShareActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WeightEditShareActivity.this.finish();
                WeightEditShareActivity.this.overridePendingTransition(0, R.anim._2130772079_res_0x7f01006f);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.ab.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.WeightEditShareActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PermissionUtil.c()) {
                    WeightEditShareActivity.this.c();
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    WeightEditShareActivity.this.l();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0097  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void c() {
        /*
            r8 = this;
            com.huawei.ui.commonui.view.ShareSquareLayout r0 = r8.x
            android.graphics.Bitmap r0 = defpackage.nrf.cHO_(r0)
            java.lang.String r1 = "WeightEditShareActivity"
            if (r0 != 0) goto L14
            java.lang.String r0 = "handleRightButtonEvents share pic is null "
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r0)
            return
        L14:
            int r2 = r8.l
            if (r2 < 0) goto L20
            java.lang.String[] r3 = com.huawei.ui.main.stories.health.activity.healthdata.WeightEditShareActivity.f10096a
            int r4 = r3.length
            if (r2 >= r4) goto L20
            r2 = r3[r2]
            goto L22
        L20:
            java.lang.String r2 = "A"
        L22:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            int r4 = r8.u
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r3.append(r4)
            int r4 = r8.q
            r3.append(r4)
            int r4 = r8.v
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "handleRightButtonEvents selectBgModel = "
            java.lang.String r5 = ", selectDataModel = "
            java.lang.Object[] r4 = new java.lang.Object[]{r4, r2, r5, r3}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r4)
            java.util.HashMap r4 = new java.util.HashMap
            r5 = 16
            r4.<init>(r5)
            java.lang.String r5 = "click"
            r6 = 1
            java.lang.Integer r7 = java.lang.Integer.valueOf(r6)
            r4.put(r5, r7)
            java.lang.String r5 = "shareBgModel"
            r4.put(r5, r2)
            java.lang.String r2 = "shareDataModel"
            r4.put(r2, r3)
            ixx r2 = defpackage.ixx.d()
            android.content.Context r3 = r8.i
            com.huawei.hwcommonmodel.constants.AnalyticsValue r5 = com.huawei.hwcommonmodel.constants.AnalyticsValue.WEIGHT_EDIT_SHARE_ICON_CLICKED_2060089
            java.lang.String r5 = r5.value()
            r7 = 0
            r2.d(r3, r5, r4, r7)
            android.graphics.Bitmap r2 = r8.dBu_()
            int r3 = r0.getHeight()
            android.graphics.Bitmap r0 = defpackage.nrf.cJj_(r0, r7, r2, r3)
            java.lang.String r2 = "handleRightButtonEvents screenCut: "
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r0}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r2)
            if (r0 != 0) goto L97
            java.lang.String r0 = "handleRightButtonEvents screenCut is null!"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r0)
            return
        L97:
            int r2 = r0.getByteCount()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.String r3 = "handleRightButtonEvents screenCut.getByteCount(): "
            java.lang.Object[] r2 = new java.lang.Object[]{r3, r2}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r2)
            int r1 = r0.getByteCount()
            r2 = 1048576(0x100000, float:1.469368E-39)
            if (r1 <= r2) goto Ld0
            fdu r1 = new fdu
            r2 = 4
            r1.<init>(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = defpackage.jcu.f
            r2.append(r3)
            java.lang.String r3 = "EditShareActivity_Share.jpg"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            defpackage.nrf.cJt_(r0, r2)
            r1.d(r2)
            goto Ld8
        Ld0:
            fdu r1 = new fdu
            r1.<init>(r6)
            r1.awp_(r0)
        Ld8:
            java.lang.String r0 = "15"
            r1.b(r0)
            r1.i(r7)
            r1.c(r7)
            r1.h(r7)
            com.huawei.health.socialshare.api.SocialShareCenterApi r0 = r8.aa
            android.content.Context r2 = r8.i
            r0.exeShare(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.health.activity.healthdata.WeightEditShareActivity.c():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        LogUtil.a("WeightEditShareActivity", "permission not granted, requesting storage permission");
        PermissionUtil.b(this, PermissionUtil.PermissionType.MEDIA_VIDEO_IMAGES, new CustomPermissionAction(this) { // from class: com.huawei.ui.main.stories.health.activity.healthdata.WeightEditShareActivity.7
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                LogUtil.a("WeightEditShareActivity", "storage permission onGranted");
                WeightEditShareActivity.this.c();
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                LogUtil.a("WeightEditShareActivity", "storage permission onDenied");
                super.onDenied(str);
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                LogUtil.a("WeightEditShareActivity", "storage permission onForeverDenied");
                nsn.e(WeightEditShareActivity.this, permissionType);
            }
        });
    }

    private Bitmap dBu_() {
        this.r.setVisibility(0);
        LogUtil.a("WeightEditShareActivity", "getWatermarkBitmap mWaterMarkLayout");
        Bitmap bitmap = null;
        try {
            bitmap = Bitmap.createBitmap(this.r.getMeasuredWidth(), this.r.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            this.r.draw(canvas);
            canvas.save();
            canvas.restore();
        } catch (IllegalArgumentException | IllegalStateException | OutOfMemoryError unused) {
            LogUtil.b("WeightEditShareActivity", "createBitmap failed!");
        }
        this.r.setVisibility(4);
        return bitmap;
    }

    @Override // com.huawei.ui.main.stories.health.interactors.healthdata.WeightShareEditListener
    public void refreshWeightShareBackground(int i) {
        LogUtil.a("WeightEditShareActivity", "refreshWeightShareBackground bitmapId = ", Integer.valueOf(i));
        ShareSquareLayout shareSquareLayout = this.x;
        if (shareSquareLayout != null) {
            String[] strArr = c;
            if (i <= strArr.length && i >= 0) {
                if (i != this.l) {
                    this.l = i;
                    shareSquareLayout.setBackgroundDrawable(CardConstants.dFc_(this.i, strArr[i]));
                    return;
                }
                return;
            }
        }
        LogUtil.h("WeightEditShareActivity", "index out of length");
    }

    @Override // com.huawei.ui.main.stories.health.interactors.healthdata.WeightShareEditListener
    public void refreshWeightShareData(int i, boolean z) {
        LogUtil.a("WeightEditShareActivity", "refreshWeightShareData index = ", Integer.valueOf(i), ", needShow = ", Boolean.valueOf(z));
        if (i == 0) {
            this.u = z ? 1 : 0;
            this.j.setVisibility(z ? 0 : 8);
            return;
        }
        if (i == 1) {
            this.q = z ? 1 : 0;
            this.f.setVisibility(z ? 0 : 8);
            return;
        }
        if (i != 2) {
            return;
        }
        this.v = z ? 1 : 0;
        if (this.n == 0) {
            this.ah.setVisibility(z ? 0 : 8);
            this.s.setVisibility(z ? 0 : 8);
            this.af.setVisibility(8);
        } else {
            this.af.setVisibility(z ? 0 : 8);
            this.ah.setVisibility(8);
            this.s.setVisibility(8);
        }
    }

    @Override // com.huawei.ui.main.stories.health.interactors.healthdata.WeightShareEditListener
    public void gotoCamera() {
        LogUtil.a("WeightEditShareActivity", "gotoCamera");
        m();
    }

    private void m() {
        Object systemService = this.i.getSystemService("layout_inflater");
        LayoutInflater layoutInflater = systemService instanceof LayoutInflater ? (LayoutInflater) systemService : null;
        if (layoutInflater == null) {
            LogUtil.h("WeightEditShareActivity", "showCameraDialog:inflater is null");
            return;
        }
        View inflate = layoutInflater.inflate(R.layout.hw_health_edit_share_camera_dialog, (ViewGroup) null);
        CustomViewDialog e2 = new CustomViewDialog.Builder(this.i).czg_(inflate).e();
        this.ag = e2;
        e2.show();
        ((HealthTextView) inflate.findViewById(R.id.hw_health_edit_share_caerma)).setOnClickListener(new View.OnClickListener() { // from class: qfm
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WeightEditShareActivity.this.dBA_(view);
            }
        });
        ((HealthTextView) inflate.findViewById(R.id.hw_health_edit_share_gallery)).setOnClickListener(new View.OnClickListener() { // from class: qfn
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WeightEditShareActivity.this.dBB_(view);
            }
        });
    }

    public /* synthetic */ void dBA_(View view) {
        PermissionUtil.b(this.i, PermissionUtil.PermissionType.CAMERA, new CustomPermissionAction(this.i) { // from class: com.huawei.ui.main.stories.health.activity.healthdata.WeightEditShareActivity.10
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                WeightEditShareActivity.this.o();
            }
        });
        CustomViewDialog customViewDialog = this.ag;
        if (customViewDialog != null) {
            customViewDialog.dismiss();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dBB_(View view) {
        a();
        CustomViewDialog customViewDialog = this.ag;
        if (customViewDialog != null) {
            customViewDialog.dismiss();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        this.aa.takePhoto((Activity) this.i);
    }

    private void a() {
        nsn.cKT_((Activity) this.i, 2);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 4 && intent == null) {
            LogUtil.h("WeightEditShareActivity", "data == null and it's not camera callback");
            return;
        }
        if (i2 == -1) {
            if (i == 2) {
                dBx_(intent);
                return;
            }
            if (i == 3) {
                dBt_(intent);
                return;
            }
            if (i != 4) {
                return;
            }
            Context context = this.i;
            if (context instanceof Activity) {
                this.aa.startCrop((Activity) context);
            } else {
                LogUtil.h("WeightEditShareActivity", "mContext not activity");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        e eVar = this.k;
        if (eVar != null) {
            eVar.removeCallbacksAndMessages(null);
        }
    }

    private void dBx_(final Intent intent) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: qfl
            @Override // java.lang.Runnable
            public final void run() {
                WeightEditShareActivity.this.dBC_(intent);
            }
        });
    }

    public /* synthetic */ void dBC_(Intent intent) {
        this.aa.startCrop(this, intent);
    }

    private void dBt_(final Intent intent) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: qfk
            @Override // java.lang.Runnable
            public final void run() {
                WeightEditShareActivity.this.dBy_(intent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: dBs_, reason: merged with bridge method [inline-methods] */
    public void dBy_(Intent intent) {
        LogUtil.a("WeightEditShareActivity", "dealCropResult");
        String stringExtra = intent.getStringExtra("bitmap");
        if (stringExtra == null) {
            LogUtil.b("WeightEditShareActivity", "dealCropResult:bitmapPath from intent is null!");
            return;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap cHC_ = nrf.cHC_(stringExtra, options);
        this.b = cHC_;
        if (cHC_ != null) {
            dBv_(cHC_);
            c(stringExtra);
        } else {
            LogUtil.h("WeightEditShareActivity", "dealCropResult:bitmap is null");
        }
    }

    private void dBv_(final Bitmap bitmap) {
        LogUtil.a("WeightEditShareActivity", "refreshBackgroundOnUi");
        runOnUiThread(new Runnable() { // from class: qfg
            @Override // java.lang.Runnable
            public final void run() {
                WeightEditShareActivity.this.dBz_(bitmap);
            }
        });
    }

    public /* synthetic */ void dBz_(Bitmap bitmap) {
        WeightEditShareFragment weightEditShareFragment = this.e;
        if (weightEditShareFragment != null) {
            weightEditShareFragment.c();
            this.l = -1;
        }
        if (this.x != null) {
            this.x.setBackground(new BitmapDrawable(this.i.getResources(), bitmap));
        }
    }

    private void c(String str) {
        FileUtils.d(new File(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, int i2, int i3) {
        Context context = this.i;
        if (context == null || this.z == null || this.w == null) {
            return;
        }
        Resources resources = context.getResources();
        double d2 = i2;
        String quantityString = resources.getQuantityString(R.plurals._2130903074_res_0x7f030022, qrp.a(d2), UnitUtil.e(d2, 1, 0));
        double d3 = i;
        this.z.setText(String.format(Locale.ENGLISH, resources.getString(R$string.IDS_wl_food_share_mode), String.format(Locale.ENGLISH, resources.getString(R$string.IDS_wl_food_hour_f_or_diet), quantityString, resources.getQuantityString(R.plurals._2130903075_res_0x7f030023, qrp.a(d3), UnitUtil.e(d3, 1, 0)))));
        this.z.setVisibility(0);
        this.w.setText(resources.getQuantityString(R.plurals._2130903079_res_0x7f030027, i3, Integer.valueOf(i3)));
        this.w.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, double d2, double d3, double d4) {
        LogUtil.c("WeightEditShareActivity", "setBottomView dataStatus = ", Integer.valueOf(i), ", joinLastedWeight = ", Double.valueOf(d4));
        d(d2, d3);
        this.n = i;
        if (i == -1) {
            int a2 = UnitUtil.a();
            if (a2 == 1) {
                this.af.setText(getResources().getString(R$string.IDS_wl_food_share_w_catty));
                return;
            } else if (a2 == 3) {
                this.af.setText(getResources().getString(R$string.IDS_wl_food_share_w_pounds));
                return;
            } else {
                this.af.setText(getResources().getString(R$string.IDS_wl_food_share_w_c_default));
                return;
            }
        }
        if (d4 > d2) {
            double d5 = d2 - d4;
            if (Math.abs(d5) > 0.1d) {
                c(d2, d4);
                dBw_(this.ah, String.format(Locale.ENGLISH, getResources().getString(R$string.IDS_wl_food_share_w_c), qsj.e(UnitUtil.a(UnitUtil.a(d5, 2)), 1)));
                return;
            }
        }
        LogUtil.a("WeightEditShareActivity", "weight is NOT CHANGE");
        this.n = -2;
        this.af.setText(getResources().getString(R$string.IDS_wl_food_share_tips_keep));
    }

    private void c(double d2, double d3) {
        ArrayList arrayList = new ArrayList();
        float f = ((float) d2) - 5.0f;
        float f2 = ((float) d3) + 5.0f;
        e(f, f2);
        arrayList.add(new HwHealthBaseEntry(-1000.0f, f2));
        arrayList.add(new HwHealthBaseEntry(10.0f, f2));
        arrayList.add(new HwHealthBaseEntry(100.0f, f));
        e(arrayList);
    }

    private void e(List<HwHealthBaseEntry> list) {
        if (this.s == null) {
            LogUtil.h("WeightEditShareActivity", "initLineDataSet mLineChart is null");
            return;
        }
        LogUtil.a("WeightEditShareActivity", "initLineDataSet valueList size is ", Integer.valueOf(list.size()));
        HwHealthLineDataSet hwHealthLineDataSet = new HwHealthLineDataSet(this.i, list, "line brief", "line label", "line unit");
        hwHealthLineDataSet.setColor(Color.parseColor("#FFFFFFFF"));
        hwHealthLineDataSet.b(Color.parseColor("#66FFFFFF"), Color.parseColor("#00ffffff"), true);
        hwHealthLineDataSet.setAxisDependency(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY);
        ArrayList arrayList = new ArrayList();
        arrayList.add(hwHealthLineDataSet);
        this.s.setData(new now(arrayList));
        this.s.refresh();
        this.s.setVisibility(8);
    }

    private void e(float f, float f2) {
        HwHealthLineChart hwHealthLineChart = this.s;
        if (hwHealthLineChart == null) {
            LogUtil.h("WeightEditShareActivity", "initAxis mLineChart is null");
            return;
        }
        XAxis xAxis = hwHealthLineChart.getXAxis();
        xAxis.setEnabled(false);
        xAxis.setAxisMinimum(-4.0f);
        xAxis.setAxisMaximum(104.0f);
        HwHealthYAxis axisFirstParty = this.s.getAxisFirstParty();
        axisFirstParty.setDrawLabels(false);
        axisFirstParty.setDrawAxisLine(false);
        axisFirstParty.setDrawGridLines(false);
        axisFirstParty.setAxisMinimum(f - 100.0f);
        axisFirstParty.setAxisMaximum(f2 + 100.0f);
    }

    /* loaded from: classes8.dex */
    static class d {

        /* renamed from: a, reason: collision with root package name */
        private String f10098a;
        private int b;
        private WeightEditShareFragment c;

        d(WeightEditShareFragment weightEditShareFragment, String str, int i) {
            this.c = weightEditShareFragment;
            this.f10098a = str;
            this.b = i;
        }

        public WeightEditShareFragment a() {
            return this.c;
        }

        public String c() {
            return this.f10098a;
        }

        int d() {
            return this.b;
        }
    }

    /* loaded from: classes8.dex */
    static class e extends BaseHandler<WeightEditShareActivity> {
        e(WeightEditShareActivity weightEditShareActivity) {
            super(weightEditShareActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dBD_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(WeightEditShareActivity weightEditShareActivity, Message message) {
            int i;
            double d;
            double d2;
            double d3;
            int i2 = message.what;
            if (i2 == 0) {
                int i3 = message.arg1;
                int i4 = message.arg2;
                if (message.obj instanceof Integer) {
                    i = ((Integer) message.obj).intValue();
                } else {
                    LogUtil.h("WeightEditShareActivity", "message.obj is not Integer");
                    i = 0;
                }
                weightEditShareActivity.c(i3, i4, i);
                return;
            }
            if (i2 != 1) {
                return;
            }
            LogUtil.a("WeightEditShareActivity", "handleMessage request weight");
            int i5 = message.arg1;
            Bundle data = message.getData();
            if (data != null) {
                double d4 = data.getDouble("latest_weight_key");
                double d5 = data.getDouble("latest_body_fate_key");
                d3 = data.getDouble("join_latest_weight_key");
                d2 = d5;
                d = d4;
            } else {
                d = 0.0d;
                d2 = 0.0d;
                d3 = 0.0d;
            }
            weightEditShareActivity.a(i5, d, d2, d3);
        }
    }
}
