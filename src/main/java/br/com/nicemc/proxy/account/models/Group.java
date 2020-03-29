package br.com.nicemc.proxy.account.models;

import br.com.nicemc.proxy.account.enums.GroupType;
import br.com.nicemc.proxy.server.ServerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Group {

    private ServerType serverType;
    private GroupType groupType;

    private Long groupTime;

}
