package br.com.nicemc.proxy.server;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ServerType {

    RANKUP_DARK(ServerStaff.SURVIVAL),
    LOBBY(ServerStaff.NETWORK);

    private ServerStaff serverStaff;

    public String getName() {
        return name().replace("_", " ");
    }

}
