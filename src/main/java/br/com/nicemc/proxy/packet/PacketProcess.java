package br.com.nicemc.proxy.packet;

import br.com.nicemc.proxy.Main;
import br.com.nicemc.proxy.packet.listener.RedisListener;
import br.com.nicemc.proxy.packet.service.PacketService;
import br.com.nicemc.proxy.packet.service.PacketServiceImpl;
import br.com.nicemc.proxy.server.enums.ServerType;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import lombok.NonNull;
import org.apache.commons.lang3.Validate;
import redis.clients.jedis.Jedis;

import java.util.Base64;
import java.util.Optional;

public class PacketProcess {

    private static Main plugin;
    private static PacketService packetService;

    private PacketProcess(Main plugin) {
        PacketProcess.plugin = plugin;
        PacketProcess.packetService = new PacketServiceImpl();

        RedisListener listener = new RedisListener();
        try (Jedis jedis = plugin.getRedis().getPool().getResource()) {
            jedis.subscribe(listener, "All", "Proxy", "Network");
        }
    }

    public static PacketProcess create(Main plugin) {
        return new PacketProcess(plugin);
    }

    public void register() {
        //Register packets
    }

    public static void sendPacketToServer(ServerType serverType, Packet packet) {
        getId(packet.getClass()).ifPresent(id -> {
            ByteArrayDataOutput byteArrayDataOutput = ByteStreams.newDataOutput();
            byteArrayDataOutput.writeInt(id);
            packet.write(byteArrayDataOutput);
            try (Jedis jedis = plugin.getRedis().getPool().getResource()) {
                jedis.publish(serverType.name().getBytes(), Base64.getEncoder().encode(byteArrayDataOutput.toByteArray()));
            }
        });
    }

    public static void sendPacketToAll(Packet packet) {
        getId(packet.getClass()).ifPresent(id -> {
            ByteArrayDataOutput byteArrayDataOutput = ByteStreams.newDataOutput();
            byteArrayDataOutput.writeInt(id);
            packet.write(byteArrayDataOutput);
            try (Jedis jedis = plugin.getRedis().getPool().getResource()) {
                jedis.publish("All".getBytes(), Base64.getEncoder().encode(byteArrayDataOutput.toByteArray()));
            }
        });
    }

    public static Optional<Class<? extends Packet>> find(@NonNull Integer id) {
        Validate.notNull(id, "id can't be null.");
        return packetService.get(id);
    }

    private static Optional<Integer> getId(Class<? extends Packet> clazz) {
        return packetService.get(clazz);
    }

}
