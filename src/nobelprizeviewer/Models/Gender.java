package nobelprizeviewer.Models;

/**
 * A Gender enumerate to assign a scoped type to genders.
 * @author Mathew Aloisio
 */
public enum Gender {
    UNKNOWN, // Incase there is any unknown gender types in their like "org".
    MALE,
    FEMALE,
    ORGANIZATION // For some reason their database stores "org" as a gender?
}
