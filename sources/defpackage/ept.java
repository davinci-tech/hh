package defpackage;

import com.huawei.basefitnessadvice.model.intplan.UserProfile;
import com.huawei.health.plan.model.intplan.UserProfileBean;

/* loaded from: classes3.dex */
public class ept implements UserProfile {

    /* renamed from: a, reason: collision with root package name */
    private float f12151a;
    private int b;
    private int c;
    private float d;
    private int e;

    @Override // com.huawei.basefitnessadvice.model.intplan.UserProfile
    public int getGender() {
        return this.e;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.UserProfile
    public int getAge() {
        return this.b;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.UserProfile
    public int getHeight() {
        return this.c;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.UserProfile
    public float getWeight() {
        return this.d;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.UserProfile
    public float getBfr() {
        return this.f12151a;
    }

    public void d(int i) {
        this.e = i;
    }

    public void e(int i) {
        this.b = i;
    }

    public void c(int i) {
        this.c = i;
    }

    public void e(float f) {
        this.d = f;
    }

    public void d(float f) {
        this.f12151a = f;
    }

    public ept e(UserProfileBean userProfileBean) {
        if (userProfileBean != null) {
            e(userProfileBean.getAge());
            d(userProfileBean.getBfr());
            d(userProfileBean.getGender());
            c(userProfileBean.getHeight());
            e(userProfileBean.getWeight());
        }
        return this;
    }
}
