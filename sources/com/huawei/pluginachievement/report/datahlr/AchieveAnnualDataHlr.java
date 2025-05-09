package com.huawei.pluginachievement.report.datahlr;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.GsonBuilder;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.report.bean.AnnualReport;
import com.huawei.pluginachievement.report.bean.AnnualReportCycle;
import com.huawei.pluginachievement.report.bean.AnnualReportFitness;
import com.huawei.pluginachievement.report.bean.AnnualReportInital;
import com.huawei.pluginachievement.report.bean.AnnualReportMarathon;
import com.huawei.pluginachievement.report.bean.AnnualReportReward;
import com.huawei.pluginachievement.report.bean.AnnualReportRun;
import com.huawei.pluginachievement.report.bean.AnnualReportSleep;
import com.huawei.pluginachievement.report.bean.AnnualReportStep;
import com.huawei.pluginachievement.report.bean.AnnualReportSumary;
import com.huawei.pluginachievement.report.bean.AnnualReportWeight;
import com.huawei.pluginachievement.report.bean.AnnualReportYear;
import defpackage.meh;
import defpackage.mhf;
import defpackage.mhh;
import defpackage.mht;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class AchieveAnnualDataHlr {
    private static final String TAG = "PLGACHIEVE_AchieveAnnualDataHlr";
    private static volatile AchieveAnnualDataHlr achieveAnnualDataHlr;
    private int currentYear;
    private Context mContext;
    private meh mService = null;
    private IBaseResponseCallback responseCallback = null;
    private boolean mIsFetchingAnnualData = false;

    private AchieveAnnualDataHlr(Context context) {
        if (context == null) {
            return;
        }
        this.mContext = context.getApplicationContext();
    }

    public static AchieveAnnualDataHlr getInstance(Context context) {
        if (achieveAnnualDataHlr == null) {
            synchronized (AchieveAnnualDataHlr.class) {
                if (achieveAnnualDataHlr == null) {
                    achieveAnnualDataHlr = new AchieveAnnualDataHlr(context);
                }
            }
        }
        return achieveAnnualDataHlr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getAnnualReport(int i) {
        LogUtil.a(TAG, "enter getAnnualReport");
        if (this.mService == null) {
            this.mService = meh.c(this.mContext);
        }
        AnnualReportReward h = mhh.h(this.mService, i);
        AnnualReportSumary j = mhh.j(this.mService, i);
        AnnualReportRun f = mhh.f(this.mService, i);
        AnnualReportCycle d = mhh.d(this.mService, i);
        AnnualReportStep g = mhh.g(this.mService, i);
        AnnualReportInital a2 = mhh.a(this.mService, i);
        AnnualReportFitness c = mhh.c(this.mService, i);
        AnnualReportSleep i2 = mhh.i(this.mService, i);
        AnnualReportWeight l = mhh.l(this.mService, i);
        AnnualReportMarathon e = mhh.e(this.mService, i);
        AnnualReport annualReport = new AnnualReport();
        annualReport.setReportCycle(d);
        annualReport.setReportRun(f);
        annualReport.setReportStep(g);
        annualReport.setReportFitness(c);
        annualReport.setReportInital(a2);
        annualReport.setReportReward(h);
        annualReport.setReportSumary(j);
        annualReport.setReportSleep(i2);
        annualReport.setReportWeight(l);
        annualReport.setReportMarathon(e);
        AnnualReportYear annualReportYear = new AnnualReportYear();
        if (i == 2018) {
            annualReportYear.setReport2018(annualReport);
        } else {
            annualReportYear.setReport(annualReport);
        }
        annualReportYear.setResultCode(mhh.d(mht.b(i, false), g, a2));
        String json = new GsonBuilder().excludeFieldsWithModifiers(4).create().toJson(annualReportYear);
        IBaseResponseCallback iBaseResponseCallback = this.responseCallback;
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(0, json);
        }
        this.responseCallback = null;
    }

    public void getAnnualReportJson(int i, IBaseResponseCallback iBaseResponseCallback) {
        if (this.mIsFetchingAnnualData) {
            if (iBaseResponseCallback != null) {
                this.responseCallback = iBaseResponseCallback;
            }
            LogUtil.a(TAG, "getAnnualReportJson has executing, return");
            return;
        }
        if (this.mService == null) {
            this.mService = meh.c(this.mContext);
        }
        LogUtil.a(TAG, "getAnnualReportJson year ", Integer.valueOf(i));
        this.mIsFetchingAnnualData = true;
        Task<Void> calculateAnnualData = new mhf().a(i).calculateAnnualData(i);
        this.responseCallback = iBaseResponseCallback;
        this.currentYear = i;
        calculateAnnualData.addOnCompleteListener(new OnCompleteListener<Void>() { // from class: com.huawei.pluginachievement.report.datahlr.AchieveAnnualDataHlr.5
            @Override // com.huawei.hmf.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                if (AchieveAnnualDataHlr.this.currentYear <= 2019) {
                    AchieveAnnualDataHlr achieveAnnualDataHlr2 = AchieveAnnualDataHlr.this;
                    achieveAnnualDataHlr2.getAnnualReport(achieveAnnualDataHlr2.currentYear);
                } else {
                    AchieveAnnualDataHlr achieveAnnualDataHlr3 = AchieveAnnualDataHlr.this;
                    achieveAnnualDataHlr3.generateAnnualReport(achieveAnnualDataHlr3.currentYear);
                }
                AchieveAnnualDataHlr.this.mIsFetchingAnnualData = false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void generateAnnualReport(int i) {
        if (this.responseCallback == null) {
            return;
        }
        JSONObject b = mhh.b(this.mService, i);
        if (!TextUtils.isEmpty(b.toString())) {
            this.responseCallback.d(0, b.toString());
            LogUtil.a(TAG, "generateAnnualReport onResponse SUCCESS.");
        } else {
            this.responseCallback.d(-1, "");
            LogUtil.h(TAG, "generateAnnualReport onResponse ERROR.");
        }
        this.responseCallback = null;
    }
}
