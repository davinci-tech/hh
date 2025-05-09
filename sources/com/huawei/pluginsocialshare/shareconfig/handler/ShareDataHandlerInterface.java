package com.huawei.pluginsocialshare.shareconfig.handler;

import com.huawei.health.socialshare.model.socialsharebean.ShareDataInfo;
import defpackage.mvq;
import java.util.List;

/* loaded from: classes6.dex */
public interface ShareDataHandlerInterface {
    void deleteData(mvq mvqVar, ShareDataInfo shareDataInfo);

    ShareDataInfo getShareDataById(int i);

    List<ShareDataInfo> getShareDataByIdList(List<Integer> list);

    void updateData(mvq mvqVar, ShareDataInfo shareDataInfo);

    void writeJson();
}
