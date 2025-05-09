package defpackage;

import com.huawei.hihealth.HiHealthData;
import com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import java.util.List;

/* loaded from: classes7.dex */
public class rpj extends BaseOperate {
    public rpj() {
        this.pageType = 104;
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfReadInOneDayNoSource(rkb rkbVar) {
        rkbVar.a(new int[]{2034});
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfReadInOneDayByDeviceSource(rkb rkbVar) {
        setVarOfReadInOneDayNoSource(rkbVar);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfReadInOneDayByAppSource(rkb rkbVar) {
        setVarOfReadInOneDayNoSource(rkbVar);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfReadByMonthNoSource(rkb rkbVar) {
        rkbVar.a(new int[]{44306});
        rkbVar.c(new String[]{"pressure"});
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfReadByMonthByDeviceSource(rkb rkbVar) {
        rkbVar.a(new int[]{2034});
        rkbVar.b(3);
        rkbVar.c(new String[]{"pressure"});
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfReadByMonthByAppSource(rkb rkbVar) {
        setVarOfReadByMonthByDeviceSource(rkbVar);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public List<PrivacyDataModel> proResultOfReadInOneDayNoSource(List<HiHealthData> list) {
        return recordDataProcess(list, 1005);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfRecordDataProcess(HiHealthData hiHealthData, PrivacyDataModel privacyDataModel) {
        privacyDataModel.setIntValue(hiHealthData.getIntValue());
        privacyDataModel.setDataTitle(rre.c(hiHealthData.getIntValue(), false));
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfHiHealthDataProcess(HiHealthData hiHealthData, PrivacyDataModel privacyDataModel) {
        privacyDataModel.setDataDesc(rsr.a(hiHealthData.getStartTime()));
        privacyDataModel.setDataTitle(rre.c(hiHealthData.getInt("pressure"), true));
        privacyDataModel.setStartTime(rrb.c(hiHealthData.getStartTime()));
        privacyDataModel.setEndTime(rrb.e(hiHealthData.getStartTime()));
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public int[] getDeleteDataTypes(rkb rkbVar) {
        return new int[]{2034};
    }
}
