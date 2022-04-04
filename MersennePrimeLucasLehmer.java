public class MersennePrimeLucasLehmer
{

   public static boolean isPowerOfNumberPrime (int power)
   {
      // If Power is NOT prime, therefore 2^N - 1 cannot be prime.

      boolean result = false;
      long lucasLehmerRemainder = 4;

      long twoPowerMinus1 = (long) Math.pow (2, power) - 1;

      if (power > 1) //&& (isPrime (power) == true) )
      {
         // Get the remainder of the N-1 th Lucas-Lehmer number
         for (int k = 2; k < power; k++)
         {
            lucasLehmerRemainder = (long) Math.pow (lucasLehmerRemainder, 2) - 2;
            lucasLehmerRemainder = lucasLehmerRemainder % twoPowerMinus1;

            //System.out.println (lucasLehmerRemainder);
         }
      }

      if (lucasLehmerRemainder == 0)
         result = true; /// We have a Prime !!!

      return result;
   }


   public static void main (String[] args)
   {
      System.out.println (isPowerOfNumberPrime (13) );  // 8191
      System.out.println (isPowerOfNumberPrime (31) );  //
      System.out.println (isPowerOfNumberPrime (61) );  //
      //System.out.println (isPowerOfNumberPrime (127) );  // Largest Prime calculated by hand, by Lucas in 1876.

      for (int count = 0; count < 100_000; count++)
      {
         int power = 1 + (count * 2);
         //int power = 1 + count;

         //System.out.println (num + ". " + isPowerOfNumberPrime (num) );

         if (isPowerOfNumberPrime (power) == true)
            System.out.println ("2^" + power + " - 1   --> " + (long) (Math.pow (2, power) - 1) );
         if (count % 1_000 == 0)
            System.out.print (".");
      }

      System.out.println ("DONE !");
   }
}
