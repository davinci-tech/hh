package defpackage;

import android.content.Context;
import android.content.Intent;
import android.util.SparseArray;
import com.huawei.haf.common.security.SecurityConstant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
final class yp {

    /* renamed from: a, reason: collision with root package name */
    private final Context f17761a;
    private final SparseArray<yt> e = new SparseArray<>();
    private final Object c = new Object();

    yp(Context context) {
        this.f17761a = context;
    }

    void a(int i, yt ytVar) {
        if (i != 0) {
            synchronized (this.c) {
                if (this.e.get(i) == null) {
                    this.e.put(i, ytVar);
                }
            }
        }
    }

    void a(int i, int i2) {
        synchronized (this.c) {
            yt ytVar = this.e.get(i);
            if (ytVar != null) {
                ytVar.e(i2);
                if (i2 == 7 || i2 == 6 || i2 == 10) {
                    this.e.remove(i);
                }
            }
        }
    }

    boolean b() {
        synchronized (this.c) {
            for (int size = this.e.size() - 1; size >= 0; size--) {
                int b = this.e.valueAt(size).b();
                if (b != 2 && b != 1) {
                }
                return true;
            }
            return false;
        }
    }

    yt a(int i) {
        yt ytVar;
        synchronized (this.c) {
            ytVar = this.e.get(i);
        }
        return ytVar;
    }

    List<yt> a() {
        List<yt> et_;
        synchronized (this.c) {
            et_ = et_(this.e);
        }
        return et_;
    }

    boolean d(List<String> list) {
        synchronized (this.c) {
            for (int size = this.e.size() - 1; size >= 0; size--) {
                List<String> c = this.e.valueAt(size).c();
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    if (c.contains(it.next())) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    void c(yt ytVar) {
        Intent intent = new Intent();
        intent.setPackage(this.f17761a.getPackageName());
        intent.putExtra("session_state", yt.er_(ytVar));
        intent.setAction("com.huawei.android.appbundle.splitinstall.receiver.SplitInstallUpdateIntentService");
        intent.addFlags(1073741824);
        intent.addFlags(2097152);
        this.f17761a.sendBroadcast(intent, SecurityConstant.d);
    }

    private static <C> List<C> et_(SparseArray<C> sparseArray) {
        ArrayList arrayList = new ArrayList(sparseArray.size());
        for (int i = 0; i < sparseArray.size(); i++) {
            arrayList.add(sparseArray.valueAt(i));
        }
        return arrayList;
    }
}
