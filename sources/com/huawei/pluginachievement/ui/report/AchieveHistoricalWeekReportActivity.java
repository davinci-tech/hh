package com.huawei.pluginachievement.ui.report;

import android.content.res.Configuration;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import defpackage.mht;
import defpackage.mkd;
import defpackage.mkx;
import defpackage.mle;
import defpackage.mlg;
import health.compact.a.LanguageUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Collections;

/* loaded from: classes8.dex */
public class AchieveHistoricalWeekReportActivity extends BaseHistoricalReportActivity {
    private int b(int i, int i2, int i3, int i4) {
        if (i3 < i) {
            return 0;
        }
        return i3 == i ? (i4 - i2) + 1 : (((i3 - i) - 1) * 12) + (13 - i2) + i4;
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseHistoricalReportActivity
    protected int getLayoutId() {
        return R.layout.activity_achieve_historical_week_report;
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseHistoricalReportActivity
    protected ArrayList<mkd> calculateHistoricalReportDataList(long j, int i, int i2) {
        long j2;
        LogUtil.a("PLGACHIEVE_AchieveHistoricalWeekReportActivity", "calculateHistoricalReportDataList() startTime = ", Long.valueOf(j), ",reportMaxNumber = ", Integer.valueOf(i), ", reportMinNumber = ", Integer.valueOf(i2));
        long currentTimeMillis = System.currentTimeMillis();
        int b = b(mht.b(mht.d(j)), mkx.a(j), mht.b(mht.d(currentTimeMillis)), mkx.a(currentTimeMillis));
        ArrayList<mkd> arrayList = new ArrayList<>(10);
        long j3 = j;
        for (int i3 = 0; i3 < b; i3++) {
            mkd mkdVar = new mkd();
            mkdVar.e(e(j3));
            mkdVar.d(j3);
            long d = mkx.d(1, j3, 1);
            long j4 = d - j3;
            if (j4 % 604800000 == 0) {
                j2 = j4 / 604800000;
            } else {
                j2 = (j4 / 604800000) + 1;
            }
            ArrayList<mkd.c> a2 = a(mkx.a(j3), (int) j2, mkx.d(1, 0, j3, Utils.o()), j);
            if (koq.c(a2)) {
                Collections.sort(a2);
                mkdVar.a(a2);
                arrayList.add(mkdVar);
                a2.size();
                j3 = d;
            }
        }
        return arrayList;
    }

    private ArrayList<mkd.c> a(int i, int i2, long j, long j2) {
        if (j < j2) {
            j = j2;
        }
        ArrayList<mkd.c> arrayList = new ArrayList<>(10);
        for (int i3 = 0; i3 < i2; i3++) {
            if (a(j)) {
                LogUtil.h("PLGACHIEVE_AchieveHistoricalWeekReportActivity", "isInArrayRange");
            } else {
                long d = mkx.d(2, 0, j, Utils.o());
                if (mkx.a(j) != i) {
                    j = mkx.d(1, -1, j, Utils.o());
                } else {
                    mkd.c cVar = new mkd.c();
                    cVar.c(j);
                    cVar.e(d);
                    cVar.a(c(j));
                    arrayList.add(cVar);
                    j = mkx.d(1, -1, j, Utils.o());
                    if (mle.d(j, System.currentTimeMillis()) && System.currentTimeMillis() >= a(j, d) && i3 == i2 - 1 && mkx.a(j) == i) {
                        mkd.c cVar2 = new mkd.c();
                        cVar2.c(j);
                        cVar2.e(a(j, d));
                        cVar2.a(c(j));
                        arrayList.add(cVar2);
                    }
                }
            }
        }
        return arrayList;
    }

    private long a(long j, long j2) {
        return j > j2 ? mkx.d(2, 0, j, Utils.o()) : j2;
    }

    private String e(long j) {
        return mlg.a(j, 0);
    }

    private boolean a(long j) {
        return j >= mkx.d(1, 0, System.currentTimeMillis(), Utils.o()) && j <= mkx.d(2, 0, System.currentTimeMillis(), Utils.o());
    }

    private String c(long j) {
        return mlg.a(mkx.d(1, 0, j, Utils.o()), 1) + (LanguageUtil.bn(BaseApplication.e()) ? " ~ " : " - ") + mlg.a(mkx.d(2, 0, j, Utils.o()), 1);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
