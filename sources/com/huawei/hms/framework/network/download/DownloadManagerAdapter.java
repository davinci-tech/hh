package com.huawei.hms.framework.network.download;

import android.content.Context;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.network.download.internal.constants.ExceptionCode;
import com.huawei.hms.framework.network.restclient.hwhttp.HttpClient;
import com.huawei.hms.network.file.api.Callback;
import com.huawei.hms.network.file.api.GlobalRequestConfig;
import com.huawei.hms.network.file.api.Progress;
import com.huawei.hms.network.file.api.Response;
import com.huawei.hms.network.file.api.exception.NetworkException;
import com.huawei.hms.network.file.core.FileManagerException;
import com.huawei.hms.network.file.core.util.FLogger;
import com.huawei.hms.network.file.core.util.Utils;
import com.huawei.hms.network.file.download.api.DownloadManager;
import com.huawei.hms.network.file.download.api.FileRequestCallback;
import com.huawei.hms.network.file.download.api.GetRequest;
import java.io.Closeable;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes9.dex */
public class DownloadManagerAdapter implements DownloadManager {
    private static final int CONST = 1000;
    static final int DOWNLOAD_DEFAULT_RETRY_TIMES = 2;
    private static final String TAG = "DownloadManagerAdapter";
    private com.huawei.hms.network.file.download.api.DownloadManager downloadManager;
    private String name = "download manager";
    ConcurrentHashMap<Long, DownloadTaskBean> taskBeanHashMap = new ConcurrentHashMap<>();

    class CallbackWrapper extends FileRequestCallback {
        DownloadTaskBean taskInfo;

        public CallbackWrapper(DownloadTaskBean downloadTaskBean) {
            this.taskInfo = downloadTaskBean;
        }

        @Override // com.huawei.hms.network.file.api.Callback
        public GetRequest onStart(GetRequest getRequest) {
            if (this.taskInfo.getCallback() == null) {
                return getRequest;
            }
            FLogger.i(DownloadManagerAdapter.TAG, "onStart", new Object[0]);
            DownloadTaskBean deepCopy = DownloadManagerAdapter.this.deepCopy(this.taskInfo);
            this.taskInfo.getCallback().updateTaskBean(this.taskInfo);
            this.taskInfo.setStatus(1);
            return DownloadManagerAdapter.this.isTaskBeanEqual(deepCopy, this.taskInfo) ? getRequest : DownloadManagerAdapter.this.updateRequest(this.taskInfo, getRequest);
        }

        @Override // com.huawei.hms.network.file.api.Callback
        public void onProgress(GetRequest getRequest, Progress progress) {
            this.taskInfo.setProgress(progress.getProgress());
            this.taskInfo.setAlreadyDownloadSize(progress.getFinishedSize());
            this.taskInfo.setFileSize(progress.getTotalSize());
            this.taskInfo.setDownloadRate((int) progress.getSpeed());
            if (this.taskInfo.getCallback() != null) {
                this.taskInfo.getCallback().onProgress(this.taskInfo);
            }
        }

        @Override // com.huawei.hms.network.file.api.Callback
        public void onSuccess(Response<GetRequest, File, Closeable> response) {
            DownloadResponse downloadResponse = new DownloadResponse();
            com.huawei.hms.network.httpclient.Response response2 = (com.huawei.hms.network.httpclient.Response) response.getRawResponse();
            if (response2 != null) {
                downloadResponse.setStatus(response2.getCode());
            }
            downloadResponse.setHeaders(response.getResponseHeader());
            this.taskInfo.setResponse(downloadResponse);
            this.taskInfo.setStatus(4);
            DownloadManagerAdapter.this.removeCacheTaskIfNeed(this.taskInfo);
            if (this.taskInfo.getCallback() != null) {
                this.taskInfo.getCallback().onCompleted(this.taskInfo);
            }
        }

