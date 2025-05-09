package com.study.apnea.utils.jni;

import android.util.Log;
import com.study.apnea.model.bean.algorithm.OsaAppInputS;
import com.study.apnea.model.bean.algorithm.OsaAppOutputS;

/* loaded from: classes10.dex */
public class OsaCalUtils {
    public static final String TAG = "OsaCalUtils";
    private static volatile OsaCalUtils instance;

    public native OsaAppOutputS osaCalAppResult(OsaAppInputS osaAppInputS);

    private OsaCalUtils() {
    }

    static {
        System.loadLibrary("apnea");
        Log.d(TAG, "load osacal library successfully!");
    }

    public static OsaCalUtils getInstance() {
        if (instance == null) {
            synchronized (OsaCalUtils.class) {
                instance = new OsaCalUtils();
            }
        }
        return instance;
    }
}
