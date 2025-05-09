package com.huawei.health.sportservice.download.listener;

import android.content.Context;

/* loaded from: classes7.dex */
public interface AudioResDownloadInterface {
    void downAudioResource(Context context, String str, String str2, String str3, ResDownloadCallback resDownloadCallback);

    default void startDownload() {
    }
}
