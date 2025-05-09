package defpackage;

import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.section.section.MeasureCardItemHolder;
import com.huawei.health.section.section.MeasureIndicator;
import com.huawei.health.servicesui.R$string;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class fbz extends MeasureCardItemHolder {
    private MeasureIndicator b;
    private double c;

    public fbz(View view) {
        super(view);
        this.c = -1.0d;
        this.mIcon.setImageResource(R.drawable._2131429936_res_0x7f0b0a30);
        this.mTitle = a();
        nsy.cMr_(this.mTitleTextView, this.mTitle);
    }

    private String a() {
        return BaseApplication.e().getResources().getString(R$string.IDS_vascular_elasticity_test).replace("\n", "");
    }

    @Override // com.huawei.health.section.section.MeasureCardItemHolder
    public long getNewDataTimeStamp(fbp fbpVar) {
        HiHealthData d = fbpVar.d();
        if (d != null) {
            return d.getStartTime();
        }
        return 0L;
    }

    @Override // com.huawei.health.section.section.MeasureCardItemHolder
    public void syncEcgData() {
        LogUtil.a("VascularItemHolder", "syncEcgData in VascularItemHolder");
    }

    @Override // com.huawei.health.section.section.MeasureCardItemHolder
    public void bindDesc(fbp fbpVar) {
        HiHealthData d = fbpVar.d();
        if (d == null) {
            this.mPrimaryTipTextView.setText(R$string.IDS_vascular_no_data_tip);
            this.mSecondaryTipTextView.setVisibility(8);
            this.mTimeTextView.setText((CharSequence) null);
            this.c = -1.0d;
            onDataAvailable(false);
            return;
        }
        this.mTimeTextView.setText(DateFormatUtil.b(d.getStartTime(), DateFormatUtil.DateFormatType.DATE_FORMAT_MONTH_DAY_HH_MM));
        List<fbl> f = fbpVar.f();
        double e = koq.c(f) ? f.get(0).e() : -1.0d;
        this.c = e;
        Pair<Integer, Integer> avV_ = avV_(e);
        this.mPrimaryTipTextView.setText(((Integer) avV_.first).intValue());
        this.mSecondaryTipTextView.setVisibility(0);
        this.mSecondaryTipTextView.setText(((Integer) avV_.second).intValue());
        onDataAvailable(true);
    }

    private Pair<Integer, Integer> avV_(double d) {
        if (d < 8.0d) {
            return new Pair<>(Integer.valueOf(R$string.IDS_vascular_good), Integer.valueOf(R$string.IDS_vascular_good_tip));
        }
        if (d >= 8.0d && d <= 10.0d) {
            return new Pair<>(Integer.valueOf(R$string.IDS_vascular_less_good), Integer.valueOf(R$string.IDS_vascular_less_good_tip));
        }
        return new Pair<>(Integer.valueOf(R$string.IDS_vascular_bad), Integer.valueOf(R$string.IDS_vascular_bad_tip));
    }

    @Override // com.huawei.health.section.section.MeasureCardItemHolder
    public void bindChart(fbp fbpVar) {
        boolean z = this.c != -1.0d;
        if (this.b == null && z) {
            this.b = new MeasureIndicator(this.mContext);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            layoutParams.gravity = 17;
            this.mChartContainer.addView(this.b, layoutParams);
        }
        MeasureIndicator measureIndicator = this.b;
        if (measureIndicator != null) {
            measureIndicator.setVisibility(z ? 0 : 8);
            if (z) {
                this.b.setIndicatorLevel(this.c);
            }
        }
    }

    @Override // com.huawei.health.section.section.MeasureCardItemHolder
    public String getTag() {
        return "VascularItemHolder";
    }
}
