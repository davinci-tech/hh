package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class jyu {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<Integer, String> f14205a = new HashMap(16) { // from class: jyu.4
        private static final long serialVersionUID = 2580757970674450558L;

        {
            put(8, "ecg_data_file");
            put(14, "sleep_state.bin");
            put(15, "sleep_data.bin");
            put(16, "rrisqi_data.bin");
            put(17, "_gps.bin");
            put(18, "_pdr.bin");
        }
    };

    public static void e(File file, int i) {
        if (file == null) {
            LogUtil.h("BluetoothFileLabelManager", "file is null.");
        } else if (a(file.getName(), i)) {
            sqc.c(file.getPath(), "S2", 0);
        }
    }

    public static boolean a(String str, int i) {
        String str2 = f14205a.get(Integer.valueOf(i));
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        return str.contains(str2);
    }
}
