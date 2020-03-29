package br.com.nicemc.proxy.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@AllArgsConstructor
@Getter
public enum ServerType {

    RANKUP_DARK,
    LOBBY,
    NETWORK;

    public String getName() {
        return name().replace("_", " ");
    }

    public static Optional<ServerType> byId(int id) {
        ServerType type = null;
        for (ServerType types : values()) {
            if (types.ordinal() == id) {
                type = types;
                break;
            }
        }
        return Optional.ofNullable(type);
    }

}
