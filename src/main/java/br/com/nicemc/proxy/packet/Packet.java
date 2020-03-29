package br.com.nicemc.proxy.packet;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

public abstract class Packet {

    public abstract void read(ByteArrayDataInput in);

    public abstract void write(ByteArrayDataOutput out);

    public abstract void process();

}
