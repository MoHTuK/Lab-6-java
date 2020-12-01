package sample;

public class Responce {
    private String lyrics;

    public Responce(String lyrics) {
        String l = lyrics;
        l = l.replaceAll("\n\n","\n");
        this.lyrics = l;
    }

    public String getLyrics() {
        return lyrics;
    }
}
