package com.huawei.ui.main.stories.history;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SpinnerAdapter;
import androidx.core.content.ContextCompat;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.vip.api.VipApi;
import com.huawei.health.vip.api.VipCallback;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.healthcloud.plugintrack.ui.view.DetailItemContainer;
import com.huawei.healthcloud.plugintrack.ui.view.SportDetailItem;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.common.data.DataHolder;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.linechart.common.ClassType;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.DataLayerType;
import com.huawei.ui.commonui.linechart.common.DateType;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder;
import com.huawei.ui.commonui.spinner.HealthSpinner;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.common.IChartLayerHolderProvider;
import com.huawei.ui.main.stories.fitness.util.chart.TrackModuleBarChartHolder;
import com.huawei.ui.main.stories.fitness.views.base.chart.AllClassfiedView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedButtonList;
import com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedViewList;
import com.huawei.ui.main.stories.fitness.views.base.chart.MonthBarClassifiedView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView;
import com.huawei.ui.main.stories.fitness.views.base.chart.WeekBarClassifiedView;
import com.huawei.ui.main.stories.fitness.views.base.chart.YearBarClassifiedView;
import com.huawei.ui.main.stories.fitness.views.base.chart.utils.IOnDataShowListener;
import com.huawei.ui.main.stories.fitness.views.base.chart.utils.UserEvent;
import com.huawei.ui.main.stories.history.SportDataActivity;
import com.huawei.ui.main.stories.history.view.SportArrayAdapter;
import com.huawei.up.model.UserInfomation;
import defpackage.fdu;
import defpackage.gpn;
import defpackage.ixx;
import defpackage.jdl;
import defpackage.jdv;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.rch;
import defpackage.rdu;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class SportDataActivity extends BaseActivity implements IChartLayerHolderProvider<TrackModuleBarChartHolder> {
    private static ixx b = ixx.d();

    /* renamed from: a, reason: collision with root package name */
    private ClassifiedButtonList f10280a;
    private ImageView c;
    private TrackModuleBarChartHolder d;
    private HealthCalendar e;
    private Context f;
    private ObserveredClassifiedView g;
    private ClassifiedViewList h;
    private CustomTitleBar i;
    private int j;
    private List<ClassifiedViewList.ClassifiedView> k;
    private View q;
    private HealthTextView r;
    private Resources s;
    private ArrayList<Integer> t;
    private HealthViewPager v;
    private int x;
    private String y;
    private SportDataInteractor l = new SportDataInteractor(this);
    private boolean n = false;
    private int p = 4;
    private int o = 4;
    private boolean m = false;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        LogUtil.a("Track_SportDataActivity", "onCreate");
        super.onCreate(bundle);
        getWindow().setBackgroundDrawable(null);
        setContentView(R.layout.layout_sport_data);
        this.f = this;
        SportDetailItem.c(true);
        if (UnitUtil.h()) {
            this.y = getString(R$string.IDS_band_data_sport_distance_unit_en);
        } else {
            this.y = getString(R$string.IDS_band_data_sport_distance_unit);
        }
        int f = f();
        this.x = f;
        this.l.b(f);
        this.l.dJM_(this);
        o();
        TrackModuleBarChartHolder trackModuleBarChartHolder = new TrackModuleBarChartHolder(getApplicationContext());
        this.d = trackModuleBarChartHolder;
        d(trackModuleBarChartHolder);
        m();
        j();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        if (this.l != null) {
            int f = f();
            this.x = f;
            this.l.b(f);
            this.l.dJM_(this);
        }
        b();
    }

    private void b() {
        if (nsn.l()) {
            LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.f).inflate(R.layout.layout_sport_data, (ViewGroup) null).findViewById(R.id.track_sport_data);
            nsn.cLF_(getBaseContext(), true, true, (LinearLayout) linearLayout.findViewById(R.id.track_detail_show_distance), (DetailItemContainer) linearLayout.findViewById(R.id.sport_data_container));
        }
    }

    private void d(TrackModuleBarChartHolder trackModuleBarChartHolder) {
        b(trackModuleBarChartHolder);
        c(trackModuleBarChartHolder);
    }

    private void b(TrackModuleBarChartHolder trackModuleBarChartHolder) {
        trackModuleBarChartHolder.spetifiyDataTypeUnit(new IChartLayerHolder.DataTypeFilter() { // from class: rbr
            @Override // com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder.DataTypeFilter
            public final boolean isAccept(DataInfos dataInfos) {
                return SportDataActivity.d(dataInfos);
            }
        }, this.y);
        trackModuleBarChartHolder.spetifiyDataTypeUnit(new IChartLayerHolder.DataTypeFilter() { // from class: rca
            @Override // com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder.DataTypeFilter
            public final boolean isAccept(DataInfos dataInfos) {
                return SportDataActivity.a(dataInfos);
            }
        }, BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903241_res_0x7f0300c9, 1));
        trackModuleBarChartHolder.spetifiyDataTypeUnit(new IChartLayerHolder.DataTypeFilter() { // from class: rbw
            @Override // com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder.DataTypeFilter
            public final boolean isAccept(DataInfos dataInfos) {
                return SportDataActivity.e(dataInfos);
            }
        }, getString(R$string.IDS_motiontrack_detail_fm_heart_min));
    }

    public static /* synthetic */ boolean d(DataInfos dataInfos) {
        return dataInfos != null && dataInfos.isChiefDistance();
    }

    public static /* synthetic */ boolean a(DataInfos dataInfos) {
        return dataInfos != null && dataInfos.isChiefCount();
    }

    public static /* synthetic */ boolean e(DataInfos dataInfos) {
        return (dataInfos == null || dataInfos.isChiefDistance() || dataInfos.isChiefCount()) ? false : true;
    }

    private void c(TrackModuleBarChartHolder trackModuleBarChartHolder) {
        trackModuleBarChartHolder.b(DataLayerType.DURATION, BaseApplication.getContext().getColor(R.color._2131296598_res_0x7f090156), BaseApplication.getContext().getColor(R.color._2131299282_res_0x7f090bd2), getString(R$string.IDS_motiontrack_detail_fm_heart_min));
        trackModuleBarChartHolder.b(DataLayerType.TIMES, BaseApplication.getContext().getColor(R.color._2131296601_res_0x7f090159), BaseApplication.getContext().getColor(R.color._2131299292_res_0x7f090bdc), BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903213_res_0x7f0300ad, 2, ""));
        trackModuleBarChartHolder.b(DataLayerType.CALORIES, BaseApplication.getContext().getColor(R.color._2131296597_res_0x7f090155), BaseApplication.getContext().getColor(R.color._2131299271_res_0x7f090bc7), getString(R$string.IDS_motiontrack_show_kcal));
    }

    private int f() {
        Intent intent = getIntent();
        if (intent != null) {
            return intent.getIntExtra(DataHolder.TYPE_INT, 0);
        }
        return 0;
    }

    private void o() {
        LogUtil.a("Track_SportDataActivity", "requestWeekData");
        this.l.d(jdl.c(System.currentTimeMillis(), 2, 0), jdl.b(System.currentTimeMillis(), 2, 0), 4);
    }

    private void m() {
        i();
        LogUtil.a("Track_SportDataActivity", "initView");
        this.f10280a = (ClassifiedButtonList) findViewById(R.id.classified_button_list);
        HealthViewPager healthViewPager = (HealthViewPager) findViewById(R.id.classified_view_place);
        this.v = healthViewPager;
        cancelLayoutById(healthViewPager);
        this.h = new ClassifiedViewList(this, this.f10280a, this.v);
        b(this.x);
        this.q = findViewById(R.id.sport_data_ll);
        this.c = (ImageView) findViewById(R.id.sport_data_detail_viewpager);
        this.r = (HealthTextView) findViewById(R.id.track_detail_map_sport_formal_time);
        this.s = getResources();
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.x == 0) {
            findViewById(R.id.sport_stat_view).setBackgroundColor(ContextCompat.getColor(this.f, R.color._2131296690_res_0x7f0901b2));
            findViewById(R.id.all_sport_data).setVisibility(0);
            findViewById(R.id.track_sport_data).setVisibility(8);
        } else {
            findViewById(R.id.sport_stat_view).setBackgroundColor(ContextCompat.getColor(this.f, R.color._2131296657_res_0x7f090191));
            findViewById(R.id.all_sport_data).setVisibility(8);
            findViewById(R.id.track_sport_data).setVisibility(0);
        }
    }

    private void j() {
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.track_share_detail_title_usrname);
        String picPath = userInfo != null ? userInfo.getPicPath() : null;
        String shareNickName = ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).getShareNickName();
        healthTextView.setText(shareNickName);
        ImageView imageView = (ImageView) findViewById(R.id.track_share_short_image);
        if (!TextUtils.isEmpty(picPath)) {
            Bitmap cIe_ = nrf.cIe_(this, picPath);
            if (cIe_ != null) {
                imageView.setImageBitmap(cIe_);
            } else {
                LogUtil.h("Track_SportDataActivity", "handleWhenGetUserInfoSuccess()bmp != null ");
            }
        } else {
            LogUtil.h("Track_SportDataActivity", "handleWhenGetUserInfoSuccess()! headImgPath is null! ");
        }
        if (Utils.o() && TextUtils.isEmpty(shareNickName)) {
            imageView.setVisibility(8);
        }
        k();
        q();
    }

    private void q() {
        ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).updateShareUserView("Track_SportDataActivity", 8, (ImageView) findViewById(R.id.track_share_short_image), (LinearLayout) findViewById(R.id.track_user_info));
    }

    private void k() {
        VipApi vipApi = (VipApi) Services.a("vip", VipApi.class);
        if (vipApi == null) {
            LogUtil.a("Track_SportDataActivity", "vipApi is null");
        } else {
            vipApi.getVipInfo(new AnonymousClass1());
        }
    }

    /* renamed from: com.huawei.ui.main.stories.history.SportDataActivity$1, reason: invalid class name */
    public class AnonymousClass1 implements VipCallback {
        AnonymousClass1() {
        }

        @Override // com.huawei.health.vip.api.VipCallback
        public void onSuccess(Object obj) {
            if (obj == null) {
                LogUtil.a("Track_SportDataActivity", "getVipMemberInfo onSuccess result is null");
                return;
            }
            if (obj instanceof UserMemberInfo) {
                UserMemberInfo userMemberInfo = (UserMemberInfo) obj;
                if (userMemberInfo.getMemberFlag() != 1) {
                    LogUtil.h("Track_SportDataActivity", "TradeViewApi memberInfo == null or != VipConstants.MEMBER_FLAG_VIP");
                    return;
                } else {
                    if (gpn.d(userMemberInfo)) {
                        return;
                    }
                    HandlerExecutor.e(new Runnable() { // from class: rbz
                        @Override // java.lang.Runnable
                        public final void run() {
                            SportDataActivity.AnonymousClass1.this.e();
                        }
                    });
                    return;
                }
            }
            LogUtil.b("Track_SportDataActivity", "result is not UserMemberInfo");
        }

        public /* synthetic */ void e() {
            HealthImageView healthImageView = (HealthImageView) SportDataActivity.this.findViewById(R.id.member_card_vip_icon);
            if (healthImageView != null) {
                healthImageView.setVisibility(0);
            }
        }

        @Override // com.huawei.health.vip.api.VipCallback
        public void onFailure(int i, String str) {
            LogUtil.b("Track_SportDataActivity", "getVipMemberInfo onFailure.");
        }
    }

    private void g() {
        String[] e = rdu.e(this.t, this);
        HealthSpinner titleSpinner = this.i.getTitleSpinner();
        SportArrayAdapter sportArrayAdapter = new SportArrayAdapter(this, R.layout.sport_spinner, e, this.t);
        if (titleSpinner != null) {
            titleSpinner.setAdapter((SpinnerAdapter) sportArrayAdapter);
            titleSpinner.setSelection(this.j, true);
            titleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.huawei.ui.main.stories.history.SportDataActivity.3
                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onNothingSelected(AdapterView<?> adapterView) {
                }

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                    HashMap hashMap = new HashMap(16);
                    hashMap.put("click", 1);
                    SportDataActivity.this.j = i;
                    SportDataActivity.this.a(i, hashMap);
                    SportDataActivity.this.d();
                    if (SportDataActivity.this.l != null) {
                        SportDataActivity sportDataActivity = SportDataActivity.this;
                        sportDataActivity.x = rdu.d(i, sportDataActivity.t);
                        SportDataActivity.this.l.b(SportDataActivity.this.x);
                        SportDataActivity.this.l.dJM_(SportDataActivity.this);
                    }
                    ixx.d().d(SportDataActivity.this, AnalyticsValue.BI_TRACK_STAT_CHANGE_SPORT_TYPE_1040036.value(), hashMap, 0);
                    ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, Map<String, Object> map) {
        LogUtil.a("Track_SportDataActivity", "initPopWindowView");
        b.a(e(this.x, this.o), new LinkedHashMap<>(16));
        int d = rdu.d(i, this.t);
        this.x = d;
        LogUtil.a("Track_SportDataActivity", "spinner choose type: ", Integer.valueOf(d));
        b(this.x);
        map.put(BleConstants.SPORT_TYPE, Integer.valueOf(this.x));
        b.e(e(this.x, this.o), new LinkedHashMap<>(16));
    }

    private void i() {
        LogUtil.a("Track_SportDataActivity", "initTitleBar,mType:", Integer.valueOf(this.x));
        this.i = (CustomTitleBar) findViewById(R.id.sport_data_titlebar);
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.b("Track_SportDataActivity", "intent == null");
            return;
        }
        try {
            this.t = intent.getIntegerArrayListExtra("type_list");
        } catch (ArrayIndexOutOfBoundsException e) {
            LogUtil.b("Track_SportDataActivity", "initTitleBar", LogAnonymous.b((Throwable) e));
        }
        ArrayList<Integer> arrayList = this.t;
        if (arrayList != null) {
            this.j = rdu.c(this.x, arrayList);
        }
        g();
        this.i.setRightButtonVisibility(0);
        if (LanguageUtil.bc(this)) {
            this.i.setRightButtonDrawable(ContextCompat.getDrawable(this, R.drawable._2131430036_res_0x7f0b0a94), nsf.h(R$string.accessibility_share));
        } else {
            this.i.setRightButtonDrawable(ContextCompat.getDrawable(this, R.drawable._2131430035_res_0x7f0b0a93), nsf.h(R$string.accessibility_share));
        }
        this.i.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.history.SportDataActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SportDataActivity.this.n();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        if (this.l == null || this.n) {
            return;
        }
        q();
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.share_user_heard);
        if (relativeLayout == null) {
            LogUtil.h("Track_SportDataActivity", "shareData shareUserViewLayout == null");
            l();
        } else {
            relativeLayout.post(new Runnable() { // from class: rbs
                @Override // java.lang.Runnable
                public final void run() {
                    SportDataActivity.this.l();
                }
            });
        }
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put("tabType", Integer.valueOf(this.p));
        hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(this.x));
        ixx.d().d(this.f, AnalyticsValue.BI_TRACK_SHOW_STAT_SHARE_1040038.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        Bitmap dvv_ = this.h.dvv_();
        if (dvv_ != null) {
            LogUtil.a("Track_SportDataActivity", "shareBitmap is not null");
            this.c.setBackground(new BitmapDrawable(this.s, dvv_));
        }
        c();
    }

    private void c() {
        LogUtil.a("Track_SportDataActivity", "doShare()");
        Bitmap bGg_ = jdv.bGg_(this.q);
        if (bGg_ == null) {
            LogUtil.h("Track_SportDataActivity", "screenCut is null");
            nrh.e(this, R$string.IDS_motiontrack_share_fail_tip);
            return;
        }
        try {
            fdu dKH_ = rch.dKH_(bGg_);
            dKH_.c(false);
            ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).exeShare(dKH_, getApplicationContext());
            HashMap hashMap = new HashMap(3);
            hashMap.put("click", 1);
            hashMap.put("tabType", Integer.valueOf(this.p));
            hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(this.x));
            ixx.d().d(this, AnalyticsValue.BI_TRACK_SHARE_HISTORY_STAT_1040039.value(), hashMap, 0);
        } catch (OutOfMemoryError unused) {
            nrh.e(this, R$string.IDS_motiontrack_share_fail_tip);
            LogUtil.h("Track_SportDataActivity", "shareTrackData outOfMemoryError");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        LogUtil.a("Track_SportDataActivity", "onResume");
        super.onResume();
        b.e(e(this.x, this.p), new LinkedHashMap<>(16));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        LogUtil.a("Track_SportDataActivity", "onPause");
        super.onPause();
        b.a(e(this.x, this.p), new LinkedHashMap<>(16));
    }

    @Override // com.huawei.ui.main.stories.fitness.common.IChartLayerHolderProvider
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public TrackModuleBarChartHolder acquireChartLayerHolder() {
        return this.d;
    }

    private void b(int i) {
        LogUtil.a("Track_SportDataActivity", "initClassifiedViewList");
        this.d.b();
        ArrayList arrayList = new ArrayList(4);
        this.k = arrayList;
        arrayList.add(g(i));
        this.k.add(d(i));
        this.k.add(f(i));
        this.k.add(a(i));
        ClassifiedViewList classifiedViewList = this.h;
        List<ClassifiedViewList.ClassifiedView> list = this.k;
        classifiedViewList.a(list, this.d, list.get(0));
        int i2 = this.x;
        if (i2 == 0) {
            e(this.l.c());
        } else if (i2 == 287) {
            e(0);
        }
        this.h.setOnClassifiedViewChangeListener(new ClassifiedViewList.OnClassifiedViewChangeListener() { // from class: com.huawei.ui.main.stories.history.SportDataActivity.2
            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedViewList.OnClassifiedViewChangeListener
            public void onClassifiedViewSelected(View view, int i3) {
                if (view instanceof ObserveredClassifiedView) {
                    SportDataActivity.this.g = (ObserveredClassifiedView) view;
                }
            }
        });
        this.l.b(i);
        h();
    }

    public void e(int i) {
        DataLayerType dataLayerType;
        if (i == 0) {
            dataLayerType = DataLayerType.DURATION;
        } else if (i == 1) {
            dataLayerType = DataLayerType.TIMES;
        } else {
            dataLayerType = DataLayerType.CALORIES;
        }
        this.d.onChange(dataLayerType);
        for (int i2 = 0; i2 < this.k.size(); i2++) {
            if (this.k.get(i2) instanceof ObserveredClassifiedView) {
                ((ObserveredClassifiedView) this.k.get(i2)).setDataLayerType(dataLayerType);
                ((ObserveredClassifiedView) this.k.get(i2)).getChart().animateBorderYAuto();
                ((ObserveredClassifiedView) this.k.get(i2)).getChart().refresh();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0025, code lost:
    
        if (r1 == 2) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String d(float r7) {
        /*
            r6 = this;
            int r0 = (int) r7
            com.huawei.ui.main.stories.history.SportDataInteractor r1 = r6.l
            int r1 = r1.c()
            r2 = 0
            boolean r2 = defpackage.nor.a(r7, r2)
            if (r2 == 0) goto L17
            android.content.Context r7 = r6.f
            int r0 = com.huawei.ui.main.R$string.IDS_motiontrack_show_invalid_data
            java.lang.String r7 = r7.getString(r0)
            return r7
        L17:
            float r0 = (float) r0
            float r0 = r7 - r0
            double r2 = (double) r0
            r4 = 4572414629676717179(0x3f747ae147ae147b, double:0.005)
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 < 0) goto L27
            r0 = 2
            if (r1 != r0) goto L28
        L27:
            r0 = 0
        L28:
            java.math.BigDecimal r1 = new java.math.BigDecimal
            java.lang.String r7 = java.lang.Float.toString(r7)
            r1.<init>(r7)
            double r1 = r1.doubleValue()
            r7 = 1
            java.lang.String r7 = health.compact.a.UnitUtil.e(r1, r7, r0)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.history.SportDataActivity.d(float):java.lang.String");
    }

    private ObserveredClassifiedView g(int i) {
        WeekBarClassifiedView weekBarClassifiedView = new WeekBarClassifiedView(this) { // from class: com.huawei.ui.main.stories.history.SportDataActivity.5
            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView
            public String convertFloat2TextShow(float f) {
                return SportDataActivity.this.d(f);
            }

            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.WeekClassifiedView, com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView, com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedViewList.ClassifiedView
            public void onSelected() {
                super.onSelected();
                if (!SportDataActivity.this.m) {
                    ixx ixxVar = SportDataActivity.b;
                    SportDataActivity sportDataActivity = SportDataActivity.this;
                    ixxVar.a(sportDataActivity.e(sportDataActivity.x, SportDataActivity.this.o), new LinkedHashMap<>(16));
                    SportDataActivity.this.o = 4;
                    ixx ixxVar2 = SportDataActivity.b;
                    SportDataActivity sportDataActivity2 = SportDataActivity.this;
                    ixxVar2.e(sportDataActivity2.e(sportDataActivity2.x, SportDataActivity.this.o), new LinkedHashMap<>(16));
                    return;
                }
                SportDataActivity.this.o = 4;
                SportDataActivity.this.m = false;
            }
        };
        weekBarClassifiedView.setStepDatatype(DataInfos.query(c(i), DateType.DATE_WEEK, i));
        weekBarClassifiedView.initCalendarView(this, this.e);
        this.g = weekBarClassifiedView;
        return weekBarClassifiedView;
    }

    private ObserveredClassifiedView d(int i) {
        MonthBarClassifiedView monthBarClassifiedView = new MonthBarClassifiedView(this) { // from class: com.huawei.ui.main.stories.history.SportDataActivity.6
            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView
            public String convertFloat2TextShow(float f) {
                return SportDataActivity.this.d(f);
            }

            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.MonthClassifiedView, com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView, com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedViewList.ClassifiedView
            public void onSelected() {
                super.onSelected();
                if (!SportDataActivity.this.m) {
                    ixx ixxVar = SportDataActivity.b;
                    SportDataActivity sportDataActivity = SportDataActivity.this;
                    ixxVar.a(sportDataActivity.e(sportDataActivity.x, SportDataActivity.this.o), new LinkedHashMap<>(16));
                    SportDataActivity.this.o = 5;
                    ixx ixxVar2 = SportDataActivity.b;
                    SportDataActivity sportDataActivity2 = SportDataActivity.this;
                    ixxVar2.e(sportDataActivity2.e(sportDataActivity2.x, SportDataActivity.this.o), new LinkedHashMap<>(16));
                    return;
                }
                SportDataActivity.this.o = 5;
                SportDataActivity.this.m = false;
            }
        };
        monthBarClassifiedView.setStepDatatype(DataInfos.query(c(i), DateType.DATE_MONTH, i));
        monthBarClassifiedView.initCalendarView(this, this.e);
        return monthBarClassifiedView;
    }

    private ObserveredClassifiedView f(int i) {
        YearBarClassifiedView yearBarClassifiedView = new YearBarClassifiedView(this) { // from class: com.huawei.ui.main.stories.history.SportDataActivity.7
            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView
            public String convertFloat2TextShow(float f) {
                return SportDataActivity.this.d(f);
            }

            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.YearClassifiedView, com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView, com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedViewList.ClassifiedView
            public void onSelected() {
                super.onSelected();
                if (!SportDataActivity.this.m) {
                    ixx ixxVar = SportDataActivity.b;
                    SportDataActivity sportDataActivity = SportDataActivity.this;
                    ixxVar.a(sportDataActivity.e(sportDataActivity.x, SportDataActivity.this.o), new LinkedHashMap<>(16));
                    SportDataActivity.this.o = 6;
                    ixx ixxVar2 = SportDataActivity.b;
                    SportDataActivity sportDataActivity2 = SportDataActivity.this;
                    ixxVar2.e(sportDataActivity2.e(sportDataActivity2.x, SportDataActivity.this.o), new LinkedHashMap<>(16));
                    return;
                }
                SportDataActivity.this.o = 6;
                SportDataActivity.this.m = false;
            }
        };
        yearBarClassifiedView.setStepDatatype(DataInfos.query(c(i), DateType.DATE_YEAR, i));
        yearBarClassifiedView.initCalendarView(this, this.e);
        return yearBarClassifiedView;
    }

    private ObserveredClassifiedView a(int i) {
        AllClassfiedView allClassfiedView = new AllClassfiedView(this) { // from class: com.huawei.ui.main.stories.history.SportDataActivity.8
            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView
            public String convertFloat2TextShow(float f) {
                return SportDataActivity.this.d(f);
            }

            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.AllClassfiedView, com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView, com.huawei.ui.main.stories.fitness.views.base.chart.ClassifiedViewList.ClassifiedView
            public void onSelected() {
                super.onSelected();
                if (!SportDataActivity.this.m) {
                    ixx ixxVar = SportDataActivity.b;
                    SportDataActivity sportDataActivity = SportDataActivity.this;
                    ixxVar.a(sportDataActivity.e(sportDataActivity.x, SportDataActivity.this.o), new LinkedHashMap<>(16));
                    SportDataActivity.this.o = 7;
                    ixx ixxVar2 = SportDataActivity.b;
                    SportDataActivity sportDataActivity2 = SportDataActivity.this;
                    ixxVar2.e(sportDataActivity2.e(sportDataActivity2.x, SportDataActivity.this.o), new LinkedHashMap<>(16));
                    return;
                }
                SportDataActivity.this.o = 7;
                SportDataActivity.this.m = false;
            }
        };
        allClassfiedView.setStepDatatype(DataInfos.query(c(i), DateType.DATE_ALL, i));
        return allClassfiedView;
    }

    private ClassType c(int i) {
        if (i == 259) {
            return ClassType.TYPE_BIKE;
        }
        if (i == 257) {
            return ClassType.TYPE_WALK;
        }
        if (i == 10001) {
            return ClassType.TYPE_FITNESS;
        }
        if (i == 262) {
            return ClassType.TYPE_SWIM;
        }
        if (i == 271) {
            return ClassType.TYPE_BASKETBALL;
        }
        if (i == 220 || i == 286) {
            return ClassType.TYPE_GOLF;
        }
        if (i == 258) {
            return ClassType.TYPE_RUN;
        }
        if (i == 287 || i == 288 || i == 289 || i == 291) {
            return ClassType.TYPE_DIVING;
        }
        if (i == 0) {
            return ClassType.TYPE_ALL_SPORT;
        }
        return ClassType.TYPE_CHANGEABLE;
    }

    private void h() {
        this.h.b(new IOnDataShowListener() { // from class: com.huawei.ui.main.stories.history.SportDataActivity.9
            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.utils.IOnDataShowListener
            public void onUserEvent(UserEvent userEvent) {
            }

            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.utils.IOnDataShowListener
            public void onDataShowChanged(DataInfos dataInfos, int i, int i2, HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
                SportDataActivity.this.n = true;
                long j = i * 60000;
                long j2 = i2;
                if (dataInfos.isMonthData()) {
                    SportDataActivity.this.p = 5;
                } else if (dataInfos.isYearData()) {
                    SportDataActivity.this.p = 6;
                } else if (dataInfos.isAllData()) {
                    SportDataActivity.this.p = 7;
                } else if (jdl.t(j) == j) {
                    SportDataActivity.this.p = 4;
                } else {
                    LogUtil.h("Track_SportDataActivity", "not is start time: ", DateFormatUtil.b(j, DateFormatUtil.DateFormatType.DATE_FORMAT_MILS));
                    return;
                }
                SportDataActivity.this.l.d(j, j2 * 60000, 7);
                SportDataActivity.this.n = false;
                SportDataActivity.this.b(AnalyticsValue.SPORT_RECORD_CHANGE_CHART_PAGE_TYPE_2040189.value());
                SportDataActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.main.stories.history.SportDataActivity.9.4
                    @Override // java.lang.Runnable
                    public void run() {
                        SportDataActivity.this.r.setText(SportDataActivity.this.getString(R$string.IDS_hwh_motiontrack_sport_data_share_date, new Object[]{SportDataActivity.this.h.d(), rdu.c(SportDataActivity.this.x, SportDataActivity.this)}));
                        SportDataActivity.this.l.c(SportDataActivity.this.getString(R$string.IDS_hwh_motiontrack_sport_data_share_date, new Object[]{SportDataActivity.this.h.d(), ""}));
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(this.p));
        ixx.d().d(BaseApplication.getContext(), str, hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String e(int i, int i2) {
        String str = i != 257 ? i != 259 ? i != 262 ? i != 271 ? i != 10001 ? "RUN_" : "FITNESS_" : "BASKETBALL_" : "SWIM_" : "BIKE_" : "WALK_";
        String str2 = i2 != 5 ? i2 != 6 ? i2 != 7 ? "WEEK" : "ALL" : "YEAR" : "MONTH";
        StringBuilder sb = new StringBuilder(16);
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        ObserveredClassifiedView observeredClassifiedView;
        super.onActivityResult(i, i2, intent);
        if (i2 != -1 || intent == null) {
            return;
        }
        Serializable serializableExtra = intent.getSerializableExtra("selectedDate");
        if (!(serializableExtra instanceof HealthCalendar) || (observeredClassifiedView = this.g) == null) {
            return;
        }
        HealthCalendar healthCalendar = (HealthCalendar) serializableExtra;
        this.e = healthCalendar;
        observeredClassifiedView.processCalendarSelect(healthCalendar);
    }
}
