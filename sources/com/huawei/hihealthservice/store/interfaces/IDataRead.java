package com.huawei.hihealthservice.store.interfaces;

import android.os.RemoteException;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSportStatDataAggregateOption;
import com.huawei.hihealth.IDataReadResultListener;
import defpackage.ifl;
import defpackage.ikv;
import java.util.List;

/* loaded from: classes7.dex */
public interface IDataRead {
    List<HiHealthData> readAggregateData(ikv ikvVar, HiAggregateOption hiAggregateOption, ifl iflVar);

    void readDataByAlignType(int i, ikv ikvVar, HiDataReadOption hiDataReadOption, IDataReadResultListener iDataReadResultListener) throws RemoteException;

    void readDataByType(HiDataReadOption hiDataReadOption, ikv ikvVar, ifl iflVar, IDataReadResultListener iDataReadResultListener) throws RemoteException;

    List<HiHealthData> readSportStatAggregateData(ikv ikvVar, HiSportStatDataAggregateOption hiSportStatDataAggregateOption);
}
