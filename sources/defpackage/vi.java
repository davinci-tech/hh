package defpackage;

import com.huawei.ads.adsrec.db.table.AdContentRspRecord;
import com.huawei.ads.adsrec.db.table.AdCreativeContentRecord;
import com.huawei.ads.adsrec.db.table.AdEventRecord;
import com.huawei.ads.adsrec.db.table.AdIECFeedbackRecord;
import com.huawei.ads.adsrec.db.table.AdIECImpRecord;
import com.huawei.ads.adsrec.db.table.AdSlotMapRecord;
import com.huawei.ads.adsrec.db.table.AdTraceRecord;
import com.huawei.ads.adsrec.db.table.DsContentRelRecord;
import com.huawei.ads.adsrec.db.table.MaterialSummaryRecord;
import com.huawei.ads.adsrec.db.table.SlotRecord;
import com.huawei.ads.fund.db.BaseDbUpdateHelper;

/* loaded from: classes2.dex */
public class vi extends BaseDbUpdateHelper {
    @Override // com.huawei.ads.fund.db.BaseDbUpdateHelper
    public String getTag() {
        return "AdsRecDbUpdateHelper";
    }

    private void e() {
        AdCreativeContentRecord adCreativeContentRecord = new AdCreativeContentRecord();
        AdEventRecord adEventRecord = new AdEventRecord();
        AdIECFeedbackRecord adIECFeedbackRecord = new AdIECFeedbackRecord();
        AdIECImpRecord adIECImpRecord = new AdIECImpRecord();
        AdSlotMapRecord adSlotMapRecord = new AdSlotMapRecord();
        SlotRecord slotRecord = new SlotRecord();
        AdContentRspRecord adContentRspRecord = new AdContentRspRecord();
        AdTraceRecord adTraceRecord = new AdTraceRecord();
        MaterialSummaryRecord materialSummaryRecord = new MaterialSummaryRecord();
        DsContentRelRecord dsContentRelRecord = new DsContentRelRecord();
        this.oldTableList.add(adCreativeContentRecord);
        this.oldTableList.add(adEventRecord);
        this.oldTableList.add(adIECFeedbackRecord);
        this.oldTableList.add(adIECImpRecord);
        this.oldTableList.add(adSlotMapRecord);
        this.oldTableList.add(slotRecord);
        this.oldTableList.add(adContentRspRecord);
        this.oldTableList.add(adTraceRecord);
        this.oldTableList.add(materialSummaryRecord);
        this.oldTableList.add(dsContentRelRecord);
        this.tableList.add(adCreativeContentRecord);
        this.tableList.add(adEventRecord);
        this.tableList.add(adIECFeedbackRecord);
        this.tableList.add(adIECImpRecord);
        this.tableList.add(adSlotMapRecord);
        this.tableList.add(slotRecord);
        this.tableList.add(adContentRspRecord);
        this.tableList.add(adTraceRecord);
        this.tableList.add(materialSummaryRecord);
        this.tableList.add(dsContentRelRecord);
    }

    public vi(vm vmVar) {
        super(vmVar);
        e();
    }
}
