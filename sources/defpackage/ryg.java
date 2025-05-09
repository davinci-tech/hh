package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.template.data.PageDataObserver;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public final class ryg {

    /* renamed from: a, reason: collision with root package name */
    private static final ryg f16964a = new ryg();
    private final Object c = new Object();
    private boolean b = false;
    private final ConcurrentHashMap<Integer, Vector<PageDataObserver>> e = new ConcurrentHashMap<>();

    private ryg() {
    }

    public static ryg b() {
        return f16964a;
    }

    public void e(int i, PageDataObserver pageDataObserver) {
        synchronized (this) {
            if (pageDataObserver == null) {
                LogUtil.b("PageDataObservable", "addObserver : observer is null");
                return;
            }
            Vector<PageDataObserver> vector = this.e.get(Integer.valueOf(i));
            if (vector == null) {
                vector = new Vector<>();
                this.e.putIfAbsent(Integer.valueOf(i), vector);
            }
            if (!vector.contains(pageDataObserver)) {
                vector.addElement(pageDataObserver);
            }
        }
    }

    public void e(PageDataObserver pageDataObserver) {
        synchronized (this) {
            Iterator<Map.Entry<Integer, Vector<PageDataObserver>>> it = this.e.entrySet().iterator();
            while (it.hasNext()) {
                Vector<PageDataObserver> value = it.next().getValue();
                if (value != null) {
                    value.removeElement(pageDataObserver);
                }
                if (koq.b(value)) {
                    it.remove();
                }
            }
        }
    }

    public void a(int i, List<cdy> list) {
        synchronized (this.c) {
            if (e()) {
                Vector<PageDataObserver> vector = this.e.get(Integer.valueOf(i));
                if (vector == null) {
                    return;
                }
                List asList = Arrays.asList(vector.toArray());
                d();
                for (int size = asList.size() - 1; size >= 0; size--) {
                    if (asList.get(size) instanceof PageDataObserver) {
                        ((PageDataObserver) asList.get(size)).update(this, list);
                    }
                }
            }
        }
    }

    protected void c() {
        synchronized (this) {
            this.b = true;
        }
    }

    public boolean e() {
        boolean z;
        synchronized (this) {
            z = this.b;
        }
        return z;
    }

    protected void d() {
        synchronized (this) {
            this.b = false;
        }
    }
}
