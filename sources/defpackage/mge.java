package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.wearable.WearableStatusCodes;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.report.bean.AnnualReportFitness;
import com.huawei.pluginachievement.report.bean.AnnualReportFitnessRaw;
import com.huawei.pluginachievement.report.constant.EnumAnnualType;
import com.huawei.pluginachievement.report.iterface.BaseCalculator;
import java.util.List;

/* loaded from: classes6.dex */
public class mge extends BaseCalculator {
    private PluginAchieveAdapter d = getPluginAchieveAdapter();
    private Context b = BaseApplication.getContext();

    @Override // com.huawei.pluginachievement.report.iterface.BaseCalculator
    public void compute(int i) {
        a(i);
        c(i);
    }

    public void c(final int i) {
        if (this.d == null) {
            this.d = mcv.d(BaseApplication.getContext()).getAdapter();
        }
        this.d.getAnnualFitnessRecord(BaseApplication.getContext(), mht.b(i, true), mht.b(i, false), new AchieveCallback() { // from class: mge.3
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i2, Object obj) {
                mge.this.c((mge) obj, i);
            }
        });
    }

    public void a(final int i) {
        if (this.d == null) {
            LogUtil.a("PLGACHIEVE_FitnessCalculator", "mAchieveAdapter is null,return");
            return;
        }
        this.d.getAnnualFitnessReport(this.b, mht.b(i, true), mht.b(i, false), new AchieveCallback() { // from class: mge.4
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i2, Object obj) {
                if (!(obj instanceof List)) {
                    LogUtil.h("PLGACHIEVE_FitnessCalculator", "getAnnualFitnessData: data error");
                    return;
                }
                try {
                    List list = (List) obj;
                    if (list.size() != 0) {
                        mge.this.c(mhl.b(list, i), i);
                    } else {
                        LogUtil.a("PLGACHIEVE_FitnessCalculator", "getFitnessListData is empty,return");
                    }
                } catch (ClassCastException unused) {
                    LogUtil.b("PLGACHIEVE_FitnessCalculator", "getAnnualFitnessReport data ClassCastException");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void c(T t, int i) {
        if (!(t instanceof List)) {
            LogUtil.h("PLGACHIEVE_FitnessCalculator", "getAnnualFitnessReport: !(data instanceof List)");
            return;
        }
        try {
            List list = (List) t;
            if (list.size() == 0) {
                LogUtil.h("PLGACHIEVE_FitnessCalculator", "getFitnessListData is empty,return");
            } else if (i < 2022) {
                c(mhl.a((List<AnnualReportFitnessRaw>) list), i);
            } else {
                c(mhl.e((List<AnnualReportFitnessRaw>) list), i);
            }
        } catch (ClassCastException unused) {
            LogUtil.b("PLGACHIEVE_FitnessCalculator", "getAnnualFitnessReport data ClassCastException");
        }
    }

    private void c(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("PLGACHIEVE_FitnessCalculator", "saveRecordFitnessData data isEmpty.");
        } else {
            insertData(i, EnumAnnualType.REPORT_FITNESS.value(), 4007, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(AnnualReportFitness annualReportFitness, int i) {
        if (annualReportFitness == null) {
            LogUtil.h("PLGACHIEVE_FitnessCalculator", "saveReportFitnessData annualReportFitness is null");
            return;
        }
        insertData(i, EnumAnnualType.REPORT_FITNESS.value(), WearableStatusCodes.DATA_ITEM_TOO_LARGE, String.valueOf(annualReportFitness.acquireMaxDuration()));
        insertData(i, EnumAnnualType.REPORT_FITNESS.value(), WearableStatusCodes.INVALID_TARGET_NODE, String.valueOf(annualReportFitness.acquireMaxDurationDay()));
        insertData(i, EnumAnnualType.REPORT_FITNESS.value(), 4005, String.valueOf(annualReportFitness.acquireDescription()));
        insertData(i, EnumAnnualType.REPORT_FITNESS.value(), 4001, String.valueOf(annualReportFitness.acquireTotalDuration()));
        insertData(i, EnumAnnualType.REPORT_FITNESS.value(), 4002, String.valueOf(annualReportFitness.acquireNumberOfTimes()));
    }
}
