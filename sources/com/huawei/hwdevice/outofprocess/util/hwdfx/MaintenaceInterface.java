package com.huawei.hwdevice.outofprocess.util.hwdfx;

import com.huawei.datatype.DeviceCommand;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.datatypes.TransferFileInfo;
import java.util.ArrayList;
import java.util.Date;

/* loaded from: classes5.dex */
public interface MaintenaceInterface {
    void cutFolder(String str, String str2);

    void deleteTenDayFile();

    ArrayList filtertFile(ArrayList arrayList, int i);

    String getDayDateTime();

    String getDeviceName(int i);

    String getMaintCheckTime();

    int getMaintRetryNum();

    boolean getMaintRetryResult();

    String getmTransferDataContentPath();

    String getmTransferStateContentPath();

    void initMaintenanceFile();

    void initMaintenanceParame(TransferFileInfo transferFileInfo);

    void initName();

    DeviceCommand maintParametersCommand();

    void onDestroyMaintenance();

    void save2File(IBaseResponseCallback iBaseResponseCallback, boolean z);

    void setMaintCheckTime(String str);

    void setMaintRetryNum(int i);

    void setMaintRetryResult(boolean z);

    DeviceCommand transferFileEndProcess();

    void writeLogToFile(ArrayList<byte[]> arrayList, String str, Date date);
}
