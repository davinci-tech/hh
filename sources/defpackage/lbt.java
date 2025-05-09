package defpackage;

import android.content.Context;
import com.huawei.indoorequip.datastruct.SupportDataRange;
import com.huawei.indoorequip.ui.view.CombineSportEquipItemDrawer;
import com.huawei.indoorequip.ui.view.SportEquipItemDrawer;
import java.util.Map;

/* loaded from: classes5.dex */
public class lbt extends CombineSportEquipItemDrawer {
    public lbt(Context context, boolean z) {
        super(context, z);
    }

    public lbt(Context context, int i, boolean z, SupportDataRange supportDataRange) {
        super(context, i, z, supportDataRange);
    }

    public void e(SportEquipItemDrawer sportEquipItemDrawer, Map<Integer, Object> map, int[] iArr) {
        setPageItem(iArr, map);
        fillTitleContainer(sportEquipItemDrawer);
    }

    public void c(SportEquipItemDrawer sportEquipItemDrawer, Map<Integer, Object> map, int[] iArr) {
        setPageItem(iArr, map);
        fillTitleContainer(sportEquipItemDrawer);
    }
}
