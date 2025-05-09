package defpackage;

import android.content.Context;
import com.huawei.featureuserprofile.media.UserInfoMedia;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class bsj implements UserInfoMedia {
    private static bsj d;
    private Context e;

    private bsj(Context context) {
        this.e = context;
    }

    public static bsj b(Context context) {
        bsj bsjVar;
        synchronized (bsj.class) {
            if (d == null) {
                d = new bsj(context);
            }
            bsjVar = d;
        }
        return bsjVar;
    }

    @Override // com.huawei.featureuserprofile.media.UserInfoMedia
    public UserInfoMedia.UserInfoReader obtainReader() {
        return new bsi(this.e);
    }

    @Override // com.huawei.featureuserprofile.media.UserInfoMedia
    public UserInfoMedia.UserInfoWriter obtainWriter() {
        return new bsh(this.e);
    }

    public UserInfoMedia.UserInfoWriter d(int i) {
        if (i == 1) {
            bsh bshVar = new bsh(this.e);
            bshVar.d(268435456);
            return bshVar;
        }
        if (i == 2) {
            bsh bshVar2 = new bsh(this.e);
            bshVar2.d(536870912);
            return bshVar2;
        }
        LogUtil.h("HiHealthMedia", "obtainCustomeWriter only support two types");
        return null;
    }
}
