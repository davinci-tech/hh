package com.apprichtap.haptic.player;

import android.content.Context;
import android.os.Process;
import android.os.VibrationEffect;
import android.os.Vibrator;
import androidx.core.internal.view.SupportMenu;
import defpackage.mx;
import defpackage.my;
import defpackage.nc;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes8.dex */
public class d implements IHapticEffectPerformer {

    /* renamed from: a, reason: collision with root package name */
    public static int f1609a = -1;
    public static int e = -1;
    private Vibrator b;
    private Class<?> c;
    private Context d;
    private String g;
    private boolean h;
    private AtomicInteger i = new AtomicInteger();
    private int f = 255;

    public interface a {

        /* renamed from: a, reason: collision with root package name */
        public static final int f1610a = 1;
        public static final int b = 256;
        public static final int c = 513;
        public static final int d = 514;
        public static final int e = 769;
        public static final int f = 1;
        public static final int g = 2;
    }

    @Override // com.apprichtap.haptic.player.IHapticEffectPerformer
    public void updateParameter(int i, int i2) {
        if (32 > e) {
            nc.b.c("RichTapPerformer", "not support updateHapticParam(), core version:" + e);
            return;
        }
        String str = this.g;
        int[] b = str != null ? mx.b(str) : null;
        if (b == null || 2 != b.length) {
            nc.b.b("RichTapPerformer", "updateHapticParams, invalid sender, do nothing!");
            return;
        }
        nc.b.e("RichTapPerformer", "updateHapticParams, sender pid:" + b[0] + ",gid:" + (b[1] & SupportMenu.CATEGORY_MASK) + ",intensity:" + i + ",freq:" + i2);
        int[] iArr = {256, 0, 0, 513, i, a.d, i2};
        System.arraycopy(b, 0, iArr, 1, 2);
        d(iArr, 7);
    }

    @Override // com.apprichtap.haptic.player.IHapticEffectPerformer
    public void swapVibrationIndex(boolean z) {
        nc.b.a("RichTapPerformer", "swapVibrationIndex:" + z);
        this.h = z;
    }

    @Override // com.apprichtap.haptic.player.IHapticEffectPerformer
    public boolean supportRealtimeAdjustment() {
        return 32 <= e;
    }

    @Override // com.apprichtap.haptic.player.IHapticEffectPerformer
    public void stop() {
        nc.b.e("RichTapPerformer", "stop()");
        c(0, 0, 0);
    }

    @Override // com.apprichtap.haptic.player.IHapticEffectPerformer
    public void start(String str) {
        String str2;
        int i;
        int myPid;
        int incrementAndGet;
        String str3 = this.g;
        int[] b = str3 != null ? mx.b(str3) : null;
        int i2 = e;
        if (i2 <= 0) {
            nc.b.b("RichTapPerformer", "start(), mRichTapCoreMajorVersion:" + e);
            return;
        }
        if (23 >= i2) {
            str2 = my.c(str, true);
            i = 1;
        } else {
            if (32 <= i2 && b != null) {
                int length = b.length;
            }
            str2 = str;
            i = 2;
        }
        if (b == null || 2 != b.length) {
            myPid = Process.myPid();
            incrementAndGet = this.i.incrementAndGet() % Integer.MAX_VALUE;
        } else {
            int i3 = b[0];
            incrementAndGet = b[1];
            myPid = i3;
        }
        int[] b2 = my.b(str2, i, e, f1609a, myPid, incrementAndGet, this.h);
        try {
            nc.b.e("RichTapPerformer", "start() mGain:" + this.f + " raw data:" + Arrays.toString(b2));
            Class<?> cls = this.c;
            Class<?> cls2 = Integer.TYPE;
            this.b.vibrate((VibrationEffect) cls.getMethod("createPatternHeWithParam", int[].class, cls2, cls2, cls2, cls2).invoke(null, b2, 1, 0, Integer.valueOf(this.f), 0));
        } catch (Throwable th) {
            nc.b.c("RichTapPerformer", "The system doesn't integrate richTap software");
            th.printStackTrace();
        }
    }

