/*
*** Sample Output:

---------------------------------------------------------------------
Mersenne Prime Calculator using Lucas-Lehmer Numbers
by Moose OMalley, v0.02
---------------------------------------------------------------------

*** TEST CASES to prove my code is correct:
* 2^13 - 1               8191
* 2^31 - 1               2147483647
* 2^61 - 1               2.305843009E+018
* 2^127 - 1              1.701411834E+038
* 2^1279 - 1             1.040793219E+0385

Starting from scratch:
* 2^3 - 1                7
* 2^5 - 1                31
* 2^7 - 1                127
* 2^13 - 1               8191
* 2^17 - 1               131071
* 2^19 - 1               524287
* 2^31 - 1               2147483647
* 2^61 - 1               2.305843009E+018
* 2^89 - 1               6.189700196E+026
* 2^107 - 1              1.622592768E+032
* 2^127 - 1              1.701411834E+038
* 2^521 - 1              6.864797660E+0156
* 2^607 - 1              5.311379928E+0182
* 2^1279 - 1             1.040793219E+0385
* 2^2203 - 1             1.475979915E+0663
* 2^2281 - 1             4.460875571E+0686
* 2^3217 - 1             2.591170860E+0968
:::::

*/
import java.math.BigInteger;


public class MersennePrimeLucasLehmer
{
   final static BigInteger TWO            = new BigInteger ("2");

   /*
      toScientificNotationStr (new BigInteger ("123456789"), 5)
      returns 1.2345E+08.
   */
   public static String toScientificNotationStr (BigInteger inBigInt, int sigDigits)
   {
      int length = inBigInt.toString().length();
      String bigResult = "";

      if (length <= sigDigits)
      {
         bigResult = inBigInt.toString();
      }

      else
      {
         String inBigIntSigDigitsStr = inBigInt.toString().substring (0, sigDigits);

         String inBigIntSigDigitsWithDecimalStr = inBigIntSigDigitsStr.charAt (0) + ".";
         for (int k = 1; k < sigDigits; k++)
            inBigIntSigDigitsWithDecimalStr += inBigIntSigDigitsStr.charAt (k);

         int remainingLength = length - 1;

         bigResult = inBigIntSigDigitsWithDecimalStr + "E+0" + remainingLength;
      }

      return bigResult;
   }

   public static boolean isPowerOfNumberPrime (int power)
   {
      // If Power is NOT prime, therefore 2^N - 1 cannot be prime.

      boolean result = false;
      //long lucasLehmerRemainder = 4;
      BigInteger lucasLehmerRemainder   = new BigInteger ("4");

      //long twoPowerMinus1 = (long) Math.pow (2, power) - 1;

      BigInteger twoPowerMinus1 = TWO.pow (power);
      twoPowerMinus1            = twoPowerMinus1.subtract (BigInteger.ONE);

      if (power > 1) //&& (isPrime (power) == true) )
      {
         // Get the remainder of the N-1 th Lucas-Lehmer number
         for (int k = 2; k < power; k++)
         {
            //lucasLehmerRemainder = (long) Math.pow (lucasLehmerRemainder, 2) - 2;
            //lucasLehmerRemainder = lucasLehmerRemainder % twoPowerMinus1;

            lucasLehmerRemainder = lucasLehmerRemainder.pow (2);
            lucasLehmerRemainder = lucasLehmerRemainder.subtract (TWO);
            lucasLehmerRemainder = lucasLehmerRemainder.mod (twoPowerMinus1);

            //System.out.println (lucasLehmerRemainder);
         }
      }

      //if (lucasLehmerRemainder == 0)
      if (lucasLehmerRemainder.toString().equals ("0") == true)
      {
         result = true; /// We have a Prime !!!

         System.out.println (String.format("%-25s", "* 2^" + power + " - 1")        +
                             toScientificNotationStr ( twoPowerMinus1, 10) +
                             //String.format ("%,d", (long) (Math.pow (2, power) - 1) )
                             //twoPowerMinus1.toString()
                             "");
      }

      return result;
   }


   public static void main (String[] args)
   {
      System.out.println ("---------------------------------------------------------------------");
      System.out.println ("Mersenne Prime Calculator using Lucas-Lehmer Numbers");
      System.out.println ("by Moose OMalley, v0.02");
      System.out.println ("---------------------------------------------------------------------");


      //System.out.println (toScientificNotationStr (new BigInteger ("123456789"), 5) );

      System.out.println ("");
      System.out.println ("*** TEST CASES to prove my code is correct: ");
      isPowerOfNumberPrime (13);  // 8191
      isPowerOfNumberPrime (31);  //
      isPowerOfNumberPrime (61);  //
      isPowerOfNumberPrime (127);  // Largest Prime calculated by hand, by Lucas in 1876.
      isPowerOfNumberPrime (1_279);  //
      //isPowerOfNumberPrime (23_209);  //
      //isPowerOfNumberPrime (20_996_011);  //


      System.out.println ("");
      System.out.println ("Starting from scratch:");

      for (int count = 0; count < 100_000; count++)
      {
         int power = 1 + (count * 2);
         //int power = 1 + count;

         //System.out.println (num + ". " + isPowerOfNumberPrime (num) );

         isPowerOfNumberPrime (power);

         //if (isPowerOfNumberPrime (power) == true)
         //   System.out.println ("2^" + power + " - 1   --> " + (long) (Math.pow (2, power) - 1) );
         //if (count % 1_000 == 0)
         //   System.out.print (".");
      }

      System.out.println ("DONE !");
   }
}
