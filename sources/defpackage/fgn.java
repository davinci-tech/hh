package defpackage;

import health.compact.a.SharedPreferenceManager;

/* loaded from: classes7.dex */
public class fgn {
    public void d(String str, String str2) {
        SharedPreferenceManager.c(String.valueOf(101010), "key_audio_res_path" + str, str2);
    }

    public int a(String str) {
        return SharedPreferenceManager.a(String.valueOf(101010), "key_base_res_version" + str, 0);
    }

    public void a(String str, int i) {
        SharedPreferenceManager.b(String.valueOf(101010), "key_base_res_version" + str, i);
    }

    public int d(String str) {
        return SharedPreferenceManager.a(String.valueOf(101010), "key_base_service_version" + str, 0);
    }

    public void d(String str, int i) {
        SharedPreferenceManager.b(String.valueOf(101010), "key_base_service_version" + str, i);
    }

    public void b(String str, String str2) {
        SharedPreferenceManager.c(String.valueOf(101010), "key_audio_config_json" + str, str2);
    }
}
