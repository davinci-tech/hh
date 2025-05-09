package com.huawei.hwdevice.api;

import android.graphics.Bitmap;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.InstalledWatchFace;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud.RecommendWatchFacesResponse;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud.WatchAbility;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud.WatchFaceDetailResponse;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud.WatchFaceVersion;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WatchFaceDto;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WatchFaceIdReportInfo;
import com.huawei.networkclient.ResultCallback;
import java.util.List;

/* loaded from: classes5.dex */
public interface IWatchFaceManager {
    void applyWatchFace(String str, ResponseCallback<Boolean> responseCallback);

    void cancelInstallingWatchFace(String str);

    void deleteWatchFaces(String str, ResponseCallback<List<WatchFaceVersion>> responseCallback);

    void getRecommendedWatchFaceBriefs(String str, String str2, int i, int i2, ResultCallback<RecommendWatchFacesResponse> resultCallback);

    void getWatchFaceLocalList(String str, ResultCallback<List<WatchFaceDto>> resultCallback);

    void installWatchFace(String str, String str2, String str3, int i);

    void isSupportDualFrame(String str, ResultCallback<Boolean> resultCallback);

    void jumpToThemePage(String str);

    void obtainDeviceWatchFace(String str, ResultCallback<List<InstalledWatchFace>> resultCallback);

    void obtainOhEntryDefaultImg(String str, ResultCallback<String> resultCallback);

    void obtainPreviewCardImage(String str, String str2, String str3, ResultCallback<Bitmap> resultCallback);

    void obtainWatchFaceDetail(String str, ResultCallback<WatchFaceDetailResponse> resultCallback);

    void queryWatchAbility(String str, ResponseCallback<List<WatchAbility>> responseCallback);

    void registerIdReportCb(String str, ResponseCallback<WatchFaceIdReportInfo> responseCallback);
}
