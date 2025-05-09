package com.huawei.pluginsocialshare.activity;

import com.huawei.health.socialshare.model.socialsharebean.ShareDataInfo;
import defpackage.fea;
import java.util.List;

/* loaded from: classes.dex */
public interface DownloadInterface {
    void notifyBackgroudChanged(fea feaVar);

    void notifyBackgroudFail();

    void notifyDownloadDataFail();

    void notifyShareDataChanged(fea feaVar);

    void refreshDataMark(List<ShareDataInfo> list);

    void refreshShareLayoutNoRecommend();

    void showDownloadError();

    void updateAllFragment(boolean z);
}
