/**
 * Rappresentazione della classe Film
 * @author Greta Maria Brugnatti
 */

public class Film {
    private final String titolo;
    private final int durata;
    private final String sala;
    private final int maxPostiDisponibili;
    private int postiDisponibili;



    /**
     * Costruttore dell'oggetto Film
     * @param t Titolo del film
     * @param d Durata in minuti del film
     * @param s Sala del film
     * @param p Posti disponibili nella sala
     */
    public Film (String t, int d, String s, int p) {
        titolo = t;
        sala = s;

        if(p < 0 || d < 0){
            throw new ArithmeticException("I posti disponibili o la durata del film non posso essere numeri negativi");
        } else {
            maxPostiDisponibili = p;
            postiDisponibili = maxPostiDisponibili;
            durata = d;
        }
    }

    public String getTitolo(){
        return titolo;
    }

    public int getDurata (){
        return durata;
    }

    public String getSala (){
        return sala;
    }

    public int getPostiDisponibili (){
        return postiDisponibili;
    }

    public int getMaxPostiDisponibili (){
        return maxPostiDisponibili;
    }

    /**
     * @param p Nuovi posti disponibili nella sala
     */
    public void setPostiDisponibili (int p){
        postiDisponibili = p;
    }

}