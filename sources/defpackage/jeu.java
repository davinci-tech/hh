package defpackage;

import android.content.Context;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes9.dex */
public class jeu extends HwBaseManager {

    static class a {
        public static final jeu e = new jeu(BaseApplication.getContext());
    }

    private jeu(Context context) {
        super(context);
    }

    public static jeu d() {
        return a.e;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return Integer.valueOf(PrebakedEffectId.RT_COIN_DROP);
    }

    public void b(String str, int i, String str2) {
        LogUtil.a("SleepServiceMgr", "enter create():");
        createStorageDataTable(str, i, str2);
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public void onDestroy() {
        super.onDestroy();
    }
}
