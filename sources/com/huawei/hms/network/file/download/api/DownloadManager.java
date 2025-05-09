package com.huawei.hms.network.file.download.api;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.network.file.a.b;
import com.huawei.hms.network.file.api.Callback;
import com.huawei.hms.network.file.api.RequestManager;
import com.huawei.hms.network.file.api.Result;
import com.huawei.hms.network.file.core.Constants;
import com.huawei.hms.network.file.core.util.FLogger;
import com.huawei.hms.network.file.core.util.Utils;
import com.huawei.hms.network.file.download.api.GetRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public final class DownloadManager extends RequestManager<GetRequest> {
    private static final String TAG = "DownloadManager";
    private static volatile RequestManager singleInstance;
    private String fileRootDir;
    private static Map<String, RequestManager> managerMap = new ConcurrentHashMap();
    private static final Object LOCK = new Object();

    @Override // com.huawei.hms.network.file.api.RequestManager, com.huawei.hms.network.file.api.IRequestManager
    public Result start(GetRequest getRequest, Callback callback) {
        if (getRequest != null) {
            if (TextUtils.isEmpty(getRequest.getFilePath()) && TextUtils.isEmpty(this.fileRootDir)) {
                return new Result(Constants.ErrorCode.REQUEST_PATH_ERROR);
            }
            if (getRequest.getOffset() < 0) {
                return new Result(Constants.ErrorCode.REQUEST_OFFSET_ERROR);
            }
        }
        return super.start((DownloadManager) getRequest, callback);
    }

    @Override // com.huawei.hms.network.file.api.RequestManager
    public Result resumeRequest(GetRequest getRequest, Callback callback) {
        return super.resumeRequest((DownloadManager) getRequest, callback);
    }

    public static final class Builder extends RequestManager.Builder<Builder> {
        private String fileRootDir;

        public Builder fileRootDir(String str) {
            this.fileRootDir = str;
            return this;
        }

        @Override // com.huawei.hms.network.file.api.RequestManager.Builder
        public DownloadManager build(Context context) {
            return new DownloadManager(this, context);
        }

        public Builder(String str) {
            super(str);
        }

        public Builder() {
            this(null);
        }
    }

    @Override // com.huawei.hms.network.file.api.RequestManager
    public Result pauseRequest(long j) {
        return super.pauseRequest(j);
    }

    public static GetRequest.Builder newGetRequestBuilder() {
        return new GetRequest.Builder();
    }

    public static DownloadManager getInstance(Context context, String str) {
        if (Utils.isBlank(str)) {
            return getInstance(context);
        }
        if (!managerMap.containsKey(str)) {
            synchronized (LOCK) {
                if (!managerMap.containsKey(str)) {
                    DownloadManager build = new Builder(str).build(context);
                    if (!managerMap.containsKey(str)) {
                        managerMap.put(str, build);
                    }
                }
            }
        }
        return (DownloadManager) managerMap.get(str);
    }

    public static DownloadManager getInstance(Context context) {
        if (singleInstance == null) {
            synchronized (LOCK) {
                if (singleInstance == null) {
                    singleInstance = new Builder().build(context);
                }
            }
        }
        return (DownloadManager) singleInstance;
    }

    private DownloadManager(Builder builder, Context context) {
        super(builder, context);
        if (!Utils.isBlank(builder.getTag())) {
            if (managerMap.containsKey(builder.getTag())) {
                FLogger.w(TAG, "this tag " + builder.getTag() + " is exist", new Object[0]);
            }
            managerMap.put(builder.getTag(), this);
        }
        FLogger.i(TAG, "DownloadManager TAG:" + builder.getTag(), new Object[0]);
        this.fileRootDir = builder.fileRootDir;
        injectRequestHandler(new b(this.context, getTag(), getGlobalRequestConfig(), this.fileRootDir));
    }
}
