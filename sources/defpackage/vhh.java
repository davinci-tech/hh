package defpackage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.event.LoggingEvent;

/* loaded from: classes7.dex */
public class vhh implements Logger {

    /* renamed from: a, reason: collision with root package name */
    private Boolean f17712a;
    private Queue<vhc> b;
    private volatile Logger c;
    private final boolean d;
    private vhe e;
    private final String g;
    private Method h;

    public vhh(String str, Queue<vhc> queue, boolean z) {
        this.g = str;
        this.b = queue;
        this.d = z;
    }

    @Override // org.slf4j.Logger
    public String getName() {
        return this.g;
    }

    @Override // org.slf4j.Logger
    public boolean isTraceEnabled() {
        return e().isTraceEnabled();
    }

    @Override // org.slf4j.Logger
    public void trace(String str) {
        e().trace(str);
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object obj) {
        e().trace(str, obj);
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object obj, Object obj2) {
        e().trace(str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object... objArr) {
        e().trace(str, objArr);
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Throwable th) {
        e().trace(str, th);
    }

    @Override // org.slf4j.Logger
    public boolean isTraceEnabled(Marker marker) {
        return e().isTraceEnabled(marker);
    }

    @Override // org.slf4j.Logger
    public void trace(Marker marker, String str) {
        e().trace(marker, str);
    }

    @Override // org.slf4j.Logger
    public void trace(Marker marker, String str, Object obj) {
        e().trace(marker, str, obj);
    }

    @Override // org.slf4j.Logger
    public void trace(Marker marker, String str, Object obj, Object obj2) {
        e().trace(marker, str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void trace(Marker marker, String str, Object... objArr) {
        e().trace(marker, str, objArr);
    }

    @Override // org.slf4j.Logger
    public void trace(Marker marker, String str, Throwable th) {
        e().trace(marker, str, th);
    }

    @Override // org.slf4j.Logger
    public boolean isDebugEnabled() {
        return e().isDebugEnabled();
    }

    @Override // org.slf4j.Logger
    public void debug(String str) {
        e().debug(str);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object obj) {
        e().debug(str, obj);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object obj, Object obj2) {
        e().debug(str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object... objArr) {
        e().debug(str, objArr);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Throwable th) {
        e().debug(str, th);
    }

    @Override // org.slf4j.Logger
    public boolean isDebugEnabled(Marker marker) {
        return e().isDebugEnabled(marker);
    }

    @Override // org.slf4j.Logger
    public void debug(Marker marker, String str) {
        e().debug(marker, str);
    }

    @Override // org.slf4j.Logger
    public void debug(Marker marker, String str, Object obj) {
        e().debug(marker, str, obj);
    }

    @Override // org.slf4j.Logger
    public void debug(Marker marker, String str, Object obj, Object obj2) {
        e().debug(marker, str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void debug(Marker marker, String str, Object... objArr) {
        e().debug(marker, str, objArr);
    }

    @Override // org.slf4j.Logger
    public void debug(Marker marker, String str, Throwable th) {
        e().debug(marker, str, th);
    }

    @Override // org.slf4j.Logger
    public boolean isInfoEnabled() {
        return e().isInfoEnabled();
    }

    @Override // org.slf4j.Logger
    public void info(String str) {
        e().info(str);
    }

    @Override // org.slf4j.Logger
    public void info(String str, Object obj) {
        e().info(str, obj);
    }

    @Override // org.slf4j.Logger
    public void info(String str, Object obj, Object obj2) {
        e().info(str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void info(String str, Object... objArr) {
        e().info(str, objArr);
    }

    @Override // org.slf4j.Logger
    public void info(String str, Throwable th) {
        e().info(str, th);
    }

    @Override // org.slf4j.Logger
    public boolean isInfoEnabled(Marker marker) {
        return e().isInfoEnabled(marker);
    }

    @Override // org.slf4j.Logger
    public void info(Marker marker, String str) {
        e().info(marker, str);
    }

    @Override // org.slf4j.Logger
    public void info(Marker marker, String str, Object obj) {
        e().info(marker, str, obj);
    }

    @Override // org.slf4j.Logger
    public void info(Marker marker, String str, Object obj, Object obj2) {
        e().info(marker, str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void info(Marker marker, String str, Object... objArr) {
        e().info(marker, str, objArr);
    }

    @Override // org.slf4j.Logger
    public void info(Marker marker, String str, Throwable th) {
        e().info(marker, str, th);
    }

    @Override // org.slf4j.Logger
    public boolean isWarnEnabled() {
        return e().isWarnEnabled();
    }

    @Override // org.slf4j.Logger
    public void warn(String str) {
        e().warn(str);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object obj) {
        e().warn(str, obj);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object obj, Object obj2) {
        e().warn(str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object... objArr) {
        e().warn(str, objArr);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Throwable th) {
        e().warn(str, th);
    }

    @Override // org.slf4j.Logger
    public boolean isWarnEnabled(Marker marker) {
        return e().isWarnEnabled(marker);
    }

    @Override // org.slf4j.Logger
    public void warn(Marker marker, String str) {
        e().warn(marker, str);
    }

    @Override // org.slf4j.Logger
    public void warn(Marker marker, String str, Object obj) {
        e().warn(marker, str, obj);
    }

    @Override // org.slf4j.Logger
    public void warn(Marker marker, String str, Object obj, Object obj2) {
        e().warn(marker, str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void warn(Marker marker, String str, Object... objArr) {
        e().warn(marker, str, objArr);
    }

    @Override // org.slf4j.Logger
    public void warn(Marker marker, String str, Throwable th) {
        e().warn(marker, str, th);
    }

    @Override // org.slf4j.Logger
    public boolean isErrorEnabled() {
        return e().isErrorEnabled();
    }

    @Override // org.slf4j.Logger
    public void error(String str) {
        e().error(str);
    }

    @Override // org.slf4j.Logger
    public void error(String str, Object obj) {
        e().error(str, obj);
    }

    @Override // org.slf4j.Logger
    public void error(String str, Object obj, Object obj2) {
        e().error(str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void error(String str, Object... objArr) {
        e().error(str, objArr);
    }

    @Override // org.slf4j.Logger
    public void error(String str, Throwable th) {
        e().error(str, th);
    }

    @Override // org.slf4j.Logger
    public boolean isErrorEnabled(Marker marker) {
        return e().isErrorEnabled(marker);
    }

    @Override // org.slf4j.Logger
    public void error(Marker marker, String str) {
        e().error(marker, str);
    }

    @Override // org.slf4j.Logger
    public void error(Marker marker, String str, Object obj) {
        e().error(marker, str, obj);
    }

    @Override // org.slf4j.Logger
    public void error(Marker marker, String str, Object obj, Object obj2) {
        e().error(marker, str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void error(Marker marker, String str, Object... objArr) {
        e().error(marker, str, objArr);
    }

    @Override // org.slf4j.Logger
    public void error(Marker marker, String str, Throwable th) {
        e().error(marker, str, th);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.g.equals(((vhh) obj).g);
    }

    public int hashCode() {
        return this.g.hashCode();
    }

    Logger e() {
        if (this.c != null) {
            return this.c;
        }
        if (this.d) {
            return vhf.f17711a;
        }
        return c();
    }

    private Logger c() {
        if (this.e == null) {
            this.e = new vhe(this, this.b);
        }
        return this.e;
    }

    public void e(Logger logger) {
        this.c = logger;
    }

    public boolean b() {
        Boolean bool = this.f17712a;
        if (bool != null) {
            return bool.booleanValue();
        }
        try {
            this.h = this.c.getClass().getMethod("log", LoggingEvent.class);
            this.f17712a = Boolean.TRUE;
        } catch (NoSuchMethodException unused) {
            this.f17712a = Boolean.FALSE;
        }
        return this.f17712a.booleanValue();
    }

    public void c(LoggingEvent loggingEvent) {
        if (b()) {
            try {
                this.h.invoke(this.c, loggingEvent);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
            }
        }
    }

    public boolean a() {
        return this.c == null;
    }

    public boolean d() {
        return this.c instanceof vhf;
    }
}
