package com.example.nominapp;

public class ReciboDeNomina {
    private int numRecibo;
    private String nombre;
    private int horasNormales;
    private int horasExtra;
    private int puesto;

    public ReciboDeNomina(int numRecibo, String nombre, int horasNormales, int horasExtra, int puesto) {
        this.numRecibo = numRecibo;
        this.nombre = nombre;
        this.horasNormales = horasNormales;
        this.horasExtra = horasExtra;
        this.puesto = puesto;
    }

    public double calcularSubtotal() {
        double pagoBase = 200;
        switch (puesto) {
            case 1: pagoBase *= 1.2; break;
            case 2: pagoBase *= 1.5; break;
            case 3: pagoBase *= 2; break;
        }
        return (pagoBase * horasNormales) + (pagoBase * horasExtra * 2);
    }

    public double calcularImpuesto() {
        return calcularSubtotal() * ((double) 16 / 100);
    }

    public double calcularTotal() {
        return calcularSubtotal() - calcularImpuesto();
    }
}

