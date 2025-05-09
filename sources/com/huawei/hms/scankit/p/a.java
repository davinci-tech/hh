package com.huawei.hms.scankit.p;

/* loaded from: classes4.dex */
public class a extends Exception {

    /* renamed from: a, reason: collision with root package name */
    protected static final boolean f5726a;
    protected static final StackTraceElement[] b;
    private static final a c;

    static {
        f5726a = System.getProperty("surefire.test.class.path") != null;
        StackTraceElement[] stackTraceElementArr = new StackTraceElement[0];
        b = stackTraceElementArr;
        a aVar = new a();
        c = aVar;
        aVar.setStackTrace(stackTraceElementArr);
    }

    private a() {
    }

    public static a a() {
        return f5726a ? new a() : c;
    }

    private a(String str) {
        super(str);
    }

    public static a a(String str) {
        return new a(str);
    }
}
