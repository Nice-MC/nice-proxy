package br.com.nicemc.proxy.account.enums;

import java.util.Optional;

public enum GroupType {

     DEFAULT,
     VIP,
     MVP,
     MVP_PLUS,
     HELPER,
     YOUTUBER,
     MODERATOR,
     ADMINISTRATOR,
     MANAGER,
     CMO,
     CTO,
     CEO,
     OWNER;

     public static Optional<GroupType> byId(int id) {
          for (GroupType groupType : values()) {
               if(groupType.ordinal() == id) {
                    return Optional.of(groupType);
               }
          }
          return Optional.empty();
     }

}
