package com.huawei.wearengine.core.device;

import com.huawei.wearengine.utils.Singleton;
import defpackage.tos;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes7.dex */
public class PowerModeChangeManager {
    private static final Singleton<PowerModeChangeManager> d = new Singleton<PowerModeChangeManager>() { // from class: com.huawei.wearengine.core.device.PowerModeChangeManager.4
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.wearengine.utils.Singleton
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public PowerModeChangeManager create() {
            return new PowerModeChangeManager();
        }
    };
    private final ExecutorService b;
    private final AtomicBoolean c;
    private final AtomicBoolean e;

    /* loaded from: classes9.dex */
    public interface HandlePowerModeChange {
        void startClearData(String str);
    }

    private PowerModeChangeManager() {
        this.c = new AtomicBoolean(false);
        this.e = new AtomicBoolean(false);
        this.b = Executors.newSingleThreadExecutor();
    }

    public static PowerModeChangeManager a() {
        return d.get();
    }

    public void d() {
        this.c.set(true);
    }

    public boolean c() {
        return this.e.get();
    }

    public boolean b(boolean z) {
        if (!c()) {
            return false;
        }
        tos.a("PowerModeChangeManager", "checkIsPowerModeChanging powerMode in changing");
        if (z) {
            throw new IllegalStateException(String.valueOf(20));
        }
        return true;
    }
}
