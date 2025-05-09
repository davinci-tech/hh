package defpackage;

import com.huawei.health.socialshare.model.socialsharebean.ShareDataInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginsocialshare.cloud.bean.DataCallback;
import com.huawei.pluginsocialshare.cloud.bean.DownloadCallback;
import health.compact.a.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mug implements Runnable {
    private List<ShareDataInfo> c;
    private volatile DownloadCallback d;
    private int e;
    private boolean b = false;

    /* renamed from: a, reason: collision with root package name */
    private boolean f15176a = false;

    public mug(List<ShareDataInfo> list, DownloadCallback downloadCallback, int i) {
        ArrayList arrayList = new ArrayList();
        this.c = arrayList;
        arrayList.addAll(list);
        this.d = downloadCallback;
        this.e = i;
    }

    public void d(boolean z) {
        this.b = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (koq.b(this.c)) {
            return;
        }
        LogUtil.a("TAG_DownloadThread", "start download resource.", Integer.valueOf(this.c.size()));
        this.f15176a = false;
        for (final ShareDataInfo shareDataInfo : this.c) {
            if (this.b) {
                LogUtil.a("TAG_DownloadThread", "download is cancel");
                return;
            } else if (shareDataInfo != null) {
                String c = shareDataInfo instanceof mut ? ((mut) shareDataInfo).c() : shareDataInfo.getUrl();
                String path = shareDataInfo.getPath();
                LogUtil.a("TAG_DownloadThread", "path:", path, " type:", Integer.valueOf(shareDataInfo.getType()));
                muj.e(c, path, new DataCallback() { // from class: mug.3
                    @Override // com.huawei.pluginsocialshare.cloud.bean.DataCallback
                    public void onFailure(int i, String str) {
                        LogUtil.b("TAG_DownloadThread", "errorCode:", Integer.valueOf(i), "errorMsg:", str);
                        ShareDataInfo shareDataInfo2 = shareDataInfo;
                        if (shareDataInfo2 instanceof mut) {
                            ((mut) shareDataInfo2).c(false);
                        }
                        if (!mug.this.b || !mug.this.f15176a) {
                            mug.this.f15176a = true;
                            if (mug.this.d != null) {
                                mug.this.d.onFailure(i, str);
                                LogUtil.a("TAG_DownloadThread", "mCallback :", mug.this.d);
                                return;
                            } else {
                                LogUtil.h("TAG_DownloadThread", " mCallback is null");
                                return;
                            }
                        }
                        LogUtil.a("TAG_DownloadThread", "callback is too much");
                        mug.this.d = null;
                    }

                    @Override // com.huawei.pluginsocialshare.cloud.bean.DataCallback
                    public void onSuccess(JSONObject jSONObject) {
                        LogUtil.a("TAG_DownloadThread", "download success.");
                        ShareDataInfo shareDataInfo2 = shareDataInfo;
                        if (shareDataInfo2 instanceof mut) {
                            ((mut) shareDataInfo2).d(true);
                            ((mut) shareDataInfo).c(false);
                        }
                        if (shareDataInfo.getType() != 3) {
                            mug.this.d(jSONObject, shareDataInfo);
                            return;
                        }
                        fef fefVar = (fef) shareDataInfo;
                        if (StringUtils.g(fefVar.d())) {
                            mug.this.d(jSONObject, shareDataInfo);
                        } else {
                            mug.this.d(fefVar);
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final fef fefVar) {
        muj.e(fefVar.d(), fefVar.b(), new DataCallback() { // from class: mug.2
            @Override // com.huawei.pluginsocialshare.cloud.bean.DataCallback
            public void onFailure(int i, String str) {
                LogUtil.b("TAG_DownloadThread", "download alter imagine errorCode:", Integer.valueOf(i), "errorMsg:", str);
                if (mug.this.d != null) {
                    mug.this.d.onFailure(i, str);
                }
            }

            @Override // com.huawei.pluginsocialshare.cloud.bean.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                LogUtil.a("TAG_DownloadThread", "download alter imagine success.");
                mug.this.d(jSONObject, fefVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(JSONObject jSONObject, ShareDataInfo shareDataInfo) {
        mvl.b().c(this.e, shareDataInfo, shareDataInfo.getType());
        if (this.d != null) {
            this.d.onSuccess(jSONObject, shareDataInfo);
        }
    }
}
