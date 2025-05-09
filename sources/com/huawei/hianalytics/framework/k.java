package com.huawei.hianalytics.framework;

import com.huawei.hianalytics.framework.config.DeviceAttributeCollector;
import com.huawei.hianalytics.framework.config.EvtHeaderAttributeCollector;
import com.huawei.hianalytics.framework.config.RomAttributeCollector;
import com.huawei.secure.android.common.encrypt.utils.HexUtil;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* loaded from: classes4.dex */
public class k {
    public static final Charset i = StandardCharsets.UTF_8;

    /* renamed from: a, reason: collision with root package name */
    public final DeviceAttributeCollector f3864a;
    public final EvtHeaderAttributeCollector b;
    public final RomAttributeCollector c;
    public final String d;
    public final String e;
    public final byte[] f;
    public final int g;
    public final int h;

    /* JADX WARN: Removed duplicated region for block: B:21:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x028f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0256 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.json.JSONObject a(java.util.List<com.huawei.hianalytics.core.storage.Event> r32, boolean r33, int r34) {
        /*
            Method dump skipped, instructions count: 807
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.framework.k.a(java.util.List, boolean, int):org.json.JSONObject");
    }

    public k(DeviceAttributeCollector deviceAttributeCollector, EvtHeaderAttributeCollector evtHeaderAttributeCollector, RomAttributeCollector romAttributeCollector, String str, String str2, String str3, int i2, int i3) {
        this.f3864a = deviceAttributeCollector;
        this.b = evtHeaderAttributeCollector;
        this.c = romAttributeCollector;
        this.f = HexUtil.hexStr2ByteArray(str);
        this.d = str2;
        this.e = str3;
        this.g = i2;
        this.h = i3;
    }
}
