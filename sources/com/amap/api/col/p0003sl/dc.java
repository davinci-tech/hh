package com.amap.api.col.p0003sl;

import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.model.Tile;
import com.amap.api.maps.model.TileProvider;
import com.autonavi.base.amap.mapcore.MapConfig;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;

/* loaded from: classes8.dex */
public final class dc implements TileProvider {
    private MapConfig c;

    /* renamed from: a, reason: collision with root package name */
    private final int f964a = 256;
    private final int b = 256;
    private final boolean d = false;

    public dc(MapConfig mapConfig) {
        this.c = mapConfig;
    }

    @Override // com.amap.api.maps.model.TileProvider
    public final Tile getTile(int i, int i2, int i3) {
        try {
            if (!this.d) {
                if (this.c.getMapLanguage().equals("zh_cn")) {
                    if (!MapsInitializer.isLoadWorldGridMap()) {
                        return NO_TILE;
                    }
                    if (i3 < 6 || Cdo.a(i, i2, i3)) {
                        return NO_TILE;
                    }
                } else if (!MapsInitializer.isLoadWorldGridMap() && i3 >= 6 && !Cdo.a(i, i2, i3)) {
                    return NO_TILE;
                }
            }
            MapConfig mapConfig = this.c;
            byte[] a2 = a(i, i2, i3, mapConfig != null ? mapConfig.getMapLanguage() : "zh_cn");
            if (a2 == null) {
                return NO_TILE;
            }
            return Tile.obtain(this.f964a, this.b, a2);
        } catch (IOException unused) {
            return NO_TILE;
        }
    }

    private byte[] a(int i, int i2, int i3, String str) throws IOException {
        try {
            return new a(i, i2, i3, str).makeHttpRequestWithInterrupted();
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // com.amap.api.maps.model.TileProvider
    public final int getTileWidth() {
        return this.f964a;
    }

    @Override // com.amap.api.maps.model.TileProvider
    public final int getTileHeight() {
        return this.b;
    }

    final class a extends dd {

        /* renamed from: a, reason: collision with root package name */
        Random f965a = new Random();
        private int g;
        private int h;
        private int i;
        private String j;
        private String k;

        public a(int i, int i2, int i3, String str) {
            this.k = "";
            this.g = i;
            this.h = i2;
            this.i = i3;
            this.j = str;
            this.k = c();
        }

        private String c() {
            if (Cdo.a(this.g, this.h, this.i) || this.i < 6) {
                return String.format(Locale.US, "http://wprd0%d.is.autonavi.com/appmaptile?", Integer.valueOf((this.f965a.nextInt(100000) % 4) + 1));
            }
            if (MapsInitializer.isLoadWorldGridMap()) {
                return "http://restsdk.amap.com/v4/gridmap?";
            }
            return null;
        }

        @Override // com.amap.api.col.p0003sl.ka
        public final String getURL() {
            StringBuffer stringBuffer = new StringBuffer("key=");
            stringBuffer.append(hn.f(z.f1381a));
            stringBuffer.append("&channel=amapapi");
            if (Cdo.a(this.g, this.h, this.i) || this.i < 6) {
                stringBuffer.append("&z=").append(this.i).append("&x=").append(this.g).append("&y=").append(this.h).append("&lang=en&size=1&scale=1&style=7");
            } else if (MapsInitializer.isLoadWorldGridMap()) {
                stringBuffer.append("&x=").append(this.g);
                stringBuffer.append("&y=").append(this.h);
                stringBuffer.append("&z=").append(this.i);
                stringBuffer.append("&ds=0&dpitype=webrd&lang=");
                stringBuffer.append(this.j);
                stringBuffer.append("&scale=2");
            }
            return this.k + a(stringBuffer.toString());
        }
    }
}
