package com.huawei.hwdevice.api;

import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.TransferProgressResp;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.TransmitWatchFace;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.WearFilterPreview;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WearWatchFaceInfo;
import com.huawei.networkclient.ResultCallback;
import java.util.List;

/* loaded from: classes9.dex */
public interface WearWatchFaceApi {
    void checkLocalResourceExist(String str, ResultCallback<Boolean> resultCallback);

    void createWearWatchFace(String str, ResultCallback<List<WearFilterPreview>> resultCallback);

    void deleteRedundantWearWatchFace(String str, ResultCallback<String> resultCallback);

    void getWearWatchFaceInfo(String str, ResultCallback<TransmitWatchFace<WearWatchFaceInfo>> resultCallback);

    void getWearWatchFacePath(int i, ResponseCallback<String> responseCallback);

    void setWearWatchFaceInfo(String str, ResultCallback<TransferProgressResp> resultCallback);
}
