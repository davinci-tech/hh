package com.huawei.pluginachievement.ui.report;

import android.content.res.Configuration;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import defpackage.mht;
import defpackage.mkd;
import defpackage.mkx;
import defpackage.mlg;
import defpackage.nom;
import java.util.ArrayList;
import java.util.Collections;

/* loaded from: classes8.dex */
public class AchieveHistoricalMonthReportActivity extends BaseHistoricalReportActivity {
    private int e(int i, int i2, int i3, int i4, int i5) {
        if (i5 == i4) {
            return i3;
        }
        if (i5 <= i || i5 >= i4) {
            return 13 - i2;
        }
        return 12;
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseHistoricalReportActivity
    protected int getLayoutId() {
        return R.layout.activity_achieve_historical_month_report;
    }

    @Override // com.huawei.pluginachievement.ui.report.BaseHistoricalReportActivity
    protected ArrayList<mkd> calculateHistoricalReportDataList(long j, int i, int i2) {
        LogUtil.a("PLGACHIEVE_AchieveHistoricalMonthReportActivity", "calculateHistoricalReportDataList() startTimestamp = ", Long.valueOf(j), ",reportMaxNumber = ", Integer.valueOf(i), ", reportMinNumber = ", Integer.valueOf(i2));
        int b = mht.b(mht.d(j));
        int a2 = mkx.a(j);
        long currentTimeMillis = System.currentTimeMillis();
        int a3 = mkx.a(currentTimeMillis);
        int b2 = mht.b(mht.d(currentTimeMillis));
        if (b > b2) {
            LogUtil.h("PLGACHIEVE_AchieveHistoricalMonthReportActivity", "calculateTimeMap startYearInt < currentYearInt. return;");
            return null;
        }
        long d = mkx.d(0, currentTimeMillis, 1);
        long d2 = mkx.d(0, currentTimeMillis, 2);
        ArrayList<mkd> arrayList = new ArrayList<>(10);
        int i3 = (b2 - b) + 1;
        long j2 = j;
        int i4 = 0;
        int i5 = i2;
        while (i4 < i3) {
            String d3 = mht.d(j2);
            mkd mkdVar = new mkd();
            mkdVar.e(d3);
            mkdVar.d(j2);
            int i6 = b;
            int e = e(b, a2, a3, b2, mht.b(d3));
            int i7 = i4;
            long j3 = j2;
            ArrayList<mkd> arrayList2 = arrayList;
            int i8 = i3;
            ArrayList<mkd.c> a4 = a(d, d2, j3, e, i5);
            if (koq.c(a4)) {
                Collections.sort(a4);
                mkdVar.a(a4);
                j3 = nom.o(j3);
                arrayList2.add(mkdVar);
                i5 += a4.size();
            }
            j2 = j3;
            i4 = i7 + 1;
            b = i6;
            arrayList = arrayList2;
            i3 = i8;
        }
        return arrayList;
    }

    private ArrayList<mkd.c> a(long j, long j2, long j3, int i, int i2) {
        ArrayList<mkd.c> arrayList = new ArrayList<>(10);
        for (int i3 = 0; i3 < i; i3++) {
            if (j3 < j || j3 > j2) {
                long d = mkx.d(0, j3, 2);
                mkd.c cVar = new mkd.c();
                cVar.c(j3);
                cVar.e(d);
                cVar.a(d(j3));
                cVar.c(i2 + i3);
                arrayList.add(cVar);
                j3 = mkx.d(1, j3, 1);
            }
        }
        return arrayList;
    }

    private String d(long j) {
        return mlg.a(j, 0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
