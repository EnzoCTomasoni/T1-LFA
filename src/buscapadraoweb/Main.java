/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package buscapadraoweb;

import buscaweb.CapturaRecursosWeb;
import java.util.ArrayList;

/** 
 *
 * @author Santiago
 */
public class Main {

    // busca char em vetor e retorna indice
    public static int get_char_ref (char[] vet, char ref ){
        for (int i=0; i<vet.length; i++ ){
            if (vet[i] == ref){
                return i;
            }
        }
        return -1;
    }

    // busca string em vetor e retorna indice
    public static int get_string_ref (String[] vet, String ref ){
        for (int i=0; i<vet.length; i++ ){
            if (vet[i].equals(ref)){
                return i;
            }
        }
        return -1;
    }
    
    

    //retorna o próximo estado, dado o estado atual e o símbolo lido
    public static int proximo_estado(char[] alfabeto, int[][] matriz,int estado_atual,char simbolo){
        int simbol_indice = get_char_ref(alfabeto, simbolo);
        if (simbol_indice != -1){
            return matriz[estado_atual][simbol_indice];
        }else{
            return -1;
        }
    }

    /*
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // instancia e usa objeto que captura código-fonte de páginas Web
        CapturaRecursosWeb crw = new CapturaRecursosWeb();
        crw.getListaRecursos().add("https://www.youtube.com/");
        ArrayList<String> listaCodigos = crw.carregarRecursos();

        String codigoHTML = listaCodigos.get(0);

        //mapa do alfabeto
        char[] alfabeto = new char[77];

        alfabeto[0] = 'a';
        alfabeto[1] = 'b';
        alfabeto[2] = 'c';
        alfabeto[3] = 'd';
        alfabeto[4] = 'e';
        alfabeto[5] = 'f';
        alfabeto[6] = 'g';
        alfabeto[7] = 'h';
        alfabeto[8] = 'i';
        alfabeto[9] = 'j';
        alfabeto[10] = 'k';
        alfabeto[11] = 'l';
        alfabeto[12] = 'm';
        alfabeto[13] = 'n';
        alfabeto[14] = 'o';
        alfabeto[15] = 'p';
        alfabeto[16] = 'q';
        alfabeto[17] = 'r';
        alfabeto[18] = 's';
        alfabeto[19] = 't';
        alfabeto[20] = 'u';
        alfabeto[21] = 'v';
        alfabeto[22] = 'w';
        alfabeto[23] = 'x';
        alfabeto[24] = 'y';
        alfabeto[25] = 'z';

        alfabeto[26] = 'A';
        alfabeto[27] = 'B';
        alfabeto[28] = 'C';
        alfabeto[29] = 'D';
        alfabeto[30] = 'E';
        alfabeto[31] = 'F';
        alfabeto[32] = 'G';
        alfabeto[33] = 'H';
        alfabeto[34] = 'I';
        alfabeto[35] = 'J';
        alfabeto[36] = 'K';
        alfabeto[37] = 'L';
        alfabeto[38] = 'M';
        alfabeto[39] = 'N';
        alfabeto[40] = 'O';
        alfabeto[41] = 'P';
        alfabeto[42] = 'Q';
        alfabeto[43] = 'R';
        alfabeto[44] = 'S';
        alfabeto[45] = 'T';
        alfabeto[46] = 'U';
        alfabeto[47] = 'V';
        alfabeto[48] = 'W';
        alfabeto[49] = 'X';
        alfabeto[50] = 'Y';
        alfabeto[51] = 'Z';

        alfabeto[52] = '0';
        alfabeto[53] = '1';
        alfabeto[54] = '2';
        alfabeto[55] = '3';
        alfabeto[56] = '4';
        alfabeto[57] = '5';
        alfabeto[58] = '6';
        alfabeto[59] = '7';
        alfabeto[60] = '8';
        alfabeto[61] = '9';

        alfabeto[62] = '-';
        alfabeto[63] = '_';
        alfabeto[64] = '.';
        alfabeto[65] = '!';
        alfabeto[66] = '~';
        alfabeto[67] = '#';
        alfabeto[68] = '$';
        alfabeto[69] = '\'';
        alfabeto[70] = '(';
        alfabeto[71] = ')';
        alfabeto[72] = '*';
        alfabeto[73] = '+';
        alfabeto[74] = ',';
        alfabeto[75] = ':';
        alfabeto[76] = '/';


        //mapa de estados
        String[] estados = new String[13];
        estados[0] = "q0";  // estado inicial
        estados[1] = "q1";  // q0 + 'h'
        estados[2] = "q2";  // q1 + 't'
        estados[3] = "q3";  // q2 + 't'
        estados[4] = "q4";  // q3 + 'p'
        estados[5] = "q5";  // q4 + 's'
        estados[6] = "q6";  // q4 + ':' ou q5 + ':'
        estados[7] = "q7";  // q6 + '/'
        estados[8] = "q8";  // q7 + '/'
        estados[9] = "q9";  // q1 + 'w' ou q8 + 'w'
        estados[10] = "q10";  // q9 + 'w'
        estados[11] = "q11";  // q10 + 'w'
        estados[12] = "q12";  // q11 + '.' ou loop de caracteres da URL (estado final)
        
        String estado_inicial = "q0";
        
        //estados finais
        String[] estados_finais = new String[1];
        estados_finais[0] = "q12";

        //tabela de transição da AFD para reconhecimento de URLs
        int[][] matriz = new int[13][77];

        //transições de q0
        //inicia em https ou http
        matriz[get_string_ref(estados, "q0")][get_char_ref(alfabeto, 'h')] = get_string_ref(estados, "q1");
        matriz[get_string_ref(estados, "q0")][get_char_ref(alfabeto, 'H')] = get_string_ref(estados, "q1");
        //inicia em www
        matriz[get_string_ref(estados, "q0")][get_char_ref(alfabeto, 'w')] = get_string_ref(estados, "q8");
        matriz[get_string_ref(estados, "q0")][get_char_ref(alfabeto, 'W')] = get_string_ref(estados, "q8");

        //transições de q1
        matriz[get_string_ref(estados, "q1")][get_char_ref(alfabeto, 't')] = get_string_ref(estados, "q2");
        matriz[get_string_ref(estados, "q1")][get_char_ref(alfabeto, 'T')] = get_string_ref(estados, "q2");

        //transições de q2
        matriz[get_string_ref(estados, "q2")][get_char_ref(alfabeto, 't')] = get_string_ref(estados, "q3");
        matriz[get_string_ref(estados, "q2")][get_char_ref(alfabeto, 'T')] = get_string_ref(estados, "q3");

        //transições de q3
        matriz[get_string_ref(estados, "q3")][get_char_ref(alfabeto, 'p')] = get_string_ref(estados, "q4");
        matriz[get_string_ref(estados, "q3")][get_char_ref(alfabeto, 'P')] = get_string_ref(estados, "q4");

        //transições de q4
        //https
        matriz[get_string_ref(estados, "q4")][get_char_ref(alfabeto, 's')] = get_string_ref(estados, "q5");
        matriz[get_string_ref(estados, "q4")][get_char_ref(alfabeto, 'S')] = get_string_ref(estados, "q5");
        //http
        matriz[get_string_ref(estados, "q4")][get_char_ref(alfabeto, ':')] = get_string_ref(estados, "q6");

        //transições de q5
        matriz[get_string_ref(estados, "q5")][get_char_ref(alfabeto, ':')] = get_string_ref(estados, "q6");
        
        //transições de q6
        matriz[get_string_ref(estados, "q6")][get_char_ref(alfabeto, '/')] = get_string_ref(estados, "q7");

        //transições de q7
        matriz[get_string_ref(estados, "q7")][get_char_ref(alfabeto, '/')] = get_string_ref(estados, "q8");
        
        //transições de q8
        matriz[get_string_ref(estados, "q8")][get_char_ref(alfabeto, 'w')] = get_string_ref(estados, "q9");
        
        //transições de q9
        matriz[get_string_ref(estados, "q9")][get_char_ref(alfabeto, 'w')] = get_string_ref(estados, "q10");
        
        //transições de q10
        matriz[get_string_ref(estados, "q10")][get_char_ref(alfabeto, 'w')] = get_string_ref(estados, "q11");
        
        //transições de q11
        matriz[get_string_ref(estados, "q11")][get_char_ref(alfabeto, '.')] = get_string_ref(estados, "q12");

        //transições de q12
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'a')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'b')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'c')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'd')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'e')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'f')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'g')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'h')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'i')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'j')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'k')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'l')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'm')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'n')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'o')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'p')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'q')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'r')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 's')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 't')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'u')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'v')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'w')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'x')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'y')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'z')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'A')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'B')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'C')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'D')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'E')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'F')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'G')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'H')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'I')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'J')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'K')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'L')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'M')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'N')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'O')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'P')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'Q')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'R')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'S')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'T')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'U')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'V')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'W')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'X')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'Y')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, 'Z')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, '0')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, '1')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, '2')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, '3')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, '4')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, '5')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, '6')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, '7')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, '8')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, '9')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, '-')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, '_')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, '.')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, '!')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, '~')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, '#')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, '$')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, '\'')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, '(')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, ')')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, '*')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, '+')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, ',')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, ':')] = get_string_ref(estados, "q12");
        matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, '/')] = get_string_ref(estados, "q12");


        
        
        int estado = get_string_ref (estados, estado_inicial);
        int estado_anterior = -1;
        ArrayList<String> palavras_reconhecidas = new ArrayList();
        
        
        String palavra = "";
        
        //varre o código fonte de um código
        for (int i=0; i<codigoHTML.length(); i++){
            
            estado_anterior = estado;
            estado = proximo_estado(alfabeto, matriz, estado, codigoHTML.charAt(i));
            //se o não há transição
            if (estado == -1) {
                //pega estado inicial
                estado = get_string_ref(estados, estado_inicial);
                // se o estado anterior foi um estado final
                if (get_string_ref(estados_finais, estados[estado_anterior]) != -1) {
                    //se a palavra não é vazia adiciona palavra reconhecida
                    if (!palavra.equals("")) {
                        palavras_reconhecidas.add(palavra);
                    }
                    // se ao analisar este caracter não houve transição
                    // teste-o novamente, considerando que o estado seja inicial
                    i--;
                }
                //zera palavra
                palavra = "";
                
            } else {
                //se houver transição válida, adiciona caracter a palavra
                palavra += codigoHTML.charAt(i);
            }
        }

        //foreach no Java para exibir todas as palavras reconhecidas
        for (String p: palavras_reconhecidas) {
            System.out.println(p);
        }
        
        
    }
    
    
    
}
