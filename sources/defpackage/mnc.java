package defpackage;

import com.huawei.basefitnessadvice.model.Topic;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.ChoreographedMultiActions;
import com.huawei.pluginfitnessadvice.ChoreographedSingleAction;
import com.huawei.pluginfitnessadvice.EquipmentWorkoutAction;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.RoadBook;
import com.huawei.pluginfitnessadvice.WorkoutPackageInfo;
import com.huawei.pluginfitnessadvice.cloudmodelparse.CloudDataParse;

/* loaded from: classes6.dex */
public class mnc {
    private static CloudDataParse<AtomicAction> d = new mnd();

    /* renamed from: a, reason: collision with root package name */
    private static CloudDataParse<AtomicAction> f15068a = new mnm();
    private static CloudDataParse<FitWorkout> e = new mnn();
    private static CloudDataParse<FitWorkout> c = new mnl();

    public static CloudDataParse<AtomicAction> d(int i) {
        if (i == 1) {
            return d;
        }
        return f15068a;
    }

    public static CloudDataParse<FitWorkout> c(int i) {
        if (i == 1 || i == 5) {
            return e;
        }
        return c;
    }

    public static CloudDataParse<ChoreographedMultiActions> a(int i) {
        return new mna(i);
    }

    public static CloudDataParse<ChoreographedSingleAction> e(int i) {
        return new mnb(i);
    }

    public static CloudDataParse<Topic> d() {
        return new mni();
    }

    public static CloudDataParse<EquipmentWorkoutAction> b() {
        return new mne();
    }

    public static CloudDataParse<WorkoutPackageInfo> e() {
        return new mno();
    }

    public static CloudDataParse<mmv> a() {
        return new mnj();
    }

    public static CloudDataParse<RoadBook> c() {
        return new mnk();
    }
}
