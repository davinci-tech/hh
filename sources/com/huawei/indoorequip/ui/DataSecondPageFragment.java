package com.huawei.indoorequip.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.model.SportDetailChartDataType;
import com.huawei.healthcloud.plugintrack.sportmusic.SportMusicController;
import com.huawei.healthcloud.plugintrack.ui.view.MusicControlLayout;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.activity.IndoorEquipDisplayActivity;
import com.huawei.indoorequip.magnet.RealTimeDynamicChartView;
import com.huawei.indoorequip.ui.view.SportEquipItemDrawer;
import com.huawei.indoorequip.viewmodel.BaseRealTimeDynamicChartViewModel;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.columnlayout.HealthColumnLinearLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.diy;
import defpackage.ffs;
import defpackage.gwg;
import defpackage.gwh;
import defpackage.lbc;
import defpackage.lbj;
import defpackage.lbt;
import defpackage.lbv;
import defpackage.lcf;
import defpackage.lch;
import defpackage.lci;
import defpackage.lcj;
import defpackage.lcp;
import defpackage.lcr;
import defpackage.lcs;
import defpackage.lct;
import defpackage.lcw;
import defpackage.nsk;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class DataSecondPageFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f6433a;
    private ImageView b;
    private HealthColumnLinearLayout d;
    private lbt e;
    private Context f;
    private HealthColumnLinearLayout g;
    private IndoorEquipDisplayActivity i;
    private MusicControlLayout k;
    private ImageView l;
    private ImageView n;
    private HealthProgressBar o;
    private SportEquipItemDrawer q;
    private int t;
    private long h = 0;
    private float j = 0.0f;
    private Map<SportDetailChartDataType, BaseRealTimeDynamicChartViewModel> c = new HashMap();
    private Handler m = new Handler() { // from class: com.huawei.indoorequip.ui.DataSecondPageFragment.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                return;
            }
            super.handleMessage(message);
            if (message.what != 7) {
                return;
            }
            if (DataSecondPageFragment.this.isAdded()) {
                if (message.obj instanceof HashMap) {
                    DataSecondPageFragment.this.e((HashMap) message.obj);
                    return;
                }
                return;
            }
            LogUtil.b("Track_IDEQ_DataSecondPageFragment", "updateUi, isAdded = false");
        }
    };

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.data_second_page_layout, (ViewGroup) null);
        if (isAdded() && getActivity() != null) {
            getActivity().setRequestedOrientation(1);
            if (getActivity() instanceof IndoorEquipDisplayActivity) {
                this.i = (IndoorEquipDisplayActivity) getActivity();
            }
        }
        int o = this.i.o();
        this.t = o;
        LogUtil.a("Track_IDEQ_DataSecondPageFragment", "Current sport :", Integer.valueOf(o));
        bVz_(inflate);
        this.q = (SportEquipItemDrawer) inflate.findViewById(R.id.second_pager_drawer);
        this.d = (HealthColumnLinearLayout) inflate.findViewById(R.id.vertical_layout_chart);
        this.e = new lbt(getContext(), false);
        this.b = (ImageView) inflate.findViewById(R.id.ie_bt_icon);
        this.f6433a = (ImageView) inflate.findViewById(R.id.ie_bolt_icon);
        this.o = (HealthProgressBar) inflate.findViewById(R.id.hw_recycler_loading_hpb);
        this.k = (MusicControlLayout) inflate.findViewById(R.id.music_content_layout);
        HealthColumnLinearLayout healthColumnLinearLayout = (HealthColumnLinearLayout) inflate.findViewById(R.id.music_column_layout);
        this.g = healthColumnLinearLayout;
        healthColumnLinearLayout.setPadding(0, nsn.c(this.f, 8.0f), 0, 0);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.ie_music_icon);
        this.n = imageView;
        imageView.setImageDrawable(lbv.bVR_(this.f, R.drawable._2131430349_res_0x7f0b0bcd));
        this.b.setImageDrawable(lbv.bVR_(this.f, lbj.c(this.t, true)));
        if (this.i.ai()) {
            this.f6433a.setVisibility(0);
            this.f6433a.setImageResource(lbv.a(this.i.ah()));
            LogUtil.a("Track_IDEQ_DataSecondPageFragment", "mIndoorEquipDisplayActivity.connectBoltNumber()", Integer.valueOf(this.i.ah()));
        }
        j();
        this.l = (ImageView) inflate.findViewById(R.id.ie_miracast_icon);
        if (Utils.d() == 1 && CommonUtil.bh() && CommonUtil.ba()) {
            this.l.setVisibility(0);
        } else {
            this.l.setVisibility(8);
        }
        return inflate;
    }

    private void j() {
        this.k.setSportTypeDrawable(true, this.t);
        if (lbc.b(this.t)) {
            this.n.setVisibility(8);
            this.g.setVisibility(8);
        } else if (this.k.getTrackSharedPreferenceUtil().f(this.t) == 1 && gwg.a(this.f)) {
            this.n.setVisibility(8);
            this.g.setVisibility(0);
        } else {
            this.n.setVisibility(0);
            this.g.setVisibility(8);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.n.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.indoorequip.ui.DataSecondPageFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DataSecondPageFragment.this.isAdded()) {
                    if (DataSecondPageFragment.this.e()) {
                        LogUtil.h("Track_IDEQ_DataSecondPageFragment", "onClick() too fast");
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                    if (DataSecondPageFragment.this.getActivity() != null) {
                        lbv.c(DataSecondPageFragment.this.getActivity().getApplicationContext(), "MusicBtn");
                    }
                    if (lbv.b(DataSecondPageFragment.this.t)) {
                        if (DataSecondPageFragment.this.k != null) {
                            diy.e(DataSecondPageFragment.this.f, DataSecondPageFragment.this.t);
                            DataSecondPageFragment.this.k.d();
                        }
                    } else if (CommonUtil.bd() && CommonUtil.v(gwh.s)) {
                        Intent intent = new Intent("android.intent.action.MUSIC_PLAYER");
                        intent.setFlags(268435456);
                        intent.setPackage(gwh.s);
                        DataSecondPageFragment.this.startActivity(intent);
                    } else {
                        LogUtil.h("Track_IDEQ_DataSecondPageFragment", "Don't play music.");
                    }
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.l.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.indoorequip.ui.DataSecondPageFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DataSecondPageFragment.this.getActivity() != null) {
                    lbv.c(DataSecondPageFragment.this.getActivity().getApplicationContext(), "ProjectionBtn");
                }
                DataSecondPageFragment.this.i.af();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public boolean e() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.h < 1000) {
            LogUtil.h("Track_IDEQ_DataSecondPageFragment", "onClick ", "click too fast");
            this.h = currentTimeMillis;
            return true;
        }
        this.h = currentTimeMillis;
        return false;
    }

    private void bVz_(View view) {
        SportDetailChartDataType[] c = lbj.c(this.t, this.i.ai(), c());
        if (c == null) {
            return;
        }
        LogUtil.a("Track_IDEQ_DataSecondPageFragment", "sport chart:", Arrays.toString(c));
        for (SportDetailChartDataType sportDetailChartDataType : c) {
            BaseRealTimeDynamicChartViewModel bVx_ = bVx_(view, sportDetailChartDataType);
            if (bVx_ != null) {
                bVx_.updateConfiguration(this.f);
                this.c.put(sportDetailChartDataType, bVx_);
            }
        }
    }

    /* renamed from: com.huawei.indoorequip.ui.DataSecondPageFragment$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[SportDetailChartDataType.values().length];
            e = iArr;
            try {
                iArr[SportDetailChartDataType.STEP_RATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[SportDetailChartDataType.GROUND_CONTACT_TIME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                e[SportDetailChartDataType.GROUND_IMPACT_ACCELERATION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                e[SportDetailChartDataType.ACTIVE_PEAK.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                e[SportDetailChartDataType.REALTIME_PACE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                e[SportDetailChartDataType.PADDLE_FREQUENCY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                e[SportDetailChartDataType.CADENCE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                e[SportDetailChartDataType.POWER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                e[SportDetailChartDataType.SPEED_RATE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    private BaseRealTimeDynamicChartViewModel bVx_(View view, SportDetailChartDataType sportDetailChartDataType) {
        switch (AnonymousClass4.e[sportDetailChartDataType.ordinal()]) {
            case 1:
                lcw lcwVar = new lcw(bVy_(view, R.id.step_rate_chart));
                lcwVar.setDefaultOrdinateY();
                return lcwVar;
            case 2:
                lci lciVar = new lci(bVy_(view, R.id.touchdown_magnet));
                lciVar.setDefaultOrdinateY();
                return lciVar;
            case 3:
                lcj lcjVar = new lcj(bVy_(view, R.id.ground_impact_magnet));
                lcjVar.setDefaultOrdinateY();
                return lcjVar;
            case 4:
                lch lchVar = new lch(bVy_(view, R.id.ground_shock_peak));
                lchVar.setDefaultOrdinateY();
                return lchVar;
            case 5:
                lcp lcpVar = new lcp(bVy_(view, R.id.rt_pace_chart));
                lcpVar.setDefaultOrdinateY();
                return lcpVar;
            case 6:
                lcs lcsVar = new lcs(bVy_(view, R.id.paddle_freq_magnet), c());
                lcsVar.setDefaultOrdinateY();
                return lcsVar;
            case 7:
                lcf lcfVar = new lcf(bVy_(view, R.id.cadence_chart));
                if (this.t == 273) {
                    lcfVar.setOrdinateY(0, 120);
                    return lcfVar;
                }
                lcfVar.setDefaultOrdinateY();
                return lcfVar;
            case 8:
                lcr lcrVar = new lcr(bVy_(view, R.id.power_chart));
                lcrVar.setDefaultOrdinateY();
                return lcrVar;
            case 9:
                lct lctVar = new lct(bVy_(view, R.id.speed_rate_chart));
                lctVar.setDefaultOrdinateY();
                return lctVar;
            default:
                return null;
        }
    }

    private RealTimeDynamicChartView bVy_(View view, int i) {
        RealTimeDynamicChartView realTimeDynamicChartView = (RealTimeDynamicChartView) view.findViewById(i);
        realTimeDynamicChartView.setVisibility(0);
        e(realTimeDynamicChartView);
        return realTimeDynamicChartView;
    }

    private void e(RealTimeDynamicChartView realTimeDynamicChartView) {
        if (nsn.ag(this.f)) {
            HealthTextView healthTextView = (HealthTextView) realTimeDynamicChartView.findViewById(R.id.title);
            HealthTextView healthTextView2 = (HealthTextView) realTimeDynamicChartView.findViewById(R.id.value);
            healthTextView.setTextSize(0, getResources().getDimension(R.dimen._2131362906_res_0x7f0a045a));
            healthTextView.setTypeface(nsk.cKP_());
            healthTextView2.setTextSize(0, getResources().getDimension(R.dimen._2131363006_res_0x7f0a04be));
        }
    }

    public void c(Map<Integer, Object> map) {
        Handler handler;
        if (map == null || !isAdded() || (handler = this.m) == null) {
            return;
        }
        Message obtainMessage = handler.obtainMessage();
        obtainMessage.what = 7;
        obtainMessage.obj = map;
        this.m.sendMessage(obtainMessage);
    }

    public void b() {
        if (!isAdded() || this.b == null) {
            return;
        }
        this.o.setVisibility(8);
        this.b.setImageDrawable(lbv.bVR_(this.f, lbj.c(this.t, false)));
    }

    public void a() {
        if (!isAdded() || this.b == null) {
            return;
        }
        this.o.setVisibility(8);
        this.b.setImageDrawable(lbv.bVR_(this.f, lbj.c(this.t, true)));
    }

    public void d() {
        if (!isAdded() || this.b == null) {
            return;
        }
        this.o.setVisibility(0);
        this.b.setImageDrawable(lbv.bVR_(this.f, lbj.a(this.t)));
    }

    private void h() {
        this.j = 0.0f;
        this.e.c(this.q, new HashMap(), lbj.d(264, 1, -1, c()));
        if (this.c.get(SportDetailChartDataType.GROUND_CONTACT_TIME) != null) {
            this.c.get(SportDetailChartDataType.GROUND_CONTACT_TIME).pushNewData(0, 0.0f);
        }
        if (this.c.get(SportDetailChartDataType.GROUND_IMPACT_ACCELERATION) != null) {
            this.c.get(SportDetailChartDataType.GROUND_IMPACT_ACCELERATION).pushNewData(0, 0.0f);
        }
        if (this.c.get(SportDetailChartDataType.ACTIVE_PEAK) != null) {
            this.c.get(SportDetailChartDataType.ACTIVE_PEAK).pushNewData(0, 0.0f);
        }
    }

    private void d(Map<Integer, Object> map) {
        if (this.t == 264 && ((ffs) map.get(20002)) == null) {
            LogUtil.b("Track_IDEQ_DataSecondPageFragment", "updateUi, runningPosture == null");
            h();
            return;
        }
        for (Map.Entry<SportDetailChartDataType, BaseRealTimeDynamicChartViewModel> entry : this.c.entrySet()) {
            SportDetailChartDataType key = entry.getKey();
            BaseRealTimeDynamicChartViewModel value = entry.getValue();
            if (value == null) {
                LogUtil.b("Track_IDEQ_DataSecondPageFragment", "updateChartUi,chartViewModel == null", key.toString());
            } else {
                c(map, key, value);
            }
        }
    }

    private void c(Map<Integer, Object> map, SportDetailChartDataType sportDetailChartDataType, BaseRealTimeDynamicChartViewModel baseRealTimeDynamicChartViewModel) {
        switch (AnonymousClass4.e[sportDetailChartDataType.ordinal()]) {
            case 1:
                baseRealTimeDynamicChartViewModel.pushNewData(b(map, 4), this.j);
                break;
            case 2:
                baseRealTimeDynamicChartViewModel.pushNewData(((ffs) map.get(20002)).b(), this.j);
                break;
            case 3:
                baseRealTimeDynamicChartViewModel.pushNewData(((ffs) map.get(20002)).e(), this.j);
                break;
            case 4:
                baseRealTimeDynamicChartViewModel.pushNewData(((ffs) map.get(20002)).m(), this.j);
                break;
            case 5:
                b(map, baseRealTimeDynamicChartViewModel);
                break;
            case 6:
                c(map, baseRealTimeDynamicChartViewModel);
                break;
            case 7:
                baseRealTimeDynamicChartViewModel.pushNewData(b(map, 31));
                break;
            case 8:
                baseRealTimeDynamicChartViewModel.pushNewData(b(map, 7));
                break;
            case 9:
                baseRealTimeDynamicChartViewModel.pushNewData(b(map, 3) / 100.0f);
                break;
        }
    }

    private int b(Map<Integer, Object> map, int i) {
        if (map == null || !map.containsKey(Integer.valueOf(i))) {
            return 0;
        }
        Object obj = map.get(Integer.valueOf(i));
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    private void b(Map<Integer, Object> map, BaseRealTimeDynamicChartViewModel baseRealTimeDynamicChartViewModel) {
        int i;
        Object obj = map.get(14);
        if (obj instanceof Integer) {
            i = ((Integer) obj).intValue();
        } else {
            LogUtil.a("Track_IDEQ_DataSecondPageFragment", "pushPaceFreq() other paceFreq = ", obj);
            i = 0;
        }
        baseRealTimeDynamicChartViewModel.pushNewData(i);
    }

    private void c(Map<Integer, Object> map, BaseRealTimeDynamicChartViewModel baseRealTimeDynamicChartViewModel) {
        float f;
        Object obj = map.get(26);
        if (obj instanceof Float) {
            f = ((Float) obj).floatValue();
        } else if (obj instanceof Integer) {
            f = ((Integer) obj).intValue();
        } else {
            LogUtil.a("Track_IDEQ_DataSecondPageFragment", "pushPaddleFreq() other paddleFreq = ", obj);
            f = 0.0f;
        }
        baseRealTimeDynamicChartViewModel.pushNewData(f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Map<Integer, Object> map) {
        if (map == null || this.e == null) {
            LogUtil.b("Track_IDEQ_DataSecondPageFragment", "updateUi, indoorEquipDataStructForShow == null");
            return;
        }
        int[] d = lbj.d(this.t, 1, -1, c());
        if (d != null && d.length > 0) {
            this.e.c(this.q, map, d);
        }
        if (this.t == 264) {
            this.j = ((Integer) map.get(3)).intValue() / 100.0f;
        }
        d(map);
    }

    private String c() {
        IndoorEquipDisplayActivity indoorEquipDisplayActivity = this.i;
        return (indoorEquipDisplayActivity == null || indoorEquipDisplayActivity.l() == null) ? "" : this.i.l().r();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        LogUtil.a("Track_IDEQ_DataSecondPageFragment", "onPause");
        HealthColumnLinearLayout healthColumnLinearLayout = this.d;
        if (healthColumnLinearLayout != null) {
            healthColumnLinearLayout.setVisibility(4);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        LogUtil.a("Track_IDEQ_DataSecondPageFragment", "onResume");
        HealthColumnLinearLayout healthColumnLinearLayout = this.d;
        if (healthColumnLinearLayout != null) {
            healthColumnLinearLayout.setVisibility(0);
        }
        if (this.k != null) {
            if (!SportMusicController.a().d() && IndoorEquipDisplayActivity.a(this.f, this.t)) {
                this.k.e(this.t);
            }
            this.k.c();
            if (lbc.b(this.t)) {
                this.n.setVisibility(8);
                this.g.setVisibility(8);
            } else if (this.k.getTrackSharedPreferenceUtil().f(this.t) == 1 && gwg.a(this.f) && lbv.b(this.t)) {
                this.n.setVisibility(8);
                this.g.setVisibility(0);
            } else {
                this.n.setVisibility(0);
                this.g.setVisibility(8);
            }
        }
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.a("Track_IDEQ_DataSecondPageFragment", "onAttach");
        this.f = context;
    }
}
