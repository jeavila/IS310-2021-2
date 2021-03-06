package parcial1.c_listas;

import java.util.ArrayList;

public class ListaSimple {
    Nodo primero;
    Nodo ultimo;
    int conteo;

    public ListaSimple(int datoInicial) {
        this.insertarPrimerNodo(datoInicial);
    }

    private void insertarPrimerNodo(int dato) {
        Nodo nuevo = new Nodo(dato);
        this.primero = this.ultimo = nuevo;
        this.conteo = 1;
    }

    public void vaciar() {
        this.primero = null;
        this.ultimo = null;
        this.conteo = 0;
    }

    public boolean estaVacia() {
        return this.primero == null && this.ultimo == null;
    }

    public void insertarPrimero(int dato) {
        /*
        1. Crear el nuevo nodo
        2. Enlazar el nuevo nodo al primer nodo
        3. Puntero PRIMERO apunta al nuevo nodo (el cual es el primero ahora)
         */
        Nodo nuevo = new Nodo(dato);
        nuevo.setSiguiente(primero);
        this.primero = nuevo;
        this.conteo++;
    }

    public void insertarUltimo(int dato) {
        Nodo nuevo = new Nodo(dato);
        this.ultimo.setSiguiente(nuevo);
        this.ultimo = nuevo;
        this.conteo++;
    }

    public int eliminarPrimero() throws Exception {
        if (this.estaVacia()) {
            throw new Exception("Lista esta vacia.");
        }
        // Almacenar el primero que esta a punto de eliminarse.
        Nodo temp = this.primero;
        this.primero = this.primero.getSiguiente();
        int valor = temp.getDato();
        temp = null;

        if (this.primero == null) {
            this.ultimo = null;
        }

        this.conteo--;
        return valor;
    }

    public int eliminarUltimo() throws Exception {
        int valor;
        if (this.estaVacia()) {
            throw new Exception("Lista esta vacia.");
        }

        Nodo previo = this.primero;
        Nodo proximo = previo.getSiguiente();

        // que pasa con LE con un 1 nodo
        if (previo == ultimo) {
            valor = previo.getDato();
            this.vaciar();
            return valor;
        }

        // Bucle para hallar al penultimo
        while(proximo != this.ultimo) {
            previo = proximo;
            proximo = proximo.getSiguiente();
        }

        valor = proximo.getDato();
        previo.setSiguiente(null);
        this.ultimo = previo;
        this.conteo--;
        proximo = null;
        return valor;
    }

    // O(1)
    public int getConteoConstante() {
        return this.conteo;
    }

    // O(n)
    public int getConteoLineal() {
        int conteo = 0;

        if (!this.estaVacia()) {
            for(Nodo temp = this.primero; temp != null; temp = temp.getSiguiente()) {
                conteo++;
            }
        }

        return conteo;
    }

    public Nodo buscar(int dato) {
        if (this.estaVacia()) {
            return null;
        }

        for(Nodo temp = this.primero; temp != null; temp = temp.getSiguiente()) {
            // Evaluar cuando sea generico
            if (temp.getDato() == dato) {
                return temp;
            }
        }

        return null;
    }

    public int[] toArray() {
        if (this.estaVacia()) {
            return null;
        }

        int[] salida = new int[this.conteo];
        int indice = 0;
        for(Nodo temp = this.primero; temp != null; temp = temp.getSiguiente()) {
            salida[indice++] = temp.getDato();
        }

        return salida;
    }
}
