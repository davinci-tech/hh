package com.huawei.health.devicemgr.api.mainprocess;

import android.graphics.Bitmap;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack;
import defpackage.cuy;
import defpackage.cvc;
import defpackage.cve;
import defpackage.cvk;
import java.util.List;

/* loaded from: classes.dex */
public interface DownloadManagerApi {
    void addDownloadIndexAllCallBack(DownloadResultCallBack downloadResultCallBack);

    void clearPluginInfoList();

    void downloadIndexAll();

    void downloadIndexByDeviceType(String str);

    void downloadIndexByUuidList(List<String> list, DownloadDeviceInfoCallBack downloadDeviceInfoCallBack);

    List<cve> getDeviceInfoByBluetooth(String str);

    List<cve> getDeviceList();

    cuy getIndexBean();

    List<cvk> getIndexList();

    String getPairDescription(String str);

    String getPluginIndexOobe(String str);

    cvc getPluginInfoByDeviceType(int i);

    cvc getPluginInfoByHiType(int i);

    cvc getPluginInfoByUuid(String str);

    List<String> getValueFeature(String str);

    boolean isHtyVersion();

    boolean isResourcesAvailable(String str);

    boolean isSupportPairCheckedOtaAutoUpdateSwitch();

    Bitmap loadImageByImageName(cvc cvcVar, String str);

    Bitmap loadImageByImagePath(String str);

    void onDownloadIndexAllDestroy();

    void resourcePresetHty();
}
