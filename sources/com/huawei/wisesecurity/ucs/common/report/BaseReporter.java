package com.huawei.wisesecurity.ucs.common.report;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.wisesecurity.kfs.log.ILogKfs;
import defpackage.tts;
import defpackage.ttv;
import defpackage.twb;

/* loaded from: classes7.dex */
public abstract class BaseReporter {
    private static final String TAG = "BaseReporter";
    public String haUrl;
    public ReportOption option;

    public abstract String getLogTag();

    public abstract String getReporterTag();

    public static class c implements ILogKfs {

        /* renamed from: a, reason: collision with root package name */
        private final String f11252a;

        @Override // com.huawei.wisesecurity.kfs.log.ILogKfs
        public void w(String str, String str2) {
            twb.b(d(str), str2, new Object[0]);
        }

        @Override // com.huawei.wisesecurity.kfs.log.ILogKfs
        public void i(String str, String str2) {
            twb.a(d(str), str2, new Object[0]);
        }

        public String d(String str) {
            if (TextUtils.isEmpty(str)) {
                return this.f11252a;
            }
            return this.f11252a + Constants.LINK + str;
        }

        @Override // com.huawei.wisesecurity.kfs.log.ILogKfs
        public void e(String str, String str2) {
            twb.e(d(str), str2, new Object[0]);
        }

        @Override // com.huawei.wisesecurity.kfs.log.ILogKfs
        public void d(String str, String str2) {
            twb.d(d(str), str2, new Object[0]);
        }

        public c(String str) {
            this.f11252a = str;
        }
    }

    public void setOobeCheck(tts ttsVar) {
        if (ReportOption.REPORT_ALWAYS != this.option || ttsVar == null) {
            return;
        }
        twb.a(TAG, "set OobeCheckOff.", new Object[0]);
        ttsVar.d();
    }

    public tts getInstance(Context context, String str, String str2) {
        try {
            return new tts(context, str, this.haUrl, new c(str2));
        } catch (ttv e) {
            twb.e(TAG, "HaReporter instance exception: {0}", e.getMessage());
            return null;
        }
    }

    public BaseReporter(String str, ReportOption reportOption) {
        this.option = reportOption;
        this.haUrl = str;
    }
}
