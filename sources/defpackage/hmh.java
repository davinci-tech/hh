package defpackage;

import android.content.Context;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.motiontrack.api.ViewHolderBase;
import com.huawei.health.sport.view.SugChart;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.healthcloud.plugintrack.ui.view.sharegroup.SpeedPercentView;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.chart.HealthRingChart;
import com.huawei.ui.commonui.chart.HealthRingChartAdapter;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.anchor.Layout;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class hmh implements ViewHolderBase {

    /* renamed from: a, reason: collision with root package name */
    private Context f13248a;
    private float b;
    private int c;
    private int d;
    private int e;
    private HwHealthBaseCombinedChart f;
    private View g;
    private e h = new e(this);
    private int i;
    private float j;
    private SugChart m;
    private int o;

    public hmh(Context context, int i, int i2) {
        this.f13248a = context;
        this.d = i;
        this.o = i2;
    }

    static class e extends BaseHandler<hmh> {
        e(hmh hmhVar) {
            super(hmhVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: bkA_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(hmh hmhVar, Message message) {
            if (message == null) {
                LogUtil.a("Track_ChartViewHolder", "message is null");
            } else {
                if (message.what == 106) {
                    if (message.obj instanceof int[]) {
                        hmhVar.c((int[]) message.obj);
                        return;
                    }
                    return;
                }
                LogUtil.h("Track_ChartViewHolder", "ChartViewHolderHandler is wrong");
            }
        }
    }

    @Override // com.huawei.health.motiontrack.api.ViewHolderBase
    public View buildView(int i, int i2) {
        View bkw_;
        this.i = i;
        this.e = i2;
        int i3 = this.d;
        if (i3 == 1) {
            bkw_ = bkw_();
        } else if (i3 == 2) {
            bkw_ = bkz_();
        } else if (i3 == 3) {
            bkw_ = bky_();
        } else if (i3 == 4) {
            bkw_ = bkx_();
        } else {
            LogUtil.a("Track_ChartViewHolder", "buildView unknow charttype");
            bkw_ = new View(this.f13248a);
        }
        this.g = bkw_;
        return bkw_;
    }

    private View bkw_() {
        int i = this.o;
        if (i == 100) {
            return bku_();
        }
        if (i == 101) {
            return bks_();
        }
        LogUtil.a("Track_ChartViewHolder", "initHeartRateChart unknown style type");
        return new View(this.f13248a);
    }

    private View bku_() {
        View inflate = View.inflate(this.f13248a, R.layout.track_share_viewholder_heart, null);
        this.f = (HwHealthBaseCombinedChart) inflate.findViewById(R.id.share_detail_heart_chart);
        c((HealthTextView) inflate.findViewById(R.id.track_share_heart_unit), gwh.h);
        c((HealthTextView) inflate.findViewById(R.id.track_share_heart_unit), String.format(this.f13248a.getString(R.string._2130839866_res_0x7f02093a), this.f13248a.getResources().getString(R.string.IDS_main_watch_heart_rate_unit_string)));
        c((HealthTextView) inflate.findViewById(R.id.track_share_device_heart_avg_title), gwh.c);
        c((HealthTextView) inflate.findViewById(R.id.track_share_device_heart_avg_num), gwh.h);
        c((HealthTextView) inflate.findViewById(R.id.track_share_device_heart_avg_unit), gwh.h);
        c((HealthTextView) inflate.findViewById(R.id.text_track_detail_right_unit), gwh.h);
        c((HealthTextView) inflate.findViewById(R.id.track_share_device_heart_max_num), gwh.h);
        c((HealthTextView) inflate.findViewById(R.id.text_track_detail_right_title), gwh.c);
        Layout acquireLayout = this.f.acquireLayout();
        if (acquireLayout != null) {
            acquireLayout.c(0.0f, 0.0f);
            acquireLayout.b(0.0f);
        }
        return inflate;
    }

    private View bks_() {
        View inflate = View.inflate(this.f13248a, R.layout.track_share_viewholder_heart, null);
        this.f = (HwHealthBaseCombinedChart) inflate.findViewById(R.id.share_detail_heart_chart);
        if (inflate.findViewById(R.id.track_share_heart_title) instanceof HealthTextView) {
            c((HealthTextView) inflate.findViewById(R.id.track_share_heart_title), gwh.b);
            c((HealthTextView) inflate.findViewById(R.id.track_share_heart_unit), gwh.b);
        }
        c((HealthTextView) inflate.findViewById(R.id.track_share_heart_unit), String.format(this.f13248a.getString(R.string._2130839866_res_0x7f02093a), this.f13248a.getResources().getString(R.string.IDS_main_watch_heart_rate_unit_string)));
        Layout acquireLayout = this.f.acquireLayout();
        if (acquireLayout != null) {
            acquireLayout.c(0.0f, 0.0f);
            acquireLayout.b(0.0f);
        }
        return inflate;
    }

    private View bkz_() {
        int i = this.o;
        if (i == 100) {
            return bkv_();
        }
        if (i == 101) {
            return bkt_();
        }
        LogUtil.a("Track_ChartViewHolder", "initStepRateChart unknow styletype");
        return new View(this.f13248a);
    }

    private View bkv_() {
        View inflate = View.inflate(this.f13248a, R.layout.track_share_viewholder_steprate, null);
        this.m = (SugChart) inflate.findViewById(R.id.sug_sc_j_bupin);
        i();
        return inflate;
    }

    private View bkt_() {
        View inflate = View.inflate(this.f13248a, R.layout.track_share_viewholder_steprate, null);
        c((HealthTextView) inflate.findViewById(R.id.step_rate_info), gwh.b);
        c((HealthTextView) inflate.findViewById(R.id.step_avg), gwh.b);
        c((HealthTextView) inflate.findViewById(R.id.sug_tv_tb_bupinrate), gwh.r);
        c((HealthTextView) inflate.findViewById(R.id.step_max), gwh.b);
        c((HealthTextView) inflate.findViewById(R.id.sug_tv_tb_bupinbig), gwh.r);
        this.m = (SugChart) inflate.findViewById(R.id.sug_sc_j_bupin);
        c((HealthTextView) inflate.findViewById(R.id.sug_tv_tb_bupinbig_unit), gwh.r);
        c((HealthTextView) inflate.findViewById(R.id.sug_tv_tb_bupinrate_unit), gwh.r);
        c((HealthTextView) inflate.findViewById(R.id.step_rate_y_unit), gwh.b);
        c((HealthTextView) inflate.findViewById(R.id.step_rate_x_unit), gwh.b);
        i();
        return inflate;
    }

    private void i() {
        this.m.setType(1);
        this.m.c(2);
        this.m.setLineWidth(r0.a(3.0f));
        this.m.d(r0.a(10.0f));
        this.m.c(2.0f);
        if (this.o == 101) {
            this.m.e(gwh.b, gwh.e);
            this.m.setLineColor(gwh.o);
        } else {
            this.m.e(gwh.c, gwh.d);
            this.m.setLineColor(gwh.m);
        }
    }

    private View bky_() {
        View inflate = View.inflate(this.f13248a, R.layout.track_share_viewholder_speed_percent, null);
        if (UnitUtil.h()) {
            HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.move_speed);
            HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.move_speed_unit);
            healthTextView.setText(this.f13248a.getString(R.string._2130843164_res_0x7f02161c));
            healthTextView2.setText(String.format(this.f13248a.getString(R.string._2130839866_res_0x7f02093a), this.f13248a.getResources().getString(R.string._2130844078_res_0x7f0219ae)));
        }
        HealthTextView healthTextView3 = (HealthTextView) inflate.findViewById(R.id.track_share_basketball_speed_avg_title);
        if (LanguageUtil.j(this.f13248a) || LanguageUtil.bn(this.f13248a) || LanguageUtil.bi(this.f13248a)) {
            healthTextView3.setText(this.f13248a.getResources().getString(R.string._2130843165_res_0x7f02161d));
        } else {
            healthTextView3.setText(this.f13248a.getResources().getString(R.string._2130843150_res_0x7f02160e));
        }
        return inflate;
    }

    private View bkx_() {
        View inflate = View.inflate(this.f13248a, R.layout.track_share_viewholder_speed_percent, null);
        ((HealthTextView) inflate.findViewById(R.id.move_speed_unit)).setVisibility(8);
        ((HealthTextView) inflate.findViewById(R.id.move_speed)).setText(R.string._2130843485_res_0x7f02175d);
        ((LinearLayout) inflate.findViewById(R.id.speed_average_layout)).setVisibility(8);
        return inflate;
    }

    private void c(HealthTextView healthTextView, int i) {
        healthTextView.setTextColor(i);
    }

    private void c(HealthTextView healthTextView, String str) {
        healthTextView.setText(str);
    }

    public void c(hjw hjwVar, float f, float f2) {
        if (this.g == null) {
            return;
        }
        this.j = f;
        this.b = f2;
        int i = this.d;
        if (i == 1) {
            d(hjwVar);
            return;
        }
        if (i == 2) {
            a(hjwVar);
            return;
        }
        if (i == 3) {
            b(hjwVar);
        } else if (i == 4) {
            e(hjwVar);
        } else {
            LogUtil.a("Track_ChartViewHolder", "addData unknow charttype");
        }
    }

    private void e(hjw hjwVar) {
        SpeedPercentView speedPercentView = (SpeedPercentView) this.g.findViewById(R.id.speed_percent_view);
        if (hjwVar != null) {
            speedPercentView.setVisibility(0);
            speedPercentView.setData(hjwVar);
        } else {
            speedPercentView.setVisibility(8);
        }
    }

    private void d(hjw hjwVar) {
        int i = (int) this.b;
        int i2 = (int) this.j;
        c((HealthTextView) this.g.findViewById(R.id.track_share_device_heart_avg_num), UnitUtil.e(i, 1, 0));
        c((HealthTextView) this.g.findViewById(R.id.track_share_device_heart_max_num), UnitUtil.e(i2, 1, 0));
        c(hjwVar);
    }

    private void c(final hjw hjwVar) {
        if (hjwVar == null) {
            this.g.findViewById(R.id.heart_rate_chart_show_layout).setVisibility(8);
            return;
        }
        HealthRingChart healthRingChart = (HealthRingChart) this.g.findViewById(R.id.sug_sc_j_heart_pie);
        if (healthRingChart != null) {
            healthRingChart.setVisibility(8);
        }
        jdx.b(new Runnable() { // from class: hmh.1
            @Override // java.lang.Runnable
            public void run() {
                ArrayList<HeartRateData> heartRateList = hjwVar.j().getHeartRateList();
                String extendDataString = hjwVar.e().getExtendDataString("isTrustHeartRate");
                UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class);
                int[] c = ffw.c(heartRateList, hjwVar.e().requestHeartRateZoneType(), hjwVar.e().requestTotalTime(), extendDataString, userProfileMgrApi.getLocalUserInfo(), hmh.this.i);
                HeartZoneConf e2 = ffw.e(hmh.this.i, hmh.this.e, userProfileMgrApi.getLocalUserInfo());
                hmh.this.c = e2.getClassifyMethod();
                Message obtainMessage = hmh.this.h.obtainMessage();
                obtainMessage.what = 106;
                obtainMessage.obj = c;
                hmh.this.h.sendMessage(obtainMessage);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int[] iArr) {
        int i = iArr[3];
        int i2 = iArr[4];
        int i3 = iArr[2];
        int i4 = iArr[1];
        int i5 = iArr[0];
        View view = this.g;
        if (view == null) {
            return;
        }
        if (i5 + i4 + i3 + i + i2 == 0) {
            View findViewById = view.findViewById(R.id.heart_rate_chart_show_layout);
            if (findViewById != null) {
                findViewById.setVisibility(8);
                return;
            }
            return;
        }
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(Integer.valueOf(i2));
        arrayList.add(Integer.valueOf(i));
        arrayList.add(Integer.valueOf(i3));
        arrayList.add(Integer.valueOf(i4));
        arrayList.add(Integer.valueOf(i5));
        List<nkz> c = c(arrayList);
        HealthRingChart healthRingChart = (HealthRingChart) this.g.findViewById(R.id.sug_sc_j_heart_pie);
        if (healthRingChart == null) {
            return;
        }
        healthRingChart.setVisibility(0);
        HealthRingChartAdapter healthRingChartAdapter = new HealthRingChartAdapter(this.f13248a, new nld().c(false).b(true), c);
        healthRingChartAdapter.a(new HealthRingChartAdapter.DataFormatter() { // from class: hmi
            @Override // com.huawei.ui.commonui.chart.HealthRingChartAdapter.DataFormatter
            public final String format(nkz nkzVar) {
                return hmh.this.d(nkzVar);
            }
        });
        healthRingChart.setAdapter(healthRingChartAdapter);
        if (LanguageUtil.j(this.f13248a)) {
            healthRingChart.setDesc(this.f13248a.getResources().getString(R.string._2130841430_res_0x7f020f56), this.f13248a.getResources().getString(R.string._2130843120_res_0x7f0215f0));
        }
    }

    /* synthetic */ String d(nkz nkzVar) {
        return c((int) nkzVar.i());
    }

    private List<nkz> c(List<Integer> list) {
        ArrayList arrayList = new ArrayList(5);
        int i = this.c;
        if (i == 3) {
            arrayList.add(this.f13248a.getString(R.string._2130845602_res_0x7f021fa2));
            arrayList.add(this.f13248a.getString(R.string._2130842685_res_0x7f02143d));
            arrayList.add(this.f13248a.getString(R.string._2130842686_res_0x7f02143e));
            arrayList.add(this.f13248a.getString(R.string._2130842687_res_0x7f02143f));
            arrayList.add(this.f13248a.getString(R.string._2130845603_res_0x7f021fa3));
        } else if (i == 1) {
            arrayList.add(this.f13248a.getString(R.string._2130842683_res_0x7f02143b));
            arrayList.add(this.f13248a.getString(R.string._2130842684_res_0x7f02143c));
            arrayList.add(this.f13248a.getString(R.string._2130842685_res_0x7f02143d));
            arrayList.add(this.f13248a.getString(R.string._2130842686_res_0x7f02143e));
            arrayList.add(this.f13248a.getString(R.string._2130842687_res_0x7f02143f));
        } else {
            arrayList.add(this.f13248a.getString(R.string._2130841807_res_0x7f0210cf));
            arrayList.add(this.f13248a.getString(R.string._2130841808_res_0x7f0210d0));
            arrayList.add(this.f13248a.getString(R.string._2130841809_res_0x7f0210d1));
            arrayList.add(this.f13248a.getString(R.string._2130841894_res_0x7f021126));
            arrayList.add(this.f13248a.getString(R.string._2130841810_res_0x7f0210d2));
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(this.f13248a, R.color._2131298929_res_0x7f090a71)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(this.f13248a, R.color._2131298927_res_0x7f090a6f)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(this.f13248a, R.color._2131298925_res_0x7f090a6d)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(this.f13248a, R.color._2131298931_res_0x7f090a73)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(this.f13248a, R.color._2131298933_res_0x7f090a75)));
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(Integer.valueOf(ContextCompat.getColor(this.f13248a, R.color._2131298928_res_0x7f090a70)));
        arrayList3.add(Integer.valueOf(ContextCompat.getColor(this.f13248a, R.color._2131298926_res_0x7f090a6e)));
        arrayList3.add(Integer.valueOf(ContextCompat.getColor(this.f13248a, R.color._2131298924_res_0x7f090a6c)));
        arrayList3.add(Integer.valueOf(ContextCompat.getColor(this.f13248a, R.color._2131298930_res_0x7f090a72)));
        arrayList3.add(Integer.valueOf(ContextCompat.getColor(this.f13248a, R.color._2131298932_res_0x7f090a74)));
        int size = arrayList.size();
        ArrayList arrayList4 = new ArrayList(size);
        int i2 = 0;
        while (i2 < size) {
            arrayList4.add(new nkz(i2 < arrayList.size() ? (String) arrayList.get(i2) : "", list.get(i2).intValue(), i2 < arrayList2.size() ? ((Integer) arrayList2.get(i2)).intValue() : 0, i2 < arrayList3.size() ? ((Integer) arrayList3.get(i2)).intValue() : 0));
            i2++;
        }
        return arrayList4;
    }

    private String c(int i) {
        Context context = this.f13248a;
        if (context == null) {
            return null;
        }
        if (i > 0 && i < 60) {
            return context.getString(R.string._2130839496_res_0x7f0207c8, 1);
        }
        return nsn.e((int) TimeUnit.SECONDS.toMinutes(i), this.f13248a);
    }

    private void a(hjw hjwVar) {
        int i = (int) this.j;
        c((HealthTextView) this.g.findViewById(R.id.sug_tv_tb_bupinbig), UnitUtil.e(i, 1, 0));
        c((HealthTextView) this.g.findViewById(R.id.sug_tv_tb_bupinrate), UnitUtil.e(this.b, 1, 0));
        this.m.setYAxisValues(0, ffw.c(i), 2);
        this.m.c(hjwVar.e().requestTotalTime());
        ffw.d(ffw.d(hjwVar.d().requestStepRateList(), hjwVar.e().requestTrackType(), 10000), hjwVar.e().requestTrackType(), this.m);
    }

    private void b(hjw hjwVar) {
        float f = this.b;
        float f2 = this.j;
        HealthTextView healthTextView = (HealthTextView) this.g.findViewById(R.id.track_share_basketball_speed_avg_num);
        HealthTextView healthTextView2 = (HealthTextView) this.g.findViewById(R.id.track_share_basketball_speed_max_num);
        HealthTextView healthTextView3 = (HealthTextView) this.g.findViewById(R.id.move_speed);
        HealthTextView healthTextView4 = (HealthTextView) this.g.findViewById(R.id.move_speed_unit);
        c(healthTextView, UnitUtil.e(f, 1, 1));
        c(healthTextView2, UnitUtil.e(f2, 1, 1));
        Context context = this.f13248a;
        if (context != null) {
            c(healthTextView3, context.getString(R.string._2130843164_res_0x7f02161c));
        }
        SpeedPercentView speedPercentView = (SpeedPercentView) this.g.findViewById(R.id.speed_percent_view);
        if (this.f13248a != null) {
            if (UnitUtil.h()) {
                c(healthTextView4, String.format(this.f13248a.getString(R.string._2130839866_res_0x7f02093a), this.f13248a.getResources().getString(R.string._2130844079_res_0x7f0219af)));
            } else {
                c(healthTextView4, String.format(this.f13248a.getString(R.string._2130839866_res_0x7f02093a), this.f13248a.getResources().getString(R.string._2130844078_res_0x7f0219ae)));
            }
        }
        if (hjwVar != null) {
            speedPercentView.setVisibility(0);
            speedPercentView.setData(hjwVar);
        } else {
            speedPercentView.setVisibility(8);
        }
    }

    @Override // com.huawei.health.motiontrack.api.ViewHolderBase
    public HwHealthBaseCombinedChart acquireHeartRateChart() {
        return this.f;
    }
}
