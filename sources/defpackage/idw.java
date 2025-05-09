package defpackage;

/* loaded from: classes.dex */
public class idw extends idy {
    public idw() {
        this(0, null, 0L, 0L);
    }

    public idw(int i, long j, long j2) {
        setType(i);
        setStartTime(j);
        setEndTime(j2);
    }

    public idw(int i, String str, long j, long j2) {
        setType(i);
        setStartTime(j);
        setEndTime(j2);
        setMetaData(str);
    }
}
