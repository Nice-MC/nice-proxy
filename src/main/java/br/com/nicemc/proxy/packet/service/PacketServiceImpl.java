package br.com.nicemc.proxy.packet.service;

import br.com.nicemc.proxy.packet.Packet;
import lombok.NonNull;
import org.apache.commons.lang3.Validate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PacketServiceImpl implements PacketService {

    private Map<Integer, Class<? extends Packet>> packets;
    private Map<Class<? extends Packet>, Integer> packetsClazz;

    public PacketServiceImpl() {
        packets = new HashMap<>();
        packetsClazz = new HashMap<>();
    }

    @Override
    public void register(@NonNull Integer id, @NonNull Class<? extends Packet> packet) {
        Validate.notNull(id, "id can't be null.");
        Validate.notNull(packet, "packet can't be null.");
        this.packets.put(id, packet);
        this.packetsClazz.put(packet, id);
    }

    @Override
    public Optional<Class<? extends Packet>> get(@NonNull Integer id) {
        Validate.notNull(id, "id can't be null.");
        return Optional.ofNullable(packets.get(id));
    }

    @Override
    public Optional<Integer> get(@NonNull Class<? extends Packet> packetClazz) {
        Validate.notNull(packetClazz, "packetClazz can't be null.");
        return Optional.ofNullable(packetsClazz.get(packetClazz));
    }
}
