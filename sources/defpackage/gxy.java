package defpackage;

import com.huawei.hihealth.data.model.RelativeSportData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import java.io.Serializable;

/* loaded from: classes4.dex */
public class gxy implements Serializable {
    private static final long serialVersionUID = 6734716191747388003L;

    /* renamed from: a, reason: collision with root package name */
    private MotionPathSimplify f13000a;
    private RelativeSportData d;
    private String e;

    public RelativeSportData d() {
        return this.d;
    }

    public void c(RelativeSportData relativeSportData) {
        this.d = relativeSportData;
    }

    public String a() {
        return this.e;
    }

    public void d(String str) {
        this.e = str;
    }

    public MotionPathSimplify b() {
        return this.f13000a;
    }

    public void e(MotionPathSimplify motionPathSimplify) {
        this.f13000a = motionPathSimplify;
    }
}
