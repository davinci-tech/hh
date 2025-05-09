package com.huawei.hihealthservice.store.interfaces;

import com.huawei.hihealth.HiDataDeleteOption;
import com.huawei.hwcloudmodel.model.unite.DataTimeDelCondition;
import defpackage.ikv;
import java.util.List;

/* loaded from: classes7.dex */
public interface IDataDelete {
    List<DataTimeDelCondition> deleteHealthData(HiDataDeleteOption hiDataDeleteOption, ikv ikvVar, int i);
}
