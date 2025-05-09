package com.huawei.agconnect.core.a;

import com.huawei.agconnect.AGCInitFinishManager;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes2.dex */
public class a extends AGCInitFinishManager {

    /* renamed from: a, reason: collision with root package name */
    private static final List<AGCInitFinishManager.AGCInitFinishCallback> f1716a = new CopyOnWriteArrayList();

    @Override // com.huawei.agconnect.AGCInitFinishManager
    public void addAGCInitFinishCallback(AGCInitFinishManager.AGCInitFinishCallback aGCInitFinishCallback) {
        if (aGCInitFinishCallback != null) {
            f1716a.add(aGCInitFinishCallback);
        }
    }

    public static void a() {
        Iterator<AGCInitFinishManager.AGCInitFinishCallback> it = f1716a.iterator();
        while (it.hasNext()) {
            it.next().onFinish();
        }
    }
}
