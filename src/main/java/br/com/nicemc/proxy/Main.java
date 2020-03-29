package br.com.nicemc.proxy;

import br.com.nicemc.proxy.packet.PacketProcess;
import br.com.nicemc.proxy.storage.mongo.MongoHandler;
import br.com.nicemc.proxy.storage.redis.Redis;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;

@Getter
public class Main extends Plugin {

    @Getter
    private static Main instance;

    private Redis redis;
    private MongoHandler mongoHandler;

    @Override
    public void onLoad() {
        instance = this;

        PacketProcess.create(this).register();
    }

}
