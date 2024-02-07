package ru.OTS4L.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
}
