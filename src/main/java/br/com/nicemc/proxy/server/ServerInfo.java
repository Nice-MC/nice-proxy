package br.com.nicemc.proxy.server;

import br.com.nicemc.proxy.account.models.Group;
import br.com.nicemc.proxy.server.enums.ServerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ServerInfo {

    private ServerType serverType;
    private Group roleToEnter;

    private int onlinePlayers;
    private int slots;

    private long currentTime;

    public boolean isValid() {
        return currentTime + 10000L > System.currentTimeMillis();
    }

}
