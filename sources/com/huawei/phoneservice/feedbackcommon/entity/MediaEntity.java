package com.huawei.phoneservice.feedbackcommon.entity;

import android.text.TextUtils;
import com.huawei.operation.utils.Constants;
import com.huawei.phoneservice.feedbackcommon.photolibrary.internal.entity.MediaItem;

/* loaded from: classes5.dex */
public class MediaEntity {
    public String attach;
    public String cache;
    public Long createTime;
    public Long duration;
    public String path;
    public String strUri;
    public String type;
    public Long updateTime;
    public String url;

    public boolean noCache() {
        return TextUtils.isEmpty(this.cache) || Constants.NULL.equalsIgnoreCase(this.cache);
    }

    public MediaEntity(String str, String str2, String str3) {
        this(str);
        this.attach = str2;
        this.url = str3;
    }

    public MediaEntity(String str, String str2) {
        this(str);
        this.path = str2;
    }

    public MediaEntity(String str) {
        this();
        this.type = str;
    }

    public MediaEntity(MediaItem mediaItem, String str, String str2) {
        this(mediaItem.getMimeType());
        this.path = str;
        this.strUri = str2;
    }

    public MediaEntity() {
        this.createTime = Long.valueOf(System.currentTimeMillis());
        this.updateTime = Long.valueOf(System.currentTimeMillis());
    }
}
