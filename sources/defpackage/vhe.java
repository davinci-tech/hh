package defpackage;

import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.event.Level;

/* loaded from: classes7.dex */
public class vhe implements Logger {

    /* renamed from: a, reason: collision with root package name */
    Queue<vhc> f17710a;
    vhh b;
    String d;

    @Override // org.slf4j.Logger
    public boolean isDebugEnabled() {
        return true;
    }

    @Override // org.slf4j.Logger
    public boolean isDebugEnabled(Marker marker) {
        return true;
    }

    @Override // org.slf4j.Logger
    public boolean isErrorEnabled() {
        return true;
    }

    @Override // org.slf4j.Logger
    public boolean isErrorEnabled(Marker marker) {
        return true;
    }

    @Override // org.slf4j.Logger
    public boolean isInfoEnabled() {
        return true;
    }

    @Override // org.slf4j.Logger
    public boolean isInfoEnabled(Marker marker) {
        return true;
    }

    @Override // org.slf4j.Logger
    public boolean isTraceEnabled() {
        return true;
    }

    @Override // org.slf4j.Logger
    public boolean isTraceEnabled(Marker marker) {
        return true;
    }

    @Override // org.slf4j.Logger
    public boolean isWarnEnabled() {
        return true;
    }

    @Override // org.slf4j.Logger
    public boolean isWarnEnabled(Marker marker) {
        return true;
    }

    public vhe(vhh vhhVar, Queue<vhc> queue) {
        this.b = vhhVar;
        this.d = vhhVar.getName();
        this.f17710a = queue;
    }

    @Override // org.slf4j.Logger
    public String getName() {
        return this.d;
    }

