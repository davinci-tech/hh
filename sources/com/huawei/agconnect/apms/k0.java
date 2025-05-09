package com.huawei.agconnect.apms;

import android.os.Process;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.hms.ml.scan.HmsScanBase;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public class k0 {
    public static final AtomicInteger abc = new AtomicInteger(0);
    public static final AtomicInteger bcd = new AtomicInteger(0);
    public static final String cde;
    public static final String def;
    public static final String efg;

    static {
        String sb;
        String valueOf = String.valueOf(Process.myPid());
        if (valueOf.length() >= 6) {
            sb = valueOf.substring(0, 6);
        } else {
            StringBuilder sb2 = new StringBuilder();
            for (int i = 0; i < 6 - valueOf.length(); i++) {
                sb2.append('0');
            }
            sb2.append(valueOf);
            sb = sb2.toString();
        }
        cde = sb;
        String substring = UUID.randomUUID().toString().substring(0, 8);
        def = substring + sb + FitRunPlayAudio.PLAY_TYPE_D;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(substring);
        sb3.append(sb.substring(2, 6));
        efg = sb3.toString();
    }

    public static String abc() {
        StringBuilder sb = new StringBuilder(16);
        long nanoTime = System.nanoTime();
        long incrementAndGet = (bcd.incrementAndGet() & 7) << 10;
        sb.append(efg);
        sb.append(((nanoTime >> 10) & 1023) + incrementAndGet + 1000);
        return sb.toString();
    }

    public static String bcd() {
        StringBuilder sb = new StringBuilder(32);
        int incrementAndGet = abc.incrementAndGet();
        sb.append(def);
        sb.append(System.currentTimeMillis());
        sb.append((incrementAndGet & HmsScanBase.ALL_SCAN_TYPE) + 1000);
        return sb.toString();
    }
}
