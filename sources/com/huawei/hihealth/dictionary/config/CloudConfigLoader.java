package com.huawei.hihealth.dictionary.config;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.json.JsonSanitizer;
import com.huawei.hihealth.dictionary.model.HiHealthDictionary;
import health.compact.a.util.LogUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/* loaded from: classes.dex */
public class CloudConfigLoader implements ConfigureLoader {
    @Override // com.huawei.hihealth.dictionary.config.ConfigureLoader
    public HiHealthDictionary loadConfig(Context context) {
        if (context == null) {
            LogUtil.e("HiH_CloudConfigLoader", "cloud loadConfig context null");
            return null;
        }
        String c = c(context.getFilesDir() + File.separator + "healthcloud" + File.separator + "com.huawei.health_common_config" + File.separator + "dict_config.txt");
        if (TextUtils.isEmpty(c)) {
            LogUtil.e("HiH_CloudConfigLoader", "read cloud json config failed.");
            return null;
        }
        try {
            return (HiHealthDictionary) new Gson().fromJson(JsonSanitizer.sanitize(c), HiHealthDictionary.class);
        } catch (JsonSyntaxException unused) {
            LogUtil.e("HiH_CloudConfigLoader", "parse json config failed for error format.");
            return null;
        } catch (Exception unused2) {
            LogUtil.e("HiH_CloudConfigLoader", "parse json config failed for unknown error.");
            return null;
        }
    }

    private String c(String str) {
        String str2;
        FileInputStream fileInputStream;
        str2 = "";
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(str);
            } catch (IOException e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
            fileInputStream = fileInputStream2;
        }
        try {
            byte[] bArr = new byte[fileInputStream.available()];
            str2 = fileInputStream.read(bArr) > 0 ? new String(bArr, StandardCharsets.UTF_8) : "";
            try {
                fileInputStream.close();
            } catch (IOException e2) {
                LogUtil.c("HiH_CloudConfigLoader", "getStringFile e is ", e2.getMessage());
            }
        } catch (IOException e3) {
            e = e3;
            fileInputStream2 = fileInputStream;
            LogUtil.c("HiH_CloudConfigLoader", "getStringFile e is ", e.getMessage());
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (IOException e4) {
                    LogUtil.c("HiH_CloudConfigLoader", "getStringFile e is ", e4.getMessage());
                }
            }
            return str2;
        } catch (Throwable th2) {
            th = th2;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e5) {
                    LogUtil.c("HiH_CloudConfigLoader", "getStringFile e is ", e5.getMessage());
                }
            }
            throw th;
        }
        return str2;
    }

    @Override // com.huawei.hihealth.dictionary.config.ConfigureLoader
    public String getName() {
        return "cloud_json_config";
    }
}
