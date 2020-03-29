package br.com.nicemc.proxy.packet.listener;

import br.com.nicemc.proxy.Main;
import br.com.nicemc.proxy.packet.Packet;
import br.com.nicemc.proxy.packet.PacketProcess;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import redis.clients.jedis.JedisPubSub;

import java.util.Base64;
import java.util.Optional;

public class RedisListener extends JedisPubSub {

    @Override
    public void onMessage(String channel, String message) {
        byte[] raw = Base64.getDecoder().decode(message);
        ByteArrayDataInput byteArrayDataInput = ByteStreams.newDataInput(raw);
        int id = byteArrayDataInput.readInt();
        Optional<Class<? extends Packet>> packetClazz = PacketProcess.find(id);
        packetClazz.ifPresent(p -> {
            try {
                Packet packet = p.newInstance();
                packet.read(byteArrayDataInput);
                packet.process();
            } catch (InstantiationException | IllegalAccessException e) {
                Main.getInstance().getLogger().info("Failed to create instance of packet class. Id " + id);
                e.printStackTrace();
            }
        });
    }
}
