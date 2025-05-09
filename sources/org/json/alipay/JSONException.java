package org.json.alipay;

/* loaded from: classes8.dex */
public class JSONException extends Exception {
    public Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public JSONException(Throwable th) {
        super(th.getMessage());
        this.cause = th;
    }

    public JSONException(String str) {
        super(str);
    }
}
