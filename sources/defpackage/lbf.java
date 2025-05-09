package defpackage;

import com.huawei.indoorequip.viewmodel.IndoorEquipViewModel;

/* loaded from: classes5.dex */
public class lbf {
    public static boolean c(IndoorEquipViewModel indoorEquipViewModel) {
        return a(indoorEquipViewModel, 0);
    }

    public static boolean d(IndoorEquipViewModel indoorEquipViewModel) {
        return a(indoorEquipViewModel, 1);
    }

    public static boolean a(IndoorEquipViewModel indoorEquipViewModel) {
        return a(indoorEquipViewModel, 2);
    }

    private static boolean a(IndoorEquipViewModel indoorEquipViewModel, int i) {
        return indoorEquipViewModel != null && indoorEquipViewModel.getSportTarget() == i;
    }
}
