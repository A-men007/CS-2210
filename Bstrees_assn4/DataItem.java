public class DataItem {

    Key theKey;
    String content;

    public DataItem(Key k, String data){
        this.theKey = k;
        this.content = data;
    }

    public Key getKey(){
        return this.theKey;
    }

    public String getContent(){
        return this.content;
    }
}
