package com.huawei.hms.framework.network.download;

import java.util.List;

/* loaded from: classes9.dex */
public interface DownloadManager {
    Result cancelTask(long j);

    List<Result> cancelTasks(List<Long> list);

    void close();

    long createTask(DownloadTaskBean downloadTaskBean) throws DownloadException;

    void destoryTasks();

    List<DownloadTaskBean> getAllTasks();

    DownloadTaskBean getTask(long j);

    Result pauseTask(long j);

    List<Result> pauseTasks(List<Long> list);

    Result resumeTask(DownloadTaskBean downloadTaskBean);

    List<Result> resumeTasks(List<DownloadTaskBean> list);

    void setAnalyticEnable(boolean z);
}
