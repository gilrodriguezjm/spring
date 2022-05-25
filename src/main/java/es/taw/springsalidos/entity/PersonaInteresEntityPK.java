package es.taw.springsalidos.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PersonaInteresEntityPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "persona_id", nullable = false)
    private Integer personaId;
    @Basic(optional = false)
    @Column(name = "interes_id", nullable = false)
    private Integer interesId;

    public Integer getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Integer personaId) {
        this.personaId = personaId;
    }

    public Integer getInteresId() {
        return interesId;
    }

    public void setInteresId(Integer interesId) {
        this.interesId = interesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonaInteresEntityPK that = (PersonaInteresEntityPK) o;
        return Objects.equals(personaId, that.personaId) && Objects.equals(interesId, that.interesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personaId, interesId);
    }
}
