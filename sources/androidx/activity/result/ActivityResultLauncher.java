package androidx.activity.result;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.core.app.ActivityOptionsCompat;

/* loaded from: classes2.dex */
public abstract class ActivityResultLauncher<I> {
    public abstract ActivityResultContract<I, ?> getContract();

    public abstract void launch(I i, ActivityOptionsCompat activityOptionsCompat);

    public abstract void unregister();

    public void launch(I i) {
        launch(i, null);
    }
}
