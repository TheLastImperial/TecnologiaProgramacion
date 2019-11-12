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

    public Conjunto<E> difference(Conjunto<E> input){
        Conjunto<E> result = (Conjunto<E>) this.clone();
        for (int i = 0; i < input.size(); i++)
            result.remove(input.get(i));
        return result;
    }

    public Conjunto<E> intersection(Conjunto<E> input){
        Conjunto<E> result = new Conjunto<E>();
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

    public Conjunto<E> union(Conjunto<E> input){
        Conjunto<E> result = (Conjunto<E>) this.clone();

        for(int i = 0; i < input.size(); i++)
            if(!this.contains(input.get(i)))
                result.add(input.get(i));

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
}
