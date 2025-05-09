package defpackage;

import com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class ary {
    private static final Object b = new Object();
    private Map<String, Set<OnFitnessStatusChangeCallback>> c;

    private ary() {
        this.c = new ConcurrentHashMap(10);
    }

    public static ary a() {
        return c.b;
    }

    static class c {
        private static final ary b = new ary();
    }

    public boolean e(OnFitnessStatusChangeCallback onFitnessStatusChangeCallback, String str) {
        boolean add;
        if (onFitnessStatusChangeCallback == null) {
            return false;
        }
        if (!this.c.containsKey(str)) {
            this.c.put(str, new HashSet(10));
        }
        synchronized (b) {
            add = this.c.get(str).add(onFitnessStatusChangeCallback);
        }
        return add;
    }

    public boolean c(OnFitnessStatusChangeCallback onFitnessStatusChangeCallback, String str) {
        boolean remove;
        if (onFitnessStatusChangeCallback == null) {
            LogUtil.h("Suggestion_WorkoutFinishedHelper", "callback == null");
            return false;
        }
        if (!this.c.containsKey(str)) {
            LogUtil.h("Suggestion_WorkoutFinishedHelper", "callback not exist");
            return false;
        }
        synchronized (b) {
            Set<OnFitnessStatusChangeCallback> set = this.c.get(str);
            remove = set != null ? set.remove(onFitnessStatusChangeCallback) : false;
        }
        return remove;
    }

    public void e(String str) {
        synchronized (b) {
            if (!this.c.containsKey(str)) {
                LogUtil.h("Suggestion_WorkoutFinishedHelper", "callback not exist");
                return;
            }
            Set<OnFitnessStatusChangeCallback> set = this.c.get(str);
            OnFitnessStatusChangeCallback[] onFitnessStatusChangeCallbackArr = set != null ? (OnFitnessStatusChangeCallback[]) set.toArray(new OnFitnessStatusChangeCallback[set.size()]) : null;
            if (onFitnessStatusChangeCallbackArr == null) {
                return;
            }
            for (OnFitnessStatusChangeCallback onFitnessStatusChangeCallback : onFitnessStatusChangeCallbackArr) {
                if (onFitnessStatusChangeCallback != null) {
                    onFitnessStatusChangeCallback.onUpdate();
                }
            }
        }
    }
}
