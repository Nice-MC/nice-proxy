package br.com.nicemc.proxy.account.enums;

public enum Medal {

    NULL;

    private String getPermission() {
        return "medal." + name().toLowerCase();
    }


}
