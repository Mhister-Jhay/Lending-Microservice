package org.jhay.domain.enums;

public enum EmploymentStatus {
    UNEMPLOYED("Unemployed"),
    SELF_EMPLOYED("Self Employed"),
    EMPLOYED("Employed");

    private final String displayName;
    EmploymentStatus(String displayName) {
        this.displayName = displayName;
    }
    public static EmploymentStatus fromDisplayName(String displayName) {
        for (EmploymentStatus status : EmploymentStatus.values()) {
            if (status.displayName.equalsIgnoreCase(displayName)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No status found with name " + displayName);
    }
}
