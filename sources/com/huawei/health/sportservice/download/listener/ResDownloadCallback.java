package com.huawei.health.sportservice.download.listener;

import java.util.HashMap;
import java.util.List;

/* loaded from: classes7.dex */
public interface ResDownloadCallback {
    default void onCompleteDownload(String str) {
    }

    default void onDownloadFail(int i, String str) {
    }

    default void onInstallSuccess(String str) {
    }

    default void onProgress(int i, String str) {
    }

    default void onQueryCloudResult(HashMap<String, Boolean> hashMap, boolean z, long j) {
    }

    default void onQueryResult(String str, boolean z, boolean z2, long j, List<String> list) {
    }
}
