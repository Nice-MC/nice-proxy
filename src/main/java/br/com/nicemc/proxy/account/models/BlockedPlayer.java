package br.com.nicemc.proxy.account.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BlockedPlayer {

    private String target;
    private Long blockedTime;


}
