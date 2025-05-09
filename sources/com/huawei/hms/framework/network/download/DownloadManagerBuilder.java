package com.huawei.hms.framework.network.download;

import android.content.Context;
import com.huawei.hms.framework.network.restclient.hwhttp.HttpClient;
import com.huawei.hms.network.file.core.util.Utils;

/* loaded from: classes9.dex */
public class DownloadManagerBuilder {
    private HttpClient httpClient;
    private String mname = null;
    private Context mcontext = null;
    private int mtaskNum = 0;
    private DownloadManagerBean mDownloadManagerBean = new DownloadManagerBean();

    public DownloadManagerBuilder context(Context context) {
        this.mcontext = context;
        return this;
    }

    public DownloadManagerBuilder taskNum(int i) {
        this.mtaskNum = i;
        return this;
    }

    @Deprecated
    public DownloadManagerBuilder analyticUrl(String str) {
        Utils.printDeprecatedMethodLog("analyticUrl of download");
        return this;
    }

    @Deprecated
    public DownloadManagerBuilder localRegion(String str) {
        Utils.printDeprecatedMethodLog("localRegion of download");
        return this;
    }

    public DownloadManagerBuilder name(String str) {
        this.mname = str;
        return this;
    }

    @Deprecated
    public DownloadManagerBuilder analyticEnable(boolean z) {
        Utils.printDeprecatedMethodLog("analyticEnable of download");
        return this;
    }

    public DownloadManagerBuilder managerBean(DownloadManagerBean downloadManagerBean) {
        if (downloadManagerBean != null) {
            this.mDownloadManagerBean = downloadManagerBean;
        }
        return this;
    }

    public DownloadManagerBuilder httpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
        return this;
    }

    public DownloadManager build() {
        Context context;
        DownloadManagerAdapter downloadManagerAdapter = new DownloadManagerAdapter();
        String str = this.mname;
        if (str == null || (context = this.mcontext) == null) {
            throw new IllegalArgumentException("name==null,please call name() or context == null,please call context()");
        }
        downloadManagerAdapter.init(context, str, this.mtaskNum, this.mDownloadManagerBean, this.httpClient);
        return downloadManagerAdapter;
    }
}
