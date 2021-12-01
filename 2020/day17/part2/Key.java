package day17.part2;

import java.util.List;
import java.util.Objects;

final class Key{
    int a , b , c, d;
    public Key(int a , int b , int c, int d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public int hashCode(){
         return Objects.hash(a, b, c, d);
    }

    public boolean equals(Object o){
         if(!(o instanceof Key))
              return false;

         Key k = (Key) o;

         return (k.a == a && k.b == b && k.c == c && k.d == d);
    }

    public static Key fromCoordinates(List<Integer> coords) {
        return new Key(coords.get(0), coords.get(1), coords.get(2), coords.get(3));
    }
}