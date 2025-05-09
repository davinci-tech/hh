package defpackage;

import android.database.ContentObserver;
import android.util.Log;

/* loaded from: classes7.dex */
public class li extends ContentObserver {
    public int b;
    public lm d;
    public String e;

    public li(lm lmVar, int i, String str) {
        super(null);
        this.d = lmVar;
        this.b = i;
        this.e = str;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        lm lmVar = this.d;
        if (lmVar != null) {
            lmVar.c(this.b, this.e);
        } else {
            Log.e("VMS_IDLG_SDK_Observer", "mIdentifierIdClient is null");
        }
    }
}
