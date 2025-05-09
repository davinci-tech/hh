package defpackage;

import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.devicesdk.entity.ScanMode;
import com.huawei.devicesdk.entity.SendMode;
import health.compact.a.LogUtil;
import java.util.Map;

/* loaded from: classes3.dex */
public class bkp<T, U> {
    public U d(T t) {
        if (t == null) {
            return null;
        }
        try {
            Map<T, String> c = c(t);
            if (c == null) {
                return null;
            }
            return (U) Class.forName(c.get(t)).newInstance();
        } catch (ClassNotFoundException unused) {
            LogUtil.e("StrategyFactory", "StrategyFactory ClassNotFoundException");
            return null;
        } catch (IllegalAccessException unused2) {
            LogUtil.e("StrategyFactory", "StrategyFactory IllegalAccessException");
            return null;
        } catch (InstantiationException unused3) {
            LogUtil.e("StrategyFactory", "StrategyFactory InstantiationException");
            return null;
        }
    }

    private Map<T, String> c(T t) {
        if (t instanceof ScanMode) {
            return (Map<T, String>) bgo.a();
        }
        if (t instanceof ConnectMode) {
            return (Map<T, String>) bgo.e();
        }
        if (t instanceof SendMode) {
            return (Map<T, String>) bgo.b();
        }
        LogUtil.e("StrategyFactory", "StrategyFactory getStrategyConfigMap null");
        return null;
    }
}
