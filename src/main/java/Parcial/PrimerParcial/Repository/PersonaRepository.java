package Parcial.PrimerParcial.Repository;

import Parcial.PrimerParcial.Recursos.Clases.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

}
