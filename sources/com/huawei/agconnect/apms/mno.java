package com.huawei.agconnect.apms;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes2.dex */
public class mno {
    public static final AgentLog abc = AgentLogManager.getAgentLog();
    public static Comparator<File> bcd = new abc();

    public class abc implements Comparator<File> {
        @Override // java.util.Comparator
        public int compare(File file, File file2) {
            long lastModified = file.lastModified() - file2.lastModified();
            if (lastModified == 0) {
                return 0;
            }
            return lastModified > 0 ? 1 : -1;
        }
    }

    public mno(String str) {
    }

    public List abc(Context context, boolean z) {
        if (TextUtils.isEmpty(".APMAnr")) {
            abc.debug("please set dir name.");
            return new ArrayList(0);
        }
        File abc2 = abc(context);
        if (abc2 == null) {
            return new ArrayList(0);
        }
        File[] listFiles = abc2.listFiles();
        if (z && listFiles != null) {
            Arrays.sort(listFiles, bcd);
        }
        return listFiles == null ? Collections.EMPTY_LIST : Arrays.asList(listFiles);
    }

    public final File abc(Context context) {
        File file = new File(context.getFilesDir(), ".APMAnr");
        if ((file.exists() && file.isDirectory()) || file.mkdir()) {
            return file;
        }
        abc.error("create dir failed.");
        return null;
    }
}
