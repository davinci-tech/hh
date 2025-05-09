package defpackage;

import com.huawei.unitedevice.config.IwearLinkConfig;
import java.util.Objects;

/* loaded from: classes7.dex */
public class snu {
    private static volatile IwearLinkConfig c;

    public static IwearLinkConfig e() {
        if (Objects.nonNull(c)) {
            return c;
        }
        return e.c;
    }

    public static void d(IwearLinkConfig iwearLinkConfig) {
        c = iwearLinkConfig;
    }

    static class e implements IwearLinkConfig {
        private static IwearLinkConfig c = new e();

        private e() {
        }
    }
}
