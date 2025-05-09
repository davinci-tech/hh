package com.huawei.profile.utils;

import android.content.Context;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.huawei.profile.utils.logger.DsLog;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/* loaded from: classes6.dex */
public abstract class AssetUrlReader {
    private static final String TAG = "AssetUrlReader";
    private static final String URL_ASSET_PATH = "base-urls.json";
    private static final Object LOCK = new Object();
    private static JsonObject urlsJson = new JsonObject();
    private static boolean isAssetVisited = false;

    public static void loadUrls(Context context) {
        synchronized (LOCK) {
            if (isAssetVisited) {
                DsLog.dt(TAG, "asset is visited.", new Object[0]);
                return;
            }
            JsonObject readAsset = readAsset(context, URL_ASSET_PATH);
            isAssetVisited = true;
            if (readAsset == null) {
                DsLog.et(TAG, "urlsJson is null", new Object[0]);
            } else {
                DsLog.dt(TAG, "load urls successfully！", new Object[0]);
                urlsJson = readAsset;
            }
        }
    }

    public static String getUrl(String str) {
        JsonElement jsonElement;
        synchronized (LOCK) {
            return (!urlsJson.has(str) || (jsonElement = urlsJson.get(str)) == null) ? "" : jsonElement.getAsString();
        }
    }

    private static JsonObject readAsset(Context context, String str) {
        try {
            InputStream open = context.getAssets().open(str);
            try {
                JsonObject asJsonObject = JsonParser.parseReader(new InputStreamReader(open, StandardCharsets.UTF_8)).getAsJsonObject();
                if (open != null) {
                    open.close();
                }
                return asJsonObject;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (open != null) {
                        try {
                            open.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        } catch (JsonParseException | IOException unused) {
            DsLog.et(TAG, "failed to load file", new Object[0]);
            return null;
        }
    }
}
