package com.huawei.hms.mlsdk.asr.engine;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hms.mlsdk.asr.engine.b;
import com.huawei.hms.mlsdk.asr.engine.c;
import com.huawei.hms.mlsdk.asr.engine.d;
import com.huawei.hms.mlsdk.asr.engine.utils.SmartLogger;
import com.huawei.hms.mlsdk.common.MLApplication;
import com.huawei.operation.OpAnalyticsConstants;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public class AsrEngine {
    private static final String TAG = "AsrEngine";
    private Callback callback;
    private AsrEngineConfig engineConfig;
    private com.huawei.hms.mlsdk.asr.engine.b mAsrProcessor;
    private com.huawei.hms.mlsdk.asr.engine.c mAsrRecorder;
    private String mAsrResult;
    private com.huawei.hms.mlsdk.asr.engine.d mAsrVadDetector;
    private int recognizerType;
    private Long recordStartTime = null;
    private Long processStartTime = null;
    private volatile boolean mIsDestroying = false;
    private String mTaskId = null;
    private Runnable mDestroy = new a();
    private Handler posterHandler = new Handler(Looper.getMainLooper());
    private Executor posterExecutor = new i();

    public interface Callback {
        void onEnergy(double d, byte[] bArr, Bundle bundle);

        void onError(AsrError asrError, String str);

        void onResult(AsrResult asrResult);

        void onState(int i);
    }

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AsrEngine.this.runDestroy();
        }
    }

    class c implements c.InterfaceC0137c {
        c() {
        }

        public void a(AsrError asrError) {
            AsrEngine.this.posterExecutor.execute(new j(AsrEngine.this.callback, asrError, AsrEngine.this.mAsrResult));
            SmartLogger.d(AsrEngine.TAG, "onError(AsrError error)");
        }
    }

    class f implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Bundle f5063a;

        f(Bundle bundle) {
            this.f5063a = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            AsrEngine.this.callback.onError(new AsrError(40, "not HuaWei phone and not Emui", this.f5063a), null);
        }
    }

    class g implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f5064a;
        final /* synthetic */ Bundle b;

        g(String str, Bundle bundle) {
            this.f5064a = str;
            this.b = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            AsrEngine.this.callback.onError(new AsrError(40, this.f5064a, this.b), null);
        }
    }

    class h implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f5065a;
        final /* synthetic */ Bundle b;

        h(String str, Bundle bundle) {
            this.f5065a = str;
            this.b = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            AsrEngine.this.callback.onError(new AsrError(40, this.f5065a, this.b), null);
        }
    }

    class i implements Executor {
        i() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            AsrEngine.this.posterHandler.post(runnable);
        }
    }

    public AsrEngine(AsrEngineConfig asrEngineConfig, Callback callback) {
        this.engineConfig = asrEngineConfig;
        this.callback = callback;
    }

    private String checkParameters() {
        int feature;
        if (isRecognizerLong() || (feature = this.engineConfig.getFeature()) == 12 || feature == 11) {
            return null;
        }
        return "Invalid feature options";
    }

    private String checkPermissions() {
        String[] strArr = {"android.permission.INTERNET", "android.permission.ACCESS_WIFI_STATE", "android.permission.ACCESS_NETWORK_STATE", "android.permission.RECORD_AUDIO"};
        Context appContext = MLApplication.getInstance().getAppContext();
        PackageManager packageManager = appContext.getPackageManager();
        String packageName = appContext.getPackageName();
        for (int i2 = 0; i2 < 4; i2++) {
            String str = strArr[i2];
            if (packageManager.checkPermission(str, packageName) == -1) {
                return com.huawei.hms.mlsdk.asr.o.a.a("Permission missing: ", str);
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean isEMUIAndHuaWeiPhone() {
        /*
            r7 = this;
            java.lang.String r0 = "EmuiUtil"
            boolean r1 = com.huawei.hms.mlsdk.asr.o.g.a()
            r2 = 1
            if (r1 != 0) goto L8d
            r1 = 0
            java.lang.String r3 = "android.os.SystemProperties"
            java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch: java.lang.reflect.InvocationTargetException -> L29 java.lang.IllegalAccessException -> L2f java.lang.NoSuchMethodException -> L35 java.lang.ClassNotFoundException -> L3b
            java.lang.Class[] r4 = new java.lang.Class[r2]     // Catch: java.lang.reflect.InvocationTargetException -> L29 java.lang.IllegalAccessException -> L2f java.lang.NoSuchMethodException -> L35 java.lang.ClassNotFoundException -> L3b
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            r4[r1] = r5     // Catch: java.lang.reflect.InvocationTargetException -> L29 java.lang.IllegalAccessException -> L2f java.lang.NoSuchMethodException -> L35 java.lang.ClassNotFoundException -> L3b
            java.lang.String r5 = "get"
            java.lang.reflect.Method r4 = r3.getDeclaredMethod(r5, r4)     // Catch: java.lang.reflect.InvocationTargetException -> L29 java.lang.IllegalAccessException -> L2f java.lang.NoSuchMethodException -> L35 java.lang.ClassNotFoundException -> L3b
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch: java.lang.reflect.InvocationTargetException -> L29 java.lang.IllegalAccessException -> L2f java.lang.NoSuchMethodException -> L35 java.lang.ClassNotFoundException -> L3b
            java.lang.String r6 = "ro.product.manufacturer"
            r5[r1] = r6     // Catch: java.lang.reflect.InvocationTargetException -> L29 java.lang.IllegalAccessException -> L2f java.lang.NoSuchMethodException -> L35 java.lang.ClassNotFoundException -> L3b
            java.lang.Object r3 = r4.invoke(r3, r5)     // Catch: java.lang.reflect.InvocationTargetException -> L29 java.lang.IllegalAccessException -> L2f java.lang.NoSuchMethodException -> L35 java.lang.ClassNotFoundException -> L3b
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.reflect.InvocationTargetException -> L29 java.lang.IllegalAccessException -> L2f java.lang.NoSuchMethodException -> L35 java.lang.ClassNotFoundException -> L3b
            goto L42
        L29:
            java.lang.String r3 = "InvocationTargetException"
            com.huawei.hms.mlsdk.asr.engine.utils.SmartLogger.e(r0, r3)
            goto L40
        L2f:
            java.lang.String r3 = "IllegalAccessException"
            com.huawei.hms.mlsdk.asr.engine.utils.SmartLogger.e(r0, r3)
            goto L40
        L35:
            java.lang.String r3 = "NoSuchMethodException"
            com.huawei.hms.mlsdk.asr.engine.utils.SmartLogger.e(r0, r3)
            goto L40
        L3b:
            java.lang.String r3 = "ClassNotFoundException"
            com.huawei.hms.mlsdk.asr.engine.utils.SmartLogger.e(r0, r3)
        L40:
            java.lang.String r3 = ""
        L42:
            java.lang.String r0 = "HUAWEI"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L8d
            java.lang.String r0 = "HONOR"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L53
            goto L8d
        L53:
            com.huawei.hms.mlsdk.asr.engine.AsrEngineConfig r0 = r7.engineConfig
            java.lang.String r0 = r0.getLanguage()
            java.lang.String r3 = "zh"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L8d
            com.huawei.hms.mlsdk.asr.engine.AsrEngineConfig r0 = r7.engineConfig
            java.lang.String r0 = r0.getLanguage()
            java.lang.String r3 = "en-US"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L8d
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            java.lang.String r2 = "subErrorCode"
            r3 = 3016(0xbc8, float:4.226E-42)
            r0.putInt(r2, r3)
            java.util.concurrent.Executor r2 = r7.posterExecutor
            com.huawei.hms.mlsdk.asr.engine.AsrEngine$f r3 = new com.huawei.hms.mlsdk.asr.engine.AsrEngine$f
            r3.<init>(r0)
            r2.execute(r3)
            java.lang.String r0 = "AsrEngine"
            java.lang.String r2 = "not HuaWei phone and not Emui"
            com.huawei.hms.mlsdk.asr.engine.utils.SmartLogger.e(r0, r2)
            return r1
        L8d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.mlsdk.asr.engine.AsrEngine.isEMUIAndHuaWeiPhone():boolean");
    }

    private boolean legalityCheckPassed() {
        String checkPermissions = checkPermissions();
        if (checkPermissions != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("subErrorCode", 1004);
            this.posterExecutor.execute(new g(checkPermissions, bundle));
            return false;
        }
        String checkParameters = checkParameters();
        if (checkParameters == null) {
            return true;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putInt("subErrorCode", 3002);
        this.posterExecutor.execute(new h(checkParameters, bundle2));
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runDestroy() {
        com.huawei.hms.mlsdk.asr.engine.c cVar = this.mAsrRecorder;
        if (cVar != null) {
            cVar.a();
            this.mAsrRecorder = null;
        }
        com.huawei.hms.mlsdk.asr.engine.d dVar = this.mAsrVadDetector;
        if (dVar != null) {
            dVar.a();
            this.mAsrVadDetector = null;
        }
        com.huawei.hms.mlsdk.asr.engine.b bVar = this.mAsrProcessor;
        if (bVar != null) {
            bVar.a();
            this.mAsrProcessor = null;
        }
        this.mIsDestroying = false;
    }

    public AsrEngine create() {
        if (!isEMUIAndHuaWeiPhone() || !legalityCheckPassed()) {
            return this;
        }
        com.huawei.hms.mlsdk.asr.o.c.c().a(MLApplication.getInstance().getAppContext(), isRecognizerLong());
        String b2 = com.huawei.hms.mlsdk.asr.o.c.b();
        com.huawei.hms.mlsdk.asr.o.c.c().a(b2);
        SmartLogger.i(TAG, "TaskId: " + b2);
        String language = this.engineConfig.getLanguage();
        Bundle bundle = new Bundle();
        bundle.putString("applanguage", language);
        com.huawei.hms.mlsdk.asr.o.c.c().a(b2, 0, bundle);
        setTaskId(b2);
        com.huawei.hms.mlsdk.asr.engine.b bVar = new com.huawei.hms.mlsdk.asr.engine.b(this);
        this.mAsrProcessor = bVar;
        bVar.a(b2);
        this.mAsrRecorder = new com.huawei.hms.mlsdk.asr.engine.c();
        this.mAsrVadDetector = new com.huawei.hms.mlsdk.asr.engine.d(this);
        this.mAsrProcessor.a(new b(bundle));
        this.mAsrRecorder.a(new c());
        this.mAsrVadDetector.a(new d(bundle));
        if (this.mAsrProcessor.d()) {
            this.mAsrRecorder.b();
        }
        this.mAsrProcessor.f();
        return this;
    }

    public AsrEngine createWriteAudio() {
        if (!isEMUIAndHuaWeiPhone()) {
            return this;
        }
        com.huawei.hms.mlsdk.asr.o.c.c().a(MLApplication.getInstance().getAppContext(), isRecognizerLong());
        String b2 = com.huawei.hms.mlsdk.asr.o.c.b();
        com.huawei.hms.mlsdk.asr.o.c.c().a(b2);
        SmartLogger.i(TAG, "TaskId: " + b2);
        String language = this.engineConfig.getLanguage();
        Bundle bundle = new Bundle();
        bundle.putString("applanguage", language);
        com.huawei.hms.mlsdk.asr.o.c.c().a(b2, 0, bundle);
        setTaskId(b2);
        com.huawei.hms.mlsdk.asr.engine.b bVar = new com.huawei.hms.mlsdk.asr.engine.b(this);
        this.mAsrProcessor = bVar;
        bVar.a(b2);
        this.mAsrProcessor.a(new e(bundle));
        this.mAsrProcessor.f();
        return this;
    }

    public void destroy() {
        if (isRecognizerLong()) {
            com.huawei.hms.mlsdk.asr.o.c.c().f(getTaskId());
        }
        com.huawei.hms.mlsdk.asr.o.c.c().a();
        com.huawei.hms.mlsdk.asr.engine.c cVar = this.mAsrRecorder;
        if (cVar != null) {
            cVar.a();
            this.mAsrRecorder = null;
        }
        if (this.mIsDestroying) {
            SmartLogger.w(TAG, "AsrEngine is destroying");
        } else {
            this.mIsDestroying = true;
            new Thread(this.mDestroy).start();
        }
    }

    public String getAsrResult() {
        String str = this.mAsrResult;
        return str == null ? "" : str;
    }

    public AsrEngineConfig getEngineConfig() {
        return this.engineConfig;
    }

    public Long getProcessStartTime() {
        return this.processStartTime;
    }

    public int getRecognizerType() {
        return this.recognizerType;
    }

    public Long getRecordStartTime() {
        return this.recordStartTime;
    }

    public String getTaskId() {
        return this.mTaskId;
    }

    public boolean hasResult() {
        return !"".equals(getAsrResult());
    }

    public boolean isRecognizerLong() {
        return this.engineConfig.getRecognizerType() == 1;
    }

    public void setAsrResult(String str) {
        this.mAsrResult = str;
    }

    public void setInput(byte[] bArr, boolean z) {
        com.huawei.hms.mlsdk.asr.engine.b bVar = this.mAsrProcessor;
        if (bVar != null) {
            bVar.a(bArr, z);
        }
    }

    public void setRecognizerType(int i2) {
        this.recognizerType = i2;
    }

    public void setTaskId(String str) {
        this.mTaskId = str;
    }

    static final class j implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private Callback f5067a;
        private int b;
        private AsrResult c;
        private AsrError d;
        private String e;
        private int f = 1;

        public j(Callback callback, int i) {
            this.f5067a = callback;
            this.b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i = this.f;
            if (i == 1) {
                this.f5067a.onState(this.b);
                return;
            }
            if (i != 2) {
                this.f5067a.onError(this.d, this.e);
                return;
            }
            StringBuilder a2 = com.huawei.hms.mlsdk.asr.o.a.a("result.text is: onResult= ");
            a2.append(this.c.getText());
            a2.append("  ");
            a2.append(this.c.getRetCode());
            SmartLogger.d("ResultDeliveryRunnable", a2.toString());
            this.f5067a.onResult(this.c);
        }

        public j(Callback callback, AsrResult asrResult) {
            this.f5067a = callback;
            this.c = asrResult;
        }

        public j(Callback callback, AsrError asrError, String str) {
            this.f5067a = callback;
            this.d = asrError;
            this.e = str;
        }
    }

    class d implements d.a {

        /* renamed from: a, reason: collision with root package name */
        private boolean f5061a = false;
        final /* synthetic */ Bundle b;

        d(Bundle bundle) {
            this.b = bundle;
        }

        public void a(d.b bVar) {
            Bundle bundle = new Bundle();
            bundle.putString(MLAsrConstants.WAVE_PAINE_ENCODING, "pcm");
            bundle.putInt(MLAsrConstants.WAVE_PAINE_SAMPLE_RATE, 16000);
            bundle.putInt(MLAsrConstants.WAVE_PAINE_BIT_WIDTH, 16);
            bundle.putInt(MLAsrConstants.WAVE_PAINE_CHANNEL_COUNT, 1);
            AsrEngine.this.callback.onEnergy(bVar.b(), bVar.a(), bundle);
            SmartLogger.d(AsrEngine.TAG, "onResult(AsrVadDetector.Result result)");
            if (AsrEngine.this.mAsrProcessor == null || !this.f5061a) {
                return;
            }
            AsrEngine.this.mAsrProcessor.a(bVar.a());
        }

        public void b(long j) {
            AsrEngine.this.posterExecutor.execute(new j(AsrEngine.this.callback, 2));
            SmartLogger.d(AsrEngine.TAG, "onNoSound(long time)");
        }

        public void c(long j) {
            AsrEngine.this.posterExecutor.execute(new j(AsrEngine.this.callback, new AsrResult(3)));
            SmartLogger.d(AsrEngine.TAG, "onNoSoundTimesExceed(long time)");
            if (AsrEngine.this.mAsrProcessor != null) {
                SmartLogger.i(AsrEngine.TAG, "onNoSoundTimesExceed and send finish");
                AsrEngine.this.mAsrProcessor.e();
            }
        }

        public void d(long j) {
            SmartLogger.i(AsrEngine.TAG, "onVoiceEnd");
            this.b.putLong("speechEndTime", System.currentTimeMillis());
            SmartLogger.i(AsrEngine.TAG, "onVoiceEnd speechEndTime: " + System.currentTimeMillis());
            com.huawei.hms.mlsdk.asr.o.c.c().a(AsrEngine.this.getTaskId(), 2, this.b);
            if (AsrEngine.this.mAsrProcessor != null) {
                AsrEngine.this.mAsrRecorder.a();
                AsrEngine.this.mAsrProcessor.b();
            }
        }

        public void e(long j) {
            long currentTimeMillis = System.currentTimeMillis();
            this.b.putLong("speechStartTime", currentTimeMillis);
            SmartLogger.i(AsrEngine.TAG, "speechStartTime: " + currentTimeMillis);
            com.huawei.hms.mlsdk.asr.o.c.c().a(AsrEngine.this.getTaskId(), 1, this.b);
            this.f5061a = true;
            AsrEngine.this.posterExecutor.execute(new j(AsrEngine.this.callback, 10));
            SmartLogger.d(AsrEngine.TAG, "onVoiceStart(long time)");
            SmartLogger.i(AsrEngine.TAG, "start send audio to cloud at " + j);
        }

        public void a(long j) {
            AsrEngine.this.posterExecutor.execute(new j(AsrEngine.this.callback, new AsrResult(4, AsrEngine.this.mAsrResult)));
            SmartLogger.d(AsrEngine.TAG, "onMaxDurationExceed(long time)");
        }

        public void a(AsrError asrError) {
            AsrEngine.this.posterExecutor.execute(new j(AsrEngine.this.callback, asrError, AsrEngine.this.mAsrResult));
            SmartLogger.d(AsrEngine.TAG, "onError(AsrError error)");
        }
    }

    class b implements b.e {

        /* renamed from: a, reason: collision with root package name */
        boolean f5059a = false;
        final /* synthetic */ Bundle b;

        b(Bundle bundle) {
            this.b = bundle;
        }

        @Override // com.huawei.hms.mlsdk.asr.engine.b.e
        public void a(AsrError asrError) {
            int errorCode = asrError.getErrorCode();
            if (AsrEngine.this.mAsrResult != null) {
                int length = AsrEngine.this.mAsrResult.length();
                SmartLogger.i(AsrEngine.TAG, "onError textLenth :" + length);
                this.b.putInt(OpAnalyticsConstants.NOTIFICATION_TEXT_LENGTH, length);
                com.huawei.hms.mlsdk.asr.o.c.c().a(AsrEngine.this.getTaskId(), 7, this.b);
            }
            this.b.putInt("result", errorCode);
            com.huawei.hms.mlsdk.asr.o.c.c().a(AsrEngine.this.getTaskId(), 9, this.b);
            this.b.putString("errMsg", asrError.toString());
            com.huawei.hms.mlsdk.asr.o.c.c().a(AsrEngine.this.getTaskId(), 10, this.b);
            com.huawei.hms.mlsdk.asr.o.c.c().f(AsrEngine.this.getTaskId());
            AsrEngine.this.posterExecutor.execute(new j(AsrEngine.this.callback, asrError, AsrEngine.this.mAsrResult));
            SmartLogger.d(AsrEngine.TAG, "onError(AsrError error)");
        }

        @Override // com.huawei.hms.mlsdk.asr.engine.b.e
        public void b(long j) {
            AsrEngine.this.processStartTime = Long.valueOf(j);
        }

        @Override // com.huawei.hms.mlsdk.asr.engine.b.e
        public void onResult(AsrResult asrResult) {
            String text = asrResult.getText();
            if (text != null && !"".equals(text)) {
                AsrEngine.this.mAsrResult = text;
            }
            if (asrResult.getRetCode() != 5 && !this.f5059a) {
                this.f5059a = true;
                if (com.huawei.hms.mlsdk.asr.o.c.c().b(AsrEngine.this.getTaskId()) == 0) {
                    long c = com.huawei.hms.mlsdk.asr.o.c.c().c(AsrEngine.this.getTaskId());
                    if (c == 0 && AsrEngine.this.mAsrProcessor != null) {
                        c = AsrEngine.this.mAsrProcessor.c();
                        SmartLogger.i(AsrEngine.TAG, "there is no sendFinalDataTime and use processor.getSendLastDataTime, is " + c);
                    }
                    long currentTimeMillis = System.currentTimeMillis() - c;
                    SmartLogger.i(AsrEngine.TAG, "onResult but lastWordCost is 0 ,so to calculate lastWorCost: " + currentTimeMillis);
                    this.b.putInt("lastWordCost", (int) currentTimeMillis);
                    com.huawei.hms.mlsdk.asr.o.c.c().a(AsrEngine.this.getTaskId(), 4, this.b);
                }
                if (AsrEngine.this.mAsrResult != null) {
                    int length = AsrEngine.this.mAsrResult.length();
                    SmartLogger.i(AsrEngine.TAG, "onResult textLenth :" + length);
                    this.b.putInt(OpAnalyticsConstants.NOTIFICATION_TEXT_LENGTH, length);
                    com.huawei.hms.mlsdk.asr.o.c.c().a(AsrEngine.this.getTaskId(), 7, this.b);
                    this.b.putInt("result", 0);
                    com.huawei.hms.mlsdk.asr.o.c.c().a(AsrEngine.this.getTaskId(), 9, this.b);
                    com.huawei.hms.mlsdk.asr.o.c.c().f(AsrEngine.this.getTaskId());
                }
            }
            StringBuilder a2 = com.huawei.hms.mlsdk.asr.o.a.a("result.text is:");
            a2.append(asrResult.getText());
            a2.append(", engine.asrResult====>");
            a2.append(AsrEngine.this.mAsrResult);
            a2.append("  ");
            a2.append(asrResult.getRetCode());
            SmartLogger.d(AsrEngine.TAG, a2.toString());
            if (!AsrEngine.this.isRecognizerLong()) {
                AsrEngine.this.posterExecutor.execute(new j(AsrEngine.this.callback, new AsrResult(asrResult.getRetCode(), null, AsrEngine.this.mAsrResult, asrResult.isFinal())));
                return;
            }
            String str = AsrEngine.this.mAsrResult;
            if (!TextUtils.isEmpty(AsrEngine.this.mAsrResult)) {
                AsrResult asrResult2 = new AsrResult(asrResult.getRetCode(), null, str, asrResult.isFinal());
                asrResult2.setWordOffsetList(asrResult.getWordOffsetList());
                asrResult2.setSentenceOffsetList(asrResult.getSentenceOffsetList());
                SmartLogger.d(AsrEngine.TAG, "onResult(AsrResult result)");
                AsrEngine.this.posterExecutor.execute(new j(AsrEngine.this.callback, asrResult2));
                if (asrResult.isFinal()) {
                    AsrEngine.this.mAsrResult = "";
                }
            }
            if (8 == asrResult.getRetCode()) {
                AsrEngine.this.destroy();
            }
        }

        @Override // com.huawei.hms.mlsdk.asr.engine.b.e
        public void onState(int i) {
            AsrEngine.this.posterExecutor.execute(new j(AsrEngine.this.callback, i));
            SmartLogger.d(AsrEngine.TAG, "onState(int state)");
        }

        @Override // com.huawei.hms.mlsdk.asr.engine.b.e
        public void a(long j) {
            SmartLogger.d(AsrEngine.TAG, "onSilence");
            AsrEngineConfig asrEngineConfig = AsrEngine.this.engineConfig;
            int vadStartMuteDetectTimes = asrEngineConfig.getVadStartMuteDetectTimes() * asrEngineConfig.getVadStartMuteDuration();
            StringBuilder a2 = com.huawei.hms.mlsdk.asr.o.a.a("engine.hasResult: ");
            a2.append(AsrEngine.this.hasResult());
            a2.append("engine.recordStartTime: ");
            a2.append(AsrEngine.this.recordStartTime);
            a2.append("time-engine.recordStartTime");
            a2.append(j - AsrEngine.this.recordStartTime.longValue());
            a2.append("maxAllowMuteDuration");
            a2.append(vadStartMuteDetectTimes);
            SmartLogger.i(AsrEngine.TAG, a2.toString());
            if (!AsrEngine.this.hasResult() && j - AsrEngine.this.recordStartTime.longValue() < vadStartMuteDetectTimes) {
                SmartLogger.i(AsrEngine.TAG, "onSilence return");
                return;
            }
            if (AsrEngine.this.isRecognizerLong()) {
                return;
            }
            onResult(new AsrResult(8));
            if (AsrEngine.this.mAsrProcessor != null) {
                SmartLogger.i(AsrEngine.TAG, "server detect silence and send finish");
                AsrEngine.this.mAsrRecorder.a();
                AsrEngine.this.mAsrProcessor.e();
            }
        }
    }

    class e implements b.e {

        /* renamed from: a, reason: collision with root package name */
        boolean f5062a = false;
        final /* synthetic */ Bundle b;

        e(Bundle bundle) {
            this.b = bundle;
        }

        @Override // com.huawei.hms.mlsdk.asr.engine.b.e
        public void a(AsrError asrError) {
            int errorCode = asrError.getErrorCode();
            if (AsrEngine.this.mAsrResult != null) {
                int length = AsrEngine.this.mAsrResult.length();
                SmartLogger.i(AsrEngine.TAG, "onError textLenth :" + length);
                this.b.putInt(OpAnalyticsConstants.NOTIFICATION_TEXT_LENGTH, length);
                com.huawei.hms.mlsdk.asr.o.c.c().a(AsrEngine.this.getTaskId(), 7, this.b);
            }
            this.b.putInt("result", errorCode);
            com.huawei.hms.mlsdk.asr.o.c.c().a(AsrEngine.this.getTaskId(), 9, this.b);
            this.b.putString("errMsg", asrError.toString());
            com.huawei.hms.mlsdk.asr.o.c.c().a(AsrEngine.this.getTaskId(), 10, this.b);
            com.huawei.hms.mlsdk.asr.o.c.c().f(AsrEngine.this.getTaskId());
            AsrEngine.this.posterExecutor.execute(new j(AsrEngine.this.callback, asrError, AsrEngine.this.mAsrResult));
            SmartLogger.d(AsrEngine.TAG, "onError(AsrError error)");
        }

        @Override // com.huawei.hms.mlsdk.asr.engine.b.e
        public void b(long j) {
            AsrEngine.this.processStartTime = Long.valueOf(j);
            AsrEngine.this.recordStartTime = Long.valueOf(j);
            long currentTimeMillis = System.currentTimeMillis();
            this.b.putLong("speechStartTime", currentTimeMillis);
            SmartLogger.i(AsrEngine.TAG, "speechStartTime: " + currentTimeMillis);
            com.huawei.hms.mlsdk.asr.o.c.c().a(AsrEngine.this.getTaskId(), 1, this.b);
            AsrEngine.this.posterExecutor.execute(new j(AsrEngine.this.callback, 10));
        }

        @Override // com.huawei.hms.mlsdk.asr.engine.b.e
        public void onResult(AsrResult asrResult) {
            String text = asrResult.getText();
            if (text != null && !"".equals(text)) {
                AsrEngine.this.mAsrResult = text;
            }
            if (asrResult.getRetCode() != 5 && !this.f5062a) {
                this.f5062a = true;
                if (com.huawei.hms.mlsdk.asr.o.c.c().b(AsrEngine.this.getTaskId()) == 0) {
                    long c = com.huawei.hms.mlsdk.asr.o.c.c().c(AsrEngine.this.getTaskId());
                    if (c == 0 && AsrEngine.this.mAsrProcessor != null) {
                        c = AsrEngine.this.mAsrProcessor.c();
                        SmartLogger.i(AsrEngine.TAG, "there is no sendFinalDataTime and use processor.getSendLastDataTime, is " + c);
                    }
                    long currentTimeMillis = System.currentTimeMillis() - c;
                    SmartLogger.i(AsrEngine.TAG, "onResult but lastWordCost is 0 ,so to calculate lastWorCost: " + currentTimeMillis);
                    this.b.putInt("lastWordCost", (int) currentTimeMillis);
                    com.huawei.hms.mlsdk.asr.o.c.c().a(AsrEngine.this.getTaskId(), 4, this.b);
                }
                if (AsrEngine.this.mAsrResult != null) {
                    int length = AsrEngine.this.mAsrResult.length();
                    SmartLogger.i(AsrEngine.TAG, "onResult textLenth :" + length);
                    this.b.putInt(OpAnalyticsConstants.NOTIFICATION_TEXT_LENGTH, length);
                    com.huawei.hms.mlsdk.asr.o.c.c().a(AsrEngine.this.getTaskId(), 7, this.b);
                    this.b.putInt("result", 0);
                    com.huawei.hms.mlsdk.asr.o.c.c().a(AsrEngine.this.getTaskId(), 9, this.b);
                    com.huawei.hms.mlsdk.asr.o.c.c().f(AsrEngine.this.getTaskId());
                }
            }
            StringBuilder a2 = com.huawei.hms.mlsdk.asr.o.a.a("result.text is:");
            a2.append(asrResult.getText());
            a2.append(", engine.asrResult====>");
            a2.append(AsrEngine.this.mAsrResult);
            a2.append("  ");
            a2.append(asrResult.getRetCode());
            SmartLogger.d(AsrEngine.TAG, a2.toString());
            if (!AsrEngine.this.isRecognizerLong()) {
                AsrEngine.this.posterExecutor.execute(new j(AsrEngine.this.callback, new AsrResult(asrResult.getRetCode(), null, AsrEngine.this.mAsrResult, asrResult.isFinal())));
                return;
            }
            String str = AsrEngine.this.mAsrResult;
            if (!TextUtils.isEmpty(AsrEngine.this.mAsrResult)) {
                AsrResult asrResult2 = new AsrResult(asrResult.getRetCode(), null, str, asrResult.isFinal());
                asrResult2.setWordOffsetList(asrResult.getWordOffsetList());
                asrResult2.setSentenceOffsetList(asrResult.getSentenceOffsetList());
                SmartLogger.d(AsrEngine.TAG, "onResult(AsrResult result)");
                AsrEngine.this.posterExecutor.execute(new j(AsrEngine.this.callback, asrResult2));
                if (asrResult.isFinal()) {
                    AsrEngine.this.mAsrResult = "";
                }
            }
            if (8 == asrResult.getRetCode()) {
                AsrEngine.this.destroy();
            }
        }

        @Override // com.huawei.hms.mlsdk.asr.engine.b.e
        public void onState(int i) {
            AsrEngine.this.posterExecutor.execute(new j(AsrEngine.this.callback, i));
            SmartLogger.d(AsrEngine.TAG, "onState(int state)");
        }

        @Override // com.huawei.hms.mlsdk.asr.engine.b.e
        public void a(long j) {
            SmartLogger.d(AsrEngine.TAG, "onSilence");
            AsrEngineConfig asrEngineConfig = AsrEngine.this.engineConfig;
            int vadStartMuteDetectTimes = asrEngineConfig.getVadStartMuteDetectTimes() * asrEngineConfig.getVadStartMuteDuration();
            StringBuilder a2 = com.huawei.hms.mlsdk.asr.o.a.a("engine.hasResult: ");
            a2.append(AsrEngine.this.hasResult());
            a2.append("engine.recordStartTime: ");
            a2.append(AsrEngine.this.recordStartTime);
            a2.append("time-engine.recordStartTime");
            a2.append(j - AsrEngine.this.recordStartTime.longValue());
            a2.append("maxAllowMuteDuration");
            a2.append(vadStartMuteDetectTimes);
            SmartLogger.i(AsrEngine.TAG, a2.toString());
            if (!AsrEngine.this.hasResult() && j - AsrEngine.this.recordStartTime.longValue() < vadStartMuteDetectTimes) {
                SmartLogger.i(AsrEngine.TAG, "onSilence return");
                return;
            }
            if (AsrEngine.this.isRecognizerLong()) {
                return;
            }
            onResult(new AsrResult(8));
            if (AsrEngine.this.mAsrProcessor != null) {
                SmartLogger.i(AsrEngine.TAG, "server detect silence and send finish");
                AsrEngine.this.mAsrProcessor.e();
            }
        }
    }
}
