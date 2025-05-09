package defpackage;

import android.database.Cursor;
import com.huawei.hihealth.WearKitPermission;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class ikc {
    private iig c;

    private ikc() {
        this.c = iig.a();
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static final ikc f13406a = new ikc();
    }

    public static ikc b() {
        return a.f13406a;
    }

    public List<WearKitPermission> c() {
        return iih.bBg_(this.c.query(null, null, null, null, null));
    }

    public List<WearKitPermission> b(int i) {
        LogUtil.c("Debug_WearKitManager", "enter queryWearKitData appId = ", Integer.valueOf(i));
        return iih.bBg_(this.c.query(d(), e(i), null, null, null));
    }

    public void a(int i, int i2, boolean z) {
        long d;
        WearKitPermission b = b(i, i2);
        LogUtil.c("Debug_WearKitManager", "appId = ", Integer.valueOf(i));
        if (b != null) {
            LogUtil.a("Debug_WearKitManager", "insertOrUpdate update");
            if (z) {
                b.setAllow(1);
            } else {
                b.setAllow(0);
            }
            b.setModifiedTime(String.valueOf(System.currentTimeMillis()));
            d = c(b);
        } else {
            LogUtil.a("Debug_WearKitManager", "insertOrUpdate insert");
            WearKitPermission wearKitPermission = new WearKitPermission();
            wearKitPermission.setAppId(i);
            if (z) {
                wearKitPermission.setAllow(1);
            } else {
                wearKitPermission.setAllow(0);
            }
            wearKitPermission.setModifiedTime(String.valueOf(System.currentTimeMillis()));
            wearKitPermission.setScopeId(i2);
            d = d(wearKitPermission);
        }
        LogUtil.a("Debug_WearKitManager", "insertOrUpdate result = ", Long.valueOf(d));
    }

    public int a(int i) {
        return this.c.delete(e(), e(i));
    }

    private WearKitPermission b(int i, int i2) {
        Cursor query = this.c.query(a(), new String[]{String.valueOf(i), String.valueOf(i2)}, null, null, null);
        List<WearKitPermission> bBg_ = iih.bBg_(query);
        if (query != null) {
            query.close();
        }
        if (koq.b(bBg_)) {
            return null;
        }
        return bBg_.get(0);
    }

    private int c(WearKitPermission wearKitPermission) {
        return this.c.update(iid.bzW_(wearKitPermission), a(), e(wearKitPermission.getAppId(), wearKitPermission.getScopeId()));
    }

    private long d(WearKitPermission wearKitPermission) {
        return this.c.insert(iid.bzW_(wearKitPermission));
    }

    private String[] e(int i) {
        return new String[]{String.valueOf(i)};
    }

    private String[] e(int i, int i2) {
        return new String[]{String.valueOf(i), String.valueOf(i2)};
    }

    private String a() {
        return "app_id =? and scope_id =? ";
    }

    private String e() {
        return "app_id =? ";
    }

    private String d() {
        return "app_id =? ";
    }
}
