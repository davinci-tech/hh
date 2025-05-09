package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class abe {
    private static abe c;

    /* renamed from: a, reason: collision with root package name */
    private SharedPreferences f160a;
    private Context e;

    private abe(Context context) {
        this(context, 0);
    }

    public static abe e(Context context) {
        abe abeVar;
        synchronized (abe.class) {
            if (c == null) {
                c = new abe(context);
            }
            abeVar = c;
        }
        return abeVar;
    }

    private boolean j(String str) {
        return str.endsWith("_last_report_num") || str.endsWith("_last_report_time") || str.endsWith("_first_prepare_time") || str.endsWith("_latest_prepare_traceId") || str.endsWith("_call_prepare_sync_times") || str.endsWith("_each_period_call_prepare_sync_times");
    }

    public void a() {
        SharedPreferences.Editor edit;
        SharedPreferences sharedPreferences = this.f160a;
        if (sharedPreferences == null || (edit = sharedPreferences.edit()) == null) {
            return;
        }
        edit.remove("syncType").commit();
        edit.remove("dataType").commit();
        edit.remove("service_disconnected_time").commit();
        edit.remove("trace_id").commit();
    }

    public boolean d() {
        synchronized (this) {
            SharedPreferences sharedPreferences = this.f160a;
            if (sharedPreferences == null) {
                return false;
            }
            return sharedPreferences.getBoolean("updated_support_recycle", false);
        }
    }

    public long e(String str, String str2) {
        synchronized (this) {
            SharedPreferences sharedPreferences = this.f160a;
            if (sharedPreferences == null) {
                return 0L;
            }
            return sharedPreferences.getLong(str + "_" + str2 + "_last_get_service_exceedlimits_time", 0L);
        }
    }

    public String e() {
        synchronized (this) {
            SharedPreferences sharedPreferences = this.f160a;
            if (sharedPreferences == null) {
                return null;
            }
            return sharedPreferences.getString("dataType", "");
        }
    }

    public long f(String str) {
        synchronized (this) {
            SharedPreferences sharedPreferences = this.f160a;
            if (sharedPreferences == null) {
                return 0L;
            }
            return sharedPreferences.getLong(str + "_first_prepare_time", 0L);
        }
    }

    public String h() {
        synchronized (this) {
            SharedPreferences sharedPreferences = this.f160a;
            if (sharedPreferences == null) {
                return null;
            }
            return sharedPreferences.getString("syncType", "");
        }
    }

    public String i() {
        synchronized (this) {
            SharedPreferences sharedPreferences = this.f160a;
            if (sharedPreferences == null) {
                return null;
            }
            return sharedPreferences.getString("trace_id", "");
        }
    }

    public String j() {
        synchronized (this) {
            SharedPreferences sharedPreferences = this.f160a;
            if (sharedPreferences == null) {
                return null;
            }
            return sharedPreferences.getString("service_disconnected_time", "");
        }
    }

    private abe(Context context, int i) {
        this.e = context;
        this.f160a = abc.ft_(context, "SharedPreferencesUtil", i);
    }

    public boolean a(String str, String str2, long j) {
        synchronized (this) {
            SharedPreferences sharedPreferences = this.f160a;
            if (sharedPreferences == null) {
                return false;
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (edit == null) {
                return false;
            }
            edit.putLong(str + "_" + str2 + "_last_get_service_exceedlimits_time", j);
            return edit.commit();
        }
    }

    public boolean c(String str) {
        synchronized (this) {
            SharedPreferences sharedPreferences = this.f160a;
            if (sharedPreferences == null) {
                return false;
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (edit == null) {
                return false;
            }
            edit.remove(str + "_last_report_time");
            return edit.commit();
        }
    }

    public boolean d(String str) {
        synchronized (this) {
            SharedPreferences sharedPreferences = this.f160a;
            if (sharedPreferences == null) {
                return false;
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (edit == null) {
                return false;
            }
            edit.remove(str + "_latest_prepare_traceId");
            return edit.commit();
        }
    }

    public boolean g(String str) {
        synchronized (this) {
            SharedPreferences sharedPreferences = this.f160a;
            if (sharedPreferences == null) {
                return false;
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (edit == null) {
                return false;
            }
            edit.remove(str + "_last_report_num");
            return edit.commit();
        }
    }

    public int h(String str) {
        synchronized (this) {
            SharedPreferences sharedPreferences = this.f160a;
            if (sharedPreferences == null) {
                return 0;
            }
            return sharedPreferences.getInt(str + "_call_prepare_sync_times", 0);
        }
    }

    public int i(String str) {
        synchronized (this) {
            SharedPreferences sharedPreferences = this.f160a;
            if (sharedPreferences == null) {
                return 0;
            }
            return sharedPreferences.getInt(str + "_each_period_call_prepare_sync_times", 0);
        }
    }

    public boolean c(boolean z) {
        synchronized (this) {
            SharedPreferences sharedPreferences = this.f160a;
            if (sharedPreferences == null) {
                return false;
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (edit == null) {
                return false;
            }
            edit.putBoolean("updated_support_recycle", z);
            return edit.commit();
        }
    }

    public boolean b(String str) {
        synchronized (this) {
            SharedPreferences sharedPreferences = this.f160a;
            if (sharedPreferences == null) {
                return false;
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (edit == null) {
                return false;
            }
            edit.remove(str + "_first_prepare_time");
            return edit.commit();
        }
    }

    public boolean b(String str, List<String> list) {
        synchronized (this) {
            SharedPreferences sharedPreferences = this.f160a;
            if (sharedPreferences == null) {
                return false;
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (edit == null) {
                return false;
            }
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                edit.remove(str + "_" + it.next() + "_upperlimits").commit();
            }
            return true;
        }
    }

    public boolean a(String str, String str2, String str3, String str4) {
        synchronized (this) {
            SharedPreferences sharedPreferences = this.f160a;
            if (sharedPreferences == null) {
                return false;
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (edit == null) {
                return false;
            }
            if (str == null) {
                str = "";
            }
            edit.putString("syncType", str);
            if (str2 == null) {
                str2 = "";
            }
            edit.putString("dataType", str2);
            if (str3 == null) {
                str3 = "";
            }
            edit.putString("service_disconnected_time", str3);
            if (str4 == null) {
                str4 = "";
            }
            edit.putString("trace_id", str4);
            return edit.commit();
        }
    }

    public long c(String str, String str2) {
        synchronized (this) {
            SharedPreferences sharedPreferences = this.f160a;
            if (sharedPreferences == null) {
                return -1L;
            }
            return sharedPreferences.getLong(str + "_" + str2 + "_upperlimits", -1L);
        }
    }

    public boolean e(String str) {
        synchronized (this) {
            SharedPreferences sharedPreferences = this.f160a;
            if (sharedPreferences == null) {
                return false;
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (edit == null) {
                return false;
            }
            edit.remove(str + "_each_period_call_prepare_sync_times");
            return edit.commit();
        }
    }

    public boolean e(String str, String str2, long j) {
        synchronized (this) {
            SharedPreferences sharedPreferences = this.f160a;
            if (sharedPreferences == null) {
                return false;
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (edit == null) {
                return false;
            }
            edit.putLong(str + "_" + str2 + "_upperlimits", j);
            return edit.commit();
        }
    }

    public void c() {
        synchronized (this) {
            SharedPreferences sharedPreferences = this.f160a;
            if (sharedPreferences == null) {
                return;
            }
            Iterator<Map.Entry<String, ?>> it = sharedPreferences.getAll().entrySet().iterator();
            while (it.hasNext()) {
                String key = it.next().getKey();
                if (!TextUtils.isEmpty(key) && j(key)) {
                    SharedPreferences.Editor edit = this.f160a.edit();
                    if (edit == null) {
                        return;
                    } else {
                        edit.remove(key).commit();
                    }
                }
            }
        }
    }

    public boolean a(String str, List<String> list) {
        synchronized (this) {
            SharedPreferences sharedPreferences = this.f160a;
            if (sharedPreferences == null) {
                return false;
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (edit == null) {
                return false;
            }
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                edit.remove(str + "_" + it.next() + "_last_get_service_exceedlimits_time").commit();
            }
            return true;
        }
    }

    public boolean a(String str) {
        synchronized (this) {
            SharedPreferences sharedPreferences = this.f160a;
            if (sharedPreferences == null) {
                return false;
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (edit == null) {
                return false;
            }
            edit.remove(str + "_call_prepare_sync_times");
            return edit.commit();
        }
    }

    public void b() {
        synchronized (this) {
            SharedPreferences sharedPreferences = this.f160a;
            if (sharedPreferences == null) {
                return;
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.clear();
            edit.commit();
        }
    }
}
