package defpackage;

import com.huawei.health.basesport.viewmodel.BaseSportingViewModel;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.AiActionTrainViewModel;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.CrossJumpViewModel;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.LongJumpViewModel;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.SkippingViewModel;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.StandingFlexionViewModel;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.SupineLegRaiseModel;

/* loaded from: classes4.dex */
public class hoq {
    public static <T extends BaseSportingViewModel> Class<T> b(int i) {
        if (i == 283) {
            return SkippingViewModel.class;
        }
        if (i == 400000) {
            return StandingFlexionViewModel.class;
        }
        if (i == 400001) {
            return SupineLegRaiseModel.class;
        }
        if (i == 400002) {
            return LongJumpViewModel.class;
        }
        if (i == 400003) {
            return CrossJumpViewModel.class;
        }
        if (i == 700001) {
            return AiActionTrainViewModel.class;
        }
        return null;
    }
}
