package defpackage;

import android.content.Context;
import android.database.Cursor;
import com.huawei.hiai.awareness.AwarenessConstants;
import com.huawei.hihealth.HiHealthUserPermission;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class ijm {
    private ihz b;

    private ijm() {
        this.b = ihz.d();
    }

    static class a {
        private static final ijm b = new ijm();
    }

    public static ijm e(Context context) {
        return a.b;
    }

    public long b(HiHealthUserPermission hiHealthUserPermission) {
        LogUtil.c("Debug_HiHealthUserPermissionManager", "insertHealthUserPermissionData()");
        if (hiHealthUserPermission == null) {
            return 200004L;
        }
        return this.b.insert(iid.bzs_(hiHealthUserPermission));
    }

    public List<HiHealthUserPermission> b(int i) {
        LogUtil.c("Debug_HiHealthUserPermissionManager", "enter queryHealthUserPermissionData appId = ", Integer.valueOf(i));
        return iih.bBd_(this.b.query(b(), c(i), null, null, null));
    }

    public List<HiHealthUserPermission> c() {
        return iih.bBd_(this.b.query(null, null, null, null, null));
    }

    public boolean a(int i, int i2) {
        Cursor query = this.b.query(e(), new String[]{String.valueOf(i), String.valueOf(i2)}, null, null, null);
        if (query == null) {
            return false;
        }
        int count = query.getCount();
        LogUtil.a("Debug_HiHealthUserPermissionManager", "queryCursor count", Integer.valueOf(count));
        query.close();
        if (count == 1) {
            return true;
        }
        if (count == 0) {
            return false;
        }
        LogUtil.c("Debug_HiHealthUserPermissionManager", "queryAppScope scope ilegal");
        return true;
    }

    public HiHealthUserPermission e(int i, int i2) {
        Cursor query = this.b.query(e(), new String[]{String.valueOf(i), String.valueOf(i2)}, null, null, null);
        List<HiHealthUserPermission> bBd_ = iih.bBd_(query);
        HiHealthUserPermission hiHealthUserPermission = new HiHealthUserPermission();
        if (bBd_ != null && !bBd_.isEmpty()) {
            LogUtil.c("Debug_HiHealthUserPermissionManager", "queryScope hiHealthUserPermission", bBd_.toString());
            hiHealthUserPermission = bBd_.get(0);
        }
        if (query != null) {
            query.close();
        }
        return hiHealthUserPermission;
    }

    public boolean c(int i, int i2) {
        HiHealthUserPermission e = e(i, i2);
        return e != null && e.getAllowRead() == 1;
    }

    public boolean d(int i, int i2) {
        HiHealthUserPermission e = e(i, i2);
        return e != null && e.getAllowWrite() == 1;
    }

    public void e(int i, int i2, int i3, boolean z) {
        boolean a2 = a(i, i2);
        LogUtil.a("Debug_HiHealthUserPermissionManager", "insertOrUpdateUserPermissionRead type = ", Integer.valueOf(i3), ", scopeId = ", Integer.valueOf(i2), ", isChecked = ", Boolean.valueOf(z));
        if (a2) {
            d(i, i2, i3, z);
        } else {
            c(i, i2, i3, z);
        }
    }

    private void d(int i, int i2, int i3, boolean z) {
        LogUtil.a("Debug_HiHealthUserPermissionManager", "insertOrUpdateHasScope update");
        HiHealthUserPermission hiHealthUserPermission = new HiHealthUserPermission();
        hiHealthUserPermission.setAppId(i);
        if (z) {
            if (i3 == 0) {
                hiHealthUserPermission.setAllowRead(1);
                hiHealthUserPermission.setAllowWrite(e(i, i2).getAllowWrite());
            } else if (i3 == 1) {
                hiHealthUserPermission.setAllowWrite(1);
                hiHealthUserPermission.setAllowRead(e(i, i2).getAllowRead());
            } else {
                LogUtil.h("Debug_HiHealthUserPermissionManager", "insertOrUpdateHasScope isChecked type is other number");
            }
        } else if (i3 == 0) {
            hiHealthUserPermission.setAllowRead(2);
            hiHealthUserPermission.setAllowWrite(e(i, i2).getAllowWrite());
        } else if (i3 == 1) {
            hiHealthUserPermission.setAllowWrite(2);
            hiHealthUserPermission.setAllowRead(e(i, i2).getAllowRead());
        } else {
            LogUtil.h("Debug_HiHealthUserPermissionManager", "insertOrUpdateHasScope type is other number");
        }
        hiHealthUserPermission.setScopeId(i2);
        LogUtil.c("Debug_HiHealthUserPermissionManager", hiHealthUserPermission.toString());
        e(hiHealthUserPermission);
    }

    private void c(int i, int i2, int i3, boolean z) {
        LogUtil.a("Debug_HiHealthUserPermissionManager", "insertOrUpdateNoScope insert");
        HiHealthUserPermission hiHealthUserPermission = new HiHealthUserPermission();
        hiHealthUserPermission.setAppId(i);
        if (z) {
            if (i3 == 0) {
                hiHealthUserPermission.setAllowRead(1);
            } else if (i3 == 1) {
                hiHealthUserPermission.setAllowWrite(1);
            } else {
                LogUtil.h("Debug_HiHealthUserPermissionManager", "insertOrUpdateNoScope isChecked type is other number");
            }
        } else if (i3 == 0) {
            hiHealthUserPermission.setAllowRead(2);
        } else if (i3 == 1) {
            hiHealthUserPermission.setAllowWrite(2);
        } else {
            LogUtil.h("Debug_HiHealthUserPermissionManager", "insertOrUpdateNoScope type is other number");
        }
        hiHealthUserPermission.setScopeId(i2);
        b(hiHealthUserPermission);
    }

    public int e(HiHealthUserPermission hiHealthUserPermission) {
        if (hiHealthUserPermission == null) {
            return AwarenessConstants.ERROR_UNKNOWN_CODE;
        }
        return this.b.update(iid.bzs_(hiHealthUserPermission), e(), b(hiHealthUserPermission.getAppId(), hiHealthUserPermission.getScopeId()));
    }

    public int a(int i) {
        LogUtil.a("Debug_HiHealthUserPermissionManager", "enter deleteHealthUserPermissionData appId = ", Integer.valueOf(i));
        int delete = this.b.delete(b(), c(i));
        LogUtil.a("Debug_HiHealthUserPermissionManager", "deleteHealthUserPermissionData deleteResult", Integer.valueOf(delete));
        return delete;
    }

    private String[] c(int i) {
        return new String[]{Integer.toString(i)};
    }

    private String[] b(int i, int i2) {
        return new String[]{String.valueOf(i), String.valueOf(i2)};
    }

    private String e() {
        return "app_id =? and scope_id =? ";
    }

    private String b() {
        return "app_id =? ";
    }
}
