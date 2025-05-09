package com.huawei.agconnect.config.impl;

import android.content.Context;
import com.huawei.agconnect.AGCRoutePolicy;
import com.huawei.agconnect.AGConnectOptions;
import com.huawei.agconnect.JsonProcessingFactory;
import com.huawei.agconnect.config.ConfigReader;
import com.huawei.agconnect.core.Service;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class b implements AGConnectOptions {

    /* renamed from: a, reason: collision with root package name */
    private final String f1703a;
    private final Context b;
    private final String c;
    private final AGCRoutePolicy d;
    private final ConfigReader e;
    private final e f;
    private final Map<String, String> g;
    private final List<Service> h;
    private final Map<String, String> i = new HashMap();

    @Override // com.huawei.agconnect.AGConnectOptions
    public String getString(String str, String str2) {
        if (str == null) {
            return str2;
        }
        String fixPath = Utils.fixPath(str);
        String str3 = this.g.get(fixPath);
        if (str3 != null) {
            return str3;
        }
        String a2 = a(fixPath);
        if (a2 != null) {
            return a2;
        }
        String string = this.e.getString(fixPath, str2);
        return e.a(string) ? this.f.decrypt(string, str2) : string;
    }

    @Override // com.huawei.agconnect.AGConnectOptions
    public String getString(String str) {
        return getString(str, null);
    }

    @Override // com.huawei.agconnect.AGConnectOptions
    public AGCRoutePolicy getRoutePolicy() {
        AGCRoutePolicy aGCRoutePolicy = this.d;
        return aGCRoutePolicy == null ? AGCRoutePolicy.UNKNOWN : aGCRoutePolicy;
    }

    @Override // com.huawei.agconnect.AGConnectOptions
    public String getPackageName() {
        return this.c;
    }

    @Override // com.huawei.agconnect.AGConnectOptions
    public int getInt(String str, int i) {
        try {
            return Integer.parseInt(getString(str, String.valueOf(i)));
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    @Override // com.huawei.agconnect.AGConnectOptions
    public int getInt(String str) {
        return getInt(str, 0);
    }

    @Override // com.huawei.agconnect.AGConnectOptions
    public String getIdentifier() {
        return this.f1703a;
    }

    @Override // com.huawei.agconnect.AGConnectOptions
    public Context getContext() {
        return this.b;
    }

    @Override // com.huawei.agconnect.AGConnectOptions
    public boolean getBoolean(String str, boolean z) {
        return Boolean.parseBoolean(getString(str, String.valueOf(z)));
    }

    @Override // com.huawei.agconnect.AGConnectOptions
    public boolean getBoolean(String str) {
        return getBoolean(str, false);
    }

    public List<Service> a() {
        return this.h;
    }

    private String b() {
        return String.valueOf(("{packageName='" + this.c + "', routePolicy=" + this.d + ", reader=" + this.e.toString().hashCode() + ", customConfigMap=" + new JSONObject(this.g).toString().hashCode() + '}').hashCode());
    }

    private String a(String str) {
        Map<String, JsonProcessingFactory.JsonProcessor> processors = JsonProcessingFactory.getProcessors();
        if (!processors.containsKey(str)) {
            return null;
        }
        if (this.i.containsKey(str)) {
            return this.i.get(str);
        }
        JsonProcessingFactory.JsonProcessor jsonProcessor = processors.get(str);
        if (jsonProcessor == null) {
            return null;
        }
        String processOption = jsonProcessor.processOption(this);
        this.i.put(str, processOption);
        return processOption;
    }

    public b(Context context, String str, AGCRoutePolicy aGCRoutePolicy, InputStream inputStream, Map<String, String> map, List<Service> list, String str2) {
        context = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.b = context;
        str = str == null ? context.getPackageName() : str;
        this.c = str;
        if (inputStream != null) {
            this.e = new i(inputStream, str);
            Utils.closeQuietly(inputStream);
        } else {
            this.e = new m(context, str);
        }
        this.f = new e(this.e);
        if (aGCRoutePolicy != AGCRoutePolicy.UNKNOWN && "1.0".equals(this.e.getString("/configuration_version", null))) {
            throw new RuntimeException("The file version does not match,please download the latest agconnect-services.json from the AGC website.");
        }
        this.d = (aGCRoutePolicy == null || aGCRoutePolicy == AGCRoutePolicy.UNKNOWN) ? Utils.getRoutePolicyFromJson(this.e.getString("/region", null), this.e.getString("/agcgw/url", null)) : aGCRoutePolicy;
        this.g = Utils.fixKeyPathMap(map);
        this.h = list;
        this.f1703a = str2 == null ? b() : str2;
    }
}
