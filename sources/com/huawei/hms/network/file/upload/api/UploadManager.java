package com.huawei.hms.network.file.upload.api;

import android.content.Context;
import com.huawei.hms.network.file.api.Callback;
import com.huawei.hms.network.file.api.RequestManager;
import com.huawei.hms.network.file.api.Result;
import com.huawei.hms.network.file.b.d;
import com.huawei.hms.network.file.core.util.FLogger;
import com.huawei.hms.network.file.core.util.Utils;
import com.huawei.hms.network.file.upload.api.PostRequest;
import com.huawei.hms.network.file.upload.api.PutRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes9.dex */
public final class UploadManager extends RequestManager<BodyRequest> {
    private static final String TAG = "UploadManager";
    private static volatile RequestManager singleInstance;
    private static Map<String, RequestManager> managerMap = new ConcurrentHashMap();
    private static final Object LOCK = new Object();

    @Override // com.huawei.hms.network.file.api.RequestManager, com.huawei.hms.network.file.api.IRequestManager
    public Result start(BodyRequest bodyRequest, Callback callback) {
        return super.start((UploadManager) bodyRequest, callback);
    }

    public static PutRequest.Builder newPutRequestBuilder() {
        return new PutRequest.Builder();
    }

    public static final class Builder extends RequestManager.Builder<Builder> {
        @Override // com.huawei.hms.network.file.api.RequestManager.Builder
        public UploadManager build(Context context) {
            return new UploadManager(this, context);
        }

        public Builder(String str) {
            super(str);
        }

        public Builder() {
        }
    }

    public static PostRequest.Builder newPostRequestBuilder() {
        return new PostRequest.Builder();
    }

    public static UploadManager getInstance(Context context, String str) {
        if (Utils.isBlank(str)) {
            return getInstance(context);
        }
        if (!managerMap.containsKey(str)) {
            synchronized (LOCK) {
                if (!managerMap.containsKey(str)) {
                    managerMap.put(str, new Builder(str).build(context));
                }
            }
        }
        return (UploadManager) managerMap.get(str);
    }

    public static UploadManager getInstance(Context context) {
        if (singleInstance == null) {
            synchronized (LOCK) {
                if (singleInstance == null) {
                    singleInstance = new Builder().build(context);
                }
            }
        }
        return (UploadManager) singleInstance;
    }

    public UploadManager(Builder builder, Context context) {
        super(builder, context);
        if (!Utils.isBlank(builder.getTag())) {
            if (managerMap.containsKey(builder.getTag())) {
                FLogger.w(TAG, "this tag " + builder.getTag() + " is exist", new Object[0]);
            }
            managerMap.put(builder.getTag(), this);
        }
        FLogger.i(TAG, "UploadManager TAG:" + builder.getTag(), new Object[0]);
        injectRequestHandler(new d(context, getTag(), this.globalConfig));
    }
}
