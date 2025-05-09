package com.huawei.ui.main.stories.fitness.activity.sportintensity;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.views.base.TotalDataRectView;
import defpackage.nrn;
import defpackage.nsf;
import defpackage.nsy;
import defpackage.pva;
import defpackage.pvc;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes6.dex */
public class SportIntensityDataInteractor {

    /* renamed from: a, reason: collision with root package name */
    private e f9884a;
    private Handler b = new d(this);
    private Context c;
    private HealthTextView d;
    private HealthTextView e;
    private HealthTextView f;
    private HealthTextView g;
    private HealthTextView h;
    private TotalDataRectView i;
    private HealthTextView j;
    private HealthTextView m;

    public SportIntensityDataInteractor(Context context) {
        this.c = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(pva pvaVar) {
        double d2;
        double d3;
        double d4;
        double d5;
        double d6;
        double d7;
        double d8;
        if (pvaVar == null) {
            a(0.0d, 0.0d, 0.0d, 0.0d, 0.0d);
            LogUtil.h("Step_SportIntensityDataInterActor", "intensity is null. ");
            d7 = 0.0d;
            d8 = 0.0d;
            d5 = 0.0d;
            d6 = 0.0d;
            d4 = 0.0d;
            d3 = 0.0d;
        } else {
            double d9 = pvaVar.d() + pvaVar.f() + pvaVar.i();
            double c = pvaVar.c() + pvaVar.e() + pvaVar.h() + pvaVar.a() + d9;
            LogUtil.a("Step_SportIntensityDataInterActor", "updateBottomTeleVisions, sumData = ", Integer.valueOf(pvaVar.b()), "  sumData", Double.valueOf(c));
            if (pvaVar.b() > c) {
                d2 = pvaVar.b() - c;
                c = pvaVar.b();
            } else {
                d2 = 0.0d;
            }
            double b = b(pvaVar.e(), c);
            double b2 = b(pvaVar.c(), c);
            double b3 = b(pvaVar.a(), c);
            double b4 = b(d2 + d9, c);
            LogUtil.a("Step_SportIntensityDataInterActor", "updateBottomTeleVisions, per_cent run = ", Double.valueOf(b), " ride = ", Double.valueOf(b2), " fitness = ", Double.valueOf(b3), "convergenceData =", Double.valueOf(d9), " other = ", Double.valueOf(b4));
            double d10 = b + b2 + b3 + b4;
            if (d10 > 100.0d) {
                double d11 = d10 - 100.0d;
                if (b >= b2 && b >= b3 && b >= b4) {
                    b -= d11;
                } else if (b3 >= b && b3 >= b2 && b3 >= b4) {
                    b3 -= d11;
                } else if (b2 >= b && b2 >= b3 && b2 >= b4) {
                    b2 -= d11;
                } else if (b4 >= b && b4 >= b2 && b4 >= b3) {
                    b4 -= d11;
                }
            }
            double round = Math.round(((pvaVar.h() * 1.0d) / c) * 100.0d);
            if (pvaVar.h() > 0 && pvaVar.b() > 0) {
                round = (((100.0d - b) - b2) - b3) - b4;
            }
            double d12 = round >= 0.0d ? round : 0.0d;
            LogUtil.a("Step_SportIntensityDataInterActor", "updateBottomTeleVisions, per_cent walk =", Double.valueOf(d12), "  run = ", Double.valueOf(b), " ride = ", Double.valueOf(b2), " fitness = ", Double.valueOf(b3), " other = ", Double.valueOf(b4));
            d3 = c;
            d4 = b4;
            d5 = b2;
            d6 = b3;
            d7 = d12;
            d8 = b;
        }
        a(d7, d8, d5, d6, d4);
        this.i.setViewData((float) (d8 * d3), (float) (d7 * d3), (float) (d3 * d5), (float) (d3 * d6), (float) (d3 * d4));
        this.i.invalidate();
    }

    private double b(double d2, double d3) {
        double d4 = d2 * 1.0d;
        double round = Math.round((d4 / d3) * 100.0d);
        if (round >= 1.0d || d4 <= 0.0d) {
            return round;
        }
        return 1.0d;
    }

    private void a(double d2, double d3, double d4, double d5, double d6) {
        this.h.setText(dtT_(d2));
        this.g.setText(dtT_(d3));
        this.j.setText(dtT_(d4));
        this.f.setText(dtT_(d5));
        this.e.setText(dtT_(d6));
    }

    protected void dtU_(Activity activity) {
        if (activity == null) {
            LogUtil.b("Step_SportIntensityDataInterActor", "initTotalData  Failed , activity is null！ ");
            return;
        }
        this.i = (TotalDataRectView) nsy.cMc_(activity, R.id.total_data_tv_bg);
        this.g = (HealthTextView) nsy.cMc_(activity, R.id.fitness_detail_total_run_time_data);
        this.h = (HealthTextView) nsy.cMc_(activity, R.id.fitness_detail_total_walk_time_data);
        this.j = (HealthTextView) nsy.cMc_(activity, R.id.fitness_detail_total_ride_time_data);
        this.f = (HealthTextView) nsy.cMc_(activity, R.id.fitness_detail_total_train_time_data);
        this.e = (HealthTextView) nsy.cMc_(activity, R.id.fitness_detail_total_other_time_data);
        HealthTextView healthTextView = (HealthTextView) nsy.cMc_(activity, R.id.view_proportionSubHeader_title);
        this.d = healthTextView;
        healthTextView.setText(this.c.getResources().getString(R$string.IDS_workout_percentage_title));
        e(activity.getResources().getColor(R.color._2131297740_res_0x7f0905cc), activity.getResources().getColor(R.color._2131297744_res_0x7f0905d0), activity.getResources().getColor(R.color._2131297742_res_0x7f0905ce), activity.getResources().getColor(R.color._2131297738_res_0x7f0905ca), nrn.d(R.color._2131296450_res_0x7f0900c2));
        this.i.setColors(activity.getResources().getColor(R.color._2131297740_res_0x7f0905cc), activity.getResources().getColor(R.color._2131297744_res_0x7f0905d0), activity.getResources().getColor(R.color._2131297742_res_0x7f0905ce), activity.getResources().getColor(R.color._2131297738_res_0x7f0905ca), nrn.d(R.color._2131296450_res_0x7f0900c2));
        this.m = (HealthTextView) nsy.cMc_(activity, R.id.card_view_what_sports_item_content);
        this.m.setText(nsf.b(R$string.IDS_workout_introduction, nsf.a(R.plurals._2130903370_res_0x7f03014a, 150, UnitUtil.e(150.0d, 1, 0)), nsf.a(R.plurals._2130903370_res_0x7f03014a, 75, UnitUtil.e(75.0d, 1, 0))));
    }

    private void e(int i, int i2, int i3, int i4, int i5) {
        this.g.setTextColor(i);
        this.h.setTextColor(i2);
        this.j.setTextColor(i3);
        this.f.setTextColor(i4);
        this.e.setTextColor(i5);
    }

    public void a(long j, long j2, int i) {
        LogUtil.a("Step_SportIntensityDataInterActor", "requestTotalDataByType , startTime = ", Long.valueOf(j), ", endTime = ", Long.valueOf(j2), ", type = ", Integer.valueOf(i));
        if (this.f9884a == null) {
            this.f9884a = new e(this);
        }
        pvc.a().e(j, j2, i, this.f9884a);
    }

    private SpannableString dtT_(double d2) {
        return UnitUtil.bCR_(this.c, "[\\d]", UnitUtil.e(d2, 2, 0), R.style.fitness_detail_data_percent_number_style_num, R.style.fitness_detail_data_percent_number_style_sign);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public pva c(List<pva> list) {
        LogUtil.a("Step_SportIntensityDataInterActor", "getTotalData ");
        if (list != null && !list.isEmpty()) {
            pva pvaVar = list.get(0);
            LogUtil.a("Step_SportIntensityDataInterActor", "intensity = ", pvaVar.toString());
            return pvaVar;
        }
        return new pva();
    }

    public static class d extends Handler {
        private WeakReference<SportIntensityDataInteractor> d;

        public d(SportIntensityDataInteractor sportIntensityDataInteractor) {
            this.d = new WeakReference<>(sportIntensityDataInteractor);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                return;
            }
            super.handleMessage(message);
            SportIntensityDataInteractor sportIntensityDataInteractor = this.d.get();
            if (sportIntensityDataInteractor != null && message.what == 101 && (message.obj instanceof pva)) {
                sportIntensityDataInteractor.b((pva) message.obj);
            }
        }
    }

    public static class e implements InterfaceSportIntensityData {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<SportIntensityDataInteractor> f9885a;

        e(SportIntensityDataInteractor sportIntensityDataInteractor) {
            this.f9885a = new WeakReference<>(sportIntensityDataInteractor);
        }

        @Override // com.huawei.ui.main.stories.fitness.activity.sportintensity.InterfaceSportIntensityData
        public void onResponse(int i, List<pva> list) {
            SportIntensityDataInteractor sportIntensityDataInteractor = this.f9885a.get();
            if (sportIntensityDataInteractor != null) {
                pva c = sportIntensityDataInteractor.c(list);
                Message obtain = Message.obtain();
                obtain.obj = c;
                obtain.what = 101;
                sportIntensityDataInteractor.b.sendMessage(obtain);
                return;
            }
            LogUtil.h("Step_SportIntensityDataInterActor", "dataInteractor is null ");
        }
    }
}
