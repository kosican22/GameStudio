package sk.ness.puzzle;


public class Winner {

    private int id;
    private String name;
    private long sec;

    public Winner(int id, String name, long sec) {
        this.id=id;
        this.name = name;
        this.sec = sec;
    }

    public Winner(String name, long sec) {
        this.name = name;
        this.sec = sec;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSec() {
        return sec;
    }

    public void setSec(long sec) {
        this.sec = sec;
    }

    @Override
    public String toString() {
        return "Winner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sec=" + sec +
                '}';
    }
}
