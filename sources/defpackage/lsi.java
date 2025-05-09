package defpackage;

import android.content.Context;
import android.os.Build;
import android.os.IBinder;
import android.os.MemoryFile;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.huawei.ohos.localability.FormCallback;
import com.huawei.ohos.localability.FormException;
import com.huawei.ohos.localability.ListenerType;
import com.huawei.ohos.localability.VisibilityChangeNotify;
import com.huawei.ohos.localability.a;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class lsi {
    private static final lsi c = new lsi();

    /* renamed from: a, reason: collision with root package name */
    private volatile b f14849a;
    private final Object e = new Object();
    private volatile int b = 0;
    private volatile IBinder d = null;
    private Map<Context, FormCallback> f = new HashMap();
    private Map<String, c> h = new HashMap();
    private final IBinder.DeathRecipient j = new a();

    /* JADX INFO: Access modifiers changed from: private */
    public void c(ListenerType listenerType, List<String> list, List<lsf> list2) {
        synchronized (this.e) {
            String str = listenerType + "_" + String.join("_", list);
            c cVar = this.h.get(str);
            if (cVar == null) {
                Log.e("AbilityFormSupplyProxy", "visibility change notify info is null: " + str);
            } else {
                VisibilityChangeNotify visibilityChangeNotify = cVar.c;
                if (visibilityChangeNotify != null) {
                    Log.i("AbilityFormSupplyProxy", "onVisibilityChange: " + str);
                    visibilityChangeNotify.onVisibilityChange(list2);
                }
            }
        }
    }

    public boolean e(long j, Context context, lsg lsgVar) throws FormException {
        if (j <= 0 || context == null || lsgVar == null) {
            Log.e("AbilityFormSupplyProxy", "updateForm param is not correct");
            throw new FormException(FormException.FormError.INPUT_PARAM_INVALID, "updateForm param is not correct");
        }
        if (this.b != 2) {
            Log.i("AbilityFormSupplyProxy", "update form " + j + " come from " + context.getPackageName());
            IBinder bZY_ = bZY_();
            if (bZY_ == null) {
                Log.e("AbilityFormSupplyProxy", "updateForm, getFmsProxy return null");
                throw new FormException(FormException.FormError.FMS_RPC_ERROR);
            }
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                try {
                    obtain.writeInterfaceToken("OHOS.AppExecFwk.IFormMgr");
                    obtain.writeLong(j);
                    obtain.writeString(context.getPackageName());
                    if (Build.VERSION.SDK_INT >= 29) {
                        obtain.writeBoolean(true);
                    } else {
                        obtain.writeInt(1);
                    }
                    if (!bZX_(obtain, lsgVar)) {
                        throw new FormException(FormException.FormError.INTERNAL_ERROR, "write js form data internal error");
                    }
                    bZY_.transact(26, obtain, obtain2, 0);
                    int readInt = obtain2.readInt();
                    if (readInt == 0) {
                        return true;
                    }
                    FormException.FormError a2 = a(readInt);
                    Log.e("AbilityFormSupplyProxy", "updateForm error, error code " + a2);
                    throw new FormException(a2);
                } catch (RemoteException unused) {
                    throw new FormException(FormException.FormError.SEND_FMS_MSG_ERROR, "form request transact occurs");
                }
            } finally {
                obtain.recycle();
                obtain2.recycle();
            }
        }
        Log.e("AbilityFormSupplyProxy", "fms is starting");
        throw new FormException(FormException.FormError.FORM_IN_RECOVER);
    }

    public void e(Context context, FormCallback formCallback) throws FormException {
        Log.i("AbilityFormSupplyProxy", "registerFormsVisibleNotifier come from aar");
        if (context == null || formCallback == null) {
            Log.e("AbilityFormSupplyProxy", "registerFormsVisibleNotifier param is not correct");
            throw new FormException(FormException.FormError.INPUT_PARAM_INVALID, "registerFormsVisibleNotifier param is not correct");
        }
        if (this.b == 2) {
            Log.e("AbilityFormSupplyProxy", "fms is starting");
            throw new FormException(FormException.FormError.FORM_IN_RECOVER);
        }
        synchronized (this.e) {
            if (this.f.get(context) != null) {
                Log.w("AbilityFormSupplyProxy", "has already register");
            } else {
                this.f.put(context, formCallback);
                c(context, formCallback, true);
            }
        }
    }

    public void a(Context context) throws FormException {
        Log.i("AbilityFormSupplyProxy", "unregisterFormsVisibleNotifier come from aar");
        if (context == null) {
            Log.e("AbilityFormSupplyProxy", "unregisterFormsVisibleNotifier param is not correct");
            throw new FormException(FormException.FormError.INPUT_PARAM_INVALID, "unregisterFormsVisibleNotifier param is not correct");
        }
        if (this.b == 2) {
            Log.e("AbilityFormSupplyProxy", "fms is starting");
            throw new FormException(FormException.FormError.FORM_IN_RECOVER);
        }
        synchronized (this.e) {
            if (this.f.get(context) == null) {
                Log.w("AbilityFormSupplyProxy", "has not register yet");
            } else {
                this.f.remove(context);
                c(context, (FormCallback) null, false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e() {
        for (int i = 0; i < 30; i++) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                Log.e("AbilityFormSupplyProxy", "reconnect, InterruptedException exception " + e.getMessage());
            }
            if (bZY_() == null) {
                Log.w("AbilityFormSupplyProxy", "get fms proxy fail, try again");
            } else {
                Log.i("AbilityFormSupplyProxy", "get bms and fms proxy success");
                return true;
            }
        }
        return false;
    }

    public static lsi a() {
        return c;
    }

    private IBinder bZY_() {
        if (this.d != null) {
            return this.d;
        }
        IBinder a2 = lsh.a();
        if (a2 == null) {
            return a2;
        }
        this.d = a2;
        try {
            this.d.linkToDeath(this.j, 0);
        } catch (RemoteException e) {
            Log.e("AbilityFormSupplyProxy", "fms proxy link to death error " + e.getMessage());
            this.d = null;
        }
        return this.d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(ListenerType listenerType, List<String> list, VisibilityChangeNotify visibilityChangeNotify, boolean z) {
        synchronized (this) {
            Log.i("AbilityFormSupplyProxy", "enableVisibilityChangeNotify come from aar, listenerType is " + listenerType + ", hostBundleNames is " + list + ", enable flag is " + z);
            IBinder bZY_ = bZY_();
            if (bZY_ == null) {
                Log.e("AbilityFormSupplyProxy", "enableVisibilityChangeNotify, getFmsProxy return null");
                return false;
            }
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                try {
                    obtain.writeInterfaceToken("OHOS.AppExecFwk.IFormMgr");
                    obtain.writeInt(listenerType.getValue());
                    obtain.writeInt(list.size());
                    Iterator<String> it = list.iterator();
                    while (it.hasNext()) {
                        obtain.writeString(it.next());
                    }
                    if (Build.VERSION.SDK_INT >= 29) {
                        obtain.writeBoolean(z);
                    } else {
                        obtain.writeInt(z ? 1 : 0);
                    }
                    obtain.writeStrongBinder(c().asBinder());
                    bZY_.transact(37, obtain, obtain2, 0);
                    if (obtain2.dataAvail() == 0) {
                        Log.e("AbilityFormSupplyProxy", "enableVisibilityChangeNotify, method does not exist");
                        return false;
                    }
                    int readInt = obtain2.readInt();
                    if (readInt != 0) {
                        Log.e("AbilityFormSupplyProxy", "handle form visible change notify error, error code " + readInt);
                        return false;
                    }
                    String str = listenerType + "_" + String.join("_", list);
                    if (z) {
                        this.h.put(str, new c(listenerType, list, visibilityChangeNotify));
                    } else {
                        this.h.remove(str);
                    }
                    Log.i("AbilityFormSupplyProxy", "enableVisibilityChangeNotify success");
                    return true;
                } finally {
                    obtain.recycle();
                    obtain2.recycle();
                }
            } catch (RemoteException e) {
                Log.e("AbilityFormSupplyProxy", "handle enableVisibilityChangeNotify transact occurs exception:" + e.getMessage());
                return false;
            }
        }
    }

    private boolean bZX_(Parcel parcel, lsg lsgVar) {
        MemoryFile memoryFile;
        parcel.writeString(lsgVar.a());
        parcel.writeInt(lsgVar.e());
        for (Map.Entry<String, byte[]> entry : lsgVar.d().entrySet()) {
            MemoryFile memoryFile2 = null;
            try {
                try {
                    try {
                        String key = entry.getKey();
                        byte[] value = entry.getValue();
                        if (key != null && value != null) {
                            memoryFile = new MemoryFile(key, value.length);
                            try {
                                try {
                                    memoryFile.writeBytes(value, 0, 0, value.length);
                                    Object invoke = memoryFile.getClass().getDeclaredMethod("getFileDescriptor", new Class[0]).invoke(memoryFile, new Object[0]);
                                    if (!(invoke instanceof FileDescriptor)) {
                                        Log.e("AbilityFormSupplyProxy", "FileDescriptor type error");
                                        memoryFile.close();
                                        return false;
                                    }
                                    parcel.writeString(key);
                                    parcel.writeInt(value.length);
                                    parcel.writeFileDescriptor((FileDescriptor) invoke);
                                    memoryFile.close();
                                } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
                                    memoryFile2 = memoryFile;
                                    Log.e("AbilityFormSupplyProxy", "writeJsFormBindingData get fd error occurs");
                                    if (memoryFile2 != null) {
                                        memoryFile2.close();
                                    }
                                    return false;
                                }
                            } catch (IOException unused2) {
                                memoryFile2 = memoryFile;
                                Log.e("AbilityFormSupplyProxy", "writeJsFormBindingData IOException occurs");
                                if (memoryFile2 != null) {
                                    memoryFile2.close();
                                }
                                return false;
                            } catch (Throwable th) {
                                th = th;
                                if (memoryFile != null) {
                                    memoryFile.close();
                                }
                                throw th;
                            }
                        }
                        Log.e("AbilityFormSupplyProxy", "JsFormBindingData is null");
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        memoryFile = null;
                    }
                } catch (IOException unused3) {
                }
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused4) {
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<Long> list) {
        if (list == null) {
            Log.e("AbilityFormSupplyProxy", "processFormVisible form failed, no form id");
            return;
        }
        synchronized (this.e) {
            Iterator<FormCallback> it = this.f.values().iterator();
            while (it.hasNext()) {
                it.next().onFormsVisible(list);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Context context, FormCallback formCallback, boolean z) throws FormException {
        Log.i("AbilityFormSupplyProxy", "enableFormsVisibleNotifierOrNot come from aar");
        IBinder bZY_ = bZY_();
        if (bZY_ == null) {
            Log.e("AbilityFormSupplyProxy", "enableFormsVisibleNotifierOrNot, getFmsProxy return null");
            throw new FormException(FormException.FormError.FMS_RPC_ERROR);
        }
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            try {
                obtain.writeInterfaceToken("OHOS.AppExecFwk.IFormMgr");
                obtain.writeString(context.getPackageName());
                if (Build.VERSION.SDK_INT >= 29) {
                    obtain.writeBoolean(z);
                } else {
                    obtain.writeInt(z ? 1 : 0);
                }
                if (formCallback != null) {
                    obtain.writeStrongBinder(c().asBinder());
                }
                bZY_.transact(27, obtain, obtain2, 0);
                int readInt = obtain2.readInt();
                if (readInt == 0) {
                    return;
                }
                FormException.FormError a2 = a(readInt);
                Log.e("AbilityFormSupplyProxy", "handle form visible notify error, error code " + a2);
                throw new FormException(a2);
            } catch (RemoteException e) {
                Log.e("AbilityFormSupplyProxy", "handleFormsVisibleNotifier transact occurs exception:" + e.getMessage());
                throw new FormException(FormException.FormError.SEND_FMS_MSG_ERROR, "handle forms visible transact occurs exception:" + e.getMessage());
            }
        } finally {
            obtain.recycle();
            obtain2.recycle();
        }
    }

    private FormException.FormError a(int i) {
        FormException.FormError a2 = FormException.FormError.a(i);
        if (a2 != null) {
            return a2;
        }
        FormException.FormError formError = FormException.FormError.INTERNAL_ERROR;
        Log.e("AbilityFormSupplyProxy", "cannot get specific error, use default " + formError);
        return formError;
    }

    private b c() {
        b bVar;
        synchronized (this) {
            if (this.f14849a == null) {
                this.f14849a = new b(this, this);
            }
            bVar = this.f14849a;
        }
        return bVar;
    }

    class a implements IBinder.DeathRecipient {
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            synchronized (lsi.this.e) {
                while (lsi.this.b == 2) {
                    try {
                        Log.e("AbilityFormSupplyProxy", "remote died, wait for previous recover finish");
                        lsi.this.e.wait();
                    } catch (InterruptedException e) {
                        Log.e("AbilityFormSupplyProxy", "wait lock occurs interrupted exception " + e);
                    }
                }
                lsi.this.b = 2;
                if (lsi.this.d != null) {
                    lsi.this.d.unlinkToDeath(lsi.this.j, 0);
                    lsi.this.d = null;
                }
            }
            try {
                if (!lsi.this.e()) {
                    Log.e("AbilityFormSupplyProxy", "binderDied, try to reconnect to bms and fms failed");
                    synchronized (lsi.this.e) {
                        lsi.this.b = 1;
                        lsi.this.e.notifyAll();
                    }
                    return;
                }
                try {
                    synchronized (lsi.this.e) {
                        for (Context context : lsi.this.f.keySet()) {
                            lsi lsiVar = lsi.this;
                            lsiVar.c(context, (FormCallback) lsiVar.f.get(context), true);
                        }
                        Iterator it = lsi.this.h.keySet().iterator();
                        while (it.hasNext()) {
                            c cVar = (c) lsi.this.h.get((String) it.next());
                            lsi.this.a(cVar.b, cVar.d, cVar.c, true);
                        }
                    }
                    synchronized (lsi.this.e) {
                        lsi.this.b = 0;
                        lsi.this.e.notifyAll();
                    }
                } catch (FormException unused) {
                    Log.e("AbilityFormSupplyProxy", "retry register form visible fail");
                    synchronized (lsi.this.e) {
                        lsi.this.b = 0;
                        lsi.this.e.notifyAll();
                    }
                }
            } catch (Throwable th) {
                synchronized (lsi.this.e) {
                    lsi.this.b = 0;
                    lsi.this.e.notifyAll();
                    throw th;
                }
            }
        }

        a() {
        }
    }

    public class b extends a.AbstractBinderC0170a {
        public WeakReference<lsi> c;

        public b(lsi lsiVar, lsi lsiVar2) {
            this.c = new WeakReference<>(lsiVar2);
        }
    }

    static class c {
        ListenerType b;
        VisibilityChangeNotify c;
        List<String> d;

        c(ListenerType listenerType, List<String> list, VisibilityChangeNotify visibilityChangeNotify) {
            this.b = listenerType;
            this.d = list;
            this.c = visibilityChangeNotify;
        }
    }

    private lsi() {
    }
}
