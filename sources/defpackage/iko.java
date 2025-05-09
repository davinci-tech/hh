package defpackage;

import android.text.TextUtils;
import android.util.LruCache;
import android.util.SparseIntArray;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class iko {

    /* renamed from: a, reason: collision with root package name */
    private LruCache<SparseIntArray, List<igr>> f13413a;

    private iko() {
        this.f13413a = new LruCache<>(20);
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        private static final iko f13414a = new iko();
    }

    public static iko a() {
        return c.f13414a;
    }

    public List<igr> a(int i, int i2) {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(i, i2);
        return this.f13413a.get(sparseIntArray);
    }

    public void b(int i, int i2, List<igr> list) {
        if (list == null) {
            return;
        }
        SparseIntArray sparseIntArray = new SparseIntArray(20);
        sparseIntArray.put(i, i2);
        this.f13413a.put(sparseIntArray, list);
    }

    public boolean e(int i, int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Debug_AuthorizationsCache", "checkAuth permission error");
            return false;
        }
        SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(i, i2);
        List<igr> list = this.f13413a.get(sparseIntArray);
        LogUtil.c("Debug_AuthorizationsCache", "checkAuth authorizationTables ", list);
        if (list == null) {
            return false;
        }
        for (igr igrVar : list) {
            if (str.equalsIgnoreCase(igrVar.d().a()) && i == igrVar.a() && i2 == igrVar.f()) {
                return true;
            }
        }
        return false;
    }

    public void e() {
        this.f13413a.evictAll();
    }
}
