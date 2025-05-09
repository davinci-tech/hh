package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import com.amap.api.col.p0003sl.hw;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.interfaces.IInputtipsSearch;
import java.util.ArrayList;

/* loaded from: classes8.dex */
public final class gw implements IInputtipsSearch {

    /* renamed from: a, reason: collision with root package name */
    private Context f1085a;
    private Inputtips.InputtipsListener b;
    private Handler c;
    private InputtipsQuery d;

    public gw(Context context, Inputtips.InputtipsListener inputtipsListener) throws AMapException {
        hx a2 = hw.a(context, fc.a(false));
        if (a2.f1161a != hw.c.SuccessCode) {
            throw new AMapException(a2.b, 1, a2.b, a2.f1161a.a());
        }
        this.f1085a = context.getApplicationContext();
        this.b = inputtipsListener;
        this.c = fo.a();
    }

    public gw(Context context, InputtipsQuery inputtipsQuery) {
        this.f1085a = context.getApplicationContext();
        this.d = inputtipsQuery;
        this.c = fo.a();
    }

    @Override // com.amap.api.services.interfaces.IInputtipsSearch
    public final InputtipsQuery getQuery() {
        return this.d;
    }

    @Override // com.amap.api.services.interfaces.IInputtipsSearch
    public final void setQuery(InputtipsQuery inputtipsQuery) {
        this.d = inputtipsQuery;
    }

    @Override // com.amap.api.services.interfaces.IInputtipsSearch
    public final void setInputtipsListener(Inputtips.InputtipsListener inputtipsListener) {
        this.b = inputtipsListener;
    }

    @Override // com.amap.api.services.interfaces.IInputtipsSearch
    public final void requestInputtipsAsyn() {
        try {
            gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.gw.1
                @Override // java.lang.Runnable
                public final void run() {
                    Message obtainMessage = fo.a().obtainMessage();
                    obtainMessage.obj = gw.this.b;
                    obtainMessage.arg1 = 5;
                    try {
                        try {
                            gw gwVar = gw.this;
                            ArrayList<? extends Parcelable> a2 = gwVar.a(gwVar.d);
                            Bundle bundle = new Bundle();
                            bundle.putParcelableArrayList("result", a2);
                            obtainMessage.setData(bundle);
                            obtainMessage.what = 1000;
                        } catch (AMapException e) {
                            obtainMessage.what = e.getErrorCode();
                        }
                    } finally {
                        gw.this.c.sendMessage(obtainMessage);
                    }
                }
            });
        } catch (Throwable th) {
            fd.a(th, "Inputtips", "requestInputtipsAsynThrowable");
        }
    }

    @Override // com.amap.api.services.interfaces.IInputtipsSearch
    public final ArrayList<Tip> requestInputtips() throws AMapException {
        return a(this.d);
    }

    @Override // com.amap.api.services.interfaces.IInputtipsSearch
    public final void requestInputtips(String str, String str2) throws AMapException {
        requestInputtips(str, str2, null);
    }

    @Override // com.amap.api.services.interfaces.IInputtipsSearch
    public final void requestInputtips(String str, String str2, String str3) throws AMapException {
        if (str == null || str.equals("")) {
            throw new AMapException("无效的参数 - IllegalArgumentException");
        }
        InputtipsQuery inputtipsQuery = new InputtipsQuery(str, str2);
        this.d = inputtipsQuery;
        inputtipsQuery.setType(str3);
        requestInputtipsAsyn();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<Tip> a(InputtipsQuery inputtipsQuery) throws AMapException {
        try {
            fm.a(this.f1085a);
            if (inputtipsQuery == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            if (inputtipsQuery.getKeyword() == null || inputtipsQuery.getKeyword().equals("")) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            return new fk(this.f1085a, inputtipsQuery).d();
        } catch (Throwable th) {
            fd.a(th, "Inputtips", "requestInputtips");
            if (th instanceof AMapException) {
                throw th;
            }
            return null;
        }
    }
}
