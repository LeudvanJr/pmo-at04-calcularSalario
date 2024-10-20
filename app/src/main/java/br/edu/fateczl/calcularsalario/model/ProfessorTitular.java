/*
 *@author:Leudvan Guedes
 */
package br.edu.fateczl.calcularsalario.model;

public class ProfessorTitular extends Professor{

    private int anosInstituicao;
    private double salario;

    public ProfessorTitular(){
        super();
    }

    @Override
    public double calcSalario() {
        float beneficio = (float) (Math.floor(anosInstituicao/5f)*0.05);
        return salario *= (1+beneficio);
    }

    public int getAnosInstituicao() {
        return anosInstituicao;
    }

    public void setAnosInstituicao(int anosInstituicao) {
        this.anosInstituicao = anosInstituicao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
