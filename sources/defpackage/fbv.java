package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import androidx.core.util.Pair;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.marketing.utils.EcgFilterManager;
import com.huawei.health.section.section.MeasureCardItemHolder;
import com.huawei.health.servicesui.R$string;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.model.EcgMetaData;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes3.dex */
public class fbv extends MeasureCardItemHolder {
    @Override // com.huawei.health.section.section.MeasureCardItemHolder
    public float getYMax() {
        return 1000000.0f;
    }

    @Override // com.huawei.health.section.section.MeasureCardItemHolder
    public float getYMin() {
        return -1000000.0f;
    }

    public fbv(View view) {
        super(view);
    }

    @Override // com.huawei.health.section.section.MeasureCardItemHolder
    public long getNewDataTimeStamp(fbp fbpVar) {
        if (fbpVar.g()) {
            HiHealthData b = fbpVar.b();
            if (b != null) {
                return b.getStartTime();
            }
        } else {
            HiHealthData d = fbpVar.d();
            if (d != null) {
                return d.getStartTime();
            }
        }
        return 0L;
    }

    @Override // com.huawei.health.section.section.MeasureCardItemHolder
    public void syncEcgData() {
        c();
        e();
    }

    @Override // com.huawei.health.section.section.MeasureCardItemHolder
    public void bindDesc(fbp fbpVar) {
        long startTime;
        int i;
        int i2;
        int i3;
        EcgMetaData ecgMetaData;
        boolean g = fbpVar.g();
        int i4 = 0;
        if (g) {
            HiHealthData b = fbpVar.b();
            startTime = b != null ? b.getStartTime() : 0L;
            if (b != null) {
                try {
                    ecgMetaData = (EcgMetaData) nrv.c(b.getMetaData(), new TypeToken<EcgMetaData>() { // from class: fbv.5
                    }.getType());
                } catch (JsonSyntaxException unused) {
                    LogUtil.b("NormalItemHolder", "JsonSyntaxException");
                    ecgMetaData = new EcgMetaData();
                }
                i4 = (int) ecgMetaData.getEcgArrhyType();
                i3 = ecgMetaData.getAverageHeartRate();
            } else {
                i3 = 0;
            }
            i2 = i3;
            i = i4;
        } else {
            HiHealthData d = fbpVar.d();
            startTime = d != null ? d.getStartTime() : 0L;
            i = 0;
            i2 = 0;
        }
        e(startTime, i, i2, g);
    }

    private void e(long j, int i, int i2, boolean z) {
        String string;
        Resources resources = BaseApplication.e().getResources();
        if (z) {
            string = resources.getString(R$string.IDS_quick_app_ecg_analysis);
        } else {
            string = resources.getString(R$string.IDS_quick_app_ecg);
        }
        this.mTitle = string;
        nsy.cMr_(this.mTitleTextView, this.mTitle);
        if (j == 0) {
            this.mPrimaryTipTextView.setText(z ? R$string.IDS_heart_rate_no_data_tip : R$string.IDS_heart_rate_cllection_no_data_tip);
            this.mSecondaryTipTextView.setVisibility(8);
            this.mTimeTextView.setVisibility(8);
            onDataAvailable(false);
        } else {
            Pair<String, String> a2 = a(i, i2);
            if (a2 != null) {
                this.mPrimaryTipTextView.setVisibility(0);
                this.mPrimaryTipTextView.setText(z ? a2.first : nsf.h(R$string.IDS_heart_rate_collection_tip));
                this.mSecondaryTipTextView.setVisibility(0);
                if (z) {
                    this.mSecondaryTipTextView.setText(a2.second);
                } else {
                    this.mSecondaryTipTextView.setText((CharSequence) null);
                    this.mSecondaryTipTextView.setVisibility(8);
                }
            } else {
                if (z) {
                    this.mPrimaryTipTextView.setVisibility(8);
                } else {
                    this.mPrimaryTipTextView.setVisibility(0);
                    this.mPrimaryTipTextView.setText(R$string.IDS_heart_rate_collection_tip);
                }
                this.mSecondaryTipTextView.setVisibility(8);
            }
            this.mTimeTextView.setVisibility(0);
            this.mTimeTextView.setText(DateFormatUtil.b(j, DateFormatUtil.DateFormatType.DATE_FORMAT_MONTH_DAY_HH_MM));
            onDataAvailable(true);
        }
        this.mIcon.setImageResource(z ? R.drawable._2131429934_res_0x7f0b0a2e : R.drawable._2131429933_res_0x7f0b0a2d);
    }

