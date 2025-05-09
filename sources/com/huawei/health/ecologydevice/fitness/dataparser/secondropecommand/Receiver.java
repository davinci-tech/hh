package com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand;

import com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.CommandArrayList;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cyf;
import defpackage.cyg;
import defpackage.cyi;
import defpackage.cyj;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import okio.Utf8;

/* loaded from: classes3.dex */
public class Receiver implements CommandArrayList.ReceiveCallback {
    private Map<Byte, CommandArrayList> b;
    private CommandReceiveCallback d;

    public interface CommandReceiveCallback {
        void onReceiveSuccess(CommandArrayList commandArrayList);
    }

    private Receiver() {
        this.b = new ConcurrentHashMap();
    }

    public static Receiver d() {
        return d.f2289a;
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        private static final Receiver f2289a = new Receiver();
    }

    public void b(CommandReceiveCallback commandReceiveCallback) {
        this.d = commandReceiveCallback;
    }

    public void b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return;
        }
        byte b = bArr[0];
        boolean z = ((byte) (b & 128)) != 0;
        boolean z2 = ((byte) (b & 64)) != 0;
        byte b2 = (byte) (b & Utf8.REPLACEMENT_BYTE);
        LogUtil.a("PDROPE_Receiver", "moreData = ", Boolean.valueOf(z), ", needSplicing = ", Boolean.valueOf(z2), ", sequence=", Byte.valueOf(b2));
        if (!this.b.containsKey(Byte.valueOf(b2))) {
            CommandArrayList commandArrayList = new CommandArrayList();
            commandArrayList.d(this);
            commandArrayList.d(b2);
            this.b.put(Byte.valueOf(b2), commandArrayList);
        }
        byte b3 = (byte) (bArr[1] & 255);
        CommandArrayList commandArrayList2 = this.b.get(Byte.valueOf(b2));
        if (b3 == 0) {
            a(bArr, commandArrayList2, z2);
            return;
        }
        if (z) {
            cyj a2 = new cyj.a().a(bArr);
            commandArrayList2.b(a2.getCommand());
            commandArrayList2.c(a2.getCode());
            commandArrayList2.b(a2.getOrder(), a2);
            return;
        }
        cyg e = new cyg.a().e(bArr);
        commandArrayList2.b(e.getOrder() + 1);
        commandArrayList2.b(e.getCommand());
        commandArrayList2.c(e.getCode());
        commandArrayList2.b(e.getOrder(), e);
    }

    private void a(byte[] bArr, CommandArrayList commandArrayList, boolean z) {
        if (z) {
            cyi d2 = new cyi.e().d(bArr);
            commandArrayList.b(d2.getCommand());
            commandArrayList.c(d2.getCode());
            commandArrayList.b(d2.e());
            commandArrayList.b(0, d2);
            return;
        }
        cyf a2 = new cyf.c().a(bArr);
        LogUtil.a("PDROPE_Receiver", "command = ", a2.toString());
        commandArrayList.b(a2.getOrder() + 1);
        commandArrayList.b(a2.getCommand());
        commandArrayList.c(a2.getCode());
        commandArrayList.b(a2.a());
        commandArrayList.b(0, a2);
    }

    @Override // com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand.CommandArrayList.ReceiveCallback
    public void onReceiveSuccess(byte b, CommandArrayList commandArrayList) {
        this.b.remove(Byte.valueOf(b));
        commandArrayList.g();
        boolean b2 = commandArrayList.b();
        LogUtil.a("PDROPE_Receiver", "commandArrayList.checkData() = ", Boolean.valueOf(b2));
        CommandReceiveCallback commandReceiveCallback = this.d;
        if (commandReceiveCallback == null || !b2) {
            return;
        }
        commandReceiveCallback.onReceiveSuccess(commandArrayList);
    }
}
