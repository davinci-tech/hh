package com.huawei.hms.ads.jsb.inner.data;

import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Scheme;

/* loaded from: classes4.dex */
public class ImageInfo {
    private int fileSize;
    private int height;
    private String imageType;
    private String url;
    private int width;

    public ImageInfo(com.huawei.openalliance.ad.inter.data.ImageInfo imageInfo) {
        this.width = 0;
        this.height = 0;
        if (imageInfo != null) {
            String url = imageInfo.getUrl();
            this.url = url;
            if (!TextUtils.isEmpty(url) && !this.url.startsWith(Scheme.HTTP.toString()) && !this.url.startsWith(Scheme.HTTPS.toString())) {
                this.url = imageInfo.getOriginalUrl();
            }
            this.width = imageInfo.getWidth();
            this.height = imageInfo.getHeight();
            this.imageType = imageInfo.getImageType();
            this.fileSize = imageInfo.getFileSize();
        }
    }
}