        @Override // com.huawei.hms.network.file.api.Callback
        public void onException(GetRequest getRequest, NetworkException networkException, Response<GetRequest, File, Closeable> response) {
            int errorCode = networkException.getCause() instanceof FileManagerException ? ((FileManagerException) Utils.cast(networkException.getCause())).getErrorCode() : 0;
            if (errorCode == com.huawei.hms.network.file.api.Result.PAUSE) {
                this.taskInfo.setStatus(2);
            } else if (errorCode == com.huawei.hms.network.file.api.Result.CANCEL) {
                this.taskInfo.setStatus(3);
            } else {
                this.taskInfo.setStatus(5);
                DownloadManagerAdapter.this.removeCacheTaskIfNeed(this.taskInfo);
            }
            if (response != null) {
                DownloadResponse downloadResponse = new DownloadResponse();
                com.huawei.hms.network.httpclient.Response response2 = (com.huawei.hms.network.httpclient.Response) response.getRawResponse();
                if (response2 != null) {
                    downloadResponse.setStatus(response2.getCode());
                }
                downloadResponse.setHeaders(response.getResponseHeader());
                this.taskInfo.setResponse(downloadResponse);
            }
            DownloadException downloadException = new DownloadException(errorCode, networkException.getMessage(), networkException);
            if (this.taskInfo.getCallback() != null) {
                this.taskInfo.getCallback().onException(this.taskInfo, downloadException);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeCacheTaskIfNeed(DownloadTaskBean downloadTaskBean) {
        if (downloadTaskBean != null) {
            removeCacheTaskIfNeed(downloadTaskBean.getId());
        }
    }

    private void removeCacheTaskIfNeed(long j) {
        if (this.taskBeanHashMap.containsKey(Long.valueOf(j))) {
            this.taskBeanHashMap.remove(Long.valueOf(j));
        }
    }

    public void init(Context context, String str, int i, DownloadManagerBean downloadManagerBean, HttpClient httpClient) {
        this.name = str;
        GlobalRequestConfig.Builder retryTimes = com.huawei.hms.network.file.download.api.DownloadManager.newGlobalRequestConfigBuilder().oldInterfaceFlag(true).threadPoolSize(i * 2).retryTimes(2);
        if (httpClient != null) {
            retryTimes.httpClient(httpClient.getProxyHttpClient());
        } else {
            retryTimes.connectTimeoutMillis(downloadManagerBean.getConnectionTimeOut() * 1000).pingIntervalMillis(downloadManagerBean.getPingInterval() * 1000).readTimeoutMillis(downloadManagerBean.getReadTimeOut() * 1000).writeTimeoutMillis(downloadManagerBean.getWriteTimeOut() * 1000);
        }
        this.downloadManager = new DownloadManager.Builder(this.name).commonConfig(retryTimes.build()).build(context);
        Utils.printDeprecatedLogIfNeed();
    }

    @Override // com.huawei.hms.framework.network.download.DownloadManager
    public long createTask(DownloadTaskBean downloadTaskBean) throws DownloadException {
        if (downloadTaskBean == null) {
            FLogger.w(TAG, "task is null", new Object[0]);
            throw new DownloadException(ExceptionCode.ErrorCode.DOWNLOAD_TRANSPOSITION_PARAMS_COMMON_ERROR.getErrorCode(), ExceptionCode.ErrorCode.DOWNLOAD_TRANSPOSITION_PARAMS_COMMON_ERROR.getErrorMessage() + "task is null");
        }
        if (Utils.isBlank(getFileName(downloadTaskBean.getFilePath()))) {
            FLogger.w(TAG, "task filePath is null", new Object[0]);
            throw new DownloadException(1103, "task filePath is null");
        }
        long startTask = startTask(downloadTaskBean);
        this.taskBeanHashMap.put(Long.valueOf(startTask), downloadTaskBean);
        return startTask;
    }

    @Override // com.huawei.hms.framework.network.download.DownloadManager
    public DownloadTaskBean getTask(long j) {
        if (this.taskBeanHashMap.containsKey(Long.valueOf(j))) {
            return this.taskBeanHashMap.get(Long.valueOf(j));
        }
        GetRequest request = this.downloadManager.getRequest(j);
        if (request == null) {
            return null;
        }
        DownloadTaskBean newTaskBean = newTaskBean(request);
        this.taskBeanHashMap.put(Long.valueOf(j), newTaskBean);
        return newTaskBean;
    }

    @Override // com.huawei.hms.framework.network.download.DownloadManager
    public List<DownloadTaskBean> getAllTasks() {
        DownloadTaskBean downloadTaskBean;
        ArrayList arrayList = new ArrayList();
        if (this.downloadManager.getAllRequests() != null) {
            for (GetRequest getRequest : this.downloadManager.getAllRequests()) {
                if (this.taskBeanHashMap.containsKey(Long.valueOf(getRequest.getId()))) {
                    downloadTaskBean = this.taskBeanHashMap.get(Long.valueOf(getRequest.getId()));
                } else {
                    DownloadTaskBean newTaskBean = newTaskBean(getRequest);
                    this.taskBeanHashMap.put(Long.valueOf(getRequest.getId()), newTaskBean);
                    downloadTaskBean = newTaskBean;
                }
                arrayList.add(downloadTaskBean);
            }
        }
        return arrayList;
    }

    @Override // com.huawei.hms.framework.network.download.DownloadManager
    public Result cancelTask(long j) {
        com.huawei.hms.network.file.api.Result cancelRequest = this.downloadManager.cancelRequest(j);
        removeCacheTaskIfNeed(j);
        return new Result(cancelRequest.getCode(), cancelRequest.getMessage());
    }

    @Override // com.huawei.hms.framework.network.download.DownloadManager
    @Deprecated
    public List<Result> cancelTasks(List<Long> list) {
        ArrayList arrayList = new ArrayList();
        for (Long l : list) {
            com.huawei.hms.network.file.api.Result cancelRequest = this.downloadManager.cancelRequest(l.longValue());
            removeCacheTaskIfNeed(l.longValue());
            arrayList.add(new Result(cancelRequest.getCode(), cancelRequest.getMessage()));
        }
        return arrayList;
    }

    @Override // com.huawei.hms.framework.network.download.DownloadManager
    public Result pauseTask(long j) {
        com.huawei.hms.network.file.api.Result pauseRequest = this.downloadManager.pauseRequest(j);
        return new Result(pauseRequest.getCode(), pauseRequest.getMessage());
    }

    @Override // com.huawei.hms.framework.network.download.DownloadManager
    public List<Result> pauseTasks(List<Long> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<Long> it = list.iterator();
        while (it.hasNext()) {
            com.huawei.hms.network.file.api.Result pauseRequest = this.downloadManager.pauseRequest(it.next().longValue());
            arrayList.add(new Result(pauseRequest.getCode(), pauseRequest.getMessage()));
        }
        return arrayList;
    }

    @Override // com.huawei.hms.framework.network.download.DownloadManager
    public Result resumeTask(DownloadTaskBean downloadTaskBean) {
        if (downloadTaskBean == null) {
            FLogger.e(TAG, "taskBean can not be null");
            return new Result(1, "taskBean can not be null");
        }
        GetRequest request = this.downloadManager.getRequest(downloadTaskBean.getId());
        if (request == null) {
            FLogger.e(TAG, "task is no exist task, taskId=" + downloadTaskBean.getId());
            return new Result(1, "task is no exist");
        }
        GetRequest updateRequest = updateRequest(downloadTaskBean, request);
        downloadTaskBean.setId(updateRequest.getId());
        com.huawei.hms.network.file.api.Result resumeRequest = this.downloadManager.resumeRequest(updateRequest, (Callback) new CallbackWrapper(downloadTaskBean));
        this.taskBeanHashMap.put(Long.valueOf(downloadTaskBean.getId()), downloadTaskBean);
        return new Result(resumeRequest.getCode(), resumeRequest.getMessage());
    }

    @Override // com.huawei.hms.framework.network.download.DownloadManager
    public List<Result> resumeTasks(List<DownloadTaskBean> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<DownloadTaskBean> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(resumeTask(it.next()));
        }
        return arrayList;
    }

    @Override // com.huawei.hms.framework.network.download.DownloadManager
    public void destoryTasks() {
        this.downloadManager.destoryRequests();
        this.taskBeanHashMap.clear();
    }

    @Override // com.huawei.hms.framework.network.download.DownloadManager
    public void close() {
        this.downloadManager.closeThreadPools();
    }

    @Override // com.huawei.hms.framework.network.download.DownloadManager
    @Deprecated
    public void setAnalyticEnable(boolean z) {
        Utils.printDeprecatedMethodLog("setAnalyticEnable of download");
    }

    private long startTask(DownloadTaskBean downloadTaskBean) throws DownloadException {
        String str;
        try {
            ArrayList arrayList = new ArrayList();
            ArrayList<String> urls = getUrls(downloadTaskBean);
            if (urls.size() > 0) {
                str = urls.get(0);
                if (urls.size() > 1) {
                    arrayList.addAll(urls.subList(1, urls.size()));
                }
            } else {
                str = null;
            }
            if (isRepeatTask(downloadTaskBean)) {
                FLogger.w(TAG, "task repeat, cause: taskId or fileName repeat", new Object[0]);
                throw new DownloadException(1103, "task repeat: taskId or fileName repeat");
            }
            GetRequest request = getRequest(downloadTaskBean, com.huawei.hms.network.file.download.api.DownloadManager.newGetRequestBuilder(), str, arrayList);
            downloadTaskBean.setId(request.getId());
            com.huawei.hms.network.file.api.Result start = this.downloadManager.start(request, (Callback) new CallbackWrapper(downloadTaskBean));
            if (start.getCode() != com.huawei.hms.network.file.api.Result.SUCCESS) {
                DownloadException downloadException = new DownloadException(start.getCode(), start.getMessage());
                if (downloadTaskBean.getCallback() != null) {
                    downloadTaskBean.getCallback().onException(downloadTaskBean, downloadException);
                }
            }
            return request.getId();
        } catch (DownloadException e) {
            throw e;
        } catch (Exception e2) {
            FLogger.e(TAG, "start exception", e2);
            String anonymizeMessage = StringUtils.anonymizeMessage(e2.getMessage());
            throw new DownloadException(ExceptionCode.ErrorCode.DOWNLOAD_TRANSPOSITION_UNKNOWN_ERROR.getErrorCode(), ExceptionCode.ErrorCode.DOWNLOAD_TRANSPOSITION_UNKNOWN_ERROR.getErrorMessage() + anonymizeMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public GetRequest updateRequest(DownloadTaskBean downloadTaskBean, GetRequest getRequest) {
        ArrayList<String> urls = getUrls(downloadTaskBean);
        ArrayList arrayList = new ArrayList();
        if (urls.size() > 0) {
            String str = urls.get(0);
            if (urls.size() > 1) {
                arrayList.addAll(urls.subList(1, urls.size()));
            }
            return getRequest(downloadTaskBean, getRequest.newBuilder(), str, arrayList);
        }
        FLogger.e(TAG, "originRequest url empty!");
        return getRequest;
    }

    private DownloadTaskBean newTaskBean(GetRequest getRequest) {
        DownloadTaskBean downloadTaskBean = new DownloadTaskBean();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList.add(getRequest.getUrl());
        arrayList2.addAll(getRequest.getBackupUrls());
        downloadTaskBean.setId(getRequest.getId());
        downloadTaskBean.setStartPostition(getRequest.getOffset());
        downloadTaskBean.setUrls(arrayList);
        downloadTaskBean.setFailoverUrls(arrayList2);
        downloadTaskBean.setFilePath(getRequest.getFilePath());
        downloadTaskBean.setSha256(getRequest.getSha256());
        downloadTaskBean.setRequestHeaders(getRequest.getHeaders());
        downloadTaskBean.setFileSize(getRequest.getFileSize());
        downloadTaskBean.setLogInfo(getRequest.getReportInfos());
        downloadTaskBean.setName(getRequest.getName());
        downloadTaskBean.setStatus(this.downloadManager.getRequestStatus(getRequest.getId()).ordinal());
        return downloadTaskBean;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DownloadTaskBean deepCopy(DownloadTaskBean downloadTaskBean) {
        DownloadTaskBean downloadTaskBean2 = new DownloadTaskBean();
        downloadTaskBean2.setDownloadRate(downloadTaskBean.getDownloadRate());
        downloadTaskBean2.setCallback(downloadTaskBean.getCallback());
        downloadTaskBean2.setId(downloadTaskBean.getId());
        HashMap hashMap = new HashMap();
        if (downloadTaskBean.getLogInfo() != null) {
            hashMap.putAll(downloadTaskBean.getLogInfo());
        }
        downloadTaskBean2.setLogInfo(hashMap);
        downloadTaskBean2.setName(downloadTaskBean.getName());
        downloadTaskBean2.setProgress(downloadTaskBean.getProgress());
        ArrayList arrayList = new ArrayList();
        if (downloadTaskBean.getUrls() != null) {
            arrayList.addAll(downloadTaskBean.getUrls());
        }
        downloadTaskBean2.setUrls(arrayList);
        if (downloadTaskBean.getFailoverUrls() != null) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(downloadTaskBean.getFailoverUrls());
            downloadTaskBean2.setFailoverUrls(arrayList2);
        }
        downloadTaskBean2.setSha256(downloadTaskBean.getSha256());
        downloadTaskBean2.setAlreadyDownloadSize(downloadTaskBean.getAlreadyDownloadSize());
        downloadTaskBean2.setFilePath(downloadTaskBean.getFilePath());
        downloadTaskBean2.setFileSize(downloadTaskBean.getFileSize());
        downloadTaskBean2.setStartPostition(downloadTaskBean.getStartPostition());
        HashMap hashMap2 = new HashMap();
        if (downloadTaskBean.getRequestHeaders() != null) {
            hashMap2.putAll(downloadTaskBean.getRequestHeaders());
        }
        downloadTaskBean2.setRequestHeaders(hashMap2);
        downloadTaskBean2.setResponse(downloadTaskBean.getResponse());
        downloadTaskBean2.setStatus(downloadTaskBean.getStatus());
        return downloadTaskBean2;
    }

    boolean isDeepEqual(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return obj.equals(obj2);
    }

    boolean isTaskBeanEqual(DownloadTaskBean downloadTaskBean, DownloadTaskBean downloadTaskBean2) {
        if (downloadTaskBean == downloadTaskBean2) {
            return true;
        }
        return downloadTaskBean != null && downloadTaskBean2 != null && downloadTaskBean.getId() == downloadTaskBean2.getId() && downloadTaskBean.getFileSize() == downloadTaskBean2.getFileSize() && downloadTaskBean.getStartPostition() == downloadTaskBean2.getStartPostition() && downloadTaskBean.getAlreadyDownloadSize() == downloadTaskBean2.getAlreadyDownloadSize() && isDeepEqual(downloadTaskBean.getFilePath(), downloadTaskBean2.getFilePath()) && isDeepEqual(downloadTaskBean.getFailoverUrls(), downloadTaskBean2.getFailoverUrls()) && isDeepEqual(downloadTaskBean.getUrls(), downloadTaskBean2.getUrls()) && isDeepEqual(downloadTaskBean.getRequestHeaders(), downloadTaskBean2.getRequestHeaders()) && isDeepEqual(downloadTaskBean.getLogInfo(), downloadTaskBean2.getLogInfo()) && isDeepEqual(downloadTaskBean.getSha256(), downloadTaskBean2.getSha256()) && downloadTaskBean.getCallback() == downloadTaskBean2.getCallback();
    }

    private GetRequest getRequest(DownloadTaskBean downloadTaskBean, GetRequest.Builder builder, String str, List<String> list) {
        return builder.offset(downloadTaskBean.getStartPostition()).fileSize(downloadTaskBean.getFileSize()).sha256(downloadTaskBean.getSha256()).filePath(downloadTaskBean.getFilePath()).headers(downloadTaskBean.getRequestHeaders()).reportInfos(downloadTaskBean.getLogInfo()).url(str).name(downloadTaskBean.getName()).config(com.huawei.hms.network.file.download.api.DownloadManager.newRequestConfigBuilder().build()).backupUrls(list).build();
    }

    private ArrayList<String> getUrls(DownloadTaskBean downloadTaskBean) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (downloadTaskBean.getUrls() != null) {
            arrayList.addAll(downloadTaskBean.getUrls());
        }
        if (downloadTaskBean.getFailoverUrls() != null) {
            arrayList.addAll(downloadTaskBean.getFailoverUrls());
        }
        return arrayList;
    }

    private boolean isRepeatTask(DownloadTaskBean downloadTaskBean) {
        ConcurrentHashMap<Long, DownloadTaskBean> concurrentHashMap = this.taskBeanHashMap;
        if (concurrentHashMap == null) {
            return false;
        }
        for (DownloadTaskBean downloadTaskBean2 : concurrentHashMap.values()) {
            if (!Utils.isBlank(getFileName(downloadTaskBean2.getFilePath())) && (downloadTaskBean2.getId() == downloadTaskBean.getId() || downloadTaskBean2.getFilePath().equals(downloadTaskBean.getFilePath()))) {
                return true;
            }
        }
        return false;
    }

    private String getFileName(String str) {
        int lastIndexOf;
        if (str == null || (lastIndexOf = str.lastIndexOf(File.separator)) == -1) {
            return null;
        }
        return str.substring(lastIndexOf + 1);
    }
}
