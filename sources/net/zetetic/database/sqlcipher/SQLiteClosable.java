package net.zetetic.database.sqlcipher;

import java.io.Closeable;

/* loaded from: classes2.dex */
public abstract class SQLiteClosable implements Closeable {
    private int mReferenceCount = 1;

    protected abstract void onAllReferencesReleased();

    @Deprecated
    protected void onAllReferencesReleasedFromContainer() {
        onAllReferencesReleased();
    }

    public void acquireReference() {
        synchronized (this) {
            int i = this.mReferenceCount;
            if (i <= 0) {
                throw new IllegalStateException("attempt to re-open an already-closed object: " + this);
            }
            this.mReferenceCount = i + 1;
        }
    }

    public void releaseReference() {
        boolean z;
        synchronized (this) {
            int i = this.mReferenceCount - 1;
            this.mReferenceCount = i;
            z = i == 0;
        }
        if (z) {
            onAllReferencesReleased();
        }
    }

    @Deprecated
    public void releaseReferenceFromContainer() {
        boolean z;
        synchronized (this) {
            int i = this.mReferenceCount - 1;
            this.mReferenceCount = i;
            z = i == 0;
        }
        if (z) {
            onAllReferencesReleasedFromContainer();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        releaseReference();
    }
}
