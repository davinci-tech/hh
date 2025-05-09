package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.info.NonceResponseInfo;
import com.huawei.hwversionmgr.info.PsiUploadResponseInfo;
import com.huawei.hwversionmgr.utils.callback.PsiUploadResultCallback;
import com.huawei.networkclient.ResultCallback;

/* loaded from: classes5.dex */
public class kyf {
    private static volatile kyf c;
    private static final byte[] e = new byte[1];

    public static kyf a() {
        synchronized (e) {
            if (c == null) {
                c = new kyf();
            }
        }
        return c;
    }

    public void c(final DeviceInfo deviceInfo, final boolean z, final PsiUploadResultCallback psiUploadResultCallback) {
        kxq.d(new ResultCallback<NonceResponseInfo>() { // from class: kyf.5
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(NonceResponseInfo nonceResponseInfo) {
                LogUtil.c("PsiUpdateUtil", "queryNonceDetail resultCode:", nonceResponseInfo.toString());
                kxq.c(deviceInfo, nonceResponseInfo.getNonce(), z, new ResultCallback<PsiUploadResponseInfo>() { // from class: kyf.5.4
                    @Override // com.huawei.networkclient.ResultCallback
                    /* renamed from: e, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(PsiUploadResponseInfo psiUploadResponseInfo) {
                        LogUtil.c("PsiUpdateUtil", "queryPsiUploadDetail data:", psiUploadResponseInfo.toString());
                        if (psiUploadResultCallback != null) {
                            psiUploadResultCallback.onSuccess(psiUploadResponseInfo);
                        }
                    }

                    @Override // com.huawei.networkclient.ResultCallback
                    public void onFailure(Throwable th) {
                        LogUtil.c("PsiUpdateUtil", "queryPsiUploadDetail onFailure:", th.getMessage());
                        if (psiUploadResultCallback != null) {
                            psiUploadResultCallback.onFailure(th);
                        }
                    }
                });
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.c("PsiUpdateUtil", "queryNonceDetail onFailure:", th.getMessage());
                PsiUploadResultCallback psiUploadResultCallback2 = psiUploadResultCallback;
                if (psiUploadResultCallback2 != null) {
                    psiUploadResultCallback2.onFailure(th);
                }
            }
        });
    }
}