    private Pair<String, String> a(int i, int i2) {
        String h;
        String h2;
        EcgFilterManager a2 = EcgFilterManager.a();
        if (a2.j() && (i == 2 || i == 4)) {
            i = 1;
        }
        if (i == 1) {
            h = nsf.h(R$string.IDS_heart_rate_sinus);
            h2 = nsf.h(a2.f() ? R$string.ecg_sinus_rhythm_result : R$string.IDS_heart_rate_sinus_tip);
        } else if (i == 2) {
            h = nsf.h(R$string.IDS_heart_rate_pac);
            h2 = nsf.h(R$string.IDS_heart_rate_pac_tip);
        } else if (i == 4) {
            h = nsf.h(R$string.IDS_heart_rate_pvc);
            h2 = nsf.h(R$string.IDS_heart_rate_pvc_tip);
        } else if (i == 8) {
            h = nsf.h(R$string.IDS_heart_rate_afib);
            h2 = nsf.h(R$string.IDS_heart_rate_afib_tip);
        } else if (i != 16) {
            if (i != 32) {
                if (i == 64) {
                    h = nsf.h(R$string.IDS_heart_rate_ambiguous);
                    h2 = nsf.h(R$string.IDS_heart_rate_ambiguous_no_exact);
                } else if (i == 128) {
                    h = nsf.h(R$string.IDS_heart_rate_ambiguous);
                    h2 = nsf.h(R$string.IDS_heart_rate_ambiguous_low_signal);
                } else {
                    ReleaseLogUtil.c(getTag(), "getDiagramDesc, unknown diagramType = ", Integer.valueOf(i));
                    return null;
                }
            } else if (a2.k() && a2.h()) {
                h = nsf.b(R$string.IDS_ecg_jp_low_rate, d(50));
                h2 = nsf.b(R$string.IDS_ecg_jp_low_rate_tip1, d(50));
            } else {
                h = nsf.h(R$string.IDS_heart_rate_ambiguous);
                h2 = nsf.b(R$string.IDS_heart_rate_ambiguous_lowerthan_fifty, d(50));
            }
        } else if (a2.k() && a2.h()) {
            h = nsf.b(R$string.IDS_ecg_jp_high_rate, d(110));
            h2 = nsf.b(R$string.IDS_ecg_jp_high_rate_tip1, d(110));
        } else {
            h = nsf.h(R$string.IDS_heart_rate_ambiguous);
            h2 = nsf.b(R$string.IDS_heart_rate_ambiguous_higherthan_onehundred, d(110), d(30));
        }
        return new Pair<>(h, h2);
    }

    private String d(int i) {
        return UnitUtil.e(i, 1, 0);
    }

    private void e() {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(15);
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setSyncScope(1);
        HiHealthNativeApi.a(this.mContext).synCloud(hiSyncOption, null);
    }

    private void c() {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(DicDataTypeUtil.DataType.ELECTROCARDIOGRAM.value());
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setSyncScope(1);
        HiHealthNativeApi.a(this.mContext).synCloud(hiSyncOption, null);
    }

    @Override // com.huawei.health.section.section.MeasureCardItemHolder
    public int getChartLineColor(Context context) {
        return nsf.c(R.color._2131297095_res_0x7f090347);
    }

    @Override // com.huawei.health.section.section.MeasureCardItemHolder
    public String getTag() {
        return "NormalItemHolder";
    }
}
