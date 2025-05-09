package com.huawei.android.hicloud.sync.logic;

import com.huawei.android.hicloud.sync.service.aidl.FileDownloadProgress;
import com.huawei.android.hicloud.sync.service.aidl.UnstructData;
import defpackage.aag;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public interface SyncDownloadInterface {
    void onDownloadFileAbort(String str, String str2, UnstructData unstructData) throws aag;

    void onDownloadFileFail(String str, String str2, UnstructData unstructData, int i) throws aag;

    void onDownloadFileProgress(String str, String str2, FileDownloadProgress fileDownloadProgress) throws aag;

    void onDownloadFileSuccess(String str, String str2, UnstructData unstructData) throws aag;

    void onDownloadFilesComplete(String str, String str2, List<String> list, Map<Integer, List<String>> map, String str3) throws aag;

    void onDownloadFilesError(String str, String str2, String str3, String str4) throws aag;
}
