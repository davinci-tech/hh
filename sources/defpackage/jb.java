package defpackage;

import com.alipay.tscenter.biz.rpc.report.general.DataReportService;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;

/* loaded from: classes7.dex */
public class jb implements Runnable {
    public final /* synthetic */ DataReportRequest c;
    public final /* synthetic */ je e;

    @Override // java.lang.Runnable
    public void run() {
        DataReportResult dataReportResult;
        DataReportResult dataReportResult2;
        DataReportService dataReportService;
        try {
            dataReportService = this.e.e;
            DataReportResult unused = je.b = dataReportService.reportData(this.c);
        } catch (Throwable th) {
            DataReportResult unused2 = je.b = new DataReportResult();
            dataReportResult = je.b;
            dataReportResult.success = false;
            dataReportResult2 = je.b;
            dataReportResult2.resultCode = "static data rpc upload error, " + mq.c(th);
            mq.c(th);
        }
    }

    public jb(je jeVar, DataReportRequest dataReportRequest) {
        this.e = jeVar;
        this.c = dataReportRequest;
    }
}
