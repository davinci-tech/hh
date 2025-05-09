package com.huawei.ui.main.stories.health.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.trusport.Coach;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.model.unite.GetRunLevelRsp;
import com.huawei.hwcommonmodel.fitnessdatatype.Vo2maxDetail;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.fragment.AchievementPredictionFragment;
import com.huawei.ui.main.stories.health.interactors.healthdata.RunningLevelCurrentData;
import com.huawei.ui.main.stories.health.interactors.healthdata.RunningStateIndexData;
import defpackage.ggl;
import defpackage.hqa;
import defpackage.kor;
import defpackage.nsy;
import defpackage.ruf;
import defpackage.sdk;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes6.dex */
public class AchievementPredictionFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f10164a;
    private HealthTextView b;
    private HealthTextView c;
    private Handler d = new b(this);
    private HealthTextView e;
    private View f;
    private int j;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f = layoutInflater.inflate(R.layout.fragment_achievement_prediction_child, viewGroup, false);
        a();
        d();
        return this.f;
    }

    private void a() {
        ((HealthSubHeader) nsy.cMd_(this.f, R.id.achievements_title_text)).setSubHeaderBackgroundColor(ContextCompat.getColor(BaseApplication.e(), R.color._2131296971_res_0x7f0902cb));
        e((HealthTextView) nsy.cMd_(this.f, R.id.running_race_5km), (HealthTextView) nsy.cMd_(this.f, R.id.running_race_10km));
        this.c = (HealthTextView) nsy.cMd_(this.f, R.id.running_race_5km_value);
        this.b = (HealthTextView) nsy.cMd_(this.f, R.id.running_race_10km_value);
        this.f10164a = (HealthTextView) nsy.cMd_(this.f, R.id.running_half_marathon_value);
        this.e = (HealthTextView) nsy.cMd_(this.f, R.id.running_marathon_value);
        this.e = (HealthTextView) nsy.cMd_(this.f, R.id.running_marathon_value);
    }

    private void d() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            LogUtil.h("AchievementPredictionChildFragment", "initData arguments is null");
            return;
        }
        this.j = arguments.getInt("vo2max_value", 0);
        Parcelable parcelable = arguments.getParcelable("running_level_data");
        if (parcelable instanceof RunningStateIndexData) {
            RunningStateIndexData runningStateIndexData = (RunningStateIndexData) parcelable;
            if (a(runningStateIndexData)) {
                c(runningStateIndexData);
                b(runningStateIndexData.getRunningLevelCurrentData());
                ReleaseLogUtil.e("AchievementPredictionChildFragment", "running state index data from last page ,", runningStateIndexData.toString());
                return;
            }
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: qic
            @Override // java.lang.Runnable
            public final void run() {
                AchievementPredictionFragment.this.e();
            }
        });
    }

    public /* synthetic */ void e() {
        int a2 = ggl.a(new Date(System.currentTimeMillis()));
        new hqa().a(BaseApplication.e(), a2, a2, 0, new d(this));
    }

    private void e(HealthTextView healthTextView, HealthTextView healthTextView2) {
        healthTextView.setText(String.format(Locale.ENGLISH, "%s %2s", UnitUtil.e(5.0d, 1, 0), BaseApplication.e().getString(R$string.IDS_band_data_sport_distance_unit)));
        healthTextView2.setText(String.format(Locale.ENGLISH, "%s %2s", UnitUtil.e(10.0d, 1, 0), BaseApplication.e().getString(R$string.IDS_band_data_sport_distance_unit)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(RunningStateIndexData runningStateIndexData) {
        RunningLevelCurrentData runningLevelCurrentData = runningStateIndexData.getRunningLevelCurrentData();
        b(UnitUtil.d(runningLevelCurrentData.getTimeForFiveKm()), UnitUtil.d(runningLevelCurrentData.getTimeForTenKm()), UnitUtil.d(runningLevelCurrentData.getHalfMarathon()), UnitUtil.d(runningLevelCurrentData.getMarathon()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("AchievementPredictionChildFragment", "enter CalculateAchievementByOxygen");
        int[] runningPerformancePrediction = Coach.runningPerformancePrediction(this.j);
        ReleaseLogUtil.e("AchievementPredictionChildFragment", "maximum oxygen uptake is ", Integer.valueOf(this.j), "calculate results is ", Arrays.toString(runningPerformancePrediction));
        if (runningPerformancePrediction != null && runningPerformancePrediction.length == 4) {
            b(UnitUtil.d(runningPerformancePrediction[0]), UnitUtil.d(runningPerformancePrediction[1]), UnitUtil.d(runningPerformancePrediction[2]), UnitUtil.d(runningPerformancePrediction[3]));
        } else {
            i();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        String string = getString(R$string.IDS_rq_no_forecast);
        b(string, string, string, string);
    }

    private void b(String str, String str2, String str3, String str4) {
        this.c.setText(str);
        this.b.setText(str2);
        this.f10164a.setText(str3);
        this.e.setText(str4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(RunningStateIndexData runningStateIndexData) {
        RunningLevelCurrentData runningLevelCurrentData;
        return runningStateIndexData != null && (runningLevelCurrentData = runningStateIndexData.getRunningLevelCurrentData()) != null && runningLevelCurrentData.getTimeForFiveKm() > 0 && runningLevelCurrentData.getTimeForTenKm() > 0 && runningLevelCurrentData.getHalfMarathon() > 0 && runningLevelCurrentData.getMarathon() > 0;
    }

    private boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("AchievementPredictionChildFragment", "hasDataFromLocalCache data is null or empty");
            return false;
        }
        String[] split = str.split(Constants.LINK);
        return (split.length != 4 || TextUtils.isEmpty(split[0]) || TextUtils.isEmpty(split[1]) || TextUtils.isEmpty(split[2]) || TextUtils.isEmpty(split[3])) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(RunningLevelCurrentData runningLevelCurrentData) {
        sdk.c().a(UnitUtil.d(runningLevelCurrentData.getTimeForFiveKm()) + Constants.LINK + UnitUtil.d(runningLevelCurrentData.getTimeForTenKm()) + Constants.LINK + UnitUtil.d(runningLevelCurrentData.getHalfMarathon()) + Constants.LINK + UnitUtil.d(runningLevelCurrentData.getMarathon()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        String d2 = sdk.c().d();
        if (d(d2)) {
            String[] split = d2.split(Constants.LINK);
            b(split[0], split[1], split[2], split[3]);
            ReleaseLogUtil.e("AchievementPredictionChildFragment", "running state index data from sp ,", split);
        } else if (this.j > 0) {
            ReleaseLogUtil.e("AchievementPredictionChildFragment", "maximum oxygen uptake from previous page");
            c();
        } else {
            kor.a().e(new c(this));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        Handler handler = this.d;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    static class b extends BaseHandler<AchievementPredictionFragment> {
        b(AchievementPredictionFragment achievementPredictionFragment) {
            super(achievementPredictionFragment);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dDQ_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(AchievementPredictionFragment achievementPredictionFragment, Message message) {
            switch (message.what) {
                case 101:
                    if (!(message.obj instanceof GetRunLevelRsp)) {
                        achievementPredictionFragment.b();
                        break;
                    } else {
                        RunningStateIndexData c = ruf.c((GetRunLevelRsp) message.obj);
                        if (achievementPredictionFragment.a(c)) {
                            achievementPredictionFragment.c(c);
                            achievementPredictionFragment.b(c.getRunningLevelCurrentData());
                            ReleaseLogUtil.e("AchievementPredictionChildFragment", "running state index data from cloud ,", c.toString());
                            break;
                        } else {
                            achievementPredictionFragment.b();
                            break;
                        }
                    }
                case 102:
                    achievementPredictionFragment.b();
                    break;
                case 103:
                    if (message.obj instanceof Vo2maxDetail) {
                        achievementPredictionFragment.j = ((Vo2maxDetail) message.obj).getVo2maxValue();
                        achievementPredictionFragment.c();
                        ReleaseLogUtil.e("AchievementPredictionChildFragment", "maximum oxygen uptake from data platform");
                        break;
                    } else {
                        achievementPredictionFragment.i();
                        break;
                    }
                case 104:
                    achievementPredictionFragment.i();
                    break;
                default:
                    LogUtil.h("AchievementPredictionChildFragment", "achievementPredictionHandler switch default");
                    break;
            }
        }
    }

    static class d implements IBaseResponseCallback {
        private WeakReference<AchievementPredictionFragment> b;

        d(AchievementPredictionFragment achievementPredictionFragment) {
            this.b = new WeakReference<>(achievementPredictionFragment);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            AchievementPredictionFragment achievementPredictionFragment = this.b.get();
            if (achievementPredictionFragment == null || achievementPredictionFragment.d == null) {
                LogUtil.h("AchievementPredictionChildFragment", "MyRunningData onResponse fragment or mHandler is null");
                return;
            }
            if (i != 200) {
                achievementPredictionFragment.d.sendEmptyMessage(102);
                return;
            }
            Message obtain = Message.obtain();
            obtain.obj = obj;
            obtain.what = 101;
            achievementPredictionFragment.d.sendMessage(obtain);
        }
    }

    static class c implements IBaseResponseCallback {
        WeakReference<AchievementPredictionFragment> d;

        c(AchievementPredictionFragment achievementPredictionFragment) {
            this.d = new WeakReference<>(achievementPredictionFragment);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            AchievementPredictionFragment achievementPredictionFragment = this.d.get();
            if (achievementPredictionFragment == null || achievementPredictionFragment.d == null) {
                LogUtil.h("AchievementPredictionChildFragment", "MaximumOxygenUptakeData onResponse fragment or mHandler is null");
                return;
            }
            if (i != 0) {
                achievementPredictionFragment.d.sendEmptyMessage(104);
                return;
            }
            Message obtain = Message.obtain();
            obtain.obj = obj;
            obtain.what = 103;
            achievementPredictionFragment.d.sendMessage(obtain);
        }
    }
}
