package com.huawei.hms.framework.network.download;

import com.huawei.hms.framework.network.download.internal.utils.CreateFileUtil;
import com.huawei.hms.network.file.core.util.FLogger;
import java.io.File;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class DownloadTaskBean {
    public static final String DEVICE_ID = "device_id";
    private static final String TAG = "DownloadTaskBean";
    public static final String TRACE_ID = "trace_id";
    private DownloadTaskHandler callback;
    protected int downloadRate;
    private List<String> failoverUrls;
    private String filePath;
    private long fileSize;
    private String name;
    private Map<String, String> requestHeaders;
    private String sha256;
    private List<String> urls;
    protected int status = 0;
    protected int progress = 0;
    protected long alreadyDownloadSize = 0;
    private long id = -1;
    private long startPostition = 0;
    private Map<String, String> logInfo = null;
    private DownloadResponse response = null;

    public void setResponse(DownloadResponse downloadResponse) {
        this.response = downloadResponse;
    }

    public DownloadResponse getResponse() {
        return this.response;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public List<String> getUrls() {
        return this.urls;
    }

    public void setUrls(List<String> list) {
        this.urls = list;
    }

    public List<String> getFailoverUrls() {
        return this.failoverUrls;
    }

    public void setFailoverUrls(List<String> list) {
        this.failoverUrls = list;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("filePath == null or filePath is empty");
        }
        File parentFile = CreateFileUtil.newFile(CreateFileUtil.getCanonicalPath(str)).getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            try {
                FLogger.i(TAG, "Have the file been created, dirsIsOk =" + parentFile.mkdirs(), new Object[0]);
            } catch (Throwable unused) {
                FLogger.w(TAG, "file is not exists,and create it failure", new Object[0]);
            }
        }
        this.filePath = str;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(long j) {
        this.fileSize = j;
    }

    public String getSha256() {
        return this.sha256;
    }

    public void setSha256(String str) {
        this.sha256 = str;
    }

    public Map<String, String> getLogInfo() {
        return this.logInfo;
    }

    public void setLogInfo(Map<String, String> map) {
        this.logInfo = map;
    }

    public Map<String, String> getRequestHeaders() {
        return this.requestHeaders;
    }

    public void setRequestHeaders(Map<String, String> map) {
        this.requestHeaders = map;
    }

    public DownloadTaskHandler getCallback() {
        return this.callback;
    }

    public void setCallback(DownloadTaskHandler downloadTaskHandler) {
        this.callback = downloadTaskHandler;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int i) {
        this.progress = i;
    }

    public long getAlreadyDownloadSize() {
        return this.alreadyDownloadSize;
    }

    public void setAlreadyDownloadSize(long j) {
        this.alreadyDownloadSize = j;
    }

    public int getDownloadRate() {
        return this.downloadRate;
    }

    public void setDownloadRate(int i) {
        this.downloadRate = i;
    }

    public long getStartPostition() {
        return this.startPostition;
    }

    public void setStartPostition(long j) {
        if (j <= 0) {
            j = 0;
        }
        this.startPostition = j;
    }
}
