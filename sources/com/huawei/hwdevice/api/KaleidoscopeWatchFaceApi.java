package com.huawei.hwdevice.api;

import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.KaleidoscopePreview;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.TransferProgressResp;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.TransmitWatchFace;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.KaleidoscopeWatchFaceInfo;
import com.huawei.networkclient.ResultCallback;
import java.util.List;

/* loaded from: classes9.dex */
public interface KaleidoscopeWatchFaceApi {
    void getKaleidoscopeTypeInfo(String str, ResultCallback<List<KaleidoscopePreview>> resultCallback);

    void getKaleidoscopeWatchFaceInfo(String str, ResultCallback<TransmitWatchFace<KaleidoscopeWatchFaceInfo>> resultCallback);

    void getKaleidoscopeWatchFacePath(ResponseCallback<String> responseCallback);

    void setKaleidoscopeWatchFaceInfo(String str, ResultCallback<TransferProgressResp> resultCallback);
}
