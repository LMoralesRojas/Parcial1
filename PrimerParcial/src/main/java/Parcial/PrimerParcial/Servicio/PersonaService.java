package Parcial.PrimerParcial.Servicio;

import Parcial.PrimerParcial.Recursos.Clases.Persona;
import Parcial.PrimerParcial.Repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    private long contadorMutanteADN = 0;
    private long contadorPersonaNormalADN = 0;

    public boolean esMutante(String[] adn) {

        String adnString = Arrays.toString(adn);

        boolean siEsMutante = confirmacionMutante(adn);

        Persona adnMutante = new Persona();
        adnMutante.setAdn(adnString);
        adnMutante.setEsMutante(siEsMutante);
        personaRepository.save(adnMutante);

        if (siEsMutante) {
            contadorMutanteADN++;
        } else {
            contadorPersonaNormalADN++;
        }

        return siEsMutante;
    }

    public Map<String, Object> getEstadisticas() {
        double ratio = contadorPersonaNormalADN > 0 ? (double) contadorMutanteADN / contadorPersonaNormalADN : 0.0;
        Map<String, Object> stats = new HashMap<>();
        stats.put("contar_adn_mutante", contadorMutanteADN);
        stats.put("contar_adn_humano", contadorPersonaNormalADN);
        stats.put("ratio", ratio);
        return stats;
    }

    public boolean confirmacionMutante(String[] dna) {
        if (dna == null || dna.length == 0) {
            throw new IllegalArgumentException("Este valor no puede ser nulo.");
        }

        int n = dna.length;
        validarFormatoDna(dna, n);
        int secuenciasEncontradas = contarSecuencias(dna, n, 0, 0, 0);

        return secuenciasEncontradas >= 2;
    }

    private void validarFormatoDna(String[] dna, int n) {
        validarFila(dna, n, 0);
    }

    private void validarFila(String[] dna, int n, int index) {
        if (index >= n) return;

        String row = dna[index];
        if (row.length() != n) {
            throw new IllegalArgumentException("Todas las filas deben tener la misma longitud.");
        }
        if (!row.matches("[ATCG]+")) {
            throw new IllegalArgumentException("El ADN contiene caracteres no válidos. Solo se permiten A, T, C, G.");
        }

        validarFila(dna, n, index + 1);
    }

    private int contarSecuencias(String[] dna, int n, int row, int col, int secuenciasEncontradas) {
        if (row >= n) return secuenciasEncontradas;
        if (col <= n - 4 && checkSequence(dna, row, col, 0, 1)) {
            secuenciasEncontradas++;
        }
        if (row <= n - 4 && checkSequence(dna, row, col, 1, 0)) {
            secuenciasEncontradas++;
        }
        if (row <= n - 4 && col <= n - 4 && checkSequence(dna, row, col, 1, 1)) {
            secuenciasEncontradas++;
        }
        if (row <= n - 4 && col >= 3 && checkSequence(dna, row, col, 1, -1)) {
            secuenciasEncontradas++;
        }

        if (secuenciasEncontradas >= 2) return secuenciasEncontradas;
        if (col + 1 < n) {
            return contarSecuencias(dna, n, row, col + 1, secuenciasEncontradas);
        } else {
            return contarSecuencias(dna, n, row + 1, 0, secuenciasEncontradas);
        }
    }

    private boolean checkSequence(String[] dna, int row, int col, int rowDir, int colDir) {
        return checkRecursively(dna, row, col, rowDir, colDir, dna[row].charAt(col), 1);
    }

    private boolean checkRecursively(String[] dna, int row, int col, int rowDir, int colDir, char target, int count) {
        if (count == 4) return true;  // Encontró una secuencia de 4 iguales
        if (row + rowDir >= dna.length || col + colDir >= dna.length || row + rowDir < 0 || col + colDir < 0) {
            return false;
        }
        if (dna[row + rowDir].charAt(col + colDir) != target) {
            return false;
        }

        return checkRecursively(dna, row + rowDir, col + colDir, rowDir, colDir, target, count + 1);
    }

    public boolean delete(Long id) throws Exception{
        try{
            if (personaRepository.existsById(id)){
                personaRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public boolean deleteAll() throws Exception{
        try{
            personaRepository.deleteAll();
            return true;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
