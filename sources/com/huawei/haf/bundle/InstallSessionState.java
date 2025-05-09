package com.huawei.haf.bundle;

import android.app.PendingIntent;
import java.util.List;

/* loaded from: classes.dex */
public interface InstallSessionState {
    long bytesDownloaded();

    List<String> downloadModuleNames();

    int errorCode();

    List<String> moduleNames();

    PendingIntent resolutionIntent();

    int sessionId();

    int status();

    long totalBytesToDownload();
}
