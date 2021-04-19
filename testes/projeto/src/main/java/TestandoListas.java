import java.util.ArrayList;
import java.util.Collections;

public class Teste {
    public static void main(String[] args) {

        String aula1 = "Modelando a classe Aula";
        String aula2 = "Conhecendo mais de listas";
        String aula3 = "Trabalhando com Cursos e Sets";

        ArrayList<String> aulas = new ArrayList<>();
        //Adicionando elementos em uma lista
        aulas.add(aula1);
        aulas.add(aula2);
        aulas.add(aula3);

        System.out.println(aulas);
        System.out.println(aulas.size());

        //Removendo elementos
        aulas.remove(1);
        System.out.println(aulas);
        //Percorrendo uma lista
        for (String aula : aulas) {
            System.out.println("aula: " + aula);

        }
        String primeiraAula = aulas.get(0);
        System.out.println(primeiraAula);
        //Percorrendo uma lista
        for (int i = 0; i < aulas.size(); i++) {
            System.out.println(aulas.get(i));
        }
        //Percorrendo uma lista
        aulas.forEach(aula -> {
            System.out.println("Percorrendo:");
            System.out.println("Aula " + aula);
        });
        //Ordenando a lista
        aulas.add("Aumetando nosso conhecimento");
        Collections.sort(aulas);
        System.out.println("Depois de ordenado:");
        System.out.println(aulas);
    }
}
