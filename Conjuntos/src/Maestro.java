public class Maestro extends SimpleObj{
    private String sepId;
    public Maestro(String name, int age, String sepId){
        super(name, age);
        this.sepId = sepId;
    }

    public String getSepId() {
        return sepId;
    }

    public void setSepId(String sepId) {
        this.sepId = sepId;
    }

    public String toString(){
        return "(" + this.getName() + "," + this.getAge() + "," + this.getSepId() +")";
    }
}
