package com.huawei.hmf.orb.bridge;

import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.taskstream.TaskStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes9.dex */
public class RemoteBridgeFactory {
    private static Map<Class, Class<? extends Bridge>> mFactoryMap;

    static {
        HashMap hashMap = new HashMap();
        mFactoryMap = hashMap;
        hashMap.put(TaskStream.class, TaskStreamBridge.class);
        mFactoryMap.put(Task.class, TaskBridge.class);
    }

    private RemoteBridgeFactory() {
    }

    public static void register(Class<?> cls, Class<? extends Bridge> cls2) {
        mFactoryMap.put(cls, cls2);
    }

    public static Bridge getBridge(Class<?> cls) {
        Iterator<Map.Entry<Class, Class<? extends Bridge>>> it = mFactoryMap.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<Class, Class<? extends Bridge>> next = it.next();
            if (next.getKey().isAssignableFrom(cls)) {
                try {
                    return next.getValue().newInstance();
                } catch (Exception unused) {
                }
            }
        }
        return null;
    }
}
