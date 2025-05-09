package defpackage;

import android.text.TextUtils;
import health.compact.a.LogUtil;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class sop {
    private static final Map<Integer, String> d = new HashMap(16) { // from class: sop.2
        {
            put(8, "ecg_data_file");
            put(14, "sleep_state.bin");
            put(15, "sleep_data.bin");
            put(16, "rrisqi_data.bin");
            put(17, "_gps.bin");
            put(18, "_pdr.bin");
        }
    };

    public static void a(File file, int i) {
        if (file == null) {
            LogUtil.a("BluetoothFileLabelManager", "file is null.");
        } else if (c(file.getName(), i)) {
            try {
                sov.a(file.getCanonicalPath(), "S2", 0);
            } catch (IOException unused) {
                LogUtil.e("BluetoothFileLabelManager", "setCommonFileFlag:getCanonicalPath IOException");
            }
        }
    }

    public static boolean c(String str, int i) {
        String str2 = d.get(Integer.valueOf(i));
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        return str.contains(str2);
    }
}
