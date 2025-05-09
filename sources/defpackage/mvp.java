package defpackage;

import com.huawei.health.socialshare.model.socialsharebean.ShareDataInfo;
import java.io.Serializable;

/* loaded from: classes6.dex */
public class mvp extends ShareDataInfo implements Serializable {
    private static final long serialVersionUID = 2809903449040264040L;

    /* renamed from: a, reason: collision with root package name */
    private int f15205a;

    public void a(int i) {
        this.f15205a = i;
    }

    public int e() {
        return this.f15205a;
    }
}
