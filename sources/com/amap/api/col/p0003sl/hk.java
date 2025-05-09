package com.amap.api.col.p0003sl;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amap.api.maps.model.LatLng;
import com.amap.api.trace.LBSTraceClient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class hk {
    private static volatile hk b;

    /* renamed from: a, reason: collision with root package name */
    private Map<String, a> f1127a;

    public hk() {
        this.f1127a = null;
        this.f1127a = Collections.synchronizedMap(new HashMap());
    }

    public static hk a() {
        if (b == null) {
            synchronized (hk.class) {
                if (b == null) {
                    b = new hk();
                }
            }
        }
        return b;
    }

    public final void a(String str, int i, List<LatLng> list) {
        synchronized (this) {
            Map<String, a> map = this.f1127a;
            if (map != null) {
                map.get(str).a().put(Integer.valueOf(i), list);
            }
        }
    }

    public final void a(String str, int i, int i2, int i3) {
        synchronized (this) {
            Map<String, a> map = this.f1127a;
            if (map != null) {
                map.put(str, new a(i, i2, i3, new HashMap(16)));
            }
        }
    }

    public final a a(String str) {
        synchronized (this) {
            Map<String, a> map = this.f1127a;
            if (map == null) {
                return null;
            }
            return map.get(str);
        }
    }

    public static void a(Handler handler, int i, String str) {
        Message obtainMessage = handler.obtainMessage();
        obtainMessage.obj = str;
        obtainMessage.what = 102;
        Bundle bundle = new Bundle();
        bundle.putInt("lineID", i);
        obtainMessage.setData(bundle);
        handler.sendMessage(obtainMessage);
    }

    final class a {
        private int b;
        private int c;
        private int e;
        private HashMap<Integer, List<LatLng>> g;
        private int d = 0;
        private int f = 0;
        private List<LatLng> h = new ArrayList();

        public a(int i, int i2, int i3, HashMap<Integer, List<LatLng>> hashMap) {
            this.b = 0;
            this.c = 0;
            this.e = 0;
            this.b = i2;
            this.g = hashMap;
            this.c = i;
            this.e = i3;
        }

        public final HashMap<Integer, List<LatLng>> a() {
            return this.g;
        }

        public final void a(Handler handler) {
            List<LatLng> list;
            for (int i = this.d; i <= this.b && (list = this.g.get(Integer.valueOf(i))) != null; i++) {
                this.h.addAll(list);
                a(handler, list);
            }
            if (this.d == this.b + 1) {
                b(handler);
            }
        }

        private void a(Handler handler, List<LatLng> list) {
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.obj = list;
            obtainMessage.what = 100;
            obtainMessage.arg1 = this.d;
            Bundle bundle = new Bundle();
            bundle.putInt("lineID", this.c);
            obtainMessage.setData(bundle);
            handler.sendMessage(obtainMessage);
            this.d++;
            this.f++;
        }

        private void b(Handler handler) {
            if (this.f > 0) {
                int a2 = hh.a(this.h);
                Message obtainMessage = handler.obtainMessage();
                obtainMessage.obj = this.h;
                obtainMessage.what = 101;
                obtainMessage.arg1 = a2;
                obtainMessage.arg2 = this.e;
                Bundle bundle = new Bundle();
                bundle.putInt("lineID", this.c);
                obtainMessage.setData(bundle);
                handler.sendMessage(obtainMessage);
                return;
            }
            hk.a(handler, this.c, LBSTraceClient.MIN_GRASP_POINT_ERROR);
        }
    }
}
