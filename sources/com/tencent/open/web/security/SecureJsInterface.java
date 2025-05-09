package com.tencent.open.web.security;

import com.tencent.open.b;
import com.tencent.open.log.SLog;

/* loaded from: classes8.dex */
public class SecureJsInterface extends b.C0290b {
    public static boolean isPWDEdit = false;

    /* renamed from: a, reason: collision with root package name */
    private String f11387a;

    @Override // com.tencent.open.b.C0290b
    public boolean customCallback() {
        return true;
    }

    public void curPosFromJS(String str) {
        int i;
        SLog.d("openSDK_LOG.SecureJsInterface", "-->curPosFromJS: " + str);
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            SLog.e("openSDK_LOG.SecureJsInterface", "-->curPosFromJS number format exception.", e);
            i = -1;
        }
        if (i < 0) {
            throw new RuntimeException("position is illegal.");
        }
        boolean z = a.c;
        if (a.b) {
            if (Boolean.valueOf(JniInterface.BackSpaceChar(a.b, i)).booleanValue()) {
                a.b = false;
            }
        } else {
            String str2 = a.f11388a;
            this.f11387a = str2;
            JniInterface.insetTextToArray(i, str2, str2.length());
            SLog.v("openSDK_LOG.SecureJsInterface", "curPosFromJS mKey: " + this.f11387a);
        }
    }

    public void isPasswordEdit(String str) {
        int i;
        SLog.i("openSDK_LOG.SecureJsInterface", "-->is pswd edit, flag: " + str);
        try {
            i = Integer.parseInt(str);
        } catch (Exception e) {
            SLog.e("openSDK_LOG.SecureJsInterface", "-->is pswd edit exception: " + e.getMessage());
            i = -1;
        }
        if (i != 0 && i != 1) {
            throw new RuntimeException("is pswd edit flag is illegal.");
        }
        if (i == 0) {
            isPWDEdit = false;
        } else if (i == 1) {
            isPWDEdit = true;
        }
    }

    public void clearAllEdit() {
        SLog.i("openSDK_LOG.SecureJsInterface", "-->clear all edit.");
        try {
            JniInterface.clearAllPWD();
        } catch (Exception e) {
            SLog.e("openSDK_LOG.SecureJsInterface", "-->clear all edit exception: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String getMD5FromNative() {
        SLog.i("openSDK_LOG.SecureJsInterface", "-->get md5 form native");
        try {
            String pWDKeyToMD5 = JniInterface.getPWDKeyToMD5(null);
            SLog.v("openSDK_LOG.SecureJsInterface", "-->getMD5FromNative, MD5= " + pWDKeyToMD5);
            return pWDKeyToMD5;
        } catch (Exception e) {
            SLog.e("openSDK_LOG.SecureJsInterface", "-->get md5 form native exception: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
