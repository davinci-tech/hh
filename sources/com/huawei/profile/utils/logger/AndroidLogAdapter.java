package com.huawei.profile.utils.logger;

import android.util.Log;

/* loaded from: classes6.dex */
class AndroidLogAdapter implements LogAdapter {
    AndroidLogAdapter() {
    }

    @Override // com.huawei.profile.utils.logger.LogAdapter
    public void verbose(String str, String str2) {
        Log.v(str, str2);
    }

    @Override // com.huawei.profile.utils.logger.LogAdapter
    public void debug(String str, String str2) {
        Log.d(str, str2);
    }

    @Override // com.huawei.profile.utils.logger.LogAdapter
    public void info(String str, String str2) {
        Log.i(str, str2);
    }

    @Override // com.huawei.profile.utils.logger.LogAdapter
    public void warn(String str, String str2) {
        Log.w(str, str2);
    }

    @Override // com.huawei.profile.utils.logger.LogAdapter
    public void error(String str, String str2) {
        Log.e(str, str2);
    }
}
