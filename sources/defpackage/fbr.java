package defpackage;

import android.content.Context;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.section.section.MeasureCardItemHolder;
import com.huawei.health.servicesui.R$string;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes3.dex */
public class fbr extends MeasureCardItemHolder {
    public fbr(View view) {
        super(view);
        this.mTitle = BaseApplication.e().getResources().getString(R$string.IDS_arrhythmia_title_tl);
        nsy.cMr_(this.mTitleTextView, this.mTitle);
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
        LogUtil.a("ArrhythmiaItemHolder", "syncEcgData in ArrhythmiaItemHolder");
    }

    @Override // com.huawei.health.section.section.MeasureCardItemHolder
    public void bindDesc(fbp fbpVar) {
        this.mIcon.setImageResource(R.drawable._2131429935_res_0x7f0b0a2f);
        HiHealthData d = fbpVar.d();
        if (d != null) {
            this.mTimeTextView.setText(DateFormatUtil.b(d.getStartTime(), DateFormatUtil.DateFormatType.DATE_FORMAT_MONTH_DAY_HH_MM));
            Pair<Integer, Integer> a2 = a((int) d.getValue(), eii.b());
            if (a2.first != null && a2.first.intValue() != 0) {
                this.mPrimaryTipTextView.setText(a2.first.intValue());
            } else {
                this.mPrimaryTipTextView.setText((CharSequence) null);
            }
            this.mSecondaryTipTextView.setVisibility(0);
            if (a2.second != null && a2.second.intValue() != 0) {
                this.mSecondaryTipTextView.setText(a2.second.intValue());
            } else {
                this.mSecondaryTipTextView.setText((CharSequence) null);
            }
            onDataAvailable(true);
            return;
        }
        this.mPrimaryTipTextView.setText(R$string.IDS_arrhythmia_no_data_tip);
        this.mSecondaryTipTextView.setVisibility(8);
        onDataAvailable(false);
    }

    private Pair<Integer, Integer> a(int i, boolean z) {
        if (i == 1) {
            return new Pair<>(Integer.valueOf(R$string.IDS_arrhythmia_normal), Integer.valueOf(R$string.IDS_arrhythmia_normal_tip));
        }
        if (i == 2) {
            return new Pair<>(Integer.valueOf(R$string.IDS_arrhythmia_pc), Integer.valueOf(R$string.IDS_arrhythmia_pc_tip));
        }
        if (i == 3) {
            return new Pair<>(Integer.valueOf(R$string.IDS_arrhythmia_afib), Integer.valueOf(R$string.IDS_arrhythmia_afib_tip));
        }
        if (i == 4) {
            if (z) {
                return new Pair<>(Integer.valueOf(R$string.IDS_arrhythmia_afib), Integer.valueOf(R$string.IDS_arrhythmia_afib_tip));
            }
            return new Pair<>(Integer.valueOf(R$string.IDS_arrhythmia_afib_risk), Integer.valueOf(R$string.IDS_arrhythmia_afib_risk_tip));
        }
        if (i != 5) {
            ReleaseLogUtil.c(getTag(), "getDiagramDesc, unknown diagramType = ", Integer.valueOf(i));
            return new Pair<>(0, 0);
        }
        if (z) {
            return new Pair<>(Integer.valueOf(R$string.IDS_arrhythmia_pc), Integer.valueOf(R$string.IDS_arrhythmia_pc_tip));
        }
        return new Pair<>(Integer.valueOf(R$string.IDS_arrhythmia_pc_risk), Integer.valueOf(R$string.IDS_arrhythmia_pc_risk_tip));
    }

    @Override // com.huawei.health.section.section.MeasureCardItemHolder
    public int getChartLineColor(Context context) {
        return ContextCompat.getColor(this.mContext, R.color._2131297094_res_0x7f090346);
    }

    @Override // com.huawei.health.section.section.MeasureCardItemHolder
    public Pair<Integer, Integer> getChartGradientColors() {
        return new Pair<>(Integer.valueOf(ContextCompat.getColor(this.mContext, R.color._2131297093_res_0x7f090345)), Integer.valueOf(ContextCompat.getColor(this.mContext, R.color._2131297092_res_0x7f090344)));
    }

    @Override // com.huawei.health.section.section.MeasureCardItemHolder
    public String getTag() {
        return "ArrhythmiaItemHolder";
    }
}
