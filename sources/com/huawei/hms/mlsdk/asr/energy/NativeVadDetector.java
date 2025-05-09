package com.huawei.hms.mlsdk.asr.energy;

import com.huawei.hms.mlsdk.asr.energy.vo.DetectResult;
import com.huawei.hms.mlsdk.asr.energy.vo.SampleBuffer;
import com.huawei.hms.mlsdk.asr.energy.vo.VoiceDetectParams;
import com.huawei.hms.mlsdk.asr.engine.utils.SmartLogger;
import com.huawei.hms.mlsdk.asr.o.a;

/* loaded from: classes4.dex */
public class NativeVadDetector {

    /* renamed from: a, reason: collision with root package name */
    private int f5055a = new Object().hashCode();

    public NativeVadDetector() {
        try {
            System.loadLibrary("ml-vadenergy");
        } catch (UnsatisfiedLinkError e) {
            StringBuilder a2 = a.a("loadLibrary e = ");
            a2.append(e.getMessage());
            SmartLogger.e("NativeVadDetector", a2.toString());
        }
    }

    public native DetectResult detect(SampleBuffer sampleBuffer);

    public int getTag() {
        return this.f5055a;
    }

    public native boolean init(VoiceDetectParams voiceDetectParams);

    public native void release();
}
