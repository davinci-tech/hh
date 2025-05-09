package defpackage;

import org.slf4j.Marker;
import org.slf4j.event.Level;
import org.slf4j.event.LoggingEvent;

/* loaded from: classes7.dex */
public class vhc implements LoggingEvent {

    /* renamed from: a, reason: collision with root package name */
    String f17709a;
    Level b;
    vhh c;
    Object[] d;
    Marker e;
    Throwable f;
    String g;
    String h;
    long j;

    @Override // org.slf4j.event.LoggingEvent
    public Level getLevel() {
        return this.b;
    }

    public void d(Level level) {
        this.b = level;
    }

    @Override // org.slf4j.event.LoggingEvent
    public Marker getMarker() {
        return this.e;
    }

    public void b(Marker marker) {
        this.e = marker;
    }

    @Override // org.slf4j.event.LoggingEvent
    public String getLoggerName() {
        return this.f17709a;
    }

    public void d(String str) {
        this.f17709a = str;
    }

    public vhh a() {
        return this.c;
    }

    public void d(vhh vhhVar) {
        this.c = vhhVar;
    }

    @Override // org.slf4j.event.LoggingEvent
    public String getMessage() {
        return this.h;
    }

    public void c(String str) {
        this.h = str;
    }

    @Override // org.slf4j.event.LoggingEvent
    public Object[] getArgumentArray() {
        return this.d;
    }

    public void a(Object[] objArr) {
        this.d = objArr;
    }

    @Override // org.slf4j.event.LoggingEvent
    public long getTimeStamp() {
        return this.j;
    }

    public void e(long j) {
        this.j = j;
    }

    @Override // org.slf4j.event.LoggingEvent
    public String getThreadName() {
        return this.g;
    }

    public void a(String str) {
        this.g = str;
    }

    @Override // org.slf4j.event.LoggingEvent
    public Throwable getThrowable() {
        return this.f;
    }

    public void b(Throwable th) {
        this.f = th;
    }
}
