import java.util.ArrayList;

public class Conacyt {
    private ArrayList<Institute> institutes;

    public Conacyt() {
        this.institutes = new ArrayList<Institute>();
    }

    public void addInstitute(Institute institute) {
        institutes.add(institute);
    }
    public Institute lastInstitution(){
        if(institutes.isEmpty())
            return null;
        return institutes.get(institutes.size() - 1);
    }

    public String toString(){
        return "Soy conacyt";
    }
}
