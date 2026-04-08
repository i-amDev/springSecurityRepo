package com.learning.springSecurity.entity;

import java.util.Set;

public enum Role {
    ADMIN(Set.of(Permissions.PERMISSION_READ, Permissions.PERMISSION_WRITE, Permissions.PERMISSION_DELETE)),
    USER(Set.of(Permissions.PERMISSION_READ));

    private final Set<Permissions> permissions;

    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }
}
