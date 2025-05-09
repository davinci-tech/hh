package android.os;

import android.util.Log;

/* loaded from: classes8.dex */
public class HapticPlayer {
    private static final String TAG = "HapticPlayer";
    DynamicEffect mEffect;

    public static boolean isAvailable() {
        return false;
    }

    public void updateParameter(int i, int i2, int i3) {
        Log.e(TAG, "not support Haptic player api, updateParameter with interval/amplitude/freq");
    }

    public void updateInterval(int i) {
        Log.e(TAG, "not support Haptic player api, updateInterval with interval");
    }

    public void updateFrequency(int i) {
        Log.e(TAG, "not support Haptic player api, updateFrequency with freq");
    }

    public void updateAmplitude(int i) {
        Log.e(TAG, "not support Haptic player api, updateAmplitude with amplitude");
    }

    public void stop() {
        Log.e(TAG, "not support Haptic player api, stop");
    }

    public void start(int i, int i2, int i3, int i4) {
        Log.e(TAG, "not support Haptic player api, start with loop & interval & amplitude & freq");
    }

    public void start(int i, int i2, int i3) {
        Log.e(TAG, "not support Haptic player api, start with loop & interval & amplitude");
    }

    public void start(int i) {
        Log.e(TAG, "not support Haptic player api, start with loop");
    }

    public HapticPlayer(DynamicEffect dynamicEffect) {
        this();
        Log.i(TAG, "HapticPlayer(DynamicEffect)");
        this.mEffect = dynamicEffect;
    }

    private HapticPlayer() {
    }
}
