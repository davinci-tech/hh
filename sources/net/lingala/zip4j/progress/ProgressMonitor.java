package net.lingala.zip4j.progress;

/* loaded from: classes7.dex */
public class ProgressMonitor {

    /* renamed from: a, reason: collision with root package name */
    private boolean f15270a;
    private Exception b;
    private boolean c;
    private String d;
    private Task e;
    private long f;
    private long g;
    private int h;
    private Result i;
    private State j;

    public enum Result {
        SUCCESS,
        WORK_IN_PROGRESS,
        ERROR,
        CANCELLED
    }

    public enum State {
        READY,
        BUSY
    }

    public enum Task {
        NONE,
        ADD_ENTRY,
        REMOVE_ENTRY,
        CALCULATE_CRC,
        EXTRACT_ENTRY,
        MERGE_ZIP_FILES,
        SET_COMMENT,
        RENAME_FILE
    }

    public ProgressMonitor() {
        c();
    }

    public void e(long j) {
        long j2 = this.f + j;
        this.f = j2;
        long j3 = this.g;
        if (j3 > 0) {
            int i = (int) ((j2 * 100) / j3);
            this.h = i;
            if (i > 100) {
                this.h = 100;
            }
        }
        while (this.c) {
            try {
                Thread.sleep(150L);
            } catch (InterruptedException unused) {
            }
        }
    }

    public void a() {
        this.i = Result.SUCCESS;
        this.h = 100;
        c();
    }

    public void a(Exception exc) {
        this.i = Result.ERROR;
        this.b = exc;
        c();
    }

    public void b() {
        c();
        this.d = null;
        this.g = 0L;
        this.f = 0L;
        this.h = 0;
    }

    private void c() {
        this.e = Task.NONE;
        this.j = State.READY;
    }

    public State e() {
        return this.j;
    }

    public void b(State state) {
        this.j = state;
    }

    public void c(long j) {
        this.g = j;
    }

    public void c(Task task) {
        this.e = task;
    }

    public void a(String str) {
        this.d = str;
    }

    public void b(Result result) {
        this.i = result;
    }

    public boolean d() {
        return this.f15270a;
    }
}
