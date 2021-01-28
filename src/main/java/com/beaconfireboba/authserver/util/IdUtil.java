package com.beaconfireboba.authserver.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IdUtil {
    public long generateUUID() {
        UUID uuid = UUID.randomUUID();
        long highbits = uuid.getMostSignificantBits();
        return Math.abs(highbits);
    }
}
