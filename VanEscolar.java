
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VanEscolar extends JFrame implements ActionListener  {
    private JButton btnAdicionarAlunos;
    private JButton btnExibirRota;
    private JLabel displayJLabel;
    private JPanel botoes;
    private static int numAlunos;
    private static GrafoVanEscolar grafo;

    public VanEscolar(){
        Container cx = getContentPane();
        cx.setLayout(new BorderLayout());
        
        botoes = new JPanel(new FlowLayout());

        btnAdicionarAlunos = new JButton("Adicionar Alunos");
        btnExibirRota = new JButton("Exibir Rota");

        botoes.add(btnAdicionarAlunos);
        botoes.add(btnExibirRota);



        btnAdicionarAlunos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
               grafo = adicionarAlunos();
            }});

        btnExibirRota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
               exibirRota(grafo);
            }});

        ImageIcon icon = new ImageIcon("Logo.jpg");
        Image imagemOriginal = icon.getImage();
        Image imagemRedimensionada = imagemOriginal.getScaledInstance(500, 500, Image.SCALE_SMOOTH);


        displayJLabel = new JLabel();
        displayJLabel.setIcon(new ImageIcon(imagemRedimensionada));

        cx.add(botoes, BorderLayout.SOUTH);
        cx.add(displayJLabel, BorderLayout.NORTH);
        
        setTitle("Van Escolar");
        setSize(500,500);
        setVisible(true);
        setLocationRelativeTo(null);   
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    private GrafoVanEscolar adicionarAlunos(){
        grafo = null;

        numAlunos=0;

        while(numAlunos<=1){
            numAlunos = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o número de alunos: ", "", JOptionPane.QUESTION_MESSAGE));
            if(numAlunos<=0){
                JOptionPane.showMessageDialog(null, "Digite um número válido");
            }
        }
        
        int numPontos = numAlunos + 1;
        GrafoVanEscolar grafo = new GrafoVanEscolar(numPontos);

        
        JOptionPane.showMessageDialog(null, "Digite as distâncias, em km, entre a escola e cada aluno...");
        for (int i = 1; i <= numAlunos; i++) {
            double distancia = Double.parseDouble(JOptionPane.showInputDialog(null,"Distância, em km, da escola ao aluno " + i + ": ", "", JOptionPane.QUESTION_MESSAGE));
            grafo.adicionarDistancia(0, i, distancia);
        }

        JOptionPane.showMessageDialog(null, "Digite as distâncias, em km, entre os alunos...");
        for (int i = 1; i <= numAlunos; i++) {
            for (int j = i + 1; j <= numAlunos; j++) {
                double distancia = Double.parseDouble(JOptionPane.showInputDialog(null,"Distância, em km, entre aluno " + i + " e aluno " + j + ": ", "", JOptionPane.QUESTION_MESSAGE));
                grafo.adicionarDistancia(i, j, distancia);
            }
        }       

        JOptionPane.showMessageDialog(null, "Rota calculada");
        

        return grafo;
    }

    private void exibirRota(GrafoVanEscolar g){
         List<Integer> rota = g.calcularRota();
         g.exibirRota(rota);
 
    }

    public static void main(String[] args) {

       new VanEscolar();
        
    }
}
