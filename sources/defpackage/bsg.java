package defpackage;

import android.content.Context;
import com.huawei.featureuserprofile.media.UserInfoMedia;
import com.huawei.featureuserprofile.media.account.AccountReader;

/* loaded from: classes3.dex */
public class bsg implements UserInfoMedia {
    private static bsg e;
    private Context d;

    @Override // com.huawei.featureuserprofile.media.UserInfoMedia
    public UserInfoMedia.UserInfoWriter obtainWriter() {
        return null;
    }

    private bsg(Context context) {
        this.d = context;
    }

    public static bsg b(Context context) {
        bsg bsgVar;
        synchronized (bsg.class) {
            if (e == null) {
                e = new bsg(context);
            }
            bsgVar = e;
        }
        return bsgVar;
    }

    @Override // com.huawei.featureuserprofile.media.UserInfoMedia
    public UserInfoMedia.UserInfoReader obtainReader() {
        return new AccountReader(this.d);
    }
}
