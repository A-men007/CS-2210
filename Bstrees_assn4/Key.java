public class Key {

    String name, kind;

    public Key(String word, String type) {
        this.name = word.toLowerCase();
        this.kind = type;
    }

    public String getName() {
        return this.name;
    }

    public String getKind() {
        return this.kind;
    }

    public int compareTo(Key k) {
        if (k.kind == this.kind && k.name == this.name)
            return 0;
        if (this.name.compareTo(k.name) < 0 || (this.name==k.name && this.kind.compareTo(k.kind) < 0))
            return -1;

        return 1;
    }
}
