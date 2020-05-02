package id.ac.binus.programming.kost.user.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EnumStatusKamar {
    KOSONG, DISEWA;

    @JsonCreator
    public static EnumStatusKamar create(String value) {
        if (value == null)
            return null;
        for (EnumStatusKamar v : values()) {
            if (value.toUpperCase().equals(v.name().toUpperCase()))
                return v;
        }
        return null;
    }
}
