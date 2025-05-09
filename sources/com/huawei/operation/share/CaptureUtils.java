package com.huawei.operation.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.Toast;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.WebViewUtils;
import com.huawei.ui.commonui.R$string;
import defpackage.fdu;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.Normalizer;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class CaptureUtils {
    private static final int CODE_ERROR = 2;
    private static final int CODE_NO_PERMISSION = -1;
    private static final int CODE_SUCCESS = 1;
    private static final int HTTP_OK = 200;
    private static final int QUALITY = 80;
    private static final String TAG = "PluginOperation_CaptureUtils";
    private Context mContext;
    private Toast mToast;
    private SocialShareCenterApi mSocialShareCenterApi = (SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class);
    private Looper mLooper = Looper.myLooper();

    public CaptureUtils(Context context) {
        this.mContext = context;
    }

    public void captureNoPermission(WebView webView, String str) {
        doResponse(webView, str, -1, null);
    }

    public void capture(final WebView webView, final String str) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.operation.share.CaptureUtils.1
            @Override // java.lang.Runnable
            public void run() {
                if (CaptureUtils.this.mContext != null) {
                    if (CaptureUtils.this.mToast == null) {
                        CaptureUtils captureUtils = CaptureUtils.this;
                        captureUtils.mToast = Toast.makeText(captureUtils.mContext, CaptureUtils.this.mContext.getString(R$string.IDS_sns_waiting), 1);
                    }
                    CaptureUtils.this.mToast.show();
                }
            }
        });
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.huawei.operation.share.CaptureUtils.2
            @Override // java.lang.Runnable
            public void run() {
                CaptureUtils.this.capture(webView, new IBaseResponseCallback() { // from class: com.huawei.operation.share.CaptureUtils.2.1
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        if (i == 2) {
                            CaptureUtils.this.doResponse(webView, str, i, null);
                        } else if (obj instanceof Bitmap) {
                            CaptureUtils.this.saveCapture(webView, i, (Bitmap) obj, str);
                        } else {
                            LogUtil.a(CaptureUtils.TAG, "objData is null || objData not instanceof bitmap");
                        }
                    }
                });
            }
        }, 200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void capture(WebView webView, IBaseResponseCallback iBaseResponseCallback) {
        int contentHeight = (int) (webView.getContentHeight() * webView.getScale());
        int measuredWidth = webView.getMeasuredWidth();
        LogUtil.a(TAG, "webView.height = ", Integer.valueOf(contentHeight), " webView.width = ", Integer.valueOf(measuredWidth));
        if (contentHeight <= 0 || measuredWidth <= 0) {
            LogUtil.a(TAG, "(height<=0) || (width<=0)");
            return;
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(measuredWidth, contentHeight, Bitmap.Config.ARGB_8888);
            webView.draw(new Canvas(createBitmap));
            iBaseResponseCallback.d(1, createBitmap);
        } catch (IllegalArgumentException | OutOfMemoryError unused) {
            LogUtil.b(TAG, "capture exception");
            iBaseResponseCallback.d(2, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x00a1, code lost:
    
        if (r13 != null) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x00cc, code lost:
    
        defpackage.jei.c(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x00cf, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x00c9, code lost:
    
        r13.recycle();
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00c7, code lost:
    
        if (r13 == null) goto L63;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00dd  */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void saveCapture(android.webkit.WebView r11, int r12, android.graphics.Bitmap r13, java.lang.String r14) {
        /*
            Method dump skipped, instructions count: 228
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.operation.share.CaptureUtils.saveCapture(android.webkit.WebView, int, android.graphics.Bitmap, java.lang.String):void");
    }

    private Bitmap scaleBitmapBySize(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        float sqrt = (float) Math.sqrt((1.012E7f / bitmap.getHeight()) / bitmap.getWidth());
        LogUtil.a(TAG, "scale = ", Float.valueOf(sqrt));
        if (sqrt > 1.0f) {
            return bitmap;
        }
        matrix.setScale(sqrt, sqrt);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doResponse(WebView webView, String str, int i, String str2) {
        LogUtil.a(TAG, "code = ", Integer.valueOf(i));
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.operation.share.CaptureUtils$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CaptureUtils.this.m757lambda$doResponse$0$comhuaweioperationshareCaptureUtils();
            }
        });
        if (this.mLooper != null) {
            new Handler(this.mLooper).post(new DoResponseRunnable(webView, WebViewUtils.getUrlForHtml(str, i + " ,  ' " + str2 + " ' ")));
            return;
        }
        LogUtil.a(TAG, "mLooper = null");
    }

    /* renamed from: lambda$doResponse$0$com-huawei-operation-share-CaptureUtils, reason: not valid java name */
    /* synthetic */ void m757lambda$doResponse$0$comhuaweioperationshareCaptureUtils() {
        Toast toast = this.mToast;
        if (toast != null) {
            toast.cancel();
        }
    }

    public void share(String str, int i) {
        if (!TextUtils.isEmpty(str)) {
            str = str.trim();
        }
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.h(TAG, "share: untrusted -> " + str);
        } else {
            fdu fduVar = new fdu(5);
            fduVar.d(str);
            fduVar.c(i);
            fduVar.b(3);
            fduVar.c(false);
            this.mSocialShareCenterApi.exeShare(fduVar, BaseApplication.getContext());
        }
    }

    public void share(String str) {
        share(str, "10", new HashMap<>());
    }

    public void share(String str, String str2, HashMap<String, Object> hashMap) {
        fdu fduVar = new fdu(4);
        if (!TextUtils.isEmpty(str)) {
            str = str.trim();
        }
        fduVar.d(str);
        fduVar.b(3);
        fduVar.b(str2);
        if (hashMap != null && !hashMap.isEmpty()) {
            fduVar.b(hashMap);
        }
        fduVar.c(false);
        SocialShareCenterApi socialShareCenterApi = this.mSocialShareCenterApi;
        if (socialShareCenterApi == null) {
            LogUtil.h(TAG, "share: mSocialShareCenterApi is null");
        } else {
            socialShareCenterApi.exeShare(fduVar, BaseApplication.getContext());
        }
    }

    private static boolean validateFileName(File file, String str) throws IOException {
        if (Normalizer.normalize(CommonUtil.c(file.getCanonicalPath()), Normalizer.Form.NFKC).startsWith(Normalizer.normalize(new File(str).getCanonicalPath(), Normalizer.Form.NFKC))) {
            return true;
        }
        LogUtil.b(TAG, "File is outside extraction target directory.");
        return false;
    }

    static class DoResponseRunnable implements Runnable {
        private final String url;
        private final WeakReference<WebView> wrWebView;

        DoResponseRunnable(WebView webView, String str) {
            this.wrWebView = new WeakReference<>(webView);
            this.url = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            LogUtil.a(CaptureUtils.TAG, "DoResponseRunnable: loadUrl");
            WebView webView = this.wrWebView.get();
            if (webView == null) {
                LogUtil.a(CaptureUtils.TAG, "DoResponseRunnable: webView is null");
                return;
            }
            String str = this.url;
            webView.loadUrl(str);
            WebViewInstrumentation.loadUrl(webView, str);
        }
    }
}
