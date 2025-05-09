package com.huawei.hms.mlsdk.asr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.hms.mlsdk.asr.engine.AsrConstants;
import com.huawei.hms.mlsdk.asr.engine.AsrEngine;
import com.huawei.hms.mlsdk.asr.engine.AsrEngineConfig;
import com.huawei.hms.mlsdk.asr.engine.AsrError;
import com.huawei.hms.mlsdk.asr.engine.AsrResult;
import com.huawei.hms.mlsdk.asr.engine.cloud.vo.AsrLanguageResponse;
import com.huawei.hms.mlsdk.asr.engine.cloud.vo.LanguageListInfo;
import com.huawei.hms.mlsdk.asr.engine.utils.HttpUtils;
import com.huawei.hms.mlsdk.asr.engine.utils.SmartLogger;
import com.huawei.hms.network.httpclient.Callback;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.hms.network.httpclient.Submit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public class MLAsrRecognizer {
    public static final String RESULTS_CODE = "results_code";
    public static final String RESULTS_RECOGNIZED = "results_recognized";
    public static final String RESULTS_RECOGNIZING = "results_recognizing";
    private static final String TAG = "MLAsrRecognizer";
    private AsrEngine mAsrEngine;
    private MLAsrListener mAsrListener;
    private MLAsrWriteAudioListener mAsrWriteAudioListener;
    private AsrEngineConfig mConfig;
    private final Context mContext;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private volatile boolean mHasStartedRecognized = false;
    private LanguageCallback mLanguageCallback;

    public interface LanguageCallback {
        void onError(int i, String str);

        void onResult(List<String> list);
    }

    /* loaded from: classes9.dex */
    class a implements AsrEngine.Callback {

        /* renamed from: a, reason: collision with root package name */
        boolean f5048a = false;
        private Handler b = new Handler(Looper.getMainLooper());
        private Executor c = new ExecutorC0134a();

        /* renamed from: com.huawei.hms.mlsdk.asr.MLAsrRecognizer$a$a, reason: collision with other inner class name */
        class ExecutorC0134a implements Executor {
            ExecutorC0134a() {
            }

            @Override // java.util.concurrent.Executor
            public void execute(Runnable runnable) {
                a.this.b.post(runnable);
            }
        }

        class b implements Runnable {

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ byte[] f5050a;
            final /* synthetic */ double b;

            b(byte[] bArr, double d) {
                this.f5050a = bArr;
                this.b = d;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (MLAsrRecognizer.this.mAsrListener != null) {
                    MLAsrRecognizer.this.mAsrListener.onVoiceDataReceived(this.f5050a, (float) this.b, new Bundle());
                }
            }
        }

        a() {
        }

        @Override // com.huawei.hms.mlsdk.asr.engine.AsrEngine.Callback
        public void onEnergy(double d, byte[] bArr, Bundle bundle) {
            this.c.execute(new b(bArr, d));
        }

        @Override // com.huawei.hms.mlsdk.asr.engine.AsrEngine.Callback
        public void onError(AsrError asrError, String str) {
            StringBuilder sb = new StringBuilder(",errorMessage: ");
            sb.append(asrError.getMessage() == null ? "no error message" : asrError.getMessage());
            String sb2 = sb.toString();
            int translateErrorCode = MLAsrRecognizer.this.translateErrorCode(asrError.getErrorCode());
            Integer subErrorCode = asrError.getSubErrorCode();
            if (MLAsrRecognizer.this.mAsrListener != null) {
                if (asrError.getMessage().equals("No network")) {
                    MLAsrRecognizer.this.mAsrListener.onState(7, new Bundle());
                } else {
                    MLAsrRecognizer.this.mAsrListener.onState(translateErrorCode, new Bundle());
                }
            }
            if (subErrorCode != null) {
                sb2 = ",subError code: " + subErrorCode + sb2;
            }
            if (MLAsrRecognizer.this.mAsrListener != null) {
                MLAsrRecognizer.this.mAsrListener.onError(translateErrorCode, sb2);
            }
            SmartLogger.i(MLAsrRecognizer.TAG, "onError code:" + asrError.getErrorCode() + sb2);
            MLAsrRecognizer.this.destroy();
        }

        @Override // com.huawei.hms.mlsdk.asr.engine.AsrEngine.Callback
        public void onResult(AsrResult asrResult) {
            if (MLAsrRecognizer.this.mAsrListener == null) {
                MLAsrRecognizer.this.destroy();
                return;
            }
            SmartLogger.i(MLAsrRecognizer.TAG, "onResult retCode: " + asrResult.getRetCode() + ", isFinal: " + asrResult.isFinal() + ", feature: " + MLAsrRecognizer.this.mConfig.getFeature());
            Bundle bundle = new Bundle();
            int retCode = asrResult.getRetCode();
            if (retCode == 3) {
                MLAsrRecognizer.this.mAsrListener.onState(3, new Bundle());
                this.f5048a = true;
                bundle.putString(MLAsrRecognizer.RESULTS_RECOGNIZED, "");
                MLAsrRecognizer.this.mAsrListener.onResults(bundle);
                MLAsrRecognizer.this.destroy();
                return;
            }
            if (retCode != 4) {
                if (retCode == 5) {
                    if (MLAsrRecognizer.this.mConfig.getFeature() == 11) {
                        if (asrResult.getText() == null) {
                            SmartLogger.d(MLAsrRecognizer.TAG, "RECOGNIZING result.getText() is null");
                            return;
                        } else {
                            bundle.putString(MLAsrRecognizer.RESULTS_RECOGNIZING, asrResult.getText());
                            MLAsrRecognizer.this.mAsrListener.onRecognizingResults(bundle);
                            return;
                        }
                    }
                    return;
                }
                if (retCode != 8) {
                    MLAsrRecognizer.this.destroy();
                    SmartLogger.w(MLAsrRecognizer.TAG, "!!! other code -->" + asrResult.getRetCode());
                    return;
                }
            }
            if (this.f5048a) {
                SmartLogger.i(MLAsrRecognizer.TAG, "NO_SOUND_TIMES_EXCEED has detected before");
                return;
            }
            bundle.putInt(MLAsrRecognizer.RESULTS_CODE, 8);
            if (asrResult.getText() != null && asrResult.isFinal()) {
                bundle.putString(MLAsrRecognizer.RESULTS_RECOGNIZED, asrResult.getText());
                MLAsrRecognizer.this.mAsrListener.onResults(bundle);
            } else if (asrResult.getText() == null) {
                bundle.putString(MLAsrRecognizer.RESULTS_RECOGNIZED, "");
                SmartLogger.i(MLAsrRecognizer.TAG, " onResults text is null");
                MLAsrRecognizer.this.mAsrListener.onResults(bundle);
            } else {
                bundle.putString(MLAsrRecognizer.RESULTS_RECOGNIZED, asrResult.getText());
                MLAsrRecognizer.this.mAsrListener.onResults(bundle);
            }
            MLAsrRecognizer.this.destroy();
        }

        @Override // com.huawei.hms.mlsdk.asr.engine.AsrEngine.Callback
        public void onState(int i) {
            SmartLogger.i(MLAsrRecognizer.TAG, "status code：" + i);
            if (MLAsrRecognizer.this.mAsrListener == null) {
                return;
            }
            if (i == 1) {
                SmartLogger.d(MLAsrRecognizer.TAG, "please start speeching");
                MLAsrRecognizer.this.mAsrListener.onStartListening();
                MLAsrRecognizer.this.mAsrListener.onState(i, new Bundle());
            } else if (i != 10) {
                MLAsrRecognizer.this.mAsrListener.onState(i, new Bundle());
            } else {
                SmartLogger.d(MLAsrRecognizer.TAG, "user started to speech");
                MLAsrRecognizer.this.mAsrListener.onStartingOfSpeech();
            }
        }
    }

    class b extends Callback<ResponseBody> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ long f5051a;

        b(long j) {
            this.f5051a = j;
        }

        @Override // com.huawei.hms.network.httpclient.Callback
        public void onFailure(Submit<ResponseBody> submit, Throwable th) {
            SmartLogger.e(MLAsrRecognizer.TAG, "language list unavailable:" + th.getMessage());
            MLAsrRecognizer.this.onError(MLAsrConstants.ERR_NO_NETWORK, "language list unavailable:" + th.getMessage());
        }

        @Override // com.huawei.hms.network.httpclient.Callback
        public void onResponse(Submit<ResponseBody> submit, Response<ResponseBody> response) throws IOException {
            if (response == null || response.getBody() == null) {
                MLAsrRecognizer.this.onError(MLAsrConstants.ERR_NO_NETWORK, "language list unavailable,response or response body is null");
                return;
            }
            String str = new String(response.getBody().bytes());
            AsrLanguageResponse asrLanguageResponse = (AsrLanguageResponse) new Gson().fromJson(str, AsrLanguageResponse.class);
            if (asrLanguageResponse == null) {
                MLAsrRecognizer.this.onError(MLAsrConstants.ERR_NO_NETWORK, "Abnormal network connection.");
                return;
            }
            String retCode = asrLanguageResponse.getRetCode();
            if (TextUtils.isEmpty(retCode)) {
                SmartLogger.e(MLAsrRecognizer.TAG, "connect failed:" + asrLanguageResponse.getRetMsg());
                MLAsrRecognizer.this.onError(MLAsrConstants.ERR_NO_NETWORK, "Abnormal network connection.");
                return;
            }
            if (!retCode.equals("0")) {
                if (retCode.equals("001001")) {
                    SmartLogger.e(MLAsrRecognizer.TAG, "Abnormal network connection.");
                    MLAsrRecognizer.this.onError(MLAsrConstants.ERR_INVALIDATE_TOKEN, asrLanguageResponse.getRetMsg());
                    return;
                } else {
                    SmartLogger.e(MLAsrRecognizer.TAG, "connect failed:" + asrLanguageResponse.getRetMsg());
                    MLAsrRecognizer.this.onError(MLAsrConstants.ERR_NO_NETWORK, asrLanguageResponse.getRetMsg());
                    return;
                }
            }
            SmartLogger.d(MLAsrRecognizer.TAG, "language list response=".concat(str));
            if (!HttpUtils.getInstance().isLegalJson(str)) {
                SmartLogger.e(MLAsrRecognizer.TAG, "language list parse error.[" + str + "]");
                MLAsrRecognizer.this.onError(MLAsrConstants.ERR_SERVICE_UNAVAILABLE, "language list parse error.[" + str + "]");
                return;
            }
            try {
                LanguageListInfo result = asrLanguageResponse.getResult();
                if (result != null && result.getLanguages() != null && result.getLanguages().size() != 0) {
                    MLAsrRecognizer.this.onResult(result.getLanguages());
                    SmartLogger.d(MLAsrRecognizer.TAG, "request support languages time is : " + (System.currentTimeMillis() - this.f5051a));
                }
                SmartLogger.e(MLAsrRecognizer.TAG, "Response json not contain languages");
                MLAsrRecognizer.this.onError(MLAsrConstants.ERR_SERVICE_UNAVAILABLE, "Response json not contain languages");
            } catch (Exception e) {
                SmartLogger.e(MLAsrRecognizer.TAG, "Exception", e);
                MLAsrRecognizer.this.onError(MLAsrConstants.ERR_SERVICE_UNAVAILABLE, "Exception" + e.getMessage());
            }
        }
    }

    class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f5052a;
        final /* synthetic */ String b;

        c(int i, String str) {
            this.f5052a = i;
            this.b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MLAsrRecognizer.this.mLanguageCallback != null) {
                MLAsrRecognizer.this.mLanguageCallback.onError(this.f5052a, this.b);
            }
        }
    }

    class d implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ List f5053a;

        d(List list) {
            this.f5053a = list;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MLAsrRecognizer.this.mLanguageCallback != null) {
                MLAsrRecognizer.this.mLanguageCallback.onResult(this.f5053a);
            }
        }
    }

    class e implements AsrEngine.Callback {
        e() {
        }

        @Override // com.huawei.hms.mlsdk.asr.engine.AsrEngine.Callback
        public void onEnergy(double d, byte[] bArr, Bundle bundle) {
        }

        @Override // com.huawei.hms.mlsdk.asr.engine.AsrEngine.Callback
        public void onError(AsrError asrError, String str) {
            StringBuilder sb = new StringBuilder(",errorMessage: ");
            sb.append(asrError.getMessage() == null ? "no error message" : asrError.getMessage());
            String sb2 = sb.toString();
            int translateErrorCode = MLAsrRecognizer.this.translateErrorCode(asrError.getErrorCode());
            Integer subErrorCode = asrError.getSubErrorCode();
            if (subErrorCode != null) {
                sb2 = ",subError code: " + subErrorCode + sb2;
            }
            if (MLAsrRecognizer.this.mAsrWriteAudioListener != null) {
                MLAsrRecognizer.this.mAsrWriteAudioListener.onError(translateErrorCode, sb2);
            }
            SmartLogger.i(MLAsrRecognizer.TAG, "onError code:" + asrError.getErrorCode() + sb2);
            MLAsrRecognizer.this.destroy();
        }

        @Override // com.huawei.hms.mlsdk.asr.engine.AsrEngine.Callback
        public void onResult(AsrResult asrResult) {
            if (MLAsrRecognizer.this.mAsrWriteAudioListener == null) {
                MLAsrRecognizer.this.destroy();
                return;
            }
            Bundle bundle = new Bundle();
            int retCode = asrResult.getRetCode();
            if (retCode == 5) {
                if (MLAsrRecognizer.this.mConfig.getFeature() == 11) {
                    if (asrResult.getText() == null) {
                        SmartLogger.d(MLAsrRecognizer.TAG, "RECOGNIZING result.getText() is null");
                        return;
                    } else {
                        bundle.putString(MLAsrRecognizer.RESULTS_RECOGNIZED, asrResult.getText());
                        MLAsrRecognizer.this.mAsrWriteAudioListener.onResults(bundle);
                        return;
                    }
                }
                return;
            }
            if (retCode != 8) {
                MLAsrRecognizer.this.destroy();
                SmartLogger.w(MLAsrRecognizer.TAG, "!!! other code -->" + asrResult.getRetCode());
                return;
            }
            if (asrResult.getText() != null && asrResult.isFinal()) {
                bundle.putString(MLAsrRecognizer.RESULTS_RECOGNIZED, asrResult.getText());
                MLAsrRecognizer.this.mAsrWriteAudioListener.onResults(bundle);
            } else if (asrResult.getText() == null) {
                bundle.putString(MLAsrRecognizer.RESULTS_RECOGNIZED, "");
                SmartLogger.i(MLAsrRecognizer.TAG, "onResults text is null");
                MLAsrRecognizer.this.mAsrWriteAudioListener.onResults(bundle);
            }
            MLAsrRecognizer.this.destroy();
        }

        @Override // com.huawei.hms.mlsdk.asr.engine.AsrEngine.Callback
        public void onState(int i) {
            SmartLogger.i(MLAsrRecognizer.TAG, "status code：" + i);
            if (MLAsrRecognizer.this.mAsrWriteAudioListener != null && i == 10) {
                SmartLogger.d(MLAsrRecognizer.TAG, "please start speeching");
                MLAsrRecognizer.this.mAsrWriteAudioListener.onStartListening();
            }
        }
    }

    private MLAsrRecognizer(Context context) {
        this.mContext = context;
        SmartLogger.d(TAG, "Context is not null");
        SmartLogger.d(TAG, "mContext:" + context.toString());
    }

    public static MLAsrRecognizer createAsrRecognizer(Context context) {
        if (context != null) {
            return new MLAsrRecognizer(context);
        }
        throw new IllegalArgumentException("Context cannot be null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onError(int i, String str) {
        this.mHandler.post(new c(i, str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onResult(List<String> list) {
        this.mHandler.post(new d(list));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int translateErrorCode(int i) {
        if (i != 6) {
            return i != 7 ? i != 40 ? i != 44 ? i : MLAsrConstants.ERR_INVALIDATE_TOKEN : MLAsrConstants.ERR_SERVICE_UNAVAILABLE : MLAsrConstants.ERR_NO_NETWORK;
        }
        return 6;
    }

    public void destroy() {
        AsrEngine asrEngine = this.mAsrEngine;
        if (asrEngine != null) {
            asrEngine.destroy();
            this.mAsrEngine = null;
        }
        this.mHasStartedRecognized = false;
    }

    public void getLanguages(LanguageCallback languageCallback) {
        this.mLanguageCallback = languageCallback;
        new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        HttpUtils.getInstance().requestSupportLanguages(HttpUtils.getInstance().getOkHttpClient(), AsrConstants.AsrGrs.ASR_SHORT_QUERY_LANGUAGE_URL, new b(currentTimeMillis));
    }

    public void receiveVoiceStream(byte[] bArr, boolean z) {
        AsrEngine asrEngine = this.mAsrEngine;
        if (asrEngine == null || bArr == null) {
            return;
        }
        asrEngine.setInput(bArr, z);
    }

    public void setAsrListener(MLAsrListener mLAsrListener) {
        this.mAsrListener = mLAsrListener;
    }

    public void setAsrWriteAudioListener(MLAsrWriteAudioListener mLAsrWriteAudioListener) {
        this.mAsrWriteAudioListener = mLAsrWriteAudioListener;
    }

    public void startRecognizing(Intent intent) {
        Bundle bundle;
        if (intent == null) {
            throw new IllegalArgumentException("intent must not be null");
        }
        if (this.mHasStartedRecognized) {
            SmartLogger.w(TAG, "AsrRecognizer is processing now");
            return;
        }
        this.mHasStartedRecognized = true;
        SmartLogger.i(TAG, "start Recognizing");
        try {
            bundle = intent.getExtras();
        } catch (RuntimeException e2) {
            SmartLogger.e(TAG, "RuntimeException e = " + e2.getMessage());
            bundle = null;
        }
        AsrEngineConfig load = new AsrEngineConfig().load(bundle);
        this.mConfig = load;
        AsrEngine asrEngine = new AsrEngine(load, new a());
        this.mAsrEngine = asrEngine;
        try {
            asrEngine.create();
        } catch (Exception e3) {
            SmartLogger.e(TAG, "AsrEngine catch Exception: " + e3.getMessage());
            MLAsrListener mLAsrListener = this.mAsrListener;
            if (mLAsrListener != null) {
                mLAsrListener.onError(40, e3.getMessage());
            }
        }
    }

    public void startWriteAudio(Intent intent) {
        Bundle bundle;
        if (intent == null) {
            throw new IllegalArgumentException("intent must not be null");
        }
        if (this.mHasStartedRecognized) {
            SmartLogger.w(TAG, "AsrRecognizer is processing now");
            return;
        }
        this.mHasStartedRecognized = true;
        SmartLogger.i(TAG, "start Recognizing");
        try {
            bundle = intent.getExtras();
        } catch (RuntimeException e2) {
            SmartLogger.e(TAG, "RuntimeException e = " + e2.getMessage());
            bundle = null;
        }
        AsrEngineConfig load = new AsrEngineConfig().load(bundle);
        this.mConfig = load;
        AsrEngine asrEngine = new AsrEngine(load, new e());
        this.mAsrEngine = asrEngine;
        try {
            asrEngine.createWriteAudio();
        } catch (Exception e3) {
            SmartLogger.e(TAG, "AsrEngine catch Exception: " + e3.getMessage());
            MLAsrWriteAudioListener mLAsrWriteAudioListener = this.mAsrWriteAudioListener;
            if (mLAsrWriteAudioListener != null) {
                mLAsrWriteAudioListener.onError(40, e3.getMessage());
            }
        }
    }
}
