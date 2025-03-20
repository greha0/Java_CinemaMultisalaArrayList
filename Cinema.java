import java.util.ArrayList;

public class Cinema {
    private final String nomeCinema;
    private final ArrayList<Film> sale;
    private final int nMax;

    /**
     * Costruttore dell'oggetto Cinema
     * @param nome Nome del cinema
     */
    public Cinema (String nome){
        sale = new ArrayList<>(10);
        nomeCinema = nome;
        nMax = 10;
    }

    /**
     * Costruttore dell'oggetto Cinema
     * @param nome Nome del cinema
     * @param n Numero massimo di sale
     */
    public Cinema (String nome, int n){
        nomeCinema = nome;
        nMax = n;
        sale = new ArrayList<>(nMax);
    }

    /**
     * Controlla se la sala è già occupata da qualche film
     * @param s Sala
     * @return true - Se la sala è occupata
     *         false - Se la sala è libera
     */
    public boolean isSalaOccupata(String s){
        if(sale.isEmpty()){
            return false;
        }
        for (Film film : sale) {
            if (film.getSala().equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Aggiungi un film al cinema
     * Non possono esserci più film nella stessa sala
     * @param f Film da aggiungere
     */
    public void aggiungiFilm(Film f){
        if(isSalaOccupata(f.getSala())){
            throw new IllegalArgumentException("La sala " + f.getSala() + " è già occupata");
        } else if(sale.size() > nMax) {
            throw new IndexOutOfBoundsException("Hai superato il numero di sale massime");
        }else{
            sale.add(f);
        }
    }

    /**
     * Metodo per prenotare i posti
     * @param n Numero di posti da prenotare
     * @param t Titolo del film
     * @return  0 - Se la prenotazione è riuscita
     *         -1 - Se il film non esiste o se i posti non sono disponibili
     */
    public int prenotaPosti (int n, String t){
        if(n < 0){
            throw new ArithmeticException("Il numero di posti da prenotare non può essere negativo");
        } else {
            for (Film film : sale) {
                if (film.getTitolo().equalsIgnoreCase(t)) {
                    if (film.getPostiDisponibili() >= n) {
                        film.setPostiDisponibili(film.getPostiDisponibili() - n);
                        return 0;
                    }
                }
            }
            return -1;
        }
    }

    /**
     * Restituisce il film con la durata più lunga
     * @return film con la durata più lunga
     */
    public Film filmPiuLungo(){
        Film max = sale.get(0);
        for (Film film : sale) {
            if (max.getDurata() < film.getDurata()) {
                max = film;
            }
        }
        return max;
    }

    /**
     * Restituisce la percentuale di occupazione del Cinema
     * @return Percentuale di occupazione del cinema
     */
    public double percentualeOccupazione(){
        int sommaMax = 0;
        int sommaOcc = 0;

        for (Film film : sale) {
            sommaMax += film.getMaxPostiDisponibili();
            sommaOcc += film.getMaxPostiDisponibili() - film.getPostiDisponibili();
        }
        return (double) (sommaOcc * 100) / sommaMax;
    }


    /**
     * Dato un titolo elimina dal cinema tutti i film con quel titolo
     * @param t Titolo del film
     */
    public void rimuoviFilm(String t){
        for(Film film : sale){
            if(film.getTitolo().equalsIgnoreCase(t)){
               sale.remove(film);
               return;
            }
        }
        throw new IllegalArgumentException("Il film non esiste");
    }

    public void stampaProgrammazione() {
        if (sale.isEmpty()) {
            System.out.println("Non ci sono film in programmazione al momento.");
        } else {
            System.out.println("Programmazione del cinema \"" + nomeCinema + "\":");
            for (Film film : sale) {
                System.out.println("Titolo: " + film.getTitolo());
                System.out.println("Sala: " + film.getSala());
                System.out.println("Durata: " + film.getDurata() + " minuti");
                System.out.println("Posti disponibili: " + film.getPostiDisponibili() + "/" + film.getMaxPostiDisponibili());
                System.out.println("---------------------------------------------------");
            }
        }
    }

    public static void main (String[] args){
        Cinema cinema = new Cinema ("Calvino");
        Cinema cinema1 = new Cinema("Calvino", 50);
        cinema1.aggiungiFilm(new Film ("MeowMeow", 150, "13", 10));
        cinema.aggiungiFilm(new Film("Minions", 120, "12", 10));
        cinema.aggiungiFilm(new Film ("Il signore degli anelli", 120, "14", 10));
        System.out.println(cinema.prenotaPosti(5, "Il signore degli anelli"));
        System.out.println(cinema.percentualeOccupazione());
        System.out.println(cinema.filmPiuLungo());
        cinema.stampaProgrammazione();
        cinema.rimuoviFilm("Minions");
        cinema.stampaProgrammazione();
    }

}
