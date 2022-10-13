package com.zgame.rpc;

import io.netty.buffer.ByteBuf;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;

/**
 * @projectName: QGame
 * @package: qgame.rpc
 * @className: Field
 * @author: DaYon
 * @description: 自定义的属性打包
 * @date: 2022/10/11 18:58
 * @version: 1.0
 */
public class Field {
    /**
     * @param buf: 外部的缓冲区
     * @param b:   要写入的数据
     * @return void
     * @author DaYon
     * @description 向缓冲区中写入一个字节数据
     * @date 2022/10/11 19:03
     */
    public void packByte(ByteBuf buf, byte b) {
        buf.writeByte(b);
    }

    /**
     * @param buf: 数据缓冲区
     * @return byte
     * @author DaYon
     * @description 从数据缓冲区中读取一个字节
     * @date 2022/10/11 19:09
     */
    public byte unpackByte(ByteBuf buf) {
        return buf.readByte();
    }

    /**
     * @param buf: 外部的缓冲区
     * @param arr: 要写入的数据
     * @return void
     * @author DaYon
     * @description 向缓冲区中写入一个字节数组
     * @date 2022/10/11 19:08
     */
    public void packByteArray(ByteBuf buf, byte[] arr) {
        if (arr == null) {
            /**
             * 字节数组为空则只写入一个数组长度0
             */
            buf.writeIntLE(0);
            return;
        }
        buf.writeIntLE(arr.length);
        for (byte b : arr) {
            packByte(buf, b);
        }
    }

    /**
     * @param buf:
     * @return byte[]
     * @author DaYon
     * @description 从缓冲区中读取一个数组
     * @date 2022/10/11 19:10
     */
    public byte[] unpackByteArray(ByteBuf buf) {
        int length = buf.readIntLE();
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = unpackByte(buf);
        }
        return bytes;
    }

    public void packShort(ByteBuf buf, short s) {
        buf.writeShortLE(s);
    }

    public short unpackShort(ByteBuf buf) {
        return buf.readShortLE();
    }

    public void packShortArray(ByteBuf buf, short[] arr) {
        if (arr == null) {
            buf.writeIntLE(0);
            return;
        }
        buf.writeIntLE(arr.length);
        for (short b : arr) {
            packShort(buf, b);

        }
    }

    public short[] unpackShortArray(ByteBuf buf) {
        int length = buf.readIntLE();
        short[] arr = new short[length];
        for (int i = 0; i < length; i++) {
            arr[i] = unpackShort(buf);
        }
        return arr;
    }

    public void packInt(ByteBuf buf, int i) {
        buf.writeIntLE(i);
    }

    public int unpackInt(ByteBuf buf) {
        return buf.readIntLE();
    }

    public void packIntArray(ByteBuf buf, int[] arr) {
        if (arr == null) {
            buf.writeIntLE(0);
            return;
        }
        buf.writeIntLE(arr.length);
        for (int b : arr) {
            packInt(buf, b);

        }
    }

    public int[] unpackIntArray(ByteBuf buf) {
        int length = buf.readIntLE();
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = unpackInt(buf);
        }
        return arr;
    }

    public void packLong(ByteBuf buf, long l) {
        buf.writeLongLE(l);
    }

    public long unpackLong(ByteBuf buf) {
        return buf.readLongLE();
    }

    public void packLongArray(ByteBuf buf, long[] arr) {
        if (arr == null) {
            buf.writeIntLE(0);
            return;
        }
        buf.writeIntLE(arr.length);
        for (long l : arr) {
            packLong(buf, l);
        }
    }

    public long[] unpackLongArray(ByteBuf buf) {
        int length = buf.readIntLE();
        long[] arr = new long[length];
        for (int i = 0; i < length; i++) {
            arr[i] = unpackLong(buf);
        }
        return arr;
    }

    public void packFloat(ByteBuf buf, float f) {
        buf.writeFloatLE(f);
    }

    public float unpackFloat(ByteBuf buf) {
        return buf.readFloatLE();
    }

    public void packFloatArray(ByteBuf buf, float[] arr) {
        if (arr == null) {
            buf.writeIntLE(0);
            return;
        }
        buf.writeIntLE(arr.length);
        for (float f : arr) {
            packFloat(buf, f);

        }
    }

    public float[] unpackFloatArray(ByteBuf buf) {
        int length = buf.readIntLE();
        float[] arr = new float[length];
        for (int i = 0; i < length; i++) {
            arr[i] = unpackFloat(buf);
        }
        return arr;
    }

    public void packDouble(ByteBuf buf, double d) {
        buf.writeDoubleLE(d);
    }

    public double unpackDouble(ByteBuf buf) {
        return buf.readDoubleLE();
    }

    public void packDoubleArray(ByteBuf buf, double[] arr) {
        if (arr == null) {
            buf.writeIntLE(0);
            return;
        }
        buf.writeIntLE(arr.length);
        for (double d : arr) {
            packDouble(buf, d);

        }
    }

    public double[] unpackDoubleArray(ByteBuf buf) {
        int length = buf.readIntLE();
        double[] arr = new double[length];
        for (int i = 0; i < length; i++) {
            arr[i] = unpackDouble(buf);
        }
        return arr;
    }

    public void packString(ByteBuf buf, String s) {
        if (s == null) {
            buf.writeIntLE(0);
            return;
        }
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        buf.writeIntLE(bytes.length);
        buf.writeBytes(bytes);
    }

    public String unpackString(ByteBuf buf) {
        int length = buf.readIntLE();
        byte[] bytes = new byte[length];
        int index = buf.readerIndex();
        String s = buf.toString(index, length, StandardCharsets.UTF_8);
        buf.readerIndex(index + length);
        return s;
    }

    public void packStringArray(ByteBuf buf, String[] arr) {
        if (arr == null) {
            buf.writeIntLE(0);
            return;
        }
        buf.writeIntLE(arr.length);
        for (String s : arr) {
            packString(buf, s);
        }
    }

    public String[] unpackStringArray(ByteBuf buf) {
        int length = buf.readIntLE();
        String[] arr = new String[length];
        for (int i = 0; i < length; i++) {
            arr[i] = unpackString(buf);
        }
        return arr;
    }

    public void packClass(ByteBuf buf, Field obj) {
        obj.pack(buf);
    }

    public Field unpackClass(ByteBuf buf, Class<?> cls) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Field obj = (Field) cls.getDeclaredConstructor().newInstance();
        obj.unpack(buf);
        return obj;
    }

    public void packClassArray(ByteBuf buf, Field[] arr) {
        if (arr == null) {
            buf.writeIntLE(0);
            return;
        }
        buf.writeIntLE(arr.length);
        for (Field field : arr) {
            packClass(buf, field);
        }
    }

    public Object[] unpackClassArray(ByteBuf buf, Class<?> cls) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        int length = buf.readIntLE();
        // Field[] fields = new Field[length];
        // Object[] arr = Array.newInstance(cls, length);
        Object[] arr = (Object[]) Array.newInstance(cls, length);
        for (int i = 0; i < length; i++) {
            arr[i] = unpackClass(buf, cls);
        }
        return arr;
    }

    public void pack(ByteBuf buf) {

    }

    public void unpack(ByteBuf buf) {

    }


}
