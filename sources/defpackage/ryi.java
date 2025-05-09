package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.List;

/* loaded from: classes7.dex */
public class ryi extends Handler {
    ryi() {
        super(Looper.getMainLooper());
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == 4097) {
            int i = message.arg1;
            List<cdy> d = ryn.d().d(i);
            ryg.b().c();
            ryg.b().a(i, d);
        }
    }
}
