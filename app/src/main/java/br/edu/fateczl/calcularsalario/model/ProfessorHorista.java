/*
 *@author:Leudvan Guedes
 */
package br.edu.fateczl.calcularsalario.model;

public class ProfessorHorista extends Professor{

    private int horasAula;
    private double valorHoraAula;

    public ProfessorHorista(){
        super();
    }

    @Override
    public double calcSalario() {
        return horasAula * valorHoraAula;
    }

    public int getHorasAula() {
        return horasAula;
    }

    public void setHorasAula(int horasAula) {
        this.horasAula = horasAula;
    }

    public double getValorHoraAula() {
        return valorHoraAula;
    }

    public void setValorHoraAula(double valorHoraAula) {
        this.valorHoraAula = valorHoraAula;
    }
}
