public class Alumno extends SimpleObj {
    private String controlNum;
    public Alumno(String name, int age, String controlNum){
        super(name, age);
        this.controlNum = controlNum;
    }

    public String getControlNum() {
        return controlNum;
    }

    public void setControlNum(String controlNum) {
        this.controlNum = controlNum;
    }

    public String toString(){
        return "(" + this.getName() + "," + this.getAge() + "," + this.getControlNum() +")";
    }
}
