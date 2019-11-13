import java.util.ArrayList;
import java.util.Collection;

public class Conjunto<E> extends ArrayList {

    public Conjunto(){
        super();
    }

    public Conjunto(E[] arr){
        super();
        for (int i = 0; i< arr.length; i++)
            this.add(arr[i]);
    }

    public Conjunto(Collection<E> coll){
        super();
        ArrayList list = new ArrayList(coll);
        for (int i = 0; i< list.size(); i++)
            this.add(list.get(i));
    }

    public boolean add(Object ele){
        if(!this.contains(ele))
            return super.add(ele);
        return false;
    }

    public Conjunto<?> difference(Conjunto<?> input){
        Conjunto<?> result = (Conjunto<?>) this.clone();
        for (int i = 0; i < input.size(); i++)
            result.remove(input.get(i));
        return result;
    }

    public Conjunto<?> intersection(Conjunto<?> input){
        Conjunto<?> result = new Conjunto();
        if(this.size() > input.size()){
            for (int i = 0; i < this.size(); i++){
                if(input.contains(this.get(i)))
                    result.add(this.get(i));
            }
        }else{
            for (int i = 0; i < input.size(); i++){
                if(this.contains(input.get(i)))
                    result.add(input.get(i));
            }
        }
        return result;
    }

    public Conjunto<?> union(Conjunto<?> input){
        Conjunto<?> result = (Conjunto<?>) this.clone();

        for(int i = 0; i < input.size(); i++)
            if(!this.contains(input.get(i)))
                result.add(input.get(i));

        return result;
    }

    public Conjunto<?> complement(Conjunto<?> universe){
        return universe.difference(this);
    }

    public boolean subset(Conjunto<E> cInput){
        boolean flag = true;
        for (int i = 0; i< cInput.size(); i++){
            if(!this.contains(cInput.get(i))){
                flag = false;
                break;
            }
        }
        return flag;
    }

    public boolean subsetP(Conjunto<E> cInput){
        boolean flag = true;
        if(cInput.size() >= this.size())
            return false;
        for (int i=0; i < cInput.size(); i++){
            if(!this.contains(cInput.get(i))){
                flag = false;
                break;
            }
        }
        return flag;
    }

    public Conjunto<Par> productC(Conjunto<?> cInput){
        Conjunto<Par> result = new Conjunto<Par>();
        boolean flag = false;
        for (int i = 0; i < this.size(); i++){
            for (int j = 0; j < cInput.size(); j++){
                Par p = new Par(this.get(i), cInput.get(j));
                flag = result.add(p);
            }
        }
        return result;
    }

    public Conjunto<Conjunto<E>> pow(){
        Conjunto<Conjunto<E>> result = new Conjunto<Conjunto<E>>();
        result.add(new Conjunto<E>());

        return result;
    }

    public boolean contains(Object ele){
        return super.contains(ele);
    }

    public boolean empty(){
        return super.isEmpty();
    }

    public int size(){
        return super.size();
    }

    public String toString(){
        String result = "";
        for (int i = 0; i < this.size(); i++){
            result += this.get(i);
            if(i < this.size() - 1)
                result += ",";
        }
        return "{" + result + "}";
    }

    public void print() {
        System.out.println(this.toString());
    }

    public void print(String setName) {
        System.out.println(setName + " : " + this);
    }
}
