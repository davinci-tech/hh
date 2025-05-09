package defpackage;

import com.huawei.featureuserprofile.media.UserInfoMedia;
import com.huawei.up.model.UserInfomation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class bwd {
    private static final Object b = new Object();
    private List<UserInfoMedia.UserInfoReader.Callback> d = new ArrayList();

    public void a(UserInfoMedia.UserInfoReader.Callback callback) {
        synchronized (b) {
            this.d.add(callback);
        }
    }

    private void b(int i, UserInfomation userInfomation) {
        synchronized (b) {
            for (UserInfoMedia.UserInfoReader.Callback callback : this.d) {
                if (i == 0) {
                    callback.onSuccess(userInfomation);
                } else {
                    callback.onFail(i);
                }
            }
            this.d.clear();
        }
    }

    public void d() {
        synchronized (b) {
            Iterator<UserInfoMedia.UserInfoReader.Callback> it = this.d.iterator();
            while (it.hasNext()) {
                it.next().onFail(-1);
            }
            this.d.clear();
        }
    }

    public void e(int i, UserInfomation userInfomation) {
        synchronized (b) {
            b(i, userInfomation);
        }
    }
}
