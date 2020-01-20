import AuxTables.ArticuleAuthor;
import AuxTables.InstituteAuthorArticule;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Conacyt {
    private ArrayList<Institute> institutes;
    private ArrayList<Author> authors;
    private ArrayList<Articule> articules;

    public Conacyt() {
        this.institutes = new ArrayList<Institute>();
        this.authors = new ArrayList<Author>();
        this.articules = new ArrayList<Articule>();
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public void addAuthor(Author author){
        this.authors.add(author);
    }

    public void addInstitute(Institute institute) {
        institutes.add(institute);
    }

    public Institute lastInstitution(){
        if(institutes.isEmpty())
            return null;
        return institutes.get(institutes.size() - 1);
    }

    public void addArticule(Articule articule){
        this.articules.add(articule);
    }

    public Articule lastArticule(){
        if(this.articules.isEmpty())
            return null;
        return this.articules.get(this.articules.size() - 1);
    }

    public Author findAuthor(String name){
        return authors.stream().filter((aut)-> aut.getName().equals(name)).findFirst().get();
    }

    public String toString(){
        return "Soy conacyt";
    }

    public void articulesByAuthor(){
        System.out.println("1.--------------Articulos publicador por autor-----------------");
        this.articules.stream()
        .flatMap(articule->{
           return articule.getAuthors()
            .stream()
            .map((author)-> {
              return new ArticuleAuthor(articule.getTitle(), author.getName());
           });
        })
        .collect(Collectors.groupingBy(ArticuleAuthor::getAuthor, Collectors.counting()))
        .forEach((autor, counter)->{
            System.out.println(autor + " " + counter);
        });
    }

    public void authorsByArticule(){
        System.out.println("3.-------------------Autores por articulo.-----------------");
        this.articules
            .stream()
            .forEach((articule)->{
                System.out.println(articule.getTitle() + " " + articule.getAuthors().size());
            });
    }

    public void articulesWithStProfInv(){
        System.out.println("4.-------------------Articulos por año con estudiantes y profesores.-----------------");
        this.articules
            .stream()
            .filter((articule) ->{
                return articule.getAuthors()
                    .stream()
                    .filter(aut -> {
                        return aut.getAuthorType().equals("prof") || aut.getAuthorType().equals("est");
                    }).collect(Collectors.toList()).size() > 0 ;
            })
            .map((art)-> {
                return new Articule(null, art.getPublicationDate().substring(0, 4), null);
            })
            .filter(distinctByKey(Articule::getPublicationDate))
            .collect(Collectors.groupingBy(Articule::getPublicationDate, Collectors.counting()))
            .forEach((year, counter)-> {
                System.out.println(year + " " + counter);
            });
    }

    public void articulesJustWithEst(){
        System.out.println("5.-------------------Cantidad publicaciones solo por estudiantes-----------------");
        long counter = this.articules
            .stream()
            .filter((art)->{
                return art.getAuthors().stream().filter((aut)->{
                    return aut.getAuthorType().equals("est");
                }).collect(Collectors.toList())
                .size() > 0;
            })
            .filter(distinctByKey(Articule::getTitle)).count();
        System.out.println("Publicaciones: " + counter);
    }

    public void articulesJustWithProfInv(){
        long counter = this.articules.stream()
                .filter((art) ->{
                    return art.getAuthors().stream()
                        .filter(aut -> {
                            return aut.equals("prof") || aut.getAuthorType().equals("inv");
                        }).collect(Collectors.toList()).size() > 0;
                })
                .filter(distinctByKey(Articule::getTitle))
                .count();
        System.out.println("6.-------------------Cantidad de publicaciones solo por profesores.-----------------");
        System.out.println("Publicaciones: " + counter);
    }

    public void articulesByType(){
        System.out.println("7.-------------------Cantidad de articulos por tipo.-----------------");
        this.articules.stream()
            .filter(distinctByKey(Articule::getArticuleType))
            .collect(Collectors.groupingBy(Articule::getArticuleType, Collectors.counting()))
            .forEach((type, counter)->{
                System.out.println(type + " " + counter);
            });
    }

    public void articulesByInstitute(){
        System.out.println("8.-------------------Cantidad de articulos por institucion.-----------------");
        this.articules.stream()
            .flatMap((articule)->{
               return articule.getAuthors().stream()
                   .map((author)->{
                       return new InstituteAuthorArticule(author.getName(), articule.getTitle());
                   });
            })
            .map((instAutArt)->{
                Optional<Institute> insti = institutes.stream().filter((it)->{
                   Optional<Author> au = it.getAuthors().stream().filter(at->{
                      return at.getName().equals(instAutArt.getAuthor());
                   }).findFirst();
                   return au.isPresent();
                }).findFirst();
                if(insti.isPresent())
                    instAutArt.setInstitute(insti.get().getName());
                return instAutArt;
            })
            .filter(distinctByKey(InstituteAuthorArticule::getArticule))
            .collect(Collectors.groupingBy(InstituteAuthorArticule::getInstitute, Collectors.counting()))
            .forEach((institute, counter)->{
                System.out.println(institute + " " + counter);
            });
    }
}
