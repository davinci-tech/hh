package com.huawei.openalliance.ad;

import android.text.TextUtils;
import com.google.flatbuffers.FlatBufferBuilder;
import com.huawei.hms.ads.data.Keyword;
import com.huawei.hms.ads.data.SearchInfo;
import com.huawei.openalliance.ad.beans.metadata.AdSlot30;
import com.huawei.openalliance.ad.beans.metadata.App;
import com.huawei.openalliance.ad.beans.metadata.AppExt;
import com.huawei.openalliance.ad.beans.metadata.CellInfo;
import com.huawei.openalliance.ad.beans.metadata.Device;
import com.huawei.openalliance.ad.beans.metadata.DeviceExt;
import com.huawei.openalliance.ad.beans.metadata.ImpEX;
import com.huawei.openalliance.ad.beans.metadata.Location;
import com.huawei.openalliance.ad.beans.metadata.Network;
import com.huawei.openalliance.ad.beans.metadata.Options;
import com.huawei.openalliance.ad.beans.metadata.v3.CachedContent;
import com.huawei.openalliance.ad.inter.data.SearchTerm;
import com.huawei.openalliance.ad.inter.data.fb.ImpEXFb;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class dv {
    private static int[] f(List<Keyword> list, FlatBufferBuilder flatBufferBuilder) {
        int[] iArr;
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            iArr = null;
        } else {
            int size = list.size();
            iArr = new int[size];
            for (int i = 0; i < size; i++) {
                Keyword keyword = list.get(i);
                if (keyword != null) {
                    iArr[i] = vq.a(flatBufferBuilder, a(keyword.getType()), a(keyword.getKeyword(), flatBufferBuilder), -1111.0f);
                }
            }
        }
        return ec.a(iArr);
    }

    private static int[] e(List<CellInfo> list, FlatBufferBuilder flatBufferBuilder) {
        int[] iArr;
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            iArr = null;
        } else {
            int size = list.size();
            iArr = new int[size];
            for (int i = 0; i < size; i++) {
                CellInfo cellInfo = list.get(i);
                if (cellInfo != null) {
                    iArr[i] = vm.a(flatBufferBuilder, a(cellInfo.a(), flatBufferBuilder), a(cellInfo.b(), flatBufferBuilder), -1111, -1111, 0, cellInfo.c());
                }
            }
        }
        return ec.a(iArr);
    }

    public static int[] d(List<CachedContent> list, FlatBufferBuilder flatBufferBuilder) {
        int[] iArr;
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            iArr = null;
        } else {
            int size = list.size();
            iArr = new int[size];
            for (int i = 0; i < size; i++) {
                CachedContent cachedContent = list.get(i);
                if (cachedContent != null) {
                    iArr[i] = vl.a(flatBufferBuilder, a(cachedContent.a(), flatBufferBuilder), a(cachedContent.b()), vl.a(flatBufferBuilder, ec.a(cachedContent.c(), flatBufferBuilder)));
                }
            }
        }
        return ec.a(iArr);
    }

    public static int[] c(List<ImpEX> list, FlatBufferBuilder flatBufferBuilder) {
        int[] iArr;
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            iArr = null;
        } else {
            int size = list.size();
            iArr = new int[size];
            for (int i = 0; i < size; i++) {
                ImpEX impEX = list.get(i);
                if (impEX != null) {
                    iArr[i] = ImpEXFb.createImpEXFb(flatBufferBuilder, a(impEX.a(), flatBufferBuilder), a(impEX.b(), flatBufferBuilder));
                }
            }
        }
        return ec.a(iArr);
    }

    public static int[] b(Map<String, Integer> map, FlatBufferBuilder flatBufferBuilder) {
        int[] iArr;
        if (com.huawei.openalliance.ad.utils.bl.a(map)) {
            iArr = null;
        } else {
            iArr = new int[map.size()];
            int i = 0;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                iArr[i] = vg.a(flatBufferBuilder, a(entry.getKey(), flatBufferBuilder), a(entry.getValue()));
                i++;
            }
        }
        return ec.a(iArr);
    }

    public static int[] b(List<App> list, FlatBufferBuilder flatBufferBuilder) {
        int[] iArr;
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            iArr = null;
        } else {
            int size = list.size();
            iArr = new int[size];
            for (int i = 0; i < size; i++) {
                App app = list.get(i);
                if (app != null) {
                    iArr[i] = a(app, flatBufferBuilder);
                }
            }
        }
        return ec.a(iArr);
    }

    public static int[] a(Map<String, String> map, FlatBufferBuilder flatBufferBuilder) {
        int[] iArr;
        if (com.huawei.openalliance.ad.utils.bl.a(map)) {
            iArr = null;
        } else {
            iArr = new int[map.size()];
            int i = 0;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                iArr[i] = vh.a(flatBufferBuilder, a(entry.getKey(), flatBufferBuilder), a(entry.getValue(), flatBufferBuilder));
                i++;
            }
        }
        return ec.a(iArr);
    }

    public static int[] a(List<AdSlot30> list, FlatBufferBuilder flatBufferBuilder) {
        int[] iArr;
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            iArr = null;
        } else {
            int size = list.size();
            iArr = new int[size];
            for (int i = 0; i < size; i++) {
                AdSlot30 adSlot30 = list.get(i);
                if (adSlot30 != null) {
                    iArr[i] = a(adSlot30, flatBufferBuilder);
                }
            }
        }
        return ec.a(iArr);
    }

    public static long a(Long l) {
        if (l == null) {
            return -1111L;
        }
        return l.longValue();
    }

    public static int a(String str, FlatBufferBuilder flatBufferBuilder) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return flatBufferBuilder.createString(ec.a(str));
    }

    public static int a(Integer num) {
        if (num == null) {
            return -1111;
        }
        return num.intValue();
    }

    public static int a(SearchTerm searchTerm, FlatBufferBuilder flatBufferBuilder) {
        if (searchTerm == null) {
            return 0;
        }
        return vt.a(flatBufferBuilder, -1111, a(searchTerm.a(), flatBufferBuilder));
    }

    public static int a(Options options, FlatBufferBuilder flatBufferBuilder) {
        if (options == null) {
            return 0;
        }
        return vs.a(flatBufferBuilder, a(options.a()), a(options.b()), a(options.c(), flatBufferBuilder));
    }

    public static int a(Network network, FlatBufferBuilder flatBufferBuilder) {
        if (network == null) {
            return 0;
        }
        return vr.a(flatBufferBuilder, network.a(), -1111, vr.a(flatBufferBuilder, e(network.b(), flatBufferBuilder)));
    }

    public static int a(Location location, FlatBufferBuilder flatBufferBuilder) {
        if (location == null) {
            return 0;
        }
        return vp.a(flatBufferBuilder, a(location.d()), location.g(), a(location.b()), a(location.c()), -1111, a(location.e()));
    }

    public static int a(DeviceExt deviceExt, FlatBufferBuilder flatBufferBuilder) {
        if (deviceExt == null) {
            return 0;
        }
        return vn.a(flatBufferBuilder, vn.a(flatBufferBuilder, ec.a(deviceExt.a(), flatBufferBuilder)), a(deviceExt.b(), flatBufferBuilder), a(deviceExt.c(), flatBufferBuilder));
    }

    public static int a(Device device, FlatBufferBuilder flatBufferBuilder) {
        if (device == null) {
            return 0;
        }
        return vo.a(flatBufferBuilder, a(device.T(), flatBufferBuilder), a(device.P(), flatBufferBuilder), a(device.Q(), flatBufferBuilder), a(device.R(), flatBufferBuilder), a(device.U()), a(device.ab()), device.ag(), device.a(), a(device.n(), flatBufferBuilder), a(device.b(), flatBufferBuilder), a(device.c(), flatBufferBuilder), a(device.d(), flatBufferBuilder), a(device.S(), flatBufferBuilder), a(device.V(), flatBufferBuilder), a(device.W(), flatBufferBuilder), a(device.e(), flatBufferBuilder), device.f(), device.g(), a(device.h(), flatBufferBuilder), 0, a(device.i(), flatBufferBuilder), 0, a(device.j(), flatBufferBuilder), a(device.k(), flatBufferBuilder), 0, device.l(), -1111, device.m(), a(device.o(), flatBufferBuilder), a(device.p(), flatBufferBuilder), 0, a(device.ac(), flatBufferBuilder), a(device.q(), flatBufferBuilder), 0, 0, a(device.s(), flatBufferBuilder), a(device.t(), flatBufferBuilder), a(device.r(), flatBufferBuilder), a(device.v(), flatBufferBuilder), a(device.F(), flatBufferBuilder), a(device.w(), flatBufferBuilder), a(device.x(), flatBufferBuilder), a(device.E(), flatBufferBuilder), a(device.y(), flatBufferBuilder), a(device.z(), flatBufferBuilder), a(device.A(), flatBufferBuilder), a(device.u(), flatBufferBuilder), a(device.B(), flatBufferBuilder), a(device.K(), flatBufferBuilder), a(device.M(), flatBufferBuilder), a(device.C(), flatBufferBuilder), a(device.D(), flatBufferBuilder), a(device.L(), flatBufferBuilder), a(device.N()), a(device.O()), a(device.G()), a(device.H()), a(device.I()), a(device.J()), 0, 0, 0, a(device.Z(), flatBufferBuilder), vo.a(flatBufferBuilder, ec.a(device.X(), flatBufferBuilder)), vo.b(flatBufferBuilder, b(device.ah(), flatBufferBuilder)), a(device.Y()), a(device.aa() == null ? null : device.aa().toString(), flatBufferBuilder), a(device.ad(), flatBufferBuilder), a(device.ae(), flatBufferBuilder), -1111, a(device.ai()), -1111, -1111, vo.c(flatBufferBuilder, b(device.af(), flatBufferBuilder)), a(device.aj(), flatBufferBuilder));
    }

    public static int a(AppExt appExt, FlatBufferBuilder flatBufferBuilder) {
        if (appExt == null) {
            return 0;
        }
        return vj.a(flatBufferBuilder, a(appExt.a()));
    }

    public static int a(App app, FlatBufferBuilder flatBufferBuilder) {
        if (app == null) {
            return 0;
        }
        return vk.a(flatBufferBuilder, a(app.g()), a(app.j(), flatBufferBuilder), a(app.a(), flatBufferBuilder), a(app.b(), flatBufferBuilder), a(app.c(), flatBufferBuilder), a(app.d(), flatBufferBuilder), a(app.e(), flatBufferBuilder), a(app.f(), flatBufferBuilder), a(app.i() == null ? "" : app.i().toString(), flatBufferBuilder), a(app.h(), flatBufferBuilder), a(app.k()), a(app.m(), flatBufferBuilder), a(app.l(), flatBufferBuilder), a(app.n(), flatBufferBuilder));
    }

    public static int a(AdSlot30 adSlot30, FlatBufferBuilder flatBufferBuilder) {
        if (adSlot30 == null) {
            return 0;
        }
        return vi.a(flatBufferBuilder, a(adSlot30.a(), flatBufferBuilder), adSlot30.b(), adSlot30.c(), adSlot30.d(), adSlot30.e(), a(adSlot30.f()), 0, a(adSlot30.n()), vi.a(flatBufferBuilder, ec.a(adSlot30.o(), flatBufferBuilder)), vi.b(flatBufferBuilder, c(adSlot30.i(), flatBufferBuilder)), a(adSlot30.p()), a(adSlot30.q()), a(adSlot30.r()), 0, a(adSlot30.h()), a(adSlot30.g()), a(adSlot30.j()), a(adSlot30.k()), a(adSlot30.l()), a(adSlot30.m()), -1111, -1111, 0, 0, 0, 0, 0, -1111, adSlot30.v(), vi.c(flatBufferBuilder, d(adSlot30.u(), flatBufferBuilder)), a(adSlot30.w()), a(adSlot30.x(), flatBufferBuilder), vi.d(flatBufferBuilder, ec.a(adSlot30.y(), flatBufferBuilder)), vi.e(flatBufferBuilder, ec.a(adSlot30.t(), flatBufferBuilder)), a(adSlot30.s(), flatBufferBuilder));
    }

    public static int a(SearchInfo searchInfo, FlatBufferBuilder flatBufferBuilder) {
        if (searchInfo == null) {
            return 0;
        }
        return vu.a(flatBufferBuilder, a(searchInfo.getQuery(), flatBufferBuilder), vu.a(flatBufferBuilder, f(searchInfo.getKeywords(), flatBufferBuilder)), a(searchInfo.getChannel(), flatBufferBuilder), 0);
    }

    public static float a(Float f) {
        if (f == null) {
            return -1111.0f;
        }
        return f.floatValue();
    }

    public static float a(Double d) {
        if (d == null) {
            return -1111.0f;
        }
        return d.floatValue();
    }
}
