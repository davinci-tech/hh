package com.amap.api.col.p0003sl;

import android.content.Context;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public final class ba {

    /* renamed from: a, reason: collision with root package name */
    public ArrayList<OfflineMapProvince> f915a = new ArrayList<>();
    private bl b;
    private Context c;

    private static boolean a(int i) {
        return i == 4;
    }

    private static boolean a(int i, int i2) {
        return i2 != 1 || i <= 2 || i >= 98;
    }

    private static boolean b(int i) {
        return i == 0 || i == 2 || i == 3 || i == 1 || i == 102 || i == 101 || i == 103 || i == -1;
    }

    public ba(Context context) {
        this.c = context;
        this.b = bl.a(context);
    }

    private void a(bg bgVar) {
        bl blVar = this.b;
        if (blVar == null || bgVar == null) {
            return;
        }
        blVar.a(bgVar);
    }

    private void b(bg bgVar) {
        bl blVar = this.b;
        if (blVar != null) {
            blVar.b(bgVar);
        }
    }

    private static boolean a(OfflineMapProvince offlineMapProvince) {
        if (offlineMapProvince == null) {
            return false;
        }
        Iterator<OfflineMapCity> it = offlineMapProvince.getCityList().iterator();
        while (it.hasNext()) {
            if (it.next().getState() != 4) {
                return false;
            }
        }
        return true;
    }

    public final ArrayList<OfflineMapProvince> a() {
        ArrayList<OfflineMapProvince> arrayList = new ArrayList<>();
        synchronized (this.f915a) {
            Iterator<OfflineMapProvince> it = this.f915a.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
        }
        return arrayList;
    }

    public final OfflineMapCity a(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        synchronized (this.f915a) {
            Iterator<OfflineMapProvince> it = this.f915a.iterator();
            while (it.hasNext()) {
                Iterator<OfflineMapCity> it2 = it.next().getCityList().iterator();
                while (it2.hasNext()) {
                    OfflineMapCity next = it2.next();
                    if (next.getCode().equals(str)) {
                        return next;
                    }
                }
            }
            return null;
        }
    }

    public final OfflineMapCity b(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        synchronized (this.f915a) {
            Iterator<OfflineMapProvince> it = this.f915a.iterator();
            while (it.hasNext()) {
                Iterator<OfflineMapCity> it2 = it.next().getCityList().iterator();
                while (it2.hasNext()) {
                    OfflineMapCity next = it2.next();
                    if (next.getCity().trim().equalsIgnoreCase(str.trim())) {
                        return next;
                    }
                }
            }
            return null;
        }
    }

    public final OfflineMapProvince c(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        synchronized (this.f915a) {
            Iterator<OfflineMapProvince> it = this.f915a.iterator();
            while (it.hasNext()) {
                OfflineMapProvince next = it.next();
                if (next.getProvinceName().trim().equalsIgnoreCase(str.trim())) {
                    return next;
                }
            }
            return null;
        }
    }

    public final ArrayList<OfflineMapCity> b() {
        ArrayList<OfflineMapCity> arrayList = new ArrayList<>();
        synchronized (this.f915a) {
            Iterator<OfflineMapProvince> it = this.f915a.iterator();
            while (it.hasNext()) {
                Iterator<OfflineMapCity> it2 = it.next().getCityList().iterator();
                while (it2.hasNext()) {
                    arrayList.add(it2.next());
                }
            }
        }
        return arrayList;
    }

    public final void a(List<OfflineMapProvince> list) {
        OfflineMapProvince offlineMapProvince;
        OfflineMapCity offlineMapCity;
        synchronized (this.f915a) {
            if (this.f915a.size() > 0) {
                for (int i = 0; i < this.f915a.size(); i++) {
                    OfflineMapProvince offlineMapProvince2 = this.f915a.get(i);
                    Iterator<OfflineMapProvince> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            offlineMapProvince = null;
                            break;
                        }
                        offlineMapProvince = it.next();
                        if (offlineMapProvince2.getPinyin().equals(offlineMapProvince.getPinyin())) {
                            break;
                        }
                        if (offlineMapProvince2.getPinyin().equals("quanguogaiyaotu") || offlineMapProvince2.getProvinceCode().equals("000001") || offlineMapProvince2.getProvinceCode().equals(SmartMsgConstant.SHOW_METHOD_SMART_CARD)) {
                            if (offlineMapProvince.getPinyin().equals("quanguogaiyaotu")) {
                                break;
                            }
                        }
                    }
                    if (offlineMapProvince != null) {
                        a(offlineMapProvince2, offlineMapProvince);
                        ArrayList<OfflineMapCity> cityList = offlineMapProvince2.getCityList();
                        ArrayList<OfflineMapCity> cityList2 = offlineMapProvince.getCityList();
                        for (int i2 = 0; i2 < cityList.size(); i2++) {
                            OfflineMapCity offlineMapCity2 = cityList.get(i2);
                            Iterator<OfflineMapCity> it2 = cityList2.iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    offlineMapCity = it2.next();
                                    if (offlineMapCity2.getPinyin().equals(offlineMapCity.getPinyin())) {
                                        break;
                                    }
                                } else {
                                    offlineMapCity = null;
                                    break;
                                }
                            }
                            if (offlineMapCity != null) {
                                a(offlineMapCity2, offlineMapCity);
                            }
                        }
                    }
                }
            } else {
                Iterator<OfflineMapProvince> it3 = list.iterator();
                while (it3.hasNext()) {
                    this.f915a.add(it3.next());
                }
            }
        }
    }

    private static void a(OfflineMapCity offlineMapCity, OfflineMapCity offlineMapCity2) {
        offlineMapCity.setUrl(offlineMapCity2.getUrl());
        offlineMapCity.setVersion(offlineMapCity2.getVersion());
        offlineMapCity.setSize(offlineMapCity2.getSize());
        offlineMapCity.setCode(offlineMapCity2.getCode());
        offlineMapCity.setPinyin(offlineMapCity2.getPinyin());
        offlineMapCity.setJianpin(offlineMapCity2.getJianpin());
    }

    private static void a(OfflineMapProvince offlineMapProvince, OfflineMapProvince offlineMapProvince2) {
        offlineMapProvince.setUrl(offlineMapProvince2.getUrl());
        offlineMapProvince.setVersion(offlineMapProvince2.getVersion());
        offlineMapProvince.setSize(offlineMapProvince2.getSize());
        offlineMapProvince.setPinyin(offlineMapProvince2.getPinyin());
        offlineMapProvince.setJianpin(offlineMapProvince2.getJianpin());
    }

    public final ArrayList<OfflineMapCity> c() {
        ArrayList<OfflineMapCity> arrayList;
        synchronized (this.f915a) {
            arrayList = new ArrayList<>();
            Iterator<OfflineMapProvince> it = this.f915a.iterator();
            while (it.hasNext()) {
                OfflineMapProvince next = it.next();
                if (next != null) {
                    for (OfflineMapCity offlineMapCity : next.getCityList()) {
                        if (offlineMapCity.getState() == 4 || offlineMapCity.getState() == 7) {
                            arrayList.add(offlineMapCity);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public final ArrayList<OfflineMapProvince> d() {
        ArrayList<OfflineMapProvince> arrayList;
        synchronized (this.f915a) {
            arrayList = new ArrayList<>();
            Iterator<OfflineMapProvince> it = this.f915a.iterator();
            while (it.hasNext()) {
                OfflineMapProvince next = it.next();
                if (next != null && (next.getState() == 4 || next.getState() == 7)) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    public final ArrayList<OfflineMapCity> e() {
        ArrayList<OfflineMapCity> arrayList;
        synchronized (this.f915a) {
            arrayList = new ArrayList<>();
            Iterator<OfflineMapProvince> it = this.f915a.iterator();
            while (it.hasNext()) {
                OfflineMapProvince next = it.next();
                if (next != null) {
                    for (OfflineMapCity offlineMapCity : next.getCityList()) {
                        if (b(offlineMapCity.getState())) {
                            arrayList.add(offlineMapCity);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public final ArrayList<OfflineMapProvince> f() {
        ArrayList<OfflineMapProvince> arrayList;
        synchronized (this.f915a) {
            arrayList = new ArrayList<>();
            Iterator<OfflineMapProvince> it = this.f915a.iterator();
            while (it.hasNext()) {
                OfflineMapProvince next = it.next();
                if (next != null && b(next.getState())) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    public final void a(av avVar) {
        String pinyin = avVar.getPinyin();
        synchronized (this.f915a) {
            Iterator<OfflineMapProvince> it = this.f915a.iterator();
            loop0: while (true) {
                if (!it.hasNext()) {
                    break;
                }
                OfflineMapProvince next = it.next();
                if (next != null) {
                    for (OfflineMapCity offlineMapCity : next.getCityList()) {
                        if (offlineMapCity.getPinyin().trim().equals(pinyin.trim())) {
                            a(avVar, offlineMapCity);
                            a(avVar, next);
                            break loop0;
                        }
                    }
                }
            }
        }
    }

    private void a(av avVar, OfflineMapCity offlineMapCity) {
        int b = avVar.c().b();
        if (avVar.c().equals(avVar.f904a)) {
            b(avVar.t());
        } else {
            if (avVar.c().equals(avVar.f)) {
                avVar.getCity();
                b(avVar);
                avVar.t().b();
            }
            if (a(avVar.getcompleteCode(), avVar.c().b())) {
                a(avVar.t());
            }
        }
        offlineMapCity.setState(b);
        offlineMapCity.setCompleteCode(avVar.getcompleteCode());
    }

    private void b(av avVar) {
        File[] listFiles = new File(dv.c(this.c)).listFiles();
        if (listFiles == null) {
            return;
        }
        for (File file : listFiles) {
            if (file.isFile() && file.exists() && file.getName().contains(avVar.getAdcode()) && file.getName().endsWith(".zip.tmp.dt")) {
                file.delete();
            }
        }
    }

    private void a(av avVar, OfflineMapProvince offlineMapProvince) {
        bg bgVar;
        int b = avVar.c().b();
        if (b == 6) {
            offlineMapProvince.setState(b);
            offlineMapProvince.setCompleteCode(0);
            b(new bg(offlineMapProvince, this.c));
            try {
                bt.b(offlineMapProvince.getProvinceCode(), this.c);
                return;
            } catch (IOException e) {
                e.printStackTrace();
                return;
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        if (a(b) && a(offlineMapProvince)) {
            if (avVar.getPinyin().equals(offlineMapProvince.getPinyin())) {
                offlineMapProvince.setState(b);
                offlineMapProvince.setCompleteCode(avVar.getcompleteCode());
                offlineMapProvince.setVersion(avVar.getVersion());
                offlineMapProvince.setUrl(avVar.getUrl());
                bgVar = new bg(offlineMapProvince, this.c);
                bgVar.a(avVar.a());
                bgVar.d(avVar.getCode());
            } else {
                offlineMapProvince.setState(b);
                offlineMapProvince.setCompleteCode(100);
                bgVar = new bg(offlineMapProvince, this.c);
            }
            bgVar.b();
            a(bgVar);
            bgVar.c();
        }
    }

    public final void g() {
        h();
        this.b = null;
        this.c = null;
    }

    private void h() {
        ArrayList<OfflineMapProvince> arrayList = this.f915a;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.f915a.clear();
            }
        }
    }
}
