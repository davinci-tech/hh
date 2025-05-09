package com.huawei.agconnect.apms;

/* loaded from: classes2.dex */
public class r0 {
    public static String abc() {
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int i = 0;
        for (int i2 = 0; i2 < stackTrace.length; i2++) {
            StackTraceElement stackTraceElement = stackTrace[i2];
            if (stackTraceElement != null) {
                String className = stackTraceElement.getClassName();
                String methodName = stackTraceElement.getMethodName();
                if (!className.startsWith("com.huawei.agconnect.apms") && ((!className.startsWith("dalvik.system.VMStack") || !methodName.startsWith("getThreadStackTrace")) && (!className.startsWith("java.lang.Thread") || !methodName.startsWith("getStackTrace")))) {
                    sb.append(stackTraceElement.toString());
                    if (i2 <= stackTrace.length - 1) {
                        sb.append("\n");
                    }
                    i++;
                    if (i >= nop.abc().efg) {
                        break;
                    }
                }
            }
        }
        return sb.toString();
    }
}
