package fr.isep.projectweb.model.dto.request;

public class LocationAccessibilityRequest {

    private Boolean wheelchairAccessible;
    private Boolean hasElevator;
    private Boolean accessibleToilet;
    private Boolean quietEnvironment;
    private Boolean stepFreeAccess;
    private String notes;

    public Boolean getWheelchairAccessible() {
        return wheelchairAccessible;
    }

    public void setWheelchairAccessible(Boolean wheelchairAccessible) {
        this.wheelchairAccessible = wheelchairAccessible;
    }

    public Boolean getHasElevator() {
        return hasElevator;
    }

    public void setHasElevator(Boolean hasElevator) {
        this.hasElevator = hasElevator;
    }

    public Boolean getAccessibleToilet() {
        return accessibleToilet;
    }

    public void setAccessibleToilet(Boolean accessibleToilet) {
        this.accessibleToilet = accessibleToilet;
    }

    public Boolean getQuietEnvironment() {
        return quietEnvironment;
    }

    public void setQuietEnvironment(Boolean quietEnvironment) {
        this.quietEnvironment = quietEnvironment;
    }

    public Boolean getStepFreeAccess() {
        return stepFreeAccess;
    }

    public void setStepFreeAccess(Boolean stepFreeAccess) {
        this.stepFreeAccess = stepFreeAccess;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
