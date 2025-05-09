package com.amap.api.maps.offlinemap;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import com.amap.api.col.p0003sl.er;
import com.amap.api.col.p0003sl.es;
import com.amap.api.col.p0003sl.eu;
import com.amap.api.offlineservice.AMapPermissionActivity;
import com.amap.api.offlineservice.a;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;

/* loaded from: classes8.dex */
public class OfflineMapActivity extends AMapPermissionActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private static int f1445a;
    private a b;
    private er c;
    private er[] d = new er[32];
    private int e = -1;
    private es f;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            getWindow().setSoftInputMode(32);
            getWindow().setFormat(-3);
            requestWindowFeature(1);
            eu.a(getApplicationContext());
            this.e = -1;
            f1445a = 0;
            b(new er());
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void showScr() {
        try {
            setContentView(this.b.d());
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a(er erVar) {
        try {
            a aVar = this.b;
            if (aVar != null) {
                aVar.e();
                this.b = null;
            }
            a c = c(erVar);
            this.b = c;
            if (c != null) {
                this.c = erVar;
                c.a(this);
                this.b.a();
                this.b.b();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void b(er erVar) {
        try {
            f1445a++;
            a(erVar);
            int i = (this.e + 1) % 32;
            this.e = i;
            this.d[i] = erVar;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private a c(er erVar) {
        try {
            if (erVar.f1024a != 1) {
                return null;
            }
            if (this.f == null) {
                this.f = new es();
            }
            return this.f;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        try {
            super.onStart();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.offlineservice.AMapPermissionActivity, android.app.Activity
    public void onResume() {
        try {
            super.onResume();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        try {
            super.onPause();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        try {
            super.onStop();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        try {
            super.onConfigurationChanged(configuration);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void closeScr() {
        try {
            if (a((Bundle) null)) {
                return;
            }
            a aVar = this.b;
            if (aVar != null) {
                aVar.e();
            }
            finish();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void closeScr(Bundle bundle) {
        try {
            if (a(bundle)) {
                return;
            }
            a aVar = this.b;
            if (aVar != null) {
                aVar.e();
            }
            finish();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private boolean a(Bundle bundle) {
        try {
            int i = f1445a;
            if ((i != 1 || this.b == null) && i > 1) {
                f1445a = i - 1;
                int i2 = (this.e + 31) % 32;
                this.e = i2;
                er erVar = this.d[i2];
                erVar.b = bundle;
                a(erVar);
                return true;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return false;
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        try {
            super.onDestroy();
            a aVar = this.b;
            if (aVar != null) {
                aVar.e();
                this.b = null;
            }
            this.c = null;
            this.d = null;
            es esVar = this.f;
            if (esVar != null) {
                esVar.e();
                this.f = null;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            a aVar = this.b;
            if (aVar != null) {
                aVar.a(view);
            }
            ViewClickInstrumentation.clickOnView(view);
        } catch (Throwable th) {
            th.printStackTrace();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            try {
                a aVar = this.b;
                if (aVar != null && !aVar.c()) {
                    return true;
                }
                if (a((Bundle) null)) {
                    return false;
                }
                if (keyEvent == null) {
                    if (f1445a == 1) {
                        finish();
                    }
                    return false;
                }
                this.e = -1;
                f1445a = 0;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return super.onKeyDown(i, keyEvent);
    }
}
