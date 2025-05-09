package com.huawei.health.sportservice.download.listener;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import java.util.List;

/* loaded from: classes7.dex */
public interface DownloadListener {
    void close();

    void getInstallStateDesc(String str);

    void showDownloadAskDialog(Context context, List<String> list, long j, View.OnClickListener onClickListener, View.OnClickListener onClickListener2);

    void showDownloadErrorDialog(Activity activity, int i, String str, View.OnClickListener onClickListener);

    void update(int i, String str);
}
