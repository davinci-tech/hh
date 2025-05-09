package com.huawei.haf.bundle;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import java.util.List;

/* loaded from: classes.dex */
public interface InstallGuide {

    public interface InstallProgress {
        void close();

        void update(int i, String str);
    }

    InstallProgress createInstallProgress(Activity activity, String str, View.OnClickListener onClickListener);

    String getInstallErrorDesc(Context context, int i);

    String getInstallStateDesc(Context context, InstallSessionState installSessionState);

    Class<? extends Activity> getTransparentGuideActivity();

    void showDownloadAskDialog(Activity activity, List<String> list, long j, View.OnClickListener onClickListener, View.OnClickListener onClickListener2);

    void showDownloadErrorDialog(Activity activity, int i, String str, View.OnClickListener onClickListener);
}
