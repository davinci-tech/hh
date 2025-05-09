package com.huawei.hwdevice.phoneprocess.mgr.hwmlkitmgr;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hms.mlsdk.asr.MLAsrRecognizer;
import com.huawei.hms.mlsdk.asr.MLAsrWriteAudioListener;
import com.huawei.hms.mlsdk.common.MLApplication;
import com.huawei.hms.network.embedded.y5;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class SpeechRecognizerManager {

    /* renamed from: a, reason: collision with root package name */
    private onResultsReady f6340a;
    protected MLAsrRecognizer b;
    private String c;
    protected boolean d;
    protected Intent e;
    private ArrayList<String> g = new ArrayList<>();

    public interface onResultsReady {
        void onError(int i);

        void onResults(ArrayList<String> arrayList);

        void onStartListening();
    }

    public SpeechRecognizerManager(onResultsReady onresultsready, String str) {
        this.c = "zh-CN";
        LogUtil.a("MLASR_Manager", "init language: ", str);
        this.f6340a = onresultsready;
        if (!TextUtils.isEmpty(str)) {
            if (str.contains("_")) {
                LogUtil.a("MLASR_Manager", "init language contains underline.");
                this.c = str.replace("_", Constants.LINK);
            } else {
                this.c = str;
            }
        }
        ThreadPoolManager.d().d("MLASR_Manager", new Runnable() { // from class: com.huawei.hwdevice.phoneprocess.mgr.hwmlkitmgr.SpeechRecognizerManager.1
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("MLASR_Manager", "init languageMlKit: ", SpeechRecognizerManager.this.c);
                SpeechRecognizerManager.this.b = MLAsrRecognizer.createAsrRecognizer(MLApplication.getInstance().getAppContext());
                SpeechRecognizerManager.this.b.setAsrWriteAudioListener(SpeechRecognizerManager.this.new e());
                SpeechRecognizerManager.this.e = new Intent(MLAsrConstants.ACTION_HMS_ASR_SPEECH);
                SpeechRecognizerManager.this.e.putExtra("vadStartMuteDuration", y5.h);
                SpeechRecognizerManager.this.e.putExtra("vadEndMuteDuration", 10000);
                SpeechRecognizerManager.this.e.putExtra("LANGUAGE", SpeechRecognizerManager.this.c).putExtra("FEATURE", 11).putExtra("PUNCTUATION_ENABLE", true);
                if (SpeechRecognizerManager.this.d) {
                    return;
                }
                SpeechRecognizerManager.this.d = true;
                LogUtil.a("MLASR_Manager", "startListening()");
                SpeechRecognizerManager.this.b.startWriteAudio(SpeechRecognizerManager.this.e);
            }
        });
    }

    public void a(byte[] bArr, boolean z) {
        MLAsrRecognizer mLAsrRecognizer = this.b;
        if (mLAsrRecognizer != null) {
            mLAsrRecognizer.receiveVoiceStream(bArr, z);
        }
    }

    public void d() {
        LogUtil.a("MLASR_Manager", "onDestroy");
        MLAsrRecognizer mLAsrRecognizer = this.b;
        if (mLAsrRecognizer != null) {
            mLAsrRecognizer.destroy();
            this.b = null;
        }
    }

    protected class e implements MLAsrWriteAudioListener {
        protected e() {
        }

        @Override // com.huawei.hms.mlsdk.asr.MLAsrWriteAudioListener
        public void onStartListening() {
            LogUtil.a("MLASR_Manager", "onStartListening");
            if (SpeechRecognizerManager.this.f6340a != null) {
                SpeechRecognizerManager.this.f6340a.onStartListening();
            }
        }

        @Override // com.huawei.hms.mlsdk.asr.MLAsrWriteAudioListener
        public void onResults(Bundle bundle) {
            if (bundle == null || SpeechRecognizerManager.this.f6340a == null) {
                return;
            }
            SpeechRecognizerManager.this.g.clear();
            SpeechRecognizerManager.this.g.add(bundle.getString(MLAsrRecognizer.RESULTS_RECOGNIZED));
            SpeechRecognizerManager.this.f6340a.onResults(SpeechRecognizerManager.this.g);
            LogUtil.a("MLASR_Manager", " MLAsrWriteAudioListener onResults is ", bundle);
        }

        @Override // com.huawei.hms.mlsdk.asr.MLAsrWriteAudioListener
        public void onError(int i, String str) {
            SpeechRecognizerManager.this.d = false;
            LogUtil.a("MLASR_Manager", "onError error: ", Integer.valueOf(i), " errorMessage: ", str);
            if (SpeechRecognizerManager.this.f6340a != null) {
                SpeechRecognizerManager.this.f6340a.onError(i);
            }
        }
    }
}
