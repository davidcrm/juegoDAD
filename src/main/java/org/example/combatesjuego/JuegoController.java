package org.example.combatesjuego;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import javax.swing.*;

public class JuegoController {

    @FXML
    private Label numeroEtiqueta1, numeroEtiqueta2,destreza1, destreza2, fuerza1,fuerza2,suerte1,suerte2;
    @FXML
    private Label comenzarLabel, siguienteLabel, resolverLabel,nuevoLabel, modalidadLabel;

    @FXML
    private ImageView j1v1, j1v2,j1v3,j2v1,j2v2,j2v3;

    private int partidaPor, valorJugador1, valorJugador2;

    private String modalidad;

    private int vidasJugador1;

    private int vidasJugador2;


    public void comenzarJuego() {
        if (vidasJugador1 == 0 && vidasJugador2 == 0) {
            vidasJugador1 = 3;
            vidasJugador2 = 3;
        }

        // Asegura que todas las vidas sean visibles
        mostrarVidas(true);

        // Reiniciar puntajes
        reiniciarPuntajes();

        // Inicializar modalidad
        elegirModalidad();
    }

    private void mostrarVidas(boolean mostrar) {
        j1v1.setVisible(mostrar);
        j1v2.setVisible(mostrar);
        j1v3.setVisible(mostrar);
        j2v1.setVisible(mostrar);
        j2v2.setVisible(mostrar);
        j2v3.setVisible(mostrar);
    }

    private void reiniciarPuntajes() {
        fuerza1.setText("0");
        fuerza2.setText("0");
        destreza1.setText("0");
        destreza2.setText("0");
        suerte1.setText("0");
        suerte2.setText("0");
    }

    private void elegirModalidad() {
        partidaPor = (int) (Math.random() * 3) + 1; // Elige aleatoriamente una modalidad
        switch (partidaPor) {
            case 1:
                modalidad = "Destreza";
                break;
            case 2:
                modalidad = "Fuerza";
                break;
            case 3:
                modalidad = "Suerte";
                break;
        }
        modalidadLabel.setText(modalidad); // Mostrar modalidad
    }

    public void resolver() {
        // Genera valores aleatorios entre 1 y 100 para cada jugador
        valorJugador1 = (int) (Math.random() * 100) + 1;
        valorJugador2 = (int) (Math.random() * 100) + 1;

        // Mostrar valores generados para depuración (opcional)
        System.out.println("Jugador 1: " + valorJugador1 + " | Jugador 2: " + valorJugador2);

        // Sumar el valor aleatorio a la característica elegida
        sumarValor(modalidad);

        // Verificar si uno de los jugadores ha perdido
        verificarFinJuego();
    }

    private void sumarValor(String modalidad) {
        switch (modalidad) {
            case "Fuerza":
                calcularResultado(fuerza1, fuerza2, valorJugador1, valorJugador2, 1, 2);
                break;
            case "Destreza":
                calcularResultado(destreza1, destreza2, valorJugador1, valorJugador2, 1, 2);
                break;
            case "Suerte":
                calcularResultado(suerte1, suerte2, valorJugador1, valorJugador2, 1, 2);
                break;
        }
    }

    private void calcularResultado(Label labelJugador1, Label labelJugador2, int valor1, int valor2, int jugador1, int jugador2) {
        if (valor1 > valor2) {
            labelJugador1.setText(String.valueOf(Integer.parseInt(labelJugador1.getText()) + valor1)); // Suma el valor al puntaje del jugador 1
            perderVida(jugador2); // Jugador 2 pierde vida
        } else if (valor2 > valor1) {
            labelJugador2.setText(String.valueOf(Integer.parseInt(labelJugador2.getText()) + valor2)); // Suma el valor al puntaje del jugador 2
            perderVida(jugador1); // Jugador 1 pierde vida
        }
    }

    private void perderVida(int jugador) {
        if (jugador == 1) {
            if (vidasJugador1 > 0) {
                vidasJugador1--;
                ocultarVidaJugador(1);
            }
        } else if (jugador == 2) {
            if (vidasJugador2 > 0) {
                vidasJugador2--;
                ocultarVidaJugador(2);
            }
        }
    }

    private void ocultarVidaJugador(int jugador) {
        if (jugador == 1) {
            if (vidasJugador1 == 2) {
                j1v3.setVisible(false);
            } else if (vidasJugador1 == 1) {
                j1v2.setVisible(false);
            } else if (vidasJugador1 == 0) {
                j1v1.setVisible(false);
            }
        } else if (jugador == 2) {
            if (vidasJugador2 == 2) {
                j2v3.setVisible(false);
            } else if (vidasJugador2 == 1) {
                j2v2.setVisible(false);
            } else if (vidasJugador2 == 0) {
                j2v1.setVisible(false);
            }
        }
    }

    private void verificarFinJuego() {
        if (vidasJugador1 <= 0 || vidasJugador2 <= 0) {
            finalizarJuego(); // Finalizar el juego
        }
    }

    private void finalizarJuego() {
        System.exit(0);
    }
}
