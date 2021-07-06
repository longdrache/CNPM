package com.hcmute.shop.Configuration;

public enum Permission  {
    USER("ROLE_USER");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
