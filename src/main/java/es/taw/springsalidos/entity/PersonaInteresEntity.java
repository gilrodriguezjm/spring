package es.taw.springsalidos.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "persona-interes", schema = "salidosSpring", catalog = "")
public class PersonaInteresEntity {

    @EmbeddedId
    private PersonaInteresEntityPK personaInteresEntityPK;

    @ManyToOne(optional = false)
    @JoinColumn(name = "persona_id", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    private PersonaEntity personaByPersonaId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "interes_id", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    private InteresEntity interesByInteresId;

    public PersonaInteresEntityPK getPersonaInteresEntityPK() {
        return personaInteresEntityPK;
    }

    public void setPersonaInteresEntityPK(PersonaInteresEntityPK personaInteresEntityPK) {
        this.personaInteresEntityPK = personaInteresEntityPK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonaInteresEntity that = (PersonaInteresEntity) o;
        return Objects.equals(this.getPersonaInteresEntityPK().getPersonaId(), that.getPersonaInteresEntityPK().getPersonaId())
                && Objects.equals(this.getPersonaInteresEntityPK().getInteresId(), that.personaInteresEntityPK.getInteresId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getPersonaInteresEntityPK().getPersonaId(),
                            this.getPersonaInteresEntityPK().getInteresId());
    }

    public PersonaEntity getPersonaByPersonaId() {
        return personaByPersonaId;
    }

    public void setPersonaByPersonaId(PersonaEntity personaByPersonaId) {
        this.personaByPersonaId = personaByPersonaId;
    }

    public InteresEntity getInteresByInteresId() {
        return interesByInteresId;
    }

    public void setInteresByInteresId(InteresEntity interesByInteresId) {
        this.interesByInteresId = interesByInteresId;
    }
}
