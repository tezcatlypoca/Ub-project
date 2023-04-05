/** Votre premier programme Java */
class FirstApp {
   static int fib( int x) 
   { if (x < 2) return x;
     return fib(x-1) + fib(x-2);
   }
   public static void main (String[] args){
    System.out.println("Hello World");
    System.out.println("Fib(5)=");
    System.out.println( fib(5));
    for( int i=0; i<14; i++)
    {
    System.out.print("Fib(");
    System.out.print(i);
    System.out.print(")=");
    System.out.println( fib(i));
    }
   }
}