    @Override // com.apprichtap.haptic.player.IHapticEffectPerformer
    public void setSenderIdKey(String str) {
        nc.b.e("RichTapPerformer", "setSenderIdKey:" + str);
        this.g = str;
    }

    @Override // com.apprichtap.haptic.player.IHapticEffectPerformer
    public void setGain(int i) {
        if (this.d == null) {
            nc.b.c("RichTapPerformer", "set gain null == mContext");
            return;
        }
        if (i == 0) {
            nc.b.e("RichTapPerformer", "0 == gain");
            i = 1;
        }
        c(0, i, 0);
        this.f = i;
    }

    @Override // com.apprichtap.haptic.player.IHapticEffectPerformer
    public int getRichTapCoreMajorVersion() {
        return e;
    }

    private void e() {
        try {
            if (this.d != null && this.b != null) {
                int intValue = ((Integer) this.c.getMethod("checkIfRichTapSupport", new Class[0]).invoke(null, new Object[0])).intValue();
                nc.b.e("RichTapPerformer", "getRichTapCoreMajorVersion check framework RichTap version:" + intValue);
                if (1 == intValue) {
                    return;
                }
                StringBuilder sb = new StringBuilder("clientCode:");
                sb.append((16711680 & intValue) >> 16);
                sb.append(" majorVersion:");
                int i = (65280 & intValue) >> 8;
                sb.append(i);
                sb.append(" minorVersion:");
                int i2 = intValue & 255;
                sb.append(i2);
                nc.b.e("RichTapPerformer", sb.toString());
                e = i;
                f1609a = i2;
                return;
            }
            nc.b.b("RichTapPerformer", "getRichTapCoreMajorVersion mContext or mVibrator null");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void d(int[] iArr, int i) {
        if (e <= 0) {
            nc.b.b("RichTapPerformer", "applyPreBakedEffect, CORE_MAJOR_VERSION:" + e);
        } else {
            try {
                this.b.vibrate((VibrationEffect) this.c.getMethod("createHapticParameter", int[].class, Integer.TYPE).invoke(null, iArr, Integer.valueOf(i)));
            } catch (Throwable th) {
                th.printStackTrace();
                nc.b.c("RichTapPerformer", "The system doesn't integrate RichTap software");
            }
        }
    }

    private void c(int i, int i2, int i3) {
        if (e <= 0) {
            nc.b.b("RichTapPerformer", "applyPatternHeParam, CORE_MAJOR_VERSION:" + e);
            return;
        }
        try {
            nc.b.a("RichTapPerformer", "applyPatternHeParam, interval:" + i + ", amplitude:" + i2 + ",freq:" + i3);
            Class<?> cls = this.c;
            Class<?> cls2 = Integer.TYPE;
            this.b.vibrate((VibrationEffect) cls.getMethod("createPatternHeParameter", cls2, cls2, cls2).invoke(null, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)));
        } catch (Throwable th) {
            th.printStackTrace();
            nc.b.c("RichTapPerformer", "The system doesn't integrate RichTap software");
        }
    }

    public d(Context context) {
        this.d = context;
        this.b = (Vibrator) context.getSystemService("vibrator");
        try {
            this.c = Class.forName("richtap.os.PhonyVibrationEffect");
        } catch (ClassNotFoundException unused) {
            this.c = null;
            nc.b.a("RichTapPerformer", "failed to reflect class: \"richtap.os.PhonyVibrationEffect\"!");
        }
        if (this.c == null) {
            try {
                this.c = Class.forName("android.os.RichTapVibrationEffect");
            } catch (ClassNotFoundException unused2) {
                this.c = null;
                nc.b.a("RichTapPerformer", "failed to reflect class: \"android.os.RichTapVibrationEffect\"!");
            }
        }
        if (this.c == null) {
            try {
                this.c = Class.forName("android.os.VibrationEffect");
            } catch (ClassNotFoundException unused3) {
                nc.b.a("RichTapPerformer", "failed to reflect class: \"android.os.VibrationEffect\"!");
            }
        }
        e();
    }
}
