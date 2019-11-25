import org.apache.commons.lang3.SerializationUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;

public class Conjunto<E> extends ArrayList<E> {

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
        ArrayList<E> list = new ArrayList<E>(coll);
        for (int i = 0; i< list.size(); i++)
            this.add(list.get(i));
    }

    public boolean add(E ele){
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

    public Conjunto<E> union(Conjunto<?> input){
        Conjunto<E> result = (Conjunto<E>) this.clone();

        for(int i = 0; i < input.size(); i++)
            if(!this.contains(input.get(i)))
                result.add((E)input.get(i));

        return result;
    }

    public Conjunto<E> complement(Conjunto<E> universe){
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
        Conjunto<E> loop = (Conjunto<E>)this.clone();
        Conjunto<E> c = new Conjunto<E>();
        while(!loop.empty()){
            Conjunto<E> c2 = (Conjunto<E>)c.clone();
            for(int i = 0; i < loop.size(); i++){
                c2.add(loop.get(i));
                result.add(c2);
                c2 = (Conjunto<E>)c.clone();
            }
            c = new Conjunto<E>();
            c.add(loop.get(0));
            loop.remove(0);
        }
        if(!this.empty())
            result.add((Conjunto<E>)this.clone());
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        Conjunto<E> comp = (Conjunto<E>) o;

        if(comp.size() != this.size())
            return false;

        for (int i = 0; i < this.size(); i++)
            if(!this.get(i).equals(comp.get(i)))
                return false;
        return true;
    }

    public Conjunto<E> clone(){
        return (Conjunto<E>) SerializationUtils.clone(this);
    }

    public Conjunto<E> clone2(){
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(this);
            ByteArrayInputStream bais = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(bais);
            return (Conjunto<E>) objectInputStream.readObject();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
/*
    public Conjunto<E> clone3(){
        Conjunto<E> result = new Conjunto<E>();
        for(int i = 0; i< this.size(); i++)
            result.add(((E)this.get(i)).clone());
        return result;
    }
*/
    public void print() {
        System.out.println(this.toString());
    }

    public void print(String setName) {
        System.out.println(setName + " : " + this);
    }
}
