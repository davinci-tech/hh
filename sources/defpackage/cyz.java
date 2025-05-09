package defpackage;

import android.os.Bundle;
import com.huawei.btsportdevice.callback.MessageOrStateCallback;
import java.util.List;

/* loaded from: classes3.dex */
public class cyz {

    /* renamed from: a, reason: collision with root package name */
    private List<Integer> f11546a;
    private boolean d;
    private MessageOrStateCallback e;

    public cyz(MessageOrStateCallback messageOrStateCallback, List<Integer> list) {
        this.d = false;
        this.e = messageOrStateCallback;
        this.f11546a = list;
        if (list == null) {
            this.d = true;
        }
    }

    public boolean b(int i) {
        return this.d || this.f11546a.contains(Integer.valueOf(i));
    }

    public void Sh_(int i, Bundle bundle) {
        this.e.onNewMessage(i, bundle);
    }

    public void e(String str) {
        this.e.onStateChange(str);
    }
}
