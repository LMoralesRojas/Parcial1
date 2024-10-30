package Parcial.PrimerParcial.Controller;

import Parcial.PrimerParcial.Servicio.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin("*")
@RequestMapping("/InfoADN")
public class InfoADN {
    @Autowired
    private PersonaService personaService;
    @GetMapping("/stats")
    public ResponseEntity<?> infoMutantes(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(personaService.getEstadisticas());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
