package defpackage;

import com.huawei.pluginachievement.manager.model.AchieveUserLevelInfo;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class mkk {

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<mki> f15038a;
    private mku b;
    private AchieveUserLevelInfo e;

    public void d(AchieveUserLevelInfo achieveUserLevelInfo) {
        this.e = achieveUserLevelInfo;
    }

    public mku d() {
        return this.b;
    }

    public void b(mku mkuVar) {
        this.b = mkuVar;
    }

    public ArrayList<mki> e() {
        return this.f15038a;
    }

    public void b(ArrayList<mki> arrayList) {
        this.f15038a = arrayList;
    }

    public String toString() {
        return "LevelLocationRelationShipData{userLevelInfo=" + this.b + ", LevelListInfo=" + this.f15038a + '}';
    }
}
