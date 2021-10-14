public class Word {
    public String wordTarget;
    public String wordExplain;
    public String wordType;

    Word(String wordTarget, String wordExplain, String wordType) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
        this.wordType = wordType;
    }

    @Override
    public String toString() {
        return this.wordTarget;
    }


}
