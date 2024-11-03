package Parcial.PrimerParcial;

import Parcial.PrimerParcial.Servicio.PersonaService;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class Testeo {

        private static PersonaService personaService;

        @BeforeAll
        public static void setUp() {
            personaService = new PersonaService();
        }

        @AfterAll
        public static void tearDown() {
            personaService = null;
        }

        @Nested
        @DisplayName("Pruebas de Confirmación de Mutante")
        class ConfirmacionMutanteTests {

            @Test
            @DisplayName("Confirmara si el ADN es mutante")
            public void testConfirmacionMutante() {
                String[] dna = {
                        "ATGGGA",
                        "CAGGGC",
                        "TTATGT",
                        "AGGAGG",
                        "CCCTTA",
                        "TATAAG"
                };
                boolean resultado = personaService.confirmacionMutante(dna);
                assertTrue(resultado);
            }

            @Test
            @DisplayName("Lanzara si hay una exepcion en el ADN si contiene caracteres no válidos")
            public void testDNAInvalido() {
                String[] dna = {
                        "ATGGGA",
                        "HAGGGC",
                        "TTATGT",
                        "AGGAGG",
                        "CCCTTA",
                        "TATAAB"
                };
                IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                    personaService.confirmacionMutante(dna);
                });
                assertEquals("El ADN contiene caracteres no válidos. Solo se permiten A, T, C, G.", thrown.getMessage());
            }

            @Test
            @DisplayName("Debería lanzar excepción si el ADN es nulo")
            public void testDNANulo() {
                String[] dna = {};
                IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                    personaService.confirmacionMutante(dna);
                });
                assertEquals("El ADN no puede ser nulo o vacío.", thrown.getMessage());
            }

            @Test
            @DisplayName("Debería lanzar excepción si hay elementos vacíos en el ADN")
            public void testDNAElementoNulos() {
                String[] dna = {"", "", "", ""};
                IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                    personaService.confirmacionMutante(dna);
                });
                assertEquals("Cada cadena de ADN debe tener una longitud de exactamente 6 caracteres.", thrown.getMessage());
            }

            @Test
            @DisplayName("Debería lanzar excepción si el ADN contiene números")
            public void testDnaArrayConNumeros() {
                String[] dna = {"012333", "203332", "110131", "033033", "222110", "101003"};
                IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                    personaService.confirmacionMutante(dna);
                });
                assertEquals("El ADN contiene caracteres no válidos. Solo se permiten A, T, C, G.", thrown.getMessage());
            }

            @Test
            @DisplayName("Debería lanzar excepción si el ADN no es cuadrado(NxN)")
            public void testDnaMxN() {
                String[] dna = {
                        "ATCGTA",
                        "GGCATT",
                        "TTAGGC",
                        "CCTGAA"
                };
                IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                    personaService.confirmacionMutante(dna);
                });
                assertEquals("Todas las filas deben tener la misma longitud. EL ADN debe ser cuadrado(NxN)", thrown.getMessage());
            }
        }

}
