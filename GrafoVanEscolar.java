import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


class GrafoVanEscolar {
    private int numPontos; 
    private double[][] distancias;
    
    public GrafoVanEscolar(int numPontos) {
        this.numPontos = numPontos;
        this.distancias = new double[numPontos][numPontos];
    }


    public void adicionarDistancia(int ponto1, int ponto2, double distancia) {
        distancias[ponto1][ponto2] = distancia;
        distancias[ponto2][ponto1] = distancia;
    }

    public List<Integer> calcularRota() {

        boolean[] visitado = new boolean[numPontos];
        
        List<Integer> rota = new ArrayList<>();
        int pontoAtual = 0; 
        
        visitado[pontoAtual] = true;
        rota.add(pontoAtual);

        for (int i = 1; i < numPontos; i++) {
            int proximoAluno = -1;
            double menorDistancia = Double.MAX_VALUE;

          
            for (int j = 0; j < numPontos; j++) {
                if (!visitado[j] && distancias[pontoAtual][j] < menorDistancia) {
                    menorDistancia = distancias[pontoAtual][j];
                    proximoAluno = j;
                }
            }

         
            pontoAtual = proximoAluno;
            rota.add(pontoAtual);
            visitado[pontoAtual] = true;
        }
        
        return rota;
    }

    public void exibirRota(List<Integer> rota) {
        StringBuilder rotaCompleta = new StringBuilder("Ordem para deixar os alunos:\n");

        for (int i = 1; i < rota.size(); i++) { 
            rotaCompleta.append("Parada ").append(i).append(": Aluno ").append(rota.get(i)).append("\n");
        }

        
        JOptionPane.showMessageDialog(null, rotaCompleta.toString(), "Rota Completa", JOptionPane.INFORMATION_MESSAGE);
    }
}

