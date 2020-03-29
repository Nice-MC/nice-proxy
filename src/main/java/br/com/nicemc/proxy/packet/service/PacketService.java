package br.com.nicemc.proxy.packet.service;

import br.com.nicemc.proxy.packet.Packet;
import lombok.NonNull;

import java.util.Optional;

public interface PacketService {

    void register(@NonNull Integer id, @NonNull Class<? extends Packet> packet);

    Optional<Class<? extends Packet>> get(@NonNull Integer id);

    Optional<Integer> get(@NonNull Class<? extends Packet> packetClazz);

}
