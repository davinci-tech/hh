package defpackage;

import android.text.TextUtils;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public final class lh {
    public final String c;
    public final String e;

    public lh(String str, String str2) {
        this.c = str;
        this.e = str2;
    }

    public JSONObject a() {
        if (TextUtils.isEmpty(this.e)) {
            return null;
        }
        try {
            return new JSONObject(this.e);
        } catch (Exception e) {
            ma.c(e);
            return null;
        }
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.e;
    }

    public String toString() {
        return String.format("<Letter envelop=%s body=%s>", this.c, this.e);
    }
}
