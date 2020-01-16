package SegundoIntento;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DB {
    private ArrayList<Table> table;
    public DB(){
        this.table = new ArrayList<Table>();
    }

    public ArrayList<Table> getTable() {
        return table;
    }

    public void setTable(ArrayList<Table> table) {
        this.table = table;
    }
    public Table findByAuthor(String autName, String instName){
        Optional<Table> result =  this.table
            .stream()
            .filter((current) -> {
                return current.authorName.equals(autName) && current.instituteName.equals(instName);
            }).findFirst();
        if(!result.isPresent())
            return null;
        else
            return result.get();
    }

    public Table findByAuthor(String autName){
        Optional<Table> result =  this.table
                .stream()
                .filter((current) -> {
                    return current.authorName.equals(autName);
                }).findFirst();
        if(!result.isPresent())
            return null;
        else
            return result.get();
    }

    public Table findArticule(String title, String autName){
        Optional<Table> result =  this.table
                .stream()
                .filter((current) -> {
                    return title.equals(current.articuleTitle) && autName.equals(current.authorName);
                }).findFirst();
        if(!result.isPresent())
            return null;
        else
            return result.get();
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public void articulesByAuthor(){
        Map<String, Long> result = this.table
            .stream()
            .filter((tab) -> tab.articuleTitle != null)
            .collect(Collectors.groupingBy(Table::getAuthorName, Collectors.counting()));
        System.out.println("1.--------------Articulos publicador por autor-----------------");
        result.forEach((author, counter)->{
            System.out.println(author + " " + counter);
        });
    }

    public void iDontUnderstand(){
        System.out.println("2.-------------------Colaboradores por año.-----------------");
    }

    public void authorsByArticule(){
        Map<String, Long> result = this.table
                .stream()
                .filter((tab) -> tab.articuleTitle != null)
                .collect(Collectors.groupingBy(Table::getArticuleTitle, Collectors.counting()));
        System.out.println("3.-------------------Autores por articulo.-----------------");
        result.forEach((articule, counter)->{
            System.out.println(articule + " " + counter);
        });
    }

    public void articulesWithStProfInv(){
        System.out.println("4.-------------------Articulos por año con estudiantes y profesores.-----------------");
        this.table
            .stream()
            .filter((tab) ->{
                return tab.authorType.equals("prof") || tab.authorType.equals("est");
            })
            .map((tab)-> {
                Table t = new Table();
                t.publicationDate = tab.publicationDate.substring(0, 4);
                t.articuleTitle = tab.articuleTitle;
                return t;
            })
            .filter(distinctByKey(Table::getArticuleTitle))
            .collect(Collectors.groupingBy(Table::getPublicationDate, Collectors.counting()))
            .forEach((year, counter)-> {
               System.out.println(year + " " + counter);
            });
    }

    public void articulesJustWithEst(){
        System.out.println("5.-------------------Cantidad publicaciones solo por estudiantes-----------------");
        long counter = this.table
            .stream()
            .filter((tab)->{
                return tab.articuleTitle != null && tab.authorType.equals("est");
            })
            .filter(distinctByKey(Table::getArticuleTitle)).count();
        System.out.println("Publicaciones: " + counter);
    }

    public void articulesJustWithProfInv(){
        long counter = this.table.stream()
            .filter((tab) ->{
               return tab.articuleTitle != null && tab.authorType.equals("prof");
            })
            .filter(distinctByKey(Table::getArticuleTitle))
            .count();
        System.out.println("6.-------------------Cantidad de publicaciones solo por profesores.-----------------");
        System.out.println("Publicaciones: " + counter);
    }

    public void articulesByType(){
        System.out.println("7.-------------------Cantidad de articulos por tipo.-----------------");
        this.table.stream()
            .filter((tab)-> tab.articuleTitle != null && tab.articuleType != null)
            .filter(distinctByKey(Table::getArticuleType))
            .collect(Collectors.groupingBy(Table::getArticuleType, Collectors.counting()))
            .forEach((type, counter)->{
                System.out.println(type + " " + counter);
            });
    }

    public void articulesByInstitute(){
        System.out.println("8.-------------------Cantidad de articulos por institucion.-----------------");
        this.table.stream()
            .filter((tab)->{
                return tab.articuleTitle != null && tab.instituteName != null;
            })
            .filter(distinctByKey(Table::getInstituteName))
            .collect(Collectors.groupingBy(Table::getInstituteName, Collectors.counting()))
            .forEach((institute, counter)->{
                System.out.println(institute + " " + counter);
            });
    }

    public void articulesByYear(){
        System.out.println("9.-------------------Cantidad de articulos por año.-----------------");
        this.table.stream()
            .filter((tab)-> {
                return tab.articuleTitle != null;
            })
            .filter(distinctByKey(Table::getArticuleTitle))
            .map((tab)->{
                Table t = new Table();
                t.publicationDate = tab.publicationDate.substring(0, 4);
                return t;
            })
            .collect(Collectors.groupingBy(Table::getPublicationDate, Collectors.counting()))
            .forEach((year, counter)->{
                System.out.println( year + " " + counter);
            });
    }
}