    @Override // org.slf4j.Logger
    public void trace(String str) {
        a(Level.TRACE, (Marker) null, str, (Throwable) null);
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object obj) {
        a(Level.TRACE, (Marker) null, str, obj);
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object obj, Object obj2) {
        b(Level.TRACE, null, str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object... objArr) {
        d(Level.TRACE, null, str, objArr);
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Throwable th) {
        a(Level.TRACE, (Marker) null, str, th);
    }

    @Override // org.slf4j.Logger
    public void trace(Marker marker, String str) {
        a(Level.TRACE, marker, str, (Throwable) null);
    }

    @Override // org.slf4j.Logger
    public void trace(Marker marker, String str, Object obj) {
        a(Level.TRACE, marker, str, obj);
    }

    @Override // org.slf4j.Logger
    public void trace(Marker marker, String str, Object obj, Object obj2) {
        b(Level.TRACE, marker, str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void trace(Marker marker, String str, Object... objArr) {
        d(Level.TRACE, marker, str, objArr);
    }

    @Override // org.slf4j.Logger
    public void trace(Marker marker, String str, Throwable th) {
        a(Level.TRACE, marker, str, th);
    }

    @Override // org.slf4j.Logger
    public void debug(String str) {
        a(Level.DEBUG, (Marker) null, str, (Throwable) null);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object obj) {
        a(Level.DEBUG, (Marker) null, str, obj);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object obj, Object obj2) {
        b(Level.DEBUG, null, str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object... objArr) {
        d(Level.DEBUG, null, str, objArr);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Throwable th) {
        a(Level.DEBUG, (Marker) null, str, th);
    }

    @Override // org.slf4j.Logger
    public void debug(Marker marker, String str) {
        a(Level.DEBUG, marker, str, (Throwable) null);
    }

    @Override // org.slf4j.Logger
    public void debug(Marker marker, String str, Object obj) {
        a(Level.DEBUG, marker, str, obj);
    }

    @Override // org.slf4j.Logger
    public void debug(Marker marker, String str, Object obj, Object obj2) {
        b(Level.DEBUG, marker, str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void debug(Marker marker, String str, Object... objArr) {
        d(Level.DEBUG, marker, str, objArr);
    }

    @Override // org.slf4j.Logger
    public void debug(Marker marker, String str, Throwable th) {
        a(Level.DEBUG, marker, str, th);
    }

    @Override // org.slf4j.Logger
    public void info(String str) {
        a(Level.INFO, (Marker) null, str, (Throwable) null);
    }

    @Override // org.slf4j.Logger
    public void info(String str, Object obj) {
        a(Level.INFO, (Marker) null, str, obj);
    }

    @Override // org.slf4j.Logger
    public void info(String str, Object obj, Object obj2) {
        b(Level.INFO, null, str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void info(String str, Object... objArr) {
        d(Level.INFO, null, str, objArr);
    }

    @Override // org.slf4j.Logger
    public void info(String str, Throwable th) {
        a(Level.INFO, (Marker) null, str, th);
    }

    @Override // org.slf4j.Logger
    public void info(Marker marker, String str) {
        a(Level.INFO, marker, str, (Throwable) null);
    }

    @Override // org.slf4j.Logger
    public void info(Marker marker, String str, Object obj) {
        a(Level.INFO, marker, str, obj);
    }

    @Override // org.slf4j.Logger
    public void info(Marker marker, String str, Object obj, Object obj2) {
        b(Level.INFO, marker, str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void info(Marker marker, String str, Object... objArr) {
        d(Level.INFO, marker, str, objArr);
    }

    @Override // org.slf4j.Logger
    public void info(Marker marker, String str, Throwable th) {
        a(Level.INFO, marker, str, th);
    }

    @Override // org.slf4j.Logger
    public void warn(String str) {
        a(Level.WARN, (Marker) null, str, (Throwable) null);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object obj) {
        a(Level.WARN, (Marker) null, str, obj);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object obj, Object obj2) {
        b(Level.WARN, null, str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object... objArr) {
        d(Level.WARN, null, str, objArr);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Throwable th) {
        a(Level.WARN, (Marker) null, str, th);
    }

    @Override // org.slf4j.Logger
    public void warn(Marker marker, String str) {
        a(Level.WARN, marker, str, (Throwable) null);
    }

    @Override // org.slf4j.Logger
    public void warn(Marker marker, String str, Object obj) {
        a(Level.WARN, marker, str, obj);
    }

    @Override // org.slf4j.Logger
    public void warn(Marker marker, String str, Object obj, Object obj2) {
        b(Level.WARN, marker, str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void warn(Marker marker, String str, Object... objArr) {
        d(Level.WARN, marker, str, objArr);
    }

    @Override // org.slf4j.Logger
    public void warn(Marker marker, String str, Throwable th) {
        a(Level.WARN, marker, str, th);
    }

    @Override // org.slf4j.Logger
    public void error(String str) {
        a(Level.ERROR, (Marker) null, str, (Throwable) null);
    }

    @Override // org.slf4j.Logger
    public void error(String str, Object obj) {
        a(Level.ERROR, (Marker) null, str, obj);
    }

    @Override // org.slf4j.Logger
    public void error(String str, Object obj, Object obj2) {
        b(Level.ERROR, null, str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void error(String str, Object... objArr) {
        d(Level.ERROR, null, str, objArr);
    }

    @Override // org.slf4j.Logger
    public void error(String str, Throwable th) {
        a(Level.ERROR, (Marker) null, str, th);
    }

    @Override // org.slf4j.Logger
    public void error(Marker marker, String str) {
        a(Level.ERROR, marker, str, (Throwable) null);
    }

    @Override // org.slf4j.Logger
    public void error(Marker marker, String str, Object obj) {
        a(Level.ERROR, marker, str, obj);
    }

    @Override // org.slf4j.Logger
    public void error(Marker marker, String str, Object obj, Object obj2) {
        b(Level.ERROR, marker, str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void error(Marker marker, String str, Object... objArr) {
        d(Level.ERROR, marker, str, objArr);
    }

    @Override // org.slf4j.Logger
    public void error(Marker marker, String str, Throwable th) {
        a(Level.ERROR, marker, str, th);
    }

    private void a(Level level, Marker marker, String str, Throwable th) {
        d(level, marker, str, null, th);
    }

    private void a(Level level, Marker marker, String str, Object obj) {
        d(level, marker, str, new Object[]{obj}, null);
    }

    private void b(Level level, Marker marker, String str, Object obj, Object obj2) {
        if (obj2 instanceof Throwable) {
            d(level, marker, str, new Object[]{obj}, (Throwable) obj2);
        } else {
            d(level, marker, str, new Object[]{obj, obj2}, null);
        }
    }

    private void d(Level level, Marker marker, String str, Object[] objArr) {
        Throwable d = vhd.d(objArr);
        if (d != null) {
            d(level, marker, str, vhd.c(objArr), d);
        } else {
            d(level, marker, str, objArr, null);
        }
    }

    private void d(Level level, Marker marker, String str, Object[] objArr, Throwable th) {
        vhc vhcVar = new vhc();
        vhcVar.e(System.currentTimeMillis());
        vhcVar.d(level);
        vhcVar.d(this.b);
        vhcVar.d(this.d);
        vhcVar.b(marker);
        vhcVar.c(str);
        vhcVar.a(Thread.currentThread().getName());
        vhcVar.a(objArr);
        vhcVar.b(th);
        this.f17710a.add(vhcVar);
    }
}
