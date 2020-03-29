package br.com.nicemc.proxy.account;

import br.com.nicemc.proxy.account.enums.Language;
import br.com.nicemc.proxy.account.enums.Medal;
import br.com.nicemc.proxy.account.models.BlockedPlayer;
import br.com.nicemc.proxy.account.models.Group;
import br.com.nicemc.proxy.account.models.Permission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NiceAccount {

    private String name;

    private int cash;

    private long onlineTime = 0L;
    private long lastLoggedIn;
    private long firstTimePlaying;

    private Set<BlockedPlayer> blockedPlayers;
    private Set<Permission> permissions;
    private Set<Group> groups;

    private Language language;
    private Medal medal;

    public void load() {
        blockedPlayers.removeIf(blockedPlayer -> blockedPlayer.getBlockedTime() < System.currentTimeMillis());
        permissions.removeIf(permission -> permission.getPermissionTime() < System.currentTimeMillis());
        groups.removeIf(groupType -> groupType.getGroupTime() < System.currentTimeMillis());
        this.lastLoggedIn = System.currentTimeMillis();
    }

    public void unload() {
        this.onlineTime += System.currentTimeMillis() - this.lastLoggedIn;
    }

}
