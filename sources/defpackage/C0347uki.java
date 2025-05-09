package defpackage;

import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.seuqneceutils.SequenceDetailFieldConfig;
import com.huawei.operation.ble.BleConstants;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import com.huawei.ui.main.stories.nps.interactors.mode.TypeParams;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.sequences.Sequence;

@Metadata(d1 = {"\u0000\u0084\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0019\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b!\u001a\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H\u0000\u001a\u001c\u0010\f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a\u001c\u0010\u0011\u001a\u00020\r*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a\u001f\u0010\u0012\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0086\u0002\u001a\u001f\u0010\u0012\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0086\u0002\u001a\u0015\u0010\u0012\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0016H\u0087\n\u001a\u0018\u0010\u0017\u001a\u00020\u0010*\u0004\u0018\u00010\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002H\u0000\u001a\u0018\u0010\u0018\u001a\u00020\u0010*\u0004\u0018\u00010\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002H\u0000\u001a\u001c\u0010\u0019\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a\u001c\u0010\u0019\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a:\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\r\u0018\u00010\u001c*\u00020\u00022\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\r0\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001aE\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\r\u0018\u00010\u001c*\u00020\u00022\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\r0\u001e2\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u0010H\u0002¢\u0006\u0002\b!\u001a:\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\r\u0018\u00010\u001c*\u00020\u00022\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\r0\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a\u0012\u0010#\u001a\u00020\u0010*\u00020\u00022\u0006\u0010$\u001a\u00020\u0006\u001a7\u0010%\u001a\u0002H&\"\f\b\u0000\u0010'*\u00020\u0002*\u0002H&\"\u0004\b\u0001\u0010&*\u0002H'2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H&0)H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010*\u001a7\u0010+\u001a\u0002H&\"\f\b\u0000\u0010'*\u00020\u0002*\u0002H&\"\u0004\b\u0001\u0010&*\u0002H'2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H&0)H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010*\u001a&\u0010,\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a;\u0010,\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010 \u001a\u00020\u0010H\u0002¢\u0006\u0002\b.\u001a&\u0010,\u001a\u00020\u0006*\u00020\u00022\u0006\u0010/\u001a\u00020\r2\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a&\u00100\u001a\u00020\u0006*\u00020\u00022\u0006\u00101\u001a\u0002022\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a,\u00100\u001a\u00020\u0006*\u00020\u00022\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\r0\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a\r\u00103\u001a\u00020\u0010*\u00020\u0002H\u0087\b\u001a\r\u00104\u001a\u00020\u0010*\u00020\u0002H\u0087\b\u001a\r\u00105\u001a\u00020\u0010*\u00020\u0002H\u0087\b\u001a \u00106\u001a\u00020\u0010*\u0004\u0018\u00010\u0002H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u001a \u00107\u001a\u00020\u0010*\u0004\u0018\u00010\u0002H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u001a\r\u00108\u001a\u000209*\u00020\u0002H\u0086\u0002\u001a&\u0010:\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a&\u0010:\u001a\u00020\u0006*\u00020\u00022\u0006\u0010/\u001a\u00020\r2\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a&\u0010;\u001a\u00020\u0006*\u00020\u00022\u0006\u00101\u001a\u0002022\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a,\u0010;\u001a\u00020\u0006*\u00020\u00022\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\r0\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a\u0010\u0010<\u001a\b\u0012\u0004\u0012\u00020\r0=*\u00020\u0002\u001a\u0010\u0010>\u001a\b\u0012\u0004\u0012\u00020\r0?*\u00020\u0002\u001a\u0015\u0010@\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0016H\u0087\f\u001a\u000f\u0010A\u001a\u00020\r*\u0004\u0018\u00010\rH\u0087\b\u001a\u001c\u0010B\u001a\u00020\u0002*\u00020\u00022\u0006\u0010C\u001a\u00020\u00062\b\b\u0002\u0010D\u001a\u00020\u0014\u001a\u001c\u0010B\u001a\u00020\r*\u00020\r2\u0006\u0010C\u001a\u00020\u00062\b\b\u0002\u0010D\u001a\u00020\u0014\u001a\u001c\u0010E\u001a\u00020\u0002*\u00020\u00022\u0006\u0010C\u001a\u00020\u00062\b\b\u0002\u0010D\u001a\u00020\u0014\u001a\u001c\u0010E\u001a\u00020\r*\u00020\r2\u0006\u0010C\u001a\u00020\u00062\b\b\u0002\u0010D\u001a\u00020\u0014\u001aG\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00010=*\u00020\u00022\u000e\u0010G\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0H2\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u000b\u001a\u00020\u0006H\u0002¢\u0006\u0004\bI\u0010J\u001a=\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00010=*\u00020\u00022\u0006\u0010G\u001a\u0002022\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u000b\u001a\u00020\u0006H\u0002¢\u0006\u0002\bI\u001a4\u0010K\u001a\u00020\u0010*\u00020\u00022\u0006\u0010L\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010M\u001a\u00020\u00062\u0006\u0010C\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0000\u001a\u0012\u0010N\u001a\u00020\u0002*\u00020\u00022\u0006\u0010O\u001a\u00020\u0002\u001a\u0012\u0010N\u001a\u00020\r*\u00020\r2\u0006\u0010O\u001a\u00020\u0002\u001a\u001a\u0010P\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u0006\u001a\u0012\u0010P\u001a\u00020\u0002*\u00020\u00022\u0006\u0010Q\u001a\u00020\u0001\u001a\u001d\u0010P\u001a\u00020\r*\u00020\r2\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u0006H\u0087\b\u001a\u0015\u0010P\u001a\u00020\r*\u00020\r2\u0006\u0010Q\u001a\u00020\u0001H\u0087\b\u001a\u0012\u0010R\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0002\u001a\u0012\u0010R\u001a\u00020\r*\u00020\r2\u0006\u0010\u001a\u001a\u00020\u0002\u001a\u0012\u0010S\u001a\u00020\u0002*\u00020\u00022\u0006\u0010T\u001a\u00020\u0002\u001a\u001a\u0010S\u001a\u00020\u0002*\u00020\u00022\u0006\u0010O\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0002\u001a\u0012\u0010S\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\u0002\u001a\u001a\u0010S\u001a\u00020\r*\u00020\r2\u0006\u0010O\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0002\u001a.\u0010U\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\u0014\b\b\u0010V\u001a\u000e\u0012\u0004\u0012\u00020X\u0012\u0004\u0012\u00020\u00020WH\u0087\bø\u0001\u0000\u001a\u001d\u0010U\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010Y\u001a\u00020\rH\u0087\b\u001a$\u0010Z\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\u00142\u0006\u0010Y\u001a\u00020\r2\b\b\u0002\u0010[\u001a\u00020\r\u001a$\u0010Z\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\r2\u0006\u0010Y\u001a\u00020\r2\b\b\u0002\u0010[\u001a\u00020\r\u001a$\u0010\\\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\u00142\u0006\u0010Y\u001a\u00020\r2\b\b\u0002\u0010[\u001a\u00020\r\u001a$\u0010\\\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\r2\u0006\u0010Y\u001a\u00020\r2\b\b\u0002\u0010[\u001a\u00020\r\u001a$\u0010]\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\u00142\u0006\u0010Y\u001a\u00020\r2\b\b\u0002\u0010[\u001a\u00020\r\u001a$\u0010]\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\r2\u0006\u0010Y\u001a\u00020\r2\b\b\u0002\u0010[\u001a\u00020\r\u001a$\u0010^\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\u00142\u0006\u0010Y\u001a\u00020\r2\b\b\u0002\u0010[\u001a\u00020\r\u001a$\u0010^\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\r2\u0006\u0010Y\u001a\u00020\r2\b\b\u0002\u0010[\u001a\u00020\r\u001a\u001d\u0010_\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010Y\u001a\u00020\rH\u0087\b\u001a)\u0010`\u001a\u00020\r*\u00020\r2\u0012\u0010V\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00140WH\u0087\bø\u0001\u0000¢\u0006\u0002\ba\u001a)\u0010`\u001a\u00020\r*\u00020\r2\u0012\u0010V\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00020WH\u0087\bø\u0001\u0000¢\u0006\u0002\bb\u001a\"\u0010c\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u00062\u0006\u0010Y\u001a\u00020\u0002\u001a\u001a\u0010c\u001a\u00020\u0002*\u00020\u00022\u0006\u0010Q\u001a\u00020\u00012\u0006\u0010Y\u001a\u00020\u0002\u001a%\u0010c\u001a\u00020\r*\u00020\r2\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u00062\u0006\u0010Y\u001a\u00020\u0002H\u0087\b\u001a\u001d\u0010c\u001a\u00020\r*\u00020\r2\u0006\u0010Q\u001a\u00020\u00012\u0006\u0010Y\u001a\u00020\u0002H\u0087\b\u001a=\u0010d\u001a\b\u0012\u0004\u0012\u00020\r0?*\u00020\u00022\u0012\u0010G\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0H\"\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u000b\u001a\u00020\u0006¢\u0006\u0002\u0010e\u001a0\u0010d\u001a\b\u0012\u0004\u0012\u00020\r0?*\u00020\u00022\n\u0010G\u001a\u000202\"\u00020\u00142\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u000b\u001a\u00020\u0006\u001a/\u0010d\u001a\b\u0012\u0004\u0012\u00020\r0?*\u00020\u00022\u0006\u0010T\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\u0006H\u0002¢\u0006\u0002\bf\u001a%\u0010d\u001a\b\u0012\u0004\u0012\u00020\r0?*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u000b\u001a\u00020\u0006H\u0087\b\u001a=\u0010g\u001a\b\u0012\u0004\u0012\u00020\r0=*\u00020\u00022\u0012\u0010G\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0H\"\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u000b\u001a\u00020\u0006¢\u0006\u0002\u0010h\u001a0\u0010g\u001a\b\u0012\u0004\u0012\u00020\r0=*\u00020\u00022\n\u0010G\u001a\u000202\"\u00020\u00142\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u000b\u001a\u00020\u0006\u001a%\u0010g\u001a\b\u0012\u0004\u0012\u00020\r0=*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u000b\u001a\u00020\u0006H\u0087\b\u001a\u001c\u0010i\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a\u001c\u0010i\u001a\u00020\u0010*\u00020\u00022\u0006\u0010O\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a$\u0010i\u001a\u00020\u0010*\u00020\u00022\u0006\u0010O\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a\u0012\u0010j\u001a\u00020\u0002*\u00020\u00022\u0006\u0010Q\u001a\u00020\u0001\u001a\u001d\u0010j\u001a\u00020\u0002*\u00020\r2\u0006\u0010k\u001a\u00020\u00062\u0006\u0010l\u001a\u00020\u0006H\u0087\b\u001a\u001f\u0010m\u001a\u00020\r*\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010-\u001a\u00020\u0006H\u0087\b\u001a\u0012\u0010m\u001a\u00020\r*\u00020\u00022\u0006\u0010Q\u001a\u00020\u0001\u001a\u0012\u0010m\u001a\u00020\r*\u00020\r2\u0006\u0010Q\u001a\u00020\u0001\u001a\u001c\u0010n\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\u00142\b\b\u0002\u0010[\u001a\u00020\r\u001a\u001c\u0010n\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\r2\b\b\u0002\u0010[\u001a\u00020\r\u001a\u001c\u0010o\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\u00142\b\b\u0002\u0010[\u001a\u00020\r\u001a\u001c\u0010o\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\r2\b\b\u0002\u0010[\u001a\u00020\r\u001a\u001c\u0010p\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\u00142\b\b\u0002\u0010[\u001a\u00020\r\u001a\u001c\u0010p\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\r2\b\b\u0002\u0010[\u001a\u00020\r\u001a\u001c\u0010q\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\u00142\b\b\u0002\u0010[\u001a\u00020\r\u001a\u001c\u0010q\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\r2\b\b\u0002\u0010[\u001a\u00020\r\u001a\f\u0010r\u001a\u00020\u0010*\u00020\rH\u0007\u001a\u0013\u0010s\u001a\u0004\u0018\u00010\u0010*\u00020\rH\u0007¢\u0006\u0002\u0010t\u001a\n\u0010u\u001a\u00020\u0002*\u00020\u0002\u001a$\u0010u\u001a\u00020\u0002*\u00020\u00022\u0012\u0010v\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00100WH\u0086\bø\u0001\u0000\u001a\u0016\u0010u\u001a\u00020\u0002*\u00020\u00022\n\u00101\u001a\u000202\"\u00020\u0014\u001a\r\u0010u\u001a\u00020\r*\u00020\rH\u0087\b\u001a$\u0010u\u001a\u00020\r*\u00020\r2\u0012\u0010v\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00100WH\u0086\bø\u0001\u0000\u001a\u0016\u0010u\u001a\u00020\r*\u00020\r2\n\u00101\u001a\u000202\"\u00020\u0014\u001a\n\u0010w\u001a\u00020\u0002*\u00020\u0002\u001a$\u0010w\u001a\u00020\u0002*\u00020\u00022\u0012\u0010v\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00100WH\u0086\bø\u0001\u0000\u001a\u0016\u0010w\u001a\u00020\u0002*\u00020\u00022\n\u00101\u001a\u000202\"\u00020\u0014\u001a\r\u0010w\u001a\u00020\r*\u00020\rH\u0087\b\u001a$\u0010w\u001a\u00020\r*\u00020\r2\u0012\u0010v\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00100WH\u0086\bø\u0001\u0000\u001a\u0016\u0010w\u001a\u00020\r*\u00020\r2\n\u00101\u001a\u000202\"\u00020\u0014\u001a\n\u0010x\u001a\u00020\u0002*\u00020\u0002\u001a$\u0010x\u001a\u00020\u0002*\u00020\u00022\u0012\u0010v\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00100WH\u0086\bø\u0001\u0000\u001a\u0016\u0010x\u001a\u00020\u0002*\u00020\u00022\n\u00101\u001a\u000202\"\u00020\u0014\u001a\r\u0010x\u001a\u00020\r*\u00020\rH\u0087\b\u001a$\u0010x\u001a\u00020\r*\u00020\r2\u0012\u0010v\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00100WH\u0086\bø\u0001\u0000\u001a\u0016\u0010x\u001a\u00020\r*\u00020\r2\n\u00101\u001a\u000202\"\u00020\u0014\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006y"}, d2 = {"indices", "Lkotlin/ranges/IntRange;", "", "getIndices", "(Ljava/lang/CharSequence;)Lkotlin/ranges/IntRange;", "lastIndex", "", "getLastIndex", "(Ljava/lang/CharSequence;)I", "requireNonNegativeLimit", "", BleConstants.LIMIT, "commonPrefixWith", "", "other", "ignoreCase", "", "commonSuffixWith", "contains", SequenceDetailFieldConfig.CHAR, "", "regex", "Lkotlin/text/Regex;", "contentEqualsIgnoreCaseImpl", "contentEqualsImpl", "endsWith", "suffix", "findAnyOf", "Lkotlin/Pair;", "strings", "", "startIndex", "last", "findAnyOf$StringsKt__StringsKt", "findLastAnyOf", "hasSurrogatePairAt", "index", "ifBlank", "R", TypeParams.SEARCH_CODE, "defaultValue", "Lkotlin/Function0;", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "ifEmpty", "indexOf", "endIndex", "indexOf$StringsKt__StringsKt", "string", "indexOfAny", "chars", "", "isEmpty", "isNotBlank", "isNotEmpty", "isNullOrBlank", "isNullOrEmpty", "iterator", "Lkotlin/collections/CharIterator;", "lastIndexOf", "lastIndexOfAny", "lineSequence", "Lkotlin/sequences/Sequence;", "lines", "", "matches", "orEmpty", "padEnd", "length", "padChar", "padStart", "rangesDelimitedBy", "delimiters", "", "rangesDelimitedBy$StringsKt__StringsKt", "(Ljava/lang/CharSequence;[Ljava/lang/String;IZI)Lkotlin/sequences/Sequence;", "regionMatchesImpl", "thisOffset", "otherOffset", "removePrefix", "prefix", "removeRange", "range", "removeSuffix", "removeSurrounding", "delimiter", "replace", "transform", "Lkotlin/Function1;", "Lkotlin/text/MatchResult;", "replacement", "replaceAfter", "missingDelimiterValue", "replaceAfterLast", "replaceBefore", "replaceBeforeLast", "replaceFirst", "replaceFirstChar", "replaceFirstCharWithChar", "replaceFirstCharWithCharSequence", "replaceRange", "split", "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Ljava/util/List;", "split$StringsKt__StringsKt", "splitToSequence", "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Lkotlin/sequences/Sequence;", "startsWith", "subSequence", "start", "end", "substring", "substringAfter", "substringAfterLast", "substringBefore", "substringBeforeLast", "toBooleanStrict", "toBooleanStrictOrNull", "(Ljava/lang/String;)Ljava/lang/Boolean;", "trim", "predicate", "trimEnd", "trimStart", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/text/StringsKt")
/* renamed from: uki, reason: from Kotlin metadata and case insensitive filesystem */
/* loaded from: classes.dex */
public class C0347uki extends CASE_INSENSITIVE_ORDER {
    public static final CharSequence d(CharSequence charSequence, int i, char c2) {
        uhy.e((Object) charSequence, "");
        if (i < 0) {
            throw new IllegalArgumentException("Desired length " + i + " is less than zero.");
        }
        if (i <= charSequence.length()) {
            return charSequence.subSequence(0, charSequence.length());
        }
        StringBuilder sb = new StringBuilder(i);
        IntIterator b = new uiv(1, i - charSequence.length()).iterator();
        while (b.hasNext()) {
            b.nextInt();
            sb.append(c2);
        }
        sb.append(charSequence);
        return sb;
    }

    public static final String d(String str, int i, char c2) {
        uhy.e((Object) str, "");
        return ujy.d((CharSequence) str, i, c2).toString();
    }

    public static final uiv b(CharSequence charSequence) {
        uhy.e((Object) charSequence, "");
        return new uiv(0, charSequence.length() - 1);
    }

    public static final int e(CharSequence charSequence) {
        uhy.e((Object) charSequence, "");
        return charSequence.length() - 1;
    }

    public static final String c(CharSequence charSequence, uiv uivVar) {
        uhy.e((Object) charSequence, "");
        uhy.e((Object) uivVar, "");
        return charSequence.subSequence(uivVar.getStart().intValue(), uivVar.getEndInclusive().intValue() + 1).toString();
    }

    public static /* synthetic */ String a(String str, char c2, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return ujy.a(str, c2, str2);
    }

    public static final String a(String str, char c2, String str2) {
        uhy.e((Object) str, "");
        uhy.e((Object) str2, "");
        int a2 = ujy.a((CharSequence) str, c2, 0, false, 6, (Object) null);
        if (a2 == -1) {
            return str2;
        }
        String substring = str.substring(0, a2);
        uhy.a(substring, "");
        return substring;
    }

    public static /* synthetic */ String d(String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str3 = str;
        }
        return ujy.e(str, str2, str3);
    }

    public static final String e(String str, String str2, String str3) {
        uhy.e((Object) str, "");
        uhy.e((Object) str2, "");
        uhy.e((Object) str3, "");
        int c2 = ujy.c((CharSequence) str, str2, 0, false, 6, (Object) null);
        if (c2 == -1) {
            return str3;
        }
        String substring = str.substring(0, c2);
        uhy.a(substring, "");
        return substring;
    }

    public static /* synthetic */ String c(String str, char c2, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return ujy.e(str, c2, str2);
    }

    public static final String e(String str, char c2, String str2) {
        uhy.e((Object) str, "");
        uhy.e((Object) str2, "");
        int a2 = ujy.a((CharSequence) str, c2, 0, false, 6, (Object) null);
        if (a2 == -1) {
            return str2;
        }
        String substring = str.substring(a2 + 1, str.length());
        uhy.a(substring, "");
        return substring;
    }

    public static /* synthetic */ String a(String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str3 = str;
        }
        return ujy.b(str, str2, str3);
    }

    public static final String b(String str, String str2, String str3) {
        uhy.e((Object) str, "");
        uhy.e((Object) str2, "");
        uhy.e((Object) str3, "");
        int c2 = ujy.c((CharSequence) str, str2, 0, false, 6, (Object) null);
        if (c2 == -1) {
            return str3;
        }
        String substring = str.substring(c2 + str2.length(), str.length());
        uhy.a(substring, "");
        return substring;
    }

    public static /* synthetic */ String d(String str, char c2, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return ujy.d(str, c2, str2);
    }

    public static final String d(String str, char c2, String str2) {
        uhy.e((Object) str, "");
        uhy.e((Object) str2, "");
        int c3 = ujy.c((CharSequence) str, c2, 0, false, 6, (Object) null);
        if (c3 == -1) {
            return str2;
        }
        String substring = str.substring(c3 + 1, str.length());
        uhy.a(substring, "");
        return substring;
    }

    public static final String b(String str, CharSequence charSequence) {
        uhy.e((Object) str, "");
        uhy.e((Object) charSequence, "");
        if (!ujy.a((CharSequence) str, charSequence, false, 2, (Object) null)) {
            return str;
        }
        String substring = str.substring(charSequence.length());
        uhy.a(substring, "");
        return substring;
    }

    public static final String d(String str, CharSequence charSequence) {
        uhy.e((Object) str, "");
        uhy.e((Object) charSequence, "");
        if (!ujy.b((CharSequence) str, charSequence, false, 2, (Object) null)) {
            return str;
        }
        String substring = str.substring(0, str.length() - charSequence.length());
        uhy.a(substring, "");
        return substring;
    }

    public static final String b(String str, CharSequence charSequence, CharSequence charSequence2) {
        uhy.e((Object) str, "");
        uhy.e((Object) charSequence, "");
        uhy.e((Object) charSequence2, "");
        if (str.length() < charSequence.length() + charSequence2.length()) {
            return str;
        }
        String str2 = str;
        if (!ujy.a((CharSequence) str2, charSequence, false, 2, (Object) null) || !ujy.b((CharSequence) str2, charSequence2, false, 2, (Object) null)) {
            return str;
        }
        String substring = str.substring(charSequence.length(), str.length() - charSequence2.length());
        uhy.a(substring, "");
        return substring;
    }

    public static final String a(String str, CharSequence charSequence) {
        uhy.e((Object) str, "");
        uhy.e((Object) charSequence, "");
        return ujy.b(str, charSequence, charSequence);
    }

    public static final boolean d(CharSequence charSequence, int i, CharSequence charSequence2, int i2, int i3, boolean z) {
        uhy.e((Object) charSequence, "");
        uhy.e((Object) charSequence2, "");
        if (i2 < 0 || i < 0 || i > charSequence.length() - i3 || i2 > charSequence2.length() - i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (!ujm.c(charSequence.charAt(i + i4), charSequence2.charAt(i2 + i4), z)) {
                return false;
            }
        }
        return true;
    }

    public static /* synthetic */ boolean a(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return ujy.a(charSequence, charSequence2, z);
    }

    public static final boolean a(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        uhy.e((Object) charSequence, "");
        uhy.e((Object) charSequence2, "");
        if (!z && (charSequence instanceof String) && (charSequence2 instanceof String)) {
            return ujy.c((String) charSequence, (String) charSequence2, false, 2, (Object) null);
        }
        return ujy.d(charSequence, 0, charSequence2, 0, charSequence2.length(), z);
    }

    public static /* synthetic */ boolean b(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return ujy.b(charSequence, charSequence2, z);
    }

    public static final boolean b(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        uhy.e((Object) charSequence, "");
        uhy.e((Object) charSequence2, "");
        if (!z && (charSequence instanceof String) && (charSequence2 instanceof String)) {
            return ujy.b((String) charSequence, (String) charSequence2, false, 2, (Object) null);
        }
        return ujy.d(charSequence, charSequence.length() - charSequence2.length(), charSequence2, 0, charSequence2.length(), z);
    }

    public static final int b(CharSequence charSequence, char[] cArr, int i, boolean z) {
        uhy.e((Object) charSequence, "");
        uhy.e((Object) cArr, "");
        if (!z && cArr.length == 1 && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(uez.c(cArr), i);
        }
        IntIterator b = new uiv(uja.a(i, 0), ujy.e(charSequence)).iterator();
        while (b.hasNext()) {
            int nextInt = b.nextInt();
            char charAt = charSequence.charAt(nextInt);
            for (char c2 : cArr) {
                if (ujm.c(c2, charAt, z)) {
                    return nextInt;
                }
            }
        }
        return -1;
    }

    public static final int d(CharSequence charSequence, char[] cArr, int i, boolean z) {
        uhy.e((Object) charSequence, "");
        uhy.e((Object) cArr, "");
        if (!z && cArr.length == 1 && (charSequence instanceof String)) {
            return ((String) charSequence).lastIndexOf(uez.c(cArr), i);
        }
        for (int b = uja.b(i, ujy.e(charSequence)); -1 < b; b--) {
            char charAt = charSequence.charAt(b);
            for (char c2 : cArr) {
                if (ujm.c(c2, charAt, z)) {
                    return b;
                }
            }
        }
        return -1;
    }

    static /* synthetic */ int d(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2, int i3, Object obj) {
        if ((i3 & 16) != 0) {
            z2 = false;
        }
        return e(charSequence, charSequence2, i, i2, z, z2);
    }

    private static final int e(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2) {
        uiv d2;
        if (!z2) {
            d2 = new uiv(uja.a(i, 0), uja.b(i2, charSequence.length()));
        } else {
            d2 = uja.d(uja.b(i, ujy.e(charSequence)), uja.a(i2, 0));
        }
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            int b = d2.getB();
            int f17433a = d2.getF17433a();
            int d3 = d2.getD();
            if ((d3 <= 0 || b > f17433a) && (d3 >= 0 || f17433a > b)) {
                return -1;
            }
            while (!ujy.d((String) charSequence2, 0, (String) charSequence, b, charSequence2.length(), z)) {
                if (b == f17433a) {
                    return -1;
                }
                b += d3;
            }
            return b;
        }
        int b2 = d2.getB();
        int f17433a2 = d2.getF17433a();
        int d4 = d2.getD();
        if ((d4 <= 0 || b2 > f17433a2) && (d4 >= 0 || f17433a2 > b2)) {
            return -1;
        }
        while (!ujy.d(charSequence2, 0, charSequence, b2, charSequence2.length(), z)) {
            if (b2 == f17433a2) {
                return -1;
            }
            b2 += d4;
        }
        return b2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ueo<Integer, String> b(CharSequence charSequence, Collection<String> collection, int i, boolean z, boolean z2) {
        Object obj;
        Object obj2;
        if (!z && collection.size() == 1) {
            String str = (String) ufe.e(collection);
            int c2 = !z2 ? ujy.c(charSequence, str, i, false, 4, (Object) null) : ujy.b(charSequence, str, i, false, 4, null);
            if (c2 < 0) {
                return null;
            }
            return to.d(Integer.valueOf(c2), str);
        }
        uiv uivVar = !z2 ? new uiv(uja.a(i, 0), charSequence.length()) : uja.d(uja.b(i, ujy.e(charSequence)), 0);
        if (charSequence instanceof String) {
            int b = uivVar.getB();
            int f17433a = uivVar.getF17433a();
            int d2 = uivVar.getD();
            if ((d2 > 0 && b <= f17433a) || (d2 < 0 && f17433a <= b)) {
                while (true) {
                    Iterator<T> it = collection.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            obj2 = null;
                            break;
                        }
                        obj2 = it.next();
                        String str2 = (String) obj2;
                        if (ujy.d(str2, 0, (String) charSequence, b, str2.length(), z)) {
                            break;
                        }
                    }
                    String str3 = (String) obj2;
                    if (str3 == null) {
                        if (b == f17433a) {
                            break;
                        }
                        b += d2;
                    } else {
                        return to.d(Integer.valueOf(b), str3);
                    }
                }
            }
        } else {
            int b2 = uivVar.getB();
            int f17433a2 = uivVar.getF17433a();
            int d3 = uivVar.getD();
            if ((d3 > 0 && b2 <= f17433a2) || (d3 < 0 && f17433a2 <= b2)) {
                while (true) {
                    Iterator<T> it2 = collection.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            obj = null;
                            break;
                        }
                        obj = it2.next();
                        String str4 = (String) obj;
                        if (ujy.d(str4, 0, charSequence, b2, str4.length(), z)) {
                            break;
                        }
                    }
                    String str5 = (String) obj;
                    if (str5 == null) {
                        if (b2 == f17433a2) {
                            break;
                        }
                        b2 += d3;
                    } else {
                        return to.d(Integer.valueOf(b2), str5);
                    }
                }
            }
        }
        return null;
    }

    public static /* synthetic */ int a(CharSequence charSequence, char c2, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return ujy.b(charSequence, c2, i, z);
    }

    public static final int b(CharSequence charSequence, char c2, int i, boolean z) {
        uhy.e((Object) charSequence, "");
        if (z || !(charSequence instanceof String)) {
            return ujy.b(charSequence, new char[]{c2}, i, z);
        }
        return ((String) charSequence).indexOf(c2, i);
    }

    public static /* synthetic */ int c(CharSequence charSequence, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return ujy.e(charSequence, str, i, z);
    }

    public static final int e(CharSequence charSequence, String str, int i, boolean z) {
        uhy.e((Object) charSequence, "");
        uhy.e((Object) str, "");
        if (z || !(charSequence instanceof String)) {
            return d(charSequence, str, i, charSequence.length(), z, false, 16, null);
        }
        return ((String) charSequence).indexOf(str, i);
    }

    public static /* synthetic */ int c(CharSequence charSequence, char c2, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = ujy.e(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return ujy.a(charSequence, c2, i, z);
    }

    public static final int a(CharSequence charSequence, char c2, int i, boolean z) {
        uhy.e((Object) charSequence, "");
        if (z || !(charSequence instanceof String)) {
            return ujy.d(charSequence, new char[]{c2}, i, z);
        }
        return ((String) charSequence).lastIndexOf(c2, i);
    }

    public static /* synthetic */ int b(CharSequence charSequence, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = ujy.e(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return ujy.c(charSequence, str, i, z);
    }

    public static final int c(CharSequence charSequence, String str, int i, boolean z) {
        uhy.e((Object) charSequence, "");
        uhy.e((Object) str, "");
        if (z || !(charSequence instanceof String)) {
            return e(charSequence, (CharSequence) str, i, 0, z, true);
        }
        return ((String) charSequence).lastIndexOf(str, i);
    }

    public static /* synthetic */ boolean e(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return ujy.e(charSequence, charSequence2, z);
    }

    public static final boolean e(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        uhy.e((Object) charSequence, "");
        uhy.e((Object) charSequence2, "");
        return !(charSequence2 instanceof String) ? d(charSequence, charSequence2, 0, charSequence.length(), z, false, 16, null) < 0 : ujy.c(charSequence, (String) charSequence2, 0, z, 2, (Object) null) < 0;
    }

    public static /* synthetic */ boolean d(CharSequence charSequence, char c2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return ujy.a(charSequence, c2, z);
    }

    public static final boolean a(CharSequence charSequence, char c2, boolean z) {
        uhy.e((Object) charSequence, "");
        return ujy.a(charSequence, c2, 0, z, 2, (Object) null) >= 0;
    }

    static /* synthetic */ Sequence c(CharSequence charSequence, char[] cArr, int i, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        return d(charSequence, cArr, i, z, i2);
    }

    private static final Sequence<uiv> d(CharSequence charSequence, char[] cArr, int i, boolean z, int i2) {
        ujy.a(i2);
        return new ujs(charSequence, i, i2, new c(cArr, z));
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\r\n\u0002\b\u0002\u0010\u0000\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0002H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "", "", "currentIndex", TrackConstants$Opers.INVOKE}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: uki$c */
    static final class c extends Lambda implements Function2<CharSequence, Integer, ueo<? extends Integer, ? extends Integer>> {
        final /* synthetic */ char[] c;
        final /* synthetic */ boolean d;

        @Override // kotlin.jvm.functions.Function2
        public /* synthetic */ ueo<? extends Integer, ? extends Integer> invoke(CharSequence charSequence, Integer num) {
            return d(charSequence, num.intValue());
        }

        public final ueo<Integer, Integer> d(CharSequence charSequence, int i) {
            uhy.e((Object) charSequence, "");
            int b = ujy.b(charSequence, this.c, i, this.d);
            if (b < 0) {
                return null;
            }
            return to.d(Integer.valueOf(b), 1);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(char[] cArr, boolean z) {
            super(2);
            this.c = cArr;
            this.d = z;
        }
    }

    static /* synthetic */ Sequence b(CharSequence charSequence, String[] strArr, int i, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        return e(charSequence, strArr, i, z, i2);
    }

    private static final Sequence<uiv> e(CharSequence charSequence, String[] strArr, int i, boolean z, int i2) {
        ujy.a(i2);
        return new ujs(charSequence, i, i2, new a(uez.c(strArr), z));
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\r\n\u0002\b\u0002\u0010\u0000\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0002H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "", "", "currentIndex", TrackConstants$Opers.INVOKE}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: uki$a */
    static final class a extends Lambda implements Function2<CharSequence, Integer, ueo<? extends Integer, ? extends Integer>> {
        final /* synthetic */ List<String> c;
        final /* synthetic */ boolean d;

        public final ueo<Integer, Integer> e(CharSequence charSequence, int i) {
            uhy.e((Object) charSequence, "");
            ueo b = C0347uki.b(charSequence, (Collection<String>) this.c, i, this.d, false);
            if (b != null) {
                return to.d(b.e(), Integer.valueOf(((String) b.c()).length()));
            }
            return null;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* synthetic */ ueo<? extends Integer, ? extends Integer> invoke(CharSequence charSequence, Integer num) {
            return e(charSequence, num.intValue());
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(List<String> list, boolean z) {
            super(2);
            this.c = list;
            this.d = z;
        }
    }

    public static final void a(int i) {
        if (i >= 0) {
            return;
        }
        throw new IllegalArgumentException(("Limit must be non-negative, but was " + i).toString());
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lkotlin/ranges/IntRange;", TrackConstants$Opers.INVOKE}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: uki$d */
    static final class d extends Lambda implements Function1<uiv, String> {
        final /* synthetic */ CharSequence e;

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public final String invoke(uiv uivVar) {
            uhy.e((Object) uivVar, "");
            return ujy.c(this.e, uivVar);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(CharSequence charSequence) {
            super(1);
            this.e = charSequence;
        }
    }

    public static /* synthetic */ Sequence e(CharSequence charSequence, String[] strArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return ujy.a(charSequence, strArr, z, i);
    }

    public static final Sequence<String> a(CharSequence charSequence, String[] strArr, boolean z, int i) {
        uhy.e((Object) charSequence, "");
        uhy.e((Object) strArr, "");
        return ujh.b(b(charSequence, strArr, 0, z, i, 2, null), (Function1) new d(charSequence));
    }

    public static /* synthetic */ List e(CharSequence charSequence, char[] cArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return ujy.c(charSequence, cArr, z, i);
    }

    public static final List<String> c(CharSequence charSequence, char[] cArr, boolean z, int i) {
        uhy.e((Object) charSequence, "");
        uhy.e((Object) cArr, "");
        if (cArr.length == 1) {
            return c(charSequence, String.valueOf(cArr[0]), z, i);
        }
        Iterable b = ujh.b(c(charSequence, cArr, 0, z, i, 2, null));
        ArrayList arrayList = new ArrayList(ufe.d(b, 10));
        Iterator it = b.iterator();
        while (it.hasNext()) {
            arrayList.add(ujy.c(charSequence, (uiv) it.next()));
        }
        return arrayList;
    }

    private static final List<String> c(CharSequence charSequence, String str, boolean z, int i) {
        ujy.a(i);
        int i2 = 0;
        int e = ujy.e(charSequence, str, 0, z);
        if (e == -1 || i == 1) {
            return ufe.d(charSequence.toString());
        }
        boolean z2 = i > 0;
        ArrayList arrayList = new ArrayList(z2 ? uja.b(i, 10) : 10);
        do {
            arrayList.add(charSequence.subSequence(i2, e).toString());
            i2 = str.length() + e;
            if (z2 && arrayList.size() == i - 1) {
                break;
            }
            e = ujy.e(charSequence, str, i2, z);
        } while (e != -1);
        arrayList.add(charSequence.subSequence(i2, charSequence.length()).toString());
        return arrayList;
    }

    public static final Sequence<String> d(CharSequence charSequence) {
        uhy.e((Object) charSequence, "");
        return ujy.e(charSequence, new String[]{"\r\n", "\n", "\r"}, false, 0, 6, (Object) null);
    }

    public static final List<String> c(CharSequence charSequence) {
        uhy.e((Object) charSequence, "");
        return ujh.d(ujy.d(charSequence));
    }

    public static final CharSequence g(CharSequence charSequence) {
        uhy.e((Object) charSequence, "");
        int length = charSequence.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean d2 = ujm.d(charSequence.charAt(!z ? i : length));
            if (z) {
                if (!d2) {
                    break;
                }
                length--;
            } else if (d2) {
                i++;
            } else {
                z = true;
            }
        }
        return charSequence.subSequence(i, length + 1);
    }
}
