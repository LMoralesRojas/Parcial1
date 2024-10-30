package Parcial.PrimerParcial.Controller;

import Parcial.PrimerParcial.Servicio.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/mutantes")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @PostMapping()
    public ResponseEntity<?> comprobarMutante(@RequestBody Map<String, String[]> persona){
        String[] adn = persona.get("dna");
        if (adn == null || adn.length == 0) {
            return ResponseEntity.badRequest().build();
        }

        boolean esMutante = personaService.esMutante(adn);

        if (esMutante) {
            return ResponseEntity.ok().body("{\"mensaje\":\"El es un mutante.\"}");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"mensaje\":\"El DNA es humano.\"}");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMutante(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(personaService.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Porfavor intentelo nuevamente.\"}");
        }
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteMutante(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(personaService.deleteAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error.  Porfavor intentelo nuevamente.\"}");
        }
    }
}

