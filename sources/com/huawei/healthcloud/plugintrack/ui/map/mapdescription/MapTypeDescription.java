package com.huawei.healthcloud.plugintrack.ui.map.mapdescription;

/* loaded from: classes4.dex */
public class MapTypeDescription {
    private MapType b = MapType.MAP_TYPE_SATELLITE;
    private boolean d = false;
    private byte[] e = null;

    /* renamed from: a, reason: collision with root package name */
    private byte[] f3772a = null;

    public enum MapType {
        MAP_TYPE_NORMAL,
        MAP_TYPE_SATELLITE,
        MAP_TYPE_NIGHT,
        MAP_TYPE_NAVI,
        MAP_TYPE_CUSTOM,
        DEFAULT_MAP_THREE_D
    }

    public MapTypeDescription e(boolean z) {
        this.d = z;
        return this;
    }

    public MapTypeDescription b(byte[] bArr) {
        this.e = a(bArr);
        return this;
    }

    public MapType c() {
        return this.b;
    }

    public MapTypeDescription d(MapType mapType) {
        this.b = mapType;
        return this;
    }

    private byte[] a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = bArr[i];
        }
        return bArr2;
    }
}
