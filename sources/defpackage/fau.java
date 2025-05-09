package defpackage;

import android.graphics.Bitmap;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.health.recognizekit.bean.FoodImageInfo;
import com.huawei.health.recognizekit.impl.RecognizeHelper;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.Tasks;
import com.huawei.hms.network.embedded.j2;
import com.huawei.hms.network.embedded.w9;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import health.compact.a.Base64;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LogUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class fau implements RecognizeHelper {
    private static String d;

    /* renamed from: a, reason: collision with root package name */
    private RecognizeHelper.RecognizeListener f12412a;
    private String c;
    private int b = 5000;
    private boolean e = false;

    public fau(RecognizeHelper.RecognizeListener recognizeListener) {
        this.f12412a = recognizeListener;
        e();
    }

    @Override // com.huawei.health.recognizekit.impl.RecognizeHelper
    public void release() {
        this.f12412a = null;
    }

    @Override // com.huawei.health.recognizekit.impl.RecognizeHelper
    public void recognize(final Bitmap bitmap) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: far
            @Override // java.lang.Runnable
            public final void run() {
                fau.this.auN_(bitmap);
            }
        });
    }

    /* synthetic */ void auN_(Bitmap bitmap) {
        this.e = false;
        final String d2 = d(new FoodImageInfo(auM_(bitmap, 90)));
        Task callInBackground = Tasks.callInBackground(new Callable() { // from class: fav
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return fau.this.b(d2);
            }
        });
        callInBackground.addOnSuccessListener(new OnSuccessListener() { // from class: fay
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                fau.this.c((String) obj);
            }
        });
        callInBackground.addOnFailureListener(new OnFailureListener() { // from class: fax
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                fau.this.b(exc);
            }
        });
        try {
            Tasks.await(callInBackground, this.b, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            LogUtil.e("RecognizeCloudHelper", "exception occurs when recognizing in cloud, e: " + e);
            RecognizeHelper.RecognizeListener recognizeListener = this.f12412a;
            if (recognizeListener == null) {
                LogUtil.c("RecognizeCloudHelper", "recognizeListener is null, no need to callback");
                return;
            }
            this.e = true;
            this.b = 10000;
            recognizeListener.onRecognized("recognize_failed");
        }
    }

    /* synthetic */ String b(String str) throws Exception {
        return (String) lqi.d().d(this.c + "/dataRecommend/app/foodRecognize", a(), str, String.class);
    }

    /* synthetic */ void c(String str) {
        if (this.f12412a == null) {
            LogUtil.c("RecognizeCloudHelper", "recognizeListener is null, no need to callback, result: " + str);
        } else if (this.e) {
            LogUtil.c("RecognizeCloudHelper", "response too late, already timeout");
        } else if (e(str)) {
            this.f12412a.onRecognized(str);
        } else {
            this.f12412a.onRecognized("recognize_failed");
        }
    }

    /* synthetic */ void b(Exception exc) {
        LogUtil.e("RecognizeCloudHelper", "exception occurs when recognizing in cloud, e: " + exc);
        RecognizeHelper.RecognizeListener recognizeListener = this.f12412a;
        if (recognizeListener == null) {
            LogUtil.c("RecognizeCloudHelper", "recognizeListener is null, no need to callback");
        } else if (this.e) {
            LogUtil.c("RecognizeCloudHelper", "response too late, already timeout");
        } else {
            recognizeListener.onRecognized("recognize_failed");
        }
    }

    public static String b() {
        return d;
    }

    private void e() {
        GRSManager.a(BaseApplication.getContext()).e("healthCloudUrl", new GrsQueryCallback() { // from class: fau.5
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                LogUtil.c("RecognizeCloudHelper", "initHostUrl success");
                fau.this.c = str;
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.a("RecognizeCloudHelper", "initHostUrl failed, resultCode: " + i);
            }
        });
    }

    private Map<String, String> a() {
        HashMap hashMap = new HashMap();
        hashMap.put("x-version", CommonUtil.c(com.huawei.haf.application.BaseApplication.e()));
        hashMap.put(j2.v, "gzip, deflate");
        hashMap.put("Connection", w9.j);
        hashMap.put("Content-Encoding", Constants.GZIP);
        hashMap.put("x-huid", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
        hashMap.put("Content-Type", HealthEngineRequestManager.CONTENT_TYPE);
        return hashMap;
    }

    private String d(FoodImageInfo foodImageInfo) {
        HashMap hashMap = new HashMap();
        hashMap.put("siteID", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009));
        hashMap.put("token", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1008));
        hashMap.put("tokenType", Integer.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        hashMap.put("appId", "com.huawei.health");
        hashMap.put("appType", 1);
        hashMap.put("ts", Long.valueOf(System.currentTimeMillis()));
        hashMap.put("timeZone", ggl.b());
        hashMap.put("countryCode", GRSManager.a(BaseApplication.getContext()).getCommonCountryCode());
        ArrayList arrayList = new ArrayList();
        arrayList.add(foodImageInfo);
        hashMap.put("imageInfos", arrayList);
        return lql.b(hashMap);
    }

    private static String auM_(Bitmap bitmap, int i) {
        String str = null;
        if (bitmap == null) {
            LogUtil.e("RecognizeCloudHelper", "bitmapToBase64Encode failed, bitmap is null");
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        try {
            try {
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
                str = Base64.a(byteArrayOutputStream.toByteArray());
                if (str != null && str.length() > 2097152) {
                    StringBuilder sb = new StringBuilder("img is too large, change quality and recur in: ");
                    int i2 = i - 10;
                    sb.append(i2);
                    LogUtil.a("RecognizeCloudHelper", sb.toString());
                    str = auM_(bitmap, i2);
                }
            } catch (IOException e) {
                LogUtil.e("RecognizeCloudHelper", "bitmapToBase64Encode failed, e: ", ExceptionUtils.d(e));
                try {
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                } catch (IOException e2) {
                    LogUtil.e("RecognizeCloudHelper", "bitmapToBase64Encode failed, e: ", ExceptionUtils.d(e2));
                }
            }
            d = str;
            return str;
        } finally {
            try {
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
            } catch (IOException e3) {
                LogUtil.e("RecognizeCloudHelper", "bitmapToBase64Encode failed, e: ", ExceptionUtils.d(e3));
            }
        }
    }

    private boolean e(String str) {
        int intValue;
        if (str == null || str.isEmpty()) {
            LogUtil.a("RecognizeCloudHelper", "result is null");
            return false;
        }
        try {
            intValue = ((Integer) new JSONObject(str).get("resultCode")).intValue();
            LogUtil.c("RecognizeCloudHelper", "result from cloud, resultCode: " + intValue);
        } catch (JSONException unused) {
            LogUtil.e("RecognizeCloudHelper", "from json failed, s: " + str);
        }
        return intValue == 0;
    }
}
