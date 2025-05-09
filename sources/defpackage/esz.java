package defpackage;

import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ProgressListener;
import java.io.File;
import java.util.Map;

/* loaded from: classes3.dex */
public class esz extends lqg {
    private final DataCallback e;

    public esz(String str, Map<String, String> map, File file, ProgressListener<File> progressListener, DataCallback dataCallback) {
        super(str, map, file, progressListener);
        this.e = dataCallback;
    }

    @Override // defpackage.lqg, com.huawei.networkclient.IDownloadStrategy
    public void handleException(Throwable th, int i) {
        super.handleException(th, i);
        if (this.e == null) {
            LogUtil.h("MediaDownloadStrategy", "handleException mCallback is null");
        } else {
            eqb.e(getUrl(), th, this.e, i);
        }
    }
}
