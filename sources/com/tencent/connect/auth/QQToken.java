package com.tencent.connect.auth;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.tencent.connect.common.Constants;
import com.tencent.open.log.SLog;
import com.tencent.open.utils.g;
import com.tencent.open.utils.k;
import com.tencent.open.utils.m;
import com.tencent.open.web.security.JniInterface;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class QQToken {
    public static final int AUTH_QQ = 2;
    public static final int AUTH_QZONE = 3;
    public static final int AUTH_WEB = 1;
    private static SharedPreferences g;

    /* renamed from: a, reason: collision with root package name */
    private String f11267a;
    private String b;
    private String c;
    private int d = 1;
    private long e = -1;
    private com.tencent.open.utils.a f;

    public QQToken(String str) {
        this.f11267a = str;
    }

    public boolean isSessionValid() {
        return this.b != null && System.currentTimeMillis() < this.e;
    }

    public String getAppId() {
        return this.f11267a;
    }

    public void setAppId(String str) {
        this.f11267a = str;
    }

    public String getAccessToken() {
        return this.b;
    }

    public void setAccessToken(String str, String str2) throws NumberFormatException {
        this.b = str;
        this.e = 0L;
        if (str2 != null) {
            this.e = System.currentTimeMillis() + (Long.parseLong(str2) * 1000);
        }
    }

    public String getOpenId() {
        return this.c;
    }

    public void setOpenId(String str) {
        this.c = str;
        com.tencent.open.b.b.a().a(str);
    }

    public String getOpenIdWithCache() {
        String openId = getOpenId();
        try {
            if (TextUtils.isEmpty(openId)) {
                JSONObject loadSession = loadSession(this.f11267a);
                if (loadSession != null) {
                    openId = loadSession.getString("openid");
                    if (!TextUtils.isEmpty(openId)) {
                        setOpenId(openId);
                    }
                }
                SLog.i("QQToken", "getOpenId from Session openId = " + openId + " appId = " + this.f11267a);
            } else {
                SLog.i("QQToken", "getOpenId from field openId = " + openId + " appId = " + this.f11267a);
            }
        } catch (Exception e) {
            SLog.i("QQToken", "getLocalOpenIdByAppId " + e.toString());
        }
        return openId;
    }

    public int getAuthSource() {
        return this.d;
    }

    public void setAuthSource(int i) {
        this.d = i;
    }

    public long getExpireTimeInSecond() {
        return this.e;
    }

    public boolean saveSession(JSONObject jSONObject) {
        try {
            if (this.f == null) {
                this.f = new com.tencent.open.utils.a(g.a());
            }
            return a(this.f11267a, jSONObject, this.f);
        } catch (Exception e) {
            SLog.i("QQToken", "login saveSession" + e.toString());
            return false;
        }
    }

    public JSONObject loadSession(String str) {
        try {
            if (this.f == null) {
                this.f = new com.tencent.open.utils.a(g.a());
            }
            return a(str, this.f);
        } catch (Exception e) {
            SLog.i("QQToken", "login loadSession" + e.toString());
            return null;
        }
    }

    private static SharedPreferences a() {
        SharedPreferences sharedPreferences;
        synchronized (QQToken.class) {
            if (g == null) {
                g = g.a().getSharedPreferences("token_info_file", 0);
            }
            sharedPreferences = g;
        }
        return sharedPreferences;
    }

    private static JSONObject a(String str, com.tencent.open.utils.a aVar) {
        String b;
        synchronized (QQToken.class) {
            if (g.a() == null) {
                SLog.i("QQToken", "loadJsonPreference context null");
                return null;
            }
            if (str == null) {
                SLog.i("QQToken", "loadJsonPreference prefKey is null");
                return null;
            }
            String string = a().getString(a(str), "");
            if (TextUtils.isEmpty(string)) {
                if (!JniInterface.isJniOk) {
                    k.a(AuthAgent.SECURE_LIB_FILE_NAME, AuthAgent.SECURE_LIB_NAME, 5);
                    JniInterface.loadSo();
                }
                if (!JniInterface.isJniOk) {
                    SLog.i("QQToken", "loadJsonPreference jni load fail SECURE_LIB_VERSION=5");
                    return null;
                }
                String c = c(str);
                String string2 = a().getString(c, "");
                try {
                    if (TextUtils.isEmpty(string2)) {
                        String b2 = b(str);
                        String string3 = a().getString(b2, "");
                        try {
                            if (TextUtils.isEmpty(string3)) {
                                SLog.i("QQToken", "loadJsonPreference oldDesValue null");
                                return null;
                            }
                            try {
                                b = JniInterface.d1(string3);
                                if (TextUtils.isEmpty(b)) {
                                    SLog.i("QQToken", "loadJsonPreference decodeResult d1 empty");
                                    return null;
                                }
                                a(str, new JSONObject(b), aVar);
                            } catch (Exception e) {
                                SLog.e("QQToken", "Catch Exception", e);
                                return null;
                            }
                        } finally {
                            a().edit().remove(b2).apply();
                        }
                    } else {
                        b = JniInterface.d2(string2);
                        a(str, new JSONObject(b), aVar);
                    }
                } catch (Exception e2) {
                    SLog.e("QQToken", "Catch Exception", e2);
                    return null;
                } finally {
                    a().edit().remove(c).apply();
                }
            } else {
                b = aVar.b(string);
            }
            try {
                JSONObject jSONObject = new JSONObject(b);
                SLog.i("QQToken", "loadJsonPreference sucess");
                return jSONObject;
            } catch (Exception e3) {
                SLog.i("QQToken", "loadJsonPreference decode " + e3.toString());
                return null;
            }
        }
    }

    private static boolean a(String str, JSONObject jSONObject, com.tencent.open.utils.a aVar) {
        synchronized (QQToken.class) {
            if (g.a() == null) {
                SLog.i("QQToken", "saveJsonPreference context null");
                return false;
            }
            if (str == null || jSONObject == null) {
                SLog.i("QQToken", "saveJsonPreference prefKey or jsonObject null");
                return false;
            }
            try {
                String string = jSONObject.getString(Constants.PARAM_EXPIRES_IN);
                if (!TextUtils.isEmpty(string)) {
                    jSONObject.put(Constants.PARAM_EXPIRES_TIME, System.currentTimeMillis() + (Long.parseLong(string) * 1000));
                    String a2 = a(str);
                    String a3 = aVar.a(jSONObject.toString());
                    if (a2.length() > 6 && a3 != null) {
                        a().edit().putString(a2, a3).commit();
                        SLog.i("QQToken", "saveJsonPreference sucess");
                        return true;
                    }
                    SLog.i("QQToken", "saveJsonPreference keyEncode or josnEncode null");
                    return false;
                }
                SLog.i("QQToken", "expires is null");
                return false;
            } catch (Exception e) {
                SLog.e("QQToken", "saveJsonPreference exception:" + e.toString());
                return false;
            }
        }
    }

    private static String a(String str) {
        return Base64.encodeToString(m.j(str), 2) + "_aes_google";
    }

    @Deprecated
    private static String b(String str) {
        return Base64.encodeToString(m.j(str), 2);
    }

    @Deprecated
    private static String c(String str) {
        return Base64.encodeToString(m.j(str), 2) + "_spkey";
    }

    public void removeSession(String str) {
        SharedPreferences.Editor edit = a().edit();
        edit.remove(c(str));
        edit.remove(c(str));
        edit.remove(a(str));
        edit.apply();
        SLog.i("QQToken", "removeSession sucess");
    }
}
